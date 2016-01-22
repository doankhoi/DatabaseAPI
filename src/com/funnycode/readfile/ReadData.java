package com.funnycode.readfile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadData {
	private String mPathFileData;
	private List<String> mColumns;
	private Connection mConnection;
	private Statement mStatement;
	private Scanner mScanner;
	private static ReadData _instance;
	
	private ReadData(){
		mPathFileData = null;
		mColumns = new ArrayList<String>();
		_instance = null;
		mScanner = null;
	}
	
	public static ReadData getInstance() {
		if (_instance == null) {
			_instance = new ReadData();
		}
		
		return _instance;
	}
	
	/**
	 * Import data into database with external file.
	 */
	public void importDataForTable() {
		/* pathFile File path data
		 * signSplit String for split data
		 * signEndSegment String for end segment
		 * database Name database
		 * nameTable Name table import
		 * */
		String pathFile = "";
		String signSplit = "";
		String signEndSegment = "";
		String database = "";
		String nameTable =  "";
		//Input parameters
		String temp = "";
		mScanner = new Scanner(System.in);
		
		System.out.println("Please enter sign split on a line file data. If sign split is empty then enter ");
		temp = "";
		do {
			temp = mScanner.nextLine();
			if (temp.length() == 0) {
				System.err.println(" not empty. Please enter again.");
			} else {
				pathFile = temp;
			}
		} while(temp.length() == 0);
		
		do {
			temp = mScanner.nextLine();
			if (temp.length() == 0) {
				System.err.println("Field path not empty. Please enter again.");
			} else {
				pathFile = temp;
			}
		} while(temp.length() == 0);
		
		try {
			Class.forName("org.sqlite.JDBC");
			mConnection = DriverManager.getConnection("jdbc:sqlite:" + database + ".db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		try {
			mStatement = mConnection.createStatement();
		} catch (SQLException e) {
			System.err.println("Create statement error.");
		}
	}
	
	/**
	 * Input parameter
	 * @param scanner
	 * @param nameParameter
	 * @param notes
	 * @return
	 */
	private String inputParameter(Scanner scanner, String nameParameter, String notes) {
		System.out.println("Please enter " + nameParameter);
		System.out.println("(==" + notes + "==)");
		String temp = "";
		do {
			temp = scanner.nextLine();
			if (temp.length() == 0) {
				System.err.println(nameParameter + " not empty. Please enter again.");
			} else {
				temp = temp.trim();
			}
		} while(temp.length() == 0);
		
		return temp;
	}
}
