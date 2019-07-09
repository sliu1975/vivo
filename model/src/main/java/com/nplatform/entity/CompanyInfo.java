package com.nplatform.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name="company_info")
public class CompanyInfo {
  @Id
  @GeneratedValue(generator="system-uuid")
  @GenericGenerator(name = "system-uuid",strategy="uuid")
  @Column(length=50)
  private String id ;

  @Column(length=50)
  private String name;

  @Column(length=50)
  private String short_name;

  @Column(length=50)
  private String location;

  @Column(length=50)
  private String street;

  @Column(length=50)
  private String state;

  @Column(length=50)
  private String country;

  @Column(length=50)
  private String contact;

  @Column(length=50)
  private String telephone;

  @Column(length=50)
  private String logo;

  @Column(length=50)
  private String website;

  @Column(length=50)
  private String email;

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  private Date created_time;

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  private Date update_time;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getShort_name() {
    return short_name;
  }

  public void setShort_name(String short_name) {
    this.short_name = short_name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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
