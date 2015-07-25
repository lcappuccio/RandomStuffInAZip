package org.systemexception.randomstuffinazip.main;

import org.systemexception.randomstuffinazip.model.Player;
import org.systemexception.randomstuffinazip.model.Match;

/**
 * @author leo
 * @date 24/07/15 22:28
 */
public class Main {

	public static void main(String[] args) {
		Player player1 = new Player("Test1", 100);
		Player player2 = new Player("Test2", 200);

		Match match = new Match();

		match.addPlayer(player1);
		match.addPlayer(player2);

		System.out.println(match.matchToXml());
	}
}
