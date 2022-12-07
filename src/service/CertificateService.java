package service;

import entity.Certification;
import entity.Employee;
import entity.Flight;
import entity.Plane;
import util.ConnectDBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CertificateService {
    private Connection connection;

    public CertificateService() throws Exception {
        connection = ConnectDBUtil.openConnection();
    }


    public List<Certification> getListCertificate() throws Exception {
        String sql = "SELECT MaNV, MaMB FROM chungnhan";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Certification> certifications = new ArrayList();
            while (resultSet.next()) {
                Certification certification = new Certification();

                certification.setIdE(resultSet.getString(1));
                certification.setIdP(resultSet.getString(2));

                certifications.add(certification);

            }
            return certifications;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //9. Cho biết mã số của các phi công lái máy báy Boeing.
    public void request9() throws Exception {
        String sql = "SELECT MaNV FROM chungnhan JOIN maybay ON chungnhan.MaMB= maybay.MaMB WHERE maybay.Loai LIKE('%Boeing%')";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Certification> certifications = new ArrayList();
            while (resultSet.next()) {
                System.out.print(resultSet.getString(1)+"\t");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //10.Cho biết các nhân viên có thể lái máy bay có mã số 747.
    public void request10() throws Exception {
        String sql = "SELECT nhanvien.MaNV, nhanvien.Ten, nhanvien.Luong FROM nhanvien JOIN chungnhan  \n" +
                "ON chungnhan.MaNV= nhanvien.MaNV \n" +
                "WHERE MaMB=747";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Certification> certifications = new ArrayList();
            while (resultSet.next()) {
                System.out.println("Ma NV: "+resultSet.getString(1) + "\tTen: "+resultSet.getString(2)+"\tLuong"+resultSet.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //11.Cho biết mã số của các loại máy bay mà nhân viên có họ Nguyễn có thể lái.
    public void request11() throws Exception {
        String sql = "SELECT chungnhan.MaMB FROM nhanvien JOIN chungnhan  \n" +
                "ON chungnhan.MaNV= nhanvien.MaNV \n" +
                "WHERE nhanvien.Ten LIKE('%Nguyen%')";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Certification> certifications = new ArrayList();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   // 12.	Cho biết Mã số của các phi công vừa lái được Boeing vừa lái được Airbus.
   public void request12() throws Exception {
       String sql = "SELECT DISTINCT nv.MaNV, TEN FROM NHANVIEN nv JOIN CHUNGNHAN cn\n" +
               "ON nv.MaNV = cn.MaNV \n" +
               "JOIN MAYBAY mb\n" +
               "ON mb.MaMB = cn.MaMB \n" +
               "WHERE mb.Loai LIKE 'Boeing%'AND mb.Loai LIKE 'Airbus%'";

       try {
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(sql);
           List<Certification> certifications = new ArrayList();
           while (resultSet.next()) {
               System.out.println("Ma nv: "+resultSet.getString(1)+ "\t Ten: "+resultSet.getString(2) );
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   //15.Cho biết tên của các phi công lái máy bay Boeing.
   public void request15() throws Exception {
       String sql = "SELECT DISTINCT nv.Ten FROM NHANVIEN nv JOIN CHUNGNHAN cn\n" +
               "ON nv.MaNV = cn.MaNV \n" +
               "JOIN MAYBAY mb\n" +
               "ON mb.MaMB = cn.MaMB \n" +
               "WHERE mb.Loai LIKE '%Boeing%'";

       try {
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(sql);
           List<Certification> certifications = new ArrayList();
           while (resultSet.next()) {
               System.out.println("Ten nv: "+resultSet.getString(1));
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}

