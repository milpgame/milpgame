//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeModelListener;

import java.awt.Dimension;
import spv.Source;
import spv.ParsingItem;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import spv.gen.Hypothesis;
import spv.gen.Axiom;
import spv.gen.Theorem;
import spv.gen.DemonstrationItem;
import spv.gen.DemonstrationConstants;
import spv.MainWindow;
import javax.swing.ImageIcon;
import org.jdesktop.application.Application;
import spv.gen.Variable;


public class FilePanel extends javax.swing.JPanel
{

    private javax.swing.JScrollPane propertiesPanel;
    private javax.swing.JSplitPane horizontalPillar;
    private javax.swing.JSplitPane verticalPillar;
    private javax.swing.JScrollPane contentPanel;
    private javax.swing.JScrollPane summaryPanel;
    private   DefaultTreeModel treeModel;
    private   DefaultTreeSelectionModel selectionModel;
    private   DefaultTreeSelectionModel demonstrationSelectionModel;
    
    private void initializes()
    {
        verticalPillar = new javax.swing.JSplitPane();
        horizontalPillar = new javax.swing.JSplitPane();
        contentPanel = new javax.swing.JScrollPane();
        propertiesPanel = new javax.swing.JScrollPane();
        summaryPanel = new javax.swing.JScrollPane();


        this.setName("FilePanel");

        verticalPillar.setName("verticalPillar");

        horizontalPillar.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        horizontalPillar.setName("horizontalPillar");

        contentPanel.setName("contentPanel");
        horizontalPillar.setTopComponent(contentPanel);

        propertiesPanel.setName("propertiesPanel");
        horizontalPillar.setRightComponent(propertiesPanel);
        horizontalPillar.setDividerLocation(300);
        verticalPillar.setRightComponent(horizontalPillar);

        summaryPanel.setName("summaryPanel"); // NOI18N
        verticalPillar.setLeftComponent(summaryPanel);
        verticalPillar.setDividerLocation(350);

        javax.swing.GroupLayout filePanelLayout1 = new javax.swing.GroupLayout(this);
        this.setLayout(filePanelLayout1);
        filePanelLayout1.setHorizontalGroup(
            filePanelLayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(verticalPillar, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
        );
        filePanelLayout1.setVerticalGroup(
            filePanelLayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(verticalPillar, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
        );
      
    }
    public FilePanel()
    {
        super();
        initializes();
        
    }
    
    private  String viewVariableText(Variable  variable0)
    {
      String displayText="";

     if (variable0!=null)
    {
       String s1=variable0.variableText,s2="";
       s2=this.S.htlmldefString1AsString2.get(s1);
       if (s2==null) s2=s1;
       displayText=s2;

    }else displayText="Variabila nu exista" ;
    Application0 a=(Application0)Application.getInstance();
    // encapsulate it in html and replace \n with <br>
    displayText="<HTML>"
              +"<base href='file:"+a.path+"/symbols/  '/>"
              +displayText
              +" </HTML>";

    return displayText;
    };

    public class ArborescentModelListener implements TreeModelListener {
        public void treeNodesChanged(TreeModelEvent e) {
            DefaultMutableTreeNode node;
            node = (DefaultMutableTreeNode)(e.getTreePath().getLastPathComponent());

            /*
             * If the event lists children, then the changed
             * node is the child of the node we've already
             * gotten.  Otherwise, the changed node and the
             * specified node are the same.
             */


                int index = e.getChildIndices()[0];
                 node = (DefaultMutableTreeNode)(node.getChildAt(index));
          
        }
        public void treeNodesInserted(TreeModelEvent e) {
        }
        public void treeNodesRemoved(TreeModelEvent e) {
        }
        public void treeStructureChanged(TreeModelEvent e) {
        }
    }
    public class SelectionListener implements TreeSelectionListener {

        public void valueChanged(TreeSelectionEvent e)
        {
         DefaultMutableTreeNode node;
            node = (DefaultMutableTreeNode)(e.getPath().getLastPathComponent());
         ParsingItem el=(ParsingItem) node.getUserObject();
         updateContentPanel(loadContent(el));
        }

    }
    public class DemonstrationSelectionListener
           implements TreeSelectionListener
    {

        public void valueChanged(TreeSelectionEvent e)
        {
         pressedCellOfDemostrationTree(e);
        }

    }
     private void pressedCellOfDemostrationTree(TreeSelectionEvent e)
     {
         DefaultMutableTreeNode node;
            node = (DefaultMutableTreeNode)(e.getPath().getLastPathComponent());
         DemonstrationItem item=(DemonstrationItem) node.getUserObject();
        
        if (item!=null)
        {
          
         String header="";
             if (
                 (item.type==DemonstrationConstants.SIMPLE_THEOREM)|
                 (item.type==DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM)
                )
             {
                 header = "Theorem ";
             }
             else
                if (
                 (item.type==DemonstrationConstants.SIMPLE_AXIOM)|
                 (item.type==DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM)
                )
             {
                 header = "Axiom ";
             }
             else
                if
                (
                 (item.type==DemonstrationConstants.PROPER_HYPOTHESIS)
                )
             {
                 header = "Hypothesis ";
             }
           if(header!="")
            {
             String text=item.name;
             text="<HTML> Add to List of Rules: <br>"+header+text+"</HTML>";
             String propertiesText=item.name;
             propertiesText=
                     "<HTML> Properties of: <br>"
                     +header
                     +propertiesText
                     +"</HTML>";
             this.frame.addToListButton.setEnabled(true);
             this.frame.addToListButton.setText(text);
             this.frame.propertiesButton.setText(propertiesText);
             this.frame.nameOfSelectedItem=item.name;
             this.frame.typeOfSelectedItem=item.type;
            }
             
                       
             
         }
        
         
     }


    public void updateSummary(DefaultMutableTreeNode  root)
    {
       javax.swing.JTree mainTree1 = new javax.swing.JTree();
        treeModel = new DefaultTreeModel(root);
        selectionModel=new DefaultTreeSelectionModel();
        selectionModel.setSelectionMode(
                DefaultTreeSelectionModel.SINGLE_TREE_SELECTION);
        selectionModel.addTreeSelectionListener(
                                  new SelectionListener());
        treeModel.addTreeModelListener(
                                  new ArborescentModelListener());

      mainTree1.setSelectionModel(selectionModel);
      mainTree1.setModel(treeModel);
      mainTree1.setAutoscrolls(true);
      mainTree1.setName("mainTree1");
      summaryPanel.setViewportView(mainTree1);
    
        int dimension2=0,dimension=mainTree1.getRowCount();
           do{
            for (int i=0;i<dimension;i++)
            {
                if(!(mainTree1.isExpanded(i)))
                {
                       mainTree1.expandRow(i);
                }

           }
            dimension2=mainTree1.getRowCount();
            if (dimension2==dimension) break;
            dimension=dimension2;
           }
            while (true);
        mainTree1.setAutoscrolls(true);
        summaryPanel.setViewportView(mainTree1);
        updateContentPanel(loadInitialContent());

    }
      public void updateContentPanel(javax.swing.JPanel panel)
    {
        contentPanel.setPreferredSize(new Dimension(650,700));
        contentPanel.setVerticalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        contentPanel.setHorizontalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        contentPanel.setViewportView(panel);
    }

      public void updatePropertiesPanel(javax.swing.JPanel panel)
    {
       
        propertiesPanel.setVerticalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        propertiesPanel.setHorizontalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        propertiesPanel.setViewportView(panel);
    }

Source S=null;
MainWindow frame=null;
ParsingItem root;
ParsingItem selectedItem=null;//selected item with the mouse

int xMargin=60,yMargin=10;
int xSpace=10,ySpace=10;
int xBlockMargin=2,yBlockMargin=2,xRightSpace=10;
int xBlockSpace=2,yBlockSpace=2;
int xMarginComment=10,yMarginComment=10;
int xSpaceComment=10,ySpaceComment=10;
int maximumCommentWidth=700;//500
int referenceWidth=800;
int chapterWidth=440;//450

int x=xMargin,y=yMargin;//absolute positioning in base panel
//provisionally
int width1=60,height1=35;
int commentWidth,commentHeight;
int baseHeight=10;
int maximumBaseHeight=0;
//END provisionally

int windowDimension=30;
int position=0;

boolean fullDemonstration0=false;//type of demonstration

//data about demonstration tree
javax.swing.JButton demonstrationSwitch=null;
javax.swing.JTree demonstrationTree=null;
DemonstrationItem demonstrationSource=null;
int xBeforeDemonstration=0,yBeforeDemonstration=0;
int baseWidthBeforeDemonstration=0,baseHeightBeforeDemonstration=0;
int maximumBaseHeightBeforeDemonstration=0;
//END data about demonstration tree

//we have a table of links between the label and the parsing item
    Map<javax.swing.JLabel,ParsingItem>
     labelAndItem=
     new java.util.HashMap<javax.swing.JLabel,ParsingItem>();
 //link between a label and index of item represented by
 //the proper hypothesis or the proper variable(base)
 public Map<javax.swing.JLabel,String> labelAndName=
                       new java.util.HashMap<javax.swing.JLabel,String>();
 
public void visualContent(Source source1,MainWindow window1)
{
 this.S=source1;
 this.frame=window1;
 if(this.S!=null)
 {
  //we update the data root
  this.root=this.S.dataRoot;
 }
}

private static void errorNewLine( String msg)
{
System.out.println(msg);
};

private static void displayNewLine( String msg)
{
//System.out.println(msg);
};

public  javax.swing.JPanel  createBase()
{
    //initialize variables
    x=xMargin;y=yMargin;//absolute positioning in base panel
    //provisionally
    width1=60;height1=35;
    commentWidth=0;commentHeight=0;
    baseHeight=10;
    maximumBaseHeight=0;
    //END initialize variables
    javax.swing.JPanel basePanel=new javax.swing.JPanel();
     basePanel.setLayout(null);
     Dimension dimension0=new Dimension();
     dimension0.width=referenceWidth+xRightSpace;
     dimension0.height=10;
     basePanel.setPreferredSize(dimension0);
     basePanel.setBackground(new java.awt.Color(255, 255, 255));
     return basePanel;
}
private void pressOnAssumption(java.awt.event.MouseEvent evt)
{
        String xyzName=this.labelAndName.get
                 (
                 (javax.swing.JLabel) evt.getComponent()
                 );
         if (xyzName!=null)
         {
             this.frame.typeOfSelectedItem=DemonstrationConstants.PROPER_HYPOTHESIS;
             this.frame.nameOfSelectedItem=xyzName;
            
              String textBAddList=
                      "<HTML> Add to List of Rules: <br> Hypothesis "
                      +xyzName+"</HTML>";

             this.frame.addToListButton.setEnabled(true);
             this.frame.addToListButton.setText(textBAddList);
         }
}
private void pressOnVariable(java.awt.event.MouseEvent evt)
{
        String xyzName=this.labelAndName.get
                 (
                 (javax.swing.JLabel) evt.getComponent()
                 );
         if (xyzName!=null)
         {
             this.frame.typeOfSelectedItem=DemonstrationConstants.PROPER_VARIABLE;
             this.frame.nameOfSelectedItem=xyzName;

             String text="<HTML> Remember: <br> Variable "
                         +xyzName+" </HTML>";
             
         }
}
public  javax.swing.JPanel loadContent(ParsingItem fromNowOn)
{
//we create base panel
javax.swing.JPanel basePanel=createBase();

if (root!=null)
{
if (root.typeSymbol!=Source.T_FATHER_FILE)
    errorNewLine("This data structure is unknown");
else
{
 if (root.containedItems!=null)
{
if (root.containedItems.size()>0)
{
 ParsingItem subordinatedItem=null;
 int dimension=root.containedItems.size();


 position=root.containedItems.indexOf(fromNowOn);
//we go through the items of root of file type
 for (int i1=0;i1<windowDimension;i1++)
 {
     if((position+i1<dimension)&(position+i1>=0))
     {
      subordinatedItem=root.containedItems.get(position+i1);
      this.createVisualItem(subordinatedItem,basePanel);
     }

 }
 int max1=root.containedItems.size();
 if(max1>=windowDimension)
 {
 //buttons
 //previous

 if(position>0)
 {
javax.swing.JButton previous=this.createButton("Back");
previous.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {

 ParsingItem item1=root;
 if (position-windowDimension>=0)
 {
  item1=root.containedItems.get(position-windowDimension);
 }
 else if (position-windowDimension<=0)
 {
  item1=root.containedItems.get(0);
 }
 if(item1!=null)
 {
 x=xMargin;y=yMargin;baseHeight=10;
 updateContentPanel(loadContent(item1));
 }

}
                                });
Dimension d=previous.getPreferredSize();
width1=d.width;
height1=d.height;

x=referenceWidth/2-width1;
y=y+maximumBaseHeight+ySpace;
maximumBaseHeight=height1;

if (y+height1+ySpace>baseHeight)
{
  int dist=y+height1+ySpace-baseHeight;
baseHeight=baseHeight+dist;

}
basePanel.setPreferredSize(new Dimension(referenceWidth+xRightSpace,baseHeight));
basePanel.add(previous);
previous.setBounds(x, y, width1,height1);


}
 
 if(position+windowDimension<(root.containedItems.size()))
 {
  if(height1>maximumBaseHeight) maximumBaseHeight=height1;
  x=x+width1+xSpace;


//next
javax.swing.JButton next=this.createButton("Next");
next.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
 int max=root.containedItems.size();
 ParsingItem item1=root;
 if ((position+windowDimension<max)&(position+windowDimension>=0))
 {
  item1=root.containedItems.get(position+windowDimension);
 }
 if(item1!=null)
 {
 x=xMargin;y=yMargin;baseHeight=10;
 updateContentPanel(loadContent(item1));
 }

}
 });
Dimension d=next.getPreferredSize();
width1=d.width;
height1=d.height;
      displayNewLine("1("+width1+","+height1+")");

basePanel.setPreferredSize(
      new Dimension(referenceWidth+xRightSpace,baseHeight));
basePanel.add(next);
next.setBounds(x, y, width1,height1);
displayNewLine("1["+x+","+y+","+width1+","+height1+"]");
if(height1>maximumBaseHeight) maximumBaseHeight=height1;
x=x+width1+xSpace;

//END buttons
}
}
}
}
}
}

