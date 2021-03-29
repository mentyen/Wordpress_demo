package com.homework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	private static FileInputStream fis;
	private static XSSFWorkbook xssfWorkbook;
	private static XSSFSheet xssfSheet;
	private static DataFormatter dataFormatter = new DataFormatter();

	public static List<HashMap<String, String>> readData(String excelName, String sheetName) {
		List<HashMap<String, String>> excelData = new ArrayList<>();
		try {
			setup(excelName, sheetName);
			int numRows = xssfSheet.getLastRowNum();
			for (int i = 0; i <= numRows; i++) {
				HashMap<String, String> inputValues = getHashMapDataFromRow(xssfSheet, i);
				excelData.add(inputValues);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(fis);
		}
		return excelData;
	}

	private static HashMap<String, String> getHashMapDataFromRow(XSSFSheet sheet, int rowIndex) {
		HashMap<String, String> res = new HashMap<>();
		String[] columnHeaders = getDataFromRow(sheet, 0);
		String[] rowValue = getDataFromRow(sheet, rowIndex);

		for (int i = 0; i < columnHeaders.length; i++) {
			if (i >= rowValue.length) {
				res.put(columnHeaders[i], "");
			} else {
				res.put(columnHeaders[i], rowValue[i]);
			}
		}
		return res;
	}

	private static String[] getDataFromRow(XSSFSheet sheet, int rowIndex) {
		FormulaEvaluator formulaEvaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();
		Row row = sheet.getRow(rowIndex);
		short numCells = row.getLastCellNum();
		String[] res = new String[numCells];
		for (int i = 0; i < numCells; i++) {
			res[i] = getValueAsString(row.getCell(i), formulaEvaluator);
		}
		return res;
	}

	private static String getValueAsString(Cell cell, FormulaEvaluator formulaEvaluator) {
		if (cell != null) {
			CellType cellType = cell.getCellTypeEnum();
			if (cellType.equals(CellType.BOOLEAN)) {
				return String.valueOf(cell.getBooleanCellValue());
			} else if (cellType.equals(CellType.NUMERIC)) {
				return dataFormatter.formatCellValue(cell);
			} else if (cellType.equals(CellType.STRING)) {
				return cell.getRichStringCellValue().getString();
			} else if (cellType.equals(CellType.FORMULA)) {
				return formulaEvaluator.evaluate(cell).getStringValue();
			}
		}
		return "";
	}

	private static void setup(String fileName, String sheetName) throws IOException {

		String xlsxFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\" + fileName + ".xlsx";
		File xlsxFile = new File(xlsxFilePath);
		if (xlsxFile.exists()) {
			fis = new FileInputStream(xlsxFilePath);
		}
		xssfWorkbook = new XSSFWorkbook(fis);
		xssfSheet = xssfWorkbook.getSheet(sheetName);
		xssfWorkbook.close();
	}

}
