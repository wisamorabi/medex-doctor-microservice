package com.medex.services;

import java.util.ArrayList;
import java.util.List;

import com.medex.communicationmodules.DoctorInfo;
import com.medex.communicationmodules.PatientInfo;
import com.medex.communicationmodules.Status;
import com.medex.database.DoctorDB;
import com.medex.model.Doctor;


//This is the "backend" of our resources. This is where the logic is executed; the logic is basic since the database handles itself very well.
public class DoctorService {
	DoctorDB doctordb = new DoctorDB(); //(Instead of the pseudo-database)
	PatientService patientService = new PatientService();
	public DoctorService() {} 
	
	//All what is below is just calling the functions belonging to the doctors' database/table.
	
	public List<DoctorInfo> getAllDoctors()
	{
		List<Doctor> doctorList = doctordb.getDoctors(); //Get all hosts.
		List<DoctorInfo> doctorinfoList = new ArrayList<DoctorInfo>(); //Make a list that contains HostInfo instances
		if (doctorList.isEmpty() == false) return null;	
		for (Doctor d : doctorList)
			doctorinfoList.add(DoctorToDoctorInfo(d));
		return doctorinfoList;
	}
	
	public DoctorInfo getDoctor(int doctorid)
	{
		Doctor doctor = doctordb.getDoctor(doctorid); //Get all hosts.
		if (doctor == null) return null;
		return DoctorToDoctorInfo(doctor);
	}
	
	public DoctorInfo addDoctor(Doctor aDoctor)
	{
		doctordb.insertDoctor(aDoctor);
		return DoctorToDoctorInfo(aDoctor);
	}
	
	public DoctorInfo updateDoctor(Doctor aDoctor)
	{
		doctordb.updateDoctor(aDoctor);
		return DoctorToDoctorInfo(aDoctor);
	}
	
	public Status removeDoctor(int doctorid)
	{
		if (doctordb.getDoctor(doctorid) == null) return new Status(false);
		doctordb.deleteDoctor(doctorid);
		return new Status(true);
	}
	
	public Doctor getDoctorLogin(String username, String password)
	{
		return doctordb.getDoctorLogin(username, password); //Get all hosts.

	}
	
	
	private DoctorInfo DoctorToDoctorInfo(Doctor adoctor)
	{
		DoctorInfo doctorInfo = new DoctorInfo(adoctor);
		List<PatientInfo> lst = patientService.getAllPatients(doctorInfo.getId()); //For every host, get the list of VMs it holds and attach that to the hashmap of VMs that each instance of HostInfo has.
		if (lst.isEmpty() == false) { doctorInfo.listToMap(lst); 	}
		
		return doctorInfo;
	}
}


