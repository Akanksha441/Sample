package com.example.demoEnumSerializationIssue.model;

public enum SettingType {
    SETTING_A("A"),
    SETTING_B("B"),
    SETTING_C("C");

    private final String value;

    SettingType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
