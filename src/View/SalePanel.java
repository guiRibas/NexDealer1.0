package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.ClientController;
import Controller.SaleController;
import Controller.VehicleController;
import Model.Client;
import Model.MakePdf;
import Model.TableClient;
import Model.TableVehicle;
import Model.Vehicle;
import net.miginfocom.swing.MigLayout;

public class SalePanel extends JPanel implements VisualWindow{

	private static final long serialVersionUID = 1L;
	private JTable jTableVehicle, jTableClient;
	private JScrollPane scrollPaneClient, scrollPaneVehicle;
	private List<Vehicle> vehicleList;
	private List<Client> clientList;
	private JPanel panelSearchClient, panelTableClient, panelSearchVehicle, panelTableVehicle, panelButtons;
	private JTextField searchClientField = new JTextField(30);
	private JTextField searchVehicleField = new JTextField(30);
	private JButton btnFindClient, btnFindVehicle, btnMakePdf, btnSale, btnCancel, btnNew;
	private ClientController clientController = new ClientController();
	private VehicleController vehicleController = new VehicleController();
	private String pdfWay;
	
	public SalePanel() {
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
		ImageIcon imgFind = new ImageIcon("/home/cabrito/workspace/NexDealer/lib/img/find.png");
		
		panelSearchClient = new JPanel(new MigLayout());
		panelSearchClient.setBorder(BorderFactory.createTitledBorder("Buscar Cliente - Nome"));
		panelSearchClient.add(searchClientField);
		btnFindClient = new JButton(imgFind);
		btnFindClient.setBorderPainted(false);
		panelSearchClient.add(btnFindClient);
		panelSearchClient.setBounds(5, 0, 450, 65);
		
		panelTableClient = new JPanel(new MigLayout());
		panelTableClient.setBorder(BorderFactory.createTitledBorder("Resultado da busca por Cliente"));
		jTableClient = new JTable();
		scrollPaneClient = new JScrollPane(jTableClient);
		panelTableClient.add(scrollPaneClient);
		panelTableClient.setBounds(5, 70, 450, 440);
		
		panelSearchVehicle = new JPanel(new MigLayout());
		panelSearchVehicle.setBorder(BorderFactory.createTitledBorder("Buscar Veículo - Placa"));
		panelSearchVehicle.add(searchVehicleField);
		btnFindVehicle = new JButton(imgFind);
		btnFindVehicle.setBorderPainted(false);
		panelSearchVehicle.add(btnFindVehicle);
		panelSearchVehicle.setBounds(520, 0, 450, 65);
		
		panelTableVehicle = new JPanel(new MigLayout());
		panelTableVehicle.setBorder(BorderFactory.createTitledBorder("Resultado da busca por Veículo"));
		jTableVehicle = new JTable();
		scrollPaneVehicle = new JScrollPane(jTableVehicle);
		panelTableVehicle.add(scrollPaneVehicle);
		panelTableVehicle.setBounds(520, 70, 450, 440);
		
		panelButtons = new JPanel(new MigLayout());
		btnMakePdf = new JButton("Gerar Ficha / Omni");
		btnSale = new JButton("Finalizar Venda");
		btnCancel = new JButton("Cancelar");
		btnNew = new JButton("Nova Venda");
		panelButtons.add(btnNew);
		panelButtons.add(btnCancel);
		panelButtons.add(btnSale);
		panelButtons.add(btnMakePdf);
		panelButtons.setBounds(200, 515, 560, 40);
		
		add(panelSearchClient);
		add(panelTableClient);
		add(panelSearchVehicle);
		add(panelTableVehicle);
		add(panelButtons);
		
		enableFields(false);
		
		setMinimumSize(new Dimension(980, 600));
	}

