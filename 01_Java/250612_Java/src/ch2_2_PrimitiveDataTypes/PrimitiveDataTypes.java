package ch2_2_PrimitiveDataTypes;
public class PrimitiveDataTypes {
	
	public static void main(String[] args) {
		
		//Java SE 7 이진 리터럴
		long creditCardNumber = 1234_5678_9012_3456L;
		long socialSecurityNumber = 999_99_9999L;
		float pi =  3.14_15F;
		long hexBytes = 0xFF_EC_DE_5E; //-1253794
		long hexWords = 0xCAFE_BABE; //-889275714
		long maxLong = 0x7fff_ffff_ffff_ffffL; //9223372036854775807
		byte nybbles = 0b0010_0101; //37
		long bytes = 0b11010010_01101001_10010100_10010010; //-764832622
		
		System.out.println(creditCardNumber);
		System.out.println(socialSecurityNumber);
		System.out.println(pi);
		System.out.println(hexBytes);
		System.out.println(hexWords);
		System.out.println(maxLong);
		System.out.println(nybbles);
		System.out.println(bytes);
		
		
	}//end main
	
}//end class

