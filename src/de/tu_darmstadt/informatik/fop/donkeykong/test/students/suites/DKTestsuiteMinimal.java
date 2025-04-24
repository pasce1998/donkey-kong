package de.tu_darmstadt.informatik.fop.donkeykong.test.students.suites;

import de.tu_darmstadt.informatik.fop.donkeykong.test.students.testcases.*;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class DKTestsuiteMinimal {
  
  public static Test suite() {
    TestSuite suite = new TestSuite("Student tests for DK - Minimal");
    
    suite.addTest(new JUnit4TestAdapter(MapTest.class));

    suite.addTest(new JUnit4TestAdapter(MovementTest.class));

    suite.addTest(new JUnit4TestAdapter(GravitationTest.class));

    suite.addTest(new JUnit4TestAdapter(CollisionTest.class));

    suite.addTest(new JUnit4TestAdapter(EndGameTest.class));

    return suite;
  }

}
