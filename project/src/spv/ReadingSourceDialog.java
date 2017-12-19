//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv;

public class ReadingSourceDialog extends javax.swing.JDialog {

    /** Creates new form ReadingSourceDialog */
    public ReadingSourceDialog(java.awt.Frame  parent, boolean modal,Source S) {
        super(parent, modal);
        initComponents();
        bar.setStringPainted(true);
        this.S=S;
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        bar = new javax.swing.JProgressBar();
        Stop = new javax.swing.JButton();

        jScrollBar1.setName("jScrollBar1"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Loading progress..."); // NOI18N
        setModal(true);
        setName("dialog_progres"); // NOI18N
        setResizable(false);

        bar.setName("bara"); // NOI18N

        Stop.setBackground(new java.awt.Color(255, 51, 51));
        Stop.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        Stop.setForeground(new java.awt.Color(255, 255, 255));
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(spv.Application0.class).getContext().getResourceMap(ReadingSourceDialog.class);
        Stop.setText(resourceMap.getString("stop.text")); // NOI18N
        Stop.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Stop.setName("stop"); // NOI18N
        Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bar, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Stop, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Stop, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(bar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  
    private void StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopActionPerformed
        this.S.stopParsing=true;//stop parsing
         
        this.S.positionInTheFile=0;
        dispose();

    }//GEN-LAST:event_StopActionPerformed

    /**
    * @param args the command line arguments
    */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Stop;
    private javax.swing.JProgressBar bar;
    private javax.swing.JScrollBar jScrollBar1;
    // End of variables declaration//GEN-END:variables
public void setPercent(int procent)
{
    bar.setValue(procent);
    
    
}
public Source S=null;
}