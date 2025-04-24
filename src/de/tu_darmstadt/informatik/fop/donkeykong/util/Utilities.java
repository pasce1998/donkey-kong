package de.tu_darmstadt.informatik.fop.donkeykong.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

/**
 * Some utilities needed thorughout the whole game
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 *
 */
public class Utilities {

	/**
	 * Loads an image
	 * @param file the name and directory of the file
	 * @return returns the image object
	 * @throws SlickException throws an exception if the image couldn't be loaded
	 * @throws NullPointerException throws nullpointer when no such file exists
	 */
	public static Image loadImage(String file) throws SlickException, NullPointerException{
		return new Image(file);
	}

	public static Image loadImageScaled(String file) throws SlickException {
		return loadImage(file).getScaledCopy(Consts.SCALE);
	}
	
	/**
	 * Calculates the center point of the given game container
	 * 
	 * @param gameContainer container where the center point needs to be calculated
	 * @return centerPoint of the given container
	 */
	public static Vector2f getCenterPoint(final GameContainer gameContainer) {
		if (gameContainer == null) {
			throw new IllegalArgumentException("Given gameContainer is null");
		}
		return new Vector2f(gameContainer.getWidth() / 2, gameContainer.getHeight() / 2);
	}

	/**
	 * Saves a file with the given data
	 * @param fileName the path where to save the file
	 * @param data the data to save to the file
	 * @return returns true if the file could be saved, false if it couldn't be saved
	 */
	public static boolean writeFile(String fileName, String data) {
		if(fileName == null || fileName.isEmpty()) throw new IllegalArgumentException();
		if(data == null || data.isEmpty()) throw new IllegalArgumentException();
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName)));
			bw.write(data);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Reads a file 
	 * @param fileName the file which needs to be read
	 * @return the data in the file
	 */
	public static String readFile(String fileName) {
		if(fileName == null || fileName.isEmpty()) throw new IllegalArgumentException();
		
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			while((line = br.readLine()) != null) {
				sb.append(line + System.lineSeparator());
			}
			br.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static List<Entity> collides(int gameState, Entity entity) {
		List<Entity> collisions = new ArrayList<>();
		// retrieve all entities from the list of entities
		List<Entity> candidates = StateBasedEntityManager.getInstance().getEntitiesByState(gameState);
		// if there is such a state with entities...
		if (candidates == null || entity == null)
			return collisions; // cannot have a collision with "nothing"

		// ...then iterate over them...
		for (Entity tempEntity : candidates) {
			// ... and check each if they collide with "entity"
			if (entity.collides(tempEntity))
				collisions.add(tempEntity);

		}
		// return list of colliding entities
		return collisions;
	}
}
