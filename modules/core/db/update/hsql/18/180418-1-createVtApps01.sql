create table SCT_VT_APPS (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    LOGO_ID varchar(36),
    STATUS varchar(50),
    IP varchar(255),
    PORT integer,
    BAT_PATH varchar(255),
    DESCRIPTION longvarchar,
    --
    primary key (ID)
);
