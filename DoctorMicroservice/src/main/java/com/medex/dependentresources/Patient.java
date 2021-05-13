package com.medex.dependentresources;

import javax.persistence.*;

//The patient class
//Type = 4
@Entity //A 'serializable' entity
@Table(name = "Patient") //Where this entity will be placed
//Now, the fields that we will annotate will be stored in the hosts table.
public class Patient {
	@Id //Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //"Do this identification for me, generate that specific ID for me
	@Column(name="id") //Which column?
	int id;
	@Column(name="name")
	String name;
	@Column(name="wallet")
	int wallet;
	
	public Patient() {}

	//Non default constructor
	public Patient(int id, String aname, int awallet) {
		this.id = id;
		this.name = aname;
		this.wallet = awallet;
	}
	public int getId()
	{
		return id;
	}

	public int getWallet() {
		return wallet;
	}
	public void setWallet(int awallet) {
		this.wallet = awallet;
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
	

}

