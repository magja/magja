/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */
package com.google.code.magja.magento;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

public class Utils {

	public static String dump(List<Map<String, Object>> datas) {
		StringBuilder builder = new StringBuilder();

		for (Map<String, Object> dataMap : datas) {
			builder.append("-----\n");
			for (String key : dataMap.keySet()) {
				builder.append(key + " -> " + dataMap.get(key)).append("\n");
			}
		}
		return builder.toString();
	}

	public static String[][] getTable(List<Map<String, Object>> datas) {
		int arrayLength = datas.size();
		int arrayWidth = datas.get(0).size();
		String[][] table = new String[arrayLength + 1][arrayWidth];

		// set head
		Object[] title = datas.get(0).keySet().toArray();
		for (int i = 0; i < arrayWidth; i++) {
			table[0][i] = title[i].toString();
		}

		// set data
		for (int i = 1; i < arrayLength; i++) {
			for (int j = 0; j < arrayWidth; j++) {
				table[i][j] = datas.get(i).get(table[0][j]).toString();
			}
		}

		return table;
	}

	public static String viewTable(String[][] matrix) {
		String s = "";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				s += "|" + matrix[i][j];
			}
			s += "|\n";
		}

		return s;
	}

	public static String viewTable(List<String> list) {
		StringBuilder builder = new StringBuilder();
		for (String s : list) {
			builder.append(s + "\n");
		}
		return builder.toString();
	}

	public static String dump(Map<String, Object> dataMap) {
		StringBuilder builder = new StringBuilder();
		for (String key : dataMap.keySet()) {
			builder.append(key + " -> " + dataMap.get(key)).append("\n");
		}
		return builder.toString();
	}

	public static String getMd5Hash(String plaintext) {
		try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(plaintext.getBytes());
            byte[] digest = md5.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String hashtext = bigInt.toString(16);

            // we need to zero pad it to use the full 32 chars
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

			return hashtext;
		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static byte[] readFile(String imageName) {
		byte[] buf = null;
		try {
			File file = new File(imageName);
			buf = new byte[(int) file.length()];
			FileInputStream fis = new FileInputStream(file);
			fis.read(buf);
			fis.close();
		} catch (IOException e) {
			System.err.println(e);
		}

		return buf;
	}
}
