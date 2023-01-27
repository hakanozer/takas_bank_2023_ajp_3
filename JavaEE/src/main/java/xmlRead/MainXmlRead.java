package xmlRead;

import java.util.Arrays;
import java.util.List;

public class MainXmlRead {

    public static void main(String[] args) {

        XmlRead xmlRead = new XmlRead();
        List<Currency> ls =  xmlRead.xml();
        System.out.println( ls.get(0) );

    }

}
