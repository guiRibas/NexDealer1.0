package Controller;

import java.util.List;

import Model.Type;
import Model.Vehicle;
import Model.Version;

import ModelDAO.TypeDAO;
import ModelDAO.VehicleDAO;
import ModelDAO.VersionDAO;

public class VehicleController implements PatternVehicleController<Vehicle>{

	VehicleDAO vehicleDAO = new VehicleDAO(this);
	VersionDAO versionDAO = new VersionDAO();
	TypeDAO typeDAO = new TypeDAO();
	
	@Override
	public int insert(Vehicle vehicle, int idVersion, int idType) {
		return vehicleDAO.create(vehicle, idVersion, idType);
	}

	@Override
	public int alter(Vehicle vehicle) {
		return vehicleDAO.update(vehicle);
	}

	@Override
	public int remove(int id) {
		return vehicleDAO.delete(id);
	}
	
	public Vehicle getNewVehicle(){
		return new Vehicle();
	}
	
	public List<Vehicle> filterByPlate(String plate){
		return vehicleDAO.filterByPlate(plate); 
	}
	
	public Vehicle findById(int id){
		return vehicleDAO.findById(id);
	}
	
	//------INSTANCIANDO NOVAS CLASSES------
	public Version getNewVersion(){
		return new Version();
	}
	
	public Type getNewType(){
		return new Type();
	}
	
	//------BUSCANDO OBJETOS NO BANCO (VERSÃO)------	
	public Version findVersionById(int id){
		return versionDAO.findById(id);
	}
	
	public int findVersionByName(String name){
		return versionDAO.findByName(name);
	}
	
	public List<String> findAllVersions(int id){
		return versionDAO.findAll(id);
	}
	
	public Type findTypeById(int id){
		return typeDAO.findById(id);
	}
	
	public int findTypeByName(String name){
		return typeDAO.findByName(name);
	}
	
	public List<String> findAllTypes(){
		return typeDAO.findAll();
	}
	
}
