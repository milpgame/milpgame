//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv;
import java.awt.Color;
import javax.swing.table.AbstractTableModel;
import javax.swing.JFrame;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListSelectionModel;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.text.StyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.BadLocationException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.StringTokenizer;
import org.jdesktop.application.Application;
import spv.gen.DemonstrationConstants;
import spv.gen.Hypothesis;
import spv.gen.Axiom;
import spv.gen.Theorem;
import spv.gen.ConstantAndVariable;


public class FormulaEditorWindow extends javax.swing.JDialog {

    
    public FormulaEditorWindow(
            java.awt.Frame parent,
            boolean modal
           
            ) {
        super(parent, modal);
        initComponents();
          /*
          StyledDocument doc=this.textPane.getStyledDocument();
           Style style=this.textPane.addStyle("red style", null);
           StyleConstants.setForeground(style, Color.red);
           try
           {
            doc.insertString(doc.getLength(), "This", null);
            doc.insertString(doc.getLength(), "Apple", style);
            doc.insertString(doc.getLength(), "is a good", null);
            doc.insertString(doc.getLength(), "Apple", style);
           } catch(BadLocationException e){}
          */
         Application0 a=(Application0)Application.getInstance();
        //we initializes the constants
          listConstants0.clear();

          if (a.frame0!=null)
          {
          if (a.frame0.source1!=null)
             {
           if (a.frame0.source1.constants!=null)
             {

                Iterator<String> it=a.frame0.source1.constants.iterator();
               while(it.hasNext())
               {
                String s1=it.next();
                listConstants0.add(s1);


                }
              }
             }
            }
         listConstants.setModel(new ModelListConstants());
         listConstants.revalidate();
         listConstants.repaint();
         //end constants

         //initializes the variables
          listVariables0.clear();

          if (a.frame0!=null)
          {
          if (a.frame0.source1!=null)
             {
           if (a.frame0.source1.variables!=null)
             {

                Iterator<String> it=a.frame0.source1.variables.iterator();
               while(it.hasNext())
               {
                String s1=it.next();
                listVariables0.add(s1);
                }
              }
             }
            }
         listVariables.setModel(new ModelListVariables());
         listVariables.revalidate();
         listVariables.repaint();
         //end variables
         
         if (a!=null)
         {
          if (a.frame0!=null)
         {
          if (a.frame0.currentDemonstrationEditor!=null)
          {
                       
            
            String s="";
           int max=formulaContent.size();

           for (int i=0;i<max;i++)
           {
            String  s2="",s1=formulaContent.get(i).constantOrVariableText;
            if(a!=null)
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
            s=s+s2;
           }

          s =s.replaceAll("ALIGN=TOP","ALIGN=CENTER");
         s="<HTML>"
         +"<base href='file:"+a.path+"/symbols/  '/>"
         +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
         +"<FONT COLOR='#ffffff' SIZE=3 >"
         +s
         +"</FONT>"
         +"</P>"
         +"</HTML>";
          variableContentLabel.setText(s);
          }
          }
        }

    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formulaContentNonfunctionalLabel = new javax.swing.JLabel();
        editingTab = new javax.swing.JTabbedPane();
        directEditingPanel = new javax.swing.JPanel();
        insertVariableButton = new javax.swing.JButton();
        deleteContentButton = new javax.swing.JButton();
        constantsTableNonfunctionalLabel = new javax.swing.JLabel();
        variablesTableNonfunctionalLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listVariables = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        listConstants = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        listContent = new javax.swing.JList();
        contentTableNonfunctionalLabel = new javax.swing.JLabel();
        insertConstantButton = new javax.swing.JButton();
        moveDownContentButton = new javax.swing.JButton();
        moveUpContentButton = new javax.swing.JButton();
        searchInConstantsText = new javax.swing.JTextField();
        searchInVariablesText = new javax.swing.JTextField();
        correctnessLabel = new javax.swing.JLabel();
        scrollContentText = new javax.swing.JScrollPane();
        variableContentLabel = new javax.swing.JLabel();
        syntaxButton = new javax.swing.JButton();
        verifyButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();
        importButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit content of a formula"); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        formulaContentNonfunctionalLabel.setForeground(new java.awt.Color(0, 51, 255));
        formulaContentNonfunctionalLabel.setText("Formula content:"); // NOI18N
        formulaContentNonfunctionalLabel.setName("formulaContentNonfunctionalLabel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(spv.Application0.class).getContext().getResourceMap(FormulaEditorWindow.class);
        editingTab.setForeground(resourceMap.getColor("editingTab.foreground")); // NOI18N
        editingTab.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        editingTab.setName("editingTab"); // NOI18N

        directEditingPanel.setForeground(new java.awt.Color(0, 51, 255));
        directEditingPanel.setName("directEditingPanel"); // NOI18N

        insertVariableButton.setForeground(new java.awt.Color(0, 51, 255));
        insertVariableButton.setText("Insert -->"); // NOI18N
        insertVariableButton.setName("insertVariableButton"); // NOI18N
        insertVariableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertVariableButtonActionPerformed(evt);
            }
        });

        deleteContentButton.setForeground(new java.awt.Color(204, 0, 51));
        deleteContentButton.setText("Delete"); // NOI18N
        deleteContentButton.setName("deleteContentButton"); // NOI18N
        deleteContentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteContentButtonActionPerformed(evt);
            }
        });

        constantsTableNonfunctionalLabel.setForeground(new java.awt.Color(0, 51, 255));
        constantsTableNonfunctionalLabel.setText("List of constants:"); // NOI18N
        constantsTableNonfunctionalLabel.setName("et_nef_lista_constante"); // NOI18N

        variablesTableNonfunctionalLabel.setForeground(new java.awt.Color(0, 51, 255));
        variablesTableNonfunctionalLabel.setText("List of variables:"); // NOI18N
        variablesTableNonfunctionalLabel.setName("et_nef_lista_variabile"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        listVariables.setModel(new ModelListVariables());
        listVariables.setName("listVariables"); // NOI18N
        listVariables.setSelectionModel(new ListVariablesSelectionModel());
        jScrollPane2.setViewportView(listVariables);

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        listConstants.setModel(new ModelListConstants());
        listConstants.setName("listConstants"); // NOI18N
        listConstants.setSelectionModel(new ListConstantsSelectionModel());
        jScrollPane3.setViewportView(listConstants);

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        listContent.setModel( new ModelListContent());
        listContent.setName("listContent"); // NOI18N
        listContent.setSelectionModel(new ListContentSelectionModel());
        jScrollPane4.setViewportView(listContent);

        contentTableNonfunctionalLabel.setForeground(resourceMap.getColor("contentTableNonfunctionalLabel.foreground")); // NOI18N
        contentTableNonfunctionalLabel.setText("Content:"); // NOI18N
        contentTableNonfunctionalLabel.setName("contentTableNonfunctionalLabel"); // NOI18N

        insertConstantButton.setForeground(resourceMap.getColor("insertConstantButton.foreground")); // NOI18N
        insertConstantButton.setText("Insert -->"); // NOI18N
        insertConstantButton.setName("insertConstantButton"); // NOI18N
        insertConstantButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertConstantButtonActionPerformed(evt);
            }
        });

        moveDownContentButton.setForeground(resourceMap.getColor("moveDownContentButton.foreground")); // NOI18N
        moveDownContentButton.setText("Move Down"); // NOI18N
        moveDownContentButton.setName("moveDownContentButton"); // NOI18N
        moveDownContentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveDownContentButtonActionPerformed(evt);
            }
        });

        moveUpContentButton.setForeground(resourceMap.getColor("moveUpContentButton.foreground")); // NOI18N
        moveUpContentButton.setText("Move Up"); // NOI18N
        moveUpContentButton.setName("moveUpContentButton"); // NOI18N
        moveUpContentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveUpContentButtonActionPerformed(evt);
            }
        });

        searchInConstantsText.setForeground(resourceMap.getColor("searchInConstantsText.foreground")); // NOI18N
        searchInConstantsText.setText(resourceMap.getString("searchInConstantsText.text")); // NOI18N
        searchInConstantsText.setName("searchInConstantsText"); // NOI18N
        searchInConstantsText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchInConstantsTextKeyTyped(evt);
            }
        });

        searchInVariablesText.setForeground(resourceMap.getColor("searchInVariablesText.foreground")); // NOI18N
        searchInVariablesText.setText(resourceMap.getString("searchInVariablesText.text")); // NOI18N
        searchInVariablesText.setName("searchInVariablesText"); // NOI18N
        searchInVariablesText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchInVariablesTextKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout directEditingPanelLayout = new javax.swing.GroupLayout(directEditingPanel);
        directEditingPanel.setLayout(directEditingPanelLayout);
        directEditingPanelLayout.setHorizontalGroup(
            directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(directEditingPanelLayout.createSequentialGroup()
                .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(directEditingPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(searchInConstantsText, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                    .addGroup(directEditingPanelLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(insertConstantButton))
                    .addGroup(directEditingPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(constantsTableNonfunctionalLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, directEditingPanelLayout.createSequentialGroup()
                        .addComponent(insertVariableButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(directEditingPanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(variablesTableNonfunctionalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                            .addGroup(directEditingPanelLayout.createSequentialGroup()
                                .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(searchInVariablesText, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                                .addGap(25, 25, 25)))))
                .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(directEditingPanelLayout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(contentTableNonfunctionalLabel)
                            .addComponent(deleteContentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(moveDownContentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(moveUpContentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(68, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, directEditingPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))))
        );
        directEditingPanelLayout.setVerticalGroup(
            directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(directEditingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(constantsTableNonfunctionalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(variablesTableNonfunctionalLabel)
                    .addComponent(contentTableNonfunctionalLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(directEditingPanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moveUpContentButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moveDownContentButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteContentButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, directEditingPanelLayout.createSequentialGroup()
                        .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchInConstantsText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchInVariablesText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, directEditingPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(insertVariableButton)
                                    .addComponent(insertConstantButton)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, directEditingPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)))))
                .addGap(123, 123, 123))
        );

        editingTab.addTab("Edit content directly", directEditingPanel);

        correctnessLabel.setName("correctnessLabel"); // NOI18N

        scrollContentText.setName("scrollContentText"); // NOI18N

        variableContentLabel.setForeground(resourceMap.getColor("variableContentLabel.foreground")); // NOI18N
        variableContentLabel.setText("____________________________________________________________________"); // NOI18N
        variableContentLabel.setName("variableContentLabel"); // NOI18N
        scrollContentText.setViewportView(variableContentLabel);

        syntaxButton.setForeground(resourceMap.getColor("syntaxButton.foreground")); // NOI18N
        syntaxButton.setText(resourceMap.getString("syntaxButton.text")); // NOI18N
        syntaxButton.setName("syntaxButton"); // NOI18N
        syntaxButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                syntaxButtonActionPerformed(evt);
            }
        });

        verifyButton.setForeground(resourceMap.getColor("verifyButton.foreground")); // NOI18N
        verifyButton.setText(resourceMap.getString("verifyButton.text")); // NOI18N
        verifyButton.setName("verifyButton"); // NOI18N
        verifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifyButtonActionPerformed(evt);
            }
        });

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        textPane.setName("textPane"); // NOI18N
        jScrollPane5.setViewportView(textPane);

        importButton.setForeground(resourceMap.getColor("importButton.foreground")); // NOI18N
        importButton.setText(resourceMap.getString("importButton.text")); // NOI18N
        importButton.setName("importButton"); // NOI18N
        importButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(correctnessLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(verifyButton)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addComponent(syntaxButton))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(formulaContentNonfunctionalLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(scrollContentText, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)))
                            .addComponent(editingTab, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addComponent(importButton, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(formulaContentNonfunctionalLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollContentText, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(correctnessLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(syntaxButton)
                        .addComponent(verifyButton)))
                .addGap(18, 18, 18)
                .addComponent(editingTab, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(importButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        editingTab.getAccessibleContext().setAccessibleName("Insert tokens"); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertConstantButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertConstantButtonActionPerformed
        
       if((selectedRowConstants>=0)&(selectedRowConstants<listConstants0.size()))
       {
       ConstantAndVariable c=new ConstantAndVariable();//we add a new constant to the content
       c.constantOrVariable=1;//is a constant
       c.constantOrVariableText=listConstants0.get(selectedRowConstants);
       //we add a new constant to the content of the edited formula
       formulaContent.add(c);
       
       listContent.updateUI();

       
       String s="";String x="";
       int max=formulaContent.size();
      Application0 a=(Application0)Application.getInstance();
       for (int i=0;i<max;i++)
       {
        String  s2="",s1=formulaContent.get(i).constantOrVariableText;
        if(a!=null)
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
        x=x+s1+" ";
        s=s+s2;
       }

      s =s.replaceAll("ALIGN=TOP","ALIGN=CENTER");
     s="<HTML>"
     +"<base href='file:"+a.path+"/symbols/  '/>"
     +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
     +"<FONT COLOR='#ffffff' SIZE=3 >"
     +s
     +"</FONT>"
     +"</P>"
     +"</HTML>";
      variableContentLabel.setText(s);
      this.textPane.setText(x);
      //we verify the correctness of the content of the formula
      
      
      this.listConstants.clearSelection();
     
     }
    }//GEN-LAST:event_insertConstantButtonActionPerformed

    private void insertVariableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertVariableButtonActionPerformed
        
      if((selectedRowVariables>=0)&(selectedRowVariables<listVariables0.size()))
       {
      ConstantAndVariable c=new ConstantAndVariable();
      c.constantOrVariable=2;//is a variable
      c.constantOrVariableText=listVariables0.get(selectedRowVariables);
      //we add a new variable to the content of the edited formula

       //we establish the class of the variable
       Application0 a=(Application0)Application.getInstance();

          if (a.frame0!=null)
          {
          if (a.frame0.source1!=null)
             {
           if (a.frame0.source1.variableAndType!=null)
           {

             c.variableClass=a.frame0.source1.variableAndType.get(c.constantOrVariableText);
           }
           }
           }
      formulaContent.add(c);

       listContent.updateUI();
       
        String s="";String x="";
       int max=formulaContent.size();
      
       for (int i=0;i<max;i++)
       {
        String  s2="",s1=formulaContent.get(i).constantOrVariableText;
        if(a!=null)
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
        x=x+s1+" ";
        s=s+s2;
       }

      s =s.replaceAll("ALIGN=TOP","ALIGN=CENTER");
     s="<HTML>"
     +"<base href='file:"+a.path+"/symbols/  '/>"
     +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
     +"<FONT COLOR='#ffffff' SIZE=3 >"
     +s
     +"</FONT>"
     +"</P>"
     +"</HTML>";
      variableContentLabel.setText(s);
      textPane.setText(x);
      
      
      this.listVariables.clearSelection();
     }
    }//GEN-LAST:event_insertVariableButtonActionPerformed

    private void deleteContentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteContentButtonActionPerformed
        
       if((selectedRowContent>=0)&
                               (selectedRowContent<formulaContent.size()))
       {
        formulaContent.remove(selectedRowContent);
        listContent.updateUI();
        
        String s="";String x="";
       int max=formulaContent.size();
        Application0 a=(Application0)Application.getInstance();
       for (int i=0;i<max;i++)
       {
        String  s2="",s1=formulaContent.get(i).constantOrVariableText;
        if(a!=null)
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
        x=x+s1+" ";
        s=s+s2;
       }

      s =s.replaceAll("ALIGN=TOP","ALIGN=CENTER");
     s="<HTML>"
     +"<base href='file:"+a.path+"/symbols/  '/>"
     +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
     +"<FONT COLOR='#ffffff' SIZE=3 >"
     +s
     +"</FONT>"
     +"</P>"
     +"</HTML>";
      variableContentLabel.setText(s);
      textPane.setText(x);
      
     listContent.clearSelection();

     }
    }//GEN-LAST:event_deleteContentButtonActionPerformed

    private void moveUpContentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveUpContentButtonActionPerformed
        
        
      if (selectedRowContent-1>=0)
      {
        byte ciType= formulaContent.
                                    get(selectedRowContent).constantOrVariable;
        String ciClass= formulaContent.
                                   get(selectedRowContent).variableClass;
        String ciName= formulaContent.
                                get(selectedRowContent).constantOrVariableText;

        ConstantAndVariable ci=new ConstantAndVariable();

        ci.constantOrVariable=ciType;
        ci.variableClass=ciClass;
        ci.constantOrVariableText=ciName;

        byte cim1Type= formulaContent.
                                   get(selectedRowContent-1).constantOrVariable;
        String cim1Class= formulaContent.
                                 get(selectedRowContent-1).variableClass;
        String cim1Name= formulaContent.
                               get(selectedRowContent-1).constantOrVariableText;

        ConstantAndVariable cim1=new ConstantAndVariable();

        cim1.constantOrVariable=cim1Type;
        cim1.variableClass=cim1Class;
        cim1.constantOrVariableText=cim1Name;

        formulaContent.set(selectedRowContent, cim1);
        formulaContent.set(selectedRowContent-1, ci);
        listContent.updateUI();
        
        String s="";String x="";
       int max1=formulaContent.size();
        Application0 a=(Application0)Application.getInstance();
       for (int i=0;i<max1;i++)
       {
        String  s2="",s1=formulaContent.get(i).constantOrVariableText;
        if(a!=null)
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
        s=s+s2;
        x=x+s1+" ";
       }

      s =s.replaceAll("ALIGN=TOP","ALIGN=CENTER");
     s="<HTML>"
     +"<base href='file:"+a.path+"/symbols/  '/>"
     +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
     +"<FONT COLOR='#ffffff' SIZE=3 >"
     +s
     +"</FONT>"
     +"</P>"
     +"</HTML>";
      variableContentLabel.setText(s);
      textPane.setText(x);
     
      listContent.clearSelection();
      listContent.setSelectedIndex(selectedRowContent-1);
      }


    }//GEN-LAST:event_moveUpContentButtonActionPerformed

    private void moveDownContentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveDownContentButtonActionPerformed
        
        int max=formulaContent.size();
      if (selectedRowContent+1<max)
      {
        byte ciType= formulaContent.
                                    get(selectedRowContent).constantOrVariable;
        String ciClass= formulaContent.
                                   get(selectedRowContent).variableClass;
        String ciName= formulaContent.
                                get(selectedRowContent).constantOrVariableText;

        ConstantAndVariable ci=new ConstantAndVariable();

        ci.constantOrVariable=ciType;
        ci.variableClass=ciClass;
        ci.constantOrVariableText=ciName;

        byte cip1Type= formulaContent.
                                   get(selectedRowContent+1).constantOrVariable;
        String cip1Class= formulaContent.
                                  get(selectedRowContent+1).variableClass;
        String cip1Name= formulaContent.
                               get(selectedRowContent+1).constantOrVariableText;

        ConstantAndVariable cip1=new ConstantAndVariable();

        cip1.constantOrVariable=cip1Type;
        cip1.variableClass=cip1Class;
        cip1.constantOrVariableText=cip1Name;

        formulaContent.set(selectedRowContent, cip1);
        formulaContent.set(selectedRowContent+1, ci);
        listContent.updateUI();
        
        String s="";String x="";
       int max1=formulaContent.size();
       Application0 a=(Application0)Application.getInstance();
       for (int i=0;i<max1;i++)
       {
        String  s2="",s1=formulaContent.get(i).constantOrVariableText;
        if(a!=null)
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
        s=s+s2;
        x=x+s1+" ";
       }

      s =s.replaceAll("ALIGN=TOP","ALIGN=CENTER");
     s="<HTML>"
     +"<base href='file:"+a.path+"/symbols/  '/>"
     +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
     +"<FONT COLOR='#ffffff' SIZE=3 >"
     +s
     +"</FONT>"
     +"</P>"
     +"</HTML>";
      variableContentLabel.setText(s);
      textPane.setText(x);
      
      listContent.clearSelection();
      listContent.setSelectedIndex(selectedRowContent+1);
      }

    }//GEN-LAST:event_moveDownContentButtonActionPerformed

    private void syntaxButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_syntaxButtonActionPerformed
        JFrame mainFrame = Application0.getApplication().getMainFrame();
        this.syntaxWindow = new SyntaxWindow(mainFrame,true);
        this.syntaxWindow.setLocationRelativeTo(mainFrame);

        Application0.getApplication().show(syntaxWindow);
    }//GEN-LAST:event_syntaxButtonActionPerformed

    private void searchInConstantsTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchInConstantsTextKeyTyped

       Application0 a=(Application0)Application.getInstance();

          listConstants0.clear();

          if (a.frame0!=null)
          {
          if (a.frame0.source1!=null)
             {
           if (a.frame0.source1.constants!=null)
             {

                Iterator<String> it=a.frame0.source1.constants.iterator();
               while(it.hasNext())
               {
                String s1=it.next();
                if(s1.indexOf(this.searchInConstantsText.getText())>-1)
                {
                listConstants0.add(s1);
                }


                }
              }
             }
            }
         listConstants.setModel(new ModelListConstants());
         listConstants.revalidate();
         listConstants.repaint();
    }//GEN-LAST:event_searchInConstantsTextKeyTyped

    private void searchInVariablesTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchInVariablesTextKeyTyped

     Application0 a=(Application0)Application.getInstance();

          listVariables0.clear();

          if (a.frame0!=null)
          {
          if (a.frame0.source1!=null)
             {
           if (a.frame0.source1.variables!=null)
             {

                Iterator<String> it=a.frame0.source1.variables.iterator();
               while(it.hasNext())
               {
                String s1=it.next();
                if(s1.indexOf(this.searchInVariablesText.getText())>-1)
                {
                listVariables0.add(s1);
                }


                }
              }
             }
            }
         listVariables.setModel(new ModelListVariables());
         listVariables.revalidate();
         listVariables.repaint();

    }//GEN-LAST:event_searchInVariablesTextKeyTyped

    private void verifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verifyButtonActionPerformed
       Application0 a=(Application0)Application.getInstance();
     if (formulaContent!=null)
     {
     if (!formulaContent.isEmpty())
     {

       SyntacticItem syntacticTree=new SyntacticItem();
      boolean correct=a.frame0.source1.verifySyntacticType
                                            (
                                            formulaContent,
                                            "wff",
                                            0,
                                            true,
                                            syntacticTree
                                            );

      if (correct)
      {
         this.correctnessLabel.setText("Entered formula is:correct!");
      }
      else
      {
         this.correctnessLabel.setText("Entered formula is:incorrect!");
      }

      }
      }
       
    }//GEN-LAST:event_verifyButtonActionPerformed

    private void importButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importButtonActionPerformed

     Application0 a=(Application0)Application.getInstance();
     StyledDocument doc=this.textPane.getStyledDocument();
     Style style=this.textPane.addStyle("red style", null);
     StyleConstants.setForeground(style, Color.red);
     
     String inputText=this.textPane.getText();
     StringTokenizer st=new StringTokenizer(inputText," ");
     this.textPane.setText("");//we clear the text

     List<ConstantAndVariable>  importedFormula = new
                                              ArrayList<ConstantAndVariable>();
     boolean correctWords=true;
     int i0=-1;
     while(st.hasMoreTokens())
     {
      String si=st.nextToken();i0++;
     
      boolean stringFound=false;

               Iterator<String> it1=a.frame0.source1.constants.iterator();
               while(it1.hasNext())
               {
                String constI=it1.next();
                if(constI.equals(si))
                {
                 ConstantAndVariable c=new ConstantAndVariable();
                 c.constantOrVariable=1;
                 c.constantOrVariableText=si;
                 importedFormula.add(c);
                 stringFound=true;
                 break;
                }
                }

               Iterator<String> it2=a.frame0.source1.variables.iterator();
               while(it2.hasNext())
               {
                String varI=it2.next();
                if(varI.equals(si))
                {
                 ConstantAndVariable v=new ConstantAndVariable();
                 v.constantOrVariable=2;
                 v.constantOrVariableText=si;
                 if (a.frame0!=null)
                  {
                  if (a.frame0.source1!=null)
                     {
                   if (a.frame0.source1.variableAndType!=null)
                   {

                     v.variableClass=a.frame0.source1.variableAndType.
                                                  get(v.constantOrVariableText);
                   }
                   }
                   }
                 importedFormula.add(v);
                 stringFound=true;
                 break;
                }
                }

     if(!stringFound)
     {
         try
           {
            doc.insertString(doc.getLength(), si, style);
            doc.insertString(doc.getLength(), " ", null);

           } catch(BadLocationException e){}

         correctWords=false;
     }
     else
     {
       if((!si.equals("|-"))&(i0==0))
       {
        try
           {
            doc.insertString(doc.getLength(), si, style);
            doc.insertString(doc.getLength(), " ", null);

           } catch(BadLocationException e){}
        correctWords=false;
       }
       else
       {
        try
           {
            doc.insertString(doc.getLength(), si, null);
            doc.insertString(doc.getLength(), " ", null);

           } catch(BadLocationException e){}
        }

     }


     }
     if(correctWords)
     {
       //redisplay formulaContent
       formulaContent=importedFormula;

       listContent.updateUI();

        String s="";
       int max=formulaContent.size();

       for (int i=0;i<max;i++)
       {
        String  s2="",s1=formulaContent.get(i).constantOrVariableText;
        if(a!=null)
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
       
        s=s+s2;
       }

      s =s.replaceAll("ALIGN=TOP","ALIGN=CENTER");
     s="<HTML>"
     +"<base href='file:"+a.path+"/symbols/  '/>"
     +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
     +"<FONT COLOR='#ffffff' SIZE=3 >"
     +s
     +"</FONT>"
     +"</P>"
     +"</HTML>";
      variableContentLabel.setText(s);
     //we verify syntactic correctness

      if (formulaContent!=null)
     {
     if (!formulaContent.isEmpty())
     {

       SyntacticItem syntacticTree=new SyntacticItem();
      boolean correct=a.frame0.source1.verifySyntacticType
                                            (
                                            formulaContent,
                                            "wff",
                                            1,
                                            true,
                                            syntacticTree
                                            );

      if (correct)
      {
         this.correctnessLabel.setText("Entered formula is:correct!");
      }
      else
      {
         this.correctnessLabel.setText("Entered formula is:incorrect!");
      }

      }
      }
     }
      else
       {
         this.correctnessLabel.setText("Entered formula is:incorrect!");
       }



    }//GEN-LAST:event_importButtonActionPerformed

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormulaEditorWindow dialog = new FormulaEditorWindow(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel constantsTableNonfunctionalLabel;
    private javax.swing.JLabel contentTableNonfunctionalLabel;
    private javax.swing.JLabel correctnessLabel;
    private javax.swing.JButton deleteContentButton;
    private javax.swing.JPanel directEditingPanel;
    private javax.swing.JTabbedPane editingTab;
    private javax.swing.JLabel formulaContentNonfunctionalLabel;
    private javax.swing.JButton importButton;
    private javax.swing.JButton insertConstantButton;
    private javax.swing.JButton insertVariableButton;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JList listConstants;
    private javax.swing.JList listContent;
    private javax.swing.JList listVariables;
    private javax.swing.JButton moveDownContentButton;
    private javax.swing.JButton moveUpContentButton;
    private javax.swing.JScrollPane scrollContentText;
    private javax.swing.JTextField searchInConstantsText;
    private javax.swing.JTextField searchInVariablesText;
    private javax.swing.JButton syntaxButton;
    public javax.swing.JTextPane textPane;
    private javax.swing.JLabel variableContentLabel;
    private javax.swing.JLabel variablesTableNonfunctionalLabel;
    private javax.swing.JButton verifyButton;
    // End of variables declaration//GEN-END:variables
    
    //here are stored the name and the content of the variable
    
    public List<ConstantAndVariable> formulaContent=new  ArrayList<ConstantAndVariable>();
   
    //here is a list of strings that represents constants
    public List<String> listConstants0=new ArrayList<String>();
    //here is a list of strings that represents variables
    public List<String> listVariables0=new ArrayList<String>();
    public int  selectedRowConstants=-1;
    public int  selectedRowVariables=-1;
    public int  selectedRowContent=-1;
    public SyntaxWindow syntaxWindow=null;
    
      public class ModelListConstants extends AbstractListModel
  {
      
     
      public ModelListConstants()
      {
         
      }
       
      public String getElementAt(int line)
      {
          Application0 a=(Application0)Application.getInstance();

          String value="";
           if ((line>=0)&(line<listConstants0.size()))
           {
               String s2="",s1=listConstants0.get(line);
                s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
                if (s2==null) s2=s1;
                s2="("+line+"|"+s1+")"+s2;
                s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
                s2="<HTML>"
                 +"<base href='file:"+a.path+"/symbols/  '/>"
         +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
                 +"<FONT COLOR='#ffffff' SIZE=3 >"
                 +s2
                 +"</FONT>"
                 +"</P>"
                 +"</HTML>";

             value=s2;
           }
          return value;
      }

      public int  getSize()
      {
          return listConstants0.size();
      }
    }
   public class ListConstantsSelectionListener
           implements ListSelectionListener
    {

        public void valueChanged(ListSelectionEvent e)
        {
         pressedRowListConstants(e);
        }

    }

   void pressedRowListConstants(ListSelectionEvent e)
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
                 selectedRowConstants=i;
                 
                }
            }
       }

   }

 
  //we create the selection model
  public class  ListConstantsSelectionModel extends DefaultListSelectionModel
  {
      public ListConstantsSelectionModel()
      {
         super();
         this.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        this.addListSelectionListener(
                                    new ListConstantsSelectionListener()
                                  );

      }
  }

  
       public class ModelListVariables extends AbstractListModel
  {
     

      public ModelListVariables()
      {
          
      }

      public String getElementAt(int line)
      {
          String value="";
          Application0 a=(Application0)Application.getInstance();
           if ((line>=0)&(line<listVariables0.size()))
           { 
                String s2="",s1=listVariables0.get(line);
               s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
                if (s2==null) s2=s1;
                String s3=a.frame0.source1.variableAndType.get(s1);
                s2="("+line+"|"+s1+")"+s2+"["+s3+"]";
                
                s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
                s2="<HTML>"
                 +"<base href='file:"+a.path+"/symbols/  '/>"
         +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
                 +"<FONT COLOR='#ffffff' SIZE=3 >"
                 +s2
                 +"</FONT>"
                 +"</P>"
                 +"</HTML>";

             value=s2;
           }
          return value;
      }



      public int  getSize()
      {
          return listVariables0.size();
      }


  }
