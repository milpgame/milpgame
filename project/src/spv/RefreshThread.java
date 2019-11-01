//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv;

public class RefreshThread extends Thread
{
 DemonstrationEditor editor=null;
 public RefreshThread(DemonstrationEditor e)
 {
  this.editor=e;
 }
 @Override
public void run()
{
 do
 {
  if (this.editor.stop){break;}
  if (this.editor.weHaveFoundTheProof){break;}
  this.editor.refreshTree();
 }
 while(true);
}

}
