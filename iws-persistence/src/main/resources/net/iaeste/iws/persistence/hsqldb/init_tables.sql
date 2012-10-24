-- =============================================================================
-- This script contain the basic tables for the IWS
-- -----------------------------------------------------------------------------
-- Country Model:
--     - countries
-- Permission Model:
--     - Permissions
--     - GroupTypes
--     - Roles
--     - Permission 2 GroupTypes
-- Access Model:
--     - Users
--     - Groups
--     - User 2 GroupTypes
-- =============================================================================


-- =============================================================================
-- Countries
-- -----------------------------------------------------------------------------
-- Fields in this table:
--     - country_id, the common 2 letter abbreviation (i.e. DK for Denmark)
--     - country_name, the name of the country (i.e. Denmark)
--     - country_full, the full name of the country (i.e, The Kingdom of Denmark)
--     - country_native, the native name of the country (i.e. Danmark)
--     - nationality, the nationality of the citizens (i.e. Danish)
--     - citizens, the nationality of the citizens (i.e. Danes)
--     - phonecode, the phone code (i.e. +45 for Denmark)
--     - currency, the 3 letter currency (EUR, USD, GBP, etc.)
--     - languages, comma-separated list of official languages
--     - membership_year, the year the country first joined IAESTE
--     - membership_status, see below
--           1 Full Member
--           2 Associative Member
--           3 Cooperating Institution(s)
--           4 Former Member
--           5 Listed Country (countries where trainees have come from)
--           6 Unlisted Countries (countries which have had no contact with IAESTE)
--           7 Regions (parts of a country with an independent country_id)
-- =============================================================================
create sequence country_sequence start with 1 increment by 1;
create table countries (
    id                  integer generated by default as sequence country_sequence,
    country_id          varchar(2) not null,
    country_name        varchar(100) not null,
    country_name_full   varchar(100),
    country_name_native varchar(100),
    nationality         varchar(100),
    citizens            varchar(100),
    phonecode           varchar(5),
    currency            varchar(3),
    languages           varchar(100),
    membership_status   integer default 5,
    member_since        integer default -1,
    modified            timestamp default now() not null,
    created             timestamp default now() not null,

    primary key (id),
    unique (country_name)
);


-- =============================================================================
-- Permissions or IWS Functionality
-- -----------------------------------------------------------------------------
-- All functionality in the IWS is mapped to the permission table. And users
-- must have access to a Permission, to be allowed to perform the associated
-- action.
--   Some Permissions are restricted, meaning that they serve a special purpose,
-- and cannot be granted to customized roles.
-- =============================================================================
create table permissions (
    id                  integer not null,
    permission          varchar(50) not null,
    restricted          decimal(1) default 1 not null,
    description         varchar(2048),

    primary key (id),
    unique (permission)
);


-- =============================================================================
-- GroupTypes, see net.iaeste.iws.api.enums.GroupType
-- -----------------------------------------------------------------------------
-- GroupTypes or MetaGroups, contains the common information for certain types
-- of Groups, the Common information is the allowed Permissions or
-- functionionality. All Groups in the IW has to be assigned an overall type.
--   Please note, that certain GroupType, are designed so any given user may
-- only be member of 1 (one), others are open, so users can be part of many.
-- The restricted groups are: Administration, Members, National and Sar - In
-- fact, a user can only be member of either 1 National or 1 SAR.
-- =============================================================================
create table grouptypes (
    id                  integer not null,
    grouptype           varchar(50) not null,
    description         varchar(2048),

    primary key (id),
    unique (grouptype)
);


