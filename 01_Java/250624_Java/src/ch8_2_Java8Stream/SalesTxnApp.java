package ch8_2_Java8Stream;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
/**
* Main class to demonstrate filtering and processing SalesTxn objects.
*/
public class SalesTxnApp {
	 /**
    * Creates a list of sample sales transactions.
    */
   private static List<SalesTxn> createSampleTxns() {
       List<SalesTxn> transactions = new ArrayList<>();
       transactions.add(new SalesTxn.Builder()
               .salesPerson("Alice")
               .buyer(new Buyer("Acme Corp", BuyerClass.PREMIUM))
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
               .buyer(new Buyer("XYZ LLC", BuyerClass.WHOLESALE))
               .state(State.CA)
               .date(LocalDate.now())
               .unitsSold(300)
               .unitPrice(30.0)
               .discount(0.15)
               .build());
       return transactions;
   }//end createSampleTxns
  
   public static void main(String[] args) {
       // Create a list of sales transactions
       List<SalesTxn> tList = createSampleTxns();
       // Print all transactions
       System.out.println("\n== All Transactions 1==");
       tList.forEach(SalesTxn::printSummary);
      
    // Print all transactions
       System.out.println("\n== All Transactions 2==");
       tList.forEach( t -> t.printSummary());
      
       // Filter and print CA transactions using lambda
       System.out.println("\n== CA Transactions Lambda ==");
       tList.stream()
               .filter(t -> t.getState() == State.CA)
               .forEach(SalesTxn::printSummary);
       // Filter and print CA transactions using a for-loop
       System.out.println("\n== CA Transactions For-Loop ==");
       for (SalesTxn t : tList) {
           if (t.getState() == State.CA) {
               t.printSummary();
           }//end if
       }// end for
   }//end main
 
}//end class

