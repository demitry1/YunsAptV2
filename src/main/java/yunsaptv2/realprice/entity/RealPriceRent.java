package yunsaptv2.realprice.entity;

import java.util.Date;

public class RealPriceRent {

	private String city_do;
	private String city_gu;
	private String dong_gu;
	private String dong;
	private String apt_name;
	private Date contract_date;
	private String contract_ym;
	private String kind;
	private int square;
	private int scale;
	private int deposit;
	private int monthrent;
	private int floor;
	private int construction_year;
	private String address_road;
	private String address_jibun;
	
	public RealPriceRent() {
		
	}

	public RealPriceRent(String city_do, String city_gu, String dong_gu, String dong, String apt_name,
			Date contract_date, String contract_ym, String kind, int square, int scale, int deposit, int monthrent,
			int floor, int construction_year, String address_road, String address_jibun) {
		super();
		this.city_do = city_do;
		this.city_gu = city_gu;
		this.dong_gu = dong_gu;
		this.dong = dong;
		this.apt_name = apt_name;
		this.contract_date = contract_date;
		this.contract_ym = contract_ym;
		this.kind = kind;
		this.square = square;
		this.scale = scale;
		this.deposit = deposit;
		this.monthrent = monthrent;
		this.floor = floor;
		this.construction_year = construction_year;
		this.address_road = address_road;
		this.address_jibun = address_jibun;
	}

	public String getCity_do() {
		return city_do;
	}

	public void setCity_do(String city_do) {
		this.city_do = city_do;
	}

	public String getCity_gu() {
		return city_gu;
	}

	public void setCity_gu(String city_gu) {
		this.city_gu = city_gu;
	}

	public String getDong_gu() {
		return dong_gu;
	}

	public void setDong_gu(String dong_gu) {
		this.dong_gu = dong_gu;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getApt_name() {
		return apt_name;
	}

	public void setApt_name(String apt_name) {
		this.apt_name = apt_name;
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

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
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

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getConstruction_year() {
		return construction_year;
	}

	public void setConstruction_year(int construction_year) {
		this.construction_year = construction_year;
	}

	public String getAddress_road() {
		return address_road;
	}

	public void setAddress_road(String address_road) {
		this.address_road = address_road;
	}

	public String getAddress_jibun() {
		return address_jibun;
	}

	public void setAddress_jibun(String address_jibun) {
		this.address_jibun = address_jibun;
	}

	@Override
	public String toString() {
		return "RealPriceRent [city_do=" + city_do + ", city_gu=" + city_gu + ", dong_gu=" + dong_gu + ", dong=" + dong
				+ ", apt_name=" + apt_name + ", contract_date=" + contract_date + ", contract_ym=" + contract_ym
				+ ", kind=" + kind + ", square=" + square + ", scale=" + scale + ", deposit=" + deposit + ", monthrent="
				+ monthrent + ", floor=" + floor + ", construction_year=" + construction_year + ", address_road="
				+ address_road + ", address_jibun=" + address_jibun + "]";
	}

	
}
