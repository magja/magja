/**
 * @author andre
 *
 */
package com.google.code.magja.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

public class MagjaFileUtils {

	/**
	 * Get the bytes of a File from a specified URL, for use in a
	 * FileOutputStream, for example.
	 * 
	 * @param url
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] getBytesFromFileURL(String url) throws IOException {
		URL u = new URL(url);

		URLConnection uc = u.openConnection();
		String contentType = uc.getContentType();
		int contentLength = uc.getContentLength();

		if(contentType.startsWith("text/") || contentLength == -1){
			throw new IOException("This is not a binary file.");
		}

		InputStream raw = uc.getInputStream();
		InputStream in = new BufferedInputStream(raw);
		byte[] data = new byte[contentLength];
		int bytesRead = 0;
		int offset = 0;
		while (offset < contentLength){
			bytesRead = in.read(data, offset, data.length - offset);
			if(bytesRead == -1)
				break;
			offset += bytesRead;
		}
		in.close();

		if(offset != contentLength){
			throw new IOException("Only read " + offset + " bytes; Expected " + contentLength + " bytes");
		}

		return data;
	}

	/**
	 * Get bytes from a specified BufferedImage, with the specified format
	 * 
	 * @param bi
	 *            - the buffered image
	 * @param format
	 *            - "JPG", "PNG" or "GIF"
	 * @return
	 */
	public static byte[] getBytesFromBufferedImage(BufferedImage bi, String format) {

		ByteArrayOutputStream buff = new ByteArrayOutputStream();
		try{

			ImageIO.write(bi, format, buff);
			byte[] bytes = buff.toByteArray();
			buff.close();

			return bytes;

		}catch(IOException ex){
			ex.printStackTrace();
		}

		return null;
	}

}
