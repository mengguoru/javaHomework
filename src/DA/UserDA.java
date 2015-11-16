package DA;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserDA {
		
	String url="jdbc:mysql://127.0.0.1:3306/oop";
	Connection aCon;
	Statement aSta;

	ResultSet rs = null;
		
	// 初始化数据库
	public static void init(){
		//TODO: initialize database
	}
	
	// 关数据库
	public static void terminate(){
		//TODO: terminate the link to database
	}
	
	//返回一个帖子
	public ArrayList<DBInfo> find() throws SQLException{
		ArrayList<DBInfo> info_to_return = new ArrayList<DBInfo>();
		
		String time = "Time";
		String title = "Title";
		String editor = "Editor";
		String detail = "Detail";

		while(rs.next()){
			//TODO: search form database, and fill in the blanks
			info_to_return.add(new DBInfo(time, title, editor, detail));
		}
		
		return info_to_return;
	}
	
	//增加一个主表项（新建问题帖子）
	public void addQuestion(DBInfo info_to_add){
		
		//TODO: 添加到主表中
	}
	
	//增加一个副表项（评论原有帖子）
	public void addComment(String id/* 主表的id */, DBInfo info_to_add){
		
		//TODO: 根据id，添加到副表中
	}
}
