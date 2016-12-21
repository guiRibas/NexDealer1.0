package ModelDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Controller.VehicleController;
import Model.Client;
import Model.Type;
import Model.Vehicle;
import Model.Version;

public class VehicleDAO extends MySqlConnection implements PatternVehicleDAO<Vehicle>{

	VehicleController vehicleController;
	
	public VehicleDAO(VehicleController vehicleController) {
		this.vehicleController = vehicleController;
	}
	
	@Override
	public int create(Vehicle vehicle, int idVersion, int idType) {
		int result = 0;
		openDB();
		try {
			String sql = "INSERT INTO vehicle (id_version, id_type, year, plate, renavam, fuel, color, frame, status) VALUES (?,?,?,?,?,?,?,?,?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, idVersion);
			pStmt.setInt(2, idType);
			pStmt.setString(3, vehicle.getYear());
			pStmt.setString(4, vehicle.getPlate());
			pStmt.setInt(5, vehicle.getRenavam());
			pStmt.setString(6, vehicle.getFuel());
			pStmt.setString(7, vehicle.getColor());
			pStmt.setString(8, vehicle.getFrame());
			pStmt.setString(9, "A");
			
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
	public int update(Vehicle vehicle) {
		int result = 0;
		openDB();
		try {
			String sql = "UPDATE vehicle SET id_version = ?, id_type = ?, year = ?, plate = ?, renavam = ?, fuel = ?, color = ?, frame = ?, status = ? WHERE id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, vehicle.getVersion().getId());
			pStmt.setInt(2, vehicle.getType().getId());
			pStmt.setString(3, vehicle.getYear());
			pStmt.setString(4, vehicle.getPlate());
			pStmt.setInt(5, vehicle.getRenavam());
			pStmt.setString(6, vehicle.getFuel());
			pStmt.setString(7, vehicle.getColor());
			pStmt.setString(8, vehicle.getFrame());
			pStmt.setString(9, "A");
			pStmt.setInt(10, vehicle.getId());
			
			result = pStmt.executeUpdate();
			
		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} finally {
			closeDB();
		}
		return result;
	}

	@Override
	public int delete(Integer id) {
		int result = 0;
		openDB();
		try {
			String sql = "UPDATE vehicle SET status = ? WHERE id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, "S");
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
	
	public List<Vehicle> filterByPlate(String plate){
		String status;
		List<Vehicle> vehicles = new ArrayList<>();
		openDB();
		try {
			String sql = "SELECT * FROM vehicle WHERE plate LIKE ? AND status = ? ORDER BY created_at;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, "%"+plate+"%");
			pStmt.setString(2, "A");
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				Vehicle vehicle = new Vehicle();
				Version version = vehicleController.findVersionById(rs.getInt("id_version"));
				Type type = vehicleController.findTypeById(rs.getInt("id_type"));
				vehicle.setId(rs.getInt("id"));
				vehicle.setVersion(version);
				vehicle.setType(type);
				vehicle.setYear(rs.getString("year"));
				vehicle.setPlate(rs.getString("plate"));
				vehicle.setRenavam(rs.getInt("renavam"));
				vehicle.setFuel(rs.getString("fuel"));
				vehicle.setColor(rs.getString("color"));
				vehicle.setFrame(rs.getString("frame"));
				status = rs.getString("status");
				vehicle.setStatus(status=="A"?"Inativo":"Ativo");
				
				vehicles.add(vehicle);		
			}
		} catch (SQLException e){
            throw new RuntimeException("Erro. Problema ao mostrar Veículos " + e.getMessage());
        }finally {
            closeDB();
        }
        return vehicles;
	}
	
	public Vehicle findById(int id){
		Vehicle vehicle = null;
		String status = "";
		openDB();
		try {
			String sql = "SELECT * FROM vehicle WHERE id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, id);
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {		
				vehicle = new Vehicle();
				Version version = vehicleController.findVersionById(rs.getInt("id_version"));
				Type type = vehicleController.findTypeById(rs.getInt("id_type"));
				vehicle.setId(rs.getInt("id"));
				vehicle.setVersion(version);
				vehicle.setType(type);
				vehicle.setYear(rs.getString("year"));
				vehicle.setPlate(rs.getString("plate"));
				vehicle.setRenavam(rs.getInt("renavam"));
				vehicle.setFuel(rs.getString("fuel"));
				vehicle.setColor(rs.getString("color"));
				vehicle.setFrame(rs.getString("frame"));
				status = rs.getString("status");
				vehicle.setStatus(status=="A"?"Inativo":"Ativo");
			}
	
		} catch (SQLException e){
            throw new RuntimeException("Erro. Problema ao encontrar Veículo " + e.getMessage());
        } finally {
            closeDB();
        }
        return vehicle;
	}

}
