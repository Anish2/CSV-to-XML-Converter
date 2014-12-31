package csv_to_xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.thoughtworks.xstream.XStream;

/**
 * CSV to XML Converter
 * @author Anish Visaria
 *
 */
public class Converter {

	/**
	 * Converts csv file to xml given class data_model.
	 * @param csv_file csv file path
	 * @param xml_file xml file path
	 * @param data_model class to serialize and deserialize from
	 * @throws IOException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void convert_csv(String csv_file, String xml_file, Class data_model) 
			throws IOException, NoSuchMethodException, SecurityException, InstantiationException, 
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		List<String[]> data = getData(csv_file);
		if (data == null || data.size() == 0)
			return;
		Class[] params = new Class[data.get(0).length];
		Arrays.fill(params, String.class);
		String xml = "";
		
		// constructing and serializing objects to xml
		for (int i = 1; i < data.size(); i++) { // assuming first line contains headers
			Constructor c = data_model.getDeclaredConstructor(params);
			Object o = c.newInstance((Object[])data.get(i));
			xml += convertToXML(o);
		}
		
		// printing out final results
		PrintStream out = new PrintStream(new File(xml_file));
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		out.println("<root>");
		out.println(xml);
		out.println("</root>");
		out.close();
		
	}

	/**
	 * Gets data from csv file via OpenCSV api
	 * @param csv_file csv file path
	 * @return Returns list of string arrays for each line
	 * @throws IOException
	 */
	public static List<String[]> getData(String csv_file) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(csv_file));
		List<String[]> data = reader.readAll();
		reader.close();
		return data;
	}

	/**
	 * Returns xml representation of object data via XStream api.
	 * @param data object to convert
	 * @return xml representation
	 * @throws FileNotFoundException
	 */
	public static String convertToXML(Object data) throws FileNotFoundException {
		XStream xstream = new XStream();
		String xml = xstream.toXML(data);
		return xml;
	}




}
