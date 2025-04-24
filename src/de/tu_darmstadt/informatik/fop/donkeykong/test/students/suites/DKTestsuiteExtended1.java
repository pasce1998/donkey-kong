package de.tu_darmstadt.informatik.fop.donkeykong.test.students.suites;

import de.tu_darmstadt.informatik.fop.donkeykong.test.students.testcases.HighscoreTest;
import de.tu_darmstadt.informatik.fop.donkeykong.test.students.testcases.ScoreTest;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class DKTestsuiteExtended1 {

  public static Test suite() {
    TestSuite suite = new TestSuite("Student tests for DK - Extended 1");
    
    suite.addTest(new JUnit4TestAdapter(HighscoreTest.class));

    suite.addTest(new JUnit4TestAdapter(ScoreTest.class));

    return suite;
  }
  
}
