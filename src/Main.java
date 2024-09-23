import java.util.List;

public class Main {
    public static void main(String[] args) {
     Repository repository = new Repository();
        List<String> customers = repository.getCustomersWhoBoughtBlackPants();

        System.out.println("Kunder som köpt svarta byxor i storlek 38 av märket SweetPants:");
        for (String customer : customers) {
            System.out.println(customer);
        }
    }
}
