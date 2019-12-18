package com.dh.mh.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		boolean trouble = false;
		trouble = true;
		
		if(trouble) {
			request.setAttribute("msg", "문제 발생쓰~~~");
			doGet(request, response);
		}
		System.out.println("uid : " + uid);
		
		
	}

}
