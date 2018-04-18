create table SCT_VT_CLIENT_DEVICE_INFORMATION (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    IP varchar(255),
    LINE_CODE varchar(50),
    CODE varchar(50),
    NAME varchar(255),
    TYPE varchar(50),
    STATUS varchar(255),
    COMMENT varchar(255),
    --
    primary key (ID)
);
