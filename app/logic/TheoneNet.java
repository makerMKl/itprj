package logic;

import akka.actor.ActorRef;

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
			
			
			
		}
		else if(mod == 2)
		{
			// -- endTurnClicked
			TheOneConfig.iEnTurnClick = 0;
			// -- logic method
			TheOneFunc_Logic.func_EnterTurn(1,0,out);
			TheOneConfig.iEnTurnClick = 1;
			
		}
		else if(mod == 3)
		{
			// -- tileClicked
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
		}
		else
		{
			// -- default
		}
		return iRet;
	}
}
