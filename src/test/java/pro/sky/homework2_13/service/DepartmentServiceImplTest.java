package pro.sky.homework2_13.service;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.homework2_13.model.Employee;
import org.assertj.core.api.Assertions.*;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    private EmployeeServiceImpl employeeService;
    @InjectMocks
    private DepartmentServiceImpl departmentService;
    private static final Faker faker = new Faker();
    private static final Collection<Employee> employees = List.of(
            new Employee(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    nextInt(), nextInt()),
            new Employee(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    nextInt(), nextInt()),
            new Employee(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    nextInt(), nextInt()));

    @Test
    void getSalarySumByDepartment_WhenCorrectData_ThenReturnCorrectSum() {
        when(employeeService.findAll()).thenReturn(employees);
        int department = employees.iterator().next().getDepartment();
        int expected = 0;
        for (Employee employee : employees) {
            if (employee.getDepartment() == department) {
                expected += employee.getSalary();
            }
        }
        int actual = departmentService.getSalarySumByDepartment(department);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getSalarySumByDepartment_WhenEmptyMap_ThenReturnZero() {
        when(employeeService.findAll()).thenReturn(new ArrayList<>());

        int department = employees.iterator().next().getDepartment();
        int actual = departmentService.getSalarySumByDepartment(department);

        assertThat(actual).isZero();
    }

    @Test
    void getMaxSalaryByDepartment_WhenCorrectData_ThenReturnCorrectMaxSalary() {
        int department = employees.iterator().next().getDepartment();
        when(employeeService.findAll()).thenReturn(employees);
        int expected = employees.stream()
                .filter(employee -> employee.getDepartment() == department)
                .map(Employee::getSalary)
                .max(Comparator.naturalOrder())
                .orElseThrow(IllegalArgumentException::new);

        Employee actual = departmentService.getMaxSalaryByDepartment(department);
        assertThat(actual.getSalary()).isEqualTo(expected);

    }

    @Test
    void getMaxSalaryByDepartment_WhenEmptyMap_ThenThrowException() {
        when(employeeService.findAll()).thenReturn(new ArrayList<>());
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> departmentService.getMaxSalaryByDepartment(1));

    }

    @Test
    void getMaxSalaryByDepartment_WhenNotContainsDepartmentEmployees_ThenThrowException() {
        when(employeeService.findAll()).thenReturn(new ArrayList<>());
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> departmentService.getMaxSalaryByDepartment(1));

    }

    @Test
    void getMinSalaryByDepartment_WhenCorrectData_ThenReturnCorrectMinSalary() {
        int department = employees.iterator().next().getDepartment();
        when(employeeService.findAll()).thenReturn(employees);
        int expected = employees.stream()
                .filter(employee -> employee.getDepartment() == department)
                .map(Employee::getSalary)
                .min(Comparator.naturalOrder())
                .orElseThrow(IllegalArgumentException::new);

        Employee actual = departmentService.getMaxSalaryByDepartment(department);
        assertThat(actual.getSalary()).isEqualTo(expected);
    }

    @Test
    void getMinSalaryByDepartment_WhenEmptyMap_ThenThrowException() {
        when(employeeService.findAll()).thenReturn(new ArrayList<>());
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> departmentService.getMinSalaryByDepartment(1));

    }

    @Test
    void getMinSalaryByDepartment_WhenNotContainsDepartmentEmployees_ThenThrowException() {
        when(employeeService.findAll()).thenReturn(new ArrayList<>());
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> departmentService.getMinSalaryByDepartment(1));

    }

    @Test
    void employeesDepartment_WhenCorrectDepartment_ThenReturnEmployeeList() {
        when(employeeService.findAll()).thenReturn(employees);
        int department = employees.iterator().next().getDepartment();
        List<Employee> expected = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getDepartment() == department) {
                expected.add(employee);
            }
        }
        Collection<Employee> actual = departmentService.employeesDepartment(department);
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    void employeesDepartment_WhenNotContainsDepartmentEmployees_ThenReturnEmptyList() {
        when(employeeService.findAll()).thenReturn(employees);

        Collection<Employee> actual = departmentService.employeesDepartment(1);
        assertThat(actual).isEmpty();
    }

    @Test
    void allEmployeesDepartments_WhenCorrectData_ThenReturnCorrectList() {
        when(employeeService.findAll()).thenReturn(employees);
        Map<Integer, List<Employee>> expected = employees.stream()
                .collect(groupingBy(Employee::getDepartment));

        Map<Integer, List<Employee>> actual = departmentService.allEmployeesDepartments();
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expected);
    }
}