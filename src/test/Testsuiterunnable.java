package test;

import com.MyOVS.util.Xls_Reader;

public class Testsuiterunnable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Xls_Reader x = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\qtpselenium\\xls\\Suite.xlsx");
		System.out.println(isSuiteRunable(x,"A Suite"));
	}
		
	
	//returns true if run mode of the test is equal to Y
		public static boolean isSuiteRunable(Xls_Reader xls, String suiteName){
			boolean isExecutable=false;
			for(int i=2; i<=xls.getRowCount("Test Suite"); i++){
									
				if(xls.getCellData("Test Suite", "TSID", i).equalsIgnoreCase(suiteName)){
					if(xls.getCellData("Test Suite", "Runmode", i).equalsIgnoreCase("Y")){
						isExecutable=true;
					}else{
						isExecutable=false;
					}
				}			
			}		
			return isExecutable;
		}

	}
