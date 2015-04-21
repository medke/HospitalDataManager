import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Medecin {
	private Medecin med[];
	private Medecin medecin;
	private int id; ;
	private String nom ;
	private String prenom ;
	private String specialite ;
	private int tel ;
	private int numinscr ;
	private String adr ;

	public Medecin()
	{

		
	}
	public Medecin(String n,String pr,String sp,int t,int num,String ad)
	{
		this.nom = n ;
		this.prenom=pr;
		this.specialite= sp;
		this.tel = t;
		this.numinscr=num;
		this.adr = ad ;
		
	}
	public Medecin(String n,String pr,String sp,int t,int num,String ad,int id)
	{
		this.nom = n ;
		this.prenom=pr;
		this.specialite= sp;
		this.tel = t;
		this.numinscr=num;
		this.adr = ad ;
		this.id = id ;
		
	}
	public Medecin(String n,String pr)
	{
		this.nom = n ;
		this.prenom=pr;

		
	}
	public Medecin(String n,String pr,int id)
	{
		this.nom = n ;
		this.prenom=pr;
		this.id = id;

		
	}
	public void setNom(String n){
		this.nom = n ;
	}
	
	public int getid(){
		return this.id;
	}
	public String getNom(){
		return this.nom;
	}
	public String getPrenom(){
		return this.prenom;
	}
	public String getSpec(){
		return this.specialite;
	}	
	public String getAdr(){
		return this.adr;
	}
	public int getTel(){
		return this.tel;
	}
	public int getNum(){
		return this.numinscr;
	}

	public Medecin[] MedecinAll()
	{
		Connexion c =new Connexion();
		ResultSet r;
		Statement st = c.getStatement();
		
		try{
			r = st.executeQuery("select NOM,PRENOM,SPECIALITE,TEL,ADRESSE,INSCRIPTION,ID from Medecin");
			
			r.last();
			this.med = new Medecin[r.getRow()];
			r.beforeFirst();
			
			int i=0;
			while(r.next()){
				this.med[i]= new Medecin(r.getString("NOM"),r.getString("PRENOM"),r.getString("SPECIALITE"),r.getInt("TEL"),r.getInt("INSCRIPTION"),r.getString("ADRESSE"),r.getInt("ID"));
				i++;
			}
		}	
		catch(SQLException s){
			System.err.println(s.getMessage());
		}
		return med;
	}
	public void save ()
	{

		Connexion c =new Connexion();
		Statement st = c.getStatement();
		try{
		st.executeUpdate("insert into MEDECIN(NOM,PRENOM,SPECIALITE,TEL,INSCRIPTION,ADRESSE) VALUES ('"+this.nom+"','"+this.prenom+"','"+this.specialite+"','"+this.tel+"','"+this.numinscr+"','"+this.adr+"')");
		}catch(SQLException s){
			System.err.println(s.getMessage());
		}
	}
	public Medecin getInfo(int id)
	{
		Connexion c =new Connexion();
		ResultSet r;
		Statement st = c.getStatement();
		
		try{
			
			r = st.executeQuery("select NOM,PRENOM,SPECIALITE,TEL,ADRESSE,INSCRIPTION from Medecin where ID = "+id+"");
			while(r.next()){
			this.medecin = new Medecin(r.getString("NOM"),r.getString("PRENOM"),r.getString("SPECIALITE"),r.getInt("TEL"),r.getInt("INSCRIPTION"),r.getString("ADRESSE"));
			break;
			}
			

			}
			catch(SQLException s){
				
				System.err.println(s.getMessage());
			}
			return medecin;
	}
	public void Delete(int id) {
		Connexion c =new Connexion();
		Statement st = c.getStatement();
		try{
		st.executeUpdate("delete * from Medecin where id="+id+"");
		st.executeUpdate("delete * from Consultation where MEDECIN_ID="+id+"");
		st.executeUpdate("delete * from Hospitalisation where MEDECIN_ID="+id+"");
		st.executeUpdate("delete * from RDV where MEDECIN_ID="+id+"");
		}catch(SQLException s){
			System.err.println(s.getMessage());
		}
	
	}
}
