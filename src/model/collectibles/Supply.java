package model.collectibles;

import java.util.ArrayList;

import exceptions.NoAvailableResourcesException;
import model.characters.Hero;

public class Supply implements Collectible {

	public Supply() {
	}

	public void pickUp(Hero h) {
		h.getSupplyInventory().add(this);
	}

	public void use(Hero h) throws NoAvailableResourcesException {
		for (Collectible a : h.getSupplyInventory()) {
			h.getSupplyInventory().remove(a);
		}
		if (h.getVaccineInventory().isEmpty()) {
		throw new NoAvailableResourcesException("no resources");
		}
	}

}
