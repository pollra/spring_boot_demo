package com.pollra.tools;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class InspectionTool {
    private Map<String, String> pattern;

    public InspectionTool() {
        pattern = new HashMap<>();

        pattern.put("<","&lt;");
        pattern.put(">","&gt;");
        pattern.put("$","&#36;");
        pattern.put("}","&#125;");
        pattern.put("{","&#123;");
    }

    public String stringDataTagCheck(String inputData){
        for(Map.Entry entry : pattern.entrySet()){
            inputData = inputData.replace(entry.getKey().toString(),entry.getValue().toString());
        }
        return inputData;
    }

    public int integerDataCheck(String iData){
        int result = 0;
        try{
            result = Integer.parseInt(iData);
        }catch (NumberFormatException e) {
            log.error("int 형 변환 실패: iData("+iData+")");
        }
        return result;
    }
}
