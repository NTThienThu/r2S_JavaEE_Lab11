package App;

import entity.Certification;
import entity.Employee;
import entity.Flight;
import entity.Plane;
import service.CertificateService;
import service.EmployeeService;
import service.FlightService;
import service.PlaneService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        EmployeeService employeeService = new EmployeeService();
        FlightService flightService = new FlightService();
        CertificateService certificationService = new CertificateService();
        PlaneService planeService = new PlaneService();

        String select;
        Scanner sc = new Scanner(System.in);
        System.out.print("choose your funtion: ");
//Thienthu
        do {
            System.out.println("\n_________________________________________ M E N U ___________________________________________");
            System.out.println("A" +
                    "A. Show all Employee");
            System.out.println("B. Show all Flight");
            System.out.println("C. Show all Certification");
            System.out.println("D. Show all Plane");
            System.out.println("E. Insert Employee");

            System.out.println("\t1.\tCho biết các chuyến bay đi Đà Lạt (DAD).");
            System.out.println("\t2.\tCho biết các loại máy bay có tầm bay lớn hơn 10,000km.");
            System.out.println("\t3.\tTìm các nhân viên có lương nhỏ hơn 10,000.");
            System.out.println("\t4.\tCho biết các chuyến bay có độ dài đường bay nhỏ hơn 10.000km và lớn hơn 8.000km.");
            System.out.println("\t5.\tCho biết các chuyến bay xuất phát từ Sài Gòn (SGN) đi Ban Mê Thuộc (BMV).");
            System.out.println("\t6.\tCó bao nhiêu chuyến bay xuất phát từ Sài Gòn (SGN).");
            System.out.println("\t7.\tCó bao nhiêu loại máy báy Boeing.");
            System.out.println("\t8.\tCho biết tổng số lương phải trả cho các nhân viên.");
            System.out.println("\t9.\tCho biết mã số của các phi công lái máy báy Boeing.");
            System.out.println("\t10.\tCho biết các nhân viên có thể lái máy bay có mã số 747");
            System.out.println("\t11.	Cho biết mã số của các loại máy bay mà nhân viên có họ Nguyễn có thể lái.");
            System.out.println("\t12.\tCho biết các loại máy bay có thể thực hiện mã số của các phi công vừa lái được Boeing vừa lái được Airbus.");
            System.out.println("\t13. Cho biết các loại máy bay có thể thực hiện chuyến bay VN280.");
            System.out.println("\t14.\tCho biết các chuyến bay có thể ñược thực hiện bởi máy bay Airbus A320.");
            System.out.println("\t15.\tCho biết tên của các phi công lái máy bay Boeing.");

            System.out.println("F. Exit");
            System.out.println("________________________________________________________________________________________________________________");

            select = sc.nextLine();
            switch (select) {
                case "A": {
                    List<Employee> employeeList = employeeService.getListEmployee();
                    employeeList.stream().forEach(element -> System.out.println(element.getId() + " " + element.getName()+ " " + element.getSalary()));

                    break;
                }
                case "B": {
                    List<Flight> flightList = flightService.getListFlight();
                    flightList.stream().forEach(element -> System.out.println(element.getId() +" " +element.getFrom() + " " +element.getTo() +" " +element.getLengthFlight() + " " +element.getOurGo() +" " +element.getOurCome() + " " +element.getCost()));

                    break;
                }

                case "C": {
                    List<Certification> certificationList = certificationService.getListCertificate();
                    certificationList.stream().forEach(element -> System.out.println(element.getIdE() + " " + element.getIdP()));

                    break;
                }
                case "D": {
                    List<Plane> planeList = planeService.getListPlane();
                    planeList.stream().forEach(element -> System.out.println(element.getId() + " " + element.getType()+ " " + element.getFlightRange()));

                    break;
                }

                case "E": {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Enter Employee ID: ");
                    String id = scanner.nextLine();
                    System.out.println("Enter Employee Name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter Employee Salary: ");
                    int salary = scanner.nextInt();

                    employeeService.insertEmployee(id,name,salary);

                    break;
                }
                case "1":{
                    for (Flight flight: flightService.request1()){
                        System.out.println(flight);
                    }
                    break;
                }
                case "2":{
                    for (Plane plane: planeService.request2()){
                        System.out.println(plane);
                    }
                    break;
                }
                case "3":{
                    for (Employee employee: employeeService.request3()){
                        System.out.println(employee);
                    }
                    break;
                }
                case "4":{
                    for (Flight flight: flightService.request4()){
                        System.out.println(flight);
                    }
                    break;
                }
                case "5":{
                    for (Flight flight: flightService.request5()){
                        System.out.println(flight);
                    }
                    break;
                }
                case "6":{
                    System.out.println("Số chuyến bay xuất phát từ SG : "+ flightService.request6());
                    break;
                }
                case "7":{
                    System.out.println("Số loại máy bay Boeing : "+ planeService.request7());
                    break;
                }
                case "8":{
                    System.out.println("Tổng lương phải trả cho nv: "+ employeeService.request8());
                    break;
                }
                case "9":{
                    certificationService.request9();
                    break;
                }
                case "10":{
                    certificationService.request10();
                    break;
                }
                case "11":{
                    certificationService.request11();
                    break;
                }
                case "12":{
                    certificationService.request12();
                    break;
                }
                case "13":{
                    planeService.request13();
                    break;
                }
                case "14":{
                    flightService.request14();
                    break;
                }
                case "15":{
                    certificationService.request15();
                    break;
                }
                case "F": {
                    System.out.println("Exit");
                    System.exit(0);
                }
            }
        }
        while (!select.equals(""));
    }
}