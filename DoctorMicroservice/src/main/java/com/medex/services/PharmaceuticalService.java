package com.medex.services;

import java.util.List;

import com.medex.database.PharmaceuticalDB;
import com.medex.dependentresources.Pharmaceutical;


//This is the "backend" of our resources. This is where the logic is executed; the logic is basic since the database handles itself very well.
public class PharmaceuticalService {
	PharmaceuticalDB pharmaceuticaldb = new PharmaceuticalDB(); //(Instead of the pseudo-database)
	public PharmaceuticalService() {} 
	
	//All what is below is just calling the functions belonging to the pharmacies' database/table.
	
	public List<Pharmaceutical>getAllPharmaceuticals()
	{
		return pharmaceuticaldb.getPharmaceuticals();
	}

}


