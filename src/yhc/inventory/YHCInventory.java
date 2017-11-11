package yhc.inventory;


import java.sql.SQLException;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class YHCInventory extends JFrame {
    
    public static void main(String[] args) {
         try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
                
        MainWindow win = new MainWindow();       
        win.setResizable(false);

        win.setVisible(true);
        LoginForm panel = new LoginForm();
        
        do {
            UIManager UI = new UIManager();
            UI.put("OptionPane.background",ColorUIResource.WHITE);
            UI.put("Panel.background",ColorUIResource.WHITE);
            int option = JOptionPane.showConfirmDialog(win, panel, "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(option != JOptionPane.OK_OPTION) {
                break;
            }
            
            String strUser = panel.getUserName();
            String strPass = panel.getPassword();
            try {
                DatabaseConnection connection = new DatabaseConnection(strUser, strPass);
            } catch(SQLException e) {
                panel.setWorngLabel("Usernam/Password is Incorrect");
            } catch(ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Class Not Found Exception: " + e.toString());
            }
        } while(!DatabaseConnection.isDatabaseConnected());
    }
    

}