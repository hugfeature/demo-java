package aliyunsql;

import java.sql.*;

public class DataBaseInsert {
    public static void main(String args[]) {
        // String connectionUrl= "jdbc:mysql://<Host>:<Port>/<myDatabase>";
        String connectionUrl= "jdbc:mysql://39.98.77.89:13306/timeseries_manage";
        ResultSet resultSet;

        try (Connection connection= DriverManager.getConnection(connectionUrl,"root","ZMJ@YYDK.67891289");
             Statement statement = connection.createStatement()) {
            //输入希望执行的SQL。
//            String selectSql = "SELECT * FROM t_workface_info";
            String insertSql = "INSERT INTO t_workface_grade_day (`id`, `agg_type`, `province`, `namespace`, `workface`, `date`, `grade`) VALUES (1, 1, 100000, NULL, '0010010001', '2021-05-11', 80.37)";
//            resultSet = statement.executeQuery(insertSql);
            statement.executeUpdate(insertSql);
//            while (resultSet.next()) {
////                System.out.println(resultSet.getString("name"));
//                System.out.println(resultSet);
//            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
