package de.tu_darmstadt.informatik.fop.donkeykong.util.highscore;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Utilities;
import de.tu_darmstadt.informatik.fop.donkeykong.util.abstracts.AbstractScore;
import de.tu_darmstadt.informatik.fop.donkeykong.util.interfaces.IHighscores;

/**
 * Class for managing HighscoreEntries
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 *
 */
public class Highscores implements IHighscores {

	private List<AbstractScore> highscores;
	private String fileName;
	
	/**
	 * Constructor, loads all entries 
	 */
	public Highscores() {
		this.highscores = new LinkedList<>();
		this.fileName = Consts.HIGHSCORE_FILE;
		loadHighscores();
	}

	public Highscores(String fileName) {
		this.highscores = new LinkedList<>();
		this.fileName = fileName;
		loadHighscores();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean loadHighscores() {
		String data = Utilities.readFile(this.fileName);
		if(data == null || data.isEmpty()) return false;
		for(String line : data.split(System.lineSeparator())) {
			if(line.isEmpty()) continue;
			this.addEntry(new Score(line));
		}
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean saveToFile() {
		final String data = this.highscores.stream().map(hs -> hs.toString()).collect(Collectors.joining(System.lineSeparator()));
		return Utilities.writeFile(Consts.HIGHSCORE_FILE, data);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean addEntry(final AbstractScore entry) {
		if(entry == null) return false;
		this.highscores.add(entry);
		Collections.sort(this.highscores);
		if(this.highscores.size() > Consts.HIGHSCORES_MAX_ENTRIES) {
			this.highscores.remove(this.highscores.size() - 1);
			return false;
		}
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<AbstractScore> getHighscores() {
		return highscores;
	}

}
