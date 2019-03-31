package game.racers.land;

import game.racers.Racer;
import utilities.EnumContainer.BikeType;

/**
 * This is the Bicycle racer class
 * @author Idan Aharon, Itay Fridman
 * 			 305437774 305360653
 * @see Racer, LandRacer
 */
public class Bicycle extends Racer implements LandRacer {
	/**
	 * These are class defaults
	 */
	private final static double DEFAULT_MAXSPEED = 270;
	private final static double DEFAULT_ACCLERATION = 10;

	private final static BikeType DEFAULT_BIKETYPE = BikeType.MOUNTAIN;
	
	/**
	 * This is the class parameter based constructor
	 * @param name
	 * @param maxSpeed
	 * @param acceleration

	 */
	public Bicycle(String name, double maxSpeed, double acceleration) {
		super(name, maxSpeed, acceleration);
		this.addAttribute("type", DEFAULT_BIKETYPE);
		if(!(this.setMaxSpeed(maxSpeed)))
			this.setMaxSpeed(DEFAULT_MAXSPEED);
		if(!(this.setAcceleration(acceleration)))
			this.setAcceleration(DEFAULT_ACCLERATION);
	}

	/**
	 * This is the default constructor
	 */
	public Bicycle() {
		super(null, DEFAULT_MAXSPEED, DEFAULT_ACCLERATION);
		this.addAttribute("type", DEFAULT_BIKETYPE);
	}

	@Override
	public String className() {
		return "Bicycle";
	}
}