package model.collectibles;

import exceptions.NoAvailableResourcesException;
import model.characters.Hero;

public class Vaccine implements Collectible {

	public Vaccine() {
	}

	public void pickUp(Hero h) {
		h.getVaccineInventory().add(this);
	}

	public void use(Hero h) throws NoAvailableResourcesException {
		for (Collectible a : h.getVaccineInventory()) {
			h.getVaccineInventory().remove(a);
		if (h.getVaccineInventory().size()==0) {
		throw new NoAvailableResourcesException("no resources");
		}
	}

}}