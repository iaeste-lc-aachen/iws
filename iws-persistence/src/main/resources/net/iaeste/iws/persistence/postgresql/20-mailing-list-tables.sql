-- =============================================================================
-- Mailing lists
-- -----------------------------------------------------------------------------
-- table is here to simulate remote mailing publishingGroup database
-- =============================================================================
create sequence mailing_list_sequence start with 1 increment by 1;
create table mailing_lists (
    id             integer default nextval('mailing_list_sequence'),
    external_id    varchar(36),
    private        boolean default true,
    list_address   varchar(100),
    subject_prefix varchar(50),
    active         boolean,
    created        timestamp default now(),
    modified       timestamp default now(),

    /* Primary & Foreign Keys */
    constraint mailing_lists_pk primary key (id),

    /* Not Null Constraints */
    constraint mailing_lists_notnull_id           check (id is not null),
    constraint mailing_lists_notnull_external_id  check (external_id is not null),
    constraint mailing_lists_notnull_private      check (private is not null),
    constraint mailing_lists_notnull_list_address check (list_address is not null),
    constraint mailing_lists_notnull_list_active  check (active is not null),
    constraint mailing_lists_notnull_created      check (created is not null),
    constraint mailing_lists_notnull_modified     check (modified is not null)
);


-- =============================================================================
-- Mailing lists
-- -----------------------------------------------------------------------------
-- table is here to simulate remote mailing publishingGroup database
-- =============================================================================
create sequence mailing_list_membership_sequence start with 1 increment by 1;
create table mailing_list_membership (
    id               integer default nextval('mailing_list_membership_sequence'),
    mailing_list_id  integer,
    member           varchar(100),
    created          timestamp default now(),

    /* Primary & Foreign Keys */
    constraint mailing_list_membership_pk                            primary key (id),
    constraint mailing_list_membership_unique_mailing_list_id_member unique (mailing_list_id, member),
    constraint mailing_list_membership_fk_mailing_list_id            foreign key (mailing_list_id) references mailing_lists (id) on delete cascade,

    /* Not Null Constraints */
    constraint mailing_list_membership_notnull_id                  check (id is not null),
    constraint mailing_list_membership_notnull_mailing_list_id     check (mailing_list_id is not null),
    constraint mailing_list_membership_notnull_list_member         check (member is not null),
    constraint mailing_list_membership_notnull_created             check (created is not null)
);


-- =============================================================================
-- Mailing Aliases
-- -----------------------------------------------------------------------------
--
-- =============================================================================
create sequence mailing_alias_sequence start with 1 increment by 1;
create table mailing_aliases (
    id               integer default nextval('mailing_alias_sequence'),
    user_name        varchar(100),
    user_alias       varchar(125),
    created          timestamp default now(),

    /* Primary & Foreign Keys */
    constraint mailing_aliases_pk                  primary key (id),
    constraint mailing_list_membership_unique_user unique (user_name, user_alias),

    /* Not Null Constraints */
    constraint mailing_aliases_notnull_id          check (id is not null),
    constraint mailing_aliases_notnull_user_name   check (user_name is not null),
    constraint mailing_aliases_notnull_user_alias  check (user_alias is not null),
    constraint mailing_aliases_notnull_created     check (created is not null)
);


-- =============================================================================
-- List the members of a mailing list, complete with list and member address.
-- =============================================================================
create view list_members as
  select
    l.id             as list_id,
    m.id             as member_id,
    l.subject_prefix as list_prefix,
    l.list_address,
    m.member         as member_address,
    l.private        as is_private,
    l.active         as is_active,
    l.created::date  as list_created,
    m.created::date  as member_since
  from mailing_lists l, mailing_list_membership m
  where l.id = m.mailing_list_id;