create table SCT_VT_OPC_SIGNAL_UPDATE_LOG (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    LOG_NUMBER varchar(255),
    ITEM_ID varchar(255),
    VALUE_ varchar(255),
    RECORD_TIME timestamp,
    VT_OPC_SIGNAL_ID varchar(36),
    --
    primary key (ID)
);
