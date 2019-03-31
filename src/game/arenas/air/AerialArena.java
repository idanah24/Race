package game.arenas.air;

import game.arenas.Arena;
import game.arenas.exceptions.*;
import game.racers.*;
import game.racers.air.*;
import utilities.EnumContainer.Height;
import utilities.EnumContainer.Vision;
import utilities.EnumContainer.Weather;
import utilities.EnumContainer.Wind;
/**
 * This is the AerialArena class
 * @see Arena class
 * @author Idan Aharon, Itay Fridman
 * 			305437774	305360653
 */
public class AerialArena extends Arena {
	/**
	 * These are the Aerial Arena data members
	 */
	private Vision vision;
	private Weather weather;
	private Height height;
	private Wind wind;
	
	/**
	 * These are class defaults
	 */
	private final static double DEFAULT_FRICTION = 0.4;
	private final static int DEFAULT_MAX_RACERS = 6;
	private final static double DEFAULT_LENGTH = 1500;
	private final static Vision DEFAULT_VISION = Vision.SUNNY;
	private final static Weather DEFAULT_WEATHER = Weather.DRY;
	private final static Height DEFAULT_HEIGHT = Height.HIGH;
	private final static Wind DEFAULT_WIND = Wind.HIGH;
	
	
	/**
	 * The following are Aerial Arena class constructors
	 * The first is the default constructor
	 */
	public AerialArena() {
		super(DEFAULT_LENGTH, DEFAULT_MAX_RACERS, DEFAULT_FRICTION);
		this.setVision(DEFAULT_VISION);
		this.setWeather(DEFAULT_WEATHER);
		this.setHeight(DEFAULT_HEIGHT);
		this.setWind(DEFAULT_WIND);
	}
	
	/**
	 * This constructor builds an AerialArena instance through given parameters
	 * @param length
	 * @param maxRacers
	 */
	public AerialArena(double length, int maxRacers){
		super(length, maxRacers, DEFAULT_FRICTION);
		if(!(this.setLength(length)))
			this.setLength(DEFAULT_LENGTH);
		this.setVision(DEFAULT_VISION);
		this.setWeather(DEFAULT_WEATHER);
		this.setHeight(DEFAULT_HEIGHT);
		this.setWind(DEFAULT_WIND);
	}
	
	 /**
	  * This function adds a given new racer
	  * @exception If racer type dosen't match arena or if max racers limit has been reached
	  */
	@Override
	public void addRacer(IRacer newRacer) throws RacerTypeException, RacerLimitException {
		super.addRacer(newRacer);
		Racer racer = (Racer)newRacer;
		if(!(racer instanceof AerialRacer))
			throw new RacerTypeException("Aerial Arena", racer.className());
		
		if(this.getNumOfRacers() == this.getMAX_RACERS())
			throw new RacerLimitException(this.getMAX_RACERS(), racer.getSerialNumber());
		
		this.activeRacers.add(racer);
		this.allRacers.add(racer);
	}

	/**
	 * The following are the AerialArena class getters & setters
	 * @return
	 */
	public Vision getVision() {
		return vision;
	}

	public boolean setVision(Vision vision) {
		this.vision = vision;
		return true;
	}

	public Weather getWeather() {
		return weather;
	}

	public boolean setWeather(Weather weather) {
		this.weather = weather;
		return true;
	}

	public Height getHeight() {
		return height;
	}

	public boolean setHeight(Height height) {
		this.height = height;
		return true;
	}

	public Wind getWind() {
		return wind;
	}

	public boolean setWind(Wind wind) {
		this.wind = wind;
		return true;
	}

	@Override
	public String className() {
		return "AerialArena";
	}	
	
}