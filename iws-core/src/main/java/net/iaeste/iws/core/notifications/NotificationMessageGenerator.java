/*
 * =============================================================================
 * Copyright 1998-2013, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-core) - net.iaeste.iws.core.notifications.NotificationMessageGenerator
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.core.notifications;

import net.iaeste.iws.persistence.notification.Notifiable;
import net.iaeste.iws.persistence.notification.NotificationType;

import java.util.Map;

/**
 * @author  Pavel Fiala / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 */
public interface NotificationMessageGenerator {

    /**
     * Generates a message for given object and notification type
     *
     * @param obj   Object to generate message for
     * @param type  Notification type
     * @return  Generated message as String
     */
    Map<String, String> generateFromTemplate(Notifiable obj, NotificationType type);
}
