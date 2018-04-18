package com.vtstar.sct.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import java.util.List;
import com.haulmont.chile.core.annotations.Composition;

@NamePattern("%s|name")
@Table(name = "SCT_VT_APPS")
@Entity(name = "sct$VtApps")
public class VtApps extends StandardEntity {
    private static final long serialVersionUID = 8612091144409536304L;

    @Column(name = "NAME")
    protected String name;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOGO_ID")
    protected FileDescriptor logo;


    @Column(name = "STATUS")
    protected String status;

    @Transient
    @MetaProperty
    protected String state = "检测中...";

    @Column(name = "IP")
    protected String ip;

    @Column(name = "PORT")
    protected Integer port;

    @Column(name = "BAT_PATH")
    protected String batPath;

    @Lob
    @Column(name = "DESCRIPTION")
    protected String description;

    public void setLogo(FileDescriptor logo) {
        this.logo = logo;
    }

    public FileDescriptor getLogo() {
        return logo;
    }


    public void setStatus(VtAppsStatusEnum status) {
        this.status = status == null ? null : status.getId();
    }

    public VtAppsStatusEnum getStatus() {
        return status == null ? null : VtAppsStatusEnum.fromId(status);
    }


    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getPort() {
        return port;
    }

    public void setBatPath(String batPath) {
        this.batPath = batPath;
    }

    public String getBatPath() {
        return batPath;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}