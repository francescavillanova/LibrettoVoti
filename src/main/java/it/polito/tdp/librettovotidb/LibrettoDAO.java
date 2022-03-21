package it.polito.tdp.librettovotidb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.librettovoti.model.Voto;

public class LibrettoDAO {
	
	public boolean creaVoto(Voto v) {  //meglio lavorare con gli oggetti che con i tipi semplici
		try {
		  Connection conn=DBConnect.getConnection();  //apro la connessione richiamando il metodo della classe DBConnect
		  
		  String sql= "INSERT INTO voti (nome, punti) VALUES (?, ?)";
		  PreparedStatement st=conn.prepareStatement(sql);
						
		  st.setString(1, v.getNome());  
		  st.setInt(2, v.getPunti());
						
		  int res=st.executeUpdate();  
		  st.close();
			
	      conn.close(); 
		
	      return (res==1); //se l'inserimento è andato a buon fine ritorna true
	      
		}catch(SQLException e) {
			e.printStackTrace();
			return false;  //ritorna false se non è andato a buon fine l'inserimento
		}
		
	}
	
	public List<Voto> readAllVoto(){
		try {
			Connection conn=DBConnect.getConnection();
			String sql="SELECT * FROM voti";
			PreparedStatement st=conn.prepareStatement(sql);
			 
			 
			 ResultSet res=st.executeQuery(); 
			 
			 List<Voto> result=new ArrayList<>();
			 
			 while(res.next()) {  
				 String nome=res.getString("nome");
				 int punti=res.getInt("punti");
				 result.add(new Voto(nome, punti));
				 
			 }
			 st.close();
			 conn.close();
			 return result;
			
		}catch(SQLException e) {
			System.out.print(e);
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Voto readVotoByNome(String nome) {
		
	}

}
