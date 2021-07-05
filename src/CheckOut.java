import java.io.Serializable;

/*
 * 자전거 대여 및 반납
 */

public class CheckOut implements Serializable{
    private String bicycleNum;   // 자전거 고유번호
    private String userId;       // 회원 아이디
    private String checkOutDate; // 대여일
    private String returnDate;   // 반납일
    
    public CheckOut() {
        
    }

    public CheckOut(String bicycleNum, String checkOutDate, String returnDate) {
        this.bicycleNum = bicycleNum;
        this.checkOutDate = checkOutDate;
        this.returnDate = returnDate;
    }


    public Object getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getBicycleNum() {
        return bicycleNum;
    }

    public String getUserId() {
        return userId;
     
    }
    
    public String setUserId(String string) {
        return string;
    }

    @Override
    public String toString() {
        return "자전거 번호:" + bicycleNum + "  대여일자:" + checkOutDate
                + "  반납일자:" + returnDate + "\n";
    }    
}
