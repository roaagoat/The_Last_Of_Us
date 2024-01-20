package model.characters;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.CharacterCell;

import java.awt.Point;
import java.util.*;

public class Zombie extends Character {

	static int ZOMBIES_COUNT;

	public Zombie() {
		super("Zombie " + ++ZOMBIES_COUNT, 40, 10);
	}

	public void attack() throws InvalidTargetException, NotEnoughActionsException {
		Game.isAdjacentz(this);
		if(this.getTarget()!=null)
		super.attack();
		if(this.getCurrentHp()==0)
			onCharacterDeath();

	}
	public static int generaterandomz() {
		int n=(int) (Math.random()*15);
		return n;
	}

	public void onCharacterDeath() {
		super.onCharacterDeath();
		int x=generaterandomz();
		int y=generaterandomz();
		while(!(Game.map[x][y]instanceof CharacterCell&&(((CharacterCell)Game.map[x][y]).getCharacter()==null))) {
			x=generaterandomz();
			y=generaterandomz();
		}
		Zombie e=new Zombie();
		e.setLocation(new Point(x,y));
		Game.map[x][y]=new CharacterCell(e);
		Game.zombies.add(e);
	}

}
