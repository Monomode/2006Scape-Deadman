package com.rs2.net.packets.impl;

import com.rs2.Connection;
import com.rs2.game.players.Player;
import com.rs2.game.players.antimacro.AntiSpam;
import com.rs2.net.packets.PacketType;
import com.rs2.util.Misc;

/**
 * Chat
 **/
public class Chat implements PacketType {

	@Override
	public void processPacket(Player player, int packetType, int packetSize) {
		player.setChatTextEffects(player.getInStream().readUnsignedByteS());
		player.setChatTextColor(player.getInStream().readUnsignedByteS());
		player.setChatTextSize((byte) (player.packetSize - 2));
		player.inStream.readBytes_reverseA(player.getChatText(), player.getChatTextSize(), 0);
		ReportHandler.addText(player.playerName, player.getChatText(), packetSize - 2);
		String word = Misc.textUnpack(player.getChatText(), player.packetSize - 2).toLowerCase();
		if (AntiSpam.blockedWords(player, word, true) && !Connection.isMuted(player)) {
			player.setChatTextUpdateRequired(true);
		}
		if (Connection.isMuted(player)) {
			player.getPacketSender().sendMessage("You are muted and can't speak.");
			player.setChatTextUpdateRequired(false);
			return;
		}
	}
}