-- =============================================================================
-- Groups
-- -----------------------------------------------------------------------------
-- All linking between functionality, data and access is going via the Groups
-- which provide the "glue". A Group is simply a collection of people, it
-- consists of a type that links the functionality in. Since Groups are so
-- fundamental for all purposes, the actual Id is never communicated out,
-- instead has an External Id assigned, that is a standard UUID value.
--   The Group hiearchy starts with a Members Group, who all have the
-- Administration Group as their parent. The Administrator Group is purely
-- there for system purposes, and can only perform the operations associated
-- with setting up the system.
--   All users will have one implicit (though physically created) Private
-- Group, which is used to link in data. This way, the Business Logic can be
-- simplified significantly, since no distinctions has to be made when looking
-- up data.
--   All Groups also have 2 Mailinglists, a public and a private. The table
-- doesn't hold both names, only the first part (field: list_name), that is
-- then expanded when creating or updating the mailinglists, so the
-- "@iaeste.org" (public) or "@iaeste.net" (private) is appended. The only
-- groups without a mailinglist, is the Private groups, since users have a
-- public e-mail alias assigned.
--   The Group also has a Status, that determines if this Group may be accessed
-- or not. In the case of the Members GroupType, users who belong to a
-- non-active Members group (you may only belong to 1 Members Group), are not
-- allowed to access the system.
--   All member groups (with the exception of the Global Members group) must
-- have a Country Id assigned.
-- =============================================================================
create sequence group_sequence start with 25 increment by 1;
create table groups (
    id                 integer generated by default as sequence group_sequence,
    external_id        varchar(36),
    parent_id          integer,
    grouptype_id       integer not null,
    groupname          varchar(50),
    full_name          varchar(100),
    group_description  varchar(250),
    country_id         integer,
    list_name          varchar(75),
    status             varchar(10) default 'Active' not null,
    modified           timestamp default now() not null,
    created            timestamp default now() not null,

    primary key (id),
    foreign key (grouptype_id) references grouptypes (id),
    foreign key (country_id) references countries (id),
    unique (external_id)
);


-- =============================================================================
-- Roles
-- -----------------------------------------------------------------------------
-- A User is asscociated to a Group with a Role, the Role serves as a "hat" for
-- the User in the context of the Group. The role is only needed to hold the
-- collection of permissions, that a User may perform on the data belonging to
-- the Group.
--   By default, 5 roles exists, these roles cannot be altered in any way, and
-- they are there to act as default roles, that will contain the most commonly
-- needed purposes. It is possible to also create customized roles, that will be
-- directed at a specific Group. A customized role can only contain a subset of
-- the Permissions that exists, some permissions are restricted, since they only
-- serve very specific Administrative needs.
--   Note, that the Role itself (the name) is not unique, since it is allowed
-- for Groups to create new Roles, and two Groups should be allowed to have the
-- same name for their Role.
--   Note, custom roles are linked to a specific Country, and will be available
-- for all subgroups in a Country. It is not permitted to create Custom Roles
-- for
-- =============================================================================
create sequence role_sequence start with 10 increment by 1;
create table roles (
    id                  integer generated by default as sequence role_sequence,
    role                varchar(50),
    country_id          integer default null,
    description         varchar(2048),
    created             timestamp default now() not null,
    modified            timestamp default now() not null,

    primary key (id),
    foreign key (country_id) references countries (id)
);


-- =============================================================================
-- Permission to GroupType Associations
-- -----------------------------------------------------------------------------
-- This table contains the actual mapping of basic Permissions to the individual
-- GroupTypes.
-- The different GroupTypes are there, since they have different purposes or
-- functions. The most fundamental part of the Permission model, is that a
-- permission must be granted to a Group, before a member of that group can
-- perform it. However, to avoid having to map the permissions to each Group,
-- they are instead mapped to the GroupType, which acts as a Template for the
-- Group.
-- =============================================================================
create table permission_to_grouptype (
    permission_id       integer not null,
    grouptype_id        integer not null,

    primary key (permission_id, grouptype_id),
    foreign key (permission_id) references permissions (id),
    foreign key (grouptype_id) references grouptypes (id)
);


