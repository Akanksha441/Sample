package com.example.demoEnumSerializationIssue.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Map;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto implements Serializable {

    private Map<SettingType, Map<String, String>> settingTypeToSettingMap;
}