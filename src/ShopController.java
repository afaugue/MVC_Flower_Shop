/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ShopController {
    ShopView view;
    private ListSelectionListener quantity;
    private ActionListener add;
    private ActionListener sub;
    private ActionListener exit;
    private ActionListener addItem;
    private ActionListener addPicture;
    
    
    public ShopController() throws SQLException{
        init();
    }
    
    public final void init() throws SQLException{
        view=new ShopView();
        setActions();
        view.setVisible(true);
        try {
            Class.forName("jdbc:sqlite:test.db");
        } catch (ClassNotFoundException w) {
            
        }
        ShopModel.createDB();
        ShopModel.c = DriverManager.getConnection("jdbc:sqlite:test.db");
        ShopModel.stateMnt=ShopModel.c.createStatement();
        ResultSet rs= ShopModel.stateMnt.executeQuery("SELECT * FROM FLOWER;");
        while(rs.next()){
            String name= rs.getString("name");
            view.lstFlower.addElement(name);
            ShopModel.updateID=rs.getRow();
            System.out.print(rs.getRow()+"  ");
        }
        view.setList();
//        rs.last();
//        ShopModel.updateID=rs.getRow()+1;
        ShopModel.c.setAutoCommit(true);
    }
    
    public void setActions() throws SQLException{
        quantity=new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                int x;
                try {
                    x=view.getPosition()+1;
                    System.out.print(x+"  ");
                    ResultSet rs= ShopModel.stateMnt.executeQuery("SELECT * FROM FLOWER WHERE ID="+ x +";");
                    int y=rs.getInt("quantity");
                    ShopModel.image=rs.getString("name");
                    ShopModel.total=y;
                    ShopModel.ID=x;
                    view.setTxt(y);
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
                }
                String j;
                j=ShopModel.image+".jpg";
                File file = new File("src/"+j);
                if (!file.exists()){
                    j="default.jpg";
                }
                view.setLabl(j);
            }
        };
        add=new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    int plus= ShopModel.total+1;
                    ShopModel.c.setAutoCommit(false);

                    ShopModel.stateMnt = ShopModel.c.createStatement();
                    String sql = "UPDATE FLOWER SET QUANTITY = "+ plus + " WHERE ID="+ShopModel.ID+";";
                    ShopModel.stateMnt.executeUpdate(sql);
                    ShopModel.c.commit();

                    ShopModel.stateMnt = ShopModel.c.createStatement();
                    int y=plus;
                    ShopModel.total=plus;
                    view.setTxt(y);
                    ResultSet tw = ShopModel.stateMnt.executeQuery("SELECT * FROM FLOWER  WHERE ID="+ ShopModel.ID +";");
                    int ok=tw.getInt("quantity");
                    tw.close();   
                } catch (SQLException ex) {
                    Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        };
        sub=new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    int plus= ShopModel.total-1;
                    ShopModel.c.setAutoCommit(false);

                    ShopModel.stateMnt = ShopModel.c.createStatement();
                    String sql = "UPDATE FLOWER SET QUANTITY = "+ plus + " WHERE ID="+ShopModel.ID+";";
                    ShopModel.stateMnt.executeUpdate(sql);
                    ShopModel.c.commit();

                    ShopModel.stateMnt = ShopModel.c.createStatement();
                    int y=plus;
                    ShopModel.total=plus;
                    view.setTxt(y);
                    ResultSet tw = ShopModel.stateMnt.executeQuery("SELECT * FROM FLOWER  WHERE ID="+ ShopModel.ID +";");
                    int ok=tw.getInt("quantity");
                    tw.close();   
                } catch (SQLException ex) {
                    Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        };
        exit=new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        };
        addItem=new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String test1=JOptionPane.showInputDialog("Please enter flower name: ");
                String test2=JOptionPane.showInputDialog("Please enter flower quantity: ");
                int num = Integer.parseInt(test2);
//                JFileChooser saveFile = new JFileChooser();
//                saveFile.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "tif"));
//                saveFile.setAcceptAllFileFilterUsed(false);
//                saveFile.showOpenDialog(null);
//                File f = saveFile.getSelectedFile();
                ShopModel.updateID++;
//                ShopModel.filePath[0]=f.getAbsolutePath();
//                ShopModel.filePathID[0]=ShopModel.updateID;
//                ShopModel.ArrrayCheck++;
                try {
                    ShopModel.c.setAutoCommit(false);
                    Class.forName("org.sqlite.JDBC");
                    //Class.forName("com.mysql.jdbc.Driver");
                    ShopModel.c = DriverManager.getConnection("jdbc:sqlite:test.db");

                    ShopModel.stateMnt = ShopModel.c.createStatement();

                    String sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                            "VALUES ("+ ShopModel.updateID +",'"+ test1 +"',"+ num +")";
                    ShopModel.stateMnt.executeQuery(sql);

                } catch ( ClassNotFoundException | SQLException e ) {
                    System.err.println("Not Working");
                }
//                ShopModel.createItem(ShopModel.updateID, test1, num);
                view.lstFlower.addElement(test1);
                view.setList();
            }
        };
        addPicture=new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    int plus= view.getTxt();
                    ShopModel.c.setAutoCommit(false);

                    ShopModel.stateMnt = ShopModel.c.createStatement();
                    String sql = "UPDATE FLOWER SET QUANTITY = "+ plus + " WHERE ID="+ShopModel.ID+";";
                    ShopModel.stateMnt.executeUpdate(sql);
                    ShopModel.c.commit();

                    ShopModel.stateMnt = ShopModel.c.createStatement();
                    int y=plus;
                    ShopModel.total=plus;
                    view.setTxt(y);
                    ResultSet tw = ShopModel.stateMnt.executeQuery("SELECT * FROM FLOWER  WHERE ID="+ ShopModel.ID +";");
                    int ok=tw.getInt("quantity");
                    tw.close();   
                } catch (SQLException ex) {
                    Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        };
        
        view.addQuantityListener(quantity);
        view.addAddListener(add);
        view.addSubListener(sub);
        view.addExitListener(exit);
        view.addItemListener(addItem);
        view.addEnterListener(addPicture);
    }
    
    public static void main(String[] args) throws SQLException{
        ShopController shop = new ShopController();
    }
}