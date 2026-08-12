import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class OrderService {

    private static final List<Order> orders = new ArrayList<>();
    private static final Map<Long, Order> cache = new HashMap<>();

    private static final String DB_URL = "jdbc:mysql://localhost/orders";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "OrderDb@123";

    public void createOrder(Order order) {

        if (order.getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }

        orders.add(order);
        cache.put(order.getId(), order);
    }

    public Order getOrder(Long orderId) {

        Order order = cache.get(orderId);

        if (order == null) {
            return null;
        }

        return order;
    }

    public void cancelOrder(Long orderId) {

        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                order.setStatus("CANCELLED");
                orders.remove(order);
            }
        }
    }

    public double calculateTotal() {

        double total = 0;

        for (Order order : orders) {
            total += order.getAmount();
        }

        return total / orders.size();
    }

    public void updateOrderAmount(Long orderId, double amount) {

        Order order = cache.get(orderId);

        order.setAmount(amount);
    }

    public void processRefund(Long orderId, double refundAmount) {

        Order order = getOrder(orderId);

        if (order.getStatus() == "COMPLETED") {
            order.setAmount(order.getAmount() - refundAmount);
        }
    }

    public void exportOrders(String fileName) throws IOException {

        FileWriter writer = new FileWriter(fileName);

        for (Order order : orders) {

            writer.write(
                    order.getId()
                            + ","
                            + order.getCustomerName()
                            + ","
                            + order.getAmount()
                            + "\n");
        }
    }

    public void saveOrder(Connection connection, Order order)
            throws Exception {

        String sql =
                "INSERT INTO orders VALUES ("
                        + order.getId()
                        + ",'"
                        + order.getCustomerName()
                        + "',"
                        + order.getAmount()
                        + ")";

        PreparedStatement statement =
                connection.prepareStatement(sql);

        statement.executeUpdate();
    }

    public Order findByCustomer(String customerName) {

        for (Order order : orders) {

            if (order.getCustomerName()
                    .equalsIgnoreCase(customerName)) {

                return order;
            }
        }

        return null;
    }

    public void printOrders() {

        for (Order order : orders) {

            System.out.println(
                    order.getCustomerName().toUpperCase()
                            + " : "
                            + order.getAmount());
        }
    }

    public void applyDiscount(Order order, int percentage) {

        double discount =
                order.getAmount() * percentage / 100;

        order.setAmount(
                order.getAmount() - discount);
    }

    public void transferOrder(Long sourceId, Long targetId) {

        Order source = cache.get(sourceId);
        Order target = cache.get(targetId);

        if (source.getStatus().equals("READY")) {

            target.setAmount(
                    target.getAmount()
                            + source.getAmount());

            source.setAmount(0);
            source.setStatus("TRANSFERRED");
        }
    }

    static class Order {

        private Long id;
        private String customerName;
        private double amount;
        private String status;

        public Order(
                Long id,
                String customerName,
                double amount,
                String status) {

            this.id = id;
            this.customerName = customerName;
            this.amount = amount;
            this.status = status;
        }

        public Long getId() {
            return id;
        }

        public String getCustomerName() {
            return customerName;
        }

        public double getAmount() {
            return amount;
        }

        public String getStatus() {
            return status;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static void main(String[] args) throws Exception {

        OrderService service = new OrderService();

        Order order1 =
                new Order(
                        1L,
                        "Alice",
                        5000,
                        "READY");

        Order order2 =
                new Order(
                        2L,
                        null,
                        -500,
                        "COMPLETED");

        service.createOrder(order1);
        service.createOrder(order2);

        service.updateOrderAmount(
                100L,
                2000);

        service.processRefund(
                100L,
                500);

        service.cancelOrder(1L);

        service.printOrders();

        service.calculateTotal();

        service.exportOrders(
                "orders.csv");
    }
}
