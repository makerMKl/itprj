package logic;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.basic.Card;
import structures.basic.EffectAnimation;
import structures.basic.Player;
import structures.basic.Tile;
import structures.basic.Unit;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;
import java.util.Random;
//-- create by MK.L 23.2.6
public class TheOneFunc_Logic {
	
	/*
	 * name: modelInit_Begin
	 * using: For initialisation of the game interface etc and begin.
	 * input: ActorRef out
	 * coder: MK.L   2023.2.6 Xiuqi Liu 2773750
	 * change:
	 */	
	public static void modelInit_Begin(ActorRef out) {
		
		BasicCommands.addPlayer1Notification(out, "Hello loser", 2);
		//try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();} // these cause processing to wait for a number of milliseconds.
		Tile[][] tiles = new Tile[9][5];
		// -- 棋盘初始化
		tiles = func_CreateBoard(tiles,out);
		// -- 创建AI线程
		Thread AICalculate = new Thread();

		
		
		// -- 血
		Player humanPlayer = new Player(20, 4);
		BasicCommands.setPlayer1Health(out, humanPlayer);
		Player aiPlayer = new Player(20, 4);
		BasicCommands.setPlayer2Health(out, aiPlayer);
		
		// -- Mana

		humanPlayer.setMana(4);
		BasicCommands.setPlayer1Mana(out, humanPlayer);
		aiPlayer.setMana(4);
		BasicCommands.setPlayer2Mana(out, aiPlayer);
		
		// -- 初始化主怪
		Tile humanTile = BasicObjectBuilders.loadTile(1, 2);
		Unit humanUnit = BasicObjectBuilders.loadUnit(StaticConfFiles.humanAvatar, 0, Unit.class);
		
		humanUnit.setPositionByTile(humanTile); 

		BasicCommands.drawUnit(out, humanUnit, humanTile);
		funcSleep(50);
		BasicCommands.setUnitHealth(out, humanUnit, 20);
		BasicCommands.setUnitAttack(out, humanUnit, 2);
		
		Tile aiTile = BasicObjectBuilders.loadTile(7, 2);
		Unit aiUnit = BasicObjectBuilders.loadUnit(StaticConfFiles.aiAvatar, 20, Unit.class);
		aiUnit.setPositionByTile(aiTile); 
		BasicCommands.drawUnit(out, aiUnit, aiTile);
		funcSleep(50);
		BasicCommands.setUnitHealth(out, aiUnit, 20);
		BasicCommands.setUnitAttack(out, aiUnit, 2);
		
		
		Tile tileX = Tile.constructTile(StaticConfFiles.gridConf);
		tileX= BasicObjectBuilders.loadTile(5, 5);
		BasicCommands.drawTile(out, tileX, 3);
		
		
		
		// Move unit, default, horizontal then vertical
		
		BasicCommands.moveUnitToTile(out, aiUnit, tiles[2][4]);
		/*	初始化手牌	 test target*/
		Card hailstone_golem = BasicObjectBuilders.loadCard(StaticConfFiles.c_hailstone_golem, 0, Card.class);
		Card ironcliff_guardian = BasicObjectBuilders.loadCard(StaticConfFiles.c_ironcliff_guardian, 1, Card.class);
		Card pureblade_enforcer = BasicObjectBuilders.loadCard(StaticConfFiles.c_pureblade_enforcer, 2, Card.class);
		Card silverguard_knight = BasicObjectBuilders.loadCard(StaticConfFiles.c_silverguard_knight, 3, Card.class);
		
		
		BasicCommands.drawCard(out, hailstone_golem, 1, 0);
		BasicCommands.drawCard(out, ironcliff_guardian, 2, 0);
		BasicCommands.drawCard(out, pureblade_enforcer, 3, 0);
		BasicCommands.drawCard(out, silverguard_knight, 4, 0);
		
		boolean gameStatus = true;
		while(gameStatus)
		{
			break;
			
		}
		
		// Effects

		for (String effectFile : TheOneConfig.effects) {
			BasicCommands.addPlayer1Notification(out, effectFile, 2);
			EffectAnimation ef = BasicObjectBuilders.loadEffect(effectFile);
			BasicCommands.playEffectAnimation(out, ef, tiles[1][2]);
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
			
		}
		

	}
	/*
	 * name: funcSleep
	 * using: Write a thread delay function as required
	 * input: int iMs
	 * coder: MK.L   2023.2.7 Xiuqi Liu 2773750
	 * change:
	 */	
	public static void funcSleep(int iMs)
	{
		try {
				Thread.sleep(iMs);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
	}
	

	/*
	 * name: getRandomArrangement
	 * using: This function first creates an array of 10 numbers, each of which appears twice, 
	and then uses the Random class to break up the elements of the array 
	into a random order. The function returns the resulting array.
	 * input: int iMs
	 * coder: MK.L   2023.2.7 Xiuqi Liu 2773750
	 * change:
	 */	
	    public static int[] getRandomArrangement() {
	        int[] iID = new int[20];
	        for (int i = 0; i < 10; i++) 
	        {
	        	iID[i*2] = i;
	        	iID[i*2 + 1] = i;
	        }
	        
	        Random rand = new Random();
	        for (int i = 0; i < iID.length; i++) 
	        {
	            int randomIndex = rand.nextInt(iID.length);
	            int temp = iID[i];
	            iID[i] = iID[randomIndex];
	            iID[randomIndex] = temp;
	        }
	        
	        return iID;
	    }

		/*
		 * name: getRandomArrangement
		 * using: This function first creates an array of 10 numbers, each of which appears twice, 
		and then uses the Random class to break up the elements of the array 
		into a random order. The function returns the resulting array.
		 * input: int iMs
		 * coder: MK.L   2023.2.7 Xiuqi Liu 2773750
		 * change:
		 */	
	    /*
		    public static int[] getRandomArrangement() {
		        int[] iID = new int[20];
		        for (int i = 0; i < 10; i++) 
		        {
		        	iID[i*2] = i;
		        	iID[i*2 + 1] = i;
		        }
		        
		        Random rand = new Random();
		        for (int i = 0; i < iID.length; i++) 
		        {
		            int randomIndex = rand.nextInt(iID.length);
		            int temp = iID[i];
		            iID[i] = iID[randomIndex];
		            iID[randomIndex] = temp;
		        }
		        
		        return iID;
		    }
		    */
	    // -- iType 0 anen 1 en
	    public static int func_EnterTurn(int iType,int iId ,ActorRef out) 
	    {
	    	int iRet = 0;
	    	if(iId == 0)// -- player
	    	{
		    	if(iType == 1)
		    	{
		    		BasicCommands.addPlayer1Notification(out, "I am finished", 2);

		    	}
	    	}

	        return iRet;
	    }
// -- 生成棋盘
	    public static Tile[][] func_CreateBoard(Tile[][] aTiles,ActorRef out) 
	    {
			Tile[][] tiles = aTiles;
			// -- 棋盘
			for(int i = 0;i<5;i++)
			{
				for(int j = 0;j<9;j++)
				{
					
					tiles[j][i]= BasicObjectBuilders.loadTile(j, i);
					BasicCommands.drawTile(out, tiles[j][i], 0);
				}
			}
			return tiles;
	    }

	    
	    
	    
	// -- 输入一个xy输出tile func
	    
}
