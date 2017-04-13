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

}
