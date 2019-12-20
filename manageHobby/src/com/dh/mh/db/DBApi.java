package com.dh.mh.db;

import static com.dh.mh.db.DBCon.close;
import static com.dh.mh.db.DBCon.getCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dh.mh.MyUtils;
import com.dh.mh.vo.MemberVO;

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
}

















