package me.jewsofhazzard.pcmrbot.Commands;

import me.jewsofhazzard.pcmrbot.twitch.TwitchUtilities;

public class Commercial implements Command {

	@Override
	public String execute(String... parameters) {
		String channel=parameters[0];
		if(parameters.length<2) {
			TwitchUtilities.runCommercial(channel.substring(1));
			return "Running a default length commercial on %channel%".replace("%channel%", channel);
		}
		String length=parameters[1];
		int time = 0;
		try {
			time = Integer.valueOf(length);
		} catch (NumberFormatException e) {
			TwitchUtilities.runCommercial(channel.substring(1));
			return	String.format("%s is not a valid time. Running a default length commercial on %s!", length, channel);
		}
		if (time <= 180 && time % 30 == 0) {
			TwitchUtilities.runCommercial(channel.substring(1), time);
			return String.format("Running a %s for %length% seconds on %s", length, channel);
		} else {
			TwitchUtilities.runCommercial(channel.substring(1));
			return	String.format("%s is not a valid time. Running a default length commercial on %s!", length, channel);
		}
	}

}