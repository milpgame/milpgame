//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv.gen;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.Serializable;


public  class ConstantAndVariable implements java.io.Serializable
{
public ConstantAndVariable()
{
   constantOrVariable=3;
   variableClass="";
   constantOrVariableText="";

};

public byte constantOrVariable;// 1 constant 2 variable 3 undefined symbol
public String variableClass;
public String constantOrVariableText;


@Override
public String toString()
  {
 String s="undefined";
 if (this.variableClass!=null) s=this.variableClass;
 return ("["+this.constantOrVariable+"|"+this.constantOrVariableText+":"+s+"]");
  };

}