//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv;

import org.jdesktop.application.Application;

public class ReadingInclusionThread extends Thread
{
 Source S1=null;
 Source S2=null;
 boolean mustBeStopped=false;
public  ReadingInclusionThread(Source S1,Source S2)
 {   this.S1=S1;
     this.S2=S2;
 }
@Override
public void run()
{
 ParsingItem dataRoot=null;

 dataRoot=S2.parse();
 if ((dataRoot==null)||(S2.stopParsing)) return;
 S1.includeTheSource(S2);
 S1.numberOfOrderOfLabel=S2.numberOfOrderOfLabel;
  Application0 a=(Application0)Application.getInstance();
         if(a!=null)
         {
         if(a.frame0!=null)
         {

              int length=S2.folderPath.length();
               String name2=S2.name.substring(length, S2.name.length());
               String name3=name2;
               if (name2.charAt(0)=='\\') {name3=name2.substring(1);}
              FilePanel pf=new FilePanel();
              a.frame0.filesTab.addTab("File: "+name3, pf);
              pf.visualContent(S2,a.frame0);
              pf.updateSummary(pf.generateSummary());
         }
         }
}

}