-- =============================================================================
-- Permission to Role Associations
-- -----------------------------------------------------------------------------
-- The GroupTypes define the all the Permissions that users may invoke, on the
-- data belonging to a specific Group. However, to better control which
-- functionality a user belonging to a Group may invoke, the Role is used to
-- give a different set of Permissions. To determine if a User may invoke a
-- certain Permission on the Group data, the joined part of the two Permission
-- sets is compared, and only if a Permission is granted for both Group & Role,
-- is the User allowed to perform the action.
--   Custom Roles can pick from all available non-restricted Permissions, and be
-- reused throughout the Groups belonging to the same Country.
-- =============================================================================
create sequence permission_to_role_sequence start with 10 increment by 1;
create table permission_to_role (
    id                  integer generated by default as sequence permission_to_role_sequence,
    permission_id       integer not null,
    role_id             integer not null,

    primary key (id),
    foreign key (permission_id) references permissions (id),
    foreign key (role_id) references roles (id),
    unique (permission_id, role_id)
);


-- =============================================================================
-- Users
-- -----------------------------------------------------------------------------
-- Everybody who may access the System is assigned a User account, this account
-- (which was called Profile in IW3), holds the system relevant information,
-- i.e. no personal information, with the exception of the users first and last
-- names.
--
-- =============================================================================
create sequence user_sequence start with 1 increment by 1;
create table users (
    id               integer generated by default as sequence user_sequence,
    username         varchar(50) not null,
    password         varchar(128),
    status           decimal(1) default 1 not null,
    created          timestamp default now() not null,
    modified         timestamp default now() not null,

    primary key (id),
    unique (username)
);


-- =============================================================================
--
-- -----------------------------------------------------------------------------
-- =============================================================================
create sequence address_sequence start with 1 increment by 1;
create table addresses (
    id                 integer generated by default as sequence address_sequence,
    street1            varchar(100) default '',
    street2            varchar(100) default '',
    zip                varchar(100) default '',
    city               varchar(100) default '',
    region             varchar(100) default '',
    country_id         integer      not null,
    modified           timestamp    default now(),
    created            timestamp    default now(),

    primary key (id),
    foreign key (country_id) references countries (id) on delete restrict
);


-- =============================================================================
--
-- -----------------------------------------------------------------------------
-- =============================================================================
create sequence person_sequence start with 1 increment by 1;
create table persons (
    id               integer generated by default as sequence person_sequence,
    created          timestamp default now() not null,
    modified         timestamp default now() not null,

    primary key (id)
);

