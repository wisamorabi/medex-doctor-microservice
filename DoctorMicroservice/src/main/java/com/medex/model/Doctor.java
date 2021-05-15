package com.medex.model;

import javax.persistence.*;

//The Doctor class
//Type = 2
@Entity //A 'serializable' entity
@Table(name = "Doctor") //Where this entity will be placed
//Now, the fields that we will annotate will be stored in the hosts table.
public class Doctor {
	@Id //Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //"Do this identification for me, generate that specific ID for me
	@Column(name="id") //Which column?
	int id;
	@Column(name="name")
	String name;
	@Column(name="username")
	String username;
	@Column(name="password")
	String password;



	public Doctor() {}

	//Non default constructor
	public Doctor(int id, String aname, String username, String password) {
		this.id = id;
		this.name = aname;
		this.username = username;
		this.password = password;
	}
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
