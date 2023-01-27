package useThread;

public class MainApp {
    public static void main(String[] args) {

        Runnable rn = () -> {
        try {

            Action ac1 = new Action("image-1.jpg");
            Thread th1 = new Thread(ac1, "Th-1");
            th1.setPriority(1);
            th1.start();
            th1.join();

            Action ac2 = new Action("image-2.jpg");
            Thread th2 = new Thread(ac2, "Th-2");
            th2.setPriority(2);
            th2.start();
            th2.join();

            Action ac3 = new Action("image-3.jpg");
            Thread th3 = new Thread(ac3, "Th-3");
            th3.setPriority(3);
            th3.start();
            th3.join();

            }catch (Exception ex) {}

        };
        new Thread(rn).start();
        System.out.println("This Line Call");

    }
}
