package lc.bimbi.refezione;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

//.xls HSSFWorkbook, or a .xlsx XSSFWorkbook
public class ReadExcel {
	
	private static final int INDEX_DATA = 0;
	private static final int INDEX_IMPORTO = 4;
	public static void main(String...strings) throws EncryptedDocumentException, InvalidFormatException, IOException {
		Workbook wb = WorkbookFactory.create(new File("C:\\input\\input.xlsx"));
		Sheet sh = wb.getSheetAt(0);
		Date date = null;
		double value = 0.0;
		Double partialSum = 0.0;
		Double totalSum = 0.0;
		int count = 0;
		for(Row r: sh) {
			count++;
			if(date == null || (r.getCell(INDEX_DATA) != null && !date.equals(r.getCell(INDEX_DATA).getDateCellValue()))) {
				if( date != null && !date.equals(r.getCell(INDEX_DATA).getDateCellValue())) {
					System.out.println(date.toString() + " Importo: " + partialSum);
					partialSum = 0.0;
				}
				date = r.getCell(INDEX_DATA).getDateCellValue();
				value =  r.getCell(INDEX_IMPORTO).getNumericCellValue();
				partialSum = Double.sum(partialSum, value);
				totalSum = Double.sum(totalSum, partialSum);

			}else {
				value = r.getCell(INDEX_IMPORTO).getNumericCellValue();
				partialSum = Double.sum(partialSum, value);
			}
			/*Iterator<Cell> itc = r.cellIterator();
			
			while(itc.hasNext()) {
				Cell c = itc.next();
				int ctInt = c.getCellType();
				CellType ct = CellType.forInt(ctInt);
				CellReference cRef = new CellReference(r.getRowNum(), c.getColumnIndex());
				System.out.print(cRef.formatAsString()); //e.g. A3
				System.out.print(" | "); 
				System.out.print(getValue(c)); 
			}
			System.out.println();/**/
		}
		System.out.println(count + " Totale " + totalSum);
	}

	private static String getValue(Cell c) {
		if(c != null) {
			switch(c.getCellTypeEnum()) {
			case STRING:
				return c.getRichStringCellValue().toString();
			case NUMERIC:
                if (DateUtil.isCellDateFormatted(c)) {
                   return c.getDateCellValue().toString();
                } else {
                    return Double.valueOf(c.getNumericCellValue()).toString();
                }
			case BOOLEAN:
                return Boolean.valueOf(c.getBooleanCellValue()).toString();
            case FORMULA:
                return c.getCellFormula();
            case BLANK:
            default:
            	return "";
			}
		}
		return "";
	}
}
