package ModelDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Type;

public class TypeDAO extends MySqlConnection{

	public List<String> findAll() {
		List<String> types = new ArrayList<>();
		String description;
		openDB();
		try {
			String sql = "SELECT * FROM type ORDER BY description;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				
				description = rs.getString("description");
				
				types.add(description);		
			}
	
		} catch (SQLException e){
            throw new RuntimeException("Erro. Problema ao mostrar Tipos " + e.getMessage());
        } finally {
            closeDB();
        }
        return types;
	}
	
	public int findByName(String name){
		int idFound = 0;
		openDB();
		try {
			String sql = "SELECT id FROM type WHERE description = ?;";
			java.sql.PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, name);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				idFound = rs.getInt("id");
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao encontrar o Tipo!" + e.getMessage());
		} finally {
			closeDB();
		}
		return idFound;
	}
	
	public Type findById(int id){
		Type type = null;
		openDB();
		try {
			String sql = "SELECT * FROM type WHERE id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, id);
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {		
				type = new Type();
				type.setId(rs.getInt("id"));
				type.setDescription(rs.getString("description"));
			}
	
		} catch (SQLException e){
            throw new RuntimeException("Erro. Problema ao mostrar Tipo" + e.getMessage());
        } finally {
            closeDB();
        }
        return type;
	}
}
