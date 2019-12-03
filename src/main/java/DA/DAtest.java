package DA;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class DAtest {
	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		ArrayList<DBInfo> temp=null;
		UserDA.init();
		
		//test getNext()
		temp=UserDA.getNext();
		for(int num = temp.size(); num > 0; num--){
			System.out.println("Title: " + temp.get(num - 1).getTitle());
			System.out.println("Editor: " + temp.get(num - 1).getEditor());
			System.out.println("detail: " + temp.get(num - 1).getDetail());
		}
		System.out.println("-------");
		
		temp=UserDA.getNext();
		for(int num = temp.size(); num > 0; num--){
			System.out.println("Title: " + temp.get(num - 1).getTitle());
			System.out.println("Editor: " + temp.get(num - 1).getEditor());
			System.out.println("detail: " + temp.get(num - 1).getDetail());
		}
		System.out.println("-------");
		
		temp=UserDA.getNext();
		for(int num = temp.size(); num > 0; num--){
			System.out.println("Title: " + temp.get(num - 1).getTitle());
			System.out.println("Editor: " + temp.get(num - 1).getEditor());
			System.out.println("detail: " + temp.get(num - 1).getDetail());
		}
		System.out.println("-------");
		//test pass 
		
		// test addQuestion()
		DBInfo temp2 = new DBInfo(null, "title title title","mgr3", "123456789", 0);
		UserDA.addQuestion(temp2);
		//test pass
		
		//test addComment()
		DBInfo temp3 = new DBInfo(null, "comment","зїеп5", "comment comment", 0);
		UserDA.addComment(1,temp3);
		//test pass

		UserDA.terminate();
	}
}
