import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;

public class EmpDao {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String User = "root";
			String Pass = "";
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bai4.2", User, Pass);
		}catch(Exception e) {
			System.out.print(e);
		}
		return con;
	}
	
	public static List<Emp> getAllEmployee() {
		List <Emp> list = new ArrayList<Emp>();
		try {
			Connection con = EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from nhanvien");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Emp e = new Emp();
				e.setId(rs.getInt(1));  
                e.setName(rs.getString(2));
                e.setPassword(rs.getString(3));
                e.setEmail(rs.getString(4));
                e.setCountry(rs.getString(5));  
                list.add(e); 
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static int Save(Emp e) {
		int status = 0;
		try {
			Connection con = EmpDao.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into nhanvien (NAME, PASSWORD, EMAIL, COUNTRY) values (?, ?, ?, ?)");
			ps.setString(1, e.getName());
			ps.setString(2, e.getPassword());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getCountry());
			status = ps.executeUpdate();
			con.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}
	
	public static int Update(Emp e) {
		int status = 0;
		try {
			Connection con = EmpDao.getConnection();
			PreparedStatement ps = con.prepareStatement("update nhanvien set NAME=?, PASSWORD=?, EMAIL=?, COUNTRY=?");
			ps.setString(1, e.getName());
			ps.setString(2, e.getPassword());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getCountry());
			
			status = ps.executeUpdate();
			
			con.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}
	
	public static Emp getEmployeeById(int id){  
        Emp e=new Emp();  
        try{  
            Connection con=EmpDao.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from nhanvien where id=?");  
            ps.setInt(1,id);  
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){  
                e.setId(rs.getInt(1));  
                e.setName(rs.getString(2));  
                e.setPassword(rs.getString(3));  
                e.setEmail(rs.getString(4));  
                e.setCountry(rs.getString(5));  
            }  
            con.close();  
        }catch(Exception ex){
        	ex.printStackTrace();
        }  
          
        return e;  
    }  
	
	public static int Delete(int id) {
		int status = 0;
		try {
			Connection con = EmpDao.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from nhanvien where id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
			con.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}
	
	public static List<Emp> searchName(String txtSearch) {
		List<Emp> list = new ArrayList<>();
		
		String query = "SELECT * FROM nhanvien where NAME like \"%" +txtSearch+ "%\";";
		try {
			Connection con = EmpDao.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			//ps.setString(1, "%" + txtSearch + "%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Emp e = new Emp();
				e.setId(rs.getInt(1));  
                e.setName(rs.getString(2));
                e.setPassword(rs.getString(3));
                e.setEmail(rs.getString(4));
                e.setCountry(rs.getString(5));  
                list.add(e); 
			}
			con.close();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
}


