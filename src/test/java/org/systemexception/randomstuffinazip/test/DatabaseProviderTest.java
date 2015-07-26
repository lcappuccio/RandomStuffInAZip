package org.systemexception.randomstuffinazip.test;

import org.junit.Before;
import org.junit.Test;
import org.systemexception.randomstuffinazip.model.Match;
import org.systemexception.randomstuffinazip.model.Player;
import org.systemexception.randomstuffinazip.pojo.DatabaseProvider;
import org.systemexception.randomstuffinazip.pojo.ZipCompressor;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author leo
 * @date 26/07/15 01:17
 */
public class DatabaseProviderTest {

	private DatabaseProvider sut;
	private File file;
	private String matchId;
	private final String databaseFileName = "target/testdb";

	@Before
	public void setUp() throws IOException {
		Player player1 = new Player("A", 1);
		Player player2 = new Player("B", 2);
		Player player3 = new Player("C", 3);
		Match match = new Match();
		match.addPlayer(player1);
		match.addPlayer(player2);
		match.addPlayer(player3);
		String xmlMatch = match.matchToXml();
		matchId = String.valueOf(match.getMatchId());
		ZipCompressor zipCompressor = new ZipCompressor(xmlMatch, matchId);
		zipCompressor.zipContents();
		file = new File("target" + File.separator + matchId + ".zip");
		assertTrue(file.exists());
		File databaseFile= new File(databaseFileName);
		if (databaseFile.exists()) {
			databaseFile.delete();
		}
		sut = new DatabaseProvider(databaseFileName);
	}

	@Test
	public void store_file_in_database() {
		sut.addRecords(matchId, file);
		File storedRecord = sut.getRecord(matchId);
		assertEquals(file, storedRecord);
	}

	@Test
	public void store_records_in_database() {
		sut.addRecords(matchId, file);
		assertTrue(1 == sut.countItems());
	}

	@Test
	public void return_all_stored_records_in_database() {
		sut.addRecords(matchId, file);
		assertTrue(1 == sut.getAllStoredRecordIds().size());
	}
}