package zadanie;

import zadanie.UI.Menu;
import zadanie.UI.MenuOption;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

public class z1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("InTest1.txt"));

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
                drzewo.printKLP();
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
                int tmp = in.nextInt();
                if ()
            }
        });

        menu.add(new MenuOption("Zapisz drzewo do pliku","6") {
            @Override
            public void onPerform() {
                System.out.println("Wczytuje");
            }
        });
        menu.run();

    }
}
