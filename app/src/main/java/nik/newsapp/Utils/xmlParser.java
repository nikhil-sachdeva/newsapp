package nik.newsapp.Utils;

import android.graphics.drawable.Drawable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by nikhil on 26/07/2018.
 */

public class xmlParser {

   private ArrayList<HashMap<String ,String>> results=new ArrayList<>();



    public ArrayList<HashMap<String,String >> processXML(InputStream inputStream) throws Exception{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;



        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document xmlDocument = documentBuilder.parse(inputStream);
        Element rootElement = xmlDocument.getDocumentElement();
        NodeList itemsList = rootElement.getElementsByTagName("item");
        NodeList itemChildren=null;
        Node currentChild=null;
        Node currentItem=null;
        HashMap<String,String > feedMap=null;


        for(int i=0;i<itemsList.getLength();i++){
            currentItem=itemsList.item(i);
            itemChildren=currentItem.getChildNodes();
            feedMap = new HashMap<>();
            for(int j=0;j<itemChildren.getLength();j++){
                currentChild=itemChildren.item(j);
                if(currentChild.getNodeName().equalsIgnoreCase("title")){
                    feedMap.put("title",currentChild.getTextContent());
                }
                if(currentChild.getNodeName().equalsIgnoreCase("link")){
                    feedMap.put("link",currentChild.getTextContent());
                }
                if(currentChild.getNodeName().equalsIgnoreCase("pubDate")){
                    feedMap.put("Date",currentChild.getTextContent());
                }
                if(currentChild.getNodeName().equalsIgnoreCase("StoryImage")){
                    feedMap.put("Image",currentChild.getTextContent());
                }
                if(currentChild.getNodeName().equalsIgnoreCase("description")){
                    feedMap.put("description",currentChild.getTextContent());
                }



            }

            if(feedMap!=null && !feedMap.isEmpty()){
                results.add(feedMap);


            }


        }


        return results;
    }


}
