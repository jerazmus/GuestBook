import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NoteDAO {
    public static Scanner scanner = new Scanner(System.in);
    public static final String filePath = "./GuestBook_02-ToCSV/GuestBook.csv";

    public static ArrayList<Note> createGuestBook() {
        ArrayList<Note> guestBook = new ArrayList<>();
        try {
            FileReader reader = new FileReader(filePath);
            CsvToBean<Note> csvToBean = new CsvToBeanBuilder<Note>(reader)
                    .withType(Note.class)
                    .withSeparator(';')
                    .build();
            List<Note> notes = csvToBean.parse();
            guestBook.addAll(notes);
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist in provided path.");
        } catch (IOException e) {
            System.out.println("Problem occurred while trying reading the file.");
        }
        return guestBook;
    }

    public static void showMenu(ArrayList<Note> guestBook) {
        System.out.println("[1] Show guest book.");
        System.out.println("[2] Add guest note.");
        System.out.println("[3] Exit.");
        optionMenu(guestBook);
    }

    public static void optionMenu(ArrayList<Note> guestBook) {
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                showGuestBook(guestBook);
                break;
            case "2":
                addGuestNote(guestBook);
                break;
            case "3":
                System.exit(0);
            default:
                System.out.println("Wrong input.");
                showMenu(guestBook);
                break;
        }
    }

    public static void addGuestNote(ArrayList<Note> guestBook) {
        System.out.println("Your name:");
        String name = scanner.nextLine();
        System.out.println("Your note:");
        String message = scanner.nextLine();

        Note note = new Note(name, message);
        guestBook.add(note);

        try {
            Writer writer = new FileWriter(filePath, true);

            StatefulBeanToCsv<Note> beanToCsv = new StatefulBeanToCsvBuilder<Note>(writer)
                    .withSeparator(';')
                    .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                    .build();

            beanToCsv.write(new Note(LocalDate.now(), name, message));
            writer.close();
        } catch (CsvRequiredFieldEmptyException e) {
            System.out.println("Required field is empty in CSV file.");
        } catch (CsvDataTypeMismatchException e) {
            System.out.println("Provided string value for conversion cannot be converted to the required type of the destination field.");
        } catch (IOException e) {
            System.out.println("Problem occurred while trying to append to file.");
        }
        showMenu(guestBook);
    }

    public static void showGuestBook(ArrayList<Note> guestBook) {
        guestBook.forEach(System.out::println);
        showMenu(guestBook);
    }
}
