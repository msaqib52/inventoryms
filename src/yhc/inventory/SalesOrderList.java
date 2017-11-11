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
public class SalesOrderList extends JFrame { 
        
    private JTable table;
    private JScrollPane scrollPane;
    private ListSelectionModel selectionModel;
    
    private JLabel lblOrderNumber;
    private JLabel lblDate;
    private JLabel lblCustomerID;
    private JLabel lblCustomerName;
    private JLabel lblTotal;
    
    private JTextField txtOrderNumber;
    private JTextField txtDate;
    private JTextField txtCustomerID;
    private JTextField txtCustomerName;
    private JTextField txtTotal;
    
    private JButton btnRefresh;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnEditProducts;
    private JButton btnPrint;
    private ButtonHandler buttonHandler;
    
    private Statement statement;
    private ResultSet rs;
   
    public SalesOrderList() {
        setTitle("Sales Orders List");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        lblOrderNumber = new JLabel("Order#:");
        lblOrderNumber.setBounds(10, 20, 60, 20);
        lblDate = new JLabel("Date:");
        lblDate.setBounds(240, 20, 60, 20);
        lblCustomerID = new JLabel("Customer ID:");
        lblCustomerID.setBounds(480, 20, 65, 20);
        
        lblCustomerName = new JLabel("Name:");
        lblCustomerName.setBounds(10, 50, 60, 20);
        lblTotal = new JLabel("Total:");
        lblTotal.setBounds(240, 50, 60, 20);
        
        txtOrderNumber = new JTextField();
        txtOrderNumber.setBounds(65, 20, 125, 20);
        txtOrderNumber.setEditable(false);
        txtDate = new JTextField();
        txtDate.setBounds(300, 20, 130, 20);
        txtCustomerID = new JTextField();
        txtCustomerID.setBounds(545, 20, 130, 20);
        
        txtCustomerName = new JTextField();
        txtCustomerName.setBounds(65, 50, 125, 20);
        txtTotal = new JTextField();
        txtTotal.setBounds(300, 50, 130, 20);
        
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

        add(lblOrderNumber);
        add(txtOrderNumber);
        add(lblDate);
        add(txtDate);
        add(lblCustomerID);
        add(txtCustomerID);
        add(lblCustomerName);
        add(txtCustomerName);
        add(lblTotal);
        add(txtTotal);
        add(btnRefresh);
        add(btnDelete);
        add(btnUpdate);
        add(btnPrint);
    }
    
    public void setTable() {
        try {  
            String query = "SELECT salesordernumber.OrderNumber, salesordernumber.Date, salesordernumber.customerID, Customers.Name, salesordernumber.Total FROM salesordernumber, Customers where Customers.ID = salesordernumber.CustomerID;";
            rs = statement.executeQuery(query);
            table = new SalesTableModel(rs);
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
        txtDate.setText("");
        txtOrderNumber.setText("");
        txtTotal.setText("");
        txtCustomerID.setText("");
        txtCustomerName.setText("");
        remove(scrollPane);
        setTable();
    }
    
    public class RowListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            int selecctedRow = table.getSelectedRow();
            txtOrderNumber.setText((String)table.getValueAt(selecctedRow, 0));
            txtDate.setText((String)table.getValueAt(selecctedRow, 1));
            txtCustomerID.setText((String)table.getValueAt(selecctedRow, 2));
            txtCustomerName.setText((String)table.getValueAt(selecctedRow, 3));
            txtTotal.setText((String)table.getValueAt(selecctedRow, 4));           
        }
        
    }
    
    public class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnRefresh)
                refreshTable();
            else if(e.getSource() == btnDelete) {
                String strOrderNumber = txtOrderNumber.getText();
                if(strOrderNumber.matches("")) {
                    JOptionPane.showMessageDialog(null, "No Sales Orders are selected to Delete");
                    return;
                }
                
                int option = JOptionPane.showConfirmDialog(null, "Are your sure you want to delete?", null, JOptionPane.YES_NO_OPTION);
                if(option != JOptionPane.YES_OPTION)
                    return;
                
                int on = Integer.parseInt(strOrderNumber);
                
                String query1 = "DELETE FROM salesordernumber WHERE salesordernumber.OrderNumber = " + on + ";";
                String query2 = "DELETE FROM salesorders WHERE salesorders.OrderNumber = " + on + ";";
                try {
                    statement.executeUpdate(query1);
                    statement.executeUpdate(query2);
                    refreshTable();
                } catch(SQLException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString());
                }
            } else if(e.getSource() == btnUpdate) {
                String orderNumber = txtOrderNumber.getText();
                if(orderNumber.matches("")) {
                    JOptionPane.showMessageDialog(null, "No Sales Orders are selected to Update");
                    return;
                }
                
                int option = JOptionPane.showConfirmDialog(null, "Are your sure you want to Update?", null, JOptionPane.YES_NO_OPTION);
                if(option != JOptionPane.YES_OPTION)
                    return;
                
                int on = Integer.parseInt(orderNumber);
                int customerID = Integer.parseInt(txtCustomerID.getText());
                String customerName = txtCustomerName.getText();
                String date = txtDate.getText();
                float total = Float.parseFloat(txtTotal.getText());
                String query1 = String.format("UPDATE salesordernumber SET salesordernumber.customerID = '%d', salesordernumber.Date = '%s', salesordernumber.total = '%f' WHERE salesordernumber.OrderNumber = '%d';", customerID, date, total, on);
                String query2 = String.format("UPDATE Customers SET Customers.Name = '%s' WHERE Customers.ID = '%d';", customerName, on);
                
                try {
                    statement.executeUpdate(query1);
                    statement.executeUpdate(query2);
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
