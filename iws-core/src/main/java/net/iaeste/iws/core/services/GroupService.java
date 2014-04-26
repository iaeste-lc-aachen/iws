/*
 * =============================================================================
 * Copyright 1998-2014, IAESTE Internet Development Team. All rights reserved.
 * ----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-core) - net.iaeste.iws.core.services.GroupService
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.core.services;

import static net.iaeste.iws.core.transformers.AdministrationTransformer.transform;
import static net.iaeste.iws.core.transformers.AdministrationTransformer.transformMembers;
import static net.iaeste.iws.core.util.LogUtil.formatLogMessage;

import net.iaeste.iws.api.constants.IWSConstants;
import net.iaeste.iws.api.constants.IWSErrors;
import net.iaeste.iws.api.dtos.Group;
import net.iaeste.iws.api.dtos.UserGroup;
import net.iaeste.iws.api.enums.GroupType;
import net.iaeste.iws.api.enums.Permission;
import net.iaeste.iws.api.exceptions.IWSException;
import net.iaeste.iws.api.requests.FetchGroupRequest;
import net.iaeste.iws.api.requests.GroupRequest;
import net.iaeste.iws.api.requests.OwnerRequest;
import net.iaeste.iws.api.requests.UserGroupAssignmentRequest;
import net.iaeste.iws.api.responses.FetchGroupResponse;
import net.iaeste.iws.api.responses.ProcessGroupResponse;
import net.iaeste.iws.api.responses.ProcessUserGroupResponse;
import net.iaeste.iws.common.notification.NotificationType;
import net.iaeste.iws.core.exceptions.PermissionException;
import net.iaeste.iws.core.notifications.Notifications;
import net.iaeste.iws.core.transformers.CommonTransformer;
import net.iaeste.iws.core.util.GroupUtil;
import net.iaeste.iws.persistence.AccessDao;
import net.iaeste.iws.persistence.Authentication;
import net.iaeste.iws.persistence.entities.CountryEntity;
import net.iaeste.iws.persistence.entities.GroupEntity;
import net.iaeste.iws.persistence.entities.GroupTypeEntity;
import net.iaeste.iws.persistence.entities.RoleEntity;
import net.iaeste.iws.persistence.entities.UserEntity;
import net.iaeste.iws.persistence.entities.UserGroupEntity;
import net.iaeste.iws.persistence.exceptions.IdentificationException;
import net.iaeste.iws.persistence.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   IWS 1.0
 */
public final class GroupService {

    private static final Logger log = LoggerFactory.getLogger(GroupService.class);

    private static final Long GENERAL_SECRETARY_GROUP = 2L;

    private final AccessDao dao;
    private final Notifications notifications;

    public GroupService(final AccessDao dao, final Notifications notifications) {
        this.dao = dao;
        this.notifications = notifications;
    }

    /**
     * This method can handle three types if requests:
     * <ul>
     *     <li>Create new subgroup</li>
     *     <li>Update current group</li>
     *     <li>Block group</li>
     * </ul>
     * If the given Group from the GroupRequest contains an Id, then it is
     * assumed that this group should be updated, otherwise it is an attempt at
     * creating a new Group.
     *
     * @param authentication User & Group information
     * @param request        Group Request information
     */
    public ProcessGroupResponse processGroup(final Authentication authentication, final GroupRequest request) {
        final String externalGroupId = request.getGroup().getGroupId();
        final GroupEntity entity;

        if (externalGroupId == null) {
            final GroupType type = request.getGroup().getGroupType();
            final GroupType parentType = authentication.getGroup().getGroupType().getGrouptype();

            // Create new subgroup for the current group. We allow that the
            // following GroupTypes can be created using this feature: Local
            // Committe or WorkGroup. A Local Committee can again have
            // sub-groups of type WorkGroup. However, a WorkGroup cannot have
            // further sub-groups. If it is a Local Committee, then it belongs
            // under the Members Group, and is parallel to the National Group
            if ((parentType != GroupType.WORKGROUP) && (type == GroupType.WORKGROUP)) {
                entity = createGroup(authentication, GroupType.WORKGROUP, request.getGroup(), authentication.getGroup());
                setGroupOwner(entity, authentication.getUser());
            } else if ((parentType == GroupType.MEMBER) && (type == GroupType.LOCAL)) {
                // Create new Local Committee
                entity = createGroup(authentication, GroupType.LOCAL, request.getGroup(), authentication.getGroup());
                setGroupOwner(entity, authentication.getUser());
            } else {
                throw new PermissionException("Not allowed to create a sub-group of type '" + type.getDescription() + "'.");
            }
            notifications.notify(authentication, entity, NotificationType.NEW_GROUP);
        } else {
            // We're fetching the Group with a permission check, to ensure that
            // a user is not attempting to force update different groups. The
            // lookup will throw an Exception, if no such Group exists that the
            // user is permitted to process.
            entity = dao.findGroupByPermission(authentication.getUser(), externalGroupId, Permission.PROCESS_GROUP);
            final GroupType type = entity.getGroupType().getGrouptype();

            if ((type == GroupType.LOCAL) || (type == GroupType.WORKGROUP)) {
                final String name = request.getGroup().getGroupName();
                if (!dao.hasGroupsWithSimilarName(entity, name)) {
                    dao.persist(authentication, entity, CommonTransformer.transform(request.getGroup()));
                } else {
                    throw new IdentificationException("Another Group exist with a similar name " + name);
                }
            } else {
                throw new PermissionException("It is not permitted to update Groups of type " + type + " with this request.");
            }
        }

        return new ProcessGroupResponse(CommonTransformer.transform(entity));
    }

