package factory;

import game.arenas.Arena;
import game.arenas.air.AerialArena;
import game.arenas.land.LandArena;
import game.arenas.naval.NavalArena;

public class ArenaFactory {

	public Arena getArena(String arenaType) {
		Arena newArena = null;
		if(arenaType.equals("Land"))
			newArena = new LandArena();
		else if(arenaType.equals("Navy"))
			newArena = new NavalArena();
		else if(arenaType.equals("Air"))
			newArena = new AerialArena();
		return newArena;
	}
}