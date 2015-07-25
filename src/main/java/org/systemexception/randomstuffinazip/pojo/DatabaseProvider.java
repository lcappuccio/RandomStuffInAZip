package org.systemexception.randomstuffinazip.pojo;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.systemexception.logger.api.Logger;
import org.systemexception.logger.impl.LoggerImpl;

import java.io.File;
import java.util.concurrent.ConcurrentNavigableMap;

/**
 * @author leo
 * @date 26/07/15 01:11
 */
public class DatabaseProvider {

	private final static Logger logger = LoggerImpl.getFor(DatabaseProvider.class);
	private final DB database;
	private ConcurrentNavigableMap<String,File> databaseMap;

	public DatabaseProvider() {
		database = DBMaker.memoryDB().make();
		databaseMap = database.treeMap("matchCollection");
	}

	public void addRecords(String recordName, File file) {
		logger.info("Added record " + recordName);
		databaseMap.put(recordName, file);
		database.commit();
	}

	public File getRecord(String recordName) {
		logger.info("Fetched record " + recordName);
		return databaseMap.get(recordName);
	}
}
