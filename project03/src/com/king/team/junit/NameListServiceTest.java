package com.king.team.junit;

import com.king.team.domain.Employee;
import com.king.team.service.NameListService;
import com.king.team.service.TeamException;
import org.junit.Test;

/**
 * @Author Manix
 * @Description 单元测试
 * @Param
 * @return
 */
public class NameListServiceTest {

    @Test
    public void testGetAllEmployee() {
        NameListService service = new NameListService();
        Employee[] employees = service.getAllEmployees();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void testGetEmployee() {
        NameListService service = new NameListService();
        int id = 2;
        try {
            Employee employee = service.getEmployee(id);
            System.out.println(employee);
        } catch (TeamException e) {
            System.out.println(e.getMessage());
        }
    }

}
