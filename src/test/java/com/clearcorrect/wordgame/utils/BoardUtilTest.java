package com.clearcorrect.wordgame.utils;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.clearcorrect.wordgame.entities.Play;

public class BoardUtilTest {
	
	@Test
	public void testGetBoard() {
		
		String board = BoardUtil.getBoard(10);
		
		assertNotNull(board);
		
		System.out.println(board);
		
	}	
	
	@Test
	public void testPopulateBoard() {
		
		int boardSize = 14;
		
		List <Play> plays = new ArrayList<>();
		Play play = null;
		play = new Play(0, 5, "example", Direction.right.toString(), null);
		plays.add(play);
		play = new Play(6, 5, "example", Direction.down.toString(), null);
		plays.add(play);
		play = new Play(6, 11, "example", Direction.right.toString(), null);
		plays.add(play);
		play = new Play(6, 5, "example", Direction.right.toString(), null);
		plays.add(play);

		play = new Play(0, 5, "example", Direction.down.toString(), null);
		plays.add(play);		
		
		
		String board = BoardUtil.getBoard(boardSize, plays);
		
		assertNotNull(board);
		
		System.out.println(board);
		
	}

}
