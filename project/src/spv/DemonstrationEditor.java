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
import spv.gen.*;
import java.awt.Dimension;
import spv.Source;
import spv.ParsingItem;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import javax.swing.JTree;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import spv.MainWindow;
import javax.swing.ImageIcon;
import java.util.Iterator;
import java.util.Set;
import java.math.RoundingMode;
import org.jdesktop.application.Application;
import java.io.*;
import javax.swing.JFileChooser;
import java.util.BitSet;





public class DemonstrationEditor extends javax.swing.JPanel
{

private javax.swing.JScrollPane demonstrationEditorPanel;
Source S=null;
MainWindow frame=null;
ParsingItem root;
ParsingItem selectedItem;//selected item with the mouse

int xMargin=60,yMargin=10;
int xSpace=10,ySpace=10;
int xBlockMargin=2,yBlockMargin=2,xSpaceRight=10;
int xBlockSpace=2,yBlockSpace=2;
int xCommentMargin=10,yCommentMargin=10;
int xCommentSpace=10,yCommentSpace=10;
int maximumWidthComment=700;//500
int referenceWidth=800;
int chapterReference=440;//450

int x=xMargin,y=yMargin;//absolute positioning in the base panel

//provisionally
int width1=60,height1=35;
int commentWidth,commentHeight;
int baseHeight=10;
int maximumHeightBase=0;
//END provisionally

int windowDimension=30;
int position=0;
public boolean fullDemonstration1=false;//type of demonstration
public int demonstrationStrategyType=1;//0- backward chaining 1-forward chaining

//data about demonstration tree
javax.swing.JButton demonstrationSwitch=null;

public javax.swing.JButton demonstrationExtraction=null;
public javax.swing.JLabel strategyTypeLabel=null;
public javax.swing.JButton strategySwitch=null;
public javax.swing.JButton exit=null;
public javax.swing.JButton saveDemonstration=null;
public javax.swing.JButton openDemonstration=null;
public javax.swing.JButton automatedSearching=null;
public javax.swing.JButton stopSearching=null;
javax.swing.JTree demonstrationTree=null;
DemonstrationItem demonstrationSource=null;
//variable used for new statements chaining
List<DemonstrationItem> listOfStatements=new ArrayList<DemonstrationItem>();
//demonstration item selected by the mouse
public DemonstrationItem selectedDemonstrationItem0=null;
public DemonstrationItem targetItem=null;
int xBeforeDemonstration=0,yBeforeDemonstration=0;
int baseWidthBeforeDemonstration=0,baseHeightBeforeDemonstration=0;
int baseMaximumHeightBeforeDemonstration=0;
//END data about demonstration tree


//link between name of new variable and her class
 public Map<String,String> newVariableAndClass=
                       new java.util.HashMap<String,String>();
//link between new variables($v) and their content from this demonstration
 public Map<String,List<ConstantAndVariable>> newVariableAndContent=
                       new java.util.HashMap<String,java.util.List<ConstantAndVariable>>();
 
  //the link between new statement($a)
 //and their content from phase 0 from this demonstration
 public Map<String,List<ConstantAndVariable>> newStatementAndContentPhaseZero=
                       new java.util.HashMap<String,java.util.List<ConstantAndVariable>>();
  
 /*the link between new statement ($a)
  and the generated variable content*/
 public Map<String,List<String>> newStatementAndGeneratedVariableContent=
                       new java.util.HashMap<String,List<String>>();
 /*the link between new statement ($a)
  and generated new variable*/
 public Map<String,List<String>> newStatementAndGeneratedVariable=
                       new java.util.HashMap<String,List<String>>();
  /*the link between new statement($a)
  and hub statement*/
 public Map<DemonstrationItem,DemonstrationItem> newStatementAndHubStatement=
                       new java.util.HashMap<DemonstrationItem,DemonstrationItem>();
 //selected items from the stack
 public List<DemonstrationItem> stackSelectedItems=new ArrayList<DemonstrationItem>();
 int stackItemsCursor=0;//the cursor of selected items from the stack


 //the current index of new statements of the whole demonstration
 public int numberOfOrderNewStatements=0;
 //the current index of variables of the whole demonstration
 public int numberOfOderVariables=0;
 //at the depth(level) we apply i rule
 List<Integer> depth=new ArrayList<Integer>();
 
 public Theorem baseTheorem=null;
 

  //name and content of selected variable for editing
  public String nameOfSelectedVariable=null;
  public List<ConstantAndVariable> contentOfSelectedVariable=null;
  public String classOfSelectedVariable="";
  
  //the vector used for demonstration salvage
  //it contains the name of axioms or theorems used,including syntactic ones
  public List<String> vector_a_p=new ArrayList<String>();
  public String demonstrationString="";
  public int stringCounter=0;
  public String demonstrationStringI="";
  public int stringCounterI=0;
  public int stringDimension=79;
  //number of hub statements from demonstration
  public int numberOfHubs=0;
  public int numberOfHubs2=0;
  public List<HubAndRepetitionItem> listOfRepetitions=new ArrayList<HubAndRepetitionItem>();
  public List<HubAndRepetitionItem> listOfHubs=new ArrayList<HubAndRepetitionItem>();
  public String theoremName="";
  public String fileName="";
  //change strategy button
    String forwardChainingStrategySwitch="<HTML>"+
             "Change strategy to: <BR> Forward Chaining"
             +"</HTML>",
          backwardChainingStrategySwitch=
              "<HTML>"+"Change strategy to:<BR> Backward Chaining"
              +"</HTML>",
          switch0="";
   //switch button
    String essential="View essential details",
          full="View full details",switch1="";
    //strategy label
    String backwardChainingStrategyLabel="Proof strategy: Backward Chaining";
    String forwardChainingStrategyLabel="Proof strategy: Forward Chaining";
 //used to counting the hubs by a certain index
 public int numberOfFoundRepetitionsWithIndex=0;
 //we found the hub with the given index
 public boolean weFoundHubWithIndex=false;
 public int howManyRepetitionsWeHave=0;
 public boolean weDestroyedHubOrRepetition=false;
 public boolean weHaveFoundTheProof=false;
 public boolean stop=false;
 public List<String> loopRules=new ArrayList<String>();
 public boolean weFoundARepetitionWithIndexAndWePasteProof=false;//forward chaining
 DemonstrationItem demonstrationBeginning=null;//forward chaining
 public int lastHub=0;//last hub
 public String proofTextVersion="";//text version of the proof
 public Set<String>  displayedHypotheses=new java.util.HashSet<String>();
 public DemonstrationEditor(
                           Source source1,
                           MainWindow window,
                           ParsingItem xItem
                           )
{
    super();
     // we create scroll pane
     demonstrationEditorPanel=new javax.swing.JScrollPane();
     javax.swing.GroupLayout foundationLayer = new javax.swing.GroupLayout(this);
     this.setLayout(foundationLayer);
     foundationLayer.setHorizontalGroup(
     foundationLayer.createParallelGroup(
             javax.swing.GroupLayout.Alignment.LEADING)
     .addComponent(demonstrationEditorPanel,
             javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
        );
      foundationLayer.setVerticalGroup(
      foundationLayer.createParallelGroup(
              javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(demonstrationEditorPanel,
              javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
        );
     //END create scroll pane

    //we make the link with source and with base frame
    this.S=source1;
    this.fileName=this.S.fileName;//we memorize the name of file
    this.frame=window;
    if(this.S!=null)
    {
     //we update data root
      this.root=this.S.dataRoot;
    }
    //END link

      //create content from x Item
        javax.swing.JPanel basePanel=createBase();

        if (xItem!=null)
        {
        if (xItem.generatedItem!=null)
         {
            this.theoremName=xItem.textSymbol;//we memorize the name of theorem
             if (xItem.generatedItem.getClass()==spv.gen.Theorem.class)
             {
                 baseTheorem=(Theorem)xItem.generatedItem;//we memorize the theorem
                 demonstrationSource=this.S.createDemonstration(xItem);
                 this.createVisualItemTheorem
                                  (
                                  baseTheorem,demonstrationSource,
                                  basePanel
                                  );
             }

         }
        }

        demonstrationEditorPanel.setVerticalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        demonstrationEditorPanel.setHorizontalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        demonstrationEditorPanel.setViewportView(basePanel);
      //END content
    
}
       
    public class SelectionListener implements TreeSelectionListener {

        public void valueChanged(TreeSelectionEvent e)
        {
         pressedCell(e);
        }

    }
     private void pressedCell(TreeSelectionEvent e)
     {
         DefaultMutableTreeNode node;
            node = (DefaultMutableTreeNode)(e.getPath().getLastPathComponent());
         DemonstrationItem item=(DemonstrationItem) node.getUserObject();
         if (item!=null)
         {
             this.selectedDemonstrationItem0=item;//we memorize the selection
             String header="";
         if (
              (this.selectedDemonstrationItem0.type==DemonstrationConstants.NEW_STATEMENT)
              |(this.selectedDemonstrationItem0.type==DemonstrationConstants.TARGET)
            )
         {

             //we activate creation button if we have a memorized index or name
             if (
                     (this.frame.nameOfMemorizedItem!=null)
                )
             {
                          
             if (
                     this.selectedDemonstrationItem0.downLink!=null
                )
             {
             if (this.selectedDemonstrationItem0.downLink.size()>0)
             {
             
             } else this.frame.branchCreationButton.setEnabled(true);
             } else this.frame.branchCreationButton.setEnabled(true);

             } //END button creation

             //we enable delete button if the item has subordinates
             if (
                     this.selectedDemonstrationItem0.downLink!=null
                )
             {
             if (this.selectedDemonstrationItem0.downLink.size()>0)
             {
             this.frame.branchDeletingButton.setEnabled(true);
             }
             }


              String s="";
              if(this.selectedDemonstrationItem0.items!=null)
              {
              if(!this.selectedDemonstrationItem0.items.isEmpty())
              {
              int max= this.selectedDemonstrationItem0.items.size();
              
               for (int i=0;i<max;i++)
               {
                String  s1=this.selectedDemonstrationItem0.items.
                                                  get(i).constantOrVariableText;
                 s=s+" "+s1;
               }
              }
              }
              this.frame.textField.setText(s);
              this.frame.startStatementFitLabel.setText("Start with:"+
                      this.selectedDemonstrationItem0.name);

              if(this.selectedDemonstrationItem0.type
                      ==DemonstrationConstants.NEW_STATEMENT)
              {
                  this.frame.markTargetButton.setText("Mark the target:"+
                          this.selectedDemonstrationItem0.name);
              }


         }
         else if(
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
           if(!header.equals(""))
            {
             String propertiesText=item.name;
             propertiesText=
                     "<HTML> Properties of: <br>"
                     +header
                     +propertiesText
                     +"</HTML>";
            
             this.frame.propertiesButton.setText(propertiesText);
             this.frame.nameOfSelectedItem=item.name;
             this.frame.typeOfSelectedItem=item.type;
            }




            
         }
     }
     public void findsStatements(DemonstrationItem item)
     {
       if (item!=null)
       {
        if((item.type==DemonstrationConstants.NEW_STATEMENT)
            &(!item.repetition))
        {
              //we check if item has at least 2 levels under
               if(item.downLink!=null)
               {
               if(!item.downLink.isEmpty())
               {
               if(item.downLink.get(0).downLink!=null)
               {
               if(!item.downLink.get(0).downLink.isEmpty())
               {
                 this.listOfStatements.add(item);
               }
               }
               }
               }
        }
        if(item.downLink!=null)
        {
         int max=item.downLink.size();
         for(int i=0;i<max;i++)
         {
             findsStatements(item.downLink.get(i));
         }

        }
       }
     }

   public void generateTextVersionOfProof(DemonstrationItem xItem)
   {
    String stepName="",stepAppliedRule="";
    String stepBaseNames="";
    String stepStatement="";

    if(xItem!=null)
    {
     if(xItem.downLink!=null)
     {
      if(xItem.downLink.size()>0)
      {
        int dim=xItem.downLink.size();
       for(int i=0;i<dim;i++)
       {
         DemonstrationItem iItem=xItem.downLink.get(i);
         generateTextVersionOfProof(iItem);


       }

      }
     }
     if(xItem.type==DemonstrationConstants.NEW_STATEMENT)
     {
       //here we get the information to show proof step

       stepName=xItem.name;
       int dim=xItem.items.size();
       for(int i=0;i<dim;i++)
       {
        stepStatement+=xItem.items.get(i).constantOrVariableText+" ";
       }

       if(xItem.downLink!=null)
       {
        if(!xItem.downLink.isEmpty())
        {
         DemonstrationItem yItem=xItem.downLink.get(0);
         stepAppliedRule=yItem.name;

         if(yItem.downLink!=null)
         {
          if(!yItem.downLink.isEmpty())
          {
           int dim2=yItem.downLink.size();

           for(int j=0;j<dim2;j++)
           {
            DemonstrationItem zItem=yItem.downLink.get(j);
            if(zItem.downLink!=null)
            {
              if(!zItem.downLink.isEmpty())
              {
               DemonstrationItem tItem=zItem.downLink.get(0);
               if((tItem.type==DemonstrationConstants.NEW_STATEMENT)|
                 (tItem.type==DemonstrationConstants.PROPER_HYPOTHESIS)
                 )
               {
                 if(tItem.repetition)
                 {
                  String repetitionName="";
                  int dimHubs=this.listOfHubs.size();
                  for(int i1=0;i1<dimHubs;i1++)
                  {
                     
                   HubAndRepetitionItem i1Item=this.listOfHubs.get(i1);
                   if(tItem.numberOfOrderInCompressedProof
                                  ==i1Item.item.numberOfOrderInCompressedProof)
                   {
                     repetitionName=i1Item.item.name;
                     break;
                   }
                   }

                  stepBaseNames+=repetitionName+",";

                 }
                 else { stepBaseNames+=tItem.name+",";}
               }
              }
            }
           }


          }
         }


        }
       }
       //here we print the proof

      if(!xItem.repetition)
      {
       String xStep="";
       int dim3=stepBaseNames.length();
       if(dim3>0)
       {
        stepBaseNames=stepBaseNames.substring(0, dim3-1);
       }
       xStep=stepName+":"+stepBaseNames+":"+stepAppliedRule+"  "+stepStatement;
       
       this.proofTextVersion+=xStep+"\n";
      }

       
     }
   else if(xItem.type==DemonstrationConstants.PROPER_HYPOTHESIS)
   {
       if(!this.displayedHypotheses.contains(xItem.name))
       {
       stepName=xItem.name;
       Hypothesis hyp=(Hypothesis)xItem.referenceObject;
       if(hyp!=null)
       {
       int dim=hyp.items.size();
       for(int i=0;i<dim;i++)
       {
        stepStatement+=hyp.items.get(i).constantOrVariableText+" ";
       }
       }

       //here we print the proof

       String xStep="";

       xStep=stepName+":"+stepStatement;

       this.proofTextVersion+=xStep+"\n";
       
       this.displayedHypotheses.add(xItem.name);
       }

   }



    }

   }

     public void createBranchAndUpdate()
    {
         
             javax.swing.JPanel basePanel=null;
             basePanel=(javax.swing.JPanel)demonstrationTree.getParent();
          if (basePanel!=null)
          {

             if (frame!=null)
             {
                 if (frame.nameOfMemorizedItem!=null)
                 {
                  
                    String name0=frame.nameOfMemorizedItem;
                    if (this.demonstrationStrategyType==0)
                    {
                      if (this.selectedDemonstrationItem0!=null)
                       {
                      if(!(this.selectedDemonstrationItem0.repetition))
                      {
                      if (this.selectedDemonstrationItem0.downLink==null)
                       {
                         this.createNewBranchBackwardChaining
                             (
                             this.selectedDemonstrationItem0,
                             name0,
                             frame.typeOfMemorizedItem
                             );
                    
                        this.verifyRepetition(this.demonstrationSource);
                       }
                       else
                       {
                       if (this.selectedDemonstrationItem0.downLink.isEmpty())
                         {
                            this.createNewBranchBackwardChaining
                             (
                             this.selectedDemonstrationItem0,
                             name0,
                             frame.typeOfMemorizedItem
                             );

                             this.verifyRepetition(this.demonstrationSource);
                         }
                       }
                     }
                     }

                     }
                    else
                    {
                     this.createNewBranchForwardChaining
                             (
                             name0,
                             frame.typeOfMemorizedItem
                             );
                    }
                 }
             }
                
                 //redisplay demonstration
                 basePanel.remove(demonstrationTree);
                 //restoration
                 x=xBeforeDemonstration;
                 y=yBeforeDemonstration;
                 referenceWidth=baseWidthBeforeDemonstration;
                 baseHeight=baseHeightBeforeDemonstration;
                 maximumHeightBase=baseMaximumHeightBeforeDemonstration;
                 //END restoration
                 if (demonstrationSource!=null)
                {
                   demonstrationTree=
                           createDemonstrationTree(demonstrationSource);
                   Dimension d2=demonstrationTree.getPreferredSize();
                    width1=d2.width;
                    height1=d2.height;

                      x=xMargin;
                      y=y+maximumHeightBase+ySpace;
                      maximumHeightBase=height1;

                  if (y+height1+ySpace>baseHeight)
                {
                      int dist=y+height1+ySpace-baseHeight;
                    baseHeight=baseHeight+dist;
                }

                 int width=0;
                 width=referenceWidth;
                 if (referenceWidth<width1) width=width1;

                  basePanel.setPreferredSize(
                       new Dimension(width+10*xSpaceRight,baseHeight));
                  basePanel.add(demonstrationTree);
                  demonstrationTree.setBounds(x, y, width1,height1);

                  if(height1>maximumHeightBase) maximumHeightBase=height1;
                   x=x+width1+xSpace;
                 //redesign base panel
                 basePanel.revalidate();
                 basePanel.repaint();
                 basePanel.repaint();
                }
                //END redisplay demonstration
         }
         
    }

   public void createBranchForwardBySearchAndUpdate()
    {
          
             javax.swing.JPanel basePanel=null;
             basePanel=(javax.swing.JPanel)demonstrationTree.getParent();
          if (basePanel!=null)
          {

             if (frame!=null)
             {
                    if (this.demonstrationStrategyType==1)
                    {
                     this.findRuleForwardChaining(
                             this.frame.targetFitStatement,
                             Integer.parseInt
                                    (this.frame.numberOfHypFitText.getText()));

                     //System.out.println("No.of hyp: "+Integer.parseInt
                    //              (this.frame.numberOfHypFitText.getText()));
                    }
               
             }

                 //redisplay demonstration
                 basePanel.remove(demonstrationTree);
                 //restoration
                 x=xBeforeDemonstration;
                 y=yBeforeDemonstration;
                 referenceWidth=baseWidthBeforeDemonstration;
                 baseHeight=baseHeightBeforeDemonstration;
                 maximumHeightBase=baseMaximumHeightBeforeDemonstration;
                 //END restoration
                 if (demonstrationSource!=null)
                {
                   demonstrationTree=
                           createDemonstrationTree(demonstrationSource);
                   Dimension d2=demonstrationTree.getPreferredSize();
                    width1=d2.width;
                    height1=d2.height;

                      x=xMargin;
                      y=y+maximumHeightBase+ySpace;
                      maximumHeightBase=height1;

                  if (y+height1+ySpace>baseHeight)
                {
                      int dist=y+height1+ySpace-baseHeight;
                    baseHeight=baseHeight+dist;
                }

                 int width=0;
                 width=referenceWidth;
                 if (referenceWidth<width1) width=width1;

                  basePanel.setPreferredSize(
                       new Dimension(width+10*xSpaceRight,baseHeight));
                  basePanel.add(demonstrationTree);
                  demonstrationTree.setBounds(x, y, width1,height1);

                  if(height1>maximumHeightBase) maximumHeightBase=height1;
                   x=x+width1+xSpace;
                 //redesign base panel
                 basePanel.revalidate();
                 basePanel.repaint();
                 basePanel.repaint();
                }
                //END redisplay demonstration
         }
         
    }
  public void createBranchForwardDirectlyUpdate()
    {

             javax.swing.JPanel basePanel=null;
             basePanel=(javax.swing.JPanel)demonstrationTree.getParent();
          if (basePanel!=null)
          {

             if (frame!=null)
             {
                    if (this.demonstrationStrategyType==1)
                    {
                     this.InsertDirectlyForwardChaining
                                              (this.frame.targetFitStatement);
                    
                    }

             }

                 //redisplay demonstration
                 basePanel.remove(demonstrationTree);
                 //restoration
                 x=xBeforeDemonstration;
                 y=yBeforeDemonstration;
                 referenceWidth=baseWidthBeforeDemonstration;
                 baseHeight=baseHeightBeforeDemonstration;
                 maximumHeightBase=baseMaximumHeightBeforeDemonstration;
                 //END restoration
                 if (demonstrationSource!=null)
                {
                   demonstrationTree=
                           createDemonstrationTree(demonstrationSource);
                   Dimension d2=demonstrationTree.getPreferredSize();
                    width1=d2.width;
                    height1=d2.height;

                      x=xMargin;
                      y=y+maximumHeightBase+ySpace;
                      maximumHeightBase=height1;

                  if (y+height1+ySpace>baseHeight)
                {
                      int dist=y+height1+ySpace-baseHeight;
                    baseHeight=baseHeight+dist;
                }

                 int width=0;
                 width=referenceWidth;
                 if (referenceWidth<width1) width=width1;

                  basePanel.setPreferredSize(
                       new Dimension(width+10*xSpaceRight,baseHeight));
                  basePanel.add(demonstrationTree);
                  demonstrationTree.setBounds(x, y, width1,height1);

                  if(height1>maximumHeightBase) maximumHeightBase=height1;
                   x=x+width1+xSpace;
                 //redesign base panel
                 basePanel.revalidate();
                 basePanel.repaint();
                 basePanel.repaint();
                }
                //END redisplay demonstration
         }

    }
   public void moveUpFCAndUpdate()
    {
         if (this.selectedDemonstrationItem0!=null)
         {
             javax.swing.JPanel basePanel=null;
             basePanel=(javax.swing.JPanel)demonstrationTree.getParent();
          if (basePanel!=null)
          {
             int position1=-1;
             if(this.demonstrationSource!=null)
                {
             if(this.demonstrationSource.downLink!=null)
                {
             position1=
                  this.demonstrationSource.downLink.indexOf(selectedDemonstrationItem0);
             if(position1>0)
               {
                DemonstrationItem item1=
                           this.demonstrationSource.downLink.get(position1-1);
                this.demonstrationSource.downLink.remove(position1);
                this.demonstrationSource.downLink.
                                               add(position1-1, selectedDemonstrationItem0);
                
               }

                }
                }
                 //redisplay demonstration
                 basePanel.remove(demonstrationTree);
                 //restoration
                 x=xBeforeDemonstration;
                 y=yBeforeDemonstration;
                 referenceWidth=baseWidthBeforeDemonstration;
                 baseHeight=baseHeightBeforeDemonstration;
                 maximumHeightBase=baseMaximumHeightBeforeDemonstration;
                 //END restoration
                 if (demonstrationSource!=null)
                {
                   demonstrationTree=
                           createDemonstrationTree(demonstrationSource);
                   Dimension d2=demonstrationTree.getPreferredSize();
                    width1=d2.width;
                    height1=d2.height;

                      x=xMargin;
                      y=y+maximumHeightBase+ySpace;
                      maximumHeightBase=height1;

                  if (y+height1+ySpace>baseHeight)
                {
                      int dist=y+height1+ySpace-baseHeight;
                    baseHeight=baseHeight+dist;
                }

                 int width=0;
                 width=referenceWidth;
                 if (referenceWidth<width1) width=width1;

                  basePanel.setPreferredSize(
                       new Dimension(width+10*xSpaceRight,baseHeight));
                  basePanel.add(demonstrationTree);
                  demonstrationTree.setBounds(x, y, width1,height1);

                  if(height1>maximumHeightBase) maximumHeightBase=height1;
                   x=x+width1+xSpace;
                 //redesign base panel
                 basePanel.revalidate();
                }
                //END redisplay demonstration
         }
         }
    }

   public void moveDownFCAndUpdate()
    {
         if (this.selectedDemonstrationItem0!=null)
         {
             javax.swing.JPanel basePanel=null;
             basePanel=(javax.swing.JPanel)demonstrationTree.getParent();
          if (basePanel!=null)
          {
             int position1=-1;int max=0;
             if(this.demonstrationSource!=null)
                {
             if(this.demonstrationSource.downLink!=null)
                {
             position1=
                  this.demonstrationSource.downLink.indexOf(selectedDemonstrationItem0);
             max=this.demonstrationSource.downLink.size();
               if(position1+1<max)
               {
                
                this.demonstrationSource.downLink.remove(position1);
                this.demonstrationSource.downLink.
                                               add(position1+1, selectedDemonstrationItem0);

               }

                }
                }
                 //redisplay demonstration
                 basePanel.remove(demonstrationTree);
                 //restoration
                 x=xBeforeDemonstration;
                 y=yBeforeDemonstration;
                 referenceWidth=baseWidthBeforeDemonstration;
                 baseHeight=baseHeightBeforeDemonstration;
                 maximumHeightBase=baseMaximumHeightBeforeDemonstration;
                 //END restoration
                 if (demonstrationSource!=null)
                {
                   demonstrationTree=
                           createDemonstrationTree(demonstrationSource);
                   Dimension d2=demonstrationTree.getPreferredSize();
                    width1=d2.width;
                    height1=d2.height;

                      x=xMargin;
                      y=y+maximumHeightBase+ySpace;
                      maximumHeightBase=height1;

                  if (y+height1+ySpace>baseHeight)
                {
                      int dist=y+height1+ySpace-baseHeight;
                    baseHeight=baseHeight+dist;
                }

                 int width=0;
                 width=referenceWidth;
                 if (referenceWidth<width1) width=width1;

                  basePanel.setPreferredSize(
                       new Dimension(width+10*xSpaceRight,baseHeight));
                  basePanel.add(demonstrationTree);
                  demonstrationTree.setBounds(x, y, width1,height1);

                  if(height1>maximumHeightBase) maximumHeightBase=height1;
                   x=x+width1+xSpace;
                  //redesign the base panel
                 basePanel.revalidate();
                }
                //END redisplay demonstration
         }
         }
    }
public void demarcateHubsOrRepetitions(DemonstrationItem d)
{
  if (d!=null)
  {
   if(d.type==DemonstrationConstants.NEW_STATEMENT)
   {
    if ((d.markedAsAHub)|(d.repetition))
     {
      d.markedAsAHub=false;
      d.repetition=false;
      d.numberOfOrderInCompressedProof=0;
     }   
   }
   if(d.downLink!=null)
   {
    if(!(d.downLink.isEmpty()))
    {
     int max=d.downLink.size();
     for(int i=0;i<max;i++)
     {
       demarcateHubsOrRepetitions(d.downLink.get(i));  
     }
    }
   }
   
   
  }
}

   public void deleteBranchAndUpdateGraph()
    {
         if (this.selectedDemonstrationItem0!=null)
         {
             javax.swing.JPanel basePanel=null;
             basePanel=(javax.swing.JPanel)demonstrationTree.getParent();
          if (basePanel!=null)
          {

             if (frame!=null)
             {
                 //we delete subordinates
                if (frame.currentDemonstrationEditor!=null)
                {
                 if (frame.currentDemonstrationEditor.demonstrationStrategyType==0)
                {
                 this.weDestroyedHubOrRepetition=false;
                 this.destroyBranchBackwardChaining(selectedDemonstrationItem0, true);
                 this.updateStatementsAfterDestruction(demonstrationSource);
                 if(this.weDestroyedHubOrRepetition)
                 {
                  this.demarcateHubsOrRepetitions(demonstrationSource);
                  this.numberOfHubs=0;
                  this.numberOfHubs2=0;
                  this.verifyRepetition(demonstrationSource);
                 }
                 
                }
                else if (frame.currentDemonstrationEditor.demonstrationStrategyType==1)
                {
                  this.destroyBranchForwardChaining(selectedDemonstrationItem0, true);
                }

                }
            }

                 //redisplay demonstration
                 basePanel.remove(demonstrationTree);
                 //restoration
                 x=xBeforeDemonstration;
                 y=yBeforeDemonstration;
                 referenceWidth=baseWidthBeforeDemonstration;
                 baseHeight=baseHeightBeforeDemonstration;
                 maximumHeightBase=baseMaximumHeightBeforeDemonstration;
                 //END restoration
                 if (demonstrationSource!=null)
                {
                   demonstrationTree=
                           createDemonstrationTree(demonstrationSource);
                   Dimension d2=demonstrationTree.getPreferredSize();
                    width1=d2.width;
                    height1=d2.height;

                      x=xMargin;
                      y=y+maximumHeightBase+ySpace;
                      maximumHeightBase=height1;

                  if (y+height1+ySpace>baseHeight)
                {
                      int dist=y+height1+ySpace-baseHeight;
                    baseHeight=baseHeight+dist;
                }

                 int width=0;
                 width=referenceWidth;
                 if (referenceWidth<width1) width=width1;

                  basePanel.setPreferredSize(
                       new Dimension(width+10*xSpaceRight,baseHeight));
                  basePanel.add(demonstrationTree);
                  demonstrationTree.setBounds(x, y, width1,height1);

                  if(height1>maximumHeightBase) maximumHeightBase=height1;
                   x=x+width1+xSpace;

                 //redesign the base panel
                 basePanel.revalidate();
                 basePanel.repaint();
                 
                }
                //END redisplay demonstration
         }
         }
    }
 

private static void errorNewLine( String msg)
{
System.out.println(msg);
};

public final javax.swing.JPanel createBase()
{
    //initialize variables
    x=xMargin;y=yMargin;//absolute positioning in base panel
    //provisionally
    width1=60;height1=35;
    commentWidth=0;commentHeight=0;
    baseHeight=10;
    maximumHeightBase=0;
    //END initialize variables
     javax.swing.JPanel basePanel=new javax.swing.JPanel();
     basePanel.setLayout(null);
     basePanel.setBackground(new java.awt.Color(255, 255, 255));
     return basePanel;
}

private void createVisualItemTheorem
        (Theorem theoremItem,
         DemonstrationItem demonstration1,
         javax.swing.JPanel basePanel)
{
  if (theoremItem!=null)
  {
    if (theoremItem.items!=null)
    {
    if (theoremItem.items.size()>0)
    {
        //display proper theorem
        if (theoremItem.items!=null)
        {
         javax.swing.JLabel label=this.createLabel
                    (this.theoremViewText(theoremItem));
           Dimension d=label.getPreferredSize();
            width1=d.width;
            height1=d.height;

              x=xMargin+referenceWidth/2-width1/2;
              y=y+maximumHeightBase+ySpace;
              maximumHeightBase=height1;

          if (y+height1+ySpace>baseHeight)
        {
              int distance=y+height1+ySpace-baseHeight;
            baseHeight=baseHeight+distance;

        }
          basePanel.setPreferredSize(
               new Dimension(referenceWidth+xSpaceRight,baseHeight));
          basePanel.add(label);
          label.setBounds(x, y, width1,height1);

          if(height1>maximumHeightBase) maximumHeightBase=height1;
           x=x+width1+xSpace;
        }

       //END display proper theorem

         
       //display type of demonstration strategy label

         strategyTypeLabel=this.createLabel
                    (forwardChainingStrategyLabel);
         
           Dimension d=strategyTypeLabel.getPreferredSize();
            width1=d.width;
            height1=d.height;

              x=xMargin+referenceWidth/2-width1/2;
              y=y+maximumHeightBase+ySpace;
              maximumHeightBase=height1;

          if (y+height1+ySpace>baseHeight)
        {
              int distance=y+height1+ySpace-baseHeight;
            baseHeight=baseHeight+distance;

        }
          basePanel.setPreferredSize(
               new Dimension(referenceWidth+xSpaceRight,baseHeight));
          basePanel.add(strategyTypeLabel);
          strategyTypeLabel.setBounds(x, y, width1,height1);

          if(height1>maximumHeightBase) maximumHeightBase=height1;
           x=x+width1+xSpace;
        

       //END display type of demonstration strategy label

        //change strategy button
                    
       if (this.demonstrationStrategyType==0)
       {
        switch0=forwardChainingStrategySwitch;
       }
        else
        {
           switch0=backwardChainingStrategySwitch;
        }

       strategySwitch=this.createButton(switch0);
       strategySwitch.addActionListener(
            new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
               changeStrategyButton(evt,strategyTypeLabel);
            }
        });
       Dimension switchDimension= strategySwitch.getPreferredSize();
        width1=200;
        height1=switchDimension.height;

          x=xMargin+referenceWidth/2-width1/2;
          y=y+maximumHeightBase+ySpace;
          maximumHeightBase=height1;

      if (y+height1+ySpace>baseHeight)
    {
        int distance=y+height1+ySpace-baseHeight;
        baseHeight=baseHeight+distance;

    }
      basePanel.setPreferredSize(
              new Dimension(referenceWidth+xSpaceRight,baseHeight));
      basePanel.add(strategySwitch);
      strategySwitch.setBounds(x, y, width1,height1);

      if(height1>maximumHeightBase) maximumHeightBase=height1;
       x=x+width1+xSpace;
      //END change strategy button
        
      
       //extraction button
       
       demonstrationExtraction=this.createButton("Extract the proof string Wait");
       demonstrationExtraction.addActionListener(
            new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
              demonstrationString="";
             
                if(demonstrationStrategyType==0)
                { //backward chaining
                 if(haveSolutionBackwardChaining())
                 {
                   //do the cleaning before a new crossing
                   numberOfHubs=0;
                   numberOfHubs2=0;
                   listOfHubs.clear();
                   listOfRepetitions.clear();
                   vector_a_p.clear();
                   demonstrationString="";
                   stringCounter=0;
                   
                   findHubsAndRepetitions(demonstrationSource);
                   recoverNewHubs();
                   
                   crossingRenumberingHubs(demonstrationSource);
                   reupdateDemonstrationTree();
                   saveBackwardChaining();
                   JFrame mainFrame = Application0.getApplication().getMainFrame();
                   Application0.getApplication().
                                    show(new ExtractionStringWindow(mainFrame,true));
          
                 }
                }
                else
                 if(demonstrationStrategyType==1)
                { //forward chaining
                 if(haveSolutionForwardChaining())
                 {
                   // do the cleaning before a new crossing
                   numberOfHubs=0;
                   numberOfHubs2=0;
                   listOfHubs.clear();
                   listOfRepetitions.clear();
                   vector_a_p.clear();
                   demonstrationString="";
                   stringCounter=0;
                   demonstrationBeginning=null;
                   if (demonstrationSource.downLink!=null)
                  {
                    if(!demonstrationSource.downLink.isEmpty())
                    {
                      int max=demonstrationSource.downLink.size();
                     for(int i=0;i<max;i++)
                     {
                      if(Source.isEqualXWithY
                              (demonstrationSource.items,
                              demonstrationSource.downLink.get(i).items
                              )
                         )
                      {
                       demonstrationBeginning=demonstrationSource.downLink.get(i);
                       break;
                      }
                     }

                   }
                   }

                   if(demonstrationBeginning!=null)
                   {
                       //here we find hubs that are outside of the main proof
                       // and we tranform them into repetions

                     int max0=demonstrationSource.downLink.size();
                    for(int i0=0;i0<max0;i0++)
                    {
                     DemonstrationItem itemI0=demonstrationSource.downLink.get(i0);
                     if(!itemI0.equals(demonstrationBeginning))
                     {
                      findHubsThatAreOutside(itemI0);
                     }
                    }
                    //we clear all the descendents of demonstrationSource
                    //and we leave only de demonstrationBeginning
                    demonstrationSource.downLink.clear();
                    demonstrationSource.downLink.add(demonstrationBeginning);
                    demonstrationBeginning.aboveLink=demonstrationSource;




                     
                   findHubsAndRepetitions(demonstrationBeginning);
                   recoverNewHubs();
                   verifyIfRepetitionIsValid();
                   crossingRenumberingHubs(demonstrationBeginning);
                   reupdateDemonstrationTree();
                   saveForwardChaining();
                   JFrame mainFrame = Application0.getApplication().
                                                                getMainFrame();
                   Application0.getApplication().
                               show(new ExtractionStringWindow(mainFrame,true));

                   }

                 }
                }
            }
        });

       
       Dimension switchDimension1=demonstrationExtraction.getPreferredSize();
        width1=200;
        height1=switchDimension1.height;

          x=xMargin+referenceWidth/2-width1/2;
          y=y+maximumHeightBase+ySpace;
          maximumHeightBase=height1;

      if (y+height1+ySpace>baseHeight)
    {
        int dist=y+height1+ySpace-baseHeight;
        baseHeight=baseHeight+dist;

    }
      basePanel.setPreferredSize(
              new Dimension(referenceWidth+xSpaceRight,baseHeight));
      basePanel.add(demonstrationExtraction);
      demonstrationExtraction.setBounds(x, y, width1,height1);

      if(height1>maximumHeightBase) maximumHeightBase=height1;
       x=x+width1+xSpace;
      //END extraction button

       //switch button
      
       if (fullDemonstration1)
       {
        switch1=essential;
       }
        else
        {
           switch1=full;
        }

       demonstrationSwitch=this.createButton(switch1);
       demonstrationSwitch.addActionListener(
            new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
               demonstrationTypePressedButton(evt);
            }
        });
       Dimension switchDimension2=demonstrationSwitch.getPreferredSize();
        width1=200;
        height1=switchDimension2.height;

          x=xMargin+referenceWidth/2-width1/2;
          y=y+maximumHeightBase+ySpace;
          maximumHeightBase=height1;

      if (y+height1+ySpace>baseHeight)
    {
        int dist=y+height1+ySpace-baseHeight;
        baseHeight=baseHeight+dist;

    }
      basePanel.setPreferredSize(
              new Dimension(referenceWidth+xSpaceRight,baseHeight));
      basePanel.add(demonstrationSwitch);
      demonstrationSwitch.setBounds(x, y, width1,height1);

      if(height1>maximumHeightBase) maximumHeightBase=height1;
       x=x+width1+xSpace;
      //END switch button

        //save button

       saveDemonstration=this.createButton("Save");
       saveDemonstration.addActionListener(
            new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
              save();
            }
        });
       Dimension saveDimension= saveDemonstration.getPreferredSize();
        width1=200;
        height1=saveDimension.height;

