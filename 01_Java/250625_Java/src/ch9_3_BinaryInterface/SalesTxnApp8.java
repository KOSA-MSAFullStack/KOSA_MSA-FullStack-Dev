package ch9_3_BinaryInterface;

import java.util.List;
import java.util.function.BiPredicate;
public class SalesTxnApp8 {
   public static void main(String[] args) {
       List<SalesTxn> tList = SalesTxn.createTxnList();
       SalesTxn first = tList.get(0);           
       String testState = "CA";
      
       BiPredicate<SalesTxn,String> stateBiPred =
         (t, s) -> t.getState().getStr().equals(s);
      
       System.out.println("\n== First is CA?");
       System.out.println(
         stateBiPred.test(first, testState));
      
       System.out.println("\n== print first ==");
       System.out.println( first.getState());
       // Print all transactions
       System.out.println("\n== printSummary ==");
       first.printSummary();// Print all transactions
       // Apply all transactions
       System.out.println("\n== Apply all transactions ==");
       tList.forEach(t -> System.out.println( stateBiPred.test(t, testState))  );
   }//end main
}//end class

