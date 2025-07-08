package ch8_2_Java8Stream;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
* Main class to demonstrate filtering and processing SalesTxn objects.
*/
public class SalesTxnApp2 {
	
	  /**
    * Creates a list of sample sales transactions.
    */
   private static List<SalesTxn> createSampleTxns() {
       List<SalesTxn> transactions = new ArrayList<>();
       transactions.add(new SalesTxn.Builder()
               .salesPerson("Alice")
               .buyer(new Buyer("Acme Electronics", BuyerClass.PREMIUM))
               .state(State.CA)
               .date(LocalDate.now())
               .unitsSold(100)
               .unitPrice(50.0)
               .discount(0.1)
               .build());
       transactions.add(new SalesTxn.Builder()
               .salesPerson("Bob")
               .buyer(new Buyer("Global Inc", BuyerClass.REGULAR))
               .state(State.NY)
               .date(LocalDate.now())
               .unitsSold(200)
               .unitPrice(40.0)
               .discount(0.05)
               .build());
       transactions.add(new SalesTxn.Builder()
               .salesPerson("Charlie")
               .buyer(new Buyer("Acme Electronics", BuyerClass.WHOLESALE))
               .state(State.CA)
               .date(LocalDate.now())
               .unitsSold(300)
               .unitPrice(30.0)
               .discount(0.15)
               .build());
       return transactions;
   }
   public static void main(String[] args) {
       // Create a list of sales transactions
       List<SalesTxn> tList = createSampleTxns();
       // Filter and print CA transactions for "Acme Electronics"
       System.out.println("\n== CA Transactions for ACME ==");
       tList.stream()
           .filter(t -> t.getState() == State.CA && t.getBuyer().getName().equals("Acme Electronics"))
           .forEach(SalesTxn::printSummary);
      
       // Filter and print CA transactions for "Acme Electronics" using traditional for-loops
       System.out.println("\n== CA Transactions for ACME == for");
       for (SalesTxn t : tList) {
           if (t.getState() == State.CA && t.getBuyer().getName().equals("Acme Electronics")) {
               t.printSummary();
           }//end if
       }//end for
       // Equivalent using chained filters
       System.out.println("\n== CA Transactions for ACME (Chained Filters) ==");
       tList.stream()
           .filter(t -> t.getState() == State.CA)
           .filter(t -> t.getBuyer().getName().equals("Acme Electronics"))
           .forEach(SalesTxn::printSummary);
        // Equivalent logic using nested if-statements
       System.out.println("\n== CA Transactions for ACME (Nested Checks) == for");
       for (SalesTxn t : tList) {
           if (t.getState() == State.CA) {
               if (t.getBuyer().getName().equals("Acme Electronics")) {
                   t.printSummary();
               }
           }
       }
   }//end main
 }//end class

