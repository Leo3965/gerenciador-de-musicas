package br.usjt.conectores;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectorMySQL {

	private String usuario = "root";
	private String senha = "4571";
	private String host = "localhost";
	private String porta = "3306";
	private String bd = "db_gerenciadordemusicas";
	public Connection obtemConexao (){
		try{
			
			Connection c = DriverManager.getConnection("jdbc:mysql://" + host + ":" + porta + "/" + bd, usuario, senha);
			return c;
		}
		catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}