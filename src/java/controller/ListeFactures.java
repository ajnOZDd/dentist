package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import generalisationArisaina.bdd.connexion.Connexion;
import model.Facture;

/**
 * Servlet implementation class ListeFactures
 */
@WebServlet("/ListeFactures")
public class ListeFactures extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection c =Connexion.connectToPostgresDatabase();
		
		try {
			ArrayList<Object> factures = new Facture().getFromDatabase(c);
			
			request.setAttribute("factures", factures);
			
			c.close();
			
			request.getRequestDispatcher("./tableauFacture.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
