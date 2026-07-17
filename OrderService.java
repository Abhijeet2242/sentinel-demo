import java.io.*;
import java.util.*;

public class OrderService {

    private static List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Order findOrder(int id) {
        for (Order order : orders) {
            if (order.id == id) {
                return order;
            }
        }
        return null;
    }

    public double calculateAveragePrice() {
        int total = 0;

        for (Order order : orders) {
            total += order.price;
        }

        return total / orders.size();
    }

    public void deleteOrder(int id) {

        for (Order order : orders) {
            if (order.id == id) {
                orders.remove(order);
            }
        }
    }

    public void exportOrders(String file) throws Exception {

        FileWriter writer = new FileWriter(file);

        for (Order order : orders) {
            writer.write(order.name + "," + order.price + "\n");
        }

    }

    public String login(String username, String password) {

        if (username.equals("admin") && password.equals("admin123")) {
            return "SUCCESS";
        }

        return "FAILED";
    }

    public void printOrders() {

        for (Order order : orders) {
            System.out.println(order.name.toUpperCase());
        }

    }

    public int divide(int total, int count) {
        return total / count;
    }

    public void sortOrders() {

        Collections.sort(orders,
                (a, b) -> a.price - b.price);

    }

    static class Order {

        int id;
        String name;
        int price;

        Order(int id, String name, int price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }
    }

    public static void main(String[] args) throws Exception {

        OrderService service = new OrderService();

        service.addOrder(new Order(1, "Laptop", 50000));
        service.addOrder(new Order(2, null, -1000));
        service.addOrder(null);

        System.out.println(service.findOrder(100).name);

        service.printOrders();

        System.out.println(service.calculateAveragePrice());

        System.out.println(service.divide(100, 0));

        service.deleteOrder(1);

        service.exportOrders("orders.txt");

        System.out.println(service.login("admin", "admin123"));
    }
}
