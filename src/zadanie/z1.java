package zadanie;

import zadanie.UI.Menu;
import zadanie.UI.MenuOption;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class z1 {
    public static void main(String[] args) throws FileNotFoundException {


        BST drzewo = new BST<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        Menu menu = new Menu();


        menu.add(new MenuOption("Dodaj element do drzewa","1") {
            @Override
            public void onPerform() {
                try {
                    drzewo.insert(menu.inputInt("Podaj element do dodania"));
                } catch (bstException e){
                    System.out.println(e.getMessage());
                }
            }
        });

        menu.add(new MenuOption("Usuń element drzewa","2") {
            @Override
            public void onPerform() {
                try {
                    drzewo.delete(menu.inputInt("Podaj element który chcesz usunąć"));
                }catch (Exception e){
                    menu.write(e.getMessage());
                }
            }
        });

        menu.add(new MenuOption("Wypisz elementy drzewa KLP","3") {
            @Override
            public void onPerform() {
                menu.write(drzewo.printKLP());
            }
        });

        menu.add(new MenuOption("Wyszukaj element","4") {
            @Override
            public void onPerform() {
                BST.Node tmp = drzewo.search(menu.inputInt("Podaj wartość elementu jaki chcesz znaleźć"));
                if(tmp == null)
                    menu.write("Brak elementu w drzewie");
                else
                    menu.write(tmp.toString());
            }
        });

        menu.add(new MenuOption("Wczytaj drzewo z pliku","5") {
            @Override
            public void onPerform() {
                Scanner in;
                try {
                    in = new Scanner(new File("InTest1.txt"));
                } catch (Exception ex){
                    menu.write("Nie udało się otworzyć pliku !!!");
                    return;
                }
                int tmp;
                while (true) {
                    try{
                      tmp = in.nextInt();
                      drzewo.insert(tmp);
                    } catch (NoSuchElementException e){
                        break;
                    } catch (bstException e){
                        menu.write(e.getMessage());
                    }
                }

                in.close();
            }
        });

        menu.add(new MenuOption("Zapisz drzewo do pliku","6") {
            @Override
            public void onPerform() {
                PrintWriter pw;

                try{
                    pw = new PrintWriter(new File("OutTest1.txt"));
                    pw.print(drzewo.printKLP());
                    pw.close();
                } catch (Exception ex){
                    menu.write("Nie udało się zapisać do pliku " + ex.getMessage());
                }
            }
        });
        menu.run();

    }
}