-- /*****************************************************************************\
-- * TABLE :: Users                                                              *
-- * -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- *
-- * Fields in this table:                                                       *
-- *   UserID             :: The system ID                                       *
-- *   UserName           :: The users e-mail address                            *
-- *   Password           :: The Users password, using MD5 encryption            *
-- *   Country            :: CountryID, the users country of residence           *
-- *   Nationality        :: CountryID, the users nationality                    *
-- *   Volunteer          :: If a user is an IAESTE volunteer or employed        *
-- * --------------------------------------------------------------------------- *
-- * General description of this table:                                          *
-- *   This table contain all the information about a user, the Country and      *
-- *   Nationality is keys for the "Countries" table.                            *
-- \*****************************************************************************/
-- CREATE SEQUENCE UserSeq START 2;
-- CREATE TABLE Users (
--     UserID             INTEGER DEFAULT NextVal('UserSeq'::text) NOT NULL PRIMARY KEY,
--     UserName           TEXT         NOT NULL,
--     Password           VARCHAR(32)  NOT NULL,
--     FirstName          TEXT         NOT NULL,
--     LastName           TEXT         NOT NULL,
--     MiddleName         TEXT         DEFAULT '',
--     NickName           TEXT         DEFAULT '',
--     Gender             VARCHAR(10)  DEFAULT '',
--     Title              TEXT         DEFAULT '',
--     POBox              TEXT         DEFAULT '',
--     Street1            TEXT         DEFAULT '',
--     Street2            TEXT         DEFAULT '',
--     Zip                VARCHAR(10)  DEFAULT '',
--     City               TEXT         DEFAULT '',
--     Country            VARCHAR(2)   NOT NULL REFERENCES Countries (CountryID) ON DELETE RESTRICT ON UPDATE CASCADE,
--     Region             TEXT         DEFAULT '',
--     University         TEXT         DEFAULT '',
--     Subject            TEXT         DEFAULT '',
--     GradYear           INTEGER      DEFAULT -1,
--     Nationality        VARCHAR(2)   NOT NULL REFERENCES Countries (CountryID) ON DELETE RESTRICT ON UPDATE CASCADE,
--     Phone              VARCHAR(25)  DEFAULT '',
--     Fax                TEXT         DEFAULT '',
--     Mobile             VARCHAR(25)  DEFAULT '',
--     Homepage           TEXT         DEFAULT '',
--     AlternativeMail    TEXT         DEFAULT '',
--     PassportNumber     TEXT         DEFAULT '',
--     PassportIssued     TEXT         DEFAULT '',
--     PassportValidity   DATE,
--     Work_Company       TEXT         DEFAULT '',
--     Work_Department    TEXT         DEFAULT '',
--     Work_Title         TEXT         DEFAULT '',
--     Work_POBox         TEXT         DEFAULT '',
--     Work_Street1       TEXT         DEFAULT '',
--     Work_Street2       TEXT         DEFAULT '',
--     Work_Zip           VARCHAR(10)  DEFAULT '',
--     Work_City          TEXT         DEFAULT '',
--     Work_Region        TEXT         DEFAULT '',
--     Work_Country       VARCHAR(2)   NOT NULL,
--     Work_Phone         VARCHAR(25)  DEFAULT '',
--     Work_Mobile        VARCHAR(25)  DEFAULT '',
--     Work_Fax           TEXT         DEFAULT '',
--     Work_Email         TEXT         DEFAULT '',
--     Work_Homepage      TEXT         DEFAULT '',
--     Type               VARCHAR(1)   DEFAULT 'v',
--     IM_Name1           TEXT         DEFAULT '',
--     IM_User1           TEXT         DEFAULT '',
--     IM_Name2           TEXT         DEFAULT '',
--     IM_User2           TEXT         DEFAULT '',
--     Birthday           DATE,
--     Comments           TEXT         DEFAULT '',
--     LastPasswordChange TIMESTAMP,
--     Modified           TIMESTAMP,
--     Created            TIMESTAMP
-- );
-- CREATE INDEX user_country     ON Users (Country);
-- CREATE INDEX user_firstname   ON Users (FirstName);
-- CREATE INDEX user_lastname    ON Users (LastName);
-- CREATE INDEX user_nationality ON Users (Nationality);
-- CREATE INDEX user_userid      ON users (UserId);
-- CREATE INDEX user_username    ON Users (UserName);
-- -- These Indexes exist, but are they needed?
-- GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE Users TO iw3;
-- GRANT SELECT,UPDATE ON SEQUENCE UserSeq TO iw3;
-- GRANT SELECT ON TABLE Users TO mail;
-- INSERT INTO Users (UserID,UserName,Password,FirstName,LastName,Country,Nationality,Work_Country,Modified,Created) VALUES (-1,'','','Nobody','','$$','$$','$$','2003-10-01','2003-10-01');
-- INSERT INTO Users (UserID,UserName,Password,FirstName,LastName,Country,Nationality,Work_Country,Modified,Created) VALUES (0,'Guest','','Guest','','$$','$$','$$','2003-10-01','2003-10-01');
-- INSERT INTO Users (UserID,UserName,Password,FirstName,LastName,Country,Nationality,Work_Country,Modified,Created) VALUES (1,'Admin','21232f297a57a5a743894a0e4a801fc3','Administrator','','dk','dk','dk','2003-10-01','2003-10-01');
--
-- /*****************************************************************************\
-- * TABLE :: Profiles                                                           *
-- * -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- *
-- * Fields in this table:                                                       *
-- *   ProfileID              :: The system ID (same as the UserID for the user) *
-- *   Status                 :: User Status either "New", "Active" or "Revoked" *
-- *   Picture                :: A picture of the user, this is just the "link"  *
-- *   Locale                 :: Localization, the two-letter country code       *
-- *   ListSize               :: The size of lists in the IntraWeb               *
-- *   CSVDelimiter           :: Allows the user to change their CSV delimiter   *
-- *   PaperType              :: For PDF files, default A4                       *
-- *   DateFormat             :: To allow different ways to display dates        *
-- *   UserName               :: Used for changing UserName (E-mail address)     *
-- *   Password               :: The new Password if a user forgot it            *
-- *   Checksum               :: Used to verify chaging the Username or Password *
-- *   DiscSize               :: The KB amount of space the user is granted      *
-- *   DiscUsed               :: The KB amount of space the user is using        *
-- *   Logins                 :: Number of logins the user had (statistics)      *
-- *   OnlineTime             :: The amount of time the user has spend online    *
-- *   MailAlias              :: This is the users personal e-mail alias         *
-- *   NotificationFrequency  :: For the MessageQueue 'n'ever, 'e'veryone,       *
-- *                          :: 'h'our, 'd'aily, 'w'eekly, 'm'onthly.           *
-- *   Private_Address        :: If address should be visible to all             *
-- *   Private_Phones         :: If phones should be vissibile to all            *
-- *   Private_Work           :: If work information should be private           *
-- * --------------------------------------------------------------------------- *
-- * General description of this table:                                          *
-- *   This table contain the profile information for the user, i.e. the system  *
-- *   dependent information which is not related to the users personal data.    *
-- \*****************************************************************************/
-- CREATE TABLE Profiles (
--     ProfileID              INTEGER PRIMARY KEY REFERENCES Users (UserID) ON DELETE CASCADE ON UPDATE CASCADE,
--     Status                 VARCHAR(25)  DEFAULT 'New',
--     Picture                VARCHAR(250) DEFAULT '',
--     Locale                 VARCHAR(2)   DEFAULT 'en',
--     Theme                  VARCHAR(25)  DEFAULT 'standard',
--     FontSize               INTEGER      DEFAULT 11,
--     ListSize               INTEGER      DEFAULT 15,
--     CSVDelimiter           CHAR(1)      DEFAULT ';',
--     PaperType              VARCHAR(10)  DEFAULT 'A4',
--     DateFormat             TEXT         DEFAULT 'Y-m-d',
--     TimeFormat             TEXT         DEFAULT 'H:i',
--     TimeZone               TEXT         DEFAULT 'CET',
--     UserName               VARCHAR(75)  DEFAULT '',
--     Password               VARCHAR(32)  DEFAULT '',
--     Checksum               VARCHAR(32)  DEFAULT '',
--     DiscSize               INTEGER      DEFAULT 26214400,
--     DiscUsed               INTEGER      DEFAULT 0,
--     Logins                 INTEGER      DEFAULT 0,
--     OnlineTime             TIMESTAMP,
--     MailAlias              VARCHAR(100) DEFAULT '',
--     NotificationFrequency  CHAR(1)      DEFAULT 'e',
--     PrivateAddress         BOOLEAN      DEFAULT true,
--     PrivatePhones          BOOLEAN      DEFAULT true,
--     PrivateWork            BOOLEAN      DEFAULT true,
--     TermsOfUsage           INTEGER      DEFAULT 0,
--     Modified               TIMESTAMP,
--     Created                TIMESTAMP
-- );
-- GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE Profiles TO iw3;
-- GRANT SELECT ON TABLE Profiles TO mail;
-- INSERT INTO Profiles (ProfileID,Modified,Created) VALUES (-1,'2003-10-01','2003-10-01');
-- INSERT INTO Profiles (ProfileID,Modified,Created) VALUES ( 0,'2003-10-01','2003-10-01');
-- INSERT INTO Profiles (ProfileID,Status,Modified,Created) VALUES ( 1,'Active','2003-10-01','2003-10-01');


