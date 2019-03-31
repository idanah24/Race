package game.arenas.exceptions;
/**
 * This class handles the exception in case of wrong racer type
 * @author Idan Aharon, Itay Fridman
 * 			305437774	305360653
 * @see Exception class
 */
public class RacerTypeException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public RacerTypeException(String arenaType, String racerType) {
		super("Invalid Racer of type '" + racerType + "' for " + arenaType);
	}
}