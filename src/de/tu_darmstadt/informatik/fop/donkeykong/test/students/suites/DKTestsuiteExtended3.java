package de.tu_darmstadt.informatik.fop.donkeykong.test.students.suites;

import de.tu_darmstadt.informatik.fop.donkeykong.test.students.testcases.BonusTest;
import de.tu_darmstadt.informatik.fop.donkeykong.test.students.testcases.HammerTest;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class DKTestsuiteExtended3 {

  public static Test suite() {
    TestSuite suite = new TestSuite("Student tests for DK - Extended 3");

    suite.addTest(new JUnit4TestAdapter(HammerTest.class));

    suite.addTest(new JUnit4TestAdapter(BonusTest.class));

    return suite;
  }
  
}
