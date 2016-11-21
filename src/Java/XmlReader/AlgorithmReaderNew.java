package XmlReader;

import Memories.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by master on 21.11.2016.
 */
public class AlgorithmReaderNew {
    public void readAll(){

    }
    public void readMemories() throws ParserConfigurationException, IOException, SAXException {
        HashMap<String, Memory> memoryHashMap = new HashMap<>();
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setValidating(false);
        DocumentBuilder builder = f.newDocumentBuilder();
        Document document = builder.parse(new File("templateStrorageNew.xml"));
        NodeList memoriesNodeList =document.getElementsByTagName("memory_block").item(0).getChildNodes();
        for (int i=0; i<memoriesNodeList.getLength();i++){
            if (memoriesNodeList.item(i).getNodeName()=="memory"){
                Node currentMemory = memoriesNodeList.item(i);
                String name="";
                switch (currentMemory.getAttributes().getNamedItem("type").getNodeValue()){
                    case "Register":
                        name = currentMemory.getAttributes().getNamedItem("name").getNodeValue();
                        memoryHashMap.put(name, new Register(name,null));
                        break;
                    case "Counter":
                        name = currentMemory.getAttributes().getNamedItem("name").getNodeValue();
                        memoryHashMap.put(name,new Counter(name,null));
                        break;
                    case "Wagon":
                        String lname = currentMemory.getAttributes().getNamedItem("leftName").getNodeValue();
                        String rname = currentMemory.getAttributes().getNamedItem("rightName").getNodeValue();
                        memoryHashMap.put(lname+"*"+rname, new Wagon(lname,rname,null));
                        break;
                    case "Table":
                        ArrayList<String> colNames = new ArrayList<>();
                        NodeList tableChildren = currentMemory.getChildNodes();
                        for(int j=0; j<tableChildren.getLength();j++){
                            if(tableChildren.item(j).getNextSibling()!=null){
                                if(tableChildren.item(j).getNextSibling().getNodeName()=="columnsName"){
                                    NodeList columnChildren=tableChildren.item(j).getNextSibling().getChildNodes();
                                    for(int k=0; k<columnChildren.getLength();k++){

                                    }
                                }
                            }
                        }
                }
            }

        }

    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        AlgorithmReaderNew algorithmReader = new AlgorithmReaderNew();
        algorithmReader.readMemories();
    }

}
