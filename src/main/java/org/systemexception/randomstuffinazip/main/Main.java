package org.systemexception.randomstuffinazip.main;

import org.systemexception.randomstuffinazip.model.Player;
import org.systemexception.randomstuffinazip.pojo.XmlMatchContainer;

/**
 * @author leo
 * @date 24/07/15 22:28
 */
public class Main {

	public static void main(String[] args) {
		Player player1 = new Player("Test1", 100);
		Player player2 = new Player("Test2", 200);

		XmlMatchContainer xmlMatchContainer = new XmlMatchContainer();

		xmlMatchContainer.addPlayer(player1);
		xmlMatchContainer.addPlayer(player2);

		System.out.println(xmlMatchContainer.getPlayerPoints());
	}
}
