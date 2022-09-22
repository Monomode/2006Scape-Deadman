package com.rs2.game.content.combat.npcs;

import com.rs2.GameConstants;
import com.rs2.game.content.combat.CombatConstants;
import com.rs2.game.content.combat.melee.MeleeData;
import com.rs2.game.content.minigames.FightCaves;
import com.rs2.game.content.minigames.PestControl;
import com.rs2.game.content.music.sound.CombatSounds;
import com.rs2.game.content.music.sound.SoundList;
import com.rs2.game.items.impl.Greegree.MonkeyData;
import com.rs2.game.npcs.NpcData;
import com.rs2.game.npcs.NpcHandler;
import com.rs2.game.players.Client;
import com.rs2.game.players.Player;
import com.rs2.game.players.PlayerHandler;
import com.rs2.util.Misc;
import com.rs2.world.Boundary;

public class NpcCombat {

	public static void multiAttackDamage(int i) {
		int max = NpcHandler.getMaxHit(i);
		for (Player player : PlayerHandler.players) {
			if (player != null) {
				Client c = (Client) player;
				if (c.isDead || c.heightLevel != NpcHandler.npcs[i].heightLevel) {
					continue;
				}
				if (player.goodDistance(c.absX, c.absY,
						NpcHandler.npcs[i].absX, NpcHandler.npcs[i].absY, 15)) {
					if (NpcHandler.npcs[i].attackType == 2) {
						if (!c.getPrayer().prayerActive[16]) {
							if (Misc.random(500) + 200 > Misc.random(c.getCombatAssistant().mageDef())) {
								int dam = Misc.random(max);
								c.dealDamage(dam);
								c.handleHitMask(dam);
							} else {
								c.dealDamage(0);
								c.handleHitMask(0);
							}
						} else {
							c.dealDamage(0);
							c.handleHitMask(0);
						}
					} else if (NpcHandler.npcs[i].attackType == 1) {
						if (!c.getPrayer().prayerActive[17]) {
							int dam = Misc.random(max);
							if (Misc.random(500) + 200 > Misc.random(c
									.getCombatAssistant()
									.calculateRangeDefence())) {
								c.dealDamage(dam);
								c.handleHitMask(dam);
							} else {
								c.dealDamage(0);
								c.handleHitMask(0);
							}
						} else {
							c.dealDamage(0);
							c.handleHitMask(0);
						}
					}
					if (NpcHandler.npcs[i].endGfx > 0) {
						c.gfx0(NpcHandler.npcs[i].endGfx);
					}
				}
				c.getPlayerAssistant().refreshSkill(GameConstants.HITPOINTS);
			}
		}
	}

	public static void multiAttackGfx(int i, int gfx) {
		if (NpcHandler.npcs[i].projectileId < 0) {
			return;
		}
		for (Player player : PlayerHandler.players) {
			if (player != null) {
				Client c = (Client) player;
				if (c.heightLevel != NpcHandler.npcs[i].heightLevel) {
					continue;
				}
				if (player.goodDistance(c.absX, c.absY,
						NpcHandler.npcs[i].absX, NpcHandler.npcs[i].absY, 15)) {
					int nX = NpcHandler.npcs[i].getX() + NpcHandler.offset(i);
					int nY = NpcHandler.npcs[i].getY() + NpcHandler.offset(i);
					int pX = c.getX();
					int pY = c.getY();
					int offX = (nY - pY) * -1;
					int offY = (nX - pX) * -1;
					c.getPlayerAssistant().createPlayersProjectile(nX, nY,
							offX, offY, 50, NpcHandler.getProjectileSpeed(i),
							NpcHandler.npcs[i].projectileId, 43, 31,
							-c.getId() - 1, 65);
				}
			}
		}
	}

