package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import Controller.VehicleController;
import Controller.VersionController;
import Model.TableVehicle;
import Model.Type;
import Model.Vehicle;
import Model.Version;
import net.miginfocom.swing.MigLayout;

public class VehiclePanel extends JPanel implements VisualWindow{
	
	private static final long serialVersionUID = 1L;
	private JTable jTableVehicle;
	private JScrollPane scrollPane;	
	private List<Vehicle> vehicleList;
	private JPanel panelSearch, panelTable, panelData, panelButtons;
	private JLabel brandLabel = new JLabel("Marca *");
	private JLabel modelLabel = new JLabel("Modelo *");
	private JLabel versionLabel = new JLabel("Versão *");	
	private JLabel typeLabel = new JLabel("Tipo *");	
	private JRadioButton rdBtnBrand = new JRadioButton("Marca");
	private JRadioButton rdBtnPlate = new JRadioButton("Placa");
	private ButtonGroup btnChoiceGroup = new ButtonGroup();
	private JLabel yearLabel = new JLabel("Ano *");
	private JLabel renavamLabel = new JLabel("Renavam *");
	private JLabel plateLabel = new JLabel("Placa *");
	private JLabel fuelLabel = new JLabel("Combustível *");
	private JLabel colorLabel = new JLabel("Cor *");
	private JLabel frameLabel = new JLabel("Chassi *");
	private JTextField searchField = new JTextField(60);
	private JComboBox comboBrand = new JComboBox();
	private JComboBox comboModel = new JComboBox();
	private JComboBox comboVersion = new JComboBox();
	private JComboBox comboType = new JComboBox();
	private JTextField yearField = new JTextField(6);
	private JTextField renavamField = new JTextField(11);
	private JTextField plateField = new JTextField(6);	
	private JRadioButton rdBtnGas = new JRadioButton("Gasolina");
	private JRadioButton rdBtnAlc = new JRadioButton("Álcool");
	private JRadioButton rdBtnFlex = new JRadioButton("Flex");
	private JRadioButton rdBtnDie = new JRadioButton("Diesel");
	private ButtonGroup btnFuelGroup = new ButtonGroup();
	private JTextField colorField = new JTextField(6);
	private JTextField frameField = new JTextField(20);
	private List<String> selectionBrands, selectionModels, selectionVersions, selectionTypes;
	private JButton btnFind, btnSave, btnDelete, btnCancel, btnNew, btnUpdate;
	private String statusMessage;
	VersionController versionController = new VersionController();
	VehicleController vehicleController = new VehicleController();
	//VARIAVEL DE INSTANCIA
	private Integer idVehicle, idVersion, idType;
	
	public VehiclePanel(){
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
            MaskFormatter plateMask = new MaskFormatter("???-####");   
            MaskFormatter yearMask = new MaskFormatter("####");    
            plateField = new javax.swing.JFormattedTextField(plateMask);
            plateField.setColumns(6);
            yearField = new javax.swing.JFormattedTextField(yearMask);
            yearField.setColumns(6);
		}
		catch (Exception error_mask) {
			JOptionPane.showMessageDialog(null,"Erro: "+error_mask);
		}
		//---------CONFIGURANDO LAYOUT----------
		
		panelSearch = new JPanel(new MigLayout());
		panelSearch.setBorder(BorderFactory.createTitledBorder("Filtro"));
		rdBtnPlate.setSelected(true);
		panelSearch.add(rdBtnBrand);
		panelSearch.add(rdBtnPlate);
		btnChoiceGroup.add(rdBtnBrand);
		btnChoiceGroup.add(rdBtnPlate);
		panelSearch.setBounds(5, 0, 490, 65);
		
		ImageIcon imgFind = new ImageIcon("/home/cabrito/workspace/NexDealer/lib/img/find.png");
		btnFind = new JButton(imgFind);
		btnFind.setBorderPainted(false);     
		
		panelSearch.add(searchField);
		panelSearch.add(btnFind);
		
		//-------------------------CONSTRUINDO PANEL DO JTABLE
		panelTable = new JPanel(new MigLayout());
		panelTable.setBorder(BorderFactory.createTitledBorder("Lista de Veículos"));
		panelTable.setBounds(500, 0, 470, 450);
		
		jTableVehicle = new JTable();
		scrollPane = new JScrollPane(jTableVehicle);
		panelTable.add(scrollPane);
		
		//------------------------CONSTRUINDO PANEL DE DADOS
		panelData = new JPanel(new MigLayout());
		panelData.setBorder(BorderFactory.createTitledBorder("Dados do Veículo"));
		panelData.setBounds(5, 66, 490, 490);
		
		panelData.add(brandLabel);
		panelData.add(comboBrand, "wrap");
		panelData.add(modelLabel);
		panelData.add(comboModel);
		
