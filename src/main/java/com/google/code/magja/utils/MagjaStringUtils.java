/**
 * @author andre
 *
 */
package com.google.code.magja.utils;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.lang.Math.pow;
import static java.lang.Math.random;
import static java.lang.Math.round;
import static org.apache.commons.lang.StringUtils.leftPad;

public class MagjaStringUtils {

	/**
	 * Generates a random String with fixed lenght
	 * 
	 * @param length
	 * @return generated String
	 */
	public static String randomString(int length) {

		StringBuffer sb = new StringBuffer();
		for(int i = length; i > 0; i -= 12){
			int n = min(12, abs(i));
			sb.append(leftPad(Long.toString(round(random() * pow(36, n)), 36), n, '0'));
		}

		return sb.toString();
	}

	/**
	 * Generates a random String with the lenght between the lo (lower) and hi
	 * (higher)
	 * 
	 * @param lo
	 * @param hi
	 * @return generated String
	 */
	public static String randomString(int lo, int hi) {
		int n = MagjaNumberUtils.randomInteger(lo, hi);
		byte b[] = new byte[n];
		for(int i = 0; i < n; i++)
			b[i] = (byte)MagjaNumberUtils.randomInteger('a', 'z');
		return new String(b, 0);
	}

	/**
	 * @param s
	 * @return true if the string is not empty or not null
	 */
	public static boolean isNotEmptyOrNullString(String s) {

		boolean ret = false;

		if(s != null && !"".equals(s)){
			if(!s.trim().equals(""))
				ret = true;
		}

		return ret;
	}

	/**
	 * @param s
	 * @return true if the string is empty or null
	 */
	public static boolean isEmptyOrNullString(String s) {
		if(s == null)
			return true;
		else if("".equals(s.trim()))
			return true;

		return false;
	}
}
