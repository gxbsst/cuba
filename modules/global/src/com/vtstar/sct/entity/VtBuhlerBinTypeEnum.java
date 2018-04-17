package com.vtstar.sct.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum VtBuhlerBinTypeEnum implements EnumClass<String> {

    Large_Material_Bin("LARGE_MATERIAL_BIN"),
    Carrier_Material_Bin("CARRIER_MATERIAL_BIN");

    private String id;

    VtBuhlerBinTypeEnum(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static VtBuhlerBinTypeEnum fromId(String id) {
        for (VtBuhlerBinTypeEnum at : VtBuhlerBinTypeEnum.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}