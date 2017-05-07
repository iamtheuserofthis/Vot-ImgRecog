import java.util.Date;

public class Voter {
    private int voterid;
    private Date dob;
    private String name;
    private String gender;

    public int getVoterid() {
        return voterid;
    }

    public Date getDob() {
        return dob;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
        
    }

    public void setVoterid(int voterid) {
        this.voterid = voterid;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    
    
}
