package com.zakiyah.Pawfella.pawapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbHandler {
    Connection koneksi = DriverManager.getConnection("147.139.129.198:3306","admin","asdf1234");

    public dbHandler() throws SQLException {

    }
}
