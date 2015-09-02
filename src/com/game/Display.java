package com.game;

public class Display {
     private int[][] mines;
	 private char[][] boardgame;
	 private int turn=0;
	public void showBoard(){
    	System.out.println("Turn "+turn);
        for(int Line = 0; Line < 10 ; Line++){            
            for(int Column = 0 ; Column < 10 ; Column++){
                    System.out.print("   "+ boardgame[Line][Column]);
            }
                
            System.out.println();
        }
    }
	
	public void showMines(){
        for(int i=0 ; i < 10; i++)
            for(int j=0 ; j < 10 ; j++)
                if(mines[i][j] == -1)
                    boardgame[i][j]='*';
        
        showBoard();
    }
	public void won(){
		System.out.println("Congratulations");
        showMines();
	}
	public void lost(){
        showMines();
		System.out.println("Mine! Game over!");
	}
	public void dataDisplay(char[][] boardgame, int[][] mines){
		this.boardgame=boardgame;
		this.mines=mines;
		showBoard();
		turn++;
	}
}
