package service;

import entity.Certification;
import entity.Employee;
import entity.Flight;
import util.ConnectDBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FlightService {
    private Connection connection;

    public FlightService() throws Exception {
        connection = ConnectDBUtil.openConnection();
    }


    public List<Flight> getListFlight() throws Exception {
        String sql = "SELECT MaCB,GaDi, GaDen, DoDai, GioDi, GioDen, ChiPhi FROM chuyenbay";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Flight> flights = new ArrayList();
            while (resultSet.next()) {
                Flight flight = new Flight();

                flight.setId(resultSet.getString(1));
                flight.setFrom(resultSet.getString(2));
                flight.setTo(resultSet.getString(3));
                flight.setLengthFlight(resultSet.getInt(4));
                flight.setOurGo(resultSet.getTime(5));
                flight.setOurCome(resultSet.getTime(6));
                flight.setCost(resultSet.getInt(7));

                flights.add(flight);
            }
            return flights;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //1.	Cho biết các chuyến bay đi Đà Lạt (DAD).
    public List<Flight> request1() throws SQLException {
        String sql = "SELECT MaCB,GaDi, GaDen, DoDai, GioDi, GioDen, ChiPhi FROM chuyenbay WHERE GaDen='DAD'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Flight> flights = new ArrayList();
            while (resultSet.next()) {
                Flight flight = new Flight();

                flight.setId(resultSet.getString(1));
                flight.setFrom(resultSet.getString(2));
                flight.setTo(resultSet.getString(3));
                flight.setLengthFlight(resultSet.getInt(4));
                flight.setOurGo(resultSet.getTime(5));
                flight.setOurCome(resultSet.getTime(6));
                flight.setCost(resultSet.getInt(7));

                flights.add(flight);
            }
            return flights;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //4. Cho biết các chuyến bay có độ dài đường bay nhỏ hơn 10.000km và lớn hơn 8.000km.
    public List<Flight> request4() throws SQLException {
        String sql = "SELECT MaCB,GaDi, GaDen, DoDai, GioDi, GioDen, ChiPhi FROM chuyenbay WHERE DoDai>8000 AND DoDai<10000";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Flight> flights = new ArrayList();
            while (resultSet.next()) {
                Flight flight = new Flight();

                flight.setId(resultSet.getString(1));
                flight.setFrom(resultSet.getString(2));
                flight.setTo(resultSet.getString(3));
                flight.setLengthFlight(resultSet.getInt(4));
                flight.setOurGo(resultSet.getTime(5));
                flight.setOurCome(resultSet.getTime(6));
                flight.setCost(resultSet.getInt(7));

                flights.add(flight);
            }
            return flights;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //5. Cho biết các chuyến bay xuất phát từ Sài Gòn (SGN) đi Ban Mê Thuộc (BMV).
    public List<Flight> request5() throws SQLException {
        String sql = "SELECT MaCB,GaDi, GaDen, DoDai, GioDi, GioDen, ChiPhi FROM chuyenbay WHERE GaDi='SGN' AND GaDen='BMV'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Flight> flights = new ArrayList();
            while (resultSet.next()) {
                Flight flight = new Flight();

                flight.setId(resultSet.getString(1));
                flight.setFrom(resultSet.getString(2));
                flight.setTo(resultSet.getString(3));
                flight.setLengthFlight(resultSet.getInt(4));
                flight.setOurGo(resultSet.getTime(5));
                flight.setOurCome(resultSet.getTime(6));
                flight.setCost(resultSet.getInt(7));

                flights.add(flight);
            }
            return flights;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //6.Có bao nhiêu chuyến bay xuất phát từ Sài Gòn (SGN).
    public int request6() throws Exception {
        int count = 0;
        for (Flight flight : getListFlight()) {
            if (flight.getFrom().equals("SGN")) {
                count++;
            }
        }
        return count;
    }


    //14.Cho biết các chuyến bay có thể ñược thực hiện bởi máy bay Airbus A320.
    public void request14() throws Exception {
        String sql = "SELECT MaCB FROM chuyenbay WHERE DoDai < \n" +
                "(SELECT TamBay FROM maybay WHERE Loai = 'Airbus A320')";
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

