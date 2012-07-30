/*
 * =============================================================================
 * Copyright 1998-2012, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-fe) - net.iaeste.iws.fe.model.Offer
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */

package net.iaeste.iws.fe.model;

/**
* Levels of study that determine the students preparedness
*
* @author Marko Cilimkovic / last $Author:$
* @version $Revision:$ / $Date:$
* @since 1.7
*/
public enum StudyLevel {
    BEGINNING("studyLevelBegin"),
    MIDDLE("studyLevelMiddle"),
    END("studyLevelEnd");

    private String nameProperty;

    StudyLevel(String nameProperty) {
        this.nameProperty = nameProperty;
    }

    public String getNameProperty() {
        return nameProperty;
    }
}
