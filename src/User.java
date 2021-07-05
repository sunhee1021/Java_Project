import java.io.Serializable;

/*
 * 사용자
 */

public class User implements Serializable{
    private String userId;        // 회원 아이디
    private String userPwd;       // 회원 비밀번호
    private String userName;      // 회원이름
    private String userPhoneNum;  // 회원 전화번호
    
    public User(String userId2) {
    }
    
    public User(String userId, String userPwd, String userName, String userPhoneNum) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userPhoneNum = userPhoneNum;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhoneNum() {
        return userPhoneNum;
    }

    @Override
    public String toString() {
        return "[회원정보]  아이디:" + userId + "  이름:" + userName
                + "  핸드폰번호:" + userPhoneNum;
    }
    
}