-- =============================================================================
-- User Sessions
-- -----------------------------------------------------------------------------
-- All actions against the IWS, must be made with an active Session. The Session
-- is closely linked to a person, and only a single active session can exists at
-- the time.
--   The SessionKey is generated with a cryptographical checksum, that contains
-- both some unique entropy, and some user information. The created timestamp is
-- set when the user is logging in, and the modified is set everytime the user
-- makes a request.
-- =============================================================================
create sequence session_sequence start with 1 increment by 1;
create table sessions (
    id               integer generated by default as sequence session_sequence,
    session_key      varchar(128) not null,
    user_id          integer not null,
    active           decimal(1) default 1 not null,
    created          timestamp default now() not null,
    modified         timestamp default now() not null,

    primary key (id),
    foreign key (user_id) references users (id)
);


-- =============================================================================
-- User to Group Associations
-- -----------------------------------------------------------------------------
-- Although this is just a "relation" table - certain additional information is
-- crammed into it, since a users association with a group also include
-- information about how the user may access data, and how the system should
-- deal with information sent to the mailinglists of the Group.
--   Further, a user can remain on the list, but with the status  "Suspended",
-- meaning that the user cannot access anything, but the status can be restored
-- together with all other settings, if so desired.
-- =============================================================================
create sequence user_to_group_sequence start with 1 increment by 1;
create table user_to_group (
    id               integer generated by default as sequence user_to_group_sequence,
    user_id          integer not null,
    group_id         integer not null,
    role_id          integer not null,
    custom_title     varchar(50),
    on_public_list   decimal(1) default 0 not null,
    on_private_list  decimal(1) default 1 not null,
    status           decimal(1) default 1 not null,
    created          timestamp default now() not null,
    modified         timestamp default now() not null,

    primary key (id),
    foreign key (user_id) references users (id),
    foreign key (group_id) references groups (id),
    foreign key (role_id) references roles (id),
    unique (user_id, group_id)
);


