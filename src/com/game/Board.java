package com.game;

public class Board {
	private char[][] mines; // 2-dimensional array for mines
	private char[][] game; // 2-dimensional array for game
	private int line;
	private int column;
	private int length; // Length of the board
	private int width; // Width of the board
	private int minecount; // Number of mines
	private int count; // Number of blocksleft
	private final int MIN = 0;
	private final char MINE = '*';
	private final char NOT_SELECTED = '_';

	public Board(int length, int width, int minecount) {
		this.length = length;
		this.width = width;
		this.count = length * width;
		this.minecount = minecount;
		mines = new char[length][width];
		game = new char[length][width];
		startMines();
		randomMines();
	}

	/*
	 * For a cell check mines in all directions and return the game board with
	 * the neighboring fields containing a mine count.
	 */
	public char[][] openNeighbors() {
		for (int rowNeighbour = -1; rowNeighbour < 2; rowNeighbour++) {
			for (int columnNeighbour = -1; columnNeighbour < 2; columnNeighbour++) {
				int line = this.line - rowNeighbour;
				int column = this.column - columnNeighbour;

				/*
				 * Check the boundaries to make sure no non-existing cells are
				 * checked (eg. column 0-1 = -1).
				 */
				if (line >= MIN && column >= MIN && line <= (length - 1)
						&& column <= (width - 1) && mines[line][column] != MINE
						&& game[line][column] == NOT_SELECTED) {
					int blockNumber = 0;
					if (column >= 1 && mines[line][column - 1] == MINE) { // East
						blockNumber++;
					}
					if (column <= 8 && mines[line][column + 1] == MINE) { // West
						blockNumber++;
					}
					if (line >= 1 && mines[line - 1][column] == MINE) { // North
						blockNumber++;
					}
					if (line <= 8 && mines[line + 1][column] == MINE) { // South
						blockNumber++;
					}
					if (line >= 1 && column >= 1
							&& mines[line - 1][column - 1] == MINE) { // NorthWest
						blockNumber++;
					}
					if (line <= 8 && column <= 8
							&& mines[line + 1][column + 1] == MINE) { // SouthEast
						blockNumber++;
					}
					if (line >= 1 && column <= 8
							&& mines[line - 1][column + 1] == MINE) { // NorthEast
						blockNumber++;
					}
					if (line <= 8 && column >= 1
							&& mines[line + 1][column - 1] == MINE) { // SouthEast
						blockNumber++;
					}

					/* Changing an int to a char. */
					game[line][column] = Character.forDigit(blockNumber, 10);
					count--;
				}
			}
		}
		return game;
	}

	/* Initialize every board space to a _ character. */
	private void startMines() {
		for (int line = 0; line < mines.length; line++) {
			for (int column = 0; column < mines.length; column++) {
				mines[line][column] = NOT_SELECTED;
				game[line][column] = NOT_SELECTED;
			}
		}
	}

	/* Returns the count of number of blocks left to select. */
	public int noOfBlocks() {
		return count;
	}

	/* Returns true if there's a mine at x,y or false if there isn't. */
	public boolean hasMine(int line, int column) {
		this.line = line;
		this.column = column;
		if (mines[line][column] == MINE) {
			return true;
		} else {
			return false;
		}

	}

	/* Returns the board with mines after the game finishes. */
	public char[][] showMines() {
		for (int line = 0; line < length; line++) {
			for (int column = 0; column < width; column++) {
				if (mines[line][column] == MINE) {
					game[line][column] = MINE;
				}
			}
		}
		return game;
	}

	private void randomMines() {
		boolean avilable;
		int line, column;
		for (int mineObject = 0; mineObject < minecount; mineObject++) {
			do {

				/* Generates random numbers between 0 and mWidth - 1. */
				line = (int) (Math.random() * minecount);
				column = (int) (Math.random() * minecount);

				/* Make sure we don't place a mine on top of another. */
				if (mines[line][column] == MINE) {
					avilable = true;
				} else {
					avilable = false;
				}
			} while (avilable);

			mines[line][column] = MINE;
		}
	}
}