package com.ait.stock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StockListenerTest {

	private StockBroker sb = mock(StockBroker.class);
	private StockListener sl;
	private Stock st;
	
	@BeforeEach
	public void setup() {
		sl = new StockListener(sb);
		st = new Stock("test", 1000.0d);
		
	}
	
	@Test
	public void sellStocksWhenPriceGoesUp() {
		when(sb.getQoute(st)).thenReturn(1100.0d);
		sl.takeAction(st);
		verify(sb).sell(st, 10);
	}
	
	@Test
	public void buyStockWhenPriceGoesDown() {
		when(sb.getQoute(st)).thenReturn(900.0d);
		sl.takeAction(st);
		verify(sb, new Times(1)).buy(st, 100);
	}
}
