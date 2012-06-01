/*
 * Created on 16/03/2008
 */
package com.minotauro.cleda.threads;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.minotauro.cleda.regexp.RegexpFilter;
import com.minotauro.cleda.regexp.RegexpFilterList;
import com.minotauro.cleda.util.OutputStreamFactory;
import com.minotauro.cleda.util.ToString;

/**
 * @author Demián Gutierrez
 */
public class ThreadMonitor extends BaseThread {

  protected RegexpFilterList<Map.Entry<Thread, StackTraceElement[]>> regexpFilterList;

  protected OutputStreamFactory outputStreamFactory;

  // --------------------------------------------------------------------------------

  public ThreadMonitor(OutputStreamFactory outputStreamFactory) {
    this.outputStreamFactory = outputStreamFactory;

    // ----------------------------------------
    // Creates the RegexpFilterList
    // ----------------------------------------

    ToString<Map.Entry<Thread, StackTraceElement[]>> toString = //
    new ToString<Map.Entry<Thread, StackTraceElement[]>>() {
      public String toString(Map.Entry<Thread, StackTraceElement[]> val) {
        return val.getKey().getName();
      }
    };

    // ----------------------------------------

    regexpFilterList = new RegexpFilterList //
    <Map.Entry<Thread, StackTraceElement[]>>(toString);
  }

  // --------------------------------------------------------------------------------

  public void addRegexpFilter(RegexpFilter regexpFilter) {
    regexpFilterList.addRegexpFilter(regexpFilter);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void failsafeRun() throws InterruptedException, NotRunningException {

    // ----------------------------------------
    // Get threads and apply filters
    // ----------------------------------------

    Map<Thread, StackTraceElement[]> m = Thread.getAllStackTraces();

    List<Map.Entry<Thread, StackTraceElement[]>> threadEntryList = //
    new ArrayList<Map.Entry<Thread, StackTraceElement[]>>(m.entrySet());

    regexpFilterList.filter(threadEntryList);

    // ----------------------------------------
    // Log remaining threads
    // ----------------------------------------

    StringBuffer strbuf = new StringBuffer();

    for (Map.Entry<Thread, StackTraceElement[]> e : threadEntryList) {

      strbuf.append("--------------------------------------------------------------------------------\n");
      strbuf.append(e.getKey().getName());
      strbuf.append("\n");

      for (int i = 0; i < e.getValue().length; i++) {
        strbuf.append(e.getValue()[i]);
        strbuf.append("\n");
      }
    }

    try {
      OutputStream outputStream = outputStreamFactory.getOutputStream();

      PrintStream ps = new PrintStream(outputStream);
      ps.println(strbuf.toString());
      ps.close();

      ThreadUtils.sleep(10000);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
