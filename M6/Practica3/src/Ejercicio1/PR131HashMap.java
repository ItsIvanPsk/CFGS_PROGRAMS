import java.io.Serializable;
import java.util.HashMap;

public class PR131HashMap implements Serializable {

    private HashMap<String, Integer> hash = new HashMap<>();

    public PR131HashMap(HashMap<String, Integer> hash) {
        this.hash = hash;
    }
    @Override
    public String toString()
    {
        return getHash().toString();
    }

    public HashMap<String, Integer> getHash() {
        return hash;
    }
}
