//package ch2_3_Operators;
//
//class ShortCircuit {
//   int x = 0;
//   int y = 0;
//  
//   public void shortCircuitForAnd() {
//       if ((5 < 1) && (x++ > y)) {
//           y++; // Dead Code
//       }
//       System.out.println("&&  x = " + x + ", y = " + y);
//       if ((5 < 1) & (x++ > y)) {
//           y++; // Dead Code
//       }
//       System.out.println("&  x = " + x + ", y = " + y);
//   }
//   public static void main(String[] args) {
//       ShortCircuit shortCircuit = new ShortCircuit();
//       shortCircuit.shortCircuitForAnd();
//   }
//}
