import java.util.Scanner;

public class bicycleRentalShop {

    public static void main(String[] args) {
        RentalService service = new RentalService();
        Scanner sc = new Scanner(System.in);
        service.menu(sc);
    }
}


