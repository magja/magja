/**
 *
 */
package net.magja.model.product;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

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

  private String imageUrl = "http://upload.wikimedia.org/wikipedia/commons/thumb/b/b4/Ambox_important.svg/40px-Ambox_important.svg.png";
  private final static Logger log = LoggerFactory.getLogger(ProductMediaTest.class);

  @Test
  public void testImageFileIThree() throws Exception {
    byte[] data = MagjaFileUtils.getBytesFromFileURL(imageUrl);
    assertNotNull(data);
  }

  @Test
  public void testImageFileTwo() throws Exception {
    // given
    BufferedImage image = ImageIO.read(new URL(imageUrl));
    assertTrue("Image is readable and has a size.", image != null && image.getHeight() != 0 && image.getWidth() != 0);

    // when
    byte[] data = MagjaFileUtils.getBytesFromBufferedImage(image, "PNG");

    // then
    assertNotNull(data);
  }

}
