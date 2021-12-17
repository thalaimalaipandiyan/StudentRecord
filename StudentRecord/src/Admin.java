import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;
interface Admin1{
	public void SignIn();
}
interface Admin2{
	public void SignUp();
}
public class Admin {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice;
		System.out.println("1.Admin 2.User");
		choice = sc.nextInt();
		SignIn si = new SignIn();
		SignUp su = new SignUp();
		UserSignin user = new UserSignin();
		if (choice == 1) {
			si.SignIn();
		} else if (choice == 2) {
			user.UserSignin();
		}
	}
}
class SignIn implements Admin1 {
	public  void SignIn() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Are you new Admin");
		String x = sc.next();
		if (x.equals("no")) {
			System.out.println("Sign In:");
			int flag = 0;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root","Thalai@2000@");
				Statement stmt = con.createStatement();
				System.out.println("Enter your Email:");
				String Email = sc.next();
				System.out.println("Enter the password:");
				String password = sc.next();
				ResultSet rs = stmt.executeQuery("select * from admindetails where email='" + Email + "' AND password='" + password + "'");
				while (rs.next()) {
					System.out.println("Login Successfully.");
					flag = 1;
					do {
						System.out.println("1.Insert" + "2.Update" + "3.Delete" + "4.Find" + "5.Full Record");
						System.out.println("Enter your choice");
						int ch = sc.nextInt();
						switch (ch) {
						case 1: {
							System.out.println("Enter rollno, name, Deptname, email, gender, Dob, Address, rollno, English, Maths, Physics, ElectronicDevices, Cgpa");
							for (int i = 0; i < 1; i++) {
								String rollno = sc.next();
								String name = sc.next();
								String Deptname = sc.next();
								String email = sc.next();
								String gender = sc.next();
								String Dob = sc.next();
								DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
								LocalDate today = LocalDate.now();
								LocalDate  d1 = LocalDate.parse(Dob, df);
								if((d1.isAfter(today))){
									System.out.println("Enter valid Dob");}
								else {
								String Address = sc.next();
								rollno = sc.next();
								String English = sc.next();
								String Maths = sc.next();
								String Physics = sc.next();
								String ElectronicDevices = sc.next();
								String Cgpa = sc.next();
                                PreparedStatement ps=con.prepareStatement("insert into student values(?,?,?,?,?,?,?)");
                                PreparedStatement ps1=con.prepareStatement("insert into student1 values(?,?,?,?,?,?)");
                                ps.setString(1, rollno);
                                ps.setString(2, name);
    							ps.setString(3, Deptname);
    							ps.setString(4, email);
    							ps.setString(5, gender);
    							ps.setString(6, Dob);
    							ps.setString(7, Address);
    							ps.executeUpdate();
    							ps1.setString(1, rollno);
    							ps1.setString(2, English);
    							ps1.setString(3, Maths);
    							ps1.setString(4, Physics);
    							ps1.setString(5, ElectronicDevices);
    							ps1.setString(6, Cgpa);
    							ps1.executeUpdate();
						     	System.out.println("Database inserted....");
								}
						}break;
							}
						
						case 2: {
							System.out.println("Enter rollno, name, Deptname, email, gender, Dob, Address, rollno, English, Maths, Physics, ElectronicDevices, Cgpa");
							String rollno = sc.next();
							String name = sc.next();
							String deptname = sc.next();
							String email = sc.next();
							String gender = sc.next();
							String Dob = sc.next();
							//LocalDate dd=LocalDate.
							
							DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
							LocalDate today = LocalDate.now();
							LocalDate  d1 = LocalDate.parse(Dob, df);
						//	LocalDate d2=LocalDate.parse(dd,df);
							if((d1.isAfter(today))){
								System.out.println("Enter valid Dob");
								}
							else {
							String Address = sc.next();
							String English = sc.next();
							String Maths = sc.next();
							String Physics = sc.next();
							String ElectronicDevices = sc.next();
							String Cgpa = sc.next();
							PreparedStatement ps = con.prepareStatement("UPDATE student INNER JOIN student1 ON student1.rollno=student.rollno SET student.name=?,student.Deptname=?,student.email=?,student.gender=?,student.Dob=?,student.Address=?,student1.English=?,student1.Maths=?,student1.Physics=?,student1.ElectronicDevices=?,student1.Cgpa=? where student.rollno=?;");
							ps.setString(1, name);
							ps.setString(2, deptname);
							ps.setString(3, email);
							ps.setString(4, gender);
							ps.setString(5, Dob);
							ps.setString(6, Address);
							ps.setString(7, English);
							ps.setString(8, Maths);
							ps.setString(9, Physics);
							ps.setString(10, ElectronicDevices);
							ps.setString(11, Cgpa);
							ps.setString(12, rollno);
							ps.executeUpdate();
							System.out.println("Database updated....");
							}break;
						}
						case 3: {
							String rollno = sc.next();
							PreparedStatement ps1 = con.prepareStatement("DELETE student,student1 from student LEFT JOIN student1 ON student.rollno=student1.rollno where student.rollno=?;");
							ps1.setString(1, rollno);
							ps1.executeUpdate();
							System.out.println("Database deleted....");
							break;
						}
						case 4: {
							System.out.print("Enter your rollno:");
							Scanner sc1 = new Scanner(System.in);
							String rollno = sc1.next();						
							ResultSet rs1=stmt.executeQuery("Select student.*,student1.* FROM student LEFT JOIN student1 ON student.rollno=student1.rollno where student.rollno='"+rollno+"' AND student1.rollno='"+rollno+"'");
							while (rs1.next()) {
								System.out.println("name :" + rs1.getString(2));
								System.out.println("deptname :" + rs1.getString(3));
								System.out.println("email :" + rs1.getString(4));
								System.out.println("gender :" + rs1.getString(5));
								System.out.println("Dob :" + rs1.getString(6));
								System.out.println("Address :" + rs1.getString(7));
								System.out.println("English :" + rs1.getString(9));
								System.out.println("Maths :" + rs1.getString(10));
								System.out.println("Physics :" + rs1.getString(11));
								System.out.println("Electronic Device :" + rs1.getString(12));
								System.out.println("Cgpa :" + rs1.getString(13));
							}
							break;
						}
						case 5:{
							 rs = stmt.executeQuery("Select student.*,student1.* FROM student LEFT JOIN student1 ON student.rollno=student1.rollno");
					         System.out.println("rollno  name    Deptname     email           gender           Dob             Address        English        Maths        Physics        ED     Cgpa");
					         while (rs.next()) {
					            String rollno = rs.getString("rollno");
					            String name = rs.getString("name");
					            String Deptname = rs.getString("deptname");
					            String email = rs.getString("email");
					            String gender = rs.getString("gender");
					            String Dob = rs.getString("dob");
					            String Address = rs.getString("Address");
					            String English = rs.getString("English");
					            String Maths = rs.getString("Maths");
					            String Physics = rs.getString("Physics");
					            String ElectronicDevices = rs.getString("ElectronicDevices");
					            String Cgpa = rs.getString("Cgpa");
					            System.out.println(rollno+"     "+name+"     "+Deptname+"      "+email+"      "+gender+"       "+Dob+"        "+Address+"      "+English+"       "+Maths+"        "+Physics+"    "+ElectronicDevices+"          "+Cgpa);
					         }
							break;
						}
						default: {
							return;
						}
						}
					} while (true);
				}
				if (flag == 0)
					System.out.println("Login failed___Invalid email or password***");
			} catch (Exception e) {
				System.out.println(e);
			}
		} else if (x.equals("yes")) {
			SignUp su = new SignUp();
			su.SignUp();
		}
	}
}
class SignUp implements Admin2 {
	public void SignUp() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "Thalai@2000@");
			Statement stmt = con.createStatement();
			int f = 0;
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the name:");
			String name = sc.nextLine();
			if((name.matches(".*[a-z]{1,}.*") || name.matches(".*[A-Z]{1,}.*")) && ((! name.matches(".*[0-9]{1,}.*") && ! name.matches(".*[@#$()!~%^&|*]{1,}.*"))))
			{
			System.out.println("Enter your Email:");
			String email = sc.nextLine();
			if(email.matches("^[a-zA-Z0-9_+&*-]+(?:\\."+"[a-zA-Z0-9_+&*-]+)*@" +"(?:[a-zA-Z0-9-]+\\.)+[a-z"+"A-Z]{2,7}$")&&email.matches("@gmail.com"))
				{
				System.out.println("Enter the password:");
			    String password = sc.next();
			if(password.matches(".*[a-z]{1,}.*") && password.matches(".*[A-Z]{1,}.*") && password.matches(".*[0-9]{1,}.*") && password.matches(".*[@#$()!~%^&|*]{1,}.*") && password.length()>=8 && password.length()<=15)
			{
			// System.out.println("Enter Key:");
			// String Key = sc.next();
			// String Keyvalue = "admin345";
			// if (Keyvalue.equals(Key)) {
			ResultSet rs = stmt.executeQuery("select * from admindetails where email='" + email + "'");
			while (rs.next()) {
				f = 1;
			}
			if (f == 0) {
				stmt.executeUpdate("insert into admindetails(name,email,password)values('" + name + "','" + email + "','" + password + "') ");
				SignIn si = new SignIn();
				si.SignIn();
			}
			if (f == 1) {
				System.out.println("This mail id already exists");
			} else {
				System.out.println("Invalid");
			}
		  }
			else
			System.out.println("Enter min 8 character,one uppercase,one numeric letter");
		}else
			System.out.println("Enter valid email");
				}
		else
			{ 
				System.out.println("Enter valid name");
			}
		}
			catch (Exception e) {
			System.out.println(e);
			}
		}
	}

