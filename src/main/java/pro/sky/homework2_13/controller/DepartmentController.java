package pro.sky.homework2_13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.homework2_13.model.Employee;
import pro.sky.homework2_13.service.DepartmentServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {


    private final DepartmentServiceImpl departmentService;

    @Autowired
    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    // Получает список сотрудников по департаменту
    @GetMapping("/{id}/employees")
    public Collection<Employee> employeesDepartment(@PathVariable("id") int id) {
        return departmentService.employeesDepartment(id);
    }

    // Возвращает сумму зарплат по департаменту
    @GetMapping("/{id}/salary/sum")
    public int getSalarySumByDepartment(@PathVariable("id") int id) {
        return departmentService.getSalarySumByDepartment(id);
    }

    // Возвращает максимальную зарплату по департаменту
    @GetMapping("/{id}/salary/max")
    public Employee getMaxSalaryByDepartment(@PathVariable("id") int id) {
        return departmentService.getMaxSalaryByDepartment(id);
    }

    @GetMapping("/{id}/salary/min")
    public Employee getMinSalaryByDepartment(@PathVariable("id") int id) {
        return departmentService.getMinSalaryByDepartment(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> allEmployeesDepartments() {
        return departmentService.allEmployeesDepartments();
    }
}
