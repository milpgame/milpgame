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
import java.util.ArrayList;
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


public class PropertiesWindow extends javax.swing.JDialog {

    
    public PropertiesWindow(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Application0 a=(Application0)Application.getInstance();
        if(a!=null)
        {
        if(a.frame0!=null)
        {
         if(a.frame0.nameOfSelectedItem!=null)
         {
          if(!a.frame0.nameOfSelectedItem.equals(""))
         {
         //we mention the source
         this.S=a.frame0.source1;

        scrollPane.setPreferredSize(new Dimension(650,700));
        scrollPane.setVerticalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
         //we add to the scroll pane the panel with properties of the
         //selected axiom or theorem
         scrollPane.setViewportView
                                (loadProperties(a.frame0.nameOfSelectedItem));

         }
         }
        }
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(spv.Application0.class).getContext().getResourceMap(PropertiesWindow.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        scrollPane.setAutoscrolls(true);
        scrollPane.setName("scrollPane"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PropertiesWindow dialog = new PropertiesWindow(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
Source S=null;

int xMargin=60,yMargin=10;
int xSpace=10,ySpace=10;
int xBlockMargin=2,yBlockMargin=2,xRightSpace=10;
int xBlockSpace=2,yBlockSpace=2;
int xCommentMargin=10,yCommentMargin=10;
int xCommentSpace=10,yCommentSpace=10;
int maximumCommentWidth=700;//500
int referenceWidth=800;
int chapterWidth=440;//450

int x=xMargin,y=yMargin;//absolute positioning in the base panel
//provisionally
int width1=60,height1=35;
int commentWidth,commentHeight;
int baseHeight=10;
int maximumBaseHeight=0;
//END provisionally

int windowDimesion=30;
int position=0;

boolean fullDemonstration=false;//demonstration type

//data about demonstration tree
javax.swing.JButton demonstrationSwitch=null;
javax.swing.JTree demonstrationTree=null;
DemonstrationItem demonstrationSource=null;
int xBeforeDemonstration=0,yBeforeDemonstration=0;
int baseWidthBeforeDemonstration=0,baseHeightBeforeDemonstration=0;
int baseMaximumHeightBeforeDemonstration=0;
//END data about demonstration tree

public  javax.swing.JPanel  createBase()
{
    //initialize variables
    x=xMargin;y=yMargin;//absolute positioning in the base panel
    //provisionally
    width1=60;height1=35;
    commentWidth=0;commentHeight=0;
    baseHeight=10;
    maximumBaseHeight=0;
    //END initialize variables
    javax.swing.JPanel basePanel=new javax.swing.JPanel();
     basePanel.setLayout(null);
     Dimension dimension=new Dimension();
     dimension.width=referenceWidth+xRightSpace;
     dimension.height=10;
     basePanel.setPreferredSize(dimension);
     basePanel.setBackground(new java.awt.Color(255, 255, 255));
     return basePanel;
}

   public  javax.swing.JPanel loadProperties(String foundName)
{
//we create base panel
javax.swing.JPanel basePanel=createBase();
Axiom a_x=null;
Theorem t_x=null;

if (S.axioms.containsKey(foundName))
{
if (S.availableAxioms.contains(foundName))
{

a_x=(Axiom)S.axioms.get(foundName);
}

} else
        if (S.theorems.containsKey(foundName))
        {
        if (S.availableTheorems.contains(foundName))
        {

        t_x=(Theorem)S.theorems.get(foundName);
        }
       
        }
if (t_x!=null)
 {
 this.createTheoremVisualItem(t_x,basePanel);
 }
 else if (a_x!=null)
 {
   this.createAxiomVisualItem(a_x,basePanel);
 }
 
return basePanel;
};

private  String theoremViewText(Theorem  theoremItem)
{
String pBeginning="<P> <B>   Theorem of ";
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
int dimension=theoremItem.items.size();
do
{
//we display the component items of the theorem
String s1=theoremItem.items.get(i).constantOrVariableText,s2="";
   s2=this.S.htlmldefString1AsString2.get(s1);
   if (s2==null) s2=s1;
displayedText=displayedText+" "+s2;
i++;
} while (i<dimension);
displayedText=displayedText+end;

}
}
//END theorem
}

 else displayedText="The theorem does not exists" ;
Application0 a=(Application0)Application.getInstance();
//we encapsulate in html and replace \n with <br>
displayedText="<HTML>"
          +"<base href='file:"+a.path+"/symbols/  '/>"
          +displayedText
          +" </HTML>";

return displayedText;
};
private  String axiomViewText(Axiom  axiomItem)
{
String aBeginning="<P> <B>   Axiom of ";
String end="</B> </FONT> </P>";

int i=0;
String displayedText="";

 if (axiomItem!=null)
{
//we have axiom
displayedText=aBeginning+axiomItem.name+":<br>  ";
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
displayedText=displayedText+" "+s2;
i++;
} while (i<dimension);
displayedText=displayedText+end;

}
}
//END axiom
}

