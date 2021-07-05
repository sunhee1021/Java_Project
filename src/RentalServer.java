import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * 데이터 저장
 */

public class RentalServer {
    
    // 회원정보, 자전거정보, 대여정보 저장할 객체 선언
    private Map<String, User> userInfo = new HashMap<String, User>();
    private Map<String, Bicycle> bicycleInfo = new HashMap<String, Bicycle>();
    private List<CheckOut> checkoutInfo = new ArrayList<CheckOut>();
    
    
    // 파일 저장 및 불러오기 경로지정
    private static final String USER_FILE = "user_file.txt";          // 회원정보 파일
    private static final String BICYCLE_FILE = "bicycle_file.txt";    // 자전거정보 파일
    private static final String CHECKOUT_FILE = "checkout_file.txt";  // 대여목록 파일
    
    
    Calendar cal; // 날짜
    SimpleDateFormat dateformat; // 날짜형식
    String today;// 대여일자(오늘)
    String dueday; // 반납일자
    
    public RentalServer() {
        this.cal = Calendar.getInstance();
        this.dateformat = new SimpleDateFormat("yyyy-MM-dd");
        this.today = dateformat.format(cal.getTime());
    }
    
    // 회원정보, 자전거정보, 대여목록 파일객체 생성
    @SuppressWarnings("unchecked")
    public void recordFile() {
        File userFile = new File(USER_FILE);
        File bicycleFile = new File(BICYCLE_FILE);
        File checkoutFile = new File(CHECKOUT_FILE);
        
        Object obj = null;
        
        // 회원정보파일이 존재한다면
        if(userFile.exists()) {
            obj = deSerialization(USER_FILE); // 역직렬화한 파일을 
            this.userInfo = (Map<String, User>) obj; // 회원정보에 할당         
        }
        
        // 자전거정보파일이 존재한다면
        if(bicycleFile.exists()) {
            obj = deSerialization(BICYCLE_FILE);
            this.bicycleInfo = (Map<String, Bicycle>) obj; // 자전거정보에 할당
        }
        
        // 대여목록파일이 존재한다면
        if(checkoutFile.exists()) {
            obj = deSerialization(CHECKOUT_FILE);
            this.checkoutInfo = (List<CheckOut>) obj; // 대여목록에 할당
        }
        
    }
    
    // 역직렬화 : 스트림으로부터 직렬화된 데이터를 읽어서 다시 객체를 만드는 과정
    public Object deSerialization(String fileName) {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ObjectInputStream ois = null;
        Object result = null; // 역직렬화한 객체를 반환하기 위한 변수
        try {
            
            fis = new FileInputStream(fileName);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);
            result = ois.readObject(); // 직렬화된 파일을 역직렬화해서 읽기 
            //Map m1 = (HashMap<String, User>)ois.readObject();
            
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                
                ois.close();
                bis.close();
                fis.close();
                
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return result;        
    }
    