    /**
     * This method will delete a Subgroup of the one from the Authentication
     * Object. If the Group contains users and data, then the users and the
     * data will also be removed. However, if the Group has a subgroup, the
     * subgroup must be deleted first. We do not support deleting group trees.
     * Another thing, only Groups of type Local Committee or WorkGroup can be
     * deleted with this method.
     *
     * @param authentication User & Group information
     * @param request        Group Request information
     */
    public void deleteGroup(final Authentication authentication, final GroupRequest request) {
        final GroupEntity parentGroup = authentication.getGroup();
        final Group toBeDeleted = request.getGroup();
        final GroupEntity group = dao.findGroupByExternalId(toBeDeleted.getGroupId());

        if (group.getParentId().equals(parentGroup.getId())) {
            final GroupType type = group.getGroupType().getGrouptype();
            if ((type != GroupType.LOCAL) && (type != GroupType.WORKGROUP)) {
                throw new IWSException(IWSErrors.NOT_PERMITTED, "It is not allowed to remove groups of type " + type + " with this request.");
            }

            if (!dao.findSubGroups(group.getId()).isEmpty()) {
                throw new IWSException(IWSErrors.NOT_PERMITTED, "The Group being deleted contains SubGroups.");
            }

            log.info(formatLogMessage(authentication, "The Group '%s' with Id '%s', is being deleted by '%s'.", group.getGroupName(), group.getExternalId(), authentication.getUser().getExternalId()));
            dao.delete(group);
        } else {
            throw new IWSException(IWSErrors.NOT_PERMITTED, "The Group is not associated with the requesting Group.");
        }
    }

    /**
     * Retrieves the requested Group, and returns it. Although the Request
     * Object is containing the GroupId, we're ignoring it and using the one
     * from the Authentication Object, since the Controller (that invoked this
     * method), is altering the Authorisation Token to use the Request provided
     * GroupId. This is done to avoid that any attempts are made at spoofing the
     * system, i.e. use one GroupId in the token, and validate against that, and
     * then a different in the Request, after our permission checks are
     * completed.
     *
     * @param authentication User & Group information
     * @param request        Group Request information
     * @return Response Object with the requested Group (or null)
     */
    public FetchGroupResponse fetchGroup(final Authentication authentication, final FetchGroupRequest request) {
        final GroupEntity entity = findGroupFromRequest(authentication, request);
        final FetchGroupResponse response;

        if (entity != null) {
            final Group group = CommonTransformer.transform(entity);
            final List<UserGroup> users = findGroupMembers(entity, request);
            final List<UserGroup> students = findStudents(entity, request.isFetchStudents());
            final List<Group> groups = findSubGroups(entity, request.isFetchSubGroups());

            response = new FetchGroupResponse(group, users, groups);
            response.setStudents(students);
        } else {
            response = new FetchGroupResponse(IWSErrors.OBJECT_IDENTIFICATION_ERROR, "No Group was found matching the requested Id.");
        }

        return response;
    }

    private GroupEntity findGroupFromRequest(final Authentication authentication, final FetchGroupRequest request) {
        final UserEntity user = authentication.getUser();
        final GroupEntity entity;

        if (request.getGroupId() != null) {
            entity = dao.findGroup(user, request.getGroupId());
        } else {
            entity = dao.findGroupByUserAndType(user, request.getGroupType());
        }

        return entity;
    }

