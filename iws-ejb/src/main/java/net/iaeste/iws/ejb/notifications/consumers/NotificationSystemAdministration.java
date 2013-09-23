/*
 * =============================================================================
 * Copyright 1998-2013, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-ejb) - net.iaeste.iws.ejb.notifications.consumers.NotificationSystemAdministration
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

import net.iaeste.iws.api.constants.IWSConstants;
import net.iaeste.iws.api.enums.GroupType;
import net.iaeste.iws.api.enums.NotificationFrequency;
import net.iaeste.iws.api.enums.UserStatus;
import net.iaeste.iws.common.notification.NotificationField;
import net.iaeste.iws.common.notification.NotificationType;
import net.iaeste.iws.common.utils.Observable;
import net.iaeste.iws.common.utils.Observer;
import net.iaeste.iws.persistence.AccessDao;
import net.iaeste.iws.persistence.MailingListDao;
import net.iaeste.iws.persistence.NotificationDao;
import net.iaeste.iws.persistence.entities.UserEntity;
import net.iaeste.iws.persistence.entities.UserNotificationEntity;
import net.iaeste.iws.persistence.entities.mailing_list.MailingListEntity;
import net.iaeste.iws.persistence.entities.mailing_list.MailingListMembershipEntity;
import net.iaeste.iws.persistence.jpa.AccessJpaDao;
import net.iaeste.iws.persistence.jpa.MailingListJpaDao;
import net.iaeste.iws.persistence.jpa.NotificationJpaDao;
import net.iaeste.iws.persistence.views.NotificationJobTasksView;

import javax.persistence.EntityManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Notification consumer for administration the system (mailing list, aliases, etc.)
 *
 * @author  Pavel Fiala / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 * @noinspection ObjectAllocationInLoop
 */
public class NotificationSystemAdministration implements Observer {
    private Long id = null;
    private static final Integer ATTEMPTS_LIMIT = 3;

    private final AccessDao accessDao;
    private final MailingListDao mailingListDao;
    private final NotificationDao notificationDao;

