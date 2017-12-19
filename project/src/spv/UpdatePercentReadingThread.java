//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv;

public class UpdatePercentReadingThread extends Thread
{
 spv.ReadingSourceDialog dialog=null;
 Source S=null;
 ReadingThread thread1=null;
public  UpdatePercentReadingThread(Source S,spv.ReadingSourceDialog dialog)
{
    this.dialog=dialog;
    this.S=S;
    
}
@Override
public void run()
{
 float dimension=100;
 float position=1;
 int percent=1;
 float report=1;

 if (S!=null)
 {
 if (S.fin!=null)
 {
  try {
       dimension=S.fin.getChannel().size();
     } catch (Exception ex) {}

  

 }
 }

 if (dimension==0) dimension=100;

 do
 {
 if (S!=null)
 {
     position=(long)S.positionInTheFile;
     if (S.typeSymbol==Source.T_END) position=100;
 }

 report=((position/dimension)*100);
 percent=(int)report;
 
 dialog.setPercent(percent);


 try
 {
     Thread.sleep(100);
 }
 catch(Exception ex) { }

 if (position==100)
 {
     dialog.dispose();
     return;
 }
 
 if(S.stopParsing) return;//if the dialog is closed we exit from thread

 }while (true);


}

}