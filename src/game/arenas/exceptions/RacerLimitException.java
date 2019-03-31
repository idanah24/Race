package game.arenas.exceptions;
/**
 * This class handles an exception thrown in case of exceeding max racers limit
* @author Idan Aharon, Itay Fridman
* 			305437774	305360653
 * @see Exception class
 */
public class RacerLimitException extends Exception {

	private static final long serialVersionUID = 1L;

	public RacerLimitException(int maxRacers, int serialNumber) {
		super("Arena is full! (" + maxRacers + " active racers exist). racer #" + serialNumber + " was not added");
	}	
}