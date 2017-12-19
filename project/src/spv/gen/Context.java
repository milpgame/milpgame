//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv.gen;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public  class Context implements Cloneable
     {
        public void Context()
        {
            savedConstants= new java.util.HashSet<String>();
            savedVariables= new java.util.HashSet<String>();
            variableAndTypeSaved= new java.util.HashMap<String, String>();
            distinctSaved=new java.util.ArrayList<java.util.List<String>>();
            blockHypothesesSaved=new ArrayList<Hypothesis>();
           
            
        };
        
      public Set<String> savedConstants=null;
      public Set<String> savedVariables=null;
      public Map<String,String> variableAndTypeSaved=null;
      public List<List<String>> distinctSaved=null;
      public List<Hypothesis> blockHypothesesSaved=null;
      
     }