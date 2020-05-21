/*
 * Joshua Belfor
 * Final Project for Collections in Java
 * 5/2/2020
 */
package finalproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

/**
 *
 * @author Joshua
 */
public class FXMLDocumentController implements Initializable {

    private String button;
    // Create the HashMap for storing books
    private HashMap<String, Book> booksMap = new HashMap<>();

    // Create emailStack.
    private Stack emailStack = new Stack();
    // Linked list for storing TextBooks
    private LinkedList textBookList = new LinkedList<TextBook>();
    private LinkedList cookBookList = new LinkedList<TextBook>();

    @FXML
    private Label author;
    @FXML
    private Label type;
    @FXML
    private Label change; //used to handle date or edition  
    @FXML
    private Label title;
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField changeField;
    @FXML
    private ListView docView;
    @FXML
    private TextArea reader;
    @FXML
    private TextArea hashField;
    @FXML
    private Button nextBT;
    @FXML
    private TextArea sortField;

    @FXML
    private void bookBTHandler(ActionEvent event) {
        button = "Book";
        title.setText("Title");
        author.setText("Author");
        // create books url title author
        Book book1 = new Book("TheThreeMusketeers.txt", "The Three Musketeers", "Alexandre Dumas");
        Book book2 = new Book("ATaleOfTwoCities.txt", "A Tale of Two Cities", "Charles Dickens");
        Book book3 = new Book("MobyDick.txt", "Moby Dick", "Herman Melville");

        // set HashMap with the books
        booksMap.put(book1.getTitle(), book1);
        booksMap.put(book2.getTitle(), book2);
        booksMap.put(book3.getTitle(), book3);

        //Fill docView with textbooks
        ObservableList<String> newOptions
                = FXCollections.observableArrayList(
                        book1.getTitle(),
                        book2.getTitle(),
                        book3.getTitle()
                );

        docView.setItems(newOptions);

    }

    @FXML
    private void textBookBTHandler(ActionEvent event) {
        button = "TextBook";
        title.setText("Title");
        author.setText("Author");
        // create books url title author
        TextBook book1 = new TextBook("spanish1.txt", "Pitman's Commercial Spanish Grammar", "C. A. Toledano", "2");
        TextBook book2 = new TextBook("biology.txt", "Text Book of Biology, Part 1: Vertebrata", "H. G. Wells", "1");
        TextBook book3 = new TextBook("geography.txt", "First Lessons In Geography", "James Monteith", "1");

        //Place TextBooks into LinkedList
        textBookList.add(book1);
        textBookList.add(book2);
        textBookList.add(book3);

        //Fill docView with textbooks
        ObservableList<String> newOptions
                = FXCollections.observableArrayList(
                        book1.getTitle(),
                        book2.getTitle(),
                        book3.getTitle()
                );
        docView.setItems(newOptions);

    }

    @FXML
    private void cookBookBTHandler(ActionEvent event) {
        button = "CookBook";
        title.setText("Title");
        author.setText("Author");
        // create books url title author
        CookBook book1 = new CookBook("cook1.txt", "Breakfast Foods 101", "Grandma Lilly", "Breakfast");
        CookBook book2 = new CookBook("cook2.txt", "We all love sweets", "Peter Griffin", "Sweets");
        CookBook book3 = new CookBook("cook3.txt", "Cakes for you and me", "Conseula", "Cakes");

        //Place TextBooks into LinkedList
        cookBookList.add(book1);
        cookBookList.add(book2);
        cookBookList.add(book3);

        //Fill docView with textbooks
        ObservableList<String> newOptions
                = FXCollections.observableArrayList(
                        book1.getTitle(),
                        book2.getTitle(),
                        book3.getTitle()
                );
        docView.setItems(newOptions);

    }

    @FXML
    private void emailBTHandler(ActionEvent event) {

        // create and load emails into the stack.
        button = "Email";
        emailStack.clear();
        title.setText("Subject");
        author.setText("Sender");
        typeField.setText("Email");
        Date date = new Date(2020, 01, 23);
        Email email1 = new Email("fromJames.txt", "A spam for you", date, "James Cameron");
        emailStack.add(email1);
        date = new Date(2019, 03, 14);
        Email email2 = new Email("fromMom.txt", "Hi son!", date, "Mom");
        emailStack.add(email2);
        date = new Date(2018, 06, 30);
        Email email3 = new Email("fromJob.txt", "URGENT: Work Related", date, "Boss");
        emailStack.add(email3);

        //Fill docView with textbooks
        ObservableList<String> newOptions
                = FXCollections.observableArrayList(
                        email1.getTitle(),
                        email2.getTitle(),
                        email3.getTitle()
                );
        docView.setItems(newOptions);
    }

