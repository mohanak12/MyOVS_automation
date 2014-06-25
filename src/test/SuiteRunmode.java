package test;

import com.MyOVS.util.Xls_Reader;

public class SuiteRunmode {



	public static void main(String[] args) {
		
	System.out.println(System.getProperty("user.dir"));	
	Xls_Reader x = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\qtpselenium\\xls\\Suite.xlsx");
	System.out.println(isSuiteRunable(x,"A Suite"));
	}
	
	public static boolean isSuiteRunable(Xls_Reader xls, String suiteName){
		boolean isExecutable=false;
		for(int i=2; i<xls.getRowCount("Test Suite"); i++){
								
			if(xls.getCellData("Test Suite", "TSID", i).equalsIgnoreCase(suiteName)){
				if(xls.getCellData("Test Suite", "Runmode", i).equalsIgnoreCase("Y")){
					isExecutable=true;
				}else{
					isExecutable=false;
				}
			}			
		}
		xls=null; //release memory
		return isExecutable;
	}
	
	
	


}
