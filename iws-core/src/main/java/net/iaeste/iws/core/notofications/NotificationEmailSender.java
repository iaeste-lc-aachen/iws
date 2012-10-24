/*
 * =============================================================================
 * Copyright 1998-2012, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-core) - net.iaeste.iws.core.notifications.NotificationEmailSender
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */

package net.iaeste.iws.core.notofications;

import net.iaeste.iws.api.enums.NotificationMessageStatus;
import net.iaeste.iws.api.enums.NotificationType;
import net.iaeste.iws.common.emails.EmailMessage;
import net.iaeste.iws.common.utils.Observable;
import net.iaeste.iws.common.utils.Observer;
import net.iaeste.iws.persistence.UserNotificationDao;
import net.iaeste.iws.persistence.entities.NotificationMessageEntity;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;
import java.util.Date;
import java.util.List;

/**
 * @author Pavel Fiala / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since 1.7
 */
public class NotificationEmailSender implements Observer {
    @Resource(mappedName = "iws-EmailQueue")
    private Queue queue;

    @Resource(mappedName = "iws-QueueConnectionFactory")
    private QueueConnectionFactory queueConnectionFactory;

    QueueConnection connection = null;
    QueueSender sender = null;
    QueueSession session = null;

    private final UserNotificationDao dao;

    public NotificationEmailSender(final UserNotificationDao dao) {
        this.dao = dao;

        QueueConnection queueConnection = null;
        try {
            queueConnection = queueConnectionFactory.createQueueConnection();
            queueConnection.start();
            session = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            sender = session.createSender(queue);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Observable subject) {
        processMessages();
    }

    private void processMessages() {
        List<NotificationMessageEntity> messages = dao.findNotificationMessages(NotificationType.EMAIL, NotificationMessageStatus.NEW, new Date());
        //TODO: do the grouping somewhere/somehow
        String subject = "IAESTE IW notification";

        for(NotificationMessageEntity message : messages) {
            dao.updateNotificationMessageStatus(message, NotificationMessageStatus.PROCESSING);

            try {
                ObjectMessage msg = session.createObjectMessage();
                EmailMessage emsg = new EmailMessage();
                emsg.setTo(message.getUser().getUserName());
                emsg.setSubject(subject);
                emsg.setMessage(message.getMessage());
                msg.setObjectProperty("emailMessage", emsg);

                sender.send(msg);
                dao.updateNotificationMessageStatus(message, NotificationMessageStatus.SENT);
            } catch (JMSException e) {
                //do something, log...
            }
        }
    }
}