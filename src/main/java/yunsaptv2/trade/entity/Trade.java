package yunsaptv2.trade.entity;

public class Trade {

	private String trade_dd;
	private int trade_cnt;
	private int change_cnt;
	private double change_rate ;
	
	public Trade() {
		
	}

	public Trade(String trade_dd, int trade_cnt, int change_cnt, double change_rate) {
		super();
		this.trade_dd = trade_dd;
		this.trade_cnt = trade_cnt;
		this.change_cnt = change_cnt;
		this.change_rate = change_rate;
	}

	public String getTrade_dd() {
		return trade_dd;
	}

	public void setTrade_dd(String trade_dd) {
		this.trade_dd = trade_dd;
	}


	public int getTrade_cnt() {
		return trade_cnt;
	}

	public void setTrade_cnt(int trade_cnt) {
		this.trade_cnt = trade_cnt;
	}

	public int getChange_cnt() {
		return change_cnt;
	}

	public void setChange_cnt(int change_cnt) {
		this.change_cnt = change_cnt;
	}

	public double getChange_rate() {
		return change_rate;
	}

	public void setChange_rate(double change_rate) {
		this.change_rate = change_rate;
	}

	@Override
	public String toString() {
		return "Trade [trade_dd=" + trade_dd +  ", trade_cnt="
				+ trade_cnt + ", change_cnt=" + change_cnt + ", change_rate=" + change_rate + "]";
	}
	
	
}
