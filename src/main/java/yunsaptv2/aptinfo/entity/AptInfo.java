package yunsaptv2.aptinfo.entity;

public class AptInfo {

	private String city_do;
	private String city_gu;
	private String dong_gu;
	private String dong;
	private String apt_name;
	private int construction_year;
	private String address_road;
	private String address_jibun;
	
	public AptInfo() {
		
	}

	public AptInfo(String city_do, String city_gu, String dong_gu, String dong, String apt_name, int construction_year,
			String address_road, String address_jibun) {
		super();
		this.city_do = city_do;
		this.city_gu = city_gu;
		this.dong_gu = dong_gu;
		this.dong = dong;
		this.apt_name = apt_name;
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
	
}
