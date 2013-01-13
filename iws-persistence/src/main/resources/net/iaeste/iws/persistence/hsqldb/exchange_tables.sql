-- =============================================================================
-- Please add the initialization of all Exchange related tables here
-- -----------------------------------------------------------------------------
-- Note; The HSQLDB is for in-memory testing, hence it makes no sense to have
--  drop the tables and sequences first!
-- =============================================================================

CREATE TABLE offers (
    id                        bigint generated by default as identity (start with 1),
    ref_no                    varchar(255)  NOT NULL  CHECK(length(ref_no) > 0),
    canteen                   boolean,
    currency                  varchar(3),
    weekly_hours              decimal(5,3)  NOT NULL,
    daily_hours               decimal(5,3),
    deduction                 varchar(20),
    employer_name             varchar(255)  NOT NULL  CHECK(length(employer_name) > 0),
    employer_address          varchar(255),
    employer_address_2        varchar(255),
    employer_business         varchar(255),
    employer_employees_cnt    integer,
    employer_website          varchar(255),
    from_date                 date          NOT NULL,
    to_date                   date          NOT NULL,
    from_date_2               date,
    to_date_2                 date,
    unavailable_from          date,
    unavailable_to            date,
    language_1                varchar(255)  NOT NULL,
    language_1_level          varchar(1)    NOT NULL,
    language_1_op             varchar(1),
    language_2                varchar(255),
    language_2_level          varchar(1),
    language_2_op             varchar(1),
    language_3                varchar(255),
    language_3_level          varchar(1),
    living_cost               decimal(12,2),
    living_cost_frequency     varchar(10),
    lodging_by                varchar(255),
    lodging_cost              decimal(12,2),
    lodging_cost_frequency    varchar(10),
    min_weeks                 integer       NOT NULL,
    max_weeks                 integer       NOT NULL,
    nearest_airport           varchar(255),
    nearest_pub_transport     varchar(255),
    nomination_deadline       date,
    other_requirements        varchar(500),
    payment                   decimal(12,2),
    payment_frequency         varchar(10),
    prev_training_req         boolean,
    work_description          varchar(1000) NOT NULL  CHECK(length(work_description) > 0),
    working_place             varchar(255),
    work_type                 char(1),
    study_levels              varchar(25)   NOT NULL  CHECK(length(study_levels) > 0),
    study_fields              varchar(1000) NOT NULL  CHECK(length(study_fields) > 0),
    specializations           varchar(1000),
    group_id                  integer       not null references groups (id) on delete cascade,
    modified                  timestamp default now(),
    created                   timestamp default now(),

    primary key (id),
    unique (ref_no)
);

CREATE SEQUENCE employer_sequence START WITH 1 INCREMENT BY 1;
CREATE TABLE employers (
    id                     INTEGER GENERATED BY DEFAULT AS SEQUENCE employer_sequence PRIMARY KEY,
    name                   VARCHAR(100)         DEFAULT '',
    address_id             INTEGER      NOT NULL REFERENCES addresses (id) ON DELETE CASCADE ON UPDATE CASCADE,
    workplace              VARCHAR(100)         DEFAULT '',
    website                VARCHAR(100)         DEFAULT '',
    business               VARCHAR(100)         DEFAULT '',
    responsible_person     VARCHAR(100)         DEFAULT '',
    airport                VARCHAR(100)         DEFAULT '',
    transport              VARCHAR(100)         DEFAULT '',
    employees              VARCHAR(100)         DEFAULT '',
    modified               TIMESTAMP    DEFAULT now(),
    created                TIMESTAMP    DEFAULT now()
);


-- =============================================================================
-- Students
-- -----------------------------------------------------------------------------
-- Description of Table
-- =============================================================================
create sequence student_sequence start with 1 increment by 1;
create table students (
    id                  bigint generated by default as identity (start with 1),
    student_name        varchar(100),
    group_id            integer,
    modified            timestamp default now(),
    created             timestamp default now(),

    /* Primary & Foreign Keys */
    constraint student_pk          primary key (id),
    constraint student_fk_group_id foreign key (group_id) references groups (id),

    /* Unique Constraints */
    constraint student_student_name unique (student_name),

    /* Not Null Constraints */
    constraint student_notnull_student_name check (student_name is not null),
    constraint student_notnull_group_id     check (group_id is not null),
    constraint student_notnull_modified     check (modified is not null),
    constraint student_notnull_created      check (created is not null)
);


create sequence offer_to_group_sequence start with 1 increment by 1;
CREATE TABLE offer_to_group (
    id                 INTEGER generated by default as sequence offer_to_group_sequence,
    offer_id           INTEGER,
    group_id           INTEGER,
    student_id         INTEGER,
    status             VARCHAR(1)    DEFAULT 'n',
    is_archived        BOOLEAN       DEFAULT false,
    visible            BOOLEAN       DEFAULT true,
    comment            VARCHAR(100)  DEFAULT '',
    answered           TIMESTAMP,
    answered_by        INTEGER,
    modified           TIMESTAMP,
    modified_by        INTEGER,
    created            TIMESTAMP,
    created_by         INTEGER,

    /* Primary & Foreign Keys */
    constraint o2g_pk          primary key (id),
    constraint o2g_fk_offer_id foreign key (offer_id) references offers (id) ON DELETE CASCADE,
    constraint o2g_fk_group_id foreign key (group_id) references groups (id) ON DELETE CASCADE,
    constraint o2g_fk_student_id foreign key (student_id) references students (id) ON DELETE SET NULL,
    constraint o2g_fk_answered_by foreign key (answered_by) references users (id) ON DELETE SET NULL,
    constraint o2g_fk_modified_by foreign key (modified_by) references users (id) ON DELETE SET NULL,
    constraint o2g_fk_created_by foreign key (created_by) references users (id) ON DELETE SET NULL,

    /* Not Null Constraints */
    constraint o2g_notnull_offer_id     check (offer_id is not null),
    constraint o2g_notnull_group_id     check (group_id is not null),
    constraint o2g_notnull_modified     check (modified is not null),
    constraint o2g_notnull_created      check (created is not null)
);

-- TODO: iw3's offer2group_history for tracking of viewed/accepted etc together with messages
