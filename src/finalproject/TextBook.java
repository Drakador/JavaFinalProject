package finalproject;

/**
 *
 * @author Joshua
 */
public class TextBook extends Book{
    private String edition;
    public TextBook(String url, String title, String author, String edition) {
        super(url, title, author);
        this.edition = edition;
    }

    @Override
    String getType() {
        return "Textbook";
    }
    
    String getEdition(){
        return this.edition;
    }
    
    
    
}