          x=xMargin+referenceWidth/2-width1/2;
          y=y+maximumHeightBase+ySpace;
          maximumHeightBase=height1;

      if (y+height1+ySpace>baseHeight)
    {
        int distance=y+height1+ySpace-baseHeight;
        baseHeight=baseHeight+distance;

    }
      basePanel.setPreferredSize(
              new Dimension(referenceWidth+xSpaceRight,baseHeight));
      basePanel.add(saveDemonstration);
      saveDemonstration.setBounds(x, y, width1,height1);

      if(height1>maximumHeightBase) maximumHeightBase=height1;
       x=x+width1+xSpace;
       //END save

       //open button

       openDemonstration=this.createButton("Open");
       openDemonstration.addActionListener(
            new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
             open();
            }
        });
       Dimension openDimension= openDemonstration.getPreferredSize();
        width1=200;
        height1=openDimension.height;

          x=xMargin+referenceWidth/2-width1/2;
          y=y+maximumHeightBase+ySpace;
          maximumHeightBase=height1;

      if (y+height1+ySpace>baseHeight)
    {
        int distance=y+height1+ySpace-baseHeight;
        baseHeight=baseHeight+distance;

    }
      basePanel.setPreferredSize(
              new Dimension(referenceWidth+xSpaceRight,baseHeight));
      basePanel.add(openDemonstration);
      openDemonstration.setBounds(x, y, width1,height1);

      if(height1>maximumHeightBase) maximumHeightBase=height1;
       x=x+width1+xSpace;
      //END open button

     
        //automation button
       /*
       automatedSearching=this.createButton("Automated search");
       automatedSearching.addActionListener(
            new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
             proofFindingFunction();
            }
        });
       Dimension automationDimension= automatedSearching.getPreferredSize();
        width1=200;
        height1=automationDimension.height;

          x=xMargin+referenceWidth/2-width1/2;
          y=y+maximumHeightBase+ySpace;
          maximumHeightBase=height1;

      if (y+height1+ySpace>baseHeight)
    {
        int distance=y+height1+ySpace-baseHeight;
        baseHeight=baseHeight+distance;

    }
      basePanel.setPreferredSize(
              new Dimension(referenceWidth+xSpaceRight,baseHeight));
      basePanel.add(automatedSearching);
      automatedSearching.setBounds(x, y, width1,height1);

      if(height1>maximumHeightBase) maximumHeightBase=height1;
       x=x+width1+xSpace;

       automatedSearching.setEnabled(false);//we block this button
       
      //END automation button

        //stop searching button

       stopSearching=this.createButton("Stop searching");
       stopSearching.addActionListener(
            new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
             stopFunction();
            }
        });
       Dimension stopDimension= stopSearching.getPreferredSize();
        width1=200;
        height1=stopDimension.height;

          x=xMargin+referenceWidth/2-width1/2;
          y=y+maximumHeightBase+ySpace;
          maximumHeightBase=height1;

      if (y+height1+ySpace>baseHeight)
    {
        int distance=y+height1+ySpace-baseHeight;
        baseHeight=baseHeight+distance;

    }
      basePanel.setPreferredSize(
              new Dimension(referenceWidth+xSpaceRight,baseHeight));
      basePanel.add(stopSearching);
      stopSearching.setBounds(x, y, width1,height1);

      if(height1>maximumHeightBase) maximumHeightBase=height1;
       x=x+width1+xSpace;

       stopSearching.setEnabled(false);//we block this buttn
      //END stop searching button

        * 
        */
        //exit button
      
       exit=this.createButton("Close");
       exit.addActionListener(
            new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
              close();
            }
        });
       Dimension exitDimension= exit.getPreferredSize();
        width1=200;
        height1=exitDimension.height;

          x=xMargin+referenceWidth/2-width1/2;
          y=y+maximumHeightBase+ySpace;
          maximumHeightBase=height1;

      if (y+height1+ySpace>baseHeight)
    {
        int distance=y+height1+ySpace-baseHeight;
        baseHeight=baseHeight+distance;

    }
      basePanel.setPreferredSize(
              new Dimension(referenceWidth+xSpaceRight,baseHeight));
      basePanel.add(exit);
      exit.setBounds(x, y, width1,height1);

      if(height1>maximumHeightBase) maximumHeightBase=height1;
       x=x+width1+xSpace;
       //END exit button

        //display demonstration

         //memorize
         xBeforeDemonstration=x;
         yBeforeDemonstration=y;
         baseWidthBeforeDemonstration=referenceWidth;
         baseHeightBeforeDemonstration=this.baseHeight;
         baseMaximumHeightBeforeDemonstration=maximumHeightBase;
         //END memorize

         if (demonstration1!=null)
        {
           demonstrationTree=this.createDemonstrationTree(demonstration1);
           Dimension d2=demonstrationTree.getPreferredSize();
            width1=d2.width;
            height1=d2.height;

              x=xMargin;
              y=y+maximumHeightBase+ySpace;
              maximumHeightBase=height1;

          if (y+height1+ySpace>baseHeight)
        {
            int dist=y+height1+ySpace-baseHeight;
            baseHeight=baseHeight+dist;

        }

         int width=0;
         width=referenceWidth;
         if (referenceWidth<width1) width=width1;

          basePanel.setPreferredSize(
               new Dimension(width+10*xSpaceRight,baseHeight));
          basePanel.add(demonstrationTree);
          demonstrationTree.setBounds(x, y, width1,height1);

          if(height1>maximumHeightBase) maximumHeightBase=height1;
           x=x+width1+xSpace;
        }
        //END display demonstration

    }
    }
    }

}
 class Filter extends javax.swing.filechooser.FileFilter
   {
       private String description=null;
       private String extension=null;
       public Filter(String extension,String description)
       {
        this.description=description;
        this.extension="."+extension.toLowerCase();
       }
       public String getDescription()
       {
           return description;
       }
       public boolean accept(File f)
       {
           if (f==null) return false;
           if (f.isDirectory()) return true;
           return f.getName().toLowerCase().endsWith(extension);
       }
   }
public void save()
{
 DemonstrationContext context=new DemonstrationContext();
 //we load  in the object all parameters of the demonstration
 context.fileName=this.fileName;
 context.problemName=this.theoremName;
 context.fullDemonstration=this.fullDemonstration1;
 context.demonstrationStrategyType=this.demonstrationStrategyType;
 context.demonstrationSource=this.demonstrationSource;
 context.newStatementAndContentAtZeroPhase=this.newStatementAndContentPhaseZero;
 context.newStatementAndGeneratedVariableContent=this.newStatementAndGeneratedVariableContent;
 context.newVariableAndClass=this.newVariableAndClass;
 context.newVariableAndContent=this.newVariableAndContent;
 context.newStatementAndGeneratedVariables=this.newStatementAndGeneratedVariable;
 //we save the list of items that will be applied in demonstration
 Application0 a=(Application0)Application.getInstance();
         if(a!=null)
         {
         if(a.frame0!=null)
         {

         if(a.frame0.listOfAppliedItems!=null)
         {
          context.listOfAppliedItems=a.frame0.listOfAppliedItems;
         }
         }
         }
 context.numberOfOrderNewStatements=this.numberOfOrderNewStatements;
 context.numberOfOrderVariables=this.numberOfOderVariables;
 context.numberOfHubs=this.numberOfHubs;
 
 //we choose the file where we save
 javax.swing.JFileChooser file=new javax.swing.JFileChooser("c:\\");
 file.setFileFilter(new Filter("mlp","*.mlp"));
 file.showSaveDialog(null);
 java.io.File f=null;
 f=file.getSelectedFile();
 //busy
 this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
 if(f!=null)
 {
 //we serialize the context object
try
{
  ObjectOutputStream oos=new ObjectOutputStream( new
          FileOutputStream(f.getAbsolutePath()));
  oos.writeObject(context);
  oos.close();
}
catch(IOException e)
{
    JOptionPane.showMessageDialog
              (null,
               "Saving issue:"+e.getMessage(),
               "Attention!",
               JOptionPane.OK_OPTION
               );
}
 }
 //unoccupied
 this.setCursor(Cursor.getDefaultCursor());
}
public void open()
{
      DemonstrationContext context=null;
 //where we find the file
 javax.swing.JFileChooser file=new javax.swing.JFileChooser("c:\\");
 file.setFileFilter(new Filter("mlp","*.mlp"));
 file.showOpenDialog(null);
 java.io.File f=null;
 f=file.getSelectedFile();
 //busy
 this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
 if(f!=null)
 {
    //deserialize the context object
try
{
  ObjectInputStream ois=new ObjectInputStream( new
          FileInputStream(f.getAbsolutePath()));
   context=(DemonstrationContext)ois.readObject();
  ois.close();
}
catch(IOException e)
{
     JOptionPane.showMessageDialog
              (null,
               "Opening issue:"+e.getMessage(),
               "Attention!",
               JOptionPane.OK_OPTION
               );
}
catch(ClassNotFoundException e)
{
    JOptionPane.showMessageDialog
              (null,
               "We did not found object class in the stream: "+e.getMessage(),
               "Attention!",
               JOptionPane.OK_OPTION
               );
}
}
//we unload from the object all parameters of the demonstration
 if(context!=null)
 {
 if( (context.fileName.equals(this.fileName) )&
         (context.problemName.equals(this.theoremName) )
   )
 {
 this.fullDemonstration1=context.fullDemonstration;
 this.demonstrationStrategyType=context.demonstrationStrategyType;
 this.demonstrationSource=context.demonstrationSource;
 this.newStatementAndContentPhaseZero=context.newStatementAndContentAtZeroPhase;
 this.newStatementAndGeneratedVariableContent=context.newStatementAndGeneratedVariableContent;
 this.newVariableAndClass=context.newVariableAndClass;
 this.newVariableAndContent=context.newVariableAndContent;
 this.newStatementAndGeneratedVariable=context.newStatementAndGeneratedVariables;
 //we save the list of items that will be applied in the demonstration
 Application0 a=(Application0)Application.getInstance();
         if(a!=null)
         {
         if(a.frame0!=null)
         {

         if(a.frame0.listOfAppliedItems!=null)
         {
          a.frame0.listOfAppliedItems=context.listOfAppliedItems;
         }
         }
         }
 this.numberOfOrderNewStatements=context.numberOfOrderNewStatements;
 this.numberOfOderVariables=context.numberOfOrderVariables;
 this.numberOfHubs=context.numberOfHubs;
 
   //strategy switch
     if (this.demonstrationStrategyType==0)
       {
        switch0=forwardChainingStrategySwitch;
        strategyTypeLabel.setText(this.backwardChainingStrategyLabel);
       }
        else
        {
           switch0=backwardChainingStrategySwitch;
           strategyTypeLabel.setText(this.forwardChainingStrategyLabel);
        }

       strategySwitch.setText(switch0);
   //END strategy switch
   //switch between full and essential demonstration
   if (fullDemonstration1)
       {
        switch1=essential;
       }
        else
        {
           switch1=full;
        }

       demonstrationSwitch.setText(switch1);
    //END switch between full and essential demonstration
    //redisplay demonstration
         javax.swing.JPanel basePanel=null;
         basePanel=(javax.swing.JPanel)demonstrationTree.getParent();
         if (basePanel!=null)
         {
         basePanel.remove(demonstrationTree);
         //restoration
         x=xBeforeDemonstration;
         y=yBeforeDemonstration;
         referenceWidth=baseWidthBeforeDemonstration;
         baseHeight=baseHeightBeforeDemonstration;
         maximumHeightBase=baseMaximumHeightBeforeDemonstration;
         //END restoration
         if (demonstrationSource!=null)
        {
           demonstrationTree=
                   createDemonstrationTree(demonstrationSource);
           Dimension d2=demonstrationTree.getPreferredSize();
            width1=d2.width;
            height1=d2.height;

              x=xMargin;
              y=y+maximumHeightBase+ySpace;
              maximumHeightBase=height1;

          if (y+height1+ySpace>baseHeight)
        {
              int distance=y+height1+ySpace-baseHeight;
            baseHeight=baseHeight+distance;
        }

         int width=0;
         width=referenceWidth;
         if (referenceWidth<width1) width=width1;

          basePanel.setPreferredSize(
               new Dimension(width+10*xSpaceRight,baseHeight));
          basePanel.add(demonstrationTree);
          demonstrationTree.setBounds(x, y, width1,height1);

          if(height1>maximumHeightBase) maximumHeightBase=height1;
           x=x+width1+xSpace;
         //redesign base panel
         basePanel.revalidate();
         basePanel.repaint();
         basePanel.repaint();
        }
        }
        //END redisplay demonstration
        }
         else
         {
          JOptionPane.showMessageDialog
              (null,
               "Name of file or name of problem/theorem does not match!",
               "Attention!",
               JOptionPane.OK_OPTION
               );
         }
}
 //unoccupied
 this.setCursor(Cursor.getDefaultCursor());
}
public void automation()
{
  this.weHaveFoundTheProof=
                          this.backwardChainingSearch(demonstrationSource, 0,0);
 
}

public void close()
 {
      int nr=JOptionPane.showConfirmDialog
              (null,
               "Are you sure that you want to close!",
               "Attention!",
               JOptionPane.YES_NO_OPTION
               );
             
             Application0 a=(Application0)Application.getInstance();
             if(a!=null)
             {
              if(a.frame0!=null)
              {
               if(a.frame0.filesTab!=null)
               {
                 if(nr==0)
                 {
                   a.frame0.filesTab.remove((Component)this);
                   a.frame0.currentDemonstrationEditor=null;
                 }
               }
              }
             }
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
   node=DisplayDemonstrationTree.generateTree(demonstration1,fullDemonstration1);
   DefaultTreeModel treeModel=new DefaultTreeModel(node);
   tree.setModel(treeModel);
   DefaultTreeSelectionModel selectionModel=new DefaultTreeSelectionModel();
   selectionModel.setSelectionMode(
                DefaultTreeSelectionModel.SINGLE_TREE_SELECTION);
   selectionModel.addTreeSelectionListener(
                                  new SelectionListener());
   tree.setSelectionModel(selectionModel);
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

public void demonstrationTypePressedButton(java.awt.event.ActionEvent evt)
{
javax.swing.JButton button=(javax.swing.JButton) evt.getSource();
javax.swing.JPanel basePanel=(javax.swing.JPanel) button.getParent();

 if (fullDemonstration1)
 {
  fullDemonstration1=false;
 }
 else
    {
     fullDemonstration1 = true;
    }
 if (fullDemonstration1)
 {
     switch1=essential;
 }
 else
  {
     switch1=full;
  }
 button.setText(switch1);

 //redisplay demonstration
 basePanel.remove(demonstrationTree);
 //restoration
 x=xBeforeDemonstration;
 y=yBeforeDemonstration;
 referenceWidth=baseWidthBeforeDemonstration;
 baseHeight=baseHeightBeforeDemonstration;
 maximumHeightBase=baseMaximumHeightBeforeDemonstration;
 //END restoration
 if (demonstrationSource!=null)
{
   demonstrationTree=createDemonstrationTree(demonstrationSource);
   Dimension d2=demonstrationTree.getPreferredSize();
    width1=d2.width;
    height1=d2.height;

    x=xMargin;
    y=y+maximumHeightBase+ySpace;
    maximumHeightBase=height1;

    if (y+height1+ySpace>baseHeight)
    {
        int dist=y+height1+ySpace-baseHeight;
        baseHeight=baseHeight+dist;
    }

 int width=0;
 width=referenceWidth;
 if (referenceWidth<width1) width=width1;
  basePanel.setPreferredSize(
       new Dimension(width+10*xSpaceRight,baseHeight));
  basePanel.add(demonstrationTree);
  demonstrationTree.setBounds(x, y, width1,height1);

  if(height1>maximumHeightBase) maximumHeightBase=height1;
   x=x+width1+xSpace;
 //redesign base panel
 basePanel.revalidate();
 basePanel.repaint();
}
//END redisplay demonstration

}
 public javax.swing.JLabel createLabel(String s)
{
    javax.swing.JLabel label=new javax.swing.JLabel();

    label.setBackground(new java.awt.Color(4, 255, 129));
    label.setForeground(new java.awt.Color(255, 255, 255));
    label.setFont(new java.awt.Font("Arial", 0, 14));
    label.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    label.setFocusCycleRoot(true);
    label.setOpaque(true);
    label.setText(s);
    
    return label;
}

  //her begins view of text for generated items e,a,p
public void reupdateDemonstrationTree()
{
    //redisplay demonstration
   javax.swing.JPanel basePanel=
    (javax.swing.JPanel)demonstrationTree.getParent();
   
     basePanel.remove(demonstrationTree);
     //restoration
     x=xBeforeDemonstration;
     y=yBeforeDemonstration;
     referenceWidth=baseWidthBeforeDemonstration;
     baseHeight=baseHeightBeforeDemonstration;
     maximumHeightBase=baseMaximumHeightBeforeDemonstration;
     //END restoration
     if (demonstrationSource!=null)
    {
       demonstrationTree=
               createDemonstrationTree(demonstrationSource);
       Dimension d2=demonstrationTree.getPreferredSize();
        width1=d2.width;
        height1=d2.height;

          x=xMargin;
          y=y+maximumHeightBase+ySpace;
          maximumHeightBase=height1;

      if (y+height1+ySpace>baseHeight)
    {
          int distance=y+height1+ySpace-baseHeight;
        baseHeight=baseHeight+distance;
    }

     int width=0;
     width=referenceWidth;
     if (referenceWidth<width1) width=width1;

      basePanel.setPreferredSize(
           new Dimension(width+10*xSpaceRight,baseHeight));
      basePanel.add(demonstrationTree);
      demonstrationTree.setBounds(x, y, width1,height1);

      if(height1>maximumHeightBase) maximumHeightBase=height1;
       x=x+width1+xSpace;
     //redesign base panel
     basePanel.revalidate();
     basePanel.repaint();
     basePanel.repaint();
     basePanel.repaint();
    }
    //END redisplay demonstration
}
private  String theoremViewText(Theorem  theoremItem)
{
String pBeginning="<P> <B>   Theorem  ";
String end="</B> </FONT> </P>";

int i=0;
String displayedText="";

 if (theoremItem!=null)
{
//we have a theorem
displayedText=pBeginning+theoremItem.name+":<br>  ";
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
displayedText=displayedText+" "+s2;
i++;
} while (i<dim);
displayedText=displayedText+end;

}
}
//END theorem
}

 else displayedText="Teorema nu exista" ;

