import java.io.Serializable;

/*
 *자전거
 */
public class Bicycle implements Serializable{

    private String bicycleNum;    // 자전거 고유번호
    private int pay;              // 자전거 대여료
    private String bicycleBrand;  // 자전거 브랜드명
    private String bicycleModel;  // 자전거 모델명
    private String bicycleStatus; // 자전거 대여상태
    
    public Bicycle() {
    
    }

    public Bicycle(String bicycleNum2, int pay, String bicycleBrand, String bicycleModel) {
        super();
        this.bicycleNum = bicycleNum2;
        this.pay = pay;
        this.bicycleBrand = bicycleBrand;
        this.bicycleModel = bicycleModel;
        setBicycleStatus("대여가능");
    }

    public String getBicycleNum() {
        return bicycleNum;
    }
    
    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public String getBicycleBrand() {
        return bicycleBrand;
    }

    public void setBicycleBrand(String bicycleBrand) {
        this.bicycleBrand = bicycleBrand;
    }

    public String getBicycleModel() {
        return bicycleModel;
    }

    public void setBicycleModel(String bicycleModel) {
        this.bicycleModel = bicycleModel;
    }
    
    public String getBicycleStatus() {
        return bicycleStatus;
    }

    public void setBicycleStatus(String bicycleStatus) {
        this.bicycleStatus = bicycleStatus;
    }

    @Override
    public String toString() {
        return "  대여료:" + pay +"원" + "  브랜드:" + bicycleBrand
                + "  모델명:" + bicycleModel + "  대여현황:" + bicycleStatus;
    }
}

