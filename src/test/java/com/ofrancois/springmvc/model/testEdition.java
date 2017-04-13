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
	
	@Test ( expected = RuntimeException.class )
	public void testBadId() {
		new Edition(-1, "Odyssée");
	}
	
	@Test ( expected = RuntimeException.class )
	public void testNullName() {
		new Edition(1, null);
	}
	
	@Test ( expected = RuntimeException.class )
	public void testEmptyName() {
		new Edition(1, "");
	}
	
	@Test ( expected = RuntimeException.class )
	public void testEmptyName2() {
		new Edition(1, "   ");
	}

}