//we encapsulate it in html and replace \n with <br>
Application0 a=(Application0)Application.getInstance();
displayedText="<HTML>"
          +"<base href='file:"+a.path+"/symbols/  '/>"
          +displayedText
          +" </HTML>";

return displayedText;
};
;




public void updateNewStatements(DemonstrationItem demonstrationItem)
{
    if (demonstrationItem!=null)
    {
    if(demonstrationItem.type==DemonstrationConstants.NEW_STATEMENT)
    {
       if(demonstrationItem.items!=null)
       {
           List<ConstantAndVariable> statement0=Source.copyTheListOfConstantAndVariable(demonstrationItem.items);
           List<ConstantAndVariable> statement1=
           Source.noCommentVariablesContentInsertion
                                                    (statement0, newVariableAndContent);
           demonstrationItem.items=statement1;//we replace the list with an updated one
       }
    }
     else if(demonstrationItem.type==DemonstrationConstants.VARIABLE)
    {
       if(demonstrationItem.provisionallyItems!=null)
       {
           List<ConstantAndVariable> statement0=Source.copyTheListOfConstantAndVariable(demonstrationItem.provisionallyItems);
           List<ConstantAndVariable> statement1=
           Source.noCommentVariablesContentInsertion
                                                    (statement0, newVariableAndContent);
           demonstrationItem.provisionallyItems=statement1;//we replace the provisional list with an updated one
       }
    }
    
    //we see if there are descendants and we go through them
    if (demonstrationItem.downLink!=null)
    {
       int max=demonstrationItem.downLink.size();
       if (max>0)
       {
         for (int i=0;i<max;i++)
         {
          updateNewStatements(demonstrationItem.downLink.get(i));
         }
       }
    }

    }


}
public void updateLocalVariables(DemonstrationItem demonstrationItem)
{
    if (demonstrationItem!=null)
    {
     if(demonstrationItem.type==DemonstrationConstants.VARIABLE)
    {
       if(demonstrationItem.provisionallyItems!=null)
       {
           DemonstrationItem subordinatedItem=null;
           if (demonstrationItem.downLink!=null)
           {
            if(!demonstrationItem.downLink.isEmpty())
            {
              if(demonstrationItem.downLink.get(0).type==DemonstrationConstants.NEW_STATEMENT)
              {
                  subordinatedItem=demonstrationItem.downLink.get(0);
                  if (subordinatedItem.items!=null)
                  {
                   if (subordinatedItem.items.size()<2)
                   {
                    //need at least 2 items:wff ch
                       subordinatedItem=null;
                   }
                  }
                    else{ 
                         subordinatedItem=null;
                        }
                 
              }
            }
           }

           if (subordinatedItem!=null)
           {
           
            List<ConstantAndVariable> statement=null;
           
           int length=1;
           length=subordinatedItem.items.size()-1;
           statement=Source.copyTheSublistOfConstantAndVariable(subordinatedItem.items,1,length);
           demonstrationItem.provisionallyItems=statement;//setam noul continut al variabilei
           
           }
           
          else
          {
           /*we delete the content of local variable*/
           demonstrationItem.provisionallyItems.clear();
          }

       }
    }
    //we see if has descendants and go through them
    if (demonstrationItem.downLink!=null)
    {
       int max=demonstrationItem.downLink.size();
       if (max>0)
       {
         for (int i=0;i<max;i++)
         {
          updateLocalVariables(demonstrationItem.downLink.get(i));
         }
       }
    }

    }


}
public void findXHub(DemonstrationItem xStatement,DemonstrationItem demonstrationItem)
{
   boolean found=false;
    if (demonstrationItem!=null)
    {
    if(demonstrationItem.type==DemonstrationConstants.NEW_STATEMENT)
    {
       if(demonstrationItem.items!=null)
       {
         //if we are not one the same leaf in the tree
         if(!(demonstrationItem.name.equals(xStatement.name)))
          {
        if((!demonstrationItem.repetition)&(!demonstrationItem.markedAsAHub))
          {
          if (Source.isEqualXWithY(demonstrationItem.items, xStatement.items))
          {
             //we verify if demonstrationItem has 2 levels of descendants
              if(demonstrationItem.downLink!=null)
              {
               if(!demonstrationItem.downLink.isEmpty())
               {

               if(demonstrationItem.downLink.get(0).downLink!=null)
              {
               if(!demonstrationItem.downLink.get(0).downLink.isEmpty())
               {
                 found=true;
                 this.numberOfHubs++;
                 //we mark as a hub demonstationItem
                 demonstrationItem.markedAsAHub=true;
                 demonstrationItem.numberOfOrderInCompressedProof=numberOfHubs;
                 //we mark as a repetition xStatement
                 xStatement.repetition=true;
                 xStatement.numberOfOrderInCompressedProof=numberOfHubs;

                }
               }
               }
              }
            
          }
         }
         }
        }
    }
    //if we not found the hub
    //we see if it has descendants and go through them
    if(!found)
    {
    if (demonstrationItem.downLink!=null)
    {
       int max=demonstrationItem.downLink.size();
       if (max>0)
       {
         for (int i=max-1;i>=0;i--)
         {
          findXHub(xStatement,demonstrationItem.downLink.get(i));
         }
       }
    }
    }

    }

}

public void findEqualsX(DemonstrationItem xStatement,DemonstrationItem demonstrationItem)
{
   boolean found=false;
    if (demonstrationItem!=null)
    {
    if(demonstrationItem.type==DemonstrationConstants.NEW_STATEMENT)
    {
       if(demonstrationItem.items!=null)
       {
         //if we are not on the same leaf in the tree
         if(!(demonstrationItem.name.equals(xStatement.name)))
          {
        if((demonstrationItem.repetition)|(demonstrationItem.markedAsAHub))
          {
          if (Source.isEqualXWithY(demonstrationItem.items, xStatement.items))
          {
           //if xStatement is equal cu demonstrationItem(which is repetition or hub)
           //then xStatement(which is leaf) will become repetitiion and will take over
           //the numberOfOrderInCompressedProof
             xStatement.repetition=true;
             xStatement.numberOfOrderInCompressedProof=demonstrationItem.numberOfOrderInCompressedProof;
             found=true;
          }
         }
         }
        }
    }

    //if is not found, we see if it has descendants and we go through them
    if(!found)
    {
    if (demonstrationItem.downLink!=null)
    {
       int max=demonstrationItem.downLink.size();
       if (max>0)
       {
         for (int i=max-1;i>=0;i--)
         {
          findEqualsX(xStatement,demonstrationItem.downLink.get(i));
         }
       }
     }
     }

    }
}
public void verifyRepetition(DemonstrationItem demonstrationItem)
{
    if (demonstrationItem!=null)
    {
    if(demonstrationItem.type==DemonstrationConstants.NEW_STATEMENT)
    {
       if(demonstrationItem.items!=null)
       {
       if((!demonstrationItem.repetition)&(!demonstrationItem.markedAsAHub))
       {
       if (demonstrationItem.downLink==null)
       {
        //we are looking for repetitions or hubs equals with demonstrationItem
        this.findEqualsX(demonstrationItem, this.demonstrationSource);
        //if we are not found repetitions or hubs equals
        if(!demonstrationItem.repetition)
        {
        this.findXHub(demonstrationItem,this.demonstrationSource);
        }
        
       }
        else if (demonstrationItem.downLink!=null)
       {
        if(demonstrationItem.downLink.isEmpty())
         {
            //we are looking for repetitions or hubs equals with demonstrationItem
            this.findEqualsX(demonstrationItem, this.demonstrationSource);
            //if we are not found repetitions or hubs equals
            if(!demonstrationItem.repetition)
            {
            this.findXHub(demonstrationItem,this.demonstrationSource);
            }
         
         }
        }

       }
       }
    }

    //we see if it has descendants and we go through them
    if (demonstrationItem.downLink!=null)
    {
       int max=demonstrationItem.downLink.size();
       if (max>0)
       {
         for (int i=max-1;i>=0;i--)
         {
          verifyRepetition(demonstrationItem.downLink.get(i));
         }
       }
    }

    }

}



public void destroyLinkStatementAndHub(DemonstrationItem newStatement)
{
 if(newStatement.repetition)
 {
 if (this.newStatementAndHubStatement.containsKey(newStatement))
 {
     DemonstrationItem hub=this.newStatementAndHubStatement.get(newStatement);
     //we destroy the link: statement and hub
     newStatementAndHubStatement.remove(newStatement);
     newStatement.repetition=false;
     //if the item 'hub' is no more hub for other statements we demarcate it
     if (!newStatementAndHubStatement.containsValue(hub))
     {
         hub.markedAsAHub=false;
     }
 }
 }
 else if (newStatement.markedAsAHub)
 {
 if (this.newStatementAndHubStatement.containsValue(newStatement))
 {
  Iterator<Map.Entry<DemonstrationItem,DemonstrationItem>>
                           iterator= newStatementAndHubStatement.entrySet().iterator();
  DemonstrationItem hub=null;
  Map.Entry<DemonstrationItem,DemonstrationItem> entry=null;
  List<DemonstrationItem> list=new ArrayList<DemonstrationItem>();
  while(iterator.hasNext())
  {
    entry=iterator.next();
    if (entry!=null)
    {
       hub=entry.getValue();
        if (hub==newStatement)
        {
            DemonstrationItem linkedStatement=entry.getKey();
            list.add(linkedStatement);
            linkedStatement.repetition=false;//we demarcate that is repetition
        }
    }
  }
  newStatement.markedAsAHub=false;//we demarcate that is hub
  if (!list.isEmpty())
  {
    int max= list.size();
    for (int i=0; i<max;i++)
    {
      DemonstrationItem xStatement=list.get(i);
      //we destroy the link: xStatement and newStatement
      this.newStatementAndHubStatement.remove(xStatement);
    }
    if (max>1)
    {
    DemonstrationItem newHub=list.get(0);//we choose an item and we make it a hub
    for (int i=1; i<max;i++)
    {
      DemonstrationItem xStatement=list.get(i);
      //we create link between xStatement and newHub
      this.newStatementAndHubStatement.put(xStatement, newHub);
    }
    }
  }
 }
 }
}

public void updateStatementsAfterDestruction(DemonstrationItem demonstrationItem)
{
    boolean clean=true;
    if (demonstrationItem!=null)
    {
    if(demonstrationItem.type==DemonstrationConstants.NEW_STATEMENT)
    {
       if (demonstrationItem.name!=null)
        {
           String statementName=demonstrationItem.name;
          if (this.newStatementAndContentPhaseZero.get(statementName)!=null)
         {
           List<ConstantAndVariable> statement0=null;
           List<ConstantAndVariable> statement1=null;
           statement1=this.newStatementAndContentPhaseZero.get(statementName);
           do {
           statement0=statement1;
           statement1=Source.noCommentVariablesContentInsertion
                                                    (statement0, newVariableAndContent);
           } while(!Source.isEqualXWithY(statement0, statement1));
           //we verify if it is 'clean' the statement and
           //does not contains non-existent variables
           int max=statement1.size();
           for (int i=0;i<max;i++)
           {
               if(statement1.get(i).constantOrVariable==2)
               {
                 String variableName=statement1.get(i).constantOrVariableText;
                 if(variableName!=null)
                 {
                  if(variableName.length()>=2)
                  {
                  if(
                          (variableName.charAt(0)=='$')
                          |(variableName.charAt(1)=='v')
                    )
                  {
                     if(!this.newVariableAndClass.containsKey(variableName))
                     {
                      //means that we have the variable that does not exist anymore
                      //so the statement no longer exists and must be deleted
                         clean=false;
                     }
                 }
                 }
                 }
                  if (!clean) break;
               }              
               }
           demonstrationItem.items=statement1;//replace the list with an updated one
           if (!clean)
                {
                 DemonstrationItem xItem=demonstrationItem;//x item from the demonstration
                 DemonstrationItem xItem1=xItem.aboveLink;//we climb the branch

                 if (xItem1!=null)
                 {
                     if (xItem1.type==DemonstrationConstants.NEW_STATEMENT)
                    {
                     //it is base, we delete all the subordinates
                     this.destroyBranchBackwardChaining(xItem1,true);
                    } else
                     {
                         DemonstrationItem xItem2=xItem1.aboveLink;//we climb the branch
                         if (xItem2!=null)
                         {
                             if (xItem2.type==DemonstrationConstants.NEW_STATEMENT)
                            {
                             //it is base, we delete all the subordinates
                             this.destroyBranchBackwardChaining(xItem2,true);
                            } else
                             {
                                 //we climb the branch
                                 DemonstrationItem xItem3 = xItem2.aboveLink;
                                 if(xItem3!=null)
                                    {
                                      if (xItem3.type==DemonstrationConstants.NEW_STATEMENT)
                                        {
                                        //it is base, we delete all the subordinates
                                        this.destroyBranchBackwardChaining(xItem3,true);
                                        }
                                    }
                             }
                         }
                     }
                  }
                }//END !clean
           }//END statementName
         }//END demonstrationItem.name
     }//END demonstrationItem.type
      
   if (clean)
   {
    //we see if it has descendants and we go through them
        if (demonstrationItem.downLink!=null)
        {
           int max=demonstrationItem.downLink.size();
           if (max>0)
           {
             for (int i=max-1;i>=0;i--)
             {
           updateStatementsAfterDestruction(demonstrationItem.downLink.get(i));
             }
           }
        }
    }//END if the statement is clean

    }//END demonstrationItem!=null
}

public synchronized void  destroyBranchBackwardChaining
                            (DemonstrationItem demonstrationItem,boolean isBase)
{
    if (demonstrationItem!=null)
    {
      //we see if it has descendants and we go through them
    if (demonstrationItem.downLink!=null)
    {
       int max=demonstrationItem.downLink.size();
       if (max>0)
       {
         for (int i=max-1;i>=0;i--)
         {
          destroyBranchBackwardChaining(demonstrationItem.downLink.get(i),false);
         }
         demonstrationItem.downLink.clear();//we destroy the links
       }
    }
    
    if(
        (demonstrationItem.type==DemonstrationConstants.NEW_STATEMENT)
        |(demonstrationItem.type==DemonstrationConstants.TARGET)
      )
    {
      String statementName=demonstrationItem.name;
    if(statementName!=null)
    {
   //we analyze if at the statement statementName are connected
   //variables that have gained content following the application of a a,e,p,v formula
   //and we destroy the content
      if(this.newStatementAndGeneratedVariableContent.get(statementName)!=null)
      {
         List<String> listVariablesWithContent=
                         this.newStatementAndGeneratedVariableContent.get(statementName);
         if(listVariablesWithContent!=null)
         {
         if (!listVariablesWithContent.isEmpty())
         {
           int max=listVariablesWithContent.size();
           for(int i=max-1;i>=0;i--)
           {
            String variable=listVariablesWithContent.get(i);

            if (this.newVariableAndContent.get(variable)!=null)
            {
               //we destroy the content associated with variable variable
               this.newVariableAndContent.remove(variable);
            }
           }
         }
         }
        //we delete the link between the statement and the variable list
        //who received content
         newStatementAndGeneratedVariableContent.remove(statementName);
      }
      //we analyze if at the statement statementName are connected
      //variables which resulted following application of a,e,p,v formula
      //and we destroy variables
      if(this.newStatementAndGeneratedVariable.get(statementName)!=null)
      {
         List<String> listNewVariables=
                         this.newStatementAndGeneratedVariable.get(statementName);
         if(listNewVariables!=null)
         {
         if (!listNewVariables.isEmpty())
         {
           int max=listNewVariables.size();
           for(int i=max-1;i>=0;i--)
           {
            String var=listNewVariables.get(i);
            if (this.newVariableAndClass.get(var)!=null)
            {
               //we destroy new variable var
               this.newVariableAndClass.remove(var);
            }
           }
         }
         }
        //we destroy also the link between statement and list of generated variables
        newStatementAndGeneratedVariable.remove(statementName);

      }



    //if is not base we destroy the statement
    if (demonstrationItem.type==DemonstrationConstants.NEW_STATEMENT)
    {
     //if demonstrationItem is a hub or repetition we mark
      if((demonstrationItem.markedAsAHub)|
          (demonstrationItem.repetition))
       {this.weDestroyedHubOrRepetition=true;}
    if(!isBase)
    {
     
     if (this.newStatementAndContentPhaseZero.containsKey(statementName))
    {
     this.newStatementAndContentPhaseZero.remove(statementName);
     //we destroy the link between new statement and his hub
     this.destroyLinkStatementAndHub(demonstrationItem);
     
          
     
    }
    }
    }


    }
    }
    }


}

