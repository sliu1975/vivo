package com.nplatform.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="policy_info")
public class PolicyInfo {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name = "system-uuid",strategy="uuid")
    @Column(length=50)
    private String id ;

    // 获得字段 id 值
    public String getId(){
        return id;
    }
    // 设置字段 id 值
    public void setId( String Value ){
        id = Value ;
    }

    @Column(length=30)
    private String policy_name;

    @Column(length=30)
    private String description;

    @Column(length=10)
    private String type;

    @Column(length=50)
    private String channel_id;

    @Column(length=30)
    private String owner;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_time;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date update_time;

    public String getPolicy_name() {
        return policy_name;
    }

    public void setPolicy_name(String policy_name) {
        this.policy_name = policy_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
