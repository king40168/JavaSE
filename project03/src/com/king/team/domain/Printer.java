package com.king.team.domain;

/**
 * @Author Manix
 * @Description //TODO $打印机类
 * @Param name打印机名称   type设备类型
 * @return
 */
public class Printer implements Equipment {

    private String name;
    private String type;

    public Printer(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getDescription() {
        return type + "(" + name + ")";
    }
}
