package com.rs2.game.content.minigames;

import com.rs2.game.players.Player;
import com.rs2.util.Misc;

public class TreasureTrails {

	public static int lowLevelReward[] = {
			// Weapons
			853, // Maple shortbow
			851, // Maple longbow
			1327, // Black scimitar
			1233, // Black Dagger (p)
			5682, // Black Dagger (p+)
			5700, // Black Dagger (p++)

			7388, // Blue skirt (t)
			7396, // Blue wizard hat (t)
			7392, // Blue wizard robe (t)
			7386, // Blue skirt (g)
			7394, // Blue wizard hat (g)
			7390, // Blue wizard robe (g)
			7364, // Studded body (t)
			7368, // Studded chaps (t)
			7362, // Studded body (g)
			7366, // Studded chaps (g)

			// Jewelery
			1639, // Emerald ring
			1641, // Ruby ring
			1637, // Sapphire ring

			// Misc
			//2524, // Black toy horsey
			//2520, // Brown toy horsey
			//2526, // Grey toy horsey
			//2522, // White toy horsey
			//6541, // Mouse toy
			//7771, // Toy cat
			//7767, // Toy mouse
			//7763, // Toy doll
			//3721, // Toy ship
			//7759, // Toy soldier
			//6856, // Bobble hat
			//6862, // Woolly hat
			//6860, // Tri-jester hat
			//6858, // Jester hat
			//6182, // Lederhosen hat
			//6547, // Doctors hat
			//6548, // Nurse hat
			//6665, // Mudskipper hat
	};
	public static int mediumLevelReward[] = {
			// Weapons
			1329, // Mithril scimitar
			1331, // Adamant scimitar
			1397, // Air battlestaff
			1399, // Earth battlestaff
			1393, // Fire battlestaff
			3053, // Lava battlestaff
			6562, // Mud battlestaff
			1395, // Water battlestaff
			857, // Yew shortbow
			855, // Yew longbow

			// Armor
			2587, // Black full helm (t)
			2589, // Black kiteshield (t)
			2583, // Black platebody (t)
			2585, // Black platelegs (t)
			3472, // Black plateskirt (t)
			2595, // Black full helm (g)
			2597, // Black kiteshield (g)
			2591, // Black platebody (g)
			2593, // Black platelegs (g)
			3473, // Black plateskirt (g)

			// Jewelery
			2568, // Ring of forging
			2570, // Ring of life
			//2550, // Ring of recoil
			1727, // Amulet of magic
			1725, // Amulet of strength

			// Misc
			2581, // Robin hood hat
			2577, // Ranger boots
			2579, // Wizard boots
			3107, // Spiked boots
			3105, // Climbing boots
			//2651, // Pirate's hat
			//2631, //Highwayman mask
	};
	public static int highLevelReward[] = {
			// Weapons
			1247, //Rune spear
			1319, //Rune 2h sword
			1303, //Rune longsword
			1373, //Rune battleaxe
			1333, // Rune scimitar
			1213, //Rune dagger
			1229, // Rune dagger(p)
			5678, // Rune dagger(p+)
			5696, // Rune dagger(p++)
			1275, //Rune pickaxe
			1359, //Rune axe

			861, // Magic shortbow
			859, // Magic longbow

			// Armor
			2605, // Adamant full helm (t)
			2603, // Adamant kiteshield (t)
			2599, // Adamant platebody (t)
			2601, // Adamant platelegs (t)
			3474, // Adamant plateskirt (t)
			2613, // Adamant full helm (g)
			2611, // Adamant kiteshield (g)
			2607, // Adamant platebody (g)
			2609, // Adamant platelegs (g)
			3475, // Adamant plateskirt (g)
			7372, // Green d'hide body (t)
			7380, // Green d'hide chaps (t)
			7370, // Green d'hide body (g)
			7378, // Green d'hide chaps (g)

			7400, // Enchanted hat
			//7398, // Enchanted robe
			//7399, // Enchanted top

			4105, // Mystic gloves (dark)
			4115, // Mystic gloves (light)
			4099, // Mystic hat (dark)
			4109, // Mystic hat (light)
			2499, // Blue d'hide body
			2493, // Blue d'hide chaps
			2487, // Blue d'hide vambs
			7376, // Blue d'hide body (t)
			7384, // Blue d'hide chaps (t)
			7374, // Blue d'hide body (g)
			7382, // Blue d'hide chaps (g)
			2489, // Red d'hide vambs
			2495, // Red d'hide chaps
			2501, // Red d'hide body

			// Jewelery
			2552, // Ring of dueling(8)
			1731, // Amulet of power

			// Misc

	};
	public static int eliteLevelReward[] = {

			1215, // Dragon dagger
			1231, // Dragon dagger(p)
			5680, // Dragon dagger(p+)
			5698, // Dragon dagger(p++)
			//1249, // Dragon spear
			3176, // Dragon spear(kp)
			//1263, // Dragon spear(p)
			//5716, // Dragon spear(p+)
			//5730, // Dragon spear(p++)

			//6739, // Dragon axe
			3054, // Mystic lava staff
			6563, // Mystic mud staff
			1403, // Mystic water staff
			1405, // Mystic air staff
			1407, // Mystic earth staff
			1401, // Mystic fire staff

			4103, // Mystic robe bottom (dark)
			4113, // Mystic robe bottom (light)
			4101, // Mystic robe top (dark)
			4111, // Mystic robe top (light)

			1147, // Rune med helm
			1163, // Rune full helm
			1113, // Rune chainbody
			1127, // Rune platebody
			1093, // Rune platelegs
			1079, // Rune plateskirt
			1185, // Rune sq shield
			1201, // Rune kiteshield
			2627, // Rune full helm (t)
			2629, // Rune kiteshield (t)
			2623, // Rune platebody (t)
			2625, // Rune platelegs (t)
			3477, // Rune plateskirt (t)
			2619, // Rune full helm (g)
			2621, // Rune kiteshield (g)
			2615, // Rune platebody (g)
			2617, // Rune platelegs (g)
			3476, // Rune plateskirt (g)
			2657, // Zamorak full helm
			2659, // Zamorak kiteshield
			2653, // Zamorak platebody
			2655, // Zamorak platelegs
			3478, // Zamorak plateskirt
			2673, // Guthix full helm
			2675, // Guthix kiteshield
			2669, // Guthix platebody
			2671, // Guthix platelegs
			3480, // Guthix plateskirt
			2665, // Saradomin full helm
			2667, // Saradomin kiteshield
			2661, // Saradomin platebody
			2663, // Saradomin platelegs
			3479, // Saradomin plateskirt
			//
			7398, // Enchanted robe
			7399, // Enchanted top

			6922, // Infinity gloves

			2491, // Black d'hide vamb
			2497, // Black d'hide chaps
			2503, // Black d'hide body
			3486, // Gilded full helm
			3488, // Gilded kiteshield
			3481, // Gilded platebody
			3483, // Gilded platelegs
			3485, // Gilded plateskirt
			// Jewelery
			1631, // Uncut dragonstone
			//1615, // Dragonstone
			//1702, // Dragonstone amulet
			1645, // Dragonstone ring
			//2572, // Ring of wealth
	};
	public static int masterLevelReward[] = {
			//2491, // Black d'hide vamb
			//2497, // Black d'hide chaps
			//2503, // Black d'hide body
			//2513, // Dragon chainbody
			//1149, // Dragon med helm
			//4087, // Dragon platelegs
			//4585, // Dragon plateskirt
			//1187, // Dragon sq shield

			6920, // Infinity boots
			//6924, // Infinity bottoms
			//6922, // Infinity gloves
			//6918, // Infinity hat
			//6916, // Infinity top

			//6733, // Archers ring
			//6737, // Berserker ring
			//6585, // Amulet of fury

			6040, // Amulet of nature
			1497, // Amulet of othanian

			// Misc
			7927, // Easter ring
			1050, // Santa hat
			1042, // Blue partyhat
			1044, // Green partyhat
			1046, // Purple partyhat
			1038, // Red partyhat
			1048, // White partyhat
			1040, // Yellow partyhat
	};

