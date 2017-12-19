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
import javax.swing.JFrame;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import org.jdesktop.application.Application;
import spv.gen.DemonstrationConstants;
import spv.gen.Hypothesis;
import spv.gen.Axiom;
import spv.gen.Theorem;
import spv.gen.ConstantAndVariable;
import javax.swing.JTable;


public class VariablesListWindow extends javax.swing.JDialog {

    
    public VariablesListWindow(java.awt.Frame parent, boolean modal) {
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
        editButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(spv.Application0.class).getContext().getResourceMap(VariablesListWindow.class);
        setTitle(resourceMap.getString("List of Rules.title")); // NOI18N
        setBackground(resourceMap.getColor("List of Rules.background")); // NOI18N
        setName("List of Rules"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

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

        editButton.setForeground(resourceMap.getColor("editButton.foreground")); // NOI18N
        editButton.setText(resourceMap.getString("editButton.text")); // NOI18N
        editButton.setEnabled(false);
        editButton.setName("editButton"); // NOI18N
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        okButton.setForeground(resourceMap.getColor("okButton.foreground")); // NOI18N
        okButton.setText(resourceMap.getString("okButton.text")); // NOI18N
        okButton.setName("okButton"); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(editButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 742, Short.MAX_VALUE)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton)
                    .addComponent(okButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_listVetoableChange

     
   
    }//GEN-LAST:event_listVetoableChange

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
     Application0 a=(Application0)Application.getInstance();


         if (a!=null)
         {
          if (a.frame0!=null)
         {
          if (a.frame0.currentDemonstrationEditor!=null)
          {
            
            a.frame0.currentDemonstrationEditor.verifyRepetition
                                     (a.frame0.currentDemonstrationEditor.demonstrationSource);
            a.frame0.currentDemonstrationEditor.reupdateDemonstrationTree();
          }
          }
         }


        this.dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed

            Application0 a=(Application0)Application.getInstance();

        if (selectedRow>-1)
        {
         if (a!=null)
         {
          if (a.frame0!=null)
         {
          if (a.frame0.currentDemonstrationEditor!=null)
         {
          if (a.frame0.currentDemonstrationEditor.newVariableAndContent!=null)
         {
              String name= variablesList.get(selectedRow);
              List<ConstantAndVariable> variableContent=null;
              String classOfVariable="";
              if (name!=null)
              {
                  variableContent=a.frame0.currentDemonstrationEditor.newVariableAndContent.get(name);
                  classOfVariable=a.frame0.currentDemonstrationEditor.newVariableAndClass.get(name);
                   if  (variableContent==null)
                   {
                     variableContent=new ArrayList<ConstantAndVariable>();
                   }
                  //we mark the selected variable
                  a.frame0.currentDemonstrationEditor.nameOfSelectedVariable=name;
                  a.frame0.currentDemonstrationEditor.contentOfSelectedVariable=variableContent;
                  a.frame0.currentDemonstrationEditor.classOfSelectedVariable=classOfVariable;
                  
                  JFrame mainFrame = Application0.getApplication().getMainFrame();
                  VariableEditorWindow variableEditorWindow =
                                     new VariableEditorWindow(mainFrame,true);
                  Application0.getApplication().show(variableEditorWindow);
                   list.clearSelection();
                   list.setModel(new ListModel0());
                   list.revalidate();
                   list.repaint();
                   
                 
              }

         }

         }
         }
         }

        }



    }//GEN-LAST:event_editButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
        Application0 a=(Application0)Application.getInstance();


