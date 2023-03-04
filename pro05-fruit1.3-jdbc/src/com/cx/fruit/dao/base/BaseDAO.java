package com.cx.fruit.dao.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/3/1-21:08
 * @Version 2022.2 1.8
 */
public abstract class BaseDAO<T> {

    protected Connection connection = null;
    protected PreparedStatement prepareStatement = null;
    protected ResultSet resultSet = null;

    protected final String DRIVER = "com.mysql.jdbc.Driver";
    protected final String URL = "jdbc:mysql://localhost:3306/fruitdb?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    protected final String USER = "root";
    protected final String PWD = "123456";

    private Class entityClass;

    public BaseDAO() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        Type actualTypeArgument = actualTypeArguments[0];
        String typeName = actualTypeArgument.getTypeName();
        try {
            entityClass = Class.forName(typeName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setParams(PreparedStatement prepareStatement, Object... params) throws SQLException {
        if (params != null && params.length > 0){
            for (int i = 0; i < params.length; i++) {
                prepareStatement.setObject(i + 1,params[i]);
            }
        }
    }

    private void setValue(Object object,String property,Object propertyValue){
        Class clazz = object.getClass();
        try {
            Field field = clazz.getDeclaredField(property);
            if(field != null){
                field.setAccessible(true);
                field.set(object,propertyValue);
            }
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    protected Object[] executeComplexQuery(String sql, Object... params){
        try {
            connection = getConnection();
            prepareStatement = connection.prepareStatement(sql);
            setParams(prepareStatement,params);
            resultSet = prepareStatement.executeQuery();
            //获取元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            Object[] columnValueArr = new Object[columnCount];
            //解析rs
            if(resultSet.next()) {
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = resultSet.getObject(i + 1);
                    columnValueArr[i] = columnValue;
                }
                return columnValueArr;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection,prepareStatement,resultSet);
        }
        return null;
    }

    protected T load(String sql,Object... params){
        try {
            connection = getConnection();
            prepareStatement = connection.prepareStatement(sql);
            setParams(prepareStatement,params);
            resultSet = prepareStatement.executeQuery();
            //获取元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            //解析rs
            if(resultSet.next()) {
                T entity = (T)entityClass.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Object columnValue = resultSet.getObject(i + 1);
                    setValue(entity,columnLabel,columnValue);
                }
                return entity;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection,prepareStatement,resultSet);
        }
        return null;
    }

    protected List<T> executeQuery(String sql,Object... params){
        List<T> list = new ArrayList<>();
        try {
            connection = getConnection();
            prepareStatement = connection.prepareStatement(sql);
            setParams(prepareStatement,params);
            resultSet = prepareStatement.executeQuery();
            //获取元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            //解析rs
            while (resultSet.next()) {
                T entity = (T)entityClass.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Object columnValue = resultSet.getObject(i + 1);
                    setValue(entity,columnLabel,columnValue);
                }
                list.add(entity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection,prepareStatement,resultSet);
        }
        return list;
    }

    protected int executeUpdate(String sql, Object... params) {
        boolean insertFlag = false;
        insertFlag = sql.trim().toUpperCase().startsWith("INSERT");
        try {
            connection = getConnection();
            if(insertFlag){
                prepareStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }else {
                prepareStatement = connection.prepareStatement(sql);
            }
            setParams(prepareStatement,params);
            int count = prepareStatement.executeUpdate();
            ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                return ((Long)generatedKeys.getLong(1)).intValue();
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection,prepareStatement,null);
        }
        return 0;
    }

    protected Connection getConnection() {
        try {
            //1.加载驱动
            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void close(Connection connection, PreparedStatement prepareStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (prepareStatement != null) {
                prepareStatement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