	public static void attackPlayer(Player c, int i) {
		if (NpcHandler.npcs[i] != null) {
			if (NpcHandler.npcs[i].absY == 3228 && c.absY == 3227
				|| NpcHandler.npcs[i].absY == 3224 && c.absY == 3225
				|| NpcHandler.npcs[i].absY == 3226 && c.absY == 3227
				|| Boundary.isIn(c, Boundary.DRAYNOR_BUILDING) && (NpcHandler.npcs[i].npcType == 172 || NpcHandler.npcs[i].npcType == 174)
				|| NpcHandler.npcs[i].inLesserNpc()
				|| !c.npcCanAttack //|| c.teleTimer > 0
				|| NpcHandler.npcs[i].isDead) {
				return;
			}
			if (NpcHandler.npcs[i].npcType == 1532
					|| NpcHandler.npcs[i].npcType == 1534
					|| NpcHandler.npcs[i].npcType == 6145
					|| NpcHandler.npcs[i].npcType == 6144
					|| NpcHandler.npcs[i].npcType == 6143
					|| NpcHandler.npcs[i].npcType == 6142
					|| NpcHandler.npcs[i].npcType == 752) {
				return;
			}
			if (Boundary.isIn(c, Boundary.APE_ATOLL) && MonkeyData.isWearingGreegree(c)) {
				return;
			}
			if (NpcHandler.npcs[i].npcType == 1401 && Boundary.isIn(c, Boundary.TUTORIAL) || c.tutorialProgress < 36) {
				return;
			}
			if (NpcHandler.npcs[i].npcType == 9 && c.absX == 3180 && c.absY > 3433 && c.absY < 3447) {
				return;
			}
			if (NpcHandler.npcs[i].npcType == 374 && c.absY == 3372 && c.absX > 2522 && c.absX < 2532) {
				return;
			}
			if (NpcHandler.npcs[i].npcType > 2462 && NpcHandler.npcs[i].npcType < 2468) {
				if (Misc.random(5) == 0) {
					NpcHandler.npcs[i].forceChat("Flee from me, " + c.playerName + "!");
				} else if (Misc.random(5) == 1) {
					NpcHandler.npcs[i].forceChat("Begone, " + c.playerName + "!");
				} else if (Misc.random(5) == 2) {
					NpcHandler.npcs[i].forceChat("Bwuk");
				} else if (Misc.random(5) == 3) {
					NpcHandler.npcs[i].forceChat("Bwuk bwuk bwuk");
				} else if (Misc.random(5) == 4) {
					NpcHandler.npcs[i].forceChat("MUAHAHAHAHAAA!");
				} else if (Misc.random(5) == 5) {
					NpcHandler.npcs[i].forceChat("Bwaaaaaaauk bwuk bwuk");
				}
			}
			if (!NpcHandler.npcs[i].inMulti() && NpcHandler.npcs[i].underAttackBy > 0 && NpcHandler.npcs[i].underAttackBy != c.playerId) {
				NpcHandler.npcs[i].killerId = 0;
				return;
			}
			if (!NpcHandler.npcs[i].inMulti() && (c.underAttackBy > 0 || c.underAttackBy2 > 0 && c.underAttackBy2 != i)) {
				NpcHandler.npcs[i].killerId = 0;
				return;
			}
			if (NpcHandler.npcs[i].heightLevel != c.heightLevel) {
				NpcHandler.npcs[i].killerId = 0;
				return;
			}
			if (!NpcData.goodDistanceNpc(NpcHandler.npcs[i].npcId, c.getX(), c.getY(), NpcData.distanceRequired(NpcHandler.npcs[i].npcId)) || NpcData.inNpc(NpcHandler.npcs[i].npcId, c.getX(), c.getY())) {
				return;
			}
			NpcHandler.npcs[i].facePlayer(c.playerId);
			boolean special = false;//specialCase(c,i);
			if (NpcData.checkClip(NpcHandler.npcs[i]) || special) {
				if (c.respawnTimer <= 0) {
					NpcHandler.npcs[i].facePlayer(c.playerId);
					NpcHandler.npcs[i].attackTimer = NpcData.getNpcDelay(i);
					NpcHandler.npcs[i].hitDelayTimer = NpcData.getHitDelay(i);
					NpcHandler.npcs[i].attackType = 0;
					if (CombatConstants.COMBAT_SOUNDS) {
						if (PestControl.npcIsPCMonster(NpcHandler.npcs[i].npcType) || PestControl.isPCPortal(NpcHandler.npcs[i].npcType)) {
							return;
						}
						c.getPacketSender().sendSound(CombatSounds.getNpcAttackSounds(NpcHandler.npcs[i].npcType), 100, 0);
					}
					if (special) {
						loadSpell2(i);
					} else {
						loadSpell(c, i);
					}
					if (NpcHandler.npcs[i].attackType == 3) {
						NpcHandler.npcs[i].hitDelayTimer += 2;
					}
					if (NpcHandler.multiAttacks(i)) {
						multiAttackGfx(i, NpcHandler.npcs[i].projectileId);
						NpcData.startAnimation(NpcEmotes.getAttackEmote(i), i);
						if (CombatConstants.COMBAT_SOUNDS) {
							if (PestControl.npcIsPCMonster(NpcHandler.npcs[i].npcType) || PestControl.isPCPortal(NpcHandler.npcs[i].npcType)) {
								return;
							}
							c.getPacketSender().sendSound(CombatSounds.getNpcAttackSounds(NpcHandler.npcs[i].npcType), 100, 0);
						}
						NpcHandler.npcs[i].oldIndex = c.playerId;
						return;
					}
					if (NpcHandler.npcs[i].projectileId > 0) {
						int nX = NpcHandler.npcs[i].getX() + NpcHandler.offset(i);
						int nY = NpcHandler.npcs[i].getY() + NpcHandler.offset(i);
						int pX = c.getX();
						int pY = c.getY();
						int offX = (nY - pY) * -1;
						int offY = (nX - pX) * -1;
						c.getPlayerAssistant().createPlayersProjectile(nX, nY, offX, offY, 50, NpcHandler.getProjectileSpeed(i), NpcHandler.npcs[i].projectileId, 43, 31, -c.getId() - 1, 65);
					}
					int random = Misc.random(10);
					if (NpcHandler.npcs[i].npcType == 222 && (NpcHandler.npcs[i].killerId > 0 && NpcHandler.npcs[i].underAttack) && !NpcHandler.npcs[i].isDead && (NpcHandler.npcs[i].HP < NpcHandler.npcs[i].MaxHP + 1)) {
						if (random < 3) {
							NpcHandler.npcs[i].HP += 2;
							//NpcHandler.npcs[i].startAnimation(84); 
							NpcHandler.npcs[i].updateRequired = true;
						}
					}
					c.underAttackBy2 = i;
					c.singleCombatDelay2 = System.currentTimeMillis();
					NpcHandler.npcs[i].oldIndex = c.playerId;
					NpcData.startAnimation(NpcEmotes.getAttackEmote(i), i);
					if (CombatConstants.COMBAT_SOUNDS) {
						if (PestControl.npcIsPCMonster(NpcHandler.npcs[i].npcType) || PestControl.isPCPortal(NpcHandler.npcs[i].npcType)) {
							return;
						}
						c.getPacketSender().sendSound(CombatSounds.getNpcAttackSounds(NpcHandler.npcs[i].npcType), 100, 0);
					}
					c.getPacketSender().closeAllWindows();
				}
			}
		}
	}

