package game.arenas.naval;


import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.IRacer;
import game.racers.Racer;
import game.racers.naval.NavalRacer;
import utilities.EnumContainer.Body;
import utilities.EnumContainer.Water;
import utilities.EnumContainer.WaterSurface;
/**
 * This is the Naval Arena class
 * @author Idan Aharon, Itay Fridman
 * 			305437774	305360653
 * @see Arena class
 */
public class NavalArena extends Arena {
	/**
	 * These are the Naval Arena data members
	 */
	private Water water;
	private WaterSurface surface;
	private Body body;
	
	/**
	 * These are the class defaults
	 */
	private final static double DEFAULT_FRICTION = 0.7;
	private final static int DEFAULT_MAX_RACERS = 5;
	private final static double DEFAULT_LENGTH = 1000;
	private final static Water DEFAULT_WATER = Water.SWEET;
	private final static WaterSurface DEFAULT_WATERSURFACE = WaterSurface.FLAT;
	private final static Body DEFAULT_BODY = Body.LAKE;
	
	/**
	 * This is the default constructor
	 */
	public NavalArena() {
		super(DEFAULT_LENGTH, DEFAULT_MAX_RACERS, DEFAULT_FRICTION);
		this.setWater(DEFAULT_WATER);
		this.setSurface(DEFAULT_WATERSURFACE);
		this.setBody(DEFAULT_BODY);
	}
	
	/**
	 * This is the parameter based constructor
	 * @param length
	 * @param maxRacers
	 * @param friction
	 */
	public NavalArena(double length, int maxRacers) {
		super(length, maxRacers, DEFAULT_FRICTION);
		this.setWater(DEFAULT_WATER);
		this.setSurface(DEFAULT_WATERSURFACE);
		this.setBody(DEFAULT_BODY);
	}
	
	 /**
	  * This function adds a given new racer
	  * @exception If racer type dosen't match arena or if max racers limit has been reached
	  */
	@Override
	public void addRacer(IRacer newRacer) throws RacerTypeException, RacerLimitException {
		super.addRacer(newRacer);
		Racer racer = (Racer)newRacer;
		if(!(racer instanceof NavalRacer))
			throw new RacerTypeException("Naval Arena", racer.className());
		
		if(this.getNumOfRacers() == this.getMAX_RACERS())
			throw new RacerLimitException(this.getMAX_RACERS(), racer.getSerialNumber());
		
		this.activeRacers.add(racer);
		this.allRacers.add(racer);
	}

	/**
	 * This is the class getters & setters
	 */
	public Water getWater() {
		return water;
	}

	public boolean setWater(Water water) {
		this.water = water;
		return true;
	}

	public WaterSurface getSurface() {
		return surface;
	}

	public boolean setSurface(WaterSurface surface) {
		this.surface = surface;
		return true;
	}

	public Body getBody() {
		return body;
	}

	public boolean setBody(Body body) {
		this.body = body;
		return true;
	}

	@Override
	public String className() {
		return "NavalArena";
	}

}