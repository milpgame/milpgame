//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv;
import java.util.List;

public class SyntacticItem
     {
        void SyntacticItem()
        {

            this.containedItems=null;
            
        };
      //1 axiom 2 theorem 3 variable 4 proper_hypothesis 5 repetition
      public byte a_p_v;
     
      public String definitionClass;
      public String definitionName;
      public String motherVariable="";//variable from above
 
      public int initialPosition;
      public int finalPosition;
      
      public List<SyntacticItem> containedItems;
      public SyntacticItem fatherItem;
          
      //for demonstration salvage
      public boolean hub=false;
      public boolean repetition=false;
      public int repetitionNumber=0;
      //END demonstration salvage
       
     }