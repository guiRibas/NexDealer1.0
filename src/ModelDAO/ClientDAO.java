package ModelDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Controller.ContactController;
import Model.Client;
import Model.Model;
import Model.Sale;
import Model.Vehicle;
import Model.Version;

public class ClientDAO extends MySqlConnection implements PatternClientDAO<Client>{

	ContactController contactController = new ContactController();
	
	@Override
	public int create(Client client, int idContact) {
		int result = 0;
		openDB();
		try {
			String sql = "INSERT INTO client (id_contact, name, cpf, rg, gender, birthDate, civilStatus, status) VALUES (?,?,?,?,?,?,?,?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, idContact);
			pStmt.setString(2, client.getName());
			pStmt.setString(3, client.getCpf());
			pStmt.setString(4, client.getRg());
			pStmt.setString(5, client.getGender());
			pStmt.setString(6, client.getBirthDate());
			pStmt.setString(7, client.getCivilStatus());
			pStmt.setString(8, "A");
			
			result = pStmt.executeUpdate();
			
			return result;		
		} catch (SQLException sqlE) {		
			System.out.println(sqlE.getMessage());
		} finally {
			closeDB();
		}
		return result;
	}

	@Override
	public int update(Client client) {
		int result = 0;
		openDB();
		try {
			String sql = "UPDATE client, contact SET client.id_contact=?, client.name=?, client.cpf=?, client.rg=?, client.gender=?, client.birthDate=?, client.civilStatus=?, contact.postalCode=?, contact.address=?, contact.numberOfHouse=?, contact.complement=?, contact.neighborhood=?, contact.ddd=?, contact.telephone=?, contact.email=?, client.status=?  WHERE client.id = ? AND contact.id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, client.getContact().getId());
			pStmt.setString(2, client.getName());
			pStmt.setString(3, client.getCpf());
			pStmt.setString(4, client.getRg());
			pStmt.setString(5, client.getGender());
			pStmt.setString(6, client.getBirthDate());
			pStmt.setString(7, client.getCivilStatus());
			pStmt.setInt(8, client.getContact().getPostalCode());
			pStmt.setString(9, client.getContact().getAddress());
			pStmt.setInt(10, client.getContact().getNumberOfHouse());
			pStmt.setString(11, client.getContact().getComplement());
			pStmt.setString(12, client.getContact().getNeighborhood());
			pStmt.setInt(13, client.getContact().getDdd());
			pStmt.setInt(14, client.getContact().getTelephone());
			pStmt.setString(15, client.getContact().getEmail());
			pStmt.setString(16, "A");
			pStmt.setInt(17, client.getId());
			pStmt.setInt(18, client.getContact().getId());
			
			result = pStmt.executeUpdate();

			return result;
		} catch (SQLException sqlE) {		
			System.out.println(sqlE.getMessage());
		} finally {
			closeDB();
		}
		return result;
	}

	@Override
	public int delete(Integer id) {
		openDB();
		int result = 0;
		try {
			String sql = "UPDATE client SET status = ? WHERE id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, "D");
			pStmt.setInt(2, id);
			
			result = pStmt.executeUpdate();

			return result;
		} catch (SQLException sqlE) {		
			sqlE.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	
	public List<Client> filterByName(String name){
		String status;
		List<Client> clients = new ArrayList<>();
		openDB();
		try {
			String sql = "SELECT * FROM client WHERE name LIKE ? AND status = ? ORDER BY createdAt;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, "%"+name+"%");
			pStmt.setString(2, "A");
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				Client client = new Client();
				client.setId(rs.getInt("id"));
				client.setContact(contactController.findById(rs.getInt("id_contact")));
				client.setName(rs.getString("name"));
				client.setCpf(rs.getString("cpf"));
				client.setRg(rs.getString("rg"));
				client.setGender(rs.getString("gender"));
				client.setBirthDate(rs.getString("birthDate"));
				client.setCivilStatus(rs.getString("civilStatus"));
				status = rs.getString("status");
				client.setStatus(status=="A"?"Inativo":"Ativo");
				
				clients.add(client);		
			}
		} catch (SQLException e){
            throw new RuntimeException("Erro. Problema ao mostrar Cliente " + e.getMessage());
        } finally {
            closeDB();
        }
        return clients;
	}
	
	public int findByName(String name){
		int idFound = 0;
		openDB();
		try {
			String sql = "SELECT id FROM client WHERE name LIKE ? AND status = ?;";
			java.sql.PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, "%"+name+"%");
			pStmt.setString(2, "A");
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				idFound = rs.getInt("id");
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao encontrar o Cliente! " + e.getMessage());
		} finally {
			closeDB();
		}
		return idFound;
	}
	
	public Client findById(int id){
		Client client = null;
		String status = "";
		openDB();
		try {
			String sql = "SELECT * FROM client WHERE id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, id);
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {		
				client = new Client();
				client.setId(rs.getInt("id"));
				client.setContact(contactController.findById(rs.getInt("id_contact")));
				client.setName(rs.getString("name"));
				client.setCpf(rs.getString("cpf"));
				client.setRg(rs.getString("rg"));
				client.setGender(rs.getString("gender"));
				client.setBirthDate(rs.getString("birthDate"));
				client.setCivilStatus(rs.getString("civilStatus"));
				status = rs.getString("status");
				client.setStatus(status=="A"?"Inativo":"Ativo");
			}
	
		} catch (SQLException e){
            throw new RuntimeException("Erro. Problema ao encontrar Cliente " + e.getMessage());
        } finally {
            closeDB();
        }
        return client;
	}
	
}
