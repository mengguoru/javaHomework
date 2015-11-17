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
	static PreparedStatement ps = null;	
	// 初始化数据库
	public void init() throws SQLException{
		//TODO: initialize database
		Connection aCon=DriverManager.getConnection(url, "root", "root12");
		aSta = aCon.createStatement();
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
		rs=aSta.executeQuery("select * from test2 where id =1");
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
		int id = 0;
		String title0 = info_to_add.title;
		String editor0 = info_to_add.editor;
		String detail0 = info_to_add.detail;		
		//TODO: 添加到主表中
		ps = aCon.prepareStatement("insert into test3 values(null);", Statement.RETURN_GENERATED_KEYS);
		ps.executeUpdate();
		ResultSet rs1 = ps.getGeneratedKeys();
		while(rs1.next()){
			id = rs1.getInt(1);
			System.out.println("数据主键：" + id);
			String sql = "insert into test2 values(null,'"+title0+"','"+editor0+"','"+detail0+"',"+id+");";
			rs1=aSta.executeQuery(sql);
		}
//			id = rs.getInt(1);
//		String sql = "insert into test2 values(null,'"+title0+"','"+editor0+"','"+detail0+"',"+id+");";
//		rs=aSta.executeQuery(sql);
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
