package org.systemexception.randomstuffinazip.pojo;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.systemexception.logger.api.Logger;
import org.systemexception.logger.impl.LoggerImpl;

import java.io.File;
import java.util.ArrayList;

/**
 * @author leo
 * @date 26/07/15 01:11
 */
public class DatabaseProvider {

	private final static Logger logger = LoggerImpl.getFor(DatabaseProvider.class);
	private final DB database;
	private HTreeMap<String, File> databaseMap;

	public DatabaseProvider() {
		database = DBMaker.fileDB(new File("target/database.db")).closeOnJvmShutdown().make();
		databaseMap = database.hashMap("matchCollection");
	}

	/**
	 * Add a record to the database
	 *
	 * @param recordName
	 * @param file
	 */
	public void addRecords(String recordName, File file) {
		logger.info("Added record " + recordName);
		databaseMap.put(recordName, file);
		database.commit();
		file.delete();
	}

	/**
	 * Fetch a file record from the database
	 *
	 * @param recordName
	 * @return
	 */
	public File getRecord(String recordName) {
		logger.info("Fetched record " + recordName);
		return databaseMap.get(recordName);
	}

	/**
	 * Get all recordIds stored in dabase
	 *
	 * @return
	 */
	public ArrayList<String> getAllStoredRecordIds() {
		ArrayList<String> records = new ArrayList<>();
		for (String recordId : databaseMap.keySet()) {
			records.add(recordId);
		}
		return records;
	}

	/**
	 * Compact database manually
	 */
	public void databaseCompact() {
		database.compact();
		database.commit();
		logger.info("Database compacted");
	}

	/**
	 * Count all items on database
	 *
	 * @return
	 */
	public int countItems() {
		logger.info("Database item count: " + databaseMap.size());
		return databaseMap.size();
	}
}
