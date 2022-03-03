package 数据表;

import util.ORM编程思想2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ormDAOImp extends BaseDAO implements CustomerDAo{
    @Override
    public void insert(Connection connection, ORM编程思想2 orm) throws SQLException, IOException, ClassNotFoundException {
        String sql = "insert into test values(?,?,?)";
        update1(connection,sql,orm.getId(),orm.getName(),orm.getTupian());
    }

    @Override
    public void deleteById(Connection connection, int id) throws SQLException, IOException, ClassNotFoundException {
        String sql = "delete from test where id = ?";
        update1(connection,sql,id);

    }

    @Override
    public void updateById(Connection connection,   ORM编程思想2 orm) throws SQLException, IOException, ClassNotFoundException {
        String sql = "update test set id = ? , name = ?, tupian =?";
        update1(connection,sql ,orm.getId(),orm.getName(),orm.getTupian());

    }

    @Override
    public void getormById(Connection connection, int id) {


    }

    @Override
    public ORM编程思想2 getorm(Connection connection, int id) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String sql = "select id `name`,tupian from test where id = ?";
        getInstance(connection,ORM编程思想2.class,sql,id);
        return null;
    }

    @Override
    public List<ORM编程思想2> getAll(Connection connection) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String sql = "select id ,`name` ,tupian form test ";
        List<ORM编程思想2> instance = getInstance(connection, ORM编程思想2.class, sql);
        return instance;
    }

    @Override
    public Long getCount(Connection connection) throws SQLException {
        String sql = "select count(*) from test";

        return getValue(connection,sql);
    }

    @Override
    public Date getMaxBirth(Connection connection) throws SQLException {
        String sql ="select max(id) from test";
        return getValue(connection ,sql);

    }
}
