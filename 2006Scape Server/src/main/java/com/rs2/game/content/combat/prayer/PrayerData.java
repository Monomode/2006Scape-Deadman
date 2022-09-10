package com.rs2.game.content.combat.prayer;

public class PrayerData {

	public int prayerId = -1;
	public static double prayerPoint = 1.0;
	public long stopPrayerDelay, prayerDelay;
	public boolean usingPrayer;

	public final int[] PRAYER_DRAIN_RATE = { 500, 500, 500, 500, 500, 500, 500,
			500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500,
			500, 500, 500, 500, 500, 500 };

	public final int[] PRAYER_LEVEL_REQUIRED = { 1, 4, 9, 8, 9, 10, 13, 27, 19,
			22, 25, 26, 27, 28, 31, 45, 37, 40, 43, 44, 45, 70, 77, 52, 60, 70 };

	public final int[] PRAYER = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
			14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 };

	public final String[] PRAYER_NAME = { "Thick Skin"/*#0*/, "Burst of Strength"/*#1*/,
			"Clarity of Thought"/*#2*/, "Sharp Eye"/*#3*/, "Mystic Will"/*#4*/, "Rock Skin"/*#5*/,
			"Superhuman Strength"/*#6*/, "Improved Reflexes"/*#7*/, "Rapid Restore"/*#8*/,
			"Rapid Heal"/*#9*/, "Protect Item"/*#10*/, "Hawk Eye"/*#11*/, "Mystic Lore"/*#12*/,
			"Steel Skin"/*#13*/, "Ultimate Strength"/*#14*/, "Incredible Reflexes"/*#15*/,
			"Protect from Magic"/*#16*/, "Protect from Missiles"/*#17*/,
			"Protect from Melee"/*#18*/, "Eagle Eye"/*#19*/, "Mystic Might"/*#20*/, "Retribution"/*#21*/,
			"Redemption"/*#22*/, "Smite"/*#23*/, "Chivalry"/*#24*/, "Piety"/*#25*/ };

	public final int[] PRAYER_GLOW = { 83, 84, 85, 601, 602, 86, 87, 88, 89,
			90, 91, 603, 604, 92, 93, 94, 95, 96, 97, 605, 606, 98, 99, 100,
			607, 608 };

	public final int[] PRAYER_HEAD_ICONS = { -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, 2, 1, 0, -1, -1, 3, 5, 4, -1, -1 };

	public boolean[] prayerActive = { false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false,
			false, false };

	/**
	 * How fast the prayer is drained
	 */

	public static final double[] prayerData = {
			0.5, // Thick Skin 1
			0.5, // Burst of Strength 1
			0.5, // Clarity of Thought 1
			1, // Sharp Eye 1
			1, // Mystic Will 1
			1, // Rock Skin 2
			1, // SuperHuman Strength 2
			1, // Improved Reflexes 2
			0.4, // Rapid restore 0.4
			0.6, // Rapid Heal 0.6
			0.6, // Protect Items 0.6
			1.5, // Hawk eye 1.5
			1.5, // Mystic Lore 2
			2, // Steel Skin 4
			2, // Ultimate Strength 4
			2, // Incredible Reflexes 4
			2, // Protect from Magic 4
			2, // Protect from Missiles 4
			2, // Protect from Melee 4
			4, // Eagle Eye 4
			4, // Mystic Might 4
			2.5, // Retribution 1
			2.5, // Redemption 2
			3, // Smite 6
			4, // Chivalry 8
			4, // Piety 8
	};
}
