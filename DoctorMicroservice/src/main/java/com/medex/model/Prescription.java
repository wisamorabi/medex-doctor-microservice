package com.medex.model;


import javax.persistence.*;

//The prescription class
@Entity //A 'serializable' entity
@Table(name = "Prescription") //Where this entity will be placed
//Now, the fields that we will annotate will be stored in the hosts table.
public class Prescription {
	@Id //Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //"Do this identification for me, generate that specific ID for me
	@Column(name="id") //Which column?
	int id;
	@Column(name="pharmaceuticalID")
	int pharmaceuticalID;
	@Column(name="doctorID")
	int doctorID;
	@Column(name="patientID")
	int patientID;
	@Column(name="count")
	int count;

	
	public Prescription() {}

	//Non default constructor
	public Prescription(int id, int apharmaceuticalID, int adoctorID, int apatientID, int acount) {
		this.id = id;
		this.pharmaceuticalID = apharmaceuticalID;
		this.doctorID = adoctorID;
		this.patientID = apatientID;
		this.count = acount;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPharmaceuticalID() {
		return pharmaceuticalID;
	}

	public void setPharmaceuticalID(int pharmaceuticalID) {
		this.pharmaceuticalID = pharmaceuticalID;
	}

	public int getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


	
}