public class ListVariablesSelectionListener
           implements ListSelectionListener
    {

        public void valueChanged(ListSelectionEvent e)
        {
         pressedRowListVariables(e);
        }

    }

   void pressedRowListVariables(ListSelectionEvent e)
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
                 selectedRowVariables=i;
                 
                }
            }
       }


      
   }


  //we create the selection model
  public class  ListVariablesSelectionModel extends DefaultListSelectionModel
  {
      public ListVariablesSelectionModel()
      {
         super();
         this.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        this.addListSelectionListener(
                                    new ListVariablesSelectionListener()
                                  );

      }
  }


 public class ModelListContent extends AbstractListModel
  {
      

      public ModelListContent()
      {

      
      }

      public String getElementAt(int line)
      {
          Application0 a=(Application0)Application.getInstance();
          String value="";
           if ((line>=0)&(line<formulaContent.size()))
           {   
               String s1=formulaContent.get(line).constantOrVariableText;
               String s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
                if (s2==null) s2=s1;
                s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
                s2="<HTML>"
                 +"<base href='file:"+a.path+"/symbols/  '/>"
         +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
                 +"<FONT COLOR='#ffffff' SIZE=3 >"
                 +s2
                 +"</FONT>"
                 +"</P>"
                 +"</HTML>";
             value=s2;
           }
          return value;
      }

      public int  getSize()
      {
          return formulaContent.size();
      }
  }

 public class ListContentSelectionListener
           implements ListSelectionListener
    {

        public void valueChanged(ListSelectionEvent e)
        {
         pressedRowListContent(e);
        }

    }

void pressedRowListContent(ListSelectionEvent e)
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
                 selectedRowContent=i;
                 
                }
            }
       }
      
   }

  //we create the selection model
  public class  ListContentSelectionModel extends DefaultListSelectionModel
  {
      public ListContentSelectionModel()
      {
         super();
         this.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        this.addListSelectionListener(
                                    new ListContentSelectionListener()
                                  );
      }
  }

}