public synchronized void  destroyBranchForwardChaining
                            (DemonstrationItem demonstrationItem,boolean isBase)
{
  if(demonstrationSource!=null)
  {
    if(demonstrationSource.downLink!=null)
  {
  if(demonstrationSource.downLink.contains(demonstrationItem))
  {
      int position2=demonstrationSource.downLink.indexOf(demonstrationItem);
      
    if(demonstrationItem.type==DemonstrationConstants.PROPER_HYPOTHESIS)
    {
     //we simply eliminate the proper hypothesis
    demonstrationSource.downLink.remove(demonstrationItem);
    }
     else if (demonstrationItem.type==DemonstrationConstants.NEW_STATEMENT)
     {
         if((!demonstrationItem.markedAsAHub)&(!demonstrationItem.repetition))
         {
         if (demonstrationItem.downLink!=null)
         {
          if(demonstrationItem.downLink.size()>0)
          {
            DemonstrationItem first= demonstrationItem.downLink.get(0);
            if(first.type==DemonstrationConstants.SIMPLE_AXIOM|
               first.type==DemonstrationConstants.SIMPLE_THEOREM   )
             {
               //we simply eliminate the simple axiom or theorem
               demonstrationSource.downLink.remove(demonstrationItem);
             }
             else if (first.type==DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM|
                      first.type==DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM)
             {
              if (first.downLink!=null)
              {
               if(first.downLink.size()>0)
               {
              //the list with proper statements or hypotheses on which 'sits'
              //an applied rule by forward chaining
             List <DemonstrationItem> list=new ArrayList<DemonstrationItem>();
             int n=first.downLink.size();
             for(int i=0;i<n;i++)
             {
              DemonstrationItem two=first.downLink.get(i);
              if (two.downLink!=null)
              {
               if(two.downLink.size()>0)
               {
                DemonstrationItem three=two.downLink.get(0);
                list.add(three);//we add to the list the item of level 3
               }
              }
             }
             //we destroy the branch
             demonstrationSource.downLink.remove(demonstrationItem);
             //we add starting up with the position position2
             int n2=list.size();
             for(int i=0;i<n2;i++)
             {
              demonstrationSource.downLink.add(position2+i,list.get(i));
             }

               }
              }
             }
          }
         }
         }
          else if(demonstrationItem.repetition)
          {
            this.numberOfFoundRepetitionsWithIndex=0;
            this.findRepetitionsWithIndex(this.demonstrationSource,
                                         demonstrationItem.numberOfOrderInCompressedProof);
            if(this.numberOfFoundRepetitionsWithIndex==1)
            {
              this.weFoundHubWithIndex=false;
              this.findHubWithIndexAndDemarcate(demonstrationSource,
                                        demonstrationItem.numberOfOrderInCompressedProof);
             }
             //we destroy the repetition
             demonstrationSource.downLink.remove(demonstrationItem);
          }
          else if(demonstrationItem.markedAsAHub)
          {
           //display that we cannot deleted an hub
            JOptionPane.showMessageDialog
                    (null,
            "We cannot delete a hub because it is linked to some repetitions!",
                     "Attention!",
                     JOptionPane.INFORMATION_MESSAGE
                     );
          }


     }
  }
  }
  }

}
public synchronized void  destroyRootForwardChaining()
{
  if(demonstrationSource!=null)
  {
   if(demonstrationSource.downLink!=null)
   {
    demonstrationSource.downLink.clear();
   }
      //we reset new variables and statements
      this.numberOfOrderNewStatements=0;
      this.newStatementAndContentPhaseZero.clear();
      this.newStatementAndHubStatement.clear();
      this.newStatementAndGeneratedVariable.clear();
      this.newStatementAndGeneratedVariableContent.clear();

      this.numberOfOderVariables=0;
      this.newVariableAndClass.clear();
      this.newVariableAndContent.clear();

      numberOfHubs=0;
      numberOfHubs2=0;
      listOfHubs.clear();
      listOfRepetitions.clear();
      //END reset
  }

}
//backward chaining
public boolean createNewBranchBackwardChaining
         (DemonstrationItem selectedDemonstrationItem,String foundName,int type)
{
 boolean   ok=false;
//the link between local variables in matching and their content
Map<String,List<ConstantAndVariable>>   variableAndContent=null;
variableAndContent= new java.util.HashMap<String,java.util.List<ConstantAndVariable>>();



Axiom xAxiom=null;
Theorem xTheorem=null;

if (S.axioms.containsKey(foundName))
{
if (S.availableAxioms.contains(foundName))
{

xAxiom=(Axiom)S.axioms.get(foundName);
}
else   {errorNewLine("The axiom is not visible in this part of the file");}
} else
        if (S.theorems.containsKey(foundName))
        {
        if (S.availableTheorems.contains(foundName))
        {

        xTheorem=(Theorem)S.theorems.get(foundName);
        }
        else {errorNewLine("The theorem is not visible in this part of the file");}
        }
//processing
if (xAxiom!=null)
{
Axiom xAxiom2=xAxiom;

//we verify if unify xAxiom2 with selectedDemonstrationItem
if (
    this.S.unifyTemplateWithBase
    (xAxiom2.items,selectedDemonstrationItem.items, variableAndContent)
   )
{
 ok=true;
 //we solve 'the equations' of variables from substitution variableAndContent
 variableAndContent=Source.resolveVariableContent(variableAndContent, this);
 //END solve'the equations'

      
 //we will attach at the selectedDemonstrationItem the backward chaining demonstration tree

//we attach at the demonstration item  the simple axiom or composed axiom
DemonstrationItem axiomOrTheoremItemSimpleOrComposed=new DemonstrationItem();

selectedDemonstrationItem.downLink=new java.util.ArrayList<DemonstrationItem>();
selectedDemonstrationItem.downLink.add(axiomOrTheoremItemSimpleOrComposed);
axiomOrTheoremItemSimpleOrComposed.aboveLink=selectedDemonstrationItem;//we create double pointer

if (xAxiom2.type==1)
{axiomOrTheoremItemSimpleOrComposed.type=DemonstrationConstants.SIMPLE_AXIOM;}//simple axiom
else if (xAxiom2.type==2)
{axiomOrTheoremItemSimpleOrComposed.type=DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM;

}//the axiom from composed axiom
axiomOrTheoremItemSimpleOrComposed.name=xAxiom2.name;
axiomOrTheoremItemSimpleOrComposed.undername=xAxiom2.name;
axiomOrTheoremItemSimpleOrComposed.referenceObject=xAxiom2;



//we attach at composed axiom the associated hypotheses and variables
axiomOrTheoremItemSimpleOrComposed.downLink=new java.util.ArrayList<DemonstrationItem>();

int hypothesesDimension=0,totalVariablesDimension=0,dimension=0,ii=0,jj=0,kk=0;

//we verify if the axiom has variables
if (xAxiom2.totalVariables!=null)
{
totalVariablesDimension=xAxiom2.totalVariables.size();
Source.generateNewVariables(xAxiom2.totalVariables, this, variableAndContent);

}
totalVariablesDimension=0;//we eliminate the variables from  the calculation
//we verify if the axiom has hypotheses
if (xAxiom2.hypotheses!=null)
{
hypothesesDimension=xAxiom2.hypotheses.size();
}
//we add number of variables with number of hypotheses
dimension=hypothesesDimension+totalVariablesDimension;

if ((dimension)>0)
{

//here we create an empty list with a given createNewBranchBackwardChaining
ii=0;DemonstrationItem xDemonstration=null;
do {
xDemonstration=new DemonstrationItem();
axiomOrTheoremItemSimpleOrComposed.downLink.add( xDemonstration );
xDemonstration.aboveLink=axiomOrTheoremItemSimpleOrComposed;
ii++;
}while(ii<dimension);
//we go through backward hypotheses and total variables of the xAxiom2
ii=dimension-1;jj=hypothesesDimension-1;kk=totalVariablesDimension-1;
Hypothesis h_x=null;Variable v_x=null;DemonstrationItem baseDemonstration=null;
xDemonstration=null;
do
{
if (ii>=0) { 
             xDemonstration=axiomOrTheoremItemSimpleOrComposed.downLink.get(ii);ii--;
           }
else  {
        xDemonstration=null;
      }
if (jj>=0) {
            h_x=xAxiom2.hypotheses.get(jj);jj--;
           }
  else      {  h_x=null;
             if (kk>=0) {
                         v_x=xAxiom2.totalVariables.get(kk);kk--;
                        }
                 else
                 {
                     v_x=null;
                 }
            }


if (xDemonstration!=null)
{
//we create the base which will have included a new statement
//or a syntactic statement of type wff,set,class
baseDemonstration=new DemonstrationItem();

//we establish double pointer
xDemonstration.downLink=new java.util.ArrayList<DemonstrationItem>();
xDemonstration.downLink.add(baseDemonstration);
baseDemonstration.aboveLink=xDemonstration;
//we modify the values of xDemonstration
if (h_x!=null)
{
xDemonstration.type=DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_AXIOM;//hypothesis from composed axiom
xDemonstration.name=h_x.name;
xDemonstration.referenceObject=h_x;
xDemonstration.numberOfOrderInCompressedProof=0;

}
else if (v_x!=null)
{
xDemonstration.type=DemonstrationConstants.VARIABLE;//variable
xDemonstration.name=v_x.variableText;
xDemonstration.variableClass=v_x.variableClass;
xDemonstration.numberOfOrderInCompressedProof=0;
}


}

xDemonstration=null; h_x=null; v_x=null;

}while (ii>=0);
//we go through the tree and generate new statements
xDemonstration=null;baseDemonstration=null;ii=00;
do {
xDemonstration=axiomOrTheoremItemSimpleOrComposed.downLink.get(ii);ii++;

if (xDemonstration!=null)
{
if (xDemonstration.downLink!=null) baseDemonstration=xDemonstration.downLink.get(0);//aici eroare
if (baseDemonstration!=null)
{

if (xDemonstration.type==DemonstrationConstants.VARIABLE)
{
     //we create a new statement with two items
     //first is a constant (wff,set,class), the second is the variable from xDemonstration
     ConstantAndVariable first=new ConstantAndVariable();
     ConstantAndVariable two= new ConstantAndVariable();
     first.constantOrVariable=1;
     first.constantOrVariableText=xDemonstration.variableClass;

     two.constantOrVariable=2;
     two.variableClass=xDemonstration.variableClass;
     two.constantOrVariableText=xDemonstration.name;

     List<ConstantAndVariable> statement0=baseDemonstration.items=new ArrayList<ConstantAndVariable>();
     statement0.add(0, first);
     statement0.add(1, two);
     List<ConstantAndVariable> statement1=null;
     //we insert content to the variables from statement created above
     statement1=Source.variablesContentInsertion(statement0, variableAndContent);
     //we generate the name of the new statement 
     //and we associate the name with the content of statement
     String newStatementName="$a"+this.numberOfOrderNewStatements;
     this.numberOfOrderNewStatements++;
     this.newStatementAndContentPhaseZero.put
                       (newStatementName,Source.copyTheListOfConstantAndVariable(statement1));

     baseDemonstration.items=statement1;//add the above processed statement

     //we establish the type of demonstration item
     baseDemonstration.type=DemonstrationConstants.NEW_STATEMENT;
     baseDemonstration.name=newStatementName;
     //we verify if baseDemonstration it is repeating in demonstration
     this.verifyRepetition(baseDemonstration);
     
     //we establish also the provisionallly items of the variable from xDemonstration
     if (variableAndContent!=null)
      {
          if(variableAndContent.containsKey(xDemonstration.name))
          {
            //we load the provisionally items with the content of the variable from the xDemonstration
            xDemonstration.provisionallyItems=variableAndContent.get(xDemonstration.name);
          }
      }
      
}
else if  (xDemonstration.type==DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_AXIOM)
{
  if (xDemonstration.referenceObject!=null)
  {
      Hypothesis hyp=(Hypothesis)xDemonstration.referenceObject;
     if (hyp.items!=null)
     {
     //we create a new statement by inserting variables content  in the
     //items of the hypothesis from composed axiom
     baseDemonstration.items=Source.variablesContentInsertion(hyp.items, variableAndContent);
     //we establish the type of demonstration item
     baseDemonstration.type=DemonstrationConstants.NEW_STATEMENT;
     //we generate the name of new statement and we associate the name with the content of the statement
     String newStatementName="$a"+this.numberOfOrderNewStatements;
     this.numberOfOrderNewStatements++;
     this.newStatementAndContentPhaseZero.put
                 (newStatementName,Source.copyTheListOfConstantAndVariable(baseDemonstration.items));
     baseDemonstration.name=newStatementName;
     
     }
   }
}



}else errorNewLine("Null pointer at base");
} else errorNewLine("Null pointer at composed axiom");



} while (ii<dimension);


}



}//END verification matching
//END 'axioms'
}
//theorems

else if (xTheorem!=null)
{
   
Theorem xTheorem2=xTheorem;

//we verify if xTheorem2 unify with selectedDemonstrationItem
if (
    this.S.unifyTemplateWithBase
    (xTheorem2.items,selectedDemonstrationItem.items, variableAndContent)
   )

{
 ok=true;
 //we solve 'the equations' of variables from the substitution variableAndContent
 variableAndContent=Source.resolveVariableContent(variableAndContent, this);
 //END solve 'equations'

 
 //we will attach at the selectedDemonstrationItem the tree of backward demonstration

//we attach to the demonstration item the simple or composed theorem
DemonstrationItem axiomOrTheoremItemSimpleOrComposed=new DemonstrationItem();

selectedDemonstrationItem.downLink=new java.util.ArrayList<DemonstrationItem>();
selectedDemonstrationItem.downLink.add(axiomOrTheoremItemSimpleOrComposed);
axiomOrTheoremItemSimpleOrComposed.aboveLink=selectedDemonstrationItem;//we create double pointer

if (xTheorem2.type==1)
{axiomOrTheoremItemSimpleOrComposed.type=DemonstrationConstants.SIMPLE_THEOREM;}//simple theorem
else if (xTheorem2.type==2)
{axiomOrTheoremItemSimpleOrComposed.type=DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM;

}
axiomOrTheoremItemSimpleOrComposed.name=xTheorem2.name;
axiomOrTheoremItemSimpleOrComposed.undername=xTheorem2.name;
axiomOrTheoremItemSimpleOrComposed.referenceObject=xTheorem2;


//we attach to the composed theorem the associated hypotheses and variables
axiomOrTheoremItemSimpleOrComposed.downLink=new java.util.ArrayList<DemonstrationItem>();

int hypothesesDimension=0,totalVariablesDimension=0,dimension=0,ii=0,jj=0,kk=0;

//we verify if the theorem has variables
if (xTheorem2.totalVariables!=null)
{
totalVariablesDimension=xTheorem2.totalVariables.size();
Source.generateNewVariables(xTheorem2.totalVariables, this, variableAndContent);
}
totalVariablesDimension=0;//we eliminate variables from calculation
//we verify if the theorem has hypotheses
if (xTheorem2.hypotheses!=null)
{
hypothesesDimension=xTheorem2.hypotheses.size();
}
//we add the number of variables with the number of hypotheses
dimension=hypothesesDimension+totalVariablesDimension;

if ((dimension)>0)
{

//here we create an empty list of given createNewBranchBackwardChaining
ii=0;DemonstrationItem xDemonstration=null;
do {
xDemonstration=new DemonstrationItem();
axiomOrTheoremItemSimpleOrComposed.downLink.add( xDemonstration );
xDemonstration.aboveLink=axiomOrTheoremItemSimpleOrComposed;
ii++;
}while(ii<dimension);
//we go through backward the hypotheses and the total variables of the xTheorem2
ii=dimension-1;jj=hypothesesDimension-1;kk=totalVariablesDimension-1;
Hypothesis h_x=null;Variable v_x=null;DemonstrationItem baseDemonstration=null;
xDemonstration=null;
do
{
if (ii>=0) { xDemonstration=axiomOrTheoremItemSimpleOrComposed.downLink.get(ii);ii--; }
else {xDemonstration=null;}
if (jj>=0) {h_x=xTheorem2.hypotheses.get(jj);jj--;}
else {  h_x=null;
if (kk>=0) {v_x=xTheorem2.totalVariables.get(kk);kk--;}
else {v_x=null;}
}


if (xDemonstration!=null)
{
//we creare the base which will have included a new statement
//or a syntactic statement of type: wff,set,class
baseDemonstration=new DemonstrationItem();

//we establish double pointer
xDemonstration.downLink=new java.util.ArrayList<DemonstrationItem>();
xDemonstration.downLink.add(baseDemonstration);
baseDemonstration.aboveLink=xDemonstration;
//we modify the values of xDemonstration
if (h_x!=null)
{
xDemonstration.type=DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_THEOREM;//hypothesis from compesed theorem
xDemonstration.name=h_x.name;
xDemonstration.referenceObject=h_x;
xDemonstration.numberOfOrderInCompressedProof=0;

}
else if (v_x!=null)
{
xDemonstration.type=DemonstrationConstants.VARIABLE;//variable
xDemonstration.name=v_x.variableText;
xDemonstration.variableClass=v_x.variableClass;
xDemonstration.numberOfOrderInCompressedProof=0;
}


}

xDemonstration=null; h_x=null; v_x=null;

}while (ii>=0);
//we go through the tree and generate the new statements
xDemonstration=null;baseDemonstration=null;ii=00;
do {
xDemonstration=axiomOrTheoremItemSimpleOrComposed.downLink.get(ii);ii++;

if (xDemonstration!=null)
{
if (xDemonstration.downLink!=null) baseDemonstration=xDemonstration.downLink.get(0);//aici eroare
if (baseDemonstration!=null)
{

if (xDemonstration.type==DemonstrationConstants.VARIABLE)
{
     //we create a new statement with two items
     //first is a constant(wff,set,clas),the second is a variable from xDemonstration
     ConstantAndVariable first=new ConstantAndVariable();
     ConstantAndVariable two= new ConstantAndVariable();
     first.constantOrVariable=1;
     first.constantOrVariableText=xDemonstration.variableClass;

     two.constantOrVariable=2;
     two.variableClass=xDemonstration.variableClass;
     two.constantOrVariableText=xDemonstration.name;

     List<ConstantAndVariable> statement0=baseDemonstration.items=new ArrayList<ConstantAndVariable>();
     statement0.add(0, first);
     statement0.add(1, two);
     List<ConstantAndVariable> statement1=null;
     //we insert content to the variables from the above statement
     statement1=Source.variablesContentInsertion(statement0, variableAndContent);
     //we generate the name of the new statement and associate the name with the statement content
     String newStatementName="$a"+this.numberOfOrderNewStatements;
     this.numberOfOrderNewStatements++;
     this.newStatementAndContentPhaseZero.put
                       (newStatementName,Source.copyTheListOfConstantAndVariable(statement1));

     baseDemonstration.items=statement1;//we add the above processed statement

     //we establish the type of demonstration item
     baseDemonstration.type=DemonstrationConstants.NEW_STATEMENT;
     baseDemonstration.name=newStatementName;
     //we verify if baseDemonstration is repeating in the demonstration
          
     if (variableAndContent!=null)
      {
          if(variableAndContent.containsKey(xDemonstration.name))
          {
            //we load the provisional items with the content of the variable from  xDemonstation
            xDemonstration.provisionallyItems=variableAndContent.get(xDemonstration.name);
          }
      }
}
else if  (xDemonstration.type==DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_THEOREM)
{
  if (xDemonstration.referenceObject!=null)
  {
      Hypothesis hyp=(Hypothesis)xDemonstration.referenceObject;
     if (hyp.items!=null)
     {
     //we create a new statement by inserting the content of the variables
     //in the hypothesis items from the composed theorem
     baseDemonstration.items=Source.variablesContentInsertion(hyp.items, variableAndContent);
     //we establish the type of the demonstration item
     baseDemonstration.type=DemonstrationConstants.NEW_STATEMENT;
     //we generate the name of the new statement and associate the name with statement content
     String newStatementName="$a"+this.numberOfOrderNewStatements;
     this.numberOfOrderNewStatements++;
     this.newStatementAndContentPhaseZero.put
                 (newStatementName,Source.copyTheListOfConstantAndVariable(baseDemonstration.items));
     baseDemonstration.name=newStatementName;
          
     }
   }
}



}else errorNewLine("Null pointer at the base");
} else errorNewLine("Null pointer at composed theorem");



} while (ii<dimension);


}




}
//END verification matching
//END 'theorems'

}

else if (type==DemonstrationConstants.PROPER_HYPOTHESIS)
{
 int index=-1;
 int max=this.baseTheorem.hypotheses.size();

 //FIND INDEX
 for (int i=0;i<max;i++)
 {
   if (foundName.equals(this.baseTheorem.hypotheses.get(i).name))
       {
       index=i;
       break;
       }
 }
 //END FIND INDEX

if (index>-1)
{
    Hypothesis xHypothesis=this.baseTheorem.hypotheses.get(index);
    //we verify if xHypothesis unify with selectedDemonstrationItem
if (
    this.S.unifyTemplateAssumptionWithBase
    (xHypothesis.items,selectedDemonstrationItem.items, variableAndContent)
   )

{
ok=true;
//we solve 'the equations' of variables from the  variableAndContent substitution
 variableAndContent=Source.resolveVariableContent(variableAndContent, this);
//END solve 'the equations'
 
//we will attach to the selectedDemonstrationItem the backward chaining demonstration tree

//we attach to the demonstration item the proper hypothesis

DemonstrationItem hypothesisItem=new DemonstrationItem();
selectedDemonstrationItem.downLink=new java.util.ArrayList<DemonstrationItem>();
selectedDemonstrationItem.downLink.add(hypothesisItem);
hypothesisItem.aboveLink=selectedDemonstrationItem;//we create double pointer

hypothesisItem.type=DemonstrationConstants.PROPER_HYPOTHESIS;
hypothesisItem.name=xHypothesis.name;
hypothesisItem.undername=xHypothesis.name;
hypothesisItem.referenceObject=xHypothesis;
}
}

}
//END

else if (type==DemonstrationConstants.PROPER_VARIABLE)
{
 int index=-1;
 int max=this.baseTheorem.totalVariables.size();
 
 //FIND INDEX
 for (int i=0;i<max;i++)
 {
   if (foundName.equals(this.baseTheorem.totalVariables.get(i).variableText))
       {
       index=i;
       break;
       }
 }
 //END FIND INDEX

if (index>-1)
{
 Variable iVariable=this.baseTheorem.totalVariables.get(index);
 //we create a list with one ConstantAndVariable item
 List<ConstantAndVariable> listOfVariables=new ArrayList<ConstantAndVariable>();
 ConstantAndVariable cv=new ConstantAndVariable();
 cv.constantOrVariable=2;//variable
 cv.constantOrVariableText=iVariable.variableText;
 cv.variableClass=iVariable.variableClass;
 listOfVariables.add(cv);//we add the newly created item
//we verify if listOfVariables unify with selectedDemonstrationItem
if (
    this.S.unifyTemplateVariableWithBase
    (listOfVariables,selectedDemonstrationItem.items, variableAndContent)
   )

{
 ok=true;
//we solve 'the equations' of variables from the variableAndContent substitution
 variableAndContent=Source.resolveVariableContent(variableAndContent, this);
 //END solve 'equations'

//we will attach to the selectedDemonstrationItem the backward chaining demonstration tree

//we attach to the demonstration item the proper variable
DemonstrationItem variableItem=new DemonstrationItem();
selectedDemonstrationItem.downLink=new java.util.ArrayList<DemonstrationItem>();
selectedDemonstrationItem.downLink.add(variableItem);
variableItem.aboveLink=selectedDemonstrationItem;//we create double pointer

variableItem.type=DemonstrationConstants.PROPER_VARIABLE;
variableItem.name=iVariable.variableText;
variableItem.variableClass=iVariable.variableClass;
variableItem.items=listOfVariables;//we tie up the newly created list

}
}
}
//END
return ok;
}
//forward chaining function
public boolean createNewBranchForwardChaining
   (String foundName,int type)
{
 boolean   ok=true;
//link between local variables from matching and their content
Map<String,List<ConstantAndVariable>>   variableAndContent=null;
variableAndContent= new java.util.HashMap<String,java.util.List<ConstantAndVariable>>();

Axiom a_x=null;
Theorem t_x=null;

if (S.axioms.containsKey(foundName))
{
if (S.availableAxioms.contains(foundName))
{

a_x=(Axiom)S.axioms.get(foundName);
}
else   {errorNewLine("The axiom is not visible in this part of the file");}
} else
        if (S.theorems.containsKey(foundName))
        {
        if (S.availableTheorems.contains(foundName))
        {

        t_x=(Theorem)S.theorems.get(foundName);
        }
        else {errorNewLine("The theorem is not visible in this part of the file");}
        }


//processing
if (a_x!=null)
{
int n=0;
int position0=-1;
int max=-1;
Axiom xAxiom=a_x;
//we establish position0 to the demonstration source
if(this.selectedDemonstrationItem0!=null)
{
 if (this.demonstrationSource.downLink!=null)
      {
       position0= this.demonstrationSource.downLink.indexOf(selectedDemonstrationItem0);
       max=this.demonstrationSource.downLink.size();
      }
}
//if target is marked we check if the axiom assertion unifies with the target

if(targetItem!=null)
{
ok=this.S.unifyTemplateWithBase
      (
      xAxiom.items,
      targetItem.items,
      variableAndContent
      );
if(!ok){return false;}
}

// check if the xAxiom items are unified
// with selectedDemonstrationItem and other n-1 items that it finds
// in stack after selectedDemonstrationItem
if  (xAxiom.type==2)
{
  
  if (xAxiom.hypotheses!=null)
  {
    n=xAxiom.hypotheses.size();
  }
  if (n>0)
  {
    //we check if in stack exist n items after selectedDemonstrationItem
    //after which we check if the hypotheses of xAxiom unify with
    //n items found in stack after selectedDemonstratonItem
       
    if ((position0+n)>max)
    {
        ok=false;
    }

     if (ok)
    {
      if ((selectedDemonstrationItem0.type==DemonstrationConstants.NEW_STATEMENT)|
          (selectedDemonstrationItem0.type==DemonstrationConstants.NEW_FORWARD_STATEMENT)
         )
      {

      ok=this.S.unifyTemplateWithBase
     (
      xAxiom.hypotheses.get(0).items,
      selectedDemonstrationItem0.items,
      variableAndContent);

      }

 else if ((selectedDemonstrationItem0.type==DemonstrationConstants.PROPER_HYPOTHESIS)|
          (selectedDemonstrationItem0.type==DemonstrationConstants.PROPER_HYPOTHESIS)
         )
      {
        Hypothesis yHypothesis=(Hypothesis)selectedDemonstrationItem0.referenceObject;
      ok=this.S.unifyTemplateWithBase
     (
      xAxiom.hypotheses.get(0).items,
      yHypothesis.items,
      variableAndContent);

      }

     if (ok)
     {
      if ((n-1)>0)
      { int i1=1;

          do
          {
              //error out of the bounds index 2 size 2
           DemonstrationItem stackItemPositionI1=
                     demonstrationSource.downLink.get(position0+i1);
          if(stackItemPositionI1!=null)
          {
           if ((stackItemPositionI1.type==DemonstrationConstants.NEW_STATEMENT)|
              (stackItemPositionI1.type==DemonstrationConstants.NEW_FORWARD_STATEMENT)
               )
              {

              ok=this.S.unifyTemplateWithBase
             (
              xAxiom.hypotheses.get(i1).items,
              stackItemPositionI1.items,
              variableAndContent);

              }

         else if ((stackItemPositionI1.type==DemonstrationConstants.PROPER_HYPOTHESIS)|
                  (stackItemPositionI1.type==DemonstrationConstants.PROPER_HYPOTHESIS)
                 )
              {
                Hypothesis yHypothesis=(Hypothesis)stackItemPositionI1.referenceObject;
              ok=this.S.unifyTemplateWithBase
             (
              xAxiom.hypotheses.get(i1).items,
              yHypothesis.items,
              variableAndContent);

              }
            }
           
          if (!ok) break;
          if (i1==(n-1)) break;
          i1++;
          }
          while (true);

        
         }

      
      if (ok)
            {
         //if it is ok
         //we memorize the target items where it will apply
         //composed axioma or composed theorem
         stackSelectedItems.clear();
         stackItemsCursor=0;
            for (int j=position0;j<=(position0+n-1);j++)
            {
             //we add the item in stackSelectedItems
             stackSelectedItems.add(this.demonstrationSource.downLink.get(j));

            }
            //reverse the list and remove the links
           for (int j=(position0+n-1);j>=position0;j--)
           {
             //remove the item from subordinate of demonstrationSource
             this.demonstrationSource.downLink.remove(j);
            }


            }





      }
     }
    }
    }
  

//if the axiom is composed and the above unifications have been achieved
//or if it's simply a simple axiom
//we're going in here
if (ok)
{

 //we solve 'the equations' of variables from substitution variableAndContent
 variableAndContent=Source.resolveVariableContent(variableAndContent, this);
 //END solve 'the equations'
 
 //highlight variables that have no content
 Source.generateNewVariablesInGeneral(xAxiom.totalVariables, this, variableAndContent);

 DemonstrationItem newDemonstrationItem=null;

 if(targetItem==null)
 {
  //we create a new demonstration item and
  //we attach to him other items of demonstration
  //and we create content
  newDemonstrationItem= new DemonstrationItem();

 newDemonstrationItem.items=
                Source.variablesContentInsertion(xAxiom.items, variableAndContent);
  //we establish the type of the demonstration item
 newDemonstrationItem.type=DemonstrationConstants.NEW_STATEMENT;
 //we generate the name of the new statement
 //and associate the name with the content of the statement
 String newStatementName1="$a"+this.numberOfOrderNewStatements;
 this.numberOfOrderNewStatements++;
 this.newStatementAndContentPhaseZero.put
               (newStatementName1,Source.copyTheListOfConstantAndVariable(newDemonstrationItem.items));
 newDemonstrationItem.name=newStatementName1;
 
  //we add on position position0+1 the new demonstrationItem
  if (demonstrationSource.downLink==null)
  {
      demonstrationSource.downLink=new ArrayList<DemonstrationItem>();
  }

    if(position0>-1)
    {
      demonstrationSource.downLink.add(position0,newDemonstrationItem);
    }
    else
    {
     demonstrationSource.downLink.add(newDemonstrationItem);
    }
}
 else
 {
   newDemonstrationItem=targetItem;
 }

DemonstrationItem axiomOrTheoremItemSimpleOrComposed=new DemonstrationItem();

newDemonstrationItem.downLink=new java.util.ArrayList<DemonstrationItem>();
newDemonstrationItem.downLink.add(axiomOrTheoremItemSimpleOrComposed);
axiomOrTheoremItemSimpleOrComposed.aboveLink=newDemonstrationItem;//we create double pointer

if (xAxiom.type==1)
{axiomOrTheoremItemSimpleOrComposed.type=DemonstrationConstants.SIMPLE_AXIOM;}//simple axiom
else if (xAxiom.type==2)
{axiomOrTheoremItemSimpleOrComposed.type=DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM;

}//axiom from composed axiom
axiomOrTheoremItemSimpleOrComposed.name=xAxiom.name;
axiomOrTheoremItemSimpleOrComposed.undername=xAxiom.name;
axiomOrTheoremItemSimpleOrComposed.referenceObject=xAxiom;

//we attach to the composed axiom hypotheses and associated variables
axiomOrTheoremItemSimpleOrComposed.downLink=new java.util.ArrayList<DemonstrationItem>();

int dimension=0,ii=0;

//we check if the axiom has hypotheses
if (xAxiom.hypotheses!=null)
{
dimension=xAxiom.hypotheses.size();
}

if ((dimension)>0)
{

//here we create an empty list of given dimension
ii=0;DemonstrationItem xDemonstration=null;
do {
xDemonstration=new DemonstrationItem();
axiomOrTheoremItemSimpleOrComposed.downLink.add( xDemonstration );
xDemonstration.aboveLink=axiomOrTheoremItemSimpleOrComposed;
ii++;
}while(ii<dimension);
//we go back to the hypotheses of xAxiom
ii=dimension-1;
stackItemsCursor=stackSelectedItems.size()-1;

DemonstrationItem baseDemonstration=null;
xDemonstration=null; Hypothesis h_x=null;


do
{
if (ii>=0) {
            xDemonstration=axiomOrTheoremItemSimpleOrComposed.downLink.get(ii);
            h_x=xAxiom.hypotheses.get(ii);
            ii--;
           }
    else {
          xDemonstration=null;
         }

if (xDemonstration!=null)
{
 if(h_x!=null)
{
xDemonstration.type=DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_AXIOM;//hypothesis from composed axiom
xDemonstration.name=h_x.name;
xDemonstration.referenceObject=h_x;
xDemonstration.numberOfOrderInCompressedProof=0;
}
//we create a base which will have included a new statement
//or a syntactic statement of type wff,set,class
baseDemonstration= stackSelectedItems.get(stackItemsCursor);
stackItemsCursor--;

//we establish double pointer
xDemonstration.downLink=new java.util.ArrayList<DemonstrationItem>();
xDemonstration.downLink.add(baseDemonstration);
baseDemonstration.aboveLink=xDemonstration;
//we modify the values of xDemonstration
}
xDemonstration=null;

}while (ii>=0);

// walk through the tree
// generate the new statements

xDemonstration=null;baseDemonstration=null;ii=00;


}



}//END verification matching
//END 'axioms'
}


//theorems
else if (t_x!=null)
{
  int n=0;
  int position0=-1;
  int max=-1;
Theorem xTheorem=t_x;
//we establish first the position of selectedDemonstrationItem to the demonstrationSource
if(this.selectedDemonstrationItem0!=null)
{
if (this.demonstrationSource.downLink!=null)
      {
       position0= this.demonstrationSource.downLink.indexOf(selectedDemonstrationItem0);
       max=this.demonstrationSource.downLink.size();
      }
}

if(targetItem!=null)
{
ok=this.S.unifyTemplateWithBase
      (
      xTheorem.items,
      targetItem.items,
      variableAndContent
      );
if(!ok){return false;}
}



//we verify if unify the items of xTheorem
//with selectedDemonstrationItem and other n-1 items which it finds
//in stack after selectedDemonstrationItem
if  (xTheorem.type==2)
{

  if (xTheorem.hypotheses!=null)
  {
    n=xTheorem.hypotheses.size();
  }
  if (n>0)
  {
    //we verify if in stack exist n items after selectedDemonstrationItem
    //after which we verify if the hypotheses of xTheorem unify with
    //n item found in stack after selectedDemonstrationItem
    if ((position0+n)>max)
    {
        ok=false;
    }

     if (ok)
    {
      if ((selectedDemonstrationItem0.type==DemonstrationConstants.NEW_STATEMENT)|
          (selectedDemonstrationItem0.type==DemonstrationConstants.NEW_FORWARD_STATEMENT)
         )
      {

      ok=this.S.unifyTemplateWithBase
     (
      xTheorem.hypotheses.get(0).items,
      selectedDemonstrationItem0.items,
      variableAndContent);

      }

 else if ((selectedDemonstrationItem0.type==DemonstrationConstants.PROPER_HYPOTHESIS)|
          (selectedDemonstrationItem0.type==DemonstrationConstants.PROPER_HYPOTHESIS)
         )
      {
        Hypothesis yHypothesis=(Hypothesis)selectedDemonstrationItem0.referenceObject;
      ok=this.S.unifyTemplateWithBase
     (
      xTheorem.hypotheses.get(0).items,
      yHypothesis.items,
      variableAndContent);

      }

     if (ok)
     {
      if ((n-1)>0)
      { int i1=1;
          do
          {
          DemonstrationItem stackItemPositionI1=
                     demonstrationSource.downLink.get(position0+i1);
          if(stackItemPositionI1!=null)
          {
           if ((stackItemPositionI1.type==DemonstrationConstants.NEW_STATEMENT)|
              (stackItemPositionI1.type==DemonstrationConstants.NEW_FORWARD_STATEMENT)
               )
              {

              ok=this.S.unifyTemplateWithBase
             (
              xTheorem.hypotheses.get(i1).items,
              stackItemPositionI1.items,
              variableAndContent);

              }

         else if ((stackItemPositionI1.type==DemonstrationConstants.PROPER_HYPOTHESIS)|
                  (stackItemPositionI1.type==DemonstrationConstants.PROPER_HYPOTHESIS)
                 )
              {
                Hypothesis yHypothesis=(Hypothesis)stackItemPositionI1.referenceObject;
              ok=this.S.unifyTemplateWithBase
             (
              xTheorem.hypotheses.get(i1).items,
              yHypothesis.items,
              variableAndContent);

              }
            }
          if (!ok) break;
          if (i1==n-1) break;
          i1++;
          }
          while (true);

        
         }

      if (ok)
            {
        //if it is ok
        //we memorize the target items where it will apply
        //composed axioma or composed theorem
         stackSelectedItems.clear();
         stackItemsCursor=0;
             for (int j=position0;j<=(position0+n-1);j++)
            {
             //we add the item to the stackSelectedItems---error out of bounds
             stackSelectedItems.add(this.demonstrationSource.downLink.get(j));

            }
            //we go back the list and remove the links
           for (int j=(position0+n-1);j>=position0;j--)
           {
            //we remove the item from subordination of demonstrationSource
             this.demonstrationSource.downLink.remove(j);
            }
            }




      }
     }
    }
    }


// if it is the complex theorem and the above unifications have been achieved
// or if it is simply the simple theorem
// we're going in here
if (ok)
{

 //we solve 'the equation' of variables from variableAndContent substitution
 variableAndContent=Source.resolveVariableContent(variableAndContent, this);
 //END solve 'the equations'

 //we highlight the variables that do not have content
 Source.generateNewVariablesInGeneral(xTheorem.totalVariables, this, variableAndContent);

 DemonstrationItem newDemonstrationItem=null;

  if (targetItem==null)
 {
 //we create a demonstration item and
 //we attach at him other demonstration items
 //and we create content
 newDemonstrationItem= new DemonstrationItem();

 newDemonstrationItem.items=
                Source.variablesContentInsertion(xTheorem.items, variableAndContent);
  //we establish the type of demonstration item
 newDemonstrationItem.type=DemonstrationConstants.NEW_STATEMENT;
 //we generate the name of new statement and we associate the name with statement content
 String newStatementName1="$a"+this.numberOfOrderNewStatements;
 this.numberOfOrderNewStatements++;
 this.newStatementAndContentPhaseZero.put
               (newStatementName1,Source.copyTheListOfConstantAndVariable(newDemonstrationItem.items));
 newDemonstrationItem.name=newStatementName1;
 
   //we add on position position0+1 the new demonstration item
  if (demonstrationSource.downLink==null)
  {
      demonstrationSource.downLink=new ArrayList<DemonstrationItem>();
  }

 if(position0>-1)
    {
      demonstrationSource.downLink.add(position0,newDemonstrationItem);
    }
 else
    {
     demonstrationSource.downLink.add(newDemonstrationItem);
    }
}
 else
 {
  newDemonstrationItem=targetItem;
 }
 
DemonstrationItem axiomOrTheoremItemSimpleOrComposed=new DemonstrationItem();

newDemonstrationItem.downLink=new java.util.ArrayList<DemonstrationItem>();
newDemonstrationItem.downLink.add(axiomOrTheoremItemSimpleOrComposed);
axiomOrTheoremItemSimpleOrComposed.aboveLink=newDemonstrationItem;//we create double pointer

if (xTheorem.type==1)
{axiomOrTheoremItemSimpleOrComposed.type=DemonstrationConstants.SIMPLE_THEOREM;}//simple theorem
else if (xTheorem.type==2)
{axiomOrTheoremItemSimpleOrComposed.type=DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM;

}//theorem from composed theorem
axiomOrTheoremItemSimpleOrComposed.name=xTheorem.name;
axiomOrTheoremItemSimpleOrComposed.undername=xTheorem.name;
axiomOrTheoremItemSimpleOrComposed.referenceObject=xTheorem;

//we attach to the composed theorem the associated hypotheses and variables
axiomOrTheoremItemSimpleOrComposed.downLink=new java.util.ArrayList<DemonstrationItem>();

int dimension=0,ii=0;

//we verify if the theorem has hypotheses
if (xTheorem.hypotheses!=null)
{
dimension=xTheorem.hypotheses.size();
}


if ((dimension)>0)
{
//here we create an empty list of given dimension
ii=0;DemonstrationItem xDemonstration=null;
do {
xDemonstration=new DemonstrationItem();
axiomOrTheoremItemSimpleOrComposed.downLink.add( xDemonstration );
xDemonstration.aboveLink=axiomOrTheoremItemSimpleOrComposed;
ii++;
}while(ii<dimension);

//we go back through the hypotheses and total variables of xTheorem
ii=dimension-1;
stackItemsCursor=stackSelectedItems.size()-1;
Hypothesis h_x=null;DemonstrationItem baseDemonstration=null;
xDemonstration=null;
do
{
if (ii>=0) { 
             xDemonstration=axiomOrTheoremItemSimpleOrComposed.downLink.get(ii);
             h_x=xTheorem.hypotheses.get(ii);
             ii--;
           }
else {xDemonstration=null;}

if (xDemonstration!=null)
{
if (h_x!=null)
{
xDemonstration.type=DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_AXIOM;//ipoteza din axioma compusa
xDemonstration.name=h_x.name;
xDemonstration.referenceObject=h_x;
xDemonstration.numberOfOrderInCompressedProof=0;
}
//we create a base which will have included a new statement
//or a syntactic statement of type:wff,set,class
baseDemonstration=stackSelectedItems.get(stackItemsCursor);
stackItemsCursor--;

//we establish double pointer
xDemonstration.downLink=new java.util.ArrayList<DemonstrationItem>();
xDemonstration.downLink.add(baseDemonstration);
baseDemonstration.aboveLink=xDemonstration;
//we modify the values of xDemonstration
}

xDemonstration=null; h_x=null;

}while (ii>=0);

//we go through the tree
//we generate the new statements

}
}//END verification matching
//END 'theorems'

}

else if (type==DemonstrationConstants.PROPER_HYPOTHESIS)
{
 int index=-1;
 int max=this.baseTheorem.hypotheses.size();

 //FIND INDEX
 for (int i=0;i<max;i++)
 {
   if (foundName.equals(this.baseTheorem.hypotheses.get(i).name))
       {
       index=i;
       break;
       }
 }
 //END FIND INDEX

if (index>-1)
{
    Hypothesis xHypothesis=this.baseTheorem.hypotheses.get(index);
    //we verify if xHypothesis unify with selectedDemonstrationItem
 ok=true;
//the demonstration source is the target item that must be demonstrated
//we will attach to the demonstrationSource the proper hypothesis demonstration item

DemonstrationItem hypothesisItem=new DemonstrationItem();
int position0=-1;

if (demonstrationSource!=null)
{
   if(this.selectedDemonstrationItem0!=null)
   {
    if (demonstrationSource.downLink!=null)
    {
     
     position0=demonstrationSource.downLink.indexOf(selectedDemonstrationItem0);
    }
   }

}
if(demonstrationSource.downLink==null)
      {
         demonstrationSource.downLink=new ArrayList<DemonstrationItem>();
      }

//we insert the hypothesis immediate after selectedDemonstrationItem
if (position0>-1)
    {
     demonstrationSource.downLink.add(position0+1,hypothesisItem);
    }
   else
    {
      demonstrationSource.downLink.add(hypothesisItem);
    }

hypothesisItem.aboveLink=demonstrationSource;//we create double pointer

hypothesisItem.type=DemonstrationConstants.PROPER_HYPOTHESIS;
hypothesisItem.name=xHypothesis.name;
hypothesisItem.undername=xHypothesis.name;
hypothesisItem.referenceObject=xHypothesis;
}


}
//END

return ok;
}

