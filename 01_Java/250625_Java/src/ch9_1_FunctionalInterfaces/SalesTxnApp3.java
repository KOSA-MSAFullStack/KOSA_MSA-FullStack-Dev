package ch9_1_FunctionalInterfaces;

import java.util.List;
import java.util.function.Predicate;
 public class SalesTxnApp3 {
	public static void main(String[] args) {
       List<SalesTxn> tList = SalesTxn.createTxnList();
       SalesTxn first = tList.get(0);
       System.out.println(first);
       // Define a Predicate to filter transactions in Massachusetts
       Predicate<SalesTxn> massSales = t -> t.getState().equals(State.MA);
       // Filter and print transactions using a stream
       System.out.println("\n== Sales - Stream ==");
       tList.stream()
               .filter(massSales)
               .forEach(SalesTxn::printSummary);
       // Filter and print transactions using a for-loop
       System.out.println("\n== Sales - Method Call ==");
       for (SalesTxn t : tList) {
           if (massSales.test(t)) {
               t.printSummary();
           }//end if
       }//end for
   }//end main**1132
}//end class

