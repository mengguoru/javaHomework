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
		
	// ��ʼ�����ݿ�
	public static void init(){
		//TODO: initialize database
	}
	
	// �����ݿ�
	public static void terminate(){
		//TODO: terminate the link to database
	}
	
	//����һ������
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
	
	//����һ��������½��������ӣ�
	public void addQuestion(DBInfo info_to_add){
		
		//TODO: ��ӵ�������
	}
	
	//����һ�����������ԭ�����ӣ�
	public void addComment(String id/* �����id */, DBInfo info_to_add){
		
		//TODO: ����id����ӵ�������
	}
}