return basePanel;
};
public  javax.swing.JPanel loadInitialContent()
{
//we create base panel
javax.swing.JPanel basePanel=createBase();

if (root!=null)
{
if (root.typeSymbol!=Source.T_FATHER_FILE)
    errorNewLine("This data structure is unknown");
else
{
 if (root.containedItems!=null)
{
if (root.containedItems.size()>0)
{
 ParsingItem subordinatedItem=null;
 int dimension=root.containedItems.size();


 position=0;//we set to zero the position
//we go through the items of the root of file type
 for (int i1=0;i1<windowDimension;i1++)
 {
     if(position+i1<dimension)
     {
      subordinatedItem=root.containedItems.get(position+i1);
      this.createVisualItem(subordinatedItem,basePanel);
     }

 }

int max1=root.containedItems.size();

 if(max1>=windowDimension)
 {
  //buttons
  //previous

if(position>0)
{
javax.swing.JButton previous=this.createButton("Back");
previous.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {

 ParsingItem item1=root;
 if (position-windowDimension>=0)
 {
  item1=root.containedItems.get(position-windowDimension);
 }
 else if (position-windowDimension<=0)
 {
  item1=root.containedItems.get(0);
 }
 if(item1!=null)
 {
 x=xMargin;y=yMargin;baseHeight=10;
 updateContentPanel(loadContent(item1));
 }

}
});
Dimension d=previous.getPreferredSize();
width1=d.width;
height1=d.height;

x=referenceWidth/2-width1;
y=y+maximumBaseHeight+ySpace;
maximumBaseHeight=height1;

if (y+height1+ySpace>baseHeight)
{
  int dist=y+height1+ySpace-baseHeight;
baseHeight=baseHeight+dist;

}
basePanel.setPreferredSize(new Dimension(referenceWidth+xRightSpace,baseHeight));
basePanel.add(previous);
previous.setBounds(x, y, width1,height1);


}

if (position+windowDimension<(root.containedItems.size()))
{
 if(height1>maximumBaseHeight) maximumBaseHeight=height1;
x=x+width1+xSpace;
 //next
javax.swing.JButton next=this.createButton("Next");
next.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
 int max=root.containedItems.size();
 ParsingItem item1=root;
 if (position+windowDimension<max)
 {
  item1=root.containedItems.get(position+windowDimension);
 }
 if(item1!=null)
 {
 x=xMargin;y=yMargin;baseHeight=10;
 updateContentPanel(loadContent(item1));
 }

}
 });
Dimension d=next.getPreferredSize();
width1=d.width;
height1=d.height;
      displayNewLine("1("+width1+","+height1+")");

basePanel.setPreferredSize(
      new Dimension(referenceWidth+xRightSpace,baseHeight));
basePanel.add(next);
next.setBounds(x, y, width1,height1);
displayNewLine("1["+x+","+y+","+width1+","+height1+"]");
if(height1>maximumBaseHeight) maximumBaseHeight=height1;
x=x+width1+xSpace;
}
//END buttons
}

}
}
}
}

