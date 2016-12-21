package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements VisualWindow{

	ScreenMenuBar menuBar;
	
	public CardLayout cl;
	
	JPanel teste = new JPanel();
	JPanel teste2 = new JPanel();
	
	ClientPanel clientPanel;
	VehiclePanel vehiclePanel;
	SalePanel salePanel;
	AllSalePanel allSalePanel;
	
	Border br, bl;
	
	public MainFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
		Point newLocation = new Point(middle.x - (500), 
		                              middle.y - (350));
		this.setLocation(newLocation);
		this.setTitle("Nex Dealer - 1.0");
		setupLayout();
		setupComponents();
		setupEvents();
		pack();
	}
	
	@Override
	public void setupLayout() {
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(995, 620));
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void setupComponents() {
		menuBar = new ScreenMenuBar(this);
		add(menuBar, BorderLayout.NORTH);
		
		teste.setBackground(Color.GREEN);
		teste.setVisible(true);

		clientPanel = new ClientPanel();
		vehiclePanel = new VehiclePanel();
		salePanel = new SalePanel();
		allSalePanel = new AllSalePanel();
		
		cl = new CardLayout();
		
		teste.setLayout(cl);
		teste.add(teste2, "");
		teste.add(clientPanel, "cliente");
		teste.add(vehiclePanel, "veiculo");
		teste.add(salePanel, "venda");
		teste.add(allSalePanel, "procurarVenda");
		
		getContentPane().add(teste, BorderLayout.CENTER);
		
		br = new Border();
		add(br, BorderLayout.EAST);
		bl = new Border();
		add(bl, BorderLayout.WEST);
		
	}

	@Override
	public void setupEvents() {
		
		
	}

	
	
}
