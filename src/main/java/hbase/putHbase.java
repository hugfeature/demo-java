package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class putHbase {
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
            //3.数据准备
            // 准备MQTT数据
            for (int i = 0; i < 100; i++){
                String rowKey = "JOB100:/zmj/test" + i;
                String mqttNameSpace = "mqtt" + i;
                Put put= new Put(Bytes.toBytes(rowKey));
                put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("mbs"), Bytes.toBytes("tcp://192.168.175.228:1883"));
                put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("mgi"), Bytes.toBytes("test"));
                put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("ibs"), Bytes.toBytes("cdh-4:9092,cdh-5:9092,cdh-6:9092"));
                put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("ins"), Bytes.toBytes(mqttNameSpace));
                put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("int"), Bytes.toBytes("whl_test1"));
                put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("st"), Bytes.toBytes("mqtt"));
                // 准备kafka数据
                rowKey = "JOB100:wzx_test" + i;
                String kafkaNameSpace = "kafka" + i;
                Put put1= new Put(Bytes.toBytes(rowKey));
                put1.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("mbs"), Bytes.toBytes("cdh-4:9092,cdh-5:9092,cdh-6:9092"));
                put1.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("mgi"), Bytes.toBytes("test"));
                put1.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("ibs"), Bytes.toBytes("cdh-4:9092,cdh-5:9092,cdh-6:9092"));
                put1.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("ins"), Bytes.toBytes(kafkaNameSpace));
                put1.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("int"), Bytes.toBytes("whl_test1"));
                put1.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("st"), Bytes.toBytes("kafka"));
                // 4. 添加数据
                table.put(put);
                table.put(put1);
            }

            table.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
