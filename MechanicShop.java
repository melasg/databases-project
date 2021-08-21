/*
 * Template JAVA User Interface
 * =============================
 *
 * Database Management Systems
 * Department of Computer Science &amp; Engineering
 * University of California - Riverside
 *
 * Target DBMS: 'Postgres'
 *
 */


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;



/**
 * This class defines a simple embedded SQL utility class that is designed to
 * work with PostgreSQL JDBC drivers.
 *
 */

public class MechanicShop{
	//reference to physical database connection
	private Connection _connection = null;
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public MechanicShop(String dbname, String dbport, String user, String passwd) throws SQLException {
		System.out.print("Connecting to database...");
		try{
			// constructs the connection URL
			String url = "jdbc:postgresql://localhost:" + dbport + "/" + dbname;
			System.out.println ("Connection URL: " + url + "\n");
			
			// obtain a physical connection
	        this._connection = DriverManager.getConnection(url, user, passwd);
	        System.out.println("Done");
		}catch(Exception e){
			System.err.println("Error - Unable to Connect to Database: " + e.getMessage());
	        System.out.println("Make sure you started postgres on this machine");
	        System.exit(-1);
		}
	}
	
	/**
	 * Method to execute an update SQL statement.  Update SQL instructions
	 * includes CREATE, INSERT, UPDATE, DELETE, and DROP.
	 * 
	 * @param sql the input SQL string
	 * @throws java.sql.SQLException when update failed
	 * */
	public void executeUpdate (String sql) throws SQLException { 
		// creates a statement object
		Statement stmt = this._connection.createStatement ();

		// issues the update instruction
		stmt.executeUpdate (sql);

		// close the instruction
	    stmt.close ();
	}//end executeUpdate

	/**
	 * Method to execute an input query SQL instruction (i.e. SELECT).  This
	 * method issues the query to the DBMS and outputs the results to
	 * standard out.
	 * 
	 * @param query the input query string
	 * @return the number of rows returned
	 * @throws java.sql.SQLException when failed to execute the query
	 */
	public int executeQueryAndPrintResult (String query) throws SQLException {
		//creates a statement object
		Statement stmt = this._connection.createStatement ();

		//issues the query instruction
		ResultSet rs = stmt.executeQuery (query);

		/*
		 *  obtains the metadata object for the returned result set.  The metadata
		 *  contains row and column info.
		 */
		ResultSetMetaData rsmd = rs.getMetaData ();
		int numCol = rsmd.getColumnCount ();
		int rowCount = 0;
		
		//iterates through the result set and output them to standard out.
		boolean outputHeader = true;
		while (rs.next()){
			if(outputHeader){
				for(int i = 1; i <= numCol; i++){
					System.out.print(rsmd.getColumnName(i) + "\t");
			    }
			    System.out.println();
			    outputHeader = false;
			}
			for (int i=1; i<=numCol; ++i)
				System.out.print (rs.getString (i) + "\t");
			System.out.println ();
			++rowCount;
		}//end while
		stmt.close ();
		return rowCount;
	}
	
	/**
	 * Method to execute an input query SQL instruction (i.e. SELECT).  This
	 * method issues the query to the DBMS and returns the results as
	 * a list of records. Each record in turn is a list of attribute values
	 * 
	 * @param query the input query string
	 * @return the query result as a list of records
	 * @throws java.sql.SQLException when failed to execute the query
	 */
	public List<List<String>> executeQueryAndReturnResult (String query) throws SQLException { 
		//creates a statement object 
		Statement stmt = this._connection.createStatement (); 
		
		//issues the query instruction 
		ResultSet rs = stmt.executeQuery (query); 
	 
		/*
		 * obtains the metadata object for the returned result set.  The metadata 
		 * contains row and column info. 
		*/ 
		ResultSetMetaData rsmd = rs.getMetaData (); 
		int numCol = rsmd.getColumnCount (); 
		int rowCount = 0; 
	 
		//iterates through the result set and saves the data returned by the query. 
		boolean outputHeader = false;
		List<List<String>> result  = new ArrayList<List<String>>(); 
		while (rs.next()){
			List<String> record = new ArrayList<String>(); 
			for (int i=1; i<=numCol; ++i) 
				record.add(rs.getString (i)); 
			result.add(record); 
		}//end while 
		stmt.close (); 
		return result; 
	}//end executeQueryAndReturnResult
	
