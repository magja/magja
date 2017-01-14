package com.google.code.magja.utils;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.lang.Math.pow;
import static java.lang.Math.random;
import static java.lang.Math.round;
import static org.apache.commons.lang3.StringUtils.leftPad;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * String utilities.
 * 
 * @author andre
 *
 */
public class MagjaStringUtils {
  private final static Logger log = LoggerFactory.getLogger(MagjaStringUtils.class);

  /**
   * Generates a random String with fixed lenght
   * 
   * @param length
   * @return generated String
   */
  public static String randomString(int length) {

    StringBuffer sb = new StringBuffer();
    for (int i = length; i > 0; i -= 12) {
      int n = min(12, abs(i));
      sb.append(leftPad(Long.toString(round(random() * pow(36, n)), 36), n, '0'));
    }

    return sb.toString();
  }

  /**
   * Generates a random String with the length between the lo (lower) and hi
   * (higher)
   * 
   * @param lo
   *          low boundary
   * @param hi
   *          high boundary
   * @return generated String
   */
  public static String randomString(int lo, int hi) {
    int n = MagjaNumberUtils.randomInteger(lo, hi);
    byte b[] = new byte[n];
    for (int i = 0; i < n; i++)
      b[i] = (byte) MagjaNumberUtils.randomInteger('a', 'z');
    return new String(b, 0);
  }

  /**
   * @param s
   * @return true if the string is not empty or not null
   */
  public static boolean isNotEmptyOrNullString(String s) {
    boolean ret = false;
    if (s != null && !"".equals(s)) {
      if (!s.trim().equals("")) {
        ret = true;
      }
    }
    return ret;
  }

  /**
   * @param s
   * @return true if the string is empty or null
   */
  public static boolean isEmptyOrNullString(String s) {
    if (s == null) {
      return true;
    } else if ("".equals(s.trim())) {
      return true;
    }
    return false;
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
      log.error("Error in generating hash: ", e);
    }
    return "";
  }

}
