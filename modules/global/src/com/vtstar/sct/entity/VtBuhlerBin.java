package com.vtstar.sct.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

@NamePattern("%s %s|canNo,invCode")
@Table(name = "SCT_VT_BUHLER_BIN")
@Entity(name = "sct$VtBuhlerBin")
public class VtBuhlerBin extends StandardEntity {
    private static final long serialVersionUID = 6151142380983945420L;

    @Column(name = "CAN_NO", unique = true)
    protected String canNo;

    @Column(name = "CAN_NAME")
    protected String canName;

    @Column(name = "TYPE")
    protected String type;

    @Column(name = "SCALE")
    protected String scale;

    @Column(name = "CAN_CAPACITY", precision = 19, scale = 3)
    protected BigDecimal canCapacity;

    @Column(name = "NOMINAL_REMAINING_QUANTITY", precision = 19, scale = 3)
    protected BigDecimal nominalRemainingQuantity;

    @Column(name = "ACTUAL_REMAINING_QUANTITY", precision = 19, scale = 3)
    protected BigDecimal actualRemainingQuantity;

    @Column(name = "ERROR_REMAINING_QUANTITY", precision = 19, scale = 3)
    protected BigDecimal errorRemainingQuantity;

    @Column(name = "MAX_QUANTITY", precision = 19, scale = 3)
    protected BigDecimal maxQuantity;

    @Column(name = "OPENED", nullable = false)
    protected Boolean opened = false;

    @Column(name = "INV_CODE")
    protected String invCode;

    @Column(name = "BINLM")
    protected String binlm;

    @Column(name = "BIN_WEIGHT")
    protected String binWeight;

    @Column(name = "STATUS")
    protected String status;

    public void setStatus(VtBuhlerBinStateEnum status) {
        this.status = status == null ? null : status.getId();
    }

    public VtBuhlerBinStateEnum getStatus() {
        return status == null ? null : VtBuhlerBinStateEnum.fromId(status);
    }


    public BigDecimal getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(BigDecimal maxQuantity) {
        this.maxQuantity = maxQuantity;
    }



    public void setBinlm(String binlm) {
        this.binlm = binlm;
    }

    public String getBinlm() {
        return binlm;
    }

    public void setBinWeight(String binWeight) {
        this.binWeight = binWeight;
    }

    public String getBinWeight() {
        return binWeight;
    }


    public void setType(VtBuhlerBinTypeEnum type) {
        this.type = type == null ? null : type.getId();
    }

    public VtBuhlerBinTypeEnum getType() {
        return type == null ? null : VtBuhlerBinTypeEnum.fromId(type);
    }


    public void setCanName(String canName) {
        this.canName = canName;
    }

    public String getCanName() {
        return canName;
    }

    public String getCanNo() {
        return canNo;
    }

    public void setCanNo(String canNo) {
        this.canNo = canNo;
    }


    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getScale() {
        return scale;
    }

    public void setCanCapacity(BigDecimal canCapacity) {
        this.canCapacity = canCapacity;
    }

    public BigDecimal getCanCapacity() {
        return canCapacity;
    }

    public void setNominalRemainingQuantity(BigDecimal nominalRemainingQuantity) {
        this.nominalRemainingQuantity = nominalRemainingQuantity;
    }

    public BigDecimal getNominalRemainingQuantity() {
        return nominalRemainingQuantity;
    }

    public void setActualRemainingQuantity(BigDecimal actualRemainingQuantity) {
        this.actualRemainingQuantity = actualRemainingQuantity;
    }

    public BigDecimal getActualRemainingQuantity() {
        return actualRemainingQuantity;
    }

    public void setErrorRemainingQuantity(BigDecimal errorRemainingQuantity) {
        this.errorRemainingQuantity = errorRemainingQuantity;
    }

    public BigDecimal getErrorRemainingQuantity() {
        return errorRemainingQuantity;
    }

    public void setOpened(Boolean opened) {
        this.opened = opened;
    }

    public Boolean getOpened() {
        return opened;
    }

    public String getInvCode() {
        return invCode;
    }

    public void setInvCode(String invCode) {
        this.invCode = invCode;
    }
}