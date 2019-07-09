package com.nplatform.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="activity")
public class Activity {
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
    private String name;

    @Column(length=100)
    private String description;

    @Column(length=1)
    private String type;

    @Column(length=1)
    private String status;

    @Column
    private int participant;  //参与人数

    @Column(length=10)
    private String startDate;

    @Column(length=10)
    private String endDate;

    @Column(length=200)
    private String picture;  //底图

    @Column(length=50)
    private String title;    //标语

    @Column(length=1)
    private String rate;

    @Column(length=30)
    private String owner;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_time;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date update_time;

    @Column
    private int countx;  //x位置

    @Column
    private int county;  //y位置

    @Column(length=45)
    private String color;

    @Column(length=45)
    private String fontName;

    @Column
    private int fontSize;

    @Column
    private int fontStyle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getParticipant() {
        return participant;
    }

    public void setParticipant(int participant) {
        this.participant = participant;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
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

    public int getCountx() {
        return countx;
    }

    public void setCountx(int countx) {
        this.countx = countx;
    }

    public int getCounty() {
        return county;
    }

    public void setCounty(int county) {
        this.county = county;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }
}
