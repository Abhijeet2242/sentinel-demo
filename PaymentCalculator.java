public class PaymentCalculator {

    public double calculateDiscount(double amount, int loyaltyYears) {
        double discount = 0;
        if (loyaltyYears > 5) {
            discount = amount * 0.15;
        } else if (loyaltyYears > 2) {
            discount = amount * 0.10;
        } else {
            discount = amount * 0.05;
        }
        return amount - discount;
    }

    public double applyTax(double price, double taxRate) {
        return price + (price * taxRate);
    }
}
