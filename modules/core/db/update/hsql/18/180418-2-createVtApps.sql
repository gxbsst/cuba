alter table SCT_VT_APPS add constraint FK_SCT_VT_APPS_LOGO foreign key (LOGO_ID) references SYS_FILE(ID);
