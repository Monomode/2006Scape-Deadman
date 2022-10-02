package com.rs2.game.npcs.impl;

import com.rs2.GameEngine;
import com.rs2.game.npcs.Npc;
import com.rs2.game.npcs.NpcHandler;
import com.rs2.game.players.Player;
import com.rs2.game.players.PlayerSave;
import com.rs2.world.clip.Region;

public class Pets {
	
	public static final int RATS_NEEDED_TO_GROW = 10;

	//npc id, item id
	private final static int[][] CATS = {
			{ 3505, 7583 }, //hell kitten
			{ 3504, 7582 }, //hellcat
			{ 3503, 7581 }, //overgrown hellcat
			{ 3506, 7584 }, //lazy hellcat
			{ 3507, 7585 }, //wily hellcat
			{ 993, 1491 }, //witch's cat
			{ 766, 1560 }, //pet kitten
			{ 765, 1559 }, //pet kitten
			{ 764, 1558 }, //pet kitten
			{ 763, 1557 }, //pet kitten
			{ 762, 1556 }, //pet kitten
			{ 761, 1555 }, //pet kitten
			{ 768, 1561 }, //pet cat
			{ 769, 1562 }, //pet cat
			{ 770, 1563 }, //pet cat
			{ 771, 1564 }, //pet cat
			{ 772, 1565 }, //pet cat
			{ 773, 1566 }, //pet cat
			//todo add Wily cats, Lazy cats, etc.
			// customs
			{2640, 6558}, //odysseus
			//slayer pets
			{2783, 6637}, //dark beast
			{1615, 4149}, //abyssal demon
			{1613, 4148}, //nechryael
			{1610, 4147}, //gargoyle
			{3068, 6811}, //skeletal wyvern
			{4229, 4146}, //kurask
			{1624, 4145}, //dust devil
			{1604, 4144}, //aberrant spectre
			{1626, 4143}, //turoth
			{1637, 4142}, //jelly
			{1618, 4141}, //bloodveld
			{1643, 4140}, //infernal mage
			{4228, 4139}, //basilisk
			{1633, 4138}, //pyrefiend
			{4227, 4137}, //cockatrice
			{1622, 4136}, //rockslug
			{1831, 4520}, //cave slime
			{1612, 4135}, //banshee
			{1600, 4134}, //cave crawler
			{5750, 4521}, //cave bug
			{1648, 4133} //crawling hand
		};

	public static final int[] CAT_ITEMS = { 
			1555, 1556, 1557, 1558, 1559, 1560, 6558,
			1561, 1562, 1563, 1564, 1565, 7585, 7581, 7582, 7583, 7584,
			// customs
			1491, //witch's cat
			6558, //odysseus
			//slayer pets
			6637, //dark beast
			4149, //abyssal demon
			4148, //nechryael
			4147, //gargoyle
			6811, //skeletal wyvern
			4146, //kurask
			4145, //dust devil
			4144, //aberrant spectre
			4143, //turoth
			4142, //jelly
			4141, //bloodveld
			4140, //infernal mage
			4139, //basilisk
			4138, //pyrefiend
			4137, //cockatrice
			4136, //rockslug
			4520, //cave slime
			4135, //banshee
			4134, //cave crawler
			4521, //cave bug
			4133 //crawling hand
		};
	
	public static boolean isCatItem(int itemId) {
		for (int i = 0; i < CAT_ITEMS.length; i++) {
			if (itemId == CAT_ITEMS[i]) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isCat(int npcId) {
		for (int i = 0; i < CATS.length; i++) {
			if (npcId == CATS[i][0]) {
				return true;
			}
		}
		return false;
	}


	public static void dropPet(Player player, int itemId, int slot) {
		if (player.hasNpc) {
			player.getPacketSender().sendMessage("You already dropped your " + NpcHandler.getNpcListName(summonItemId(itemId)) + ".");
			return;
		}
		player.getItemAssistant().deleteItem(itemId, slot, player.playerItemsN[slot]);
		player.hasNpc = true;
		player.getPacketSender().sendMessage("You drop your " + NpcHandler.getNpcListName(summonItemId(itemId)) + ".");
		int offsetX = 0;
		int offsetY = 0;
		if (Region.getClipping(player.getX() - 1, player.getY(), player.heightLevel, -1, 0)) {
			offsetX = -1;
		} else if (Region.getClipping(player.getX() + 1, player.getY(), player.heightLevel, 1, 0)) {
			offsetX = 1;
		} else if (Region.getClipping(player.getX(), player.getY() - 1, player.heightLevel, 0, -1)) {
			offsetY = -1;
		} else if (Region.getClipping(player.getX(), player.getY() + 1, player.heightLevel, 0, 1)) {
			offsetY = 1;
		}
		GameEngine.npcHandler.spawnNpc3(player, summonItemId(itemId), player.absX+offsetX, player.absY+offsetY, player.heightLevel, 0, 120, 25, 200, 200, false, false, true);			
		PlayerSave.saveGame(player);
	}
	
	public void quickPickup(Player player, int id) {
			for (Npc i : NpcHandler.npcs) {
				if (i == null) {
					continue;
				}
				if (i.npcType == id) {
					i.absX = 0;
					i.absY = 0;
					i = null;
				}
			}
	}


	public void pickUpPet(Player player, int id) {
		if (player.getItemAssistant().hasFreeSlots(1)) {
			for (Npc i : NpcHandler.npcs) {
				if (i == null) {
					continue;
				}
				if (i.npcType == id) {
					player.startAnimation(827);
					i.absX = 0;
					i.absY = 0;
					i = null;
					for (int[] element : CATS) {
						if (element[0] == id) {
							player.getItemAssistant().addItem(element[1], 1);
						}
					}
				}
			}
		} else {
			player.getPacketSender().sendMessage("You do not have enough space in your inventory to do that.");
		}
	}

	public static int summonItemId(int itemId) {
		for (int i = 0; i < CATS.length; i++) {
			if (itemId == CATS[i][1]) {
				return CATS[i][0];
			}
		}
		return 0;
	}
	
}
