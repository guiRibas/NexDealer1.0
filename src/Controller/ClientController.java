package Controller;

import java.util.List;

import Model.City;
import Model.Client;
import Model.Contact;
import ModelDAO.ClientDAO;

public class ClientController implements PatternClientController<Client>{
	
	private ClientDAO clientDao = new ClientDAO();
	private ContactController contactController = new ContactController();
	private CityController cityController = new CityController();
	
	@Override
	public int insert(Client client, int idContact) {
		return clientDao.create(client, idContact);
	}

	@Override
	public int alter(Client client) {
		return clientDao.update(client);
	}

	@Override
	public int remove(int id) {
		return clientDao.delete(id);
	}
	
	public Client getNewClient(){
		return new Client();
	}
	
	public City getNewCity(){
		return cityController.getNewCity();
	}
	
	public Contact getNewContact(){
		return contactController.getNewContact();
	}
	
	public List<Client> filterByName(String name){
		return clientDao.filterByName(name); 
	}
	
	public int findByName(String name){
		return clientDao.findByName(name);
	}
	
	public Client findById(int id){
		return clientDao.findById(id);
	}
	
}
