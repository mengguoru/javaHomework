package DA;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.Driver;


public class UserDA {
	static Properties pros = null;
	static Connection aCon;
	static Statement aSta;

	static ResultSet rs = null;
	static PreparedStatement ps = null;	
	
	static int find_id;
	// ?????????
	public static void init() throws SQLException, ClassNotFoundException, IOException {
		try{
			pros = new Properties();
			InputStream is = UserDA.class.getClassLoader().getResourceAsStream("db.properties");
			pros.load(is);

			//¼ÓÔØÇý¶¯
			Class.forName(pros.getProperty("mysqlDriver"));
			aCon = DriverManager.getConnection(pros.getProperty("mysqlURL"), pros.getProperty("mysqlUser"), pros.getProperty("mysqlPwd"));
			aSta = aCon.createStatement();
			find_id = 1;
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	// ??????
	public static void terminate() throws SQLException{
		//TODO: terminate the link to database
		if(null != ps) ps.close();
		if(null !=rs) rs.close();
		aSta.close();
		aCon.close();
	}
	
	public static ArrayList<String> getIds() throws SQLException{
		ArrayList<String> info = new ArrayList<String>();
		String sql = "select * from IDsTable order by id desc";
		rs = aSta.executeQuery(sql);
		while(rs.next()){
			info.add(rs.getString(1));
		}
		return info;
	}
	
	//???????????
	public static ArrayList<DBInfo> getNextByID(String ID) throws SQLException{
		ArrayList<DBInfo> info_to_return = new ArrayList<DBInfo>();
		
		String time;
		String title;
		String editor;
		String detail;
		
		String sql = "select * from Questions where id =" + ID
				 + " order by Time desc";
		rs=aSta.executeQuery(sql);
		while(rs.next()){
			//TODO: search form database, and fill in the blanks
			time=rs.getString(1);
			title=rs.getString(2);
			editor=rs.getString(3);
			detail=rs.getNString(4);
			info_to_return.add(new DBInfo(time, title, editor, detail, find_id));
		}
		
		return info_to_return;
	}
	
	public static ArrayList<DBInfo> getNext() throws SQLException{
		ArrayList<DBInfo> info_to_return = new ArrayList<DBInfo>();
		
		String time;
		String title;
		String editor;
		String detail;
		
		String sql = "select * from test2 where id =" + String.valueOf(find_id)
				 + "order by Time desc";
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
	
	//?????????????????????????
	public static void addQuestion(DBInfo info_to_add) throws SQLException{
		int last_inserted_id = 0;
		String title0 = info_to_add.getTitle();
		String editor0 = info_to_add.getEditor();
		String detail0 = info_to_add.getDetail();		
		//TODO: ??????????
		
		aSta.execute("insert into test3(id) values(null)");
		rs = aSta.executeQuery("select LAST_INSERT_ID()");
		rs.next();
		last_inserted_id = rs.getInt(1);
//		
		System.out.println("???? id->"+last_inserted_id);
		String sql = "insert into test2 values(null,'"+title0+"','"+editor0+"','"+detail0+"',"+last_inserted_id+");";
		aSta.execute(sql);
	}
	
	//?????????????????????????
	public static void addComment(int id/* ?????id */, DBInfo info_to_add) throws SQLException{
		String title0 = info_to_add.getTitle();
		String editor0 = info_to_add.getEditor();
		String detail0 = info_to_add.getDetail();
		System.out.println(detail0);
		//TODO: ???id????????????
		
		String sql;
		
		sql = "select id from test3 where id=" + id;
		rs = aSta.executeQuery(sql);
		if(rs.next()){
			sql = "insert into test2 (Time,title,editor,detail,id) values(null,'"+title0+"','"+editor0+"','"+detail0+"',"+id+");";
			aSta.execute(sql);
		}
	}
}
