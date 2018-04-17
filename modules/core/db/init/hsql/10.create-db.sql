-- begin SCT_MQTT
create table SCT_MQTT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CREATED_AT timestamp,
    TOPIC varchar(255),
    MESSAGE varchar(255),
    --
    primary key (ID)
)^
-- end SCT_MQTT
-- begin SCT_RFID_IMPINJ
create table SCT_RFID_IMPINJ (
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
    IP varchar(255),
    SPEC_ID varchar(255),
    --
    primary key (ID)
)^
-- end SCT_RFID_IMPINJ
-- begin SCT_RFID_PORT
create table SCT_RFID_PORT (
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
    ANTENNA integer,
    VALID_RSSI integer,
    RFID_IMPINJ_ID varchar(36),
    MQTT_TOPICS longvarchar,
    --
    primary key (ID)
)^
-- end SCT_RFID_PORT
-- begin SCT_VT_OPC_SIGNAL
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
)^
-- end SCT_VT_OPC_SIGNAL
-- begin SCT_VT_APPS
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
    STATUS varchar(50),
    IP varchar(255),
    PORT integer,
    BAT_PATH varchar(255),
    DESCRIPTION longvarchar,
    --
    primary key (ID)
)^
-- end SCT_VT_APPS
-- begin SCT_VT_OPC_SIGNAL_UPDATE_LOG
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
)^
-- end SCT_VT_OPC_SIGNAL_UPDATE_LOG
-- begin SCT_VT_BUHLER_BIN
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
    INV_CODE varchar(255),
    BINLM varchar(255),
    BIN_WEIGHT varchar(255),
    STATUS varchar(50),
    --
    primary key (ID)
)^
-- end SCT_VT_BUHLER_BIN
