package de.tu_darmstadt.informatik.fop.donkeykong;

import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.ImageIOImageData;

/**
 * Main class of the project, its needed to start the game (only solution to work with maven build process)
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 *
 */
public class Main {

	public static void main(String[] args) throws SlickException {

		System.out.println("Starting DonkeyKong!");
		System.out.println("=======================================================");
		System.out.println("Version: " + Consts.APP_VERSION);
		System.out.println("Developed by Pascal Schikora and Egemen Ulutürk");
		System.out.println("=======================================================");

		setLibraryPath();

		// Set this StateBasedGame in a new Window/App-Container
		AppGameContainer app = new AppGameContainer(new DonkeyKong(false));

		// set some basic settings
		app.setDisplayMode(Consts.DISPLAY_WIDTH, Consts.DISPLAY_HEIGHT, Consts.FULLSCREEN);
		app.setVSync(Consts.VSYNC);
		app.setShowFPS(Consts.SHOW_FPS);
		app.setAlwaysRender(true);
		app.setUpdateOnlyWhenVisible(false);

		setGameIcon();

		// launch the game
		app.start();
	}
	
	/**
	 * Sets the library path for the appropriate operating system
	 * Sets a different directory when run as jar
	 */
	private static void setLibraryPath() {
		System.setProperty("org.lwjgl.input.Mouse.allowNegativeMouseCoords", "true");
		if (System.getProperty(Consts.OS_NAME).toLowerCase().contains(Consts.WINDOWS_OS_NAME)) {
			System.setProperty(Consts.WINDOWS_LIB_PATH, System.getProperty(Consts.USER_DIR) + Consts.WINDOWS_USER_DIR);
		} else if (System.getProperty(Consts.OS_NAME).toLowerCase().contains(Consts.MAC_OS_NAME)) {
			 System.setProperty(Consts.MAC_LIB_PATH, System.getProperty(Consts.USER_DIR) + Consts.MAC_USER_DIR);
		} else {
			System.setProperty(Consts.LINUX_LIB_PATH, System.getProperty(Consts.USER_DIR) + Consts.LINUX_USER_DIR + System.getProperty(Consts.OS_NAME).toLowerCase());
		}
	}

	/**
	 * Sets the icon of the taskbar
	 */
	private static void setGameIcon() {
		try {
			Display.setIcon(new ByteBuffer[] {
				new ImageIOImageData().imageToByteBuffer(ImageIO.read(DonkeyKong.class.getResource(Consts.GAME_ICON)), false, false, null),
				new ImageIOImageData().imageToByteBuffer(ImageIO.read(DonkeyKong.class.getResource(Consts.GAME_ICON)), false, false, null)
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
