package DA;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;


public class UserDA {
		
	static String url="jdbc:mysql://127.0.0.1:3306/oop";
	static Connection aCon;
	static Statement aSta;

	static ResultSet rs = null;
	static PreparedStatement ps = null;	
	
	static int find_id;
	// 初始化数据库
	public static void init() throws SQLException, ClassNotFoundException{
		//TODO: initialize database
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aCon=DriverManager.getConnection(url, "root", "root12");
		aSta = aCon.createStatement();
		find_id = 1;
	}
	
	// 关数据库
	public static void terminate() throws SQLException{
		//TODO: terminate the link to database
		if(null != ps) ps.close();
		if(null !=rs) rs.close();
		aSta.close();
		aCon.close();
	}
	
	//返回一个帖子
	public static ArrayList<DBInfo> getNext() throws SQLException{
		ArrayList<DBInfo> info_to_return = new ArrayList<DBInfo>();
		
		String time;
		String title;
		String editor;
		String detail;
		
		String sql = "select * from test2 where id =" + String.valueOf(find_id);
		rs=aSta.executeQuery(sql);
		while(rs.next()){
			//TODO: search form database, and fill in the blanks
			time=rs.getString(1);
			title=rs.getString(2);
			editor=rs.getString(3);
			detail=rs.getNString(4);
			info_to_return.add(new DBInfo(time, title, editor, detail, find_id));
		}
		find_id++;
		
		return info_to_return;
	}
	
	//增加一个主表项（新建问题帖子）
	public static void addQuestion(DBInfo info_to_add) throws SQLException{
		int last_inserted_id = 0;
		String title0 = info_to_add.getTitle();
		String editor0 = info_to_add.getEditor();
		String detail0 = info_to_add.getDetail();		
		//TODO: 添加到主表中
		
		aSta.execute("insert into test3(id) values(null)");
		rs = aSta.executeQuery("select LAST_INSERT_ID()");
		rs.next();
		last_inserted_id = rs.getInt(1);
//		
		System.out.println("主表 id->"+last_inserted_id);
		String sql = "insert into test2 values(null,'"+title0+"','"+editor0+"','"+detail0+"',"+last_inserted_id+");";
		aSta.execute(sql);
	}
	
	//增加一个副表项（评论原有帖子）
	public static void addComment(int id/* 主表的id */, DBInfo info_to_add) throws SQLException{
		String title0 = info_to_add.getTitle();
		String editor0 = info_to_add.getEditor();
		String detail0 = info_to_add.getDetail();
		System.out.println(detail0);
		//TODO: 根据id，添加到副表中
		
		String sql;
		
		sql = "select id from test3 where id=" + id;
		rs = aSta.executeQuery(sql);
		if(rs.next()){
			sql = "insert into test2 (Time,title,editor,detail,id) values(null,'"+title0+"','"+editor0+"','"+detail0+"',"+id+");";
			aSta.execute(sql);
		}
	}
}
