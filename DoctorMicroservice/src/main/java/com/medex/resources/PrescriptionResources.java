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

import com.medex.communicationmodules.Status;
import com.medex.model.Prescription;
import com.medex.services.PrescriptionService;



//Request resources which acts as a layer before our Prescription services

public class PrescriptionResources {
	PrescriptionService prescriptionService = new PrescriptionService();

	public PrescriptionResources() {
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Prescription> getPrescriptions(@PathParam("Doctorid") int doctorid, @PathParam("Patientid") int patientid) {
		return prescriptionService.getAllPrescriptions(doctorid, patientid);
	}

	@GET
	@Path("{Prescriptionid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Prescription getPrescription(@PathParam("Doctorid") int doctorid, @PathParam("Patientid") int patientid, @PathParam("Prescriptionid") int prescriptionid) {
		return prescriptionService.getPrescription(doctorid, patientid, prescriptionid);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Prescription addPrescription(@PathParam("Doctorid") int doctorid, @PathParam("Patientid") int patientid, Prescription aPrescription) {
		return prescriptionService.addPrescription(doctorid, patientid, aPrescription);
	}

	@PUT
	@Path("{Prescriptionid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Prescription updatePrescription(@PathParam("Doctorid") int doctorid, @PathParam("Patientid") int patientid, @PathParam("Prescriptionid") int prescriptionid, Prescription aPrescription) {
		aPrescription.setId(prescriptionid);
		return prescriptionService.updatePrescription(doctorid, patientid, prescriptionid, aPrescription);
	}

	@DELETE
	@Path("{Prescriptionid}")
	public Status removePrescription(@PathParam("Doctorid") int doctorid, @PathParam("Patientid") int patientid, @PathParam("Prescriptionid") int prescriptionid) {
		return prescriptionService.removePrescription(doctorid, patientid, prescriptionid);
	}
}
