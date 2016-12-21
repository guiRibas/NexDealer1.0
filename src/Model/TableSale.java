package Model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableSale extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private static final int COL_CLIENT = 0;
	private static final int COL_VEHICLE = 1;
	private static final int COL_TOTAL = 2;
	
	private List<Sale> values;
	
	public TableSale(List<Sale> values) {
		this.values = values;
	}
	
	@Override
	public int getRowCount() {
		return values.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int numLine, int numColumn) {
		Sale sale = values.get(numLine);
		switch (numColumn) {
		case COL_CLIENT:
			return sale.getClient().getName();
		case COL_VEHICLE:
			return sale.getVechicle().getVersion().getModel().getDescription();
		case COL_TOTAL:
			return sale.getTotal();
		default:
			break;
		}	
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
		String nameOfColumn = "";
		switch (column) {
		case COL_CLIENT:
			nameOfColumn = "Cliente";
			break;
		case COL_VEHICLE:
			nameOfColumn = "Veículo";
			break;
		case COL_TOTAL:
			nameOfColumn = "Total";
			break;
		default:
			throw new IllegalArgumentException("Erro. Coluna Inválida");
		}
		return nameOfColumn;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case COL_CLIENT:
			return String.class;
		case COL_VEHICLE:
			return String.class;
		case COL_TOTAL:
			return String.class;
		default:
			break;
		}	
		return null;
	}

	public Sale get(int row){
		return values.get(row);
	}

}
