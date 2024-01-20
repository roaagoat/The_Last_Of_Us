package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;

public class Fighter extends Hero {

	public Fighter(String name, int maxHp, int attackDamage, int maxActions) {
		super(name, maxHp, attackDamage, maxActions);
	}

	public void attack() throws InvalidTargetException, NotEnoughActionsException {
		super.attack();

	}

	public void useSpecial() throws InvalidTargetException {
		if (this.getMaxHp() == 0)
			throw new InvalidTargetException("no hp");

	}
}
