package model.characters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Currency;

import engine.Game;
import exceptions.*;
import model.collectibles.*;

import model.world.*;


public abstract class Hero extends Character {

	private int actionsAvailable;
	private int maxActions;
	private boolean specialAction;
	private ArrayList<Vaccine> vaccineInventory;
	private ArrayList<Supply> supplyInventory;

	public Hero(String name, int maxHp, int attackDamage, int maxActions) {
		super(name, maxHp, attackDamage);
		this.maxActions = maxActions;
		this.actionsAvailable = maxActions;
		this.supplyInventory = new ArrayList<Supply>();
		this.vaccineInventory = new ArrayList<Vaccine>();
	}

	public int getActionsAvailable() {
		return actionsAvailable;
	}

	public void setActionsAvailable(int actionsAvailable) {
		this.actionsAvailable = actionsAvailable;
	}

	public boolean isSpecialAction() {
		return specialAction;
	}

	public void setSpecialAction(boolean specialAction) {
		this.specialAction = specialAction;
	}

	public int getMaxActions() {
		return maxActions;
	}

	public ArrayList<Vaccine> getVaccineInventory() {
		return vaccineInventory;
	}

	public ArrayList<Supply> getSupplyInventory() {
		return supplyInventory;
	}

	public boolean isAdjacent(Character c) {
		int x = c.getLocation().x;
		int y = c.getLocation().y;
		int tx = c.getTarget().getLocation().x;
		int ty = c.getTarget().getLocation().y;
		if (Math.abs(x - tx) <= 1 && Math.abs(y - ty) <= 1) {
			return true;
		}
		return false;
	}

	public void attack() throws InvalidTargetException, NotEnoughActionsException {
		if (this.getTarget() == null) {
			throw new InvalidTargetException("Invalid target");
		}
		if ( this.getTarget() instanceof Hero) {
			throw new InvalidTargetException("Invalid target");

		}
		if (isAdjacent(this) != true) {
			throw new InvalidTargetException("cannot attack");
		}
		
		if (this.actionsAvailable == 0&&!(this instanceof Fighter&&specialAction)) {
			throw new NotEnoughActionsException("no enough action point");
		}
		super.attack();
		if(!(this instanceof Fighter&&specialAction))
		
		this.actionsAvailable--;
		if(getCurrentHp()==0)
			onCharacterDeath();
	
	}
	public Point moveHelper(Direction d) throws MovementException, NotEnoughActionsException{
		if(actionsAvailable==0)
			throw new NotEnoughActionsException();
		int x = (int) this.getLocation().getX();
		int y = (int) this.getLocation().getY();
		int newX = x;
		int newY = y;
		switch(d) {
		case UP:newX++;break;
		case DOWN:newX--;break;
		case RIGHT:newY++;break;
		case LEFT:newY--;break;
		}
		if(newX<0||newX>14||newY<0||newY>14)
			throw new MovementException();
		if(Game.map[newX][newY] instanceof CharacterCell && ((CharacterCell)Game.map[newX][newY]).getCharacter()!=null)
			throw new MovementException();
		if(Game.map[newX][newY] instanceof CharacterCell && ((CharacterCell)Game.map[newX][newY]).getCharacter()==null) {
			((CharacterCell)Game.map[newX][newY]).setCharacter(this);
			((CharacterCell)Game.map[x][y]).setCharacter(null);
			//visible method
		}
		for(int i=newX-1;i<=1;i++) {
			for(int j=newX-1;j<=1;j++) {
			Game.map[i][j].setVisible(true);
		}
	}
		if (Game.map[newX][newY] instanceof TrapCell) {
			setCurrentHp(getCurrentHp()-((TrapCell)Game.map[newX][newY]).getTrapDamage());
		}
		if (Game.map[newX][newY] instanceof CollectibleCell) {
			((CollectibleCell)Game.map[newX][newY]).getCollectible().pickUp(this);
		}else
			throw new MovementException("");
		return new Point(newX,newY);
	}

	public void move(Direction d) throws MovementException, NotEnoughActionsException, ArrayIndexOutOfBoundsException {
		if(actionsAvailable==0)
			throw new NotEnoughActionsException();
		int x = (int) this.getLocation().getX();
		int y = (int) this.getLocation().getY();
		int newX = x;
		int newY = y;
		switch(d) {
		case UP:newX++;break;
		case DOWN:newX--;break;
		case RIGHT:newY++;break;
		case LEFT:newY--;break;
		}
		if(newX<0||newX>14||newY<0||newY>14)
			throw new MovementException();
		if(Game.map[newX][newY] instanceof CharacterCell && ((CharacterCell)Game.map[newX][newY]).getCharacter()!=null)
			throw new MovementException();
		if(Game.map[newX][newY] instanceof CharacterCell && ((CharacterCell)Game.map[newX][newY]).getCharacter()==null) {
			((CharacterCell)Game.map[newX][newY]).setCharacter(this);
			((CharacterCell)Game.map[x][y]).setCharacter(null);
			//visible method
		}
		for(int i=newX-1;i<=1;i++) {
			for(int j=newX-1;j<=1;j++) {
			Game.map[i][j].setVisible(true);
		}
	}
		if (Game.map[newX][newY] instanceof TrapCell) {
			setCurrentHp(getCurrentHp()-((TrapCell)Game.map[newX][newY]).getTrapDamage());
		}
		if (Game.map[newX][newY] instanceof CollectibleCell) {
			((CollectibleCell)Game.map[newX][newY]).getCollectible().pickUp(this);
		}
		Game.map[newX][newY]=new CharacterCell(this);
		((CharacterCell)Game.map[x][y]).setCharacter(null);
		this.setLocation(new Point(newX,newY));
		if(getCurrentHp()==0) {
			onCharacterDeath();
			return;
		}
		actionsAvailable--;
	}
	

	public void useSpecial() throws InvalidTargetException, NotEnoughActionsException, NoAvailableResourcesException {
		this.setSpecialAction(true);
		for (int i = 0; i < Game.heroes.size(); i++) {
			Hero h=Game.heroes.get(i);
		if (this.specialAction == true) {
			this.supplyInventory.remove(h);
		}
		}
		if(this.getSupplyInventory().size()==0|| this.actionsAvailable==0) {
			throw new NoAvailableResourcesException("blah");
		}
		else {
		(this.getSupplyInventory().get(0)).use(this);
		}
		
	}

	public void cure() throws InvalidTargetException, NotEnoughActionsException {
		this.actionsAvailable--;
		if(!(getTarget() instanceof Zombie)) {
			throw new InvalidTargetException();
		}
		if (isAdjacent(this) != true) {
			throw new InvalidTargetException("cannot attack");
		}
		if(	this.actionsAvailable==0) {
			throw new NotEnoughActionsException("sorry");
		}
	}
}