package mypackage;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FTableModel implements TableModel {

    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    private RecordsArray recordsArray;
    private Object[][] local_array;

    public FTableModel(RecordsArray array) {
        recordsArray = array;
        local_array = new Object[2][17];
    }

    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

    @Override
    public int getRowCount() {
        return recordsArray.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Username";
            case 1:
                return "Record";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            return recordsArray.get(rowIndex).getUsername();
        } else {
            return recordsArray.get(rowIndex).getScore();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }
}