	/**
	 * Method to execute an input query SQL instruction (i.e. SELECT).  This
	 * method issues the query to the DBMS and returns the number of results
	 * 
	 * @param query the input query string
	 * @return the number of rows returned
	 * @throws java.sql.SQLException when failed to execute the query
	 */
	public int executeQuery (String query) throws SQLException {
		//creates a statement object
		Statement stmt = this._connection.createStatement ();

		//issues the query instruction
		ResultSet rs = stmt.executeQuery (query);

		int rowCount = 0;

		//iterates through the result set and count number of results.
		while(rs.next()){
			rowCount++;
		}//end while
		stmt.close ();
		return rowCount;
	}
	
	/**
	 * Method to fetch the last value from sequence. This
	 * method issues the query to the DBMS and returns the current 
	 * value of sequence used for autogenerated keys
	 * 
	 * @param sequence name of the DB sequence
	 * @return current value of a sequence
	 * @throws java.sql.SQLException when failed to execute the query
	 */
	
	public int getCurrSeqVal(String sequence) throws SQLException {
		Statement stmt = this._connection.createStatement ();
		
		ResultSet rs = stmt.executeQuery (String.format("Select currval('%s')", sequence));
		if (rs.next()) return rs.getInt(1);
		return -1;
	}

	/**
	 * Method to close the physical connection if it is open.
	 */
	public void cleanup(){
		try{
			if (this._connection != null){
				this._connection.close ();
			}//end if
		}catch (SQLException e){
	         // ignored.
		}//end try
	}//end cleanup

	/**
	 * The main execution method
	 * 
	 * @param args the command line arguments this inclues the <mysql|pgsql> <login file>
	 */
	public static void main (String[] args) {
		/*if (args.length != 3) {
			System.err.println (
				"Usage: " + "java [-classpath <classpath>] " + MechanicShop.class.getName () +
		            " <dbname> <port> <user>");
			return;
		}//end if*/
		
		
		
		MechanicShop esql = null;
	    
	    
		try{
			System.out.println("(1)");
			
			try {
				Class.forName("org.postgresql.Driver");
			}catch(Exception e){

				System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
				e.printStackTrace();
				return;
			}
			
			System.out.println("(2)");
			String dbname = "carRepair";
			String dbport = "5432";
			String user = "postgres";
			
			esql = new MechanicShop (dbname, dbport, user, "86032121");
			
			boolean keepon = true;
			while(keepon){
				
																															
				System.out.println("MAIN MENU");
				System.out.println("---------");
				System.out.println("1. AddCustomer");
				System.out.println("2. AddMechanic");
				System.out.println("3. AddCar");
				System.out.println("4. InsertServiceRequest");
				System.out.println("5. CloseServiceRequest");
				System.out.println("6. ListCustomersWithBillLessThan100");
				System.out.println("7. ListCustomersWithMoreThan20Cars");
				System.out.println("8. ListCarsBefore1995With50000Milles");
				System.out.println("9. ListCarsWithTheMostServices");
				System.out.println("10. ListCustomersInDescendingOrderOfTheirTotalBill");
				System.out.println("11. < EXIT");
				
				/*
				 * FOLLOW THE SPECIFICATION IN THE PROJECT DESCRIPTION
				 */
				switch (readChoice()){
					case 1: AddCustomer(esql); break;
					case 2: AddMechanic(esql); break;
					case 3: AddCar(esql); break;
					case 4: InsertServiceRequest(esql); break;
					case 5: CloseServiceRequest(esql); break;
					case 6: ListCustomersWithBillLessThan100(esql); break;
					case 7: ListCustomersWithMoreThan20Cars(esql); break;
					case 8: ListCarsBefore1995With50000Milles(esql); break;
					case 9: ListCarsWithTheMostServices(esql); break;
					case 10: ListCustomersInDescendingOrderOfTheirTotalBill(esql); break;
					case 11: keepon = false; break;
				}
			}
		}catch(Exception e){
			System.err.println (e.getMessage ());
		}finally{
			try{
				if(esql != null) {
					System.out.print("Disconnecting from database...");
					esql.cleanup ();
					System.out.println("Done\n\nBye !");
				}//end if				
			}catch(Exception e){
				// ignored.
			}
		}
	}

	public static int readChoice() {
		int input;
		// returns only if a correct value is given.
		do {
			System.out.print("Please make your choice: ");
			try { // read the integer, parse it and break.
				input = Integer.parseInt(in.readLine());
				break;
			}catch (Exception e) {
				System.out.println("Your input is invalid!");
				continue;
			}//end try
		}while (true);
		return input;
	}//end readChoice
	
