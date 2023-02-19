package logic;

import akka.actor.ActorRef;
import commands.BasicCommands;

import java.util.LinkedList;
import logic.TheOneFunc_Logic;
import structures.basic.Tile;
import structures.basic.Unit;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;
//-- create by MK.L 23.2.6
public class TheoneNet {
	/*
	 * name: informationTransfer
	 * using: Messaging interface for event and commond reception processing
	 * input: int mod int iX,int iY
	 * mod:0 <heart> 	1<cardClicked> 	2<endTurnClicked>	3<tileClicked>	
	 * 4<unitMoving>	5<unitStopped>	6<otherClicked>		77<default>
	 * 
	 * coder: MK.L   2023.2.6 Xiuqi Liu 2773750
	 * change:
	 */	
	public  static int NO;
	public  static int type;
	public static int informationTransfer(ActorRef out,int mod,int iX,int iY,int iPosion,int id)
	{
		int iRet = 0;
		// -- 需要增加范围判断
		if(mod == 0)
		{
			// -- heart
			TheOneConfig.iHeart = 1;// -- heart beat
		}
		else if(mod == 1)
		{
			// -- cardClicked
			// -- highlight
			TheOneFunc_Logic.func_CardHighLight(out,TheOneFunc_Logic.theOnelist.get(iPosion - 1)
					,iPosion);
			TheOneFunc_Logic.configdata = TheOneFunc_Logic.theOnelist.get(iPosion - 1).getUnitConfig();
			NO = TheOneFunc_Logic.compareConfigToNO(TheOneFunc_Logic.configdata);
			type = TheOneFunc_Logic.compareConfigToNO(TheOneFunc_Logic.configdata);
			BasicCommands.addPlayer1Notification(out, TheOneFunc_Logic.configdata+type+" TD "+NO , 20);

		}
		else if(mod == 2)
		{
			//改变回合状态，更新回合结束的蓝量
			if(TheOneConfig.roundStatus==1)
			{
				TheOneConfig.roundStatus=2;
				TheOneFunc_Logic.updateManaWhenEndTurn(out,TheOneFunc_Logic.getHumanPlayer());

			}else if(TheOneConfig.roundStatus==2)
			{
				TheOneConfig.roundStatus=1;
				TheOneFunc_Logic.updateManaWhenEndTurn(out,TheOneFunc_Logic.getAiPlayer());
			}

			// -- endTurnClicked
			TheOneConfig.iEnTurnClick = 0;
			// -- logic method
			
			TheOneFunc_Logic.func_EnterTurn(1,0,out);
			TheOneConfig.iEnTurnClick = 1;
			
		}
		else if(mod == 3)
		{
			//人类玩家回合
			//&&(iX != TheOneConfig.clickPosition[0] && iY != TheOneConfig.clickPosition[1])
			// -- tileClicked         (!TheOneFunc_Logic.arry[iX][iY].isUnit() && !TheOneFunc_Logic.arry[iX][iY].isPlayer())
			if(TheOneConfig.clickUnit != null  && TheOneConfig.clickPositionStatus == 1
					&& TheOneConfig.lowLightPosition[iX][iY]!=0
					&&(!TheOneFunc_Logic.arry[iX][iY].isUnit() && !TheOneFunc_Logic.arry[iX][iY].isPlayer())
					&& TheOneFunc_Logic.arry[TheOneConfig.clickPosition[0]][TheOneConfig.clickPosition[1]].getSquareStatus()==1
					&& TheOneConfig.roundStatus==1)
			{
				int ID = TheOneFunc_Logic.arry[TheOneConfig.clickPosition[0]][TheOneConfig.clickPosition[1]].getiID();
				TheOneFunc_Logic.func_UnitMove(out,TheOneConfig.clickUnit,
						TheOneFunc_Logic.publicTiles[iX][iY],true);
				//BasicCommands.addPlayer1Notification(out, "sdsd"+ID, 20);
				//TheOneFunc_Logic.funcSquareStatusUpdate(iX,iY,ID);

				BasicCommands.addPlayer1Notification(out, "sdsd2"+ID, 20);
				
				TheOneFunc_Logic.typeUnit[ID].
				setPositionByTile(TheOneFunc_Logic.publicTiles[TheOneConfig.clickPosition[0]]
						[TheOneConfig.clickPosition[1]]); 

				//luo 移动之后。清除上一个位置的状态
				TheOneFunc_Logic.initPositionStatus(out,TheOneConfig.clickPosition[0],TheOneConfig.clickPosition[1]);

				TheOneFunc_Logic.displayMovePosition(out,iX,iY);
				TheOneFunc_Logic.funcSquareStatusUpdate(iX,iY,ID,1);
				TheOneConfig.clickPositionStatus=0;
			}
			//AI回合
			else if(TheOneConfig.clickUnit != null  && TheOneConfig.clickPositionStatus == 1
					&& TheOneConfig.lowLightPosition[iX][iY]!=0
					&&(!TheOneFunc_Logic.arry[iX][iY].isUnit() && !TheOneFunc_Logic.arry[iX][iY].isPlayer())
					&& TheOneFunc_Logic.arry[TheOneConfig.clickPosition[0]][TheOneConfig.clickPosition[1]].getSquareStatus()==2
					&& TheOneConfig.roundStatus==2)
			{
				int ID = TheOneFunc_Logic.arry[TheOneConfig.clickPosition[0]][TheOneConfig.clickPosition[1]].getiID();
				TheOneFunc_Logic.func_UnitMove(out,TheOneConfig.clickUnit,
						TheOneFunc_Logic.publicTiles[iX][iY],true);
				//BasicCommands.addPlayer1Notification(out, "sdsd"+ID, 20);
				//TheOneFunc_Logic.funcSquareStatusUpdate(iX,iY,ID);

				BasicCommands.addPlayer1Notification(out, "sdsd2"+ID, 20);

				TheOneFunc_Logic.typeUnit[ID].
						setPositionByTile(TheOneFunc_Logic.publicTiles[TheOneConfig.clickPosition[0]]
								[TheOneConfig.clickPosition[1]]);

				//luo 移动之后。清除上一个位置的状态
				TheOneFunc_Logic.initPositionStatus(out,TheOneConfig.clickPosition[0],TheOneConfig.clickPosition[1]);

				TheOneFunc_Logic.displayMovePosition(out,iX,iY);
				TheOneFunc_Logic.funcSquareStatusUpdate(iX,iY,ID,2);
				TheOneConfig.clickPositionStatus=0;
			}

			else if(TheOneConfig.clickPositionStatus==0 && TheOneFunc_Logic.arry[iX][iY].getSquareStatus()==1
					&& TheOneConfig.roundStatus==1)
			{
				TheOneConfig.clickPosition[0]=iX;
				TheOneConfig.clickPosition[1]=iY;
				TheOneConfig.clickPositionStatus=1;
				TheOneFunc_Logic.displayMovePosition(out,iX,iY);
				//BasicCommands.addPlayer1Notification(out, "aaaa", 20);
			}
			else if(TheOneConfig.clickPositionStatus==0 && TheOneFunc_Logic.arry[iX][iY].getSquareStatus()==2
					&& TheOneConfig.roundStatus==2)
			{
				TheOneConfig.clickPosition[0]=iX;
				TheOneConfig.clickPosition[1]=iY;
				TheOneConfig.clickPositionStatus=1;
				TheOneFunc_Logic.displayMovePosition(out,iX,iY);
				//BasicCommands.addPlayer1Notification(out, "aaaa", 20);
			}


			if(TheOneConfig.iCardNeedToPut != 0)// -- 放卡
			{

				TheOneFunc_Logic.typeUnit[TheOneConfig.iUnitID] =BasicObjectBuilders.loadUnit
						(TheOneFunc_Logic.configdata, TheOneConfig.iUnitID, Unit.class);
				// -- set unit
				TheOneFunc_Logic.typeUnit[TheOneConfig.iUnitID]
				.setPositionByTile(TheOneFunc_Logic.publicTiles[iX][iY]); 
				BasicCommands.drawUnit(out, TheOneFunc_Logic.typeUnit[TheOneConfig.iUnitID]
						, TheOneFunc_Logic.publicTiles[iX][iY]);
				
				// -- 设置unit血量魔法
				TheOneFunc_Logic.funcSleep(50);
				BasicCommands.setUnitHealth(out, 
						TheOneFunc_Logic.typeUnit[TheOneConfig.iUnitID], 
						TheOneFunc_Logic.compareConfigToHealth(TheOneFunc_Logic.configdata));
				BasicCommands.setUnitAttack(out,
						TheOneFunc_Logic.typeUnit[TheOneConfig.iUnitID], 
						TheOneFunc_Logic.compareConfigToAttack(TheOneFunc_Logic.configdata));
				// -- spell 卡
				{
					
				}
				
				BasicCommands.drawCard(out, TheOneFunc_Logic.
						theOnelist.remove(TheOneConfig.iCardNeedToPut-1), 1, 0);
				
				TheOneFunc_Logic.func_CardListUpdate(out);
				TheOneConfig.iUnitID++;
				TheOneConfig.iCardNeedToPut = 0;
			}
			
		}
		else if(mod == 4)
		{
			// -- unitMoving
		}
		else if(mod == 5)
		{
			// -- unitStopped
		}
		else if(mod == 6)
		{
			// -- other click
			TheOneFunc_Logic.func_CardListUpdate(out);
			TheOneConfig.iCardNeedToPut = 0;
			TheOneConfig.clickUnit = null;
		}
		else
		{
			// -- default
		}
		return iRet;
	}
}
