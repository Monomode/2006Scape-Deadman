package com.rs2.game.objects.impl;

import com.rs2.event.CycleEvent;
import com.rs2.event.CycleEventContainer;
import com.rs2.event.CycleEventHandler;
import com.rs2.game.players.Player;
import com.rs2.util.Misc;

public class SinisterChest {

	private static final int[] CHEST_REWARDS = { 1079, 1093, 526, 1969, 371,
			2363, 451 };
	public static final int KEY = 993;
	private static final int RANARR = 207;
	private static final int HARRALANDER = 205;
	private static final int IRIT = 209;
	private static final int KWUARM = 213;
	private static final int AVANTOE = 211;
	private static final int TORSTOL = 219;
	private static final int OPEN_ANIMATION = 881;

	public static boolean canOpen(Player c) {
		if (c.getItemAssistant().playerHasItem(KEY)) {
			return true;
		} else {
			c.getPacketSender().sendMessage("The chest is locked.");
			return false;
		}
	}

	public static void searchChest(final Player c, final int id, final int x,
			final int y) {
		if (canOpen(c)) {
			c.getPacketSender().sendMessage(
					"You unlock the chest with your key.");
			c.getItemAssistant().deleteItem(KEY, 1);
			c.startAnimation(OPEN_ANIMATION);
			c.getPacketSender().checkObjectSpawn(id + 1, x, y, 1, 10); //default face 2
			  CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
		            @Override
		            public void execute(CycleEventContainer container) {
					/*c.getItemAssistant().addItem(DRAGONSTONE, 1);*/
						c.getItemAssistant().addItem(RANARR, 3);
						c.getItemAssistant().addItem(HARRALANDER, 2);
						c.getItemAssistant().addItem(IRIT, 1);
						c.getItemAssistant().addItem(AVANTOE, 1);
						c.getItemAssistant().addItem(KWUARM, 1);
						c.getItemAssistant().addItem(TORSTOL, 1);
					c.getItemAssistant().addItem(995, Misc.random(8230));
					/*c.getItemAssistant().addItem(
							CHEST_REWARDS[Misc.random(getLength() - 1)], 1);*/
					c.getPacketSender().sendMessage(
							"You find some treasure in the chest.");
					c.getPacketSender().checkObjectSpawn(id, x, y, 1, 10); //default face 2
					container.stop();
				}
				@Override
					public void stop() {
						
					}
			}, 3);
		}
	}

	public static int getLength() {
		return CHEST_REWARDS.length;
	}
}