	public static int lowLevelStacks[] = {
			995, // Coins
			//380, // Lobster
			4697, // Smoke rune
			4698, // Mud rune
			559, // Body rune
	};
	public static int mediumLevelStacks[] = {
			995, // Coins
			380, // Lobster
			//374, // Swordfish
			890, // Adamant arrow
			563, // Law rune
			561, // Nature rune
			562, // Chaos rune
			564, // Cosmic rune
	};
	public static int highLevelStacks[] = {
			//Super energy potion
			//Super restore
			//Antifire
			//Super attack
			//Super strength
			//Super defence
			995, // Coins
			374, // Swordfish
			//386, // Shark
			892, // Rune arrow
			4697, // Smoke rune
			4698, // Mud rune
			566, // Soul rune
			//4694, // Steam rune
			//4699, // Lava rune
			//4695, // Mist rune
			565, // Blood rune
			560, // Death rune
			//4696, // Dust rune
			//535, // Babydragon bones
			//537, // Dragon bones
	};
	public static int eliteLevelStacks[] = {
			//Super energy potion
			//Super restore
			//2452, // Antifire
			386, // Shark
			2436, // Super attack
			2440, // Super strength
			2442, // Super defence
			535, // Babydragon bones
	};
	public static int masterLevelStacks[] = {
			//Super energy potion
			//Super restore
			2452, // Antifire
			2436, // Super attack
			2440, // Super strength
			2442, // Super defence
			537, // Dragon bones
	};



