package useThread;

public class Action implements Runnable {

    String path = "";
    public Action(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        try {
            System.out.println( this.path + " Upload... Start");
            Thread.sleep(5000);
            System.out.println( this.path + " Upload... Finish");
        }catch (Exception ex) {}
        //System.out.println(Thread.currentThread().getName() + " Finish ");
    }

}
