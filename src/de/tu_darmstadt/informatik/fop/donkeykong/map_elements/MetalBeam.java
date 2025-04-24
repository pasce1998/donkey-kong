package de.tu_darmstadt.informatik.fop.donkeykong.map_elements;

import org.newdawn.slick.SlickException;

import de.tu_darmstadt.informatik.fop.donkeykong.DonkeyKong;
import de.tu_darmstadt.informatik.fop.donkeykong.map.Identifier;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Utilities;
import eea.engine.component.render.ImageRenderComponent;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class MetalBeam extends MapElement {


  public MetalBeam(boolean climbable) throws SlickException {
      super("MetalBeam", climbable);
      if(!DonkeyKong.isTesting) this.addComponent(new ImageRenderComponent(Utilities.loadImageScaled(Consts.IMAGE_METAL_BEAM)));
      this.setPassable(climbable);
  }

  public MetalBeam() throws SlickException {
      this(false);
  }


  @Override
  public Identifier getIdentifier() {
    return Identifier.METAL_BEAM;
  }
}
