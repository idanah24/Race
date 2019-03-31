package factory;

import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.arenas.land.LandArena;
import game.racers.land.Car;

public class CarRaceBuilder {
	private LandArena carRace;
	
	public CarRaceBuilder(int n) {
		ArenaFactory factory = new ArenaFactory();
		Car car = new Car();
		
		carRace = (LandArena) factory.getArena("Land");
		
		for(int i = 0; i<n; i++) {
			try {
				carRace.addRacer(car);
			} catch (RacerTypeException | RacerLimitException e) {
				e.printStackTrace();
			}
			car = (Car) car.clone();
		}
	}
	
	
	public LandArena getRace() {
		return carRace;
	}
	
}
