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

import nik.newsapp.Model.Article;

/**
 * Created by nikhil on 26/07/2018.
 */

public class xmlParser {

   private ArrayList<Article> results=new ArrayList<>();



    public ArrayList<Article> processXML(InputStream inputStream) throws Exception{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;



        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document xmlDocument = documentBuilder.parse(inputStream);
        Element rootElement = xmlDocument.getDocumentElement();
        NodeList itemsList = rootElement.getElementsByTagName("item");
        NodeList itemChildren=null;
        Node currentChild=null;
        Node currentItem=null;
        Article feedMap=null;


        for(int i=0;i<itemsList.getLength();i++){
            currentItem=itemsList.item(i);
            itemChildren=currentItem.getChildNodes();
            feedMap = new Article();
            for(int j=0;j<itemChildren.getLength();j++){
                currentChild=itemChildren.item(j);
                if(currentChild.getNodeName().equalsIgnoreCase("title")){
                    feedMap.setTitle(currentChild.getTextContent());
                }
                if(currentChild.getNodeName().equalsIgnoreCase("link")){
                    feedMap.setLink(currentChild.getTextContent());
                }
                if(currentChild.getNodeName().equalsIgnoreCase("pubDate")){
                    feedMap.setDate(currentChild.getTextContent());
                }
                if(currentChild.getNodeName().equalsIgnoreCase("StoryImage")){
                    feedMap.setImageUrl(currentChild.getTextContent());
                }
                if(currentChild.getNodeName().equalsIgnoreCase("description")){
                    feedMap.setDescription(currentChild.getTextContent());
                }



            }

            if(feedMap!=null){
                results.add(feedMap);


            }


        }


        return results;
    }


}
