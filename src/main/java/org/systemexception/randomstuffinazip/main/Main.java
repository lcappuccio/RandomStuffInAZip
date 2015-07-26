package org.systemexception.randomstuffinazip.main;

import org.systemexception.randomstuffinazip.model.Match;
import org.systemexception.randomstuffinazip.model.Player;
import org.systemexception.randomstuffinazip.pojo.DatabaseProvider;
import org.systemexception.randomstuffinazip.pojo.ZipCompressor;

import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @author leo
 * @date 24/07/15 22:28
 */
public class Main {

	private static DatabaseProvider databaseProvider = new DatabaseProvider();

	public static void main(String[] args) {
		for (int i = 0; i < 3000; i++) {
			Match match = generateMatch();
			zipMatch(match);
		}

	}

	private static Match generateMatch() {
		Match match = new Match();
		for (int i = 0; i < 10; i++) {
			Random rnd = new Random();
			Player player = new Player("Player" + String.valueOf(i), rnd.nextInt(100) + 1);
			match.addPlayer(player);
		}
		return match;
	}

	private static void zipMatch(Match match) {
		ZipCompressor zipCompressor = new ZipCompressor(match.matchToXml(), String.valueOf(match.getMatchId()));
		try {
			zipCompressor.zipContents();
			storeRecord(new File("target" + File.separator + String.valueOf(match.getMatchId()) + ".zip"), String
					.valueOf(match.getMatchId()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void storeRecord(File matchFile, String matchId) {
		databaseProvider.addRecords(matchId, matchFile);
	}
}
