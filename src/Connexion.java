import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Connexion {
	private Statement st;
	public Connection cnx;
	
	public Connexion() {
		
	try
	{
		
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+".//CLINIQUE.accdb";
		Connection cnx = DriverManager.getConnection(url);
		st = cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
			    ResultSet.CONCUR_READ_ONLY) ;
	
	}
	catch(ClassNotFoundException e){
		System.err.println(e.getMessage());
	}
	catch(SQLException s){
		System.err.println(s.getMessage());
	}
	
	
	
	}
	public Statement getStatement()
	{
		return this.st;
	}


}

