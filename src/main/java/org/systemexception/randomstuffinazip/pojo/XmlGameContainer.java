package org.systemexception.randomstuffinazip.pojo;

import org.systemexception.randomstuffinazip.model.Player;

import java.util.ArrayList;

/**
 * @author leo
 * @date 24/07/15 22:18
 */
public class XmlGameContainer {

	private final String xmlHeader = "<game>", xmlFooter = "</game>";
	private String player = "<Player>$PLAYER</player>", points = "<points>$POINTS</points>";
	private ArrayList<ArrayList<String>> playerPoints = new ArrayList<ArrayList<String>>();

	public void addPlayerPoints(Player player) {
		ArrayList<String> playerScore = new ArrayList<String>();
		playerScore.add(player.getName());
		playerScore.add(String.valueOf(player.getPoints()));
		playerPoints.add(playerScore);
	}

	public String getPlayerPoints() {
		String xml = xmlHeader;
		for (ArrayList<String> playerScore: playerPoints) {
			player = player.replace("$PLAYER", playerScore.get(0));
			points = points.replace("$POINTS", playerScore.get(1));
			xml = xml.concat(player).concat(points);
		}
		xml = xml.concat(xmlFooter);
		return xml;
	}
}
