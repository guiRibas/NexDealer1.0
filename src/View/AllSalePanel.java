package View;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Controller.SaleController;
import Model.Sale;
import Model.TableSale;
import net.miginfocom.swing.MigLayout;

public class AllSalePanel extends JPanel implements VisualWindow{

	private static final long serialVersionUID = 1L;
	private JLabel nameLabel = new JLabel("Nome ");
	private JLabel cpfLabel = new JLabel("CPF ");
	private JLabel rgLabel = new JLabel("RG ");
	private JLabel birthDateLabel = new JLabel("Data Nasc. ");
	private JLabel brandLabel = new JLabel("Marca ");
	private JLabel modelLabel = new JLabel("Modelo ");
	private JLabel versionLabel = new JLabel("Versão ");	
	private JLabel typeLabel = new JLabel("Tipo ");	
	private JLabel yearLabel = new JLabel("Ano ");
	private JLabel renavamLabel = new JLabel("Renavam ");
	private JLabel plateLabel = new JLabel("Placa ");
	private JLabel fuelLabel = new JLabel("Combustível ");
	private JLabel colorLabel = new JLabel("Cor ");
	private JLabel frameLabel = new JLabel("Chassi ");
	private JTextField nameField = new JTextField(40);
	private JTextField cpfField = new JTextField(10);
	private JTextField rgField = new JTextField(10);
	private JTextField birthDateField = new JTextField(8);
	private JTextField brandField = new JTextField(12);
	private JTextField modelField = new JTextField(12);
	private JTextField versionField = new JTextField(20);
	private JTextField typeField = new JTextField(10);
	private JTextField yearField = new JTextField(6);
	private JTextField renavamField = new JTextField(10);
	private JTextField plateField = new JTextField(6);
	private JTextField fuelField = new JTextField(6);
	private JTextField colorField = new JTextField(6);
	private JTextField frameField = new JTextField(20);
	private List<Sale> saleList;
	private JPanel panelSearchSale, panelDataVehicle, panelDataClient, panelSales;
	private JTable jTableSales;
	private JScrollPane scrollPaneSales;
	private JTextField searchSale = new JTextField(80);
	private JButton btnFindSale, btnDownloadPdf, btnShow, btnDelete;
	private SaleController saleController = new SaleController();
	private Sale globalSale;
	
	public AllSalePanel() {
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
		
		panelSearchSale = new JPanel(new MigLayout());
		panelSearchSale.setBorder(BorderFactory.createTitledBorder("Buscar Venda - Data"));
		panelSearchSale.add(searchSale);
		btnFindSale = new JButton(imgFind);
		btnFindSale.setBorderPainted(false);
		panelSearchSale.add(btnFindSale);
		panelSearchSale.setBounds(5, 0, 970, 65);
		
//		panelResultSearchClient = new JPanel(new MigLayout());
//		panelResultSearchClient.setBorder(BorderFactory.createTitledBorder("Resultado de Clientes"));
//		jTableClients = new JTable();
//		scrollPaneClient = new JScrollPane(jTableClients);
//		panelResultSearchClient.add(scrollPaneClient);
//		panelResultSearchClient.setBounds(5, 70, 470, 240);
		
		panelSales = new JPanel(new MigLayout());
		panelSales.setBorder(BorderFactory.createTitledBorder("Vendas"));
		jTableSales = new JTable();
		scrollPaneSales = new JScrollPane(jTableSales);
		panelSales.add(scrollPaneSales, "wrap");
		btnShow = new JButton("Visualizar");
		panelSales.add(btnShow);
		btnDelete = new JButton("Excluir");
		panelSales.add(btnDelete, "cell 0 1");
		btnDownloadPdf = new JButton("Download ficha / Omni");
		panelSales.add(btnDownloadPdf, "cell 0 1");
		panelSales.setBounds(5, 70, 470, 445);
		
		panelDataClient = new JPanel(new MigLayout());
		panelDataClient.setBorder(BorderFactory.createTitledBorder("Cliente"));
		panelDataClient.add(nameLabel);
		panelDataClient.add(nameField, "wrap");
		panelDataClient.add(cpfLabel);
		panelDataClient.add(cpfField);
		panelDataClient.add(rgLabel, "cell 0 2");
		panelDataClient.add(rgField);
		panelDataClient.add(birthDateLabel, "cell 0 6");
		panelDataClient.add(birthDateField);
		panelDataClient.setBounds(500, 70, 470, 195);
		
		panelDataVehicle = new JPanel(new MigLayout());
		panelDataVehicle.setBorder(BorderFactory.createTitledBorder("Veículo"));
		panelDataVehicle.add(brandLabel);
		panelDataVehicle.add(brandField, "wrap");
		panelDataVehicle.add(modelLabel);
		panelDataVehicle.add(modelField);
		
		panelDataVehicle.add(versionLabel, "cell 0 2");
		panelDataVehicle.add(versionField);
		panelDataVehicle.add(typeLabel, "cell 0 3");
		panelDataVehicle.add(typeField);
		panelDataVehicle.add(yearLabel, "cell 0 7");
		panelDataVehicle.add(yearField);
		panelDataVehicle.add(renavamLabel, "cell 0 8");
		panelDataVehicle.add(renavamField);
		panelDataVehicle.add(plateLabel, "cell 0 9");
		panelDataVehicle.add(plateField);
		panelDataVehicle.add(fuelLabel, "cell 0 10");
		panelDataVehicle.add(fuelField);
		panelDataVehicle.add(colorLabel, "cell 0 14");
		panelDataVehicle.add(colorField);
		panelDataVehicle.add(frameLabel, "cell 0 15");
		panelDataVehicle.add(frameField);
		panelDataVehicle.setBounds(500, 270, 470, 283);
		
		add(panelSearchSale);
		add(panelDataVehicle);
		add(panelDataClient);
		add(panelSales);
		
		disableFields(false);
	
		setMinimumSize(new Dimension(980, 600));
	}

