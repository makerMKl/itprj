package logic;

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
}
