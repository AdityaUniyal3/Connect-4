//My Evaluate class
public class Evaluate{
//my instance variables
private int size;
private int tilesToWin;
private int maxLevels;
private char [] [] gameBoard;
    
    //My constructor for this class 
	public Evaluate (int size, int tilesToWin, int maxLevels) {
		this.size = size;
		this.tilesToWin = tilesToWin;
		this.maxLevels = maxLevels;
		
        gameBoard = new char[size][size];
		
		//sets all elements of gameboard to 'e'
		for(int i = 0; i < gameBoard.length; i++) {
			for(int j = 0; j < gameBoard.length; j++) {
				gameBoard[i][j] = 'e';
			}
		}
	}
	
	//createDictionry method creates an empty dictionary
	public Dictionary createDictionary() {
		return new Dictionary(8719);
	}
	
	//repeatedState method returns the Record object that contains it; otherwise the method returns the value null
	public Record repeatedState(Dictionary dict) {
		
		String myBoard = "";
		for (int j = 0; j<gameBoard[0].length; j++){
		     for (int i = 0; i<gameBoard.length; i++){
		    	 myBoard += gameBoard [j][i];
		     }
		}
		Record myRecord = dict.get(myBoard);
		if(myRecord != null) {
			return myRecord;
		}
		return null;
	}
	
	//insertState creates a object of the class Record storing this string, score, and level
	public void insertState(Dictionary dict, int score, int level) {
		String myBoard = "";
		for (int j = 0; j<gameBoard[0].length; j++){
		     for (int i = 0; i<gameBoard.length; i++){
		    	 myBoard += gameBoard [j][i];
		     }
		}
		Record myRecord = dict.get(myBoard);
		if(dict.get(myBoard) == null) {
			dict.put(new Record (myBoard,score,level));
		}
	}
	
	//storePlay method stores a given symbol into gameBoard at a specified row and column
	public void storePlay(int row, int col, char symbol) {
		gameBoard [row] [col] = symbol;
	}
	
	//squareIsEmpty returns true if gameBoard at a given row and column is ’e’ else false
	public boolean squareIsEmpty (int row, int col) {
		if(gameBoard [row] [col] == 'e') {
			return true;
		}
		return false;
	}
	
	//tileOfComputer returns true if gameBoad at a given row and column is 'c' else false
	public boolean tileOfComputer (int row, int col) {
		if(gameBoard [row] [col] == 'c') {
			return true;
		}
		return false;	
	}
	
	//tileOfHuman returns true if gameBoad at a given row and column is 'h' else false
	public boolean tileOfHuman (int row, int col) {
		if(gameBoard [row] [col] == 'h') {
			return true;
		}
		return false;
	}
	
	//wins method checks if there are the required number of adjacent tiles of type symbol in the same row, 
	//column, or diagonal of gameBoard, if so returns true else false
	public boolean wins (char symbol) {
		int count;
		int limit = size - tilesToWin;
		//check column 
    	for(int i = 0; i < size; i++){
    		count = 0;
    		for (int x = 0; x <size; x++){
    			if(gameBoard[x][i] == symbol) {
    				count++;
    			}
    			else {
    				count = 0;
    			}
    			if (count >= this.tilesToWin) {
    			return true;
    		    }
    		
    		 }
    	}
    	
    	//check rows
    	for(int j = 0; j < size; j++){
    		count = 0;
        	for (int y = 0; y < size; y++){
        		if(gameBoard[j][y] == symbol) {
        			count++;
        		}
        		else {
        			count = 0;   
        		}
        		if (count >= tilesToWin) {
    			return true;
        	    }	
        	
    		 }
    	}
    	
    	//check diagonal
    	for(int k = 0; k <= limit; k++) {
    		for (int f = 0; f <= limit; f++) {   
    			count = 0;
    			for (int i = 0; i < tilesToWin; i++) {
    				if (gameBoard[i+k][i+f] == symbol)
						count++;
					else
						count = 0;
    		    }
    			if (count >= tilesToWin) {
    			return true;
    	        }
    	    }
    	}
    	
    	//check anti diagonal 
    	for(int k = 0; k <= limit; k++) {
    		for (int f = 0; f <= limit; f++) {   
    			count = 0;
    			for (int i = 0; i < tilesToWin; i++) {
    				if (gameBoard[i+k][tilesToWin-i-1+f] == symbol)
						count++;
					else
						count = 0;
    		    }
    			if (count >= tilesToWin) {
    			return true;
    	        }
    	    }
    	}

     return false;
	}
	
	//isDraw method checks the amount of empty tiles in gameBoard if none returns true else false
	public boolean isDraw() {
		int count = 0;
		for (int j = 0; j<gameBoard[0].length; j++){
		     for (int i = 0; i<gameBoard.length; i++){
		    	 if(gameBoard [j][i] == 'e') {
		    		 count++;
		    	 }
		     }
		}
		if(count == 0) 
			return true;
		else 
			return false;
	}
	
	//evalBord method checks who won, computer or human, returns 3 for computer, 0 for human, 2 for draw and 1 for undecided
	public int evalBoard() {
	//3, if the computer has won, i.e. there are the required number of adjacent ’c’s in the same row, column, or diagonal of gameBoard.
	if(wins('c') == true) {
		return 3;
	}
	
    //0, if the human player has won, i.e. there are the required number of adjacent ’h’s in the same row, column, or diagonal of gameBoard.
	if(wins('h') == true) {
		return 0;
	}
	
    //2, if the game is a draw.
	if(isDraw() == true) {
		return 2;
	}
	
    //1, if the game is still undecided, i.e. no player has won and the game is not a draw.
	return 1;
	}
}
