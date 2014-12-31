package csv_to_xml;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Testing {

	public static void main(String[] args) throws TransformerException {
		
		// make it as a rating system, include conditionals in xsl file
		
		TransformerFactory tfactory = TransformerFactory.newInstance();
		Transformer xform = tfactory.newTransformer(new StreamSource("transform.xsl"));
		xform.transform(new StreamSource("contest_results.xml"),new StreamResult("out.xml"));
		
		/*try {
			Converter.convert_csv("contestants.csv", "contest_results.xml", Contestant.class);
			
			
			
			
			
			
		} catch (NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| IOException e) {
			e.printStackTrace();
		}*/

	}

}
