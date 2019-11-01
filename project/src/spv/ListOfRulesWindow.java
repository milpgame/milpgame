//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv;
import javax.swing.table.AbstractTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.util.List;
import java.util.ArrayList;
import org.jdesktop.application.Application;
import spv.gen.DemonstrationConstants;
import spv.gen.Hypothesis;
import spv.gen.Axiom;
import spv.gen.Theorem;
import javax.swing.JFrame;
import javax.swing.JTable;


public class ListOfRulesWindow extends javax.swing.JDialog {

    
    public ListOfRulesWindow(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        list.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        list.getColumnModel().getColumn(0).setPreferredWidth(50);
        list.getColumnModel().getColumn(1).setPreferredWidth(65);
        list.getColumnModel().getColumn(2).setPreferredWidth(90);
        list.getColumnModel().getColumn(3).setPreferredWidth(600);
       }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        list = new javax.swing.JTable();
        selectButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        moveUpButton = new javax.swing.JButton();
        moveDownButton = new javax.swing.JButton();
        deleteAllButton = new javax.swing.JButton();
        propertiesButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(spv.Application0.class).getContext().getResourceMap(ListOfRulesWindow.class);
        setTitle(resourceMap.getString("List of Rules.title")); // NOI18N
        setBackground(resourceMap.getColor("List of Rules.background")); // NOI18N
        setName("List of Rules"); // NOI18N
        setResizable(false);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        list.setForeground(new java.awt.Color(0, 51, 255));
        list.setModel(new ListModel0());
        list.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        list.setInheritsPopupMenu(true);
        list.setName("list"); // NOI18N
        list.setSelectionModel(new ListSelectionModel0());
        list.getTableHeader().setReorderingAllowed(false);
        list.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                listVetoableChange(evt);
            }
        });
        jScrollPane1.setViewportView(list);

        selectButton.setForeground(resourceMap.getColor("selectButton.foreground")); // NOI18N
        selectButton.setText(resourceMap.getString("selectButton.text")); // NOI18N
        selectButton.setEnabled(false);
        selectButton.setName("selectButton"); // NOI18N
        selectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectButtonActionPerformed(evt);
            }
        });

        deleteButton.setForeground(resourceMap.getColor("deleteButton.foreground")); // NOI18N
        deleteButton.setText(resourceMap.getString("deleteButton.text")); // NOI18N
        deleteButton.setEnabled(false);
        deleteButton.setName("deleteButton"); // NOI18N
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        moveUpButton.setForeground(resourceMap.getColor("moveUpButton.foreground")); // NOI18N
        moveUpButton.setText(resourceMap.getString("moveUpButton.text")); // NOI18N
        moveUpButton.setEnabled(false);
        moveUpButton.setName("moveUpButton"); // NOI18N
        moveUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveUpButtonActionPerformed(evt);
            }
        });

        moveDownButton.setForeground(resourceMap.getColor("moveDownButton.foreground")); // NOI18N
        moveDownButton.setText(resourceMap.getString("moveDownButton.text")); // NOI18N
        moveDownButton.setEnabled(false);
        moveDownButton.setName("moveDownButton"); // NOI18N
        moveDownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveDownButtonActionPerformed(evt);
            }
        });

        deleteAllButton.setForeground(resourceMap.getColor("deleteAllButton.foreground")); // NOI18N
        deleteAllButton.setText(resourceMap.getString("deleteAllButton.text")); // NOI18N
        deleteAllButton.setName("deleteAllButton"); // NOI18N
        deleteAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAllButtonActionPerformed(evt);
            }
        });

        propertiesButton.setForeground(resourceMap.getColor("propertiesButton.foreground")); // NOI18N
        propertiesButton.setText(resourceMap.getString("propertiesButton.text")); // NOI18N
        propertiesButton.setName("propertiesButton"); // NOI18N
        propertiesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                propertiesButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(selectButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteAllButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moveUpButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moveDownButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(propertiesButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectButton)
                    .addComponent(deleteButton)
                    .addComponent(deleteAllButton)
                    .addComponent(moveUpButton)
                    .addComponent(moveDownButton)
                    .addComponent(propertiesButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_listVetoableChange

     
      
    }//GEN-LAST:event_listVetoableChange

    private void selectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectButtonActionPerformed

            Application0 a=(Application0)Application.getInstance();
         if(a!=null)
         {
         if(a.frame0!=null)
         {
         
         if(a.frame0.listOfAppliedItems!=null)
         {
             int max=a.frame0.listOfAppliedItems.size();
          if(
             (selectedRow>-1)&
             (selectedRow<max)
            )
        {
        a.frame0.nameOfMemorizedItem=
           a.frame0.listOfAppliedItems.get(selectedRow).name;
        a.frame0.typeOfMemorizedItem=
           a.frame0.listOfAppliedItems.get(selectedRow).type;
        
        String header="",name=a.frame0.nameOfMemorizedItem;
             if (
                 (a.frame0.typeOfMemorizedItem==DemonstrationConstants.SIMPLE_THEOREM)|
                 (a.frame0.typeOfMemorizedItem==DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM)
                )
             {
                 header = "Theorem of ";
             }
             else
                if (
                 (a.frame0.typeOfMemorizedItem==DemonstrationConstants.SIMPLE_AXIOM)|
                 (a.frame0.typeOfMemorizedItem==DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM)
                )
             {
                 header = "Axiom of ";

             }
           else
                if (
                 (a.frame0.typeOfMemorizedItem==DemonstrationConstants.PROPER_VARIABLE)
                )
             {
                 header = "Variabile of ";

             }
            else
                if (
                 (a.frame0.typeOfMemorizedItem==DemonstrationConstants.PROPER_HYPOTHESIS)
                )
             {
                 header = "Hypothesis of ";

             }
        String text2="<HTML>Apply Rule: <br>"
                      +header
                      +name
                      +"</HTML>";
       a.frame0.branchCreationButton.setText(text2);
       this.selectButton.setEnabled(false);
       this.deleteButton.setEnabled(false);
       this.moveUpButton.setEnabled(false);
       this.moveDownButton.setEnabled(false);
       this.list.clearSelection();
       this.dispose();//we close the window after selection
       
       }
       }
       
       }
       }
    }//GEN-LAST:event_selectButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
            
          Application0 a=(Application0)Application.getInstance();
         if(a!=null)
         {
         if(a.frame0!=null)
         {
         
         if(a.frame0.listOfAppliedItems!=null)
         {
             int max=a.frame0.listOfAppliedItems.size();
          if(
             (selectedRow>-1)&
             (selectedRow<max)
            )
        {
         a.frame0.listOfAppliedItems.remove(selectedRow);
         this.list.clearSelection();
         selectedRow=-1;
         this.selectButton.setEnabled(false);
         this.deleteButton.setEnabled(false);
         this.moveUpButton.setEnabled(false);
         this.moveDownButton.setEnabled(false);
         this.list.setModel(new ListModel0());
         this.list.repaint();
        }
        }
        
        }
        }

    }//GEN-LAST:event_deleteButtonActionPerformed

    private void moveUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveUpButtonActionPerformed
       
        Application0 a=(Application0)Application.getInstance();
         if(a!=null)
         {
         if(a.frame0!=null)
         {
         
         if(a.frame0.listOfAppliedItems!=null)
         {
             int max=a.frame0.listOfAppliedItems.size();
          if(
             (selectedRow>0)&
             (selectedRow<max)
            )
        {
          String selectedName="",nameOfHeader="";
          int    selectedType=0,typeOfHeader=0;
        AppliedItem selectedItem=new AppliedItem(),headerItem=new AppliedItem();
         selectedName=
             a.frame0.listOfAppliedItems.get(selectedRow).name;
         selectedType=
             a.frame0.listOfAppliedItems.get(selectedRow).type;
         nameOfHeader=
           a.frame0.listOfAppliedItems.get(selectedRow-1).name;
         typeOfHeader=
           a.frame0.listOfAppliedItems.get(selectedRow-1).type;
         selectedItem.name=selectedName;
         selectedItem.type=selectedType;

         headerItem.name=nameOfHeader;
         headerItem.type=typeOfHeader;
          a.frame0.listOfAppliedItems.set(selectedRow-1, selectedItem);
          a.frame0.listOfAppliedItems.set(selectedRow, headerItem);
         
         selectedRow=-1;
         this.selectButton.setEnabled(false);
         this.deleteButton.setEnabled(false);
         this.moveUpButton.setEnabled(false);
         this.moveDownButton.setEnabled(false);
         this.list.setModel(new ListModel0());
         this.list.repaint();
        }
        }
        
        }
        }
        

    }//GEN-LAST:event_moveUpButtonActionPerformed

    private void moveDownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveDownButtonActionPerformed

     Application0 a=(Application0)Application.getInstance();
         if(a!=null)
         {
         if(a.frame0!=null)
         {
         
         
         if(a.frame0.listOfAppliedItems!=null)
         {
             int max=a.frame0.listOfAppliedItems.size();
          if(
             (selectedRow>-1)&
             (selectedRow+1<max)
            )
        {
          String selectedName="",lastName="";
          int    selectedType=0,lastType=0;
        AppliedItem selectedItem=new AppliedItem(),lastItem=new AppliedItem();
         selectedName=
             a.frame0.listOfAppliedItems.get(selectedRow).name;
         selectedType=
             a.frame0.listOfAppliedItems.get(selectedRow).type;
         lastName=
           a.frame0.listOfAppliedItems.get(selectedRow+1).name;
         lastType=
           a.frame0.listOfAppliedItems.get(selectedRow+1).type;
         selectedItem.name=selectedName;
         selectedItem.type=selectedType;

         lastItem.name=lastName;
         lastItem.type=lastType;
          a.frame0.listOfAppliedItems.set(selectedRow+1, selectedItem);
          a.frame0.listOfAppliedItems.set(selectedRow, lastItem);
         
         selectedRow=-1;
         this.selectButton.setEnabled(false);
         this.deleteButton.setEnabled(false);
         this.moveUpButton.setEnabled(false);
         this.moveDownButton.setEnabled(false);
         this.list.setModel(new ListModel0());
         this.list.repaint();
        }
        }
        
        }
        }


    }//GEN-LAST:event_moveDownButtonActionPerformed

    private void deleteAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAllButtonActionPerformed

         Application0 a=(Application0)Application.getInstance();
         if(a!=null)
         {
         if(a.frame0!=null)
         {

         if(a.frame0.listOfAppliedItems!=null)
         {
          
         a.frame0.listOfAppliedItems.clear();
         this.selectButton.setEnabled(false);
         this.deleteButton.setEnabled(false);
         this.moveUpButton.setEnabled(false);
         this.moveDownButton.setEnabled(false);
         this.list.setModel(new ListModel0());
         this.list.repaint();
        
        }

        }
        }
        
    }//GEN-LAST:event_deleteAllButtonActionPerformed

    private void propertiesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_propertiesButtonActionPerformed

        Application0 a=(Application0)Application.getInstance();
       if(selectedRow>-1)
       {
       if(a!=null)
       {
       if(a.frame0!=null)
       {
       if(a.frame0.listOfAppliedItems!=null)
       {
        a.frame0.nameOfSelectedItem=
                        a.frame0.listOfAppliedItems.get(selectedRow).name;

        JFrame mainFrame = Application0.getApplication().getMainFrame();
        PropertiesWindow propertiesWindow = new PropertiesWindow(mainFrame,true);
        propertiesWindow.setLocationRelativeTo(mainFrame);

        Application0.getApplication().show(propertiesWindow);
           }
           }
           }
           }
    }//GEN-LAST:event_propertiesButtonActionPerformed

   
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ListOfRulesWindow dialog = new ListOfRulesWindow(new javax.swing.JFrame(), true);
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
    public javax.swing.JButton deleteAllButton;
    public javax.swing.JButton deleteButton;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable list;
    public javax.swing.JButton moveDownButton;
    public javax.swing.JButton moveUpButton;
    private javax.swing.JButton propertiesButton;
    public javax.swing.JButton selectButton;
    // End of variables declaration//GEN-END:variables

   public MainWindow frame=null;
  public class ListModel0 extends AbstractTableModel
  {
      List<List<String>> table=null;
      String[] namesColumns={"No.","Type","Name","Content","Priority"};

      
      public ListModel0()
      {
          table= new ArrayList<List<String>>();
          Application0 a=(Application0)Application.getInstance();
          List<AppliedItem> applicable=null;
          if (a.frame0!=null)
          {
          
           if (a.frame0.listOfAppliedItems!=null)
             {
               applicable=a.frame0.listOfAppliedItems;
             }
          
          }
                  
          if (applicable!=null)
          {
           if (!applicable.isEmpty())
           {
               int max=applicable.size();
               for (int i=0;i<max;i++)
               {
                List<String> line=new ArrayList<String>();
                AppliedItem item=applicable.get(i);
                int type=item.type;
                String name=item.name;
                String displayedType="";
                String content="";
                String priority0=String.valueOf(item.priority);

                if (type==DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM)
                {
                    displayedType="Complex axiom";
                    if (a.frame0.source1.axioms.containsKey(name))
                    {
                      Axiom ax=(Axiom)a.frame0.source1.axioms.get(name);
                      if (ax.items.size()>0)
                        {
                        int j=0;
                        do
                        {
                        //we display component items of the axiom
                        //display htmldef
                        String s1=ax.items.get(j).constantOrVariableText,s2="";
                           s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
                            if (s2==null) s2=s1;
                           s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
                        //END display htmldef
                        content=content+" "+s2;
                        j++;
                        } while (j<ax.items.size());
                        }
                    }
                }
                else if (type==DemonstrationConstants.SIMPLE_AXIOM)
                {
                    displayedType="Simple axiom";
                    if (a.frame0.source1.axioms.containsKey(name))
                    {
                      Axiom ax=(Axiom)a.frame0.source1.axioms.get(name);
                      if (ax.items.size()>0)
                        {
                        int j=0;
                        do
                        {
                        //we display component items of the axiom
                        //display htmldef
                        String s1=ax.items.get(j).constantOrVariableText,s2="";
                           s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
                            if (s2==null) s2=s1;
                           s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
                        //END display htmldef
                        content=content+" "+s2;
                        j++;
                        } while (j<ax.items.size());
                        }
                    }
                }
                else if (type==DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM)
                {
                    displayedType="Complex theorem";
                    if (a.frame0.source1.theorems.containsKey(name))
                    {
                      Theorem tx=(Theorem)a.frame0.source1.theorems.get(name);
                      if (tx.items.size()>0)
                        {
                        int j=0;
                        do
                        {
                        //we display component items of the theorem
                        //display htmldef
                        String s1=tx.items.get(j).constantOrVariableText,s2="";
                           s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
                            if (s2==null) s2=s1;
                           s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
                        //END display htmldef
                        content=content+" "+s2;
                        j++;
                        } while (j<tx.items.size());
                        }
                    }
                }
                else if (type==DemonstrationConstants.SIMPLE_THEOREM)
                {
                    displayedType="Simple theorem";
                    if (a.frame0.source1.theorems.containsKey(name))
                    {
                      Theorem tx=(Theorem)a.frame0.source1.theorems.get(name);
                      if (tx.items.size()>0)
                        {
                        int j=0;
                        do
                        {
                        //we display component items of the theorem
                        //display htmldef
                        String s1=tx.items.get(j).constantOrVariableText,s2="";
                           s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
                            if (s2==null) s2=s1;
                           s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
                        //END display htmldef
                        content=content+" "+s2;
                        j++;
                        } while (j<tx.items.size());
                        }
                    }
                }
                else if (type==DemonstrationConstants.PROPER_HYPOTHESIS)
                {
                 displayedType="Hypothesis";
              
                if (a!=null)
                 {
                 if (a.frame0!=null)
                 {
                 if (a.frame0.source1!=null)
                 {
                 if(a.frame0.source1.hypotheses!=null)
                 {
                 if(a.frame0.source1.hypotheses.get(name)!=null)
                 {
                 Hypothesis xHypothesis=(Hypothesis)a.frame0.source1.hypotheses.get(name);
                 if
                   (xHypothesis!=null)
                 {
                if (xHypothesis.items.size()>0)
                {
                int j=0;
                do
                {
                 //we display component items of the hypothesis
                 //display htmldef
                String s1=xHypothesis.items.get(j).constantOrVariableText,s2="";
                   s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
                    if (s2==null) s2=s1;
                   s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
                //END display htmldef
                content=content+" "+s2;
                j++;
                } while (j<xHypothesis.items.size());
                
                }
                }
                }
                }
                }
                }
                }

                }
               
                //we create i line from the list
                line.add(""+i);
                line.add(displayedType);
                line.add(name);
                content="<HTML>"
                 +"<base href='file:"+a.path+"/symbols/  '/>"
                 +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
                 +"<FONT COLOR='#ffffff' SIZE=3 >"
                +content
                +"</FONT>"
                +"</P>"
                +"</HTML>";
                line.add(content);
                line.add(priority0);
                //we add i line to the table(list)
                table.add(line);
               }
           }
          }

          
      }
      public String getValueAt(int line,int column)
      {
          String value="";
          List<String> xLine=null;
          if ((line>=0)&(line<table.size()))
          {
            xLine=table.get(line);
            if (xLine!=null)
            {
                if ((column>=0)&(column<xLine.size()))
                 {
                  value=xLine.get(column);
                 }
            }

          }
          return value;
      }

      public int getColumnCount()
      {
          return 5;
      }

      public int  getRowCount()
      {
          return table.size();
      }
      @Override
      public String getColumnName(int column)
      {
          return namesColumns[column];
      }
      
  }
   public class ListSelectionListener0
           implements ListSelectionListener
    {

        public void valueChanged(ListSelectionEvent e)
        {
         pressedRowList(e);
        }

    }

   void pressedRowList(ListSelectionEvent e)
   {
       int max=list.getRowCount();
     ListSelectionModel lsm = (ListSelectionModel)e.getSource();
       if (!lsm.isSelectionEmpty())
       {
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++)
            {
                if (lsm.isSelectedIndex(i))
                {
                   selectedRow=i;
                   
                   this.selectButton.setEnabled(true);
                   this.deleteButton.setEnabled(true);
                   if(selectedRow==0)
                   {
                       this.moveUpButton.setEnabled(false);
                   }
                   else
                   {
                     this.moveUpButton.setEnabled(true);
                   }

                   if(selectedRow+1==max)
                   {
                      this.moveDownButton.setEnabled(false);
                   }
                   else
                   {
                    this.moveDownButton.setEnabled(true);
                   }
                
                }
            }
       }
           
   }
   
  public int  selectedRow=-1;
  //creem modelul de selectie//we create selection model
  public class  ListSelectionModel0 extends DefaultListSelectionModel
  {
      public ListSelectionModel0()
      {
         super();
         this.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
         this.addListSelectionListener(new ListSelectionListener0() );
        
         
        
      }
  }
   //END create model

}