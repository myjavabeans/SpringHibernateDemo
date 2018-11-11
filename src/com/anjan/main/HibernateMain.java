package com.anjan.main;

import java.util.List;

import org.hibernate.property.EmbeddedPropertyAccessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anjan.hibernate.bean.EmployeeBean;
import com.anjan.hibernate.bo.EmployeeBeanBO;

/**
 * Main Class
 * @author Anjan 
 *
 */
public class HibernateMain {

	public static void main(String args[]) {

		ApplicationContext context = new ClassPathXmlApplicationContext("/resources/beans-location.xml");
		
		EmployeeBeanBO employeeBeanBO = (EmployeeBeanBO)context.getBean("employeeBo");
		
		/**
		 * insert
		 */
		EmployeeBean bean = new EmployeeBean();
		bean.setId(1);
		bean.setName("Anjan");
		employeeBeanBO.saveEmployee(bean);
		
		bean = new EmployeeBean();
		bean.setId(2);
		bean.setName("Jasmeet");
		employeeBeanBO.saveEmployee(bean);
		
		bean = new EmployeeBean();
		bean.setId(3);
		bean.setName("India");
		employeeBeanBO.saveEmployee(bean);
		
		/**
		 * select
		 */
		bean = employeeBeanBO.getBeanById(1);
		System.out.println(bean.getId()+" - "+bean.getName());
		
		/**
		 * Update
		 */
		bean = employeeBeanBO.getBeanById(2);
		bean.setName("Jasmeet Kaur");
		employeeBeanBO.updateEmployee(bean);

		/**
		 * get all employee
		 */
		List<EmployeeBean> list = employeeBeanBO.getAllEmployee();
		
		for(EmployeeBean eb : list){
			System.out.println(eb.getId() + " - "+eb.getName());
		}
		
		/**
		 * delete
		 */
		bean = employeeBeanBO.getBeanById(1);
		employeeBeanBO.deleteEmployee(bean);
		
		/**
		 * get all employee
		 */
		list = employeeBeanBO.getAllEmployee();
		
		for(EmployeeBean eb : list){
			System.out.println(eb.getId() + " - "+eb.getName());
		}
		
		System.out.println("Named Query Result");
		bean = employeeBeanBO.getEmployeeByIdHQL(3);
		System.out.println(bean.getId() + " - "+bean.getName());
		
		list =  employeeBeanBO.getAllEmployeeHQL();
		for(EmployeeBean eb : list){
			System.out.println(eb.getId() + " - "+eb.getName());
		}
		
		List<Object[]> empList = employeeBeanBO.getAllEmployeeSQL();
		for(Object[] row : empList){
			for(Object obj : row){
				System.out.print(obj+"::");
			}
			System.out.println("");
		}
		
		empList = employeeBeanBO.getEmployeeByIdSQL(2);
		for(Object[] row : empList){
			for(Object obj : row){
				System.out.print(obj+"::");
			}
			System.out.println("");
		}
		
		
	}
}
