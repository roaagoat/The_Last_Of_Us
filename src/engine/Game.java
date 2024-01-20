package engine;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.characters.Character;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public class Game {

	public static ArrayList<Hero> availableHeroes;
	public static ArrayList<Hero> heroes = new ArrayList<Hero>();
	public static ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	public static Cell[][] map = new Cell[15][15];

	public static void loadHeroes(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		while (line != null) {
			String[] sp = line.split(",");
			Hero h;
			if (sp[1].equals("EXP")) {
				h = new Explorer(sp[0], Integer.parseInt(sp[2]), Integer.parseInt(sp[4]), Integer.parseInt(sp[3]));
			} else if (sp[1].equals("FIGH")) {
				h = new Fighter(sp[0], Integer.parseInt(sp[2]), Integer.parseInt(sp[4]), Integer.parseInt(sp[3]));
			} else {
				h = new Medic(sp[0], Integer.parseInt(sp[2]), Integer.parseInt(sp[4]), Integer.parseInt(sp[3]));
			}
			availableHeroes.add(h);
			line = br.readLine();
		}
		br.close();

	}
	public static int generaterandom() {
		int n=(int) (Math.random()*15);
		return n;
	}
	public static void startGame(Hero h) {
		//loadHeroes(Heroes.csv);
		for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++)
			{
				map[i][j]=new CharacterCell(null);
			}
		}
		map[0][0] = new CharacterCell(h);
		availableHeroes.remove(h);
		heroes.add(h);
		h.setLocation(new Point(0, 0));
	   ArrayList<Point> adj = h.adjacentCells(new Point(0,0));
	   adj.add(new Point(0,0));
	   for(int i = 0; i< adj.size(); i++) {
		   map[adj.get(i).x][adj.get(i).y].setVisible(true);   
	   }
		int x;
		int y;
		for(int i=0;i<5;i++) {
			x=generaterandom();
			y=generaterandom();
			while(!((Game.map[x][y] instanceof CharacterCell)&&((CharacterCell)map[x][y]).getCharacter()==null )) {
			    x=generaterandom();
				y=generaterandom();
		   }
			map[x][y]=new CollectibleCell(new Vaccine());
		}
		for(int i=0;i<5;i++) {
			x=generaterandom();
			y=generaterandom();
			while(!((Game.map[x][y] instanceof CharacterCell)&&((CharacterCell)map[x][y]).getCharacter()==null )) {
			    x=generaterandom();
				y=generaterandom();
		   }
			map[x][y]=new CollectibleCell(new Supply());
		}
		for(int i=0;i<5;i++) {
			 x=generaterandom();
			 y=generaterandom();
			while(!((Game.map[x][y] instanceof CharacterCell)&&((CharacterCell)map[x][y]).getCharacter()==null )) {
		    x=generaterandom();
			y=generaterandom();
		   }
			map[x][y]=new TrapCell();
		}
		for(int i=0;i<10;i++) {
			 x=generaterandom();
			y=generaterandom();
			while(!((Game.map[x][y] instanceof CharacterCell)&&((CharacterCell)map[x][y]).getCharacter()==null )) {
			    x=generaterandom();
				y=generaterandom();
		   }
			Zombie e=new Zombie();
			map[x][y]=new CharacterCell(e);
			e.setLocation(new Point(x,y));
			zombies.add(e);
		}
	}
	
	public static boolean checkWin() {
		if (heroes.size()<5)
			return false;
		
		for(int i=0;i<map.length;i++) {
				for(int j=0;j<map.length;j++) {
					if(map[i][j] instanceof CollectibleCell) {
						if(((CollectibleCell) (map[i][j])).getCollectible() instanceof Vaccine) {
							return false;
						}
					}
				}
		}
		for (int i = 0; i < heroes.size(); i++) {
			if(heroes.get(i).getVaccineInventory().size() !=0) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkGameOver() {
		if (heroes.size() == 0) {
			return true;
		}
		return false;

	}
	public static boolean isAdjacentz(Zombie c) throws InvalidTargetException, NotEnoughActionsException {
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length;j++) {
				int tx = c.getLocation().x;
				int ty = c.getLocation().y;
				if (Math.abs(i - tx) <= 1 && Math.abs(j - ty) <= 1) {
					if((map[i][j])instanceof CharacterCell&&((CharacterCell)(map[i][j])).getCharacter() instanceof Hero)
						c.setTarget(((CharacterCell)(map[i][j])).getCharacter());
						return true;
				}
			}
		}
		return false;
	}
	
	public static void endTurn() throws InvalidTargetException, NotEnoughActionsException {
		for(int k=0;k<map.length;k++) {
			for(int j=0;j<map.length;j++) {
				map[k][j].setVisible(false);
			}
		}
		for (int i = 0; i < zombies.size(); i++) {
			if(isAdjacentz(zombies.get(i))) {
				zombies.get(i).attack();
				zombies.get(i).setTarget(null);
				
			}
		}
		for (int i = 0; i < heroes.size(); i++) {
			Hero h=heroes.get(i);
			h.setActionsAvailable(h.getMaxActions());
			h.setTarget(null);
			h.setSpecialAction(false);
			
			for(int k=0;k<map.length;k++) {
				for(int j=0;j<map.length;j++) {
					int x = h.getLocation().x;
					int y = h.getLocation().y;
					if ((k <= x + 1 && j <= y + 1) && (k >= x - 1 && j >= y - 1)) {
						map[k][j].setVisible(true);
					}
					}
		}
			
		}
		int x=generaterandom();
		int y=generaterandom();
		while(!(map[x][y]instanceof CharacterCell&&(((CharacterCell)map[x][y]).getCharacter()==null))) {
			x=generaterandom();
			y=generaterandom();
		}
		Zombie e=new Zombie();
		e.setLocation(new Point(x,y));
		map[x][y]=new CharacterCell(e);
		zombies.add(e);
	}
	
}
	
