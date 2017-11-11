package yhc.inventory;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputListener;


public class MainWindow extends JFrame{
    
    private JLabel     vendorIcon;
    private JLabel     newVendor;
    private JLabel     vendorList;
    private JPanel     vendorPanel;
    
    private JLabel     purchaseOrderIcon;
    private JLabel     newPurchaseOrder;
    private JLabel     purchaseOrderList;
    private JPanel     purchaseOrderPanel;
    
    private JLabel     salesOrderIcon;
    private JLabel     newSalesOrder;
    private JLabel     salesOrderList;
    private JPanel     salesOrderPanel;
    
    private JLabel     inventoryIcon;
    private JLabel     newProduct;
    private JLabel     productList;
    private JLabel     reorderStock;
    private JLabel     adjustStock;
    private JLabel     currentStock;
    private JPanel     upperProductPanel;
    private JPanel     lowerProductPanel;

    private JLabel     customerIcon;
    private JLabel     newCustomer;
    private JLabel     customerList;
    private JPanel     customerPanel;
    
    private JLabel     lblCompanyName;
    private JLabel     lblCredit;
    private JLabel     lblInventoryStatus;
    private JPanel     background;
    private Dimension  screenSize;    
    private MouseEventHandler mouseEventHandler;
    
    
    public JLabel getNewProduct() {
        return newProduct;
    }

    public JLabel getProductList() {
        return productList;
    }

    public JLabel getAdjustStock() {
        return adjustStock;
    }

    public JLabel getCurrentStock() {
        return currentStock;
    }

    public JLabel getReorderStock() {
        return reorderStock;
    }

    public JPanel getUpperProductPanel() {
        return upperProductPanel;
    }
    

    public JPanel getLowerProductPanel() {
        return lowerProductPanel;
    }
    
    public JLabel getNewSalesOrder() {
        return newSalesOrder;
    }

    public JLabel getSalesOrderList() {
        return salesOrderList;
    }

    public JPanel getSalesOrderPanel() {
        return salesOrderPanel;
    }
    
    public JLabel getNewPurchaseOrder() {
        return newPurchaseOrder;
    }

    public JLabel getPurchaseOrderList() {
        return purchaseOrderList;
    }

    public JPanel getPurchaseOrderPanel() {
        return purchaseOrderPanel;
    }
    
    public JLabel getInventoryIcon() {
        return inventoryIcon;
    }

    public JLabel getPurchaseOrderIcon() {
        return purchaseOrderIcon;
    }

    public JLabel getSalesOrderIcon() {
        return salesOrderIcon;
    }
    
    public JLabel getNewCustomer() {
        return newCustomer;
    }

    public JLabel getNewVendor() {
        return newVendor;
    }

    public JLabel getCustomerList() {
        return customerList;
    }

    public JLabel getVendorList() {
        return vendorList;
    }

    public JLabel getCustomerIcon() {
        return customerIcon;
    }

    public JLabel getVendorIcon() {
        return vendorIcon;
    }
    
    public JPanel getCustomerPanel() {
    return customerPanel;
}

