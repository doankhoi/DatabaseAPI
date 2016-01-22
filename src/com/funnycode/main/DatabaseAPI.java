package com.funnycode.main;

import java.sql.Connection;
import java.sql.DriverManager;

import com.funnycode.generate.CreateDatabase;
import com.funnycode.generate.CreateTable;

public class DatabaseAPI {
	public static void main(String[] args) {
		CreateDatabase.getInstance().generateDatabase();
	}
}
