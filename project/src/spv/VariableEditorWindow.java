//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv;
import javax.swing.table.AbstractTableModel;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JFrame;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import org.jdesktop.application.Application;
import spv.gen.DemonstrationConstants;
import spv.gen.Hypothesis;
import spv.gen.Axiom;
import spv.gen.Theorem;
import spv.gen.ConstantAndVariable;

public class VariableEditorWindow extends javax.swing.JDialog {

    
    public VariableEditorWindow(
            java.awt.Frame parent,
            boolean modal
           
            ) {
        super(parent, modal);
        initComponents();

         Application0 a=(Application0)Application.getInstance();


         if (a!=null)
         {
          if (a.frame0!=null)
         {
          if (a.frame0.currentDemonstrationEditor!=null)
          {
            //we initialize the variable in question
              variableName=a.frame0.currentDemonstrationEditor.nameOfSelectedVariable;
              variableContent=a.frame0.currentDemonstrationEditor.contentOfSelectedVariable;
              variableClass=a.frame0.currentDemonstrationEditor.classOfSelectedVariable;
              
              this.variableNameLabel.setText(variableName);
              this.variableClassLabel.setText(variableClass);
            //we modify variable content label
            String s="";
           int max=variableContent.size();

           for (int i=0;i<max;i++)
           {
            String  s2="",s1=variableContent.get(i).constantOrVariableText;
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

        variableContentNonfunctionalLabel = new javax.swing.JLabel();
        editingTab = new javax.swing.JTabbedPane();
        directEditingPanel = new javax.swing.JPanel();
        insertVariableButton = new javax.swing.JButton();
        deleteContentButton = new javax.swing.JButton();
        constantsTableNonfunctionalLabel = new javax.swing.JLabel();
        variablesTableNonfunctionalLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listVariables = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        ListConstants = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        listContent = new javax.swing.JList();
        contentTableNonfunctionalLabel = new javax.swing.JLabel();
        insertConstantButton = new javax.swing.JButton();
        moveDownContentButton = new javax.swing.JButton();
        moveUpContentButton = new javax.swing.JButton();
        applyButton = new javax.swing.JButton();
        variableNameNonfuncttionalLabel = new javax.swing.JLabel();
        variableNameLabel = new javax.swing.JLabel();
        correctnessLabel = new javax.swing.JLabel();
        variableClassNonfunctionalLabel = new javax.swing.JLabel();
        variableClassLabel = new javax.swing.JLabel();
        scrollContent = new javax.swing.JScrollPane();
        variableContentLabel = new javax.swing.JLabel();
        syntaxButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit content of a variable"); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        variableContentNonfunctionalLabel.setForeground(new java.awt.Color(0, 51, 255));
        variableContentNonfunctionalLabel.setText("Variable content:"); // NOI18N
        variableContentNonfunctionalLabel.setName("variableContentNonfunctionalLabel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(spv.Application0.class).getContext().getResourceMap(VariableEditorWindow.class);
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

        ListConstants.setModel(new ModelListConstants());
        ListConstants.setName("ListConstants"); // NOI18N
        ListConstants.setSelectionModel(new ListConstantsSelectionModel());
        jScrollPane3.setViewportView(ListConstants);

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

        applyButton.setForeground(resourceMap.getColor("applyButton.foreground")); // NOI18N
        applyButton.setText("Apply"); // NOI18N
        applyButton.setEnabled(false);
        applyButton.setName("applyButton"); // NOI18N
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout directEditingPanelLayout = new javax.swing.GroupLayout(directEditingPanel);
        directEditingPanel.setLayout(directEditingPanelLayout);
        directEditingPanelLayout.setHorizontalGroup(
            directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(directEditingPanelLayout.createSequentialGroup()
                .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(directEditingPanelLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(insertConstantButton))
                    .addGroup(directEditingPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(constantsTableNonfunctionalLabel)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(directEditingPanelLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(variablesTableNonfunctionalLabel))
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, directEditingPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(insertVariableButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)))
                .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(applyButton, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                    .addComponent(contentTableNonfunctionalLabel)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveUpContentButton, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                    .addComponent(moveDownContentButton, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                    .addComponent(deleteContentButton, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                .addGap(42, 42, 42))
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
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moveUpContentButton)
                        .addGap(2, 2, 2)
                        .addComponent(moveDownContentButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteContentButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(applyButton))
                    .addGroup(directEditingPanelLayout.createSequentialGroup()
                        .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(insertConstantButton)
                            .addComponent(insertVariableButton))))
                .addGap(24, 24, 24))
        );

        editingTab.addTab("Edit content directly", directEditingPanel);

        variableNameNonfuncttionalLabel.setForeground(new java.awt.Color(0, 51, 255));
        variableNameNonfuncttionalLabel.setText("Variable name:"); // NOI18N
        variableNameNonfuncttionalLabel.setName("variableNameNonfuncttionalLabel"); // NOI18N

        variableNameLabel.setForeground(new java.awt.Color(0, 51, 255));
        variableNameLabel.setText("________"); // NOI18N
        variableNameLabel.setName("variableNameLabel"); // NOI18N

        correctnessLabel.setForeground(resourceMap.getColor("correctnessLabel.foreground")); // NOI18N
        correctnessLabel.setText(resourceMap.getString("correctnessLabel.text")); // NOI18N
        correctnessLabel.setName("correctnessLabel"); // NOI18N

        variableClassNonfunctionalLabel.setForeground(resourceMap.getColor("variableClassNonfunctionalLabel.foreground")); // NOI18N
        variableClassNonfunctionalLabel.setText("Variable class:"); // NOI18N
        variableClassNonfunctionalLabel.setName("variableClassNonfunctionalLabel"); // NOI18N

        variableClassLabel.setForeground(resourceMap.getColor("variableClassLabel.foreground")); // NOI18N
        variableClassLabel.setText("___________"); // NOI18N
        variableClassLabel.setName("variableClassLabel"); // NOI18N

        scrollContent.setName("scrollContent"); // NOI18N

        variableContentLabel.setForeground(resourceMap.getColor("variableContentLabel.foreground")); // NOI18N
        variableContentLabel.setText("__________________________"); // NOI18N
        variableContentLabel.setName("variableContentLabel"); // NOI18N
        scrollContent.setViewportView(variableContentLabel);

        syntaxButton.setForeground(resourceMap.getColor("syntaxButton.foreground")); // NOI18N
        syntaxButton.setText(resourceMap.getString("syntaxButton.text")); // NOI18N
        syntaxButton.setName("syntaxButton"); // NOI18N
        syntaxButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                syntaxButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(correctnessLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 416, Short.MAX_VALUE)
                        .addComponent(syntaxButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(variableNameNonfuncttionalLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(variableNameLabel)
                        .addGap(91, 91, 91)
                        .addComponent(variableClassNonfunctionalLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(variableClassLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(variableContentNonfunctionalLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollContent, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE))
                    .addComponent(editingTab, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(variableNameLabel)
                    .addComponent(variableClassLabel)
                    .addComponent(variableClassNonfunctionalLabel)
                    .addComponent(variableNameNonfuncttionalLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(variableContentNonfunctionalLabel)
                    .addComponent(scrollContent, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(correctnessLabel)
                    .addComponent(syntaxButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editingTab, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        editingTab.getAccessibleContext().setAccessibleName("Insert tokens"); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertConstantButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertConstantButtonActionPerformed
       
       if((selectedRowConstant>=0)&(selectedRowConstant<listConstants0.size()))
       {
       ConstantAndVariable c=new ConstantAndVariable();//we add a new constant to the content
       c.constantOrVariable=1;//is a constant
       c.constantOrVariableText=listConstants0.get(selectedRowConstant);
       //we add a new constant to the content of the edited variables
       variableContent.add(c);
       
       listContent.updateUI();

       //we modify the label of the edited variable
       String s="";
       int max=variableContent.size();
      Application0 a=(Application0)Application.getInstance();
       for (int i=0;i<max;i++)
       {
        String  s2="",s1=variableContent.get(i).constantOrVariableText;
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
      //correctness verification of the content of the variable
      SyntacticItem syntacticTree=new SyntacticItem();
       correct=a.frame0.source1.verifySyntacticType
                                            (
                                            variableContent,
                                            this.variableClass,
                                            0,
                                            true,
                                            syntacticTree
                                            );

      if (correct)
      {
         this.correctnessLabel.setText("Entered formula is:correct!");
         this.applyButton.setEnabled(true);
      }
      else
      {
         this.correctnessLabel.setText("Entered formula is:incorrect!");
         this.applyButton.setEnabled(false);
      }
      this.ListConstants.clearSelection();
     
      }
    }//GEN-LAST:event_insertConstantButtonActionPerformed

    private void insertVariableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertVariableButtonActionPerformed
       
      if((selectedRowVariable>=0)&(selectedRowVariable<listVariables0.size()))
      {
      ConstantAndVariable c=new ConstantAndVariable();
      c.constantOrVariable=2;//is a variable
      c.constantOrVariableText=listVariables0.get(selectedRowVariable);
      //we add a new variable to the content of the edited variable

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
      variableContent.add(c);

       listContent.updateUI();
         //we modify the label of the variable content
        String s="";
       int max=variableContent.size();
      
       for (int i=0;i<max;i++)
       {
        String  s2="",s1=variableContent.get(i).constantOrVariableText;
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
      SyntacticItem syntacticTree=new SyntacticItem();
      correct=a.frame0.source1.verifySyntacticType
                                            (
                                            variableContent,
                                            this.variableClass,
                                            0,
                                            true,
                                            syntacticTree
                                            );

      if (correct)
      {
         this.correctnessLabel.setText("Entered formula is:correct!");
         this.applyButton.setEnabled(true);
      }
      else
      {
         this.correctnessLabel.setText("Entered formula is:incorrect!");
         this.applyButton.setEnabled(false);
      }
      this.listVariables.clearSelection();
     }
    }//GEN-LAST:event_insertVariableButtonActionPerformed

    private void deleteContentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteContentButtonActionPerformed
        
    if((selectedRowContent>=0)&
                            (selectedRowContent<variableContent.size()))
       {
        variableContent.remove(selectedRowContent);
        listContent.updateUI();
        //we modify the label of the variable content
        String s="";
       int max=variableContent.size();
        Application0 a=(Application0)Application.getInstance();
       for (int i=0;i<max;i++)
       {
        String  s2="",s1=variableContent.get(i).constantOrVariableText;
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
      SyntacticItem syntacticTree=new SyntacticItem();
      correct=false;
      if(!variableContent.isEmpty())
      {
        correct=a.frame0.source1.verifySyntacticType
                                            (
                                            variableContent,
                                            this.variableClass,
                                            0,
                                            true,
                                            syntacticTree
                                            );
       }

      if (correct)
      {
         this.correctnessLabel.setText("Entered formula is:correct!");
         this.applyButton.setEnabled(true);
      }
      else
      {
         this.correctnessLabel.setText("Entered formula is:incorrect!");
         this.applyButton.setEnabled(false);
      }
     listContent.clearSelection();
     }
    }//GEN-LAST:event_deleteContentButtonActionPerformed

    private void moveUpContentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveUpContentButtonActionPerformed
        
        
      if (selectedRowContent-1>=0)
      {
        byte ciType= variableContent.
                                    get(selectedRowContent).constantOrVariable;
        String ciClass= variableContent.
                                   get(selectedRowContent).variableClass;
        String ciName= variableContent.
                                get(selectedRowContent).constantOrVariableText;

        ConstantAndVariable ci=new ConstantAndVariable();

        ci.constantOrVariable=ciType;
        ci.variableClass=ciClass;
        ci.constantOrVariableText=ciName;

        byte cim1Type= variableContent.
                                   get(selectedRowContent-1).constantOrVariable;
        String cim1Class= variableContent.
                                 get(selectedRowContent-1).variableClass;
        String cim1Name= variableContent.
                               get(selectedRowContent-1).constantOrVariableText;

        ConstantAndVariable cim1=new ConstantAndVariable();

        cim1.constantOrVariable=cim1Type;
        cim1.variableClass=cim1Class;
        cim1.constantOrVariableText=cim1Name;

        variableContent.set(selectedRowContent, cim1);
        variableContent.set(selectedRowContent-1, ci);
        listContent.updateUI();
        //we modify the label of variable content
        String s="";
       int max1=variableContent.size();
        Application0 a=(Application0)Application.getInstance();
       for (int i=0;i<max1;i++)
       {
        String  s2="",s1=variableContent.get(i).constantOrVariableText;
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
      SyntacticItem syntacticTree=new SyntacticItem();
      correct=a.frame0.source1.verifySyntacticType
                                            (
                                            variableContent,
                                            this.variableClass,
                                            0,
                                            true,
                                            syntacticTree
                                            );

      if (correct)
      {
         this.correctnessLabel.setText("Entered formula is:correct!");
         this.applyButton.setEnabled(true);
      }
      else
      {
         this.correctnessLabel.setText("Entered formula is:incorrect!");
         this.applyButton.setEnabled(false);
      }
      listContent.clearSelection();
      }


    }//GEN-LAST:event_moveUpContentButtonActionPerformed

    private void moveDownContentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveDownContentButtonActionPerformed
        
        int max=variableContent.size();
      if (selectedRowContent+1<max)
      {
        byte ciType= variableContent.
                                    get(selectedRowContent).constantOrVariable;
        String ciClass= variableContent.
                                   get(selectedRowContent).variableClass;
        String ciName= variableContent.
                                get(selectedRowContent).constantOrVariableText;

        ConstantAndVariable ci=new ConstantAndVariable();

        ci.constantOrVariable=ciType;
        ci.variableClass=ciClass;
        ci.constantOrVariableText=ciName;

        byte cip1Type= variableContent.
                                   get(selectedRowContent+1).constantOrVariable;
        String cip1Class= variableContent.
                                  get(selectedRowContent+1).variableClass;
        String cip1Name= variableContent.
                               get(selectedRowContent+1).constantOrVariableText;

        ConstantAndVariable cip1=new ConstantAndVariable();

        cip1.constantOrVariable=cip1Type;
        cip1.variableClass=cip1Class;
        cip1.constantOrVariableText=cip1Name;

        variableContent.set(selectedRowContent, cip1);
        variableContent.set(selectedRowContent+1, ci);
        listContent.updateUI();
        //we modify the label of variable content
        String s="";
       int max1=variableContent.size();
       Application0 a=(Application0)Application.getInstance();
       for (int i=0;i<max1;i++)
       {
        String  s2="",s1=variableContent.get(i).constantOrVariableText;
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
      SyntacticItem syntacticTree=new SyntacticItem();
      correct=a.frame0.source1.verifySyntacticType
                                            (
                                            variableContent,
                                            this.variableClass,
                                            0,
                                            true,
                                            syntacticTree
                                            );

      if (correct)
      {
         this.correctnessLabel.setText("Entered formula is:correct!");
         this.applyButton.setEnabled(true);
      }
      else
      {
         this.correctnessLabel.setText("Entered formula is:incorrect!");
         this.applyButton.setEnabled(false);
      }
      listContent.clearSelection();
      }

    }//GEN-LAST:event_moveDownContentButtonActionPerformed

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
       
        //we modify the content of the help variable
        Application0 a=(Application0)Application.getInstance();
      if (a!=null)
      {
       if (a.frame0!=null)
      {
       if (a.frame0.currentDemonstrationEditor!=null)
      {
      if (a.frame0.currentDemonstrationEditor.newVariableAndContent!=null)
      {
         if(variableContent.size()>0)
         {
          a.frame0.currentDemonstrationEditor.newVariableAndContent.
                                     put(variableName, variableContent);
         }
      }

      }
      }
      }

         
      if (a!=null)
      {
      if (a.frame0!=null)
      {
      if (a.frame0.currentDemonstrationEditor!=null)
      {
       if (a.frame0.currentDemonstrationEditor.demonstrationSource!=null)
      {
        a.frame0.currentDemonstrationEditor.updateNewStatements
                (
                a.frame0.currentDemonstrationEditor.demonstrationSource
                );

      }
      }
      a.frame0.listOfVariablesWindow.dispose();


      }

      }


      this.dispose();

    }//GEN-LAST:event_applyButtonActionPerformed

    private void syntaxButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_syntaxButtonActionPerformed
        JFrame mainFrame = Application0.getApplication().getMainFrame();
        this.syntaxWindow = new SyntaxWindow(mainFrame,true);
        this.syntaxWindow.setLocationRelativeTo(mainFrame);

        Application0.getApplication().show(syntaxWindow);
    }//GEN-LAST:event_syntaxButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VariableEditorWindow dialog = new VariableEditorWindow(new javax.swing.JFrame(), true);
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
    private javax.swing.JList ListConstants;
    private javax.swing.JButton applyButton;
    private javax.swing.JLabel constantsTableNonfunctionalLabel;
    private javax.swing.JLabel contentTableNonfunctionalLabel;
    private javax.swing.JLabel correctnessLabel;
    private javax.swing.JButton deleteContentButton;
    private javax.swing.JPanel directEditingPanel;
    private javax.swing.JTabbedPane editingTab;
    private javax.swing.JButton insertConstantButton;
    private javax.swing.JButton insertVariableButton;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList listContent;
    private javax.swing.JList listVariables;
    private javax.swing.JButton moveDownContentButton;
    private javax.swing.JButton moveUpContentButton;
    private javax.swing.JScrollPane scrollContent;
    private javax.swing.JButton syntaxButton;
    private javax.swing.JLabel variableClassLabel;
    private javax.swing.JLabel variableClassNonfunctionalLabel;
    private javax.swing.JLabel variableContentLabel;
    private javax.swing.JLabel variableContentNonfunctionalLabel;
    private javax.swing.JLabel variableNameLabel;
    private javax.swing.JLabel variableNameNonfuncttionalLabel;
    private javax.swing.JLabel variablesTableNonfunctionalLabel;
    // End of variables declaration//GEN-END:variables
    
    //here are stored the name and the content of the variable
    public String variableName="";
    public List<ConstantAndVariable> variableContent=new  ArrayList<ConstantAndVariable>();
    public String variableClass="";
    //here is a list of strings that represents constants
    public List<String> listConstants0=new ArrayList<String>();
    //here is a list of strings that represents variables
    public List<String> listVariables0=new ArrayList<String>();
    public int  selectedRowConstant=-1;
    public int  selectedRowVariable=-1;
    public int  selectedRowContent=-1;
    public boolean correct=false;
    public SyntaxWindow syntaxWindow=null;
    
      public class ModelListConstants extends AbstractListModel
  {
     
     
      public ModelListConstants()
      {
          //here we reset the table
          
          Application0 a=(Application0)Application.getInstance();
          
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
                s2="("+line+")"+s2;
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
                 selectedRowConstant=i;
                 
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
          //here we reset the table
          
          Application0 a=(Application0)Application.getInstance();

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
                s2="("+line+")"+s2+"["+s3+"]";
                
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
                 selectedRowVariable=i;
                
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
           if ((line>=0)&(line<variableContent.size()))
           {   
               String s1=variableContent.get(line).constantOrVariableText;
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
          return variableContent.size();
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