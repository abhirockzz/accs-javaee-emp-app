package com.oracle.cloud.employees;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "EMPLOYEE")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_SEQ")
    private long id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String title;
    private String department;
    private String email;

    public Employee() {
        //for JPA, JAXB
    }

    public Employee(String name, String lastName, String birthDate, String role, String department, String email) {
        this.firstName = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.title = role;
        this.department = department;
        this.email = email;     
    }


    public long getId() {
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }

    public String getName() {
        return firstName;
    }

    public void setName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getRole() {
        return title;
    }

    public void setRole(String role) {
        this.title = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name=" + firstName + 
                ", lastName=" + lastName + ", birthDate=" + birthDate + 
                ", role=" + title + ", department=" + department + 
                ", email=" + email + '}';
    }

    
}
