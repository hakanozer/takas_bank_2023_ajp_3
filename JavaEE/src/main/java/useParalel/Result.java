package useParalel;

import java.util.ArrayList;
import java.util.List;

public class Result {

    public List<Customer> data() {
        List<Customer> ls = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Customer c = new Customer();
            c.setAge(i*2);
            c.setCid(i);
            c.setName("Ali - " + i);
            ls.add(c);
        }
        return ls;
    }

}
