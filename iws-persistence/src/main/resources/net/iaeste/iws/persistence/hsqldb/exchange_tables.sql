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
    deduction                 numeric(2,0),
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
    living_cost_frequency     integer,
    lodging_by                varchar(255),
    lodging_cost              decimal(12,2),
    lodging_cost_frequency    integer,
    max_weeks                 integer       NOT NULL,
    min_weeks                 integer       NOT NULL,
    nearest_airport           varchar(255),
    nearest_pub_transport     varchar(255),
    nomination_deadline       date,
    other_requirements        varchar(500),
    payment                   decimal(12,2),
    payment_frequency         varchar(1),
    prev_training_req         boolean,
    work_description          varchar(1000) NOT NULL  CHECK(length(work_description) > 0),
    working_place             varchar(255),
    work_type                 char(1),
    study_levels              char(3)       NOT NULL  CHECK(length(study_levels) > 0),
    study_fields              varchar(1000) NOT NULL  CHECK(length(study_fields) > 0),
    specializations           varchar(1000),
    primary key (id),
    unique (ref_no),

    changed_on       TIMESTAMP DEFAULT NOW(),
    changed_by       INTEGER       NULL REFERENCES users (id) ON DELETE SET NULL,
    created_on       TIMESTAMP DEFAULT NOW(),
    created_by       INTEGER       NULL REFERENCES users (id) ON DELETE SET NULL
);

CREATE SEQUENCE employer_sequence START WITH 1 INCREMENT BY 1;
CREATE TABLE employers (
    id                     INT GENERATED BY DEFAULT AS SEQUENCE employer_sequence PRIMARY KEY,
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

CREATE SEQUENCE studentfiles_sequence START WITH 1 INCREMENT BY 1;
CREATE TABLE student_files (
    id                INT GENERATED BY DEFAULT AS SEQUENCE studentfiles_sequence PRIMARY KEY,
    studentid         INTEGER,
    filetype          CHARACTER VARYING(1) DEFAULT 'f',
    filename          CHARACTER VARYING(100) NOT NULL,
    systemname        CHARACTER VARYING(100) DEFAULT '',
    filesize          INTEGER DEFAULT 0,
    folderid          INTEGER DEFAULT 1,
    mimetypeid        INTEGER DEFAULT 1,
    description       CHARACTER VARYING(250) DEFAULT '',
    keywords          CHARACTER VARYING(250) DEFAULT '',
    checksum          CHARACTER VARYING(32) DEFAULT '',
    modified          TIMESTAMP DEFAULT now(),
    created           TIMESTAMP DEFAULT now()
);

CREATE SEQUENCE student_sequence START WITH 1 INCREMENT BY 1;
CREATE TABLE students (
    id                INT GENERATED BY DEFAULT AS SEQUENCE student_sequence PRIMARY KEY,
    firstname         VARCHAR(100) NOT NULL,
    lastname          VARCHAR(100) NOT NULL,
    addressid         INTEGER REFERENCES addresses(id) ON DELETE CASCADE,
    countryid         INTEGER NOT NULL,
    phone             VARCHAR(20),
    termaddressid     INTEGER REFERENCES addresses(id) ON DELETE CASCADE,
    termphone         VARCHAR(20),
    termcountryid     INTEGER NOT NULL,
    email             VARCHAR(100) NOT NULL,
    alternativemail   VARCHAR(100),
    birthday          DATE,
    birthplace        VARCHAR(100),
    nationalityid     INTEGER,
    passportnumber    VARCHAR(100),
    passportissued    VARCHAR(100),
    passportvalidity  DATE,
    gender            character(1),
    maritalstatus     character(1),
    medicallyfit      character(1),
    university        VARCHAR(100),
--    facultyid         INTEGER REFERENCES study_fields (id) ON DELETE SET NULL,
    specialization    VARCHAR(100),
    studycompleted    character(1),
    studyrequired     character(1),
    languages1id      INTEGER,
    languages2id      INTEGER,
    languages3id      INTEGER,
    fromdate          DATE,
    todate            DATE,
    requireloding     BOOLEAN,
    trainingreport    BOOLEAN,
    comment           VARCHAR(1000),
    filepicture       INTEGER REFERENCES student_files(id) ON DELETE CASCADE,
    filecv            INTEGER REFERENCES student_files(id) ON DELETE CASCADE,
    filecover         INTEGER REFERENCES student_files(id) ON DELETE CASCADE,
    fileother         INTEGER REFERENCES student_files(id) ON DELETE CASCADE,
    status            CHARACTER(1),
    modified          TIMESTAMP WITHOUT TIME ZONE,
    modifiedby        INTEGER,
    created           TIMESTAMP WITHOUT TIME ZONE,
    createdby         INTEGER,
    logincode         VARCHAR(100),
    completed         BOOLEAN DEFAULT FALSE
);

CREATE TABLE offer2group (
    offer_id           INTEGER    REFERENCES offers (id) ON DELETE CASCADE,
    group_id           INTEGER    REFERENCES groups (id) ON DELETE CASCADE,
    student_id         INTEGER    REFERENCES students (id) ON DELETE SET NULL,
    status             VARCHAR(1) DEFAULT 'n',
    visible            BOOLEAN    DEFAULT true,
    comment            VARCHAR(100)       DEFAULT '',
    exchanged          BOOLEAN    DEFAULT false,
    answered_by        INTEGER    REFERENCES users (id),
    answered           TIMESTAMP,
    modified           TIMESTAMP,
    created_by         INTEGER    REFERENCES users (id),
    created            TIMESTAMP,
    is_archived        BOOLEAN    DEFAULT false NOT NULL
);