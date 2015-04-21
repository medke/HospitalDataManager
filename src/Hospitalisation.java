import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Hospitalisation {
	private Consultation consultation;
	private Hospitalisation[] hosp;
	private int id;
	private String service;
	private int num_salle;
	private int num_lit;
	private Date date_entre;
	private Date date_sortie;
	private Boolean gard_malad;
	private String bilan;
	
	public Hospitalisation()
	{
		
	}
	public Hospitalisation(Consultation c,String s , int ns, int nl , Date de,Date ds,Boolean gm,String b)
	{
		this.consultation = c;
		this.service= s;
		this.num_salle = ns;
		this.num_lit =nl;
		this.date_entre=de;
		this.date_sortie=ds;
		this.gard_malad=gm;
		this.bilan=b;

	}
	public Consultation getCons()
	{
		return this.consultation;
	}
	public int getNumS()
	{
		return this.num_salle;
	}
	public int getNumL()
	{
		return this.num_lit;
	}
	public Date getEntre()
	{
		return this.date_entre;
	}
	public Date getSortie()
	{
		return this.date_sortie;
	}
	public String getService()
	{
		return this.service;
	}
	public String getBilan()
	{
		return this.bilan;
	}
	public Boolean getGard()
	{
		return this.gard_malad;
	}
	public void save ()
	{
		
		Connexion c =new Connexion();
		Statement st = c.getStatement();
		try{
		st.executeUpdate("insert into Hospitalisation(PATIENT_ID,MEDECIN_ID,SERVICE,DATE_ENTRE,DATE_SORTIE,NUM_SALLE,NUM_LIT,BILAN_A_FAIRE,GARDE_MALADE) " +
				"VALUES ('"+this.consultation.getRdv().getPatId()+"','"+this.consultation.getRdv().getMedId()+"','"+this.service+"','"+this.date_entre+"','"+this.date_sortie+"','"+this.num_salle+"','"+this.num_lit+"','"+this.bilan+"',"+this.gard_malad+")");
		}catch(SQLException s){
			System.err.println(s.getMessage());
		}
	}
	public Hospitalisation[] getAll()
	{
		Connexion c =new Connexion();
		ResultSet r;
		Statement st = c.getStatement();
		
		try{
			r = st.executeQuery("select PATIENT_ID,MEDECIN_ID,SERVICE,DATE_ENTRE,DATE_SORTIE,NUM_SALLE,NUM_LIT,BILAN_A_FAIRE,GARDE_MALADE from Hospitalisation");
			r.last();
			this.hosp = new Hospitalisation[r.getRow()];
			r.beforeFirst();
			
			int i=0;
			while(r.next()){
				this.hosp[i]= new Hospitalisation(new Consultation(new RDV(r.getInt("PATIENT_ID"),r.getInt("MEDECIN_ID"))),
						r.getString("SERVICE"),r.getInt("NUM_SALLE"),r.getInt("NUM_LIT"),r.getDate("DATE_ENTRE"),
						r.getDate("DATE_SORTIE"),r.getBoolean("GARDE_MALADE"),r.getString("BILAN_A_FAIRE"));
				i++;
			}

		}catch(SQLException s){
			
			System.err.println(s.getMessage());
		}
			return hosp;
	}	

}
