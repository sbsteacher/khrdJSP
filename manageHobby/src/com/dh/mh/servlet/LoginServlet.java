package com.dh.mh.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dh.mh.db.DBApi;
import com.dh.mh.vo.MemberVO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		if(hs.getAttribute("loginUser") != null) {
			response.sendRedirect("home");
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String upw = request.getParameter("upw");
		
		MemberVO param = new MemberVO();
		param.setUid(uid);
		param.setUpw(upw);
		
		int result = DBApi.login(param);
		
		if(result == 1) {
			//원하는 곳으로
			System.out.println("로그인 성공!!!!!");

			//로그인 세션 처리
			HttpSession hs = request.getSession();			
			hs.setAttribute("loginUser", param);
			
		
			response.sendRedirect("home");
		} else {
			String msg = null;
			switch(result) {
			case 0:
				msg = "에러가 발생하였습니다.";
				break;
			case 2:
				msg = "비밀번호를 확인해 주세요.";				
				break;
			case 3:
				msg = "아이디를 확인해 주세요.";
				break;
			}
			
			request.setAttribute("msg", msg);
			doGet(request, response);
		}
	}

}
