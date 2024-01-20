package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;

public class Medic extends Hero {

	public Medic(String name, int maxHp, int attackDamage, int maxActions) {
		super(name, maxHp, attackDamage, maxActions);
	}

	public void attack() throws InvalidTargetException, NotEnoughActionsException {

		super.attack();
	}

	public void useSpecial() throws InvalidTargetException {
	
		this.getTarget().setCurrentHp(getMaxHp());
	}

}
