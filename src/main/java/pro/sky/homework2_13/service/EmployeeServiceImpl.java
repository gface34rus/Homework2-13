package pro.sky.homework2_13.service;

import org.springframework.stereotype.Service;
import pro.sky.homework2_13.exceptions.EmployeeAlreadyAddedException;
import pro.sky.homework2_13.exceptions.EmployeeNotFoundException;
import pro.sky.homework2_13.exceptions.EmployeeStorageIsFullException;
import pro.sky.homework2_13.model.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final int MAX_EMPLOYEES = 5;
    Map<String, Employee> employees = new HashMap<>();


    @Override
    public Employee addEmployee(String firstName, String lastName, int department, int salary) {
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Хранилище заполнено! Невозможно добавить работника.");
        }
        Employee newEmployee = new Employee(firstName, lastName, department, salary);
        if (employees.containsKey(newEmployee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой работник уже добавлен в список");
        } else {
            employees.put(newEmployee.getFullName(), newEmployee);
        }
        return newEmployee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employeeToRemove = new Employee(firstName, lastName);
        if (employees.containsKey(employeeToRemove.getFullName())) {
            employees.remove(employeeToRemove.getFullName());
            return employeeToRemove;
        }
        throw new EmployeeNotFoundException("Работник не найден.");
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employeeToFind = new Employee(firstName, lastName);
        if (employees.containsKey(employeeToFind.getFullName())) {
            return employees.get(employeeToFind.getFullName());
        }
        throw new EmployeeNotFoundException("Работник не найден.");
    }

    @Override
    public Map<String, Employee> getAllEmployees() {
        return new HashMap<>(employees);
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

}

