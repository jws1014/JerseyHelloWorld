package com.jschlim.rest;
import com.jschlim.Employee;

import org.hibernate.Session;
import com.jschlim.persistence.HibernateUtil;

public class HibernateService {

	public static void main(String[] args) {
		
		System.out.println("Maven + Hibernate + MySQL");
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		Employee emp = new Employee();
		emp.setId(20);
		emp.setName("John");
		
		session.save(emp);
		session.getTransaction().commit();
		
		HibernateUtil.shutdown();
	}

}
