package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ScreenMenuBar extends JMenuBar implements VisualWindow{

	JMenu clientMenu, vehicleMenu, saleMenu, about, logout;
	JMenuItem newClient, findClient, newVehicle, findVehicle, newSale, allSale;
	private MainFrame mainF;
	
	public ScreenMenuBar(MainFrame mainF) {
		this.mainF = mainF;
		setupLayout();
		setupComponents();
		setupEvents();
	}
	
	@Override
	public void setupLayout() {
		
		setBackground(Color.GRAY);
		setVisible(true);
		
	}

	@Override
	public void setupComponents() {
		//----------CRIANDO MENU CLIENTE E SUAS OPÇÕES------------------
		clientMenu = new JMenu("Cliente");
		newClient = new JMenuItem("Geral");
		add(clientMenu);
		clientMenu.add(newClient);
		//--------------------------------------------------------------
		//----------CRIANDO MENU VEICULOS E SUAS OPÇÕES-----------------
		vehicleMenu = new JMenu("Veiculos");
		newVehicle = new JMenuItem("Geral");
		add(vehicleMenu);
		vehicleMenu.add(newVehicle);
		//--------------------------------------------------------------
		//----------CRIANDO MENU EMPREGADOS E SUAS OPÇÕES---------------
		saleMenu = new JMenu("Venda");
		newSale = new JMenuItem("Nova");
		allSale = new JMenuItem("Procurar");
		add(saleMenu);
		saleMenu.add(newSale);
		saleMenu.add(allSale);
		//--------------------------------------------------------------
		//----------CRIANDO O BOTÃO SOBRE ------------------------------
		about = new JMenu("Sobre");
		add(about);
	}

	@Override
	public void setupEvents() {
		
		newClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainF.cl.show(mainF.teste, "cliente");
			}
		});
		newVehicle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainF.cl.show(mainF.teste, "veiculo");				
			}
		});
		newSale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainF.cl.show(mainF.teste, "venda");				
			}
		});
		allSale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainF.cl.show(mainF.teste, "procurarVenda");
			}
		});
		
	}

	
	
}
