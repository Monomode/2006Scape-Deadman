package com.rs2.game.content.combat.range;

import com.rs2.GameConstants;
import com.rs2.game.npcs.NpcHandler;
import com.rs2.game.players.Player;
import com.rs2.util.Misc;

public class RangeMaxHit {

	public static int calculateRangeDefence(Player c) {
		int defenceLevel = c.playerLevel[GameConstants.DEFENCE];
		if (c.getPrayer().prayerActive[0]) {
			defenceLevel += c.getLevelForXP(c.playerXP[GameConstants.DEFENCE]) * 0.05;
		} else if (c.getPrayer().prayerActive[5]) {
			defenceLevel += c.getLevelForXP(c.playerXP[GameConstants.DEFENCE]) * 0.1;
		} else if (c.getPrayer().prayerActive[13]) {
			defenceLevel += c.getLevelForXP(c.playerXP[GameConstants.DEFENCE]) * 0.15;
		} else if (c.getPrayer().prayerActive[24]) {
			defenceLevel += c.getLevelForXP(c.playerXP[GameConstants.DEFENCE]) * 0.2;
		} else if (c.getPrayer().prayerActive[25]) {
			defenceLevel += c.getLevelForXP(c.playerXP[GameConstants.DEFENCE]) * 0.25;
		}
		return defenceLevel + c.playerBonus[9] + c.playerBonus[9] / 2;
	}

	public static int calculateRangeAttack(Player c) {
		int rangeLevel = c.playerLevel[GameConstants.RANGED];
		rangeLevel *= c.specAccuracy;
		if (RangeData.fullVoidRange(c)) {
			rangeLevel += c.getLevelForXP(c.playerXP[GameConstants.RANGED]) * 0.1;
		}
		if (c.getPrayer().prayerActive[2]) {
			rangeLevel *= 1.05;
		} else if (c.getPrayer().prayerActive[7]) {
			rangeLevel *= 1.10;
		} else if (c.getPrayer().prayerActive[15]) {
			rangeLevel *= 1.15;
		} else if (c.getPrayer().prayerActive[22]) {
			rangeLevel *= 1.20;
		}
		// dbow spec
		if (RangeData.fullVoidRange(c) && c.specAccuracy > 1.15) {
			rangeLevel *= 1.75;
		}
		return (int) (rangeLevel + c.playerBonus[4] * 1.95);
	}

	public static int rangeMaxHit(Player c) {
		int rangeLevel = c.playerLevel[GameConstants.RANGED];
		int itemUsed = getRangeStr(c.usingBow ? c.lastArrowUsed : c.lastWeaponUsed);
		double modifier = 1.00;
		if (c.getPrayer().prayerActive[2]) {
			modifier *= 1.05;
		} else if (c.getPrayer().prayerActive[7]) {
			modifier *= 1.10;
		} else if (c.getPrayer().prayerActive[15]) {
			modifier *= 1.15;
		} else if (c.getPrayer().prayerActive[22]) {
			modifier *= 1.15;
		}
		if (RangeData.fullVoidRange(c)) {
			modifier *= 1.20;
		}
		double e = Math.floor(rangeLevel * modifier);
		if (c.fightMode == 0) {
			e = (e + 3.0);
		}
		double darkbow = 1.0;
		if (c.usingSpecial) {
			if (c.playerEquipment[3] == 11235) {
				if (c.lastArrowUsed == 11212) {
					darkbow = 1.5;
				} else {
					darkbow = 1.3;
				}
			}
		}
		double max = (1.3 + e / 10 + itemUsed / 80 + e * itemUsed / 640) * darkbow;
		return (int) max;
	}

	public static int getRangeStr(int i) {
		int str = 87; // 78 for crystal bow, 0 for default, 106 for bofa
		int[][] data = { { 877, 49 }, { 881, 100 }, { 9140, 46 }, { 9145, 36 }, { 9141, 64 },
				{ 9142, 82 }, { 9143, 100 }, { 9144, 115 }, { 9236, 14 },
				{ 9237, 30 }, { 9238, 48 }, { 9239, 66 }, { 9240, 83 },
				{ 9241, 85 }, { 9242, 103 }, { 9243, 105 }, { 9244, 117 },
				{ 9245, 120 }, { 882, 7 }, { 884, 10 }, { 886, 16 },
				{ 888, 22 }, { 890, 31 }, { 892, 49 }, { 4740, 55 },
				{ 11212, 60 }, { 806, 1 }, { 807, 3 }, { 808, 4 }, { 809, 7 },
				{ 810, 10 }, { 811, 14 }, { 11230, 20 }, { 864, 3 },
				{ 863, 4 }, { 865, 7 }, { 866, 10 }, { 867, 14 }, { 868, 24 },
				{ 825, 6 }, { 826, 10 }, { 827, 12 }, { 828, 18 }, { 829, 28 },
				{ 830, 42 }, { 800, 5 }, { 801, 7 }, { 802, 11 }, { 803, 16 },
				{ 804, 23 }, { 805, 36 }, { 9976, 0 }, { 9977, 15 },
				{ 4212, 78 }, { 4214, 78 }, { 4215, 78 }, { 4216, 78 },
				{ 4217, 78 }, { 4218, 78 }, { 4219, 78 }, { 4220, 78 },
				{ 4221, 78 }, { 4222, 78 }, { 4223, 78 }, { 6522, 49 },
				{ 10034, 15 }, };
		for (int[] element : data) {
			if (i == element[0]) {
				str = element[1];
			}
		}
		return str;
	}

}
