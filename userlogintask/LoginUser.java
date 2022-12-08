package employeeInterview;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Base64.Decoder;
import java.util.Properties;
import java.util.Scanner;

import org.apache.poi.hssf.record.PageBreakRecord.Break;


public class LoginUser {
	    static String passcode;
	    static String Decript;
	    
     public static void main(String[] args) throws SQLException, IOException {
    	 
         try {
       	    Properties Prop =new Properties();
       	    InputStream input = new FileInputStream("C:\\Users\\Vinoth07\\Desktop\\db.properties");
				Prop.load(input);
				//String JDBC_DRIVER = Prop.getProperty("JDBC_DRIVER");
			    String JDBC_URL    = Prop.getProperty("JDBC_URL");
			    String USER_NAME   = Prop.getProperty("USER_NAME");
				String PASSWORD    = Prop.getProperty("PASSWORD");
				
				//Database connection
				Connection con=DriverManager.getConnection(JDBC_URL,USER_NAME,PASSWORD);
				Statement stmt=con.createStatement();
				System.out.println("connected to dataBase Sucessfully");
				System.out.println();
				
				 Scanner myScanner = new Scanner( System.in );
                
                
             
               
               System.out.println("ENTER YOUR USERNAME: ");
               String userid = myScanner.nextLine();//qqqqq
               
               System.out.println("ENTER YOUR PASSWORD: ");
               String PassWord = myScanner.nextLine();//
              // System.out.println("User password in scanner :"+PassWord);
               
               
               ResultSet checkPassword = stmt.executeQuery("SELECT * FROM USER_DETIALS.CreateUserdata"); //Where username = '"+userid+"' ");      
               
             while(checkPassword.next())   //to get specific  data from database
             {
          	    passcode=checkPassword.getString(7);
             }
              
               
               System.out.println("ENCRIPTED PASSWORD FROM DATA BASE  "+passcode);
               Decoder decoder = Base64.getDecoder();
               byte[] bytes = decoder.decode(passcode);
               Decript= new String(bytes);
               
               System.out.println("Decrypted Value : " + new String(bytes));
               System.out.println("here passcode is "+passcode);
               System.out.println("Encripted password is decripted   "+Decript);
                
               
				    ResultSet tableUser1 = stmt.executeQuery("SELECT * FROM USER_DETIALS.CreateUserdata ");//Where username = '"+userid+"' ");  ////AND PassWord = '"+Decript+"' "    
                
				System.out.println("result final");
					String dpassword=Decript;
					while (tableUser1.next()){
						String userids= tableUser1.getString(6);
					if(userid.equals(userids) & PassWord.equals(dpassword)){
						System.out.println("user login Sucessfull");
						break;
					}
					//else {
						//System.out.println("USER login FAILED");
						
					//}

					}
               
               
               
            	
       

				
	
		 
			
	            
				
         }catch (Exception e) {
       	  e.printStackTrace();
         }
			}
     }

