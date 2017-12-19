//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv.gen;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;
import java.util.Set;
import spv.ParsingItem;
import java.io.Serializable;


public class Hypothesis implements java.io.Serializable
     {
                  
      public String name;
    
      public List<ConstantAndVariable> items;
      
      public List<Variable> variables;
     
      @Override
      public String toString() { return this.name;};

     }