//find rule that fits and apply rule forward chaining
public boolean findRuleForwardChaining
   (
    List<ConstantAndVariable> target,
    int numberOfHypotheses)
{
 boolean   ok=false;
//link between local variables from matching and their content
Map<String,List<ConstantAndVariable>>   variableAndContent=null;
variableAndContent= new java.util.HashMap<String,java.util.List<ConstantAndVariable>>();

int position0=-1;
int max=-1;
//we establish position0 to the demonstration source
if(this.selectedDemonstrationItem0!=null)
{

 if (this.demonstrationSource.downLink!=null)
      {
       position0= this.demonstrationSource.downLink.
               indexOf(this.selectedDemonstrationItem0);
       max=this.demonstrationSource.downLink.size();
      }
}
if(numberOfHypotheses>0)
{
  if (position0==-1) {return false;}
}

if (numberOfHypotheses<0) {return false;}

if(numberOfHypotheses>0)
{
if ((position0+numberOfHypotheses)>max)
    {
        return false;
    }
}
if(this.targetItem!=null)
{
 target=this.targetItem.items;
}
SyntacticItem targetTree=this.S.generateSyntacticTree(target);

List<List<ConstantAndVariable>> listOfBaseStatements=
                                    new ArrayList<List<ConstantAndVariable>>();
List<SyntacticItem> listOfBaseTreeStatements=new ArrayList<SyntacticItem>();

//we memorize the base statements
//and generate(and memorize) their syntactic trees
  if(numberOfHypotheses>0)
  {
  int i1=0;
  do
  {

   DemonstrationItem stackItemPositionI1=
             demonstrationSource.downLink.get(position0+i1);
  if(stackItemPositionI1!=null)
  {
   if ((stackItemPositionI1.type==DemonstrationConstants.NEW_STATEMENT)|
      (stackItemPositionI1.type==DemonstrationConstants.NEW_FORWARD_STATEMENT)
       )
      {
          listOfBaseStatements.add(stackItemPositionI1.items);
          listOfBaseTreeStatements.add
             (this.S.generateSyntacticTree(stackItemPositionI1.items));
      }

 else if ((stackItemPositionI1.type==DemonstrationConstants.PROPER_HYPOTHESIS)|
          (stackItemPositionI1.type==DemonstrationConstants.PROPER_HYPOTHESIS)
         )
      {
        Hypothesis yHypothesis=(Hypothesis)stackItemPositionI1.referenceObject;
        listOfBaseStatements.add(yHypothesis.items);
        listOfBaseTreeStatements.add
                      (this.S.generateSyntacticTree(yHypothesis.items));
      }
    }

  
  if (i1==(numberOfHypotheses-1)) break;
  i1++;
  }
  while (true);
  }



Axiom xAxiom=null;
Theorem xTheorem=null;
Application0 a=(Application0)Application.getInstance();
//syntactic tree of the target afirmation


 
//here we have axioms


       if(a.frame0.source1.axioms!=null)
        {
          Iterator<String> it=a.frame0.source1.axioms.keySet().iterator();
           while(it.hasNext())
           {
            String name=it.next();
            xAxiom=(Axiom)a.frame0.source1.axioms.get(name);
            String firstCV=xAxiom.items.get(0).constantOrVariableText;
            int n=xAxiom.hypotheses.size();
            if((firstCV.equals("|-"))&(n==numberOfHypotheses))
            {
             variableAndContent.clear();
             ok=true;
             ok=this.S.unifyTemplateWithTreeBase(xAxiom.items,
                     target,
                     targetTree,
                     variableAndContent);
             if (ok)
             {
             int max1=listOfBaseStatements.size();
             for(int i=0;i<max1;i++)
             {
              List<ConstantAndVariable> iItem=listOfBaseStatements.get(i);
              SyntacticItem iTree=listOfBaseTreeStatements.get(i);
              List<ConstantAndVariable> iHyp=xAxiom.hypotheses.get(i).items;
              ok=this.S.unifyTemplateWithTreeBase(iHyp,
                      iItem,
                      iTree,
                      variableAndContent);
              if(!ok){break;}

             }
             }
            }

            if(ok){break;}
            }
        }

       

      if (ok)
            {
          if(numberOfHypotheses>0)
          {
         //if it is ok
         //we memorize the target items where it will apply
         //composed axioma or composed theorem
         stackSelectedItems.clear();
         stackItemsCursor=0;
            for (int j=position0;j<=(position0+numberOfHypotheses-1);j++)
            {
             //we add the item in stackSelectedItems
             stackSelectedItems.add(this.demonstrationSource.downLink.get(j));

            }
            //reverse the list and remove the links
           for (int j=(position0+numberOfHypotheses-1);j>=position0;j--)
           {
             //remove the item from subordinate of demonstrationSource
             this.demonstrationSource.downLink.remove(j);
            }
            }

            }

//if the axiom is composed and the above unifications have been achieved
//or if it's simply a simple axiom
//we're going in here
if (ok)
{

 //we solve 'the equations' of variables from substitution variableAndContent
 variableAndContent=Source.resolveVariableContent(variableAndContent, this);
 //END solve 'the equations'

 //highlight variables that have no content
 Source.generateNewVariablesInGeneral(xAxiom.totalVariables, this, variableAndContent);
 DemonstrationItem newDemonstrationItem=null;
  if(this.targetItem==null)
  {
  //we create a new demonstration item and
  //we attach to him other items of demonstration
  //and we paste the  content from the target
 newDemonstrationItem= new DemonstrationItem();

 newDemonstrationItem.items=
                Source.copyTheListOfConstantAndVariable(target);
  //we establish the type of the demonstration item
 newDemonstrationItem.type=DemonstrationConstants.NEW_STATEMENT;
 //we generate the name of the new statement
 //and associate the name with the content of the statement
 String newStatementName1="$a"+this.numberOfOrderNewStatements;
 this.numberOfOrderNewStatements++;
 this.newStatementAndContentPhaseZero.put
               (newStatementName1,Source.copyTheListOfConstantAndVariable(newDemonstrationItem.items));
 newDemonstrationItem.name=newStatementName1;

  //we add on position position0+1 the new demonstrationItem
  if (demonstrationSource.downLink==null)
  {
      demonstrationSource.downLink=new ArrayList<DemonstrationItem>();
  }

    if(position0>-1)
    {
      demonstrationSource.downLink.add(position0,newDemonstrationItem);
    }
    else
    {
     demonstrationSource.downLink.add(newDemonstrationItem);
    }

 }
 else
 {
   newDemonstrationItem=targetItem;
 }
DemonstrationItem axiomOrTheoremItemSimpleOrComposed=new DemonstrationItem();

newDemonstrationItem.downLink=new java.util.ArrayList<DemonstrationItem>();
newDemonstrationItem.downLink.add(axiomOrTheoremItemSimpleOrComposed);
axiomOrTheoremItemSimpleOrComposed.aboveLink=newDemonstrationItem;//we create double pointer

if (xAxiom.type==1)
{axiomOrTheoremItemSimpleOrComposed.type=DemonstrationConstants.SIMPLE_AXIOM;}//simple axiom
else if (xAxiom.type==2)
{axiomOrTheoremItemSimpleOrComposed.type=DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM;

}//axiom from composed axiom
axiomOrTheoremItemSimpleOrComposed.name=xAxiom.name;
axiomOrTheoremItemSimpleOrComposed.undername=xAxiom.name;
axiomOrTheoremItemSimpleOrComposed.referenceObject=xAxiom;

//we attach to the composed axiom hypotheses and associated variables
axiomOrTheoremItemSimpleOrComposed.downLink=new java.util.ArrayList<DemonstrationItem>();

int dimension=0,ii=0;

//we check if the axiom has hypotheses
if (xAxiom.hypotheses!=null)
{
dimension=xAxiom.hypotheses.size();
}

if ((dimension)>0)
{

//here we create an empty list of given dimension
ii=0;DemonstrationItem xDemonstration=null;
do {
xDemonstration=new DemonstrationItem();
axiomOrTheoremItemSimpleOrComposed.downLink.add( xDemonstration );
xDemonstration.aboveLink=axiomOrTheoremItemSimpleOrComposed;
ii++;
}while(ii<dimension);
//we go back to the hypotheses of xAxiom
ii=dimension-1;
stackItemsCursor=stackSelectedItems.size()-1;

DemonstrationItem baseDemonstration=null;
xDemonstration=null; Hypothesis h_x=null;


do
{
if (ii>=0) {
            xDemonstration=axiomOrTheoremItemSimpleOrComposed.downLink.get(ii);
            h_x=xAxiom.hypotheses.get(ii);
            ii--;
           }
    else {
          xDemonstration=null;
         }

if (xDemonstration!=null)
{
 if(h_x!=null)
{
xDemonstration.type=DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_AXIOM;//hypothesis from composed axiom
xDemonstration.name=h_x.name;
xDemonstration.referenceObject=h_x;
xDemonstration.numberOfOrderInCompressedProof=0;
}
//we create a base which will have included a new statement
//or a syntactic statement of type wff,set,class
baseDemonstration= stackSelectedItems.get(stackItemsCursor);
stackItemsCursor--;

//we establish double pointer
xDemonstration.downLink=new java.util.ArrayList<DemonstrationItem>();
xDemonstration.downLink.add(baseDemonstration);
baseDemonstration.aboveLink=xDemonstration;
//we modify the values of xDemonstration
}
xDemonstration=null;

}while (ii>=0);

// walk through the tree
// generate the new statements

xDemonstration=null;baseDemonstration=null;ii=00;


}


return true;
}//END verification matching
//END 'axioms'



//here we have theorems
ok=false;

       if(a.frame0.source1.theorems!=null)
        {
          Iterator<String> it=a.frame0.source1.theorems.keySet().iterator();
           while(it.hasNext())
           {
            String name=it.next();
             xTheorem=(Theorem)a.frame0.source1.theorems.get(name);
             String firstCV=xTheorem.items.get(0).constantOrVariableText;
            int n=xTheorem.hypotheses.size();
            if((firstCV.equals("|-"))&(n==numberOfHypotheses))
            {
             variableAndContent.clear();
             ok=true;
             ok=this.S.unifyTemplateWithTreeBase(xTheorem.items,
                     target,
                     targetTree,
                     variableAndContent);
             if (ok)
             {
             int max1=listOfBaseStatements.size();
             for(int i=0;i<max1;i++)
             {
              List<ConstantAndVariable> iItem=listOfBaseStatements.get(i);
              SyntacticItem iTree=listOfBaseTreeStatements.get(i);
              List<ConstantAndVariable> iHyp=xTheorem.hypotheses.get(i).items;
              ok=this.S.unifyTemplateWithTreeBase(iHyp,
                      iItem,
                      iTree,
                      variableAndContent);
              if(!ok){break;}

             }
             }
            }

            if(ok){break;}


           }
        }



  
    

     
     
      

      if (ok)
            {
          if(numberOfHypotheses>0)
           {
        //if it is ok
        //we memorize the target items where it will apply
        //composed axioma or composed theorem
         stackSelectedItems.clear();
         stackItemsCursor=0;
             for (int j=position0;j<=(position0+numberOfHypotheses-1);j++)
            {
             //we add the item to the stackSelectedItems---error out of bounds
             stackSelectedItems.add(this.demonstrationSource.downLink.get(j));

            }
            //we go back the list and remove the links
           for (int j=(position0+numberOfHypotheses-1);j>=position0;j--)
           {
            //we remove the item from subordination of demonstrationSource
             this.demonstrationSource.downLink.remove(j);
            }
            }
            }



// if it is the complex theorem and the above unifications have been achieved
// we're going in here
if (ok)
{

 //we solve 'the equation' of variables from variableAndContent substitution
 variableAndContent=Source.resolveVariableContent(variableAndContent, this);
 //END solve 'the equations'

 //we highlight the variables that do not have content
 Source.generateNewVariablesInGeneral(xTheorem.totalVariables, this, variableAndContent);
 DemonstrationItem newDemonstrationItem=null;
 if(this.targetItem==null)
  {
 //we create a demonstration item and
 //we attach at him other demonstration items
 //and we create content
 newDemonstrationItem= new DemonstrationItem();

 newDemonstrationItem.items=
                Source.copyTheListOfConstantAndVariable(target);
  //we establish the type of demonstration item
 newDemonstrationItem.type=DemonstrationConstants.NEW_STATEMENT;
 //we generate the name of new statement and we associate the name with statement content
 String newStatementName1="$a"+this.numberOfOrderNewStatements;
 this.numberOfOrderNewStatements++;
 this.newStatementAndContentPhaseZero.put
               (newStatementName1,Source.copyTheListOfConstantAndVariable(newDemonstrationItem.items));
 newDemonstrationItem.name=newStatementName1;

   //we add on position position0+1 the new demonstration item
  if (demonstrationSource.downLink==null)
  {
      demonstrationSource.downLink=new ArrayList<DemonstrationItem>();
  }

 if(position0>-1)
    {
      demonstrationSource.downLink.add(position0,newDemonstrationItem);
    }
 else
    {
     demonstrationSource.downLink.add(newDemonstrationItem);
    }
    }
 else
 {
     newDemonstrationItem=targetItem;
 }

DemonstrationItem axiomOrTheoremItemSimpleOrComposed=new DemonstrationItem();

newDemonstrationItem.downLink=new java.util.ArrayList<DemonstrationItem>();
newDemonstrationItem.downLink.add(axiomOrTheoremItemSimpleOrComposed);
axiomOrTheoremItemSimpleOrComposed.aboveLink=newDemonstrationItem;//we create double pointer

if (xTheorem.type==1)
{axiomOrTheoremItemSimpleOrComposed.type=DemonstrationConstants.SIMPLE_THEOREM;}//simple theorem
else if (xTheorem.type==2)
{axiomOrTheoremItemSimpleOrComposed.type=DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM;

}//theorem from composed theorem
axiomOrTheoremItemSimpleOrComposed.name=xTheorem.name;
axiomOrTheoremItemSimpleOrComposed.undername=xTheorem.name;
axiomOrTheoremItemSimpleOrComposed.referenceObject=xTheorem;

//we attach to the composed theorem the associated hypotheses and variables
axiomOrTheoremItemSimpleOrComposed.downLink=new java.util.ArrayList<DemonstrationItem>();

int dimension=0,ii=0;

//we verify if the theorem has hypotheses
if (xTheorem.hypotheses!=null)
{
dimension=xTheorem.hypotheses.size();
}


if ((dimension)>0)
{
//here we create an empty list of given dimension
ii=0;DemonstrationItem xDemonstration=null;
do {
xDemonstration=new DemonstrationItem();
axiomOrTheoremItemSimpleOrComposed.downLink.add( xDemonstration );
xDemonstration.aboveLink=axiomOrTheoremItemSimpleOrComposed;
ii++;
}while(ii<dimension);

//we go back through the hypotheses and total variables of xTheorem
ii=dimension-1;
stackItemsCursor=stackSelectedItems.size()-1;
Hypothesis h_x=null;DemonstrationItem baseDemonstration=null;
xDemonstration=null;
do
{
if (ii>=0) {
             xDemonstration=axiomOrTheoremItemSimpleOrComposed.downLink.get(ii);
             h_x=xTheorem.hypotheses.get(ii);
             ii--;
           }
else {xDemonstration=null;}

if (xDemonstration!=null)
{
if (h_x!=null)
{
xDemonstration.type=DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_AXIOM;//ipoteza din axioma compusa
xDemonstration.name=h_x.name;
xDemonstration.referenceObject=h_x;
xDemonstration.numberOfOrderInCompressedProof=0;
}
//we create a base which will have included a new statement
//or a syntactic statement of type:wff,set,class
baseDemonstration=stackSelectedItems.get(stackItemsCursor);
stackItemsCursor--;

//we establish double pointer
xDemonstration.downLink=new java.util.ArrayList<DemonstrationItem>();
xDemonstration.downLink.add(baseDemonstration);
baseDemonstration.aboveLink=xDemonstration;
//we modify the values of xDemonstration
}

xDemonstration=null; h_x=null;

}while (ii>=0);

//we go through the tree
//we generate the new statements

}

}//END verification matching
//END 'theorems'




//END

return ok;
}

public void InsertDirectlyForwardChaining
   ( List<ConstantAndVariable> target)
{
  //we create a new demonstration item and
  //and we paste the  content from the target
 DemonstrationItem newDemonstrationItem= new DemonstrationItem();

 newDemonstrationItem.items=
                Source.copyTheListOfConstantAndVariable(target);
  //we establish the type of the demonstration item
 newDemonstrationItem.type=DemonstrationConstants.NEW_STATEMENT;
 //we generate the name of the new statement
 //and associate the name with the content of the statement
 String newStatementName1="$a"+this.numberOfOrderNewStatements;
 this.numberOfOrderNewStatements++;
 this.newStatementAndContentPhaseZero.put
               (newStatementName1,Source.copyTheListOfConstantAndVariable(newDemonstrationItem.items));
 newDemonstrationItem.name=newStatementName1;

  //we add on position position0+1 the new demonstrationItem
  if (demonstrationSource.downLink==null)
  {
      demonstrationSource.downLink=new ArrayList<DemonstrationItem>();
  }
    
     demonstrationSource.downLink.add(newDemonstrationItem);
}

public void reUpdateDemonstrationBackwardChaining()
{
  //we reset the new variables and statements
  this.numberOfOrderNewStatements=0;
  this.newStatementAndContentPhaseZero.clear();
  this.newStatementAndHubStatement.clear();
  this.newStatementAndGeneratedVariable.clear();
  this.newStatementAndGeneratedVariableContent.clear();
  
  this.numberOfOderVariables=0;
  this.newVariableAndClass.clear();
  this.newVariableAndContent.clear();

   numberOfHubs=0;
   numberOfHubs2=0;
   listOfHubs.clear();
   listOfRepetitions.clear();
   //END reset
 
}


