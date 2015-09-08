package com.game;

public class Board {
	public int[][] gameBoard; // 2-dimensional array for game
	private int row;
	private int column;
	private int length; // Length of the board
	private int width; // Width of the board
	private int mineCount; // Number of mines
	public int blocksLeft; // Number of blocks left
	private final int MINE = -1;
	private final int NOT_SELECTED = 9;

	public Board(int length, int width, int mineCount) {
		this.length = length;
		this.width = width;
		this.blocksLeft = length * width;
		this.mineCount = mineCount;
		gameBoard = new int[length][width];
		initGameBoard();
		generateMines();
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return length;
	}

	public int getMineCount() {
		return mineCount;
	}

	public int getMine() {
		return MINE;
	}

	public int getNotSelected() {
		return NOT_SELECTED;
	}

	/*
	 * For a cell, check mines in all directions and update the cell and return
	 * the game board with the selected input neighboring fields updated with
	 * the mine count.
	 */
	public void openNeighbors() {
		for (int rowNeighbour = -1; rowNeighbour < 2; rowNeighbour++) {
			for (int columnNeighbour = -1; columnNeighbour < 2; columnNeighbour++) {
				int row = this.row - rowNeighbour;
				int column = this.column - columnNeighbour;

				/*
				 * Check the boundaries to make sure no non-existing cells are
				 * checked (eg. column 0-1 = -1).
				 */
				if (row >= 0 && column >= 0 && row <= (length - 1)
						&& column <= (width - 1)
						&& gameBoard[row][column] != MINE
						&& gameBoard[row][column] == NOT_SELECTED) {
					int cellMineCount = 0;
					if (column >= 1 && gameBoard[row][column - 1] == MINE) { // East
						cellMineCount++;
					}
					if (column <= 8 && gameBoard[row][column + 1] == MINE) { // West
						cellMineCount++;
					}
					if (row >= 1 && gameBoard[row - 1][column] == MINE) { // North
						cellMineCount++;
					}
					if (row <= 8 && gameBoard[row + 1][column] == MINE) { // South
						cellMineCount++;
					}
					if (row >= 1 && column >= 1
							&& gameBoard[row - 1][column - 1] == MINE) { // NorthWest
						cellMineCount++;
					}
					if (row <= 8 && column <= 8
							&& gameBoard[row + 1][column + 1] == MINE) { // SouthEast
						cellMineCount++;
					}
					if (row >= 1 && column <= 8
							&& gameBoard[row - 1][column + 1] == MINE) { // NorthEast
						cellMineCount++;
					}
					if (row <= 8 && column >= 1
							&& gameBoard[row + 1][column - 1] == MINE) { // SouthEast
						cellMineCount++;
					}
					gameBoard[row][column] = cellMineCount;
					blocksLeft--;
				}
			}
		}
	}

	/* Initialize every board space to a _ character. */
	private void initGameBoard() {
		for (int line = 0; line < gameBoard.length; line++) {
			for (int column = 0; column < gameBoard.length; column++) {
				gameBoard[line][column] = NOT_SELECTED;
			}
		}
	}

	/* Returns true if there's a mine at x,y or false if there isn't. */
	public boolean hasMine() {
		if (gameBoard[row][column] == MINE) {
			return true;
		} else {
			return false;
		}

	}

	public int getNoOfBlocksLeft() {
		return blocksLeft;
	}

	public int[][] getGameBoard() {
		return gameBoard;
	}

	private void generateMines() {
		boolean avilable;
		int line, column;
		for (int mineObject = 0; mineObject < mineCount; mineObject++) {
			do {

				/* Generates random numbers between 0 and mWidth - 1. */
				line = (int) (Math.random() * length);
				column = (int) (Math.random() * width);

				/* Make sure we don't place a mine on top of another. */
				if (gameBoard[line][column] == MINE) {
					avilable = true;
				} else {
					avilable = false;
				}
			} while (avilable);

			gameBoard[line][column] = MINE;
		}
	}
}