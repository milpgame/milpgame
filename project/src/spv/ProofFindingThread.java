//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv;


public class ProofFindingThread extends Thread
{
 DemonstrationEditor editor=null;
 public ProofFindingThread(DemonstrationEditor e)
 {
  this.editor=e;
 }
@Override
public void run()
{
 //automated proof finding
 this.editor.automation();
}

}