    public JPanel getVendorPanel() {
        return vendorPanel;
    }

    
    public MainWindow() {
        setTitle("Inventory Management System.");
        setSize(800, 600);

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (screenSize.width-w)/2;
        int y = (screenSize.height-h)/2;
        background = new BackgroundPanel();
        background.setBackground(Color.WHITE);
        background.setBounds(0, 0, 800, 600);
    
        this.setLocation(x, y);
        this.setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mouseEventHandler = new MouseEventHandler();
        
        lblCompanyName = new JLabel("Inventory Management System.");
        lblCompanyName.setFont(new Font("Magneto", Font.BOLD, 24));
        lblCompanyName.setForeground(Color.BLACK);
        lblCompanyName.setBounds(260, 20, 300, 20);
        
        lblCredit = new JLabel("<html>Developed By: <b>Muhammad Saqib</b><br />E-Mail: msaqib52@gmail.com</html>");
        lblCredit.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblCredit.setForeground(Color.BLACK);
        lblCredit.setBounds(600, 540, 300, 25);
               
        lblInventoryStatus = new JLabel("Inventory Status :  Normal");
        lblInventoryStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblInventoryStatus.setForeground(Color.BLACK);
        lblInventoryStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblInventoryStatus.setSize(300, 35);
        lblInventoryStatus.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lblInventoryStatus.setBounds(260, 55, 300, 35);
        
        vendorIcon = new JLabel(new ImageIcon(getClass().getResource("vendor.jpg")));
        vendorIcon.setBounds(20, 325, 83, 114);
        vendorIcon.addMouseMotionListener(mouseEventHandler);
        vendorIcon.addMouseListener(mouseEventHandler);
        
        newVendor  = new JLabel("<html><b>New Vendor</b><br />Enter a new Vendor to buy From</html>");
        newVendor.addMouseListener(mouseEventHandler);       

        vendorList = new JLabel("<html><b>Vendor List</b><br />View or update your Vendors</html>");
        vendorList.addMouseListener(mouseEventHandler);
        
        vendorPanel = new JPanel(new GridLayout(2, 1));
        vendorPanel.add(newVendor);
        vendorPanel.add(vendorList);
        vendorPanel.setBounds(25, 440, 100, 114);
        vendorPanel.setBackground(Color.WHITE);

        purchaseOrderIcon = new JLabel(new ImageIcon(getClass().getResource("purchaseorder.jpg")));
        purchaseOrderIcon.setBounds(160, 330, 120, 103);
        purchaseOrderIcon.addMouseListener(mouseEventHandler);
        purchaseOrderIcon.addMouseMotionListener(mouseEventHandler);
        
        newPurchaseOrder = new JLabel("<html><b>New Order</b><br />Buy more stock from your vendors</html>");
        newPurchaseOrder.addMouseListener(mouseEventHandler);
        
        purchaseOrderList = new JLabel("<html><b>Order List</b><br />View or update past Purchase Orders</html>");
        purchaseOrderList.addMouseListener(mouseEventHandler);
        
        purchaseOrderPanel = new JPanel(new GridLayout(2, 1));
        purchaseOrderPanel.add(newPurchaseOrder);
        purchaseOrderPanel.add(purchaseOrderList);
        purchaseOrderPanel.setBounds(165, 430, 100, 114);
        purchaseOrderPanel.setBackground(Color.WHITE);

        inventoryIcon = new JLabel(new ImageIcon(getClass().getResource("inventory.jpg")));
        inventoryIcon.setBounds(345, 325, 105, 118);
        inventoryIcon.addMouseMotionListener(mouseEventHandler);
        inventoryIcon.addMouseListener(mouseEventHandler);
        
        newProduct = new JLabel("<html><b>New Product</b><br />Add a new type of product to track</html>");
        newProduct.addMouseListener(mouseEventHandler);
        
        productList = new JLabel("<html><b>Product List</b><br />View or update your product & inventory</html>");
        productList.addMouseListener(mouseEventHandler);

        lowerProductPanel = new JPanel(new GridLayout(2, 1));
        lowerProductPanel.add(newProduct);
        lowerProductPanel.add(productList);
        lowerProductPanel.setBounds(350, 442, 110, 114);
        lowerProductPanel.setBackground(Color.WHITE);
        
        reorderStock = new JLabel("<html><b>Reorder Stock</b><br />Reorder products that you're running out of</html>");
        reorderStock.addMouseListener(mouseEventHandler);
        
        adjustStock = new JLabel("<html><b>Adjust Stock</b><br />Change how much you have in stock</html>");
        adjustStock.addMouseListener(mouseEventHandler);
        
        currentStock = new JLabel("<html><b>Current Stock</b><br />See what you have in Stock</html>");
        currentStock.addMouseListener(mouseEventHandler);
        
        upperProductPanel = new JPanel(new GridLayout(3, 1));
        upperProductPanel.add(currentStock);
        upperProductPanel.add(adjustStock);
        upperProductPanel.add(reorderStock);
        upperProductPanel.setBounds(350, 160, 110, 170);
        upperProductPanel.setBackground(Color.WHITE);
        
        salesOrderIcon = new JLabel(new ImageIcon(getClass().getResource("salesorder.jpg")));
        salesOrderIcon.setBounds(525, 330, 90, 103);
        salesOrderIcon.addMouseListener(mouseEventHandler);
        salesOrderIcon.addMouseMotionListener(mouseEventHandler);
        
        newSalesOrder = new JLabel("<html><b>New Order</b><br />Sell something to one of your customers</html>");
        newSalesOrder.addMouseListener(mouseEventHandler);
        
        salesOrderList = new JLabel("<html><b>Order List</b><br />View or update past Sales Orders</html>");
        salesOrderList.addMouseListener(mouseEventHandler);
        
        salesOrderPanel = new JPanel(new GridLayout(2, 1));
        salesOrderPanel.add(newSalesOrder);
        salesOrderPanel.add(salesOrderList);
        salesOrderPanel.setBounds(530, 432, 110, 114);
        salesOrderPanel.setBackground(Color.WHITE);

        customerIcon = new JLabel(new ImageIcon(getClass().getResource("customer.jpg")));
        customerIcon.setBounds(690, 325, 100, 112);
        customerIcon.addMouseMotionListener(mouseEventHandler);
        customerIcon.addMouseListener(mouseEventHandler);
        
        newCustomer  = new JLabel("<html><b>New Customer</b><br />Add a new Customer to your list</html>");
        newCustomer.addMouseListener(mouseEventHandler);
        
        customerList = new JLabel("<html><b>Customer List</b><br />View or update your Customer list</html>");
        customerList.addMouseListener(mouseEventHandler);
        
        customerPanel = new JPanel(new GridLayout(2, 1));
        customerPanel.add(newCustomer);
        customerPanel.add(customerList);
        customerPanel.setBounds(690, 440, 100, 114);
        customerPanel.setBackground(Color.WHITE);
        

        setLayout(null);
        background.setLayout(null);
        background.add(lblCompanyName);
        background.add(vendorIcon);
        background.add(customerIcon);
        background.add(purchaseOrderIcon);
        background.add(salesOrderIcon);
        background.add(inventoryIcon);
        background.add(vendorPanel);
        background.add(purchaseOrderPanel);
        background.add(customerPanel);
        //background.add(upperProductPanel);
        background.add(lowerProductPanel);
        background.add(salesOrderPanel);
        background.add(lblCredit);
        
        add(background);

        vendorPanel.setVisible(false);
        purchaseOrderPanel.setVisible(false);
        upperProductPanel.setVisible(false);
        lowerProductPanel.setVisible(false);
        customerPanel.setVisible(false);
        salesOrderPanel.setVisible(false);
    }
    
