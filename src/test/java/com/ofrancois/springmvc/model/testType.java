package com.ofrancois.springmvc.model;

import org.junit.Assert;
import org.junit.Test;

public class testType {

	@Test
	public void testNewType() {
		Type t = new Type(1, "Créatures");
		Assert.assertEquals( 1, t.getTypeId() );
		Assert.assertEquals( "Créatures", t.getName() );
	}
	
	@Test ( expected = RuntimeException.class )
	public void testBadId() {
		new Type(-1, "Créature");
	}
	
	@Test ( expected = RuntimeException.class )
	public void testNullName() {
		new Type(1, null);
	}
	
	@Test ( expected = RuntimeException.class )
	public void testEmptyName() {
		new Type(1, "");
	}
	
	@Test ( expected = RuntimeException.class )
	public void testEmptyName2() {
		new Type(1, "   ");
	}
	
}
