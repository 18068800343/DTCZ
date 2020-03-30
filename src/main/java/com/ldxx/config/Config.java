package com.ldxx.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "remote", ignoreUnknownFields = false)
@PropertySource("classpath:config.properties")
@Data
@Component
public class Config {
    private String reportPath;
    private String reportCmd;

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
