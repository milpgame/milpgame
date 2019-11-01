//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv;



public class DistinctRequirement {
    public DistinctRequirement(String provedTheorem0, String appliedRule0,
       String variablesAndContent0,String distinctV10,String distinctV20)
    {
        provedTheorem=provedTheorem0;
        appliedRule=appliedRule0;
        variablesAndContent=variablesAndContent0;
        distinctV1=distinctV10;
        distinctV2=distinctV20;
    }

    public String provedTheorem="";
    public String appliedRule="";
    public String variablesAndContent="";
    public String distinctV1="";//distinct variable at position 1
    public String  distinctV2="";//distinct variable at position 2
     

}
