package utilities.state;

import game.racers.Racer;

public class Active implements IState {

	@Override
	public void action(Racer racer) {
		int location = racer.getArena().getAllRacers().lastIndexOf(racer);
		
		if(!racer.getArena().getActiveRacers().contains((Racer)racer))
			racer.getArena().getActiveRacers().add(((Racer)racer));
		racer.getArena().getBrokenRacers().remove(((Racer)racer));
		
		System.out.println(racer.getName() + " is active again, in place: #" + (location + 1));
	}

}
