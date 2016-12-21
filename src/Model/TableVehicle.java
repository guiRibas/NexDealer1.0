package Model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableVehicle extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private static final int COL_BRAND = 0;
	private static final int COL_MODEL = 1;
	private static final int COL_PLATE = 2;
	private static final int COL_STATUS = 3;
	
	private List<Vehicle> values;
	
	public TableVehicle(List<Vehicle> values) {
		this.values = values;
	}
	
	@Override
	public int getRowCount() {
		return values.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int numLine, int numColumn) {
		Vehicle vehicle = values.get(numLine);
		switch (numColumn) {
		case COL_BRAND:
			return vehicle.getVersion().getModel().getBrand().getDescription();
		case COL_MODEL:
			return vehicle.getVersion().getModel().getDescription();
		case COL_PLATE:
			return vehicle.getPlate();
		case COL_STATUS:
			return vehicle.getStatus();
		default:
			break;
		}	
		return null;
	}

	@Override
	public String getColumnName(int column) {
		String nameOfColumn = "";
		switch (column) {
		case COL_BRAND:
			nameOfColumn = "Marca";
			break;
		case COL_MODEL:
			nameOfColumn = "Modelo";
			break;
		case COL_PLATE:
			nameOfColumn = "Placa";
			break;	
		case COL_STATUS:
			nameOfColumn = "Status";
			break;
		default:
			throw new IllegalArgumentException("Erro. Coluna Inválida");
		}
		return nameOfColumn;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case COL_BRAND:
			return String.class;
		case COL_MODEL:
			return String.class;
		case COL_PLATE:
			return int.class;
		case COL_STATUS:
			return String.class;
		default:
			break;
		}	
		return null;
	}
	
	public Vehicle get(int row){
		return values.get(row);
	}

}
