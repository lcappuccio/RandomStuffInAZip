package org.systemexception.randomstuffinazip.test;


import org.junit.Test;
import org.systemexception.randomstuffinazip.model.Player;

/**
 * @author leo
 * @date 25/07/15 04:07
 */
public class PlayerTest {

	private Player sut;

	@Test(expected = IllegalArgumentException.class)
	public void will_not_accept_null_names() {
		sut = new Player(null,10);
	}

}