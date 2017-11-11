/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yhc.inventory;

import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Saqib
 */
public class ResultSetTable extends JTable{
    
    private final DefaultTableModel dataModel;
    
    public ResultSetTable(ResultSet rs) throws SQLException {
        super();
        dataModel = new DefaultTableModel();
        setModel(dataModel);

        try {
            ResultSetMetaData mdata = rs.getMetaData();

            int columnCount = mdata.getColumnCount();
            String columnNames[] = new String[columnCount];

            for(int i = 1; i <= columnCount; i++)
                columnNames[i-1] = mdata.getColumnName(i);

            dataModel.setColumnIdentifiers(columnNames);

            while(rs.next()) {
                String rowData[] = new String[columnCount];
                for(int i = 1; i <= columnCount; i++)
                    rowData[i-1] = rs.getString(i);

                dataModel.addRow(rowData);
            }
            int x = getRowCount();
            for(; x < 20; x++)
                dataModel.addRow(new Vector());
            
            this.getColumnModel().getColumn(0).setPreferredWidth(1);           

        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    
    
    @Override
    public boolean isCellEditable (int row, int column) {
        return false;
    }
    
}
