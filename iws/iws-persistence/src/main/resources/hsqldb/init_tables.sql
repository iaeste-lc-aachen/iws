--create schema iws authorization dba

CREATE SEQUENCE user_sequence START WITH 1 INCREMENT BY 1;
create table users (
  id               INT GENERATED BY DEFAULT AS SEQUENCE user_sequence PRIMARY KEY,
  username         VARCHAR(50),
  password         VARCHAR(128)
);
CREATE INDEX user_id ON users (id);

CREATE SEQUENCE session_sequence START WITH 1 INCREMENT BY 1;
CREATE TABLE sessions (
    id            INT GENERATED BY DEFAULT AS SEQUENCE session_sequence PRIMARY KEY,
    session_key   VARCHAR(128),
    user_id       INTEGER REFERENCES users (id),
    created       TIMESTAMP
);
CREATE INDEX session_id ON sessions (id);

CREATE SEQUENCE grouptype_sequence START WITH 1 INCREMENT BY 1;
create table grouptypes (
  id               INT GENERATED BY DEFAULT AS SEQUENCE grouptype_sequence PRIMARY KEY,
  grouptype        VARCHAR(50)
);
CREATE INDEX grouptype_id ON grouptypes (id);

CREATE SEQUENCE group_sequence START WITH 1 INCREMENT BY 1;
create table groups (
  id               INT GENERATED BY DEFAULT AS SEQUENCE group_sequence PRIMARY KEY,
  grouptype_id     INTEGER REFERENCES grouptypes (id),
  groupname        VARCHAR(50)
);

create table permissions (
  id               INTEGER PRIMARY KEY,
  permission       VARCHAR(50)
);

CREATE SEQUENCE role_sequence START WITH 1 INCREMENT BY 1;
create table roles (
  id               INT GENERATED BY DEFAULT AS SEQUENCE role_sequence PRIMARY KEY,
  role             VARCHAR(50)
);

create table permission_to_grouptype (
  permission_id    INTEGER REFERENCES permissions (id),
  grouptype_id     INTEGER REFERENCES grouptypes (id),
  UNIQUE (permission_id, grouptype_id)
);

create table permission_to_role (
  permission_id    INTEGER REFERENCES permissions (id),
  role_id          INTEGER REFERENCES roles (id),
  UNIQUE (permission_id, role_id)
);

create table role_to_group (
  role_id          INTEGER REFERENCES roles (id),
  group_id         INTEGER REFERENCES groups (id),
  PRIMARY KEY (role_id, group_id)
);

create table user_to_group (
  user_id          INTEGER REFERENCES users (id),
  group_id         INTEGER REFERENCES groups (id),
  role_id          INTEGER REFERENCES roles (id),
  PRIMARY KEY (user_id, group_id)
);

CREATE SEQUENCE countries_sequence START WITH 1 INCREMENT BY 1;
CREATE TABLE countries (
    id                  INT GENERATED BY DEFAULT AS SEQUENCE countries_sequence PRIMARY KEY,
    country_code        VARCHAR(2)   NOT NULL CHECK (length(country_code) = 2),
    country_name        VARCHAR(100) NOT NULL CHECK (length(country_name) > 1),
    country_fullname    VARCHAR(100) DEFAULT '',
    country_native      VARCHAR(100) DEFAULT '',
    nationality         VARCHAR(100) NOT NULL,
    citizens            VARCHAR(100) DEFAULT '',
    phone_code          VARCHAR(5)   DEFAULT '',
    currency            VARCHAR(3)   DEFAULT '',
    æanguages           VARCHAR(100) DEFAULT '',
    membership          INTEGER      DEFAULT 5,
    membership_since    INTEGER      DEFAULT -1,
    modified            TIMESTAMP    DEFAULT now(),
    created             TIMESTAMP    DEFAULT now()
);