    public NotificationSystemAdministration(final EntityManager iwsEntityManager, final EntityManager mailingEntityManager) {
        notificationDao = new NotificationJpaDao(iwsEntityManager);
        accessDao = new AccessJpaDao(iwsEntityManager);
        //mailingListDao = new MailingListJpaDao(mailingEntityManager);
        mailingListDao = new MailingListJpaDao(iwsEntityManager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Observable subject) {
        processMessages();
    }

    private void processMessages() {
        //TODO this DB request doesn't work just after the task is persisted, I (Pavel) have no idea why. once it's solved, some TODOs in NotificationManager(Bean) could fixed
        final List<NotificationJobTasksView> jobTasks = notificationDao.findUnprocessedNotificationJobTaskByConsumerId(id, ATTEMPTS_LIMIT);
        for (final NotificationJobTasksView jobTask : jobTasks) {
            try {
                final ByteArrayInputStream inputStream = new ByteArrayInputStream(jobTask.getObject());
                final ObjectInputStream objectStream = new ObjectInputStream(inputStream);
                final Map<NotificationField, String> fields = (Map<NotificationField, String>) objectStream.readObject();
                NotificationProcessTaskStatus processedStatus = NotificationProcessTaskStatus.ERROR;
                if (fields != null) {
                    processedStatus = processTask(fields, jobTask.getNotificationType());
                }
                final boolean processed = (processedStatus != NotificationProcessTaskStatus.ERROR);
                notificationDao.updateNotificationJobTask(jobTask.getId(), processed, jobTask.getattempts() + 1);
            } catch (IOException|ClassNotFoundException ignored) {
                //TODO write to log and skip the task or throw an exception?
            }
        }
    }

    private NotificationProcessTaskStatus processTask(final Map<NotificationField, String> fields, final NotificationType type) {
        NotificationProcessTaskStatus ret = NotificationProcessTaskStatus.NOT_FOR_ME;
        switch (type) {
            case NEW_USER:
                prepareNewUserNotificationSetting(fields.get(NotificationField.EMAIL));
                ret = NotificationProcessTaskStatus.OK;
                break;
            case USER_ACTIVATED:
                prepareActivatedUserNotificationSetting(fields.get(NotificationField.EMAIL));
                ret = NotificationProcessTaskStatus.OK;
                break;
            case NEW_GROUP:
                createGroupMailinglist(fields.get(NotificationField.GROUP_NAME), fields.get(NotificationField.COUNTRY_NAME), fields.get(NotificationField.GROUP_TYPE), fields.get(NotificationField.GROUP_EXTERNAL_ID));
                ret = NotificationProcessTaskStatus.OK;
                break;
            case PROCESS_MAILING_LIST:
                updateGroupMailingList(fields.get(NotificationField.GROUP_NAME), fields.get(NotificationField.COUNTRY_NAME), fields.get(NotificationField.GROUP_TYPE), fields.get(NotificationField.GROUP_EXTERNAL_ID));
                ret = NotificationProcessTaskStatus.OK;
                break;
            case CHANGE_IN_GROUP_MEMBERS:
                updateMailingListSubscription(fields.get(NotificationField.GROUP_TYPE), fields.get(NotificationField.GROUP_EXTERNAL_ID), fields.get(NotificationField.EMAIL), fields.get(NotificationField.USER_STATUS), fields.get(NotificationField.ON_PRIVATE_LIST), fields.get(NotificationField.ON_PUBLIC_LIST));
                ret = NotificationProcessTaskStatus.OK;
                break;
        }
        return ret;
    }

    private void prepareNewUserNotificationSetting(final String username) {
        final UserEntity user = accessDao.findUserByUsername(username);
        if (user != null) {
            final UserNotificationEntity userNotification = new UserNotificationEntity(user, NotificationType.ACTIVATE_USER, NotificationFrequency.IMMEDIATELY);
            notificationDao.persist(userNotification);
        }
    }

    private void prepareActivatedUserNotificationSetting(final String username) {
        final UserEntity user = accessDao.findUserByUsername(username);
        final Set<NotificationType> notificationTypes = new HashSet<>();
        notificationTypes.add(NotificationType.UPDATE_USERNAME);
        notificationTypes.add(NotificationType.RESET_PASSWORD);
        notificationTypes.add(NotificationType.RESET_SESSION);

        if (user != null) {
            for (final NotificationType notificationType : notificationTypes) {
                UserNotificationEntity userNotification = notificationDao.findUserNotificationSetting(user, notificationType);
                if (userNotification == null) {
                    userNotification = new UserNotificationEntity(user, notificationType, NotificationFrequency.IMMEDIATELY);
                    notificationDao.persist(userNotification);
                }
            }
        }
    }

    private void createGroupMailinglist(final String groupName, final String countryName, final String type, final String groupExternalId) {
        final GroupType groupType;
        try {
            groupType = GroupType.valueOf(type);
        } catch (IllegalArgumentException ignored) {
            return;
        }

        if (hasPublicList(groupType)) {
            createMailingList(groupExternalId, getPublicListAddress(groupType, groupName, countryName), false);
        }

        if (hasPrivateList(groupType)) {
            createMailingList(groupExternalId, getPrivateListAddress(groupType, groupName, countryName), false);
        }
    }

    private void updateGroupMailingList(final String groupName, final String countryName, final String type, final String groupExternalId) {
        final GroupType groupType;
        try {
            groupType = GroupType.valueOf(type);
        } catch (IllegalArgumentException ignored) {
            return;
        }

        if (hasPublicList(groupType)) {
            final MailingListEntity publicList = mailingListDao.findPublicMailingList(groupExternalId);
            if (publicList != null) {
                publicList.setListAddress(getPublicListAddress(groupType, groupName, countryName));
                publicList.setModified(new Date());
                mailingListDao.persist(publicList);
            } else {
                //TODO trying to modify non-existing list -> throw exception? ignore?
            }
        }

        if (hasPrivateList(groupType)) {
            final MailingListEntity privateList = mailingListDao.findPrivateMailingList(groupExternalId);
            if (privateList != null) {
                privateList.setListAddress(getPrivateListAddress(groupType, groupName, countryName));
                privateList.setModified(new Date());
                mailingListDao.persist(privateList);
            } else {
                //TODO trying to modify non-existing list -> throw exception? ignore?
            }
        }
    }

    private void createMailingList(final String groupId, final String address, final boolean privateList) {
        final MailingListEntity list = new MailingListEntity();
        list.setExternalId(groupId);
        list.setListAddress(address);
        list.setPrivateList(privateList);

        mailingListDao.persist(list);
    }

    private void updateMailingListSubscription(final String type, final String groupExternalId, final String emailAddress, final String userStatus, final String onPrivateList, final String onPublicList) {
        final GroupType groupType;
        try {
            groupType = GroupType.valueOf(type);
        } catch (IllegalArgumentException ignored) {
            return;
        }

        //only active users can be subscribed to mailing lists
        final boolean subscribedToPublicList = onPublicList.equals("true") && UserStatus.ACTIVE.name().equals(userStatus);
        final boolean subscribedToPrivateList = onPrivateList.equals("true") && UserStatus.ACTIVE.name().equals(userStatus);

        if (hasPublicList(groupType)) {
            final MailingListEntity publicMailingList = mailingListDao.findPublicMailingList(groupExternalId);
            updateListSubscription(publicMailingList, emailAddress, subscribedToPublicList);
        }

        if (hasPrivateList(groupType)) {
            final MailingListEntity privateMailingList = mailingListDao.findPrivateMailingList(groupExternalId);
            updateListSubscription(privateMailingList, emailAddress, subscribedToPrivateList);
        }
    }

    private void updateListSubscription(final MailingListEntity mailingList, final String emailAddress, final boolean onList) {
        if (mailingList != null) {
            final MailingListMembershipEntity subscription = mailingListDao.findMailingListSubscription(mailingList.getId(), emailAddress);
            if (subscription != null && !onList) {
                mailingListDao.delete(subscription);
            } else if (subscription == null && onList) {
                createListSubscription(mailingList, emailAddress);
            }
        }
    }

    private void createListSubscription(final MailingListEntity mailingList, final String emailAddress) {
        final MailingListMembershipEntity subscription = new MailingListMembershipEntity();
        subscription.setMailingList(mailingList);
        subscription.setMember(emailAddress);
        mailingListDao.persist(subscription);
    }

    private boolean hasPublicList(final GroupType type) {
        boolean result = false;
        switch (type) {
            //TODO general secretary
            case PRIVATE:
                //TODO this public list is alias for users
                break;

            case INTERNATIONAL:
            case NATIONAL:
            case REGIONAL:
            case SAR:
                result = true;
                break;
        }
        return result;
    }

    private boolean hasPrivateList(final GroupType type) {
        boolean result = false;
        switch (type) {
            //TODO general secretary

            case INTERNATIONAL:
            case LOCAL:
            case MEMBER:
            case NATIONAL:
            case REGIONAL:
            case SAR:
            case WORKGROUP:
                result = true;
                break;
        }
        return result;
    }

    private String getPublicListAddress(final GroupType type, final String groupName, final String countryName) {
        String name = "";
        switch (type) {
            //TODO general secretary

            case PRIVATE:
                //TODO this public list is alias for users
                name = "";
                break;

            case NATIONAL:
            case SAR:
                name = prepareMailingListName(countryName) + '@' + IWSConstants.PUBLIC_EMAIL_ADDRESS;
                break;

            case INTERNATIONAL:
            case REGIONAL:
                name = prepareMailingListName(groupName) + '@' + IWSConstants.PUBLIC_EMAIL_ADDRESS;
                break;
        }

        return name;
    }

    private String getPrivateListAddress(final GroupType type, final String groupName, final String countryName) {
        String name = "";
        switch (type) {
            //TODO general secretary

            case MEMBER:
                name = prepareMailingListName(countryName) + '@' + IWSConstants.PRIVATE_EMAIL_ADDRESS;
                break;

            case INTERNATIONAL:
            case REGIONAL:
                name = prepareMailingListName(groupName) + '@' + IWSConstants.PRIVATE_EMAIL_ADDRESS;
                break;

            case NATIONAL:
            case SAR:
                name = prepareMailingListName(countryName) + ".staff" + '@' + IWSConstants.PRIVATE_EMAIL_ADDRESS;
                break;

            case LOCAL:
            case WORKGROUP:
                name = prepareMailingListName(countryName) + '.' + prepareMailingListName(groupName) + '@' + IWSConstants.PRIVATE_EMAIL_ADDRESS;
                break;
        }

        return name;
    }

    private String prepareMailingListName(final String name) {
        String result = name.replace(' ', '_');
        //any other replacement
        return result;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }
}
