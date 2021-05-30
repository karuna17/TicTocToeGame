import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTocToeGame {

	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();


	public static void printGameBoard(char[][] gameBoard) {
		for(char[] row : gameBoard) {
			for(char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public static void place(char[][] gameBoard, int position, String user) {
		char symbol = ' ';
		if(user.equalsIgnoreCase("player")) {
			symbol = 'X'; 
			playerPositions.add(position);
		}else if(user.equalsIgnoreCase("CPU")) {
			symbol = 'O';
			cpuPositions.add(position);
		}

		switch(position) {	
		case 1: 
			gameBoard[0][0] = symbol;
			break;
		case 2: 
			gameBoard[0][2] = symbol;
			break;
		case 3: 
			gameBoard[0][4] = symbol;
			break;
		case 4: 
			gameBoard[2][0] = symbol;
			break;
		case 5: 
			gameBoard[2][2] = symbol;
			break;
		case 6: 
			gameBoard[2][4] = symbol;
			break;
		case 7: 
			gameBoard[4][0] = symbol;
			break;
		case 8: 
			gameBoard[4][2] = symbol;
			break;
		case 9: 
			gameBoard[4][4] = symbol;
			break;
		default:
			break;
		}
	}

	public static String checkWinner() {

		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List cross1 = Arrays.asList(1, 5, 9);	
		List cross2 = Arrays.asList(7, 5, 3);

		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);

		for(List l : winning) {
			if(playerPositions.containsAll(l)) {
				return "Congratulations player won!!!";
			}else if(cpuPositions.containsAll(l)) {
				return "CPU wins!!";
			}else if (playerPositions.size() + cpuPositions.size() == 9) {
				return "SORRY!!! lets play again..... ";
			}
		}
		return "";
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		char[][] gameBoard= {{' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'}, {' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'}, {' ', '|', ' ', '|', ' '}};
		printGameBoard(gameBoard);

		while(true) {

			System.out.println("Enter your position in (1-9): ");
			int playerPosition = sc.nextInt();
			while(playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition)) {
				System.out.println("Position taken!!! Enter correct postion");
				playerPosition = sc.nextInt();
			}
			place(gameBoard, playerPosition, "Player");	
			String checkWinner = checkWinner();
			if(checkWinner.length() > 0) {
				System.out.println(checkWinner);	
			}

			Random random = new Random();
			int cpuPosition = random.nextInt(9) + 1;
			while(playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)) {
				cpuPosition = random.nextInt(9) + 1;
			}
			place(gameBoard, cpuPosition, "cpu");

			checkWinner = checkWinner();
			if(checkWinner.length() > 0) {
				System.out.println(checkWinner);
			}
			
			printGameBoard(gameBoard);

		}
	}
}
