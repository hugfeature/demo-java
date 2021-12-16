package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.TreeSet;

/**
 * @author wangzhaoxian
 */
public class getHbase {
    public static void main(String[] args) throws IOException {

        TreeSet<Long> longs = new TreeSet<>();
        boolean b = true;
        Connection connection = null;
        int num = 0;
        try {
            // 连接hbase
            Configuration conf = new Configuration();
            conf.set("hbase.zookeeper.quorum", "192.168.175.234:2181,192.168.175.235:2181,192.168.175.236:2181");
            connection = ConnectionFactory.createConnection(conf);
            Table table = connection.getTable(TableName.valueOf("zmjtest:timeseries-202108"));
            Scan scan = new Scan();
            ResultScanner resultScanner = table.getScanner(scan);
            // scan 获取表数据
            for (Result result : resultScanner) {
                for (Cell cell : result.listCells()) {
                    num ++;
                    if (b) {
                        System.out.println("rowKey:" + Bytes.toString(result.getRow()));
                        System.out.println("family:" + Bytes.toString(CellUtil.cloneFamily(cell)));
                        System.out.println("Qualifier:" + Bytes.toString(CellUtil.cloneQualifier(cell)));
                        if ("+".equals(Bytes.toString(CellUtil.cloneQualifier(cell)))){
                            System.out.println("value:" + Bytes.toString(CellUtil.cloneValue(cell)));
                        }else {
                            System.out.println("value:" + Bytes.toLong(CellUtil.cloneValue(cell)));
                        }
                        if (num == 2) {
                            b = false;
                        }
                    }
                    Long time = cell.getTimestamp();
                    longs.add(time);
                }
            }
            System.out.println("RowCount: " + num / 6);
            System.out.println("Max:" + longs.last()  + ",Min:" + longs.first() + ",Use:" + (longs.last() - longs.first()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            connection.close();
        }
    }

}
