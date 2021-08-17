package com.medex.model;

import javax.persistence.*;

//The patient class
//Type = 4
@Entity //A 'serializable' entity
@Table(name = "PatientDoctor") //Where this entity will be placed
//Now, the fields that we will annotate will be stored in the hosts table.
public class PatientDoctor {
	@Column(name="patientID") //Which column?
	int patientid;
	@Column(name="doctorID")
	int doctorid;
	
	public PatientDoctor() {}

	//Non default constructor
	public PatientDoctor(int apatientid, int adoctorid) {
		this.patientid = apatientid;
		this.doctorid = adoctorid;
	}

	public int getPatientid() {
		return patientid;
	}

	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}

	public int getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(int doctorid) {
		this.doctorid = doctorid;
	}

}

