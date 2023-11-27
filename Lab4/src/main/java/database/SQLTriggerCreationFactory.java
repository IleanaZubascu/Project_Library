package database;

public class SQLTriggerCreationFactory {
    public String createTriggerBook() {
        return "CREATE TRIGGER after_book_insert " +
                "AFTER INSERT ON book " +
                "FOR EACH ROW " +
                "BEGIN " +
                "    INSERT INTO bookDeposit (idBook, stock, pricePerBook) " +
                "    VALUES (NEW.id, 0, 0); " +
                "END;";
    }
}
