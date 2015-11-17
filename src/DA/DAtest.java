package DA;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class DAtest {
	public static void main(String[] args) throws SQLException{
		ArrayList<DBInfo> temp=null;
		UserDA temp1=new UserDA();
		temp1.init();
		
		//test find()
		temp=temp1.find();
		for(Iterator<DBInfo> i = temp.iterator();i.hasNext();)
			System.out.println(i.next().title);
		// test addQuestion()
//		DBInfo temp2 = new DBInfo(null, "标题3","蒙国儒3", "顶顶顶顶顶顶");
//		temp1.addQuestion(temp2);
		
		//test addComment()
		DBInfo temp3 = new DBInfo(null, "标题5","作者5", "内容5");
		temp1.addComment(1,temp3);
		temp1.terminate();
	}
}
