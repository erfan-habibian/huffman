package huff;

import java.util.Scanner;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println("enter a sentence:");
		String inputSt = input.nextLine();
		Huffman h = new Huffman(inputSt);
		h.initialize();	
	}

}