	public static void loadSpell2(int i) {
		NpcHandler.npcs[i].attackType = 3;
		int random = Misc.random(3);
		if (random == 0) {
			NpcHandler.npcs[i].projectileId = 393; // red
			NpcHandler.npcs[i].endGfx = 430;
		} else if (random == 1) {
			NpcHandler.npcs[i].projectileId = 394; // green
			NpcHandler.npcs[i].endGfx = 429;
		} else if (random == 2) {
			NpcHandler.npcs[i].projectileId = 395; // white
			NpcHandler.npcs[i].endGfx = 431;
		} else if (random == 3) {
			NpcHandler.npcs[i].projectileId = 396; // blue
			NpcHandler.npcs[i].endGfx = 428;
		}
	}

	public static void loadSpell(Player c, int i) {
		if (NpcHandler.npcs[i].npcType > 2462 && NpcHandler.npcs[i].npcType < 2469 || NpcHandler.npcs[i].npcType > 3751 && NpcHandler.npcs[i].npcType < 3762) {
			NpcHandler.npcs[i].attackType = 2;
		}
		if (NpcHandler.npcs[i].npcType > 3761 && NpcHandler.npcs[i].npcType < 3772) {
			NpcHandler.npcs[i].attackType = 1;
		}
		switch (NpcHandler.npcs[i].npcType) {
		case 1158://kq first form
			int kqRandom = Misc.random(3);
				if (kqRandom == 2) {
					NpcHandler.npcs[i].projectileId = 280; //gfx
					NpcHandler.npcs[i].attackType = 2;	
					NpcHandler.npcs[i].endGfx = 279;
				} else {
					NpcHandler.npcs[i].attackType = 0;	
				}
				break;
			case 1160://kq secondform
				int kqRandom2 = Misc.random(3);
				if (kqRandom2 == 2) {
					NpcHandler.npcs[i].projectileId = 279; //gfx
					NpcHandler.npcs[i].attackType = 1 + Misc.random(1);	
					NpcHandler.npcs[i].endGfx = 278;
				} else {
					NpcHandler.npcs[i].attackType = 0;	
				}
				break;
		case 2607:
			NpcHandler.npcs[i].attackType = 1;
		case 2591:
			NpcHandler.npcs[i].attackType = 2;
			break;	
		case 172:
		case 174:
			NpcHandler.npcs[i].gfx100(96); // Dark Wizards use earth strike
			NpcHandler.npcs[i].projectileId = 97; 
			NpcHandler.npcs[i].endGfx = 98;
			NpcHandler.npcs[i].attackType = 2;
			break;
		case 3068:
		if(Misc.random(10) > 7) {
			NpcHandler.npcs[i].projectileId = 393; //red
			NpcHandler.npcs[i].endGfx = 430;
			NpcHandler.npcs[i].attackType = 3;
			NpcData.startAnimation(2989, i);
		} else {
			NpcData.startAnimation(2980, i);
			NpcHandler.npcs[i].attackType = 0;
		}
		break;
		case 2892:
			NpcHandler.npcs[i].projectileId = 94;
			NpcHandler.npcs[i].attackType = 2;
			NpcHandler.npcs[i].endGfx = 95;
			break;
		case 2894:
			NpcHandler.npcs[i].projectileId = 298;
			NpcHandler.npcs[i].attackType = 1;
			break;
		/*
		 * Better Dragons
		 */
		case 5363: // Mithril-Dragon
		case 53: // Red Dragon
		case 54: // Black-Dragon
		case 55: // Blue-Dragon
		case 941: // Green-Dragon
		case 4682:
		case 5362:
		case 1590: //bronze
		case 1591: //iron
		case 1592: //steel
			int randomDragon = Misc.random(4);
			switch (randomDragon) {
				case 0:
					NpcHandler.npcs[i].projectileId = 393; // red
					NpcHandler.npcs[i].endGfx = 430;
					NpcHandler.npcs[i].attackType = 3;
					break;
				case 1:
					NpcHandler.npcs[i].projectileId = 394; // green
					NpcHandler.npcs[i].endGfx = 429;
					NpcHandler.npcs[i].attackType = 3;
					break;
				case 2:
					NpcHandler.npcs[i].projectileId = 395; // white
					NpcHandler.npcs[i].endGfx = 431;
					NpcHandler.npcs[i].attackType = 3;
					break;
				case 3:
					NpcHandler.npcs[i].projectileId = 396; // blue
					NpcHandler.npcs[i].endGfx = 428;
					NpcHandler.npcs[i].attackType = 3;
					break;
				case 4:
					NpcHandler.npcs[i].projectileId = -1; // melee
					NpcHandler.npcs[i].endGfx = -1;
					NpcHandler.npcs[i].attackType = 0;
					break;
			}
			break;
		case 134:
			if (c.playerLevel[GameConstants.PRAYER] > 0) {
				c.playerLevel[GameConstants.PRAYER]--;
				c.getPlayerAssistant().refreshSkill(GameConstants.PRAYER);
				c.getPlayerAssistant().appendPoison(6);
				c.getCombatAssistant().resetPlayerAttack();
			}
			break;
		case 997: //venenatis
				if (c.playerLevel[GameConstants.PRAYER] > 0) {
					c.playerLevel[GameConstants.PRAYER]--;
					c.getPlayerAssistant().refreshSkill(GameConstants.PRAYER);
					c.getPlayerAssistant().appendPoison(8);
					c.getCombatAssistant().resetPlayerAttack();
				}
				break;

		case 3590: //Lava Dragon, Wilderness Dragon
		case 50:
		case 742:
			int random = Misc.random(4);
			switch (random) {
			case 0:
				NpcHandler.npcs[i].projectileId = 393; // red
				NpcHandler.npcs[i].endGfx = 430;
				NpcHandler.npcs[i].attackType = 3;
				break;
			case 1:
				NpcHandler.npcs[i].projectileId = 394; // green
				NpcHandler.npcs[i].endGfx = 429;
				NpcHandler.npcs[i].attackType = 3;
				break;
			case 2:
				NpcHandler.npcs[i].projectileId = 395; // white
				NpcHandler.npcs[i].endGfx = 431;
				NpcHandler.npcs[i].attackType = 3;
				break;
			case 3:
				NpcHandler.npcs[i].projectileId = 396; // blue
				NpcHandler.npcs[i].endGfx = 428;
				NpcHandler.npcs[i].attackType = 3;
				break;
			case 4:
				NpcHandler.npcs[i].projectileId = -1; // melee
				NpcHandler.npcs[i].endGfx = -1;
				NpcHandler.npcs[i].attackType = 0;
				break;
			}
			break;
		// arma npcs
		case 2561:
			NpcHandler.npcs[i].attackType = 0;
			break;
		case 2560:
			NpcHandler.npcs[i].attackType = 1;
			NpcHandler.npcs[i].projectileId = 1190;
			break;
		case 2559:
			NpcHandler.npcs[i].attackType = 2;
			NpcHandler.npcs[i].projectileId = 1203;
			break;
		case 2558:
			random = Misc.random(1);
			NpcHandler.npcs[i].attackType = 1 + random;
			if (NpcHandler.npcs[i].attackType == 1) {
				NpcHandler.npcs[i].projectileId = 1197;
			} else {
				NpcHandler.npcs[i].attackType = 2;
				NpcHandler.npcs[i].projectileId = 1198;
			}
			break;
		// sara npcs
		case 2562: // sara
			random = Misc.random(1);
			if (random == 0) {
				NpcHandler.npcs[i].attackType = 2;
				NpcHandler.npcs[i].endGfx = 1224;
				NpcHandler.npcs[i].projectileId = -1;
			} else if (random == 1) {
				NpcHandler.npcs[i].attackType = 0;
			}
			break;
		case 2563: // star
			NpcHandler.npcs[i].attackType = 0;
			break;
		case 2564: // growler
			NpcHandler.npcs[i].attackType = 2;
			NpcHandler.npcs[i].projectileId = 1203;
			break;
		case 2565: // bree
			NpcHandler.npcs[i].attackType = 1;
			NpcHandler.npcs[i].projectileId = 9;
			break;
		// bandos npcs
		case 2550:
			random = Misc.random(2);
			if (random == 0 || random == 1) {
				NpcHandler.npcs[i].attackType = 0;
			} else {
				NpcHandler.npcs[i].attackType = 1;
				NpcHandler.npcs[i].endGfx = 1211;
				NpcHandler.npcs[i].projectileId = 288;
			}
			break;
		case 2551:
			NpcHandler.npcs[i].attackType = 0;
			break;
		case 2552:
			NpcHandler.npcs[i].attackType = 2;
			NpcHandler.npcs[i].projectileId = 1203;
			break;
		case 2553:
			NpcHandler.npcs[i].attackType = 1;
			NpcHandler.npcs[i].projectileId = 1206;
			break;
		case 2025:
			NpcHandler.npcs[i].attackType = 2;
			int r = Misc.random(3);
			if (r == 0) {
				NpcHandler.npcs[i].gfx100(158);
				NpcHandler.npcs[i].projectileId = 159;
				NpcHandler.npcs[i].endGfx = 160;
			}
			if (r == 1) {
				NpcHandler.npcs[i].gfx100(161);
				NpcHandler.npcs[i].projectileId = 162;
				NpcHandler.npcs[i].endGfx = 163;
			}
			if (r == 2) {
				NpcHandler.npcs[i].gfx100(164);
				NpcHandler.npcs[i].projectileId = 165;
				NpcHandler.npcs[i].endGfx = 166;
			}
			if (r == 3) {
				NpcHandler.npcs[i].gfx100(155);
				NpcHandler.npcs[i].projectileId = 156;
			}
			break;
		case 2881:// supreme
			NpcHandler.npcs[i].attackType = 1;
			NpcHandler.npcs[i].projectileId = 298;
			break;

		case 2882:// prime
			NpcHandler.npcs[i].attackType = 2;
			NpcHandler.npcs[i].projectileId = 162;
			NpcHandler.npcs[i].endGfx = 477;
			break;

		case 2028:
			NpcHandler.npcs[i].attackType = 1;
			NpcHandler.npcs[i].projectileId = 27;
			break;

		case 3200:
			int r2 = Misc.random(1);
			if (r2 == 0) {
				NpcHandler.npcs[i].attackType = 1;
				NpcHandler.npcs[i].gfx100(550);
				NpcHandler.npcs[i].projectileId = 551;
				NpcHandler.npcs[i].endGfx = 552;
			} else {
				NpcHandler.npcs[i].attackType = 2;
				NpcHandler.npcs[i].gfx100(553);
				NpcHandler.npcs[i].projectileId = 554;
				NpcHandler.npcs[i].endGfx = 555;
			}
			break;
		case 2745:
			int r3 = 0;
			if (NpcHandler
					.goodDistance(
							NpcHandler.npcs[i].absX,
							NpcHandler.npcs[i].absY,
							PlayerHandler.players[NpcHandler.npcs[i].spawnedBy].absX,
							PlayerHandler.players[NpcHandler.npcs[i].spawnedBy].absY,
							1)) {
				r3 = Misc.random(2);
			} else {
				r3 = Misc.random(1);
			}
			if (r3 == 0) {
				NpcHandler.npcs[i].attackType = 2;
				NpcHandler.npcs[i].endGfx = 157;
				NpcHandler.npcs[i].projectileId = 448;
			} else if (r3 == 1) {
				NpcHandler.npcs[i].attackType = 1;
				NpcHandler.npcs[i].projectileId = 451;
			} else if (r3 == 2) {
				NpcHandler.npcs[i].attackType = 0;
				NpcHandler.npcs[i].projectileId = -1;
			}
			break;
		case 2743:
			NpcHandler.npcs[i].attackType = 2;
			NpcHandler.npcs[i].projectileId = 445;
			NpcHandler.npcs[i].endGfx = 446;
			break;

		case 2631:
			NpcHandler.npcs[i].attackType = 1;
			NpcHandler.npcs[i].projectileId = 443;
			break;
		}
	}

