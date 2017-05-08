import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
public class DBManager {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String ADDR = "jdbc:mysql://localhost:3306/voting_project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    
    static{
            try {
                    Class.forName(DRIVER);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null,"Drivers not found");
                }
          }
    
    public static Voter getDetails(int voterid)
    {
      Voter v = null;
        try(Connection con = DriverManager.getConnection(ADDR,USERNAME,PASSWORD))
        {
            String query = "Select * from login where VoterID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, voterid);
            ResultSet rs = ps.executeQuery();
             
            while(rs.next())
            {
              v = new Voter();
              v.setVoterid(rs.getInt(1));
              v.setDob(rs.getDate(2));
              v.setName(rs.getString(3));
              v.setGender(rs.getString(4));
                
            } 
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    return v;
}
    
    public static void castVote(int voterid,String partyname,java.util.Date d)
    {
        try(Connection con = DriverManager.getConnection(ADDR,USERNAME,PASSWORD))
        {
            SimpleDateFormat dat,tim;
            java.sql.Date dte = new java.sql.Date(d.getTime());
            java.sql.Time vottimes= new java.sql.Time(d.getTime());
            //dat = new SimpleDateFormat("yy-MM-dd");
            
            String query = "update castavote set "+partyname+"=1,DOV = ?,TOV=? where VoterID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDate(1,dte);
            ps.setTime(2, vottimes);
            ps.setInt(3, voterid);
            ps.executeUpdate();
                        
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public static boolean seeEligibility(int voterid)
    {
        boolean status = false ;
        java.util.Date datedb=null;
        try(Connection con = DriverManager.getConnection(ADDR,USERNAME,PASSWORD))
        {
         String query = "select DOV from castavote where VoterID = ?";   
         PreparedStatement ps = con.prepareStatement(query);
         ps.setInt(1, voterid);
         ResultSet rs = ps.executeQuery();
         if(rs.next()){
         datedb = rs.getDate(1);
         status = false;
         }
         System.out.println(datedb.toString());
            
        }catch(NullPointerException ex)
        {
            status = true;
        }
        catch(Exception exc)
        {
            JOptionPane.showMessageDialog(null, exc, "Warn",JOptionPane.ERROR_MESSAGE );
        }
       return status;
    }
    
    public static int getIndiResult(String parname)
    {
        int resu=0;
        try(Connection con = DriverManager.getConnection(ADDR,USERNAME,PASSWORD)){
        String query = "select sum("+parname+") from castavote" ;   
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
         resu = rs.getInt(1);
        // JOptionPane.showMessageDialog(null," :"+resu);
        }
       }catch(Exception exp){
            JOptionPane.showMessageDialog(null,"Error :"+exp);
        }
        return resu;
    }
    
    public static String getRole(int voterid){
        String rolename = null;
        try(Connection con = DriverManager.getConnection(ADDR,USERNAME,PASSWORD);){
            String query = "select RoleName from users where VoterID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, voterid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                rolename=rs.getString(1);
            }
            
        }catch(Exception exp){
            JOptionPane.showMessageDialog(null, exp);
        }
        
        
        return rolename;
    }
    
    public static boolean loginUsers(String username,String pass,int voterid,int head){
        boolean status = false;
        try(Connection con = DriverManager.getConnection(ADDR,USERNAME,PASSWORD)){
            String query ="select * from users where Username=? and VoterID=? and Pass=password(?) and Pichead=?;";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,username);
            ps.setInt(2,voterid);
            ps.setString(3,pass);
            ps.setInt(4, head);
            
          ResultSet rs=ps.executeQuery();
          if(rs.next()){
              status = true;
          }
          else{
              JOptionPane.showMessageDialog(null, "Login not Successful");
          }
          
            
        }catch(Exception exp){
            JOptionPane.showMessageDialog(null,exp);
        }
        
        return status;
    }
}
