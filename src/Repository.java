import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private final String url = "jdbc:mysql://localhost:3306/Webbshop";
    private final String user = "root";
    private final String password = "24r46q4w";

    // Metod för att hämta kunder som köpt svarta byxor
    public List<String> getCustomersWhoBoughtBlackPants() {
        List<String> customers = new ArrayList<>();
        String query = """
            SELECT DISTINCT k.Fornamn, k.Efternamn
            FROM Kunder k
            JOIN Bestallningar b ON k.KundID = b.KundID
            JOIN Bestallningsrader br ON b.BestallningsID = br.BestallningsID
            JOIN Produkter p ON br.ProduktID = p.ProduktID
            WHERE p.Namn = 'Svarta Byxor' AND p.Storlek = '38' AND p.Farg = 'Svart' AND p.Marke = 'SweetPants';
        """;


        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                customers.add(rs.getString("Fornamn") + " " + rs.getString("Efternamn"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }
}