return basePanel;
};
public  javax.swing.JPanel loadProperties(ParsingItem xItem)
{
//we create the base panel
javax.swing.JPanel basePanel=createBase();

if (xItem!=null)
{
 if (xItem.generatedItem!=null)
 {
     if (xItem.generatedItem.getClass()==spv.gen.Theorem.class)
     {
         Theorem t1=(Theorem)xItem.generatedItem;
         DemonstrationItem el_dem=this.S.generateDemonstration(xItem);
         this.createVisualTheoremItem(t1,el_dem,basePanel);
     }
     else
     if (xItem.generatedItem.getClass()==spv.gen.Axiom.class)
     {
       this.createVisualAxiomItem((Axiom)xItem.generatedItem,basePanel);
     }
 }
}
return basePanel;
};
private void createVisualItem(ParsingItem item,javax.swing.JPanel basePanel)
{

  if ((item.functionSymbol==Source.F_LABEL_F)|
      (item.functionSymbol==Source.F_LABEL_E)|
      (item.functionSymbol==Source.F_LABEL_A)|
      (item.functionSymbol==Source.F_LABEL_P)|
             (item.codeSymbol == Source.C_C_SYMBOL)|
               (item.codeSymbol==Source.C_D_SYMBOL)|
               (item.codeSymbol==Source.C_V_SYMBOL)|
                (item.codeSymbol==Source.C_OPEN_SQUARE_BRACKET))
    {
     javax.swing.JLabel label=this.createLabel
                (this.viewText(item));

     //we make the link between label and parsing item
     if ((item.functionSymbol==Source.F_LABEL_P)|
      (item.functionSymbol==Source.F_LABEL_A))
        {
           this.labelAndItem.put(label, item);
        }
     //END link

       Dimension d=label.getPreferredSize();
        width1=d.width;
        height1=d.height;


     if ((x+width1+xSpace>referenceWidth)|(item.onNewLine))
      {
          x=xMargin;
          y=y+maximumBaseHeight+ySpace;
          maximumBaseHeight=height1;
      }

      if (y+height1+ySpace>baseHeight)
    {
          int distance=y+height1+ySpace-baseHeight;
        baseHeight=baseHeight+distance;

    }
      basePanel.setPreferredSize(
                            new Dimension(referenceWidth+xRightSpace,baseHeight));
      basePanel.add(label);
      label.setBounds(x, y, width1,height1);

      if(height1>maximumBaseHeight) maximumBaseHeight=height1;
       x=x+width1+xSpace;
    }
 //here we have a comment with his subordinates
 else if (item.typeSymbol==Source.T_COMMENT)
 {
     boolean simpleComment=false;
     commentWidth=10;commentHeight=10;
      javax.swing.JPanel commentPanel = this.createComment();

     int dimension=0;
    if (item.containedItems!=null)
    {
    if (item.containedItems.size()>0)
    {
     dimension=item.containedItems.size();
    }
    }

    if (dimension==1)
    {
        if ((item.containedItems.get(0).typeSymbol==Source.TN_CONTENT_FROM_COMMENT)|
           (item.containedItems.get(0).typeSymbol==Source.TN_CHAPTER_FROM_COMMENT)|
           (item.containedItems.get(0).typeSymbol==Source.TN_SUPERCHAPTER_FROM_COMMENT)|
           (item.containedItems.get(0).typeSymbol==Source.TN_SUBCHAPTER_FROM_COMMENT)
            )
        {
            simpleComment=true;
        }
    }
 if (!simpleComment)
 {
    int x1=xMarginComment;int y1=yMarginComment;
    int maximumHeight1=0;
    for(int i=0;i<dimension;i++)
    {
        boolean weAreInVisualSettings=false;
        ParsingItem someItem=item.containedItems.get(i);
        if (someItem.typeSymbol==Source.T_COMMENT)
        {
            if (someItem.containedItems!=null)
            {
            if (someItem.containedItems.size()>0)
            {
              //we choose the first item from the comment
                boolean line=someItem.onNewLine;
              someItem=someItem.containedItems.get(0);
              someItem.onNewLine=line;
            }
            }
        }
        ParsingItem parentItem=someItem;
        int i2=0;
       do {
        javax.swing.JLabel label=null;
        Dimension d;
        label=this.createCommentLabel
                (this.viewText(someItem));
        d=label.getPreferredSize();
        width1=d.width;
        height1=d.height;

        //if we have a chapter,subchapter,superchapter,chapter_from_typesettings item
        //we set width1 at maximum

        if ((someItem.typeSymbol==Source.TN_CHAPTER_FROM_COMMENT)|
            (someItem.typeSymbol==Source.TN_SUBCHAPTER_FROM_COMMENT)|
            (someItem.typeSymbol==Source.TN_SUPERCHAPTER_FROM_COMMENT)|
            (someItem.typeSymbol==Source.TN_CHAPTER_FROM_TYPESETTINGS)
              )
             {
             width1=chapterWidth;
             }


       if ((x1+width1+xSpaceComment>maximumCommentWidth)|(someItem.onNewLine))
          {
              x1=xMarginComment;
              y1=y1+maximumHeight1+yMarginComment;
              maximumHeight1=height1;
          }

       if ((x1+width1+xSpaceComment>commentWidth)&
                                         (x1+width1+xSpaceComment<=maximumCommentWidth))
          {
              commentWidth=x1+width1+xSpaceComment;

          }
          if (y1+height1+ySpaceComment>commentHeight)
        {
              int dist=y1+height1+ySpaceComment-commentHeight;
            commentHeight=commentHeight+dist;

        }

      commentPanel.setPreferredSize(new Dimension(commentWidth,commentHeight));
      commentPanel.add(label);
      label.setBounds(x1, y1, width1,height1);

      if(height1>maximumHeight1) maximumHeight1=height1;
       y1=y1+height1+10;

      //here we check if this was a $t mark (typesettings mark)
      if(someItem.typeSymbol==Source.TN_DISPLAY)
      {
         if (someItem.containedItems!=null)
         {
           if(someItem.containedItems.size()>0)
           {
               weAreInVisualSettings = true;
               someItem=parentItem.containedItems.get(i2);
               i2++;
           }
         }
       }
       //or we are already in interior of $t mark
      else if (weAreInVisualSettings)
      {
          if (i2<parentItem.containedItems.size())
          {
              //we pass to the nex item from $t mark
              someItem=parentItem.containedItems.get(i2);
              i2++;
          }
           else weAreInVisualSettings=false;//we get out from loop
      }

     } while(weAreInVisualSettings);

    }

        if ((x+commentWidth+xSpace>referenceWidth)|(item.onNewLine))
      {
          x=xMargin;
          y=y+maximumBaseHeight+ySpace;
          maximumBaseHeight=commentHeight;
      }
      if (y+commentHeight+ySpace>baseHeight)
    {
          int dist=y+commentHeight+ySpace-baseHeight;
        baseHeight=baseHeight+dist;

    }
    basePanel.setPreferredSize(
                            new Dimension(referenceWidth+xRightSpace,baseHeight));

    basePanel.add(commentPanel);
    commentPanel.setBounds(x, y, commentWidth, commentHeight);
    if(commentHeight>maximumBaseHeight) maximumBaseHeight=commentHeight;
    x=x+commentWidth+xSpace;
  }//END this is not a simple comment

 else//here follows a simple comment
 {   boolean line=item.onNewLine;
     ParsingItem simpleItem=item.containedItems.get(0);
     simpleItem.onNewLine=line;
     javax.swing.JLabel label=
               this.createCommentLabel(this.viewText(simpleItem));
       Dimension d=label.getPreferredSize();
        width1=d.width;
        height1=d.height;

         if ((simpleItem.typeSymbol==Source.TN_CHAPTER_FROM_COMMENT)|
            (simpleItem.typeSymbol==Source.TN_SUBCHAPTER_FROM_COMMENT)|
            (simpleItem.typeSymbol==Source.TN_SUPERCHAPTER_FROM_COMMENT)|
            (simpleItem.typeSymbol==Source.TN_CHAPTER_FROM_TYPESETTINGS)
              )
             {
             width1=chapterWidth;
             }

        if ((x+width1+xSpace>referenceWidth)|(simpleItem.onNewLine))
      {
          x=xMargin;
          y=y+maximumBaseHeight+ySpace;
          maximumBaseHeight=height1;
      }
      if (y+height1+ySpace>baseHeight)
    {
          int dist=y+height1+ySpace-baseHeight;
        baseHeight=baseHeight+dist;

    }
      basePanel.setPreferredSize(
                        new Dimension(referenceWidth+xRightSpace,baseHeight));
      basePanel.add(label);
      label.setBounds(x, y, width1,height1);

      if(height1>maximumBaseHeight) maximumBaseHeight=height1;
       x=x+width1+xSpace;
  }

 }
 //here we have a scope block with its subordinates
 else if (item.codeSymbol==Source.C_OPEN_BRACE)
 {
     int blockWidth,blockHeight;
    javax.swing.JPanel panou_bloc=this.processBlock(item);
    Dimension dd=panou_bloc.getPreferredSize();
    blockWidth=dd.width;blockHeight=dd.height;

      //at the advent of a block we automatically pass to the next line
      x=xMargin;
      y=y+maximumBaseHeight+ySpace;
      maximumBaseHeight=blockHeight;

      if (y+blockHeight+ySpace>baseHeight)
    {
        int dist=y+blockHeight+ySpace-baseHeight;
        baseHeight=baseHeight+dist;
    }
    basePanel.setPreferredSize(
                             new Dimension(referenceWidth+xRightSpace,baseHeight));
    basePanel.add(panou_bloc);
    panou_bloc.setBounds(x, y, blockWidth, blockHeight);
    x=x+blockWidth+xSpace;
 }

}