 else displayedText="The axiom does not exist" ;
Application0 a=(Application0)Application.getInstance();
//we encapsulate it in html and replace \n with <br>
displayedText="<HTML>"
          +"<base href='file:"+a.path+"/symbols/  '/>"
          +displayedText
          +" </HTML>";

return displayedText;
};

private  String hypothesisViewText(Hypothesis  hypothesisItem)
{
String eBeginning="<P> <B>   Hypothesis of ";
String end="</B> </FONT> </P>";

int i=0;
String displayedText="";

 if (hypothesisItem!=null)
{
//we have hypothesis
displayedText=eBeginning+hypothesisItem.name+":<br>  ";
if (hypothesisItem.items!=null)
{
if (hypothesisItem.items.size()>0)
{
int dim=hypothesisItem.items.size();
do
{
//we display component items of the hypothesis

String s1=hypothesisItem.items.get(i).constantOrVariableText,s2="";
   s2=this.S.htlmldefString1AsString2.get(s1);
   if (s2==null) s2=s1;
displayedText=displayedText+" "+s2;
i++;
} while (i<dim);
displayedText=displayedText+end;

}
}
//END hypothesis
}

 else displayedText="The hypothesis does not exist" ;
Application0 a=(Application0)Application.getInstance();
//we encapsulate it in html and replace \n with <br>
displayedText="<HTML>"
          +"<base href='file:"+a.path+"/symbols/  '/>"
          +displayedText
          +" </HTML>";

return displayedText;
};
private  String distinctViewText(List<String>  distinct)
{

int i=0;
String displayedText="<P> <B> Distinct:<br> ";

 if (distinct!=null)
{
if (!distinct.isEmpty())
{
if (distinct.size()>0)
{
int dimension=distinct.size();
do
{
//we display component items of the list
String s1=distinct.get(i),s2="";
   s2=this.S.htlmldefString1AsString2.get(s1);
   if (s2==null) s2=s1;
displayedText=displayedText+" "+s2;
i++;
} while (i<dimension);
displayedText=displayedText+"</B> </P>";

}
}

}

 else displayedText="The list does not exist" ;
Application0 a=(Application0)Application.getInstance();
//we encapsulated it in html and replace \n with <br>
displayedText="<HTML>"
          +"<base href='file:"+a.path+"/symbols/  '/>"
          +displayedText
          +" </HTML>";

return displayedText;
};
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

public javax.swing.JLabel createLabel(String s)
{
    javax.swing.JLabel label=new javax.swing.JLabel();

    label.setBackground(new java.awt.Color(4, 255, 129));
    label.setForeground(new java.awt.Color(0,51,255));
    label.setFont(new java.awt.Font("Arial", 0, 14));
    label.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    label.setFocusCycleRoot(true);
    label.setOpaque(true);
    label.setText(s);
    label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                
            }
        });
    return label;
}

