package zad06;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class zad06xml {
    public static void main(String[] args) {
        try {
            List<Student> list = new ArrayList<>();
            list.add(new Student("Jan", "Kwasowski", "s19191", 1998, 3, 6));
            list.add(new Student("Adam", "Marian", "s20000", 2000, 1, 1));
            list.add(new Student("Ala", "Kowalska", "s18000", 1995, 4, 7));
            list.add(new Student("Anna", "Maria", "s21922", 2001, 2, 3));
            list.add(new Student("Roman", "Janusz", "s18999", 1997, 3, 5));

            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document doc = build.newDocument();
            Element root = doc.createElement("root");
            doc.appendChild(root);
            Element studentsList = doc.createElement("students");
            root.appendChild(studentsList);

            for (Student s : list) {
                Element student = doc.createElement("student");
                studentsList.appendChild(student);
                Element imie = doc.createElement("imie");
                imie.appendChild(doc.createTextNode(s.getImie()));
                student.appendChild(imie);
                Element nazwisko = doc.createElement("nazwisko");
                nazwisko.appendChild(doc.createTextNode(s.getNazwisko()));
                student.appendChild(nazwisko);
                Element index = doc.createElement("index");
                index.appendChild(doc.createTextNode(s.getIndex()));
                student.appendChild(index);
                Element rokUrodzenia = doc.createElement("rokUrodzenia");
                rokUrodzenia.appendChild(doc.createTextNode(s.getRokUrodzenia() + ""));
                student.appendChild(rokUrodzenia);
                Element rokStudiow = doc.createElement("rokStudiow");
                rokStudiow.appendChild(doc.createTextNode(s.getRokStudiow() + ""));
                student.appendChild(rokStudiow);
                Element semestr = doc.createElement("semestr");
                semestr.appendChild(doc.createTextNode(s.getSemestr() + ""));
                student.appendChild(semestr);
            }

            TransformerFactory tFact = TransformerFactory.newInstance();
            Transformer trans = tFact.newTransformer();

            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(new File("src/zad06/xmlStudents.xml"));
            DOMSource source = new DOMSource(doc);
            trans.transform(source, result);
            System.out.println(writer.toString());

        } catch (TransformerException ex) {
            System.out.println("Error outputting document");
        } catch (ParserConfigurationException ex) {
            System.out.println("Error building document");
        }
    }
}
