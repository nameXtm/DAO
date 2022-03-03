package 数据表;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装了针对于数据表的通用操作
 */
//通用的查询操作
public abstract class  BaseDAO {
    public static <T> List<T> getInstance(Connection connection , Class<T> clazz, String sql, Object... argh) throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //填充占位符
        for (int i = 0; i < argh.length; i++) {
            preparedStatement.setObject(i + 1, argh[i]);
        }
        //执行获取结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        //获取结果集的元数据(getMetaData())
        ResultSetMetaData metaData = resultSet.getMetaData();
        //通过ResultSetMetadata获取结果集中的列（getColumnCount）
        int columnCount = metaData.getColumnCount();
        ArrayList<T> list = new ArrayList<>();
        while (resultSet.next()) {
//            ORM编程思想 orm = new ORM编程思想();
            T t = clazz.newInstance();


            //处理结果集一行数据中的每一个列
            for (int c = 0; c < columnCount; c++) {
                //获取列值（etObject(c + 1)）
                Object object = resultSet.getObject(c + 1);
                //获取每个列的列名(getColumnName(c + 1))
                //获取列的别名 String columnLabel = metaData.getColumnLabel(c + 1);
//                String columnName = metaData.getColumnName(c + 1);
                String columnLabel = metaData.getColumnLabel(c + 1);
                //给orm对象指定某个属性赋值,通过反射
                Field declaredField = clazz.getDeclaredField(columnLabel);
                declaredField.setAccessible(true);
                declaredField.set(t, object);

            }
            list.add(t);
        }

        preparedStatement.close();
        resultSet.close();

        return list;

    }
    //通用的增删改操作
    public static void update1(Connection conn ,String sql, Object... args) throws SQLException, IOException, ClassNotFoundException {
        //1.获取数据库连接

        //2.编译SQL语言
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        //3.填充占位符
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i + 1, args[i]);
        }
        //4.执行
        preparedStatement.execute();
        //5.关闭流
        preparedStatement.close();
    }

    //用于查询特殊（函数等）操作
    public<E> E getValue(Connection connection , String sql ,Object...args) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int a = 0; a < args.length; a++) {
            preparedStatement.setObject(a+1,args[a]);

        }
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next() ) {
           return (E) resultSet.getObject(1);

        }
        connection.close();
        preparedStatement.close();

        return null;
    }
}
