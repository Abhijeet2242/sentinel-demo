import java.util.*;

public class EmployeeService {

    private static List<Employee> employees = new ArrayList<>();
    private static Map<Integer, Employee> cache = new HashMap<>();

    public static void main(String[] args) {

        EmployeeService service = new EmployeeService();

        service.addEmployee(new Employee(1, "Alice", 50000));
        service.addEmployee(new Employee(2, "Bob", -1000));
        service.addEmployee(new Employee(2, "Duplicate", 70000));
        service.addEmployee(null);

        System.out.println(service.findEmployee(100).getName());

        service.updateSalary(null, 5000);

        service.divideSalary(10000, 0);

        service.removeEmployee(1);

        service.printEmployees();

        System.out.println(service.calculateAverageSalary());

        service.sortEmployees();

        System.out.println(service.generateReport());

        Employee emp = new Employee(5, null, 1000);
        System.out.println(emp.getName().toUpperCase());
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        cache.put(employee.getId(), employee);
    }

    public Employee findEmployee(int id) {
        return cache.get(id);
    }

    public void updateSalary(Employee employee, int increment) {
        employee.salary += increment;
    }

    public void divideSalary(int salary, int divisor) {
        System.out.println(salary / divisor);
    }

    public void removeEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employees.remove(employee);
            }
        }
    }

    public double calculateAverageSalary() {

        int total = 0;

        for (Employee employee : employees) {
            total += employee.salary;
        }

        return total / employees.size();
    }

    public void sortEmployees() {
        Collections.sort(employees,
                (a, b) -> a.salary - b.salary);
    }

    public String generateReport() {

        String report = "";

        for (Employee employee : employees) {
            report = report + employee.name + " : " + employee.salary + "\n";
        }

        return report;
    }

    public void printEmployees() {

        for (Employee employee : employees) {
            System.out.println(
                    employee.id + " "
                            + employee.name.toUpperCase()
                            + " "
                            + employee.salary);
        }
    }

    static class Employee {

        int id;
        String name;
        int salary;

        Employee(int id, String name, int salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object obj) {
            Employee other = (Employee) obj;
            return this.id == other.id;
        }

        @Override
        public int hashCode() {
            return new Random().nextInt();
        }
    }
}
