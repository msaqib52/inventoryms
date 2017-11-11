/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PurchaseOrderForm.java
 *
 * Created on Jul 13, 2011, 3:08:50 PM
 */
package yhc.inventory;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Saqib
 */
public class PurchaseOrderForm extends javax.swing.JFrame implements TableModelListener, Printable {
    int column;
    int row;
    int vendorID  = 0;
    int productID = 0;
    
    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

        if (page > 0) {
             return NO_SUCH_PAGE;
        }


        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        String OrderNumber = txtOrderNumber.getText();
        String VendorID    = txtVendorID.getText();
        String date        = txtOrderDate.getText();
        String name        = txtVendorName.getText();
        String phone       = txtVendorPhone.getText();
        String address     = txtVendorAddress.getText();
        String total       = lblTotal.getText();
        int rowCount       = 10;
        PurchasePrintLayout printLayout = new PurchasePrintLayout();
        
        JTable table = printLayout.getPrintTable();
               
        printLayout.getLblOrderNumber().setText(OrderNumber);
        printLayout.getLblVendorNumber().setText(VendorID);
        printLayout.getLblDate().setText(date);
        printLayout.getLblName().setText(name);
        printLayout.getLblPhone().setText(phone);
        printLayout.getLblAddress().setText(address);
        printLayout.getLblTotal().setText(total);
        
        for(int i = 0; i < rowCount; i++) {
            table.setValueAt(jTable1.getValueAt(i, 0), i, 0);
            table.setValueAt(jTable1.getValueAt(i, 1), i, 1);
            table.setValueAt(jTable1.getValueAt(i, 2), i, 2);
            table.setValueAt(jTable1.getValueAt(i, 3), i, 3);
        }

