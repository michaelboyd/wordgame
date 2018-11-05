package com.clearcorrect.wordgame.utils;

import java.util.ArrayList;
import java.util.List;

import com.clearcorrect.wordgame.entities.Play;

import de.vandermeer.asciitable.AT_Context;
import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

public class BoardUtil {
	
	public static String getBoard(Integer size, List<Play> plays) {

		char[][] grid = getDataGrid(size, plays);

		AsciiTable table = new AsciiTable();
		AT_Context context = table.getContext();
		Double width = size * 4.19;
		context.setWidth(width.intValue());
		List <String> rowLists[] = new List[size];
		AT_Row row = null;

		for (int i = 0; i < size; i++) {
			List<String> rows = new ArrayList<>();
			for (int j = 0; j < size; j++) {
				if (grid[i][j] != 0) {
					char letter = grid[i][j];
					String val = Character.toString(letter);
					rows.add(val);
				} else {
					rows.add("");
				}
			}
			rowLists[i] = rows;
		}

		table.addRule();
		for (int i = 0; i < size; i++) {
			row = table.addRow(rowLists[i]);
			row.setTextAlignment(TextAlignment.CENTER);
			table.addRule();
		}

		String renderedTable = table.render();
		return renderedTable;
	}
	
	public static char[][] getDataGrid(int size, List<Play> plays) {
		
		char[][] grid = new char[size][size];

		for (Play play : plays) {

			int x = play.getX();
			int y = play.getY();
			String word = play.getWord();
			String direction = play.getDirection();

			if (Direction.right.toString().equals(direction)) {
				for (int i = 0; i < word.length(); i++) {
					char letter = word.charAt(i);
					grid[y][x+i] = letter;
				}
			} else {
				for (int i = 0; i < word.length(); i++) {
					char letter = word.charAt(i);
					grid[y + i][x] = letter;
				}

			}
		}
		return grid;
	}
	
	public static String getBoard(Integer size) {
		AsciiTable table = new AsciiTable();

		AT_Context context = table.getContext();

		Double width = size * 4.19;

		context.setWidth(width.intValue());

		List<String> rows = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			rows.add("");
		}
		table.addRule();
		for (int i = 0; i < size; i++) {
			table.addRow(rows);
			table.addRule();
		}
		return table.render();
	}	

}
