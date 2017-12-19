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

public class SyntaxWindow extends javax.swing.JDialog {

   
    public SyntaxWindow(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        wffList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        wffList.getColumnModel().getColumn(0).setPreferredWidth(50);
        wffList.getColumnModel().getColumn(1).setPreferredWidth(65);
        wffList.getColumnModel().getColumn(2).setPreferredWidth(400);

        classList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        classList.getColumnModel().getColumn(0).setPreferredWidth(50);
        classList.getColumnModel().getColumn(1).setPreferredWidth(65);
        classList.getColumnModel().getColumn(2).setPreferredWidth(400);

        setList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        setList.getColumnModel().getColumn(0).setPreferredWidth(50);
        setList.getColumnModel().getColumn(1).setPreferredWidth(65);
        setList.getColumnModel().getColumn(2).setPreferredWidth(400);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        classList = new javax.swing.JTable();
        wffLabel = new javax.swing.JLabel();
        classLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        wffList = new javax.swing.JTable();
        setLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        setList = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(spv.Application0.class).getContext().getResourceMap(SyntaxWindow.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        classList.setModel(new ClassListModel());
        classList.setName("classList"); // NOI18N
        jScrollPane1.setViewportView(classList);

        wffLabel.setForeground(resourceMap.getColor("wffLabel.foreground")); // NOI18N
        wffLabel.setText(resourceMap.getString("wffLabel.text")); // NOI18N
        wffLabel.setName("wffLabel"); // NOI18N

        classLabel.setForeground(resourceMap.getColor("classLabel.foreground")); // NOI18N
        classLabel.setText(resourceMap.getString("classLabel.text")); // NOI18N
        classLabel.setName("classLabel"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        wffList.setModel(new WffListModel());
        wffList.setName("wffList"); // NOI18N
        jScrollPane2.setViewportView(wffList);

        setLabel.setForeground(resourceMap.getColor("setLabel.foreground")); // NOI18N
        setLabel.setText(resourceMap.getString("setLabel.text")); // NOI18N
        setLabel.setName("setLabel"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        setList.setModel(new SetListModel());
        setList.setName("setList"); // NOI18N
        jScrollPane3.setViewportView(setList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(wffLabel)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                    .addComponent(classLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                    .addComponent(setLabel)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(wffLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(classLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SyntaxWindow dialog = new SyntaxWindow(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel classLabel;
    private javax.swing.JTable classList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel setLabel;
    private javax.swing.JTable setList;
    private javax.swing.JLabel wffLabel;
    private javax.swing.JTable wffList;
    // End of variables declaration//GEN-END:variables
public class WffListModel extends AbstractTableModel
  {
      List<List<String>> table=null;
      String[] namesColumns={"No.","Name","Content"};


      public WffListModel()
      {
          table= new ArrayList<List<String>>();
          Application0 a=(Application0)Application.getInstance();


    if (a.frame0!=null)
       {
       if (a.frame0.source1!=null)
       {
       if (a.frame0.source1.classListOfDefinitions!=null)
       {
       if (a.frame0.source1.classListOfDefinitions.get("wff")!=null)
       {
        List<Object> vector=null;
        //find the vector with definitions (class)
        vector=a.frame0.source1.classListOfDefinitions.get("wff");
         String name="";
          if (!vector.isEmpty())
          { int i1=0;int max1=vector.size();
               while(i1<max1)
               {
                List<String> line=new ArrayList<String>();

                String content="";

                 List<ConstantAndVariable> content0=null;
                 if (vector.get(i1).getClass().equals(Axiom.class))
                {
                    Axiom ax=(Axiom) vector.get(i1);
                    content0=ax.items;
                    name=ax.name;
                }
                 else
                 if (vector.get(i1).getClass().equals(Theorem.class))
                    {
                        Theorem tx=(Theorem) vector.get(i1);
                        content0=tx.items;
                        name=tx.name;
                    }
                 
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
                line.add(name);
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
public class ClassListModel extends AbstractTableModel
  {
      List<List<String>> table=null;
      String[] namesColumns={"No.","Name","Content"};


      public ClassListModel()
      {
          table= new ArrayList<List<String>>();
          Application0 a=(Application0)Application.getInstance();


    if (a.frame0!=null)
       {
       if (a.frame0.source1!=null)
       {
       if (a.frame0.source1.classListOfDefinitions!=null)
       {
       if (a.frame0.source1.classListOfDefinitions.get("class")!=null)
       {
        List<Object> vector=null;
        //find the vector with definitions(class)
        vector=a.frame0.source1.classListOfDefinitions.get("class");
         String name="";
          if (!vector.isEmpty())
          { int i1=0;int max1=vector.size();
               while(i1<max1)
               {
                List<String> line=new ArrayList<String>();

                String content="";

                 List<ConstantAndVariable> content0=null;
                 if (vector.get(i1).getClass().equals(Axiom.class))
                {
                    Axiom ax=(Axiom) vector.get(i1);
                    content0=ax.items;
                    name=ax.name;
                }
                 else
                 if (vector.get(i1).getClass().equals(Theorem.class))
                    {
                        Theorem tx=(Theorem) vector.get(i1);
                        content0=tx.items;
                        name=tx.name;
                    }

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
                line.add(name);
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
public class SetListModel extends AbstractTableModel
  {
      List<List<String>> table=null;
      String[] namesColumns={"No.","Name","Content"};


      public SetListModel()
      {
          table= new ArrayList<List<String>>();
          Application0 a=(Application0)Application.getInstance();


    if (a.frame0!=null)
       {
       if (a.frame0.source1!=null)
       {
       if (a.frame0.source1.classListOfDefinitions!=null)
       {
       if (a.frame0.source1.classListOfDefinitions.get("set")!=null)
       {
        List<Object> vector=null;
       //find the vector with definitions(class)
        vector=a.frame0.source1.classListOfDefinitions.get("set");
         String name="";
          if (!vector.isEmpty())
          { int i1=0;int max1=vector.size();
               while(i1<max1)
               {
                List<String> line=new ArrayList<String>();

                String content="";

                 List<ConstantAndVariable> content0=null;
                 if (vector.get(i1).getClass().equals(Axiom.class))
                {
                    Axiom ax=(Axiom) vector.get(i1);
                    content0=ax.items;
                    name=ax.name;
                }
                 else
                 if (vector.get(i1).getClass().equals(Theorem.class))
                    {
                        Theorem tx=(Theorem) vector.get(i1);
                        content0=tx.items;
                        name=tx.name;
                    }

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
                line.add(name);
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



}