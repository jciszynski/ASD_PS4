package zadanie.UI;

public abstract class MenuOption {
    private String label;
    private String key;

    public MenuOption(String label, String key) {
        this.label = label;
        this.key = key;
    }

    public abstract void onPerform();

    @Override
    public String toString() {
        return "<< " + key + " >> " + label;
    }

    public String getLabel() {
        return label;
    }

    public String getKey() {
        return key;
    }
}
