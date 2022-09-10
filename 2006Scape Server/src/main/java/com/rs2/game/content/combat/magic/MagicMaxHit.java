package com.rs2.game.content.combat.magic;

import com.rs2.GameConstants;
import com.rs2.game.players.Player;

public class MagicMaxHit {

	public static int mageAttackBonus(Player c) {
		int magicBonus = c.playerLevel[GameConstants.MAGIC];
		if (MagicData.fullVoidMage(c)) {
			magicBonus += c.getLevelForXP(c.playerXP[GameConstants.MAGIC]) * 0.2;
		}
		if (c.getPrayer().prayerActive[2]) {
			magicBonus *= 1.05;
		} else if (c.getPrayer().prayerActive[7]) {
			magicBonus *= 1.10;
		} else if (c.getPrayer().prayerActive[15]) {
			magicBonus *= 1.15;
		} else if (c.getPrayer().prayerActive[22]) {
			magicBonus *= 1.25;
		}
		return magicBonus + c.playerBonus[3] * 2;
	}

	public static int mageDefenceBonus(Player c) {
		int defenceBonus = c.playerLevel[GameConstants.MAGIC]; //c.playerLevel[GameConstants.DEFENCE] / 2 + c.playerLevel[GameConstants.MAGIC] / 2;
		/*if (c.getPrayer().prayerActive[0]) {
			defenceBonus += c.getLevelForXP(c.playerXP[GameConstants.DEFENCE]) * 0.05; //0.05
		} else if (c.getPrayer().prayerActive[5]) { // */ /*
			defenceBonus += c.getLevelForXP(c.playerXP[GameConstants.DEFENCE]) * 0.1;
		} else if (c.getPrayer().prayerActive[13]) {
			defenceBonus += c.getLevelForXP(c.playerXP[GameConstants.DEFENCE]) * 0.15;
		} else if (c.getPrayer().prayerActive[12]) {
			defenceBonus += c.getLevelForXP(c.playerXP[GameConstants.DEFENCE]) * 0.2;
		} else if (c.getPrayer().prayerActive[20]) {
			defenceBonus += c.getLevelForXP(c.playerXP[GameConstants.DEFENCE]) * 0.25;
		} */
		return defenceBonus + c.playerBonus[8] + c.playerBonus[8] / 3;
	}

}
