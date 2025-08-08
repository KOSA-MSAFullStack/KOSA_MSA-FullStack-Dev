package ch5_3_NestedClasses;

public class BankEMICalculator {
   // Customer details
   private String customerName;
   private String accountNo;
  
   // Loan details
   private double loanAmount;
   private double monthlyPayment;
  
   // Helper object for EMI calculation
   private EMICalculatorHelper helper = new EMICalculatorHelper();
   // Constructor to initialize customer and loan details
   public BankEMICalculator(String customerName, String accountNo, double loanAmount) {
       this.customerName = customerName;
       this.accountNo = accountNo;
       this.loanAmount = loanAmount;
       this.monthlyPayment = helper.calcMonthlyPayment(loanAmount);

       System.out.println(loanAmount);
   }
   // Getter for monthly payment
   public double getMonthlyPayment() {
       return monthlyPayment;
   }
   // Getter for customer name
   public String getCustomerName() {
       return customerName;
   }
   // Getter for account number
   public String getAccountNo() {
       return accountNo;
   }
   // Private inner helper class for EMI calculation
   private class EMICalculatorHelper {
       int loanTerm = 60; // Loan term in months
       double interestRate = 0.9; // Annual interest rate
       double interestPerMonth = interestRate / loanTerm; // Monthly interest rate
       // Method to calculate the monthly EMI payment
       protected double calcMonthlyPayment(double loanAmount) {
           double EMI = (loanAmount * interestPerMonth) / ((1.0) - ((1.0) /
               Math.pow(1.0 + interestPerMonth, loanTerm)));
           return Math.round(EMI);
       }
   }
   // Main method to run the program
   public static void main(String[] args) {
       // Creating a BankEMICalculator object with sample data
       BankEMICalculator customer = new BankEMICalculator("John Doe", "123456789", 50000);
      
       // Display customer details and EMI information
       System.out.println("Customer Name: " + customer.getCustomerName());
       System.out.println("Account Number: " + customer.getAccountNo());
       System.out.println("Monthly EMI Payment: $" + customer.getMonthlyPayment());

       System.out.println(customer.loanAmount);
   }
}

