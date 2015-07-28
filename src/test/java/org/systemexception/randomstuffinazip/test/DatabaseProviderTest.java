package org.systemexception.randomstuffinazip.test;

import org.junit.Before;
import org.junit.Test;
import org.systemexception.randomstuffinazip.model.Match;
import org.systemexception.randomstuffinazip.model.Player;
import org.systemexception.randomstuffinazip.pojo.DatabaseProvider;
import org.systemexception.randomstuffinazip.pojo.ZipCompressor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * @author leo
 * @date 26/07/15 01:17
 */
public class DatabaseProviderTest {

	private DatabaseProvider sut;
	private File zipFile, matchFile;
	private String matchId;
	private byte[] zipFileBytes;
	private final String databaseFileName = "target/testdb.db";
	private final String outputPath = "target";

	@Before
	public void setUp() throws IOException {
		Player player1 = new Player("A", 1);
		Player player2 = new Player("B", 2);
		Player player3 = new Player("C", 3);
		Match match = new Match();
		match.addPlayer(player1);
		match.addPlayer(player2);
		match.addPlayer(player3);
		match.saveMatchToFile(outputPath);
		matchId = String.valueOf(match.getMatchId());
		ZipCompressor zipCompressor = new ZipCompressor(outputPath + File.separator + matchId);
		matchFile = new File(outputPath + File.separator + matchId + ".xml");
		zipCompressor.addFileToZip(matchFile);
		zipFile = new File(outputPath + File.separator + matchId + ".zip");
		zipFileBytes = Files.readAllBytes(Paths.get(zipFile.getAbsolutePath()));
		assertTrue(zipFile.exists());
		File databaseFile= new File(databaseFileName);
		if (databaseFile.exists()) {
			databaseFile.delete();
		}
		sut = new DatabaseProvider(databaseFileName);
	}

	@Test
	public void store_file_in_database() throws IOException {
		Path filePath = Paths.get(zipFile.getAbsolutePath());
		byte[] fileData = Files.readAllBytes(filePath);
		sut.addRecords(matchId, zipFileBytes, zipFile);
		byte[] storedRecord = sut.getRecord(matchId);
		assertTrue(Arrays.equals(fileData,storedRecord));
	}

	@Test
	public void store_records_in_database() {
		sut.addRecords(matchId, zipFileBytes, zipFile);
		assertTrue(1 == sut.countItems());
	}

	@Test
	public void return_all_stored_records_in_database() {
		sut.addRecords(matchId, zipFileBytes, zipFile);
		assertTrue(1 == sut.getAllStoredRecordIds().size());
	}
}