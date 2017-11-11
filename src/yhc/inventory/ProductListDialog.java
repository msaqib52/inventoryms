/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yhc.inventory;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Saqib
 */
public class ProductListDialog extends JFrame { 
        
    private JTable table;
    private JScrollPane scrollPane;
    private ListSelectionModel selectionModel;
    
    private JLabel lblProductID;
    private JLabel lblVendorID;
    private JLabel lblProductName;
    private JLabel lblUnitPrice;
    private JLabel lblProductQuantity;
    
    private JTextField txtProductID;
    private JTextField txtVendorID;
    private JTextField txtProductName;
    private JTextField txtUnitPrice;
    private JTextField txtProductQuantity;
    
    private JButton btnRefresh;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnPrint;
    private ButtonHandler buttonHandler;
    
    private Statement statement;
    private ResultSet rs;
   
    public ProductListDialog() {
        setTitle("Products List");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        lblProductID = new JLabel("Product ID:");
        lblProductID.setBounds(10, 20, 60, 20);
        lblProductName = new JLabel("Name:");
        lblProductName.setBounds(240, 20, 60, 20);
        lblProductQuantity = new JLabel("Quantity:");
        lblProductQuantity.setBounds(480, 20, 60, 20);
        
        lblVendorID = new JLabel("Vendor ID:");
        lblVendorID.setBounds(10, 50, 60, 20);
        lblUnitPrice = new JLabel("Unit Price:");
        lblUnitPrice.setBounds(240, 50, 60, 20);
        
        txtProductID = new JTextField();
        txtProductID.setBounds(65, 20, 125, 20);
        txtProductID.setEditable(false);
        txtProductName = new JTextField();
        txtProductName.setBounds(300, 20, 130, 20);
        txtProductQuantity = new JTextField();
        txtProductQuantity.setBounds(540, 20, 130, 20);
        
        txtVendorID = new JTextField();
        txtVendorID.setBounds(65, 50, 125, 20);
        txtUnitPrice = new JTextField();
        txtUnitPrice.setBounds(300, 50, 130, 20);
        
        buttonHandler = new ButtonHandler();
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBackground(Color.WHITE);
        btnRefresh.setBounds(592, 85, 80, 25);
        btnRefresh.addActionListener(buttonHandler);
        
        btnDelete = new JButton("Delete");
        btnDelete.setBackground(Color.WHITE);
        btnDelete.setBounds(502, 85, 80, 25);
        btnDelete.addActionListener(buttonHandler);
        
        btnUpdate = new JButton("Update");
        btnUpdate.setBackground(Color.WHITE);
        btnUpdate.setBounds(412, 85, 80, 25);
        btnUpdate.addActionListener(buttonHandler);
        
        btnPrint = new JButton("Print");
        btnPrint.setBackground(Color.WHITE);
        btnPrint.setBounds(322, 85, 80, 25);
        btnPrint.addActionListener(buttonHandler);
        
        
        statement = DatabaseConnection.getStatement();
        
                  
        setTable();

        setLayout(null);

        add(lblProductID);
        add(txtProductID);
        add(lblProductName);
        add(txtProductName);
        add(lblProductQuantity);
        add(txtProductQuantity);
        add(lblUnitPrice);
        add(txtUnitPrice);
        add(lblVendorID);
        add(txtVendorID);
        add(btnRefresh);
        add(btnDelete);
        add(btnUpdate);
        add(btnPrint);
    }
    
    public void setTable() {
        try {  
            String query = "SELECT * FROM Products;";
            rs = statement.executeQuery(query);
            table = new ProductTableModel(rs);
            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(0, 125, 695, 347);
            selectionModel = table.getSelectionModel();
            selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            selectionModel.addListSelectionListener(new RowListener());
            add(scrollPane);
         } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            try {
                rs.close();
            } catch(SQLException rse) {
                
            }
        }
    }
    
    public void refreshTable() {
        txtProductID.setText("");
        txtProductName.setText("");
        txtProductQuantity.setText("");
        txtUnitPrice.setText("");
        txtVendorID.setText("");
        remove(scrollPane);
        setTable();
    }
    
    public class RowListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            int selecctedRow = table.getSelectedRow();
            txtProductID.setText((String)table.getValueAt(selecctedRow, 0));
            txtVendorID.setText((String)table.getValueAt(selecctedRow, 1));
            txtProductName.setText((String)table.getValueAt(selecctedRow, 2));
            txtProductQuantity.setText((String)table.getValueAt(selecctedRow, 3));
            txtUnitPrice.setText((String)table.getValueAt(selecctedRow, 4));           
        }
        
    }
    
    public class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnRefresh)
                refreshTable();
            else if(e.getSource() == btnDelete) {
                String strID = txtProductID.getText();
                if(strID.matches("")) {
                    JOptionPane.showMessageDialog(null, "No Products are selected to Delete");
                    return;
                }
                
                int option = JOptionPane.showConfirmDialog(null, "Are your sure you want to delete?", null, JOptionPane.YES_NO_OPTION);
                if(option != JOptionPane.YES_OPTION)
                    return;
                
                int id = Integer.parseInt(strID);
                
                String query = "DELETE FROM Products WHERE productID = " + id + ";";
                try {
                    statement.executeUpdate(query);
                    refreshTable();
                } catch(SQLException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString());
                }
            } else if(e.getSource() == btnUpdate) {
                String strID = txtProductID.getText();
                if(strID.matches("")) {
                    JOptionPane.showMessageDialog(null, "No Products are selected to Update");
                    return;
                }
                
                int option = JOptionPane.showConfirmDialog(null, "Are your sure you want to Update?", null, JOptionPane.YES_NO_OPTION);
                if(option != JOptionPane.YES_OPTION)
                    return;
                
                int id = Integer.parseInt(strID);
                int vendorID = Integer.parseInt(txtVendorID.getText());
                String productName = txtProductName.getText();
                int productQuantity = Integer.parseInt(txtProductQuantity.getText());
                float unitPrice = Float.parseFloat(txtUnitPrice.getText());
                String query = String.format("UPDATE Products SET vendorID = '%d', name = '%s', quantity = '%d', unitPrice = '%f' WHERE productID = '%d';", vendorID, productName, productQuantity, unitPrice, id);
                try {
                    statement.executeUpdate(query);
                    refreshTable();
                } catch(SQLException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString());
                }
            } else if(e.getSource() == btnPrint) {
                try {
                    table.print();
                } catch(PrinterException pe) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            }
        }
    }
}