public boolean existNonTerminalLeaf(DemonstrationItem item)
{
    boolean exist=false;
    if (item!=null)
    {
     if (item.downLink==null)
     {
               if(item.type==DemonstrationConstants.TARGET)
               {
                exist=true;
               }
               else
               if(item.type==DemonstrationConstants.NEW_STATEMENT)
               {
                 if (item.items!=null)
                 {
                  if (!item.items.isEmpty())
                  {
                   String first="";
                   first=item.items.get(0).constantOrVariableText;
                   if(
                       (!first.equals("wff"))
                      &(!first.equals("class"))
                      &(!first.equals("set"))
                      &(!item.repetition)
                     )
                     {
                       exist=true;
                     }
                  }
                 }
               }
               else
                 if(
                      (item.type!=DemonstrationConstants.PROPER_HYPOTHESIS)
                     &(item.type!=DemonstrationConstants.SIMPLE_AXIOM)
                     &(item.type!=DemonstrationConstants.SIMPLE_THEOREM)

                   )
                 {
                  exist=true;//here we have a non-terminal leaf
                 }
      }
      else if (item.downLink.isEmpty())
      {
              if(item.type==DemonstrationConstants.TARGET)
               {
                exist=true;
               }
               else
               if(item.type==DemonstrationConstants.NEW_STATEMENT)
               {
                 if (item.items!=null)
                 {
                  if (!item.items.isEmpty())
                  {
                   String first="";
                   first=item.items.get(0).constantOrVariableText;
                   if(
                      (!first.equals("wff"))
                      &(!first.equals("class"))
                      &(!first.equals("set"))
                      &(!item.repetition)
                      )
                     {
                       exist=true;
                     }
                   }
                 }
               }
               else
                   if(
                       (item.type!=DemonstrationConstants.PROPER_HYPOTHESIS)
                      &(item.type!=DemonstrationConstants.SIMPLE_AXIOM)
                      &(item.type!=DemonstrationConstants.SIMPLE_THEOREM)
                     )
                 {
                  exist=true;//here we have a non-terminal leaf
                 }
       }
       else if (item.downLink!=null)
       {
         if (!item.downLink.isEmpty())/*if we have subordinates*/
        {
        int max=item.downLink.size();
        for(int i=0;i<max;i++)
        {
           DemonstrationItem el_subordonat=item.downLink.get(i);
            if (this.existNonTerminalLeaf(el_subordonat))
            {
                exist=true;
                // if one of the subordinates of the node
                // has at least one non-terminal leaf
                break;
            }
        }
           
        }
      }

    }
    return exist;
}
public boolean haveSolutionBackwardChaining()
{
 boolean ok=true;
 //we verify if we have leafs which are not proper hypotheses,
 //simple axioms or simple theorems
 if (this.existNonTerminalLeaf(this.demonstrationSource))
  {
   ok=false;
  }
 int n1=this.newVariableAndClass.size();
 int n2=this.newVariableAndContent.size();
 //we verify if we have unknown variables
 if(n1>n2)
 {
   ok=false;
 }
 

 return ok;
}

public boolean haveSolutionForwardChaining()
{
 boolean ok=false;
 DemonstrationItem ProofSource=null;
  if (this.demonstrationSource.downLink!=null)
  {
    if(!demonstrationSource.downLink.isEmpty())
    {
      int max=demonstrationSource.downLink.size();
     for(int i=0;i<max;i++)
     {
      if(Source.isEqualXWithY
              (this.demonstrationSource.items,
              demonstrationSource.downLink.get(i).items
              )
         )
      {
       ok=true;
       ProofSource=demonstrationSource.downLink.get(i);
       break;
      }
     }

    }
  }

  if(ok)
  {
   if(this.existNonTerminalLeaf(ProofSource))
   {
     ok=false;
   }
  }

 return ok;
}
void changeStrategyButton(java.awt.event.ActionEvent evt,
                               javax.swing.JLabel label)
{
    int nr=JOptionPane.showConfirmDialog
        (this,
        "Changing proof strategy it will delete all your work!",
        "Attention!",
        JOptionPane.YES_NO_OPTION
        );
    
    javax.swing.JButton button=(javax.swing.JButton) evt.getSource();
    
      Application0 a=(Application0)Application.getInstance();
    if (nr==0)
    {
       if (this.demonstrationStrategyType==0)
       {
           //we are switching from backward chaining to forward chaining
           this.destroyBranchBackwardChaining(demonstrationSource,true);
           this.reUpdateDemonstrationBackwardChaining();
         //we are changing the type of strategy
         this.demonstrationStrategyType=1;
          button.setText(backwardChainingStrategySwitch);
          label.setText(forwardChainingStrategyLabel);
       }
      else
          if (this.demonstrationStrategyType==1)
       {
         //we are switching from forward chaining to backward chaining
         this.destroyRootForwardChaining();
             
         //we are changing the type of strategy
         this.demonstrationStrategyType=0;
         button.setText(forwardChainingStrategySwitch);
         label.setText(backwardChainingStrategyLabel);
       
       }
       this.reupdateDemonstrationTree();

    }

}
void saveForwardChaining()
{
 
  if (demonstrationBeginning!=null)
  {
   //System.out.println("--------");
   SyntacticItem arbore=null;
   arbore=crossing0ForwardChaining(demonstrationBeginning);
   cleanNulls(arbore);
   this.findRepetition(arbore, arbore);// n p e
   //we do the renumbering of hubs and repetitions
   int initial=numberOfHubs2;
   //System.out.println("Initial no. of Hubs:"+initial);
   this.numberOfHubs2=0;
   this.crossingRenumberingHubsS(arbore, arbore,initial);
   //System.out.println("Second no. of Hubs:"+this.numberOfHubs2);
   this.numberOfHubs2=0;
   this.crossingRenumberingHubsS(arbore, arbore,0);
    //System.out.println("Third no. of Hubs:"+this.numberOfHubs2);
   //END renumbering
   this.findVector_a_p(arbore);
   this.displayVector_a_p();
   lastHub=0;//we reset hub counter;
   
   crossing1ForwardChaining(arbore);
  }

  
}
public void viewSpecificHubs(SyntacticItem tree)
{
  if (tree!=null)
  {
     if (tree.hub)
      {
                if((tree.repetitionNumber==102)|(tree.repetitionNumber==103))
                 {
                 System.out.println("Hub no.: "+tree.repetitionNumber
                 +" def.:"+tree.definitionName);
                 }

       }
  if (tree.containedItems!=null)
   {
      if(!(tree.containedItems.isEmpty()))
      {
       int max=tree.containedItems.size();
       for(int i=0;i<max;i++)
       {
        viewSpecificHubs(tree.containedItems.get(i));
       }

      }
   }
  }


}
public void displayVector_a_p()
{
int max=vector_a_p.size();
demonstrationString+="      ( ";
stringCounter=8;
int stringCounter0=0;
String demonstrationString0="";
for(int i=0;i<max;i++)
{
 stringCounter0=vector_a_p.get(i).length()+1;
 demonstrationString0=vector_a_p.get(i)+" ";
 if((stringCounter+stringCounter0)>stringDimension)
                    {
                    
                     String rest="";
                     int dim=stringDimension-stringCounter;
                     for(int i0=0;i0<dim;i0++)
                     {
                      rest+=" ";   
                     }
                    demonstrationString+=rest+"\n";
                    demonstrationString+="      "+demonstrationString0;
                    stringCounter=6+stringCounter0;
                    }
                    else
                    {
                     demonstrationString+=demonstrationString0;
                     stringCounter+=stringCounter0;

                    }

}


demonstrationString0=" ) ";
stringCounter0=3;
if((stringCounter+stringCounter0)>stringDimension)
                    {

                     String rest="";
                     int dim=stringDimension-stringCounter;
                     for(int i0=0;i0<dim;i0++)
                     {
                      rest+=" ";
                     }
                    demonstrationString+=rest+"\n";
                    demonstrationString+="      "+demonstrationString0;
                    stringCounter=6+stringCounter0;
                    }
                    else
                    {
                     demonstrationString+=demonstrationString0;
                     stringCounter+=stringCounter0;

                    }
}

 public void findVector_a_p(SyntacticItem syntacticItem)
{
   if (syntacticItem!=null)
   {
    if ((syntacticItem.a_p_v==1)|
        (syntacticItem.a_p_v==2))
    {
     int max=vector_a_p.size();
     boolean alreadyExist=false;
     for(int i=0;i<max;i++)
     {
       String v_i=vector_a_p.get(i);
      if (syntacticItem.definitionName.equals(v_i))
      {
        alreadyExist=true;
        break;
      }
      }

     if(!alreadyExist)
      {
       //if does not exist, we add it to the vector_a_p
       vector_a_p.add(syntacticItem.definitionName);
      }
      }


    if (syntacticItem.containedItems!=null)
    {
     if (!syntacticItem.containedItems.isEmpty())
     {
      int max=syntacticItem.containedItems.size();
      for(int i=0;i<max;i++)
      {
       //we still look on all branches of axioms or theorems
       this.findVector_a_p(syntacticItem.containedItems.get(i));

      }
     }
    }

   }


}

public String numericalToAlpha20(int n)
{
 String ret="";
 if (n<=20)
 {
  if      (n==1) { ret="A"; }
  else if (n==2) { ret="B"; }
  else if (n==3) { ret="C"; }
  else if (n==4) { ret="D"; }
  else if (n==5) { ret="E"; }
  else if (n==6) { ret="F"; }
  else if (n==7) { ret="G"; }
  else if (n==8) { ret="H"; }
  else if (n==9) { ret="I"; }
  else if (n==10) { ret="J"; }
  else if (n==11) { ret="K"; }
  else if (n==12) { ret="L"; }
  else if (n==13) { ret="M"; }
  else if (n==14) { ret="N"; }
  else if (n==15) { ret="O"; }
  else if (n==16) { ret="P"; }
  else if (n==17) { ret="Q"; }
  else if (n==18) { ret="R"; }
  else if (n==19) { ret="S"; }
  else if (n==20) { ret="T"; }
  }
return ret;
}
public String numericalToAlpha5(int n)
{
 String ret="";
 if (n<=5)
 {
  if(n==0) {ret=""; }
  else if (n==1) {ret = "U";}
  else if (n==2) { ret="V"; }
  else if (n==3) { ret="W"; }
  else if (n==4) { ret="X"; }
  else if (n==5) { ret="Y"; }
  }
return ret;
}
public String alphabetic(int n)
{
  String ret="";
  if (n<=20){ ret=numericalToAlpha20(n);}
  else
  {
    int quotient=(int) n/20;
    int remainder=n-quotient*20;
    if (remainder==0)
    { 
        ret=alphabetic5(quotient-1)+"T";
    }
    else
      {
      ret=alphabetic5(quotient)+numericalToAlpha20(remainder);
      }
   

  }

  return ret;
}
public String alphabetic5(int n)
{
 String ret="";
 if (n<=5) {ret=numericalToAlpha5(n);}
 else
 {
    int quotient=(int) n/5;
    int remainder=n-quotient*5;
    if(remainder==0)
    {
     ret=alphabetic5(quotient-1)+"Y";
    }
     else
    {
    ret=alphabetic5(quotient)+numericalToAlpha5(remainder);
    }
    
  }
 
 return ret;
}

//we go through demonstrationItem and build an other tree
SyntacticItem crossing0ForwardChaining(DemonstrationItem demonstrationItem)
{
   SyntacticItem ret=new SyntacticItem();
if(demonstrationItem!=null)
{
if(demonstrationItem.type==DemonstrationConstants.NEW_STATEMENT)
{
if(demonstrationItem.downLink!=null)
{
if(!demonstrationItem.downLink.isEmpty())
{
  DemonstrationItem firstItem=demonstrationItem.downLink.get(0);

 if((firstItem.type==DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM)
     |(firstItem.type==DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM)
    )
{
     if((firstItem.type==DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM))
 {
    Axiom ax=(Axiom)firstItem.referenceObject;
    Map<String,List<ConstantAndVariable>> variableAndContent0=
                  new java.util.HashMap<String,java.util.List<ConstantAndVariable>>();
   if(!this.S.unifyTemplateWithBase(ax.items, demonstrationItem.items, variableAndContent0))
       { System.out.println("Inadequacy axiom:"+ax.name+" statement: "+demonstrationItem.name); }


   if(firstItem.downLink!=null)
   {
   int max=firstItem.downLink.size();
    for(int i=0;i<max;i++)
    {
      DemonstrationItem level1=firstItem.downLink.get(i);
      if (level1.downLink!=null)
      {
       if (!level1.downLink.isEmpty())
       {
          DemonstrationItem level2 = level1.downLink.get(0);
          if (level2.type==DemonstrationConstants.NEW_STATEMENT)
          {
           if(!this.S.unifyTemplateWithBase(ax.hypotheses.get(i).items,
                                    level2.items,
                                    variableAndContent0))
               { System.out.println("Inadequacy axiom: "+ax.name+" statement2 "+level2.name); }
          }
           else if(level2.type == DemonstrationConstants.PROPER_HYPOTHESIS)
          {
           Hypothesis xHypothesis=(Hypothesis) level2.referenceObject;
           if(!this.S.unifyTemplateWithBase(ax.hypotheses.get(i).items,
                                    xHypothesis.items,
                                    variableAndContent0))

              { System.out.println("Inadequacy axiom: "+ax.name+" statement2 "+level2.name); }
          }
       }
      }
     }
    }
   //we memorize the type and name of axiom/theorem
   ret.a_p_v=1;
   ret.definitionName=ax.name;
   if(demonstrationItem.markedAsAHub)
     {
       ret.hub=true;
       ret.repetitionNumber=demonstrationItem.numberOfOrderInCompressedProof;
     }
   ret.containedItems=new ArrayList<SyntacticItem>();
   int max0 =ax.totalVariables.size();
   for(int i=0;i<max0;i++)
   {String name0=ax.totalVariables.get(i).variableText;
    String name1="$l"+name0;
     String class0=ax.totalVariables.get(i).variableClass;
     //we add the syntactic trees of the variables
     if(variableAndContent0.get(name1)==null)
          { System.out.println("axiom: "+ax.name+" variable: "+name1+" null"); }
    SyntacticItem
                   item=this.S.generateSyntacticTree2(variableAndContent0.get(name1),class0);
    this.treeReorganization(item);//we do the reordering
    ret.containedItems.add(item);
   }
    //we add also the hypotheses of the axiom/theorem
   if(firstItem.downLink!=null)
   {
   int max=firstItem.downLink.size();
    for(int i=0;i<max;i++)
    {
      DemonstrationItem level1=firstItem.downLink.get(i);
      if (level1.downLink!=null)
      {
       if (!level1.downLink.isEmpty())
       {
          DemonstrationItem level2 = level1.downLink.get(0);
          if (level2.type==DemonstrationConstants.NEW_STATEMENT)
          {
           if (level2.downLink==null)
          {
               if(level2.repetition)
                {
                  SyntacticItem syntacticItem=new SyntacticItem();
                  syntacticItem.definitionName="repeat";
                  syntacticItem.repetition=true;
                  syntacticItem.repetitionNumber=level2.numberOfOrderInCompressedProof;

                  ret.containedItems.add(syntacticItem);
                }
           }
           ret.containedItems.add(crossing0ForwardChaining(level2));
          }
           else
            if (level2.type==DemonstrationConstants.PROPER_HYPOTHESIS)
          {
           SyntacticItem syntacticItem=new SyntacticItem();
           syntacticItem.definitionName=level2.name;
           syntacticItem.a_p_v=4;// we mark that we are dealing with the proper hypothesis
           ret.containedItems.add(syntacticItem);
          }
       }
      }
     }
    }

  }
  else if((firstItem.type==DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM))
 {
    Theorem tx=(Theorem)firstItem.referenceObject;
    Map<String,List<ConstantAndVariable>> variableAndContent0=
                  new java.util.HashMap<String,java.util.List<ConstantAndVariable>>();
   if(!this.S.unifyTemplateWithBase(tx.items, demonstrationItem.items, variableAndContent0))
        { System.out.println("Inadequacy theorem: "+tx.name+" statement: "+demonstrationItem.name); }

   if(firstItem.downLink!=null)
   {
   int max=firstItem.downLink.size();
    for(int i=0;i<max;i++)
    {
      DemonstrationItem level1=firstItem.downLink.get(i);
      if (level1.downLink!=null)
      {
       if (!level1.downLink.isEmpty())
       {
          DemonstrationItem level2 = level1.downLink.get(0);
          if (level2.type==DemonstrationConstants.NEW_STATEMENT)
          {
           if(!this.S.unifyTemplateWithBase(tx.hypotheses.get(i).items,
                                    level2.items,
                                    variableAndContent0))
               { System.out.println("Inadequacy theorem:"+tx.name+" statement2: "+level2.name); }
          }
           else if(level2.type == DemonstrationConstants.PROPER_HYPOTHESIS)
          {
           Hypothesis xHypothesis=(Hypothesis) level2.referenceObject;
           if(!this.S.unifyTemplateWithBase(tx.hypotheses.get(i).items,
                                    xHypothesis.items,
                                    variableAndContent0))
               { System.out.println("Inadequacy theorem: "+tx.name+" statement2: "+level2.name); }
          }
       }
      }
     }
    }
   //we mention the type and name of the axiom/theorem
   ret.a_p_v=2;
   ret.definitionName=tx.name;
   if(demonstrationItem.markedAsAHub)
   {
    ret.hub=true;
    ret.repetitionNumber=demonstrationItem.numberOfOrderInCompressedProof;
   }
   ret.containedItems=new ArrayList<SyntacticItem>();
   int max0 =tx.totalVariables.size();
   for(int i=0;i<max0;i++)
   {String name0=tx.totalVariables.get(i).variableText;
    String name1="$l"+name0;
     String class0=tx.totalVariables.get(i).variableClass;
     //we add the syntactic trees of the variables
     if(variableAndContent0.get(name1)==null)
                   { System.out.println("xTheorem: "+tx.name+" variable: "+name1+" null"); }
     SyntacticItem
                   item=this.S.generateSyntacticTree2(variableAndContent0.get(name1),class0);
    this.treeReorganization(item);//we do the reordering
    ret.containedItems.add(item);

   }
   //we add also the hypotheses of the axiom/theorem
   if(firstItem.downLink!=null)
   {
   int max=firstItem.downLink.size();
    for(int i=0;i<max;i++)
    {
      DemonstrationItem level1=firstItem.downLink.get(i);
      if (level1.downLink!=null)
      {
       if (!level1.downLink.isEmpty())
       {
          DemonstrationItem level2 = level1.downLink.get(0);
          if (level2.type==DemonstrationConstants.NEW_STATEMENT)
          {
            if (level2.downLink==null)
          {
               if(level2.repetition)
                {
                  SyntacticItem syntacticItem=new SyntacticItem();
                  syntacticItem.definitionName="repeat";
                  syntacticItem.repetition=true;
                  syntacticItem.repetitionNumber=level2.numberOfOrderInCompressedProof;

                  ret.containedItems.add(syntacticItem);
                }
           }
           ret.containedItems.add(crossing0ForwardChaining(level2));
          }
          else
            if (level2.type==DemonstrationConstants.PROPER_HYPOTHESIS)
          {
           SyntacticItem syntacticItem=new SyntacticItem();
           syntacticItem.definitionName=level2.name;
           syntacticItem.a_p_v=4;//we mark that we are dealing with proper hypothesis
           ret.containedItems.add(syntacticItem);
          }
       }
      }
     }
    }

  }
  }
 else
  if((firstItem.type==DemonstrationConstants.SIMPLE_AXIOM)
    |(firstItem.type==DemonstrationConstants.SIMPLE_THEOREM)
   )
 {
  if((firstItem.type==DemonstrationConstants.SIMPLE_AXIOM))
 {
    Axiom ax=(Axiom)firstItem.referenceObject;
    Map<String,List<ConstantAndVariable>> variableAndContent0=
                  new java.util.HashMap<String,java.util.List<ConstantAndVariable>>();
   if(!this.S.unifyTemplateWithBase(ax.items, demonstrationItem.items, variableAndContent0))
       { System.out.println("Inadequacy axiom: "+ax.name+" statement: "+demonstrationItem.name); }
   //we mention the type and name of the axiom/theorem
   ret.a_p_v=1;
   ret.definitionName=ax.name;
   if(demonstrationItem.markedAsAHub)
   {
       ret.hub=true;
       ret.repetitionNumber=demonstrationItem.numberOfOrderInCompressedProof;
   }
   ret.containedItems=new ArrayList<SyntacticItem>();
   int max0 =ax.totalVariables.size();
   for(int i=0;i<max0;i++)
   {String name0=ax.totalVariables.get(i).variableText;
    String name1="$l"+name0;
     String class0=ax.totalVariables.get(i).variableClass;
    //we add the syntactic trees of the variables
     if(variableAndContent0.get(name1)==null)
                  { System.out.println("axiom: "+ax.name+" variable: "+name1+" null"); }
     SyntacticItem
                   item=this.S.generateSyntacticTree2(variableAndContent0.get(name1),class0);
    this.treeReorganization(item);//we do the reordering
    ret.containedItems.add(item);

   }
  }
  else if((firstItem.type==DemonstrationConstants.SIMPLE_THEOREM))
 {
    Theorem tx=(Theorem)firstItem.referenceObject;
    Map<String,List<ConstantAndVariable>> variableAndContent1=
                  new java.util.HashMap<String,java.util.List<ConstantAndVariable>>();
   if(!this.S.unifyTemplateWithBase(tx.items, demonstrationItem.items, variableAndContent1))
       { System.out.println("Inadequacy theorem: "+tx.name+" statement: "+demonstrationItem.name); }
    //we mention the type and name of the axiom/theorem
   ret.a_p_v=2;
   ret.definitionName=tx.name;
   if(demonstrationItem.markedAsAHub)
   {
       ret.hub=true;
       ret.repetitionNumber=demonstrationItem.numberOfOrderInCompressedProof;
   }
   ret.containedItems=new ArrayList<SyntacticItem>();
   int max0 =tx.totalVariables.size();
   for(int i=0;i<max0;i++)
   {String name0=tx.totalVariables.get(i).variableText;
    String name1="$l"+name0;
     String class0=tx.totalVariables.get(i).variableClass;
    //we add the syntactic trees of the variables
     if(variableAndContent1.get(name1)==null)
                  { System.out.println("theorem: "+tx.name+" variable: "+name1+" null"); }
    SyntacticItem
                   item=this.S.generateSyntacticTree2(variableAndContent1.get(name1),class0);
    this.treeReorganization(item);//we do the reordering
    ret.containedItems.add(item);
   }
   }
}

}
}

}

}
return ret;
}