    // 로그아웃(직렬화) : 로그아웃시 객체에 저장된 데이터를 스트림에 쓰기 위해 연속적인 데이터로 변환하여 파일로 저장
    public void logout() {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ObjectOutputStream oos = null;
        
        try {
            // 회원정보안에 정보가 들어있다면
            if(this.userInfo.size() > 0) {
                fos = new FileOutputStream(USER_FILE, true); // user_file.txt 생성 및 이어쓰기
                bos = new BufferedOutputStream(fos);
                oos = new ObjectOutputStream(bos);
                oos.writeObject(this.userInfo); // userInfo를 직렬화해서 user_file.txt에 쓰기
            }
            
            // 자전거정보
            if(this.bicycleInfo.size() > 0) { // bicycle_file.txt 생성 및 이어쓰기
                fos = new FileOutputStream(BICYCLE_FILE);
                bos = new BufferedOutputStream(fos);
                oos = new ObjectOutputStream(bos);
                oos.writeObject(this.bicycleInfo); // bicycleInfo를 직렬화해서 bicycle_file.txt에 쓰기
            }
            
            // 대여정보
            if(this.checkoutInfo.size() > 0) { // checkout_file.txt 생성 및 이어쓰기
                fos = new FileOutputStream(CHECKOUT_FILE);
                bos = new BufferedOutputStream(fos);
                oos = new ObjectOutputStream(bos);
                oos.writeObject(this.checkoutInfo); // checkOutInfo를 직렬화해서 checkout_file.txt에 쓰기              
            }            
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                oos.close();
                bos.close();
                fos.close();
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }
    
    // 회원가입
    public void memberRegister(String userId, String userPwd, String userName, String userPhoneNum) {
        String userIdKey = userId;
        User user = new User(userIdKey, userPwd, userName, userPhoneNum);
        if(!userInfo.containsKey(userIdKey)) {
            this.userInfo.put(userIdKey, user);
            System.out.println();
            System.out.println("회원가입이 완료되었습니다!");
        }
    }
//    public void memberRegister(String userId, String userPwd, String userName, String userPhoneNum) {
//        String userIdKey = userId;
//        User user = new User(userIdKey, userPwd, userName, userPhoneNum);
//        if(userInfo.containsKey(userIdKey)) {
//            System.out.println("이미 사용중인 아이디입니다.");
//            return;
//        }else {
//            this.userInfo.put(userIdKey, user);
//        }
//    }
    
    
    // 자전거등록
    public void bicycleRegister(String bicycleNum, int pay, String bicycleBrand, String bicycleModel) {        
        this.bicycleInfo.put(bicycleNum, new Bicycle(bicycleNum, pay, bicycleBrand, bicycleModel));
    }
    
    // 자전거 삭제
    public void deleteBicycle(String inputBicycleNum) {
        if (bicycleInfo.containsKey(inputBicycleNum)) {
            bicycleInfo.remove(inputBicycleNum);
            System.out.println("삭제되었습니다.");
        } else {
            System.out.println("자전거 등록번호를 잘못입력하셨습니다.");
        }
    }
    
    // 로그인 및 비밀번호 불일치
    public boolean login(String inputUserId, String inputUserPwd) {
        boolean result = false;
        if(!userInfo.containsKey(inputUserId)) {
            System.out.println("[아이디 불일치] 다시 입력하세요.");
        }else {
            if(userInfo.get(inputUserId).getUserPwd().equals(inputUserPwd)){
            System.out.println();
            System.out.println("로그인되었습니다!");
            result = true;
            }else {
            System.out.println("[비밀번호 불일치] 다시 입력하세요.");
            }            
        }
        return result;
        
    }
    
    // 아아디 중복체크
    public boolean idCheck(String userId) {
        User user = new User(userId);
        if(userInfo.containsKey(userId) == true) {
            System.out.println("이미 사용중인 아이디 입니다.");            
        }
        return false;        
    }
    
    
    // 회원 조회
    public String userSearch(String inputUserId) {
        String member = null;
        if(!userInfo.containsKey(inputUserId)) {
            System.out.println("아이디가 틀렸습니다.");
        }else {
           User user = userInfo.get(inputUserId);
           member = user.toString();           
           }
        return member;
        }
    
    
    // 대여날짜
    public void today() {                
        System.out.println("        대 여 일 : " + today);
    }
    
    // 반납날짜
    public void dueDay(int inputDueDay) {
        cal.add(Calendar.DATE, inputDueDay);
        this.dueday = dateformat.format(cal.getTime()); 
        System.out.println("        반 납 일 : " + dueday);
    }
    
    // 대여료 계산
    public void pay(String bicycleNo, int inputDueDay) {
        int pay = bicycleInfo.get(bicycleNo).getPay() * inputDueDay; 
        System.out.println("        결제 금액 : "+pay+"원");
    }
    
    
    // 자전거 대여
    public void checkOutBicycle(String inputBicycleNum, int inputDueDay) {
        this.checkoutInfo.add(new CheckOut(inputBicycleNum, today, dueday)); 
        Bicycle bicycle = this.bicycleInfo.get(inputBicycleNum);
        bicycle.setBicycleStatus("대여중");   
    }
    

    // 대여가능여부
    public boolean isCheckOutAble(String bicycleNum) {
        boolean result = false;        
        if(this.bicycleInfo.get(bicycleNum).getBicycleStatus().equals("대여중")) {
            result = true;
        }
        return result;    
    }
    

    // 대여현황
    public void totalCheckOut() {
        System.out.println();
        System.out.println(this.checkoutInfo.toString());        
    }
    
    // 자전거 반납
    public void returnBicycle(String inputBicycleNum) { //체크아웃인포에 삭제되는거
        for(CheckOut checkout : this.checkoutInfo) {
            if((checkout.getBicycleNum().equals(inputBicycleNum))){
               Bicycle bicycle = this.bicycleInfo.get(inputBicycleNum);            
            bicycle.setBicycleStatus("대여가능");
            }            
        }
    }
    

    // 전체 자전거 목록
    public void bicycleList() {
        Set<String> key = userInfo.keySet();
        System.out.println();
        System.out.println("==============================! 자전거 리스트 !==============================");
        List<String> list = new ArrayList<String>(key);

        for(Object i : bicycleInfo.keySet()) {
           System.out.println("[자전거정보] 등록번호:" + i + bicycleInfo.get(i));
        }
    }
    
    // 전체 회원 목록
    public void userList() {
        Set<String> key = userInfo.keySet();
        System.out.println();
        System.out.println("==============================! 회원 리스트 !==============================");
        List<String> list = new ArrayList<String>(key);

        for(Object i : userInfo.keySet()) {
           System.out.println(userInfo.get(i));
        }
     }
}
    
    
    
    
