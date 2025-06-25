package ch9_1_FunctionalInterfaces;

import java.util.List;
import java.util.function.Function;
/**
* Main class to demonstrate using Function to process SalesTxn objects.
*/
public class SalesTxnApp5 {
	
   public static void main(String[] args) {
       // Create a list of sales transactions
       List<SalesTxn> tList =SalesTxn.createTxnList();
       // Extract the first transaction
       SalesTxn first = tList.get(0);
       // Define a Function to extract buyer name
       Function<SalesTxn, String> buyerFunction = t -> t.getBuyer().getName();
       // Apply the Function to the first transaction
       System.out.println("\n== Function First Buyer ==");
       System.out.println(buyerFunction.apply(first));
      
    // old Way the first transaction
       System.out.println("\n== Old Way First Buyer ==");
       System.out.println(first.getBuyer().getName());
      
    //  Apply the Function  to all transactions
       System.out.println("\n== Function All Transactions ==");
       tList.forEach( t -> System.out.println(buyerFunction.apply(t) ));
       // Print all transactions
       System.out.println("\n== Print All Transactions ==");
       tList.forEach( t -> t.printSummary());
   }
 }

