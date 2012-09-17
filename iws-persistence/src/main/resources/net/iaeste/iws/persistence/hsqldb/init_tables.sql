
create sequence country_sequence start with 1 increment by 1;
create table countries (
    id                  integer generated by default as sequence country_sequence primary key,
    country_name        VARCHAR(100) unique NOT NULL
);

create sequence address_sequence start with 1 increment by 1;
create table addresses (
    id                 integer generated by default as sequence address_sequence primary key,
    street1            varchar(100) default '',
    street2            varchar(100) default '',
    zip                varchar(100) default '',
    city               varchar(100) default '',
    region             varchar(100) default '',
    country_id         integer      not null references countries (id) on delete restrict,
    modified           timestamp    default now(),
    created            timestamp    default now()
);

CREATE SEQUENCE user_sequence START WITH 1 INCREMENT BY 1;
create table users (
    id                 integer GENERATED BY DEFAULT AS SEQUENCE user_sequence PRIMARY KEY,
    username           VARCHAR(50),
    password           VARCHAR(128),
    created            TIMESTAMP
);
CREATE INDEX user_id ON users (id);

CREATE SEQUENCE session_sequence START WITH 1 INCREMENT BY 1;
CREATE TABLE sessions (
    id            integer GENERATED BY DEFAULT AS SEQUENCE session_sequence PRIMARY KEY,
    session_key   VARCHAR(128),
    user_id       INTEGER REFERENCES users (id),
    created       TIMESTAMP
);
CREATE INDEX session_id ON sessions (id);

CREATE SEQUENCE grouptype_sequence START WITH 1 INCREMENT BY 1;
create table grouptypes (
  id               integer GENERATED BY DEFAULT AS SEQUENCE grouptype_sequence PRIMARY KEY,
  grouptype        VARCHAR(50)
);
CREATE INDEX grouptype_id ON grouptypes (id);

CREATE SEQUENCE group_sequence START WITH 1 INCREMENT BY 1;
create table groups (
  id               integer GENERATED BY DEFAULT AS SEQUENCE group_sequence PRIMARY KEY,
  grouptype_id     INTEGER REFERENCES grouptypes (id),
  groupname        VARCHAR(50)
);

create table permissions (
  id               INTEGER PRIMARY KEY,
  permission       VARCHAR(50)
);

CREATE SEQUENCE role_sequence START WITH 1 INCREMENT BY 1;
create table roles (
  id               integer GENERATED BY DEFAULT AS SEQUENCE role_sequence PRIMARY KEY,
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

create sequence history_sequence start with  1 increment by 1;
create table history (
  id               integer generated by default as sequence history_sequence primary key,
  user_id          integer not null references users (id),
  group_id         integer not null references groups (id),
  tablename        varchar(50) not null,
  record_id        integer not null,
  fields           varbinary(4096),
  changed          timestamp default now()
);
