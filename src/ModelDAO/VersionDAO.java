package ModelDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import Controller.VersionController;
import Model.Model;
import Model.Version;

public class VersionDAO extends MySqlConnection implements PatternSimpleDAO<Version>{

	VersionController versionController = new VersionController();
	
	@Override
	public int create(Version version) {
		int result = 0;
		int key = 0;
		openDB();
		try {
			String sql = "INSERT INTO version (description) VALUES (?);";
			PreparedStatement pStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			pStmt.setString(1, version.getDescription());
			
			result = pStmt.executeUpdate();
			
			ResultSet resultKey = pStmt.getGeneratedKeys();
			if(resultKey.next()){
				key = resultKey.getInt(1);
			}

		} catch (SQLException sqlE) {		
			System.out.println(sqlE.getMessage());
		} finally {
			closeDB();
		}
		return key;
	}	
	
	public Version findById(int id){
		Version version = null;
		openDB();
		try {
			String sql = "SELECT * FROM version WHERE id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, id);
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {		
				version = new Version();
				Model model = versionController.findModelById(rs.getInt("id_model"));
				version.setId(rs.getInt("id"));
				version.setModel(model);
				version.setDescription(rs.getString("description"));
			}
	
		} catch (SQLException e){
            throw new RuntimeException("Erro. Problema ao mostrar Versão" + e.getMessage());
        } finally {
            closeDB();
        }
        return version;
	}
	
	public int findByName(String name){
		int idFound = 0;
		openDB();
		try {
			String sql = "SELECT id FROM version WHERE description = ?;";
			java.sql.PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, name);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				idFound = rs.getInt("id");
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao encontrar a Versão!" + e.getMessage());
		} finally {
			closeDB();
		}
		return idFound;
	}
	
	public List<String> findAll(int id) {
		List<String> versions = new ArrayList<>();
		String description;
		openDB();
		try {
			String sql = "SELECT * FROM version WHERE id_model = ? ORDER BY description;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, id);
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				
				description = rs.getString("description");
				
				versions.add(description);		
			}
	
		} catch (SQLException e){
            throw new RuntimeException("Erro. Problema ao mostrar Versões " + e.getMessage());
        } finally {
            closeDB();
        }
        return versions;
	}
	
}
