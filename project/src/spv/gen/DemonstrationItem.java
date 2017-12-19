//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv.gen;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;
import spv.Application0;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import java.util.Map;
import spv.DisplayDemonstrationTree;
import java.io.Serializable;




public class DemonstrationItem implements java.io.Serializable
{

public DemonstrationItem aboveLink;

public String name;
public String undername;
public String variableClass;
public  Object referenceObject;
/**
 * 0 simple axiom 1 simple theorem 2 hypothesis from complex axiom
 * 3 hypothesis from complex theorem 4 axiom from complex axiom
 * 5 theorem from complex theorem 6 new statement 7 variable content
 * 8 variable 9 so called variable 10 so called hypothesis
 */
public int type;
/**
 * if is marked as a hub it will be repeated later in the demonstration
 */
public boolean markedAsAHub=false;
/**
 * if the statement or variable content is repeating
 */
public boolean repetition=false;
// number of order in the compressed proof
public int numberOfOrderInCompressedProof;
public List<ConstantAndVariable> items;
public List<ConstantAndVariable> provisionallyItems;// for help


public List<DemonstrationItem> downLink;
public void errorLine( String msg)
{
System.out.println(msg);
};
public String typeMethod(int t)
{ String text="";

if (t==DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM
        | t==DemonstrationConstants.SIMPLE_AXIOM)
{text="According to axiom: ";}
else if (t==DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM
        | t==DemonstrationConstants.SIMPLE_THEOREM)
{text="According to theorem: ";}
else if (t==DemonstrationConstants.TARGET)
{text="Target statement is: ";}
else if
(t==DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_THEOREM |
t==DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_AXIOM)
{text="Hypothesis required: ";}
else if (t==DemonstrationConstants.PROPER_HYPOTHESIS)
{text="Hypothesis: ";}
else if (t==DemonstrationConstants.VARIABLE |this.type==DemonstrationConstants.PROPER_VARIABLE )
{text="Variable: ";}
else if (t==DemonstrationConstants.NEW_STATEMENT )
{text="New statement: ";}

  return text;
}

@Override
public String toString()
{
//
Application0 a=(Application0)Application.getInstance();

//
int i=0;
String displayedText="",displayedName=this.name;
if (this.type==DemonstrationConstants.VARIABLE
   )
{
    String s1=displayedName,s2="";
    s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
    if (s2==null) s2=s1;
   s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
   displayedName=s2;
   displayedText=typeMethod(this.type)+displayedName+" is: ";
   displayedText="<I>"+displayedText+"</I> <BR>";
}
 else
if (this.type==DemonstrationConstants.PROPER_VARIABLE)
{
    String s1=displayedName,s2="";
    s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
    if (s2==null) s2=s1;
   s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
   displayedName=s2;
   displayedText=typeMethod(this.type)+displayedName+" : ";
   displayedText="<I>"+displayedText+"</I> <BR>";
}
else
   {
    String s1=displayedName,s2="";
    s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
    if (s2==null) s2=s1;
   s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
   displayedName=s2;
   if (this.type!=DemonstrationConstants.NEW_STATEMENT)
   {
   displayedText=typeMethod(this.type)+"<U>"+displayedName+"</U>"+"  ";
   }
   else
   {
   displayedText=typeMethod(this.type)+displayedName+" : ";
   }
   displayedText="<I>"+displayedText+"</I> <BR>";
   }

//We classify the input

if (this.type==DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM
        | this.type==DemonstrationConstants.SIMPLE_AXIOM)
{
//here we have axioms
if (this.referenceObject!=null)
{
Axiom ax= (Axiom)this.referenceObject;
if (
    (ax.items.size()>0)
    &(a.demonstrationDisplayMode==2)
   )
{ i=0;
do
{
  // we show up the items of the axiom
  //htmldef display
String s1=ax.items.get(i).constantOrVariableText,s2="";
   s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
    if (s2==null) s2=s1;
   s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
  //end htmldef display

displayedText=displayedText+" "+s2;
i++;
} while (i<ax.items.size());
}
}

}
else if (this.type==DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM
        | this.type==DemonstrationConstants.SIMPLE_THEOREM
        )
{
//here we have theorems

if (this.referenceObject!=null)
{
Theorem tex= (Theorem)this.referenceObject;
if ((tex.items.size()>0)
    &(a.demonstrationDisplayMode==2)
    )
{ i=0;
do
{
// we show up the items of the theorem
//htmldef display
String s1=tex.items.get(i).constantOrVariableText,s2="";
   s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
    if (s2==null) s2=s1;
   s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
//end htmldef display
displayedText=displayedText+" "+s2;
i++;
} while (i<tex.items.size());
}
}

}
else if
(this.type==DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_THEOREM |
this.type==DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_AXIOM )
{
//here we have hypotheses
if (this.referenceObject!=null)
{
Hypothesis hypx= (Hypothesis)this.referenceObject;
if (
     (hypx.items.size()>0)
     &(a.demonstrationDisplayMode==2)
   )
{ i=0;
do
{
//we show up the items of the hypothesis
 //display htmldef
String s1=hypx.items.get(i).constantOrVariableText,s2="";
   s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
    if (s2==null) s2=s1;
   s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
//end htmldef display
displayedText=displayedText+" "+s2;
i++;
} while (i<hypx.items.size());
}
}

}
 else
  if (this.type==DemonstrationConstants.PROPER_HYPOTHESIS)
{
//here we have a so called hypothesis
if (this.referenceObject!=null)
{
Hypothesis hypx= (Hypothesis)this.referenceObject;
if (hypx.items.size()>0)
{ i=0;
do
{
//here we show up the items of the hypothesis
 //display htmldef
String s1=hypx.items.get(i).constantOrVariableText,s2="";
   s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
    if (s2==null) s2=s1;
   s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
//end display htmldef
displayedText=displayedText+" "+s2;
i++;
} while (i<hypx.items.size());
}
}

}


else if (this.type==DemonstrationConstants.VARIABLE )
{
//here we have provisionally variables
if (this.provisionallyItems!=null)
{
if (this.provisionallyItems.size()>0)
{ i=0;
displayedText=displayedText+" ";
do
{
  //show htmldef
String s1=this.provisionallyItems.get(i).constantOrVariableText,s2="";
   s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
    if (s2==null) s2=s1;
   s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
 //end show htmldef
displayedText=displayedText+" "+s2;
i++;
} while (i<this.provisionallyItems.size());
}
displayedText=displayedText+" ";

}

}
else if (this.type==DemonstrationConstants.NEW_STATEMENT
        |this.type==DemonstrationConstants.TARGET
        |this.type==DemonstrationConstants.NEW_FORWARD_STATEMENT)
{
if (this.items.size()>0)
{ i=0;
do
{
 //show htmldef
String s1=this.items.get(i).constantOrVariableText,s2="";
   s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
   if (s2==null) s2=s1;
   s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");

//end show htmldef
displayedText=displayedText+" "+s2;
i++;
} while (i<this.items.size());
}
}


if (this.numberOfOrderInCompressedProof>0)
         displayedText=displayedText+" ::"+this.numberOfOrderInCompressedProof;
if (this.repetition==true)
{ displayedText=""+displayedText+" :Repetition";}
if (this.markedAsAHub==true)
{ displayedText=""+displayedText+" :Hub";}

displayedText=
"<HTML>"
 +"<base href='file:"+a.path+"/symbols/  '/>"
 +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
 +"<FONT COLOR='#ffffff' SIZE=3 >"
+displayedText
+"</FONT>"
+"</P>"
+"</HTML>";

return displayedText;
};


}