	public static void addClueReward(Player player, int clueLevel) {
		int chanceReward = Misc.random(2);
		if (clueLevel == 0) {
			switch (chanceReward) {
			case 0:
				displayReward(player,
						Misc.randomArrayItem(lowLevelReward), 1,
						Misc.randomArrayItem(lowLevelReward), 1,
						Misc.randomArrayItem(lowLevelReward), 1,
						Misc.randomArrayItem(lowLevelStacks), Misc.random(33, 66)
				);
				break;
			case 1:
				displayReward(player,
						Misc.randomArrayItem(lowLevelReward), 1,
						Misc.randomArrayItem(lowLevelReward), 1,
						Misc.randomArrayItem(lowLevelStacks), Misc.random(33, 66)
				);
				break;
			case 2:
				displayReward(player,
						Misc.randomArrayItem(lowLevelReward), 1,
						Misc.randomArrayItem(lowLevelStacks), Misc.random(33, 66)
				);
				break;
			}
		} else if (clueLevel == 1) {
			switch (chanceReward) {
			case 0:
				displayReward(player,
						Misc.randomArrayItem(mediumLevelReward), 1,
						Misc.randomArrayItem(mediumLevelReward), 1,
						Misc.randomArrayItem(mediumLevelReward), 1,
						Misc.randomArrayItem(mediumLevelStacks), Misc.random(33, 66)
				);
				break;
			case 1:
				displayReward(player,
						Misc.randomArrayItem(mediumLevelReward), 1,
						Misc.randomArrayItem(mediumLevelReward), 1,
						Misc.randomArrayItem(mediumLevelStacks), Misc.random(33, 66)
				);
				break;
			case 2:
				displayReward(player,
						Misc.randomArrayItem(mediumLevelReward), 1,
						Misc.randomArrayItem(mediumLevelStacks), Misc.random(33, 66)
				);
				break;
			}
		} else if (clueLevel == 2) {
			switch (chanceReward) {
			case 0:
				displayReward(player,
						Misc.randomArrayItem(highLevelReward), 1,
						Misc.randomArrayItem(highLevelReward), 1,
						Misc.randomArrayItem(highLevelReward), 1,
						Misc.randomArrayItem(highLevelStacks), Misc.random(33, 66)
				);
				break;
			case 1:
				displayReward(player,
						Misc.randomArrayItem(highLevelReward), 1,
						Misc.randomArrayItem(highLevelReward), 1,
						Misc.randomArrayItem(highLevelStacks), Misc.random(33, 66)
				);
				break;
			case 2:
				displayReward(player,
						Misc.randomArrayItem(highLevelReward), 1,
						Misc.randomArrayItem(highLevelStacks), Misc.random(33, 66)
				);
				break;
			}
		} else if (clueLevel == 3) {
			switch (chanceReward) {
				case 0:
					displayReward(player,
							Misc.randomArrayItem(eliteLevelReward), 1,
							//Misc.randomArrayItem(eliteLevelReward), 1,
							//Misc.randomArrayItem(eliteLevelReward), 1,
							Misc.randomArrayItem(eliteLevelStacks), Misc.random(1, 3)
					);
					break;
			}
		} else if (clueLevel == 4) {
			switch (chanceReward) {
				case 0:
					displayReward(player,
							Misc.randomArrayItem(masterLevelReward), 1,
							//Misc.randomArrayItem(masterLevelReward), 1,
							//Misc.randomArrayItem(masterLevelReward), 1,
							Misc.randomArrayItem(masterLevelStacks), Misc.random(3, 6)
					);
					break;
			}
		}
	}
	public static void displayReward(Player c, int item, int amount) {
		displayReward(c, item, amount, -1, 1);
	}

	public static void displayReward(Player c, int item, int amount, int item2, int amount2) {
		displayReward(c, item, amount, item2, amount2, -1, 1);
	}

	public static void displayReward(Player c, int item, int amount, int item2, int amount2, int item3, int amount3) {
		displayReward(c, item, amount, item2, amount2, item3, amount3, -1, 1);
	}

	public static void displayReward(Player c, int item, int amount, int item2, int amount2, int item3, int amount3, int item4, int amount4) {
		int[] items = { item, item2, item3, item4 };
		int[] amounts = { amount, amount2, amount3, amount4 };
		c.outStream.createFrameVarSizeWord(53);
		c.outStream.writeWord(6963);
		c.outStream.writeWord(items.length);
		for (int i = 0; i < items.length; i++) {
			if (c.playerItemsN[i] > 254) {
				c.outStream.writeByte(255);
				c.outStream.writeDWord_v2(amounts[i]);
			} else {
				c.outStream.writeByte(amounts[i]);
			}
			if (items[i] > 0) {
				c.outStream.writeWordBigEndianA(items[i] + 1);
			} else {
				c.outStream.writeWordBigEndianA(0);
			}
		}
		c.outStream.endFrameVarSizeWord();
		c.flushOutStream();
		for (int i = 0; i < items.length; i++) {
			if (items[i] > 0) c.getItemAssistant().addOrDropItem(items[i], amounts[i]);
		}
		c.getPacketSender().showInterface(6960);
	}

}
