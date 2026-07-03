package multiplex.serviceclasses;

public class PaymentService {
    
    public boolean processPayment(String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        
        //Check for null or empty values
        if (cardNumber == null || cardHolderName == null || expiryDate == null || cvv == null) {
            System.out.println("Payment failed: Missing payment information.");
            return false;
        }

        //Validate card number length and format (basic validation)
        if (cardNumber.length() != 16 || !cardNumber.matches("\\d+")) {
            System.out.println("Payment failed: Invalid card number.");
            return false;
        }

        //Validate CVV length and format (basic validation)
        if (cvv.length() != 3 || !cvv.matches("\\d+")) {
            System.out.println("Payment failed: Invalid CVV.");
            return false;
    }

        //Successful payment
        System.out.println("Payment processed successfully.");
        return true;
    }

}
