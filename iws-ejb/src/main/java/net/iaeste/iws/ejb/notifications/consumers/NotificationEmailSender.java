/*
 * =============================================================================
 * Copyright 1998-2013, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-ejb) - net.iaeste.iws.ejb.notifications.consumers.NotificationEmailSender
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.ejb.notifications.consumers;

import net.iaeste.iws.api.constants.IWSErrors;
import net.iaeste.iws.api.enums.NotificationFrequency;
import net.iaeste.iws.api.exceptions.IWSException;
import net.iaeste.iws.common.notification.NotificationField;
import net.iaeste.iws.common.notification.NotificationType;
import net.iaeste.iws.common.utils.Observable;
import net.iaeste.iws.common.utils.Observer;
import net.iaeste.iws.ejb.emails.EmailMessage;
import net.iaeste.iws.ejb.ffmq.MessageServer;
import net.iaeste.iws.ejb.notifications.NotificationMessageGenerator;
import net.iaeste.iws.ejb.notifications.NotificationMessageGeneratorFreemarker;
import net.iaeste.iws.persistence.AccessDao;
import net.iaeste.iws.persistence.NotificationDao;
import net.iaeste.iws.persistence.entities.UserEntity;
import net.iaeste.iws.persistence.entities.UserNotificationEntity;
import net.iaeste.iws.persistence.views.NotificationJobTasksView;
import net.timewalker.ffmq3.FFMQConstants;
import org.apache.log4j.Logger;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * The Class requires an EJB framework to properly work. For this reason, large
 * parts of the code is commented out.
 *
 * @author  Pavel Fiala / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 * @noinspection ObjectAllocationInLoop
 */
public class NotificationEmailSender implements Observer {
    private Long id = null;
    private static Integer ATTEMPTS_LIMIT = 3;

    private final NotificationMessageGenerator messageGenerator;
    private final NotificationDao dao;
    private final AccessDao accessDao;

    private static final Logger LOG = Logger.getLogger(NotificationEmailSender.class);

    //Need to be EJB to use @resource
//    @Resource(mappedName = "iwsEmailQueue")
    private static String QUEUE_NAME = "queue/iwsEmailQueue";
    private Queue queue;

//    @Resource(mappedName = "iwsQueueConnectionFactory")
    private static String QUEUE_FACTORY_NAME = "factory/iwsQueueConnectionFactory";
    private QueueConnectionFactory queueConnectionFactory;

    private QueueConnection queueConnection = null;
    private QueueSender sender = null;
    private QueueSession session = null;

    //TODO all consumers have to have same constructor parameters -> any idea how to make it dynamic?
    public NotificationEmailSender(final NotificationDao dao, final AccessDao accessDao) {
        this.dao = dao;
        this.accessDao = accessDao;
        this.messageGenerator = new NotificationMessageGeneratorFreemarker();

        initializeQueue();
    }

    private void initializeQueue() {
        try {
            final Context context = new InitialContext();

            queueConnectionFactory = (QueueConnectionFactory)context.lookup(QUEUE_FACTORY_NAME);
            queueConnection = queueConnectionFactory.createQueueConnection();
            queueConnection.start();

            queue = (Queue)context.lookup(QUEUE_NAME);
            context.close();

            session = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            sender = session.createSender(queue);
            //TODO added for FFMQ, keep it for glassfish?
            sender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        } catch (NamingException |JMSException e) {
            throw new IWSException(IWSErrors.ERROR, "Queue sender (NotificationEmailSender) initialization failed.", e);
        }
    }

