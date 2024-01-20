package model.characters;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;

public class Explorer extends Hero {

	public Explorer(String name, int maxHp, int attackDamage, int maxActions) {
		super(name, maxHp, attackDamage, maxActions);
	}

	public void attack() throws InvalidTargetException, NotEnoughActionsException {
		super.attack();
		int a = getActionsAvailable() - 1;
	}

	public void useSpecial() throws InvalidTargetException, NotEnoughActionsException, NoAvailableResourcesException {
		super.useSpecial();
	
		for (int i = 0; i < Game.map.length; i++) {
			for (int j = 0; j < Game.map.length; i++) {
				Game.map[i][j].setVisible(true);
			}
		}
	}
}
