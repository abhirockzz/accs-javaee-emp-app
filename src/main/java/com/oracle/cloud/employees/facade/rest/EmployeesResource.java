package com.oracle.cloud.employees.facade.rest;

import com.oracle.cloud.employees.facade.EmployeeFacade;
import com.oracle.cloud.employees.Employee;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("employees")
public class EmployeesResource {
    
    @Inject
    EmployeeFacade empService;

    public EmployeesResource() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Employee entity) {
        empService.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Employee entity) {
        empService.update(id, entity.getName(), entity.getLastName(), entity.getBirthDate(), 
                entity.getRole(), entity.getDepartment(), entity.getEmail());
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        empService.remove(id);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Employee find(@PathParam("id") Long id) {
        return empService.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Employee> findAll() {
        return empService.findAll();
    }
    
}
