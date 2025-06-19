//package ch2_3_Operators;
//class ShortCircuit2 {
//   int a = 0;
//   int b = 0;
//   public void shortCircuitForOR() {
//       if ((5 > 1) || (a++ > b)) { //Dead Code
//           b++;
//       }
//       System.out.println("|| a = " + a + ", b = " + b);
//       if ((5 > 1) | (a++ > b)) {
//           b++; //
//       }
//       System.out.println("|  a = " + a + ", b = " + b);
//   }
//   public static void main(String[] args) {
//       ShortCircuit2 shortCircuit2 = new ShortCircuit2();
//       shortCircuit2.shortCircuitForOR();
//   }
//}
