package in.co.rays.example;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NextPkEx {
	
		
public Integer nextpk(){ 
	
		 int pk=0;
		 try{
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_ors","root","");
		
		PreparedStatement stmt=conn.prepareStatement("select max(id) from st_user");
             
		ResultSet rs=stmt.executeQuery();
		while(rs.next()){
			 pk=rs.getInt(1);
			 System.out.println(pk+1);
		}
		
		}
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 return pk+1;
		
}
		 public static void main(String[] args) {
			 
			 NextPkEx np=new NextPkEx();
			 
			 np.nextpk();

	}
}
