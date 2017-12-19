//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv;


public class ReadingThread extends Thread
{
 spv.MainWindow frame=null;
 Source S=null;
 boolean mustBeStopped=false;
public  ReadingThread(Source S,spv.MainWindow frame)
{
    this.S=S;
    this.frame=frame;
}
@Override
public void run()
{
 ParsingItem dataRoot=null;

 dataRoot=S.parse();
 if ((dataRoot==null)||(S.stopParsing)) return;
 int length=S.folderPath.length();
 String name2=S.name.substring(length, S.name.length());
 String name3=name2;
 if (name2.charAt(0)=='\\') {name3=name2.substring(1);}
 FilePanel filePanel=new FilePanel();
 frame.filesTab.addTab("File: "+name3, filePanel);
  S.fileName=name3;//we memorize the name of file
  this.S.editable=true;//we mark that source is editable
  filePanel.visualContent(this.S,this.frame);
  filePanel.updateSummary(filePanel.generateSummary());
   
}

}