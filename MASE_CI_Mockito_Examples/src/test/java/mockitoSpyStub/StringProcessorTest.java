package mockitoSpyStub;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import mockitoSpyStub.PrinterNotConnectedException;
import mockitoSpyStub.SysoutPrinter;
import mockitoSpyStub.Printer;
import mockitoSpyStub.StringProcessor;

@RunWith(MockitoJUnitRunner.class)
public class StringProcessorTest {
	
	@Mock
	private Printer printer;
	
	@Spy
	private SysoutPrinter sysoutPrinter;
	
	@Test
	public void internal_buffer_should_be_absent_after_construction() throws Exception {
		// Given
		StringProcessor processor = new StringProcessor(printer);
		
		// When
		Optional<String> actualBuffer = processor.statusAndTest();
		
		// Then
		assertFalse(actualBuffer.isPresent());
	}
	
	@Test
	public void internal_buffer_should_be_absent_after_construction_sysout() throws Exception {
		// Given
		StringProcessor processor = new StringProcessor(sysoutPrinter);
		
		// When
		Optional<String> actualBuffer = processor.statusAndTest();
		
		// Then
		assertFalse(actualBuffer.isPresent());
	}
	
	@Test
	public void internal_buffer_should_be_absent_after_construction_sysout_with_donothing() throws Exception {
		// Given
		StringProcessor processor = new StringProcessor(sysoutPrinter);
		doNothing().when(sysoutPrinter).printTestPage();
		
		// When
		Optional<String> actualBuffer = processor.statusAndTest();
		
		// Then
		assertFalse(actualBuffer.isPresent());
	}
	
	@Test(expected = PrinterNotConnectedException.class)
	public void printer_not_connected_exception_should_be_thrown_up_the_stack() throws Exception {
		// Given
		StringProcessor processor = new StringProcessor(printer);
		doThrow(new PrinterNotConnectedException()).when(printer).printTestPage();
		
		// When
		Optional<String> actualBuffer = processor.statusAndTest();
		
		// Then
		assertFalse(actualBuffer.isPresent());
	}
}
