package model;

import DAO.BDD;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import generalisationArisaina.bdd.connexion.Connexion;
import generalisationArisaina.bdd.objects.DatabaseObject;

public class Facture extends DatabaseObject{
	String facture_id;
    String facture_client;
    Date facture_date;
    String client_id;
    
    public Facture() {
    	
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }
    

	public String getFacture_id() {
		return facture_id;
	}

	public void setFacture_id(String facture_id) {
		this.facture_id = facture_id;
	}

	public String getFacture_client() {
		return facture_client;
	}

	public void setFacture_client(String facture_client) {
		this.facture_client = facture_client;
	}

	public Date getFacture_date() {
		return facture_date;
	}

	public void setFacture_date(Date facture_date) {
		this.facture_date = facture_date;
	}
	
	public ArrayList<Object> getDetailsFacture(Connection c) throws Exception {
		boolean opened = false;
		
		if (c == null) {
			opened = true;
			
			c = Connexion.connectToPostgresDatabase();
		}
		
		FactureDetails factureDetail = new FactureDetails();
		factureDetail.setSelection("factureId_origine", getFacture_id());
		
		ArrayList<Object> factureDetails = factureDetail.getFromDatabase(c);
		
		if (opened) {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return factureDetails;
	}
	
	public double getTotalPrix(Connection c) throws Exception {
		boolean opened = false;
		
		if (c == null) {
			opened = true;
			
			c = Connexion.connectToPostgresDatabase();
		}
		
		ArrayList<Object> f_services = getDetailsFacture(c);
		
		double somme = 0;
		
		for (Object o : f_services) {
			FactureDetails details = (FactureDetails) o;
			
			somme += details.getTotalPrix(c);
		}
				
		if (opened) {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return somme;
	}
        
        public double efaVoaloha(Report report){
            double vola = 0.0;
            BDD bdd = new BDD();
            
            String requete = "";
            if (report == null) {
                requete = "select sum(montant_paye) from facture_payer where paye_date <= now()";
            } else {
                requete = "select sum(montant_paye) from facture_payer where paye_date <= now() and paye_date > '" + report.getDate_report() + "'";
            }
        
            vola = Double.valueOf(bdd.getOne(requete));
            return vola;
        }
        
        public double getReste() throws Exception {
            return getTotalPrix(null) - efaVoaloha(null);
        }
        
        public double efaVoaloha(String datee, Report report){
            double vola = 0.0;
            BDD bdd = new BDD();
            String requete = "";
            
            if (report == null) {
                requete = "select sum(montant_paye) from facture_payer where paye_date <='"+datee+"'";
            } else {
                requete = "select sum(montant_paye) from facture_payer where paye_date <='"+datee+"' and paye_date > '" + report.getDate_report() + "'";
            }
            vola = Double.valueOf(bdd.getOne(requete));
            return vola;
        }
}
