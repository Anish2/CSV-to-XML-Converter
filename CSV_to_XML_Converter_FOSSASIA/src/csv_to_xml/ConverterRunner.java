package csv_to_xml;

/*
  Copyright 2014 Anish Visaria

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   */

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Runner for conversion of csv to xml with xsl.
 * @author Anish Visaria
 *
 */
public class ConverterRunner {

	public static void main(String[] args) throws ClassNotFoundException {

		
		try {
			// main conversion code
			Converter.convert_csv(args[0], args[1], getClassFromFile(args[2]));
			TransformerFactory tfactory = TransformerFactory.newInstance();
			Transformer xform = tfactory.newTransformer(new StreamSource(args[3]));
			xform.transform(new StreamSource(args[1]),new StreamResult(args[4]));
			
		} catch (NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| IOException | TransformerException e) {
			e.printStackTrace();
		}

	}
	
	@SuppressWarnings("rawtypes")
	public static Class getClassFromFile(String class_path) throws ClassNotFoundException, IOException {
		Path p = Paths.get(class_path);
	    URLClassLoader loader = new URLClassLoader(new URL[] {
	          p.toUri().toURL()
	    });
	    Class c = loader.loadClass("csv_to_xml.Contestant");
	    loader.close();
	    return c;
	}

}
