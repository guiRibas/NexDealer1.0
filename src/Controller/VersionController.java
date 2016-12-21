package Controller;

import java.util.List;

import Model.Brand;
import Model.Model;
import ModelDAO.BrandDAO;
import ModelDAO.ModelDAO;

public class VersionController {

	ModelDAO modelDAO = new ModelDAO(this);
	BrandDAO brandDAO = new BrandDAO();
	
	public Model findModelById(int id){
		return modelDAO.findById(id);
	}
	
	public Brand findBrandById(int id){
		return brandDAO.findById(id);
	}
		
	public int findModelByName(String name){
		return modelDAO.findByName(name);
	}
	
	public int findBrandByName(String name){
		return brandDAO.findByName(name);
	}
	
	public List<String> findAllBrands(){
		return brandDAO.findAll();
	}
	
	public List<String> findAllModels(int id){
		return modelDAO.findAll(id);
	}
	
}
