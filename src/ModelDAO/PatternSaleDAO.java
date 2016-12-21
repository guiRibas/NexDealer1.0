package ModelDAO;

import Model.Vehicle;

public interface PatternSaleDAO {

	int create(int idClient, Vehicle vehicle, int idEmployee, String total, String pdfWay);
	
}
