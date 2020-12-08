package in.co.rays.util;

import java.sql.Connection;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

// TODO: Auto-generated Javadoc
/**
 * JDBC DataSource is a Data Connection Pool.
 *
 * @author 
 */

public class JDBCDataSource {

    /** JDBC Database connection pool ( DCP ). */
    private static JDBCDataSource datasource;

    /**
     * Instantiates a new JDBC data source.
     */
    private JDBCDataSource() {
    }

    /** The cpds. */
    private ComboPooledDataSource cpds = null;

    /**
     * Create instance of Connection Pool.
     *
     * @return single instance of JDBCDataSource
     */
    public static JDBCDataSource getInstance() {
        if (datasource == null) {

            ResourceBundle rb = ResourceBundle
                    .getBundle("in.co.rays.bundle.system");

            datasource = new JDBCDataSource();
            
            datasource.cpds = new ComboPooledDataSource();
            try {
                datasource.cpds.setDriverClass(rb.getString("driver"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            datasource.cpds.setJdbcUrl(rb.getString("url"));
           
            datasource.cpds.setUser(rb.getString("username"));
            
            datasource.cpds.setPassword(rb.getString("password"));
            
            datasource.cpds.setInitialPoolSize(new Integer((String) rb
                    .getString("initialPoolSize")));
            
            datasource.cpds.setAcquireIncrement(new Integer((String) rb
                    .getString("acquireIncrement")));
            
            datasource.cpds.setMaxPoolSize(new Integer((String) rb
                    .getString("maxPoolSize")));
            
            datasource.cpds.setMaxIdleTime(DataUtility.getInt(rb.getString("timeout")));
            
            datasource.cpds.setMinPoolSize(new Integer((String) rb
                    .getString("minPoolSize")));

        }
        return datasource;
    }

    /**
     * Gets the connection from ComboPooledDataSource.
     *
     * @return connection
     * @throws Exception the exception
     */
    public static Connection getConnection() throws Exception {
        
        return getInstance().cpds.getConnection();
    }

    /**
     * Closes a connection.
     *
     * @param connection the connection
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            System.out.println("Connection Close");
            try {
                connection.close();
                System.out.println("Connection Close Done");
            } catch (Exception e) {
            }
        }
        
        
        
    }

}
