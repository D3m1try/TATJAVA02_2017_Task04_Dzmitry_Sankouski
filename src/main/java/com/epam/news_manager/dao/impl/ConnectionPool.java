package com.epam.news_manager.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Dzmitry_Sankouski on 13-Feb-17.
 */
public final class ConnectionPool {

    private final int MAX_SIZE = 10;
    private final String CON_NAME = "jdbc:mysql://127.0.0.1/News";
    private final String USER = "root";
    private final String PASS = "123456";



    private BlockingQueue<Connection> connectionQueue = new ArrayBlockingQueue<Connection>(10);
    private BlockingQueue<Connection> givenAwayConQueue = new ArrayBlockingQueue<Connection>(10);

    private static ConnectionPool instance;

    private ConnectionPool() throws SQLException {
        for (int i = 0; i < 10; i++) {
            connectionQueue.add(DriverManager.getConnection(CON_NAME,USER,PASS));
        }
    }


    public Connection getConnection() throws SQLException {
        Connection connection;
        connection = connectionQueue.poll();
        if (connection == null){
            reFill();
            connection = connectionQueue.poll();
        }
        givenAwayConQueue.offer(connection);
        return connection;
    }

    public void returnConnection(Connection connection){
        givenAwayConQueue.remove(connection);
        connectionQueue.offer(connection);
    }

    public void close() throws SQLException {
        for (Connection conn :
                connectionQueue) {
            conn.close();
        }

        for (Connection conn :
                givenAwayConQueue) {
            conn.close();
        }
    }

    public static ConnectionPool getInstance() throws SQLException {
        if (instance == null){
            instance = new ConnectionPool();
        }
        return instance;
    }

    private void reFill() throws SQLException {
        for (int i = 0; i < MAX_SIZE - getConnectionsCount(); i++) {
            connectionQueue.add(DriverManager.getConnection(CON_NAME,USER,PASS));
        }
    }

    private int getConnectionsCount(){
        return connectionQueue.size() + givenAwayConQueue.size();
    }


}
