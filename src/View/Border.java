package View;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Border extends JPanel implements VisualWindow{
	
	@Override
	public void setupLayout() {
		setVisible(true);
		setBackground(Color.green);
		setSize(100, 600);
		setMinimumSize(new Dimension(100, 600));		
	}

	@Override
	public void setupComponents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupEvents() {
		// TODO Auto-generated method stub
		
	}	
	
}
