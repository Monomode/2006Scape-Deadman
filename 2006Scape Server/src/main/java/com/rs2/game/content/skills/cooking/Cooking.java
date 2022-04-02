package com.rs2.game.content.skills.cooking;

import java.security.SecureRandom;
import com.rs2.GameConstants;
import com.rs2.event.CycleEvent;
import com.rs2.event.CycleEventContainer;
import com.rs2.event.CycleEventHandler;
import com.rs2.game.content.music.sound.SoundList;
import com.rs2.game.content.randomevents.RandomEventHandler;
import com.rs2.game.content.skills.SkillHandler;
import com.rs2.game.items.ItemAssistant;
import com.rs2.game.items.ItemConstants;
import com.rs2.game.players.Player;
import com.rs2.util.Misc;

public class Cooking extends SkillHandler {

	private static SecureRandom cookingRandom = new SecureRandom(); // The random factor

	private static enum CookingItems {
		//raw, cooked, burnt, levelreq, exp, stopburn, stopburn w/gloves, name
				SHRIMP(317, 315, 7954, 1, 30, 1, 30, "shrimp"), //stopBurn= 34
				SARDINE(327, 325, 369, 1, 40, 1, 38, "sardine"), //stopBurn= 38
				HERRING(345, 347, 357, 5, 50, 1, 41, "herring"), //stopBurn= 41
				TROUT(335, 333, 343, 15, 70, 1, 50, "trout"), //stopBurn= 50
				TUNA(359, 361, 367, 30, 100, 1, 63, "tuna"), //stopBurn= 64
				ANCHOVIES(321, 319, 323, 5, 45, 1, 34, "anchovies"), //stopBurn= 34
				RAW_BEEF(2132, 2142, 2146, 1, 30, 1, 33, "raw beef"), //stopBurn= 33
				RAW_RAT(2134, 2142, 2146, 1, 30, 1, 33, "raw rat meat"), //stopBurn= 33
				BURNT_MEAT(2142, 2146, 2146, 1, 1, 100, 100, "cooked meat"), //stopBurn= 100
				RAW_CHICKEN(2138, 2140, 2144, 1, 30, 1, 33, "raw chicken"), //stopBurn= 33
				RAW_BEAR_MEAT(2136, 2142, 2146, 1, 30, 1, 33, "raw bear meat"), //stopBurn= 33
				MACKERAL(353, 355, 357, 10, 60, 1, 45, "mackeral"), //stopBurn= 45
				SALMON(331, 329, 343, 25, 90, 1, 55, "salmon"), //stopBurn= 58
				UNCOOKED_BERRY_PIE(2321, 2325, 2329, 10, 78, 1, 50, "uncooked pie"), //stopBurn= 50
				PIKE(349, 351, 343, 20, 80, 1, 59, "pike"), //stopBurn= 59
				KARAMBWAN(3142, 3144, 3146, 1, 80, 1, 20, "karambwan"), //stopBurn= 20
				LOBSTER(377, 379, 381, 40, 120, 1, 68, "lobster"), //stopBurn= 74
				SWORDFISH(371, 373, 375, 50, 140, 1, 81, "swordfish"), //stopBurn= 86
				MONKFISH(7944, 7946, 7948, 62, 150, 1, 90,	"monkfish"), //stopBurn= 92
				SHARK(383, 385, 387, 76, 210, 1, 94, "shark"), //stopBurn= 100
				SEA_TURTLE(395, 397, 399, 82, 211, 1, 99, "sea turtle"), //stopBurn= 100
				MANTA_RAY(389, 391, 393, 91, 216, 1, 100, "manta ray"), //stopBurn= 100
				SEAWEED(401, 1781, 1781, 1, 1, 1, 1, "sea weed"), //stopBurn= 1
				CURRY(2009, 2011, 2013, 60, 280, 1, 74, "curry"); //stopBurn= 74

		int rawItem, cookedItem, burntItem, levelReq, xp, stopBurn, stopBurnGloves;
		String name;

