package ch9_1_FunctionalInterfaces;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;
/**
* Main class to demonstrate using Supplier to create and add SalesTxn objects.
*/
public class SalesTxnApp6 {
   public static void main(String[] args) {
       // Create a list of transactions
       List<SalesTxn> tList = SalesTxn.createTxnList();
       // Define a Supplier to create a new transaction
       Supplier<SalesTxn> txnSupplier = () -> new SalesTxn.Builder()
               .txnId(101)
               .salesPerson("John Adams")
               .buyer(Buyer.getBuyerMap().get("PriceCo"))
               .product("Widget")
               .paymentType("Cash")
               .unitPrice(20.0)
               .discount(0.05)
               .date(LocalDate.now())
               .unitsSold(200)
               .state(State.CA)
               .build();
       // Add the new transaction to the list
       tList.add(txnSupplier.get());
       // Print all transactions
       System.out.println("\n== TList ==");
       tList.stream().forEach(SalesTxn::printSummary);
   }
}