public void treeReorganization(SyntacticItem syntacticItem)
{
  List<SyntacticItem> list=new ArrayList<SyntacticItem>();
  if(syntacticItem!=null){
 if((syntacticItem.a_p_v==1)|
    (syntacticItem.a_p_v==2))
 {
   Axiom a_x=null;
   Theorem t_x=null;

   if (S.axioms.containsKey(syntacticItem.definitionName))
   {
       a_x=(Axiom)S.axioms.get(syntacticItem.definitionName);
   }
   if (S.theorems.containsKey(syntacticItem.definitionName))
   {
       t_x=(Theorem)S.theorems.get(syntacticItem.definitionName);
   }
   List<Variable> variables=null;

   if(a_x!=null)
   {
    variables=a_x.totalVariables;
   }
   else if(t_x!=null)
   {
    variables=t_x.totalVariables;
   }
   if(variables!=null)
   {
    int maximumVariables=variables.size();
    int maximumItems=0;
     maximumItems=syntacticItem.containedItems.size();
    
    for(int i=0;i<maximumVariables;i++)
    {
     Variable v_i=variables.get(i);
     for(int j=0;j<maximumItems;j++)
     {
      SyntacticItem jItem=syntacticItem.containedItems.get(j);
      if(v_i.variableText.equals(jItem.motherVariable))
      {
        list.add(jItem);
        break;
      }
     }
    }
    syntacticItem.containedItems=null;
    syntacticItem.containedItems=list;//we replace with the new order

    int max2=syntacticItem.containedItems.size();
    if(max2!=maximumItems)
    {
     System.out.println("Diff. in no. of syn. items:"
                                                +syntacticItem.definitionName);
    }



    if(syntacticItem.containedItems!=null)
    {
     int max=syntacticItem.containedItems.size();
     for(int i=0;i<max;i++)
     {
       SyntacticItem item=syntacticItem.containedItems.get(i);
       this.treeReorganization(item);
     }

    }
   }
 }
 }
}
public void flatbetDisplay(SyntacticItem syntacticItem)
{
  String restA="",restB="";//strings used for splitting
  if (syntacticItem!=null)
  {
   if ( (syntacticItem.a_p_v==1)
       |(syntacticItem.a_p_v==2)
      )
   {
     int position0=-1;
     int max=vector_a_p.size();
     for (int i=0;i<max;i++)
     {
       String v_i=vector_a_p.get(i);
        if (syntacticItem.definitionName.equals(v_i))
        {
         position0=i;
         break;
        }
     }
     int numberOfVariables=this.baseTheorem.totalVariables.size();
     int numberOfHypotheses=this.baseTheorem.hypotheses.size();
      if (position0>-1)
      {
         stringCounterI=this.alphabetic
                         (numberOfVariables+numberOfHypotheses+position0+1).length();
         demonstrationStringI=this.alphabetic
                         (numberOfVariables+numberOfHypotheses+position0+1);
         
        if(syntacticItem.hub){ 
                              demonstrationStringI+="Z";lastHub++;
                              stringCounterI++;
                         
                             }
        
         if((stringCounter+stringCounterI)>stringDimension)
         {
           
           //we split the demonstrationStringI in two
           int dim=stringDimension-stringCounter;
           if(dim>0)
           {
           restA=demonstrationStringI.substring(0, dim);
           restB="      "+demonstrationStringI.substring(dim);
           }
           else {restA="";restB="      "+demonstrationStringI;}

          demonstrationString+=restA+"\n";
          demonstrationString+=restB;
          stringCounter=restB.length();
          /*if( !((restA+restB).equals(demonstrationStringI)) )
          {
           
           System.out.println(restA+" + "+restB+" != "+demonstrationStringI
           +" dim= "+dim+" sD= "+stringDimension+" sC= "+stringCounter);
          }
           */
         }
         else
         {
          demonstrationString+=demonstrationStringI;
          stringCounter+=stringCounterI;
         }

      }

   }
   else if(syntacticItem.a_p_v ==3)
   {
     int position0=-1;
     int max=this.baseTheorem.totalVariables.size();
     for (int i=0;i<max;i++)
     {
       Variable var_i=this.baseTheorem.totalVariables.get(i);
        if (syntacticItem.definitionName.equals(var_i.variableText))
        {
         position0=i;
         break;
        }
     }

      if (position0>-1)
      {
        stringCounterI=this.alphabetic(position0+1).length();
        demonstrationStringI=this.alphabetic(position0+1);
        
        if((stringCounter+stringCounterI)>stringDimension)
         {
           
            //we split the demonstrationStringI in two
           int dim=stringDimension-stringCounter;
           if(dim>0)
           {
           restA=demonstrationStringI.substring(0, dim);
           restB="      "+demonstrationStringI.substring(dim);
           }
           else {restA="";restB="      "+demonstrationStringI;}
           
          demonstrationString+=restA+"\n";
          demonstrationString+=restB;
          stringCounter=restB.length();
          /*if( !((restA+restB).equals(demonstrationStringI)) )
          {
            
           System.out.println(restA+" + "+restB+" != "+demonstrationStringI
           +" dim= "+dim+" sD= "+stringDimension+" sC= "+stringCounter);
          }
           */
         }
         else
         {
          demonstrationString+=demonstrationStringI;
          stringCounter+=stringCounterI;
         }
      }

   }
  //we have proper hypothesis
  else if(syntacticItem.a_p_v ==4)
   {
     int position0=-1;
     int max=this.baseTheorem.hypotheses.size();
     for (int i=0;i<max;i++)
     {
       Hypothesis iHypothesis=this.baseTheorem.hypotheses.get(i);
        if (syntacticItem.definitionName.equals(iHypothesis.name))
        {
         position0=i;
         break;
        }
     }
      int numberOfVariables=this.baseTheorem.totalVariables.size();
      if (position0>-1)
      {
        stringCounterI=this.alphabetic(numberOfVariables+position0+1).length();
        demonstrationStringI=this.alphabetic(numberOfVariables+position0+1);
        
        if((stringCounter+stringCounterI)>stringDimension)
         {
            
            //we split the demonstrationStringI in two
           int dim=stringDimension-stringCounter;
           if(dim>0)
           {
           restA=demonstrationStringI.substring(0, dim);
           restB="      "+demonstrationStringI.substring(dim);
           }
           else {restA="";restB="      "+demonstrationStringI;}
           
          demonstrationString+=restA+"\n";
          demonstrationString+=restB;
          stringCounter=restB.length();
          /*if( !((restA+restB).equals(demonstrationStringI)) )
          {
            
           System.out.println(restA+" + "+restB+" != "+demonstrationStringI
           +" dim= "+dim+" sD= "+stringDimension+" sC= "+stringCounter);
          }
          */
         }
         else
         {
          demonstrationString+=demonstrationStringI;
          stringCounter+=stringCounterI;
         }
      }

   }
    else if ( syntacticItem.repetition )
   {
     
     int max=vector_a_p.size();
     int numberOfVariables=this.baseTheorem.totalVariables.size();
     int numberOfHypotheses=this.baseTheorem.hypotheses.size();
      if (syntacticItem.repetitionNumber>0)
      {
        if(syntacticItem.repetitionNumber>lastHub){System.out.println("rep: "+syntacticItem.repetitionNumber+">"+lastHub);}
        stringCounterI=this.alphabetic
               (numberOfVariables+numberOfHypotheses+max+syntacticItem.repetitionNumber).length();
        demonstrationStringI=this.alphabetic
                        (numberOfVariables+numberOfHypotheses+max+syntacticItem.repetitionNumber);
        
        if((stringCounter+stringCounterI)>stringDimension)
         {
           
            //we split the demonstrationStringI in two
           int dim=stringDimension-stringCounter;
           if(dim>0)
           {
           restA=demonstrationStringI.substring(0, dim);
           restB="      "+demonstrationStringI.substring(dim);
           }
           else {restA="";restB="      "+demonstrationStringI;}
           
          demonstrationString+=restA+"\n";
          demonstrationString+=restB;
          stringCounter=restB.length();
          /*if( !((restA+restB).equals(demonstrationStringI)) )
          {
            
           System.out.println(restA+" + "+restB+" != "+demonstrationStringI
           +" dim= "+dim+" sD= "+stringDimension+" sC= "+stringCounter);
          }*/
         }
         else
         {
          demonstrationString+=demonstrationStringI;
          stringCounter+=stringCounterI;
         }
       
      }

   }

  }
}
//we go through the demonstrationItem and build an other tree
SyntacticItem crossing0BackwardChaining(DemonstrationItem demonstrationItem)
{
   SyntacticItem ret=new SyntacticItem();
      
if(demonstrationItem!=null)
{
if((demonstrationItem.type==DemonstrationConstants.NEW_STATEMENT)
    |(demonstrationItem.type==DemonstrationConstants.TARGET)
  )
{
  
if(demonstrationItem.downLink!=null)
{
if(!demonstrationItem.downLink.isEmpty())
{
  DemonstrationItem firstItem=demonstrationItem.downLink.get(0);

 if((firstItem.type==DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM)
     |(firstItem.type==DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM)
    )
{
     if((firstItem.type==DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM))
 {
    Axiom ax=(Axiom)firstItem.referenceObject;
    Map<String,List<ConstantAndVariable>> variableAndContent0=
                  new java.util.HashMap<String,java.util.List<ConstantAndVariable>>();
   if(!this.S.unifyTemplateWithBase(ax.items, demonstrationItem.items, variableAndContent0))
           { System.out.println("Inadequacy axiom:"+ax.name+" statement: "+demonstrationItem.name); }

   if(firstItem.downLink!=null)
   {
   int max=firstItem.downLink.size();
   //where non-syntactical statements begin
   int threshold=ax.totalVariables.size();
   threshold=0;//we remove the variables from the calculation
    for(int i=threshold;i<max;i++)
    {
      DemonstrationItem level1=firstItem.downLink.get(i);
      if (level1.downLink!=null)
      {
       if (!level1.downLink.isEmpty())
       {
          DemonstrationItem level2 = level1.downLink.get(0);
          if (level2.type==DemonstrationConstants.NEW_STATEMENT)
          {
           if(!this.S.unifyTemplateWithBase(ax.hypotheses.get(i-threshold).items,
                                           level2.items,
                                           variableAndContent0))
               { System.out.println("Inadequacy axiom: "+ax.name+" statement2: "+level2.name); }
          }
           
       }
      }
     }
    }
   //we mention the type and name of the axiom/theorem
   ret.a_p_v=1;
   ret.definitionName=ax.name;
   if(demonstrationItem.markedAsAHub)
     {
       ret.hub=true;
       ret.repetitionNumber=demonstrationItem.numberOfOrderInCompressedProof;
     }
   ret.containedItems=new ArrayList<SyntacticItem>();
   int max0 =ax.totalVariables.size();
   for(int i=0;i<max0;i++)
   {String name0=ax.totalVariables.get(i).variableText;
    String name1="$l"+name0;
     String class0=ax.totalVariables.get(i).variableClass;
    //we add the syntatic trees of the variables
     if(variableAndContent0.get(name1)==null)
                 { System.out.println("axiom: "+ax.name+" variable: "+name1+" null"); }
    SyntacticItem
                   item=this.S.generateSyntacticTree2(variableAndContent0.get(name1),class0);
    this.treeReorganization(item);//we do the reordering
    ret.containedItems.add(item);
   }
    //we add also the hypotheses of the axiom/theorem
   if(firstItem.downLink!=null)
   {
   int max=firstItem.downLink.size();
   //where non-syntactical statements begin
   int threshold=ax.totalVariables.size();
   threshold=0;//remove the variables from the calculation
    for(int i=threshold;i<max;i++)
    {
      DemonstrationItem level1=firstItem.downLink.get(i);
      if (level1.downLink!=null)
      {
       if (!level1.downLink.isEmpty())
       {
          DemonstrationItem level2 = level1.downLink.get(0);
          if(level2.downLink!=null)
          {
          if(!level2.downLink.isEmpty())
          {

          DemonstrationItem level3=level2.downLink.get(0);
          if ((level2.type==DemonstrationConstants.NEW_STATEMENT)
              &(level3.type!=DemonstrationConstants.PROPER_HYPOTHESIS))
          {
           ret.containedItems.add(crossing0BackwardChaining(level2));
          }
           else
            if ((level2.type==DemonstrationConstants.NEW_STATEMENT)
              &(level3.type==DemonstrationConstants.PROPER_HYPOTHESIS))
          {
           SyntacticItem syntacticItem=new SyntacticItem();
           syntacticItem.definitionName=level3.name;
           syntacticItem.a_p_v=4;//we mark that we are dealing with proper hypothesis
           ret.containedItems.add(syntacticItem);
          }
          
          }
          }
          else if (level2.downLink==null)
          {
               if(level2.repetition)
                {                 
                  SyntacticItem syntacticItem=new SyntacticItem();
                  syntacticItem.definitionName="repeat";
                  syntacticItem.repetition=true;
                  syntacticItem.repetitionNumber=level2.numberOfOrderInCompressedProof;

                  ret.containedItems.add(syntacticItem);
                }
           }

       }
      }
     }
    }

  }
  else if((firstItem.type==DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM))
 {
    Theorem tx=(Theorem)firstItem.referenceObject;
    Map<String,List<ConstantAndVariable>> variableAndContent0=
                  new java.util.HashMap<String,java.util.List<ConstantAndVariable>>();
    if(!this.S.unifyTemplateWithBase(tx.items, demonstrationItem.items, variableAndContent0))
        { System.out.println("Inadequacy theorem:"+tx.name+" statement: "+demonstrationItem.name); }


   if(firstItem.downLink!=null)
   {
   int max=firstItem.downLink.size();
   //where the non-syntactic statements begin
   int threshold=tx.totalVariables.size();
   threshold=0;//we remove variables from the calculation
    for(int i=threshold;i<max;i++)
    {
      DemonstrationItem level1=firstItem.downLink.get(i);
      if (level1.downLink!=null)
      {
       if (!level1.downLink.isEmpty())
       {
          DemonstrationItem level2 = level1.downLink.get(0);
          if (level2.type==DemonstrationConstants.NEW_STATEMENT)
          {
           if(!this.S.unifyTemplateWithBase(tx.hypotheses.get(i-threshold).items,
                                           level2.items,
                                           variableAndContent0))
               { System.out.println("Inadequacy theorem:"+tx.name+" statement2: "+ level2.name); }
          }
           
       }
      }
     }
    }
   //we mention the type and name of the axiom/theorem
   ret.a_p_v=2;
   ret.definitionName=tx.name;
   if(demonstrationItem.markedAsAHub)
     {
       ret.hub=true;
       ret.repetitionNumber=demonstrationItem.numberOfOrderInCompressedProof;
     }
   ret.containedItems=new ArrayList<SyntacticItem>();
   int max0 =tx.totalVariables.size();
   for(int i=0;i<max0;i++)
   {String name0=tx.totalVariables.get(i).variableText;
    String name1="$l"+name0;
     String class0=tx.totalVariables.get(i).variableClass;
     //we add the syntactic trees of the variables
     if(variableAndContent0.get(name1)==null)
                 { System.out.println("theorem: "+tx.name+" variable: "+name1+" null"); }
    SyntacticItem
                   item=this.S.generateSyntacticTree2(variableAndContent0.get(name1),class0);
    this.treeReorganization(item);//we do the reordering
    ret.containedItems.add(item);

   }
   //we add also the hypotheses of the axiom/theorem
   if(firstItem.downLink!=null)
   {
   int max=firstItem.downLink.size();
   //where non-syntactical statements begin
   int threshold=tx.totalVariables.size();
   threshold=0;//we remove the variables from the calcution
    for(int i=threshold;i<max;i++)
    {
      DemonstrationItem level1=firstItem.downLink.get(i);
      if (level1.downLink!=null)
      {
       if (!level1.downLink.isEmpty())
       {
           DemonstrationItem level2 = level1.downLink.get(0);
         if(level2.downLink!=null)
          {
          if(!level2.downLink.isEmpty())
          {

          DemonstrationItem level3=level2.downLink.get(0);
          if ((level2.type==DemonstrationConstants.NEW_STATEMENT)
              &(level3.type!=DemonstrationConstants.PROPER_HYPOTHESIS))
          {
           ret.containedItems.add(crossing0BackwardChaining(level2));
          }
           else
            if ((level2.type==DemonstrationConstants.NEW_STATEMENT)
              &(level3.type==DemonstrationConstants.PROPER_HYPOTHESIS))
          {
           SyntacticItem syntacticItem=new SyntacticItem();
           syntacticItem.definitionName=level3.name;
           syntacticItem.a_p_v=4;//we mark that we are dealing with proper hypothesis
           ret.containedItems.add(syntacticItem);
          }

          }
          }
           else if (level2.downLink==null)
          {
               if(level2.repetition)
                {                 
                  SyntacticItem syntacticItem=new SyntacticItem();
                  syntacticItem.definitionName="repeat";
                  syntacticItem.repetition=true;
                  syntacticItem.repetitionNumber=level2.numberOfOrderInCompressedProof;

                  ret.containedItems.add(syntacticItem);
                }
           }




       }
      }


     }
    }

  }
  }
 else
  if((firstItem.type==DemonstrationConstants.SIMPLE_AXIOM)
    |(firstItem.type==DemonstrationConstants.SIMPLE_THEOREM)
   )
 {
  if((firstItem.type==DemonstrationConstants.SIMPLE_AXIOM))
 {
    Axiom ax=(Axiom)firstItem.referenceObject;
    Map<String,List<ConstantAndVariable>> variableAndContent0=
                  new java.util.HashMap<String,java.util.List<ConstantAndVariable>>();
   if(!this.S.unifyTemplateWithBase(ax.items, demonstrationItem.items, variableAndContent0))
       { System.out.println("Inadequacy axiom:"+ax.name+" statement: "+demonstrationItem.name); }
   //we mention the type and the name of the axiom/theorem
   ret.a_p_v=1;
   ret.definitionName=ax.name;
   if(demonstrationItem.markedAsAHub)
     {
       ret.hub=true;
       ret.repetitionNumber=demonstrationItem.numberOfOrderInCompressedProof;
     }
   ret.containedItems=new ArrayList<SyntacticItem>();
   int max0 =ax.totalVariables.size();
   for(int i=0;i<max0;i++)
   {String name0=ax.totalVariables.get(i).variableText;
    String name1="$l"+name0;
     String class0=ax.totalVariables.get(i).variableClass;
     //we add syntactic trees of the variables
     if(variableAndContent0.get(name1)==null)
                  { System.out.println("axiom: "+ax.name+" variable: "+name1+" null"); }
     SyntacticItem
                   item=this.S.generateSyntacticTree2(variableAndContent0.get(name1),class0);
    this.treeReorganization(item);//we do the reordering
    ret.containedItems.add(item);
   }
  }
  else if((firstItem.type==DemonstrationConstants.SIMPLE_THEOREM))
 {
    Theorem tx=(Theorem)firstItem.referenceObject;
    Map<String,List<ConstantAndVariable>> variableAndContent1=
                  new java.util.HashMap<String,java.util.List<ConstantAndVariable>>();
   if(!this.S.unifyTemplateWithBase(tx.items, demonstrationItem.items, variableAndContent1))
             { System.out.println("Inadequacy theorem:"+tx.name+" statement: "+demonstrationItem.name); }
   //we mention the type and name of the axiom/theorem
   ret.a_p_v=2;
   ret.definitionName=tx.name;
   if(demonstrationItem.markedAsAHub)
     {
       ret.hub=true;
       ret.repetitionNumber=demonstrationItem.numberOfOrderInCompressedProof;
     }
   ret.containedItems=new ArrayList<SyntacticItem>();
   int max0 =tx.totalVariables.size();
   for(int i=0;i<max0;i++)
   {String name0=tx.totalVariables.get(i).variableText;
    String name1="$l"+name0;
     String class0=tx.totalVariables.get(i).variableClass;
     //we add the syntactic trees of the variables
     if(variableAndContent1.get(name1)==null)
                  { System.out.println("theorem: "+tx.name+" variable: "+name1+" null"); }
    SyntacticItem
                   item=this.S.generateSyntacticTree2(variableAndContent1.get(name1),class0);
    this.treeReorganization(item);//we do the reordering
    ret.containedItems.add(item);
   }
   }
}

}
}

}

}
return ret;
}
//we flatten the syntactic tree
void crossing1ForwardChaining(SyntacticItem syntacticItem)
{
    if (syntacticItem!=null)
    {
     if (syntacticItem.containedItems!=null)
     {
      if (!syntacticItem.containedItems.isEmpty())
     {
        int max=syntacticItem.containedItems.size();
        for (int i=0;i<max;i++)
        {
         SyntacticItem iItem=syntacticItem.containedItems.get(i);
         crossing1ForwardChaining(iItem);
        }
     }
     }
      this.flatbetDisplay(syntacticItem);
     
    }
}

void findRepetition(SyntacticItem base,SyntacticItem syntacticItem)
{
    if (syntacticItem!=null)
    {
        if (syntacticItem.containedItems!=null)
     {
      if (!syntacticItem.containedItems.isEmpty())
     {
        int max=syntacticItem.containedItems.size();
        for (int i=0;i<max;i++)
        {
         SyntacticItem iItem=syntacticItem.containedItems.get(i);
         findRepetition(base,iItem);// n p e
        }
     }
     }

       if(syntacticItem.containedItems!=null)
       {//The syntacticItem tree must have at least 2 levels
       if (syntacticItem.containedItems.size()>=1)
       {
       if(!syntacticItem.repetition)
       {
        String s=syntacticItem.definitionName;
        this.identicalItem(base,s, syntacticItem);// n p e
        
       }
       }
       }
      

    }
}
void identicalItem(SyntacticItem syntacticItem, String s,SyntacticItem tree)
{
    if (syntacticItem!=null)
    {
        if (syntacticItem.containedItems!=null)
     {
      if (!syntacticItem.containedItems.isEmpty())
     {
        int max=syntacticItem.containedItems.size();
        for (int i=0;i<max;i++)
        {
         SyntacticItem iItem=syntacticItem.containedItems.get(i);
         if(iItem!=null)
         {
         identicalItem(iItem,s,tree);// n p e
         }
        }
     }
     }
        if(syntacticItem.definitionName!=null)
        {
       if(s!=null)
       {

      if(syntacticItem!=tree)
      {
      if(syntacticItem.definitionName.equals(s))
      {
        if((!syntacticItem.hub)&(!syntacticItem.repetition))
        {
          if (this.haveIdenticalTrees(syntacticItem, tree))// n p e
          {
           

             if(tree.hub)
             {
                 /*
                 System.out.println();
                 this.displaySyntax(tree);
                 System.out.println();
                 this.displaySyntax(syntacticItem);
                 */

                 //note with 5 a_p_v to avoid confusion
                 syntacticItem.a_p_v=5;
                 //we copy the repetition number
                 syntacticItem.repetitionNumber=tree.repetitionNumber;
                 //we mark as a repetition the syntactic item
                 syntacticItem.repetition=true;
                 /*System.out.println("Hub:"+tree.repetitionNumber+"|"
                                                          +tree.definitionName);
                  * 
                  */
              }
              else
              {
                 /*
                 System.out.println();
                 this.displaySyntax(tree);
                 System.out.println();
                 this.displaySyntax(syntacticItem);
                 */
                //we increment the number of hubs
                this.numberOfHubs2++;
                //we mark the syntactic item as a hub
                tree.hub=true;
                tree.repetitionNumber=this.numberOfHubs2;
                //note with 5 a_p_v to avoid confusion
                syntacticItem.a_p_v=5;
                syntacticItem.repetition=true;
                syntacticItem.repetitionNumber=this.numberOfHubs2;
                /*System.out.println("New hub:"+tree.repetitionNumber+"|"
                                                          +tree.definitionName);
                 * 
                 */
              }

             //we cut the branches
              if (syntacticItem.containedItems!=null)
              {
              syntacticItem.containedItems.clear();
              }

              }

          }
          
        }

      }
      }


       }
       }
    }




boolean haveIdenticalTrees(SyntacticItem syntacticItem1,SyntacticItem syntacticItem2)
{
    boolean ok=true;
    //if one of the variables is null we have false
    if((syntacticItem1!=null)&(syntacticItem2==null)) ok=false;
    if((syntacticItem1==null)&(syntacticItem2!=null)) ok=false;
    if((syntacticItem1==null)&(syntacticItem2==null)) ok=false;
    if(syntacticItem1==syntacticItem2) ok=false;



    //if the definitionName is different at those two variables then we have false
     if (ok)
     {
        if(syntacticItem1.definitionName!=null)
        {
        if (syntacticItem2.definitionName!=null)
        {
        if (!syntacticItem1.definitionName.equals(syntacticItem2.definitionName)) ok=false;//n p e
        } else { ok=false;
                 System.out.println("Error at haveIdenticalTrees 2: rep.no.: "+
                         syntacticItem2.repetitionNumber
                         +"rep:"+syntacticItem2.repetition
                         +"hub:"+syntacticItem2.hub
                         +"a_p_v_h_r:"+syntacticItem2.a_p_v
                         +"mother:"+syntacticItem2.motherVariable);
                }
        }else {ok=false;
                System.out.println("Error at haveIdenticalTrees 1: rep.no.: "+
                         syntacticItem1.repetitionNumber
                         +"rep:"+syntacticItem1.repetition
                         +"hub:"+syntacticItem1.hub
                         +"a_p_v_h_r:"+syntacticItem1.a_p_v
                         +"mother:"+syntacticItem1.motherVariable);
               }
      

     }

    boolean checked=false;//if it is necesary to verify down on branches

    if(ok)
    {
       if ((syntacticItem2.hub)&(!syntacticItem2.repetition)
          &(!syntacticItem1.hub)&(!syntacticItem1.repetition))
       {
         //we go further
       }
  else if ((syntacticItem2.hub) & (!syntacticItem2.repetition)
          &(!syntacticItem1.hub)&(syntacticItem1.repetition))
       {
         if(syntacticItem2.repetitionNumber==syntacticItem1.repetitionNumber)
         {
          checked=true;//is not necesary to verify down on braches
         }
          else {ok=false;}
       }

else if((!syntacticItem2.hub) & (syntacticItem2.repetition)
          &(!syntacticItem1.hub)&(syntacticItem1.repetition))
       {
         if(syntacticItem2.repetitionNumber==syntacticItem1.repetitionNumber)
         {
          checked=true;//is not necesary to verify down on braches
         }
          else {ok=false;}
       }
 else if ((!syntacticItem2.hub) & (syntacticItem2.repetition)
          &(!syntacticItem1.hub)&(!syntacticItem1.repetition))
 {
     ok=false;
 }
 else if ((!syntacticItem2.hub) & (!syntacticItem2.repetition)
          &(!syntacticItem1.hub)&(syntacticItem1.repetition))
 {
     ok=false;
 }

    }

    if(!checked)
    {
    if(ok)
    {
     if ((syntacticItem1.containedItems!=null)&(syntacticItem2.containedItems!=null))
     {
      if ((!syntacticItem1.containedItems.isEmpty())&
              (!syntacticItem2.containedItems.isEmpty()))
     {
        int max1=syntacticItem1.containedItems.size();
        int max2=syntacticItem2.containedItems.size();

        if(max1==max2)
        {
        for (int i=0;i<max1;i++)
        {
         SyntacticItem itemI1=syntacticItem1.containedItems.get(i);
         SyntacticItem itemI2=syntacticItem2.containedItems.get(i);

         if(itemI1!=null)
         {

         if(itemI2!=null)
         {
         // if we find different sub-trees then we have false
         if(!haveIdenticalTrees(itemI1,itemI2)){ ok=false; }//n p e
         }else ok=false;

         } else ok=false;

        }
        }
         else ok=false;
     } else if ((syntacticItem1.containedItems.isEmpty())&
              (syntacticItem2.containedItems.isEmpty())) 
                   { //do nothing
         
                   } else {ok=false;}
     

     } 

     }
     }

     return ok;
   
}

public void cleanNulls(SyntacticItem syntacticItem)
{
   if(syntacticItem!=null)
   {
    if (syntacticItem.containedItems!=null)
    {
      if(!syntacticItem.containedItems.isEmpty())
        {
         List<SyntacticItem> newList=new ArrayList<SyntacticItem>();
       int max=syntacticItem.containedItems.size();

       for (int i0=0;i0<max;i0++)
       {
       
          SyntacticItem iItem=syntacticItem.containedItems.get(i0);
          
          if (iItem.definitionName!=null)
          {
           newList.add(iItem);
          }
           else
           {
            
           }
        
        }
         syntacticItem.containedItems.clear();
         syntacticItem.containedItems=null;
         syntacticItem.containedItems=newList;

        }



      if(!syntacticItem.containedItems.isEmpty())
        {

       int max2=syntacticItem.containedItems.size();

       for (int i2=0;i2<max2;i2++)
       {

          SyntacticItem iItem=syntacticItem.containedItems.get(i2);

          cleanNulls(iItem);

        }

        }



    }
   }
}

void saveBackwardChaining()
{
   SyntacticItem tree=null;
   tree=crossing0BackwardChaining(demonstrationSource);
   cleanNulls(tree);
   this.findRepetition(tree, tree);
   //we do the renumbering of the hubs and repetitions
   int initial=numberOfHubs2;
   this.numberOfHubs2=0;
   this.crossingRenumberingHubsS(tree, tree,initial);
   this.numberOfHubs2=0;
   this.crossingRenumberingHubsS(tree, tree,0);
   //END renumbering
   this.findVector_a_p(tree);
   this.displayVector_a_p();
   lastHub=0;//we reset hub counter
   crossing1ForwardChaining(tree);

}
public void findPath(DemonstrationItem item,List<String> path)
{
  if(item.aboveLink!=null)
  {
    if(item.aboveLink.downLink!=null)
    {
     int index=item.aboveLink.downLink.indexOf(item);
     findPath(item.aboveLink,path);
     path.add(""+index);
     
     
    }
  }

}
public boolean justBefore(List<String> x,List<String>y)
{boolean ok=false;
 int n_x=x.size(),n_y=y.size(),n=0;
 if(n_x!=0)
 {
  if(n_y!=0)
  {
   if(n_x<=n_y)
   {
   n=n_x;
   }
  else if(n_y<=n_x)
  {
   n=n_y;
  }
   for(int i=0;i<n;i++)
   {
     int x_i=Integer.parseInt(x.get(i)),y_i=Integer.parseInt(y.get(i));
     if(x_i<y_i)
     {
      ok=true;break;
     }
     else if(x_i>y_i)
     {
      ok=false;break;
     }
      else if(x_i==y_i)
     {
      //we move on
     }
       
   }

  }
 }
 return ok;
}
//here we take every hub that is outside of main proof
//and we put the hub proof to a repetition from the main proof

public void findHubsThatAreOutside(DemonstrationItem item)
{
 if(item!=null)
 {
  if(item.type==DemonstrationConstants.NEW_STATEMENT)
  {
   
   if(item.markedAsAHub)
   {
       
   if(item.downLink!=null)
   {
   DemonstrationItem paste1=item.downLink.get(0);
   item.downLink.clear();
   item.markedAsAHub=false;
   item.repetition=true;
   this.weFoundARepetitionWithIndexAndWePasteProof=false;
   this.findRepetitionsWithIndexAndPasteProof(this.demonstrationBeginning,
                                  item.numberOfOrderInCompressedProof, paste1);

   }

   }
  }

  if(item.downLink!=null)
  {
   if(!item.downLink.isEmpty())
   {
    int max=item.downLink.size();
    for(int i=0;i<max;i++)
    {
     this.findHubsThatAreOutside(item.downLink.get(i));
    }
   }
  }

 }
}

public void findRepetitionsWithIndexAndPasteProof(DemonstrationItem item,
                                                    int index,
                                                   DemonstrationItem paste0
                                                    )
{
 if(item!=null)
 {
  if(item.type==DemonstrationConstants.NEW_STATEMENT)
  {
   if(item.repetition)
   {
    if(
       (item.numberOfOrderInCompressedProof==index)&
       (!this.weFoundARepetitionWithIndexAndWePasteProof)
      )
    {
     item.markedAsAHub=true;
     item.repetition=false;
      if(item.downLink!=null)
      {
      item.downLink.clear();
      
      }
       else
       {
        item.downLink=new ArrayList<DemonstrationItem>();
        
       }

     item.downLink.add(paste0);//we attach  the proof of the outside hub
     paste0.aboveLink=item;
     this.weFoundARepetitionWithIndexAndWePasteProof=true;

    }
   }

  }
   if(!this.weFoundARepetitionWithIndexAndWePasteProof)
   {
  if(item.downLink!=null)
  {
   if(!item.downLink.isEmpty())
   {
    
    int max=item.downLink.size();
    for(int i=0;i<max;i++)
    {
     this.findRepetitionsWithIndexAndPasteProof
                                            (item.downLink.get(i),index,paste0);
    }
    
   }
  }
 }


 }
}

