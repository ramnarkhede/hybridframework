package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//Data Provider 1
	
	@DataProvider(name="LoginData")
	public String [][]getData()throws IOException
	{
		String path=".\\testData\\datasheet.xlsx";
		ExcelUtils excelUtils=new ExcelUtils(path);
		int totalRow=excelUtils.getRowCount("sheet1");
		int totalCols=excelUtils.getCellCount("sheet1", 1);
		String loginData[][]=new String[totalRow][totalCols];
		
		for(int i=1;i<=totalRow;i++) {
			for(int j=0;j<totalCols;j++) {
				loginData[i-1][j]=excelUtils.getCellData("sheet1", i, j);
			}
		}
		return loginData;
	}
	
	
	//Data Provider 2
	
	//Data Provider 3

}
