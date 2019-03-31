package game.racers.decorator;

import game.racers.IRacer;

public abstract class RacerDecorator implements IRacer  {

	protected IRacer decoratedRacer;
	/**/
	public RacerDecorator(IRacer racer) {
		this.decoratedRacer = racer;
	}
	
	public void introduce() {
		this.decoratedRacer.introduce();
	}
	public abstract void addAttribute(String key,Object value);
}
