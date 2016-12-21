package ModelDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import Model.Brand;

public class BrandDAO extends MySqlConnection implements PatternSimpleDAO<Brand>{

	@Override
	public int create(Brand brand) {
		int result = 0;
		int key = 0;
		openDB();
		try {
			String sql = "INSERT INTO brand (description) VALUES (?);";
			PreparedStatement pStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			pStmt.setString(1, brand.getDescription());
			
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
	
	public Brand findById(int id){
		Brand brand = null;
		openDB();
		try {
			String sql = "SELECT * FROM brand WHERE id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, id);
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {		
				brand = new Brand();
				brand.setDescription(rs.getString("description"));
			}
	
		} catch (SQLException e){
            throw new RuntimeException("Erro. Problema ao mostrar Marca" + e.getMessage());
        } finally {
            closeDB();
        }
        return brand;
	}
	
	public int findByName(String name){
		int idFound = 0;
		openDB();
		try {
			String sql = "SELECT id FROM brand WHERE description = ?;";
			java.sql.PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, name);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				idFound = rs.getInt("id");
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao encontrar a Marca!" + e.getMessage());
		} finally {
			closeDB();
		}
		return idFound;
	}
	
	public List<String> findAll() {
		List<String> brands = new ArrayList<>();
		String description;
		openDB();
		try {
			String sql = "SELECT * FROM brand ORDER BY description;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				
				description = rs.getString("description");
				
				brands.add(description);		
			}
	
		} catch (SQLException e){
            throw new RuntimeException("Erro. Problema ao mostrar Marcas " + e.getMessage());
        } finally {
            closeDB();
        }
        return brands;
	}
	
}
