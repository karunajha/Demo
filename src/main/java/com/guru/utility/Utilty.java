package com.guru.utility;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;



public class Utilty {
    public static final String FILE_PATH = "F:\\TestWork\\demo_project\\excel\\testData.xlsx"; // File Path
    public static final String SHEET_NAME = "Data"; // Sheet name
    public static final String TABLE_NAME = "testData"; // Name of data table
    public static final String CHROME_PATH = "F:/TestWork//demo_project//Drivers//chromedriver.exe";
	public static final String BASE_URL = "http://www.demo.guru99.com/V4/";
//DELAY TIME TO WAIT THE WEBSITE
	public static final int WAIT_TIME = 30;
//VALID ACCOUNT FOR LOGIN
	public static final String USER_NAME = "mngr254209";
	public static final String PASSWORD = "ahevUjy";
// Expected output
	public static final String EXPECT_TITLE = "Guru99 Bank Manager HomePage";
	public static final String EXPECT_ERROR = "User or password os not valid";

	/**
	 * This method reads the data from the Sheet name "Data" of file "testData.xls"
	 * 
	 * 
	 * @param xlFilePath : Path of excel file
	 * @param sheetName  : Sheet name which contains test data
	 * @param tableName  : Table name is used to mark the start and end position of
	 *                   the test data table. The method will find the cell which
	 *                   contains the table name to find position of data table
	 * @return a 2 dimensions array to store all the test data read from excel
	 * @throws Exception
	 */



public static String[][] getDataFromExcel(String xFilePath,
		String sheetName, String tableName) throws Exception {	// Declare a 2 dimensions array to store all the test data read from
		// excel
	// Declare a 2 dimensions array to store all the test data read from
			// excel
			String[][] tabArray = null;

			// get the workbook file. Provide the path of excel file
			Workbook workbook = Workbook.getWorkbook(new File(xFilePath));
			// get the sheet name
			Sheet sheet = workbook.getSheet(sheetName);

			int startRow, startCol, endRow, endCol, ci, cj;

			// find cell position which contain first appear table name
			Cell tableStart = sheet.findCell(tableName);
			// Row position of FIRST appear table name
			startRow = tableStart.getRow();
			// Col position of FIRST appear table name
			startCol = tableStart.getColumn();

			// find cell position which contain last appear table name
			Cell tableEnd = sheet.findCell(tableName, startCol + 1, startRow + 1,
					100, 64000, false);
			// Row position of LAST appear table name
			endRow = tableEnd.getRow();
			// Col position of LAST appear table name
			endCol = tableEnd.getColumn();

			tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
			ci = 0;

			// Store all data in an array
			for (int i = startRow + 1; i < endRow; i++, ci++) {
				cj = 0;
				for (int j = startCol + 1; j < endCol; j++, cj++) {
					//Get content of each cell and store to each array element.
					tabArray[ci][cj] = sheet.getCell(j, i).getContents();
				}
			}

			return (tabArray);
		}


	}
