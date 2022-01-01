package zadanie;

public class z2 {
    public static void main(String[] args) throws DictionaryException {
        Dictionary slownik = new Dictionary();
        System.out.println("k".compareToIgnoreCase("l"));
        slownik.add("kot", "cat");
        slownik.add("pies", "dog");
        slownik.add("jeleń", "deer");

        slownik.print(Dictionary.LANG_ENG);
    }
}
