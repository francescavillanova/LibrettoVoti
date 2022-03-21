package it.polito.tdp.librettovotidb;

import java.sql.Connection;
import java.sql.DriverManager; //permette di usare l'oggetto DriverManager
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProvaVoti {
	
	public void aggiungiVoto(String nome, int punti) {  //metodo al di fuori del main, devo aprire e chiudere la connessione anche qui
		String url="jdbc:mysql://localhost:3306/libretto?user=root&password=root";  
		 
		try {
			Connection conn = DriverManager.getConnection(url);  
			//Statement st=conn.createStatement();  
			String sql= "INSERT INTO voti (nome, punti) VALUES (?, ?)";
			PreparedStatement st=conn.prepareStatement(sql);
			//String sql="INSERT INTO voti (nom, punti)"+"VALUES ('" +nome+ "', "+punti+");";
			
			st.setString(1, nome); //inserisco i valori passati come input al posto dei ?
			st.setInt(2, punti);
			
			//int res=st.executeUpdate(sql);
			int res=st.executeUpdate();  //senza la stringa
			st.close();
			conn.close(); 
			if(res==1)
				System.out.println("Dato correttamente inserito");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		ProvaVoti provaVoti=new ProvaVoti();  //creo un oggetto di questa stessa classe per poter chiamare un metodo statico all'interno del main
		provaVoti.aggiungiVoto("Tecniche di Programmazione", 30);
		
		//Per connettermi al database
		
		String url="jdbc:mysql://localhost:3306/libretto?user=root&password=root";  //stringa di connessione
		 
		try {
			Connection conn = DriverManager.getConnection(url);  //creo una connessione
			
			 Statement st=conn.createStatement();
			 
			 String sql="SELECT * FROM voti";
			 ResultSet res=st.executeQuery(sql); //esegue la query passata
			 
			 while(res.next()) {  //si ferma all'ultima riga della tabella perchè quando riceve false esce dal ciclo
				 String nome=res.getString("nome");
				 int voto=res.getInt("punti");
				 System.out.println(nome+" ,"+voto);
				 
			 }
			 st.close();
			
			
			conn.close(); //devo sempre ricordarmi di chiudere la connessione alla fine
		}catch(SQLException e) {
			e.printStackTrace();
		}
		

	}

}
