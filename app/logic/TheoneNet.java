package logic;
//-- create by MK.L 23.2.6
public class TheoneNet {
	/*
	 * name: informationTransfer
	 * using: Messaging interface for event and commond reception processing
	 * input: int mod int iX,int iY
	 * mod:0 <heart> 	1<cardClicked> 	2<endTurnClicked>	3<tileClicked>	
	 * 4<unitMoving>	5<unitStopped>
	 * 
	 * coder: MK.L   2023.2.6 Xiuqi Liu 2773750
	 * change:
	 */	
	public int informationTransfer(int mod,int iX,int iY,int iPosion,int id)
	{
		int iRet = 0;
		if(mod == 0)
		{
			// -- heart
		}
		else if(mod == 1)
		{
			// -- cardClicked
		}
		else if(mod == 2)
		{
			// -- endTurnClicked
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
		else
		{
			// -- default
		}
		return iRet;
	}
}
