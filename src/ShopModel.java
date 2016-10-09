import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShopModel {
    public static Connection c = null;
    public static Statement stateMnt=null;
    public static int total;
    public static int ID;
    public static int ArrrayCheck=0;
    public static int updateID;
    public static String image;
    public int Check=0;
    public static String[] filePath;
    public static int[] filePathID;
    public static void createDB(){
        try {
            Class.forName("org.sqlite.JDBC");
            //Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");

            stateMnt = c.createStatement();
            String sql = "CREATE TABLE FLOWER" +
                    "(ID INT PRIMARY KEY   NOT NULL," +
                    "NAME    TEXT    NOT NULL," +
                    "QUANTITY   INT   NOT NULL)";
            stateMnt.executeUpdate(sql);

            sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES (1,'Carnation',15)";
            stateMnt.executeUpdate(sql);
            sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES (2,'Chrysanthemum',22)";
            stateMnt.executeUpdate(sql);
            sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES (3,'Daffodil',8)";
            stateMnt.executeUpdate(sql);
            sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES (4,'Dahlia',32)";
            stateMnt.executeUpdate(sql);
            sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES (5,'Delphinium',26)";
            stateMnt.executeUpdate(sql);
            sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES (6,'Gardenia',11)";
            stateMnt.executeUpdate(sql);
            sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES (7,'Hydrangea',3)";
            stateMnt.executeUpdate(sql);
            sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES (8,'Iris',20)";
            stateMnt.executeUpdate(sql);
            sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES (9,'Orchid',17)";
            stateMnt.executeUpdate(sql);
            sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES (10,'Pear Blossom',29)";
            stateMnt.executeUpdate(sql);
            sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES (11,'Rose',16)";
            stateMnt.executeUpdate(sql);
            sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES (12,'Rose Spray',17)";
            stateMnt.executeUpdate(sql);
            sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES (13,'Snapdragon',26)";
            stateMnt.executeUpdate(sql);
            sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES (14,'Solidaster',1)";
            stateMnt.executeUpdate(sql);
            sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES (15,'Statice',3)";
            stateMnt.executeUpdate(sql);
            sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES (16,'Stephanotis',30)";
            stateMnt.executeUpdate(sql);
            sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES (17,'Stock',25)";
            stateMnt.executeUpdate(sql);
            sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES (18,'Sunflower',9)";
            stateMnt.executeUpdate(sql);
            sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES (19,'Tulip',13)";
            stateMnt.executeUpdate(sql);
            sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES (20,'Viburnum',12)";
            stateMnt.executeUpdate(sql);
            sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES (21,'Waxflower',33)";
            stateMnt.executeUpdate(sql);
            

        } catch ( ClassNotFoundException | SQLException e ) {
            //System.err.println( "OK");
        }
    }
    
    public static void createItem(int x, String y, int z){
        try {
            Class.forName("org.sqlite.JDBC");
            //Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");

            stateMnt = c.createStatement();

            String sql = "INSERT INTO FLOWER (ID,NAME,QUANTITY)" +
                    "VALUES ("+ x +",'"+ y +"',"+ z +")";
            stateMnt.executeQuery(sql);
            
        } catch ( ClassNotFoundException | SQLException e ) {
            System.err.println("Not Working");
        }
    }
}