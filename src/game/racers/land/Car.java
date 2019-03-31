package game.racers.land;

import game.racers.Racer;
import utilities.EnumContainer.Engine;

/**
 * This is the Car racer class
 * @author Idan Aharon, Itay Fridman
 * 			 305437774 305360653
 * @see Racer, LandRacer, Wheeled
 */
public class Car extends Racer implements LandRacer {	
	/**
	 * These are class defaults
	 */
	private final static double DEFAULT_MAXSPEED = 400;
	private final static double DEFAULT_ACCLERATION = 20;
	private final static Engine DEFAULT_ENGINE = Engine.FOURSTROKE;
	
	/**
	 * This is the default class constructor
	 */
	public Car() {
		super(null, DEFAULT_MAXSPEED, DEFAULT_ACCLERATION);
		this.addAttribute("engine", DEFAULT_ENGINE);
	}
	
	/**
	 * This is the parameter based class constructor
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 */
	public Car(String name, double maxSpeed, double acceleration) {
		super(name, maxSpeed, acceleration);
		this.addAttribute("engine", DEFAULT_ENGINE);
		if(!(this.setMaxSpeed(maxSpeed)))
			this.setMaxSpeed(DEFAULT_MAXSPEED);
		if(!(this.setAcceleration(acceleration)))
			this.setAcceleration(DEFAULT_ACCLERATION);
	}

	@Override
	public String className() {
		return "Car";
	}
}