package ui;

import business.BasicCalculations;
import util.Console;

public class CalculationsApp {
	
	public static void main(String[] args) {
		System.out.println("hello");
		int n1 = Console.getInt("Enter a # to square: ");
		System.out.println("Square: " +BasicCalculations.square(n1));
		
		String s1 = Console.getString("Enter a sentect to count a's from: ");
		int n2 = BasicCalculations.countA(s1);
		System.out.println("# of a's:"+ n2);
		System.out.println("Bye");
	}

}
