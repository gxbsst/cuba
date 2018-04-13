package com.vtstar.sct.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Lob;

@NamePattern("%s|antenna")
@Table(name = "SCT_RFID_PORT")
@Entity(name = "sct$RfidPort")
public class RfidPort extends StandardEntity {
    private static final long serialVersionUID = 8708239511600344743L;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "ANTENNA")
    protected Integer antenna;

    @Column(name = "VALID_RSSI")
    protected Integer valid_rssi;





    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RFID_IMPINJ_ID")
    protected RfidImpinj rfidImpinj;

    @Lob
    @Column(name = "MQTT_TOPICS")
    protected String mqtt_topics;

    public void setMqtt_topics(String mqtt_topics) {
        this.mqtt_topics = mqtt_topics;
    }

    public String getMqtt_topics() {
        return mqtt_topics;
    }


    public void setRfidImpinj(RfidImpinj rfidImpinj) {
        this.rfidImpinj = rfidImpinj;
    }

    public RfidImpinj getRfidImpinj() {
        return rfidImpinj;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setAntenna(Integer antenna) {
        this.antenna = antenna;
    }

    public Integer getAntenna() {
        return antenna;
    }

    public void setValid_rssi(Integer valid_rssi) {
        this.valid_rssi = valid_rssi;
    }

    public Integer getValid_rssi() {
        return valid_rssi;
    }


}