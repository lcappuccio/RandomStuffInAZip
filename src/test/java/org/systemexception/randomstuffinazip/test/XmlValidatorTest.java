package org.systemexception.randomstuffinazip.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.systemexception.randomstuffinazip.model.Player;
import org.systemexception.randomstuffinazip.pojo.XmlMatchContainer;
import org.systemexception.randomstuffinazip.pojo.XmlValidator;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * @author leo
 * @date 24/07/15 22:10
 */
public class XmlValidatorTest {

	private XmlValidator sut;
	private static Player player1, player2;
	private static final XmlMatchContainer xmlMatchContainer = new XmlMatchContainer();
	private static String xmlMatchString;

	@BeforeClass
	public static void setUp() {
		player1 = new Player("John", 100);
		player2 = new Player("Appleseed", 200);
		xmlMatchContainer.addPlayer(player1);
		xmlMatchContainer.addPlayer(player2);
		xmlMatchString = xmlMatchContainer.getPlayerPoints();
	}

	@Test
	public void validate_correctly() throws IOException, SAXException {
		sut = new XmlValidator(xmlMatchString, "MatchPoints.xsd");
		sut.validateXml();
	}

	@Test(expected = SAXException.class)
	public void throw_exception_on_invalid_xml() throws IOException, SAXException {
		sut = new XmlValidator("<abc></abc>", "MatchPoints.xsd");
		sut.validateXml();
	}
}