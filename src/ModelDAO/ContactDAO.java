package ModelDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import Model.City;
import Model.Contact;

public class ContactDAO extends MySqlConnection implements PatternSimpleDAO<Contact>{

	@Override
	public int create(Contact contact) {
		int result = 0;
		int key = 0;
		openDB();
		try {
			String sql = "INSERT INTO contact (id_city, postalCode, address, numberOfHouse, complement, neighborhood, ddd, telephone, email) VALUES (?,?,?,?,?,?,?,?,?);";
			PreparedStatement pStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			pStmt.setInt(1, contact.getCity().getId());
			pStmt.setInt(2, contact.getPostalCode());
			pStmt.setString(3, contact.getAddress());
			pStmt.setInt(4, contact.getNumberOfHouse());
			pStmt.setString(5, contact.getComplement());
			pStmt.setString(6, contact.getNeighborhood());
			pStmt.setInt(7, contact.getDdd());
			pStmt.setInt(8, contact.getTelephone());
			pStmt.setString(9, contact.getEmail());
			
			result = pStmt.executeUpdate();
			
			ResultSet resultKey = pStmt.getGeneratedKeys();
			if(resultKey.next()){
				key=resultKey.getInt(1);
			}
			
		} catch (SQLException sqlE) {		
			System.out.println(sqlE.getMessage());
		} finally {
			closeDB();
		}
		return key;
	}
	
	public Contact findById(int id){
		Contact contact = null;
		openDB();
		try {
			String sql = "SELECT * FROM contact WHERE id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, id);
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {		
				contact = new Contact();
				contact.setId(rs.getInt("id"));
				contact.setCity(City.findById(rs.getInt("id_city")));
				contact.setPostalCode(rs.getInt("postalCode"));
				contact.setAddress(rs.getString("address"));
				contact.setNumberOfHouse(rs.getInt("numberOfHouse"));
				contact.setComplement(rs.getString("complement"));
				contact.setNeighborhood(rs.getString("neighborhood"));
				contact.setDdd(rs.getInt("ddd"));
				contact.setTelephone(rs.getInt("telephone"));
				contact.setEmail(rs.getString("email"));		
			}
	
		} catch (SQLException e){
            throw new RuntimeException("Erro. Problema ao mostrar Cliente" + e.getMessage());
        } finally {
            closeDB();
        }
        return contact;
	}

}
