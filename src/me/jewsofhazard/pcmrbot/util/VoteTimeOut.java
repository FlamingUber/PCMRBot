package me.jewsofhazard.pcmrbot.util;

import java.util.ArrayList;

import me.jewsofhazard.pcmrbot.MyBotMain;

public class VoteTimeOut {

	private String channel;
	private String kickee;
	private ArrayList<String> kickers;
	
	public VoteTimeOut(String c, String k) {
		channel = c;
		kickee = k;
		kickers= new ArrayList<>();
		new Timer(channel, 120, this);
	}

	public void count() {

		if (((double)kickers.size() / MyBotMain.getBot().getUsers(channel).length) >= .55) {

			MyBotMain.getBot().sendMessage(channel, "The community has chosen to kick %out%.".replace("%out%", kickee));

		} else {

			MyBotMain.getBot().sendMessage(channel, "The community has chosen to spare %safe%.".replace("%safe%", kickee));

		}

	}

	public String addVote(String sender) {
		if (kickers.contains(sender)) {
			return String.format("%s tried to kick %s twice. Do they have a personal vendetta? That is for me to know.", sender, channel);
		}
		kickers.add(sender);
		if (MyBotMain.getBot().getConfirmationReplies(channel)) {
			return String.format("%s has has voted to kick %s.", sender, channel);
		}
		return null;
	}
	
}