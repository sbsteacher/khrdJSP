package com.dh.mh.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dh.mh.MyUtils;
import com.dh.mh.db.DBApi;
import com.dh.mh.vo.HobbyVO;

@WebServlet("/cHobby")
public class CHobbyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("hobbyList", DBApi.getHobbyList());
		
		request.setAttribute("title", "취미 등록");
		request.setAttribute("view", "cHobby");
		
		request.getRequestDispatcher("WEB-INF/view/template.jsp")
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String i_hobby = request.getParameter("i_hobby");
		String hnm = request.getParameter("hnm");
		
		HobbyVO param = new HobbyVO();
		if(!"".equals(i_hobby)) { //삭제
			int int_hobby = MyUtils.parseStringToInt(i_hobby, 0);
			
			if(int_hobby == 0) {
				request.setAttribute("msg", "삭제할 수 없습니다");
			} else {
				param.setI_hobby(int_hobby);
				int result = DBApi.delHobby(param);
				if(result != 1) {
					request.setAttribute("msg", "삭제할 수 없습니다");
				}
			}
			
		} else if(!"".equals(hnm)) { //등록
			param.setHnm(hnm);
			
			int result = DBApi.createHobby(param);
			if(result != 1) {
				request.setAttribute("msg", "등록할 수 없습니다");
			}	
		}
		
		doGet(request, response);
	}
}






