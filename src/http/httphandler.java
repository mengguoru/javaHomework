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
					int tips = 0;
					
					// TODO
					printhead(out);
					do{
						info = UserDA.getNext();
						if(info.size() == 0)
							break;
						
						out.print("<li >");
						out.print("<p><button onclick=\"openShutManager(this,"+"'"+String.valueOf(tips)+"'"+",false,'收起','展开')\">点击展开</button></p> ");
						out.print("<article>");
						
						out.print("<h3>"+info.get(0).getTitle() + "</h3>"  + info.get(0).getEditor() + " at " + info.get(0).getTime() );
						out.print("<div id="+"'"+String.valueOf(tips)+"'"+" style='display:none'> ");
						for(int num = info.size(); num > 0; num--){
							//out.print("<p>");
							out.print("<br/>" + info.get(num - 1).getTime());
							//out.print("</p>");
							out.print("<h5>" + info.get(num - 1).getEditor() + " 说: " + "</h5>");
							//out.print("<p>");
							out.print(info.get(num - 1).getDetail());
							//out.print("</p>");
						}
						out.print("<form action='httphandler' method='get'>");
						out.print("<label for='editor'>你的姓名</label>");
						out.print("<input type='text' name='editor' value='a visitor'>");
						out.print("<br/><label for='detail'>你想说</label>");
						out.print("<input type='text' name='detail'>");
						out.print("<input type='text' name='id' style='display:none' value=" + String.valueOf(info.get(0).getId()) + ">");
						out.print("<input type='text' name='type' style='display:none' value=3>");
						out.print("<input type='submit' value='提交'>");
						out.print("</form>");
						out.print("</div>");
						
						out.print("</article>" + "</li>");
						
						out.print("<hr/>");
						tips++;
					}while(true);
					printtail(out);
					
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
					res.sendRedirect("httphandler?type=1");
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
					res.sendRedirect("httphandler?type=1");
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
	
	private void printhead(PrintWriter out){
		out.print(
			"<html xmlns='http://www.w3.org/1999/xhtml'>"+
			"<head> "+ 
			"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
			"<style type='text/css'>"+
			"#box,#box2,#box3,#box4{padding:10px;border:1px solid green;}"+
			"</style>"+
			"<script type='text/javascript'>"+
			"function openShutManager(oSourceObj,oTargetObj,shutAble,oOpenTip,oShutTip){"+
			"var sourceObj = typeof oSourceObj == 'string' ? document.getElementById(oSourceObj) : oSourceObj;  "+
			"var targetObj = typeof oTargetObj == 'string' ? document.getElementById(oTargetObj) : oTargetObj;  "+
			"var openTip = oOpenTip || '';  "+
			"var shutTip = oShutTip || '';  "+
			"if(targetObj.style.display!='none'){  "+
			   "if(shutAble) return;  "+
			   "targetObj.style.display='none';  "+
			   "if(openTip  &&  shutTip){  "+
			    "sourceObj.innerHTML = shutTip;   }"+  
			"} else {  "+
			   "targetObj.style.display='block'; "+ 
			   "if(openTip  &&  shutTip){  "+
			    "sourceObj.innerHTML = openTip;  } } }  "+
			"</script>  "+
			"<title>班级问题交流</title>  "+
			"<script type='text/javascript' async='' src='../ga.js'></script>"
			+ "<script type='text/javascript' src='../modernizr.custom.66147.js'></script>"+
	        "<script type='text/javascript' src='../qio6inw.js'></script>"+
	        "<link rel='stylesheet' href='../responsive-tables.css'>"+
	        "<link rel='stylesheet' href='../style.css'>"+
			"<script type='text/javascript' src='../25093.js'></script>"+
			"</head>  "+
			"<body  class='home'>  "+
			
			
			
			
			
			

		    
	        "<section class='intro'>"+

	                "<div class='page-width'>"+
	                    "<div class='intro-text'>"+
	                       "<p style='font-family=微软雅黑' class='intro-main fade'>OOP心得交流，只要你要，只要我有</p>" +
	                       "<p style='font-family:微软雅黑' class='clear fade'>  </p>"+
	                       "<p style='font-family:微软雅黑' class='fade'><strong>  </strong></p>"+
	                    "</div>"+
	                "</div>"+
	            "</section>"+

	    "<div class='wrapper'>"+
	        
	        "<header class='header'>"+

	            "<div class='page-width'>"+
	             "<div class='mobile-nav'>"+
	                               "</div>"+

	              "  <ul class='nav left-align'>"+
	                
	              "  <li><a style='font-family:微软雅黑' href='#x' title='Work' tabindex=''>发表新帖</a></li>"+
	              "<li><a style='font-family:微软雅黑' href='#y' title='Work' tabindex=''>查看问题</a></li>"+
	                
	                "</ul>"+
	            "</div>"+
	        "</header>"+
	                
	  "<div id='x' style='height:600px'>"+      
"<center><h1>提问</h1></center>"+
"<center><form action='httphandler' method='get'>"+
"<label for='editor'>你的姓名</label>"+
"<input type='text' name='editor' value='a visitor'>"+
"<br/><label for='title'>你想问</label>"+
"<input type='text' name='title'>"+
"<br/><label for='detail'>详细内容</label>"+
"<input type='text' name='detail' value='如题'>"+
"<input type='text' name='type' style='display:none' value=2>"+
"<input type='submit' value='提交'>"+
"</form></center>" + 
"</div>"+

"<center><a style='font-family:微软雅黑' href='' class='btn'>"+
"Top"+
"</a></center>"+
	        
	        
	        "<div class='main' id='y'>"+

	            "<section class='some-of-our-work'>"+
	                "<h1 style='font-family:微软雅黑'>  </h1>"+
	                "<div class='page-width'>"+
	                "<ul class='some-of-our-work-grid' id='details'>"
			);
	}
	private void printtail(PrintWriter out){
		out.print(
				"</ul>"+
	            "</div>"+
	            "</section>"+

	            "<div class='grad-area'>"+
	          

	                "<section class='from-the-blog trans-white-bg'>"+
	                    "<div class='from-the-blog-wrapper visible'>"+
	                        "<article class='from-the-blog-article'>"+
	                                
	                            "<p style='font-family:微软雅黑'>  </p>"+                                  
	                                                                            
	                            "<a style='font-family:微软雅黑' href='' class='btn'>"+
	                                  "Top"+
	                            "</a>"+
	                            
	                       "</article>"+
	                    "</div>"+
	                "</section>"+

	                
	            "</div>"+
	    "<nav class='hidden-nav'>"+
	      "<div class='page-width'>"+

	            "<ul class='nav'>"+
	                "<li><a  style='font-family:微软雅黑' href='' title='Work' tabindex=''>登陆</a></li>"+

	                "<li><a  style='font-family:微软雅黑' href='' title='Work' tabindex=''>注册</a></li>"+

	                "<li><a style='font-family:微软雅黑' href='' title='Work' tabindex=''>发表新帖</a></li>"+
	            "</ul>"+

	            "<a href='' class='scroll-logo'>"+
	                "<img src='' class='white-logo' alt=''>"+
	            "</a>"+
	        "</div>"+
	      "</nav>");
		
		out.println("  </body>");
		out.println("</html>");
		out.flush();
		out.close();
		
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