    private Hashtable<String, String> getFfmqEnvironment() {
//        try {
            final Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, FFMQConstants.JNDI_CONTEXT_FACTORY);
            env.put(Context.PROVIDER_URL, "vm://"+ MessageServer.engineName);
            return env;
    }
    private void initializeFfmqQueue() {
        try {
            final Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, FFMQConstants.JNDI_CONTEXT_FACTORY);
            env.put(Context.PROVIDER_URL, "vm://"+ MessageServer.engineName);
            //connection using 'vm://' protocol should have better performance, if not working, use tcp connection instead
//            env.put(Context.PROVIDER_URL, "tcp://" + MessageServer.listenAddr + ":" + MessageServer.listenPort);
            final Context context = new InitialContext(env);

            queueConnectionFactory = (QueueConnectionFactory)context.lookup(FFMQConstants.JNDI_QUEUE_CONNECTION_FACTORY_NAME);
            // end FFMQ specific

            queueConnection = queueConnectionFactory.createQueueConnection();
            queueConnection.start();

            //FFMQ specific
            queue = (Queue)context.lookup(MessageServer.queueNameForIws);
            context.close();
            // end FFMQ specific

            session = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            sender = session.createSender(queue);
            //TODO added for FFMQ, keep it for glassfish?
            sender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        } catch (NamingException|JMSException e) {
            throw new IWSException(IWSErrors.ERROR, "Queue sender (NotificationEmailSender) initialization failed.", e);
        }
    }

    /**
     * Method for unsubscibing from queue and closing connection
     */
    public void stop() {
        try {
            sender.close();
            session.close();
            queueConnection.stop();
        } catch (JMSException e) {
            throw new IWSException(IWSErrors.ERROR, "Queue recipient stopping failed.", e);
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Observable subject) {
        processMessages();
    }

    private void processMessages() {
        final List<NotificationJobTasksView> jobTasks = dao.findUnprocessedNotificationJobTaskByConsumerId(id, ATTEMPTS_LIMIT);
        for (final NotificationJobTasksView jobTask : jobTasks) {
            try {
                final ByteArrayInputStream inputStream = new ByteArrayInputStream(jobTask.getObject());
                final ObjectInputStream objectStream = new ObjectInputStream(inputStream);
                final Map<NotificationField, String> fields = (Map<NotificationField, String>) objectStream.readObject();
                boolean processed = false;
                if (fields != null) {
                    processTask(fields, jobTask.getNotificationType());//toto processed = processTask
                    processed = true;
                }
                dao.updateNotificationJobTask(jobTask.getId(), processed, jobTask.getattempts()+1);
            } catch (IOException |ClassNotFoundException ignored) {
                //TODO write to log and skip the task or throw an exception?
            }
        }
    }

    private boolean processTask(final Map<NotificationField, String> fields, final NotificationType type) {
        //TODO marking task as processed depending on the successful sending to all recepients might be a problem. if it
        //     fails for one user, even those already sent users will be included during next processing. Just failed user
        //     NotificationType and message could be saved for furthere processing/investigation

        boolean ret = false;
        final List<UserEntity> recipients = getRecipients(fields, type);
        for (final UserEntity recipient : recipients) {
            final UserNotificationEntity userSetting;
            try {
                userSetting = dao.findUserNotificationSetting(recipient, type);
                //Processing of other notification than 'IMMEDIATELY' ones will be triggered by a timer and all required information
                //should be get from DB directly according to the NotificationType
                if (userSetting.getFrequency() == NotificationFrequency.IMMEDIATELY) {
                    try {
                        final ObjectMessage msg = session.createObjectMessage();
                        final EmailMessage emsg = new EmailMessage();
                        emsg.setTo(recipient.getUserName());
                        final Map<String, String> messageData = messageGenerator.generateFromTemplate(fields, type);
                        emsg.setSubject(messageData.get("title"));
                        emsg.setMessage(messageData.get("body"));
                        msg.setObject(emsg);

                        sender.send(msg);
                        ret = true;
                    } catch (IWSException e) {
                        LOG.error("Notification message generating failed", e);
                    } catch (JMSException e) {
                        //do something, log or exception?
                        LOG.error("Error during sending notification message to JMS queue", e);
                    }
                }
            } catch (IWSException ignore) {
                LOG.warn("User " + recipient.getId() + " has not proper notification setting for notification type " + type);
            }
        }
        return ret;
    }

    //TODO probably not necessary to have the whole UserEntity, maybe just List<string> (emails) would be enough
    //     for the current notification processing
    private List<UserEntity> getRecipients(final Map<NotificationField, String> fields, final NotificationType type) {
        final List<UserEntity> result = new ArrayList<>();
        switch (type) {
            case ACTIVATE_USER:
            case RESET_PASSWORD:
            case RESET_SESSION:
            case UPDATE_USERNAME:
                final UserEntity user = accessDao.findUserByUsername(fields.get(NotificationField.EMAIL));
                if (user != null)
                    result.add(user);
                break;
        }
        return result;
    }

}
