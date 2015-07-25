package org.systemexception.randomstuffinazip.test;

import org.junit.Before;
import org.junit.Test;
import org.systemexception.randomstuffinazip.model.Match;
import org.systemexception.randomstuffinazip.model.Player;
import org.systemexception.randomstuffinazip.pojo.XmlValidator;
import org.systemexception.randomstuffinazip.pojo.ZipCompressor;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * @author leo
 * @date 25/07/15 23:49
 */
public class ZipCompressorTest {

	private ZipCompressor sut;
	private static Player player1, player2;
	private static final Match MATCH = new Match();
	private static String xmlMatchString;
	private static XmlValidator xmlValidator;

	@Before
	public void setUp() throws Exception {
		player1 = new Player("John", 100);
		player2 = new Player("Appleseed", 200);
		MATCH.addPlayer(player1);
		MATCH.addPlayer(player2);
		xmlMatchString = MATCH.matchToXml();
	}

	@Test(expected = IllegalArgumentException.class)
	public void refuse_null_filename() {
		sut = new ZipCompressor("abc",null);
	}

	@Test
	public void zip_contents() throws IOException {
		sut = new ZipCompressor(MATCH.matchToXml(), String.valueOf(MATCH.getMatchId()));
		sut.zipContents();
		assertTrue(new File("target" + File.separator + String.valueOf(MATCH.getMatchId()) + ".zip").exists());
	}
}