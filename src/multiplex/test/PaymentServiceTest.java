package multiplex.test;

import multiplex.serviceclasses.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


//Validates the business logic for processing payments, focusing strictly on  input validation, boundary checks, and error handling for malformed data.
//This service evaluates pure logic and does not interact with a database, therefore Mockito and dependency injection are not required here.
class PaymentServiceTest {
    
    //The service instance being tested
    private PaymentService paymentService;
    
    //Executes before each individual test method.
    //Initializes a fresh instance to ensure complete test isolation.
    @BeforeEach
    void setUp() {
        paymentService = new PaymentService();
    }
    
    //Test successful payment
    @Test
    void testProcessPayment_Success() {
        //ACT
        //Attempt to process a payment with completely valid dummy data.
        boolean result = paymentService.processPayment(
                "1234567812345678",
                "John Smith",
                "12/27",
                "123");

        //ASSERT
        //Verify that the service accepts valid payment details.
        assertTrue(result);
    }

    //Null input tests
    @Test
    void testProcessPayment_NullCardNumber() {
        //ACT
        //Pass a null value for the card number to verify null-handling logic.
        boolean result = paymentService.processPayment(
                null,
                "John Smith",
                "12/27",
                "123");

        //ASSERT
        assertFalse(result);
    }

    @Test
    void testProcessPayment_NullCardHolderName() {
        //ACT
        boolean result = paymentService.processPayment(
                "1234567812345678",
                null,
                "12/27",
                "123");

        //ASSERT
        assertFalse(result);
    }

    @Test
    void testProcessPayment_NullExpiryDate() {
        //ACT
        boolean result = paymentService.processPayment(
                "1234567812345678",
                "John Smith",
                null,
                "123");

        //ASSERT
        assertFalse(result);
    }

    @Test
    void testProcessPayment_NullCVV() {
        //ACT
        boolean result = paymentService.processPayment(
                "1234567812345678",
                "John Smith",
                "12/27",
                null);

        //ASSERT
        assertFalse(result);
    }

    //Invalid card number tests
    @Test
    void testProcessPayment_CardNumberTooShort() {
        //ACT
        //Provide a card number that is shorter than the required 16 digits.
        boolean result = paymentService.processPayment(
                "12345678",
                "John Smith",
                "12/27",
                "123");

        //ASSERT
        assertFalse(result);
    }

    @Test
    void testProcessPayment_CardNumberTooLong() {
        //ACT
        //Provide a card number that exceeds the required 16 digits.
        boolean result = paymentService.processPayment(
                "123456781234567890",
                "John Smith",
                "12/27",
                "123");

        //ASSERT
        assertFalse(result);
    }

    @Test
    void testProcessPayment_CardNumberContainsLetters() {
        //ACT
        //Provide a 16-character string that illegally includes alphabetical characters.
        boolean result = paymentService.processPayment(
                "12345678ABCD5678",
                "John Smith",
                "12/27",
                "123");

        //ASSERT
        assertFalse(result);
    }

    //Invalid CVV tests
    @Test
    void testProcessPayment_CVVTooShort() {
        //ACT
        //Provide a CVV that is shorter than the standard 3 digits.
        boolean result = paymentService.processPayment(
                "1234567812345678",
                "John Smith",
                "12/27",
                "12");

        //ASSERT
        assertFalse(result);
    }

    @Test
    void testProcessPayment_CVVTooLong() {
        //ACT
        //Provide a CVV that exceeds the standard 3 digits.
        boolean result = paymentService.processPayment(
                "1234567812345678",
                "John Smith",
                "12/27",
                "1234");

        //ASSERT
        assertFalse(result);
    }

    @Test
    void testProcessPayment_CVVContainsLetters() {
        //ACT
        //Provide a 3-character CVV that illegally includes alphabetical characters.
        boolean result = paymentService.processPayment(
                "1234567812345678",
                "John Smith",
                "12/27",
                "1A3");

        //ASSERT
        assertFalse(result);
    }

    //Empty string tests
    @Test
    void testProcessPayment_EmptyCardNumber() {
        //ACT
        //Provide an empty string instead of a valid card number.
        boolean result = paymentService.processPayment(
                "",
                "John Smith",
                "12/27",
                "123");

        //ASSERT
        assertFalse(result);
    }

    @Test
    void testProcessPayment_EmptyCVV() {
        //ACT
        //Provide an empty string instead of a valid CVV.
        boolean result = paymentService.processPayment(
                "1234567812345678",
                "John Smith",
                "12/27",
                "");

        //ASSERT
        assertFalse(result);
    }
}