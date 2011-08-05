/**
 *
 */
package com.google.code.magja.model.product;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import org.junit.Test;

import com.google.code.magja.model.media.Media;
import com.google.code.magja.utils.MagjaFileUtils;

/**
 * @author andre
 *
 */
public class ProductMediaTest {

	@Test
	public void testImageFileIThree() throws Exception {

		String url = "http://www.rockstore.com.br/store/catalog/GV003_foto.jpg";

	    byte[] data = MagjaFileUtils.getBytesFromFileURL(url);



	}

	@Test
	public void testImageFileIOne() throws Exception {

		String url = "http://www.rockstore.com.br/store/catalog/GV003_foto.jpg";

	    byte[] data = MagjaFileUtils.getBytesFromFileURL(url);

	    //String filename = u.getFile().substring(url.lastIndexOf('/') + 1);
	    //String filename = "/home/andre/DEV/temp/image.jpg";
	    //FileOutputStream out = new FileOutputStream(filename);
	    //out.write(data);
	    //out.flush();
	    //out.close();

	}

	@Test
	public void testImageFileTwo() throws Exception {
		BufferedImage image = null;

		// Read from a file
		// File file = new File("image.gif");
		// image = ImageIO.read(file);

		// Read from an input stream
		// InputStream is = new BufferedInputStream( new
		// FileInputStream("image.gif"));
		// image = ImageIO.read(is);

		// Read from a URL
		URL url = new URL(
				"http://www.rockstore.com.br/store/catalog/GV003_foto.jpg");
		image = ImageIO.read(url);

		System.out.println(image.toString());

		ImageInputStream iis = ImageIO.createImageInputStream(image);

	}

}
