/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbcFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.swing.JOptionPane;
import view.Inicio;

/**
 *
 * @author CDM-51
 */
public class ConnectionFactory {
    
    private final DataSource dataSource;
     
     public ConnectionFactory() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel?useTimeZone=true&serverTimeZone=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("71-42@(01)#");
        comboPooledDataSource.setMaxPoolSize(10);
        this.dataSource = comboPooledDataSource;
    }
     
     
      public Connection realizarConexion() {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al conectar con la Base de Datos de MySQL, inténtelo "
                    + "más tarde.",
                    "Error en la conexión :(",
                    JOptionPane.ERROR_MESSAGE
            );
            Inicio menuPrincipal = new Inicio();
            menuPrincipal.setVisible(true);
            throw new RuntimeException(e);
        }
    }
}