		private CookingItems(int rawItem, int cookedItem, int burntItem, int levelReq, int xp, int stopBurn, int stopBurnGloves, String name) {
			this.rawItem = rawItem;
			this.cookedItem = cookedItem;
			this.burntItem = burntItem;
			this.levelReq = levelReq;
			this.xp = xp;
			this.stopBurn = stopBurn;
			this.name = name;
		}

		private int getRawItem() {
			return rawItem;
		}

		private int getCookedItem() {
			return cookedItem;
		}

		private int getBurntItem() {
			return burntItem;
		}

		private int getLevelReq() {
			return levelReq;
		}

		private int getXp() {
			return xp;
		}

		private int getStopBurn() {
			return stopBurn;
		}

		private int getStopBurnGloves() {
			return stopBurnGloves;
		}

		private String getName() {
			return name;
		}
	}

	public static CookingItems forId(int itemId) {
		for (CookingItems item : CookingItems.values()) {
			if (itemId == item.getRawItem()) {
				return item;
			}
		}
		return null;
	}

	public static void makeBreadOptions(Player c, int item) {
		if (c.getItemAssistant().playerHasItem(1929) && c.getItemAssistant().playerHasItem(1933) && item == c.breadID) {
			c.getItemAssistant().deleteItem(1929, 1);
			c.getItemAssistant().deleteItem(1933, 1);
			c.getItemAssistant().addItem(1925, 1);
			c.getItemAssistant().addItem(1931, 1);
			c.getItemAssistant().addItem(item, 1);
			c.getPacketSender().sendMessage("You mix the water and flour to make some " + ItemAssistant.getItemName(item) + ".");
		}
		c.getPacketSender().closeAllWindows();
	}

	public static void pastryCreation(Player c, int itemID1, int itemID2, int giveItem, String message) {
		if (c.getItemAssistant().playerHasItem(itemID1) && c.getItemAssistant().playerHasItem(itemID2)) {
			c.getItemAssistant().deleteItem(itemID1, 1);
			c.getItemAssistant().deleteItem(itemID2, 1);
			c.getItemAssistant().addItem(giveItem, 1);
			if (message.equalsIgnoreCase("")) {
				c.getPacketSender().sendMessage("You mix the two ingredients and get an " + ItemAssistant.getItemName(giveItem) + ".");
			} else {
				c.getPacketSender().sendMessage(message);
			}
		}
	}

	public static void cookingAddon(Player c, int itemID1, int itemID2, int giveItem, int requiredLevel, int expGained) {
		if (c.playerLevel[GameConstants.COOKING] >= requiredLevel) {
			if (c.getItemAssistant().playerHasItem(itemID1) && c.getItemAssistant().playerHasItem(itemID2)) {
				c.getItemAssistant().deleteItem(itemID1, 1);
				c.getItemAssistant().deleteItem(itemID2, 1);
				c.getItemAssistant().addItem(giveItem, 1);
				c.getPlayerAssistant().addSkillXP(expGained, 7);
				c.getPacketSender().sendMessage("You create a " + ItemAssistant.getItemName(giveItem) + ".");
			}
		} else {
			c.getPacketSender().sendMessage("You don't have the required level to make an " + ItemAssistant.getItemName(giveItem));
		}
	}

	public static void setCooking(Player player, boolean isCooking) {
		player.playerIsCooking = isCooking;
		player.stopPlayerSkill = isCooking;
	}
	
	private static void viewCookInterface(Player c, int item) {
		c.getPacketSender().sendChatInterface(1743);
		c.getPacketSender().sendFrame246(13716, view190 ? 190 : 170, item);
		c.getPacketSender().sendString(getLine(c) + "" + ItemAssistant.getItemName(item) + "", 13717);
	}

