import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    // Anslutnings information
    private final String url = "jdbc:mysql://localhost:3306/Webbshop";
    private final String user = "root";
    private final String password = "24r46q4w";

    // Hämtar kunder som köpt svarta byxor
    public List<String> getCustomersWhoBoughtBlackPants() {
        List<String> customers = new ArrayList<>();

        // SQL-fråga för att hitta kunder
        String query = """
            SELECT DISTINCT k.Fornamn, k.Efternamn
            FROM Kunder k
            JOIN Bestallningar b ON k.KundID = b.KundID
            JOIN Bestallningsrader br ON b.BestallningsID = br.BestallningsID
            JOIN Produkter p ON br.ProduktID = p.ProduktID
            WHERE p.Namn = 'Svarta Byxor' AND p.Storlek = '38' AND p.Farg = 'Svart' AND p.Marke = 'SweetPants';
        """;

        // Försöker ansluta till databasen och köra frågan
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            // Lägger till varje kund i listan
            while (rs.next()) {
                customers.add(rs.getString("Fornamn") + " " + rs.getString("Efternamn"));
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return customers; // Returnerar listan med kunder
    }
}
