package test;

import com.MyOVS.util.Xls_Reader;

public class TestDataExtract {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\qtpselenium\\xls\\A suite.xlsx");
		getData(xls, "TestCase_A1");
	}


		public static void getData(Xls_Reader xls, String testCaseName){
			// If the sheet is not present
			if(! xls.isSheetExist(testCaseName)){
				xls=null;   // Cleaning the memory
				//return new Object[1][0];   // We are creating a object array with 1 row for the test case run at least once
			}
			
			// If the sheet is present
			
			int rows=xls.getRowCount(testCaseName);
			int cols=xls.getColumnCount(testCaseName);
			
			System.out.println(rows+"---"+cols);
			
			
			for(int rowNum=2;rowNum<=rows;rowNum++){
				for(int colNum=0;colNum<cols-3;colNum++){
					System.out.print(xls.getCellData(testCaseName, colNum, rowNum) + " -------  ");					
				}
				System.out.println();
			
			/*
			
			Object[][] data= new Object[rows-1][cols-3];
			for(int rowNum=2;rowNum<=rows;rowNum++){
				for(int colNum=0;colNum<cols-3;colNum++){
					data[rowNum-2][colNum]=xls.getCellData(testCaseName, colNum, rowNum);
				}
			}return data;
			*/
		
			}
		}
}


