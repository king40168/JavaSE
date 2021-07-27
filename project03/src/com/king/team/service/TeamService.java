package com.king.team.service;

import com.king.team.domain.Architect;
import com.king.team.domain.Designer;
import com.king.team.domain.Employee;
import com.king.team.domain.Programmer;

/**
 * @author Manix
 * @version 1.0
 * @description: 关于开发团队成员的管理：添加、删除等。
 * @date 2021/7/24 10:59
 */
public class TeamService {

    private static int counter = 1;//给memberId复制使用
    private final int MAX_MEMBER = 5;//限制开发团队的人数
    private Programmer[] team = new Programmer[MAX_MEMBER];//保存开发团队成员
    private int total = 0;//记录开发团队中实际人数

    /**
     * @description: 返回当前团队的所有对象
     * @author Manix
     */
    public Programmer[] getTeam() {
        Programmer[] team = new Programmer[total];
        for (int i = 0; i < team.length; i++) {
            team[i] = this.team[i];
        }
        return team;
    }

    /**
     * @description: 向团队中添加成员
     * @author Manix
     */
    public void addMember(Employee e) throws TeamException {
//        成员已满，无法添加
        if (total >= MAX_MEMBER) {
            throw new TeamException("成员已满，无法添加");
        }
//        该成员不是开发人员，无法添加
        if (!(e instanceof Programmer)) {
            throw new TeamException("该成员不是开发人员，无法添加");
        }
//        该员工已在本开发团队中
        if (isExist(e)) {
            throw new TeamException("该员工已在本开发团队中");
        }
//        该员工已是某团队成员
//        该员正在休假，无法添加
        Programmer p = (Programmer) e; //一定不会出现ClassException,因为已经一定是Programmer了
        if ("BUSY".equals(p.getStatus().getNAME())) {
            throw new TeamException("该员工已是某团队成员");
        } else if ("VOCATION".equals(p.getStatus().getNAME())) {
            throw new TeamException("该员正在休假，无法添加");
        }

//        团队中至多只能有一名架构师
//        团队中至多只能有两名设计师
//        团队中至多只能有三名程序员
        int numOfArch = 0, numOfDes = 0, numOfPro = 0;
        for (int i = 0; i < total; i++) {
            if (team[i] instanceof Architect) {
                numOfArch++;
            } else if (team[i] instanceof Designer) {
                numOfDes++;
            } else if (team[i] instanceof Programmer) {
                numOfPro++;
            }
        }
        if (p instanceof Architect) {
            if (numOfArch >= 1) {
                throw new TeamException("团队中至多只能有一名架构师");
            }
        } else if (p instanceof Designer) {
            if (numOfArch >= 2) {
                throw new TeamException("团队中至多只能有两名设计师");
            }
        } else if (p instanceof Programmer) {
            if (numOfArch >= 3) {
                throw new TeamException("团队中至多只能有三名程序员");
            }
        }

        //将p（或e）添加到现有的team中
        team[total++] = p;
        //开发团队中--p的属性赋值
        p.setStatus(Status.BUSY);
        p.setMemberId(counter++);

    }

    /**
     * @description: 判断指定员工是否在本开发团队中方法
     * @author Manix
     */
    private boolean isExist(Employee e) {
        for (int i = 0; i < total; i++) {
            if (team[i].getId() == e.getId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @description: 从团队中删除成员
     * @author Manix
     */
    public void removeMember(int memberId) throws TeamException {
        int i = 0;
        for (; i < total; i++) {
            if (team[i].getMemberId() == memberId) {
                team[i].setStatus(Status.FREE);
                break;
            }
        }

        //未找到指定memberId的员工
        if (i == total) {
            throw new TeamException("找不到指定memberId的员工，删除失败");
        }

        //找到指定memberId的员工，后一个元素覆盖前一个元素，实现删除操作
        for (int j = i + 1; j < total; j++) {
            team[j - 1] = team[j];
        }
        team[--total] = null;

    }

}
