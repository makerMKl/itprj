package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import structures.basic.Card;
import structures.basic.Unit;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

// -- create by MK.L 23.2.6
public class TheOneConfig {
// -- 写本地 excel ini 全局变量等
	public static int iEnTurnClick = 1;	 // -- 0:en 1 anen
	public static int iHeart = 0;	 // -- 0:en 1 anen
	
	public static String[] effects = {
			StaticConfFiles.f1_buff,
			StaticConfFiles.f1_inmolation,
			StaticConfFiles.f1_martyrdom,
			StaticConfFiles.f1_summon
	};
	public static int iCardNeedToPut = 0;	 // -- 0: unenable 1-6
	public static int iUnitID = 0;	 // -- 0: unenable 1-
	public static String[][] gCardToUnit = {};
	public static String[][] gCardToID = {};
	public static int gClickUnitStatus = 0;	 // -- 0: null one 1
	public static Unit clickUnit = new Unit();	 // -- 0: null one 1
	public static int clickTemp = 0;
	public static int[] clickPosition = new int[2];// --记录移动的初始坐标
	public static int clickPositionStatus =0;// -- 0表示需要记录开始坐标，1表示不用记录
	 public static int[] gCardNumAll = {3,3};// -- 手牌每轮上限
	 public static int[] gDeckCardNumAll = {0,0};// -- 手牌每轮上限
	public static int[][] lowLightPosition = new int[9][5];//标记不可移动块，1表示可以移动，0表示不能移动

	public static int roundStatus = 1;// 1表示玩家，2表示AI
	public void func_CardToUnitInit()
	{
		gCardToUnit[0][0] = StaticConfFiles.u_comodo_charger;
		gCardToUnit[0][1] = StaticConfFiles.u_azure_herald;
		gCardToUnit[0][2] = StaticConfFiles.u_azurite_lion;
		gCardToUnit[0][3] = StaticConfFiles.u_fire_spitter;
		gCardToUnit[0][4] = StaticConfFiles.u_hailstone_golem;
		gCardToUnit[0][5] = StaticConfFiles.u_ironcliff_guardian;
		gCardToUnit[0][6] = StaticConfFiles.u_pureblade_enforcer;
		gCardToUnit[0][7] = StaticConfFiles.u_silverguard_knight;

		gCardToUnit[1][0] = StaticConfFiles.u_blaze_hound;
		gCardToUnit[1][1] = StaticConfFiles.u_bloodshard_golem;
		gCardToUnit[1][2] = StaticConfFiles.u_hailstone_golemR;
		gCardToUnit[1][3] = StaticConfFiles.u_planar_scout;
		gCardToUnit[1][4] = StaticConfFiles.u_pyromancer;
		gCardToUnit[1][5] = StaticConfFiles.u_rock_pulveriser;
		gCardToUnit[1][6] = StaticConfFiles.u_serpenti;
		gCardToUnit[1][7] = StaticConfFiles.u_windshrike;
		
		gCardToUnit[2][0] = StaticConfFiles.f1_inmolation;
		gCardToUnit[2][1] = StaticConfFiles.f1_buff;
		gCardToUnit[2][2] = StaticConfFiles.f1_martyrdom;
		gCardToUnit[2][3] = StaticConfFiles.f1_projectiles;
		gCardToUnit[2][4] = StaticConfFiles.f1_summon;
		
	}

	public void func_CardToIDInit()
	{
		gCardToID[0][0] = StaticConfFiles.c_comodo_charger;
		gCardToID[0][1] = StaticConfFiles.c_azure_herald;
		gCardToID[0][2] = StaticConfFiles.c_azurite_lion;
		gCardToID[0][3] = StaticConfFiles.c_fire_spitter;
		gCardToID[0][4] = StaticConfFiles.c_hailstone_golem;
		gCardToID[0][5] = StaticConfFiles.c_ironcliff_guardian;
		gCardToID[0][6] = StaticConfFiles.c_pureblade_enforcer;
		gCardToID[0][7] = StaticConfFiles.c_silverguard_knight;
		
		gCardToID[0][8] = StaticConfFiles.c_truestrike;
		gCardToID[0][9] = StaticConfFiles.c_sundrop_elixir;
		
		gCardToID[1][0] = StaticConfFiles.c_blaze_hound;
		gCardToID[1][1] = StaticConfFiles.c_bloodshard_golem;
		gCardToID[1][2] = StaticConfFiles.c_hailstone_golem;
		gCardToID[1][3] = StaticConfFiles.c_planar_scout;
		gCardToID[1][4] = StaticConfFiles.c_pyromancer;
		gCardToID[1][5] = StaticConfFiles.c_rock_pulveriser;
		gCardToID[1][6] = StaticConfFiles.c_serpenti;
		gCardToID[1][7] = StaticConfFiles.c_windshrike;

		gCardToID[1][8] = StaticConfFiles.c_staff_of_ykir;
		gCardToID[1][9] = StaticConfFiles.c_entropic_decay;
		
	}
    public static class Deck {
        public List<Card> cards;

        public Deck() {
            this.cards = new ArrayList<>();
        }

        public void addCard(Card card) {
            this.cards.add(card);
        }
    }
    public static class Configuration {
        public List<Deck> decks;

        public Configuration() {
            this.decks = new ArrayList<>();
        }

        public void addDeck(Deck deck) {
            this.decks.add(deck);
        }
    }
}
