package com.vtstar.sct.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.util.Date;

@NamePattern("%s|logNumber")
@Table(name = "SCT_VT_OPC_SIGNAL_UPDATE_LOG")
@Entity(name = "sct$VtOPCSignalUpdateLog")
public class VtOPCSignalUpdateLog extends StandardEntity {
    private static final long serialVersionUID = 5733199870806516740L;

    @Column(name = "LOG_NUMBER")
    protected String logNumber;

    @Column(name = "ITEM_ID")
    protected String itemId;

    @Column(name = "VALUE_")
    protected String value;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "RECORD_TIME")
    protected Date recordTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VT_OPC_SIGNAL_ID")
    protected VtOPCSignal vtOPCSignal;

    public void setVtOPCSignal(VtOPCSignal vtOPCSignal) {
        this.vtOPCSignal = vtOPCSignal;
    }

    public VtOPCSignal getVtOPCSignal() {
        return vtOPCSignal;
    }


    public void setLogNumber(String logNumber) {
        this.logNumber = logNumber;
    }

    public String getLogNumber() {
        return logNumber;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Date getRecordTime() {
        return recordTime;
    }


}