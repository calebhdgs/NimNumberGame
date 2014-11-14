import java.util.Scanner;


public class Play {
	public static void main(String[] args){
		System.out.println("Please input a number between 1 and 5 to set the difficulty.");
		
		Scanner scan = new Scanner(System.in);
		String word = scan.next();
		int depth = Integer.parseInt(word);
		AbstractGame.repeatPlay("MyGame", depth);
	}
}
