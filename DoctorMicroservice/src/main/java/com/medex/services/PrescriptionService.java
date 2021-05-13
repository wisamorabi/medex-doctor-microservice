package com.medex.services;

import java.util.List;


import com.medex.communicationmodules.Status;
import com.medex.database.DoctorDB;
import com.medex.database.PatientDB;
import com.medex.database.PatientDoctorDB;
import com.medex.database.PharmaceuticalDB;
import com.medex.database.PrescriptionDB;
import com.medex.model.PatientDoctor;
import com.medex.model.Prescription;

//This is the "backend" of our resources. This is where the logic is executed; the logic is basic since the database handles itself very well.
public class PrescriptionService {
	PrescriptionDB prescriptiondb = new PrescriptionDB(); //(Instead of the pseudo-database)
    DoctorDB doctordb = new DoctorDB(); //(Instead of the pseudo-database)
	PatientDB patientDB = new PatientDB(); //(Instead of the pseudo-database)
	PatientDoctorDB doctorpatientdb = new PatientDoctorDB(); //(Instead of the pseudo-database)
	PharmaceuticalDB pharmaceuticaldb = new PharmaceuticalDB(); //(Instead of the pseudo-database)
	public PrescriptionService() {} 
	
	//All what is below is just calling the functions belonging to the prescriptions' database/table.
	
	public List<Prescription>getAllPrescriptions(int doctorid, int patientid)
	{
		return prescriptiondb.getPrescriptions(doctorid, patientid);
	}
	
	public Prescription getPrescription(int doctorid, int patientid, int prescriptionid)
	{
		return prescriptiondb.getPrescription(doctorid, patientid, prescriptionid);
	}
	
	public Prescription addPrescription(int doctorid, int patientid, Prescription aPrescription)
	{
		aPrescription.setPatientID(patientid);
		aPrescription.setDoctorID(doctorid);
		if (doctordb.getDoctor(doctorid) == null) return null;
		if (patientDB.getPatient(patientid) == null) return null;
		if (doctorpatientdb.getPatientDoctor(doctorid, patientid) == null) return null;
		if (pharmaceuticaldb.getPharmaceutical(aPrescription.getPharmaceuticalID()) == null) return null;
		Boolean found = false;
		for (PatientDoctor k : doctorpatientdb.getPatientDoctors(doctorid))
		{
			if (k.getPatientid() == patientid) 
			{
				found = true;
				break;
			}
		}
		if (found == false) return null;
		Prescription checkNullAndActive = prescriptiondb.getPrescriptionWithMedicine(doctorid, patientid, aPrescription.getPharmaceuticalID());
		if (checkNullAndActive == null) return null;
		if (checkNullAndActive.getActive() == false) return null;
		if (aPrescription.getCount() == 0) aPrescription.setActive(false);
		prescriptiondb.insertPrescription(aPrescription);
		return aPrescription;
	}
	
	public Prescription updatePrescription(int doctorid, int patientid, int prescriptionid, Prescription aPrescription)
	{
		aPrescription.setPatientID(patientid);
		aPrescription.setDoctorID(doctorid);
		aPrescription.setId(prescriptionid);
		if (doctordb.getDoctor(doctorid) == null) return null;
		if (patientDB.getPatient(patientid) == null) return null;
		if (prescriptiondb.getPrescription(doctorid, patientid, prescriptionid) == null) return null;
		if (doctorpatientdb.getPatientDoctor(doctorid, patientid) == null) return null;
		if (pharmaceuticaldb.getPharmaceutical(aPrescription.getPharmaceuticalID()) == null) return null;

		if (prescriptiondb.getPrescriptionWithMedicine(doctorid, patientid, aPrescription.getPharmaceuticalID()) == null) return null;
		prescriptiondb.updatePrescription(aPrescription);
		return aPrescription;
	}
	
	
	public Status removePrescription(int doctorid, int patientid, int prescriptionid)
	{
		if (doctordb.getDoctor(doctorid) == null) return new Status(false);
		if (patientDB.getPatient(patientid) == null)return new Status(false);
		if (prescriptiondb.getPrescription(doctorid, patientid, prescriptionid) == null) return new Status(false);
		if (doctorpatientdb.getPatientDoctor(doctorid, patientid) == null) return new Status(false);	
		prescriptiondb.deletePrescription(prescriptionid);
		return new Status(true);	
	}
}


