public class Camera {
    private TipCamera tip;
    private float pret;
    private boolean disponibiltate;
    public Camera(TipCamera tip, float pret) {
        this.tip = tip;
        this.pret = pret;
        this.disponibiltate = true;
    }
    public void afisare() {
        System.out.println("Tip camera: " + tip);
        System.out.println("Pret: " + pret);
        if (disponibiltate)
            System.out.println("Camera disponibila");
        else
            System.out.println("Camera non disponibila");
    }

    public void setTip(TipCamera tip) {
        this.tip = tip;
    }
    public void setPret(float pret) {
        this.pret = pret;
    }
    public void setDisponibilitate(boolean disponibiltate) {
        this.disponibiltate = disponibiltate;
    }
}
