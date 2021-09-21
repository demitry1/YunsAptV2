package yunsaptv2.notice.entity;

import java.util.Date;

public class NoticeView extends Notice {
	private int cmtCount;
	
	public NoticeView() {		
	}
	
	public NoticeView(int id, String title, String writer_id, Date regdate, String hit, String files, boolean pub, int cmtCount) {
		super(id, title, writer_id, regdate, hit, files, "", pub);
		this.cmtCount = cmtCount;
	}

	public int getCmtCount() {
		return cmtCount;
	}

	public void setCmtCount(int cmtCount) {
		this.cmtCount = cmtCount;
	}
}
