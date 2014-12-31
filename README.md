CSV to XML Converter
----------------------
This is a CSV to XML converter written in Java for FOSSASIA.

How to Use it
--------------

Download repository and extract in same folder.

Open and edit <b>csvxmlconverter.bat</b> and change the paths of the files for your own directory.

```bat
java -jar csvxmlconverter.jar "[ folder_path ]contestants.csv" "[ folder_path ]contest_results.xml" "[ folder_path ]csvxmlconverter.jar" "[ folder_path ]transform.xsl" "[ folder_path ]out.xml"
PAUSE
```
<b>contestants.csv</b> is the csv data.
<b>contest_results.xml</b> is the file created when csv is converted to xml.
<b>out.xml</b> is the file that is created with the contest_results.xml data with the <b>transform.xsl</b> styling.

Run <b>csvxmlconverter.bat</b> and get xml file and xml file with xsl styling from paths passed in as command-line arguments.
