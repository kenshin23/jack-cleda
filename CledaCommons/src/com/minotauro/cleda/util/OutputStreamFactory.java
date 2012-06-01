/*
 * Created on 21/03/2008
 */
package com.minotauro.cleda.util;

import java.io.OutputStream;

/**
 * @author Demián Gutierrez
 */
public interface OutputStreamFactory {

  public OutputStream getOutputStream() throws Exception;
}
