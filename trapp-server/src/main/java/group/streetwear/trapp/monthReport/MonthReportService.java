package group.streetwear.trapp.monthReport;

import java.time.LocalDate;
import java.util.List;

public interface MonthReportService {
    List<MonthReport> getAllMonthReport(LocalDate month);
}
