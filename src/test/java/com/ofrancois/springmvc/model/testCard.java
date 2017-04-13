package com.ofrancois.springmvc.model;

import org.junit.Assert;
import org.junit.Test;

public class testCard {

	@Test
	public void testNewCard() {
		Card c = new Card(1, "Contrainte", "Duress", "B", 1, 4);
		Assert.assertEquals( 1, c.getId() );
		Assert.assertEquals( "Contrainte", c.getNameFr() );
		Assert.assertEquals( "Duress", c.getNameEn() );
		Assert.assertEquals( "B", c.getManaCost() );		
	}
	
}
