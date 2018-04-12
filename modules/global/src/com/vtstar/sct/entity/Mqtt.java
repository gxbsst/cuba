package com.vtstar.sct.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s %s %s %s %s %s|id,message,topic,createdBy,created_at,createTs")
@Table(name = "SCT_MQTT")
@Entity(name = "sct$Mqtt")
public class Mqtt extends StandardEntity {
    private static final long serialVersionUID = 3873351845697264368L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    protected Date created_at;

    @Column(name = "TOPIC")
    protected String topic;

    @Column(name = "MESSAGE")
    protected String message;

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}