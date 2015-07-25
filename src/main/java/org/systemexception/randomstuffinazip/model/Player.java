package org.systemexception.randomstuffinazip.model;

/**
 * @author leo
 * @date 24/07/15 21:52
 */
public class Player {

	private final String name;
	private final int points;

	public Player(final String name, final int points) {
		this.name = name;
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}
}
