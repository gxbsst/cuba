package com.vtstar.sct.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum VtClientDeviceTypeEnum implements EnumClass<String> {
    PC_Device("PC_DEVICE"),
    RFID_Device("RFID_DEVICE");

    private String id;

    VtClientDeviceTypeEnum(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static VtClientDeviceTypeEnum fromId(String id) {
        for (VtClientDeviceTypeEnum at : VtClientDeviceTypeEnum.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}