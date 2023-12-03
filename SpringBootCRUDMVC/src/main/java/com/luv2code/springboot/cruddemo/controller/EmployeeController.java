package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService thEmployeeService) {
        this.employeeService = thEmployeeService;
    }

    //add mapping for /list
    @GetMapping("/list")
    public String listEmployees(Model theModel) {
        // Get employees from the db
        List<Employee> theEmployees = employeeService.findAll();

        //Add it to spring model
        theModel.addAttribute("employees", theEmployees);

        return "list-employees";
    }

   @GetMapping("/showFormForAdd")
    public String showFormForAdd (Model theModel) {
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public  String showFormForUpdate (@RequestParam("employeeId") int theId, Model theModel) {
        // Get employee from database
        Employee employee = employeeService.findById(theId);
        // Add employee to the model
        theModel.addAttribute("employee", employee);
        // Send to update form
        return "employee-form";
    }

    @GetMapping ("/delete")
    public String deleteEmployee (@RequestParam("employeeId") int theId) {
        employeeService.deleteById(theId);

        return "redirect:/employees/list";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
        employeeService.save(theEmployee);

        // use a redirect to avoid duplicate submissions

        return "redirect:/employees/list";
    }
}