        printLayout.getContentPane().print(g);
        return PAGE_EXISTS;
    }

    @Override
    public void tableChanged(TableModelEvent tevt) {

        int col = tevt.getColumn();
        if(col == 0 || col == 2) {
            column        = tevt.getColumn();
            row           = tevt.getFirstRow();

            if(column == 0) {
                try {
                    productID = (Integer)jTable1.getValueAt(row, column);
                } catch(Exception e) {return;} 
            }

            if( productID > 0 && col == 0) {
                Statement stmt = DatabaseConnection.getStatement();
                try {
                    String query = String.format("Select Products.Name AS productName FROM Products Where Products.productID = %d;", productID);
                    ResultSet rs   = stmt.executeQuery(query);

                    String arr = new String("");
                    if(rs.next()) {
                        arr = rs.getString("productName");
                    }
                    if(arr.equals("")) {
                        JOptionPane.showMessageDialog(null, "Product Does Not Exist");
                        return;
                    }
                    jTable1.setValueAt(arr, row, 1);
                } catch(SQLException sqle) {
                    JOptionPane.showMessageDialog(null, sqle.toString());
                }
            } else if(col == 2) {
                Statement stmt = DatabaseConnection.getStatement();
                int quantity = 0;
                int productID;
                try {
                    productID = (Integer)jTable1.getValueAt(jTable1.getSelectedRow(), 0);
                    quantity = (Integer)jTable1.getValueAt(row, col);
                } catch(Exception e) {return;} 
                try {
                    String query = String.format("Select Products.unitPrice AS productPrice FROM Products Where Products.productID = %d;", productID);
                    ResultSet rs   = stmt.executeQuery(query);
                    rs.next();
                    double unitPrice = rs.getDouble("productPrice");
                    double subtotal = unitPrice * quantity;
                    jTable1.setValueAt(subtotal, row, 3);
                    int rowCount = jTable1.getRowCount();
                    Double total = 0.0;
                    try {
                        for(int i = 0; i < rowCount; i++) {
                           String str = String.valueOf(jTable1.getValueAt(i, 3));
                           if(str.matches("null"))
                               continue;
                           total += Double.valueOf(str);
                        }
                        setLblTotal(total);
                    } catch(Exception e) {JOptionPane.showMessageDialog(null, e.toString());
                    }
                    
                } catch(SQLException sqle) {
                    JOptionPane.showMessageDialog(null, "Product does not Exist");
                }
            }
        }
    }
    
    /** Creates new form PurchaseOrderForm */
    public PurchaseOrderForm() {
        initComponents();

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtOrderNumber = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtOrderDate = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtVendorID = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtVendorName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtVendorPhone = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtVendorAddress = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        btnSavePrint = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Purchase Order Form");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 192, 0));

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTitle.setText("Puchase Order Information");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 210, Short.MAX_VALUE)
                    .addComponent(lblTitle)
                    .addGap(0, 211, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 9, Short.MAX_VALUE)
                    .addComponent(lblTitle)
                    .addGap(0, 9, Short.MAX_VALUE)))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Total RS:");

        lblTotal.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotal.setText("0.0");

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setBorder(null);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Product ID", "Product Name", "Quantity", "Sub-Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getModel().addTableModelListener(this);
        jTable1.setGridColor(new java.awt.Color(204, 204, 204));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable1PropertyChange(evt);
            }
        });
        jTable1.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                jTable1VetoableChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(391, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblTotal)))
        );

        jLabel2.setText("Order Number:");

        jLabel3.setText("Order Date:");

        jLabel4.setText("Vendor ID:");

        txtVendorID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtVendorIDFocusLost(evt);
            }
        });
        txtVendorID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtVendorIDKeyPressed(evt);
            }
        });

        jLabel5.setText("Vendor Name:");

        jLabel6.setText("Vendor Phone:");

        jLabel7.setText("Address:");

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnSavePrint.setText("Save & Print");
        btnSavePrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSavePrintActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtVendorPhone)
                    .addComponent(txtOrderNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtVendorAddress)
                    .addComponent(txtVendorID, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtOrderDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(txtVendorName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(343, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSavePrint)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtOrderNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtVendorID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtVendorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOrderDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(txtVendorPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtVendorAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnSavePrint)
                    .addComponent(btnSave))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable1PropertyChange
    }//GEN-LAST:event_jTable1PropertyChange

    private void jTable1VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jTable1VetoableChange
    }//GEN-LAST:event_jTable1VetoableChange

    private void txtVendorIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVendorIDFocusLost
        int Id = -1;
        try {
            Id = Integer.parseInt(getTxtVendorID().getText());
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid Vendor ID");
            txtVendorName.setText("");
            txtVendorPhone.setText("");
            txtVendorAddress.setText("");
        }
        
        if(Id == -1)
            return;
        
        Statement stmt = DatabaseConnection.getStatement();
        String query = String.format("Select Vendors.Name AS Name, Vendors.street AS Street, Vendors.City AS City, Vendors.Phone AS Phone FROM Vendors WHERE Vendors.ID = %d;", Id);
        try{
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            txtVendorName.setText(rs.getString("Name"));
            txtVendorPhone.setText(rs.getString("Phone"));
            txtVendorAddress.setText(rs.getString("Street") + ", " + rs.getString("City"));
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Invalid Vendor ID");
            txtVendorName.setText("");
            txtVendorPhone.setText("");
            txtVendorAddress.setText("");
        }
        
    }//GEN-LAST:event_txtVendorIDFocusLost

    private void txtVendorIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVendorIDKeyPressed
    }//GEN-LAST:event_txtVendorIDKeyPressed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose(); 
    }//GEN-LAST:event_btnCancelActionPerformed
    public void save() {
        int rowCount = jTable1.getRowCount();
        
        try {
            int vid = -1;

            Statement stmt = DatabaseConnection.getStatement();
            Statement upstmt = DatabaseConnection.getStatement();
            try {
                vid = Integer.parseInt(txtVendorID.getText());
                if(vid == -1) {
                    JOptionPane.showMessageDialog(null, "Invalid Vendor ID");
                return;
            }
                String query = String.format("Select Vendors.Name AS Name FROM Vendors WHERE Vendors.ID = %d;", vid);
                ResultSet rs = stmt.executeQuery(query);
                rs.next();
                rs.getString("Name");
                
            } catch(Exception se) {
                JOptionPane.showMessageDialog(null, "Invalid Vendor ID");
                return;
            }
            int on = Integer.parseInt(txtOrderNumber.getText());
            String od = txtOrderDate.getText();
            double total = Double.parseDouble(getLblTotal().getText());
            
            for(int i = 0; i < rowCount; i++) {
                String strPid = String.valueOf(jTable1.getValueAt(i, 0));
                String strPqty = String.valueOf(jTable1.getValueAt(i, 2));
                String strPst = String.valueOf(jTable1.getValueAt(i, 3));
                if(strPid.matches("null") || strPqty.matches("null"))
                    continue;
                
                int pid  = Integer.parseInt(strPid);
                int pqty = Integer.parseInt(strPqty);
                double pst  = Double.parseDouble(strPst);
                ResultSet rs = upstmt.executeQuery("SELECT Products.quantity AS ProID FROM Products WHERE Products.productID = " + pid + ";");
                rs.next();
                int quantity = rs.getInt("ProID");
                quantity += pqty;
                upstmt.executeUpdate("UPDATE Products SET Products.quantity = " + quantity + " WHERE Products.productID = " + pid + ";");
                String q1 = String.format("INSERT INTO purchaseorders(OrderNumber, ProductID, Quantity, SubTotal) VALUES(%d, %d, %d, %f);", on, pid, pqty, pst);

                stmt.addBatch(q1);
            }
            int arr[] = stmt.executeBatch();
            if(arr.length > 0) {
                String q2 = String.format("INSERT INTO purchaseordernumber(VendorID, Date, Total) Values(%d, '%s', %f);", vid, od, total);
                stmt.executeUpdate(q2);
            }
            JOptionPane.showMessageDialog(null, arr.length + " Records Added to the Database");
            dispose();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        save();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSavePrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSavePrintActionPerformed
        save();
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        try {
            //if(job.printDialog())
                job.print();
        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(null, "Unable to Print");
        }
    }//GEN-LAST:event_btnSavePrintActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PurchaseOrderForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSavePrint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTextField txtOrderDate;
    private javax.swing.JTextField txtOrderNumber;
    private javax.swing.JTextField txtVendorAddress;
    private javax.swing.JTextField txtVendorID;
    private javax.swing.JTextField txtVendorName;
    private javax.swing.JTextField txtVendorPhone;
    // End of variables declaration//GEN-END:variables

    public void setTxtOrderNumber(int x) {
        txtOrderNumber.setText(String.valueOf(x));
    }

    public JTextField getTxtOrderDate() {
        return txtOrderDate;
    }

    public JTextField getTxtOrderNumber() {
        return txtOrderNumber;
    }

    public JLabel getLblTotal() {
        return lblTotal;
    }

    public JTextField getTxtVendorID() {
        return txtVendorID;
    }

    public void setLblTotal(Double t) {
        this.lblTotal.setText(String.valueOf(t));
    }
}
