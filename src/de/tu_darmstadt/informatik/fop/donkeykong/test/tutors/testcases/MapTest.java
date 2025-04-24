package de.tu_darmstadt.informatik.fop.donkeykong.test.tutors.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

  private final String level1 = "src/de/tu_darmstadt/informatik/fop/donkeykong/test/tutors/testcases/maps/level1.dk";
  private final String level2 = "src/de/tu_darmstadt/informatik/fop/donkeykong/test/tutors/testcases/maps/level2.dk";
  private final String level3 = "src/de/tu_darmstadt/informatik/fop/donkeykong/test/tutors/testcases/maps/level3.dk";
  private final String level4 = "src/de/tu_darmstadt/informatik/fop/donkeykong/test/tutors/testcases/maps/level4.dk";
  private final String level5 = "src/de/tu_darmstadt/informatik/fop/donkeykong/test/tutors/testcases/maps/level5.dk";

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
  public final void testLoadDefaultMaps() {
    // load second map
    assertTrue(adapter.isCorrectMap()); // no error should exist before loading a new map
    adapter.loadMapFromFile(this.level1);
    assertTrue(adapter.isCorrectMap()); // check if no error occured while parsing
    assertEquals("T1", adapter.getCurrentMapName()); // check if the right map was loaded
    adapter.loadMapFromFile(this.level2);
    assertTrue(adapter.isCorrectMap()); // check if no error occured while parsing
    assertEquals("T2", adapter.getCurrentMapName()); // check if the right map was loaded
    // load third map
    adapter.loadMapFromFile(this.level3);
    assertTrue(adapter.isCorrectMap()); // check if no error occured while parsing
    assertEquals("T3", adapter.getCurrentMapName()); // check if the right map was loaded
  }

  @Test
  public final void testElementOutsideMap() {
    assertTrue(adapter.isCorrectMap()); // no error should exist before loading a new map
    adapter.loadMapFromFile(this.level5);
    assertFalse(adapter.isCorrectMap()); // check if error occured while parsing
  }

  @Test
  public final void testDefaultSettings() {
    assertTrue(adapter.isCorrectMap()); // no error should exist before loading a new map
    adapter.loadMapFromFile(this.level4);
    assertTrue(adapter.isCorrectMap()); // check if no error occured while parsing
    assertFalse(adapter.getCurrentMapName() == null || adapter.getCurrentMapName().isEmpty()); // check if a name was set
    // check default settings
    assertEquals(64, adapter.getChunksX());
    assertEquals(96, adapter.getChunksY());
    assertEquals(320, adapter.getOffsetX());
    assertEquals(16, adapter.getOffsetY());
    assertEquals(8, adapter.getChunkSize());
  }

  @Test
  public final void testTranslatedPositions() {
    assertTrue(adapter.isCorrectMap()); // no error should exist before loading a new map
    adapter.loadMapFromFile(this.level1);
    assertTrue(adapter.isCorrectMap()); // check if no error occured while parsing
    assertEquals("T1", adapter.getCurrentMapName()); // check if the right map was loaded
    // check marios start position
    float delta = 0.5f;
    assertEquals(656.0f, adapter.getTranslatedMarioPosition()[0], delta);
    assertEquals(664.0f, adapter.getTranslatedMarioPosition()[1], delta);
    // check paulines position
    assertEquals(760.0f, adapter.getTranslatedPaulinePosition()[0], delta);
    assertEquals(240.0f, adapter.getTranslatedPaulinePosition()[1], delta);
    // check donkey kongs position
    assertEquals(640.0f, adapter.getTranslatedDonkeyKongPosition()[0], delta);
    assertEquals(216.0f, adapter.getTranslatedDonkeyKongPosition()[1], delta);
  }
}
