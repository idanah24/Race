package game.racers.decorator;
import game.racers.IRacer;
import utilities.EnumContainer.Color;

/**
 * This is the ColoredRacer class
 * @author Idan Aharon, Itay Fridman
 * 			 305437774 305360653
 * @see Racer, IRacer, RacerDecorator
 */
public class ColoredRacer extends RacerDecorator {

	private final String ATTRIBUTENAME = "color";
	
	
	public ColoredRacer(IRacer racer, Color color) {
		super(racer);
		addAttribute(ATTRIBUTENAME,color);
	}

	public void addAttribute(String key,Object value) {
		this.decoratedRacer.addAttribute(key, value);
	}
}
