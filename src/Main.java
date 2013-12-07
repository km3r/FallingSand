
public class Main {

    public static void main(String[] args) {
        Runnable eng = new Engine();
        Thread t = new Thread(eng);
        t.start();

    }

}
