package service;

import entity.Certification;
import entity.Employee;
import entity.Plane;
import util.ConnectDBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlaneService {
    private Connection connection;

    public PlaneService() throws Exception {
        connection = ConnectDBUtil.openConnection();
    }


    public List<Plane> getListPlane() throws Exception {
        String sql = "SELECT MaMB,Loai,TamBay FROM maybay";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Plane> planes = new ArrayList();
            while (resultSet.next()) {
                Plane plane = new Plane();

                plane.setId(resultSet.getInt(1));
                plane.setType(resultSet.getString(2));
                plane.setFlightRange(resultSet.getInt(3));

                planes.add(plane);
            }
            return planes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //  2.	Cho biết các loại máy bay có tầm bay lớn hơn 10,000km.
    public List<Plane> request2() throws Exception {
        String sql = "SELECT MaMB,Loai,TamBay FROM maybay WHERE TamBay>10000";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Plane> planes = new ArrayList();
            while (resultSet.next()) {
                Plane plane = new Plane();

                plane.setId(resultSet.getInt(1));
                plane.setType(resultSet.getString(2));
                plane.setFlightRange(resultSet.getInt(3));

                planes.add(plane);
            }
            return planes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //7. Có bao nhiêu loại máy báy Boeing.
    public int request7() throws Exception {
        int count =0;
        for (Plane plane: getListPlane()){
            if (plane.getType().contains("Boeing")){
                count++;
            }
        }
        return count;
    }
    //13. Cho biết các loại máy bay có thể thực hiện chuyến bay VN280.
    public void request13() throws Exception {
        String sql = "SELECT Loai FROM maybay WHERE TamBay >\n" +
                "(SELECT DoDai FROM chuyenbay WHERE MaCB = 'VN280')";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Certification> certifications = new ArrayList();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
