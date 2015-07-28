package org.systemexception.randomstuffinazip.test;

import org.junit.Before;
import org.junit.Test;
import org.systemexception.randomstuffinazip.model.Match;
import org.systemexception.randomstuffinazip.model.Player;
import org.systemexception.randomstuffinazip.pojo.ZipCompressor;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.junit.Assert.assertTrue;

/**
 * @author leo
 * @date 25/07/15 23:49
 */
public class ZipCompressorTest {

	private ZipCompressor sut;
	private static Player player1, player2;
	private static final Match MATCH = new Match();
	private final String outputPath = "target";

	@Before
	public void setUp() throws Exception {
		player1 = new Player("John", 100);
		player2 = new Player("Appleseed", 200);
		MATCH.addPlayer(player1);
		MATCH.addPlayer(player2);
		MATCH.saveMatchToFile(outputPath);
	}

	@Test(expected = IllegalArgumentException.class)
	public void refuse_null_filename() {
		sut = new ZipCompressor(null);
	}

	@Test
	public void zip_contents() throws IOException {
		sut = new ZipCompressor("target" + File.separator + String.valueOf(MATCH.getMatchId()));
		sut.addFileToZip(new File("target" + File.separator + String.valueOf(MATCH.getMatchId()) + ".xml"));
		String zipFileName = String.valueOf("target" + File.separator + MATCH.getMatchId()) + ".zip";
		assertTrue(new File(zipFileName).exists());
		// Check that zip file includes the xml
		ZipFile zipFile = new ZipFile(zipFileName);
		Enumeration zipEntries = zipFile.entries();
		while (zipEntries.hasMoreElements()) {
			assertTrue(((ZipEntry) zipEntries.nextElement()).getName().equals(MATCH.getMatchId() + ".xml"));
		}
	}
}