package com.dh.mh.db;

import static com.dh.mh.db.DBCon.close;
import static com.dh.mh.db.DBCon.getCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dh.mh.MyUtils;
import com.dh.mh.vo.HobbyVO;
import com.dh.mh.vo.MemberHobbyVO;
import com.dh.mh.vo.MemberVO;
import com.google.gson.Gson;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class DBApi {
	
	//리턴값 1:회원가입 성공!!, 0: 회원가입 실패
	public static int join(MemberVO param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " INSERT INTO t_member(uid, upw, usex, unm, uphone) "
				+ " VALUES(?, ?, ?, ?, ?) ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, param.getUid());
			ps.setString(2, param.getUpw());
			ps.setInt(3, param.getUsex());
			ps.setString(4,  param.getUnm());
			ps.setString(5,  param.getUphone());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps);
		}
		
		return result;
	}
	
	//리턴값 0:에러, 1:로긴 성공, 2:비밀번호 틀림, 3:아이디 없음,
	public static int login(MemberVO param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM t_member WHERE uid = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, param.getUid());
			rs = ps.executeQuery();
			
			if(rs.next()) { //아이디 있음
				String dbPw = rs.getString("upw");
				String cryPw = MyUtils.encryptSHA256(param.getUpw());
				if(dbPw.equals(cryPw)) { 
					result = 1;
				} else {
					result = 2;
				}
				
				int i_member = rs.getInt("i_member");
				int usex = rs.getInt("usex");
				String unm = rs.getString("unm");
				param.setUpw(null);
				param.setI_member(i_member);
				param.setUsex(usex);
				param.setUnm(unm);
				
			} else { //아이디 없음
				result = 3;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		
		return result;
	}
	
	public static int createHobby(HobbyVO param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " INSERT INTO t_hobby (hnm) VALUES (?) ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, param.getHnm());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps);
		}
		
		return result;
	}
	
	public static int delHobby(HobbyVO param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " DELETE FROM t_hobby WHERE i_hobby = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_hobby());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps);
		}
		
		return result;
	}
	
	public static List<HobbyVO> getHobbyList() {
		List<HobbyVO> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM t_hobby ORDER BY i_hobby ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int i_hobby = rs.getInt("i_hobby");
				String hnm = rs.getString("hnm");
				
				HobbyVO vo = new HobbyVO();
				vo.setI_hobby(i_hobby);
				vo.setHnm(hnm);
				
				list.add(vo);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return list;
	}
	
	public static List<HobbyVO> getHobbyList(int i_member) {
		List<HobbyVO> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select A.* " + 
				" from t_hobby A " + 
				" left join t_member_hobby B " + 
				"	on A.i_hobby = B.i_hobby " + 
				"	and B.i_member = ? " + 
				" where B.i_hobby is null " + 
				" order by A.i_hobby ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, i_member);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int i_hobby = rs.getInt("i_hobby");
				String hnm = rs.getString("hnm");
				
				HobbyVO vo = new HobbyVO();
				vo.setI_hobby(i_hobby);
				vo.setHnm(hnm);
				
				list.add(vo);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return list;
	}
	
	public static List<MemberVO> getMemberList() {
		List<MemberVO> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM t_member ORDER BY i_member ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				MemberVO vo  = new MemberVO();
				vo.setI_member(rs.getInt("i_member"));
				vo.setUnm(rs.getString("unm"));
				
				list.add(vo);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return list;
	}
	
	//1:등록 성공, 0:에러, 2:중복된 자료가 있음
	public static int regMemberHobby(MemberHobbyVO param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " INSERT INTO t_member_hobby "
					+ " (i_member, i_hobby) "
					+ " VALUES "
					+ " (?, ?) ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_member());
			ps.setInt(2, param.getI_hobby());
			
			result = ps.executeUpdate();
			
		} catch(MySQLIntegrityConstraintViolationException e) {
			result = 2;
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps);
		}
		
		return result;
	}
	
	public static List<MemberHobbyVO> getMemberHobbyList() {
		List<MemberHobbyVO> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select A.*, B.unm, C.hnm " + 
				" from t_member_hobby A " + 
				" inner join t_member B " + 
				" on A.i_member = B.i_member " + 
				" inner join t_hobby C " + 
				" on A.i_hobby = C.i_hobby ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				MemberHobbyVO vo = new MemberHobbyVO();
				vo.setI_member(rs.getInt("i_member"));
				vo.setI_hobby(rs.getInt("i_hobby"));
				vo.setUnm(rs.getString("unm"));
				vo.setHnm(rs.getString("hnm"));
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return list;
	}
	
	public static String getMemberHobbyListJson() {
		List<Map<String, String>> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select A.*, B.unm, C.hnm " + 
				" from t_member_hobby A " + 
				" inner join t_member B " + 
				" on A.i_member = B.i_member " + 
				" inner join t_hobby C " + 
				" on A.i_hobby = C.i_hobby ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String i_member = rs.getString("i_member");
				String i_hobby = rs.getString("i_hobby");
				String unm = rs.getString("unm");
				String hnm = rs.getString("hnm");
					
				Map<String, String> map = new HashMap();
				map.put("i_member", i_member);
				map.put("i_hobby", i_hobby);
				map.put("unm", unm);
				map.put("hnm", hnm);
				list.add(map);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return new Gson().toJson(list);
	}
	
	public static int delMemberHobby(MemberHobbyVO param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " DELETE FROM t_member_hobby "
					   + " WHERE i_member = ? and i_hobby = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_member());
			ps.setInt(2, param.getI_hobby());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps);
		}
		
		return result;
	}
}

















