package test;

import com.MyOVS.util.Xls_Reader;

public class TestcaseRunmode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));	
		Xls_Reader x = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\qtpselenium\\xls\\A suite.xlsx");
		System.out.println(isTestCaseRunable(x,"TestCase_A1"));
		}
	
	//returns true if run mode of the test is equal to Y
			public static boolean isTestCaseRunable(Xls_Reader xls, String testName){
				boolean isExecutable=false;
				for(int i=2; i<xls.getRowCount("Test Cases"); i++){
										
					if(xls.getCellData("Test Cases", "TCID", i).equalsIgnoreCase(testName)){
						if(xls.getCellData("Test Cases", "Runmode", i).equalsIgnoreCase("Y")){
							isExecutable=true;
						}else{
							isExecutable=false;
						}
					}			
				}
				return isExecutable;
			}

	}


