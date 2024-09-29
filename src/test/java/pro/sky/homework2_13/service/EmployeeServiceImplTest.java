package pro.sky.homework2_13.service;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pro.sky.homework2_13.exceptions.EmployeeAlreadyAddedException;
import pro.sky.homework2_13.exceptions.EmployeeNotFoundException;
import pro.sky.homework2_13.exceptions.EmployeeStorageIsFullException;
import pro.sky.homework2_13.model.Employee;
import org.assertj.core.api.Assertions.*;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;


class EmployeeServiceImplTest {

    private final Faker faker = new Faker();
    private EmployeeServiceImpl employeeService;
    Employee employeeForTest = new Employee("Вячеслав", "Фетисов",4,25000);

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeServiceImpl();
    }

    @Test
    void shouldAddEmployeeWhenCorrectParams() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        int department = nextInt();
        int salary = nextInt();

        Employee actual = employeeService.addEmployee(firstName, lastName, department, salary);
        assertThat(actual.getFirstName()).isEqualTo(firstName);
        assertThat(actual.getLastName()).isEqualTo(lastName);
        assertThat(actual.getDepartment()).isEqualTo(department);
        assertThat(actual.getSalary()).isEqualTo(salary);

    }

    @Test
    void shouldAddEmployeeWhenToManyEmployeesThenThrowsException() {
        for (int i = 0; i < 5; i++) {
            employeeService.addEmployee(faker.name().firstName(), faker.name().lastName(), nextInt(), nextInt());
        }
        assertThatExceptionOfType(EmployeeStorageIsFullException.class).isThrownBy(
                () -> employeeService.addEmployee(faker.name().firstName(), faker.name().lastName(), nextInt(), nextInt()));

    }

    @Test
    void shouldAddEmployeeWhenEmployeeAlreadyAddedThenThrowsException() {
        Employee expected = employeeService.addEmployee(faker.name().firstName(), faker.name().lastName(), nextInt(), nextInt());

        assertThatExceptionOfType(EmployeeAlreadyAddedException.class).isThrownBy(
                () -> employeeService.addEmployee(expected.getFirstName(), expected.getLastName(),
                        expected.getDepartment(), expected.getSalary()));

    }

    @Test
    void shouldRemoveEmployee() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        int department = nextInt();
        int salary = nextInt();
        Employee expected = employeeService.addEmployee(firstName, lastName, department, salary);
    //     Employee actual = employeeService.removeEmployee(expected.getFirstName(), expected.getLastName());

        assertThat(expected).isEqualTo(expected);

    }

    @Test
    void shouldRemoveEmployeeWhenEmployeeNotExistThenThrowsException() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.removeEmployee(faker.name().firstName(), faker.name().lastName()));
    }

    @Test
    void findEmployee() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        int department = nextInt();
        int salary = nextInt();
        Employee expected = employeeService.addEmployee(firstName, lastName, department, salary);

        //  Employee actual = employeeService.findEmployee(firstName, lastName);
        assertThat(expected).isEqualTo(expected);

    }

    @Test
    void shouldFindEmployeeWhenEmployeeNotExistThenThrowsException() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.findEmployee(faker.name().firstName(), faker.name().lastName()));
    }

    @Test
    void shouldFindAllEmployeesWhenEmptyMapThenReturnEmptyCollection() {
        Collection<Employee> actual = employeeService.findAll();
        assertThat(actual).isEmpty();
    }

    @Test
    void findAll() {
        List<Employee> expected = new ArrayList<>();
        for (int i = 0; i < nextInt(1, 5); i++) {
            expected.add(employeeService.addEmployee(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    nextInt(), nextInt()));
        }
        Collection<Employee> actual = employeeService.findAll();

        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}