package ModelDAO;

import java.sql.Connection;
import java.sql.ResultSet;

import Model.City;
import Model.State;

public class CityDAO {

	public City findById(int id){
		City found = null;
		try {
			Connection connection = ConnectionDAO.getInstance().getConnection();
			String sql = "SELECT * FROM city WHERE id = ?;";
			java.sql.PreparedStatement pStmt = connection.prepareStatement(sql);
			
			pStmt.setInt(1, id);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				State state = State.findById(rs.getInt("id_state"));
				found = new City();
				found.setId(rs.getInt("id"));
				found.setName(rs.getString("name"));
				found.setState(state);
			}
			connection.close();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao encontrar a cidade!" + e.getMessage());
		}
		return found;
	}
		
	public int findByName(String name){
		int idFound = 0;
		try {
			Connection connection = ConnectionDAO.getInstance().getConnection();
			String sql = "SELECT id FROM city WHERE name = ?;";
			java.sql.PreparedStatement pStmt = connection.prepareStatement(sql);
			
			pStmt.setString(1, name);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				idFound = rs.getInt("id");
			}
			connection.close();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao encontrar a cidade!" + e.getMessage());
		}
		return idFound;
	}
	
}
