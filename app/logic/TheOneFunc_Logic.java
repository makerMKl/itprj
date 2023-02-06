package logic;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.basic.Card;
import structures.basic.Player;
import structures.basic.Tile;
import structures.basic.Unit;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

//-- create by MK.L 23.2.6
public class TheOneFunc_Logic {
	
	/*
	 * name: modelInit
	 * using: For initialisation of the game interface etc.
	 * input: ActorRef out
	 * coder: MK.L   2023.2.6 Xiuqi Liu 2773750
	 * change:
	 */	
	public static void modelInit(ActorRef out) {
		
		BasicCommands.addPlayer1Notification(out, "Hello loser", 2);
		//try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();} // these cause processing to wait for a number of milliseconds.
		// -- 棋盘
		for(int i = 0;i<5;i++)
		{
			for(int j = 0;j<9;j++)
			{
				Tile tile = BasicObjectBuilders.loadTile(j, i);
				BasicCommands.drawTile(out, tile, 0);
			}
		}
		// -- 血
		Player humanPlayer = new Player(20, 0);
		BasicCommands.setPlayer1Health(out, humanPlayer);
		Player aiPlayer = new Player(20, 0);
		BasicCommands.setPlayer2Health(out, aiPlayer);
		
		// -- Mana

		humanPlayer.setMana(4);
		BasicCommands.setPlayer1Mana(out, humanPlayer);
		aiPlayer.setMana(4);
		BasicCommands.setPlayer2Mana(out, aiPlayer);
		// -- 初始化主怪
		Tile humanTile = BasicObjectBuilders.loadTile(1, 2);
		Unit unit = BasicObjectBuilders.loadUnit(StaticConfFiles.humanAvatar, 0, Unit.class);
		unit.setPositionByTile(humanTile); 
		BasicCommands.drawUnit(out, unit, humanTile);
		
		Tile aiTile = BasicObjectBuilders.loadTile(7, 2);
		Unit aiUnit = BasicObjectBuilders.loadUnit(StaticConfFiles.aiAvatar, 0, Unit.class);
		aiUnit.setPositionByTile(aiTile); 
		BasicCommands.drawUnit(out, aiUnit, aiTile);
		
		/*	初始化手牌	 test*/
		Card hailstone_golem = BasicObjectBuilders.loadCard(StaticConfFiles.c_hailstone_golem, 0, Card.class);
		Card ironcliff_guardian = BasicObjectBuilders.loadCard(StaticConfFiles.c_ironcliff_guardian, 0, Card.class);
		Card pureblade_enforcer = BasicObjectBuilders.loadCard(StaticConfFiles.c_pureblade_enforcer, 0, Card.class);
		Card silverguard_knight = BasicObjectBuilders.loadCard(StaticConfFiles.c_silverguard_knight, 0, Card.class);
		BasicCommands.drawCard(out, hailstone_golem, 1, 0);
		BasicCommands.drawCard(out, ironcliff_guardian, 2, 0);
		BasicCommands.drawCard(out, pureblade_enforcer, 3, 0);
		BasicCommands.drawCard(out, silverguard_knight, 4, 0);
		

	}
}
