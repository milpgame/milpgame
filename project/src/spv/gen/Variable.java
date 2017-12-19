//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv.gen;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.*;

public class Variable implements java.io.Serializable
     {
        public Variable()
        {
           
            
        };
        
      
      public String variableClass;
      public String variableText;

      public String label;//declaration label
      public String numberOfLabelOrder;//number of order declaration label
    
      
      
      
      @Override
      public String toString()
          {

           return ("["+this.variableText+":"+this.variableClass+"] ");
          };

     }