    /**
     * Updates the Owner of a Group to the one provided, under the conditions
     * that the new Owner is an existing and active user. The current owner is
     * then demoted to Moderator of the Group.
     *
     * @param authentication User & Group information
     * @param request        User Group Request information
     */
    public void changeUserGroupOwner(final Authentication authentication, final OwnerRequest request) {
        if (!authentication.getUser().getExternalId().equals(request.getUser().getUserId())) {
            final UserEntity user = dao.findActiveUserByExternalId(request.getUser().getUserId());

            if (user != null) {
                final GroupEntity group = authentication.getGroup();
                final GroupType type = group.getGroupType().getGrouptype();

                // If we have a National/General Secretary change, we
                // additionally have to change the Owner of the (Parent)
                // Member Group
                if ((type == GroupType.NATIONAL) || group.getId().equals(GENERAL_SECRETARY_GROUP)) {
                    final GroupEntity memberGroup = dao.findMemberGroup(user);
                    log.debug(formatLogMessage(authentication, "Changing owner for Member Group '%s' with Id '%s'.", memberGroup.getGroupName(), memberGroup.getExternalId()));
                    if (memberGroup.getId().equals(group.getParentId())) {
                        changeGroupOwner(authentication, user, memberGroup, request.getTitle());
                    } else {
                        final String secretary = group.getId().equals(GENERAL_SECRETARY_GROUP) ? "General" : "National";
                        throw new PermissionException("Cannot reassign " + secretary + " Secretary to a person who is not a member from " + group.getGroupName() + '.');
                    }
                }

                // As the NS/GS aspects are gone, we can deal with the actual
                // change just as with any other group
                log.debug(formatLogMessage(authentication, "Changing owner for the Group '%s' with Id '%s'.", group.getGroupName(), group.getExternalId()));
                changeGroupOwner(authentication, user, group, request.getTitle());
            } else {
                throw new PermissionException("Cannot reassign ownership to an inactive person.");
            }
        } else {
            throw new PermissionException("Cannot reassign ownership to the current Owner.");
        }
    }

    /**
     * This method will change the owner from the existing that is part of the
     * given {@code Authentication} Object to the provided for the Group given.
     * All of the current Owners information is copied over to the new Owner,
     * and the current (now old) Owner, will be downgraded to Moderator and have
     * the existing title altered.
     *
     * @param authentication Authentication Object for the existing Owner
     * @param user           The new Owner
     * @param group          The Group to change the Owner of
     * @param title          The new title for the former owner
     */
    private void changeGroupOwner(final Authentication authentication, final UserEntity user, final GroupEntity group, final String title) {
        final UserGroupEntity oldOwner = dao.findByGroupAndUser(group, authentication.getUser());
        final UserGroupEntity newOwner;

        // Check if the person already are a member of the Group, if not then
        // we'll create a new Record
        final UserGroupEntity existing = dao.findByGroupAndUser(group, user);
        if (existing == null) {
            newOwner = new UserGroupEntity();
        } else {
            newOwner = existing;
        }

        // Ensure that the data for the two new Entities are correct. The new
        // Owner will get all the same information as the existing
        newOwner.setGroup(group);
        newOwner.setUser(user);
        // Bug #482; Copying instrumented Objects and changing them is a bad
        // idea! Apparently, the EntityManager overwrote the Owner role with the
        // new role!
        newOwner.setRole(dao.findRoleById(IWSConstants.ROLE_OWNER));
        newOwner.setTitle(oldOwner.getTitle());
        newOwner.setOnPublicList(true);
        newOwner.setOnPrivateList(true);

        // The old  Owner will get the Moderator Role and have the title
        // removed, since it may no longer be valid
        oldOwner.setRole(dao.findRoleById(IWSConstants.ROLE_MODERATOR));
        oldOwner.setTitle(title);

        // Persist the two Entities
        dao.persist(authentication, newOwner);
        dao.persist(authentication, oldOwner);
        log.debug(formatLogMessage(authentication, "Persisting membership changes."));

        // Old Owner is the one invoking this request, so no need to include that
        // Commenting out the notifications, since they cause errors
        //notifications.notify(authentication, newOwner, NotificationType.NEW_GROUP_OWNER);
        //notifications.notify(authentication, newOwner, NotificationType.CHANGE_IN_GROUP_MEMBERS);
    }

