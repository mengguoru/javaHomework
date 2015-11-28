package http;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DA.*;

public class httphandler extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public httphandler() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

			req.setCharacterEncoding("UTF-8");
			
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
	
			try {
				UserDA.init();
				
				// determine which type
				String string_from_jsp = req.getParameter("type");
				if(string_from_jsp.length() == 0){
					UserDA.terminate();
					out.close();
					return;
				}
				int type_code = Integer.valueOf(string_from_jsp);
				switch(type_code){
				case 1:
					// get all information
					ArrayList<DBInfo> info = new ArrayList<DBInfo>();
					ArrayList<String> idinfo = UserDA.getIds();
					int tips = 0;
					
					// TODO
					for(int n = 0; n < idinfo.size(); n++){
						info = UserDA.getNextByID(idinfo.get(n));
						if(info.size() == 0)
							break;
						
						out.print("<li style='border:2px solid #000' id='" + info.get(0).getTitle() + "'>");
						out.print("<font size=5 face='楷体'><button style='float:right;color:white;background:#5adae2;margin-right:10px;margin-top:10px' onclick=\"openShutManager(this,"+"'"+String.valueOf(tips)+"'"+",false,'收起','展开')\">"
								+ "点击展开"
								+ "</button></font>");
						
						out.print("<h1 style='margin-left:60px'>"+info.get(0).getTitle() + "</h1>"  
								+ "<font style='margin-left:100px' face='Axure Handwriting'>"
								+ info.get(0).getEditor() + " at " + info.get(0).getTime()
								+ "</font>");
						out.print("<article style='margin-left:80px;margin-right:50px'>");
						out.print("<div id="+"'"+String.valueOf(tips)+"'"+" style='display:none'> ");
						for(int num = info.size(); num > 0; num--){
							out.print("<br/>" + "<span style='float:right'>" + info.get(num - 1).getTime() + "</span>");
							out.print("<h3>" + info.get(num - 1).getEditor() + " 说: " + "</h3>");
							out.print("<font face='黑体'>" + info.get(num - 1).getDetail() + "</font>");
							out.print("<hr/><hr/>");
						}
						out.print("<hr/><br/><form action='javaHomework/servlet/httphandler' method='get'>");
						out.print("<label for='editor'><font face='华文行楷' size=5>你的姓名</font></label>");
						out.print("<input type='text' name='editor' value='a visitor'>");
						out.print("<br/><br/><label for='detail'><font face='华文行楷' size=5>你想说：</font></label>");
						out.print("<textarea name='detail' style='width:650px; height=80px'></textarea>");
						out.print("<input type='text' name='id' style='display:none' value=" + idinfo.get(n) + ">");
						out.print("<input type='text' name='type' style='display:none' value=3>");
						out.print("<input typt='text' name='title' style='display:none' value='" + info.get(0).getTitle() + "'>");
						out.print("<br/><input type='submit' value='提交' style='height:35px'>");
						out.print("<a href='javaHomework/index.jsp' style='float:right'>TOP</a>");
						out.print("</form>");
						out.print("</div>");
						
						out.print("</article>");
						out.print("</li>");
						
						out.print("<hr/>");
						tips++;
					}
					
					break;
				case 2:{
					// add a new tip
					String title = req.getParameter("title");
					String detail = req.getParameter("detail");
					String editor = req.getParameter("editor");
					if(title != null)
						title = new String(title.getBytes("ISO-8859-1"), "UTF-8");
					if(detail != null)
						detail = new String(detail.getBytes("ISO-8859-1"), "UTF-8");
					if(editor != null)
						editor = new String(editor.getBytes("ISO-8859-1"), "UTF-8");
					UserDA.addQuestion(new DBInfo(null, title, editor, detail, 0));
					res.sendRedirect("../..//javaHomework/index.jsp#" + "questions");
					break;
				}
				case 3:{
					// add a comment
					String title = req.getParameter("title");
					String detail = req.getParameter("detail");
					String editor = req.getParameter("editor");
					int id = Integer.valueOf(req.getParameter("id"));
					if(title != null)
						title = new String(title.getBytes("ISO-8859-1"), "UTF-8");
					if(detail != null)
						detail = new String(detail.getBytes("ISO-8859-1"), "UTF-8");
					if(editor != null)
						editor = new String(editor.getBytes("ISO-8859-1"), "UTF-8");
					UserDA.addComment(id, new DBInfo(null, title, editor, detail, 0));
					res.sendRedirect("../..//javaHomework/index.jsp#" + "questions");
					break;
				}
				case 4:{
					out.print("<html><head><script>function myFunction(){alert('Hello World!');}</script></head><body><button onclick='myFunction()'>�������</button></body></html>");
					break;
				}
				case 5:{
					System.out.println(req.getCharacterEncoding());
					System.out.println("中文" + new String(req.getParameter("test").getBytes("ISO-8859-1"), "UTF-8"));
					break;
				}
				case 6:{
					String json;
					ArrayList<DBInfo> jinfo = new ArrayList<DBInfo>();
					
					json = "[";
					boolean ishead = true;
					do{
						jinfo = UserDA.getNext();
						if(jinfo.size() == 0)
							break;
						
						if(ishead != true)
							json = json + ",";
						else
							ishead = false;
						
						json = json + "{";
						boolean ishead1 = true;
						for(int num = jinfo.size(); num > 0; num--){
							if(ishead1 == true)
								ishead1 = false;
							else
								json = json + ",";
							
							json = json + "{";
							json = json + "\"editor\":\"" + jinfo.get(num - 1).getEditor() + "\",";
							json = json + "\"time\":\"" + jinfo.get(num - 1).getTime() + "\",";
							json = json + "\"detail\":\"" + jinfo.get(num - 1).getDetail();
							json = json + "\"}";
						}
						json = json + "}";
					}while(true);
					json = json + "]";
					
					out.print(json);
					break;
				}
				case 7:{
					break;
				}				
				default:
					break;
				}
				
				UserDA.terminate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			out.close();
		}
	}
	
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
