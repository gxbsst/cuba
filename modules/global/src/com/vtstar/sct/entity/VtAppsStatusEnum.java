package com.vtstar.sct.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum VtAppsStatusEnum implements EnumClass<String> {

    closed("default"),
    processing("processing");

    private String id;

    VtAppsStatusEnum(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static VtAppsStatusEnum fromId(String id) {
        for (VtAppsStatusEnum at : VtAppsStatusEnum.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}