	public static boolean startCooking(Player c, int itemId, int objectId) {
		CookingItems item = forId(itemId);
		if (item != null) {
			if (c.playerLevel[GameConstants.COOKING] < item.getLevelReq()) {
				c.getPacketSender().closeAllWindows();
				c.getDialogueHandler().sendStatement("You need a Cooking level of " + item.getLevelReq() + " to cook this.");
				c.nextChat = 0;
				return false;
			}
			if (c.playerIsCooking) {
				c.getPacketSender().closeAllWindows();
				return false;
			}
			if (!COOKING) {
				c.getPacketSender().sendMessage("This skill is currently disabled.");
				return false;
			}
			// save the id of the item and object for the cooking interface.
			c.cookingItem = itemId;
			c.cookingObject = objectId;
			viewCookInterface(c, item.getRawItem());
			return true;
		}
		return false;
	}

	private static boolean getSuccess(Player c, int burnBonus, int levelReq, int stopBurn) {
		if (c.playerLevel[GameConstants.COOKING] >= stopBurn) {
			return true;
		}
		double burn_chance = 55.0 - burnBonus;
		double cook_level = c.playerLevel[GameConstants.COOKING];
		double lev_needed = levelReq;
		double burn_stop = stopBurn;
		double multi_a = burn_stop - lev_needed;
		double burn_dec = burn_chance / multi_a;
		double multi_b = cook_level - lev_needed;
		burn_chance -= multi_b * burn_dec;
		double randNum = cookingRandom.nextDouble() * 100.0;
		return burn_chance <= randNum;
	}

	public static void cookItem(final Player player, final int itemId, final int amount, final int objectId) {
		CycleEventHandler.getSingleton().stopEvents(player, "cookingEvent".hashCode());
		final CookingItems item = forId(itemId);
		if (item != null) {
			setCooking(player, true);
			RandomEventHandler.addRandom(player);
			player.getPacketSender().closeAllWindows();
			player.doAmount = amount;
			if (player.doAmount > player.getItemAssistant().getItemAmount(itemId)) {
				player.doAmount = player.getItemAssistant().getItemAmount(itemId);
			}
			if (objectId > 0) {
				player.startAnimation(objectId == 2732 ? 897 : 896);
			}
			CycleEventHandler.getSingleton().addEvent("cookingEvent".hashCode(), player, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if (!player.playerIsCooking) {
						setCooking(player, false);
						container.stop();
						return;
					}
					if (!player.getItemAssistant().playerHasItem(item.getRawItem(), 1)) {
						player.getPacketSender().sendMessage("You have run out of " + item.getName() + " to cook.");
						setCooking(player, false);
						container.stop();
						return;
					}
					boolean burn;
					if (player.playerEquipment[ItemConstants.HANDS] == 775) {
						burn = !getSuccess(player, 3, item.getLevelReq(), item.getStopBurnGloves());
					} else {
						burn = !getSuccess(player, 3, item.getLevelReq(), item.getStopBurn());
					}
					player.getItemAssistant().deleteItem(item.getRawItem(),
							player.getItemAssistant().getItemSlot(itemId), 1);
					if (!burn) {
						//gamefilter player.getPacketSender().sendMessage("You successfully cook the " + item.getName().toLowerCase() + ".");
						if (GameConstants.SOUND) {
							player.getPacketSender().sendSound(SoundList.COOK_ITEM, 100, 0);
						}
						player.getPlayerAssistant().addSkillXP(item.getXp(), GameConstants.COOKING);
						player.getItemAssistant().addItem(item.getCookedItem(), 1);
					} else {
						player.getPacketSender().sendMessage(
								"Oops! You accidentally burnt the "
										+ item.getName().toLowerCase() + "!");
						player.getItemAssistant().addItem(item.getBurntItem(), 1);
					}
					player.doAmount--;
					if (player.disconnected) {
						container.stop();
						return;
					}
					if (objectId < 0) {
						container.stop();
						return;
					}
					if (player.playerIsCooking && !Misc.goodDistance(player.objectX, player.objectY, player.absX, player.absY, 2)) {
						container.stop();
						return;
					}
					if (player.doAmount > 0) {
						if (objectId > 0) {
							player.startAnimation(objectId == 2732 ? 897 : 896);
						}
					} else if (player.doAmount == 0) {
						setCooking(player, false);
						container.stop();
					}
				}

				@Override
				public void stop() {
					
				}
			}, 4);
		}
	}
}
