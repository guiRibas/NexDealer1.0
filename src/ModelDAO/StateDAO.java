package ModelDAO;

import java.sql.Connection;
import java.sql.ResultSet;

import Model.State;

public class StateDAO {

	public State findById(int id){
		State found = null;
		try {
			Connection connection = ConnectionDAO.getInstance().getConnection();
			String sql = "SELECT * FROM state WHERE id=?;";
			java.sql.PreparedStatement pStmt = connection.prepareStatement(sql);
			
			pStmt.setInt(1, id);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				found = new State(rs.getString("name"), rs.getString("initials"));
				found.setId(id);
			}
			connection.close();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao encontrar o estado!" + e.getMessage());
		}
		return found;
	}
	
}
