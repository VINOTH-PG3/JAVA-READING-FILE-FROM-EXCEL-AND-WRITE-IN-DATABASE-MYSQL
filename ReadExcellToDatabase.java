package employeeInterview;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcellToDatabase {



	     public static void main(String[] args) throws SQLException, IOException {
			
				//Database connection
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/world","root","vinothpg");
				Statement stmt=con.createStatement();
				System.out.println("connected to dataBase Sucessfully");
				System.out.println();
				
				
	//////////////FOR RE-TRYING BY DELETING DATABASE TABLE//////////////////////////			
	//			String sqdrop = "drop database CANDIDATES " ;   //its just given to delete database 
	//		    stmt.executeUpdate(sqdrop);
			    
			    String sqLcreate = "create database CANDIDATES " ;
                stmt.executeUpdate(sqLcreate);
				  System.out.println("DATABASE CREATED SUCESSFULLY");// database will be created in mysql and check sql database
			    
			    
				
				//create a new table in the database 'EmployeeData'
				String sql="create table candidates.EmployeeData(E_id varchar(60) not null,employeeId varchar(60) not null, firstName varchar(40) not null,midleName varchar(40) not null,lastName varchar(40) not null,fullName varchar(40) not null,emailId varchar(40) not null,education varchar(40) not null,mobileNumber varchar(40) not null,dateOfBirth varchar(40) not null,gender varchar(40) not null,address1 varchar(40) not null,address2 varchar(40) not null,pincode varchar(40) not null,City varchar(40) not null,State varchar(40) not null,jobDesignation varchar(40) not null,jobRole varchar(40) not null,joiningdDate varchar(40)not null,Salary varchar(40)not null,perAnnum varchar(40) not null )";
	            stmt.execute(sql);
	            System.out.println("EmployeeData Table created Sucessfully in Database ");
	            System.out.println();
	                                                                              		                                                                 			                                                             	        		                                                                                            			                                                                                                    			                                                                                       	 	     	 		 
				//Excel
	            System.out.println("Rading a file from excelSheet and printing....");
	            System.out.println();
				FileInputStream ReadExcel=new FileInputStream("C:\\Users\\Vinoth07\\Downloads\\empsheet.xlsx");//its used for file reading 
				XSSFWorkbook workbook=new XSSFWorkbook(ReadExcel);
				XSSFSheet sheet=workbook.getSheet("EmpData2");
				
				int rows=sheet.getLastRowNum();
				
				for(int r=1;r<=rows;r++)
				{
					XSSFRow row=sheet.getRow(r);
			         
					 UUID Gendrate = UUID.randomUUID();
				
			         
			         
	                 int E_id = (int)(row.getCell(0).getNumericCellValue());
				     String employeeId =row.getCell(1).getStringCellValue();
				     employeeId=Gendrate.toString();
				    
				     String firstName = row.getCell(2).getStringCellValue();
				     String midleName = row.getCell(3).getStringCellValue();
				     String lastName = row.getCell(4).getStringCellValue();
				     String fullName = row.getCell(5).getStringCellValue();
				     String emailId = row.getCell(6).getStringCellValue();
				     String education = row.getCell(7).getStringCellValue();
				     long mobileNumber = (long) row.getCell(8).getNumericCellValue();
				     String dateOfBirth = row.getCell(10).getStringCellValue();
				     String gender = row.getCell(10).getStringCellValue();
				     String address1 = row.getCell(11).getStringCellValue();
				     String address2 = row.getCell(12).getStringCellValue();
				     int pincode = (int)(row.getCell(13).getNumericCellValue());
				     String City = row.getCell(14).getStringCellValue();
				     String State = row.getCell(15).getStringCellValue();
				     String jobDesignation = row.getCell(16).getStringCellValue();
				     String jobRole = row.getCell(17).getStringCellValue();
				     String joiningdDate = row.getCell(18).getStringCellValue();
				     int Salary = (int)(row.getCell(19).getNumericCellValue());
				     String perAnnum = row.getCell(20).getStringCellValue();
				    
				    
				 	 	     	 		 
				     System.out.println(E_id+" "+employeeId+" "+firstName+" "+midleName+" "+lastName+" "+fullName+" "+emailId+" "+education+" "+mobileNumber+" "+dateOfBirth+" "+gender+" "+address1+" "+address2+" "+pincode+" "+City+" "+State+" "+jobDesignation+" "+ jobRole+" "+joiningdDate+" "+Salary+" "+perAnnum );
				     
				    
				     
				      				                                                                                                                       		        		    				                                                                                     			 	     	 		  
				     
					sql="insert into candidates.EmployeeData values('"+E_id+"', '"+employeeId+"','"+firstName+"', '"+midleName+"', '"+lastName+"', '"+fullName+"', '"+emailId+"', '"+education+"', '"+mobileNumber+"', '"+dateOfBirth+"', '"+gender+"', '"+address1+"', '"+address2+"', '"+pincode+"', '"+City+"', '"+State+"', '"+jobDesignation+"', '"+jobRole+"', '"+joiningdDate+"', '"+Salary+"', '"+perAnnum+"')";
					stmt.execute(sql);
					stmt.execute("commit");
				}
				
				ResultSet showdata = stmt.executeQuery("select E_id,fullname,education,mobileNumber,City,jobRole,salary from candidates.EmployeeData where gender='male'");
				while(showdata.next())
				{
					System.out.println(showdata.getString("E_id") + " " + showdata.getString("fullname") + " " + showdata.getString("education")+ " " + showdata.getLong("mobileNumber")+ " " + showdata.getString("City")+ " " + showdata.getString("jobRole")+ " " + showdata.getInt("Salary"));
				}
				workbook.close();
				ReadExcel.close();
				con.close();
				System.out.println();
				System.out.println("EXCELL DATA UPDATED TO DATABASE");
				
			}

		}

