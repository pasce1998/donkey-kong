package de.tu_darmstadt.informatik.fop.donkeykong.test.students.suites;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class DKTestsuiteAll {
  
  public static Test suite() {
    TestSuite suite = new TestSuite("Student tests for DK - All");

    suite.addTest(DKTestsuiteMinimal.suite());
    suite.addTest(DKTestsuiteExtended1.suite());
    suite.addTest(DKTestsuiteExtended2.suite());
    suite.addTest(DKTestsuiteExtended3.suite());

    return suite;
  }

}
