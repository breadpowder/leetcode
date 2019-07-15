package source;
import java.util.*;

class Value {
    private HashMap<String, Value> dict = null;
    private Integer number = null;

    public Value(Integer n) {
        this.number = n;
    }

    public Value(HashMap<String, Value> d) {
        this.dict = d;
    }

    public HashMap<String, Value> getDict() {
        return this.dict;
    }

    public Integer getNumber() {
        return this.number;
    }

    public String toString() {
        if (this.number != null) {
            return number.toString();
        }
        if (this.dict != null) {
            return dict.toString();
        }
        return "";
    }



}
