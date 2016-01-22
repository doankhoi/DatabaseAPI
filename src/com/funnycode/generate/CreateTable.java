package com.funnycode.generate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Generate table.
 * @author KhoiDN
 *
 */
public class CreateTable {
	private Scanner mScanner;
	private String mName;
	private List<String> mColumns;
	private List<String> mTypes;
	private List<String> mDefaults;

	private static CreateTable _instance;

	private CreateTable() {
		mScanner = null;
		mName = null;
		mColumns = new ArrayList<String>();
		mTypes = new ArrayList<String>();
		mDefaults = new ArrayList<String>();
	}

	public static CreateTable getInstance() {
		if (_instance == null) {
			_instance = new CreateTable();
		}
		return _instance;
	}

	private void inputInfoTable() {
		if (mScanner == null) {
			mScanner = new Scanner(System.in);
		}

		System.out.println("\n Enter the name table: ");
		String nameTable = "";
		do {
			nameTable = mScanner.nextLine();
		} while (nameTable.length() == 0);
		mName = nameTable;

		System.out
				.println("\nEnter info field (Format: name_field,type_field,default_field).\n"
						+ "If the field is empty then enter character #.\n"
						+ "Example:\n"
						+ "Statement: create table demo (\n"
						+ "		_id integer primary key autoincrement\n"
						+ "); \n"
						+ "Enter: _id,integer,primary key autoincrement"
						+ "\nEnter character q into finish.):");
		String temp = "";
		String[] arrTemp = null;
		int numField = 1;
		do {

			do {
				temp = "";
				arrTemp = null;
				System.out.print("\nField " + numField + ": ");
				temp = mScanner.nextLine();
				if (temp.equalsIgnoreCase("q")) {
					break;
				}
				arrTemp = temp.split(",");
				if (arrTemp.length != 3) {
					System.out.println("Input string wrong format");
				} else {
					if (isEmpty(arrTemp[0]) || (isEmpty(arrTemp[1]))) {
						System.out
								.println("name_field and type_field not empty");
					}
				}
			} while ((arrTemp.length != 3) || isEmpty(arrTemp[0])
					|| isEmpty(arrTemp[1]));

			if (!temp.equalsIgnoreCase("q")) {
				mColumns.add(arrTemp[0].trim());
				mTypes.add(arrTemp[1].trim());
				if (isEmpty(arrTemp[2])) {
					mDefaults.add("");
				} else {
					mDefaults.add(arrTemp[2].trim());
				}
				numField++;
			}
		} while (!temp.equalsIgnoreCase("q"));
	}

	private boolean isEmpty(String value) {
		if ((value.length() == 0) || (value.equals("#"))) {
			return true;
		}

		return false;
	}

	public String generateTable() {
		System.out.println("=====================TABLE============================");
		this.inputInfoTable();
		String command = "create table " + mName + "(";
		for (int i = 0; i < mColumns.size(); i++) {
			command += (mColumns.get(i) + " " + mTypes.get(i) + " "
					+ mDefaults.get(i) + ",");
		}
		command = command.substring(0, command.lastIndexOf(","));
		command += ");";
		System.out.println("Command completed: " + command);
		System.out.println("===============END TABLE \""+ mName +"\"===============");
		return command;
	}
}
