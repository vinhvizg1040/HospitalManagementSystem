package hospitalmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    public static Connection connectDB() {

        Connection connect = null;
        try {
            // Load JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Kết nối đến cơ sở dữ liệu
            connect = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=hospital;trustServerCertificate=true",
                    "sa",
                    "1"
            );
            System.out.println(">>>>>>>>>>>>> ket noi database thanh cong");

        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>> ket noi database thất bại");

            e.printStackTrace();

        }
//        return null;
        return connect;
    }
}
