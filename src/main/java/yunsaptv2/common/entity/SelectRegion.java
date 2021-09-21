package yunsaptv2.common.entity;

public class SelectRegion {

	private String region;

	public SelectRegion() {
		
	}
	
	public SelectRegion(String region) {
		super();
		this.region = region;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return region+'^';
	}
	
}
