package com.oracle.cloud.employees.facade;

import com.oracle.cloud.employees.Employee;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EmployeeFacade {

    @PersistenceContext(unitName = "employees_PU")
    private EntityManager em;
    
    public Employee find(Long id){
        return em.find(Employee.class, id);
    }
    
    public void create(Employee emp){
        em.persist(emp);
    }
    public List<Employee> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Employee.class));
        return em.createQuery(cq).getResultList();
    }
    
    public List<Employee> findByName(String name){
        String query = "SELECT * FROM EMPLOYEE WHERE LASTNAME ='"+name+"' OR FIRSTNAME='"+name+"'";
        return em.createNativeQuery(query, Employee.class).getResultList();
    }
    
    public void update(Long empid, String name, String lastName, String birthday, String role, String department, String email){
        Employee emp = find(empid);
        emp.setBirthDate(birthday);
        emp.setDepartment(department);
        emp.setEmail(email);
        emp.setLastName(lastName);
        emp.setName(name);
        emp.setRole(role);
        
        em.merge(emp);
        System.out.println("Employee updated......");
        
    }
    
    public void remove(Long empid){
        Employee emp = find(empid);
        em.remove(emp);
        System.out.println("Employee removed......");
    }
}
