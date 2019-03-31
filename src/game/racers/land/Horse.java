package game.racers.land;
import game.racers.Racer;
import utilities.EnumContainer.Breed;

/**
 * This is the Horse racer class
 * @author Idan Aharon, Itay Fridman
 * 			 305437774 305360653
 * @see Racer, LandRacer
 */
public class Horse extends Racer implements LandRacer {
	/**
	 * These are class defaults
	 */
	private final static double DEFAULT_MAXSPEED = 50;
	private final static double DEFAULT_ACCLERATION = 3;
	private final static Breed DEFAULT_BREED = Breed.THOROUGHBRED;
	
	/**
	 * This is the parameter base constructor
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 */
	public Horse(String name, double maxSpeed, double acceleration) {
		super(name, maxSpeed, acceleration);
		if(!(this.setMaxSpeed(maxSpeed)))
			this.setMaxSpeed(DEFAULT_MAXSPEED);
		if(!(this.setAcceleration(acceleration)))
			this.setAcceleration(DEFAULT_ACCLERATION);
		this.addAttribute("breed", DEFAULT_BREED);
	}
	
	/**
	 * This is the default constructor
	 */
	public Horse() {
		super(null, DEFAULT_MAXSPEED, DEFAULT_ACCLERATION);
		this.addAttribute("breed", DEFAULT_BREED);
	}
	
	@Override
	public String className() {
		return "Horse";
	}
}