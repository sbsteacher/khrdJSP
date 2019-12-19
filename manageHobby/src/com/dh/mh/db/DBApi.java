package com.dh.mh.db;

import static com.dh.mh.db.DBCon.getCon;
import static com.dh.mh.db.DBCon.close;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.dh.mh.vo.MemberVO;

public class DBApi {
	
	//리턴값 1:회원가입 성공!!, 0: 회원가입 실패
	public static int join(MemberVO param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " INSERT INTO t_member(uid, upw, usex, unm) "
				+ " VALUES(?, ?, ?, ?) ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, param.getUid());
			ps.setString(2, param.getUpw());
			ps.setInt(3, param.getUsex());
			ps.setString(4,  param.getUnm());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps);
		}
		
		return result;
	}
}
