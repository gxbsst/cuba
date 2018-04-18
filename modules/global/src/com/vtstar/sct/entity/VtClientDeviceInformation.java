package com.vtstar.sct.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@NamePattern("%s %s|ip,state")
@Table(name = "SCT_VT_CLIENT_DEVICE_INFORMATION")
@Entity(name = "sct$VtClientDeviceInformation")
public class VtClientDeviceInformation extends StandardEntity {
    private static final long serialVersionUID = 6028180301131363201L;

    @Column(name = "IP", unique = true)
    protected String ip;

    @Column(name = "LINE_CODE")
    protected String lineCode;

    @Column(name = "CODE", unique = true)
    protected String code;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "TYPE")
    protected String type;

    @Column(name = "STATUS")
    protected String status;


    @Transient
    @MetaProperty
    protected String state = "检查中...";

    @Column(name = "COMMENT")
    protected String comment;

    public VtAppsStatusEnum getStatus() {
        return status == null ? null : VtAppsStatusEnum.fromId(status);
    }

    public void setStatus(VtAppsStatusEnum status) {
        this.status = status == null ? null : status.getId();
    }


    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }


    public void setCode(VtClientCodeEnum code) {
        this.code = code == null ? null : code.getId();
    }


    public void setLineCode(VtClientLineCodeEnum lineCode) {
        this.lineCode = lineCode == null ? null : lineCode.getId();
    }


    public VtClientLineCodeEnum getLineCode() {
        return lineCode == null ? null : VtClientLineCodeEnum.fromId(lineCode);
    }

    public VtClientCodeEnum getCode() {
        return code == null ? null : VtClientCodeEnum.fromId(code);
    }


    public VtClientDeviceTypeEnum getType() {
        return type == null ? null : VtClientDeviceTypeEnum.fromId(type);
    }

    public void setType(VtClientDeviceTypeEnum type) {
        this.type = type == null ? null : type.getId();
    }


    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }



    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }


}