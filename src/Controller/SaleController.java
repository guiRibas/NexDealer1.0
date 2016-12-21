package Controller;

import java.util.List;

import Model.Sale;
import Model.Vehicle;
import ModelDAO.SaleDAO;

public class SaleController implements PatternSaleController{

	SaleDAO saleDao = new SaleDAO();
	
	@Override
	public int insert(int idClient, Vehicle vehicle, int idEmployee, String total, String pdfWay) {
		return saleDao.create(idClient, vehicle, idEmployee, total, pdfWay);
	}
	
	public List<Sale> findByDate(String date){
		return saleDao.findByDate(date);
	}
	
}
