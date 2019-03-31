package game.racers.air;
import game.racers.Racer;
/**
 * This is the Helicopter racer class
 * @author Idan Aharon, Itay Fridman
 * 			 305437774 305360653
 * @see AerialRacer, Racer
 */
public class Helicopter extends Racer implements AerialRacer {
	/**
	 * These are class defaults
	 */
	private final static double DEFAULT_MAXSPEED = 400;
	private final static double DEFAULT_ACCLERATION = 50;	
	
	/**
	 * The following are Airplane class constructors
	 * This is the default class constructor
	 */
	public Helicopter() {
		super(null, DEFAULT_MAXSPEED, DEFAULT_ACCLERATION);
	}
	
	/**
	 * This is the parameter based class constructor
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 * @param color
	 */
	public Helicopter(String name, double maxSpeed, double acceleration) {
		super(name, maxSpeed, acceleration);
		if(!(this.setMaxSpeed(maxSpeed)))
			this.setMaxSpeed(DEFAULT_MAXSPEED);
		if(!(this.setAcceleration(acceleration)))
			this.setAcceleration(DEFAULT_ACCLERATION);
	}

	/**
	 * This function returns a string with this class name
	 */
	@Override
	public String className() {
		return "Helicopter";
	}
}
