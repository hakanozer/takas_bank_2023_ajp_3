package useJaxB;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlRead {
    public void read() {

        try {
            File file = new File("sample.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Products products = (Products) unmarshaller.unmarshal(file);
            for ( Product item : products.getProducts() ) {
                System.out.println( item.getTitle() + " " + item.getPrice() );
            }
        }catch (Exception ex) {
            System.err.println("Xml Read Error : " + ex);
        }

    }
}
