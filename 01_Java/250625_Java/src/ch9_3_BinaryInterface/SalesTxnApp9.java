package ch9_3_BinaryInterface;

import java.util.List;
import java.util.function.UnaryOperator;
public class SalesTxnApp9 {
   public static void main(String[] args) {
       List<SalesTxn> tList = SalesTxn.createTxnList();
       SalesTxn first = tList.get(0);
       UnaryOperator<String> unaryStr =    s -> s.toUpperCase();
      
       System.out.println("== Upper Buyer");
       System.out.println( unaryStr.apply(first.getBuyer().getName()));
      
       System.out.println("\n== print first ==");
       System.out.println( first.getBuyer().getName());
        // Print all transactions
       System.out.println("\n== printSummary ==");
       first.printSummary();
      
       // Apply all transactions
       System.out.println("\n== Apply all transactions ==");
       tList.forEach(t -> System.out.println(unaryStr.apply(t.getBuyer().getName())));
   }//end main
}//end class

