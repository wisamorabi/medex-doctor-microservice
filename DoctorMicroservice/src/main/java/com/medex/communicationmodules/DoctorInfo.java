package com.medex.communicationmodules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import com.medex.model.Doctor;


//The Doctor class
//Type = 2
@Entity //A 'serializable' entity
@Table(name = "Doctor") //Where this entity will be placed
//Now, the fields that we will annotate will be stored in the hosts table.
public class DoctorInfo {
	@Id //Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //"Do this identification for me, generate that specific ID for me
	@Column(name="id") //Which column?
	int id;
	@Column(name="name")
	String name;
	
	Map<Integer, PatientInfo> orderItems = new HashMap<>(); 


	public DoctorInfo() {}

	//Non default constructor
	public DoctorInfo(int id, String aname) {
		this.id = id;
		this.name = aname;
	}
	public DoctorInfo(Doctor doctor) {
		this.id = doctor.getId();
		this.name = doctor.getName();
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
	public void listToMap(List<PatientInfo> lst)
	{
		for (PatientInfo i : lst) 
		{
			orderItems.put(i.getId(),i);
		}
	}
	public Map<Integer, PatientInfo> getPatientInfo()
	{
		return orderItems;
	}
	
}
