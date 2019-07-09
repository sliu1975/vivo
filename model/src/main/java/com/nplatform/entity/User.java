package com.nplatform.entity;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tuser")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column
	private Integer id;

	@Column(length=32)
	private String userName;

	@Column(length=10)
	private String category;

	@Column(length=20)
	private String password;

	@Column(length=20)
	private String department;

	@Column(length=40)
	private String role;

	@Column(length=1)
	private String firstlogin;

	@Column(length=1)
	private String locked;

	@Column(length=1)
	private String securitycode;

	@Column(length=45)
	private String email;

	@Column(length=11)
	private String mobile;

	@Column(length=20)
	private String telephone;

	@Column(length=50)
	private String displayname;

	@Column(length=50)
	private Integer companyid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstlogin() {
		return firstlogin;
	}

	public void setFirstlogin(String firstlogin) {
		this.firstlogin = firstlogin;
	}

	public String getLocked() {
		return locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	public String getSecuritycode() {
		return securitycode;
	}

	public void setSecuritycode(String securitycode) {
		this.securitycode = securitycode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public Integer getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	@Column(length=18)
	private String idnumber ;

	// 获得字段 idnumber 值
	public String getIdnumber(){
		return idnumber;
	}
	// 设置字段 idnumber 值
	public void setIdnumber( String Value ){
		idnumber = Value ;
	}


	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthdate ;

	// 获得字段 birthdate 值
	public Date getBirthdate(){
		return birthdate;
	}
	// 设置字段 birthdate 值
	public void setBirthdate( Date Value ){
		birthdate = Value ;
	}


	@Column(length=3)
	private String gender ;

	// 获得字段 gender 值
	public String getGender(){
		return gender;
	}
	// 设置字段 gender 值
	public void setGender( String Value ){
		gender = Value ;
	}


	@Column(length=10)
	private String education ;

	// 获得字段 education 值
	public String getEducation(){
		return education;
	}
	// 设置字段 education 值
	public void setEducation( String Value ){
		education = Value ;
	}


	@Column(length=50)
	private String school ;

	// 获得字段 school 值
	public String getSchool(){
		return school;
	}
	// 设置字段 school 值
	public void setSchool( String Value ){
		school = Value ;
	}


	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date graduatedate ;

	// 获得字段 graduatedate 值
	public Date getGraduatedate(){
		return graduatedate;
	}
	// 设置字段 graduatedate 值
	public void setGraduatedate( Date Value ){
		graduatedate = Value ;
	}


	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date workdate ;

	// 获得字段 workdate 值
	public Date getWorkdate(){
		return workdate;
	}
	// 设置字段 workdate 值
	public void setWorkdate( Date Value ){
		workdate = Value ;
	}

	@Column(length=45)
	private String level ;

	// 获得字段 level 值
	public String getLevel(){
		return level;
	}
	// 设置字段 level 值
	public void setLevel( String Value ){
		level = Value ;
	}


	@Column(length=250)
	private String memo ;

	// 获得字段 memo 值
	public String getMemo(){
		return memo;
	}
	// 设置字段 memo 值
	public void setMemo( String Value ){
		memo = Value ;
	}
}
