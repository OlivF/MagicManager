package com.ofrancois.springmvc.model;

import org.junit.Assert;
import org.junit.Test;

public class testRarity {

	@Test
	public void testNewRarity() {
		Rarity r = new Rarity(1, "Commune");
		Assert.assertEquals( 1, r.getId() );
		Assert.assertEquals( "Commune", r.getName() );
	}
	
	@Test ( expected = RuntimeException.class )
	public void testBadId() {
		new Rarity(-1, "Commune");
	}
	
	@Test ( expected = RuntimeException.class )
	public void testNullName() {
		new Rarity(1, null);
	}
	
	@Test ( expected = RuntimeException.class )
	public void testEmptyName() {
		new Rarity(1, "");
	}
	
	@Test ( expected = RuntimeException.class )
	public void testEmptyName2() {
		new Rarity(1, "   ");
	}

}
