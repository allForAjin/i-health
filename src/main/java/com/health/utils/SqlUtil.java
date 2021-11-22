package com.health.utils;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName SqlUtil.java
 * @Description TODO
 * @createTime 2021-10-24 21:59:43
 */
public class SqlUtil {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/healthtest";
    private static final String USER = "root";

//    private static final String URL = "jdbc:mysql://106.15.50.102:3306/healthtest";
//    private static final String USER = "healthtest";

    private static final String PASSWORD = "165404026lmkaz";
    private static Connection CONN = null;

    private static final String STRING = "String";
    private static final String INTEGER = "Integer";
    private static final String DOUBLE = "Double";
    private static final String FLOAT = "Float";


    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行数据库查询语句
     * @author lmk
     * @Date 2021/11/17 18:32
     * @param sql 语句
     * @param objects 查询参数
     * @return java.util.List<java.lang.Object> 获取的结果列表
     */
    public static List<Object> executeQuery(String sql, Object... objects) {
        List<Object> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = getResultSetByPreparedStatement(sql, objects);
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while (rs != null && rs.next()) {
                Object[] result = new Object[columnCount];
                for (int i = 0; i < result.length; i++) {
                    result[i] = rs.getObject(i + 1);
                }
                list.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return list;
    }


    /**
     * 执行更新语句（添加，更新，删除）
     * @author lmk
     * @Date 2021/11/17 18:31
     * @param sql 语句
     * @param objects 更新的参数
     * @return int 更新的数量
     */
    public static int executeUpdate(String sql, Object... objects) {
        int num = -1;
        try {
            CONN = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = CONN.prepareStatement(sql);
            for (int i = 0; i < objects.length; i++) {
                setParams(ps, i + 1, objects[i]);
            }
            num = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return num;
    }

    /**
     * 获取记录数
     * @param sql 数据库语句
     * @param objects 查询参数
     * @return int 记录数
     * @author lmk
     * @Date 2021/11/12 14:55
     */
    public static int executeQueryCount(String sql, Object... objects) {
        ResultSet rs = null;
        try {

            rs = getResultSetByPreparedStatement(sql, objects);
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return -1;
    }

    /**
     * 执行带输入参数的存储过程
     * @author lmk
     * @Date 2021/11/17 14:53
     * @param sql 存储过程语句
     * @param inputParam 输入参数map，key为参数名，value为参数值
     */
    public static void executeProcedureWithInput(String sql, Map<String, Object> inputParam) {
        try {
            CONN = DriverManager.getConnection(URL, USER, PASSWORD);
            CallableStatement callableStatement = CONN.prepareCall(sql);
            for (String key : inputParam.keySet()) {
                callableStatement.setObject(key, inputParam.get(key));
            }
            callableStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 带输入参数和输出参数的map
     * @author lmk
     * @Date 2021/11/17 14:53
     * @param sql 存储过程语句
     * @param inputParam 输入参数map，key为参数名，value为参数值
     * @param outputParam 输出参数map，key为参数名，value为参数类型Type值，用于注册输出参数
     * @return java.util.Map 获取到的参数输出值，key为参数名，value为输出参数值
     */
    public static Map executeProcedureWithInputAndOutput(String sql, Map<String, Object> inputParam,Map<String,Integer> outputParam) {
        Map<String,Object> outputMap=new HashMap<>();
        try {
            CONN = DriverManager.getConnection(URL, USER, PASSWORD);
            CallableStatement callableStatement = CONN.prepareCall(sql);
            for (String key : inputParam.keySet()) {
                callableStatement.setObject(key, inputParam.get(key));
            }
            for (String key: outputParam.keySet()){
                callableStatement.registerOutParameter(key,outputParam.get(key));
            }

            callableStatement.execute();
            for (String key: outputParam.keySet()){
                Object value=callableStatement.getObject(key);
                outputMap.put(key,value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return outputMap;
    }

    /**
     * 通过PreparedStatement获取结果集
     * @author lmk
     * @Date 2021/11/17 18:34
     * @param sql 语句
     * @param objects 待设置的参数
     * @return java.sql.ResultSet 返回的结果集
     */
    private static ResultSet getResultSetByPreparedStatement(String sql, Object... objects) {
        try {
            CONN = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = CONN.prepareStatement(sql);
            for (int i = 0; i < objects.length; i++) {
                setParams(ps, i + 1, objects[i]);
            }
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 判断插入数据类型设置 PreparedStatement
     * @author lmk
     * @Date 2021/11/17 18:33
     * @param ps PreparedStatement
     * @param column 列号
     * @param object 该列参数
     */
    private static void setParams(PreparedStatement ps, int column, Object object) throws SQLException {
        String className = object.getClass().getSimpleName();
        switch (className) {
            case STRING:
                ps.setString(column, (String) object);
                break;
            case INTEGER:
                ps.setInt(column, (Integer) object);
                break;
            case DOUBLE:
                ps.setDouble(column, (Double) object);
                break;
            case FLOAT:
                ps.setFloat(column, (Float) object);
                break;
            default:
                ps.setObject(column, object);
                break;
        }
    }


    /**
     * @author lmk
     * @Description //TODO 关闭数据库连接
     * @Date 2021/10/24 22:56
     * @Param []
     * @Return void
     */
    private static void closeConnection() {
        try {
            if (CONN != null && !CONN.isClosed()) {
                CONN.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
