
package controller;



//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import conexion.Conexion;
import view.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Controller implements ActionListener{
    private final View vista;

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource()== vista.btn1){
            informe1();
           
            
        }
        if (e.getSource()== vista.btn2){
            informe2();
            
        }
        if (e.getSource() == vista.btn3){
            informe3();
        }
        
    }
    
    public Controller(){
        this.vista = new View();
    }
    
    public void inicio(){
        this.vista.setVisible(true);
        this.vista.setLocationRelativeTo(null);
        this.vista.setTitle("Reto 5 By: Emanuel Montilla Villanueva");
        this.vista.setResizable(false);
        this.vista.btn1.addActionListener(this);
        this.vista.btn2.addActionListener(this);
        this.vista.btn3.addActionListener(this);
        DefaultTableModel t_vacia = new DefaultTableModel();
        t_vacia.setRowCount(0);
        this.vista.jTable1.setModel(t_vacia);
        salir(vista);
    }
    
    public void close(JFrame ver){
        if (JOptionPane.showConfirmDialog(ver, "¿Seguro que deseas salir?","Salir",JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
            ver.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            System.exit(0);
            
        }
    }
    
    public void salir (JFrame ver){
        ver.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        
        ver.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent event){
            close(ver);
                //System.out.println("cerrado2");
            }
        }   );
        
    }
    
    public void informe1(){
        String[] nombreColumnas = {"ID del Lider","Nombre","Primer Apellido","Ciudad"};
        String[] registros = new String[4];
        DefaultTableModel model = new DefaultTableModel(null,nombreColumnas);
        String sql = "SELECT ID_Lider, Nombre, Primer_Apellido, Ciudad_residencia from Lider \n" +
        "order by Ciudad_Residencia asc;";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            con = Conexion.conexion1();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                registros[0] = rs.getString("ID_lider");
                registros[1] = rs.getString("Nombre");
                registros[2] = rs.getString("Primer_Apellido");
                registros[3] = rs.getString("Ciudad_Residencia");
                model.addRow(registros);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "No hay resultados");
            //System.out.println(e.getMessage());
        }finally{
           try{
                if (rs != null){
                rs.close();
                }
                if (pst != null){
                pst.close();
                }
                if (con != null){
                con.close();
                }
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, e);
                }     
        
        }
        vista.jTable1.setModel(model);
        vista.jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        if (vista.jTable1.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data Base Vacia");
        }
    }
    
    
    public void informe2(){
        String[] nombreColumnas = {"ID del Proyecto","Constructora","Numero de Habitaciones","Ciudad"};
        String[] registros = new String[4];
        DefaultTableModel model = new DefaultTableModel(null,nombreColumnas);
        String sql = "SELECT ID_proyecto, constructora, numero_habitaciones, ciudad from Proyecto \n" +
        "where Clasificacion = 'Casa Campestre' and Ciudad  in ('Santa Marta','Cartagena','Barranquilla');";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = Conexion.conexion1();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                registros[0] = rs.getString("ID_Proyecto");
                registros[1] = rs.getString("Constructora");
                registros[2] = rs.getString("Numero_Habitaciones");
                registros[3] = rs.getString("Ciudad");
                model.addRow(registros);
            }
        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(null, "No hay resultados");
        } finally {
            try {
                if (rs != null){
                    rs.close();
                }
                if (pst != null){
                    pst.close();
                }
                if (con != null){
                    con.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        vista.jTable1.setModel(model);
        vista.jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        if (vista.jTable1.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data Base Vacia");
        }
    }
    
    public void informe3(){
        String[] nombreColumnas = {"ID de la Compra","Constructora","Banco Vinculado"};
        String[] registros = new String[3];
        DefaultTableModel model = new DefaultTableModel(null, nombreColumnas);
        String sql = "SELECT ID_compra, constructora, banco_vinculado from Compra c \n" +
        "join Proyecto p on c.ID_Proyecto = p.ID_Proyecto \n" +
        "where c.Proveedor = 'Homecenter'and p.Ciudad = 'Salento';";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = Conexion.conexion1();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                registros[0] = rs.getString("ID_Compra");
                registros[1] = rs.getString("Constructora");
                registros[2] = rs.getString("Banco_Vinculado");
                model.addRow(registros);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay resultados");
        } finally {
            try {
                if(rs != null){
                    rs.close();
                }
                if (pst != null){
                    pst.close();
                }
                if (con != null){
                    con.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        vista.jTable1.setModel(model);
        vista.jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        if (vista.jTable1.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data Base Vacia");
        }
    }
}
