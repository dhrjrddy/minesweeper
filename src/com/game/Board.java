package com.game;

public class Board {
    private int[][] mines;
    private char[][] boardgame;
    private int Line, Column;
    private int count=100;
    private Display output;
    
    public Board (){
        mines = new int[10][10];
        boardgame = new char[10][10];
        startMines();
        randomMines();
        fillNeighbours();
        startBoard();
        output= new Display();
        
    }
 /*  
    public boolean win(){
        int count=0;
        for(int line = 0 ; line < 10 ; line++)
            for(int column = 0 ; column < 10 ; column++)
                if(boardgame[line][column]=='_')
                    count++;
        if(count == 10)
            return true;
        else
            return false;                
    }
   */ 
    public int openNeighbors(){
        for(int i=-1 ; i<2 ; i++)
            for(int j=-1 ; j<2 ; j++)
                if((Line+i)!= -1 && (Line+i)!= 10 && (Column+j) != -1 && (Column+j) != 10) 
                	if (mines[Line+i][Column+j] != -1){
                		if (boardgame[Line+i][Column+j]=='_')
                			count--;
                    	boardgame[Line+i][Column+j]=Character.forDigit(mines[Line+i][Column+j], 10);                    	
                	}
        if(count==10){
            output.won();
        }
        return count;
    }
    
    public boolean inputPosition(int line , int column){
    		this.Line=line;
    		this.Column=column;         
            if(mines[Line][Column]== -1){
            	output.lost();
                return true;
            }
            else{
                return false;}
            
    }
    
   
    public void show(){
        output.dataDisplay(boardgame,mines);
    }
    
    public void fillNeighbours(){
        for(int line=0 ; line < 10 ; line++)
            for(int column=0 ; column < 10 ; column++){

                    for(int i=-1 ; i<=1 ; i++)
                        for(int j=-1 ; j<=1 ; j++)
                            if(mines[line][column] != -1 && ((line+i)!= -1 && (line+i)!= 10 && (column+j) != -1 && (column+j) != 10))
                                if(mines[line+i][column+j] == -1)
                                    mines[line][column]++;
                
            }
            
    }
/*    
    public void showMines(){
        for(int i=0 ; i < 10; i++)
            for(int j=0 ; j < 10 ; j++)
                if(mines[i][j] == -1)
                    boardgame[i][j]='*';
        
        show();
    }
   */ 
    public void startBoard(){
        for(int i=0 ; i<mines.length ; i++)
            for(int j=0 ; j<mines.length ; j++)
                boardgame[i][j]= '_';
    }
    
    public void startMines(){
        for(int i=0 ; i<mines.length ; i++)
            for(int j=0 ; j<mines.length ; j++)
                mines[i][j]=0;
    }
    
    public void randomMines(){
        boolean avilable;
        int Line, Column;
        for(int i=0 ; i<10 ; i++){
            
            do{
                Line = (int)(Math.random() * 10) ;
                Column = (int)(Math.random() * 10);
                if(mines[Line][Column] == -1)
                	avilable=true;
                else
                	avilable = false;
            }while(avilable);
            
            mines[Line][Column] = -1;
        }
    }
}