package com.clearcorrect.wordgame.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.clearcorrect.wordgame.entities.Play;

public class PlayUtilTest {
	
	private List<Play> plays = new ArrayList<>();
	private static final int BOARD_SIZE = 15;
	private Play play = null;
	private boolean valid;
	
	@Before
	public void createPlayHistory() {
		Play play = null;
		play = new Play(0, 5, "example", Direction.right.toString(), null);
		plays.add(play);
		play = new Play(6, 5, "example", Direction.down.toString(), null);
		plays.add(play);
		play = new Play(6, 11, "example", Direction.right.toString(), null);
		plays.add(play);
		play = new Play(6, 5, "example", Direction.right.toString(), null);
		plays.add(play);		
	}
	
	@Test
	public void testNullInputValue() {
		//null value for X
		play = new Play(null, 5, "example", Direction.right.toString(), null);
		valid = PlayUtil.isValid(play, BOARD_SIZE,plays);
		assertFalse(valid);		
	}
	
	@Test
	public void testInvalidDirectionValue() {
		//invalid value for direction
		play = new Play(0, 5, "example", "left", null);
		valid = PlayUtil.isValid(play, BOARD_SIZE,plays);
		assertFalse(valid);			
	}
	
	@Test
	public void testInvalidWordSize() {
		//word does not fit on board on the right side
		play = new Play(12, 0, "example", Direction.right.toString(), null);
		valid = PlayUtil.isValid(play, BOARD_SIZE,plays);
		assertFalse(valid);			

		//word does not fit on board on the bottom side
		play = new Play(0, 12, "example", Direction.down.toString(), null);
		valid = PlayUtil.isValid(play, BOARD_SIZE,plays);
		assertFalse(valid);
	}
	
	@Test
	public void testSpaceSharingNotIntersecting() {
		//word DOES NOT intersect with another word
		play = new Play(0, 7, "example", Direction.down.toString(), null);
		valid = PlayUtil.isValid(play, BOARD_SIZE,plays);
		assertFalse(valid);		
	}

	@Test
	public void testSpaceSharingIntersecting() {
		//word DOES intersect with another word and should be valid
		play = new Play(0, 5, "example", Direction.down.toString(), null);
		valid = PlayUtil.isValid(play, BOARD_SIZE,plays);
		assertTrue(valid);	
	}
	
	@Test
	public void testFirstPlayScenario() {
		play = new Play(0, 5, "example", Direction.down.toString(), null);
		valid = PlayUtil.isValid(play, BOARD_SIZE,new ArrayList<>());
		assertTrue(valid);
	}

}
