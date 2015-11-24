package it.arcun.mysqlpopulator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class Connect {

	protected static boolean SetConnection(String user, String password, int port) throws Exception{
		try{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:"+port+"/", user, password);
		return con.isClosed();
		
		
		}catch (Exception e){
			return true;
		}
	}
	
	protected static boolean Create(String user, String password, int port, String DB) throws Exception{
		
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:"+port+"/", user, password);
			Statement st = con.createStatement();
			st.execute("CREATE DATABASE "+DB+";");
			Create(user, password, port, DB);
			return true;
			
			
		}catch(Exception e){
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:"+port+"/"+DB, user, password);
			
			Statement st = con.createStatement();
			
			try{
				st.execute("CREATE TABLE Persons(PersonID int,LastName varchar(255),"
					+ "FirstName varchar(255),Address varchar(255),City varchar(255));");
			
			String[] surname = {"Futterkiste","Emparedados", "Taquería", "Hardy", "Hernández", "Rovelli", "de Castro", "Pfalzheim", "Bertrand", "Cartrain"};
			String[] name = {"Alfreds", "Ana Trujillo", "Antonio", "Thomas", "Carlos", "Giovanni", "Isabel", "Henriette", "Marie", "Pascale"};
			String[] address = {"Obere Str. 57", "Avda. de la Constitución 2222" , "Mataderos 2312", "120 Hanover Sq.", "Carrera 22 con Ave.", "Via Ludovico il Moro 22", "Estrada da saúde n. 58", "Mehrheimerstr. 369", "265, boulevard Charonne", "Boulevard Tirou, 255"};
			String[] city = {"Berlin", "México D.F." , "México D.F.", "London", "San Cristóbal", "Bergamo", "Lisboa", "Köln", "Paris", "Charleroi"};
			
			for (Integer i = 0; i <= surname.length-1; i++){
				
				st.execute("INSERT INTO Persons VALUES("+i.toString()+", '"+surname[i]+"', '"+name[i]+"', '"+address[i]+"', '"+city[i]+"');");
				
			}
			}catch(Exception e1){
				return false;
			}
			return true;
		}
	}
}