private void createVisualTheoremItem
        (Theorem theoremItem,DemonstrationItem demonstrationSource2,
         javax.swing.JPanel basePanel)
{
  if (theoremItem!=null)
  {
    if (theoremItem.items!=null)
    {
    if (theoremItem.items.size()>0)
    {
     if (this.S.editable)
     {
        //edit button
        //we display the button only if the source is editable
      javax.swing.JButton editButton=this.createButton("Edit this proof");
       editButton.addActionListener(
            new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
             pressedEditDemonstrationButton(evt);
            }
        });
       Dimension d_editare=editButton.getPreferredSize();
        width1=150;
        height1=d_editare.height;

          x=xMargin+referenceWidth/2-width1/2;
          y=y+maximumBaseHeight+ySpace;
          maximumBaseHeight=height1;

      if (y+height1+ySpace>baseHeight)
        {
            int dist=y+height1+ySpace-baseHeight;
            baseHeight=baseHeight+dist;

        }
      basePanel.setPreferredSize(
              new Dimension(referenceWidth+xRightSpace,baseHeight));
      basePanel.add(editButton);
      editButton.setBounds(x, y, width1,height1);

      if(height1>maximumBaseHeight) maximumBaseHeight=height1;
       x=x+width1+xSpace;
      //END edit Button
      }

     //add rules button
        //we display the button only if the source is editable
      javax.swing.JButton addRulesButton=this.createButton("Add all rules to LR");
       addRulesButton.addActionListener(
            new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
             pressedAddRulesButton(evt);
            }
        });
       Dimension d_addRules=addRulesButton.getPreferredSize();
        width1=150;
        height1=d_addRules.height;

          x=xMargin+referenceWidth/2-width1/2;
          y=y+maximumBaseHeight+ySpace;
          maximumBaseHeight=height1;

      if (y+height1+ySpace>baseHeight)
        {
            int dist=y+height1+ySpace-baseHeight;
            baseHeight=baseHeight+dist;

        }
      basePanel.setPreferredSize(
              new Dimension(referenceWidth+xRightSpace,baseHeight));
      basePanel.add(addRulesButton);
      addRulesButton.setBounds(x, y, width1,height1);

      if(height1>maximumBaseHeight) maximumBaseHeight=height1;
       x=x+width1+xSpace;
      //END add rules Button

        //we verify if has hypotheses
             if (theoremItem.hypotheses!=null)
             {
             if (theoremItem.hypotheses.size()>0)
             {
               int dimensionHypotheses=theoremItem.hypotheses.size();

               for (int i=0;i<dimensionHypotheses;i++)
               {
                   Hypothesis iHypothesis=theoremItem.hypotheses.get(i);
                    //display i hypothesis
                    if (iHypothesis!=null)
                    {
                     javax.swing.JLabel label=this.createLabel
                                (this.viewHypothesisText(iHypothesis));
                      //we make the link between label and number of order
                       String name=theoremItem.hypotheses.get(i).name;
                       this.labelAndName.put(label,name);
                      //we attach mouse listener
                     label.addMouseListener(new java.awt.event.MouseAdapter()
                       {
                            @Override
                            public void mousePressed
                                    (java.awt.event.MouseEvent evt)
                            {
                                pressOnAssumption(evt);
                            }
                        });
                       Dimension d=label.getPreferredSize();
                        width1=d.width;
                        height1=d.height;

                      x=xMargin+referenceWidth/2-width1/2;
                      y=y+maximumBaseHeight+ySpace;
                      maximumBaseHeight=height1;

                      if (y+height1+ySpace>baseHeight)
                    {
                          int dist=y+height1+ySpace-baseHeight;
                        baseHeight=baseHeight+dist;

                    }
                      basePanel.setPreferredSize(
                           new Dimension(referenceWidth+xRightSpace,baseHeight));
                      basePanel.add(label);
                      label.setBounds(x, y, width1,height1);

                      if(height1>maximumBaseHeight) maximumBaseHeight=height1;
                       x=x+width1+xSpace;
                    }
                   //END display i hypothesis
               }
             }
             }

        //END verification hypotheses

        //we verify if has total variables
             x=xMargin+referenceWidth/2-width1/2;
             y=y+maximumBaseHeight+ySpace;
             if (theoremItem.totalVariables!=null)
             {
             if (theoremItem.totalVariables.size()>0)
             {
               int dimensionTotalVariables=theoremItem.totalVariables.size();
                 //display label
                 String s="<HTML>"
                     +"<FONT COLOR='#0033ff' SIZE=4>"
                     +"Variables:"
                     +"</FONT>"
                     +"</HTML>";

                   javax.swing.JLabel label0=this.createCommentLabel(s);
                   Dimension d0=label0.getPreferredSize();
                    width1=d0.width;
                    height1=d0.height;
                      
                      maximumBaseHeight=height1;

                      if (y+height1+ySpace>baseHeight)
                    {
                          int dist=y+height1+ySpace-baseHeight;
                        baseHeight=baseHeight+dist;
                    }
                  basePanel.setPreferredSize(
                       new Dimension(referenceWidth+xRightSpace,baseHeight));
                  basePanel.add(label0);
                  label0.setBounds(x, y, width1,height1);

                  if(height1>maximumBaseHeight) maximumBaseHeight=height1;
                   x=x+width1+xSpace;
                  //END display label

               for (int i=0;i<dimensionTotalVariables;i++)
               {
                   Variable iVariable=theoremItem.totalVariables.get(i);
                    //display i variable
                    if (iVariable!=null)
                    {
                     javax.swing.JLabel label=this.createLabel
                                (this.viewVariableText(iVariable));
                      //we make the link between label and number of order
                       String name=theoremItem.totalVariables.get(i).variableText;
                       this.labelAndName.put(label,name);
                      //we attach a mouse listener
                     label.addMouseListener(new java.awt.event.MouseAdapter()
                       {
                            @Override
                            public void mousePressed
                                    (java.awt.event.MouseEvent evt)
                            {
                                pressOnVariable(evt);
                            }
                        });
                       Dimension d=label.getPreferredSize();
                        width1=d.width;
                        height1=d.height;

                        maximumBaseHeight=height1;

                      if (y+height1+ySpace>baseHeight)
                    {
                          int dist=y+height1+ySpace-baseHeight;
                        baseHeight=baseHeight+dist;

                    }
                      basePanel.setPreferredSize(
                           new Dimension(referenceWidth+xRightSpace,baseHeight));
                      basePanel.add(label);
                      label.setBounds(x, y, width1,height1);

                      if(height1>maximumBaseHeight) maximumBaseHeight=height1;
                       x=x+width1+xSpace;
                    }
                   //END display i variable
               }
             }
             }

        //END verification of total variables
        //display distinct variables
             if (theoremItem.distinct!=null)
             {
             if (theoremItem.distinct.size()>0)
             {
               int dimensionDistinct=theoremItem.distinct.size();

               for (int i=0;i<dimensionDistinct;i++)
               {

                    //display i list
                    if (theoremItem.distinct.get(i)!=null)
                    {
                      List<String> iList=theoremItem.distinct.get(i);
                     javax.swing.JLabel label=this.createLabel
                     (this.viewDistinctText(iList));

                       Dimension d=label.getPreferredSize();
                        width1=d.width;
                        height1=d.height;

                      x=xMargin+referenceWidth/2-width1/2;
                      y=y+maximumBaseHeight+ySpace;
                      maximumBaseHeight=height1;

                      if (y+height1+ySpace>baseHeight)
                    {
                          int distance=y+height1+ySpace-baseHeight;
                        baseHeight=baseHeight+distance;

                    }
                      basePanel.setPreferredSize(
                           new Dimension(referenceWidth+xRightSpace,baseHeight));
                      basePanel.add(label);
                      label.setBounds(x, y, width1,height1);

                      if(height1>maximumBaseHeight) maximumBaseHeight=height1;
                       x=x+width1+xSpace;
                    }
                   //END display i List
               }
             }
             }

        //END display distinct variables
        //display proper theorem
        if (theoremItem.items!=null)
        {
         javax.swing.JLabel label=this.createLabel
                    (this.viewTheoremText(theoremItem));
           Dimension d=label.getPreferredSize();
            width1=d.width;
            height1=d.height;

              x=xMargin+referenceWidth/2-width1/2;
              y=y+maximumBaseHeight+ySpace;
              maximumBaseHeight=height1;

          if (y+height1+ySpace>baseHeight)
        {
              int dist=y+height1+ySpace-baseHeight;
            baseHeight=baseHeight+dist;

        }
          basePanel.setPreferredSize(
               new Dimension(referenceWidth+xRightSpace,baseHeight));
          basePanel.add(label);
          label.setBounds(x, y, width1,height1);

          if(height1>maximumBaseHeight) maximumBaseHeight=height1;
           x=x+width1+xSpace;
        }

        //END display proper theorem

         //display label
         String s1="<HTML>"
             +"<FONT COLOR='#0033ff' SIZE=4><B>"
             +"Here is the proof:"
             +"</B></FONT>"
             +"</HTML>";
         String s2="<HTML>"
                 +"<FONT COLOR='#0033ff' SIZE=4><B>"
                 +"Here is no proof!"
                 +"</B></FONT>"
                 +"</HTML>";
         String s="";
        if (demonstrationSource2!=null)
        {
            s=s1;
        }else
        {
             s=s2;
        }


         javax.swing.JLabel label1=this.createCommentLabel(s);
           Dimension d=label1.getPreferredSize();
            width1=d.width;
            height1=d.height;

              x=xMargin+referenceWidth/2-width1/2;
              y=y+maximumBaseHeight+ySpace;
              maximumBaseHeight=height1;

          if (y+height1+ySpace>baseHeight)
        {
              int dist=y+height1+ySpace-baseHeight;
            baseHeight=baseHeight+dist;

        }
          basePanel.setPreferredSize(
               new Dimension(referenceWidth+xRightSpace,baseHeight));
          basePanel.add(label1);
          label1.setBounds(x, y, width1,height1);

          if(height1>maximumBaseHeight) maximumBaseHeight=height1;
           x=x+width1+xSpace;
       //END display label

       //switch button
       String essential="View essential details",
                                         full="View full details",switch0="";
       if (fullDemonstration0)
       {
        switch0=essential;
       }
        else
        {
           switch0=full;
        }

       demonstrationSwitch=this.createButton(switch0);
       demonstrationSwitch.addActionListener(
            new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
               pressedDemonstrationTypeButton(evt);
            }
        });
       Dimension dimensionSwitch=demonstrationSwitch.getPreferredSize();
        width1=200;
        height1=dimensionSwitch.height;

          x=xMargin+referenceWidth/2-width1/2;
          y=y+maximumBaseHeight+ySpace;
          maximumBaseHeight=height1;

      if (y+height1+ySpace>baseHeight)
    {
        int dist=y+height1+ySpace-baseHeight;
        baseHeight=baseHeight+dist;

    }
      basePanel.setPreferredSize(
              new Dimension(referenceWidth+xRightSpace,baseHeight));
      basePanel.add(demonstrationSwitch);
      demonstrationSwitch.setBounds(x, y, width1,height1);

      if(height1>maximumBaseHeight) maximumBaseHeight=height1;
       x=x+width1+xSpace;
      //END switch button

        //display demonstration

         //memorize
         xBeforeDemonstration=x;
         yBeforeDemonstration=y;
         baseWidthBeforeDemonstration=referenceWidth;
         baseHeightBeforeDemonstration=this.baseHeight;
         maximumBaseHeightBeforeDemonstration=maximumBaseHeight;
         //END memorize

         if (demonstrationSource2!=null)
        {
           demonstrationSource=demonstrationSource2;
           demonstrationTree=this.createDemonstrationTree(demonstrationSource2);
           Dimension d2=demonstrationTree.getPreferredSize();
            width1=d2.width;
            height1=d2.height;

              x=xMargin;
              y=y+maximumBaseHeight+ySpace;
              maximumBaseHeight=height1;

          if (y+height1+ySpace>baseHeight)
        {
            int dist=y+height1+ySpace-baseHeight;
            baseHeight=baseHeight+dist;

        }

         int width=0;
         width=referenceWidth;
         if (referenceWidth<width1) width=width1;

          basePanel.setPreferredSize(
               new Dimension(width+10*xRightSpace,baseHeight));
          basePanel.add(demonstrationTree);
          demonstrationTree.setBounds(x, y, width1,height1);

          if(height1>maximumBaseHeight) maximumBaseHeight=height1;
           x=x+width1+xSpace;
           this.revalidate();
        }
        //END display demonstration

    }
    }
    }




}
private void createVisualAxiomItem(Axiom axiomItem,javax.swing.JPanel basePanel)
{

  if (axiomItem!=null)
  {
    if (axiomItem.items!=null)
    {
    if (axiomItem.items.size()>0)
    {
       //we verify if has hypotheses
             if (axiomItem.hypotheses!=null)
             {
             if (axiomItem.hypotheses.size()>0)
             {
               int hypothesesDimension=axiomItem.hypotheses.size();

               for (int i=0;i<hypothesesDimension;i++)
               {
                   Hypothesis iHypothesis=axiomItem.hypotheses.get(i);
                    //display i hypothesis
                    if (iHypothesis!=null)
                    {
                     javax.swing.JLabel label=this.createLabel
                                (this.viewHypothesisText(iHypothesis));
                       Dimension d=label.getPreferredSize();
                        width1=d.width;
                        height1=d.height;

                          x=xMargin+referenceWidth/2-width1/2;
                          y=y+maximumBaseHeight+ySpace;
                          maximumBaseHeight=height1;

                      if (y+height1+ySpace>baseHeight)
                    {
                          int dist=y+height1+ySpace-baseHeight;
                        baseHeight=baseHeight+dist;

                    }
                      basePanel.setPreferredSize(
                           new Dimension(referenceWidth+xRightSpace,baseHeight));
                      basePanel.add(label);
                      label.setBounds(x, y, width1,height1);

                      if(height1>maximumBaseHeight) maximumBaseHeight=height1;
                       x=x+width1+xSpace;
                    }
                   //END display i hypothesis
               }
             }
             }

        //END verification of hypotheses
        //we verify if has total variables
             x=xMargin+referenceWidth/2-width1/2;
             y=y+maximumBaseHeight+ySpace;
             if (axiomItem.totalVariables!=null)
             {
             if (axiomItem.totalVariables.size()>0)
             {
               int totalVariablesDimension=axiomItem.totalVariables.size();
                //display label
                 String s="<HTML>"
                     +"<FONT COLOR='#0033ff' SIZE=4>"
                     +"Variables:"
                     +"</FONT>"
                     +"</HTML>";

                   javax.swing.JLabel label0=this.createCommentLabel(s);
                   Dimension d0=label0.getPreferredSize();
                    width1=d0.width;
                    height1=d0.height;
                      
                      maximumBaseHeight=height1;

                      if (y+height1+ySpace>baseHeight)
                    {
                          int dist=y+height1+ySpace-baseHeight;
                        baseHeight=baseHeight+dist;
                    }
                  basePanel.setPreferredSize(
                       new Dimension(referenceWidth+xRightSpace,baseHeight));
                  basePanel.add(label0);
                  label0.setBounds(x, y, width1,height1);

                  if(height1>maximumBaseHeight) maximumBaseHeight=height1;
                   x=x+width1+xSpace;
                 //END display label

               for (int i=0;i<totalVariablesDimension;i++)
               {
                   Variable iVariable=axiomItem.totalVariables.get(i);
                    //display i variable
                    if (iVariable!=null)
                    {
                     javax.swing.JLabel eticheta=this.createLabel
                                (this.viewVariableText(iVariable));

                       Dimension d=eticheta.getPreferredSize();
                        width1=d.width;
                        height1=d.height;
            

                      maximumBaseHeight=height1;

                      if (y+height1+ySpace>baseHeight)
                    {
                          int distance=y+height1+ySpace-baseHeight;
                        baseHeight=baseHeight+distance;

                    }
                      basePanel.setPreferredSize(
                           new Dimension(referenceWidth+xRightSpace,baseHeight));
                      basePanel.add(eticheta);
                      eticheta.setBounds(x, y, width1,height1);

                      if(height1>maximumBaseHeight) maximumBaseHeight=height1;
                       x=x+width1+xSpace;
                    }
                   //END display i variable
               }
             }
             }

        //END verify total variables
        //display distinct
             if (axiomItem.distinct!=null)
             {
             if (axiomItem.distinct.size()>0)
             {
               int dim_distincte=axiomItem.distinct.size();

               for (int i=0;i<dim_distincte;i++)
               {

                    //display i list
                    if (axiomItem.distinct.get(i)!=null)
                    {
                      List<String> iList=axiomItem.distinct.get(i);
                     javax.swing.JLabel label=this.createLabel
                     (this.viewDistinctText(iList));

                       Dimension d=label.getPreferredSize();
                        width1=d.width;
                        height1=d.height;

                      x=xMargin+referenceWidth/2-width1/2;
                      y=y+maximumBaseHeight+ySpace;
                      maximumBaseHeight=height1;

                      if (y+height1+ySpace>baseHeight)
                    {
                          int dist=y+height1+ySpace-baseHeight;
                        baseHeight=baseHeight+dist;

                    }
                      basePanel.setPreferredSize(
                           new Dimension(referenceWidth+xRightSpace,baseHeight));
                      basePanel.add(label);
                      label.setBounds(x, y, width1,height1);

                      if(height1>maximumBaseHeight) maximumBaseHeight=height1;
                       x=x+width1+xSpace;
                    }
                   //END display i list
               }
             }
             }

        //END display distinct
        //display proper axiom
        if (axiomItem.items!=null)
        {
         javax.swing.JLabel label=this.createLabel
                    (this.viewAxiomText(axiomItem));
           Dimension d=label.getPreferredSize();
            width1=d.width;
            height1=d.height;

              x=xMargin+referenceWidth/2-width1/2;
              y=y+maximumBaseHeight+ySpace;
              maximumBaseHeight=height1;

          if (y+height1+ySpace>baseHeight)
            {
            int dist=y+height1+ySpace-baseHeight;
            baseHeight=baseHeight+dist;
            }
          basePanel.setPreferredSize(
               new Dimension(referenceWidth+xRightSpace,baseHeight));
          basePanel.add(label);
          label.setBounds(x, y, width1,height1);

          if(height1>maximumBaseHeight) maximumBaseHeight=height1;
           x=x+width1+xSpace;
        }
       //END display proper axiom

    }
    }

  }

}
private  String viewDistinctText(List<String>  distinct)
{

int i=0;
String displayText="<P> <B> Distinct:<br> ";

 if (distinct!=null)
{
if (!distinct.isEmpty())
{
if (distinct.size()>0)
{
int dim=distinct.size();
do
{
//we display components items of list

String s1=distinct.get(i),s2="";
   s2=this.S.htlmldefString1AsString2.get(s1);
   if (s2==null) s2=s1;
displayText=displayText+" "+s2;
i++;
} while (i<dim);
displayText=displayText+"</B> </P>";

}
}

}

 else displayText="The list does not exist" ;
Application0 a=(Application0)Application.getInstance();
//we encapsulate it in html and replace it \n  with <br>
displayText="<HTML>"
          +"<base href='file:"+a.path+"/symbols/  '/>"
          +displayText
          +" </HTML>";

return displayText;
};
 public javax.swing.tree.DefaultMutableTreeNode generateSummary()
{
     Map<javax.swing.tree.DefaultMutableTreeNode,
                                       javax.swing.tree.DefaultMutableTreeNode>
             previousAndCurrent=
             new java.util.HashMap<javax.swing.tree.DefaultMutableTreeNode,
             javax.swing.tree.DefaultMutableTreeNode>();

        javax.swing.tree.DefaultMutableTreeNode tree=null,
                currentTree=null,previousTree=null;


        //current and previous parsing items
        ParsingItem  currentParsingItem=null,previousParsingItem=null;

        //this item has not a previous item
        currentParsingItem=this.S.dataRoot;
        //we generate the string for the current DefaultMutableTreeNode
        tree =new DefaultMutableTreeNode();
        tree.setUserObject(currentParsingItem);
        //END generate
        currentTree=tree;
        boolean untouchedNode=true, leafNode=false;

        do
        {

        int dimesion=0;
        //we verify if it has down links
        if (currentParsingItem.chapterItems!=null)
        {
        dimesion=currentParsingItem.chapterItems.size();leafNode=false;

        if (
            currentParsingItem.functionSymbol==Source.F_SUBCHAPTER_COMMENT
           )

        {
            leafNode=true;//we consider it leaf
        }

        {
        }
        }
        else {
        dimesion=0;leafNode=true;
        }

        if((dimesion>0)&untouchedNode&!leafNode)
        {
         //we descent on down link
         //we choose first descendant
        currentParsingItem=currentParsingItem.chapterItems.get(0);
        //END downhill

        if ((currentParsingItem.functionSymbol==Source.F_CHAPTER_COMMENT)|
            (currentParsingItem.functionSymbol==Source.F_SUBCHAPTER_COMMENT)|
            (currentParsingItem.functionSymbol==Source.F_SUPERCHAPTER_COMMENT)
            )
        {
        //we generate the display for current DefaultMutableTreeNode
        previousTree=currentTree;
        currentTree =new DefaultMutableTreeNode();
        currentTree.setUserObject(currentParsingItem);
        previousTree.add(currentTree);
        previousAndCurrent.put(currentTree, previousTree);
        //END generate
        }
        else
        {
        //we generate the display for current DefaultMutableTreeNode
        previousTree=currentTree;
        currentTree =new DefaultMutableTreeNode();
        currentTree.setUserObject(currentParsingItem);
        previousAndCurrent.put(currentTree, previousTree);
        //END generate
        }
          untouchedNode=true;//we are on a untouched node
        }

        else
        {
            //else if it is a touched node

        if(this.S.dataRoot!=currentParsingItem)
        {


        if (currentParsingItem.fatherChapter!=null)
        {
        previousParsingItem = currentParsingItem.fatherChapter;


        }
        else {errorNewLine("We do not have a previous parsing item");}

        if (previousAndCurrent.get(currentTree)!=null)
        {
        previousTree=previousAndCurrent.get(currentTree);
        }
        else{errorNewLine("We do not have a previous tree");}

        int position0=previousParsingItem.chapterItems.indexOf(currentParsingItem);


        //if if if if
        if (position0+1<previousParsingItem.chapterItems.size())
        {
         //we descend on position0+1 branch and we choose the p+1 descedant
        currentParsingItem=previousParsingItem.chapterItems.get(position0+1);
        //END downhill
        
       if ((currentParsingItem.functionSymbol==Source.F_CHAPTER_COMMENT)|
            (currentParsingItem.functionSymbol==Source.F_SUBCHAPTER_COMMENT)|
            (currentParsingItem.functionSymbol==Source.F_SUPERCHAPTER_COMMENT)
            )
        {
        //generate display
        currentTree =new DefaultMutableTreeNode();
        currentTree.setUserObject(currentParsingItem);
        previousTree.add(currentTree);
        previousAndCurrent.put(currentTree, previousTree);
        //END display
         }
         else
        {
        //generate display
        currentTree =new DefaultMutableTreeNode();
        currentTree.setUserObject(currentParsingItem);
        previousAndCurrent.put(currentTree, previousTree);
        //END display
        }
        untouchedNode=true;//we are on an untouched node

        }
        else
        {
        currentParsingItem=previousParsingItem;
        currentTree=previousTree;

        untouchedNode=false;//we are on a touched node
        }
        }
        }

        }
        while(this.S.dataRoot!=currentParsingItem);

        return tree;
};

