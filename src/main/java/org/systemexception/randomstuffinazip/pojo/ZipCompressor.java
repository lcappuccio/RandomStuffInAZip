package org.systemexception.randomstuffinazip.pojo;

import org.systemexception.logger.api.Logger;
import org.systemexception.logger.impl.LoggerImpl;
import org.systemexception.randomstuffinazip.exception.ErrorCodes;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author leo
 * @date 25/07/15 23:46
 */
public class ZipCompressor {

	private final static Logger logger = LoggerImpl.getFor(ZipCompressor.class);
	private final String zipFileName;

	public ZipCompressor(final String zipFileName) {
		if (zipFileName == null) {
			IllegalArgumentException illegalArgumentException = new IllegalArgumentException(ErrorCodes
					.MISSING_FILENAME.toString());
			logger.error(illegalArgumentException.getMessage(), illegalArgumentException);
			throw illegalArgumentException;
		}
		this.zipFileName = zipFileName + ".zip";
	}

	/**
	 * Zips the contents into the file
	 *
	 * @throws IOException
	 */
	public void addFileToZip(final File fileToZip) throws IOException {
		byte[] buffer = new byte[1024];
		FileOutputStream fos = new FileOutputStream(zipFileName);
		ZipOutputStream zipOutput = new ZipOutputStream(fos);
		ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
		zipOutput.putNextEntry(zipEntry);
		try (FileInputStream in = new FileInputStream(fileToZip)) {
			int len;
			while ((len = in.read(buffer)) > 0) {
				zipOutput.write(buffer, 0, len);
			}
		}
		zipOutput.closeEntry();
		zipOutput.close();
		fileToZip.delete();
		logger.info("Saved file " + zipFileName);
	}
}
