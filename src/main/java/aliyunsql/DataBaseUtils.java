package aliyunsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseUtils {
    String connection = "jdbc:mysql://39.98.77.89:13306/timeseries_manage";
    String user = "root";
    String password = "ZMJ@YYDK.67891289";

    /**
     *
     * @param connectionUrl 数据库连接信息
     * @param user 用户名
     * @param password 密码
     * @return SQL预加载语句
     */
    public static Statement getStatement(String connectionUrl, String user, String password) throws SQLException {
        try(Connection connection = DriverManager.getConnection(connectionUrl, user, password);
        Statement statement = connection.createStatement()){
            return statement;
        }
    }

}