public javax.swing.JPanel processBlock(ParsingItem blockItem)
{
 int blockWidthHere=10,blockHeightHere=10;
 int widthHere=10,heightHere=10;
 int maximumWidthHere=520;
 int xHere=xBlockMargin;int yHere=yBlockMargin;
 int maximumHeightHere=0;
    javax.swing.JPanel blockPanel = this.createBlock();

     int dimension=0;
    if (blockItem.containedItems!=null)
    {
    if (blockItem.containedItems.size()>0)
    {
     dimension=blockItem.containedItems.size();
    }
    }


    for(int i=0;i<dimension;i++)
    {
        ParsingItem someItem=blockItem.containedItems.get(i);
        if (someItem.typeSymbol==Source.T_COMMENT)
        {
            if (someItem.containedItems!=null)
            {
            if (someItem.containedItems.size()>0)
            {
              //we choose the first item from comment
                boolean line=someItem.onNewLine;
              someItem=someItem.containedItems.get(0);
              someItem.onNewLine=line;//we inherit from the parent
            }
            }
        }
        javax.swing.JLabel label=null;
        javax.swing.JPanel nestedBlock=null;
        Dimension d;
        if (someItem.typeSymbol==Source.TN_CONTENT_FROM_COMMENT)
        {
            label = this.createCommentLabel
                (this.viewText(someItem));
            d=label.getPreferredSize();
        }
        else if (someItem.codeSymbol==Source.C_OPEN_BRACE)
        {
           nestedBlock=this.processBlock(someItem);
           nestedBlock.setBorder(javax.swing.BorderFactory.
            createLineBorder(new java.awt.Color(255,255,0)));
           d=nestedBlock.getPreferredSize();
        }
        else
        {
        label=this.createLabel
                (this.viewText(someItem));
        d=label.getPreferredSize();
        //we make the link between label and parsing item
         if ((someItem.functionSymbol==Source.F_LABEL_P)|
          (someItem.functionSymbol==Source.F_LABEL_A))
            {
               this.labelAndItem.put(label, someItem);
            }
        //END link
        }

        widthHere=d.width;
        heightHere=d.height;

        if ((xHere + widthHere + xBlockSpace > maximumWidthHere)
                |(someItem.onNewLine))
          {
              xHere=xBlockMargin;
              yHere=yHere+maximumHeightHere+yBlockSpace;
              maximumHeightHere=heightHere;
          }

        if ((xHere+widthHere+xBlockSpace>blockWidthHere)&
                 (xHere+widthHere+xBlockSpace<maximumWidthHere))
          {
              blockWidthHere=xHere+widthHere+xBlockSpace;
          }
        if (yHere+heightHere+yBlockSpace>blockHeightHere)
        {
              int distance=yHere+heightHere+yBlockSpace-blockHeightHere;
            blockHeightHere=blockHeightHere+distance;
        }
      
      blockPanel.setPreferredSize(new Dimension(blockWidthHere,blockHeightHere));
      if (someItem.codeSymbol==Source.C_OPEN_BRACE)
      {
          blockPanel.add(nestedBlock);
          nestedBlock.setBounds(xHere, yHere, widthHere,heightHere);
      }
      else
      {
      blockPanel.add(label);
      label.setBounds(xHere, yHere, widthHere,heightHere);
      }

      if(heightHere>maximumHeightHere) maximumHeightHere=heightHere;
      xHere=xHere+widthHere+xBlockSpace;

    }
return blockPanel;

}
public javax.swing.JPanel createBlock()
{
    javax.swing.JPanel panel = new javax.swing.JPanel();
    panel.setBackground(Color.white);
    panel.setBorder(javax.swing.BorderFactory.
            createLineBorder(new java.awt.Color(4, 255, 129)));
    panel.setForeground(new java.awt.Color(51, 255, 0));
    panel.setCursor(new java.awt.Cursor(
        java.awt.Cursor.DEFAULT_CURSOR));
    panel.setLayout(null);
    panel.setPreferredSize(new Dimension(470,50));

    return panel;
}
public javax.swing.JPanel createComment()
{
    javax.swing.JPanel panel = new javax.swing.JPanel();

    panel.setBackground(Color.white);
    panel.setBorder(javax.swing.BorderFactory.
            createLineBorder(new java.awt.Color(0, 51, 255)));
    panel.setForeground(new java.awt.Color(51, 255, 0));
    panel.setCursor(new java.awt.Cursor(
        java.awt.Cursor.DEFAULT_CURSOR));
    panel.setLayout(null);
    panel.setPreferredSize(new Dimension(470,50));

    return panel;
}

