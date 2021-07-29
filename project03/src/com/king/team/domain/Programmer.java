package com.king.team.domain;

import com.king.team.service.Status;

/**
 * @Author Manix
 * @Description 程序员类
 * @Param memberId 成员编号；    status 状态；  equipment 所领用的设备
 * @return
 */

public class Programmer extends Employee {

    private int memberId;
    private Status status = Status.FREE;
    private Equipment equipment;

    public Programmer(int id, String name, int age, double salary, Equipment equipment) {
        super(id, name, age, salary);
        this.equipment = equipment;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return getDetails() + "\t程序员\t" + status + "\t\t\t" + equipment.getDescription();
    }

    public String getDetailsForTeam() {
        return getMemberDetails()+ "\t程序员";
    }

    protected String getMemberDetails(){
        return getMemberId() + "/" + getDetails();
    }


}
