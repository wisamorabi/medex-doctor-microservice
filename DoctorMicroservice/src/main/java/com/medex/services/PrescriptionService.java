package com.medex.services;

import java.util.List;

import com.medex.database.PrescriptionDB;
import com.medex.model.Prescription;

//This is the "backend" of our resources. This is where the logic is executed; the logic is basic since the database handles itself very well.
public class PrescriptionService {
	PrescriptionDB prescriptiondb = new PrescriptionDB(); //(Instead of the pseudo-database)
	public PrescriptionService() {} 
	
	//All what is below is just calling the functions belonging to the prescriptions' database/table.
	
	public List<Prescription>getAllPrescriptions()
	{
		return prescriptiondb.getPrescriptions();
	}
	
	public Prescription getPrescription(int id)
	{
		return prescriptiondb.getPrescription(id);
	}
	
	public Prescription addPrescription(Prescription aPrescription)
	{
		prescriptiondb.insertPrescription(aPrescription); return aPrescription;
	}
	
	public Prescription updatePrescription(Prescription aPrescription)
	{
		prescriptiondb.updatePrescription(aPrescription); return aPrescription;
	}
	
	public void removePrescription(int id)
	{
		prescriptiondb.deletePrescription(id);
	}
}