public javax.swing.JTree createDemonstrationTree(DemonstrationItem demonstration1)
{
    DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
    renderer.setBackground(new java.awt.Color(4, 255, 129));
    renderer.setBackgroundNonSelectionColor(new java.awt.Color(4, 255, 129));
    renderer.setBackgroundSelectionColor(new java.awt.Color(4, 255, 129));
    renderer.setBorderSelectionColor(Color.white);
    renderer.setBorderSelectionColor(Color.yellow);

   javax.swing.JTree tree=new javax.swing.JTree();
   DefaultMutableTreeNode node;
   node=DisplayDemonstrationTree.generateTree(demonstration1,fullDemonstration0);
   DefaultTreeModel demonstrationTreeModel=new DefaultTreeModel(node);
   tree.setModel(demonstrationTreeModel);
    demonstrationSelectionModel=new DefaultTreeSelectionModel();
    demonstrationSelectionModel.setSelectionMode(
                DefaultTreeSelectionModel.SINGLE_TREE_SELECTION);
    demonstrationSelectionModel.addTreeSelectionListener(
                                new DemonstrationSelectionListener());
    tree.setSelectionModel(demonstrationSelectionModel);
    
   tree.setCellRenderer(renderer);
   tree.setBackground(new java.awt.Color(4, 255, 129));
   tree.setRowHeight(50);

   int dimension2=0,dimension=tree.getRowCount();
   do{

    for (int i=0;i<dimension;i++)
    {
        if(!(tree.isExpanded(i)))
        {
               tree.expandRow(i);
        }


   }
    dimension2=tree.getRowCount();
    if (dimension2==dimension) break;
    dimension=dimension2;
   } while (true);

    return tree;
}

public javax.swing.JButton createButton(String s)
{
    javax.swing.JButton button=new javax.swing.JButton();

    button.setFont(new java.awt.Font("Tahoma", 1, 11));
    button.setForeground(new java.awt.Color(0, 51, 255));
    button.setText(s);
    button.setName(s);

    return button;

}
public void pressedDemonstrationTypeButton(java.awt.event.ActionEvent evt)
{
javax.swing.JButton button=(javax.swing.JButton) evt.getSource();
javax.swing.JPanel basePanel=(javax.swing.JPanel) button.getParent();
String essential="View essential details",full="View full details",switch0="";
 if (fullDemonstration0)
 {
  fullDemonstration0=false;
 }
 else
    {
     fullDemonstration0 = true;
    }
 if (fullDemonstration0)
 {
     switch0=essential;
 }
 else
  {
     switch0=full;
  }
 button.setText(switch0);

 //redisplay demonstration
 basePanel.remove(demonstrationTree);
 //restoration
 x=xBeforeDemonstration;
 y=yBeforeDemonstration;
 referenceWidth=baseWidthBeforeDemonstration;
 baseHeight=baseHeightBeforeDemonstration;
 maximumBaseHeight=maximumBaseHeightBeforeDemonstration;
 //END restoration
 if (demonstrationSource!=null)
{
   demonstrationTree=createDemonstrationTree(demonstrationSource);
   Dimension d2=demonstrationTree.getPreferredSize();
    width1=d2.width;
    height1=d2.height;

      x=xMargin;
      y=y+maximumBaseHeight+ySpace;
      maximumBaseHeight=height1;

  if (y+height1+ySpace>baseHeight)
{
      int distance=y+height1+ySpace-baseHeight;
    baseHeight=baseHeight+distance;
}

 int width=0;
 width=referenceWidth;
 if (referenceWidth<width1) width=width1;

  basePanel.setPreferredSize(
       new Dimension(width+10*xRightSpace,baseHeight));
  basePanel.add(demonstrationTree);
  demonstrationTree.setBounds(x, y, width1,height1);

  if(height1>maximumBaseHeight) maximumBaseHeight=height1;
   x=x+width1+xSpace;
  basePanel.revalidate();
  basePanel.repaint();
}
//END redisplay demonstration

}


