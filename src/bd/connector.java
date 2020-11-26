package bd;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.*;
import java.util.ArrayList;

public class connector {
    private final String serverName = "localhost";
    private final String database = "jnaves";
    private final String user = "root";
    private final String password = "";
    
    private MysqlDataSource getDataSource() {
        MysqlDataSource mds = new MysqlDataSource();
        mds.setServerName(serverName);
        mds.setDatabaseName(database);
        mds.setUser(user);
        mds.setPassword(password);
        
        return mds;
    }
    
    public void RegistrarMarcador(Marcador m) {
        MysqlDataSource mds = getDataSource();
        
        try {
            Connection con = mds.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO marcadores VALUES (NULL,?,?)");
            ps.setInt(1, m.getPuntaje());
            ps.setString(2, m.getFecha());
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("El programa falló.");
            System.out.println(e.toString());
        }
    }
    
    public ArrayList<Marcador> ListarMarcadores() {
        ArrayList<Marcador> lista = new ArrayList<>();
        
        MysqlDataSource mds = getDataSource();        
        try {
            Connection con = mds.getConnection();
            Statement s = con.createStatement();            
            ResultSet rs = s.executeQuery("SELECT * FROM marcadores");
            while (rs.next()) {
                Marcador m = new Marcador();
                m.setCodigo(rs.getInt("codigo"));
                m.setPuntaje(rs.getInt("puntaje"));
                m.setFecha(rs.getString("fecha"));
                lista.add(m);
            }
          
        } catch (Exception e) {
            System.out.println("El programa falló.");
            System.out.println(e.toString());
        }
        
        return lista;
    }
}
