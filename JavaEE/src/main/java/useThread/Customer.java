package useThread;

public class Customer extends Thread{

    int total = 0;
    String name = "";
    public Customer( int total, String name ) {
        this.total = total;
        this.name = name;
    }

    @Override
    public void run() {
        CustomerMain.fncTotal(total, name);
    }


}
