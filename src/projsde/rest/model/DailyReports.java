package projsde.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DailyReports {
	

	private List<DailyReport> dailyReport;

	@XmlElement
	public List<DailyReport> getDailyReport() {
		return dailyReport;
	}

	public void setDailyReport(List<DailyReport> dailyReport) {
		this.dailyReport = dailyReport;
	}

	public DailyReports(List<DailyReport> dailyReport) {
		super();
		this.dailyReport = dailyReport;
	}

	public DailyReports() {
		
	}
	


}
