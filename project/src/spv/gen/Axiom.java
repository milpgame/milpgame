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

public class Axiom implements java.io.Serializable
     {
        public  Axiom()
        {
           
            
        };
       
           
      public  String name;
      public byte  type=1;//1 simple axiom 2 complex axiom
      public  List<ConstantAndVariable> items;
      public List<Hypothesis> hypotheses;

      public List<Variable> variables;
      
      public List<List<String>> distinct;

      public List<Variable> totalVariables;
      
      public transient ParsingItem sourceLabel;
      @Override
      public String toString() 
      {
          
          return this.name;
      };

     }