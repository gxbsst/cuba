create table SCT_VT_OPC_SIGNAL (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ITEM_ID varchar(255),
    GROUP_TYPE varchar(50),
    DATA_TYPE varchar(255),
    VALUE_ varchar(255),
    QUALITY varchar(255),
    UPDATE_COUNT integer,
    LAST_UPDATE_TIME timestamp,
    DESCRIPTION varchar(255),
    --
    primary key (ID)
);
