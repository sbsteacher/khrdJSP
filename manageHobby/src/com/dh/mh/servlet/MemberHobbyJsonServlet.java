package com.dh.mh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dh.mh.MyUtils;
import com.dh.mh.db.DBApi;

@WebServlet("/mhJson")
public class MemberHobbyJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String i_member = request.getParameter("i_member");
		int intMember = MyUtils.parseStringToInt(i_member, 0);
		
		String json = DBApi.getMemberHobbyListJson(intMember);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.write(json);
		out.flush();
	}

}
