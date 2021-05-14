package com.medex.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.medex.communicationmodules.PatientInfo;
import com.medex.communicationmodules.Status;
import com.medex.dependentresources.Patient;
import com.medex.services.PatientService;



//Request resources which acts as a layer before our Patient services

public class PatientResources {
	PatientService patientService = new PatientService();

	public PatientResources() {
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PatientInfo> getPatients(@PathParam("Doctorid") int doctorid) {
		return patientService.getAllPatients(doctorid);
	}

	@GET
	@Path("{Patientid}")
	@Produces(MediaType.APPLICATION_JSON)
	public PatientInfo getPatient(@PathParam("Doctorid") int doctorid, @PathParam("Patientid") int patientid) {
		return patientService.getPatient(doctorid, patientid);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Status addPatientDoctor(@PathParam("Doctorid") int doctorid, @PathParam("Patientid") int patientid) {
		return patientService.addPatientDoctor(doctorid, patientid);
	}

//	@PUT
//	@Path("{Patientid}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public PatientInfo updatePatient(@PathParam("Patientid") int id, Patient Patient) {
//		Patient.setId(id);
//		return patientService.updatePatient(Patient);
//	}

	@DELETE
	@Path("{Patientid}")
	public Status removePatient(@PathParam("Doctorid") int doctorid, @PathParam("Patientid") int patientid) {
		return patientService.removePatientDoctor(doctorid, patientid);
	}
	
	@Path("{Patientid}/Prescriptions")
	public PrescriptionResources getPrescriptions()
	{
		return new PrescriptionResources();
	}
}