		panelData.add(versionLabel, "cell 0 2");
		panelData.add(comboVersion);
		panelData.add(typeLabel, "cell 0 3");
		panelData.add(comboType);
		panelData.add(yearLabel, "cell 0 7");
		panelData.add(yearField);
		panelData.add(renavamLabel, "cell 0 8");
		panelData.add(renavamField);
		panelData.add(plateLabel, "cell 0 9");
		panelData.add(plateField);
		rdBtnGas.setSelected(true);
		panelData.add(fuelLabel, "cell 0 10");
		panelData.add(rdBtnGas);
		panelData.add(rdBtnAlc, "cell 1 11");
		panelData.add(rdBtnFlex, "cell 1 12");
		panelData.add(rdBtnDie, "cell 1 13");
		btnFuelGroup.add(rdBtnGas);
		btnFuelGroup.add(rdBtnAlc);
		btnFuelGroup.add(rdBtnFlex);
		btnFuelGroup.add(rdBtnDie);
		panelData.add(colorLabel, "cell 0 14");
		panelData.add(colorField);
		panelData.add(frameLabel, "cell 0 15");
		panelData.add(frameField);
		
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
		panelButtons.add(btnDelete, "cell 3 3");
		panelButtons.add(btnUpdate, "cell 2 3");
		
		btnCancel.setEnabled(false);
		btnSave.setEnabled(false);
		
		enableFields(false);
		
