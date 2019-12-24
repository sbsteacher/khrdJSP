package com.dh.mh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dh.mh.MyUtils;
import com.dh.mh.db.DBApi;
import com.dh.mh.vo.MemberHobbyVO;

@WebServlet("/cMemberHobby")
public class CMemberHobby extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String i_member = request.getParameter("i_member");
		
		request.setAttribute("mhList", DBApi.getMemberHobbyList());
		
		request.setAttribute("memberList", DBApi.getMemberList());
		if(i_member != null) {
			int intMember = MyUtils.parseStringToInt(i_member, 0);
			request.setAttribute("hobbyList", DBApi.getHobbyList(intMember));
		}
		
		request.setAttribute("title", "멤버 취미 등록");
		request.setAttribute("view", "cMemberHobby");
		
		request.getRequestDispatcher("WEB-INF/view/template.jsp")
		.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typ = request.getParameter("typ"); //'1':등록 , '2': 삭제 
		String i_member = request.getParameter("i_member");
		String i_hobby = request.getParameter("i_hobby");
		
		
		
		int intMember = MyUtils.parseStringToInt(i_member, 0);
		int intHobby = MyUtils.parseStringToInt(i_hobby, 0);
		
		
		if("1".equals(typ)) { //등록
			if(intMember == 0 || intHobby == 0) {
				request.setAttribute("msg", "등록할 수 없습니다.");
			} else {
				
				MemberHobbyVO param = new MemberHobbyVO();
				param.setI_member(intMember);
				param.setI_hobby(intHobby);
				
				int result = DBApi.regMemberHobby(param);
				switch(result) {
				case 0:
					request.setAttribute("msg", "등록할 수 없습니다.");
					break;
				case 2:
					request.setAttribute("msg", "이미 등록이 되어 있습니다.");
					break;
				}	
				
			}
		} else if("2".equals(typ)) { //삭제
			
		}
		
		doGet(request, response);
	}

}
