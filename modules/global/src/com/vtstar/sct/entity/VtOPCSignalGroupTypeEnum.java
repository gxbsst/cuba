package com.vtstar.sct.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum VtOPCSignalGroupTypeEnum implements EnumClass<String> {

    OPC_Start_Flag("OPC_START_FLAG"),
    Balance_Empty_Flag("BALANCE_EMPTY_FLAG"),
    Mixe_Info("MIXE_INFO"),
    Bin_Switch_Signal("BIN_SWITCH_SIGNAL"),
    Bin_Name("BIN_NAME"),
    Bunker_Allowance("BUNKER_ALLOWANCE"),
    Feeding_Alarm_F6("FEEDING_ALARM_F6"),
    Small_Materila_Feeding_sign("SMALL_MATERIAL_FEEDING_SIGN"),
    Carrier_Bin_Control_Signal("CARRIER_BIN_CONTROL_SIGNAL");

    private String id;

    VtOPCSignalGroupTypeEnum(String value) {
        this.id = value;
    }

    @Override
    public String getId() {
        return id;
    }

    @Nullable
    public static VtOPCSignalGroupTypeEnum fromId(String id) {
        for (VtOPCSignalGroupTypeEnum at : VtOPCSignalGroupTypeEnum.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}