/**
 *
 */
package net.magja.model.product;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
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
  private final static String imageUrl = "https://de.wikipedia.org/wiki/Portable_Network_Graphics#/media/File:Png-logo.png";

  private BufferedImage image;

  @Before
  public void preCheck() throws Exception {
    // given
    image = ImageIO.read(new URL(imageUrl));
    assertTrue("Image is readable and has a size.", image != null && image.getHeight() != 0 && image.getWidth() != 0);
  }

  @Test
  public void testImageFileTwo() throws Exception {
    // when
    byte[] data = MagjaFileUtils.getBytesFromBufferedImage(image, "PNG");
    // then
    assertNotNull(data);
  }


  @Test
  public void testImageFileThree() throws Exception {
    // when
    byte[] data = MagjaFileUtils.getBytesFromFileURL(imageUrl);
    // then
    assertNotNull(data);
  }


}
