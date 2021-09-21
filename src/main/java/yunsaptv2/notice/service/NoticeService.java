package yunsaptv2.notice.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import yunsaptv2.common.service.CommonService;
import yunsaptv2.notice.entity.Notice;
import yunsaptv2.notice.entity.NoticeView;

public class NoticeService {
	private CommonService common;
	
	public NoticeService() {
		common = new CommonService();
	}

	public List<NoticeView> getNoticeList() throws ClassNotFoundException, NamingException {
		return getNoticeList("title", "", 1);
	}

	public List<NoticeView> getNoticeList(int page) throws ClassNotFoundException, NamingException {
		return getNoticeList("title", "", page);
	}

	public List<NoticeView> getNoticeList(String field, String query, int page)
			throws NamingException, ClassNotFoundException {

		List<NoticeView> list = new ArrayList<NoticeView>();

		String sql = "SELECT * FROM ( " + "    SELECT ROWNUM NUM, N.* " + "    FROM (SELECT * FROM APT_NOTICE_VIEW "
				+ "    WHERE " + field + " LIKE ? ORDER BY REGDATE DESC ) N ) " + " WHERE NUM BETWEEN ? AND ? ";
//   1, 11, 21, 31  -> an = 1 + (page-1)*10	
//   10, 20, 30, 40 -> page * 10
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = common.getConnection(); 
			st = con.prepareStatement(sql);
			st.setString(1, "%" + query + "%");
			st.setInt(2, 1 + (page - 1) * 10);
			st.setInt(3, page * 10);

			rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String writer_id = rs.getString("writer_id");
				Date regdate = rs.getDate("regdate");
				String hit = rs.getString("hit");
				String files = rs.getString("files");
				int cmtCount = rs.getInt("cmt_count");
				boolean pub = rs.getBoolean("pub");

				NoticeView n = new NoticeView(id, title, writer_id, regdate, hit, files, pub, cmtCount);
				list.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close();} catch (Exception e2) {}
			if(st != null) try { st.close();} catch (Exception e2) {}
			if(con != null) try { con.close();} catch (Exception e2) {}
		}

		return list;
	}

	public Notice getNotice(int id, int hitCnt) throws NamingException, ClassNotFoundException {
		Notice notice = null;

		String sql = "SELECT * FROM APT_NOTICE WHERE ID = ?";  // 세부조회
		String sql_2 = "update apt_notice SET hit = ? where id = ?";  // 조회카운트 증가

		Connection con = null;
		PreparedStatement st = null;
		PreparedStatement st2 = null;
		ResultSet rs = null;
		try {
			con = common.getConnection(); 
			st = con.prepareStatement(sql);
			st2 = con.prepareStatement(sql_2);
			st.setInt(1, id);
			rs = st.executeQuery();
            int newHit = 0;
			// 공지사항 상세조회 
			if (rs.next()) {
				int nid = rs.getInt("id");
				String title = rs.getString("title");
				String writer_id = rs.getString("writer_id");
				Date regdate = rs.getDate("regdate");
				String hit = rs.getString("hit");
				String files = rs.getString("files");
				String content = rs.getString("content");
				boolean pub = rs.getBoolean("pub");
				newHit = rs.getInt("hit");

				notice = new Notice(nid, title, writer_id, regdate, hit, files, content, pub);
			}
			// 조회수 카운드 증가 업데이트
			if(hitCnt > 0) {   //수정모드에서 호출할떄는 카운드 증가 안하기
				newHit += 1; 
				st2.setInt(1, newHit);
				st2.setInt(2, id);
			    st2.executeUpdate();
			};			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close();} catch (Exception e2) {}
			if(st != null) try { st.close();} catch (Exception e2) {}
			if(st2 != null) try { st2.close();} catch (Exception e2) {}
			if(con != null) try { con.close();} catch (Exception e2) {}
		}

		return notice;		
	}
	
	public Notice getNotice(int id) throws NamingException, ClassNotFoundException {
		return getNotice(id,0);
	}

	
	public Notice getNextNotice(int id) throws NamingException, ClassNotFoundException {

		Notice notice = null;
		String sql = "SELECT * FROM APT_NOTICE " + "   WHERE ID = (" + "    SELECT ID FROM APT_NOTICE "
				+ "     WHERE REGDATE > (SELECT REGDATE FROM NOTICE WHERE ID = ?) " + "       AND ROWNUM = 1 " + ")";

		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = common.getConnection(); 
			st = con.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				int nid = rs.getInt("id");
				String title = rs.getString("title");
				String writer_id = rs.getString("writer_id");
				Date regdate = rs.getDate("regdate");
				String hit = rs.getString("hit");
				String files = rs.getString("files");
				String content = rs.getString("content");
				boolean pub = rs.getBoolean("pub");

				notice = new Notice(nid, title, writer_id, regdate, hit, files, content, pub);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close();} catch (Exception e2) {}
			if(st != null) try { st.close();} catch (Exception e2) {}
			if(con != null) try { con.close();} catch (Exception e2) {}
		}

		return notice;
	}

	public Notice getPrevNotice(int id) throws NamingException, ClassNotFoundException {

		Notice notice = null;
		String sql = "SELECT * FROM APT_NOTICE " + "   WHERE ID = ("
				+ "    SELECT ID FROM (SELECT * FROM APT_NOTICE ORDER BY REGDATE DESC) "
				+ "     WHERE REGDATE < (SELECT REGDATE FROM APT_NOTICE WHERE ID = ?) " + "       AND ROWNUM = 1" + ")";

		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = common.getConnection(); 
			st = con.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				int nid = rs.getInt("id");
				String title = rs.getString("title");
				String writer_id = rs.getString("writer_id");
				Date regdate = rs.getDate("regdate");
				String hit = rs.getString("hit");
				String files = rs.getString("files");
				String content = rs.getString("content");
				boolean pub = rs.getBoolean("pub");

				notice = new Notice(nid, title, writer_id, regdate, hit, files, content, pub);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close();} catch (Exception e2) {}
			if(st != null) try { st.close();} catch (Exception e2) {}
			if(con != null) try { con.close();} catch (Exception e2) {}
		}
		
		return notice;
	}

	public String[] delFileList(int[] ids) throws NamingException {
		String[] tmpFiles = new String[ids.length*2];
		String[] delFiles = new String[ids.length*3];
		String params = "";
		for (int i = 0; i < ids.length; i++) {
			params += String.valueOf(ids[i]);
			if (i < ids.length - 1) {
				params += ",";
			}
		}
		// 첨부파일 삭제 로직
		String sql = "select * from apt_notice where id in (" + params + ")";	
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = common.getConnection(); 
			st = con.createStatement();
			rs = st.executeQuery(sql);
			// 삭제될 데이터에 포함된 첨부파일들 가져오기
			int k = 0;
			while(rs.next()) {
				String dFiles = rs.getString("files");
				// 첨부파일이 없으면 스킵
				if(dFiles == null || dFiles.equals("")) {
					continue;
				}
				
				tmpFiles = dFiles.split(",");
				for(int j=0; j < tmpFiles.length; j++) {
					delFiles[k] = tmpFiles[j];
					k += 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close();} catch (Exception e2) {}
			if(st != null) try { st.close();} catch (Exception e2) {}
			if(con != null) try { con.close();} catch (Exception e2) {}
		}
		
		return delFiles;
	}
	
	public int delNoticeAll(int[] ids) throws NamingException, ClassNotFoundException {
		int result = 0;
		String params = "";
		for (int i = 0; i < ids.length; i++) {
			params += String.valueOf(ids[i]);
			if (i < ids.length - 1) {
				params += ",";
			}
		}

		String sql = "delete apt_notice where id in (" + params + ")";
		
		Connection con = null;
		Statement st = null;
		try {
			con = common.getConnection(); 
			st = con.createStatement();
			result = st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(st != null) try { st.close();} catch (Exception e2) {}
			if(con != null) try { con.close();} catch (Exception e2) {}
		}

		return result;
	}

	public int removeNoticeAll(int[] ids) {
		return 0;
	}

	public int pubNoticeAll(int[] ids) {
		return 0;
	}

	public int insertNotice(Notice notice) throws NamingException, ClassNotFoundException {
		int result = 0;

		String sql = "insert into apt_notice(id, title, regdate, content, pub, writer_id, files, hit) "
				+ "values (NOTICE_ID_SEQ.nextval,?,to_char(sysdate,'yyyy.mm.dd hh24:mi'),?,?,?,?,1)";

		Connection con = null;
		PreparedStatement st = null;
		try {
			con = common.getConnection(); 
			st = con.prepareStatement(sql);
			st.setString(1, notice.getTitle());
			st.setString(2, notice.getContent());
			st.setBoolean(3, notice.getPub());
			st.setString(4, notice.getWriter_id());
			st.setString(5, notice.getFiles());

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(st != null) try { st.close();} catch (Exception e2) {}
			if(con != null) try { con.close();} catch (Exception e2) {}
		}

		return result;
	}


	public int updateNotice(Notice notice) throws NamingException {
		int result = 0;

		String sql = "update apt_notice SET title = ?, content = ?, files = ? where id = ?";  // 조회카운트 증가
		
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = common.getConnection(); 
			st = con.prepareStatement(sql);
			st.setString(1, notice.getTitle());
			st.setString(2, notice.getContent());
			st.setString(3, notice.getFiles());
			st.setInt(4, notice.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(st != null) try { st.close();} catch (Exception e2) {}
			if(con != null) try { con.close();} catch (Exception e2) {}
		}

		return result;
	}

	public List<Notice> getNoticeNewestList() {
		return null;
	}

	public int[] getNoticeCount() throws ClassNotFoundException, NamingException {
		return getNoticeCount("title", "");
	}

	public int[] getNoticeCount(String field, String query) throws NamingException, ClassNotFoundException {
		int[] count = new int[2];

		String sql = "SELECT COUNT(ID) COUNT FROM (" + "    SELECT ROWNUM NUM, N.* "
				+ "    FROM (SELECT * FROM APT_NOTICE " + "    WHERE " + field + " LIKE ? ORDER BY REGDATE DESC ) N "
				+ ") ";

		String sql2 = "SELECT COUNT(ID) TOTAL_COUNT FROM APT_NOTICE";

		Connection con = null;
		PreparedStatement st = null;
		PreparedStatement st2 = null;
		ResultSet rs = null;
		try {
			con = common.getConnection(); 
			st = con.prepareStatement(sql);
			st.setString(1, "%" + query + "%");
			rs = st.executeQuery();

			rs.next();
			count[0] = rs.getInt("count");
			rs.close();

			st2 = con.prepareStatement(sql2);
			rs = st2.executeQuery();
			rs.next();
			count[1] = rs.getInt("total_count");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close();} catch (Exception e2) {}
			if(st != null) try { st.close();} catch (Exception e2) {}
			if(st2 != null) try { st2.close();} catch (Exception e2) {}
			if(con != null) try { con.close();} catch (Exception e2) {}
		}

		return count;
	}
}
