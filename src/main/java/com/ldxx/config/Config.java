package com.ldxx.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "config", ignoreUnknownFields = false)
@PropertySource("classpath:config.properties")
@Data
public class Config {

    private String reportPath;
    private String reportCmd;
    private String reportCmdMonth;
    private String reportCmdWeek;
    private String reportCmdWeekEast;

    public String getReportCmdWeekEast() {
        return reportCmdWeekEast;
    }

    public void setReportCmdWeekEast(String reportCmdWeekEast) {
        this.reportCmdWeekEast = reportCmdWeekEast;
    }

    public String getReportCmdWeek() {
        return reportCmdWeek;
    }

    public void setReportCmdWeek(String reportCmdWeek) {
        this.reportCmdWeek = reportCmdWeek;
    }

    public String getReportCmdMonth() {
        return reportCmdMonth;
    }

    public void setReportCmdMonth(String reportCmdMonth) {
        this.reportCmdMonth = reportCmdMonth;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public String getReportCmd() {
        return reportCmd;
    }

    public void setReportCmd(String reportCmd) {
        this.reportCmd = reportCmd;
    }
}
