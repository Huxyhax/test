import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Skapar en instans av Repository för databasanrop
        Repository repository = new Repository();

        // Hämtar lista över kunder som köpt svarta byxor
        List<String> customers = repository.getCustomersWhoBoughtBlackPants();

        // Skriver ut rubrik
        System.out.println("Kunder som köpt svarta byxor i storlek 38 av märket SweetPants:");

        // Skriver ut varje kund i listan
        for (String customer : customers) {
            System.out.println(customer);
        }
    }
}
