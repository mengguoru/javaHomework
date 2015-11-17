package DA;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class DAtest {
	public static void main(String[] args) throws SQLException{
		ArrayList<DBInfo> temp=null;
		UserDA temp1=new UserDA();
		temp1.init();
		
		//test getNext()
		temp=temp1.getNext();
		for(int num = temp.size(); num > 0; num--){
			System.out.println("Title: " + temp.get(num - 1).getTitle());
			System.out.println("Editor: " + temp.get(num - 1).getEditor());
		}
		System.out.println("-------");
		
		temp=temp1.getNext();
		for(int num = temp.size(); num > 0; num--){
			System.out.println("Title: " + temp.get(num - 1).getTitle());
			System.out.println("Editor: " + temp.get(num - 1).getEditor());
		}
		System.out.println("-------");
		
		temp=temp1.getNext();
		for(int num = temp.size(); num > 0; num--){
			System.out.println("Title: " + temp.get(num - 1).getTitle());
			System.out.println("Editor: " + temp.get(num - 1).getEditor());
		}
		System.out.println("-------");
		//test pass 
		
		// test addQuestion()
		DBInfo temp2 = new DBInfo(null, "标题3","蒙国儒3", "顶顶顶顶顶顶");
		temp1.addQuestion(temp2);
		//test pass
		
		//test addComment()
		DBInfo temp3 = new DBInfo(null, "标题5","作者5", "内容5");
		temp1.addComment(29,temp3);
		//test pass

		temp1.terminate();
	}
}
