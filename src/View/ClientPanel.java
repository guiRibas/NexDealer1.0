package View;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import Controller.ClientController;
import Controller.ContactController;
import Model.City;
import Model.Client;
import Model.ConnectionCEP;
import Model.Contact;
import Model.TableClient;
import net.miginfocom.swing.MigLayout;

public class ClientPanel extends JPanel implements VisualWindow{
	
	private static final long serialVersionUID = 1L;
	private ConnectionCEP cep = new ConnectionCEP();
	private JTable jTableClient;
	private JScrollPane scrollPane;	
	private List<Client> clientList;
	private JPanel panelSearch, panelTable, panelData, panelPersonalAddress, panelButtons;
	private JLabel nameLabel = new JLabel("Nome *");
	private JLabel cpfLabel = new JLabel("CPF *");
	private JLabel rgLabel = new JLabel("RG *");
	private JLabel genderLabel = new JLabel("Sexo");	
	private JRadioButton rdBtnMale = new JRadioButton("Masculino");
	private JRadioButton rdBtnFemale = new JRadioButton("Feminino");
	private ButtonGroup btnGenderGroup = new ButtonGroup();
	private JLabel birthDateLabel = new JLabel("Data Nasc. *");
	private JLabel civilStatusLabel = new JLabel("Estado Civil *");
	private JLabel postalCodeLabel = new JLabel("CEP");
	private JLabel addressLabel = new JLabel("Endereço *");
	private JLabel numberOfHouseLabel = new JLabel("Número");
	private JLabel complementLabel = new JLabel("Complemento *");
	private JLabel neighborhoodLabel = new JLabel("Bairro");
	private JLabel cityLabel = new JLabel("Cidade");
	private JLabel stateLabel = new JLabel("UF");
	private JLabel dddLabel = new JLabel("DDD *");
	private JLabel telephoneLabel = new JLabel("Telefone *");
	private JLabel emailLabel = new JLabel("E-mail");
	private JTextField searchNameField = new JTextField(60);
	private JTextField nameField = new JTextField(60);
	private JFormattedTextField cpfField = new JFormattedTextField();
	private JFormattedTextField rgField = new JFormattedTextField();
	private JFormattedTextField birthDateField = new JFormattedTextField();
	private JTextField civilStatusField = new JTextField(10);
	private JTextField postalCodeField = new JTextField(10);
	private JTextField addressField = new JTextField(60);
	private JTextField numberOfHouseField = new JTextField(4);
	private JTextField complementField = new JTextField(20);
	private JTextField neighborhoodField = new JTextField(20);
	private JTextField dddField = new JTextField(4);
	private JTextField telephoneField = new JTextField(10);
	private JTextField emailField = new JTextField(28);
	private JComboBox states = new JComboBox<>();
	private JComboBox cities = new JComboBox<>();
	private JButton btnFind, btnRefresh, btnSave, btnDelete, btnCancel, btnNew, btnUpdate;
	private String statusMessage;
	//VARIAVEL DE INSTANCIA
	private Integer idClient;
	private Integer idContact;
	
	public ClientPanel() throws HeadlessException {
		setupLayout();
		setupComponents();
		setupEvents();
	}
	
	@Override
	public void setupLayout() {
		setLayout(null);
		setVisible(true);
	}

