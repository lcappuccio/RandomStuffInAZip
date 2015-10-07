package org.systemexception.randomstuffinazip.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.systemexception.randomstuffinazip.model.Match;
import org.systemexception.randomstuffinazip.model.Player;
import org.systemexception.randomstuffinazip.pojo.XmlValidator;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

/**
 * @author leo
 * @date 24/07/15 22:10
 */
public class XmlValidatorTest {

	private XmlValidator sut;
	private static Player player1, player2;
	private static final Match MATCH = new Match();
	private static String xmlMatchString;
	private final String xsdFilePath = "xsd" + File.separator + "MatchPoints.xsd";

	@BeforeClass
	public static void setUp() {
		player1 = new Player("John", 100);
		player2 = new Player("Appleseed", 200);
		MATCH.addPlayer(player1);
		MATCH.addPlayer(player2);
		xmlMatchString = MATCH.matchToXml();
	}

	@Test
	public void validate_correctly() throws IOException, SAXException {
		sut = new XmlValidator(xmlMatchString, xsdFilePath);
		sut.validateXml();
	}

	@Test(expected = SAXException.class)
	public void throw_exception_on_invalid_xml() throws IOException, SAXException {
		sut = new XmlValidator("<abc></abc>", xsdFilePath);
		sut.validateXml();
	}
}