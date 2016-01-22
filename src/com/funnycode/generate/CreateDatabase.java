package com.funnycode.generate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Generate database.
 * @author KhoiDN
 *
 */
public class CreateDatabase {
	private String mNameDatabase;
	private static CreateDatabase _instance;
	private Scanner mScanner;
	private Connection mConnection;
	private Statement mStatement;

	private CreateDatabase() {
		mNameDatabase = "myDb.db";
		_instance = null;
		mConnection = null;
		mStatement = null;
		mScanner = new Scanner(System.in);
	}

	public static CreateDatabase getInstance() {
		if (_instance == null) {
			_instance = new CreateDatabase();
		}
		return _instance;
	}

	public void generateDatabase() {
		System.out.println("Enter the name of your database:");
		String nameDatabase = "";
		do {
			nameDatabase = mScanner.nextLine();
		} while (nameDatabase.length() == 0);

		mNameDatabase = nameDatabase;
		try {
			Class.forName("org.sqlite.JDBC");
			mConnection = DriverManager.getConnection("jdbc:sqlite:" + nameDatabase + ".db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		System.out.println("STARTING CREATE TABLE");
		String finish = "";
		do {
			System.out.println("Do you want to create table ? (y/n)");
			finish = mScanner.nextLine();
			if (finish.equalsIgnoreCase("y")) {
				String sql = CreateTable.getInstance().generateTable();
				try {
					mStatement = mConnection.createStatement();
					mStatement.execute(sql);
					mStatement.close();
				} catch (SQLException e) {
					System.out.println("Input infomation for create table wrong. Please enter again!");
					finish = "";
				}
			}
			
			if(finish.equalsIgnoreCase("n")) {
				try {
					mConnection.close();
					System.out.println("======(-_-)BYE BYE(-_-)=========");
				} catch (SQLException e) {
					System.out.println("Processing create table error !");
					e.printStackTrace();
				}
			}
		} while(!finish.equalsIgnoreCase("n"));
	}
	
	
}