    public class MouseEventHandler implements MouseInputListener {
        
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() == getNewVendor()) {
                if(!DatabaseConnection.isDatabaseConnected()) {
                    JOptionPane.showMessageDialog(null, "Database not Connected");
                    return;
                }
                
                VendorForm form = new VendorForm();
                
                Statement stmt = DatabaseConnection.getStatement();
                int d;
                try {
                    ResultSet rs = stmt.executeQuery("SELECT Auto_increment FROM information_schema.tables WHERE table_name='Vendors';");
                    if(rs.next()) {
                        d = rs.getInt(1);
                        form.setSupplierNumber(d);
                    }
                } catch(SQLException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString());
                }
                
                form.setLocationRelativeTo(null);
                form.setVisible(true);
            } else if(e.getSource() == getVendorList()) {
                if(!DatabaseConnection.isDatabaseConnected()) {
                    JOptionPane.showMessageDialog(null, "Database not Connected");
                    return;
                }
                
                ListDialog vendorListDialog = new ListDialog("Vendors");
                vendorListDialog.setLocationRelativeTo(null);
                vendorListDialog.setVisible(true);                
            } else if(e.getSource() == getNewCustomer()) {
                if(!DatabaseConnection.isDatabaseConnected()) {
                    JOptionPane.showMessageDialog(null, "Database not Connected");
                    return;
                }
                
                CustomerForm form = new CustomerForm();
                
                Statement stmt = DatabaseConnection.getStatement();
                int d;
                try {
                    ResultSet rs = stmt.executeQuery("SELECT Auto_increment FROM information_schema.tables WHERE table_name='customers';");
                    if(rs.next()) {
                        d = rs.getInt(1);
                        form.setCustomerNumber(d);
                    }
                } catch(SQLException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString());
                }
                
                
                int w = form.getSize().width;
                int h = form.getSize().height;
                int x = (screenSize.width-w)/2;
                int y = (screenSize.height-h)/2;
                form.setLocation(x, y);                
                form.setVisible(true);
            } else if(e.getSource() == getCustomerList()) {
                if(!DatabaseConnection.isDatabaseConnected()) {
                    JOptionPane.showMessageDialog(null, "Database not Connected");
                    return;
                }
                
                ListDialog customerListDialog = new ListDialog("Customers");
                customerListDialog.setLocationRelativeTo(null);
                customerListDialog.setVisible(true); 
            } else if(e.getSource() == getNewProduct()) {
                if(!DatabaseConnection.isDatabaseConnected()) {
                    JOptionPane.showMessageDialog(null, "Database not Connected");
                    return;
                }
                
                ProductFrom productForm = new ProductFrom();
                
                Statement stmt = DatabaseConnection.getStatement();
                int d;
                try {
                    ResultSet rs = stmt.executeQuery("SELECT Auto_increment FROM information_schema.tables WHERE table_name='Products';");
                    if(rs.next()) {
                        d = rs.getInt(1);
                        productForm.setTxtProductID(d);
                    }
                } catch(SQLException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString());
                }
                productForm.setLocationRelativeTo(null);
                productForm.setVisible(true); 
            } else if(e.getSource() == getProductList()) {
                if(!DatabaseConnection.isDatabaseConnected()) {
                    JOptionPane.showMessageDialog(null, "Database not Connected");
                    return;
                }
                
                ProductListDialog productList = new ProductListDialog();
                productList.setLocationRelativeTo(null);
                productList.setVisible(true); 
            } else if(e.getSource() == getNewPurchaseOrder()) {
                if(!DatabaseConnection.isDatabaseConnected()) {
                    JOptionPane.showMessageDialog(null, "Database not Connected");
                    return;
                }
                
                PurchaseOrderForm purchaseOrderForm = new PurchaseOrderForm();
                Statement stmt = DatabaseConnection.getStatement();
                int d;
                try {
                    ResultSet rs = stmt.executeQuery("SELECT Auto_increment FROM information_schema.tables WHERE table_name='PurchaseOrderNumber';");
                    if(rs.next()) {
                        d = rs.getInt(1);
                        purchaseOrderForm.setTxtOrderNumber(d);
                    }
                } catch(SQLException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString());
                }
                
                Calendar cal = new GregorianCalendar();
                int month = cal.get(Calendar.MONTH)+1;
                int year = cal.get(Calendar.YEAR);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                purchaseOrderForm.getTxtOrderDate().setText(day + "/" + month + "/" + year);
                purchaseOrderForm.getTxtOrderDate().setEditable(false);
                purchaseOrderForm.getTxtOrderNumber().setEditable(false);
                purchaseOrderForm.setLocationRelativeTo(null);
                purchaseOrderForm.setVisible(true); 
            } else if(e.getSource() == getPurchaseOrderList()) {
                if(!DatabaseConnection.isDatabaseConnected()) {
                    JOptionPane.showMessageDialog(null, "Database not Connected");
                    return;
                }
                
                PurchaseOrderList puchaseOrderList = new PurchaseOrderList();
                puchaseOrderList.setLocationRelativeTo(null);
                puchaseOrderList.setVisible(true); 
            } else if(e.getSource() == getNewSalesOrder()) {
                if(!DatabaseConnection.isDatabaseConnected()) {
                    JOptionPane.showMessageDialog(null, "Database not Connected");
                    return;
                }
                
                SalesOrderForm saleOrderForm = new SalesOrderForm();
                Statement stmt = DatabaseConnection.getStatement();
                int d;
                try {
                    ResultSet rs = stmt.executeQuery("SELECT Auto_increment FROM information_schema.tables WHERE table_name='SalesOrderNumber';");
                    if(rs.next()) {
                        d = rs.getInt(1);
                        saleOrderForm.setTxtOrderNumber(d);
                    }
                } catch(SQLException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString());
                }
                
                Calendar cal = new GregorianCalendar();
                int month = cal.get(Calendar.MONTH)+1;
                int year = cal.get(Calendar.YEAR);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                saleOrderForm.getTxtOrderDate().setText(day + "/" + month + "/" + year);
                saleOrderForm.getTxtOrderDate().setEditable(false);
                saleOrderForm.getTxtOrderNumber().setEditable(false);
                saleOrderForm.setLocationRelativeTo(null);
                saleOrderForm.setVisible(true); 
            } else if(e.getSource() == getSalesOrderList()) {
                if(!DatabaseConnection.isDatabaseConnected()) {
                    JOptionPane.showMessageDialog(null, "Database not Connected");
                    return;
                }
                
                SalesOrderList salesOrderList = new SalesOrderList();
                salesOrderList.setLocationRelativeTo(null);
                salesOrderList.setVisible(true); 
            }
        } 

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if(e.getSource() == getVendorIcon() || e.getSource() == getPurchaseOrderIcon() || e.getSource() == getInventoryIcon() || e.getSource() == getSalesOrderIcon() || e.getSource() == getCustomerIcon())
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else if(e.getSource() == getNewVendor()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                getNewVendor().setText("<html><u><b>New Vendor</b><br />Enter a new Vendor to buy From</u></html>");
            } else if(e.getSource() == getVendorList()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                getVendorList().setText("<html><u><b>Vendor List</b><br />View or update your Vendors<u></html>");                
            } else if(e.getSource() == getNewCustomer()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                getNewCustomer().setText("<html><u><b>New Customer</b><br />Add a new Customer to your list</u></html>");
            } else if(e.getSource() == getCustomerList()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                getCustomerList().setText("<html><u><b>Customer List</b><br />View or update your Customer list</u></html>");
            } else if(e.getSource() == getNewPurchaseOrder()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                getNewPurchaseOrder().setText("<html><u><b>New Order</b><br />Buy more stock from your vendors</u></html>");
            } else if(e.getSource() == getPurchaseOrderList()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                getPurchaseOrderList().setText("<html><u><b>Order List</b><br />View or update past Purchase Orders</u></html>");
            } else if(e.getSource() == getNewProduct()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                getNewProduct().setText("<html><u><b>New Product</b><br />Add a new type of product to track</u></html>");
            } else if(e.getSource() == getProductList()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                getProductList().setText("<html><u><b>Product List</b><br />View or update your product & inventory</u></html>");
            } else if(e.getSource() == getNewSalesOrder()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                getNewSalesOrder().setText("<html><u><b>New Order</b><br />Sell something to one of your customers</u></html>");
            } else if(e.getSource() == getSalesOrderList()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                getSalesOrderList().setText("<html><u><b>Order List</b><br />View or update past Sales Orders</u></html>");
            } /*else if(e.getSource() == getReorderStock()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                getReorderStock().setText("<html><u><b>Reorder Stock</b><br />Reorder products that you're running out of</u></html>");
            } else if(e.getSource() == getAdjustStock()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                getAdjustStock().setText("<html><u><b>Adjust Stock</b><br />Change how much you have in stock</u></html>");
            } else if(e.getSource() == getCurrentStock()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                getCurrentStock().setText("<html><u><b>Current Stock</b><br />See what you have in Stock</u></html>");
            }*/
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(e.getSource() == getCustomerIcon() || e.getSource() == getVendorIcon() || e.getSource() == getPurchaseOrderIcon() || e.getSource() == getInventoryIcon() || e.getSource() == getSalesOrderIcon())
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            else if(e.getSource() == getNewVendor()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                newVendor.setText("<html><b>New Vendor</b><br />Enter a new Vendor to buy From</html>");
            } else if(e.getSource() == getVendorList()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                vendorList.setText("<html><b>Vendor List</b><br />View or update your Vendors</html>");
            } else if(e.getSource() == getNewCustomer()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                newCustomer.setText("<html><b>New Customer</b><br />Add a new Customer to your list</html>");
            } else if(e.getSource() == getCustomerList()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                customerList.setText("<html><b>Customer List</b><br />View or update your Customer list</html>");
            } else if(e.getSource() == getNewPurchaseOrder()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                getNewPurchaseOrder().setText("<html><b>New Order</b><br />Buy more stock from your vendors</html>");
            } else if(e.getSource() == getPurchaseOrderList()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                getPurchaseOrderList().setText("<html><b>Order List</b><br />View or update past Purchase Orders</html>");
            } else if(e.getSource() == getNewProduct()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                getNewProduct().setText("<html><b>New Product</b><br />Add a new type of product to track</html>");
            } else if(e.getSource() == getProductList()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                getProductList().setText("<html><b>Product List</b><br />View or update your product & inventory</html>");
            } else if(e.getSource() == getNewSalesOrder()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                getNewSalesOrder().setText("<html><b>New Order</b><br />Sell something to one of your customers</html>");
            } else if(e.getSource() == getSalesOrderList()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                getSalesOrderList().setText("<html><b>Order List</b><br />View or update past Sales Orders</html>");
            } /*else if(e.getSource() == getReorderStock()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                getReorderStock().setText("<html><b>Reorder Stock</b><br />Reorder products that you're running out of</html>");
            } else if(e.getSource() == getAdjustStock()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                getAdjustStock().setText("<html><b>Adjust Stock</b><br />Change how much you have in stock</html>");
            } else if(e.getSource() == getCurrentStock()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                getCurrentStock().setText("<html><b>Current Stock</b><br />See what you have in Stock</html>");
            } */
        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if(e.getSource() == getVendorIcon()) {
                getVendorPanel().setVisible(true);
                getCustomerPanel().setVisible(false);
                getPurchaseOrderPanel().setVisible(false);
                //getUpperProductPanel().setVisible(false);
                getLowerProductPanel().setVisible(false);
                getSalesOrderPanel().setVisible(false);
            } else if(e.getSource() == getPurchaseOrderIcon()) {
                getPurchaseOrderPanel().setVisible(true);
                getCustomerPanel().setVisible(false);
                getVendorPanel().setVisible(false);
                //getUpperProductPanel().setVisible(false);
                getLowerProductPanel().setVisible(false);
                getSalesOrderPanel().setVisible(false);
            } else if(e.getSource() == getInventoryIcon()) {
                getLowerProductPanel().setVisible(true);
                //getUpperProductPanel().setVisible(true);
                getPurchaseOrderPanel().setVisible(false);
                getCustomerPanel().setVisible(false);
                getVendorPanel().setVisible(false);
                getSalesOrderPanel().setVisible(false);
            }else if(e.getSource() == getSalesOrderIcon()) {
                getSalesOrderPanel().setVisible(true);
                //getUpperProductPanel().setVisible(false);
                getLowerProductPanel().setVisible(false);
                getPurchaseOrderPanel().setVisible(false);
                getCustomerPanel().setVisible(false);
                getVendorPanel().setVisible(false);
            } else if(e.getSource() == getCustomerIcon()) {
                getCustomerPanel().setVisible(true);
                getVendorPanel().setVisible(false);
                getPurchaseOrderPanel().setVisible(false);
                //getUpperProductPanel().setVisible(false);
                getLowerProductPanel().setVisible(false);
                getSalesOrderPanel().setVisible(false);
            } 
        }
    }  
}
