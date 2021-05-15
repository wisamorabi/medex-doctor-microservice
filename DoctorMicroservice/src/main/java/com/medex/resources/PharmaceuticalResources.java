package com.medex.resources;

import java.util.List;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.medex.dependentresources.Pharmaceutical;
import com.medex.services.PharmaceuticalService;


//Request resources which acts as a layer before our Pharmaceutical services
@Path("/pharmaceuticals")
public class PharmaceuticalResources {
	PharmaceuticalService pharmaceuticalService = new PharmaceuticalService();

	public PharmaceuticalResources() {
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pharmaceutical> getPharmaceuticals() {
		return pharmaceuticalService.getAllPharmaceuticals();
	}
	

}
