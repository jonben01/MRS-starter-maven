package dk.easv.mrs.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;

public class MyDBConnector {

    private SQLServerDataSource dataSource;

    public MyDBConnector() {
        dataSource = new SQLServerDataSource();
        //LOKAL SERVER, MEN DEN FINDES IKKE :)
        dataSource.setServerName("127.0.0.1");
        dataSource.setDatabaseName("MRS");
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setPortNumber(1433);


    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

    public static void main(String[] args) throws SQLException {
        MyDBConnector dBConnector = new MyDBConnector();
       try (Connection connection = dBConnector.getConnection()) {
           System.out.println("is it open" + !connection.isClosed());
       }
    }
}