public void pressedEditDemonstrationButton(java.awt.event.ActionEvent evt)
{

if (selectedItem!=null)
{
 if (this.frame.currentDemonstrationEditor==null)
 {
DemonstrationEditor demonstrationEditor=
                                new DemonstrationEditor(S, frame,selectedItem);
this.frame.currentDemonstrationEditor=demonstrationEditor;//we memorize the demonstration editor in the frame
String display="Edit proof of: ";
display=display+selectedItem.textSymbol;
frame.filesTab.add(display,demonstrationEditor);
 }
 }



}
public void pressedAddRulesButton(java.awt.event.ActionEvent evt)
{

if (selectedItem!=null)
{
 ParsingItem theoremLabelItem=selectedItem;
 ParsingItem equalItem=null;
//with this cursor we go through compressed demonstration
int demonstrationCursor=0;
//the list of theorems and axioms used in demonstration
List<String> nameList=new java.util.ArrayList<String>();
//the index from which begins the compressed demonstration
int compressedDemonstrationIndex=0;


if (theoremLabelItem!=null)
{
if (theoremLabelItem.containedItems!=null)
{
if (theoremLabelItem.containedItems.size()==2)
{

if ((theoremLabelItem.containedItems.get(0).codeSymbol==Source.C_P_SYMBOL)
&(theoremLabelItem.containedItems.get(1).codeSymbol==Source.C_EQUAL_SYMBOL))
{
equalItem=theoremLabelItem.containedItems.get(1);
}
}
}
}
else this.errorNewLine("Non-existing theorem label!");

//we build the name list
//the maximum index of items contained by equal label
int maximumIndexContainedByEqualItem=0;
if (equalItem!=null)
{
if (equalItem.containedItems!=null)
{
//the maximum index of items contained by equal label
maximumIndexContainedByEqualItem=equalItem.containedItems.size()-1;
//we verify if it is a compressed demonstration (if exists open parenthesis)
if (equalItem.containedItems.get(0).textSymbol.equals("("))
{ demonstrationCursor=0; demonstrationCursor++;
do {
if  ( (!equalItem.containedItems.get(demonstrationCursor).textSymbol.equals(")"))&
(demonstrationCursor!=maximumIndexContainedByEqualItem)
)
{
nameList.add(equalItem.containedItems.get(demonstrationCursor).textSymbol);
demonstrationCursor++;
}
else break;
} while (true);

if (equalItem.containedItems.get(demonstrationCursor).textSymbol.equals(")"))
{
compressedDemonstrationIndex=demonstrationCursor+1;
}
else if (demonstrationCursor==maximumIndexContainedByEqualItem)
{ this.errorNewLine("Expect )!");
}
}
else  {  this.errorNewLine("Here is not a compressed proof!");}
}
else {}
}
else this.errorNewLine("Not found equal sign in the proof!");

if (!nameList.isEmpty())
{
if(this.S!=null)
{
 Application0 a=(Application0)Application.getInstance();
if(a!=null)
{
if(a.frame0!=null)
{    
   
   int d=nameList.size();
   
   for(int i=0;i<d;i++)
   {
     String si=nameList.get(i);
     //axioms
     if(this.S.axioms.containsKey(si))
     {
       if(this.S.availableAxioms.contains(si))
     {
       Axiom ai=(Axiom)this.S.axioms.get(si);
       if(ai.items!=null)
       {
         if(!(ai.items.isEmpty()))
           {
            if(ai.items.get(0).constantOrVariableText.equals("|-"))
            {
              
              AppliedItem appliedItem=new AppliedItem();
              appliedItem.name=si;
              if(ai.type==1) {appliedItem.type=
                                           DemonstrationConstants.SIMPLE_AXIOM;}
                  else if(ai.type==2) {appliedItem.type=
                              DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM;}
              //we add the selected item to the list of rules
              if(a.frame0.listOfAppliedItems!=null)
              {
              a.frame0.listOfAppliedItems.add(appliedItem);
              }
            }
           }
       }
     }
     }
     //theorems
     if(this.S.theorems.containsKey(si))
     {
       if(this.S.availableTheorems.contains(si))
     {
       Theorem ti=(Theorem)this.S.theorems.get(si);
       if(ti.items!=null)
       {
         if(!(ti.items.isEmpty()))
           {
            if(ti.items.get(0).constantOrVariableText.equals("|-"))
            {
               
              AppliedItem appliedItem=new AppliedItem();
              appliedItem.name=si;
              if(ti.type==1) {appliedItem.type=
                                        DemonstrationConstants.SIMPLE_THEOREM;}
                  else if(ti.type==2) {appliedItem.type=
                         DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM;}
              //we add the selected item to the list of rules
              if(a.frame0.listOfAppliedItems!=null)
              {
              a.frame0.listOfAppliedItems.add(appliedItem);
              }
            }
           }
       }
     }
     }
     
   }
   
}
}
//end this.S
}
}

}



}

 public javax.swing.JLabel createLabel(String s)
{
    javax.swing.JLabel label=new javax.swing.JLabel();

    label.setBackground(new java.awt.Color(4, 255, 129));
    label.setForeground(new java.awt.Color(0, 0, 0));
    label.setFont(new java.awt.Font("Arial", 0, 14));
    label.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    label.setFocusCycleRoot(true);
    label.setOpaque(true);
    label.setText(s);
    label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pressContentItem(evt);
            }
        });
    return label;
}

 public javax.swing.JLabel createCommentLabel(String s)
{
    javax.swing.JLabel label=new javax.swing.JLabel();
    label.setBackground(new java.awt.Color(255, 255, 255));
    label.setForeground(new java.awt.Color(0, 51, 255));
    label.setFont(new java.awt.Font("Arial", 0, 14));
    label.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    label.setFocusCycleRoot(true);
    label.setOpaque(true);
    label.setText(s);
    return label;
}
private void pressContentItem(java.awt.event.MouseEvent evt)
{
        ParsingItem
                 xyzItem=this.labelAndItem.get(
                 (javax.swing.JLabel) evt.getComponent()
                 );
         if (xyzItem!=null)
         {
             selectedItem=xyzItem;
             updatePropertiesPanel(
                     loadProperties(xyzItem)
                     );
             String name=selectedItem.textSymbol;
             int type=0;
             Axiom a0=null;
             Theorem t0=null;
             Hypothesis h0=null;
             if (selectedItem.functionSymbol==Source.F_LABEL_P)
             {
               if (selectedItem.generatedItem!=null)
               {
                t0 = (Theorem) selectedItem.generatedItem;
                if (t0.type==1)
                 {
                    type=DemonstrationConstants.SIMPLE_THEOREM;
                }
                else if (t0.type==2)
                 {
                    type=DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM;
                 }
               }
             }
             else if (selectedItem.functionSymbol==Source.F_LABEL_A)
             {
                 if (selectedItem.generatedItem!=null)
               {
                a0 = (Axiom) selectedItem.generatedItem;
                if (a0.type==1)
                {
                    type=DemonstrationConstants.SIMPLE_AXIOM;
                }
                else if (a0.type==2)
                 {
                    type=DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM;
                 }
               }
             }
             else if (selectedItem.functionSymbol==Source.F_LABEL_E)
             {
              type=DemonstrationConstants.PROPER_HYPOTHESIS;
             }
             String header="";
             if (
                 (type==DemonstrationConstants.SIMPLE_THEOREM)|
                 (type==DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM)
                )
             {
                 header = "Theorem ";
             }
             else
                if (
                 (type==DemonstrationConstants.SIMPLE_AXIOM)|
                 (type==DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM)
                )
             {
                 header = "Axiom ";
             }
             else
                if (
                 (type==DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_AXIOM)
                 |(type==DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_THEOREM)
                 |(type==DemonstrationConstants.PROPER_HYPOTHESIS)
                )
             {
                 header = "Hypothesis ";
             }
             
             if (this.frame!=null)
             {
                     
             this.frame.nameOfSelectedItem=name;
             this.frame.typeOfSelectedItem=type;

             String
                 addToListText
                 ="<HTML> Add to List of Rules: <br>"+header+name+"</HTML>";

             this.frame.addToListButton.setEnabled(true);
             this.frame.addToListButton.setText(addToListText);
             
             }


          }

    }
