package org.systemexception.randomstuffinazip.pojo;

import org.systemexception.randomstuffinazip.model.Player;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author leo
 * @date 24/07/15 22:18
 */
public class XmlMatchContainer {

	private final String xmlHeader = "<match><match_id>$MATCH_ID</match_id>", xmlFooter = "</match>";
	private final String player = "<playername>$PLAYER</playername>";
	private final String points = "<playerpoints>$POINTS</playerpoints>";
	private final ArrayList<ArrayList<String>> playerPoints = new ArrayList<>();

	public void addPlayer(final Player player) {
		ArrayList<String> playerScore = new ArrayList<>();
		playerScore.add(player.getName());
		playerScore.add(String.valueOf(player.getPoints()));
		playerPoints.add(playerScore);
	}

	public String getPlayerPoints() {
		String xml = xmlHeader.replace("$MATCH_ID", String.valueOf(getRandomMatchId()));
		for (ArrayList<String> playerScore : playerPoints) {
			xml = xml.concat("<playerscore>");
			xml = xml.concat(player.replace("$PLAYER", playerScore.get(0))).concat(points.replace("$POINTS", String
					.valueOf(playerScore.get(1))));
			xml = xml.concat("</playerscore>");
		}
		xml = xml.concat(xmlFooter);
		return xml;
	}

	private int getRandomMatchId() {
		Random rnd = new Random();
		return rnd.nextInt(Integer.MAX_VALUE) + 1;
	}
}
