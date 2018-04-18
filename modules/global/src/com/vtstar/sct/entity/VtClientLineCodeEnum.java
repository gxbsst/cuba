package com.vtstar.sct.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum VtClientLineCodeEnum implements EnumClass<String> {

    LINE_11("LINE_11"),
    LINE_12("LINE_12"),
    LINE_21("LINE_21");

    private String id;

    VtClientLineCodeEnum(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static VtClientLineCodeEnum fromId(String id) {
        for (VtClientLineCodeEnum at : VtClientLineCodeEnum.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}