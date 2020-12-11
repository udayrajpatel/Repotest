package in.co.rays.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BatchProcessing {


		public void batch()  {
			try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rays","root","");
			Statement st= con.createStatement();
			
			
			String query1="insert into employee values(6,'i','jj','sss',10)";
			String query2="insert into employee values(7,'i','jj','hhh',15)";
	       
			st.addBatch(query1);
			st.addBatch(query2);
			
			int a[]=st.executeBatch();
			System.out.println("no of rows affected "+ a);
		
			st.close();
			}
			catch(Exception e)
			
			{
				 e.printStackTrace();
			}
			
			
			
		}
		
		public static void main(String[] args) {
			BatchProcessing b=new BatchProcessing();
			b.batch();
		  }
	
}
