/*
 * =============================================================================
 * Copyright 1998-2013, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-migrate) - net.iaeste.iws.migrate.MigrateTest
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.migrate;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import net.iaeste.iws.api.constants.IWSConstants;
import net.iaeste.iws.api.enums.GroupType;
import net.iaeste.iws.api.exceptions.IWSException;
import net.iaeste.iws.migrate.converters.CommonConverter;
import net.iaeste.iws.migrate.converters.CountryConverter;
import net.iaeste.iws.migrate.converters.GroupConverter;
import net.iaeste.iws.migrate.converters.UserConverter;
import net.iaeste.iws.migrate.daos.IW3Dao;
import net.iaeste.iws.migrate.daos.IW3JpaDao;
import net.iaeste.iws.migrate.entities.IW3CountriesEntity;
import net.iaeste.iws.migrate.entities.IW3GroupsEntity;
import net.iaeste.iws.migrate.entities.IW3ProfilesEntity;
import net.iaeste.iws.migrate.entities.IW3User2GroupEntity;
import net.iaeste.iws.migrate.spring.Config;
import net.iaeste.iws.persistence.AccessDao;
import net.iaeste.iws.persistence.entities.CountryEntity;
import net.iaeste.iws.persistence.entities.GroupEntity;
import net.iaeste.iws.persistence.entities.GroupTypeEntity;
import net.iaeste.iws.persistence.entities.RoleEntity;
import net.iaeste.iws.persistence.entities.UserEntity;
import net.iaeste.iws.persistence.entities.UserGroupEntity;
import net.iaeste.iws.persistence.jpa.AccessJpaDao;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { Config.class })
@TransactionConfiguration(defaultRollback = false)
public class MigrateTest {

    @PersistenceContext(unitName = "IW3PersistenceUnit")
    private EntityManager iw3EntityManager;

    @PersistenceContext(unitName = "IWSPersistenceUnit")
    private EntityManager iwsEntityManager;

    private AccessDao accessDao = null;
    private IW3Dao iw3Dao = null;

    @Before
    public void before() {
        accessDao = new AccessJpaDao(iwsEntityManager);
        iw3Dao = new IW3JpaDao(iw3EntityManager);
    }

    // =========================================================================
    // Migration is done using the following test methods, in order
    // =========================================================================

    @Test
    @Transactional("transactionManagerIWS")
    public void test1ReadingWritingCountries() {
        final CountryConverter converter = new CountryConverter();
        final List<IW3CountriesEntity> countries = iw3Dao.findAllCountries();
        System.out.println("Found " + countries.size() + " Countries to migrate.");
        assertThat(countries.isEmpty(), is(false));
        int persisted = 0;

        for (final IW3CountriesEntity oldCountry : countries) {
            final CountryEntity entity = converter.convert(oldCountry);
            accessDao.persist(entity);
        }

        System.out.println("Completed Countries Groups; Persisted " + persisted + '.');
    }

    @Test
    @Transactional("transactionManagerIWS")
    public void test2ReadingWritingGroups() {
        final GroupConverter converter = new GroupConverter();
        final List<IW3GroupsEntity> groups = iw3Dao.findAllGroups();
        System.out.println("Found " + groups.size() + " Groups to migrate.");
        assertThat(groups.isEmpty(), is(false));
        int persisted = 0;
        int dropped = 0;

        for (final IW3GroupsEntity oldGroup : groups) {
            GroupEntity entity = converter.convert(oldGroup);
            if (entity.getOldId() < 10) {
                final GroupEntity existing = accessDao.findGroupByIW3Id(entity.getOldId());
                if (existing != null) {
                    existing.merge(entity);
                    accessDao.persist(existing);
                    persisted++;
                } else {
                    System.out.println("The standard group " + entity.getGroupName() + " (id " + entity.getOldId() + "), is dropped.");
                    dropped++;
                }
            } else {
                final CountryEntity country = findExistingCountry(findCorrectCountry(oldGroup.getCountryid(), oldGroup.getRealcountryid()));
                final GroupEntity parent = accessDao.findGroupByIW3Id(oldGroup.getParentid());
                if (parent == null) {
                    // For Holland, we have the problem that Group 629 exists, but 628 (the parent) doesn't.
                    System.out.println("Couldn't find a parent for " + oldGroup.getGroupname() + " with id " + oldGroup.getGroupid());
                    entity.setParentId(0L);
                } else {
                    entity.setParentId(parent.getId());
                }
                final GroupTypeEntity groupType = accessDao.findGroupType(GroupConverter.convertGroupType(oldGroup.getGrouptype().getGrouptype()));
                entity.setCountry(country);
                entity.setGroupType(groupType);
                accessDao.persist(entity);
                persisted++;
            }
        }

        System.out.println("Completed Migrating Groups; Persisted " + persisted + ", dropped " + dropped + '.');
    }

    @Test
    @Transactional("transactionManagerIWS")
    public void test3ReadingWritingUsers() {
        final UserConverter converter = new UserConverter();
        final List<IW3ProfilesEntity> profiles = iw3Dao.findAllProfiles();
        System.out.println("Found " + profiles.size() + " Users to migrate.");
        assertThat(profiles.isEmpty(), is(false));
        int persisted = 0;

        for (final IW3ProfilesEntity profile : profiles) {
            final CountryEntity country = findExistingCountry(profile.getUser().getCountry());
            final CountryEntity nationality = findExistingCountry(profile.getUser().getNationality());
            final GroupTypeEntity groupType = accessDao.findGroupType(GroupType.PRIVATE);
            final RoleEntity role = accessDao.findRoleById(IWSConstants.ROLE_OWNER);

            final UserEntity entity = converter.convert(profile);
            entity.getPerson().setNationality(nationality);
            entity.getPerson().getAddress().setCountry(country);
            final UserGroupEntity userGroup = converter.preparePrivateGroup(entity, groupType, role);
            accessDao.persist(entity.getPerson().getAddress());
            accessDao.persist(entity.getPerson());
            accessDao.persist(entity);
            accessDao.persist(userGroup.getGroup());
            accessDao.persist(userGroup);
            persisted++;
        }

        System.out.println("Completed Migrating Users; Persisted " + persisted + '.');
    }

    @Test
    @Transactional("transactionManagerIWS")
    public void test4ReadingWritingUserGroups() {
        final List<IW3User2GroupEntity> userGroups = iw3Dao.findAllUserGroups();
        System.out.println("Found " + userGroups.size() + " UserGroups to migrate.");
        assertThat(userGroups.size(), is(not(0)));
        int persisted = 0;
        int updated = 0;

        for (final IW3User2GroupEntity oldUserGroupEntity : userGroups) {
            final UserGroupEntity userGroup = accessDao.findIw3UserGroup(oldUserGroupEntity.getUser().getUserid(), oldUserGroupEntity.getGroup().getGroupid());

            if (userGroup == null) {
                final UserEntity user = accessDao.findUserByIW3Id(oldUserGroupEntity.getUser().getUserid());
                final GroupEntity group = accessDao.findGroupByIW3Id(oldUserGroupEntity.getGroup().getGroupid());
                final RoleEntity role = accessDao.findRoleById(0L + oldUserGroupEntity.getRole().getRoleid());

                final UserGroupEntity entity = new UserGroupEntity(user, group, role);
                entity.setTitle(CommonConverter.convert(oldUserGroupEntity.getUsertitle()));
                entity.setOnPublicList(oldUserGroupEntity.getOnmailinglist());
                entity.setOnPrivateList(oldUserGroupEntity.getOnmailinglist());
                entity.setModified(CommonConverter.convert(oldUserGroupEntity.getModified()));
                entity.setCreated(CommonConverter.convert(oldUserGroupEntity.getCreated(), oldUserGroupEntity.getModified()));

                if (user != null) {
                    accessDao.persist(entity);
                    persisted++;
                } else {
                    System.out.println("Skipping UserGroup Entity where the user no longer exists (userid=" + oldUserGroupEntity.getUser().getUserid() + ").");
                }
            } else {
                System.out.print("Duplicate UserGroup Entity found, ");
                final Date modified = oldUserGroupEntity.getModified();

                if ((modified != null) && modified.after(userGroup.getModified())) {
                    System.out.println("changes are more recent than existing record - merging.");
                    userGroup.setTitle(oldUserGroupEntity.getUsertitle());
                    userGroup.setOnPublicList(oldUserGroupEntity.getOnmailinglist());
                    userGroup.setOnPrivateList(oldUserGroupEntity.getOnmailinglist());
                    userGroup.setModified(CommonConverter.convert(oldUserGroupEntity.getModified()));
                    accessDao.persist(userGroup);
                    updated++;
                } else {
                    System.out.println("changes are older than the existing record - skipping.");
                }
            }
        }

        System.out.println("Completed Migrating UserGroups; Persisted " + persisted + " and updated " + updated + '.');
    }

    // =========================================================================
    // Internal Helper Methods
    // =========================================================================

    private CountryEntity findExistingCountry(final String countrycode) {
        CountryEntity entity = null;

        try {
            if ((countrycode != null) && (countrycode.length() == 2) && !"$$".equals(countrycode)) {
                entity = accessDao.findCountry(countrycode.toUpperCase(IWSConstants.DEFAULT_LOCALE));
            }
        } catch (IWSException e) {
            System.out.println("Couldn't find Entity for country " + countrycode);
            entity = null;
        }

        return entity;
    }

    private static String findCorrectCountry(final String countryid, final String realcountryid) {
        final String result;

        if (countryid.equals(realcountryid)) {
            result = countryid;
        } else if (!"$$".equals(realcountryid)) {
            result = realcountryid;
        } else {
            result = countryid;
        }

        return result;
    }
}