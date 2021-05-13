package com.medex.services;

import java.util.ArrayList;
import java.util.List;

import com.medex.communicationmodules.PatientInfo;
import com.medex.communicationmodules.Status;
import com.medex.database.PatientDB;
import com.medex.database.PrescriptionDB;
import com.medex.dependentresources.Patient;
import com.medex.model.Prescription;


//This is the "backend" of our resources. This is where the logic is executed; the logic is basic since the database handles itself very well.
public class PatientService {
	PatientDB patientdb = new PatientDB(); //(Instead of the pseudo-database)
	PrescriptionDB prescriptiondb = new PrescriptionDB(); //(Instead of the pseudo-database)
	public PatientService() {} 
	
	//All what is below is just calling the functions belonging to the patients' database/table.
	
	public List<PatientInfo>getAllPatients()
	{
		List<Patient> patientList = patientdb.getPatients(); //Get all hosts.
		List<PatientInfo> patientinfoList = new ArrayList<PatientInfo>(); //Make a list that contains HostInfo instances
		if (patientList.isEmpty() == false) return null;	
		for (Patient p : patientList)
			patientinfoList.add(PatientToPatientInfo(p));
		return patientinfoList;
	}
	
	public PatientInfo getPatient(int doctorid, int patientid)
	{
		Patient patient = patientdb.getPatient(doctorid, patientid); //Get all hosts.
		if (patient == null) return null;
		return PatientToPatientInfo(patient);
	}
	
	public PatientInfo addPatient(Patient aPatient)
	{
		patientdb.insertPatient(aPatient); return aPatient;
	}
	
	public PatientInfo updatePatient(Patient aPatient)
	{
		patientdb.updatePatient(aPatient); return aPatient;
	}
	
	public Status removePatient(int id)
	{
		patientdb.deletePatient(id);
		return new Status(true);
	}
	private PatientInfo PatientToPatientInfo(Patient aPatient)
	{
		PatientInfo patientInfo = new PatientInfo(aPatient);
		List<Prescription> lst = prescriptiondb.get(patientInfo.getId()); //For every host, get the list of VMs it holds and attach that to the hashmap of VMs that each instance of HostInfo has.
		if (lst.isEmpty() == false) { patientInfo.listToMap(lst); 	}
		
		return patientInfo;
	}
}


