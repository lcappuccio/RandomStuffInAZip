package org.systemexception.randomstuffinazip.pojo;

import org.systemexception.logger.api.Logger;
import org.systemexception.logger.impl.LoggerImpl;
import org.systemexception.randomstuffinazip.exception.ErrorCodes;

/**
 * @author leo
 * @date 25/07/15 23:46
 */
public class ZipCompressor {

	private final static Logger logger = LoggerImpl.getFor(ZipCompressor.class);
	private final String fileName, fileContents;

	public ZipCompressor(final String fileContents, final String fileName) {
		if (fileName == null) {
			IllegalArgumentException illegalArgumentException = new IllegalArgumentException(ErrorCodes
					.MISSING_FILENAME.toString());
			logger.error(illegalArgumentException.getMessage(), illegalArgumentException);
			throw illegalArgumentException;
		}
		this.fileName = fileName;
		this.fileContents = fileContents;
	}
}
