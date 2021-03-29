package com.homework.stepdefinition;

import java.util.HashMap;
import java.util.List;

import com.homework.utils.ReadExcel;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class DefaultStepDefinition {
	
	public static HashMap<String,String> currentIterationMap;
	private HashMap<String,HashMap<String,String>>excelData=new HashMap<String,HashMap<String,String>>();
	private static String rowName;
	
	@SuppressWarnings("deprecation")
	@Before
	public void readScenarioName(cucumber.api.Scenario scenario) {
		rowName =scenario.getName();
	}

	@SuppressWarnings("deprecation")
	@Given("A workbook named {string} and sheet named {string} is read")
	public void workbook_read(String excelName,String sheetName) {
		List<HashMap<String,String>>data=ReadExcel.readData(excelName,sheetName);
		for(HashMap<String,String>map:data) {
			excelData.put(map.get("TestDataID").trim(),map);
		}
		currentIterationMap=excelData.get(rowName);
				
	}
}
