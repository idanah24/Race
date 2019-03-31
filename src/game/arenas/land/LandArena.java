package game.arenas.land;

import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.IRacer;
import game.racers.Racer;
import game.racers.land.LandRacer;
import utilities.EnumContainer.Coverage;
import utilities.EnumContainer.LandSurface;
/**
 * This is the Land Arena class
 * @author Idan Aharon, Itay Fridman
 * 			305437774	305360653
 * @see Arena class
 */
public class LandArena extends Arena {
	/**
	 * These are Land Arena data members
	 */
	private Coverage coverage;
	private LandSurface surface;
	
	/**
	 * These are class defaults
	 */
	private final static double DEFAULT_FRICTION = 0.5;
	private final static int DEFAULT_MAX_RACERS = 8;
	private final static double DEFAULT_LENGTH = 1500;
	private final static Coverage DEFAULT_COVERAGE = Coverage.GRASS;
	private final static LandSurface DEFAULT_LANDSURFACE = LandSurface.FLAT;
	
	/**
	 * This is the default constructor
	 */
	public LandArena() {
		super(DEFAULT_LENGTH, DEFAULT_MAX_RACERS, DEFAULT_FRICTION);
		this.setCoverage(DEFAULT_COVERAGE);
		this.setSurface(DEFAULT_LANDSURFACE);
	}
	
	/**
	 * This is the parameter based constructor
	 * @param length
	 * @param maxRacers
	 * @param friction
	 */
	public LandArena(double length, int maxRacers) {
		super(length, maxRacers, DEFAULT_FRICTION);
		this.setCoverage(DEFAULT_COVERAGE);
		this.setSurface(DEFAULT_LANDSURFACE);
	}

	 /**
	  * This function adds a given new racer
	  * @exception If racer type dosen't match arena or if max racers limit has been reached
	  */
	@Override
	public void addRacer(IRacer newRacer) throws RacerTypeException, RacerLimitException {
		super.addRacer(newRacer);
		Racer racer = (Racer)newRacer;
		if(!(racer instanceof LandRacer) && !(racer.getAttributes().containsKey("numOfWheels")))
			throw new RacerTypeException("Land Arena", racer.className());
		
		if(this.getNumOfRacers() == this.getMAX_RACERS())
			throw new RacerLimitException(this.getMAX_RACERS(), racer.getSerialNumber());
		
		this.activeRacers.add(racer);
		this.allRacers.add(racer);

	}
	
	/**
	 * The following are class getters & setters
	 */
	public Coverage getCoverage() {
		return coverage;
	}

	public boolean setCoverage(Coverage coverage) {
		this.coverage = coverage;
		return true;
	}

	public LandSurface getSurface() {
		return surface;
	}

	public boolean setSurface(LandSurface surface) {
		this.surface = surface;
		return true;
	}

	@Override
	public String className() {
		return "LandArena";
	}

}