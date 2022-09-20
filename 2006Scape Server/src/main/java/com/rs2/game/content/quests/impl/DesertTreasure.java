package com.rs2.game.content.quests.impl;

import com.rs2.game.players.Player;

/**
 * Imp Catcher
 * @author Andrew (Mr Extremez)
 */

public class DesertTreasure {

	public static void showInformation(Player client) {
		for (int i = 8144; i < 8295; i++) {
			client.getPacketSender().sendString("", i);
		}
		client.getPacketSender().sendString("@dre@Desert Treasure", 8144);
		client.getPacketSender().sendString("", 8145);
		if (client.desertT == 0) {
			client.getPacketSender().sendString( "I can start this quest by speaking to an Archaeologist", 8147);
			client.getPacketSender().sendString("who is at the Bedabin Camp in Al-Kharid Desert.", 8148);
			client.getPacketSender().sendString("", 8149);
			client.getPacketSender().sendString("Quest Requirements:", 8150);
			client.getPacketSender().sendString("1000 Total Level", 8151);
			client.getPacketSender().sendString("50 Firemaking Level", 8152);
			client.getPacketSender().sendString("53 Thieving Level", 8153);
		} else if (client.desertT == 1) {
			client.getPacketSender().sendString("@str@I can start this quest by speaking to an Archaeologist", 8147);
			client.getPacketSender().sendString("@str@who is at the Bedabin Camp in Al-Kharid Desert.", 8148);
			client.getPacketSender().sendString("", 8149);
			client.getPacketSender().sendString("The Archaeologist has asked you to get the following items:", 8150);
			client.getPacketSender().sendString("@red@Blood diamond", 8151);
			client.getPacketSender().sendString("@yel@Shadow diamond", 8152);
			client.getPacketSender().sendString("Smoke diamond", 8153);
			client.getPacketSender().sendString("@whi@Ice diamond", 8154);
		} else if (client.desertT == 2) {
			client.getPacketSender().sendString("@str@I can start this quest by speaking to an Archaeologist", 8147);
			client.getPacketSender().sendString("@str@who is at the Bedabin Camp in Al-Kharid Desert.", 8148);
			client.getPacketSender().sendString("", 8149);
			client.getPacketSender().sendString("@str@The Archaeologist has asked you to get the following items:", 8150);
			client.getPacketSender().sendString("@str@Blood diamond", 8151);
			client.getPacketSender().sendString("@str@Shadow diamond", 8152);
			client.getPacketSender().sendString("@str@Smoke diamond", 8153);
			client.getPacketSender().sendString("@str@Ice diamond", 8154);
			client.getPacketSender().sendString("", 8155);
			client.getPacketSender().sendString("You have completed this quest!", 8156);
		}
		client.getPacketSender().showInterface(8134);
	}

}
