import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Patient {
	private int id;
	private String nom;
	private String prenom;	
	private String malad_ant;	
	private String maladie;
	private int age;
	private int num_tel;
	private int num_id;
	private int num_idp;
	private int code;
	private Patient[] pats ;
	private Patient patient ;
	
	public Patient()
	{
		
	}
	public Patient(String n,String p,String ma,String m,int a,int nt,int nid,int nidp,int i)
	{
		this.id=i;
		this.nom = n;
		this.prenom = p;
		this.malad_ant = ma;
		this.maladie = m;
		this.age = a;
		this.num_tel = nt;
		this.num_id = nid;
		this.num_idp = nidp;
	}
	public Patient(String n,String p,String ma,String m,int a,int nt,int nid,int nidp)
	{

		this.nom = n;
		this.prenom = p;
		this.malad_ant = ma;
		this.maladie = m;
		this.age = a;
		this.num_tel = nt;
		this.num_id = nid;
		this.num_idp = nidp;
	}
	public int save ()
	{
		int lastid=0 ;
		Connexion c =new Connexion();
		ResultSet r;
		Statement st = c.getStatement();
		try{
		st.executeUpdate("insert into PATIENT(NOM,PRENOM,AGE,MALAD_ANT,NUM_ID,NUM_ID_PAR,NUM_TEL,MALADIE) VALUES ('"+this.nom+"','"+this.prenom+"','"+this.age+"','"+this.malad_ant+"','"+this.num_id+"','"+this.num_idp+"','"+this.num_tel+"','"+this.maladie+"')");
		}catch(SQLException s){
			System.err.println(s.getMessage());
		}
		try{
			r = st.executeQuery("select ID from PATIENT");
			while(r.next()){
				lastid= r .getInt("ID");
			} 
			
		}catch(SQLException s){
			System.err.println(s.getMessage());
		}
		return lastid;
		
	}
	public Patient[] getAll()
	{
		Connexion c =new Connexion();
		ResultSet r;
		Statement st = c.getStatement();
		
		try{
			r = st.executeQuery("select NOM,PRENOM,AGE,MALAD_ANT,NUM_ID,NUM_ID_PAR,NUM_TEL,MALADIE,ID from Patient");
			
			r.last();
			this.pats = new Patient[r.getRow()];
			r.beforeFirst();
			
			int i=0;
			while(r.next()){
				this.pats[i]= new Patient(r.getString("NOM"),r.getString("PRENOM"),r.getString("MALAD_ANT"),r.getString("MALADIE"),r.getInt("AGE"),r.getInt("NUM_TEL"),r.getInt("NUM_ID"),r.getInt("NUM_ID_PAR"),r.getInt("ID"));
				i++;
			}
		}	
		catch(SQLException s){
			System.err.println(s.getMessage());
		}
		return pats;
	}
	public String getNom()
	{
		return this.nom;
	}
	public String getPrenom()
	{
		return this.prenom;
	}
	public String getMalad()
	{
		return this.maladie;
	}
	public String getMaladAnt()
	{
		return this.malad_ant;
	}
	public int getAge()
	{
		return this.age;
	}
	public int getTel()
	{
		return this.num_tel;
	}
	public int getId()
	{
		return this.id;
	}
	public int getNid()
	{
		return this.num_id;
	}
	public int getNidp()
	{
		return this.num_idp;
	}
	public Patient getInfo(int id)
	{
		Connexion c =new Connexion();
		ResultSet r;
		Statement st = c.getStatement();
		
		try{
			
			r = st.executeQuery("select NOM,PRENOM,AGE,MALAD_ANT,NUM_ID,NUM_ID_PAR,NUM_TEL,MALADIE from Patient where ID = "+id+"");
			while(r.next()){
			this.patient = new Patient(r.getString("NOM"),r.getString("PRENOM"),r.getString("MALAD_ANT"),r.getString("MALADIE"),r.getInt("AGE"),r.getInt("NUM_TEL"),r.getInt("NUM_ID"),r.getInt("NUM_ID_PAR"),id);
			break;
			}
			

			}
			catch(SQLException s){
				
				System.err.println(s.getMessage());
			}
			return patient;
	}
	public void Delete(int id) {
		Connexion c =new Connexion();
		Statement st = c.getStatement();
		try{
		st.executeUpdate("delete * from Patient where id="+id+"");
		st.executeUpdate("delete * from Consultation where PATIENT_ID="+id+"");
		st.executeUpdate("delete * from Hospitalisation where PATIENT_ID="+id+"");
		st.executeUpdate("delete * from RDV where PATIENT_ID="+id+"");
		}catch(SQLException s){
			System.err.println(s.getMessage());
		}
	
	}
}
