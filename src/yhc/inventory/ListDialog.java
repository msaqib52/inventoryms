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
public class ListDialog extends JFrame { 
        
    private JTable table;
    private JScrollPane scrollPane;
    private ListSelectionModel selectionModel;
    
    private JLabel lblID;
    private JLabel lblName;
    private JLabel lblPhone;
    private JLabel lblStreet;
    private JLabel lblCity;
    private JLabel lblEmail;
    
    private JTextField txtID;
    private JTextField txtName;
    private JTextField txtPhone;
    private JTextField txtStreet;
    private JTextField txtCity;
    private JTextField txtEmail;
    
    private JButton btnRefresh;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnPrint;
    private ButtonHandler buttonHandler;
    
    private Statement statement;
    private ResultSet rs;
    
    private String listName;

    public ListDialog(String listName) {
        setTitle(listName + " List");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        lblID = new JLabel("ID:");
        lblID.setBounds(10, 20, 40, 20);
        lblName = new JLabel("Name:");
        lblName.setBounds(240, 20, 40, 20);
        lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(480, 20, 40, 20);
        
        lblStreet = new JLabel("Street:");
        lblStreet.setBounds(10, 50, 45, 20);
        lblCity = new JLabel("City:");
        lblCity.setBounds(240, 50, 40, 20);
        lblEmail = new JLabel("Email:");
        lblEmail.setBounds(480, 50, 40, 20);
        
        txtID = new JTextField();
        txtID.setBounds(45, 20, 145, 20);
        txtID.setEditable(false);
        txtName = new JTextField();
        txtName.setBounds(280, 20, 150, 20);
        txtPhone = new JTextField();
        txtPhone.setBounds(520, 20, 150, 20);
        
        txtStreet = new JTextField();
        txtStreet.setBounds(45, 50, 145, 20);
        txtCity = new JTextField();
        txtCity.setBounds(280, 50, 150, 20);
        txtEmail = new JTextField();
        txtEmail.setBounds(520, 50, 150, 20);
        
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
        
        this.listName = listName;
        
        statement = DatabaseConnection.getStatement();
        
                  
        setTable(this.listName);

        setLayout(null);

        add(lblID);
        add(txtID);
        add(lblName);
        add(txtName);
        add(lblPhone);
        add(txtPhone);
        add(lblStreet);
        add(txtStreet);
        add(lblCity);
        add(txtCity);
        add(lblEmail);
        add(txtEmail);
        add(btnRefresh);
        add(btnDelete);
        add(btnUpdate);
        add(btnPrint);
    }
    
    public void setTable(String listName) {
        try {  
            String query = "SELECT * FROM " + listName  + " ;";
            rs = statement.executeQuery(query);
            table = new ResultSetTable(rs);
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
    
    public void refreshTable(String listName) {
        txtID.setText("");
        txtName.setText("");
        txtStreet.setText("");
        txtCity.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        remove(scrollPane);
        setTable(listName);
    }
    
    public class RowListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            int selecctedRow = table.getSelectedRow();
            txtID.setText((String)table.getValueAt(selecctedRow, 0));
            txtName.setText((String)table.getValueAt(selecctedRow, 1));
            txtStreet.setText((String)table.getValueAt(selecctedRow, 2));
            txtCity.setText((String)table.getValueAt(selecctedRow, 3));
            txtPhone.setText((String)table.getValueAt(selecctedRow, 4));
            txtEmail.setText((String)table.getValueAt(selecctedRow, 5));
            
        }
        
    }
    
    public class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnRefresh)
                refreshTable(listName);
            else if(e.getSource() == btnDelete) {
                String strID = txtID.getText();
                if(strID.matches("")) {
                    JOptionPane.showMessageDialog(null, "No " + listName + " are selected to Delete");
                    return;
                }
                
                int option = JOptionPane.showConfirmDialog(null, "Are your sure you want to delete?", null, JOptionPane.YES_NO_OPTION);
                if(option != JOptionPane.YES_OPTION)
                    return;
                
                int id = Integer.parseInt(strID);
                
                String query = "DELETE FROM " + listName + " WHERE ID = " + id + ";";
                try {
                    statement.executeUpdate(query);
                    refreshTable(listName);
                } catch(SQLException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString());
                }
            } else if(e.getSource() == btnUpdate) {
                String strID = txtID.getText();
                if(strID.matches("")) {
                    JOptionPane.showMessageDialog(null, "No " + listName + " are selected to Update");
                    return;
                }
                
                int option = JOptionPane.showConfirmDialog(null, "Are your sure you want to Update?", null, JOptionPane.YES_NO_OPTION);
                if(option != JOptionPane.YES_OPTION)
                    return;
                
                int id = Integer.parseInt(strID);
                String name = txtName.getText();
                String phone = txtPhone.getText();
                String street = txtStreet.getText();
                String city = txtCity.getText();
                String email = txtEmail.getText();
                String query = String.format("UPDATE " + listName + " SET Name = '%s', Phone = '%s', Street = '%s', City = '%s', Email = '%s' WHERE ID = %d;", name, phone, street, city, email, id);
                try {
                    statement.executeUpdate(query);
                    refreshTable(listName);
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
