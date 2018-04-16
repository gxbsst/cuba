package com.vtstar.sct.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NamePattern("%s|itemId")
@Table(name = "SCT_VT_OPC_SIGNAL")
@Entity(name = "sct$VtOPCSignal")
public class VtOPCSignal extends StandardEntity {
    private static final long serialVersionUID = -865775390599112845L;

    @Column(name = "ITEM_ID", unique = true)
    protected String itemId;

    @OneToMany(mappedBy = "vtOPCSignal")
    protected List<VtOPCSignalUpdateLog> update_logs;

    @Column(name = "GROUP_TYPE")
    protected String groupType;

    @Column(name = "DATA_TYPE")
    protected String dataType;

    @Column(name = "VALUE_")
    protected String value;

    @Column(name = "QUALITY")
    protected String quality;

    @Column(name = "UPDATE_COUNT")
    protected Integer updateCount;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_UPDATE_TIME")
    protected Date lastUpdateTime;


    @Column(name = "DESCRIPTION")
    protected String description;

    public void setUpdate_logs(List<VtOPCSignalUpdateLog> update_logs) {
        this.update_logs = update_logs;
    }

    public List<VtOPCSignalUpdateLog> getUpdate_logs() {
        return update_logs;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }


    public VtOPCSignalGroupTypeEnum getGroupType() {
        return groupType == null ? null : VtOPCSignalGroupTypeEnum.fromId(groupType);
    }

    public void setGroupType(VtOPCSignalGroupTypeEnum groupType) {
        this.groupType = groupType == null ? null : groupType.getId();
    }



    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getQuality() {
        return quality;
    }

    public void setUpdateCount(Integer updateCount) {
        this.updateCount = updateCount;
    }

    public Integer getUpdateCount() {
        return updateCount;
    }


}