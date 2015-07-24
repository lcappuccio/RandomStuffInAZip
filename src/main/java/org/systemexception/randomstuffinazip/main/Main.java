package org.systemexception.randomstuffinazip.main;

import org.systemexception.randomstuffinazip.model.Player;
import org.systemexception.randomstuffinazip.pojo.XmlGameContainer;

/**
 * @author leo
 * @date 24/07/15 22:28
 */
public class Main {

	public static void main(String[] args) {
		Player player1 = new Player("Test1");
		Player player2 = new Player("Test2");

		player1.setPoints(100);
		player2.setPoints(200);

		XmlGameContainer xmlGameContainer = new XmlGameContainer();

		xmlGameContainer.addPlayerPoints(player1);
		xmlGameContainer.addPlayerPoints(player2);

		System.out.printf(xmlGameContainer.getPlayerPoints());
	}
}
