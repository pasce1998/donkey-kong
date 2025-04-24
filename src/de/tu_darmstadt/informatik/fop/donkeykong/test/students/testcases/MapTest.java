package de.tu_darmstadt.informatik.fop.donkeykong.test.students.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.tu_darmstadt.informatik.fop.donkeykong.test.adapters.DKTestAdapterMinimal;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class MapTest {
  
  DKTestAdapterMinimal adapter;

  private final String level1 = "src/de/tu_darmstadt/informatik/fop/donkeykong/test/students/testcases/maps/level1.dk";

  @Before
  public void setUp() {
    adapter = new DKTestAdapterMinimal();
    adapter.initializeGame();
  }

  @After
  public void finish() {
    adapter.stopGame();
  }

  @Test
  public final void testLoadMap() {
    assertTrue(adapter.isCorrectMap()); // no error should exist before loading a new map
    adapter.loadMapFromFile(this.level1);
    assertTrue(adapter.isCorrectMap()); // check if no error occured while parsing
    assertEquals("T1", adapter.getCurrentMapName()); // check if the right map was loaded
  }

  @Test
  public final void testFirstLevel() {
    adapter.loadMapFromFile(this.level1);
    assertTrue(adapter.isCorrectMap()); // check if no error occured while parsing
    assertEquals("T1", adapter.getCurrentMapName()); // check if the right map was loaded
    
    // test how many entities were created/loaded
    assertEquals(416, adapter.getBorderAmount());
    assertEquals(10, adapter.getEntitiesAmount());
    assertEquals(324, adapter.getMapElementsAmount());
  }

  @Test
  public final void testLevelSettings() {
    adapter.loadMapFromFile(this.level1);
    assertTrue(adapter.isCorrectMap()); // check if no error occured while parsing
    assertEquals("T1", adapter.getCurrentMapName()); // check if the right map was loaded

    // test loaded settings
    assertEquals(118, adapter.getChunksX());
    assertEquals(86, adapter.getChunksY());
    assertEquals(8, adapter.getChunkSize());
  }

}
