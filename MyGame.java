import java.util.Arrays;
import java.util.Vector;

public class MyGame extends AbstractGame implements Cloneable{

	int[][] board = new int[2][6];
	int humanPoints = 0;
	int computerPoints = 0;
	boolean gameOver;
	
	public MyGame(){
		Arrays.fill(board[0], 4);
		Arrays.fill(board[1], 4);
	}
	
	public void displayStatus(){
		System.out.println(" [0]     [1]     [2]     [3]     [4]     [5]");
		System.out.println("");
		int h=0;
		while (h<2){
			int i =0;
			while (i<6){
				System.out.print("[ "+board[h][i]+" ]   ");
				i++;
			}
			System.out.println();
			h++;
		}
		System.out.println("");
		System.out.println("Computer points: "+computerPoints);
		System.out.println("Human points: "+humanPoints);
		System.out.println();
	}
	
	public MyGame clone(){
		 MyGame answer;

	      try
	      {
	         answer = (MyGame) super.clone( );
	         answer.board = board.clone();
	         int x=0;
	         for(int[] i: board){
	        	 board[x] = i.clone();
	        	 x++;
	         }
	      }
	      catch (Exception e)
	      {
	         throw new RuntimeException
	         ("This class does not implement Cloneable.");
	      }
	      return answer;
	}
	
	public void displayMessage(){
		System.out.println();
	}
	
	protected Vector<String> computeMoves( ){
		Vector<String> moves = new Vector<String>();
		int i = 0;
		while(i<6){
			String move = Integer.toString(i);
			if (isLegal(move))
				moves.add(move);
			i++;
		}
		return moves;
	}
	protected double evaluate( ){
		if (humanPoints>=computerPoints)
			return 10;
		else
			return -10;
	}
	protected boolean isGameOver( ){
		Vector<String> moves = computeMoves();
		if (humanPoints>24 || computerPoints >24 || moves.isEmpty())
			gameOver = true;
		return gameOver;
	}
	protected boolean isLegal(String move){
		int point = Integer.parseInt(move);
		int row;
		if (nextMover()==Player.computer)
			row = 0;
		else
			row = 1;
		if (board[row][point]>0)
			return true;
		else
			return false;
	}
	protected void makeHumMove(String move){
		int column = Integer.parseInt(move);
		int row = 1;
		int value = board[row][column];
		board[row][column] = 0;
		
		while (value>0){
			if (row==1){
				if (column==5){
					row--;
					column--;
				}
				column++;
				board[row][column]++;
				value--;
			}
			else if (row == 0){
				if (column == 0){
					row++;
					column++;
				}
				column--;
				board[row][column]++;
				value--;
			}
		}
		if(row==0){
		if (board[row][column] == 2 || board[row][column] == 3){
			humanPoints+=board[row][column];
			boolean next = true;
			while(next){
				if(row==0&&column<5){
					column++;
					if(board[row][column]==2 || board[row][column]==3)
						addPoints(board[row][column]);
				}
				else if (row==0&&column==5){
					next = false;
				}
				else if (row==1&&column!=0){
					column--;
					if(board[row][column]==2||board[row][column]==3)
						addPoints(board[row][column]);
				}
				else if (row==1&&column==0)
					next = false;
				else
					next = false;
			}
		}
		}
	}
	
	public void makeCompMove(String move){
		int column = Integer.parseInt(move);
		int row = 0;
		int value = board[row][column];
		board[row][column] = 0;

		while (value>0){
			if (row==1){
				if (column==5){
					row--;
					column--;
				}
				column++;
				board[row][column]++;
				value--;
			}
			else if (row == 0){
				if (column == 0){
					row++;
					column++;
				}
				column--;
				board[row][column]++;
				value--;
			}
		}
		if (row == 1){
		if (board[row][column] == 2 || board[row][column] == 3){
			computerPoints+=board[row][column];
			board[row][column] = 0;
			boolean next = true;
			while(next){
				if(row==0&&column<5){
					column++;
					if(board[1][column]==2 || board[1][column]==3)
						addPoints(board[1][column]);
				}
				else if (row==0&&column==5){
					next = false;
				}
				else if (row==1&&column!=0){
					column--;
					if(board[row][column]==2||board[row][column]==3)
						addPoints(board[row][column]);
				}
				else if (row==1&&column==0)
					next = false;
				else
					next = false;
			}
		}
		}
	}
	
	public void makeMove(String move){
		if (nextMover() == Player.computer){
			makeCompMove(move);
			}
		else if (nextMover() == Player.human)
			makeHumMove(move);
		else
			return;
	}
	
	public void addPoints(int points){
		if (nextMover() == Player.computer)
			computerPoints+=points;
		else if (nextMover() == Player.human)
			humanPoints+=points;
		else 
			return;
	}
}
