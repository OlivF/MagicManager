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

}
