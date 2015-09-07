package com.game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Minesweeper {

	private Board board;
	private char[][] boardGame;
	private int turn = 0;
	private int blockLeft;
	private int length;
	private int width;
	private int mines;
	private final int MIN = 0;   

	public Minesweeper(int length, int width, int mines) {
		this.length = length;
		this.width = width;
		this.mines = mines;
		board = new Board(length, width, mines);
		boardGame = new char[length][width];
		startBoard();
	}

	private void showBoard() {
		System.out.println("Turn " + turn);
		for (int line = 0; line < length; line++) {
			for (int column = 0; column < width; column++) {
				System.out.print("   " + boardGame[line][column]);
			}
			System.out.println();
		}
	}

	// Initializing the board
	private void startBoard() {
		for (int line = 0; line < length; line++) {
			for (int column = 0; column < width; column++) {
				boardGame[line][column] = '_';
			}
		}
	}

	// Check the user input to make sure no non-existing cell is checked.
	private boolean isValidInput(int line, int column) {
		if (line < MIN || line > (length - 1) || column < MIN
				|| column > (width - 1)) {
			System.out.println("Choose a number between 1 and 10");
			return false;
		} else if ((boardGame[line][column] != '_')) {
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
				System.out.println("Choose a number between 1 and 10");
				continue;
			}
			if (isValidInput(line, column)) {
				turn++;
				isMine = board.hasMine(line, column);
				if (!isMine) {
					boardGame = board.openNeighbors();
					blockLeft = board.noOfBlocks();
				}
			}
		} while (!isMine && blockLeft != mines);
		showResult();
		sc.close();
	}

	private void showResult() {
		if (blockLeft == mines) {
			boardGame = board.showMines();
			System.out.println("Congratulations");
			showBoard();
		} else {
			boardGame = board.showMines();
			System.out.println("Mine! You lost!");
			showBoard();
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
