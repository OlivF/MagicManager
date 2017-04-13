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
	
}
