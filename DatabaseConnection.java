import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//import com.mysql.jdbc.PreparedStatement;

public class DatabaseConnection
{
  private static double moneyFromVendingMachine = 0;
  //private  PreparedStatement p = null;
  Connection conn;

  public DatabaseConnection()
  {

  }
  //return the connection
  public Connection getConnection() throws Exception
  {
    String driver = "com.mysql.jdbc.Driver";
    Class.forName(driver);
    String userName = "Tim";		// userName in mysql of user Tim
    String password = "1234";		// password in mysql
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/student",userName, password);//student is database
    return con;
  }

  // this method get the string the password that match related input ID
  public String getStudentPassword(int inputID) throws Exception
  {
    String password = null;
    Statement st = getConnection().createStatement();
    String sql = "select * from Student ";		// from table Student
    ResultSet rs = st.executeQuery(sql);
    while(rs.next())
    {
      // check Student ID and password
      if(inputID == rs.getInt(2))
      {
        password = rs.getString(3);
      }
    }
    getConnection().close();		// close the connection
    return password;
  }


  //this method get the balance of student that matched related input ID
  public double getStudentBalance(int inputID) throws Exception
  {
    double balance = 0;
    Statement st = getConnection().createStatement();
    String sql = "select * from Student ";		// from table Student
    ResultSet rs = st.executeQuery(sql);
    while(rs.next())
    {
      // check Student ID and password
      if(inputID == rs.getInt(2))
      {
        balance = rs.getDouble(4);
      }
    }
    getConnection().close();		// close the connection
    return balance;
  }

  // displays the data of Student table
  public String getStudentData(int studentID, String password) throws Exception
  {
    // get connection and create a statement
    String StringStudentInformation = null;
    Statement st = getConnection().createStatement();
    String sql = "select * from Student ";		// from table Student
    ResultSet rs = st.executeQuery(sql);
    while(rs.next())
    {
      // check Student ID and password
      if(studentID == rs.getInt(2) && password.equals((Object)rs.getString(3)))
      {
        StringStudentInformation = rs.getString(1) + ":" + rs.getString(2) + ":"+ rs.getDouble(4);
      }
    }
    getConnection().close();		// close the connection
    return StringStudentInformation;
  }

  //check whether still items leave in the database.
  public boolean checkItemsLeave(String itemID) throws Exception
  {
    boolean check = true;
    Statement st = getConnection().createStatement();
    String sql = "select * from items ";	// table items
    ResultSet rs = st.executeQuery(sql);
    while(rs.next())
    {
      // check Student ID and get data
      if(itemID.equals(rs.getInt(2)))
      {
        if(rs.getString(4) == "0")	// if no item leaves, return false
          check = false;
      }
    }
    getConnection().close();		// close the connection
    return check;
  }

  // return the price of input item
  public String getItemsData(String itemID) throws Exception
  {
    String price = null;
    Statement st = getConnection().createStatement();
    String sql = "select * from items ";	// table items
    ResultSet rs = st.executeQuery(sql);
    while(rs.next())
    {
      // check Student ID and get data
      if(itemID.equals(rs.getString(2)))
      {
        price = rs.getString(3);
      }
    }
    getConnection().close();		// close the connection
    return price;
  }


  public ArrayList<String> getAllItemInformation() throws Exception
  {
    ArrayList<String> arraylist = new ArrayList<String>();
    Statement st = getConnection().createStatement();
    String sql = "select * from items";
    ResultSet rs = st.executeQuery(sql);
    while(rs.next())
    {
      for(int i = 1; i <= 9; i++)
      {
        arraylist.add(rs.getString(i)+":");	// all the information added into the arraylist with ":"
      }
    }
    return arraylist;
  }

  public void getMoneyBoxData(int input) throws Exception
  {
    Statement st = getConnection().createStatement();
    String sql = "select * from MoneyBox";
    ResultSet rs = st.executeQuery(sql);
    while(rs.next())
    {
      // check Student ID and get data
      if(input == rs.getInt(1))
      {
        System.out.println(rs.getDouble(2));
      }
    }
    getConnection().close();		// close the connection
  }


  // output is the index of data,  newData is the new balance to be updated.
  public void updateStudent(int IDnumber, double newData)
      throws SQLException, Exception
  {

  }

  // this method only update the data of number of item leave.
  // when the transaction is finished, this should be updated.
  public void updateItems(int IDnumber) throws SQLException, Exception
  {

  }


  // this method only works for vendingMachine, when vendingMachine updates the money box with this method, it will update into datebase
  // @methodFromVendingMoneyBox is the total money that vendingMachine provide, then vendingMachine's moneyBox will clear 0.
  public void updateMoneyBox(
      int IDnumber, double methodFromVendingingMoneyBox)
      throws SQLException, Exception
  {

  }
}
