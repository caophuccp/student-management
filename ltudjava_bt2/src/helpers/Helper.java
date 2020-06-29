package helpers;

public class Helper {
    public static Float parseFloat(String f){
        if (f == null || f.isEmpty()) return null;
        try {
            return Float.valueOf(f);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toString(Float f){
        return f == null ? "" : String.valueOf(f);
    }

    public static String getPath(String p){
        if (p.length() <= 40) return p;
        return p.substring(0, 38) + "...";
    }
}
