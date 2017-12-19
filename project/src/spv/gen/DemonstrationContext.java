//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv.gen;

import java.io.Serializable;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import spv.*;

public class DemonstrationContext implements java.io.Serializable
{
 public  DemonstrationContext()
 {
 }
 static final long serialVersionUID=1;
 public String fileName="";
 public String problemName="";
 public boolean fullDemonstration=false;// demonstration type
 //0-backward chaining 1-forward chaining
 public int demonstrationStrategyType=0;
 public DemonstrationItem demonstrationSource=null;
 //link between new variable and his class
 public Map<String,String> newVariableAndClass=
                       new java.util.HashMap<String,String>();
 //link between new variables($v) and their content in this demonstration
  public Map<String,List<ConstantAndVariable>> newVariableAndContent=
            new java.util.HashMap<String,java.util.List<ConstantAndVariable>>();
  /**
   * link between new statement ($a) and their content at phase 0 in this
   * demonstration
   */
public Map<String,List<ConstantAndVariable>> newStatementAndContentAtZeroPhase=
            new java.util.HashMap<String,java.util.List<ConstantAndVariable>>();
/**
 * link between new statement ($a) and their generated variable content
 */
  public Map<String,List<String>> newStatementAndGeneratedVariableContent=
                       new java.util.HashMap<String,List<String>>();
  /**
   * link between new statement ($a) and new generated variable
   */
 
 public Map<String,List<String>> newStatementAndGeneratedVariables=
                       new java.util.HashMap<String,List<String>>();
 /**
  * list of items that will be applied at a certain moment in the proof
  */
 public List<AppliedItem>
            listOfAppliedItems=new ArrayList<AppliedItem>();
 
 public int numberOfOrderNewStatements;

 public int numberOfOrderVariables;

 public int numberOfHubs;
}