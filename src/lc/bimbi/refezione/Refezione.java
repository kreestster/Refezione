package lc.bimbi.refezione;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Refezione {
	public static void main(String...strings) throws EncryptedDocumentException, InvalidFormatException, IOException {
		Workbook wb = WorkbookFactory.create(new File("C:\\input\\input.xlsx"));
		Sheet sh = wb.getSheetAt(0);
		
		ArrayList<DayLine> list = new ArrayList<>();
		//creazione dataset
		for(Row r: sh) {
			DayLine d = DayLine.createDayLine(r);
			list.add(d);
		}
		//caricamento dataset
		ArrayList<DayBalance> bal = new ArrayList<>();
		bal = DayBalance.calculateFromList(list);
		//fine creazione e caricamento dataset
		
		bal.forEach(i -> System.out.println(i.getDay() + " : " + i.getImporto()));
		double total = bal.stream().mapToDouble(DayBalance::getImporto).sum();
		System.out.println("Saldo: " + total);
	}
}