	public static void registerNpcHit(int i) {
		if (NpcHandler.npcs[i] != null) {
			if (PlayerHandler.players[NpcHandler.npcs[i].oldIndex] == null) {
				return;
			}
			if (NpcHandler.npcs[i].isDead) {
				return;
			}
			Client c = (Client) PlayerHandler.players[NpcHandler.npcs[i].oldIndex];
			if (NpcHandler.multiAttacks(i)) {
				NpcCombat.multiAttackDamage(i);
				return;
			}
			if (c.playerIndex <= 0 && c.npcIndex <= 0) {
				if (c.autoRet == 1 && NpcHandler.npcs[i].npcType != 411) {
					c.npcIndex = i;
				}
			}
			if (c.attackTimer <= 3 || c.attackTimer == 0 && c.npcIndex == 0 && c.oldNpcIndex == 0) {
				c.startAnimation(c.getCombatAssistant().getBlockEmote());
			}
			if (c.respawnTimer <= 0) {
				int damage = 0;
				if (NpcHandler.npcs[i].attackType == 0) {
					damage = Misc.random(NpcHandler.npcs[i].maxHit);
					if (10 + Misc.random(c.getCombatAssistant().calcDef()) > Misc
							.random(NpcHandler.npcs[i].attack)) {
						damage = 0;
					}
					if (NpcData.cantKillYou(NpcHandler.npcs[i].npcType)) {
						if (damage >= c.playerLevel[GameConstants.HITPOINTS]) {
							damage = c.playerLevel[GameConstants.HITPOINTS] - 1;
						}
					}
					if (c.getPrayer().prayerActive[18] && !(NpcHandler.npcs[i].npcType == 2030)) { // protect from melee
						damage = 0;
					} else if (c.getPrayer().prayerActive[18] && NpcHandler.npcs[i].npcType == 2030) {
						if (NpcHandler.npcs[i].attackType == 0) {
							damage = Misc.random(NpcHandler.npcs[i].maxHit);
						}
						if (10 + Misc.random(MeleeData.calculateMeleeDefence(c)) > Misc.random(NpcHandler.npcs[i].attack)) {
							 if (NpcHandler.npcs[i].npcType == 1158 || NpcHandler.npcs[i].npcType == 1160) 
								damage = (damage / 2);
							 else
								 damage = 0;
						}
					}
					if (c.playerLevel[GameConstants.HITPOINTS] - damage < 0) {
						damage = c.playerLevel[GameConstants.HITPOINTS];
					}
				}

				if (NpcHandler.npcs[i].attackType == 1) { // range
					damage = Misc.random(NpcHandler.npcs[i].maxHit);
					if (10 + Misc.random(c.getCombatAssistant().calculateRangeDefence()) > Misc.random(NpcHandler.npcs[i].attack)) {
						if (NpcHandler.npcs[i].npcType == 1158 || NpcHandler.npcs[i].npcType == 1160) 
							damage = (damage / 2);
						 else
							 damage = 0;
					}
					if (NpcData.cantKillYou(NpcHandler.npcs[i].npcType)) {
						if (damage >= c.playerLevel[GameConstants.HITPOINTS]) {
							damage = c.playerLevel[GameConstants.HITPOINTS] - 1;
						}
					}
					if (c.getPrayer().prayerActive[17]) { // protect from range
						damage = 0;
					}
					if (c.playerLevel[GameConstants.HITPOINTS] - damage < 0) {
						damage = c.playerLevel[GameConstants.HITPOINTS];
					}
				}

				if (NpcHandler.npcs[i].attackType == 2) { // magic
					damage = Misc.random(NpcHandler.npcs[i].maxHit);
					boolean magicFailed = false;
					if (10 + Misc.random(c.getCombatAssistant().mageDef()) > Misc.random(NpcHandler.npcs[i].attack)) {
						damage = 0;
						magicFailed = true;
					}
					if (NpcData.cantKillYou(NpcHandler.npcs[i].npcType)) {
						if (damage >= c.playerLevel[GameConstants.HITPOINTS]) {
							damage = c.playerLevel[GameConstants.HITPOINTS] - 1;
						}
					}
					if(c.getPrayer().prayerActive[16]) { // protect from magic
						
						 if (NpcHandler.npcs[i].npcType == 1158)  {
							 damage = (damage / 2);
						 } else {
							 damage = 0;
						}
						magicFailed = true;			
						if (c.playerLevel[GameConstants.HITPOINTS] - damage < 0) { 
							damage = c.playerLevel[GameConstants.HITPOINTS];
						}
						if(NpcHandler.npcs[i].endGfx > 0 && (!magicFailed || FightCaves.isFightCaveNpc(i))) {
							c.gfx100(NpcHandler.npcs[i].endGfx);
						} else {
							c.gfx100(85);
							c.getPacketSender().sendSound(SoundList.MAGE_FAIL, 100, 0);
						}
					}
				}

				if (NpcHandler.npcs[i].attackType == 3) { // fire breath
					int anti = c.getPlayerAssistant().antiFire();
					switch (anti) {
					case 0:// has no shield
						//if (c.getPlayerAssistant().antiFire()) = true {
						damage = Misc.random(45) + 10;
						c.getPacketSender().sendMessage("@red@You are badly burnt by the dragon-fire!");
						break;
					case 1:// has a shield
						if (c.getItemAssistant().playerHasEquipped(5, 1540)) {
							damage = Misc.random(4) + 1;
							c.getPacketSender().sendMessage("@yel@Your shield provides partial protection from the dragon-fire.");
						} else {
						if (c.getItemAssistant().playerHasEquipped(5, 1187)) {
							damage = Misc.random(1) + 0; //damage = Misc.random(4) + 1;
							c.getPacketSender().sendMessage("Your dragon sq shield protects you from the dragon-fire.");
						}
					}
						break;
					/* case 2:// shield and potion protection
						damage = Misc.random(2) + 1; //damage = Misc.random(4) + 1;
						c.getPacketSender().sendMessage("Your potion protects you from the fire.");
						break; */
					case 2:// melee
							damage = Misc.random(5);
						c.getPacketSender().sendMessage("You are hurt by the dragon's melee!");
							break;
					}
				}
				if (damage > 0) {
					c.getCombatAssistant().applyRecoilNPC(c, damage, i);
				}
				if (c.playerLevel[GameConstants.HITPOINTS] - damage < 0) {
					damage = c.playerLevel[GameConstants.HITPOINTS];
				}
				int difference = c.playerLevel[GameConstants.HITPOINTS] - damage;
				if (c.getPlayerAssistant().savePlayer()) {
					c.getPlayerAssistant().handleROL();
				} else if (difference <= c.getLevelForXP(c.playerXP[GameConstants.HITPOINTS]) / 10 && difference > 0) {
					c.appendRedemption();
				} else {
					NpcHandler.handleSpecialEffects(c, i, damage);
					c.logoutDelay = System.currentTimeMillis(); // logout delay
					c.handleHitMask(damage);
					c.playerLevel[GameConstants.HITPOINTS] -= damage;
					c.getPlayerAssistant().refreshSkill(GameConstants.HITPOINTS);
					FightCaves.tzKihEffect(c, i, damage);
					if (damage > 0)
					{
						c.getPacketSender().sendSound(822 + Misc.random(2), 100, 0);
					}
					c.updateRequired = true;
				}
			}
		}
	}

}