    @FXML
    // Handles selecting TextBooks and Books from ListView
    private void ListViewHandler() {

        // create the bookReader that both cases use
        FileReader bookReader = null;
        switch (button) {

            // Book button was selected
            case "Book":

                // grab the selected book from the HashMap, set the fields and labels
                Book book = booksMap.get((String) docView.getSelectionModel().getSelectedItem());
                titleField.setText(book.getTitle());
                authorField.setText(book.getCreator());
                typeField.setText(book.getType());

                // Set the changing field to point to the file name
                change.setText("File");
                changeField.setText(book.getURL());

                // Load the book into the reader
                setReader(book.getUrl());
                break;

            // If the textbook button has been selected
            case "TextBook":
                title.setText("Title");
                author.setText("Author");

                // Like with the book, get the TextBook selected
                String textBookTitle = (String) docView.getSelectionModel().getSelectedItem();
                TextBook textBook = null;

                //Check to see if the textBook matches what was selected
                for (int i = 0; i < textBookList.size(); i++) {
                    TextBook test = (TextBook) textBookList.get(i);

                    if (test.getTitle().equals(textBookTitle)) {
                        textBook = test;
                    }
                }
                // Inject the fields with the proper values
                titleField.setText(textBook.getTitle());
                authorField.setText(textBook.getCreator());
                typeField.setText(textBook.getType());
                change.setText("Edition:");
                changeField.setText(textBook.getEdition());

                // load the reader with the textbook text
                setReader(textBook.getUrl());
                break;

            // If the cookBook was selected
            case "CookBook":
                title.setText("Title");
                author.setText("Author");

                // Like with the book, get the CookBook selected
                String cookBookTitle = (String) docView.getSelectionModel().getSelectedItem();
                CookBook cookBook = null;

                //Check to see if the textBook matches what was selected
                for (int i = 0; i < cookBookList.size(); i++) {
                    CookBook test = (CookBook) cookBookList.get(i);

                    if (test.getTitle().equals(cookBookTitle)) {
                        cookBook = test;
                    }
                }
                // Inject the fields with the proper values
                titleField.setText(cookBook.getTitle());
                authorField.setText(cookBook.getCreator());
                typeField.setText(cookBook.getType());
                change.setText("Recipie:");
                changeField.setText(cookBook.getRecipie());

                // load the reader with the textbook text
                setReader(cookBook.getUrl());
                break;
            default:
                break;
        }
    }

    @FXML
    void spamBTHandler(ActionEvent event) {
        button = "Advertisement";

        // create and load emails into the stack.
        button = "Email";
        emailStack.clear();
        title.setText("Subject");
        author.setText("Sender");
        Date date = new Date(2020, 04, 18);
        AdvertisementEmail email1 = new AdvertisementEmail("spam1.txt", "You got mail!", date, "Real Person");
        emailStack.add(email1);
        date = new Date(2020, 03, 14);
        AdvertisementEmail email2 = new AdvertisementEmail("spam2.txt", "Hi Person!", date, "Spambot");
        emailStack.add(email2);
        date = new Date(2020, 01, 30);
        AdvertisementEmail email3 = new AdvertisementEmail("spam3.txt", "Weak spam", date, "Hacker");
        emailStack.add(email3);

        //Fill docView with textbooks
        ObservableList<String> newOptions
                = FXCollections.observableArrayList(
                        email1.getTitle(),
                        email2.getTitle(),
                        email3.getTitle()
                );
        docView.setItems(newOptions);
    }

