package com.game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Minesweeper {

	private Board board;
	private char[][] boardGame;
	private int turn = 0;
	private int blockLeft = 100;

	public Minesweeper() {
		board = new Board();
		boardGame = new char[10][10];
		startBoard();
	}

	public void showBoard() {
		System.out.println("Turn " + turn);
		for (int line = 0; line < 10; line++) {
			for (int column = 0; column < 10; column++) {
				System.out.print("   " + boardGame[line][column]);
			}
			System.out.println();
		}
	}

	public void startBoard() {
		for (int line = 0; line < 10; line++) {
			for (int column = 0; column < 10; column++) {
				boardGame[line][column] = '_';
			}
		}
	}

	public boolean isValidInput(int line, int column) {
		if (line < 0 || line > 9 || column < 0 || column > 9) {
			System.out.println("Choose a number between 1 and 10");
			return false;
		} else if ((boardGame[line][column] != '_')) {
			System.out.println("Field already shown");
			return false;
		} else
			return true;
	}

	public void selectCoordinates() {
		boolean isMine = false;
		int line;
		int column;
		Scanner sc = new Scanner(System.in);
		do {
			showBoard();
			try {
				System.out.print("row: ");
				line = sc.nextInt() - 1;
				System.out.print("column: ");
				column = sc.nextInt() - 1;
				if (isValidInput(line, column)) {
					turn++;
					isMine = board.hasMine(line, column);
					if (!isMine) {
						boardGame = board.openNeighbors(boardGame);
						blockLeft = board.noOfBlocks();
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("Choose a number between 1 and 10");
				sc.next();
			}
		} while (!isMine && blockLeft != 10);
		sc.close();
		showResult();
	}

	public void showResult() {
		if (blockLeft == 10) {
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
		Minesweeper game = new Minesweeper();
		game.selectCoordinates();
	}
}
