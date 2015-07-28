package org.systemexception.randomstuffinazip.main;

import org.systemexception.randomstuffinazip.model.Match;
import org.systemexception.randomstuffinazip.model.Player;
import org.systemexception.randomstuffinazip.pojo.DatabaseProvider;
import org.systemexception.randomstuffinazip.pojo.ZipCompressor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

/**
 * @author leo
 * @date 24/07/15 22:28
 */
public class Main {

	private static DatabaseProvider databaseProvider = new DatabaseProvider("target/database.db");

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 10; i++) {
			Match match = generateMatch();
			match.saveMatchToFile("target");
			zipMatch(match);
		}
		databaseProvider.countItems();
		databaseProvider.closeDatabase();
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
		ZipCompressor zipCompressor = new ZipCompressor("target" + File.separator + String.valueOf(match.getMatchId()));
		try {
			File matchFile = new File("target" + File.separator + String.valueOf(match.getMatchId()) + ".xml");
			byte[] fileData = Files.readAllBytes(matchFile.toPath());
			zipCompressor.addFileToZip(matchFile);
			File zipMatchFile = new File("target" + File.separator + String.valueOf(match.getMatchId()) + ".zip");
			byte[] zipFileData = Files.readAllBytes(zipMatchFile.toPath());
			storeRecord(String.valueOf(match.getMatchId()), zipFileData, zipMatchFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void storeRecord(String matchId, byte[] matchFileData, File matchFile) {
		databaseProvider.addRecords(matchId, matchFileData, matchFile);
	}
}
