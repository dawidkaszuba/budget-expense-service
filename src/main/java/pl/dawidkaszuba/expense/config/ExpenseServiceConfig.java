package pl.dawidkaszuba.expense.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "expense-service")
@Getter
@Setter
@ToString
public class ExpenseServiceConfig {
    private String msg;
    private String buildVersion;
}
