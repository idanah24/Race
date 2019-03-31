package utilities.state;

import game.racers.Racer;

public class Disabled implements IState {

	@Override
	public void action(Racer racer) {
		if(!racer.getArena().getDisabledRacers().contains((Racer)racer))
			racer.getArena().getDisabledRacers().add((Racer)racer);
		racer.getArena().getActiveRacers().remove(((Racer)racer));
		System.out.println(racer.getName() + " failed to finish");
	}

}
