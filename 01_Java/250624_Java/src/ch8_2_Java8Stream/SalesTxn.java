package ch8_2_Java8Stream;

import java.time.LocalDate;
enum BuyerClass {    REGULAR, PREMIUM, WHOLESALE}
enum State {    CA, NY, TX, FL, WA}
enum TaxRate {
   CA(0.0725), NY(0.088), TX(0.0625), FL(0.06), WA(0.065);
   private final double rate;
   TaxRate(double rate) {        this.rate = rate;    }
   public double getRate() {        return rate;    }
}
class Buyer {
   private String name;
   private BuyerClass buyerClass;
   public Buyer(String name, BuyerClass buyerClass) {
       this.name = name;
       this.buyerClass = buyerClass;
   }
   public String getName() {        return name;    }
   public BuyerClass getBuyerClass() {        return buyerClass;    }
   @Override
   public String toString() {
       return "Buyer{" +
               "name='" + name + '\'' +
               ", buyerClass=" + buyerClass +
               '}';
   }	
}
/**
* Represents a sales transaction with details like seller, buyer, state, and more.
*/
class SalesTxn {
   private String salesPerson;    private Buyer buyer;
   private State state;    private LocalDate date;
   private int unitsSold;    private double unitPrice;
   private double discount;
   private SalesTxn(Builder builder) {
       this.salesPerson = builder.salesPerson;
       this.buyer = builder.buyer;
       this.state = builder.state;
       this.date = builder.date;
       this.unitsSold = builder.unitsSold;
       this.unitPrice = builder.unitPrice;
       this.discount = builder.discount;
   }
   public String getSalesPerson() {        return salesPerson;    }
   public Buyer getBuyer() {        return buyer;    }
   public State getState() {        return state;    }
   public LocalDate getDate() {        return date;    }
   public int getUnitsSold() {        return unitsSold;    }
   public double getUnitPrice() {        return unitPrice;    }
   public double getDiscount() {        return discount;    }
   public double calculateTotal() {
       double subtotal = unitsSold * unitPrice * (1 - discount);
       double tax = subtotal * TaxRate.valueOf(state.name()).getRate();
       return subtotal + tax;
   }
   public void printSummary() {
       System.out.println("Transaction: SalesPerson=" + salesPerson +
               ", Buyer=" + buyer +
               ", State=" + state +
               ", Total=" + calculateTotal());
   }
   /**
    * Builder class for constructing SalesTxn objects.
    */
   public static class Builder {
       private String salesPerson;        private Buyer buyer;
       private State state;        private LocalDate date;
       private int unitsSold;        private double unitPrice;
       private double discount;
       public Builder salesPerson(String salesPerson) {
           this.salesPerson = salesPerson;            return this;
       }
       public Builder buyer(Buyer buyer) {
           this.buyer = buyer;            return this;
       }
       public Builder state(State state) {
           this.state = state;            return this;
       }
       public Builder date(LocalDate date) {
           this.date = date;            return this;
       }
       public Builder unitsSold(int unitsSold) {
           this.unitsSold = unitsSold;            return this;
       }
       public Builder unitPrice(double unitPrice) {
           this.unitPrice = unitPrice;            return this;
       }
       public Builder discount(double discount) {
           this.discount = discount;            return this;
       }
       public SalesTxn build() {
           return new SalesTxn(this);
       }
   }
}

