import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Consultation {
	
	private int id ;
	private Consultation cons[];
	private RDV rdv ;
	private String diagnostique;
	private String Trairement;
	private Boolean decision ;
	
	public Consultation()
	{
		
	}
	public Consultation(RDV r ,String d , String t,Boolean b)
	{
		this.rdv = r ;
		this.diagnostique = d;
		this.Trairement = t;
		this.decision=b;
	}
	public Consultation(RDV r ,String d , String t)
	{
		this.rdv = r ;
		this.diagnostique = d;
		this.Trairement = t;

	}
	public Consultation(RDV r )
	{
		this.rdv = r ;

	}
	public RDV getRdv(){
		return this.rdv;
	}
	public String getDiagnostique(){
		return this.diagnostique;
	}
	public String getTraitement(){
		return this.Trairement;
	}
	public Boolean getDecision(){
		return this.decision;
	}
	public void save ()
	{
		
		Connexion c =new Connexion();
		Statement st = c.getStatement();
		try{
		st.executeUpdate("insert into Consultation(NUM_RDV,PATIENT_ID,MEDECIN_ID,DIAGNOSTIQUE,TRAITEMENT,DECISION) " +
				"VALUES ('"+this.rdv.getId()+"','"+this.rdv.getPatId()+"','"+this.rdv.getMedId()+"','"+this.diagnostique+"','"+this.Trairement+"',"+this.decision+")");
		}catch(SQLException s){
			System.err.println(s.getMessage());
		}
	}
	public Consultation[] getAll()
	{
		Connexion c =new Connexion();
		ResultSet r;
		Statement st = c.getStatement();
		
		try{
			r = st.executeQuery("select NUM_RDV,PATIENT_ID,MEDECIN_ID,DIAGNOSTIQUE,TRAITEMENT,DECISION from Consultation");
			r.last();
			this.cons = new Consultation[r.getRow()];
			r.beforeFirst();
			
			int i=0;
			while(r.next()){
				this.cons[i]= new Consultation(new RDV(r.getInt("NUM_RDV"),r.getInt("MEDECIN_ID"),r.getInt("PATIENT_ID")),r.getString("DIAGNOSTIQUE"),r.getString("TRAITEMENT"),r.getBoolean("DECISION"));
				i++;
			}

		}catch(SQLException s){
			
			System.err.println(s.getMessage());
		}
			return cons;
	}	
	public Consultation[] getHosp()
	{
		Connexion c =new Connexion();
		ResultSet r;
		Statement st = c.getStatement();
		
		try{
			r = st.executeQuery("select NUM_RDV,PATIENT_ID,MEDECIN_ID,DIAGNOSTIQUE,TRAITEMENT from Consultation where DECISION="+true+"");
			r.last();
			this.cons = new Consultation[r.getRow()];
			r.beforeFirst();
			
			int i=0;
			while(r.next()){
				this.cons[i]= new Consultation(new RDV(r.getInt("NUM_RDV"),r.getInt("MEDECIN_ID"),r.getInt("PATIENT_ID")),r.getString("DIAGNOSTIQUE"),r.getString("TRAITEMENT"));
				i++;
			}

		}catch(SQLException s){
			
			System.err.println(s.getMessage());
		}
			return cons;
	}
	public void Delete(int id) {
		Connexion c =new Connexion();
		Statement st = c.getStatement();
		try{
		st.executeUpdate("delete * from Consultation where id="+id+"");
		}catch(SQLException s){
			System.err.println(s.getMessage());
		}
	
	}

}
