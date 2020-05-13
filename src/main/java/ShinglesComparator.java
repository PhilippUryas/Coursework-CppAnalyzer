import java.util.ArrayList;

public class ShinglesComparator {

    ShinglesComparator() {
    }

    public float compare(ArrayList<Integer> sourceHashes, ArrayList<Integer> targetHashes) {
        ArrayList<Integer> a = new ArrayList<>(sourceHashes);
        a.removeAll(targetHashes);

        int temp = sourceHashes.size() - a.size();

        System.out.println("Compared: " + temp + " source: " + sourceHashes.size() + " target: " + targetHashes.size());

        return 1 - (float) temp / (sourceHashes.size() + targetHashes.size() - temp);
    }
}
