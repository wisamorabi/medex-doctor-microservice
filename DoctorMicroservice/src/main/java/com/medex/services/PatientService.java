package com.medex.services;

import java.util.ArrayList;
import java.util.List;

import com.medex.communicationmodules.PatientInfo;
import com.medex.communicationmodules.Status;
import com.medex.database.DoctorDB;
import com.medex.database.PatientDB;
import com.medex.database.PatientDoctorDB;
import com.medex.database.PrescriptionDB;
import com.medex.dependentresources.Patient;
import com.medex.model.PatientDoctor;
import com.medex.model.Prescription;


//This is the "backend" of our resources. This is where the logic is executed; the logic is basic since the database handles itself very well.
public class PatientService {
	PatientDB patientdb = new PatientDB(); //(Instead of the pseudo-database)
	PrescriptionDB prescriptiondb = new PrescriptionDB(); //(Instead of the pseudo-database)
	DoctorDB doctordb = new DoctorDB(); //(Instead of the pseudo-database)
	PatientDoctorDB patientdoctordb = new PatientDoctorDB(); //(Instead of the pseudo-database)
	public PatientService() {} 
	
	//All what is below is just calling the functions belonging to the patients' database/table.
	
	public List<PatientInfo>getAllPatients(int doctorid)
	{
		if (doctordb.getDoctor(doctorid) == null) return null;
		List<PatientDoctor> patientdoctorlist = patientdoctordb.getPatientDoctors(doctorid);
		List<Patient> patientList = new ArrayList<Patient>();
		for (PatientDoctor pd : patientdoctorlist)
		{
			patientList.add(patientList.get(pd.getPatientid()));
		}

		List<PatientInfo> patientinfoList = new ArrayList<PatientInfo>(); //Make a list that contains HostInfo instances
		if (patientList.isEmpty() == false) return null;	
		for (Patient p : patientList)
			patientinfoList.add(PatientToPatientInfo(doctorid, p));
		return patientinfoList;
	}
	
	
	
	
	public PatientInfo getPatient(int doctorid, int patientid)
	{
		if (doctordb.getDoctor(doctorid) == null) return null;
		List<PatientDoctor> patientdoctorlist = patientdoctordb.getPatientDoctors(doctorid);
		Patient patient = null;
		for (PatientDoctor pd : patientdoctorlist)
		{
			if (patientid == pd.getPatientid())
				patient = patientdb.getPatient(patientid);
				break;
		}
		return PatientToPatientInfo(doctorid, patient);
	}
	
	public Status addPatientDoctor(int doctorid, int patientid)
	{
		if (patientdb.getPatient(patientid) == null) return new Status(false);
		if (doctordb.getDoctor(doctorid) == null) return new Status(false);
		if (patientdoctordb.getPatientDoctor(doctorid, patientid) == null) return new Status(false);
		patientdoctordb.insertPatientDoctor(new PatientDoctor(doctorid, patientid));
		return new Status(true);
	}
	
	//Does not make sense to have it.
//	public PatientInfo updatePatient(Patient aPatient)
//	{
//		patientdb.updatePatient(aPatient); return aPatient;
//	}
//	
	public Status removePatientDoctor(int doctorid, int patientid)
	{
		if (patientdb.getPatient(patientid) == null) return new Status(false);
		if (doctordb.getDoctor(doctorid) == null) return new Status(false);
		if (patientdoctordb.getPatientDoctor(doctorid, patientid) == null) return new Status(false);
		patientdoctordb.deletePatientDoctor(doctorid, patientid);
		return new Status(true);
	}
	private PatientInfo PatientToPatientInfo(int doctorid, Patient aPatient)
	{
		PatientInfo patientInfo = new PatientInfo(aPatient);
		List<Prescription> lst = prescriptiondb.getPrescriptions(doctorid, patientInfo.getId()); //For every host, get the list of VMs it holds and attach that to the hashmap of VMs that each instance of HostInfo has.
		if (lst.isEmpty() == false) { patientInfo.listToMap(lst); 	}
		
		return patientInfo;
	}
}