    /**
     * Assigning or updating a given users access to a specific group. To ensure
     * that the logic is as simple as possible, all special cases from IW3 have
     * currently been dropped, and will only be added, if they are explicitly
     * requested.<br />
     *   Note, this request will not allow changing of the Owner, that is dealt
     * with in a different request.
     *
     * @param authentication User & Group information
     * @param request        User Group Request information
     * @return Processed result
     */
    public ProcessUserGroupResponse processUserGroupAssignment(final Authentication authentication, final UserGroupAssignmentRequest request) {
        final UserGroupEntity invokingUser = dao.findMemberGroupByUser(authentication.getUser());
        final ProcessUserGroupResponse response;

        if (shouldChangeSelf(authentication, request)) {
            response = updateSelf(authentication, invokingUser, request);
        } else if (shouldChangeOwner(invokingUser, request)) {
            throw new PermissionException("It is not permitted to change ownership with this request. Please use the correct request.");
        } else {
            final String roleExternalId = request.getUserGroup().getRole().getRoleId();
            final String externalUserId = request.getUserGroup().getUser().getUserId();

            final RoleEntity role = dao.findRoleByExternalIdAndGroup(roleExternalId, authentication.getGroup());
            final UserGroupEntity existingEntity = dao.findByGroupAndExternalUserId(authentication.getGroup(), externalUserId);

            if (request.isDeleteUserRequest()) {
                response = deleteUserGroupRelation(role, existingEntity);
            } else {
                response = processUserGroupRelation(authentication, request, externalUserId, role, existingEntity);
            }
        }

        return response;
    }

    private static boolean shouldChangeSelf(final Authentication authentication, final UserGroupAssignmentRequest request) {
        final String invokingExternalUserId = authentication.getUser().getExternalId();
        final String requestedExternalUserId = request.getUserGroup().getUser().getUserId();

        return invokingExternalUserId.equals(requestedExternalUserId);
    }

    /**
     * If a person updates his or her own record, it means that they can change
     * their title and mailinglist settings. As the request can only be
     * performed by someone who is either the owner or moderator, it will not
     * pose as a "bug", that someone else may attempt to escalate their
     * privileges.
     *
     * @param authentication User & Group information
     * @param request        User Group Request information
     * @return Processed UserGroup relation
     */
    private ProcessUserGroupResponse updateSelf(final Authentication authentication, final UserGroupEntity currentEntity, final UserGroupAssignmentRequest request) {
        // Update the UserGroup relation
        currentEntity.setTitle(request.getUserGroup().getTitle());
        currentEntity.setOnPrivateList(request.getUserGroup().isOnPrivateList());
        currentEntity.setOnPublicList(request.getUserGroup().isOnPublicList());
        dao.persist(authentication, currentEntity);

        return new ProcessUserGroupResponse(transform(currentEntity));
    }

    private boolean shouldChangeOwner(final UserGroupEntity invokingUser, final UserGroupAssignmentRequest request) {
        final RoleEntity requestedRole = dao.findRoleByExternalId(request.getUserGroup().getRole().getRoleId());
        final boolean result;

        if (IWSConstants.ROLE_OWNER.equals(requestedRole.getId())) {
            if (IWSConstants.ROLE_OWNER.equals(invokingUser.getRole().getId())) {
                result = true;
            } else {
                throw new PermissionException("Illegal attempt at changing Ownership.");
            }
        } else {
            result = false;
        }

        return result;
    }

    private ProcessUserGroupResponse deleteUserGroupRelation(final RoleEntity role, final UserGroupEntity existingEntity) {
        if (existingEntity != null) {
            if (role.getId() == 1) {
                // We're attempting to delete the owner, major no-no
                throw new PermissionException("It is not allowed to delete the current Owner of the Group.");
            } else {
                dao.delete(existingEntity);
                // We're just returning an empty response Object, since the User
                // has now been deleted
                return new ProcessUserGroupResponse();
            }
        } else {
            throw new IdentificationException("No user were found to be deleted.");
        }
    }

