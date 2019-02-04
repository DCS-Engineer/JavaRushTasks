package com.javarush.task.task33.task3309;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) {
        StringWriter writer = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // Определяем API фабрики, что позволит получить синтаксический анализатор, который производит деревья объекта DOM из XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Определяет, что синтаксический анализатор, произведенный этим кодом, преобразует узлы CDATA в текстовые узлы и добавит его к смежному (если есть) текстовому узлу
            factory.setCoalescing(Boolean.TRUE);
            // Определяет API, чтобы получить экземпляры документа DOM из XML
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Создаем документ
            Document document = builder.newDocument();
            // Сериализация объекта в созданный документ
            marshaller.marshal(obj, document);
            // Вывод документа
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");

            NodeList nodeList = document.getElementsByTagName("*");
            if (nodeList.getLength() > 0){
                for (int i = 0; i < nodeList.getLength(); i++){
                    Node node = nodeList.item(i);
                    if (node.getNodeName().equals(tagName)){
                        node.getParentNode().insertBefore(document.createComment(comment), node);
                    }
                    if (node.getFirstChild().getNodeType() == Node.TEXT_NODE){
                        Node currentNode = node.getFirstChild();
                        if (currentNode.getTextContent().matches(".*[<>&\'\"].*")){
                            String content = currentNode.getTextContent();
                            CDATASection cdataSection = document.createCDATASection(content);
                            node.replaceChild(cdataSection, currentNode);
                        }
                    }
                }
            }
            transformer.transform(new DOMSource(document), new StreamResult(writer));
        }
        catch (JAXBException e) {
        }
        catch (TransformerConfigurationException e) {
        }
        catch (ParserConfigurationException e) {
        }
        catch (TransformerException e) {
        }
        return writer.toString();
    }

    public static void main(String[] args) {
//        System.out.println(toXmlWithComment(new First(), "nothing", "it's a comment"));
//        System.out.println(toXmlWithComment(new First(), "second", "it's a comment"));
    }
/*
    @XmlRootElement(name = "first")
    public static class First {
        @XmlElement(name = "second")
        public String item1 = "some string";
        @XmlElement(name = "second")
        public String item2 = "need CDATA because of <second>";
        @XmlElement(name = "second")
        public String item3 = "";
        @XmlElement(name = "third")
        public String item4;
        @XmlElement(name = "forth")
        public Second[] third = new Second[]{new Second()};
        @XmlElement(name = "fifth")
        public String item5 = "need CDATA because of \"";
    }

    public static class Second {
        @XmlElement(name = "second")
        public String item1 = "some string";
        @XmlElement(name = "second")
        public String item2 = "need CDATA because of <second>";
    }
    */
}