		add(panelSearch);
		add(panelTable);
		add(panelData);
		add(panelButtons);
		setMinimumSize(new Dimension(980, 600));
	}
	
	@Override
	public void setupEvents() {
		btnFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toFilterByPlate();
			}
		});
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
		comboBrand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fillComboModel();
			}
		});
		comboModel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fillComboVersion();				
			}
		});
	}
	
	private void toSave(){
		VehicleController vehicleController = new VehicleController();
		Vehicle vehicle = vehicleController.getNewVehicle();
		Version version = vehicleController.getNewVersion();
		Type type = vehicleController.getNewType();
		try {
			int result;
			if (    comboBrand.getSelectedItem() != null && comboModel.getSelectedItem() != null && comboVersion.getSelectedItem() != null &&
					comboType.getSelectedItem() != null && frameField.getText().length() > 0 &&
					yearField.getText().length() > 0 && renavamField.getText().length() > 0 ) {
		
				version.setDescription(comboVersion.getSelectedItem().toString());
				vehicle.setVersion(version);
				vehicle.setType(type);
				vehicle.setYear(yearField.getText());
				vehicle.setRenavam(Integer.parseInt(renavamField.getText()));
				vehicle.setPlate(plateField.getText());
				String fuelOption = rdBtnGas.isSelected() ? "Gasolina" : rdBtnAlc.isSelected() ? "Álcool" :
					rdBtnFlex.isSelected() ? "Flex" : rdBtnDie.isSelected() ? "Diesel" : "";
				vehicle.setFuel(fuelOption);
				vehicle.setColor(colorField.getText());
				vehicle.setFrame(frameField.getText());
			}else{
				JOptionPane.showMessageDialog(this, "Campos Obrigatórios estão vazios!");
				return;
			}
			
			if (idVehicle == null) {
				statusMessage = "salvo";
				idVersion = vehicleController.findVersionByName(comboVersion.getSelectedItem().toString());
				idType = vehicleController.findTypeByName(comboType.getSelectedItem().toString());
				result = vehicleController.insert(vehicle, idVersion, idType);
			}else{
				vehicle.setId(idVehicle);
				version.setId(vehicleController.findVersionByName(comboVersion.getSelectedItem().toString()));
				type.setId(vehicleController.findTypeByName(comboType.getSelectedItem().toString()));
				result = vehicleController.alter(vehicle);
				statusMessage = "alterado";
				idVehicle = null;
				idVersion = null;
				idType = null;
			}
			
			if (result == 1 || result == 3) {
				JOptionPane.showMessageDialog(this, "Veículo "+statusMessage+" com Sucesso!");
				enableFields(false);
				clearAllFields();
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				btnNew.setEnabled(true);
				btnUpdate.setEnabled(true);
			}else{
				JOptionPane.showMessageDialog(this, "Erro. Problema ao salvar o Veículo");
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
		fillComboBrand();
		comboModel.removeAllItems();
		comboVersion.removeAllItems();
		fillComboType();
		enableFields(true);
		btnNew.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		btnCancel.setEnabled(true);
		btnSave.setEnabled(true);
	}
	
	private void toUpdate(){		
		int rowIndex = jTableVehicle.getSelectedRow();
		
		//VERIFICANDO SE FOI SELECIONADO ALGUMA LINHA DA TABELA DE VE
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Selecione o veículo desejado para altera-lo!");
			return;
		}
		
		//OBTENDO VALORES DO VEÍCULO SELECIONADO
		Vehicle vehicle = new TableVehicle(vehicleList).get(rowIndex);		
		
		//SETANDO VALORES NAS VARIAVES DE INSTANCIAS
		idVehicle = vehicle.getId();
		
		//SETANDO VALORES NO CAMPOS PARA POSSIVEIS EDIÇÕES
		fillComboBrand();
		fillComboType();
		comboBrand.setSelectedItem(vehicle.getVersion().getModel().getBrand().getDescription());
		comboModel.setSelectedItem(vehicle.getVersion().getModel().getDescription());
		comboVersion.setSelectedItem(vehicle.getVersion().getDescription());
		comboType.setSelectedItem(vehicle.getType().getDescription());
		yearField.setText(vehicle.getYear());
		plateField.setText(String.valueOf(vehicle.getPlate()));
		renavamField.setText(String.valueOf(vehicle.getRenavam()));
		String fuel = vehicle.getFuel();
		switch (fuel) {
		case "Gasolina":
			rdBtnGas.setSelected(true);
			break;
		case "Álcool":
			rdBtnAlc.setSelected(true);
			break;
		case "Flex":
			rdBtnFlex.setSelected(true);
			break;
		case "Diesel":
			rdBtnDie.setSelected(true);
			break;
		default:
			break;
		}
		colorField.setText(vehicle.getColor());
		frameField.setText(String.valueOf(vehicle.getFrame()));
		
		enableFields(true);
		
		btnUpdate.setEnabled(false);
		btnNew.setEnabled(false);
		btnSave.setEnabled(true);
		btnCancel.setEnabled(true);
	}
	
	private void toFilterByPlate(){
		if (searchField.getText().length() > 0) {
			vehicleList = vehicleController.filterByPlate(searchField.getText());
			jTableVehicle.setModel(new TableVehicle(vehicleList));
		}
	}
	
	private void toDelete(){
		int rowIndex = jTableVehicle.getSelectedRow();
		
		//VERIFICANDO SE FOI SELECIONADO ALGUMA LINHA DA TABELA DE CLIENTE
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Selecione o veículo desejado para exclui-lo!");
			return;
		}
		
		Vehicle vehicle = new TableVehicle(vehicleList).get(rowIndex);
		String plate = vehicle.getPlate();
		
		int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
		
		if (confirm != 0) {
			return;
		}
		
		int result = vehicleController.remove(vehicle.getId());

		if (result == 1) {
			JOptionPane.showMessageDialog(null, "Veículo com placa(s) > "+plate+" < removido com Sucesso!");
			clearAllFields();
			enableFields(false);
			toFilterByPlate();
		}else{
			JOptionPane.showMessageDialog(null, "Erro. Problema ao remover o Veículo");
		}
	}
	
	private void clearAllFields(){
		comboBrand.removeAllItems();
		comboModel.removeAllItems();
		comboVersion.removeAllItems();
		comboType.removeAllItems();
		yearField.setText("");
		renavamField.setText("");
		plateField.setText("");
		colorField.setText("");
		frameField.setText("");
		jTableVehicle.setModel(new DefaultTableModel());
	}
	
	private void enableFields(boolean status){
		comboBrand.setEnabled(status);
		comboModel.setEnabled(status);
		comboVersion.setEnabled(status);
		comboType.setEnabled(status);
		yearField.setEnabled(status);
		renavamField.setEnabled(status);
		plateField.setEnabled(status);
		rdBtnGas.setEnabled(status);
		rdBtnAlc.setEnabled(status);
		rdBtnFlex.setEnabled(status);
		rdBtnDie.setEnabled(status);
		colorField.setEnabled(status);
		frameField.setEnabled(status);
	}

	public void fillComboBrand(){
		selectionBrands = versionController.findAllBrands();
		for (int i = 0; i < selectionBrands.size(); i++) {
			comboBrand.addItem(selectionBrands.get(i));
		}
	}
	
	public void fillComboModel(){
		if (comboBrand.getSelectedItem() != null) {
			int idBrand = versionController.findBrandByName(comboBrand.getSelectedItem().toString());
			comboModel.removeAllItems();
			selectionModels = versionController.findAllModels(idBrand);
			for (int i = 0; i < selectionModels.size(); i++) {
				comboModel.addItem(selectionModels.get(i));
			}
		}
	}
	
	public void fillComboVersion(){
		if (comboModel.getSelectedItem() != null) {
			comboVersion.removeAllItems();
			int idModel = versionController.findModelByName(comboModel.getSelectedItem().toString());
			selectionVersions = vehicleController.findAllVersions(idModel);
			for (int i = 0; i < selectionVersions.size(); i++) {
				comboVersion.addItem(selectionVersions.get(i));
			}
		}
	}
	
	public void fillComboType(){
		if (comboBrand.getSelectedItem() != null) {
			comboType.removeAllItems();
			selectionTypes = vehicleController.findAllTypes();
			for (int i = 0; i < selectionTypes.size(); i++) {
				comboType.addItem(selectionTypes.get(i));
			}
		}
	}
	
}

