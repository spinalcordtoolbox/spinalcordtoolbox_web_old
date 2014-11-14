package spinalToolBoxWeb.TreeGenerator.internal;

import java.util.LinkedList;


public class TreeGeneratorController {


    private LinkedList<String> generatedTree = null;

    private int uploadedImages = 0;

    private String actualHTML = null;

    public TreeGeneratorController()
    {
        generatedTree = new LinkedList<String>();
        generatedTree.add("<div id = 'arbo'>");
        //generatedTree.add("<span><img src='/resources/viewRessouces/img/open.gif' alt=''></span><br>");
        generatedTree.add("</div>");
    };

    public String generateTree(String fileName)
    {
        uploadedImages++;

        int TreeSize = generatedTree.size();

        String tempNode = "<span id= 'arbo_" + uploadedImages +
                          "'>" + "<img src='/resources/viewRessouces/img/tline.gif' alt=''>" +
                          "<img src='/resources/viewRessouces/img/open.gif' alt=''>" +
                          "&nbsp;&nbsp;" + fileName + "</span>" + "&nbsp;&nbsp;&nbsp;&nbsp;" +
                          "<input type='checkbox' id='chBox_" + uploadedImages + "_ID" + "'>" +
                          "<br>";

        generatedTree.add(TreeSize - 1, tempNode);

        return generateStringFromList(generatedTree);
    }

    public String generateStringFromList(LinkedList<String> tree)
    {
        String html = null;
        html = "";
        for(int i=0 ; i < tree.size() ; i++)
        {
            html += tree.get(i);
        }

        actualHTML = html;

        return actualHTML;
    }


    public void deleteGeneratedTreeElements()
    {
        if(generatedTree.size() > 0)
        {
            generatedTree.clear();
            uploadedImages = 0;

            // generatedTree and actualHTML will be garbage collected
            generatedTree = null;
            actualHTML = null;

            // generatedTree references new object
            generatedTree = new LinkedList<String>();
            generatedTree.add("<div id = 'arbo'>");
            generatedTree.add("</div>");
        }
    }

    public String getActualTree()
    {
        return actualHTML;
    }

}
