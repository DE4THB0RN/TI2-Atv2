package atv2;

import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "Wishlist";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirItem(Wishlist wishlist) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO item (nome, estado, prcnt, id) "
					       + "VALUES ('"+wishlist.getNome()+ "', " + wishlist.getEstado() + ", "  
					       + wishlist.getPrcnt() + ", " + wishlist.getId() + ");");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarItem(Wishlist wishlist) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE item SET nome = '" + wishlist.getNome() + "', estado = '"  
				       + wishlist.getEstado() + "', prcnt = '" + wishlist.getPrcnt() + "'"
					   + " WHERE id = " + wishlist.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirItem(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM item WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Wishlist[] getItems() {
		Wishlist[] wishlist = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM item");		
			if(rs.next()){
	             rs.last();
	             wishlist = new Wishlist[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
		                wishlist[i] = new Wishlist(rs.getInt("prcnt"), rs.getString("nome"), 
                        		                  rs.getBoolean("estado"), rs.getInt("id"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return wishlist;
	}

	
	public Wishlist[] getJogosPromo() {
		Wishlist[] wishlist = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM item WHERE estado = true");		
	         if(rs.next()){
	             rs.last();
	             wishlist = new Wishlist[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
		                wishlist[i] = new Wishlist(rs.getInt("prcnt"), rs.getString("nome"), 
                         		                  rs.getBoolean("estado"), rs.getInt("id"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return wishlist;
	}
}