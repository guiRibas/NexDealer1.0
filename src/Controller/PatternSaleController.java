package Controller;

import Model.Vehicle;

public interface PatternSaleController {

	public int insert(int idClient, Vehicle vehicle, int idEmployee, String total, String pdfWay);
	
}
