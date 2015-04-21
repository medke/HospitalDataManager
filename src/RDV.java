import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;



public class RDV {

	public int id ;
	public int pat;
	public int med;
	private Date date;
	private String type_mal;
	private RDV[] rdvs ;
	private Time  hour;
	
	public RDV() {
		
	}
	public RDV(int m,int p,Date d,Time h) {
		
		this.med=m;
		this.pat=p;
		this.date=new Date(d.getTime());
		this.hour=h;
		
	}
	public RDV(int m,int p,Date d) {
		
		this.med=m;
		this.pat=p;
		this.date=new Date(d.getTime());
		
	}
	public RDV(int id,int m,int p) {
		
		this.med=m;
		this.pat=p;
		this.id=id;
		
	}
	public RDV(int m,int p) {
		
		this.med=m;
		this.pat=p;

		
	}
	public RDV(int m,int p,Date d,int id,Time h) {
		
		this.med=m;
		this.pat=p;
		this.date=new Date(d.getTime());
		this.id= id;
		this.hour=h;
		
	}	
	public int getId()
	{
		return this.id;
	}
	public int getMedId()
	{
		return this.med;
	}
	public int getPatId()
	{
		return this.pat;
	}
	public Date getDate()
	{
		return this.date;
	}
	public Time getHeur()
	{
		return this.hour;
	}
	public void save ()
	{

		Connexion c =new Connexion();
		Statement st = c.getStatement();
		try{
		st.executeUpdate("insert into RDV(MEDECIN_ID,PATIENT_ID,RDV_DATE,RDV_HEUR) VALUES ('"+this.med+"','"+this.pat+"','"+this.date+"','"+this.hour+"')");
		}catch(SQLException s){
			System.err.println(s.getMessage());
		}
	}
	public RDV[] getAll()
	{
		Connexion c =new Connexion();
		ResultSet r;
		Statement st = c.getStatement();
		
		try{
			r = st.executeQuery("select MEDECIN_ID,PATIENT_ID,RDV_DATE,RDV_HEUR,ID from RDV");
			r.last();
			this.rdvs = new RDV[r.getRow()];
			r.beforeFirst();
			
			int i=0;
			while(r.next()){
				this.rdvs[i]= new RDV(r.getInt("MEDECIN_ID"),r.getInt("PATIENT_ID"),r.getDate("RDV_DATE"),r.getInt("ID"),r.getTime("RDV_HEUR"));
				i++;
			}

		}catch(SQLException s){
			
			System.err.println(s.getMessage());
		}
			return rdvs;
	}
	public void Delete(int id) {
		Connexion c =new Connexion();
		Statement st = c.getStatement();
		try{
		st.executeUpdate("delete * from RDV where id="+id+"");
		}catch(SQLException s){
			System.err.println(s.getMessage());
		}
	
	}
}
