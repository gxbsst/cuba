package com.vtstar.sct.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.util.List;

@NamePattern("%s|port")
@Table(name = "SCT_RFID_IMPINJ")
@Entity(name = "sct$RfidImpinj")
public class RfidImpinj extends StandardEntity {
    private static final long serialVersionUID = 1865526808665750746L;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "IP")
    protected String ip;

    @Column(name = "SPEC_ID")
    protected String specId;

    @OneToMany(mappedBy = "rfidImpinj")
    protected List<RfidPort> port;

    public List<RfidPort> getPort() {
        return port;
    }

    public void setPort(List<RfidPort> port) {
        this.port = port;
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

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getSpecId() {
        return specId;
    }


}