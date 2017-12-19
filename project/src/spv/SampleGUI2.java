//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv;

import java.awt.*;
public class SampleGUI2  extends javax.swing.JFrame {
     int lat1=60,inal1=35;
     int lat_bloc=10,inal_bloc=10;
     int max_lat_bloc=600;

    public SampleGUI2() {
        derulare();
    }

   
    @SuppressWarnings("unchecked")
   
    private void derulare() {


        panou_derulare = new javax.swing.JScrollPane();
        panou1 = new javax.swing.JPanel();
        
        panou_principal = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form");

        this.setPreferredSize(new Dimension(650,650));

        panou_derulare.setName("jScrollPane4");


        panou1.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
        "Block1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
        javax.swing.border.TitledBorder.DEFAULT_POSITION,
        new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 0, 153)));
        
        panou1.setForeground(new java.awt.Color(51, 255, 0));
        panou1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panou1.setName("jPanel1");

        
        
        
       
        panou1.setLayout(null);
        

        panou1.setPreferredSize(new Dimension(lat_bloc,inal_bloc));

        String s1="aaa\naaaaaaaaaaaaa\naaaaaaaaaaaaa\naaaaa",s2="";
         s2=s1.replaceAll("\n", "<br>");

         System.out.println(s1);
         System.out.println(s2);


        String[] s={"1234","1234","123456789","12345","12345","12345","12345",
        "1234512",
        "<HTML><base href='file:C://symbols// '  />"+
        "<img src='mm.gif' width='64'"
      + " height='64' alt='Waving Duke icon'/>"
      + "</HTML>"
                ,"1234512","<HTML>12341221221212<br>512345123<br>451121212121212121212345</HTML>","12345",
        "1234512341234","12345123412345","<HTML>1234<br>51234<br>1234<br>12345678788788<br>1234</HTML>","12345","1234512","1234512"
                ,"1234512"};

        int x1=10;int y1=10;
        int max_inal1=0;
        for(int i=0;i<s.length;i++)
        {
            Dimension d;
            d=this.dim_eticheta(s[i]);
            lat1=d.width;
            inal1=d.height;
           if(inal1>max_inal1) max_inal1=inal1;
            if (x1+lat1+10>max_lat_bloc)
          {
              x1=10;
              y1=y1+max_inal1+10;
              max_inal1=inal1;
          }
          else if ((x1+lat1+10>lat_bloc)&(x1+lat1+10<=max_lat_bloc))
          {
              lat_bloc=x1+lat1+10;
              
          }
          if (y1+inal1+10>inal_bloc)
        {
              int dist=y1+inal1+10-inal_bloc;
            inal_bloc=inal_bloc+dist;
         
        }
          this.init_eticheta(panou1, s[i],x1,y1);
          x1=x1+lat1+10;
          
        }
        
        
        
        

        panou_principal.setLayout(null);
        panou_derulare.setPreferredSize(new Dimension(620,620));
        panou_derulare.setVerticalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        panou_derulare.setHorizontalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panou_principal.add(panou1);

        panou1.setBounds(10, 10, lat_bloc, inal_bloc);
        

        panou_derulare.setViewportView(panou_principal);


       //derulare.setViewport(panou2);
              this.add(panou_derulare);
        
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SampleGUI1().setVisible(true);
            }
        });
    }

    public void init_eticheta(javax.swing.JPanel p,String s,int x,int y)
    {
        p.setPreferredSize(new Dimension(lat_bloc,inal_bloc));
        javax.swing.JLabel etic=new javax.swing.JLabel();
        etic.setBackground(new java.awt.Color(251, 244, 255));
        etic.setForeground(new java.awt.Color(100, 232, 255));
        etic.setText(s);
        etic.setOpaque(true);
        
        //etic.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
        //"Variables", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
        //javax.swing.border.TitledBorder.DEFAULT_POSITION,
        //new java.awt.Font("Tahoma 11 Bold", 0, 12),
        //new java.awt.Color(0, 0, 204)));
        
        etic.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        etic.setFocusCycleRoot(true);
        etic.setName("jLabel1");
        System.out.println("Lat:"+etic.getPreferredSize().width+"Inal:"+etic.getPreferredSize().height);
         p.add(etic);
         
        etic.setBounds(x, y, lat1,inal1);
       
        
    }

     public Dimension dim_eticheta(String s)
    {
        javax.swing.JLabel etic=new javax.swing.JLabel();
        etic.setBackground(new java.awt.Color(51, 0, 255));
        etic.setForeground(new java.awt.Color(0, 0, 255));
        etic.setText(s);

        //etic.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
        //"Variables", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
        //javax.swing.border.TitledBorder.DEFAULT_POSITION,
        //new java.awt.Font("Tahoma 11 Bold", 0, 12),
        //new java.awt.Color(0, 0, 204)));

        etic.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        etic.setFocusCycleRoot(true);
        etic.setName("jLabel1");
        return etic.getPreferredSize();
        
    }
   public javax.swing.JEditorPane init_panou_editare()
    {
     javax.swing.JEditorPane panou= new javax.swing.JEditorPane();
     panou.setBackground(new java.awt.Color(240, 240, 240));
     panou.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
     "Axiom", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
     javax.swing.border.TitledBorder.DEFAULT_POSITION,
     new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 255, 0)));
     
     panou.setText("alabalaalabalaalabalaalabalaalabalaalabalaalabalaalabalaal"
     + "abalaalabalaalabalaalabalaalabalaalabalaal\nalabalaalabalaalabalaalabal"
     + "aalabalaalabalaalabalaalabala\nalabalaalabalaalabalaalabalaalabalaalaba"
     + "laalabalaalabala\nalabalaalabalaalabalaalabalaalabalaalabalaalabalaalab"
     + "ala\nalabalaalabalaalabalaalabalaalabalaalabalaalabalaalabala\nalabala"
   + "alabalaalabalaalabalaalabalaalabala\nalabala\nalabala\nalabala\nalabala"); 
     panou.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
     panou.setName("jEditorPane1"); 
      return panou;
    }

    
    
   
    private javax.swing.JPanel panou1;
   // private javax.swing.JPanel panou2;
    private javax.swing.JPanel panou_principal;
    private javax.swing.JScrollPane panou_derulare;
    // End of variables declaration

}
