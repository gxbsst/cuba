create table SCT_VT_BUHLER_BIN (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CAN_NO varchar(255),
    CAN_NAME varchar(255),
    TYPE varchar(50),
    SCALE varchar(255),
    CAN_CAPACITY decimal(19, 3),
    NOMINAL_REMAINING_QUANTITY decimal(19, 3),
    ACTUAL_REMAINING_QUANTITY decimal(19, 3),
    ERROR_REMAINING_QUANTITY decimal(19, 3),
    MAX_QUANTITY decimal(19, 3),
    OPENED boolean not null,
    BINLM varchar(255),
    BIN_WEIGHT varchar(255),
    STATUS varchar(50),
    --
    primary key (ID)
);
