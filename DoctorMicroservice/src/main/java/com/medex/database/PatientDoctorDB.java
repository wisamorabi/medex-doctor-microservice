package com.medex.database;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.medex.model.PatientDoctor;

//This class is specifically for the patientdoctors database operations
public class PatientDoctorDB {
	public void insertPatientDoctor(PatientDoctor patientdoctor)
	{
		Transaction transaction = null; //You have to make a transaction object
		try (Session session = HibernateUtil.getDoctorSessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			session.save(patientdoctor); //Save transaction allows us to store the patientdoctor object into the database (This is like insert with the fields, etc, etc)
								   //But hibernate knows what to do using the annotation on the patientdoctor class
			
			// commit transaction		
			transaction.commit(); //Finalize transaction
		}
		catch (Exception e) //If anything goes wrong
		{
			if (transaction != null)
			{
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	//This is the update, which patientdoctor we want to delete
	public void updatePatientDoctor(PatientDoctor patientdoctor)
	{
		Transaction transaction = null; //You have to make a transaction object
		try (Session session = HibernateUtil.getDoctorSessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			session.saveOrUpdate(patientdoctor); //Save transaction allows us to store the patientdoctor object into the database (This is like insert with the fields, etc, etc)
										   //But hibernate knows what to do using the annotation on the patientdoctor class
			
			// commit transaction
			
			transaction.commit(); //Finalize transaction
		}
		catch (Exception e) //If anything goes wrong
		{
			if (transaction != null)
			{
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	//Id of what you want to delete
	public void deletePatientDoctor(int doctorid, int patientid)
	{
		Transaction transaction = null; //You have to make a transaction object
		PatientDoctor patientdoctor = null;
		try (Session session = HibernateUtil.getDoctorSessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			
			
			
			String hql = " FROM PatientDoctor H WHERE H.doctorid = :doctorid AND H.patientid = :patientid"; //From the patientdoctor table
			Query query = session.createQuery(hql);
			query.setParameter("doctorid", doctorid); //The parameter ":id" is set to the id we passed.
			query.setParameter("patientid", patientid); //The parameter ":id" is set to the id we passed.
			List results = query.getResultList(); //The results are given to us in a list.
												  //Since the id is unique, we will get a list of one item
			
			//If the result is not null, we get a single patientdoctor object
			if (results != null && !results.isEmpty())
			{
				patientdoctor = (PatientDoctor) results.get(0); //So, we retrieve said patientdoctor from the first index in the list
				session.delete(patientdoctor); //We delete that patientdoctor
			}
			
			// commit transaction
			transaction.commit(); //Finalize transaction
		}
		catch (Exception e) //If anything goes wrong
		{
			if (transaction != null)
			{
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	//Retrieve all patientdoctors from the database and store them in a list
	public List<PatientDoctor> getPatientDoctors(int doctorid)
	{
		Transaction transaction = null;
		List<PatientDoctor> patientdoctors = null;
		
		try (Session session = HibernateUtil.getDoctorSessionFactory().openSession())
		{
			transaction = session.beginTransaction();
			patientdoctors = session.createQuery("from PatientDoctor P WHERE P.doctorid = :doctorid", PatientDoctor.class).setParameter("doctorid", doctorid).list(); //This is a hibernate query (Get all patientdoctors from the patientdoctors database)
																		 //Each returned row is a patientdoctor object inserted into the list of patientdoctors --> patientdoctors
			transaction.commit();
		}
		return patientdoctors;
	}
	
	public PatientDoctor getPatientDoctor(int doctorid, int patientid)
	{
		Transaction transaction = null;
		PatientDoctor patientdoctor = null;
		try (Session session = HibernateUtil.getDoctorSessionFactory().openSession())
		{
			//start a transaction
			transaction = session.beginTransaction();
			
			// get one object
			String hql = " FROM PatientDoctor H WHERE H.doctorid = :doctorid AND H.patientid = :patientid"; //From the patientdoctor table
			Query query = session.createQuery(hql);
			query.setParameter("doctorid", doctorid); //The parameter ":id" is set to the id we passed.
			query.setParameter("patientid", patientid); //The parameter ":id" is set to the id we passed.
			List results = query.getResultList(); //The results are given to us in a list.
												  //Since the id is unique, we will get a list of one item
			
			//If the result is not null, we get a single patientdoctor object
			if (results != null && !results.isEmpty())
			{
				patientdoctor = (PatientDoctor) results.get(0); //So, we retrieve said patientdoctor from the first index in the list
			}
			//commit transaction
			transaction.commit();
		}
		catch (Exception e)
		{
			if (transaction != null)
			{
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return patientdoctor; //Return the patientdoctor object retrieved
	}
	
}
