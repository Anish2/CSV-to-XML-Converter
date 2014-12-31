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

public class Converter {

	public static void convert_csv(String csv_file, String xml_file, Class data_model) 
			throws IOException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		List<String[]> data = getData(csv_file);
		if (data == null || data.size() == 0)
			return;
		Class[] params = new Class[data.get(0).length];
		Arrays.fill(params, String.class);
		String xml = "";
		for (int i = 1; i < data.size(); i++) { // assuming first line contains headers
			Constructor c = data_model.getDeclaredConstructor(params);
			System.out.println(Arrays.toString(data.get(i)));
			Object o = c.newInstance(data.get(i));
			xml += convertToXML(o);
		}
		
		PrintStream out = new PrintStream(new File(xml_file));
		out.println(xml);
		out.close();
		
	}

	public static List<String[]> getData(String csv_file) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(csv_file));
		List<String[]> data = reader.readAll();
		reader.close();
		return data;
	}

	public static String convertToXML(Object data) throws FileNotFoundException {
		XStream xstream = new XStream();
		String xml = xstream.toXML(data);
		return xml;
	}




}
