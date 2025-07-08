package ch9_2_PrimitiveInterface;

import java.util.List;
import java.util.function.ToDoubleFunction;
public class SalesTxnApp7 {
   public static void main(String[] args) {
       List<SalesTxn> tList = SalesTxn.createTxnList();
       SalesTxn first = tList.get(0);
       ToDoubleFunction<SalesTxn> discountFunction =
           t -> t.getTransactionTotal()
               * t.getDiscountRate();
       System.out.println("\n== Discount ==");
       System.out.println(
           discountFunction.applyAsDouble(first));
       //
       System.out.println("\n== print first ==");
       System.out.println( first.getTransactionTotal() + " : "+first.getDiscountRate());
       System.out.println( first.getTransactionTotal()  * first.getDiscountRate());
      
      
    // Print all transactions
       System.out.println("\n== printSummary ==");
       first.printSummary();
      
       System.out.println("\n== ALL Discount Print ==");
       tList.forEach( t ->  System.out.println(discountFunction.applyAsDouble(t) ));
      
   }//end main
}//end class

