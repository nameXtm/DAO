package 数据表;

import util.ORM编程思想2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * 将orm对象添加到数据库中
 *
 */
public interface CustomerDAo {
    //添加数据库记录
    void insert(Connection connection, ORM编程思想2 orm) throws SQLException, IOException, ClassNotFoundException;
    //删除数据表记录
    void deleteById(Connection connection,int id ) throws SQLException, IOException, ClassNotFoundException;
    //修改数据库记录
    void updateById(Connection connection ,ORM编程思想2 orm ) throws SQLException, IOException, ClassNotFoundException;
    //查询数据库记录
    void getormById(Connection connection,int id);
    //针对于指定的id查询
    ORM编程思想2 getorm(Connection connection , int id) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException;
    //查询表中的所有记录构成集合
    List<ORM编程思想2>  getAll(Connection connection) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException;
    //返会表中的数据条目数
    Long getCount(Connection connection) throws SQLException;
    //返回表中的最大生日
    Date getMaxBirth(Connection connection) throws SQLException;

}
