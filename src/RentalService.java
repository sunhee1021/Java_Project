import java.io.Serializable;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RentalService implements Serializable{
    private RentalServer server = new RentalServer();
    private Admin admin = new Admin();
    private CheckOut checkout = new CheckOut();
    private Bicycle bicycle = new Bicycle();
    
    // 초기메뉴화면
    public void menu(Scanner sc) {
        try {
            while (true) {
                System.out.println();
                System.out.println("                         ********************");
                System.out.println("　　　　　　　　　　　    　　　 자전거 대여소");
                System.out.println("                         ********************");
                System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
                System.out.println("                        1.회원가입   2.로그인   0.종료");
                System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
                System.out.print("메뉴입력 > ");
                int input = sc.nextInt();
                sc.nextLine();                
                switch (input) {
                case 0:
                    System.out.println();
                    System.out.println("이용해주셔서 감사합니다!");
                    server.logout();
                    System.exit(0);
                case 1:
                    this.register(sc);
                    break;
                case 2:
                    this.login(sc);
                    break;
                default:
                    System.out.println("다시 입력해주세요.");
                    break;
                }                
            }            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    // 회원가입
    public void register(Scanner sc) {
        System.out.println();
        System.out.println("================================! 회원가입 !================================");
        System.out.print("아이디(숫자, 문자 포함의 3~6자리) : ");
        String userId = sc.nextLine();
        while (true) {
            try {
                // 아이디 패턴 예외처리
                this.isIdPattern(userId);
                break;
            } catch (Exception e) {
                System.out.println();
                System.out.println("잘못된형식입니다. 다시 입력해주세요.");
                System.out.print("아이디(숫자, 문자 포함의 6~8자리) : ");
                userId = sc.nextLine();
            }
        }
        System.out.print("비밀번호(숫자, 문자 포함의 6~12자리 이내) : ");
        String password = sc.nextLine();
        while (true) {
            try {
                // 비밀번호 패턴 예외처리
                this.isPwdPattern(password);
                break;
            } catch (Exception e) {
                System.out.println();
                System.out.println("잘못된형식입니다. 다시 입력해주세요.");
                System.out.print("비밀번호(숫자, 문자 포함의 6~12자리 이내) : ");
                password = sc.nextLine();
            }
        }
        System.out.print("이름 : ");
        String name = sc.nextLine();
        while (true) {
            try {
                // 아이디 패턴 예외처리
                this.isNamePattern(name);
                break;
            } catch (Exception e) {
                
                System.out.println();
                System.out.println("잘못된형식입니다. 다시 입력해주세요.");
                System.out.print("이름 : ");
                name = sc.nextLine();
            }
        }
        System.out.print("전화번호(010-1234-5678) : ");
        String phone = sc.nextLine();
        while (true) {
            try {
                // 전화번호 패턴 예외처리
                this.isPhonePattern(phone);
                break;
            } catch (Exception e) {
                System.out.println();
                System.out.println("잘못된형식입니다. 다시 입력해주세요.");
                System.out.print("전화번호(010-1234-5678) : ");
                phone = sc.nextLine();
            }
        }
        this.server.idCheck(userId);
        this.server.memberRegister(userId, password, name, phone);
        System.out.println("=========================================================================");
    }
    
    // 회원 가입시 아이디 예외처리
    private void isIdPattern(String userId) throws PatternException {
        //아이디 패턴 (영문 대소문자 + 숫자 혼용 3글자~6글자)
        String temp = "^[a-zA-Z0-9]{3,6}$";
        Boolean bool = Pattern.matches(temp, userId);
        if (!bool) {
            throw new PatternException("잘못된 아이디 형식입니다. 다시 입력해주세요.(숫자, 문자 포함의 3~6자리)\n");
        }   
    }
    
    // 회원 가입시 비밀번호 예외처리
    private void isPwdPattern(String password) throws PatternException {
        //비밀번호 패턴 (영문 소문자 + 숫자 혼용 6글자~12글자)
        String temp = "^[a-zA-Z0-9]{6,12}$";
        Boolean bool = Pattern.matches(temp, password);
        if (!bool) {
            throw new PatternException("잘못된 비밀번호 형식입니다. 다시 입력해주세요.\n");
        }
    }
    
    // 회원 가입시 이름 예외처리
    private void isNamePattern(String userName) throws PatternException {
        //이름 패턴 (영문 소문자 + 숫자 혼용 6글자~12글자)
        String temp = "^[가-힣]*$";
        Boolean bool = Pattern.matches(temp, userName);
        if (!bool) {
            throw new PatternException("잘못된 이름 형식입니다. 다시 입력해주세요.\n");
        }
    }
    
    // 회원 가입시 전화번호 예외처리
    private void isPhonePattern(String phoneNum) throws PatternException {
        // 전화번호 패턴 (010-1234-5678)
        String temp = "^\\d{3}-\\d{3,4}-\\d{4}$";
        Boolean bool = Pattern.matches(temp, phoneNum);
        if (!bool) {
            throw new PatternException("잘못된 전화번호 형식입니다. 다시 입력해주세요.\n");
        }
    }
    
//        System.out.println();
//        System.out.println("회원가입을 진행합니다.");        
//        System.out.print("아이디 : ");
//        String userId = sc.nextLine();
//        System.out.print("비밀번호(영문+숫자 조합 8자 이상) : ");
//        String password = sc.nextLine();
//        System.out.print("이름 : ");
//        String name = sc.nextLine();
//        System.out.print("전화번호(010-1234-5678) : ");
//        String phone = sc.nextLine();        
//        this.server.memberRegister(userId, password, name, phone);
//        System.out.println();
//        System.out.println("회원가입이 완료되었습니다!");
        
    
    
    // 로그인
    public void login(Scanner sc) {
        System.out.println();
        System.out.println("로그인을 진행합니다.");
        System.out.print("아이디 : ");
        String userId = sc.nextLine();
        System.out.print("비밀번호 : ");
        String password = sc.nextLine();
        if (userId.equals(admin.getAdminId()) && password.equals(admin.getAdminPwd())) {
            this.adminMenu(sc);
        } else if(this.server.login(userId, password) == true) {
            this.userMenu(sc);            
        }    
    }
    
    // 관리자 메뉴
    public void adminMenu(Scanner sc) {
        while(true) {
            try {
                System.out.println();
                System.out.println("                              [관리자 모드]");
                System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
                System.out.println("              1.회원 조회   2.대여 현황   3. 자전거 관리   0.로그아웃");
                System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
                System.out.print("메뉴입력 > ");
                int input = sc.nextInt();
                switch (input) {
                case 0:
                    this.menu(sc);;
                case 1:
                    this.memberCheck(sc);
                    break;
                case 2:
                    this.server.totalCheckOut();
                    break;
                case 3:
                    this.bicycleMenage(sc);
                    break;
                default:
                    System.out.println("다시 입력해주세요.");
                }
            } catch (Exception e) {
                e.getMessage();
            }        
            
        }
    }
    
    // 회원조회
    private void memberCheck(Scanner sc) {
        while(true) {
            try {
                System.out.println();
                System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
                System.out.println("                        1.전체 회원   2.회원 조회   0.나가기");
                System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
                System.out.print("메뉴입력 > ");
                int input = sc.nextInt();
                switch (input) {
                case 0:
                    this.adminMenu(sc);
                    break;
                case 1:
                    this.server.userList();
                    System.out.println("=========================================================================");
                    break;
                case 2:
                    System.out.println();
                    System.out.println("조회할 회원아이디를 입력해주세요.");
                    System.out.print("아이디 : ");
                    sc.nextLine();
                    String memberId = sc.nextLine();
                    String result2 = this.server.userSearch(memberId);
                    System.out.println();
                    System.out.println("==============================! 회원 리스트 !==============================");
                    System.out.println(result2);
                    System.out.println("=========================================================================");
                    break;
                default:
                    System.out.println("다시 입력해주세요.");
                }             
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }
    
    
    // 자전거 관리메뉴
    private void bicycleMenage(Scanner sc) {
        System.out.println();
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        System.out.println("                        1.자전거 등록   2.자전거 삭제");
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        System.out.print("메뉴입력 > ");  
        int input = sc.nextInt();
        switch (input) {
        case 1:
            this.bicycleRegister(sc);            
            break;
        case 2:
            this.deleteBicycle(sc);
            break;
        default:
            System.out.println("다시 입력해주세요.");
            break;
        }
    }
    
    // 자전거 등록
    private void bicycleRegister(Scanner sc) {
        System.out.println();
        System.out.println("등록할 자전거의 정보를 입력해주세요.");
        System.out.print("등록번호 : ");
        sc.nextLine();
        String num = sc.nextLine();        
        System.out.print("대여가격 : ");
        int pay = sc.nextInt();        
        System.out.print("브랜드 : ");
        sc.nextLine();
        String brand = sc.nextLine();
        System.out.print("모델명 : ");
        String model = sc.nextLine();        
        this.server.bicycleRegister(num, pay, brand, model);
        System.out.println();
        System.out.println("자전거가 등록되었습니다!");
        this.server.bicycleList();
        System.out.println("=========================================================================");
    }
    
    // 자전거 삭제
    private void deleteBicycle(Scanner sc) {
        while (true) {
            try {
                System.out.println("삭제할 자전거의 등록번호를 입력해주세요.");
                System.out.println();
                System.out.print("등록번호 : ");
                sc.nextLine();
                String bicycleNo = sc.nextLine();
                this.server.deleteBicycle(bicycleNo);
                this.server.bicycleList();
                System.out.println("=========================================================================");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }
}
    
   // 사용자메뉴
    private void userMenu(Scanner sc) {
        while (true) {
            try {
                System.out.println();
                System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
                System.out.println("                       1.대여   2.반납   0.로그아웃");
                System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
                System.out.print("메뉴입력 > ");
                int input = sc.nextInt();
                sc.nextLine();
                switch (input) {
                case 0:
                    this.menu(sc);
                    break;
                case 1:
                    this.rentalBicycle(sc);
                    break;
                case 2:
                    this.returnBicycle(sc);
                    break;
                default:
                    System.out.println("다시 입력해주세요.");
                }
            }
            catch (Exception e) {
                e.getMessage();
            }
        }
    }
    
    // 자전거 대여
    private void rentalBicycle(Scanner sc) {
        while (true) {
            this.server.bicycleList();
            System.out.println("==========================================================================");
            System.out.println();
            System.out.println("대여하시겠습니까?");
            System.out.print("[1.대여하기 0.나가기]  선택 > ");
            int input = sc.nextInt();
            sc.nextLine();
            switch (input) {
            case 0:
                this.userMenu(sc);
                break;
            case 1:
                this.bicycleCheckout(sc);
                break; 
            default:
                System.out.println("다시 입력해주세요.");
                break;
            }
        }
    }
    
    // 중복대여체크
    public void bicycleCheckout(Scanner sc) {
        System.out.println();
        System.out.println("대여할 자전거의 등록번호를 입력해 주세요.");
        System.out.print("등록번호 : ");
        String bicycleNo = sc.nextLine();
        try {
            if(this.server.isCheckOutAble(bicycleNo)) {
            System.out.println("현재 대여중인 자전거입니다. 다른자전거를 선택해주세요.");
            return;
         }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            bicycleCheckout(sc);
        }
        System.out.print("대여기간 : ");
        int dueday = sc.nextInt();
        try {
            System.out.println("===================================");
            this.server.today();
            this.server.dueDay(dueday);
            this.server.pay(bicycleNo, dueday); // 결제금액            
            System.out.println("===================================");
            System.out.println();
            System.out.println("결제하시겠습니까?");
            
            System.out.print("[1.결제하기 0.돌아가기]  선택 >");
            int input = sc.nextInt();
            sc.nextLine();
            switch (input) {
            case 0:
                this.userMenu(sc);
                break;
            case 1:
                this.server.checkOutBicycle(bicycleNo, dueday); // 대여중으로 변경 
                System.out.println();
                System.out.println("결제되었습니다. 감사합니다!");            
                userMenu(sc);
                break;
            default:
                System.out.println("다시 입력해주세요.");
                break;
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    } 
    

    // 자전거 반납
    public void returnBicycle(Scanner sc) {
        System.out.println();
        System.out.println("반납할 자전거의 등록번호를 입력해 주세요.");
        System.out.print("등록번호 : ");
        String bicycleNo = sc.nextLine();
        try {
            this.server.returnBicycle(bicycleNo);
            System.out.println();
            System.out.println("반납되었습니다. 감사합니다!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
        
}   
        





 
