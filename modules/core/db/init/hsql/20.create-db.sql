-- begin SCT_RFID_PORT
alter table SCT_RFID_PORT add constraint FK_SCT_RFID_PORT_RFID_IMPINJ foreign key (RFID_IMPINJ_ID) references SCT_RFID_IMPINJ(ID)^
create index IDX_SCT_RFID_PORT_RFID_IMPINJ on SCT_RFID_PORT (RFID_IMPINJ_ID)^
-- end SCT_RFID_PORT
-- begin SCT_VT_OPC_SIGNAL
create unique index IDX_SCT_VT_OPC_SIGNAL_UNIQ_ITEM_ID on SCT_VT_OPC_SIGNAL (ITEM_ID) ^
-- end SCT_VT_OPC_SIGNAL
-- begin SCT_VT_OPC_SIGNAL_UPDATE_LOG
alter table SCT_VT_OPC_SIGNAL_UPDATE_LOG add constraint FK_SCT_VT_OPC_SIGNAL_UPDATE_LOG_VT_OPC_SIGNAL foreign key (VT_OPC_SIGNAL_ID) references SCT_VT_OPC_SIGNAL(ID)^
create index IDX_SCT_VT_OPC_SIGNAL_UPDATE_LOG_VT_OPC_SIGNAL on SCT_VT_OPC_SIGNAL_UPDATE_LOG (VT_OPC_SIGNAL_ID)^
-- end SCT_VT_OPC_SIGNAL_UPDATE_LOG
-- begin SCT_VT_BUHLER_BIN
create unique index IDX_SCT_VT_BUHLER_BIN_UNIQ_CAN_NO on SCT_VT_BUHLER_BIN (CAN_NO) ^
-- end SCT_VT_BUHLER_BIN
-- begin SCT_VT_APPS
alter table SCT_VT_APPS add constraint FK_SCT_VT_APPS_LOGO foreign key (LOGO_ID) references SYS_FILE(ID)^
-- end SCT_VT_APPS
