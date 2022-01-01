package zadanie.UI;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static final String QUIT_KEY = "q";
    public static final String UNKNOWN_OPTION = " Nieznana opcja";
    private ArrayList<MenuOption> menuList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public Menu() {
    }

    public void run(){
        String input;
        boolean ended = false;

        while(!ended){
            printMenu();
            input = scanner.nextLine();
            if(input.equals(QUIT_KEY))
                ended = true;
            else if(!performOption(input))
                System.out.println(UNKNOWN_OPTION);
        }

    }

    public boolean performOption(String key){
        for (MenuOption option:menuList){
            if (option.getKey().equals(key)){
                option.onPerform();
                return true;
            }
        }
        return false;
    }

    public void printMenu(){
        System.out.println(">> MENU <<");

        for(MenuOption mo: menuList)
            System.out.println(mo);

        System.out.println("Aby zakończyć naciśnij "+ QUIT_KEY);
    }

    public void add(MenuOption option){
        menuList.add(option);
    }

    public void write(String text) {
        System.out.println(text);
    }


    public int inputInt(String question) {
        if (question != null && !question.isEmpty()) {
            write(question);
        }
        int input = 0;
        boolean isValid = false;
        while(!isValid) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                isValid = true;
            } catch (NumberFormatException ex) {
                write("Podaj liczbe całkowitą!");
            }
        }
        return input;
    }
}
