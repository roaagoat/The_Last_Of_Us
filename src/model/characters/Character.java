package model.characters;

import java.util.*;
import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.Hero;

import java.io.*;
import java.awt.Point;
import java.util.ArrayList;

import model.world.Cell;
import model.world.CharacterCell;

public abstract class Character {

	private String name;
	private int maxHp;
	private int currentHp;
	private Point location;
	private int attackDmg;
	private Character target;

	public Character(String name, int maxHp, int attackDamage) {
		this.name = name;
		this.maxHp = maxHp;
		this.attackDmg = attackDamage;
		this.currentHp = maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		this.currentHp = (currentHp < 0) ? 0 : (currentHp > maxHp) ? maxHp : currentHp;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public Character getTarget() {
		return target;
	}

	public void setTarget(Character target) {
		this.target = target;
	}

	public String getName() {
		return name;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getAttackDmg() {
		return attackDmg;
	}

	public boolean isAdjacent(Character c) {
		int x = this.getLocation().x;
		int y = this.getLocation().y;
		int tx = this.getTarget().getLocation().x;
		int ty = this.getTarget().getLocation().y;
		if (Math.abs(x - tx) <= 1 && Math.abs(y - ty) <= 1) {
			return true;
		}
		return false;
	}

	public  ArrayList<Point> adjacentCells (Point p){
		ArrayList<Point> adjacent = new ArrayList<Point>();
		for(int i=0;i<Game.map.length;i++) {
			for(int j=0;j<Game.map.length;j++) {
				int tx = (int) p.getX();
				int ty = (int) p.getY();
				if (Math.abs(i - tx) <= 1 && Math.abs(j - ty) <= 1) {
					adjacent.add(new Point(i,j));
				}
				}
		}
		return adjacent;
	}

	public void attack() throws InvalidTargetException, NotEnoughActionsException {
		if (this.getTarget() == null) {
			throw new InvalidTargetException("Invalid target");
		}
		this.getTarget().setCurrentHp(this.getTarget().getCurrentHp()-attackDmg);
		this.defend(target);
		if (this.target.getCurrentHp() == 0) {
			target.onCharacterDeath();
		}
	}

	public void defend(Character c) {
		this.setCurrentHp(this.currentHp-(c.attackDmg/2)); 
	}

	public void onCharacterDeath() {
		if (this.currentHp == 0) {
	
		}
			if(this instanceof Hero) {
				int i = getLocation().x;
				int j = getLocation().y;
				((CharacterCell)Game.map[i][j]).setCharacter(null);
				Game.heroes.remove(this);
			
			}
			else {
				int i = getLocation().x;
				int j = getLocation().y;
				((CharacterCell)Game.map[i][j]).setCharacter(null);
					Game.zombies.remove(this);
			}
			}
		}
	


