//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv.gen;

import java.util.Comparator;


public class CompareVariablesOrder implements Comparator<Variable>
{
//we compare two variables by the number of order of the declaration label
public int compare (Variable v1,Variable v2)
{
int o1=0,o2=0;
if (v1.numberOfLabelOrder!=null) o1=java.lang.Integer.parseInt(v1.numberOfLabelOrder);
if (v2.numberOfLabelOrder!=null) o2=java.lang.Integer.parseInt(v2.numberOfLabelOrder);

return o1-o2;
}
}