	@Override
	public void setupEvents() {
		btnFindSale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filterByClientName();
			}
		});
		btnShow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toShow();
			}
		});
		btnDownloadPdf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toDownloadPdf();				
			}
		});
	}
	
	public void toShow(){
		int rowIndex = jTableSales.getSelectedRow();
		
		//VERIFICANDO SE FOI SELECIONADO ALGUMA LINHA DA TABELA DE VE
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Selecione a venda desejada para visualizar!");
			return;
		}
		
		//OBTENDO VALORES DO VEÍCULO SELECIONADO
		Sale sale = new TableSale(saleList).get(rowIndex);		
		
		//ATRIBUINDO VALOR A GLOBAL PARA GERAR PDF
		globalSale = sale;
		
		//SETANDO VALORES NO CAMPOS PARA POSSIVEIS EDIÇÕES
		nameField.setText(sale.getClient().getName());
		cpfField.setText(sale.getClient().getCpf());
		rgField.setText(sale.getClient().getRg());
		birthDateField.setText(sale.getClient().getBirthDate());
		brandField.setText(sale.getVechicle().getVersion().getModel().getBrand().getDescription());
		modelField.setText(sale.getVechicle().getVersion().getModel().getDescription());
		versionField.setText(sale.getVechicle().getVersion().getDescription());
		typeField.setText(sale.getType().getDescription());
		yearField.setText(sale.getVechicle().getYear());
		renavamField.setText(String.valueOf(sale.getVechicle().getRenavam()));
		plateField.setText(sale.getVechicle().getPlate());
		fuelField.setText(sale.getVechicle().getFuel());
		colorField.setText(sale.getVechicle().getColor());
		frameField.setText(sale.getVechicle().getFrame());
		
		btnDownloadPdf.setEnabled(true);
	}
	
	public void toDownloadPdf(){
		try {
			Desktop.getDesktop().open(new File(globalSale.getPdfWay()));
		} catch (IOException e) {
			System.out.println("Erro. Problema ao abrir PDF");
		}
	}
	
	public void filterByClientName(){
		if (searchSale.getText().length() > 0) {
			saleList = saleController.findByDate(searchSale.getText());
			jTableSales.setModel(new TableSale(saleList));
			btnShow.setEnabled(true);
		}
	}
	
	public void disableFields(boolean status){
		nameField.setDisabledTextColor(Color.BLACK);
		nameField.setBorder(new LineBorder(Color.GRAY));
		nameField.setEnabled(status);
		cpfField.setDisabledTextColor(Color.BLACK);
		cpfField.setBorder(new LineBorder(Color.GRAY));
		cpfField.setEnabled(status);
		rgField.setDisabledTextColor(Color.BLACK);
		rgField.setBorder(new LineBorder(Color.GRAY));
		rgField.setEnabled(status);
		birthDateField.setDisabledTextColor(Color.BLACK);
		birthDateField.setBorder(new LineBorder(Color.GRAY));
		birthDateField.setEnabled(status);
		brandField.setDisabledTextColor(Color.BLACK);
		brandField.setBorder(new LineBorder(Color.GRAY));
		brandField.setEnabled(status);
		modelField.setDisabledTextColor(Color.BLACK);
		modelField.setBorder(new LineBorder(Color.GRAY));
		modelField.setEnabled(status);
		versionField.setDisabledTextColor(Color.BLACK);
		versionField.setBorder(new LineBorder(Color.GRAY));
		versionField.setEnabled(status);
		typeField.setDisabledTextColor(Color.BLACK);
		typeField.setBorder(new LineBorder(Color.GRAY));
		typeField.setEnabled(status);
		yearField.setDisabledTextColor(Color.BLACK);
		yearField.setBorder(new LineBorder(Color.GRAY));
		yearField.setEnabled(status);
		renavamField.setDisabledTextColor(Color.BLACK);
		renavamField.setBorder(new LineBorder(Color.GRAY));
		renavamField.setEnabled(status);
		plateField.setDisabledTextColor(Color.BLACK);
		plateField.setBorder(new LineBorder(Color.GRAY));
		plateField.setEnabled(status);
		fuelField.setDisabledTextColor(Color.BLACK);
		fuelField.setBorder(new LineBorder(Color.GRAY));
		fuelField.setEnabled(status);
		colorField.setDisabledTextColor(Color.BLACK);
		colorField.setBorder(new LineBorder(Color.GRAY));
		colorField.setEnabled(status);
		frameField.setDisabledTextColor(Color.BLACK);
		frameField.setBorder(new LineBorder(Color.GRAY));
		frameField.setEnabled(status);
		btnShow.setEnabled(status);
		btnDelete.setEnabled(status);
		btnDownloadPdf.setEnabled(status);
	}

}
