<%@page import="http_handler.handler"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DA.*;
	
	class handler extends HttpServlet {

	
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		// TODO: add handler segments
		
		try {
			UserDA.init();
			PrintWriter output = res.getWriter();
			res.setContentType("text/html");
			
			// determine which type
			String string_from_jsp = req.getParameter("type");
			int type_code = Integer.valueOf(string_from_jsp);
			switch(type_code){
			case 1:
				// get all information
				ArrayList<DBInfo> info = new ArrayList<DBInfo>();
				do{
					info = UserDA.getNext();
					for(int num = info.size(); num > 0; num--){
						// TODO format to HTML
						output.println("Title: " + info.get(num - 1).getTitle());
						output.println("Title: " + info.get(num - 1).getEditor());
						output.println("Title: " + info.get(num - 1).getDetail());
					}
				}while(info.size() != 0);
				break;
			case 2:{
				// add a new tip
				String title = req.getParameter("title");
				String detail = req.getParameter("detail");
				String editor = req.getParameter("editor");
				UserDA.addQuestion(new DBInfo(null, title, editor, detail));
				break;
			}
			case 3:{
				// add a comment
				String title = req.getParameter("title");
				String detail = req.getParameter("detail");
				String editor = req.getParameter("editor");
				int id = Integer.valueOf(req.getParameter("id"));
				UserDA.addComment(id, new DBInfo(null, title, editor, detail));
				break;
			}
			default:
				break;
			}
			
//			output.print("<h3>You inputed: <br>" + string_from_jsp + "</h3>");
//			output.print("<p>number= " + Integer.valueOf(string_from_jsp) + "</p>");
			UserDA.terminate();
			output.close();
		} catch (IOException e) {
			throw(e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% handler temp = new handler(); %>
</body>
</html>