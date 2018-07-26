/**
 *
 */
package net.magja.model.product;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.magja.utils.MagjaFileUtils;

/**
 * Tests the FileUtils.
 *
 * @author andre
 */
public class ProductMediaTest {

  private final static Logger log = LoggerFactory.getLogger(ProductMediaTest.class);

  // = "https://de.wikipedia.org/wiki/Portable_Network_Graphics#/media/File:Png-logo.png";

  private URL imageUrl;
  private BufferedImage image;


  @Before
  public void preCheck() throws Exception {

    final File imageFile = new File("src/test/resources/CC-BY-SA_icon.svg.png");
    this.imageUrl = imageFile.toURI().toURL();

    // given
    image = ImageIO.read(imageUrl);
    assertTrue("Image is readable and has a size.", image != null && image.getHeight() != 0 && image.getWidth() != 0);
  }

  @Test
  public void testImageFileTwo() {
    // when
    byte[] data = MagjaFileUtils.getBytesFromBufferedImage(image, "PNG");
    // then
    assertNotNull(data);
  }


  @Test
  public void testImageFileThree() throws IOException {
    // when
    byte[] data = MagjaFileUtils.getBytesFromFileURL(imageUrl.toString());
    // then
    assertNotNull(data);
  }


}
