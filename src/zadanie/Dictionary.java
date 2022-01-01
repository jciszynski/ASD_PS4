package zadanie;
import zadanie.dictBST.dictNode;

import java.util.Locale;

public class Dictionary {
    public static final int LANG_PL = 0;
    public static final int LANG_ENG = 1;
    dictBST pl;
    dictBST eng;

    public Dictionary() {
        pl = new dictBST<String>(){
            @Override
            public int compare(String o1, String o2) {
                int tmp = o1.compareToIgnoreCase(o2);
                if(tmp>0) tmp = 1;
                if(tmp<0) tmp = -1;
                return tmp;
            }
        };

        eng = new dictBST<String>(){
            @Override
            public int compare(String o1, String o2) {
                int tmp = o1.compareToIgnoreCase(o2);
                if(tmp>0) tmp = 1;
                if(tmp<0) tmp = -1;
                return tmp;
            }
        };
    }

    public String translate(String origin, int lang) throws DictionaryException {
        if(lang == LANG_ENG){
            dictNode tmp = pl.search(origin.toUpperCase());
            if(tmp == null)
                throw new DictionaryException("Brak słowa w słowniku!");

            return (String) tmp.Equivalent.data;
        }

        if(lang == LANG_PL){
            dictNode tmp = eng.search(origin.toUpperCase());
            if(tmp == null)
                throw new DictionaryException("Brak słowa w słowniku!");

            return (String) tmp.Equivalent.data;
        }

        throw new DictionaryException("Nieznany język");
    }

    public void add(String plWord, String engWord) throws DictionaryException {
        if(pl.search(plWord.toUpperCase())!=null || eng.search(engWord.toLowerCase())!=null){
            throw new DictionaryException("Wyrazy istnieją już w słowniku!");
        }

        dictNode tempPl = pl.insert(plWord.toUpperCase());
        dictNode tempEng = eng.insert(engWord.toUpperCase());
        tempPl.Equivalent = tempEng;
        tempEng.Equivalent  = tempPl;
    }

    public void remove(String s, int lang) throws DictionaryException {
        if(lang == LANG_ENG){
            dictNode tmp = eng.search(s);
            if(tmp == null)
                throw new DictionaryException("Brak wyrazu w słowniku");

            pl.delete(tmp.Equivalent.data);
            eng.delete(tmp.data);
        }

        if(lang == LANG_PL){
            dictNode tmp = pl.search(s);
            if(tmp == null)
                throw new DictionaryException("Brak wyrazu w słowniku");

            eng.delete(tmp.Equivalent.data);
            pl.delete(tmp.data);
        }
    }

    private String printKLP( dictNode node){
            String s = "";
            if (node != null)
                s+= node.data + " " + node.Equivalent.data + "\n";
            if (node.left != null) s += printKLP(node.left);
            if (node.right != null) s += printKLP(node.right);
            return s;
    }

    private String printLKP( dictNode node){
        String s = "";
        if (node.left != null) s += printLKP(node.left);
        if (node != null)
            s += node.data + " " + node.Equivalent.data + "\n";
        if (node.right != null) s+=printLKP(node.right);
        return s;
    }

    public String print(int lang) throws DictionaryException {
        if(lang == LANG_PL)
            return printKLP(pl.root);
        else if(lang == LANG_ENG)
            return printKLP(eng.root);
        else
            throw new DictionaryException("Nieznany język");
    }
}
