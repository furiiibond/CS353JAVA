public class Noeud {
    public int px;
    public int p999;

    public Noeud(int px, int p999) {
        this.px = px;
        this.p999 = p999;
    }

    public String toString() {
        return Convertors.numberToString(px) + "," + Convertors.numberToString(p999);
    }

    //build a node from a string
    public static Noeud fromString(String s) {
        String[] split = s.split(",");
        return new Noeud(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }
}