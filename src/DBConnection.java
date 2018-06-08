import java.sql.Connection;
import java.sql.Statement;

public class DBConnection {
    public Statement stmt = null;
    public Connection conn = null;

    public DBConnection (Statement stmt, Connection conn){
        this.stmt = stmt;
        this.conn = conn;
    }
}