	@Override
	public void setupComponents() {
		//------ATRIBUINDO AS MASCARAS AOS CAMPOS--------
		try {
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");   
            MaskFormatter rgMask = new MaskFormatter("##.###.###-#");
            MaskFormatter birthDateMask = new MaskFormatter("##/##/####");
            cpfField = new javax.swing.JFormattedTextField(cpfMask);
            cpfField.setColumns(10);
            rgField = new javax.swing.JFormattedTextField(rgMask);
            rgField.setColumns(10);
            birthDateField = new javax.swing.JFormattedTextField(birthDateMask);
            birthDateField.setColumns(8);
		}
		catch (Exception error_mask) {
			JOptionPane.showMessageDialog(null,"Erro: "+error_mask);
		}
		
		//---------CONFIGURANDO LAYOUT----------
		
		panelSearch = new JPanel(new MigLayout());
		panelSearch.setBorder(BorderFactory.createTitledBorder("Filtro por Nome"));
		panelSearch.setBounds(5, 0, 490, 65);
		
		ImageIcon imgFind = new ImageIcon("/home/cabrito/workspace/NexDealer/lib/img/find.png");
		btnFind = new JButton(imgFind);
		btnFind.setBorderPainted(false);   
		
		ImageIcon imgRefresh = new ImageIcon("/home/cabrito/workspace/NexDealer/lib/img/refresh.png");
		btnRefresh = new JButton("Atualizar");
		btnRefresh.setIcon(imgRefresh);
		btnRefresh.setBorderPainted(false);    
		
		panelSearch.add(searchNameField);
		panelSearch.add(btnFind);
		
		//-------------------------CONSTRUINDO PANEL DO JTABLE
		panelTable = new JPanel(new MigLayout());
		panelTable.setBorder(BorderFactory.createTitledBorder("Lista de Clientes"));
		panelTable.setBounds(500, 0, 470, 450);
		
		jTableClient = new JTable();
		scrollPane = new JScrollPane(jTableClient);
		panelTable.add(scrollPane);
		
		//------------------------CONSTRUINDO PANEL DE DADOS
		panelData = new JPanel(new MigLayout());
		panelData.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));
		panelData.setBounds(5, 66, 490, 490);
		
		panelPersonalAddress = new JPanel(new MigLayout());
		panelPersonalAddress.setBorder(BorderFactory.createTitledBorder("Dados de Localização"));
		panelPersonalAddress.setBounds(10, 330, 480, 220);
		
		panelData.add(nameLabel);
		panelData.add(nameField, "wrap");
		panelData.add(cpfLabel);
		panelData.add(cpfField);
		panelData.add(rgLabel, "cell 0 2");
		panelData.add(rgField);
		rdBtnMale.setSelected(true);
		panelData.add(genderLabel, "cell 0 3");
		panelData.add(rdBtnMale);
		panelData.add(rdBtnFemale, "cell 1 5");
		btnGenderGroup.add(rdBtnMale);
		btnGenderGroup.add(rdBtnFemale);
		panelData.add(birthDateLabel, "cell 0 6");
		panelData.add(birthDateField);
		panelData.add(civilStatusLabel, "cell 0 7");
		panelData.add(civilStatusField);
		panelData.add(dddLabel, "cell 0 8");
		panelData.add(dddField);
		panelData.add(telephoneLabel, "cell 1 8");
		panelData.add(telephoneField, "cell 1 8");
		panelData.add(emailLabel, "cell 0 9");
		panelData.add(emailField);
		
		panelPersonalAddress.add(postalCodeLabel, "cell 0 1");
		panelPersonalAddress.add(postalCodeField);
		panelPersonalAddress.add(btnRefresh);
		panelPersonalAddress.add(addressLabel, "cell 0 2");
		panelPersonalAddress.add(addressField);
		panelPersonalAddress.add(numberOfHouseLabel, "cell 0 3");
		panelPersonalAddress.add(numberOfHouseField);
		panelPersonalAddress.add(complementLabel, "cell 0 4");
		panelPersonalAddress.add(complementField);
		panelPersonalAddress.add(neighborhoodLabel, "cell 0 5");
		panelPersonalAddress.add(neighborhoodField);
		panelPersonalAddress.add(stateLabel, "cell 0 6");
		panelPersonalAddress.add(states);
		panelPersonalAddress.add(cityLabel, "cell 0 7");
		panelPersonalAddress.add(cities);
		
		//------------------------CONSTRUINDO PANEL DE BOTOES
		panelButtons = new JPanel(new MigLayout());
		panelButtons.setBorder(BorderFactory.createTitledBorder("Ações"));
		panelButtons.setBounds(500, 451, 470, 105);
		
		ImageIcon imgSave = new ImageIcon("/home/cabrito/workspace/NexDealer/lib/img/save.png");
		btnSave = new JButton();
		btnSave.setIcon(imgSave);
		btnSave.setText("Salvar");
		ImageIcon imgCancel = new ImageIcon("/home/cabrito/workspace/NexDealer/lib/img/cancel.png");
		btnCancel = new JButton();
		btnCancel.setIcon(imgCancel);
		btnCancel.setText("Cancelar");
		ImageIcon imgNew = new ImageIcon("/home/cabrito/workspace/NexDealer/lib/img/new.png");
		btnNew = new JButton();
		btnNew.setIcon(imgNew);
		btnNew.setText("Novo");
		ImageIcon imgDelete = new ImageIcon("/home/cabrito/workspace/NexDealer/lib/img/trash.png");
		btnDelete = new JButton();
		btnDelete.setIcon(imgDelete);
		btnDelete.setText("Excluir");
		ImageIcon imgUpdate = new ImageIcon("/home/cabrito/workspace/NexDealer/lib/img/edit.png");
		btnUpdate = new JButton();
		btnUpdate.setIcon(imgUpdate);
		btnUpdate.setText("Editar / Visualizar");
		
		panelButtons.add(btnSave, "cell 1 2");
		panelButtons.add(btnCancel, "cell 2 2");
		panelButtons.add(btnNew, "cell 3 2");
		panelButtons.add(btnDelete, "cell 1 3");
		panelButtons.add(btnUpdate, "cell 2 3");
		
		btnCancel.setEnabled(false);
		btnSave.setEnabled(false);
		
		enableFields(false);
		
		add(panelSearch);
		add(panelTable);
		add(panelPersonalAddress);
		add(panelData);
		add(panelButtons);
		setMinimumSize(new Dimension(980, 600));
	}
	
	@Override
	public void setupEvents() {
		btnSave.addActionListener(new ActionListener(){	
			@Override
			public void actionPerformed(ActionEvent e) {
				toSave();
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toCancel();
			}
		});
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toNew();
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toUpdate();
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toDelete();				
			}
		});
		btnFind.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				toFilterByName();
			}
		});
		btnRefresh.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				toRefresh();
			}
		});
	}
	
	private void toSave(){
		ClientController clientController = new ClientController();
		ContactController contactController = new ContactController();
		Client client = clientController.getNewClient();
		Contact contact = clientController.getNewContact();
		City city = clientController.getNewCity();
		try {
			int result;
			if (nameField.getText().length() > 0 && cpfField.getText().length() > 0 && rgField.getText().length() > 0
					&& birthDateField.getText().length() > 0 && civilStatusField.getText().length() > 0 
					&& addressField.getText().length() > 0 && complementField.getText().length() > 0
					&& dddField.getText().length() > 0 && telephoneField.getText().length() > 0) {
				
				city.setId(city.findByName(cities.getSelectedItem().toString()));
				contact.setCity(city);
				contact.setAddress(addressField.getText());
				contact.setComplement(complementField.getText());
				contact.setNeighborhood(neighborhoodField.getText());
				contact.setEmail(emailField.getText());
				contact.setNumberOfHouse(Integer.parseInt(numberOfHouseField.getText()));
				contact.setDdd(Integer.parseInt(dddField.getText()));
				contact.setTelephone(Integer.parseInt(telephoneField.getText()));
				contact.setPostalCode(Integer.parseInt(postalCodeField.getText()));
				client.setContact(contact);
				client.setName(nameField.getText());
				client.setCpf(cpfField.getText());
				client.setRg(rgField.getText());
				client.setBirthDate(birthDateField.getText());
				String gender = rdBtnMale.isSelected() ? "M" : "F";
				client.setGender(gender);
				client.setCivilStatus(civilStatusField.getText());
			}else{
				JOptionPane.showMessageDialog(this, "Campos Obrigatórios estão vazios!");
				return;
			}
			
			if (idClient == null) {
				statusMessage = "salvo";
				idContact = contactController.insert(contact);
				result = clientController.insert(client, idContact);
			}else{
				client.setId(idClient);
				contact.setId(idContact);
				result = clientController.alter(client);
				statusMessage = "alterado";
				idClient = null;
				idContact = null;
			}
			
			if (result == 1 || result == 2) {
				JOptionPane.showMessageDialog(this, "Cliente "+statusMessage+" com Sucesso!");
				enableFields(false);
				clearAllFields();
				btnCancel.setEnabled(false);
				btnSave.setEnabled(false);
				btnNew.setEnabled(true);
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
			}else{
				JOptionPane.showMessageDialog(this, "Erro. Problema ao salvar o Cliente");
			}
			
		} catch (Exception e2) {
			System.out.println(e2);
		}
	}
	
	private void toCancel(){
		clearAllFields();
		enableFields(false);
		btnCancel.setEnabled(false);
		btnSave.setEnabled(false);
		btnNew.setEnabled(true);
		btnUpdate.setEnabled(true);
		btnDelete.setEnabled(true);
	}
	
	private void toNew(){
		enableFields(true);
		btnNew.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		btnCancel.setEnabled(true);
		btnSave.setEnabled(true);
	}
	
	private void toUpdate(){
		int rowIndex = jTableClient.getSelectedRow();
		
		//VERIFICANDO SE FOI SELECIONADO ALGUMA LINHA DA TABELA DE CLIENTE
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Selecione o cliente desejado para altera-lo!");
			return;
		}
		
		//OBTENDO VALORES DO CLIENTE SELECIONADO
		Client client = new TableClient(clientList).get(rowIndex);
		
		//SETANDO VALORES NAS VARIAVES DE INSTANCIAS
		idContact = client.getContact().getId();
		idClient = client.getId();
		
		//SETANDO VALORES NO CAMPOS PARA POSSIVEIS EDIÇÕES
		nameField.setText(client.getName());
		cpfField.setText(client.getCpf());
		rgField.setText(client.getRg());
		birthDateField.setText(client.getBirthDate());
		String gender = client.getGender();
		switch (gender) {
		case "M":
			rdBtnMale.setSelected(true);
			break;
		case "F":
			rdBtnFemale.setSelected(true);
		default:
			break;
		}
		civilStatusField.setText(client.getCivilStatus());
		postalCodeField.setText(String.valueOf(client.getContact().getPostalCode()));
		addressField.setText(client.getContact().getAddress());
		numberOfHouseField.setText(String.valueOf(client.getContact().getNumberOfHouse()));
		complementField.setText(client.getContact().getComplement());
		neighborhoodField.setText(client.getContact().getNeighborhood());
		dddField.setText(String.valueOf(client.getContact().getDdd()));
		telephoneField.setText(String.valueOf(client.getContact().getTelephone()));
		emailField.setText(client.getContact().getEmail());
		states.removeAllItems();
		cities.removeAllItems();
		cities.addItem(client.getContact().getCity().getName());
		states.addItem(client.getContact().getCity().getState().getInitials());
		
		enableFields(true);
		
		btnUpdate.setEnabled(false);
		btnNew.setEnabled(false);
		btnSave.setEnabled(true);
		btnCancel.setEnabled(true);
	}
	
	private void toFilterByName(){
		ClientController clientController = new ClientController();
		clientList = clientController.filterByName(searchNameField.getText());
		jTableClient.setModel(new TableClient(clientList));
	}

	private void toRefresh(){
		try {
			addressField.setText(cep.getEndereco(postalCodeField.getText()));
			neighborhoodField.setText(cep.getBairro(postalCodeField.getText()));
			states.removeAllItems();
			cities.removeAllItems();
			states.addItem(cep.getUF(postalCodeField.getText()));
			cities.addItem(cep.getCidade(postalCodeField.getText()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void toDelete(){
		ClientController clientController = new ClientController();
		int rowIndex = jTableClient.getSelectedRow();
		
		//VERIFICANDO SE FOI SELECIONADO ALGUMA LINHA DA TABELA DE CLIENTE
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Selecione o cliente desejado para exclui-lo!");
			return;
		}
		
		Client client = new TableClient(clientList).get(rowIndex);
		
		int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
		
		if (confirm != 0) {
			return;
		}
		
		int result = clientController.remove(client.getId());

		if (result == 1) {
			JOptionPane.showMessageDialog(null, "Cliente removido com Sucesso!");
			//refreshTable();
			clearAllFields();
			enableFields(false);
		}else{
			JOptionPane.showMessageDialog(null, "Erro. Problema ao remover o Cliente");
		}
	}
	
	private void clearAllFields(){
		nameField.setText("");
		cpfField.setText("");
		rgField.setText("");
		birthDateField.setText("");
		civilStatusField.setText("");
		postalCodeField.setText("");
		addressField.setText("");
		numberOfHouseField.setText("");
		complementField.setText("");
		neighborhoodField.setText("");
		dddField.setText("");
		telephoneField.setText("");
		emailField.setText("");
		states.removeAllItems();
		cities.removeAllItems();
		jTableClient.setModel(new DefaultTableModel());
	}
	
	private void enableFields(boolean status){
		nameField.setEnabled(status);
		cpfField.setEnabled(status);
		rgField.setEnabled(status);
		birthDateField.setEnabled(status);
		civilStatusField.setEnabled(status);
		postalCodeField.setEnabled(status);
		addressField.setEnabled(status);
		rdBtnMale.setEnabled(status);
		rdBtnFemale.setEnabled(status);
		numberOfHouseField.setEnabled(status);
		complementField.setEnabled(status);
		neighborhoodField.setEnabled(status);
		dddField.setEnabled(status);
		telephoneField.setEnabled(status);
		emailField.setEnabled(status);
		states.setEnabled(status);
		cities.setEnabled(status);
	}
	
}
