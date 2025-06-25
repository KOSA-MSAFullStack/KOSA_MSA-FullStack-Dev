package ch9_3_BinaryInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
* Enumeration for US states.
* Each state has a unique name and provides a method to get the state name as a string.
*/
enum State {   
   CA, NY, TX, FL, MA, WA;
   public String getStr() {
       return this.name();
   }
}
/**
* Enumeration for buyer classifications.
* Represents different types of buyer categories like REGULAR, PREMIUM, and WHOLESALE.
*/
enum BuyerClass {   
   REGULAR, PREMIUM, WHOLESALE
}
/**
* Enumeration for tax rates based on US states.
* Each state has an associated sales tax rate.
*/
enum TaxRate {
   CA(0.0725), NY(0.088), TX(0.0625), FL(0.06), MA(0.0625), WA(0.065);
   private final double rate;
   TaxRate(double rate) {       
       this.rate = rate;   
   }
   public double getRate() {       
       return rate;   
   }
}
/**
* Class representing a buyer in a transaction.
* Each buyer has a name and a classification.
*/
class Buyer {
   private String name;
   private BuyerClass buyerClass;
   public Buyer(String name, BuyerClass buyerClass) {
       this.name = name;
       this.buyerClass = buyerClass;
   }
   public String getName() {       
       return name;   
   }
   public BuyerClass getBuyerClass() {       
       return buyerClass;   
   }
   @Override
   public String toString() {
       return "Buyer{name='" + name + "', buyerClass=" + buyerClass + '}';
   }
   /**
    * Returns a predefined map of buyers for testing purposes.
    */
   public static Map<String, Buyer> getBuyerMap() {
       Map<String, Buyer> buyerMap = new HashMap<>();
       buyerMap.put("PriceCo", new Buyer("PriceCo", BuyerClass.WHOLESALE));
       buyerMap.put("Acme Corp", new Buyer("Acme Corp", BuyerClass.PREMIUM));
       buyerMap.put("Global Inc", new Buyer("Global Inc", BuyerClass.REGULAR));
       return buyerMap;
   }
}
/**
* Class representing a sales transaction.
* Includes details like transaction ID, salesperson, buyer, state, and more.
*/
public class SalesTxn {
   private static int idCounter = 0; // Unique ID for each transaction
   private int txnId;
   private String salesPerson;
   private Buyer buyer;
   private State state;
   private LocalDate date;
   private int unitsSold;
   private double unitPrice;
   private double discount;
   private String product;
   private String paymentType;
   /**
    * Builder class to construct SalesTxn objects.
    */
   public static class Builder {
       private int txnId;
       private String salesPerson;
       private Buyer buyer;
       private State state;
       private LocalDate date;
       private int unitsSold;
       private double unitPrice;
       private double discount;
       private String product;
       private String paymentType;
       public Builder txnId(int txnId) {  
           this.txnId = txnId;  
           return this;       
       }
       public Builder salesPerson(String salesPerson) {      
           this.salesPerson = salesPerson;   
           return this;    
       }
       public Builder buyer(Buyer buyer) {    
           this.buyer = buyer;         
           return this;       
       }
       public Builder state(State state) {   
           this.state = state;           
           return this;   
       }
       public Builder date(LocalDate date) {  
           this.date = date;           
           return this;    
       }
       public Builder unitsSold(int unitsSold) {    
           this.unitsSold = unitsSold;           
           return this;    
       }
       public Builder unitPrice(double unitPrice) {    
           this.unitPrice = unitPrice;       
           return this;       
       }
       public Builder discount(double discount) {     
           this.discount = discount;         
           return this;      
       }
       public Builder product(String product) {      
           this.product = product;       
           return this;       
       } 
       public Builder paymentType(String paymentType) {      
           this.paymentType = paymentType;       
           return this;       
       } 
       public SalesTxn build() {    
           return new SalesTxn(this);       
       }
   }
   private SalesTxn(Builder builder) {
       this.txnId = ++idCounter;
       this.salesPerson = builder.salesPerson;
       this.buyer = builder.buyer;
       this.state = builder.state;
       this.date = builder.date;
       this.unitsSold = builder.unitsSold;
       this.unitPrice = builder.unitPrice;
       this.discount = builder.discount;
       this.product = builder.product;
       this.paymentType = builder.paymentType;
   }
   public int getTxnId() {       
       return txnId;   
   }
   public Buyer getBuyer() {       
       return buyer;   
   }
   public State getState() {       
       return state;   
   }
   public double getDiscountRate() {
       return discount;
   }
   public double getTransactionTotal() {
       return unitsSold * unitPrice;
   }
   /**
    * Calculates the total cost of the transaction including tax and discount.
    */
   public double calculateTotal() {
       double subtotal = unitsSold * unitPrice * (1 - discount);
       double tax = subtotal * TaxRate.valueOf(state.name()).getRate();
       return subtotal + tax;
   }
   /**
    * Prints a detailed summary of the transaction.
    */
   public void printSummary() {
       System.out.println("Transaction ID=" + txnId +
               ", SalesPerson=" + salesPerson +
               ", Buyer=" + buyer +
               ", State=" + state +
               ", product=" + product +
               ", paymentType=" + paymentType +
               ", getTransactionTotal=" + getTransactionTotal() +
               ", getDiscountRate=" + getDiscountRate() +
               ", CalculateTotal=" + calculateTotal() );
   }
   /**
    * Generates a list of predefined transactions for demonstration.
    */
   public static List<SalesTxn> createTxnList() {
       List<SalesTxn> transactions = new ArrayList<>();
       transactions.add(new Builder()
               .salesPerson("Alice")
               .buyer(new Buyer("Acme Corp", BuyerClass.PREMIUM))
               .state(State.CA)
               .date(LocalDate.now())
               .product("Widget")
               .paymentType("Card")
               .unitsSold(100)
               .unitPrice(50.0)
               .discount(0.1)
               .build());
       transactions.add(new Builder()
               .salesPerson("Bob")
               .buyer(new Buyer("Global Inc", BuyerClass.REGULAR))
               .state(State.MA)
               .product("Widget")
               .paymentType("Cash")
               .date(LocalDate.now())
               .unitsSold(200)
               .unitPrice(40.0)
               .discount(0.05)
               .build());
       transactions.add(new Builder()
               .salesPerson("Charlie")
               .buyer(new Buyer("XYZ LLC", BuyerClass.WHOLESALE))
               .state(State.TX)
               .product("Widget")
               .paymentType("Cash")
               .date(LocalDate.now())
               .unitsSold(300)
               .unitPrice(30.0)
               .discount(0.15)
               .build());
       return transactions;
   }
}

