package com.rs2.game.content.skills.prayer;

import com.rs2.event.CycleEvent;
import com.rs2.event.CycleEventContainer;
import com.rs2.event.CycleEventHandler;
import com.rs2.game.content.music.sound.SoundList;
import com.rs2.game.content.randomevents.Shade;
import com.rs2.game.content.randomevents.Zombie;
import com.rs2.game.content.skills.SkillHandler;
import com.rs2.game.items.ItemAssistant;
import com.rs2.game.players.Player;
import com.rs2.util.Misc;

/**
 * Class Prayer Handles Prayer
 * @author 2012 23:56 29/12/2010
 */

public class Prayer {

	private static int[][] data = {
			{ 526, 2 }, // NPC BONES 5
			{ 528, 2 }, // BURNT BONES 5
			{ 530, 2 }, // BAT BONES 5
			{ 2859, 2 }, // WOLF BONES 5
			{ 3179, 2 }, // MONKEY BONES 5
			{ 3180, 2 }, // MONKEY BONES 5
			{ 3181, 2 }, // MONKEY BONES 5
			{ 3182, 2 }, // MONKEY BONES 5
			{ 3183, 2 }, // MONKEY BONES 5
			{ 3185, 2 }, // MONKEY BONES 5
			{ 3186, 2 }, // MONKEY BONES 5
			{ 3187, 2 }, // MONKEY BONES 5
			{ 532, 7 }, // BIG BONES 15
			{ 534, 15 }, // BABY DRAGON BONES 30
			{ 536, 36 }, // DRAGON BONES 72
			{ 2530, 5 }, // PLAYER BONES 5
			{ 3123, 25 }, // SHAIKAHAN BONES 25
			{ 3125, 15 }, // JOGRE BONES 23
			{ 3127, 15 }, // BURNT JOGRE BONES 25
			{ 4812, 22 }, // ZOGRE BONES 82
			{ 4830, 84 }, // FAYGR BONES 84
			{ 4832, 96 }, // RAURG BONES 96
			{ 4834, 140 }, // OURG BONES 140
			{ 6729, 62 }, // DAGANNOTH BONES 125
			{ 6812, 36 }, // WYVERN BONES 72
	};

	public static boolean playerBones(Player player, int item) {
		for (int[] element : data) {
			if (item == element[0]) {
				return true;
			}
		}
		return false;
	}

	private static void handleBones(final Player c, int i, int slot) {
		if(c.randomEventsEnabled) {
			if (Misc.random(300) == 4 && c.shadeSpawned == false) {
				Zombie.spawnZombie(c);
			} else if (Misc.random(300) == 2 && c.zombieSpawned == false) {
				Shade.spawnShade(c);
			}
		}
		for (final int[] element : data) {
			if (i == element[0]) {
				if (!SkillHandler.PRAYER) {
					c.getPacketSender().sendMessage("This skill is currently disabled.");
				}
				if (System.currentTimeMillis() - c.buryDelay > 800) {
					c.getItemAssistant().deleteItem(element[0], slot, 1);
					c.getPlayerAssistant().addSkillXP(element[1], 5);
					c.buryDelay = System.currentTimeMillis();
					c.startAnimation(827);
					c.getPacketSender().sendSound(SoundList.BONE_BURY, 100, 0);
					c.getPacketSender().sendMessage("You dig a hole in the ground...");
					CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer container) {
							c.getPacketSender().sendMessage("You bury the " + ItemAssistant.getItemName(element[0]).toLowerCase() + ".");
							container.stop();
						}
						@Override
						public void stop() {
							
						}
					}, 1);
				}
			}
		}
	}

	public static void buryBones(Player c, int i, int slot) {
		handleBones(c, i, slot);
	}
}
