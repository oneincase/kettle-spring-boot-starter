package io.github.oneincase.core;

/**
 * 通用编码及说明
 */
public class CodeDesc<T> {

    private T code;

    private String desc;

    public CodeDesc(T code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(T code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "CodeDesc{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                '}';
    }

}
