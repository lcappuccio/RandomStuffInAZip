package org.systemexception.randomstuffinazip.pojo;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

/**
 * @author leo
 * @date 24/07/15 22:06
 */
public class XmlMatchGenerator {

	private final String xsdPath, xmlContent;

	public XmlMatchGenerator(String xmlContent, String xsdPath) {
		this.xmlContent = xmlContent;
		this.xsdPath = xsdPath;
	}

	public void validateXml() throws SAXException, IOException {
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = factory.newSchema(loadXsd());
		Validator validator = schema.newValidator();
		StringReader stringReader = new StringReader(xmlContent);
		validator.validate(new StreamSource(stringReader));
	}

	private File loadXsd() {
		ClassLoader classLoader = getClass().getClassLoader();
		return new File(classLoader.getResource(xsdPath).getFile());
	}
}
