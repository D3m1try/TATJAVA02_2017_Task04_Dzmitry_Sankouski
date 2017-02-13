package com.epam.news_manager.dao.impl;

import java.sql.Connection;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Dzmitry_Sankouski on 13-Feb-17.
 */
public final class ConnectionPool {

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConQueue;

    public Connection getConnection(){
        return null;
    }
}
