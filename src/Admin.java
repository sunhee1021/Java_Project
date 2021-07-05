import java.io.Serializable;

/*
 * 관리자
 */

public class Admin implements Serializable{
    private final String adminId = "team3";     // 관리자 아이디
    private final String adminPwd = "team3";    // 관리자 비밀번호
    private User user;                          // 사용자 정보
    
    public void setUserCurrent(User user) {
        this.user = user;
    }
    
    
    
    
    
    public Admin() {
    
    }

    public String getAdminId() {
        return adminId;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public User getUserCurrent() {
        return user;
    }
    
    

    @Override
    public String toString() {
        return "[관리자 아이디 = " + adminId + ", 관리자 비밀번호 = " + adminPwd + "]";
    }
    
    
}
