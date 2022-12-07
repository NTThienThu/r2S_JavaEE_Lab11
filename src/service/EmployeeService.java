package service;

import entity.Employee;
import util.ConnectDBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private Connection connection;

    public EmployeeService() throws Exception {
        connection = ConnectDBUtil.openConnection();
    }



    public List<Employee> getListEmployee() throws Exception {
        String sql = "SELECT MaNV, Ten, Luong FROM nhanvien";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Employee> employees = new ArrayList();
            while (resultSet.next()) {
                Employee employee = new Employee();

                employee.setId(resultSet.getString(1));
                employee.setName(resultSet.getString(2));
                employee.setSalary(resultSet.getInt(3));

                employees.add(employee);
            }
            return employees;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertEmployee(String id, String name, int salary) throws Exception {
        String sql = "INSERT INTO nhanvien(MaNV, Ten, Luong)\n" +
                "VALUES('" + id + "','" + name + "'," + salary + ")";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3.Tìm các nhân viên có lương nhỏ hơn 10,000.
    public List<Employee> request3() throws Exception {
        String sql = "SELECT MaNV, Ten, Luong FROM nhanvien WHERE Luong<10000";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Employee> employees = new ArrayList();
            while (resultSet.next()) {
                Employee employee = new Employee();

                employee.setId(resultSet.getString(1));
                employee.setName(resultSet.getString(2));
                employee.setSalary(resultSet.getInt(3));

                employees.add(employee);
            }
            return employees;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //8. Cho biết tổng số lương phải trả cho các nhân viên.
    public int request8() throws Exception {
        int count = 0;
        for (Employee employee: getListEmployee()){
            count+=employee.getSalary();
        }
        return count;
    }
}
