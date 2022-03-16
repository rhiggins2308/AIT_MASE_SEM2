package mockitoSpyStub;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;

import java.awt.Image;
import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import mockitoSpyStub.ImageProcessor;

@RunWith(MockitoJUnitRunner.class)
public class ImageProcessorTest {
	
	private ImageProcessor processor;
	
	@Spy
	private BufferedImage imageSpy = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
	@Mock
	Image mockThumbnail;
	
	@Before
	public void setup() {
		processor = new ImageProcessor(imageSpy);	
	}
	
	@Test
	public void scale_should_return_internal_image_scaled() throws Exception {
		// Given
		given(imageSpy.getScaledInstance(-1, 100, Image.SCALE_FAST)).willReturn(mockThumbnail);
		
		// When
		Image actualImage = processor.overwriteImageWithStripesAndReturnThumbnail(100);
		
		// Then
		assertEquals(actualImage, mockThumbnail);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void throws_exception() throws Exception {
		// Given
		given(imageSpy.getScaledInstance(anyInt(), anyInt(), anyInt())).willReturn(mockThumbnail);
		
		// Immediate IllegalArgumentException
	}
	
	@Test
	public void scale_should_return_internal_image_scaled_doReturn() throws Exception {
		// Given
		doReturn(mockThumbnail).when(imageSpy).getScaledInstance(anyInt(), anyInt(), anyInt());
		
		// When
		Image actualImage = processor.overwriteImageWithStripesAndReturnThumbnail(100);
		
		// Then
		assertEquals(actualImage, mockThumbnail);
	}
	
	@Test
	public void scale_should_return_internal_image_scaled_doReturn_doCallRealMethod() throws Exception {
		// Given
		doReturn(mockThumbnail).doCallRealMethod().when(imageSpy).getScaledInstance(anyInt(), anyInt(), anyInt());
		
		// When
		Image actualImage = processor.overwriteImageWithStripesAndReturnThumbnail(100);
		
		// Then
		assertEquals(actualImage, mockThumbnail);
	}
}
