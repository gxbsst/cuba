-- begin CUBA_MQTT
create table CUBA_MQTT (
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
-- end CUBA_MQTT
