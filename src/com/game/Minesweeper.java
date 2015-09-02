package com.game;

import java.util.Scanner;
public class Minesweeper {
	
	private Board board;
    
    public Minesweeper(){
        board = new Board();
        play(board);
    }
    
    public void play(Board board){
    	boolean inputmine = false;
    	int Line, Column;
    	int blockleft=100;
        Scanner input = new Scanner(System.in);
        do{
            board.show();
            System.out.print("row: "); 
            Line = input.nextInt();
            System.out.print("column: "); 
            Column = input.nextInt();
            inputmine = board.inputPosition(Line , Column);            
            if(!inputmine){
            	blockleft=board.openNeighbors();
            }
            
        }while(!inputmine && blockleft!=10);
        input.close();
    }
	public static void main(String[] args) {
		Minesweeper game = new Minesweeper();
	}
}
