package mockitoSpyStub;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ImageProcessor {

	private BufferedImage image;

	public ImageProcessor(BufferedImage image) {
		this.image = image;
	}

	public Image overwriteImageWithStripesAndReturnThumbnail(int thumbHeight) {
		debugOutputColorSpace();

		Random random = new Random();
		Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));

		for (int x = 0; x < image.getWidth(); x++) {
			if (x% 15 == 0) {
				color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
			}
			for (int y = 0; y < image.getHeight(); y++) {
				image.setRGB(x, y, color.getRGB());
			}
		}

		Image thumbnail = image.getScaledInstance(-1, thumbHeight, Image.SCALE_FAST);

		Image microScale = image.getScaledInstance(-1, 5, Image.SCALE_DEFAULT);
		debugOutput(microScale);
		return thumbnail;
	}

	private void debugOutput(Image microScale) {
		System.out.println("Runtime type of microScale Image is " + microScale.getClass());

	}

	private void debugOutputColorSpace() {
		for (int i=0; i< image.getColorModel().getColorSpace().getNumComponents(); i++) {
			String componentName = image.getColorModel().getColorSpace().getName(i);
			System.out.println(String.format("Colorspace Component[%d]: %s", i, componentName));
		}
	}
}
