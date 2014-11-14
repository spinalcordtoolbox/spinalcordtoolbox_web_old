package spinalToolBoxWeb.TreeGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spinalToolBoxWeb.TreeGenerator.internal.TreeGeneratorController;


@Service
public class TreeGeneratorService {


    @Autowired
    private TreeGeneratorController treeGeneratorController;

    public TreeGeneratorService(){};

    public String generateTree(String fileName)
    {
        return treeGeneratorController.generateTree(fileName);
    }

    public void deleteGeneratedTreeElements()
    {
        treeGeneratorController.deleteGeneratedTreeElements();
    }

    public String getActualTree()
    {
        return treeGeneratorController.getActualTree();
    }
}
