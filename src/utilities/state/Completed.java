package utilities.state;

import game.racers.Racer;

public class Completed implements IState {
	@Override
	public void action(Racer racer) {
		if(!racer.getArena().getCompletedRacers().contains(racer))
			racer.getArena().getCompletedRacers().add((Racer)racer);
		racer.getArena().getActiveRacers().remove((Racer) racer);
		System.out.println(racer.getName() + " has finished!");
	}
}