//END of operation

public void findHubsAndRepetitions(DemonstrationItem item)
{
 if(item!=null)
 {
  if(item.type==DemonstrationConstants.NEW_STATEMENT)
  {
   if(item.repetition)
   {
     HubAndRepetitionItem repetition=new HubAndRepetitionItem();
     List<String> path=new  ArrayList<String>();
     this.findPath(item, path);
     repetition.item=item;
     repetition.path=path;
     this.listOfRepetitions.add(repetition);
   }
   if(item.markedAsAHub)
   {
     HubAndRepetitionItem hub=new HubAndRepetitionItem();
     List<String> path=new  ArrayList<String>();
     this.findPath(item, path);
     hub.item=item;
     hub.path=path;
     this.listOfHubs.add(hub);
   }
  }

  if(item.downLink!=null)
  {
   if(!item.downLink.isEmpty())
   {
    int max=item.downLink.size();
    for(int i=0;i<max;i++)
    {
     this.findHubsAndRepetitions(item.downLink.get(i));
    }
   }
  }

 }
}

public void findRepetitionsWithIndex(DemonstrationItem item, int index)
{
 if(item!=null)
 {
  if(item.type==DemonstrationConstants.NEW_STATEMENT)
  {
   if(item.repetition)
   {
    if(item.numberOfOrderInCompressedProof==index)
    {
      this.numberOfFoundRepetitionsWithIndex++;
    }
   }

  }

  if(item.downLink!=null)
  {
   if(!item.downLink.isEmpty())
   {
    if(this.numberOfFoundRepetitionsWithIndex<2)
    {
    int max=item.downLink.size();
    for(int i=0;i<max;i++)
    {
     this.findRepetitionsWithIndex(item.downLink.get(i),index);
    }
    }
   }
  }

 }
}

public void findHubWithIndexAndDemarcate(DemonstrationItem item,int index)
{
 if(item!=null)
 {
  if(item.type==DemonstrationConstants.NEW_STATEMENT)
  {
   if(item.markedAsAHub)
   {
    if(item.numberOfOrderInCompressedProof==index)
    {
      this.weFoundHubWithIndex=true;
      item.markedAsAHub=false;
    }
   }

  }

  if(item.downLink!=null)
  {
   if(!item.downLink.isEmpty())
   {
    if(!this.weFoundHubWithIndex)
    {
    int max=item.downLink.size();
    for(int i=0;i<max;i++)
    {
     this.findHubWithIndexAndDemarcate(item.downLink.get(i),index);
    }
    }
   }
  }

 }
}
public void recoverNewHubs()
{
  DemonstrationItem auxDem=null;
  List<String> auxPath=null;
 if(this.listOfHubs!=null)
 {
  if(!this.listOfHubs.isEmpty())
  {
    int max=this.listOfHubs.size();
    int max2=this.listOfRepetitions.size();
    for(int i=0;i<max;i++)
    {
      DemonstrationItem iItem=this.listOfHubs.get(i).item;
      List<String> iPath=this.listOfHubs.get(i).path;
      
      boolean weFoundMinimumARepetition=false;
    for(int j=0;j<max2;j++)
    {
      DemonstrationItem jItem=this.listOfRepetitions.get(j).item;
      List<String> jPath=this.listOfRepetitions.get(j).path;

      if(Source.isEqualXWithY(iItem.items, jItem.items))
      {
        weFoundMinimumARepetition=true;
       if(this.justBefore(jPath, iPath))
       {
        if(iItem.downLink!=null)
        {
         if(iItem.downLink.size()==1)
         {
           System.out.println("Swap:");

           //hub
           int maxk=iItem.items.size();
           String s1="";
           for(int k=0;k<maxk;k++)
           {
               s1=s1+" "+iItem.items.get(k).constantOrVariableText;
           }
           int maxk2=iPath.size();
           String s2="";
           for(int k2=0;k2<maxk2;k2++)
           {
               s2=s2+" "+iPath.get(k2);
           }
           System.out.println("hub no.: "+iItem.numberOfOrderInCompressedProof+
                   ": "+s1+" path:"+s2);


           //repetition
           maxk=jItem.items.size();
            s1="";
           for(int k=0;k<maxk;k++)
           {
               s1=s1+" "+jItem.items.get(k).constantOrVariableText;
           }
            maxk2=jPath.size();
            s2="";
           for(int k2=0;k2<maxk2;k2++)
           {
               s2=s2+" "+jPath.get(k2);
           }
           System.out.println("rep. no.: "+jItem.numberOfOrderInCompressedProof+
                   ": "+s1+" path:"+s2);

          //we make jItem hub
          DemonstrationItem changeItem=iItem.downLink.get(0);
          jItem.downLink=new ArrayList<DemonstrationItem>();
          jItem.downLink.add(changeItem);
          changeItem.aboveLink=jItem;
          jItem.markedAsAHub=true;
          jItem.repetition=false;
          //we make iItem repetition
          iItem.downLink.clear();
          iItem.downLink=null;
          iItem.markedAsAHub=false;
          iItem.repetition=true;
          //iItem.numberOfOrderInCompressedProof=jItem.numberOfOrderInCompressedProof;
          //we swap iTem with jItem because jItem is hub now
          auxDem=iItem;
          auxPath=iPath;
          iItem=jItem;
          iPath=jPath;
          jItem=auxDem;
          jPath=auxPath;
          //we update list of hubs and list of repetition
          this.listOfHubs.get(i).item=iItem;
          this.listOfHubs.get(i).path=iPath;

          this.listOfRepetitions.get(j).item=jItem;
          this.listOfRepetitions.get(j).path=jPath;

         }
        }
       }
      }

    }
     if(!weFoundMinimumARepetition)
     {
       //if the hub has no repetition we deactivate the hub
        
        iItem.markedAsAHub=false;
        iItem.repetition=false;
        System.out.println("We found a hub without repetition: "+iItem.numberOfOrderInCompressedProof);
        iItem.numberOfOrderInCompressedProof=0;
         
     }

    }
      
  }
 }

}

public void crossingRenumberingHubs(DemonstrationItem item)
{
    if(item!=null)
    {
        if(item.downLink!=null)
        {
         if(!item.downLink.isEmpty())
         {
           int max=item.downLink.size();
           for(int i=0;i<max;i++)
           {
               crossingRenumberingHubs(item.downLink.get(i));
           }
         }
        }
      //we see if this is a hub
      if(item.type==DemonstrationConstants.NEW_STATEMENT)
      {
       if(item.markedAsAHub)
       {
        numberOfHubs2++;//we increase the number of hubs
        
        item.numberOfOrderInCompressedProof=numberOfHubs2;//we renumber the initial hub
        renumberingRepetitions(item.items,numberOfHubs2,this.demonstrationSource);
       }
      }
    }
}
public void renumberingRepetitions(List<ConstantAndVariable> a,int bNumber, DemonstrationItem item)
{
  if(item!=null)
    {
      //we see if this is a repetition
      if(item.type==DemonstrationConstants.NEW_STATEMENT)
      {
       if(item.repetition)
       {
        if(Source.isEqualXWithY(item.items, a))
        {
         item.numberOfOrderInCompressedProof=bNumber;//we renumber the repetition
        }
       }
      }
       //keep going
       if(item.downLink!=null)
        {
         if(!item.downLink.isEmpty())
         {
           int max=item.downLink.size();
           for(int i=0;i<max;i++)
           {
               renumberingRepetitions(a,bNumber,item.downLink.get(i));
           }
         }
        }
   
    }
}
public void crossingRenumberingHubsS(SyntacticItem base,
                                    SyntacticItem item,int initial)
{
    if(item!=null)
    {
        if(item.containedItems!=null)
        {
         if(!item.containedItems.isEmpty())
         {
           int max=item.containedItems.size();
           for(int i=0;i<max;i++)
           {
           crossingRenumberingHubsS
                                   (base,item.containedItems.get(i),initial);
           }
         }
        }
       //we see if this is a hub
      
       if(item.hub)
       {
          int previousNumber=item.repetitionNumber;
          this.howManyRepetitionsWeHave=0;//we reset
          this.howManyRepetitionsS(previousNumber, base);
          
           if(this.howManyRepetitionsWeHave>0)
           {
            numberOfHubs2++;//we increase the counter of the hubs
            item.repetitionNumber=numberOfHubs2+initial;//we initial renumber the hub
            renumberingRepetitionsS(previousNumber,numberOfHubs2+initial,base);
            }
            else if(this.howManyRepetitionsWeHave==0)
            {
             item.hub=false;
             item.repetitionNumber=0;
            }
       }
      
    }
}
public void renumberingRepetitionsS(int a,int bNumber, SyntacticItem item)
{
  if(item!=null)
    {
           
       //keep going
       if(item.containedItems!=null)
        {
         if(!item.containedItems.isEmpty())
         {
           int max=item.containedItems.size();
           for(int i=0;i<max;i++)
           {
               renumberingRepetitionsS(a,bNumber,item.containedItems.get(i));
           }
         }
        }

        //we see if this is a repetition

       if(item.repetition)
       {
        if(item.repetitionNumber==a)
        {
         item.repetitionNumber=bNumber;//we renumber the repetition
        }
       }

    }
}

public void howManyRepetitionsS(int a, SyntacticItem item)
{
  if(item!=null)
    {

     //keep going
       if(item.containedItems!=null)
        {
         if(!item.containedItems.isEmpty())
         {
           int max=item.containedItems.size();
           for(int i=0;i<max;i++)
           {
               howManyRepetitionsS(a,item.containedItems.get(i));
           }
         }
        }

        //we see if this is a repetition

       if(item.repetition)
       {
        if(item.repetitionNumber==a)
        {
         this.howManyRepetitionsWeHave++;//we count the repetitions of type a
        }
       }

    }
}

public void verifyIfRepetitionIsValid()
{

 if(this.listOfRepetitions!=null)
 {
  if(!this.listOfRepetitions.isEmpty())
  {
    int max2=this.listOfRepetitions.size();
    int max=this.listOfHubs.size();
    
    for(int j=0;j<max2;j++)
    {
      DemonstrationItem jItem=this.listOfRepetitions.get(j).item;
      

      boolean weFoundTheHub=false;
    for(int i=0;i<max;i++)
    {
      DemonstrationItem iItem=this.listOfHubs.get(i).item;
      

      if(Source.isEqualXWithY(jItem.items, iItem.items))
      {
        weFoundTheHub=true;

      }

    }
     if(!weFoundTheHub)
     {
       //if the hub doesnt exist we return a error
       System.out.println("We found a repetition without hub: "
                                    +jItem.numberOfOrderInCompressedProof);
        

     }

    }

  }
 }

}
boolean backwardChainingSearch(DemonstrationItem demonstrationItem, 
                                                         int level,int position)
{
 boolean ok=false, finishedSearch=false;int i=0;
 this.selectedDemonstrationItem0=demonstrationItem;
 if(this.stop){return ok; }
 if(demonstrationItem!=null)
 {
 do
 {
   //nameI- the name of a a,e,p from the listOfAppliedItems(List of Rules)
   //typeI - the type of a a,e,p from the listOfAppliedItems(List of Rules)
   String nameI="";
   int typeI=0;
   int maxRules=0;//number of rules in the List of Rules

        Application0 a=(Application0)Application.getInstance();
         if(a!=null)
         {
         if(a.frame0!=null)
         {

         if(a.frame0.listOfAppliedItems!=null)
         {
            maxRules=a.frame0.listOfAppliedItems.size();
          if(
             (i>-1)&
             (i<maxRules)
            )
        {
        nameI=a.frame0.listOfAppliedItems.get(i).name;
        typeI=a.frame0.listOfAppliedItems.get(i).type;
        }
        }
        }
        }
  if(i==maxRules) {break;}
  if(this.stop){return ok; }
  boolean itUnifies=false;
  if(!(nameI.equals(this.demonstrationSource.name))
     &(!this.isInLoopRules(nameI))
     )
  {
     if(this.createNewBranchBackwardChaining(demonstrationItem,nameI ,typeI ))
  {
   itUnifies=true;
   /*here the program enters if demonstrationItem unify with the i rule from
   the List of Rules */
   this.depth.add(level,i);//at level level we apply i rule
   System.out.println("We are at level:"+level+",pos:"+position+
                                    " and we have apllied:"+nameI+" order:"+i);
   /* here we discover the newly generated statements(if exists) obtained
    by applying the i rule and we put them in a collection  */
   List<DemonstrationItem> generatedStatements=new ArrayList<DemonstrationItem>();

   if ((demonstrationItem.type==DemonstrationConstants.TARGET)
       |(demonstrationItem.type==DemonstrationConstants.BASE)
       |(demonstrationItem.type==DemonstrationConstants.NEW_STATEMENT))
   {
     DemonstrationItem appliedTheoremOrAxiom=null;
     if (demonstrationItem.downLink!=null)
     {
     if (!(demonstrationItem.downLink.isEmpty()))
     {
      appliedTheoremOrAxiom=demonstrationItem.downLink.get(0);
      if(appliedTheoremOrAxiom!=null)
      {
       if((appliedTheoremOrAxiom.type==DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM)
         |(appliedTheoremOrAxiom.type==DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM)
         )
          {
           if(appliedTheoremOrAxiom.downLink!=null)
            {
            if (!(appliedTheoremOrAxiom.downLink.isEmpty()))
               {
                int max0=appliedTheoremOrAxiom.downLink.size();

                for(int c1=0;c1<max0;c1++)
                 {
                  DemonstrationItem generatedStatement=null;
                  DemonstrationItem hypothesis=null;
                  hypothesis=appliedTheoremOrAxiom.downLink.get(c1);

                  if((hypothesis.type==DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_AXIOM)
                     |(hypothesis.type==DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_THEOREM)
                    )
                   {
                    if(hypothesis.downLink!=null)
                     {
                      if(!(hypothesis.downLink.isEmpty()))
                      {
                       generatedStatement=hypothesis.downLink.get(0);
                       //if the found demonstration item is a new statement
                       //we add to the list generatedStatements
                       if(generatedStatement.type==DemonstrationConstants.NEW_STATEMENT)
                         {
                           generatedStatements.add(c1,generatedStatement);
                         }

                      }

                     }
                   }

                  

                 }

               }
            }
          }
      }

     }
     }
   }
             
   if(this.stop){return ok; }
   /*
   try
    {
        Thread.sleep(5000);
    }
    catch(InterruptedException ex)
    {
        Thread.currentThread().interrupt();
    }
   */
    
   if (generatedStatements.isEmpty())
   {
    finishedSearch=true;ok=true;
   }
   else
   {
     int max=generatedStatements.size();
     boolean allAreProved=true;
     BitSet bs=new BitSet(max);
     //here we want to pass only one time
     for(int i0=0;i0<1;i0++)
     {
       allAreProved=true;
       for(int j0=0;j0<max;j0++)
       {
        /*here we search in the generatedStatements the statement
          with the biggest number of clues
          (constants or variables that are not new)
         *
         */
         if(this.stop){return ok; }
         int index=-1;//the index were is the statement with biggest number of clues
         int maximum=-1;//the maximum number of clues
         for (int k0=0;k0<max;k0++)
         {
          if (!(bs.get(k0)))//if is not demonstated
          {
           int numberk0=this.numberOfClues(generatedStatements.get(k0));
            if (maximum<numberk0)
            {
             maximum=numberk0;
             index=k0;
            }
          }
         }

        /*we try to prove the (index) statement from the generatedStatements
         * and we mark if is a succes inf  bs array
         */
        boolean boolValue=false;
        if (index>-1)
        {
        boolValue=backwardChainingSearch
                                (generatedStatements.get(index),level+1,index);
        bs.set(index, boolValue);
        }
        
        if(!boolValue){allAreProved=false;}
       }
       //if all are proved we exit from the loop
       if(allAreProved){finishedSearch=true;ok=true;break;}
     }
     /*if it's not possible to prove all the generatedStatements
      we are moving to the next rules from the List of Rules
      */
     /*
     if(!allAreProved) 
     {
     //we delete the proof on demonstrationItem and we pass to the next rule
     this.destroyBranchBackwardChaining(demonstrationItem, true);
     this.updateStatementsAfterDestruction(demonstrationSource);
     i++;
     }
     */

   }

  }
  }
  if(!itUnifies){ i++;}
 if (i==maxRules) {break;}//we exit from the main loop
 if(this.stop){return ok; }
 }while(!finishedSearch);
 }
 
 return ok;

}

public boolean isALoopRule(String nameI,byte typeI)
{
 //here we test if an axiom or a theorem whould generate a loop in the proof

 //nameI- the name of a a,e,p from the listOfAppliedItems(List of Rules)
 //typeI - the type of a a,e,p from the listOfAppliedItems(List of Rules)
 boolean ok=false;
 DemonstrationItem demonstrationItem1=null,demonstrationItem2=null,lastItem=null;

//here we want to obtain the assertion of a the axiom or theorem
Axiom xAxiom=null;
Theorem xTheorem=null;
if (S.axioms.containsKey(nameI))
{
if (S.availableAxioms.contains(nameI))
{

xAxiom=(Axiom)S.axioms.get(nameI);
}
else   {errorNewLine("The axiom is not visible in this part of the file");}
} else
        if (S.theorems.containsKey(nameI))
        {
        if (S.availableTheorems.contains(nameI))
        {

        xTheorem=(Theorem)S.theorems.get(nameI);
        }
        else {errorNewLine("The theorem is not visible in this part of the file");}
        }
 
if (xAxiom!=null)
{
 //here we copy the items from the axiom
 demonstrationItem1= new DemonstrationItem();
 demonstrationItem1.type=DemonstrationConstants.NEW_STATEMENT;
 demonstrationItem1.items=Source.copyTheListOfConstantAndVariable(xAxiom.items);
}
 else if (xTheorem!=null)
 {
 //here we copy the items from the theorem
 demonstrationItem1= new DemonstrationItem();
 demonstrationItem1.type=DemonstrationConstants.NEW_STATEMENT;
 demonstrationItem1.items=
                       Source.copyTheListOfConstantAndVariable(xTheorem.items);
 }

//the first level of demonstration tree
 this.selectedDemonstrationItem0=demonstrationItem1;

 if(demonstrationItem1!=null)
 {
   
 
     if(this.createNewBranchBackwardChaining(demonstrationItem1,nameI ,typeI ))
  {
  
   
   /* here we discover the newly generated statements(if exists) obtained
    by applying the nameI rule and we put them in a collection  */
   List<DemonstrationItem> generatedStatements=new ArrayList<DemonstrationItem>();

   if ((demonstrationItem1.type==DemonstrationConstants.TARGET)
       |(demonstrationItem1.type==DemonstrationConstants.BASE)
       |(demonstrationItem1.type==DemonstrationConstants.NEW_STATEMENT))
   {
     DemonstrationItem appliedTheoremOrAxiom=null;
     if (demonstrationItem1.downLink!=null)
     {
     if (!(demonstrationItem1.downLink.isEmpty()))
     {
      appliedTheoremOrAxiom=demonstrationItem1.downLink.get(0);
      if(appliedTheoremOrAxiom!=null)
      {
       if((appliedTheoremOrAxiom.type==DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM)
         |(appliedTheoremOrAxiom.type==DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM)
         )
          {
           if(appliedTheoremOrAxiom.downLink!=null)
            {
            if (!(appliedTheoremOrAxiom.downLink.isEmpty()))
               {
                int max0=appliedTheoremOrAxiom.downLink.size();

                for(int c1=0;c1<max0;c1++)
                 {
                  DemonstrationItem generatedStatement=null;
                  DemonstrationItem hypothesis=null;
                  hypothesis=appliedTheoremOrAxiom.downLink.get(c1);

                  if((hypothesis.type==DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_AXIOM)
                     |(hypothesis.type==DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_THEOREM)
                    )
                   {
                    if(hypothesis.downLink!=null)
                     {
                      if(!(hypothesis.downLink.isEmpty()))
                      {
                       generatedStatement=hypothesis.downLink.get(0);
                       //if the found demonstration item is a new statement
                       //we add to the list generatedStatements
                       if(generatedStatement.type==DemonstrationConstants.NEW_STATEMENT)
                         {
                           generatedStatements.add(c1,generatedStatement);
                         }

                      }

                     }
                   }



                 }

               }
            }
          }
      }

     }
     }
   }
   int max=generatedStatements.size();
   if (max==1)
   {
     //here we catch the genereated item
     demonstrationItem2=generatedStatements.get(0);
   }

   
 }
 }
 //the second level of demonstration tree
 this.selectedDemonstrationItem0=demonstrationItem2;

 if(demonstrationItem2!=null)
 {


     if(this.createNewBranchBackwardChaining(demonstrationItem2,nameI ,typeI ))
  {


   /* here we discover the newly generated statements(if exists) obtained
    by applying the nameI rule and we put them in a collection  */
   List<DemonstrationItem> generatedStatements=new ArrayList<DemonstrationItem>();

   if ((demonstrationItem2.type==DemonstrationConstants.TARGET)
       |(demonstrationItem2.type==DemonstrationConstants.BASE)
       |(demonstrationItem2.type==DemonstrationConstants.NEW_STATEMENT))
   {
     DemonstrationItem appliedTheoremOrAxiom=null;
     if (demonstrationItem2.downLink!=null)
     {
     if (!(demonstrationItem2.downLink.isEmpty()))
     {
      appliedTheoremOrAxiom=demonstrationItem2.downLink.get(0);
      if(appliedTheoremOrAxiom!=null)
      {
       if((appliedTheoremOrAxiom.type==DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM)
         |(appliedTheoremOrAxiom.type==DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM)
         )
          {
           if(appliedTheoremOrAxiom.downLink!=null)
            {
            if (!(appliedTheoremOrAxiom.downLink.isEmpty()))
               {
                int max0=appliedTheoremOrAxiom.downLink.size();

                for(int c1=0;c1<max0;c1++)
                 {
                  DemonstrationItem generatedStatement=null;
                  DemonstrationItem hypothesis=null;
                  hypothesis=appliedTheoremOrAxiom.downLink.get(c1);

                  if((hypothesis.type==DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_AXIOM)
                     |(hypothesis.type==DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_THEOREM)
                    )
                   {
                    if(hypothesis.downLink!=null)
                     {
                      if(!(hypothesis.downLink.isEmpty()))
                      {
                       generatedStatement=hypothesis.downLink.get(0);
                       //if the found demonstration item is a new statement
                       //we add to the list generatedStatements
                       if(generatedStatement.type==DemonstrationConstants.NEW_STATEMENT)
                         {
                           generatedStatements.add(c1,generatedStatement);
                         }

                      }

                     }
                   }



                 }

               }
            }
          }
      }

     }
     }
   }
   int max=generatedStatements.size();
   if (max==1)
   {
     //here we catch the genereated item
     lastItem=generatedStatements.get(0);
   }


 }
 }
 //we check if the statement where we start is identical with last one
 if (demonstrationItem1!=null)
 {
 if (lastItem!=null)
 {
 ok=Source.isEqualXWithY(demonstrationItem1.items, lastItem.items);
 }
 }

 return ok;
}
public boolean isInLoopRules(String name)
{
 boolean ok=false;
int max=0;
max=this.loopRules.size();
for(int i=0;i<max;i++)
{
 String loopi=this.loopRules.get(i);
 
 if (loopi.equals(name))
 {
   ok=true;
   break;
 }
}

return ok;
}
//here we search the items of demonstation item for constants
//and variables that are not new (the clues)
public int numberOfClues(DemonstrationItem d)
{ int number=0;//the number of clues in a statement
  int max=d.items.size();
  for(int i=0;i<max;i++)
  {
    ConstantAndVariable cvi=d.items.get(i);
    if (cvi.constantOrVariable==1)
    {
      number++;
    }
    else if ((cvi.constantOrVariable==2)&
        (!(Source.stringStartsWith(cvi.constantOrVariableText, "$v"))))
    {
      number++;
    }
  }
  return number;
}
public void refreshTree()
{
 //here we repaint the proof tree
     
     javax.swing.JPanel basePanel=null;
     basePanel=(javax.swing.JPanel)demonstrationTree.getParent();

     if(basePanel!=null)
     {
         //redisplay demonstration
         basePanel.remove(demonstrationTree);
         //redesign base panel
         basePanel.revalidate();
         basePanel.repaint();
         basePanel.repaint();
        //restoration
         x=xBeforeDemonstration;
         y=yBeforeDemonstration;
         referenceWidth=baseWidthBeforeDemonstration;
         baseHeight=baseHeightBeforeDemonstration;
         maximumHeightBase=baseMaximumHeightBeforeDemonstration;
         //END restoration
         if (demonstrationSource!=null)
        {
           demonstrationTree=
                   createDemonstrationTree(demonstrationSource);
           Dimension d2=demonstrationTree.getPreferredSize();
            width1=d2.width;
            height1=d2.height;

              x=xMargin;
              y=y+maximumHeightBase+ySpace;
              maximumHeightBase=height1;

          if (y+height1+ySpace>baseHeight)
        {
              int dist=y+height1+ySpace-baseHeight;
            baseHeight=baseHeight+dist;
        }

         int width=0;
         width=referenceWidth;
         if (referenceWidth<width1) width=width1;

          basePanel.setPreferredSize(
               new Dimension(width+10*xSpaceRight,baseHeight));
          basePanel.add(demonstrationTree);
          demonstrationTree.setBounds(x, y, width1,height1);

          if(height1>maximumHeightBase) maximumHeightBase=height1;
           x=x+width1+xSpace;
         //redesign base panel
         basePanel.revalidate();
         basePanel.repaint();
         basePanel.repaint();

     }

     }
            
   //END display
   
   try
    {
        Thread.sleep(5000);
    }
    catch(InterruptedException ex)
    {
        Thread.currentThread().interrupt();
    }
      
    
}
public void proofFindingFunction()
{
ProofFindingThread f1=new ProofFindingThread(this);
RefreshThread f2=new RefreshThread(this);
f1.start();
f2.start();
}
public void stopFunction()
{
  this.stop=true;//we stop the automated search
}
public void displaySyntax(SyntacticItem syn)
{

 if (syn!=null)
 {
   System.out.print(" { ");
     if (syn.containedItems!=null)
     {
       if(!syn.containedItems.isEmpty())
       {
           int max=syn.containedItems.size();
           for(int i=0;i<max;i++)
           {
             displaySyntax(syn.containedItems.get(i));
           }

       }
     }

  System.out.print(" ["+syn.hub
          +"|"+syn.repetition
          +"|"+syn.repetitionNumber
          +":"+syn.definitionName+"] } ");


 }


}

public void cleanTheProof()
{
  // do the cleaning before a new crossing
    numberOfHubs=0;
    numberOfHubs2=0;
    listOfHubs.clear();
    listOfRepetitions.clear();
    vector_a_p.clear();


    findHubsAndRepetitions(demonstrationSource);
    recoverNewHubs();
    verifyIfRepetitionIsValid();
    crossingRenumberingHubs(demonstrationSource);
    reupdateDemonstrationTree();

                  
}

}