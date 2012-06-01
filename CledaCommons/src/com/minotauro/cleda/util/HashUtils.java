/*
 * Created on 12/10/2004
 */
package com.minotauro.cleda.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Random;

/**
 * @author Demián Gutierrez
 */
public class HashUtils {

  public static final String SHA_512/**/= "SHA-512";
  public static final String SHA_384/**/= "SHA-384";
  public static final String SHA_256/**/= "SHA-256";
  public static final String SHA_1/*  */= "SHA-1";
  public static final String MD5/*    */= "MD5";
  public static final String MD2/*    */= "MD2";

  // --------------------------------------------------------------------------------

  private static final String[] hex = //
  {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

  private static final Random random = new Random(System.currentTimeMillis());

  private static final int BUFFER_SIZE = 1024;

  // --------------------------------------------------------------------------------

  private HashUtils() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public static String getHash() {
    byte[] hash = new byte[16];

    random.nextBytes(hash);

    return byteArrayToHexString(hash);
  }

  // --------------------------------------------------------------------------------

  public static String getHash(String alg, String input) {
    try {
      return getHash(alg, new ByteArrayInputStream(input.getBytes()));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  public static String getHash(String alg, InputStream is) //
      throws Exception {

    // ----------------------------------------
    // Inits the MessageDigest
    // ----------------------------------------

    MessageDigest md = MessageDigest.getInstance(alg);

    // ----------------------------------------
    // Reads from the InputStream
    // ----------------------------------------

    byte[] buffer = new byte[BUFFER_SIZE];

    int length;

    // ----------------------------------------

    while ((length = is.read(buffer)) > 0) {
      md.update(buffer, 0, length);
    }

    return byteArrayToHexString(md.digest());
  }

  // --------------------------------------------------------------------------------

  public static String byteArrayToHexString(byte[] b) {
    StringBuffer ret = new StringBuffer(b.length * 2);

    for (int i = 0; i < b.length; ++i) {
      ret.append(byteToHexString(b[i]));
    }

    return ret.toString();
  }

  // --------------------------------------------------------------------------------

  public static String byteToHexString(byte b) {
    int n = b;

    n = n < 0 ? 256 + n : n;

    int d1 = n / 16;
    int d2 = n % 16;

    return hex[d1] + hex[d2];
  }

  // --------------------------------------------------------------------------------

  public static void main(String[] args) {
    System.err.println(getHash(MD2,/*    */"some_pass"));
    System.err.println(getHash(MD5,/*    */"some_pass"));
    System.err.println(getHash(SHA_1,/*  */"some_pass"));
    System.err.println(getHash(SHA_256,/**/"some_pass"));
    System.err.println(getHash(SHA_384,/**/"some_pass"));
    System.err.println(getHash(SHA_512,/**/"some_pass"));
  }
}