	public static void AddCustomer(MechanicShop esql){//1 //id should be given automatically, handle the error when the customer is duplicate 

		try{
			
			int rowCount = esql.executeQuery("SELECT * FROM Customer;");
			rowCount++;
			esql.executeUpdate("ALTER SEQUENCE Customer_id_seq RESTART WITH "+ rowCount +" INCREMENT BY 1;");
			String query = "INSERT INTO Customer (fname, lname, phone, address) VALUES (";
			
			String input;
	        
			System.out.println("Enter First Name:");
			input = in.readLine();
			input = "'" + input +  "'" ;
	        query += input;
	        query += ", ";
			System.out.println("Enter Last Name:");
			input = in.readLine();
			input = "'" + input +  "'" ;
	        query += input;
	        query += ", ";
			System.out.println("Enter Phone Number:");
			input = in.readLine();
			input = "'" + input +  "'" ;
	        query += input;
	        query += ", ";
			System.out.println("Enter Address:");
			input = in.readLine();
			input = "'" + input +  "'" ;
	        query += input;
	        query += ")";
	       
	        
	         esql.executeUpdate(query);
	         System.out.println ("A new costumer has been inserted.");
	       }catch(Exception e){
	         System.err.println (e.getMessage());
	       }
	       
	}
	
	public static void AddMechanic(MechanicShop esql){//2
		
		try{
			
			int rowCount = esql.executeQuery("SELECT * FROM Mechanic;");
			rowCount++;
			esql.executeUpdate("ALTER SEQUENCE Mechanic_id_seq RESTART WITH "+ rowCount +" INCREMENT BY 1;");
			String query = "INSERT INTO Mechanic (fname, lname, experience) VALUES (";
			
			String input;
	        
			System.out.println("Enter First Name:");
			input = in.readLine();
			input = "'" + input +  "'" ;
	        query += input;
	        query += ", ";
			System.out.println("Enter Last Name:");
			input = in.readLine();
			input = "'" + input +  "'" ;
	        query += input;
	        query += ", ";
			System.out.println("Enter Years of Experience:");
			input = in.readLine();
	        query += input;
	        query += ")";
	       
	       
	         esql.executeUpdate(query);
	         System.out.println ("A new mechanic has been inserted.");
	       }catch(Exception e){
	         System.err.println (e.getMessage());
	       }
		}
	
	public static void AddCar(MechanicShop esql){//3
try{
			
			
			String query = "INSERT INTO Car (vin, make, model, year) VALUES (";
			
			String input;
	        
			System.out.println("Enter the VIN number:");
			input = in.readLine();
			input = "'" + input +  "'" ;
	        query += input;
	        query += ", ";
			System.out.println("Enter the make:");
			input = in.readLine();
			input = "'" + input +  "'" ;
	        query += input;
	        query += ", ";
			System.out.println("Enter the model:");
			input = in.readLine();
			input = "'" + input +  "'" ;
	        query += input;
	        query += ", ";
			System.out.println("Enter the year:");
			input = in.readLine();
	        query += input;
	        query += ")";
	       
	       
	         esql.executeUpdate(query);
	         System.out.println ("A new car has been inserted.");
	       }catch(Exception e){
	         System.err.println (e.getMessage());
	       }
	}
	
