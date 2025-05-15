package clase;
import java.util.ArrayList;

public class Facilitati {
    private ArrayList<TipFacilitati> tip;

    public Facilitati(ArrayList<TipFacilitati> tip) {
        this.tip = tip;
    }

    public void afisare() {
        System.out.println("Facilitatile noastre: ");
        for (TipFacilitati tipFacilitati : tip) {
            System.out.println(tipFacilitati.toString());
        }
    }
}
