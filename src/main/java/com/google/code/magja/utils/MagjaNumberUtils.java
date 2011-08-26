/**
 * @author andre
 *
 */
package com.google.code.magja.utils;

public class MagjaNumberUtils {

	/**
	 * Generates a random integer inside the lo and hi interval
	 * @param lo
	 * @param hi
	 * @return the generated int
	 */
	public static int randomInteger(int lo, int hi) {
		java.util.Random rn = new java.util.Random();
		int n = hi - lo + 1;
		int i = rn.nextInt() % n;
		if (i < 0) i = -i;
		return lo + i;
	}

}
