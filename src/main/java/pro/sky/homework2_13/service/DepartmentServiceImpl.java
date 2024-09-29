package pro.sky.homework2_13.service;


import org.springframework.stereotype.Service;
import pro.sky.homework2_13.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeServiceImpl employeeServiceImpl;
    Map<String, Employee> employees = new HashMap<>();

    public DepartmentServiceImpl(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @Override
    public int getSalarySumByDepartment(Integer departmentId) {
        return employeesDepartment(departmentId)
                .stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }


    @Override
    public Employee getMaxSalaryByDepartment(int department) {
        return employeeServiceImpl.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .max(comparing(Employee::getSalary))
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Employee getMinSalaryByDepartment(int department) {
        return employeeServiceImpl.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .min(comparing(Employee::getSalary))
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Collection<Employee> employeesDepartment(int department) {
        return employeeServiceImpl.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> allEmployeesDepartments() {
        return employeeServiceImpl.findAll().stream()
                .collect(groupingBy(Employee::getDepartment));
    }
}
