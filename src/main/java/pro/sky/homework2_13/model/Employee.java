package pro.sky.homework2_13.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private int salary;
    private int department;

    public Employee(String firstName, String lastName) {
    }

    public int getSalary() {
        return salary;
    }

    public int getDepartment() {
        return department;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int setDepartment(int department) {
        if (department < 1 || department > 5) {
            throw new IllegalArgumentException("Недопустимый номер отдела. Должно быть от 1 до 5.");
        }
        return department;
    }

    public Employee(String firstName, String lastName, int department, int salary) {
        checkStringParam(firstName);
        this.firstName = firstName;
        checkStringParam(lastName);
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    private String checkStringParam(String... strings) {
        for (String string : strings)
            if (!StringUtils.isAlpha(string)) {
                throw new RuntimeException("Не корректный символ!!");
            }
        return StringUtils.capitalize(Arrays.toString(strings));
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "Работник - " + firstName + " " + lastName;
    }
}
