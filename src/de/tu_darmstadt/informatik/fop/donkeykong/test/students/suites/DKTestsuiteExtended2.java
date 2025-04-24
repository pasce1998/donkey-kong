package de.tu_darmstadt.informatik.fop.donkeykong.test.students.suites;

import de.tu_darmstadt.informatik.fop.donkeykong.test.students.testcases.GameplayTest;
import de.tu_darmstadt.informatik.fop.donkeykong.test.students.testcases.InteractionTest;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class DKTestsuiteExtended2 {

  public static Test suite() {
    TestSuite suite = new TestSuite("Student tests for DK - Extended 2");

    suite.addTest(new JUnit4TestAdapter(GameplayTest.class));

    suite.addTest(new JUnit4TestAdapter(InteractionTest.class));
    
    return suite;
  }
  
}
