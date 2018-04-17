package com.vtstar.sct.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum VtBuhlerBinStateEnum implements EnumClass<String> {

    open("OPEN"),
    close("CLOSE");

    private String id;

    VtBuhlerBinStateEnum(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static VtBuhlerBinStateEnum fromId(String id) {
        for (VtBuhlerBinStateEnum at : VtBuhlerBinStateEnum.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}