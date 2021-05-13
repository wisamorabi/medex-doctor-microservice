package com.medex.communicationmodules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import com.medex.dependentresources.Patient;
import com.medex.model.Prescription;

//The patient class
//Type = 4
//Now, the fields that we will annotate will be stored in the hosts table.
public class PatientInfo {

	int id;

	String name;

	int wallet;
	
	Map<Integer, Prescription> Prescriptions = new HashMap<>(); 
	
	public PatientInfo() {}

	//Non default constructor
	public PatientInfo(int id, String aname, int awallet) {
		this.id = id;
		this.name = aname;
	}
	public PatientInfo(Patient patient) {
		this.id = patient.getId();
		this.name = patient.getName();
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
	
	public void listToMap(List<Prescription> lst)
	{
		for (Prescription i : lst) 
		{
			Prescriptions.put(i.getId(),i);
		}
	}
	public Map<Integer, Prescription> getPrescriptions()
	{
		return Prescriptions;
	}

}

