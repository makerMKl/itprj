package logic;

import structures.basic.Card;
import structures.basic.Unit;

//棋盘类，检测每个坐标是否是unit或者player
public class TheOneSquare
{
    private int row;
    private int column;
    private boolean isUnit;
    private boolean isPlayer;
    private int iID;
    private int squareStatus;//1表示是玩家的unit,2表示是AI的unit

    public int getSquareStatus() {
        return squareStatus;
    }

    public void setSquareStatus(int squareStatus) {
        this.squareStatus = squareStatus;
    }

	private Unit[][] unit = new Unit[9][5];

    public Unit[][] getUnit() {
        return unit;
    }

    public void setUnit(Unit[][] unit) {
        this.unit = unit;
    }

    public TheOneSquare(int row, int column, boolean isUnit, boolean isPlayer) {
        this.row = row;
        this.column = column;
        this.isUnit = isUnit;
        this.isPlayer = isPlayer;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
    public int getiID() {
		return iID;
	}

	public void setiID(int iID) {
		this.iID = iID;
	}
    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isUnit() {
        return isUnit;
    }

    public void setUnit(boolean unit) {
        isUnit = unit;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }
}
