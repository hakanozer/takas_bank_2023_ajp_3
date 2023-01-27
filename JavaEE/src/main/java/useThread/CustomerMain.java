package useThread;

public class CustomerMain {

    static int mainTotal = 0;

    public static void main(String[] args) {

        Customer c1 = new Customer(1000, "c1");
        c1.start();

        Customer c2 = new Customer(-200, "c2");
        c2.start();

        Customer c3 = new Customer(100, "c3");
        c3.start();

        Customer c4 = new Customer(400, "c4");
        c4.start();


    }

    public synchronized static void fncTotal(  int total, String name ) {
        mainTotal = mainTotal + total;
        System.out.println(name + " - Total : " + mainTotal);
    }

}
