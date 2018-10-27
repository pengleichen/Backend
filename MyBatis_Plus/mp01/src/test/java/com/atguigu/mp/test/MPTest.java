package com.atguigu.mp.test;

import com.atguigu.mp.beans.Employee;
import com.atguigu.mp.mappers.EmployeeMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MPTest {

    private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    private EmployeeMapper mapper = ctx.getBean(EmployeeMapper.class);

    @Test
    public void testCommonInsert() {
        Employee employee = new Employee();
        employee.setLastName("MP");
        employee.setEmail("mp@atguigu.com");
        //employee.setGender(1);
        //employee.setAge(22);
        employee.setSalary(30000.00);
        //Integer result = mapper.insert(employee);
        Integer result = mapper.insertAllColumn(employee);
        System.out.println("result :" + result);

        Integer id = employee.getId();
        System.out.println("key : " + id);
    }

    @Test
    public void testCommonSelect() {
        //1. 根据ID查询
//        Employee employee = mapper.selectById(4);
        //2. 根据对象属性查询，只能返回一条记录，如有多条记录满足条件，则报错
//        Employee emp = new Employee();
//        emp.setId(3);
//        emp.setLastName("Black");
//        Employee employee = mapper.selectOne(emp);
        //3. 根据表中的字段对应的值进行条件查询
//        Map<String, Object> columnMap = new HashMap<String, Object>();
//        columnMap.put("last_name", "MP");
//        columnMap.put("age", 22);
//        List<Employee> employees = mapper.selectByMap(columnMap);
        // 4. 根据IDs进行批量查询
//        List<Employee> employees = mapper.selectBatchIds(Arrays.asList(2,3,4));
        //5. 利用RowBound进行内存分页查询
        List<Employee> employees = mapper.selectPage(new Page<Employee>(2, 3), null);
        System.out.println("result: " + employees);

    }

    @Test
    public void testCommonUpdate() {
        Employee employee = new Employee();
        employee.setId(9);
        employee.setLastName("小王");
        employee.setGender(0);
        //employee.setAge(33);
        //Integer result = mapper.updateById(employee);
        Integer result = mapper.updateAllColumnById(employee);
        System.out.println("result: " + result);
    }

    @Test
    public void testCommonDelete() {
//        Integer result = mapper.deleteById(11);
//        Map<String, Object> columnMap = new HashMap<String, Object>();
//        columnMap.put("age", 22);
//        Integer result = mapper.deleteByMap(columnMap);
            Integer result = mapper.deleteBatchIds(Arrays.asList(9, 10));
        System.out.println("Result: " + result);


    }

    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        Assert.assertNotNull(dataSource);

        Connection connection = dataSource.getConnection();
        Assert.assertNotNull(connection);
    }
}
