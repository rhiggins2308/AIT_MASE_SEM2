package com.ait.retailers;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.internal.verification.Times;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;


public class BiggestBazarRetailTest {
	private Inventory inv;
	private PublicAddressSystem pas;
	private BiggestBazarRetail bbr;
	private ArrayList<Item> items;
	
	@BeforeEach
	public void setUp() {
		inv = mock(Inventory.class);
		pas = mock(PublicAddressSystem.class);
		bbr = new BiggestBazarRetail(inv, pas);
		items = new ArrayList<>();
	}
	
	@Test
	public void testDiscountAppliedSingleItem() {
		Item item = new Item("TestItemBarCode", "Test Item", 100.0d, 0.0d);
		items.add(item);

		when(inv.getItemsExpireInAMonth()).thenReturn(items);
		when(inv.itemsUpdated()).thenReturn(1);

		assertEquals(1, bbr.issueDiscountForItemsExpireIn30Days(0.2d));
		
		verify(inv, new Times(1)).update(item, 80.0d);
		verify(pas).announce(isA(Offer.class));
	}
	
	@Test
	public void testNoItemsDiscountedLessThanBasePrice() {
		
		Item item = new Item("TestItemBarCode", "Test Item", 100.0d, 100.0d);
		items.add(item);

		when(inv.getItemsExpireInAMonth()).thenReturn(items);
		when(inv.itemsUpdated()).thenReturn(0);

		assertEquals(0, bbr.issueDiscountForItemsExpireIn30Days(0.2d));
		
		verify(inv, new Times(0)).update(item, 80.0d);
		verify(pas, new Times(0)).announce(isA(Offer.class));		
	}
	
	@Test
	public void testDiscountAppliedTwoItems() {
		
		Item item1 = new Item("TestItemBarCode_1", "Test Item 1", 100.0d, 100.0d);
		Item item2 = new Item("TestItemBarCode_2", "Test Item 2", 100.0d, 0.0d);
		Item item3 = new Item("TestItemBarCode_3", "Test Item 3", 150.0d, 0.0d);
		items.add(item1);
		items.add(item2);
		items.add(item3);
		
		when(inv.getItemsExpireInAMonth()).thenReturn(items);
		when(inv.itemsUpdated()).thenReturn(2);
		
		assertEquals(2, bbr.issueDiscountForItemsExpireIn30Days(0.2d));
		verify(inv, new Times(1)).update(item2, 80.0d);
		verify(inv, new Times(1)).update(item3, 120.0d);
		
		verify(pas, new Times(2)).announce(isA(Offer.class));	
		/*
		 
		
		Use test data values to ensure that the discounted price
		will be less than the base price for one of the Items.
		Stub the itemsUpdated method to return 2.
		Assert that 0 is returned from issueDiscountForItemsExpireIn30Days.
		Verify that the update method is called on inventory twice
		and the announce method is called on PAS twice.

		*/
	}
	
}
