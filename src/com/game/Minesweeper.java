package com.game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Minesweeper {

	private Board board;
	private int turn = 0;

	public Minesweeper(int length, int width, int mines) {
		board = new Board(length, width, mines);
	}

	private void showBoard() {
		System.out.println("Turn " + turn);
		for (int row = 0; row < board.getLength(); row++) {
			for (int column = 0; column < board.getWidth(); column++) {
				if (board.getGameBoard()[row][column] == board.getNotSelected()
						|| board.getGameBoard()[row][column] == board.getMine()) {
					System.out.print("   " + '_');
				} else {
					System.out.print("   " + board.getGameBoard()[row][column]);
				}
			}
			System.out.println();
		}
	}

	/* Check the user input to make sure no non-existing cell is checked. */
	private boolean isValidInput(int row, int column) {
		if (row < 0 || row > (board.getLength() - 1) || column < 0
				|| column > (board.getWidth() - 1)) {
			System.out.println("Choose a number between 1 and "
					+ board.getLength());
			return false;
		} else if (board.getGameBoard()[row][column] != board.getNotSelected()
				&& board.getGameBoard()[row][column] != board.getMine()) {
			System.out.println("Field already shown");
			return false;
		} else
			return true;
	}

	private void startGame() {
		Scanner sc = new Scanner(System.in);
		boolean isMine = false;
		do {
			showBoard();
			int row = 0;
			int column = 0;
			try {
				System.out.print("row: ");
				row = sc.nextInt() - 1; // As array starts from 0
				System.out.print("column: ");
				column = sc.nextInt() - 1;
			} catch (InputMismatchException e) {
				System.out.println("Choose a number between 1 and "
						+ board.getLength());
				continue;
			}
			if (isValidInput(row, column)) {
				turn++;
				board.setRow(row);
				board.setColumn(column);
				isMine = board.hasMine();
				if (!isMine) {
					board.openNeighbors();
				}
			}
		} while (!isMine && board.getNoOfBlocksLeft() != board.getMineCount());
		gameOverMessageDisplay();
		sc.close();
	}

	private void gameOverBoardDisplay() {
		System.out.println("Turn " + turn);
		for (int line = 0; line < board.getLength(); line++) {
			for (int column = 0; column < board.getWidth(); column++) {
				if (board.getGameBoard()[line][column] == board
						.getNotSelected()) {
					System.out.print("   " + '_');
				} else if (board.getGameBoard()[line][column] == board
						.getMine()) {
					System.out.print("   " + '*');
				} else {
					System.out
							.print("   " + board.getGameBoard()[line][column]);
				}
			}
			System.out.println();
		}
	}

	private void gameOverMessageDisplay() {
		if (board.getNoOfBlocksLeft() == board.getMineCount()) {
			gameOverBoardDisplay();
			System.out.println("Congratulations");
		} else {
			gameOverBoardDisplay();
			System.out.println("Mine! You lost!");
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int length = 0;
		int width = 0;
		int mines = 0;
		try {
			System.out.print("Board Length: ");
			length = sc.nextInt();
			System.out.print("Board Width: ");
			width = sc.nextInt();
			System.out.print("No of Mines: ");
			mines = sc.nextInt();
			Minesweeper game = new Minesweeper(length, width, mines);
			game.startGame();
		} catch (InputMismatchException e) {
			System.out.println("Choose a valid number");
		}

		sc.close();
	}
}
