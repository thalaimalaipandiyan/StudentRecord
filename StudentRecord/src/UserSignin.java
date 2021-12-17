import java.sql.*;
import java.util.Scanner;

public class UserSignin extends Admin {
	void UserSignin() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "Thalai@2000@");
			Statement stmt = con.createStatement();
            System.out.print("Enter your rollno");
			Scanner sc1 = new Scanner(System.in);
			String rollno = sc1.next();
			System.out.print("Enter your password:");
			String Dob = sc1.next();
			int f = 0;
            ResultSet rs = stmt.executeQuery("select * from student where rollno= '" + rollno + "'AND  Dob= '" + Dob + "'");
			{
				while (rs.next()) {
					System.out.println("Login Successful");
					f = 1;
					do {
					System.out.print("1.Student Profile 2.Student Marklist 3.Exit");
					int choice = sc1.nextInt();
					if (choice == 1) {
						PreparedStatement ps = con.prepareStatement("select * from student where rollno='" + rollno + "'");
						rs = ps.executeQuery(); 
						while (rs.next()) {
						System.out.println("rollno :" + rs.getString(1));
						System.out.println("name :" + rs.getString(2));
						System.out.println("deptname :" + rs.getString(3));
						System.out.println("email :" + rs.getString(4));
						System.out.println("gender :" + rs.getString(5));
						System.out.println("Dob :" + rs.getString(6));
						System.out.println("Address :" + rs.getString(7));}
					} else if (choice == 2) {
						PreparedStatement ps = con.prepareStatement("select * from student1 where rollno='" + rollno + "'");
						 rs = ps.executeQuery(); 
						while (rs.next()) {
							System.out.println("Rollno :" + rs.getString(1));
							System.out.println("English :" + rs.getString(2));
							System.out.println("Maths :" + rs.getString(3));
							System.out.println("Physics :" + rs.getString(4));
							System.out.println("Electronic Device :" + rs.getString(5));
							System.out.println("Cgpa :" + rs.getString(6));
						}
					}else{
						return;
					}
				}while(true);}
				if (f == 0)
					System.out.println("Invalid Data");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
