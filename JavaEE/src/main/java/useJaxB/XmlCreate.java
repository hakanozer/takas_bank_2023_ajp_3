package useJaxB;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class XmlCreate {
    public void create() {

        try {

            Model model = new Model();
            List<Product> list = model.data();
            Products products = new Products();
            products.setProducts(list);

            File file = new File("sample.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(products, file );

        }catch (Exception ex) {
            System.err.println("Xml Create Error : " + ex);
        }

    }
}
