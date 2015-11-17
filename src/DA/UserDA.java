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
	// ��ʼ�����ݿ�
	public void init() throws SQLException{
		//TODO: initialize database
		Connection aCon=DriverManager.getConnection(url, "root", "root12");
		aSta = aCon.createStatement();
	}
	
	// �����ݿ�
	public void terminate() throws SQLException{
		//TODO: terminate the link to database
		if(null != ps) ps.close();
		if(null !=rs) rs.close();
		aSta.close();
		aCon.close();
	}
	
	//����һ������
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
	
	//����һ��������½��������ӣ�
	public void addQuestion(DBInfo info_to_add) throws SQLException{
		int id = 0;
		String title0 = info_to_add.title;
		String editor0 = info_to_add.editor;
		String detail0 = info_to_add.detail;		
		//TODO: ��ӵ�������
		ps = aCon.prepareStatement("insert into test3 values(null);", Statement.RETURN_GENERATED_KEYS);
		ps.executeUpdate();
		ResultSet rs1 = ps.getGeneratedKeys();
		while(rs1.next()){
			id = rs1.getInt(1);
			System.out.println("����������" + id);
			String sql = "insert into test2 values(null,'"+title0+"','"+editor0+"','"+detail0+"',"+id+");";
			rs1=aSta.executeQuery(sql);
		}
//			id = rs.getInt(1);
//		String sql = "insert into test2 values(null,'"+title0+"','"+editor0+"','"+detail0+"',"+id+");";
//		rs=aSta.executeQuery(sql);
	}
	
	//����һ�����������ԭ�����ӣ�
	public void addComment(int id/* �����id */, DBInfo info_to_add) throws SQLException{
		String title0 = info_to_add.title;
		String editor0 = info_to_add.editor;
		String detail0 = info_to_add.detail;	
		//TODO: ����id����ӵ�������
		String sql = "insert into test2 (Time,title,editor,detail,id) values(null,'"+title0+"','"+editor0+"','"+detail0+"',"+id+");";
		aSta.execute(sql);
	}
}
