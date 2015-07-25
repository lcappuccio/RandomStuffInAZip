package org.systemexception.randomstuffinazip.exception;

/**
 * @author leo
 * @date 25/07/15 03:53
 */
public enum ErrorCodes {

	NULL_PLAYER_NAME("Null player name"),
	NEGATIVE_POINTS("Negative points not allowed"),
	MISSING_FILENAME("No filename provided");

	private final String errorCode;

	ErrorCodes(String errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		return errorCode;
	}
}
