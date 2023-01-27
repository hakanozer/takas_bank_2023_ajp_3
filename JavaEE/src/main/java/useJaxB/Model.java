package useJaxB;

import java.util.ArrayList;
import java.util.List;

public class Model {

    public List<Product> data() {
        List<Product> ls = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product p = new Product();
            p.setTitle("Title - " + i );
            p.setPid(i);
            //p.setPrice(i * 10);
            Category c = new Category();
            c.setCid(i*2);
            c.setName("Cat - " + i);
            p.setCategory(c);
            ls.add(p);
        }
        return ls;
    }
}
