package org.systemexception.randomstuffinazip.model;

/**
 * @author leo
 * @date 24/07/15 21:52
 */
public class Player {

	private final String name;
	private int points;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
