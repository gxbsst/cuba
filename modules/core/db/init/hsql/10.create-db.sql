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
