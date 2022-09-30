import java.awt.print.Book;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.util.stream.*;


public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Aby sie zalogowac wybierz odpowiedni numer: ");
        System.out.println("1 - Administrator\n2 - Uzytkownik\n3 - Wyjscie z programu");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if (choice == 1)
        {
            BooksControl();
        }
        else if (choice == 2)
        {
            BooksControl();
        }
        /*
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        */
    }


    public static void BooksControl() throws IOException {

        List<String> listBooks;
        try (Stream<String> lines = Files.lines(Paths.get("books.txt"))) {
            listBooks = lines.collect(Collectors.toList());
        }
        List<String> listUnavailableBooks;
        try (Stream<String> lines = Files.lines(Paths.get("booksUnv.txt"))) {
            listUnavailableBooks = lines.collect(Collectors.toList());
        }

        System.out.println("Wybierz akcje: ");
        System.out.println("1 - Pokaz liste dostepnych ksiazek\n2 - Pokaz liste aktualnie wypozyczonych ksiazek" +
                "\n3 - Wypozycz ksiazke\n4 - Zwroc ksiazke\n5 - Wyloguj sie");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        if (choice == 1) {
            for (int i = 0; i < listBooks.size(); i++) {
                System.out.println(i + 1 + " - " + listBooks.get(i));
            }
            System.out.println("Aby cofnac wcisnij 0");
            choice = sc.nextInt();
            if (choice == 0) {
                BooksControl();
            }
        } else if (choice == 2) {
            {
                for (int i = 0; i < listUnavailableBooks.size(); i++) {
                    System.out.println(i + 1 + " - " + listUnavailableBooks.get(i));
                }
                System.out.println("Aby cofnac wcisnij 0");
                choice = sc.nextInt();
                if (choice == 0) {
                    BooksControl();
                }
            }
        } else if (choice == 3) // WYPOZYCZANIE KSIAZKI
        {
            System.out.println("Wybierz numer ksiazki, ktora chcesz wypozyczyc:");
            for (int i = 0; i < listBooks.size(); i++) {
                System.out.println(i + 1 + " - " + listBooks.get(i));
            }
            System.out.println("Aby cofnac wcisnij 0");
            choice = sc.nextInt();
            if (choice == 0) {
                BooksControl();
            } else if (choice <= listBooks.size() && choice != 0) {
                {
                    listUnavailableBooks.add(listBooks.get(choice - 1));
                    listBooks.remove(choice - 1);
                    System.out.println("Wypozyczono ksiazke");
                    System.out.println("Aby cofnac wcisnij 0");

                    FileWriter writeBooks = new FileWriter("books.txt");
                    FileWriter writeUnavailable = new FileWriter("booksUnv.txt");
                    for (String str : listBooks) {
                        writeBooks.write(str + System.lineSeparator());
                    }
                    writeBooks.close();
                    for (String str : listUnavailableBooks) {
                        writeUnavailable.write(str + System.lineSeparator());
                    }
                    writeUnavailable.close();

                    choice = sc.nextInt();
                    if (choice == 0) {
                        BooksControl();
                    }
                }
            }

        } else if (choice == 4) // ODDAWANIE KSIAZKI
        {
            System.out.println("Wybierz numer ksiazki, ktora chcesz oddac:");
            for (int i = 0; i < listUnavailableBooks.size(); i++) {
                System.out.println(i + 1 + " - " + listUnavailableBooks.get(i));
            }
            System.out.println("Aby cofnac wcisnij 0");
            choice = sc.nextInt();
            if (choice == 0) {
                BooksControl();
            } else if (choice <= listUnavailableBooks.size() && choice != 0) {
                {
                    listBooks.add(listBooks.get(choice - 1));
                    listUnavailableBooks.remove(choice - 1);
                    System.out.println("Zwrocono ksiazke");
                    System.out.println("Aby cofnac wcisnij 0");

                    FileWriter writeBooks = new FileWriter("books.txt");
                    FileWriter writeUnavailable = new FileWriter("booksUnv.txt");
                    for (String str : listBooks) {
                        writeBooks.write(str + System.lineSeparator());
                    }
                    writeBooks.close();
                    for (String str : listUnavailableBooks) {
                        writeUnavailable.write(str + System.lineSeparator());
                    }
                    writeUnavailable.close();

                    choice = sc.nextInt();
                    if (choice == 0) {
                        BooksControl();
                    }
                }

            }
        }
    }
}