private  String variableViewText(Variable  variable)
    {
    
    String displayedText="";

     if (variable!=null)
    {
       String s1=variable.variableText,s2="";
       s2=this.S.htlmldefString1AsString2.get(s1);
       if (s2==null) s2=s1;
       displayedText=s2;

    }else displayedText="The variable does not exist" ;
    Application0 a=(Application0)Application.getInstance();
    //we encapsulate it in html and replace \n with <br>
    displayedText="<HTML>"
              +"<base href='file:"+a.path+"/symbols/  '/>"
              +displayedText
              +" </HTML>";

    return displayedText;
    };

private void createTheoremVisualItem
        (Theorem theoremItem,javax.swing.JPanel basePanel)
{
  if (theoremItem!=null)
  {
    if (theoremItem.items!=null)
    {
    if (theoremItem.items.size()>0)
    {
      
        //we verify if has hypothesis
             if (theoremItem.hypotheses!=null)
             {
             if (theoremItem.hypotheses.size()>0)
             {
               int hypothesisDimension=theoremItem.hypotheses.size();

               for (int i=0;i<hypothesisDimension;i++)
               {
                   Hypothesis iHypothesis=theoremItem.hypotheses.get(i);
                    //display i hypothesis
                    if (iHypothesis!=null)
                    {
                     javax.swing.JLabel eticheta=this.createLabel
                                (this.hypothesisViewText(iHypothesis));
                                       
                     //we attach a mouse listener
                     eticheta.addMouseListener(new java.awt.event.MouseAdapter()
                       {
                            @Override
                            public void mousePressed
                                    (java.awt.event.MouseEvent evt)
                            {
                               
                            }
                        });
                       Dimension d=eticheta.getPreferredSize();
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
                      basePanel.add(eticheta);
                      eticheta.setBounds(x, y, width1,height1);

                      if(height1>maximumBaseHeight) maximumBaseHeight=height1;
                       x=x+width1+xSpace;
                    }
                   //END display i hypothesis
               }
             }
             }

        //END verify hypotheses
        
        //we verify is has total variables
             x=xMargin+referenceWidth/2-width1/2;
             y=y+maximumBaseHeight+ySpace;
             if (theoremItem.totalVariables!=null)
             {
             if (theoremItem.totalVariables.size()>0)
             {
               int totalVariablesDimension=theoremItem.totalVariables.size();
                //display label
                 String s="<HTML>"
                     +"<FONT COLOR='#0033ff' SIZE=4>"
                     +"Variables:"
                     +"</FONT>"
                     +"</HTML>";

                   javax.swing.JLabel label=this.createCommentLabel(s);
                   Dimension d0=label.getPreferredSize();
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
                  basePanel.add(label);
                  label.setBounds(x, y, width1,height1);

                  if(height1>maximumBaseHeight) maximumBaseHeight=height1;
                   x=x+width1+xSpace;
                //END display label

               for (int i=0;i<totalVariablesDimension;i++)
               {
                   Variable iVariable=theoremItem.totalVariables.get(i);
                    //display i variable
                    if (iVariable!=null)
                    {
                     javax.swing.JLabel label0=this.createLabel
                                (this.variableViewText(iVariable));
                      
                     //we attach a mouse listener
                     label0.addMouseListener(new java.awt.event.MouseAdapter()
                       {
                            @Override
                            public void mousePressed
                                    (java.awt.event.MouseEvent evt)
                            {
                                
                            }
                        });
                       Dimension d=label0.getPreferredSize();
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
                      basePanel.add(label0);
                      label0.setBounds(x, y, width1,height1);

                      if(height1>maximumBaseHeight) maximumBaseHeight=height1;
                       x=x+width1+xSpace;
                    }
                    //END display i variable
               }
             }
             }

        //END verify total variables
        //display distinct variables
             if (theoremItem.distinct!=null)
             {
             if (theoremItem.distinct.size()>0)
             {
               int distinctDimension=theoremItem.distinct.size();

               for (int i=0;i<distinctDimension;i++)
               {

                    //display i list
                    if (theoremItem.distinct.get(i)!=null)
                    {
                      List<String> iList=theoremItem.distinct.get(i);
                     javax.swing.JLabel eticheta=this.createLabel
                     (this.distinctViewText(iList));

                       Dimension d=eticheta.getPreferredSize();
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
                      basePanel.add(eticheta);
                      eticheta.setBounds(x, y, width1,height1);

                      if(height1>maximumBaseHeight) maximumBaseHeight=height1;
                       x=x+width1+xSpace;
                    }
                   //END display i list
               }
             }
             }

       //END display distinct variables
       //display proper theorem
        if (theoremItem.items!=null)
        {
         javax.swing.JLabel label=this.createLabel
                    (this.theoremViewText(theoremItem));
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

          //display text before theorem
          
          ParsingItem parsingItem = theoremItem.sourceLabel;
          if(parsingItem!=null)
          {
          ParsingItem fatherItem=parsingItem.fatherItem;
          int position=-1;
          ParsingItem beforeItem=null;
          if(fatherItem.containedItems!=null)
          {
           position=fatherItem.containedItems.indexOf(parsingItem);
           if (position-1>=0)
           {
           beforeItem=fatherItem.containedItems.get(position-1);
           }
          }


        if (beforeItem!=null)
        {
         javax.swing.JLabel label=this.createLabel
                    (
                    "<HTML> "
                    +beforeItem.textSymbol.replaceAll('\n'+"", "<br>")
                    +" </HTML>");
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
        }
        //END text before theorem

         //display text after theorem
          ParsingItem parsingItem2=theoremItem.sourceLabel;
          if(parsingItem2!=null)
          {
          ParsingItem fatherItem2=parsingItem2.fatherItem;
          int position2=-1;
          ParsingItem afterItem=null;
          if(fatherItem2.containedItems!=null)
          {
           position2=fatherItem2.containedItems.indexOf(parsingItem);
           if (position2+1<fatherItem2.containedItems.size())
           {
           afterItem=fatherItem2.containedItems.get(position2+1);
           }
          }
          

        if (afterItem!=null)
        {
         javax.swing.JLabel label=this.createLabel
                    ("Writing date: "+afterItem.textSymbol);
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
        }
       //END display text after theorem

        

    }
    }
    }




}
private void createAxiomVisualItem(Axiom axiomItem,javax.swing.JPanel basePanel)
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
                                (this.hypothesisViewText(iHypothesis));
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

        //END verify hypotheses
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
                     javax.swing.JLabel label=this.createLabel
                                (this.variableViewText(iVariable));
                                           
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

        //END verify total variables
        //display distinct variables
             if (axiomItem.distinct!=null)
             {
             if (axiomItem.distinct.size()>0)
             {
               int distinctDimension=axiomItem.distinct.size();

               for (int i=0;i<distinctDimension;i++)
               {

                    //display i list
                    if (axiomItem.distinct.get(i)!=null)
                    {
                      List<String> iList=axiomItem.distinct.get(i);
                     javax.swing.JLabel label=this.createLabel
                     (this.distinctViewText(iList));

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

       //END display distinct variables
       //display proper axiom
        if (axiomItem.items!=null)
        {
         javax.swing.JLabel label=this.createLabel
                    (this.axiomViewText(axiomItem));
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
       //END diplay proper axiom

      //display text before axiom
          ParsingItem parsingItem=axiomItem.sourceLabel;
          if(parsingItem!=null)
          {
          ParsingItem fatherItem=parsingItem.fatherItem;
          int position0=-1;
          ParsingItem beforeItem=null;
          if(fatherItem.containedItems!=null)
          {
           position0=fatherItem.containedItems.indexOf(parsingItem);
           if (position0-1>=0)
           {
           beforeItem=fatherItem.containedItems.get(position0-1);
           }
          }


        if (beforeItem!=null)
        {
         javax.swing.JLabel label0=this.createLabel
                    (
                     "<HTML>"+
                     beforeItem.textSymbol.replaceAll('\n'+"", "<br>")
                     +"</HTML>"
                     );
           Dimension d=label0.getPreferredSize();
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
          basePanel.add(label0);
          label0.setBounds(x, y, width1,height1);

          if(height1>maximumBaseHeight) maximumBaseHeight=height1;
           x=x+width1+xSpace;
        }
        }
       //END display text before axiom

         
    }
    }

  }

}


}