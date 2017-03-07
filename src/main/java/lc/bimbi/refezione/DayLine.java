package lc.bimbi.refezione;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;

import org.apache.poi.ss.usermodel.Row;
import java.util.Date;

public class DayLine implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4211331492938134916L;
	
	private static final int INDEX_DATA = 0;
	private static final int INDEX_TIPO_OPERAZIONE = 2;
	private static final int INDEX_NUMERO_PASTI = 3;
	private static final int INDEX_IMPORTO = 4;
	
	private LocalDate dataOperazione;
	private String tipoOperazione;
	private int numeroPasti;
	private float importo;
	
	public static DayLine createDayLine(Row r) {
		try {
			Date d = r.getCell(INDEX_DATA).getDateCellValue();
			String type = r.getCell(INDEX_TIPO_OPERAZIONE).getStringCellValue();
			int num = (int) r.getCell(INDEX_NUMERO_PASTI).getNumericCellValue();
			float imp = (float)r.getCell(INDEX_IMPORTO).getNumericCellValue();
			return createDayLine(d, type, num, imp);
		} catch (Exception e) {
			return new DayLine();
		}
	}
	
	public static DayLine createDayLine(Date d, String type, int num, float imp) {
		java.time.LocalDate dateLocal = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return new DayLine(dateLocal, type, num, imp);
	}
	
	public static DayLine createDayLine(LocalDate d, String type, int num, float imp) {
		return new DayLine(d, type, num, imp);
	}
	
	private DayLine() {
		
	}
	
	private DayLine(LocalDate d, String type, int num, float imp) {
		this.dataOperazione = d;
		this.tipoOperazione = type;
		this.numeroPasti = num;
		this.importo = imp;
	}
	public LocalDate getDataOperazione() {
		return dataOperazione;
	}
	public void setDataOperazione(LocalDate dataOperazione) {
		this.dataOperazione = dataOperazione;
	}
	public String getTipoOperazione() {
		return tipoOperazione;
	}
	public void setTipoOperazione(String tipoOperazione) {
		this.tipoOperazione = tipoOperazione;
	}
	public int getNumeroPasti() {
		return numeroPasti;
	}
	public void setNumeroPasti(int numeroPasti) {
		this.numeroPasti = numeroPasti;
	}
	public float getImporto() {
		return importo;
	}
	public void setImporto(float importo) {
		this.importo = importo;
	}
}
