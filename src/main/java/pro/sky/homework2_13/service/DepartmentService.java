package pro.sky.homework2_13.service;


import pro.sky.homework2_13.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    int getSalarySumByDepartment(Integer departmentId);

    Employee getMaxSalaryByDepartment(int department);

    Employee getMinSalaryByDepartment(int department);

    Collection<Employee> employeesDepartment(int department);

    Map<Integer, List<Employee>> allEmployeesDepartments();
}
