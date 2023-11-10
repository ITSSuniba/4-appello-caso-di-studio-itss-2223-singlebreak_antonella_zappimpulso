package org.example;

public class DiscountCalculator {
    public static boolean isDiscountApplicable(double price, int quantity) {
        if (price <= 0 || quantity <= 0) {
            return false;
        }
        return true;
    }
    public static double calculateProductDiscount(double price, int quantity) {
        if (!isDiscountApplicable(price, quantity)) {
            return 0.0;
        }

        double discount = 0.0;

        if (quantity >= 10 && quantity < 20) {
            discount = 0.1;
        }else {
            discount = 0.15;
        }

        return price * quantity * (1 - discount);
    }

}
