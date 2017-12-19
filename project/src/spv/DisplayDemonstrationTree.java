//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv;
import spv.gen.DemonstrationItem;
import spv.gen.DemonstrationConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import org.jdesktop.application.Application;


public class DisplayDemonstrationTree {

   public static void recurrentGenerateBigTree
            (
            DemonstrationItem fatherItem,
            javax.swing.tree.DefaultMutableTreeNode fatherTree
            )
   {
     if (fatherItem!=null)
     {
     if (fatherTree!=null)
      {
       if (fatherItem.downLink!=null)
       {
       int dimension=fatherItem.downLink.size();
       if (dimension>0)
       {

        for(int i=0;i<dimension;i++)
        {
         DemonstrationItem currentItem=fatherItem.downLink.get(i);
          javax.swing.tree.DefaultMutableTreeNode
                  currentTree= new javax.swing.tree.DefaultMutableTreeNode();
         currentTree.setUserObject(currentItem);
         fatherTree.add(currentTree);
         DisplayDemonstrationTree.
                               recurrentGenerateBigTree
                                 (
                                 currentItem,
                                 currentTree
                                 );
        }
       }

       }
       
      }
     }
     
   }
   public static void recurrentGenerateLittleTree
            (
            DemonstrationItem fatherItem,
            javax.swing.tree.DefaultMutableTreeNode fatherTree
            )
   {
     if (fatherItem!=null)
     {
     if (fatherTree!=null)
      {
       if (fatherItem.downLink!=null)
       {
       int dimension=fatherItem.downLink.size();
       if (dimension>0)
       {

        for(int i=0;i<dimension;i++)
        {

         DemonstrationItem currentItem=fatherItem.downLink.get(i);
         boolean displayCurrentItem=true;
            if (currentItem.type==DemonstrationConstants.NEW_STATEMENT)
            {
            if(currentItem.items!=null)
            {
                int length=0;
                length=currentItem.items.size();
            if (length>0)
            {
                String s=currentItem.items.get(0).constantOrVariableText;

                if (s.equals("wff")|
                    s.equals("class")|
                    s.equals("set")
                   )
                    {
                     displayCurrentItem=false;
                    }
            }

            }
            }
         
            else if (currentItem.type==DemonstrationConstants.PROPER_VARIABLE)
            {
            displayCurrentItem=false;
            }
            else if (currentItem.type==DemonstrationConstants.VARIABLE)
            {
            displayCurrentItem=false;
            }

         if (displayCurrentItem)
         {
          javax.swing.tree.DefaultMutableTreeNode
                  currentTree= new javax.swing.tree.DefaultMutableTreeNode();
          currentTree.setUserObject(currentItem);
          fatherTree.add(currentTree);
          DisplayDemonstrationTree.
                               recurrentGenerateLittleTree
                                 (
                                 currentItem,
                                 currentTree
                                 );

         }
       }
       }

       }

      }
     }

   }

 public static javax.swing.tree.DefaultMutableTreeNode generateLittleTree
                                                        (
                                                         DemonstrationItem item
                                                        )
{

javax.swing.tree.DefaultMutableTreeNode tree =new DefaultMutableTreeNode();
if (item!=null)
{
 tree.setUserObject((Object)item);
 if (item.downLink!=null)
 {
 int dimension=item.downLink.size();
 if(dimension>0)
 {
  DisplayDemonstrationTree.recurrentGenerateLittleTree
                             (
                             item,
                             tree
                             );
 }

 }
 }

return tree;
};

public static javax.swing.tree.DefaultMutableTreeNode generateBigTree
                                                         (
                                                          DemonstrationItem item
                                                         )
{

javax.swing.tree.DefaultMutableTreeNode tree =new DefaultMutableTreeNode();
if (item!=null)
{
 tree.setUserObject((Object)item);
 if (item.downLink!=null)
 {
 int dimension=item.downLink.size();
 if(dimension>0)
 {
  DisplayDemonstrationTree.recurrentGenerateBigTree
                             (
                             item,
                             tree
                             );
 }

}
}

return tree;
};

public static javax.swing.tree.DefaultMutableTreeNode generateTree
                                                (
                                                DemonstrationItem item,
                                                boolean fullDemonstration
                                                )
{
    Application0 a=(Application0)Application.getInstance();
  
   if (fullDemonstration)
   {

       a.demonstrationDisplayMode=2;//full demonstration
       return DisplayDemonstrationTree.generateBigTree(item);
   }
   else
   {
       a.demonstrationDisplayMode=1;//essential demonstration
       return DisplayDemonstrationTree.generateLittleTree(item);
   }


}


}