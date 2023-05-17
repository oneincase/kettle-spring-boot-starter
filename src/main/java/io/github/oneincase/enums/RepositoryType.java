package io.github.oneincase.enums;

/**
 * 资源库类型枚举类（未用到pentaho资源库）
 */
public enum RepositoryType {

    DB("db","数据库资源库"),

    FILE("file","文件资源库");


    private String code;

    private String desc;

    RepositoryType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
