package multiplex.serviceclasses;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentService();
    }

    // Test successful payment
    @Test
    void testProcessPayment_Success() {
        boolean result = paymentService.processPayment(
                "1234567812345678",
                "John Smith",
                "12/27",
                "123");

        assertTrue(result);
    }

    // Null input tests

    @Test
    void testProcessPayment_NullCardNumber() {
        boolean result = paymentService.processPayment(
                null,
                "John Smith",
                "12/27",
                "123");

        assertFalse(result);
    }

    @Test
    void testProcessPayment_NullCardHolderName() {
        boolean result = paymentService.processPayment(
                "1234567812345678",
                null,
                "12/27",
                "123");

        assertFalse(result);
    }

    @Test
    void testProcessPayment_NullExpiryDate() {
        boolean result = paymentService.processPayment(
                "1234567812345678",
                "John Smith",
                null,
                "123");

        assertFalse(result);
    }

    @Test
    void testProcessPayment_NullCVV() {
        boolean result = paymentService.processPayment(
                "1234567812345678",
                "John Smith",
                "12/27",
                null);

        assertFalse(result);
    }

    // Invalid card number tests

    @Test
    void testProcessPayment_CardNumberTooShort() {
        boolean result = paymentService.processPayment(
                "12345678",
                "John Smith",
                "12/27",
                "123");

        assertFalse(result);
    }

    @Test
    void testProcessPayment_CardNumberTooLong() {
        boolean result = paymentService.processPayment(
                "123456781234567890",
                "John Smith",
                "12/27",
                "123");

        assertFalse(result);
    }

    @Test
    void testProcessPayment_CardNumberContainsLetters() {
        boolean result = paymentService.processPayment(
                "12345678ABCD5678",
                "John Smith",
                "12/27",
                "123");

        assertFalse(result);
    }

    // Invalid CVV tests

    @Test
    void testProcessPayment_CVVTooShort() {
        boolean result = paymentService.processPayment(
                "1234567812345678",
                "John Smith",
                "12/27",
                "12");

        assertFalse(result);
    }

    @Test
    void testProcessPayment_CVVTooLong() {
        boolean result = paymentService.processPayment(
                "1234567812345678",
                "John Smith",
                "12/27",
                "1234");

        assertFalse(result);
    }

    @Test
    void testProcessPayment_CVVContainsLetters() {
        boolean result = paymentService.processPayment(
                "1234567812345678",
                "John Smith",
                "12/27",
                "1A3");

        assertFalse(result);
    }

    // Empty string tests

    @Test
    void testProcessPayment_EmptyCardNumber() {
        boolean result = paymentService.processPayment(
                "",
                "John Smith",
                "12/27",
                "123");

        assertFalse(result);
    }

    @Test
    void testProcessPayment_EmptyCVV() {
        boolean result = paymentService.processPayment(
                "1234567812345678",
                "John Smith",
                "12/27",
                "");

        assertFalse(result);
    }
}