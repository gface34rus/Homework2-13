package pro.sky.homework2_13.service;

import pro.sky.homework2_13.model.Employee;

import java.util.Collection;
import java.util.Map;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, int departmentId, int salary);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Map<String, Employee> getAllEmployees();

    Collection<Employee> findAll();
}
