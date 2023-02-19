package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import akka.actor.ActorRef;
import commands.BasicCommands;
import logic.TheOneConfig.Configuration;
import logic.TheOneConfig.Deck;
import structures.basic.Card;
import structures.basic.EffectAnimation;
import structures.basic.Player;
import structures.basic.Tile;
import structures.basic.Unit;
import structures.basic.UnitAnimationType;
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
	//标记每个坐标，检测状态是否unit或者player--------------------------------------
	public static TheOneSquare[][] arry=new TheOneSquare[9][5];
	public static TheOnelist theOnelist = new TheOnelist();	// -- 6 limit
	public static String configdata = "";
	public static TheOnelist theOnelistToAll = new TheOnelist();
	public static Unit[] typeUnit  = new Unit[4096];
	public static Tile[][] publicTiles = new Tile[9][5];
	public static String temptest = "555555";;

	public static Player humanPlayer = new Player(20, 4);
	public static int[] iBlood[];
	public static Player aiPlayer = new Player(20, 4);

	//public static int iGID = 0;
	public static void modelInit_Begin(ActorRef out) throws IOException {
		
		BasicCommands.addPlayer1Notification(out, "Hello loser", 2);
		//try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();} // these cause processing to wait for a number of milliseconds.
		
		// -- 棋盘初始化
		publicTiles = func_CreateBoard(publicTiles,out);
		//sqare初始化-----------------------------------------------
		for (int i1 = 0; i1 < 9; i1++)
		{
			for (int i = 0; i < 5; i++)
			{
				//Storage square object to Two-dimensional arrays
				arry[i1][i]=new TheOneSquare(i1,i,false,false);
			}
		}
		// -- 创建AI线程
		Thread AICalculate = new Thread();

		
		
		// -- 血
		//Player humanPlayer = new Player(20, 4);
		BasicCommands.setPlayer1Health(out, humanPlayer);
		//Player aiPlayer = new Player(20, 4);
		BasicCommands.setPlayer2Health(out, aiPlayer);
		
		// -- Mana

		humanPlayer.setMana(4);
		BasicCommands.setPlayer1Mana(out, humanPlayer);
		aiPlayer.setMana(4);
		BasicCommands.setPlayer2Mana(out, aiPlayer);
		
		// -- 初始化主怪
		Tile humanTile = BasicObjectBuilders.loadTile(1, 2);
		//--------------------------------------
		funcSquareStatusUpdate(1,2,30,1);
		typeUnit[30] = BasicObjectBuilders.loadUnit(StaticConfFiles.humanAvatar,
				TheOneConfig.iUnitID, Unit.class);
		TheOneConfig.iUnitID++;
		typeUnit[30].setPositionByTile(humanTile); 

		BasicCommands.drawUnit(out, typeUnit[30], humanTile);
		funcSleep(50);
		BasicCommands.setUnitHealth(out, typeUnit[30], 20);
		BasicCommands.setUnitAttack(out, typeUnit[30], 2);
		
		Tile aiTile = BasicObjectBuilders.loadTile(7, 2);
		//--------------------------------------
	
		funcSquareStatusUpdate(7,2,31,2);
		typeUnit[31] = BasicObjectBuilders.loadUnit(StaticConfFiles.aiAvatar,
				TheOneConfig.iUnitID, Unit.class);
		TheOneConfig.iUnitID++;
		typeUnit[31].setPositionByTile(aiTile); 
		BasicCommands.drawUnit(out, typeUnit[31], aiTile);
		funcSleep(50);
		BasicCommands.setUnitHealth(out, typeUnit[31], 20);
		BasicCommands.setUnitAttack(out, typeUnit[31], 2);

		
		/*
		
		// Move unit, default, horizontal then vertical
		//func_UnitMove(out, aiUnit, tiles[2][4],false);

		for (UnitAnimationType value : UnitAnimationType.values()) {
			func_UnitAnimation(out, aiUnit,value);
			BasicCommands.addPlayer1Notification(out, "value"+value, 2);
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
			
		}
		*/
		//BasicObjectBuilders.loadCard(theOnelistToAll.get(j).
		//getCardConfig()
		//funcSleep(50000);
		

		/*	初始化手牌	 test target*/
        /*
		Card hailstone_golem = BasicObjectBuilders.loadCard(StaticConfFiles.c_hailstone_golem, 0, Card.class);
		Card ironcliff_guardian = BasicObjectBuilders.loadCard(StaticConfFiles.c_ironcliff_guardian, 1, Card.class);
		Card pureblade_enforcer = BasicObjectBuilders.loadCard(StaticConfFiles.c_pureblade_enforcer, 2, Card.class);
		Card silverguard_knight = BasicObjectBuilders.loadCard(StaticConfFiles.c_silverguard_knight, 3, Card.class);
		
		theOnelist.add(hailstone_golem);
		theOnelist.add(ironcliff_guardian);
		theOnelist.add(pureblade_enforcer);
		theOnelist.add(silverguard_knight);
*/

		funcDeckInit(TheOneConfig.gCardNumAll[0],10);
		func_CardListUpdate(out);
		/*
		int NO = TheOneFunc_Logic.theOnelistToAll.get(1).getiNO();
		int type = TheOneFunc_Logic.theOnelistToAll.get(1).getiType();
		String stype = TheOneFunc_Logic.theOnelistToAll.get(1).getCardConfig();
		BasicCommands.addPlayer1Notification(out,"  "+ type+" TD "+NO +temptest, 20);
		*/
		// -- test dele card
    	//theOnelist.remove(2);
		//BasicCommands.drawCard(out, theOnelist.remove(2), 1, 0);
		/*
		BasicCommands.drawCard(out, hailstone_golem, 1, 0);
		BasicCommands.drawCard(out, ironcliff_guardian, 2, 0);
		BasicCommands.drawCard(out, pureblade_enforcer, 3, 0);
		BasicCommands.drawCard(out, silverguard_knight, 4, 0);
		*/

        
		//func_UnitMove(out, aiUnit, tiles[0][0],true);
		

		
		boolean gameStatus = true;
		while(gameStatus)
		{
			break;
			
		}
		/*
		// Effects

		for (String effectFile : TheOneConfig.effects) {
			BasicCommands.addPlayer1Notification(out, effectFile, 2);
			EffectAnimation ef = BasicObjectBuilders.loadEffect(effectFile);
			BasicCommands.playEffectAnimation(out, ef, tiles[1][2]);
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
			
		}
		*/

	}
	
	public static int compareConfigToNO(String strConfig) {
		int iRet = 99;
		for (int i = 0; i < 10; i++) {
		    if (strConfig.equals(theOnelistToAll.get(i).getUnitConfig()))
		    {
		    	iRet = theOnelistToAll.get(i).getiNO();
		    }
		}
		return iRet;

	}
	public static int compareConfigToHealth(String strConfig) {
		int iRet = 99;
		for (int i = 0; i < 10; i++) {
		    if (strConfig.equals(theOnelistToAll.get(i).getUnitConfig()))
		    {
		    	iRet = theOnelistToAll.get(i).getiHealth();
		    }
		}
		return iRet;

	}
	public static int compareConfigToAttack(String strConfig) {
		int iRet = 99;
		for (int i = 0; i < 10; i++) {
		    if (strConfig.equals(theOnelistToAll.get(i).getUnitConfig()))
		    {
		    	iRet = theOnelistToAll.get(i).getiAttack();
		    }
		}
		return iRet;

	}
	/*
	 * name: funcDeckInit
	 * using: Deck Initiation
	 * input: iCardNum int iDeck
	 * coder: MK.L   2023.2.7 Xiuqi Liu 2773750
	 * change:
	 */	
	public static void funcDeckInit(int iCardNum, int iDeck) throws IOException {
	    LinkedList<Card> allCards = readIniConfiguration("app/Cardconfig.ini");
	    theOnelist.clear();
	    theOnelistToAll.clear();
	    for (int i = 0; i < iDeck; i++) {
            Card card = allCards.get(i);
            theOnelistToAll.add(BasicObjectBuilders.loadCard(
                    card.getCardConfig(), i, Card.class));
            theOnelistToAll.get(i).setCardConfig(card.getCardConfig());
            theOnelistToAll.get(i).setUnitConfig(card.getUnitConfig());
            theOnelistToAll.get(i).setiNO(card.getiNO());
            theOnelistToAll.get(i).setiType(card.getiType());
            theOnelistToAll.get(i).setiHealth(card.getiHealth());
            theOnelistToAll.get(i).setiAttack(card.getiAttack());
            theOnelistToAll.get(i).setManacost(card.getManacost());
	    }

	    for (int j = 0; j < iCardNum; j++) {
	        theOnelist.add(theOnelistToAll.get(j));
	    }
	    TheOneConfig.gDeckCardNumAll[0] = 3;
	}
	public static LinkedList<Card> readIniConfiguration(String filename) throws IOException {
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(filename));
	        String line;
	        LinkedList<Card> cards = new LinkedList<>();
	        Card currentCard = null;

	        while ((line = reader.readLine()) != null) {
	            int semicolonIndex = line.indexOf(";");
	            if (semicolonIndex >= 0) {
	                line = line.substring(0, semicolonIndex);
	            }
	            line = line.trim();

	            if (line.startsWith("[")) {
	                if (currentCard != null) {
	                    cards.add(currentCard);
	                }
	                currentCard = new Card();
	            } else if (line.contains("=")) {
	                String[] parts = line.split("=");
	                String paramName = parts[0].trim();
	                String paramValue = parts[1].trim();

	                if (paramName.equals("CardConfig")) {
	                    currentCard.setCardConfig(paramValue);
	                } else if (paramName.equals("NO")) {
	                    currentCard.setiNO(Integer.parseInt(paramValue));
	                } else if (paramName.equals("Name")) {
	                    currentCard.setCardname(paramValue);
	                } else if (paramName.equals("UnitConfig")) {
	                    currentCard.setUnitConfig(paramValue);
	                } else if (paramName.equals("Type")) {
	                    currentCard.setiType(Integer.parseInt(paramValue));
	                }else if (paramName.equals("mana")) {
	                    currentCard.setManacost(Integer.parseInt(paramValue));
	                }else if (paramName.equals("attack")) {
	                    currentCard.setiAttack(Integer.parseInt(paramValue));
	                }else if (paramName.equals("health")) {
	                    currentCard.setiHealth(Integer.parseInt(paramValue));
	                }
	            } else {
	                if (currentCard != null) {
	                    cards.add(currentCard);
	                    currentCard = null;
	                }
	            }
	        }

	        if (currentCard != null) {
	            cards.add(currentCard);
	        }

	        return cards;
	    } finally {
	        if (reader != null) {
	            reader.close();
	        }
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
		    		func_CardListadd(1,0);
		    		func_CardListUpdate(out);
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
			aTiles = tiles;
			return tiles;
	    }
		/*
		 * name: 单位卡牌移动逻辑 汉
		 * using: 
		 * input: yfirst true y false x
		 * coder: MK.L   2023.2.15 Xiuqi Liu 2773750
		 * change:
		 */	
	    public static int func_UnitMove(ActorRef out, Unit unit, Tile tile, boolean yfirst)
	    {
	    	int iRet = 0;
	    	
	    	BasicCommands.moveUnitToTile(out, unit, tile,yfirst);

	    	return iRet;
	    }
		/*
		 * name: 单位卡牌攻击逻辑 汉
		 * using: 
		 * input: 
		 * 	idle,
			death,
			attack,
			move,
			channel,
			hit
		 * coder: MK.L   2023.2.15 Xiuqi Liu 2773750
		 * change:
		 */	
	    public static int func_UnitAnimation(ActorRef out, Unit unit, UnitAnimationType animationToPlay)
	    {
	    	int iRet = 0;
	    	if(animationToPlay == UnitAnimationType.idle)
	    	{
	    		BasicCommands.playUnitAnimation(out, unit, animationToPlay);
	    	}
	    	else if(animationToPlay == UnitAnimationType.death)
	    	{
	    		BasicCommands.playUnitAnimation(out, unit, animationToPlay);
	    	}
	    	else if(animationToPlay == UnitAnimationType.attack)
	    	{
	    		BasicCommands.playUnitAnimation(out, unit, animationToPlay);
	    	}
	    	else if(animationToPlay == UnitAnimationType.move)
	    	{
	    		BasicCommands.playUnitAnimation(out, unit, animationToPlay);
	    	}
	    	else if(animationToPlay == UnitAnimationType.channel)
	    	{
	    		BasicCommands.playUnitAnimation(out, unit, animationToPlay);
	    	}
	    	else if(animationToPlay == UnitAnimationType.hit)
	    	{
	    		BasicCommands.playUnitAnimation(out, unit, animationToPlay);
	    	}
	    	return iRet;
	    }
	    
	    // -- update card list
	    public static void func_CardListUpdate(ActorRef out)
	    {
			for(int i = 1; i <= 6; i++) 
			{
					BasicCommands.deleteCard(out , i);
			}
			for(int i = 1; i <= theOnelist.getSize(); i++) 
			{
				if(theOnelist.get(i-1) != null)
				{
					BasicCommands.drawCard(out, theOnelist.get(i-1), i, 0);
				}
				
			}
	    }
	    // -- update card 手牌增加几张
	    public static void func_CardListadd(int iNum,int iNo)
	    {
		    for (int j = 0; j < iNum; j++) {
		        theOnelist.add(theOnelistToAll.get(TheOneConfig.gDeckCardNumAll[0]));
		        TheOneConfig.gDeckCardNumAll[0]++;
		    }
	    }
	    // -- 点击卡牌高亮函数
	    public static void func_CardHighLight(ActorRef out,Card cCard,int iPosition)
	    {
			for(int i = 1; i <= 6; i++) 
			{
				if(theOnelist.get(i-1) != null)
				{
					BasicCommands.drawCard(out, theOnelist.get(i-1), i, 0);
				}
				
			}
			
			BasicCommands.drawCard(out, cCard, iPosition, 1);
			TheOneConfig.iCardNeedToPut = iPosition;
	    }

	//出牌的时候，更新蓝量函数----------------
	public static void updateManaWhenPutCard(ActorRef out, Player player,Card card,int iposition)
	{
		int manaCost = theOnelist.get(iposition-1).getManacost();
		if(player.getMana() >= manaCost)
		{
			player.setMana(player.getMana()-manaCost);
		}

	}

	//回合结束的时候的时候，更新蓝量函数----------------
	public static void updateManaWhenEndTurn(ActorRef out, Player player)
	{
		if(player.getMana()<=7 && player== logic.TheOneFunc_Logic.getHumanPlayer())
		{
			player.setMana(player.getMana()+2);
			BasicCommands.setPlayer1Mana(out, player);
		}
		else if(player.getMana()>7 && player== logic.TheOneFunc_Logic.getHumanPlayer())
		{
			player.setMana(9);
			BasicCommands.setPlayer1Mana(out, player);
		}
		else if(player.getMana()<=7 && player== logic.TheOneFunc_Logic.getAiPlayer())
		{
			player.setMana(player.getMana()+2);
			BasicCommands.setPlayer2Mana(out, player);
		}
		else if(player.getMana()>7 && player== logic.TheOneFunc_Logic.getAiPlayer())
		{
			player.setMana(9);
			BasicCommands.setPlayer2Mana(out, player);
		}
	}
			//更新血量函数----------------
			public static void updateHealth(ActorRef out, Unit unit, int x)
			{
				//BasicCommands.setUnitHealth(out, unit, );
			}


			//点击Unit显示可移动坐标-----------------
			public static void displayMovePosition(ActorRef out,int iX,int iY)
			{
				
				Tile[][] tiles = publicTiles;
				// -- 棋盘
				for(int i = 0;i<5;i++)
				{
					for(int j = 0;j<9;j++)
					{
						
						tiles[j][i]= BasicObjectBuilders.loadTile(j, i);
						BasicCommands.drawTile(out, tiles[j][i], 0);
						TheOneConfig.lowLightPosition[j][i]=0;
					}
				}
				funcSleep(50);
				/*
				for(int i = 0;i<5;i++)
				{
				   for(int j = 0;j<9;j++)
				   {
				      BasicCommands.drawTile(out, publicTiles[j][i], 0);
				   }
				}
				*/
				if(arry[iX][iY].isUnit() || arry[iX][iY].isPlayer())
				{
					
					TheOneConfig.clickUnit = typeUnit[arry[iX][iY].getiID()];
					for (int i = -2; i < 3; i++)
					{
						if(iX+i>=0 && iX+i<=8)
						{
							if(arry[iX + i][iY].isUnit() || arry[iX + i][iY].isPlayer())
							{
								BasicCommands.drawTile(out, tiles[iX + i][iY], 2);
								TheOneConfig.lowLightPosition[iX + i][iY]=1;
								
							}
							else
							{
								BasicCommands.drawTile(out, tiles[iX + i][iY], 1);
								TheOneConfig.lowLightPosition[iX + i][iY]=1;
							}
						}
						if(iY+i>=0 && iY+i<=4)
						{
							if(arry[iX][iY + i].isUnit() || arry[iX][iY + i].isPlayer())
							{
								BasicCommands.drawTile(out, tiles[iX][iY + i], 2);
								TheOneConfig.lowLightPosition[iX][iY + i]=1;
							}
							else
							{
								BasicCommands.drawTile(out, tiles[iX][iY + i], 1);
								TheOneConfig.lowLightPosition[iX][iY + i]=1;
							}

						}

					}
					if(iX-1>=0 && iY-1>=0)
					{
						if(arry[iX - 1][iY - 1].isUnit() || arry[iX - 1][iY - 1].isPlayer())
						{
							BasicCommands.drawTile(out, tiles[iX - 1][iY - 1], 2);
							TheOneConfig.lowLightPosition[iX - 1][iY - 1]=1;
						}
						else
						{
							BasicCommands.drawTile(out, tiles[iX - 1][iY - 1], 1);
							TheOneConfig.lowLightPosition[iX - 1][iY - 1]=1;
						}

					}
					if(iX+1<=8 && iY+1<=4)
					{
						if(arry[iX + 1][iY + 1].isUnit() || arry[iX + 1][iY + 1].isPlayer())
						{
							BasicCommands.drawTile(out, tiles[iX + 1][iY + 1], 2);
							TheOneConfig.lowLightPosition[iX + 1][iY + 1]=1;
						}
						else
						{
							BasicCommands.drawTile(out, tiles[iX + 1][iY + 1], 1);
							TheOneConfig.lowLightPosition[iX + 1][iY + 1]=1;
						}

					}
					if (iX-1>=0 && iY+1<=4)
					{
						if(arry[iX - 1][iY + 1].isUnit() || arry[iX - 1][iY + 1].isPlayer())
						{
							BasicCommands.drawTile(out, tiles[iX - 1][iY + 1], 2);
							TheOneConfig.lowLightPosition[iX - 1][iY + 1]=1;
						}
						else
						{
							BasicCommands.drawTile(out, tiles[iX - 1][iY + 1], 1);
							TheOneConfig.lowLightPosition[iX - 1][iY + 1]=1;
						}

					}
					if (iX+1<=8 && iY-1>=0)
					{
						if(arry[iX + 1][iY - 1].isUnit() || arry[iX + 1][iY - 1].isPlayer())
						{
							BasicCommands.drawTile(out, tiles[iX + 1][iY - 1], 2);
							TheOneConfig.lowLightPosition[iX + 1][iY - 1]=1;
						}
						else
						{
							BasicCommands.drawTile(out, tiles[iX + 1][iY - 1], 1);
							TheOneConfig.lowLightPosition[iX + 1][iY - 1]=1;
						}

					}
				}
				
			}
			// -- 更新square 状态
			public static void funcSquareStatusUpdate(int iX,int iY,int iID,int status)
			{
				arry[iX][iY].setPlayer(true);
				//arry[iX][iY].setUnit(true);
				arry[iX][iY].setiID(iID);
				arry[iX][iY].setSquareStatus(status);
			}

			
			public static void initPositionStatus(ActorRef out,int iX,int iY)
			{
				if(arry[iX][iY].isPlayer())
				{
					arry[iX][iY].setPlayer(false);
				}
				else if(arry[iX][iY].isUnit())
				{
					arry[iX][iY].setUnit(false);
				}
				arry[iX][iY].setSquareStatus(0);

			}



		//清除
		public static void clearMovePosition(ActorRef out,int iX,int iY)
		{

		}

	public static Player getHumanPlayer() {
		return humanPlayer;
	}

	public static Player getAiPlayer() {
		return aiPlayer;
	}
	    
	    
	// -- 输入一个xy输出tile func
	    
}
