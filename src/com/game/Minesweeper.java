package com.game;

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

	public void selectCoordinates() {
		boolean isMine = false;
		int line;
		int column;
		Scanner sc = new Scanner(System.in);
		do {
			showBoard();
			turn++;
			System.out.print("row: ");
			line = sc.nextInt();
			System.out.print("column: ");
			column = sc.nextInt();
			isMine = board.hasMine(line - 1, column - 1);
			if (!isMine) {
				boardGame = board.openNeighbors(boardGame);
				blockLeft = board.noOfBlocks();
			}
		} while (!isMine && blockLeft != 10);
		sc.close();
		showResult();
	}

	public void showResult() {
		if (blockLeft == 10) {
			System.out.println("Congratulations");
			boardGame = board.showMines();
			showBoard();
		} else {
			System.out.println("Mine! You lost!");
			boardGame = board.showMines();
			showBoard();
		}
	}

	public static void main(String[] args) {
		Minesweeper game = new Minesweeper();
		game.selectCoordinates();
	}
}
