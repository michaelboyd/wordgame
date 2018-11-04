package com.clearcorrect.wordgame.utils;

import java.util.List;

import com.clearcorrect.wordgame.entities.Play;

public class PlayUtil {
	
	public static boolean isValid(Play play, int boardSize, List<Play> plays) {
		
		boolean valid = true;
		
		//make sure we have required values to play
		if (play.getX() == null || play.getY() == null || play.getWord() == null || play.getDirection() == null) {
			return false;
		}		
		
		//check the value being used for direction
		String direction = play.getDirection().toLowerCase();
		if(!(Direction.right.toString().equals(direction) 
				|| Direction.down.toString().equals(direction)))
		{
			return false;
		}
		
		//make sure the word fits on the board
		int x = play.getX();
		int y = play.getY();
		int wordSize = play.getWord().length();
		if( (x+wordSize > boardSize) || (y+wordSize > boardSize)) {
			return false;
		}
		
		if(plays.size() > 0) {
			//make sure word is sharing space with other word
			if(!isSharingLetterSpace(play, boardSize, plays)) {
				return false;
			}
		}
		
		return valid;
	}
	
	private static boolean isSharingLetterSpace(Play newPlay, int size, List<Play> plays) {
		
		boolean sharing = false;

		char[][] grid = BoardUtil.getDataGrid(size, plays);

		int x = newPlay.getX();
		int y = newPlay.getY();
		String word = newPlay.getWord();
		String direction = newPlay.getDirection();

		if (Direction.right.toString().equals(direction)) {
			for (int i = 0; i < word.length(); i++) {
				char letter = word.charAt(i);
				char currentLetter = grid[y][x + i];
				if(currentLetter != 0) { //not empty
					if(letter == currentLetter) {
						grid[y][x + i] = letter;
						sharing = true;
					}
					else {
						return false;
					}
				}
			}
		} else {
			for (int i = 0; i < word.length(); i++) {
				char letter = word.charAt(i);
				char currentLetter = grid[y + i][x];
				if(currentLetter != 0) { //not empty
					if(letter == currentLetter) {
						grid[y + i][x] = letter;
						sharing = true;
					}
					else {
						return false;
					}
				}				
			}
		}

		return sharing;

	}

}