-- =============================================================================
-- Monitoring History
-- -----------------------------------------------------------------------------
-- Monitoring of changes is stored in this table. The changes include the user,
-- the name of the table + the Id of the table and the actual changes (fields),
-- that have been changed. Please note, that not all fields may be present here,
-- if at all - this is depending on the setup of the monitoring for the Entity
-- and the Group.
--   The Group is in the table, so changes can be deleted if the Group is being
-- deleted. Or if the Group desires to have all data removed due to data
-- protection / privacy reasons.
-- =============================================================================
create sequence history_sequence start with 1 increment by 1;
create table history (
  id               integer generated by default as sequence history_sequence,
  user_id          integer not null,
  group_id         integer not null,
  tablename        varchar(50) not null,
  record_id        integer not null,
  fields           varbinary(8192),
  changed          timestamp default now(),

    primary key (id),
    foreign key (user_id) references users (id),
    foreign key (group_id) references groups (id) on delete cascade
);

-- =============================================================================
-- User notifications setting
-- -----------------------------------------------------------------------------
--
-- =============================================================================
create sequence user_notification_sequence start with 1 increment by 1;
create table user_notifications (
  id               integer generated by default as sequence user_notification_sequence primary key,
  user_id          integer not null references users (id),
  subject          varchar(100),
  frequency        varchar(100),
  changed          timestamp default now()
);

-- =============================================================================
-- Notification messages
-- -----------------------------------------------------------------------------
--
-- =============================================================================
create sequence notification_message_sequence start with 1 increment by 1;
create table notification_messages (
  id               integer generated by default as sequence notification_message_sequence primary key,
  user_id          integer not null,
  subject          varchar(100),
  message          varchar(100),
  status           varchar(100),
  process_after    timestamp default now(),
  changed          timestamp default now(),

  foreign key (user_id) references users (id)
);
