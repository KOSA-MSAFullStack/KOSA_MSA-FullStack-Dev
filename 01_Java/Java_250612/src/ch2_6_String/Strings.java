package ch2_6_String;
public class Strings {
	public static void main(String args[]) {
		char letter = 'a';
		String string1 = "Hello";
		String string2 = "World";
		String string3 = "";
		String dontDoThis = new String("Bad Practice");
		string3 = string1 + string2; // Concatenate strings
		System.out.println("Output: " + string3 + " " + letter);
	}
}

