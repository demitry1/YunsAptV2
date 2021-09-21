package yunsaptv2.aptinfo.entity;

public class AptInfoDetail {

	private String city_do;
	private String city_gu;
	private String dong_gu;
	private String apt_name;
	private String construction_year;
	private String address_road;
	private String address_jibun;
	private int dong_cnt;
	private int household_cnt;
	private String heating;
	private String corridor;
	private String constructor;
	private int park_tot;
	private String office_tel;

    
	public AptInfoDetail(String city_do, String city_gu, String dong_gu, String apt_name, String construction_year,
			String address_road, String address_jibun, int dong_cnt, int household_cnt, String heating, String corridor,
			String constructor, int park_tot, String office_tel) {
		super();
		this.city_do = city_do;
		this.city_gu = city_gu;
		this.dong_gu = dong_gu;
		this.apt_name = apt_name;
		this.construction_year = construction_year;
		this.address_road = address_road;
		this.address_jibun = address_jibun;
		this.dong_cnt = dong_cnt;
		this.household_cnt = household_cnt;
		this.heating = heating;
		this.corridor = corridor;
		this.constructor = constructor;
		this.park_tot = park_tot;
		this.office_tel = office_tel;
	}

	public AptInfoDetail() {

	}

	public String getApt_name() {
		return apt_name;
	}

	public void setApt_name(String apt_name) {
		this.apt_name = apt_name;
	}

	public String getConstruction_year() {
		return construction_year;
	}

	public void setConstruction_year(String construction_year) {
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

	public int getDong_cnt() {
		return dong_cnt;
	}

	public void setDong_cnt(int dong_cnt) {
		this.dong_cnt = dong_cnt;
	}

	public int getHousehold_cnt() {
		return household_cnt;
	}

	public void setHousehold_cnt(int household_cnt) {
		this.household_cnt = household_cnt;
	}

	public String getHeating() {
		return heating;
	}

	public void setHeating(String heating) {
		this.heating = heating;
	}

	public String getCorridor() {
		return corridor;
	}

	public void setCorridor(String corridor) {
		this.corridor = corridor;
	}

	public String getConstructor() {
		return constructor;
	}

	public void setConstructor(String constructor) {
		this.constructor = constructor;
	}

	public int getPark_tot() {
		return park_tot;
	}

	public void setPark_tot(int park_tot) {
		this.park_tot = park_tot;
	}

	public String getOffice_tel() {
		return office_tel;
	}

	public void setOffice_tel(String office_tel) {
		this.office_tel = office_tel;
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


}