	public static void InsertServiceRequest(MechanicShop esql){//4 //https://stackoverflow.com/questions/33507546/how-to-validate-date-input-format
		try{
			
			int rowCount = esql.executeQuery("SELECT * FROM Service_Request;");
			rowCount++;
			esql.executeUpdate("ALTER SEQUENCE Service_Request_rid_seq RESTART WITH "+ rowCount +" INCREMENT BY 1;");
			String query = "INSERT INTO Service_Request (customer_id, car_vin, date, odometer,complain) VALUES (";
			
			String input;
			

	        
			System.out.println("Enter Customer ID:");
			input = in.readLine();
	        query += input;
	        query += ", ";
			System.out.println("Enter Car's VIN:");
			input = in.readLine();
			input = "'" + input +  "'" ;
	        query += input;
	        query += ", ";
			
					    
		    boolean date = false;
			while(!date) {
				System.out.println("Enter the date:");
				input = in.readLine();
				 try {
				        
     			      
				        String myDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a")
					            .format(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a").parse(input));
				        
				        date = true;
				        
				        //System.out.println(input);
				        
				        int month = Integer.parseInt(input.substring(0, 2));
				        int day = Integer.parseInt(input.substring(3, 5));
				        int year = Integer.parseInt(input.substring(6, 10));
				        int hour = Integer.parseInt(input.substring(12, 14));
				        int minute = Integer.parseInt(input.substring(15, 17));
				        int second = Integer.parseInt(input.substring(18, 20));
				        
				       /* System.out.println(month);
				        System.out.println(day);
				        System.out.println(year);
				        System.out.println(hour);
				        System.out.println(minute);
				        System.out.println(second);
				        */
				        
				        if(month > 12 || month <= 0 || day > 31 || day <= 0 || year > 2021 || year < 1900 || hour > 12 || hour < 0 || minute > 60 
				        		|| minute < 0 || second > 60 || second < 0) {
				        	System.out.println("The inserted date is not valid. Please try again.");
				        	date = false;
				        }
		    } catch (ParseException e) {
		    	System.out.println(input+" is invalid date format, please insert the date in this format: MM/dd/yyyy hh:mm:ss AM/PM");
		    	date = false;
		    }
		    
			}
		    
		    
		    
		    
		    
		    
			
			
			
			
			
			
			
			
			
			input = "'" + input +  "'" ;
	        query += input;
	        query += ", ";
	        System.out.println("Enter the odometer:");
			input = in.readLine();
	        query += input;
	        query += ", ";
	        System.out.println("Enter the Complain:");
			input = in.readLine();
			input = "'" + input +  "'" ;
	        query += input;
	        query += ")";
	       
	        
	         esql.executeUpdate(query);
	         System.out.println ("A new service request has been inserted.");
	       }catch(Exception e){
	         System.err.println (e.getMessage());
	       }
		}
	
	
	public static void CloseServiceRequest(MechanicShop esql) throws Exception{//5 https://www.javatpoint.com/java-string-to-int
		try{
			
			int rowCount = esql.executeQuery("SELECT * FROM Closed_Request;");
			rowCount++;
			esql.executeUpdate("ALTER SEQUENCE Closed_Request_wid_seq RESTART WITH "+ rowCount +" INCREMENT BY 1;");
			String query = "INSERT INTO Closed_Request (rid, mid, date, comment, bill) VALUES (";
		
			String input;
	        
			System.out.println("Enter the Service Request's ID:");
			input = in.readLine();
	        query += input;
	        query += ", ";
			System.out.println("Enter the Mechanic's ID:");
			input = in.readLine();
	        query += input;
	        query += ", ";
	        
	        	        	        			
			
			 boolean date = false;
				while(!date) {
					System.out.println("Enter the date:");
					input = in.readLine();
			    try {
			        
			        			      			      
			        String myDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a")
				            .format(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a").parse(input));
			        
			        date = true;
			        
			        //System.out.println(input);
			        
			        int month = Integer.parseInt(input.substring(0, 2));
			        int day = Integer.parseInt(input.substring(3, 5));
			        int year = Integer.parseInt(input.substring(6, 10));
			        int hour = Integer.parseInt(input.substring(12, 14));
			        int minute = Integer.parseInt(input.substring(15, 17));
			        int second = Integer.parseInt(input.substring(18, 20));
			        
			       /* System.out.println(month);
			        System.out.println(day);
			        System.out.println(year);
			        System.out.println(hour);
			        System.out.println(minute);
			        System.out.println(second);
			        */
			        
			        if(month > 12 || month <= 0 || day > 31 || day <= 0 || year > 2021 || year < 1900 || hour > 12 || hour < 0 || minute > 60 
			        		|| minute < 0 || second > 60 || second < 0) {
			        	System.out.println("The inserted date is not valid. Please try again.");
			        	date = false;
			        }
			      
			    } catch (ParseException e) {
			    	System.out.println(input+" is invalid date format, please insert the date in this format: MM/dd/yyyy hh:mm:ss AM/PM");
			    	date = false;
			    }
			    
				}
				
				/*
				if(date) {
					 SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
					 java.sql.Date javaDate = (java.sql.Date) sdfrmt.parse(input); 
				     int month = javaDate.getMonth(); 
				     int day = javaDate.getDay();
				     int hour = javaDate.getHours();
				     int minute= javaDate.getMinutes();
				     int second = javaDate.getSeconds();
				     if(month > 12 || month < 1) {
				    	 System.out.println("Month has to be between 1-12");
				     }
				     
				}
			
				*/								
			
			input = "'" + input +  "'" ;
	        query += input;
	        query += ", ";
	        System.out.println("Enter the comment:");
			input = in.readLine();
			input = "'" + input +  "'" ;
	        query += input;
	        query += ", ";
	        System.out.println("Enter the bill:");
			input = in.readLine();
	        query += input;
	        query += ")";
	       
	        
	         esql.executeUpdate(query);
	         System.out.println ("A service request has been closed.");
	       }catch(Exception e){
	         System.err.println (e.getMessage());
	       }
		}
		
	
	
	public static void ListCustomersWithBillLessThan100(MechanicShop esql){//6
		try{
	         String query = "SELECT Customer.id, Customer.fname, Customer.lname, Customer.phone, Customer.address FROM Closed_request, Service_Request, Customer WHERE Closed_request.rid = Service_Request.rid "+
	        		 "AND Service_Request.customer_id = Customer.id AND Closed_request.bill < 100";
	         
	         int rowCount = esql.executeQueryAndPrintResult(query);
	         System.out.println ("total row(s): " + rowCount);
	      }catch(Exception e){
	         System.err.println (e.getMessage());
	      }
	}
	
	public static void ListCustomersWithMoreThan20Cars(MechanicShop esql){//7
		 try{
	         String query = "SELECT Customer.id, Customer.fname, Customer.lname, Customer.phone, Customer.address FROM Customer, Owns WHERE Customer.id = Owns.customer_id GROUP BY Customer.id HAVING count(*)>20"; 

	         int rowCount = esql.executeQueryAndPrintResult(query);
	         System.out.println ("total row(s): " + rowCount);
	      }catch(Exception e){
	         System.err.println (e.getMessage());
	      }
	}
	
	public static void ListCarsBefore1995With50000Milles(MechanicShop esql){//8
		try{
	         String query = "SELECT * FROM Car, Service_Request WHERE Car.vin = Service_Request.car_vin AND "
	         		+ "Car.year < 1995 AND Service_Request.odometer >= 50000";
	         
	         int rowCount = esql.executeQueryAndPrintResult(query);
	         System.out.println ("total row(s): " + rowCount);
	      }catch(Exception e){
	         System.err.println (e.getMessage());
	      }
	}
	
	public static void ListCarsWithTheMostServices(MechanicShop esql){//9
		try{
	         String query = "SELECT  * FROM "+
	        		 "(SELECT Car.vin, Car.make, Car.model, Car.year, COUNT(*) AS service_count FROM Service_Request, Car WHERE Service_Request.car_vin = Car.vin GROUP BY Car.vin) AS temp_1 "+
	        		 "WHERE temp_1.service_count = (SELECT MAX(temp_2.service_count) FROM (SELECT Service_Request.car_vin, COUNT(*) AS service_count FROM Service_Request GROUP BY Service_Request.car_vin) AS temp_2)";

	         
	         int rowCount = esql.executeQueryAndPrintResult(query);
	         System.out.println ("total row(s): " + rowCount);
	      }catch(Exception e){
	         System.err.println (e.getMessage());
	      }
		
	}
	
	public static void ListCustomersInDescendingOrderOfTheirTotalBill(MechanicShop esql){//10
		try{
	         String query = 
	        		 "SELECT * FROM "+
	        		 "(SELECT Customer.id, Customer.fname, Customer.lname, Customer.phone, Customer.address, SUM(Closed_Request.bill) AS sum_bill FROM Closed_request, Service_Request, Customer WHERE Closed_request.rid = Service_Request.rid AND Service_Request.customer_id = Customer.id GROUP BY Customer.id) AS temp "+ 
	        		 "ORDER BY temp.sum_bill Desc";
	        		 					 
	         
	         int rowCount = esql.executeQueryAndPrintResult(query);
	         System.out.println ("total row(s): " + rowCount);
	      }catch(Exception e){
	         System.err.println (e.getMessage());
	      }
		
	}
	
	/*
	 try{
         String query = "SELECT * FROM Catalog WHERE cost < ";
         System.out.print("\tEnter cost: $");
         String input = in.readLine();
         query += input;

         int rowCount = esql.executeQuery(query);
         System.out.println ("total row(s): " + rowCount);
      }catch(Exception e){
         System.err.println (e.getMessage());
      }
	*/
}
