package DA;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserDA {
		
	static String url="jdbc:mysql://127.0.0.1:3306/oop";
	static Connection aCon;
	static Statement aSta;

	static ResultSet rs = null;
	PreparedStatement ps = null;	
	
	static int find_id;
	// 初始化数据库
	public void init() throws SQLException{
		//TODO: initialize database
		aCon=DriverManager.getConnection(url, "root", "root12");
		aSta = aCon.createStatement();
		find_id = 1;
	}
	
	// 关数据库
	public void terminate() throws SQLException{
		//TODO: terminate the link to database
		if(null != ps) ps.close();
		if(null !=rs) rs.close();
		aSta.close();
		aCon.close();
	}
	
	//返回一个帖子
	public ArrayList<DBInfo> find() throws SQLException{
		ArrayList<DBInfo> info_to_return = new ArrayList<DBInfo>();
		
		String time = "Time";
		String title = "Title";
		String editor = "Editor";
		String detail = "Detail";
		
		String sql = "select * from test2 where id =" + String.valueOf(find_id);
		find_id++;
		rs=aSta.executeQuery(sql);
		while(rs.next()){
			//TODO: search form database, and fill in the blanks
			time=rs.getString(1);
			title=rs.getString(2);
			editor=rs.getString(3);
			detail=rs.getNString(4);
			info_to_return.add(new DBInfo(time, title, editor, detail));
		}
		
		return info_to_return;
	}
	
	//增加一个主表项（新建问题帖子）
	public void addQuestion(DBInfo info_to_add) throws SQLException{
//		int id = 0;
		int last_inserted_id = 0;
		String title0 = info_to_add.title;
		String editor0 = info_to_add.editor;
		String detail0 = info_to_add.detail;		
		//TODO: 添加到主表中
		
//		ps = aCon.prepareStatement("insert into test3(id) values(null)", Statement.RETURN_GENERATED_KEYS);
//		ps.executeUpdate();
//		ResultSet rs1 = ps.getGeneratedKeys();
		
		aSta.execute("insert into test3(id) values(null)");
		/*
		rs=aSta.executeQuery("select * from test3");		
		while(rs.next())
			last_inserted_id = rs.getInt(1);
		*/
		rs = aSta.executeQuery("select max(id) from test3");
		while(rs.next())
			last_inserted_id = rs.getInt(1);
//		
		System.out.println("主表 id->"+last_inserted_id);
		String sql = "insert into test2 values(null,'"+title0+"','"+editor0+"','"+detail0+"',"+last_inserted_id+");";
		aSta.execute(sql);
	}
	
	//增加一个副表项（评论原有帖子）
	public void addComment(int id/* 主表的id */, DBInfo info_to_add) throws SQLException{
		String title0 = info_to_add.title;
		String editor0 = info_to_add.editor;
		String detail0 = info_to_add.detail;	
		//TODO: 根据id，添加到副表中
		String sql = "insert into test2 (Time,title,editor,detail,id) values(null,'"+title0+"','"+editor0+"','"+detail0+"',"+id+");";
		aSta.execute(sql);
	}
}
