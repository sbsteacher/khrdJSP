package com.dh.mh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dh.mh.MyUtils;
import com.dh.mh.db.DBApi;
import com.dh.mh.vo.MemberVO;

@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		request.getRequestDispatcher("WEB-INF/view/join.jsp")
		.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("!!!!! join doPost run!!!!!");
		String uid = request.getParameter("uid");
		String upw = request.getParameter("upw");
		String reupw = request.getParameter("reupw");
		String usex = request.getParameter("usex");
		String unm = request.getParameter("unm");
		String uphone = request.getParameter("uphone");
		/*
		boolean trouble = false;
		trouble = true;
		
		if(trouble) {
			request.setAttribute("msg", "문제 발생쓰~~~");
			doGet(request, response);
		}
		*/		
		
		System.out.println("upw : " + upw);
		upw = MyUtils.encryptSHA256(upw);
		System.out.println("upw : " + upw);
		System.out.println("upw.length" + upw.length());
		
		MemberVO param = new MemberVO();
		param.setUid(uid);
		param.setUpw(upw);
		param.setUsex(MyUtils.parseStringToInt(usex, 0));
		param.setUnm(unm);
		param.setUphone(uphone);
		
		int result = DBApi.join(param);
		if(result == 1) { //(회원가입 성공) login 페이지로 가야됨
			response.sendRedirect("login");
		} else { //join페이지로 가야됨
			request.setAttribute("msg", "회원가입에 실패하였습니다.");
			doGet(request, response);
		}
	}
}
