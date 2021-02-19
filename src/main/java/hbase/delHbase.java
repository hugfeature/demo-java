package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

public class delHbase {
    public static void main(String[] args){

        try {

            // 连接hbase
            Configuration conf = new Configuration();
            conf.set("hbase.zookeeper.quorum","192.168.175.234:2181,192.168.175.235:2181,192.168.175.236:2181");
            Connection connection = ConnectionFactory.createConnection(conf);
            //1. 定义表名称对象
            TableName tableName = TableName.valueOf("TS_CONVERT_META");

            //2. 获取表对象，通过连接获取表对象
            Table table = connection.getTable(tableName);
            //3.删除
            // 准备MQTT数据
            for (int i = 0; i < 100; i++){
                String rowKey = "JOB100:/zmj/test" + i;
                Delete delete= new Delete(Bytes.toBytes(rowKey));
                // 准备kafka数据
                rowKey = "JOB100:wzx_test" + i;
                Delete delete1= new Delete(Bytes.toBytes(rowKey));
                table.delete(delete);
                table.delete(delete1);
            }

            table.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
