package ModelDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import Controller.VersionController;
import Model.Brand;
import Model.Model;

public class ModelDAO extends MySqlConnection implements PatternSimpleDAO<Model>{

	VersionController versionController;
	
	public ModelDAO(VersionController versionController) {
		this.versionController = versionController;
	}
	
	@Override
	public int create(Model model) {
		int result = 0;
		int key = 0;
		openDB();
		try {
			String sql = "INSERT INTO model (description) VALUES (?);";
			PreparedStatement pStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			pStmt.setString(1, model.getDescription());
			
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
	
	public Model findById(int id){
		Model model = null;
		openDB();
		try {
			String sql = "SELECT * FROM model WHERE id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, id);
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {		
				model = new Model();
				Brand brand = versionController.findBrandById(rs.getInt("id_brand"));
				model.setBrand(brand);
				model.setDescription(rs.getString("description"));
			}
	
		} catch (SQLException e){
            throw new RuntimeException("Erro. Problema ao mostrar Marca" + e.getMessage());
        } finally {
            closeDB();
        }
        return model;
	}
	
	public int findByName(String name){
		int idFound = 0;
		openDB();
		try {
			String sql = "SELECT id FROM model WHERE description = ?;";
			java.sql.PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, name);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				idFound = rs.getInt("id");
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao encontrar o Modelo!" + e.getMessage());
		} finally {
			closeDB();
		}
		return idFound;
	}
	
	public List<String> findAll(int id) {
		List<String> models = new ArrayList<>();
		String description;
		openDB();
		try {
			String sql = "SELECT * FROM model WHERE id_brand = ? ORDER BY description;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, id);
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				
				description = rs.getString("description");
				
				models.add(description);		
			}
	
		} catch (SQLException e){
            throw new RuntimeException("Erro. Problema ao mostrar Modelos " + e.getMessage());
        } finally {
            closeDB();
        }
        return models;
	}
	
}
