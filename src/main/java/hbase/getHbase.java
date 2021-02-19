package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.sql.DriverManager;
import java.sql.Statement;

public class getHbase {
    public static void main(String[] args){
        try {
            // 连接postgresql
            java.sql.Connection connectionSql = null;
            Statement statement = null;
            String url = "jdbc:postgresql://127.0.01:5432/postgres";
            String user = "postgres";
            String password = "wzx670905";
            Class.forName("org.postgresql.Driver");
            connectionSql= DriverManager.getConnection(url, user, password);
            connectionSql.setAutoCommit(true);
            statement = connectionSql.createStatement();

            // 连接hbase
            Configuration conf = new Configuration();
            conf.set("hbase.zookeeper.quorum","192.168.175.234:2181,192.168.175.235:2181,192.168.175.236:2181");
            Connection connection = ConnectionFactory.createConnection(conf);
            Table table = connection.getTable(TableName.valueOf("111000:ts-202108"));
            Scan scan = new Scan();
            ResultScanner resultScanner = table.getScanner(scan);
            for (Result result : resultScanner){
//                System.out.println("row1:" + result.toString());
                for (Cell cell : result.listCells()){
//                    System.out.println("value:" + Bytes.toString(cell.getValueArray()));
//                    System.out.println("family:"+ Bytes.toString(cell.getFamilyArray()) );
                    Long time = cell.getTimestamp();
                    System.out.println("time:"+ cell.getTimestamp());
                    String sql ="INSERT INTO time_hbase(\"time_end\") VALUES (" + time + ")" ;
                    statement.executeUpdate(sql);
                }
//                for (Cell cell1 : result.rawCells()){
//                    System.out.println("time:"+ cell1.getTimestamp());
//                    System.out.println("time:"+ new String( cell1.getValueArray()));
//                }
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
