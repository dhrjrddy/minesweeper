package com.game;

public class Board {
	private int[][] mines;
	private char[][] boardGame;
	private int line;
	private int column;
	private int count = 100;

	public Board() {
		mines = new int[10][10];
		boardGame = new char[10][10];
		startMines();
		randomMines();
		fillNeighbours();
	}

	public char[][] openNeighbors(char[][] boardGame) {
		this.boardGame = boardGame;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if ((line + i) != -1 && (line + i) != 10 && (column + j) != -1
						&& (column + j) != 10) {
					if (mines[line + i][column + j] != -1) {
						if (boardGame[line + i][column + j] == '_')
							count--;
						boardGame[line + i][column + j] = Character.forDigit(
								mines[line + i][column + j], 10);
					}
				}
			}
		}
		return boardGame;
	}

	public boolean hasMine(int line, int column) {
		this.line = line;
		this.column = column;
		if (mines[line][column] == -1) {
			return true;
		} else {
			return false;
		}

	}

	public int noOfBlocks() {
		return count;
	}

	public void fillNeighbours() {
		for (int line = 0; line < 10; line++)
			for (int column = 0; column < 10; column++) {

				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						if (mines[line][column] != -1
								&& ((line + i) != -1 && (line + i) != 10
										&& (column + j) != -1 && (column + j) != 10)) {
							if (mines[line + i][column + j] == -1) {
								mines[line][column]++;
							}
						}
					}
				}
			}

	}

	public char[][] showMines() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (mines[i][j] == -1) {
					boardGame[i][j] = '*';
				}
			}
		}
		return boardGame;
	}

	public void startMines() {
		for (int i = 0; i < mines.length; i++) {
			for (int j = 0; j < mines.length; j++) {
				mines[i][j] = 0;
			}
		}
	}

	public void randomMines() {
		boolean avilable;
		int line, column;
		for (int i = 0; i < 10; i++) {
			do {
				line = (int) (Math.random() * 10);
				column = (int) (Math.random() * 10);
				if (mines[line][column] == -1) {
					avilable = true;
				} else {
					avilable = false;
				}
			} while (avilable);

			mines[line][column] = -1;
		}
	}
}