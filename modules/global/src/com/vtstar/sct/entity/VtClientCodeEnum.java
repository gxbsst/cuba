package com.vtstar.sct.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum VtClientCodeEnum implements EnumClass<String> {

    LINE_11_WEIGHTING("weighting"),
    LINE_11_3F_FEEDING("feeding_3f"),
    LINE_11_6F_FEEDING("feeding_6f"),
    LINE_11_1F_FEEDING("feeding_1f"),
    LINE_11_1F_PACKING("packing_1f"),
    WMS("wms"),
    LINE_12_FEEDING("FeedingLine12"),
    LINE_12_PACKING("PackingLine12"),
    LINE_12_SMALL_WEIGHTING("WeightingSmallLine12"),
    LINE_12_BIG_WEIGHTING("WeightingBigLine12"),
    LINE_21_FEEDING("FeedingLine21"),
    LINE_21_PACKING("PackingLine21"),
    LINE_21_SMALL_WEIGHTING("WeightingSmallLine21"),
    LINE_21_BIG_WEIGHTING("WeightingBigLine21"),
    LINE_11_MANUFACTURING_CENTER("ManufacturingCenter");

    private String id;

    VtClientCodeEnum(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static VtClientCodeEnum fromId(String id) {
        for (VtClientCodeEnum at : VtClientCodeEnum.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}