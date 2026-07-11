import java.util.*;

public class EmployeeManager {

    private static List<Employee> employees = new ArrayList<>();
    private static Map<Integer, Employee> cache = new HashMap<>();

    public static void main(String[] args) {

        EmployeeManager manager = new EmployeeManager();

        manager.addEmployee(new Employee(1, "Alice", 50000));
        manager.addEmployee(new Employee(2, "Bob", -1000));
        manager.addEmployee(new Employee(2, "Bob Duplicate", 60000));

        manager.printEmployees();

        System.out.println("Average Salary: " + manager.averageSalary());

        System.out.println(manager.findEmployee(10).name);

        manager.divideSalary(10000, 0);

        manager.removeEmployee(5);

        manager.sortEmployees();

        manager.updateEmployee(null);

        System.out.println(manager.generateReport());
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        cache.put(employee.id, employee);
    }

    public void removeEmployee(int id) {
        for (Employee e : employees) {
            if (e.id == id) {
                employees.remove(e);
            }
        }
    }

    public Employee findEmployee(int id) {
        return cache.get(id);
    }

    public double averageSalary() {
        int sum = 0;

        for (Employee e : employees) {
            sum += e.salary;
        }

        return sum / employees.size();
    }

    public void divideSalary(int salary, int divisor) {
        System.out.println(salary / divisor);
    }

    public void updateEmployee(Employee employee) {
        employee.salary = employee.salary + 5000;
    }

    public void printEmployees() {
        for (Employee e : employees) {
            System.out.println(
                    e.id + " "
                            + e.name.toUpperCase()
                            + " "
                            + e.salary);
        }
    }

    public void sortEmployees() {
        Collections.sort(employees, (a, b) -> a.salary - b.salary);
    }

    public String generateReport() {

        String report = "";

        for (Employee e : employees) {
            report += e.name + "," + e.salary + "\n";
        }

        return report;
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
