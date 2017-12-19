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
import spv.gen.DemonstrationItem;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;


public class StatementsListWindow extends javax.swing.JDialog {

    
    public StatementsListWindow(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        list.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        list.getColumnModel().getColumn(0).setPreferredWidth(50);
        list.getColumnModel().getColumn(1).setPreferredWidth(65);
        list.getColumnModel().getColumn(2).setPreferredWidth(600);
        
       }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        list = new javax.swing.JTable();
        selectButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(spv.Application0.class).getContext().getResourceMap(StatementsListWindow.class);
        setTitle(resourceMap.getString("List of Rules.title")); // NOI18N
        setBackground(resourceMap.getColor("List of Rules.background")); // NOI18N
        setName("List of Rules"); // NOI18N
        setResizable(false);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        list.setForeground(new java.awt.Color(0, 51, 255));
        list.setModel(new ListModel());
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
                    .addComponent(selectButton))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(selectButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_listVetoableChange

     
   
    }//GEN-LAST:event_listVetoableChange

    private void selectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectButtonActionPerformed

         Application0 a=(Application0)Application.getInstance();

       if (a.frame0!=null)
       {
       if (a.frame0.currentDemonstrationEditor!=null)
       {
         if(a.frame0.currentDemonstrationEditor.demonstrationStrategyType==1)
         {
          DemonstrationItem item=new DemonstrationItem();
          item.name="$a"+a.frame0.currentDemonstrationEditor.numberOfOrderNewStatements;
          a.frame0.currentDemonstrationEditor.numberOfOrderNewStatements++;
          item.type=DemonstrationConstants.NEW_STATEMENT;
          item.items=Source.copyTheListOfConstantAndVariable(a.frame0.currentDemonstrationEditor.
                             listOfStatements.get(this.selectedRow).items);

          a.frame0.currentDemonstrationEditor.demonstrationSource.downLink.add(item);
         
          
          a.frame0.currentDemonstrationEditor.verifyRepetition
                                     (a.frame0.currentDemonstrationEditor.demonstrationSource);
          a.frame0.currentDemonstrationEditor.reupdateDemonstrationTree();
          
         }
       else if(a.frame0.currentDemonstrationEditor.demonstrationStrategyType==0)
        {

        Map<String,List<ConstantAndVariable>>   variableAndContent=null;
        variableAndContent= new java.util.HashMap<String,java.util.List<ConstantAndVariable>>();
        

        //we verify if selectedRow unify with selectedDemonstrationItem
        //selectedRow behave like an hypothesis
        if (
            a.frame0.currentDemonstrationEditor.S.unifyTemplateAssumptionWithBase
            (a.frame0.currentDemonstrationEditor.
            listOfStatements.get(this.selectedRow).items,
            a.frame0.currentDemonstrationEditor.selectedDemonstrationItem.items,
             variableAndContent)
           )
        {

         //we solve 'the equations' of variables from the substitution variableAndContent
         variableAndContent=Source.resolveVariableContent(variableAndContent,
                                                   a.frame0.currentDemonstrationEditor);
         
         a.frame0.currentDemonstrationEditor.verifyRepetition
                                     (a.frame0.currentDemonstrationEditor.demonstrationSource);
          a.frame0.currentDemonstrationEditor.reupdateDemonstrationTree();
          if(a.frame0.currentDemonstrationEditor.haveSolutionBackwardChaining())
          {
             JOptionPane.showMessageDialog
                    (null,
                     "You have found a proof using Backward Chaining Strategy!",
                     "Congratulations!",
                     JOptionPane.INFORMATION_MESSAGE
                     );
          }
         }
        
        }
         //we exit from window
         this.dispose();
       }
       }



    }//GEN-LAST:event_selectButtonActionPerformed

   
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                StatementsListWindow dialog = new StatementsListWindow(new javax.swing.JFrame(), true);
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
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable list;
    public javax.swing.JButton selectButton;
    // End of variables declaration//GEN-END:variables

      
   public MainWindow frame=null;
  public class ListModel extends AbstractTableModel
  {
      List<List<String>> table=null;
      String[] namesColumns={"No.","Name","Content"};

      
      public ListModel()
      {
          table= new ArrayList<List<String>>();
          Application0 a=(Application0)Application.getInstance();
         
          
    if (a.frame0!=null)
       {
       if (a.frame0.currentDemonstrationEditor!=null)
       {
         a.frame0.currentDemonstrationEditor.listOfStatements.clear();
         a.frame0.currentDemonstrationEditor.findsStatements(
                          a.frame0.currentDemonstrationEditor.demonstrationSource);
                            
          if (!a.frame0.currentDemonstrationEditor.listOfStatements.isEmpty())
          { int i1=0;int max1=a.frame0.currentDemonstrationEditor.listOfStatements.size();
               while(i1<max1)
               {
                List<String> line=new ArrayList<String>();
                
                String content="";

                 List<ConstantAndVariable>
                       content0=a.frame0.currentDemonstrationEditor.
                                               listOfStatements.get(i1).items;
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
                   

                
               
               //we create i line from the list
                line.add(""+i1);
                line.add(a.frame0.currentDemonstrationEditor.listOfStatements.get(i1).name);
                content="<HTML>"
                 +"<base href='file:"+a.path+"/symbols/  '/>"
         +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
                 +"<FONT COLOR='#ffffff' SIZE=3 >"
                +content
                +"</FONT>"
                +"</P>"
                +"</HTML>";
                line.add(content);i1++;
                //we add i line to the table(list)
                table.add(line);
               }
           
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
          return 3;
      }

      public int  getRowCount()
      {
          return table.size();
      }
      @Override
      public String getColumnName(int coloana)
      {
          return namesColumns[coloana];
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
                 
                 this.selectButton.setEnabled(true);
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