package com.ofrancois.springmvc.model;

import org.junit.Assert;
import org.junit.Test;

public class testEdition {

	@Test
	public void testNewEdition() {
		Edition e = new Edition(1, "Odyssée");
		Assert.assertEquals( 1, e.getId() );
		Assert.assertEquals( "Odyssée", e.getName() );
	}
	
}
