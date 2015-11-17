package DA;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class DAtest {
	public static void main(String[] args) throws SQLException{
		ArrayList<DBInfo> temp=null;
		UserDA temp1=new UserDA();
		UserDA.init();
		
		//test find()
		temp=temp1.find();
		for(Iterator<DBInfo> i = temp.iterator();i.hasNext();)
			System.out.println(i.next().title);
		UserDA.terminate();
	}
}
