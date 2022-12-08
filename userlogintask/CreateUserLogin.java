package employeeInterview;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Properties;
import java.util.Scanner;
//import org.apache.commons.codec.binary.Base64;

public class CreateUserLogin {
	
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
				
				
	                  //////////////FOR RE-TRYING BY DELETING DATABASE TABLE//////////////////////////			
//				   String sqdrop = "drop database USER_DETIALS " ;   //its just given to delete database 
//			       stmt.executeUpdate(sqdrop);
				
				
				   // getting user details in scanner
				 Scanner myScanner = new Scanner( System.in );
			       
				         System.out.println("Please enter first name:");
			             String firstName = myScanner.nextLine();
			 
					
					     System.out.println("ENTER YOUR LASTNAME: ");
					     String lastname = myScanner.nextLine();
			        
				
					     System.out.println("ENTER YOUR DATE OF BIRTH: ");
					     String dateOfBirth = myScanner.nextLine();
			     
			        
			        	 System.out.println("ENTER YOUR MOBILENUMBER: ");
			        	 String mobileNo = myScanner.nextLine();
			        
			        
			        	
                    	 System.out.println("ENTER YOUR USERNAME: ");
                    	 String userName1 = myScanner.nextLine();
			        
                    	 
                    	    // to check duplicate entry and retrying
                    	 ResultSet renameUser = stmt.executeQuery("SELECT * FROM USER_DETIALS.CreateUserdata WHERE userName='"+userName1+"' ");
                    	 
                         if(renameUser.next())   //to get specific  data from database
                         {
                        	 System.out.println("already exists retry");
                        	 con.close();
                        	 return;
                         }
			        	
                         
	                     System.out.println("ENTER YOUR PASSWORD: ");
	                     String password = myScanner.nextLine();
			        
	                     // encripting the password and update the encripted password in database
	                     Encoder encoder = Base64.getEncoder();
	                     String originalString = password;
	                     String EncriptPassword = encoder.encodeToString(originalString.getBytes());
	                     
			        
			            System.out.println("ENTER YOUR EMAILID:  "); 
			            String emailId = myScanner.nextLine();
			        
			        
			         //query is qiven to create database name
					   String createDatabase = "create database USER_DETIALS " ;
	                   stmt.executeUpdate(createDatabase);
					  System.out.println("DATABASE CREATED SUCESSFULLY");// database will be created in mysql and check sql database
					        
					       // database is selected to EXCECUTE to add data in table
					  String useDatabase = "use USER_DETIALS " ;
	                  stmt.executeUpdate(useDatabase);
	               
					  System.out.println("DATABASE CREATED CAN BE IS USED AND ADD DATA");
			       
			                                                                                                                                                                                                      
			           String createTable = "CREATE TABLE USER_DETIALS.CreateUserdata (Id int AUTO_INCREMENT PRIMARY KEY,firstName VARCHAR(60)not null,lastname VARCHAR(60)not null,DateOfBirth  VARCHAR(60)not null,mobileNo  VARCHAR(60)not null,userName  VARCHAR(60)not null,password  VARCHAR(60)not null, emailId VARCHAR(60)not null,createddate  VARCHAR(60)not null,updateddate  VARCHAR(60)not null)";                                                             
			           stmt.executeUpdate(createTable);                                                                                                   
			        
			        	
                    	   String insertRows1 = "INSERT INTO USER_DETIALS.CreateUserdata(firstName,lastname,dateOfBirth,mobileNo,userName,password,emailId,createddate,updateddate) VALUES ('"+firstName+"','"+lastname+"' ,'"+dateOfBirth+"' ,'"+mobileNo+"' ,'"+userName1+"' ,'"+EncriptPassword+"' ,'"+emailId+"' ,current_timestamp ,current_timestamp)";
                    	   stmt.executeUpdate(insertRows1);
                    	  
                    	   System.out.println ("User data recieved succesfull");
		              			
         }catch (Exception e) {
        	 e.printStackTrace();
         }
	 }
}
