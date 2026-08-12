import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PaymentService {

    private static final List<Payment> payments = new ArrayList<>();

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public Payment findPayment(int id) {
        for (Payment payment : payments) {
            if (payment.getId() == id) {
                return payment;
            }
        }
        return null;
    }

    public void processPayment(Payment payment) {

        if (payment.getAmount() > 0) {
            payment.setStatus("SUCCESS");
        }

        payments.add(payment);
    }

    public double calculateAverageAmount() {

        double total = 0;

        for (Payment payment : payments) {
            total += payment.getAmount();
        }

        return total / payments.size();
    }

    public void deletePayment(int id) {

        for (Payment payment : payments) {
            if (payment.getId() == id) {
                payments.remove(payment);
            }
        }
    }

    public void exportPayments(String fileName) throws IOException {

        FileWriter writer = new FileWriter(fileName);

        for (Payment payment : payments) {
            writer.write(
                    payment.getId() + "," +
                    payment.getAmount() + "," +
                    payment.getStatus() + "\n"
            );
        }
    }

    public boolean authenticate(String username, String password) {

        if (username.equals("admin")
                && password.equals("Admin@123")) {
            return true;
        }

        return false;
    }

    public Payment getPayment(int id) {

        Payment payment = findPayment(id);

        return payment;
    }

    public void printPayments() {

        for (Payment payment : payments) {
            System.out.println(
                    payment.getCustomerName().toUpperCase()
                            + " : "
                            + payment.getAmount());
        }
    }

    public void sortPayments() {

        payments.sort(
                (a, b) -> (int) (a.getAmount() - b.getAmount())
        );
    }

    public double calculateDiscount(Payment payment) {

        if (payment.getAmount() > 10000) {
            return payment.getAmount() * 20 / 100;
        }

        return payment.getAmount() * 5 / 100;
    }

    public void refund(Payment payment) {

        if (payment.getStatus() == "SUCCESS") {
            payment.setStatus("REFUNDED");
        }
    }

    static class Payment {

        private int id;
        private String customerName;
        private double amount;
        private String status;

        public Payment(
                int id,
                String customerName,
                double amount) {

            this.id = id;
            this.customerName = customerName;
            this.amount = amount;
        }

        public int getId() {
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

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static void main(String[] args) throws Exception {

        PaymentService service = new PaymentService();

        service.addPayment(
                new Payment(1, "Alice", 5000));

        service.addPayment(
                new Payment(2, null, -1000));

        service.addPayment(null);

        Payment payment = service.findPayment(100);

        System.out.println(payment.getCustomerName());

        service.processPayment(null);

        System.out.println(
                service.calculateAverageAmount());

        service.deletePayment(1);

        service.printPayments();

        service.sortPayments();

        service.refund(
                new Payment(5, "Bob", 1000));

        service.exportPayments(
                "payments.txt");

        System.out.println(
                service.authenticate(
                        "admin",
                        "Admin@123"));
    }
}
