package Controller;

import Model.Contact;
import ModelDAO.ContactDAO;

public class ContactController implements PatternSimpleController<Contact>{

	ContactDAO contactDAO = new ContactDAO();
	
	@Override
	public int insert(Contact contact) {
		return contactDAO.create(contact);
	}
	
	public Contact getNewContact(){
		return new Contact();
	}
	
	public Contact findById(int id){
		return contactDAO.findById(id);
	}
	
}
