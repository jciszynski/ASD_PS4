package zadanie;

import zadanie.UI.Menu;
import zadanie.UI.MenuOption;

import java.io.File;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class z2 {
    public static void main(String[] args) throws DictionaryException {
        Dictionary slownik = new Dictionary();
        slownik.add("kot", "cat");
        slownik.add("pies", "dog");
        slownik.add("jeleń", "deer");

        Menu menu = new Menu();
        menu.add(new MenuOption("Wyświetl słownik ENG","1") {
            @Override
            public void onPerform() {
                try {
                    menu.write(slownik.print(Dictionary.LANG_ENG));
                } catch (Exception ex){
                    menu.writeMessage(ex.getMessage());
                }
            }
        });

        menu.add(new MenuOption("Dodaj słowo","2") {
            @Override
            public void onPerform() {
                String wordENG = menu.inputString("Podaj słowo agielskie");
                String wordPL = menu.inputString("Podaj słowo polskie");
                try{
                    slownik.add(wordPL,wordENG);
                } catch (Exception ex){
                    menu.writeMessage(ex.getMessage());
                }
            }
        });

        menu.add(new MenuOption("Usuń słowo PL","3") {
            @Override
            public void onPerform() {
                String word = menu.inputString("Podaj słowo do usunięcia");
                try{
                    slownik.remove(word, Dictionary.LANG_PL);
                } catch (Exception ex){
                    menu.writeMessage(ex.getMessage());
                }
            }
        });

        menu.add(new MenuOption("Usuń słowo ENG","4") {
            @Override
            public void onPerform() {
                String word = menu.inputString("Podaj słowo do usunięcia");
                try{
                    slownik.remove(word, Dictionary.LANG_ENG);
                } catch (Exception ex){
                    menu.writeMessage(ex.getMessage());
                }
            }
        });

        menu.add(new MenuOption("Przetłumacz PL->ENG","5") {
            @Override
            public void onPerform() {
                String input = menu.inputString("Podaj słowo:");

                try{
                    menu.write(slownik.translate(input, Dictionary.LANG_PL));
                } catch(Exception ex){
                    menu.writeMessage(ex.getMessage());
                }
            }
        });

        menu.add(new MenuOption("Przetłumacz ENG->PL","6") {
            @Override
            public void onPerform() {
                String input = menu.inputString("Podaj słowo:");

                try{
                    menu.writeMessage(slownik.translate(input, Dictionary.LANG_ENG));
                } catch(Exception ex){
                    menu.writeMessage(ex.getMessage());
                }

            }
        });

        menu.add(new MenuOption("Wczytaj słownik","7") {
            @Override
            public void onPerform() {
                Scanner sc = null;
                try{
                    sc = new Scanner(new File("InTest0401.txt"));
                    String PL, ENG;
                    while(true){
                        ENG = sc.next();
                        PL= sc.next();
                        try {
                            slownik.add(PL, ENG);
                        } catch (DictionaryException e){
                            menu.write(e.getMessage());
                        }
                    }
                } catch (NoSuchElementException ex){
                    if(sc!=null)
                        sc.close();
                    return;
                } catch (Exception ex){
                    menu.writeMessage(ex.getMessage());
                }


            }
        });

        menu.add(new MenuOption("Zapisz słownik","8") {
            @Override
            public void onPerform() {
                try{
                    PrintWriter pw = new PrintWriter("OutTest0401.txt");
                    pw.print(slownik.print(Dictionary.LANG_ENG));
                    pw.close();
                } catch (Exception ex){
                    menu.writeMessage(ex.getMessage());
                }
            }
        });


        menu.run();


    }
}
