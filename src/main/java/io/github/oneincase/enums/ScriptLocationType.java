package io.github.oneincase.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 脚本存储位置
 */
public enum ScriptLocationType {

    LOCAl_FILE(1,"本地文件"),
    REPOSITORY(2,"资源库");

    private final Integer code;

    private final String desc;

    ScriptLocationType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static List<Map<Integer,String>> getList(){
        return Arrays.stream(ScriptLocationType.values()).map(item->{
            Map<Integer,String> tempMap = new HashMap<>();
            tempMap.put(item.getCode(),item.getDesc());
            return tempMap;
        }).collect(Collectors.toList());
    }

}
