package com.shopme.admin.report;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class AbstractReportService {

	protected DateTimeFormatter dateFormatter;
	
	public List<ReportItem> getReportDataLast7Days(ReportType reportType) {
		return getReportDataLastXDays(7, reportType);
	}

	public List<ReportItem> getReportDataLast28Days(ReportType reportType) {
		return getReportDataLastXDays(28, reportType);
	}
	
	protected List<ReportItem> getReportDataLastXDays(int days, ReportType reportType) {
		/*
		LocalDateTime endTime = LocalDateTime.now();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -(days - 1));
		LocalDateTime startTime = cal.getTime();
		
		System.out.println("Start time: " + startTime);
		System.out.println("End time: " + endTime);
		
		dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		
		return getReportDataByDateRangeInternal(startTime, endTime, reportType);
		*/
		
		LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minusDays(days - 1);

        System.out.println("Start time: " + startTime);
        System.out.println("End time: " + endTime);

        dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return getReportDataByDateRangeInternal(startTime, endTime, reportType);
	}
	
	public List<ReportItem> getReportDataLast6Months(ReportType reportType) {
		return getReportDataLastXMonths(6, reportType);
	}

	public List<ReportItem> getReportDataLastYear(ReportType reportType) {
		return getReportDataLastXMonths(12, reportType);
	}
	
	protected List<ReportItem> getReportDataLastXMonths(int months, ReportType reportType) {
		/*
		LocalDateTime endTime = LocalDateTime.now();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -(months - 1));
		LocalDateTimeS startTime = cal.getTime();
		
		System.out.println("Start time: " + startTime);
		System.out.println("End time: " + endTime);
		
		dateFormatter = new SimpleDateFormat("yyyy-MM");
		
		return getReportDataByDateRangeInternal(startTime, endTime, reportType);
		*/
		
		LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minusMonths(months - 1);

        System.out.println("Start time: " + startTime);
        System.out.println("End time: " + endTime);

        dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");

        return getReportDataByDateRangeInternal(startTime, endTime, reportType);
	}

	public List<ReportItem> getReportDataByDateRange(LocalDateTime startTime, LocalDateTime endTime, ReportType reportType) {
		
		/*
		dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		return getReportDataByDateRangeInternal(startTime, endTime, reportType);
		*/
		
		dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return getReportDataByDateRangeInternal( startTime, endTime, reportType);
	}
	
	protected abstract List<ReportItem> getReportDataByDateRangeInternal(
			LocalDateTime startDate, LocalDateTime endDate, ReportType reportType);
}
