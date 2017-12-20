package lc.bimbi.refezione;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DayBalance implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4463860373027024008L;
	
	private List<DayLine> days;
	private LocalDate day;
	private int numPasti;
	private float importo;
	
	public List<DayLine> getDays() {
		return days;
	}
	
	public void setDays(List<DayLine> days) {
		this.days = days;
		for(DayLine d:days) {
			this.addNumPasti(d.getNumeroPasti());
			this.addImporto(d.getImporto());
		}
	}
	
	public int getNumPasti() {
		return numPasti;
	}
	
	private void addNumPasti(int numPasti) {
		this.numPasti += numPasti;
	}
	
	public float getImporto() {
		return importo;
	}
	
	private void addImporto(float importo) {
		this.importo += importo;
	}
	
	public LocalDate getDay() {
		return day;
	}
	public void setDay(LocalDate day) {
		this.day = day;
	}
	
	public static List<DayBalance> calculateFromList(List<DayLine> list) {
		ArrayList<DayBalance> result = new ArrayList<>((int) Math.floor((double)list.size()/2));
		DayBalance t = new DayBalance();
		for(int i=0;i < list.size();i++) {
			DayLine d = list.get(i);
			if(i == 0) {
				t.setDay(d.getDataOperazione());
			}
			if(!t.getDay().equals(d.getDataOperazione())) {
				result.add(t);
				t = new DayBalance();
				t.setDay(d.getDataOperazione());
			}
			t.addImporto(d.getImporto());
			t.addNumPasti(d.getNumeroPasti());
		}
		return result;
	}

}
