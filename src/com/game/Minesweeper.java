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
		for (int line = 0; line < board.getLength(); line++) {
			for (int column = 0; column < board.getWidth(); column++) {
				if (board.getGameBoard()[line][column] == board
						.getNotSelected()
						|| board.getGameBoard()[line][column] == board
								.getMine()) {
					System.out.print("   " + '_');
				} else {
					System.out
							.print("   " + board.getGameBoard()[line][column]);
				}
			}
			System.out.println();
		}
	}

	/* Check the user input to make sure no non-existing cell is checked. */
	private boolean isValidInput(int line, int column) {
		if (line < 0 || line > (board.getLength() - 1) || column < 0
				|| column > (board.getWidth() - 1)) {
			System.out.println("Choose a number between 1 and "
					+ board.getLength());
			return false;
		} else if (board.getGameBoard()[line][column] != board.getNotSelected()
				&& board.getGameBoard()[line][column] != board.getMine()) {
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
			int line = 0;
			int column = 0;
			try {
				System.out.print("row: ");
				line = sc.nextInt() - 1; // As array starts from 0
				System.out.print("column: ");
				column = sc.nextInt() - 1;
			} catch (InputMismatchException e) {
				System.out.println("Choose a number between 1 and "
						+ board.getLength());
				continue;
			}
			if (isValidInput(line, column)) {
				turn++;
				board.setRow(line);
				board.setColumn(column);
				isMine = board.hasMine();
				if (!isMine) {
					board.openNeighbors();
				}
			}
		} while (!isMine && board.getNoOfBlocksLeft() != board.getMineCount());
		gameOverMessage();
		sc.close();
	}

	private void gameOverBoard() {
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

	private void gameOverMessage() {
		if (board.getNoOfBlocksLeft() == board.getMineCount()) {
			gameOverBoard();
			System.out.println("Congratulations");
		} else {
			gameOverBoard();
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
