package com.king.team.view;

import com.king.team.domain.Employee;
import com.king.team.domain.Programmer;
import com.king.team.service.NameListService;
import com.king.team.service.TeamException;
import com.king.team.service.TeamService;

/**
 * @author Manix
 * @version 1.0
 * @description: TODO 团队显示控制类
 * @date 2021/7/24 14:33
 */
public class TeamView {

    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();

    /**
     * @description: 主界面显示及控制方法
     * @author Manix
     */
    public void enterMainMenu() {
        char menu = 0;
        boolean loopFlag = true;
        while (loopFlag) {

            if (menu != '1') {
                listAllEmployees();
            }

            System.out.print("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：");
            menu = TSUtility.readMenuSelection();
            switch (menu) {
                case '1':
                    getTeam();
                    break;
                case '2':
                    addMember();
                    break;
                case '3':
                    deleteMember();
                    break;
                case '4':
                    System.out.print("确认是否退出(Y/N)：");
                    char isExit = TSUtility.readConfirmSelection();
                    if (isExit == 'Y') {
                        loopFlag = false;
                    }
                    break;
            }

        }

    }

    /**
     * @description: 以表格显示列出所有成员
     * @author Manix
     */
    private void listAllEmployees() {
        System.out.println("-------------------------------------开发团队调度软件--------------------------------------\n");

        Employee[] employees = listSvc.getAllEmployees();
        if (employees == null || employees.length == 0) {
            System.out.println("公司中没有任何员工的信息");
        } else {
            System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");
            for (int i = 0; i < employees.length; i++) {
                System.out.println(employees[i]);
            }


            System.out.println("-----------------------------------------------------------------------------------------\n");

        }
    }

    /**
     * @description: 显示成员列表操作
     * @author Manix
     */
    private void getTeam() {
        System.out.println("--------------------团队成员列表---------------------\n");
        Programmer[] team = teamSvc.getTeam();
        if (team == null || team.length == 0) {
            System.out.println("开发团队目前没有成员！");
        } else {
            System.out.println("TDI/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票");
            for (Programmer p : team) {
                System.out.println(p.getDetailsForTeam());
            }
        }
        System.out.println("---------------------------------------------------\n");
    }

    /**
     * @description: 实现添加成员操作
     * @author Manix
     */
    private void addMember() {

        System.out.println("---------------------添加成员---------------------\n");
        System.out.print("请输入要添加的员工ID：");
        int id = TSUtility.readInt();

        try {
            Employee emp = listSvc.getEmployee(id);
            teamSvc.addMember(emp);
            System.out.println("添加成功");

        } catch (TeamException e) {
            System.out.println("添加失败，原因：" + e.getMessage());
        }
        //按回车键继续...
        TSUtility.readReturn();

    }


    /**
     * @description: 实现删除成员操作
     * @author Manix
     */
    private void deleteMember() {
        System.out.println("---------------------删除成员---------------------\n");
        System.out.println("请输入要删除员工的TID：");
        int memberId = TSUtility.readInt();
        System.out.println("确认是否删除(Y/N)：");

        char isDelete = TSUtility.readConfirmSelection();

        if (isDelete == 'N') {
            return;
        }

        Employee emp = null;
        try {
            teamSvc.removeMember(memberId);
            System.out.println("删除成功");
        } catch (TeamException e) {
            System.out.println("删除失败，原因是：" + e.getMessage());
        }
        //按回车键继续...
        TSUtility.readReturn();
    }

    public static void main(String[] args) {
        TeamView teamView = new TeamView();
        teamView.enterMainMenu();
    }

}
