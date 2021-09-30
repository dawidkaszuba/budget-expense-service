package pl.dawidkaszuba.expense.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Properties {
    private String msg;
    private String buildVersion;

    public Properties(String msg, String buildVersion) {
        this.msg = msg;
        this.buildVersion = buildVersion;
    }
}