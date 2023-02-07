import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Agenda {
    private JButton buscarButton;
    private JPanel Panel;
    private JTextField ced;
    private JTextField nom;
    private JTextField cel;
    private JTextField ema;

    Statement ps;

    public Agenda() {
    buscarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Connection con;

            try{
               con = getConection();
               ps = con.createStatement();
               ResultSet rs;
               rs=ps.executeQuery("select * from INF where cedula="+ced.getText()+";");
               while (rs.next()){
                   nom.setText(rs.getString("NOMBRE"));
               }
            }catch (Exception s){

            }
        }
    });
    }
    public static Connection getConection(){
        Connection con = null;
        String base= "INGRESODATOS";
        String url = "jdbc:mysql://localhost:3306/" + base;
        String user = "root";
        String password = "Pelota2002";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,password);
        }catch (ClassNotFoundException | SQLException e){
            System.err.println(e);
        }
        return con;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("insertar");
        frame.setContentPane(new Agenda().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