    // Handler for getting the next email
    @FXML
    void nextBTHandler(ActionEvent event) {

        // Ensure the emails button has been selected
        if (button.equals("Email")) {

            // Ensure there are emails left
            if (emailStack.size() >= 1) {

                // Adjust the titles, get the email and display the data
                Email email = (Email) emailStack.pop();
                authorField.setText(email.getCreator());
                titleField.setText(email.getTitle());
                change.setText("Date");
                typeField.setText(email.getType());

                // call setReader with the Emails URL
                setReader(email.getUrl());

                // Output Date
                Date date = email.getDate();
                changeField.setText(date.getDay() + "/" + date.getMonth() + "/" + date.getYear());

                // Refresh the lineup in the listview
                String[] tempTitles = new String[emailStack.size()];
                for (int i = 0; i < emailStack.size(); i++) {
                    Email tempEmail = (Email) emailStack.get(i);
                    tempTitles[i] = tempEmail.getTitle();
                }
                ObservableList<String> newOptions = FXCollections.observableArrayList(tempTitles);
                docView.setItems(newOptions);
            } else {

                // Output there are no emails left
                reader.setText("There are no more Emails left");
            }
        } else if (button.equals("Advertisement")) {
            // Ensure there are emails left
            if (emailStack.size() >= 1) {

                // Adjust the titles, get the email and display the data
                AdvertisementEmail email = (AdvertisementEmail) emailStack.pop();
                authorField.setText(email.getCreator());
                titleField.setText(email.getTitle());
                change.setText("Date");
                typeField.setText(email.getType());

                // call setReader with the Emails URL
                setReader(email.getUrl());

                // Output Date
                Date date = email.getDate();
                changeField.setText(date.getDay() + "/" + date.getMonth() + "/" + date.getYear());

                // Refresh the lineup in the listview
                String[] tempTitles = new String[emailStack.size()];
                for (int i = 0; i < emailStack.size(); i++) {
                    Email tempEmail = (Email) emailStack.get(i);
                    tempTitles[i] = tempEmail.getTitle();
                }
                ObservableList<String> newOptions = FXCollections.observableArrayList(tempTitles);
                docView.setItems(newOptions);
            } else {

                // Output there are no emails left
                reader.setText("There are no more Emails left");
            }
        } else {

            // Tell the user to select the email button
            reader.setText("Please Select an Email Button.");
        }

    }

    @FXML
    private void hashBTHandler(ActionEvent event
    ) {
        hashField.clear();

        // The PrintChar class inside of the hashBTHandler
        class PrintChar implements Runnable {

            private char c;

            // generates random chars and adds them to hashField
            public PrintChar(int t) {
                for (int i = 0; i < t; i++) {
                    Random ran = new Random();
                    c = (char) (ran.nextInt(26) + 'a');
                    hashField.appendText(c + "");
                }
            }

            @Override
            public synchronized void run() {

            }
        }

        // create new PrintChars set to 100 each
        Runnable print1 = new PrintChar(100);
        Runnable print2 = new PrintChar(100);

        // Establish 2 threads for use, set second one to priority and start them
        Thread thread1 = new Thread(print1);
        Thread thread2 = new Thread(print2);
        thread2.setPriority(1);

        thread1.start();
        thread2.start();

        // Output hash to the file, removing the old one
        try {
            File hashFile = new File("hash.txt");
            PrintWriter output = new PrintWriter(hashFile);
            output.print("");
            output.print(hashField.getText());
            output.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }

    // sorts the Hash
    @FXML
    private void sortBTHandler(ActionEvent event
    ) {

        // gets hashField as a string, puts it into a char array
        String chars = hashField.getText();
        char[] charArray = chars.toCharArray();
        char[] sortedArray = selectionSort(charArray);

        // put sorted into sortField
        sortField.setText(new String(sortedArray));

    }

    // Selection Sort for the char array
    char[] selectionSort(char[] charArray
    ) {
        int length = charArray.length;

        // Loop through the array, checking the elements
        for (int i = 0; i < length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if (charArray[j] < charArray[min]) {
                    min = j;
                }
            }

            // Swap the found minimum element with i
            char temp = charArray[min];
            charArray[min] = charArray[i];
            charArray[i] = temp;
        }
        return charArray;
    }
    // reused code, put into seperate method.

    public void setReader(String url) {
        // creates a File and FileReader to populate the reader
        try {
            File textLines = new File(url);
            FileReader textReader = new FileReader(textLines);
            char[] allLines = new char[(int) textLines.length()];
            textReader.read(allLines);
            reader.setText(new String(allLines));

            // catch the exceptions
        } catch (FileNotFoundException ex) {
            reader.setText("File not found");
        } catch (IOException ex) {
            reader.setText("IO Failure");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Seperate from setReader() as it acts on a different TextArea
        // get the hashText and upload it into the hashField
        try {
            File hashText = new File("hash.txt");

            // File reader into a char array, load that to hashField
            FileReader booksReader = new FileReader(hashText);
            char[] hashLines = new char[(int) hashText.length()];
            booksReader.read(hashLines);
            hashField.setText(new String(hashLines));

            // Catch exceptions
        } catch (FileNotFoundException ex) {
            hashField.setText("File not found");
        } catch (IOException ex) {
            hashField.setText("IO Failure");
        }
    }

}
