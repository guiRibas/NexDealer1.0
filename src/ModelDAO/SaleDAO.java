package ModelDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Controller.ClientController;
import Controller.VehicleController;
import Model.Client;
import Model.Sale;
import Model.Type;
import Model.Vehicle;

public class SaleDAO extends MySqlConnection implements PatternSaleDAO{

	ClientController clientController = new ClientController();
	VehicleController vehicleController = new VehicleController();
	
	@Override
	public int create(int idClient, Vehicle vehicle, int idEmployee, String total, String pdfWay) {
		int result = 0;
		openDB();
		try {
			String sql = "INSERT INTO sale (id_client, id_vehicle, id_employee, id_type, total, pdf_way) VALUES (?,?,?,?,?,?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, idClient);
			pStmt.setInt(2, vehicle.getId());
			pStmt.setInt(3, idEmployee);
			pStmt.setInt(4, vehicle.getType().getId());
			pStmt.setString(5, total);
			pStmt.setString(6, pdfWay);
			
			result = pStmt.executeUpdate();
			
			return result;		
		} catch (SQLException sqlE) {		
			System.out.println(sqlE.getMessage());
		} finally {
			closeDB();
		}
		return result;
	}	
	
	public List<Sale> findByDate(String date){
		List<Sale> sales = new ArrayList<>();
		openDB();
		try {
			String sql = "SELECT id, id_client, id_vehicle, id_type, total, annotation, pdf_way FROM sale WHERE date LIKE ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, "%"+date+"%");
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {		
				Sale sale = new Sale();
				sale.setId(rs.getInt("id"));
				Client client = clientController.findById(rs.getInt("id_client"));
				Vehicle vehicle = vehicleController.findById(rs.getInt("id_vehicle"));
				Type type = vehicleController.findTypeById(rs.getInt("id_type"));
				sale.setClient(client);
				sale.setVechicle(vehicle);
				sale.setType(type);
				sale.setTotal(rs.getString("total"));
				sale.setAnnotation(rs.getString("annotation"));
				sale.setPdfWay(rs.getString("pdf_way"));
				
				sales.add(sale);
			}
	
		} catch (SQLException e){
            throw new RuntimeException("Erro. Problema ao mostrar Versão" + e.getMessage());
        } finally {
            closeDB();
        }
        return sales;
	}
	
}
