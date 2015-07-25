package org.systemexception.randomstuffinazip.model;

import org.systemexception.logger.api.Logger;
import org.systemexception.logger.impl.LoggerImpl;
import org.systemexception.randomstuffinazip.exception.ErrorCodes;

/**
 * @author leo
 * @date 24/07/15 21:52
 */
public class Player {

	private final static Logger logger = LoggerImpl.getFor(Player.class);
	private final String name;
	private final int points;

	public Player(final String name, final int points) {
		if (name == null) {
			exceptionHandler(ErrorCodes.NULL_PLAYER_NAME.toString());
		}
		if (points < 0) {
			exceptionHandler(ErrorCodes.NEGATIVE_POINTS.toString());
		}
		this.name = name;
		this.points = points;
		logger.info("New player: " + name + ", points: " + points);
	}

	private void exceptionHandler(String exceptionMessage) {
		IllegalArgumentException illegalArgumentException = new IllegalArgumentException(exceptionMessage);
		logger.error(illegalArgumentException.getMessage(), illegalArgumentException);
		throw illegalArgumentException;
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}
}
