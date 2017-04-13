package com.ofrancois.springmvc.model;

import org.junit.Assert;
import org.junit.Test;

public class testDeck {

	@Test
	public void testNewDeck() {
		Deck d = new Deck(1, "Gobelin", "R");
		Assert.assertEquals( 1, d.getId() );
		Assert.assertEquals( "Gobelin", d.getName() );
		Assert.assertEquals( "R", d.getColor() );
	}
	
	@Test ( expected = RuntimeException.class )
	public void testBadId() {
		new Deck(-1, "Odyssée", "R");
	}
	
	@Test ( expected = RuntimeException.class )
	public void testNullName() {
		new Deck(1, null, "R");
	}
	
	@Test ( expected = RuntimeException.class )
	public void testEmptyName() {
		new Deck(1, "" ,"R");
	}
	
	@Test ( expected = RuntimeException.class )
	public void testEmptyName2() {
		new Deck(1, "   ", "R");
	}

	@Test ( expected = RuntimeException.class )
	public void testNullColor() {
		new Deck(1, "Gobelin", null);
	}
	
	@Test ( expected = RuntimeException.class )
	public void testEmptyColor() {
		new Deck(1, "Gobelin", "");
	}
	
	@Test ( expected = RuntimeException.class )
	public void testEmptyColor2() {
		new Deck(1, "Gobelin", "   ");
	}
}
