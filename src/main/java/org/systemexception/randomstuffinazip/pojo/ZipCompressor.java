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
	private final String fileName, fileContents;

	public ZipCompressor(final String fileContents, final String fileName) {
		if (fileName == null) {
			IllegalArgumentException illegalArgumentException = new IllegalArgumentException(ErrorCodes
					.MISSING_FILENAME.toString());
			logger.error(illegalArgumentException.getMessage(), illegalArgumentException);
			throw illegalArgumentException;
		}
		this.fileName = fileName + ".zip";
		this.fileContents = fileContents;
	}

	/**
	 * Zips the contents into the file
	 *
	 * @throws IOException
	 */
	public void zipContents() throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream("target" + File.separator + fileName);
		ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
		ZipEntry zipEntry = new ZipEntry(fileName + ".xml");
		zipOutputStream.putNextEntry(zipEntry);
		InputStream inputStream = new ByteArrayInputStream(fileContents.getBytes());
		byte[] data = new byte[1024];
		int byteCount;
		while ((byteCount = (inputStream.read(data))) > 0) {
			zipOutputStream.write(data, 0, byteCount);
		}
		zipOutputStream.flush();
		zipOutputStream.closeEntry();
		inputStream.close();
		zipOutputStream.close();
		logger.info("Saved file " + fileName);
	}
}
