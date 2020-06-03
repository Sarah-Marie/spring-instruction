package business;

public class BasicCalculations {
	
	public static int square(int number) {
		return number * number;
		
	}
	
	public static int countA(String str) {
		int count =0;
		for (int i = 0; i < str.length(); i++) {
			if (str.toLowerCase().charAt(i)=='a') {
				count++;
			}
		}
		return count;
	}

}
