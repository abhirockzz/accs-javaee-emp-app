package com.oracle.cloud.employees;

import com.oracle.cloud.employees.facade.EmployeeFacade;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "EmployeeServlet",
        urlPatterns = {"/employee"}
)
public class EmployeeServlet extends HttpServlet {

    @Inject
    EmployeeFacade empService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("searchAction");
        if (action != null) {
            switch (action) {
                case "searchById":
                    System.out.println("Searching for emp by ID " + req.getParameter("idEmployee"));
                    System.out.println("Long version of ID -- " + Long.valueOf(req.getParameter("idEmployee")));
                    searchEmployeeById(req, resp, empService.find(Long.valueOf(req.getParameter("idEmployee"))));
                    break;
                case "searchByName":

                    searchEmployeeByName(req, resp, empService.findByName(req.getParameter("employeeName")));
                    break;
            }
        } else {
            List<Employee> emps = empService.findAll();
            forwardListEmployees(req, resp, emps);
        }
    }

    private void searchEmployeeById(HttpServletRequest req, HttpServletResponse resp, Employee employee)
            throws ServletException, IOException {

        req.setAttribute("employee", employee);
        req.setAttribute("action", "edit");
        String nextJSP = "/jsp/new-employee.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
    }

    private void searchEmployeeByName(HttpServletRequest req, HttpServletResponse resp, List<Employee> emps)
            throws ServletException, IOException {
        forwardListEmployees(req, resp, emps);
    }

    private void forwardListEmployees(HttpServletRequest req, HttpServletResponse resp, List employeeList)
            throws ServletException, IOException {
        String nextJSP = "/jsp/list-employees.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        req.setAttribute("employeeList", employeeList);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                addEmployeeAction(req, resp);
                break;
            case "edit":
                editEmployeeAction(req, resp);
                break;
            case "remove":
                removeEmployeeByName(req, resp);
                break;
        }

    }

    private void addEmployeeAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String birthday = req.getParameter("birthDate");
        String role = req.getParameter("role");
        String department = req.getParameter("department");
        String email = req.getParameter("email");
        Employee employee = new Employee(name, lastName, birthday, role, department, email);
        
        empService.create(employee);

        List<Employee> employeeList = empService.findAll();
        String message = "The new employee has been successfully created.";
        req.setAttribute("message", message);
        forwardListEmployees(req, resp, employeeList);
    }

    private void editEmployeeAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String birthday = req.getParameter("birthDate");
        String role = req.getParameter("role");
        String department = req.getParameter("department");
        String email = req.getParameter("email");

        long idEmployee = Integer.valueOf(req.getParameter("idEmployee"));
        empService.update(idEmployee, name, lastName, birthday, role, department, email);
        String message = "The employee has been successfully updated.";

        List<Employee> employeeList = empService.findAll();
        req.setAttribute("message", message);
        forwardListEmployees(req, resp, employeeList);
    }

    private void removeEmployeeByName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long empid = Integer.valueOf(req.getParameter("idEmployee"));
        empService.remove(empid);
        String message = "The employee has been successfully removed.";
        req.setAttribute("message", message);

        List<Employee> employeeList = empService.findAll();
        forwardListEmployees(req, resp, employeeList);
    }
}
