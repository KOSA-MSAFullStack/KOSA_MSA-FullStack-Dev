package ch9_1_FunctionalInterfaces;

import java.util.List;
import java.util.function.Consumer;
 /**
* Main class to demonstrate using Consumer to process SalesTxn objects.
*/
public class SalesTxnApp4 {
   public static void main(String[] args) {
       // Create a list of sales transactions
       List<SalesTxn> tList = SalesTxn.createTxnList();
       // Define a Consumer to display buyer information
       Consumer<SalesTxn> buyerConsumer = t ->
               System.out.println("Id: " + t.getTxnId()
                       + ", Buyer: " + t.getBuyer().getName());
       // Process all transactions using stream and Consumer
       System.out.println("== Buyers - Lambda ==");
       tList.stream().forEach(buyerConsumer);
       // Process a single transaction using Consumer.accept()
       System.out.println("\n== First Buyer - Method ==");
       if (!tList.isEmpty()) {
           buyerConsumer.accept(tList.get(0)); // Display the first transaction's buyer
       }
   }
 }

