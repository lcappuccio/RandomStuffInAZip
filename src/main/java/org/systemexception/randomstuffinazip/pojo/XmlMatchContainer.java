package org.systemexception.randomstuffinazip.pojo;

import org.systemexception.randomstuffinazip.model.Player;

import java.util.ArrayList;

/**
 * @author leo
 * @date 24/07/15 22:18
 */
public class XmlMatchContainer {

	private final String xmlHeader = "<match>", xmlFooter = "</match>";
	private final String player = "<playername>$PLAYER</playername>";
	private final String points = "<playerpoints>$POINTS</playerpoints>";
	private final ArrayList<ArrayList<String>> playerPoints = new ArrayList<ArrayList<String>>();

	public void addPlayer(final Player player) {
		ArrayList<String> playerScore = new ArrayList<String>();
		playerScore.add(player.getName());
		playerScore.add(String.valueOf(player.getPoints()));
		playerPoints.add(playerScore);
	}

	public String getPlayerPoints() {
		String xml = xmlHeader;
		for (ArrayList<String> playerScore: playerPoints) {
			xml = xml.concat("<playerscore>");
			xml = xml.concat(player.replace("$PLAYER", playerScore.get(0))).concat(points.replace("$POINTS", String
					.valueOf(playerScore.get(1))));
			xml = xml.concat("</playerscore>");
		}
		xml = xml.concat(xmlFooter);
		return xml;
	}
}
