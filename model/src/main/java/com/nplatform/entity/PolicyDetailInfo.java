package com.nplatform.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="policy_detail_info")
public class PolicyDetailInfo {
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

    @Column(length=50)
    private String policy_id;

    @Column(length=30)
    private String description;

    @Column(length=50)
    private String location;

    @Column(length=30)
    private String radius;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_time;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date update_time;

    public String getPolicy_id() {
        return policy_id;
    }

    public void setPolicy_id(String policy_id) {
        this.policy_id = policy_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
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
