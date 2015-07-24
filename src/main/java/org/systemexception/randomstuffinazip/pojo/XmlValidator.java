package org.systemexception.randomstuffinazip.pojo;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author leo
 * @date 24/07/15 22:06
 */
public class XmlValidator {

	private final Path xmlPath, xsdPath;

	public XmlValidator(String xmlPath, String xsdPath) {
		if (xmlPath.equals(null) || xsdPath.equals(null)) {
			IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
			throw new IllegalArgumentException("Null values not allowed");
		}
		this.xmlPath = Paths.get(xmlPath);
		this.xsdPath = Paths.get(xsdPath);
	}
}