	@Override
	public void setupEvents() {
		btnFindClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toFilterByClient();				
			}
		});
		btnFindVehicle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toFilterByVehicle();
			}
		});
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enableFields(true);
				btnNew.setEnabled(false);
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enableFields(false);
				clearAllFields();
				btnNew.setEnabled(true);
			}
		});
		btnSale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toSale();
			}
		});
		btnMakePdf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				generatePdf();
			}
		});
	}
	
	private void toFilterByClient(){
		if (searchClientField.getText().length() > 0) {
			clientList = clientController.filterByName(searchClientField.getText());
			jTableClient.setModel(new TableClient(clientList));
		}
	}
	
	private void toFilterByVehicle(){
		if (searchVehicleField.getText().length() > 0) {
			vehicleList = vehicleController.filterByPlate(searchVehicleField.getText());
			jTableVehicle.setModel(new TableVehicle(vehicleList));
		}
	}
	
	private void toSale(){
		SaleController saleController = new SaleController();
		
		//PEGANDO O NUMERO DA LINHA SELECIONADA NA TABELA
		int rowClientIndex = jTableClient.getSelectedRow();
		int rowVehicleIndex = jTableVehicle.getSelectedRow();
		
		//VERIFICANDO SE FOI SELECIONADO ALGUMA LINHA DAS TABELAS
		int verify = verifySelectionOnTable(rowClientIndex, rowVehicleIndex);
		
		if (verify != 0) {
			//OBTENDO VALORES DO VEÍCULO SELECIONADO
			Client client = new TableClient(clientList).get(rowClientIndex);
			Vehicle vehicle = new TableVehicle(vehicleList).get(rowVehicleIndex);
			
			saleController.insert(client.getId(), vehicle, 1, "38.000,00", pdfWay);
			int result = vehicleController.remove(vehicle.getId());
			
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "Sucesso! Venda realizada");
				clearAllFields();
				enableFields(false);
				btnNew.setEnabled(true);
			}else{
				JOptionPane.showMessageDialog(null, "Erro. Problema ao realizar a venda");
			}
		}
	}
	
	private void generatePdf(){
		MakePdf makePdf = new MakePdf();
		
		int rowClientIndex = jTableClient.getSelectedRow();
		int rowVehicleIndex = jTableVehicle.getSelectedRow();
		
		//VERIFICANDO SE FOI SELECIONADO ALGUMA LINHA DAS TABELAS
		int verify = verifySelectionOnTable(rowClientIndex, rowVehicleIndex);
		if (verify != 0) {
			//OBTENDO VALORES DO VEÍCULO SELECIONADO
			Client client = new TableClient(clientList).get(rowClientIndex);
			Vehicle vehicle = new TableVehicle(vehicleList).get(rowVehicleIndex);
			
			//CONSTRUINDO O PDF COM O CLIENTE 
			pdfWay = makePdf.newPdf(client, vehicle);
			//DESABILITANDO O BOTÃO PDF
			btnMakePdf.setEnabled(false);
			//EXIBINDO MENSAGEM DO PDF
			JOptionPane.showMessageDialog(null, makePdf.getResultOfSave());
		}
	}
	
	private void clearAllFields(){
		searchClientField.setText("");
		searchVehicleField.setText("");
		jTableClient.setModel(new DefaultTableModel());
		jTableVehicle.setModel(new DefaultTableModel());
	}
	
	private int verifySelectionOnTable(int rowClientIndex, int rowVehicleIndex){
		if (rowClientIndex == -1 ) {
			JOptionPane.showMessageDialog(this, "Selecione o cliente relacionado a venda!");
			return 0;
		} else if (rowVehicleIndex == -1) {
			JOptionPane.showMessageDialog(this, "Selecione o veículo relacionado a venda!");
			return 0;
		}
		return 1;
	}
	
	private void enableFields(boolean status){
		searchClientField.setEnabled(status);
		searchVehicleField.setEnabled(status);
		btnFindClient.setEnabled(status);
		btnFindVehicle.setEnabled(status);
		btnCancel.setEnabled(status);
		btnSale.setEnabled(status);
		btnMakePdf.setEnabled(status);
	}

}
