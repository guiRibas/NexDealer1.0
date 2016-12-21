package Model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableClient extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private static final int COL_NAME = 0;
	private static final int COL_CPF = 1;
	private static final int COL_TELEPHONE = 2;
	private static final int COL_STATUS = 3;
	
	private List<Client> values;
	
	public TableClient(List<Client> values) {
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
		Client client = values.get(numLine);
		switch (numColumn) {
		case COL_NAME:
			return client.getName();
		case COL_CPF:
			return client.getCpf();
		case COL_TELEPHONE:
			return client.getContact().getTelephone();
		case COL_STATUS:
			return client.getStatus();
		default:
			break;
		}	
		return null;
	}

	@Override
	public String getColumnName(int column) {
		String nameOfColumn = "";
		switch (column) {
		case COL_NAME:
			nameOfColumn = "Nome";
			break;
		case COL_CPF:
			nameOfColumn = "CPF";
			break;
		case COL_TELEPHONE:
			nameOfColumn = "Telefone";
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
		case COL_NAME:
			return String.class;
		case COL_CPF:
			return String.class;
		case COL_TELEPHONE:
			return int.class;
		case COL_STATUS:
			return String.class;
		default:
			break;
		}	
		return null;
	}
	
	public Client get(int row){
		return values.get(row);
	}

}
