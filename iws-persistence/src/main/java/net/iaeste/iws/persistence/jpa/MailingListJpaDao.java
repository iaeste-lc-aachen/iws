/*
 * =============================================================================
 * Copyright 1998-2016, IAESTE Internet Development Team. All rights reserved.
 * ----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-persistence) - net.iaeste.iws.persistence.jpa.MailingListJpaDao
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.persistence.jpa;

import net.iaeste.iws.api.enums.GroupStatus;
import net.iaeste.iws.api.enums.GroupType;
import net.iaeste.iws.api.enums.UserStatus;
import net.iaeste.iws.common.configuration.Settings;
import net.iaeste.iws.persistence.MailingListDao;
import net.iaeste.iws.persistence.entities.AliasEntity;
import net.iaeste.iws.persistence.entities.GroupEntity;
import net.iaeste.iws.persistence.entities.MailinglistEntity;
import net.iaeste.iws.persistence.entities.UserGroupEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.EnumSet;
import java.util.List;

/**
 * @author  Pavel Fiala / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   IWS 1.0
 */
public final class MailingListJpaDao extends BasicJpaDao implements MailingListDao {

    private final Settings settings;

    /**
     * Default Constructor.
     *
     * @param entityManager Entity Manager instance to use
     */
    public MailingListJpaDao(final EntityManager entityManager, final Settings settings) {
        super(entityManager);

        this.settings = settings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MailinglistEntity> findMailinglists(final GroupEntity group) {
        final Query query = entityManager.createQuery("select m from MailinglistEntity m where m.group = :group");
        query.setParameter("group", group);

        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MailinglistEntity findListByAddress(final String address) {
        final String jql =
                "select m " +
                "from MailinglistEntity m " +
                "where m.listAddress = :address";
        final Query query = entityManager.createQuery(jql);
        query.setParameter("address", address);

        return findSingleResult(query, "Mailinglist");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MailinglistEntity> findListsByGroup(final GroupEntity group) {
        final Query query = entityManager.createNamedQuery("mailingList.findListsByGroup");
        query.setParameter("group", group);

        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GroupEntity> findUnprocessedGroups() {
        final String jql =
                "select g from GroupEntity g " +
                "where g.status = 'ACTIVE'" +
                "  and (g.publicList = true" +
                "    or g.privateList = true)" +
                "  and g.id not in (" +
                "    select m.group.id" +
                "    from MailinglistEntity m)";
        final Query query = entityManager.createQuery(jql);

        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserGroupEntity> findUnprocessedSubscriptions() {
        final String jql =
                "select u from UserGroupEntity u " +
                "where u.user.status = 'ACTIVE'" +
                "  and u.id not in (" +
                "    select s.userGroup.id" +
                "    from UserMailinglistEntity s)";
        final Query query = entityManager.createQuery(jql);

        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int activateMailinglists() {
        return changeMailingListState(GroupStatus.ACTIVE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int suspendMailinglists() {
        return changeMailingListState(GroupStatus.SUSPENDED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteDeadMailinglists() {
        final String jql =
                "delete from MailinglistEntity " +
                "where group.id in (" +
                "    select g.id" +
                "    from GroupEntity g" +
                "    where g.status = :status)";
        final Query query = entityManager.createQuery(jql);
        query.setParameter("status", GroupStatus.DELETED);

        return query.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteMailingLists(final GroupEntity group) {
        final String jql =
                "delete from MailinglistEntity " +
                "where group = :group";
        final Query query = entityManager.createQuery(jql);
        query.setParameter("group", group);

        return query.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int activateMailinglistSubscriptions() {
        return changeMailingListSubscriptionState(UserStatus.ACTIVE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int suspendMailinglistSubscriptions() {
        return changeMailingListSubscriptionState(UserStatus.SUSPENDED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteDeadMailinglistSubscriptions() {
        final String jql =
                "delete from UserMailinglistEntity " +
                "where userGroup.id in (" +
                "    select s.id" +
                "    from UserGroupEntity s" +
                "    where s.user.status = 'DELETED')";
        final Query query = entityManager.createQuery(jql);

        return query.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AliasEntity> findAliases() {
        final String jql =
                "select a from AliasEntity a";
        final Query query = entityManager.createQuery(jql);

        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteMailinglistSubscriptions(final MailinglistEntity mailingList) {
        final String jql =
                "delete from UserMailinglistEntity " +
                "where mailinglist = :list";
        final Query query = entityManager.createQuery(jql);
        query.setParameter("list", mailingList);

        return query.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteMailingList(final MailinglistEntity mailinglist) {
        entityManager.remove(mailinglist);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserGroupEntity> findMissingNcsSubscribers() {
        //// Under IW3, the schema for the NC's mailing list was that
        //// Owner/Moderator of the Staff's were on it. At the same time the
        //// list was limited to Owners of the International Groups. This rule
        //// was inflexible and instead, the new rule is that members who are on
        //// the public list of the following Groups (Administration, National &
        //// International) are all included. For now, a hybrid version is used,
        //// which allows both variants.
        //@NamedQuery(name = "userGroup.findNCs",
        //        query = "select distinct ug from UserGroupEntity ug " +
        //                "where ug.group.status = " + EntityConstants.GROUP_STATUS_ACTIVE +
        //                "  and ug.user.status = " + EntityConstants.USER_STATUS_ACTIVE +
        //                "  and ug.onPublicList = true" +
        //                "  and (ug.group.groupType.grouptype = " + EntityConstants.GROUPTYPE_ADMINISTRATION +
        //                "    or ug.group.groupType.grouptype = " + EntityConstants.GROUPTYPE_INTERNATIONAL +
        //                "    or ug.group.groupType.grouptype = " + EntityConstants.GROUPTYPE_NATIONAL + ')'),
        final String jql =
                "select ug " +
                "from UserGroupEntity ug " +
                "where ug.group.status = :groupStatus" +
                "  and ug.user.status = :userStatus" +
                "  and ug.onPublicList = true" +
                "  and ug.group.groupType.grouptype in (:types)" +
                "  and ug not in (" +
                "    select um.userGroup" +
                "    from UserMailinglistEntity um" +
                "    where um.mailinglist.listAddress = :address)";
        final Query query = entityManager.createQuery(jql);
        query.setParameter("groupStatus", GroupStatus.ACTIVE);
        query.setParameter("userStatus", UserStatus.ACTIVE);
        query.setParameter("types", EnumSet.of(GroupType.ADMINISTRATION, GroupType.INTERNATIONAL, GroupType.NATIONAL));
        query.setParameter("address", settings.getNcsList() + '@' + settings.getPrivateMailAddress());

        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserGroupEntity> findMissingAnnounceSubscribers() {
        final String jql =
                "select ug " +
                "from UserGroupEntity ug " +
                "where ug.group.groupType.grouptype = :type" +
                "  and ug.user.status = :status" +
                "  and ug not in (" +
                "    select um.userGroup" +
                "    from UserMailinglistEntity um" +
                "    where um.mailinglist.listAddress = :address)";
        final Query query = entityManager.createQuery(jql);
        query.setParameter("type", GroupType.MEMBER);
        query.setParameter("status", UserStatus.ACTIVE);
        query.setParameter("address", settings.getAnnounceList() + '@' + settings.getPrivateMailAddress());

        return query.getResultList();
    }

    private int changeMailingListState(final GroupStatus status) {
        final String jql =
                "update MailinglistEntity set" +
                "   status = :status," +
                "   modified = current_timestamp " +
                "where group.id in (" +
                "    select m.group.id" +
                "    from MailinglistEntity m" +
                "    where m.group.status = :status" +
                "      and m.status <> m.group.status)";
        final Query query = entityManager.createQuery(jql);
        query.setParameter("status", status);

        return query.executeUpdate();
    }

    private int changeMailingListSubscriptionState(final UserStatus status) {
        final String jql =
                "update UserMailinglistEntity set" +
                "   status = :status," +
                "   modified = current_timestamp " +
                "where userGroup.id in (" +
                "    select s.userGroup.id" +
                "    from UserMailinglistEntity s" +
                "    where s.userGroup.user.status = :status" +
                "      and s.status <> s.userGroup.user.status)";
        final Query query = entityManager.createQuery(jql);
        query.setParameter("status", status);

        return query.executeUpdate();
    }
}