    private ProcessUserGroupResponse processUserGroupRelation(final Authentication authentication, final UserGroupAssignmentRequest request, final String externalUserId, final RoleEntity role, final UserGroupEntity existingEntity) {
        final UserGroupEntity given = transform(request.getUserGroup());
        final ProcessUserGroupResponse response;

        if (existingEntity == null) {
            // Throws an exception if no User was found
            final UserEntity user = dao.findUserByExternalId(externalUserId);

            // Now, fill in persisted Entities to the new relation
            given.setUser(user);
            given.setGroup(authentication.getGroup());
            given.setRole(role);

            // Now set the information from the request
            final UserGroup information = request.getUserGroup();
            given.setTitle(information.getTitle());
            given.setOnPublicList(information.isOnPublicList());
            given.setOnPrivateList(information.isOnPrivateList());

            // And save...
            dao.persist(given);

            notifications.notify(authentication, given, NotificationType.CHANGE_IN_GROUP_MEMBERS);
            response = new ProcessUserGroupResponse(transform(given));
        } else {
            // We're adding the new role here, and won't have history of the
            // changes, since the normal merge method is a general purpose
            // method. The role is not something we should allow being handled
            // via a general purpose method, since it critical information.
            existingEntity.setRole(role);

            // Following two lines are set by the merge method, so this is duplication.
            existingEntity.setOnPrivateList(request.getUserGroup().isOnPrivateList());
            existingEntity.setOnPublicList(request.getUserGroup().isOnPublicList());
            dao.persist(authentication, existingEntity, given);

            notifications.notify(authentication, existingEntity, NotificationType.CHANGE_IN_GROUP_MEMBERS);
            response = new ProcessUserGroupResponse(transform(existingEntity));
        }

        return response;
    }

    // =========================================================================
    // Internal Methods
    // =========================================================================

    private GroupEntity createGroup(final Authentication authentication, final GroupType type, final Group group, final GroupEntity parent) {
        // Before we begin, let's just make sure that there are no other groups
        // with the same name
        throwIfGroupnameIsUsed(parent, group.getGroupName());
        // Find pre-requisites
        final CountryEntity country = dao.findCountryByCode(parent.getCountry().getCountryCode());
        final GroupTypeEntity groupType = dao.findGroupTypeByType(type);
        final String basename = GroupUtil.prepareBaseGroupName(
                parent.getGroupType().getGrouptype(),
                parent.getCountry().getCountryName(),
                parent.getGroupName(),
                parent.getFullName());

        // Create the new Entity
        final GroupEntity groupEntity = new GroupEntity();
        groupEntity.setGroupName(GroupUtil.prepareGroupName(type, group));
        groupEntity.setGroupType(groupType);
        groupEntity.setCountry(country);
        groupEntity.setDescription(group.getDescription());
        groupEntity.setFullName(GroupUtil.prepareFullGroupName(type, group, basename));
        groupEntity.setParentId(parent.getId());
        groupEntity.setListName(GroupUtil.prepareListName(type, groupEntity.getFullName(), country.getCountryName()));

        // Save the new Group in the database
        dao.persist(authentication, groupEntity);

        // And return it, so the remainder of the processing can use it
        return groupEntity;
    }

    private void throwIfGroupnameIsUsed(final GroupEntity parent, final String groupName) {
        final List<GroupEntity> found = dao.findGroupByNameAndParent(groupName, parent);
        if (!found.isEmpty()) {
            throw new PersistenceException(IWSErrors.PERSISTENCE_ERROR, "Group cannot be created, another Group with the same name exists.");
        }
    }

    private void setGroupOwner(final GroupEntity group, final UserEntity user) {
        final RoleEntity role = dao.findRoleById(IWSConstants.ROLE_OWNER);

        final UserGroupEntity userGroup = new UserGroupEntity();
        userGroup.setGroup(group);
        userGroup.setUser(user);
        userGroup.setRole(role);
        userGroup.setTitle(role.getRole());

        dao.persist(userGroup);
    }

    private List<UserGroup> findGroupMembers(final GroupEntity entity, final FetchGroupRequest request) {
        List<UserGroupEntity> members = null;
        switch (request.getUsersToFetch()) {
            case ACTIVE:
                members = dao.findActiveGroupMembers(entity);
                break;
            case ALL:
                members = dao.findAllGroupMembers(entity);
                break;
            case NONE:
                members = new ArrayList<>(0);
        }

        final List<UserGroup> result = transformMembers(members);
        return result;
    }

    private List<UserGroup> findStudents(final GroupEntity entity, final boolean fetchStudents) {
        final List<UserGroup> result;

        if (fetchStudents && (entity.getGroupType().getGrouptype() == GroupType.NATIONAL)) {
            final List<UserGroupEntity> members = dao.findStudents(entity.getParentId());
            result = transformMembers(members);
        } else {
            result = new ArrayList<>(0);
        }

        return result;
    }

    private List<Group> findSubGroups(final GroupEntity entity, final boolean fetchSubGroups) {
        final List<Group> result;

        if (fetchSubGroups) {
            final List<GroupEntity> subGroups = dao.findSubGroups(entity.getParentId());
            result = transform(subGroups);
        } else {
            result = new ArrayList<>(0);
        }

        return result;
    }
}