         if (a!=null)
         {
          if (a.frame0!=null)
         {
          if (a.frame0.currentDemonstrationEditor!=null)
          {

            a.frame0.currentDemonstrationEditor.verifyRepetition
                                     (a.frame0.currentDemonstrationEditor.demonstrationSource);
            a.frame0.currentDemonstrationEditor.reupdateDemonstrationTree();
          }
          }
         }
    }//GEN-LAST:event_formWindowClosed

   
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VariablesListWindow dialog = new VariablesListWindow(new javax.swing.JFrame(), true);
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
    public javax.swing.JButton editButton;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable list;
    public javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables

   
   //the vector where we find the names of the variables in a demonstration
   public List<String> variablesList=new ArrayList<String>();
   
  
  public class ListModel0 extends AbstractTableModel
  {
      List<List<String>> table=null;
      String[] namesColumns={"No.","Class","Name","Content"};

      
      public ListModel0()
      {
          table= new ArrayList<List<String>>();
          Application0 a=(Application0)Application.getInstance();
          Iterator<String> variablesIterator=null;
          
          if (a.frame0!=null)
          {
          
           if (a.frame0.currentDemonstrationEditor!=null)
             {
              if(a.frame0.currentDemonstrationEditor.newVariableAndClass!=null)
              {
                if(a.frame0.currentDemonstrationEditor.newVariableAndClass.keySet()!=null)
              {
          if(a.frame0.currentDemonstrationEditor.newVariableAndClass.keySet().iterator()!=null)
              {
            variablesIterator=a.frame0.currentDemonstrationEditor.newVariableAndClass.keySet().iterator();
              }
              }
              }
             }
          
          }
                  
          if (variablesIterator!=null)
          { int i1=0;
               while(variablesIterator.hasNext())
               {
                List<String> line=new ArrayList<String>();
                String item=variablesIterator.next();
                boolean isInStatement=false;
                if(a!=null)
                {
                 if(a.frame0!=null)
                {
                 if(a.frame0.currentDemonstrationEditor!=null)
                {
                  if(a.frame0.currentDemonstrationEditor.selectedDemonstrationItem!=null)
                 {
                   int max=a.frame0.currentDemonstrationEditor.selectedDemonstrationItem.items.size();
                  for(int i2=0;i2<max;i2++)
                  {
                   ConstantAndVariable v_i=a.frame0.currentDemonstrationEditor.
                                              selectedDemonstrationItem.items.get(i2);
                   if (v_i.constantOrVariableText.equals(item))
                   {
                    isInStatement=true;
                    break;
                   }
                  }
                 }
                }
                }
                }
                if(isInStatement)
                {
                variablesList.add(item);//we add to the variables list from the demonstration
                String name=item;
                String class0="";

                 if (a.frame0!=null)
                  {
                   if (a.frame0.currentDemonstrationEditor!=null)
                     {
                      if(a.frame0.currentDemonstrationEditor.newVariableAndClass!=null)
                      {
                       class0=a.frame0.currentDemonstrationEditor.newVariableAndClass.get(item);
                      }
                     }
                   }
                if (class0==null) class0="";
                String content="";

                if (a.frame0!=null)
                  {
                   if (a.frame0.currentDemonstrationEditor!=null)
                     {
                      if(a.frame0.currentDemonstrationEditor.newVariableAndContent!=null)
                      {
                       List<ConstantAndVariable>
                       content0=a.frame0.currentDemonstrationEditor.newVariableAndContent.get(item);
                       if (content0!=null)
                       {
                        if (!content0.isEmpty())
                        {
                           int max=content0.size();
                          for(int i=0;i<max;i++)
                          {
                           String s1=content0.get(i).constantOrVariableText,s2="";
                           if (a!=null)
                           {
                            if (a.frame0!=null)
                           {
                            if (a.frame0.source1!=null)
                           {
                            if (a.frame0.source1.htlmldefString1AsString2!=null)
                           {
                             s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
                           }
                           }
                           }
                           }
                           
                            if (s2==null) s2=s1;
                           s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
                           content=content+s2;
                          }
                        }
                       }
                      }
                     }
                   }

                
               
               //we create i line from list
                line.add(""+i1);i1++;
                line.add(class0);
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
          return 4;
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
                   
                   this.editButton.setEnabled(true);

                }
            }
       }
   }
   
  public int  selectedRow=-1;
  //we create selection model

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