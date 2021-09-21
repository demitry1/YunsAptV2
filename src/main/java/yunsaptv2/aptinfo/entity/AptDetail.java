package yunsaptv2.aptinfo.entity;

import java.util.Date;

public class AptDetail {

	private Date contract_date;
	private String contract_ym;
	private int square;
	private int scale;
	private int trade_price;
	private int floor;
	private String kind;
	private int deposit;
	private int monthrent;	
	
	public AptDetail() {
		
	}

	public AptDetail(Date contract_date, String contract_ym, int square, int scale, int trade_price, int floor,
			String kind, int deposit, int monthrent) {
		this.contract_date = contract_date;
		this.contract_ym = contract_ym;
		this.square = square;
		this.scale = scale;
		this.trade_price = trade_price;
		this.floor = floor;
		this.kind = kind;
		this.deposit = deposit;
		this.monthrent = monthrent;
	}

	public Date getContract_date() {
		return contract_date;
	}

	public void setContract_date(Date contract_date) {
		this.contract_date = contract_date;
	}

	public String getContract_ym() {
		return contract_ym;
	}

	public void setContract_ym(String contract_ym) {
		this.contract_ym = contract_ym;
	}

	public int getSquare() {
		return square;
	}

	public void setSquare(int square) {
		this.square = square;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public int getTrade_price() {
		return trade_price;
	}

	public void setTrade_price(int trade_price) {
		this.trade_price = trade_price;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	public int getMonthrent() {
		return monthrent;
	}

	public void setMonthrent(int monthrent) {
		this.monthrent = monthrent;
	}


	
}