private  String viewText(ParsingItem  parsingItem)
{
String eBeginning="<P> <B>   Hypothesis ";
String aBeginning="<P> <B>   Axiom ";
String pBeginning="<P> <B>   Theorem ";
String fBeginning="<P> <B>   Type declaration ";

String cBeginning="<P> <B>    Constants:<br> ";
String vBeginning="<P> <B>    Variables:<br> ";
String dBeginning="<P> <B>    Distinct:<br> ";

String chapterBeginning="<P> <FONT COLOR='#330099'> <FONT SIZE=5><B> ";
String chapterInTypeSettingsBeginning="<P><FONT COLOR='#330099'> <FONT SIZE=5><B> ";
String subchapterBeginning="<P><FONT COLOR='#330099'> <FONT SIZE=4> <I> ";

String contentBeginning="<P>";


String end="</B> </FONT> </P>";
String endChapter="</FONT></B> </FONT> </P>";
String endChapterInTypeSettings="</FONT></I> </FONT> </P>";

String endSubchapter="</FONT></I> </FONT> </P>";
String endContent=" </P>";

int i=0;
String displayText="";

//we are splitting into categories
 if (parsingItem.typeSymbol==Source.TN_CONTENT_FROM_COMMENT)
{
//we have content from the comment
displayText=contentBeginning;
if (parsingItem.containedItems!=null)
{
if (parsingItem.containedItems.size()>0)
{
 int dimension=parsingItem.containedItems.size();
 i=0;
do
{
 //we display items unaltered and replaceable
 ParsingItem item1=parsingItem.containedItems.get(i);
 if (item1.typeSymbol==Source.TN_UNTAINED)
 {
  displayText=displayText+" "+item1.textSymbol;
 }
 else
 if (item1.typeSymbol==Source.TN_TO_BE_REPLACED)
 {
   String s1=item1.textSymbol,s2="";
   s2=this.S.htlmldefString1AsString2.get(s1);
    if (s2==null) s2=s1;
   s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
  displayText=displayText+" "+s2;
 }
i++;
} while (i<dimension);

displayText=displayText+endContent;
}
}
//END content
}
else if (parsingItem.typeSymbol==Source.TN_SUPERCHAPTER_FROM_COMMENT)
{
//we are using the same setting as the chapter
displayText=chapterBeginning+parsingItem.textSymbol+endChapter;

}
else if (parsingItem.typeSymbol==Source.TN_CHAPTER_FROM_COMMENT)
{

displayText=chapterBeginning+parsingItem.textSymbol+endChapter;

}
else if (parsingItem.typeSymbol==Source.TN_SUBCHAPTER_FROM_COMMENT)
{

displayText=subchapterBeginning+parsingItem.textSymbol+endSubchapter;

}
else if (parsingItem.typeSymbol==Source.TN_CHAPTER_FROM_TYPESETTINGS)
{

displayText=chapterInTypeSettingsBeginning+parsingItem.textSymbol+endChapterInTypeSettings;

}
else if (parsingItem.typeSymbol==Source.TN_COMMENT_FROM_TYPESETTINGS)
{

displayText=parsingItem.textSymbol;

}
else if (parsingItem.typeSymbol==Source.TN_DISPLAY)
{

displayText=
     "<FONT SIZE='5'COLOR='ff6600' ><I><B> VISUAL SETTINGS:</B> </I></FONT>";

}
else if (parsingItem.typeSymbol==Source.TN_DISPLAY_DEFINITION)
{
    boolean haveHtmlCss=false;
 if(parsingItem.textSymbol.equals("htmlcss")){ haveHtmlCss=true;}
displayText=
    "<FONT COLOR='ff6600' SIZE=3><B> "+parsingItem.textSymbol+": </B></FONT>";

if (parsingItem.containedItems!=null)
{
int dimension1=parsingItem.containedItems.size();

if (dimension1==2)
{
 displayText=displayText+parsingItem.containedItems.get(0).textSymbol
         +"<FONT COLOR='#330099' SIZE=3><B> as </FONT></B><br>";
 displayText=displayText+parsingItem.containedItems.get(1).textSymbol;
}
 else if (dimension1==1)
{
 if(!haveHtmlCss)
 {
 displayText=displayText+parsingItem.containedItems.get(0).textSymbol;
 }
 else {displayText=displayText+" This cannot be shown!";}

}

}

}
else if(parsingItem.functionSymbol == Source.F_LABEL_A)
{
//we have axiom
displayText=aBeginning +parsingItem.textSymbol+":<br>  ";

if (parsingItem.containedItems!=null)
{
if (parsingItem.containedItems.size()>0)
{

ParsingItem axiomItem=parsingItem.containedItems.get(0);
if (axiomItem.containedItems.size()>0)
{ i=0;
do
{
//we display component items of the axiom
String space=" ";
if (axiomItem.containedItems.get(i).onNewLine) space="<br>";
                                               else space=" ";
if (i==0) space=" ";
String s1=axiomItem.containedItems.get(i).textSymbol,s2="";
   s2=this.S.htlmldefString1AsString2.get(s1);
   if (s2==null) s2=s1;
displayText=displayText+space+s2;
i++;
} while (i<axiomItem.containedItems.size());
displayText=displayText+end;
}
}
}
//END axiom
}

else if (parsingItem.functionSymbol==Source.F_LABEL_P)
{
//we have theorem
displayText=pBeginning+parsingItem.textSymbol+":<br>  ";
if (parsingItem.containedItems!=null)
{
if (parsingItem.containedItems.size()>0)
{

ParsingItem theoremItem=parsingItem.containedItems.get(0);
if (theoremItem.containedItems.size()>0)
{ i=0;
do
{
//we display component items of the theorem
String space=" ";
if (theoremItem.containedItems.get(i).onNewLine) space="<br>";
                                               else space=" ";
if (i==0) space=" ";
String s1=theoremItem.containedItems.get(i).textSymbol,s2="";
   s2=this.S.htlmldefString1AsString2.get(s1);
   if (s2==null) s2=s1;
   s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
displayText=displayText+space+s2;
i++;
} while (i<theoremItem.containedItems.size());
displayText=displayText+end;
}
}
}
//END theorem
}
else if (parsingItem.functionSymbol==Source.F_LABEL_E)
{
//we have hypothesis
displayText=eBeginning+parsingItem.textSymbol+":<br>  ";
if (parsingItem.containedItems!=null)
{
if (parsingItem.containedItems.size()>0)
{

ParsingItem hypothesisItem=parsingItem.containedItems.get(0);
if (hypothesisItem.containedItems.size()>0)
{ i=0;
do
{
//we display component items of the hypothesis
String space=" ";
if (hypothesisItem.containedItems.get(i).onNewLine) space="<br>";
                                               else space=" ";
if (i==0) space=" ";
String s1=hypothesisItem.containedItems.get(i).textSymbol,s2="";
   s2=this.S.htlmldefString1AsString2.get(s1);
   if (s2==null) s2=s1;
   s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
displayText=displayText+space+s2;
i++;
} while (i<hypothesisItem.containedItems.size());
displayText=displayText+end;
}
}
}
//END hypothesis
}
else  if (parsingItem.functionSymbol==Source.F_LABEL_F)
{
//we have type-variable f
displayText=fBeginning+parsingItem.textSymbol+":<br>  ";
if (parsingItem.containedItems!=null)
{
if (parsingItem.containedItems.size()>0)
{

ParsingItem fItem=parsingItem.containedItems.get(0);
if (fItem.containedItems.size()>0)
{ i=0;
do
{
//we display the component items of type-variable f
String space=" ";
if (fItem.containedItems.get(i).onNewLine) space="<br>";
                                               else space=" ";
if (i==0) space=" ";
String s1=fItem.containedItems.get(i).textSymbol,s2="";
   s2=this.S.htlmldefString1AsString2.get(s1);
   if (s2==null) s2=s1;
   s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
displayText=displayText+space+s2;
i++;
} while (i<fItem.containedItems.size());
displayText=displayText+end;
}
}
}
//END type-variable f

}
else  if ((parsingItem.codeSymbol==Source.C_C_SYMBOL)|
(parsingItem.codeSymbol==Source.C_V_SYMBOL)|
(parsingItem.codeSymbol==Source.C_D_SYMBOL)|
 (parsingItem.codeSymbol==Source.C_OPEN_SQUARE_BRACKET))
{
//we have c,v,d
if (parsingItem.codeSymbol==Source.C_C_SYMBOL) displayText=cBeginning;
else if (parsingItem.codeSymbol==Source.C_V_SYMBOL) displayText=vBeginning;
else if (parsingItem.codeSymbol==Source.C_D_SYMBOL) displayText=dBeginning;
else if (parsingItem.codeSymbol==Source.C_OPEN_SQUARE_BRACKET)
                                              displayText="<P> <B> Use: <br> ";

if (parsingItem.containedItems.size()>0)
{ i=0;
do
{
//we display component items of c,v,d
String space=" ";
if (parsingItem.containedItems.get(i).onNewLine) space="<br>";
                                               else space=" ";
if (i==0) space=" ";
String s1=parsingItem.containedItems.get(i).textSymbol,s2="";
   s2=this.S.htlmldefString1AsString2.get(s1);
   if (s2==null) s2=s1;
   s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
displayText=displayText+space+s2;
i++;
} while (i<parsingItem.containedItems.size());
displayText=displayText+end;

}
//END c,v,d
}
 else displayText="Unknown Type:"
                                +parsingItem.typeSymbol+" Code:"+parsingItem.codeSymbol;
Application0 a=(Application0)Application.getInstance();
//we encapsulate in html and replace \n with <br>
displayText="<HTML>"
          +"<base href='file:"+a.path+"/symbols/  '/>"
          +displayText
          +" </HTML>";
String s2="";

s2=displayText.replaceAll("\n","<br>");

displayText=s2;

return displayText;
};

//here begins the view of the text for generated items: e,a,p

private  String viewTheoremText(Theorem  theoremItem)
{
String pBeginning="<P> <B>   Theorem ";
String end="</B> </FONT> </P>";

int i=0;
String displayText="";

 if (theoremItem!=null)
{
//we have theorem
displayText=pBeginning+theoremItem.name+":<br>  ";
if (theoremItem.items!=null)
{
if (theoremItem.items.size()>0)
{
int dim=theoremItem.items.size();
do
{
//we display component items of the theorem
String s1=theoremItem.items.get(i).constantOrVariableText,s2="";
   s2=this.S.htlmldefString1AsString2.get(s1);
   if (s2==null) s2=s1;
displayText=displayText+" "+s2;
i++;
} while (i<dim);
displayText=displayText+end;

}
}
//END theorem
}

 else displayText="The theorem does not exist" ;
Application0 a=(Application0)Application.getInstance();
//we encapsulate it in html and replace \n with <br>
displayText="<HTML>"
          +"<base href='file:"+a.path+"/symbols/  '/>"
          +displayText
          +" </HTML>";

return displayText;
};
private  String viewAxiomText(Axiom  axiomItem)
{
String aBeginning="<P> <B>   Axiom ";
String end="</B> </FONT> </P>";

int i=0;
String displayText="";

 if (axiomItem!=null)
{
//we have axiom
displayText=aBeginning+axiomItem.name+":<br>  ";
if (axiomItem.items!=null)
{
if (axiomItem.items.size()>0)
{
int dimension=axiomItem.items.size();
do
{
//we display the component items of the axiom

String s1=axiomItem.items.get(i).constantOrVariableText,s2="";
   s2=this.S.htlmldefString1AsString2.get(s1);
   if (s2==null) s2=s1;
displayText=displayText+" "+s2;
i++;
} while (i<dimension);
displayText=displayText+end;

}
}
//END axiom
}

 else displayText="The axiom does not exist" ;
Application0 a=(Application0)Application.getInstance();
//we encapsulate it in html and replace \n with <br>
displayText="<HTML>"
          +"<base href='file:"+a.path+"/symbols/  '/>"
          +displayText
          +" </HTML>";

return displayText;
};

private  String viewHypothesisText(Hypothesis  hypothesisItem)
{
String eBeginning="<P> <B>   Hypothesis ";
String end="</B> </FONT> </P>";

int i=0;
String displayText="";

 if (hypothesisItem!=null)
{
//we have hypothesis
displayText=eBeginning+hypothesisItem.name+":<br>  ";
if (hypothesisItem.items!=null)
{
if (hypothesisItem.items.size()>0)
{
int dimension=hypothesisItem.items.size();
do
{
//we display component items of  hypothesis

String s1=hypothesisItem.items.get(i).constantOrVariableText,s2="";
   s2=this.S.htlmldefString1AsString2.get(s1);
   if (s2==null) s2=s1;
displayText=displayText+" "+s2;
i++;
} while (i<dimension);
displayText=displayText+end;

}
}
//END hypothesis
}

 else displayText="The hypothesis does not exist" ;
Application0 a=(Application0)Application.getInstance();
//we encapsulate it in html and replace \n with <br>
displayText="<HTML>"
          +"<base href='file:"+a.path+"/symbols/  '/>"
          +displayText
          +" </HTML>";

return displayText;
};


}