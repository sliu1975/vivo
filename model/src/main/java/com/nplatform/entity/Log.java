package com.nplatform.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tlog")
public class Log {
    private String id;
    private String userId;
    private String operation;
    private String business;
    private String ipAddress;
    private String macAddress;
    private String remark;
    private Timestamp logTime;

    @Id
    @Column(name = "ID", nullable = false, length = 10)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "UserID", nullable = false, length = 10)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "Operation", nullable = true, length = 50)
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Basic
    @Column(name = "Business", nullable = true, length = 6)
    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    @Basic
    @Column(name = "IPAddress", nullable = true, length = 15)
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Basic
    @Column(name = "MacAddress", nullable = true, length = 100)
    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    @Basic
    @Column(name = "Remark", nullable = true, length = 200)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "LogTime", nullable = true)
    public Timestamp getLogTime() {
        return logTime;
    }

    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log tbLog = (Log) o;
        return Objects.equals(id, tbLog.id) &&
                Objects.equals(userId, tbLog.userId) &&
                Objects.equals(operation, tbLog.operation) &&
                Objects.equals(business, tbLog.business) &&
                Objects.equals(ipAddress, tbLog.ipAddress) &&
                Objects.equals(macAddress, tbLog.macAddress) &&
                Objects.equals(remark, tbLog.remark) &&
                Objects.equals(logTime, tbLog.logTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, operation, business, ipAddress, macAddress, remark, logTime);
    }
}
