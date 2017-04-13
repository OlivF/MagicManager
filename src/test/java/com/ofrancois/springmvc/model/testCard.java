package com.ofrancois.springmvc.model;

import org.junit.Test;

public class testCard {

	@Test
	public void testNewCard() {
		new Card(1, "Contrainte", "Duress", "B", 1, 4);
	}
	
	@Test ( expected = RuntimeException.class )
	public void testBadId() {
		new Card(-1, "Contrainte", "Duress", "B", 1, 4);
	}

	@Test ( expected = RuntimeException.class )
	public void testBadNameFr() {
		new Card(-1, "", "Duress", "B", 1, 4);
	}
	
	@Test ( expected = RuntimeException.class )
	public void testBadNameFr2() {
		new Card(-1, "  ", "Duress", "B", 1, 4);
	}
	
	@Test ( expected = RuntimeException.class )
	public void testNullNameFr() {
		new Card(1, null, "Duress", "B", 1, 4);
	}
	
	@Test ( expected = RuntimeException.class )
	public void testBadNameEn() {
		new Card(1, "Contrainte", "", "B", 1, 4);
	}
	
	@Test ( expected = RuntimeException.class )
	public void testBadNameEn2() {
		new Card(1, "Contrainte", "  ", "B", 1, 4);
	}
	
	@Test ( expected = RuntimeException.class )
	public void testNullNameEn() {
		new Card(1, "Contrainte", null, "B", 1, 4);
	}
	
	@Test ( expected = RuntimeException.class )
	public void testBadPrice() {
		new Card(1, "Contrainte", "Duress", "B", 0, 4);
	}
	
	@Test ( expected = RuntimeException.class )
	public void testBadQuantity() {
		new Card(1, "Contrainte", "Duress", "B", 0, -4);
	}
	
}
