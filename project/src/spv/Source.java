//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.Set;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import spv.gen.Hypothesis;
import spv.gen.Axiom;
import spv.gen.Theorem;
import spv.gen.Variable;
import spv.gen.ConstantAndVariable;
import spv.gen.Context;
import spv.gen.DemonstrationItem;

import spv.gen.DemonstrationConstants;
import java.util.Collections;
import java.util.BitSet;
import javax.swing.JFrame;
import org.jdesktop.application.Application;
import javax.swing.JOptionPane;



public class Source {
    
 //data declaration

   //input file stream
 FileInputStream fin;
  
  //symbol codes
 public static byte
    C_DOLLAR_POINT=1,C_C_SYMBOL=2, C_V_SYMBOL=3, C_D_SYMBOL=4, C_F_SYMBOL=5, C_E_SYMBOL=6, C_A_SYMBOL=7,
    C_P_SYMBOL=8,C_EQUAL_SYMBOL=9,C_OPEN_BRACE=10,C_CLOSED_BRACE=11,C_OPEN_PARENTHESIS=12,
    C_CLOSED_PARENTHESIS=13,C_OPEN_SQUARE_BRACKET=14,C_CLOSED_SQUARE_BRACKET=15,C_ANYOTHER_SYMBOL=16;

  //the type of symbol
 public static byte  
     T_LABEL=17,T_SYMBOL=18,T_COMMENT=19,T_KEY_SYMBOL=20,T_LABEL_OR_SYMBOL=21,
     T_END=22,T_FATHER_FILE=23;
 
 // the function of symbol
 public static byte F_VARIABLE=23,F_CONSTANT =24,F_UNDEFINED=25;
 public static byte F_LABEL_F=26,F_LABEL_E=27,F_LABEL_A=28,F_LABEL_P=29;

 public static byte F_NORMAL_COMMENT=30,F_CHAPTER_COMMENT=31,F_SUBCHAPTER_COMMENT =32;
 public static byte F_NAME_COMMENT_A_P=33,F_DATE_COMMENT=34,F_SUPERCHAPTER_COMMENT=46;

 
 //codification needed to parse the comments
 //the type of symbol

 //new items
 public static byte  TN_CHAPTER_FROM_COMMENT=35,TN_SUBCHAPTER_FROM_COMMENT=36,TN_CONTENT_FROM_COMMENT=37;
 public static byte  TN_COMMENT_FROM_TYPESETTINGS=38,TN_CHAPTER_FROM_TYPESETTINGS=39,TN_SUPERCHAPTER_FROM_COMMENT=45;
 //other items
 public static byte  TN_TO_BE_REPLACED=40,TN_UNTAINED=41,TN_STRING=42
         ,TN_DISPLAY_DEFINITION=43,TN_DISPLAY=44;
 //END comments codification
  
 //position in the Metamath file
 int positionInTheFile=0;
 boolean fileEnd=false;
 boolean graveApostropheActivated=false;
 boolean newLine=false;//if we have \n
 int commentsNumber=0;
 
 //symbol variable
 byte typeSymbol;
 byte functionSymbol;
 byte codeSymbol;
 String textSymbol;
 int positionSymbol;
 int lengthSymbol;
 byte errorCodeSymbol;
 boolean newLineSymbol;

 //previous symbol variable
 byte previousTypeSymbol;
 byte previousFunctionSymbol;
 byte previousCodeSymbol;
 String previousTextSymbol;
 int previousPositionSymbol;
 int previousLengthSymbol;
 byte previousErrorCodeSymbol;
 boolean previousNewLineSymbol;
//variables to build the tree
 public ParsingItem dataRoot=new ParsingItem();
 ParsingItem dataParentItem=new ParsingItem();
 ParsingItem dataCurrentItem=new ParsingItem();

  //crossing
  ParsingItem  lastLeaf=null;
 // END crossing

  //Variables with c,v,f,d
  Set<String> constants= new java.util.HashSet<String>();
  Set<String> variables= new java.util.HashSet<String>();
  Map<String,String> variableAndType= new java.util.HashMap<String, String>();
  Map<String,String> variableAndLabel= new java.util.HashMap<String, String>();
  Map<String,String> labelAndVariable= new java.util.HashMap<String, String>();
  List<List<String>> distincts=new java.util.ArrayList<java.util.List<String>>();
  //every generated hypotheses
  Map<String,Object> hypotheses= new java.util.HashMap<String, Object>();
   //every generated axioms
  Map<String,Object> axioms= new java.util.HashMap<String, Object>();
   //available axioms at a moment
  Set<String> availableAxioms= new java.util.HashSet<String>();
   //every generated theorems
  Map<String,Object> theorems = new java.util.HashMap<String, Object>();
  //available theorems at a moment
  Set<String> availableTheorems= new java.util.HashSet<String>();
  int numberOfInactiveTheorems=0;// number
   //link between occurrence order and label
 Map<String,String> labelAndOrder=new java.util.HashMap<String,String >();
 //the set of all the labels
 Set<String>  setOfLabels=new java.util.HashSet<String>();
 int numberOfOrderOfLabel=1;//number of order of current label
  //superchapter, chapter, subchapter
 ParsingItem commentSuperchapter=null,commentChapter=null,commentSubchapter=null;
                 
 //END Variables with c,v and f



 //hypotheses from a block
List<Hypothesis> blockHypotheses=new ArrayList<Hypothesis>();
//captured item usually represent e,a,p label
ParsingItem capturedItem=null;
//if this variable is active we stop the parsing
public boolean stopParsing=false;
public String name="";
public String fileName="";

public boolean editable=false;//if the source is editable
public String folderPath="";
//variables for nice display
public Map<String,String> htlmldefString1AsString2=
                                       new java.util.HashMap<String, String>();

//END  variables for nice display

//map for <clasa_variabila, list of syntactic definitions(type:wff,set,class)>
public Map<String,List<Object>> classListOfDefinitions=
new java.util.HashMap< String,
                     List<Object>>();

//END map
public byte[] buffer1=new byte[200];// array used for file reading
public int positionInBuffer1=0;//position in the buffer1
public int maxBuffer1=0;//maximum of read byte from buffer1
public byte buffer0=0;//current byte from the file

//variables for building distinct requirements of proved theorem

public String provedTheorem1="";
public String appliedRule1="";
public String variablesAndContent1="";
//list of generated distinct requirements for the proved theorem
 List<DistinctRequirement>  listDR=new ArrayList<DistinctRequirement>();

//END requirements
public Source(String fileName)
{   
       try
        {
        fin=new FileInputStream(fileName);
        this.name=fileName;
        }
           catch(IOException e)
        {
            
             JOptionPane.showMessageDialog
                    (null,
                     e.getMessage(),
                     "Error!",
                     JOptionPane.INFORMATION_MESSAGE
                     );
            
        }
};
public int readNextByte()
{
 int code=1;
 if (positionInBuffer1<maxBuffer1)
 {
   buffer0=buffer1[positionInBuffer1];
   positionInBuffer1++;
 }
 else
 {
   try {
       code = fin.read(buffer1);
       } catch (IOException ex)
       {
       //Logger.getLogger(Source.class.getName()).log(Level.SEVERE, null, ex);
       JOptionPane.showMessageDialog
                    (null,
                     "Error reading from file[ o.s.]! "+ex.getMessage(),
                     "Error!",
                     JOptionPane.INFORMATION_MESSAGE
                     );
       }
   if (code>-1)
   {
     if (code>0)
     {
       maxBuffer1=code;
       positionInBuffer1=0;
       buffer0=buffer1[positionInBuffer1];
       positionInBuffer1++;
     }
    }
   }

 return code;
}
protected void addToDataNodeAndChangeNode()
{
//data
if (this.dataParentItem.containedItems==null)
        {
        List<ParsingItem> ls=new java.util.ArrayList<ParsingItem>();
        this.dataParentItem.containedItems=ls;
        }
   this.dataParentItem.containedItems.add(this.dataCurrentItem);
         //links
            this.dataCurrentItem.fatherItem=this.dataParentItem;
         //END links
        this.dataParentItem=this.dataCurrentItem;
         //refer to chapters
          tieUpItemToTheChapter();
       //END chapters
//fin
};

protected void addToDataNode()
{
//data
    if (this.dataParentItem.containedItems==null)
    {
    List<ParsingItem> ls=new java.util.ArrayList<ParsingItem>();
    this.dataParentItem.containedItems=ls;
    }
this.dataParentItem.containedItems.add(this.dataCurrentItem);
 //links
 this.dataCurrentItem.fatherItem=this.dataParentItem;
 //END links
 //refer to chapters
      tieUpItemToTheChapter();
 //END chapters
//END
};

protected void changeDataNode(ParsingItem previousData )
{
//data
this.dataParentItem=previousData;
//END
};

public void getSymbol()
{
//memorize previous symbol
previousTypeSymbol=typeSymbol ;
previousFunctionSymbol=functionSymbol;
previousCodeSymbol=codeSymbol;
previousTextSymbol=textSymbol;
previousPositionSymbol=positionSymbol;
previousLengthSymbol=lengthSymbol;
previousErrorCodeSymbol=errorCodeSymbol;
previousNewLineSymbol=newLineSymbol;
//data items
this.dataCurrentItem= new ParsingItem();
this.dataCurrentItem.typeSymbol=previousTypeSymbol;
this.dataCurrentItem.functionSymbol=previousFunctionSymbol;
this.dataCurrentItem.codeSymbol=previousCodeSymbol;
this.dataCurrentItem.textSymbol=previousTextSymbol;
this.dataCurrentItem.positionSymbol=previousPositionSymbol;
this.dataCurrentItem.lengthSymbol=previousLengthSymbol;
this.dataCurrentItem.errorCodeSymbol=previousErrorCodeSymbol;
this.dataCurrentItem.onNewLine=previousNewLineSymbol;



int number;
boolean buildLabelOrSymbol=false;
int initialPositionOnLabelOrSymbol=-1;
StringBuilder buildedLabelOrSymbol=new StringBuilder();
StringBuilder comment=new StringBuilder();

boolean exitFromDo=false;
do {

 number=this.readNextByte();
if (number!=-1)
{
if( buffer0== '$')
{

 number=this.readNextByte();
if (number!=-1) {
        if(  buffer0=='.' )
        {
         typeSymbol=Source.T_KEY_SYMBOL;
         codeSymbol=Source.C_DOLLAR_POINT;
         textSymbol="$.";
         positionSymbol=positionInTheFile;
         lengthSymbol=2;
         positionInTheFile=positionInTheFile+2;
         newLineSymbol=this.newLine;
         exitFromDo=true;
         break;
        }
        else
        if(buffer0=='c' )
        {
         typeSymbol=Source.T_KEY_SYMBOL;
         codeSymbol=Source.C_C_SYMBOL;
         textSymbol="$c";
         positionSymbol=positionInTheFile;
         lengthSymbol=2;
         positionInTheFile=positionInTheFile+2;
         newLineSymbol=this.newLine;
         exitFromDo=true;
         break;
        }
      else
       if( buffer0=='v' )
        {
         typeSymbol=Source.T_KEY_SYMBOL;
         codeSymbol=Source.C_V_SYMBOL;
         textSymbol="$v";
         positionSymbol=positionInTheFile;
         lengthSymbol=2;
         positionInTheFile=positionInTheFile+2;
         newLineSymbol=this.newLine;
         exitFromDo=true;
         break;
        }

       else
       if( buffer0=='d' )
        {
         typeSymbol=Source.T_KEY_SYMBOL;
         codeSymbol=Source.C_D_SYMBOL;
         textSymbol="$d";
         positionSymbol=positionInTheFile;
         lengthSymbol=2;
         positionInTheFile=positionInTheFile+2;
         newLineSymbol=this.newLine;
         exitFromDo=true;
         break;
        }
        else
       if( buffer0=='e' )
        {
         typeSymbol=Source.T_KEY_SYMBOL;
         codeSymbol=Source.C_E_SYMBOL;
         textSymbol="$e";
         positionSymbol=positionInTheFile;
         lengthSymbol=2;
         positionInTheFile=positionInTheFile+2;
         newLineSymbol=this.newLine;
         exitFromDo=true;
         break;
        }
         else
       if( buffer0=='f' )
        {
         typeSymbol=Source.T_KEY_SYMBOL;
         codeSymbol=Source.C_F_SYMBOL;
         textSymbol="$f";
         positionSymbol=positionInTheFile;
         lengthSymbol=2;
         positionInTheFile=positionInTheFile+2;
         newLineSymbol=this.newLine;
         exitFromDo=true;
         break;
        }
         else
       if( buffer0=='a' )
        {
         typeSymbol=Source.T_KEY_SYMBOL;
         codeSymbol=Source.C_A_SYMBOL;
         textSymbol="$a";
         positionSymbol=positionInTheFile;
         lengthSymbol=2;
         positionInTheFile=positionInTheFile+2;
         newLineSymbol=this.newLine;
         exitFromDo=true;
         break;
        }
         else
       if( buffer0=='p' )
        {
         typeSymbol=Source.T_KEY_SYMBOL;
         codeSymbol=Source.C_P_SYMBOL;
         textSymbol="$p";
         positionSymbol=positionInTheFile;
         lengthSymbol=2;
         positionInTheFile=positionInTheFile+2;
         newLineSymbol=this.newLine;
         exitFromDo=true;
         break;
        }
         else
       if( buffer0=='=' )
        {
         typeSymbol=Source.T_KEY_SYMBOL;
         codeSymbol=Source.C_EQUAL_SYMBOL;
         textSymbol="$=";
         positionSymbol=positionInTheFile;
         lengthSymbol=2;
         positionInTheFile=positionInTheFile+2;
         newLineSymbol=this.newLine;
         exitFromDo=true;
         break;
       }
        else
       if( buffer0=='{' )
        {
         typeSymbol=Source.T_KEY_SYMBOL;
         codeSymbol=Source.C_OPEN_BRACE;
         textSymbol="${";
         positionSymbol=positionInTheFile;
         lengthSymbol=2;
         positionInTheFile=positionInTheFile+2;
         newLineSymbol=this.newLine;
         exitFromDo=true;
         break;
       }
         else
       if( buffer0=='}' )
        {
         typeSymbol=Source.T_KEY_SYMBOL;
         codeSymbol=Source.C_CLOSED_BRACE;
         textSymbol="$}";
         positionSymbol=positionInTheFile;
         lengthSymbol=2;
         positionInTheFile=positionInTheFile+2;
         newLineSymbol=this.newLine;
         exitFromDo=true;
         break;
       }
        else
       if( buffer0=='[' )
        {
         typeSymbol=Source.T_KEY_SYMBOL;
         codeSymbol=Source.C_OPEN_SQUARE_BRACKET;
         textSymbol="$[";
         positionSymbol=positionInTheFile;
         lengthSymbol=2;
         positionInTheFile=positionInTheFile+2;
         newLineSymbol=this.newLine;
         exitFromDo=true;
         break;
       }
         else
       if( buffer0==']' )
        {
         typeSymbol=Source.T_KEY_SYMBOL;
         codeSymbol=Source.C_CLOSED_SQUARE_BRACKET;
         textSymbol="$]";
         positionSymbol=positionInTheFile;
         lengthSymbol=2;
         positionInTheFile=positionInTheFile+2;
         newLineSymbol=this.newLine;
         exitFromDo=true;
         break;
       }
       else
       //here starts the comment
       if( buffer0=='(' )
        {
                     typeSymbol=Source.T_COMMENT;
                     codeSymbol=Source.C_ANYOTHER_SYMBOL;
                     positionSymbol=positionInTheFile+2;
                     newLineSymbol=this.newLine;
                     boolean iesi_din_cm = false;
                     
                     comment=new StringBuilder();
               do {
                       
                        number=this.readNextByte();
                       if (number!=-1)
                       {
                       if( buffer0== '$')
                       {
                       
                        number=this.readNextByte();
                       if (number!=-1)
                       {
                       if( buffer0== ')')
                        {
                            //if the comment closes
                             textSymbol=comment.toString();
                             lengthSymbol=positionInTheFile-positionSymbol;
                             positionInTheFile=positionInTheFile+2;
                             iesi_din_cm =true;
                             return;
                        }
                   else {
                          //if we have other character than ( after $
                         char caracter=(char)buffer0;
                         
                         comment.append("$");
                         comment.append(caracter);
                         positionInTheFile=positionInTheFile+2;
                        }
                        }

                  else {
                         //if we could not read the character after $
                       this.errorNewLine("Error in comment 1!");
                       typeSymbol=Source.T_END;
                       codeSymbol=Source.C_ANYOTHER_SYMBOL;
                       functionSymbol=Source.F_UNDEFINED;
                       break;
                       }

                       }
                       //END equal with $$$$

                  else
                      {
                       // if we have in the comment a character different of $
                      char caracter=(char)buffer0;
                      
                      comment.append(caracter);
                      positionInTheFile=positionInTheFile+1;
                      }
                  }
                  //END read for $$$
                else {
                    //if it could not read  some character from the comment
                 
                  this.errorNewLine("Error in comment 2!");
                  typeSymbol=Source.T_END;
                  codeSymbol=Source.C_ANYOTHER_SYMBOL;
                  functionSymbol=Source.F_UNDEFINED;
                  break;
                  }
              }  while (iesi_din_cm==false);

                    if (iesi_din_cm==false)
                    {
                     
                     errorNewLine("It was not found:$)");
                    }
                    else {exitFromDo=true;break;}
           }
             //exit from the comment
  }
else
{
  //error at reading a character after $ from outside the comment

 errorNewLine("Error reading file on loop 2!");
typeSymbol=Source.T_END;
codeSymbol=Source.C_ANYOTHER_SYMBOL;
functionSymbol=Source.F_UNDEFINED;
break;
}
//if we have space,new line, tab, and so on other than character $
} else if ((buffer0==32 || buffer0==13 ||
  buffer0==10 )&& buildLabelOrSymbol==false)
            {
              positionInTheFile=positionInTheFile+1;
              if (buffer0==10)
                               {
                                 this.newLine=true;
                               }
            }
//if we have a character different of $,space, new line,tab and so on
else if (buffer0!='$'&& buffer0!=32 && buffer0!=13
                               &&  buffer0!=10 )
      //here we start the construction of a label or a symbol
{
  if (buildLabelOrSymbol==false)
  {
   buildLabelOrSymbol=true;
   initialPositionOnLabelOrSymbol=positionInTheFile+1;
  }
char caracter=(char)buffer0;

buildedLabelOrSymbol.append(caracter);
positionInTheFile=positionInTheFile+1;
}

//closing the construction of a label or a symbol
else if ((buffer0==32 || buffer0==13 || buffer0==10  )
                     && buildLabelOrSymbol==true)
      {
        positionInTheFile=positionInTheFile+1;
        typeSymbol=Source.T_LABEL_OR_SYMBOL;
        codeSymbol=Source.C_ANYOTHER_SYMBOL;
        textSymbol=buildedLabelOrSymbol.toString();
        positionSymbol=initialPositionOnLabelOrSymbol;
        lengthSymbol=positionInTheFile-initialPositionOnLabelOrSymbol;
        newLineSymbol=this.newLine;

        exitFromDo=true;
        this.newLine=false;//demarcate the flag
        if (buffer0==10)
                               {
                                 this.newLine=true;
                               }
        break;
       }
//error: we have $ inside a symbol
else if (buffer0=='$'&& buildLabelOrSymbol==true)
{
positionInTheFile=positionInTheFile+1;

errorNewLine("It was found $ in a new symbol or in a new label!");
exitFromDo=true;
break;
}

}
else  {
//could not read some character from outside the comment
typeSymbol=Source.T_END;
codeSymbol=Source.C_ANYOTHER_SYMBOL;
functionSymbol=Source.F_UNDEFINED;
break;
}
if (exitFromDo==true){ break;}
} while(true);

};

public final static void errorNewLine( String msg)
{
    System.out.println(msg);
    Application0 a= (Application0) Application.getInstance();
    if(a!=null)
    {
    if(a.frame0!=null)
    {
     a.frame0.errorsString=a.frame0.errorsString+"\n"+msg;
    }
    }
  
};
//information displayed necesary to software construction
public void display( String msg)
{
    //System.out.print(msg);
};
public void displayNewLine( String msg)
{
   //System.out.println(msg);
};
public void displayNewLine2( String msg)
{
   System.out.println(msg);
};


//functions II


public boolean acceptSymbol(byte type, byte code, byte function)
{
       // if (symbol == s) {
       //getsym();
       // return true;
      // }
     // return false;
 if (type==Source.T_KEY_SYMBOL)
 {
     if(code==codeSymbol)
     {
        this.getSymbol();
        return true;
     }
 }
 else if (type==Source.T_LABEL)
        {
           if ( typeSymbol== Source.T_LABEL_OR_SYMBOL)
           {
            this.getSymbol();
            return true;
           }
        }
 else if (type==Source.T_SYMBOL)
        {
           if ( typeSymbol== Source.T_LABEL_OR_SYMBOL)
              {
                this.getSymbol();
                return true;
              }
        }
 else if (type==Source.T_COMMENT)
        {
           if ( typeSymbol== Source.T_COMMENT)
               {
               this.getSymbol();
               return true;
               }
        }
 return false;

};

public  boolean expectSymbol(byte type,byte code, byte function)
{
    //if (accept(s))
     //   return true;
    //error("expect:"+s+" unexpected symbol");
    //return false;

    if (acceptSymbol(type,code,function)) {return true;}
    
    errorNewLine("Expect type: "+type+" code: "+code+" and not other symbol!");
    return false;
};

public void declareConstants()
{
ParsingItem  dataPreviousParent=new ParsingItem();

if (this.acceptSymbol(Source.T_KEY_SYMBOL,Source.C_C_SYMBOL,Source.F_UNDEFINED))
   {
   display("\n-->[Const\n");
   //data
   dataPreviousParent=this.dataParentItem;
   this.addToDataNodeAndChangeNode();
   //END
     do {
        if (acceptSymbol(Source.T_SYMBOL,Source.C_ANYOTHER_SYMBOL,Source.F_UNDEFINED))
        {
            display(" ["+previousTextSymbol+"] ");
             //add the found symbol to the constants
            constants.add(previousTextSymbol);
            this.addToDataNode();
        }
   else  if (this.acceptSymbol(Source.T_COMMENT,
                                          Source.C_ANYOTHER_SYMBOL,Source.F_UNDEFINED))
   {
    //we do nothing because we have a interleaved comment
   }
   else if (acceptSymbol(Source.T_KEY_SYMBOL,Source.C_DOLLAR_POINT,Source.F_UNDEFINED))
         {
          display("\nConst]<--\n");
          this.changeDataNode(dataPreviousParent);
          break;
         }
    else { expectSymbol(Source.T_KEY_SYMBOL,Source.C_DOLLAR_POINT,Source.F_UNDEFINED);
           break;
         }
        } while (true);
    }
};

public void declareVariables()
{
    ParsingItem  dataPreviousParent=new ParsingItem();

    if (this.acceptSymbol(Source.T_KEY_SYMBOL,Source.C_V_SYMBOL,Source.F_UNDEFINED))
   {
        display("\n-->[Var\n");
       //data
        dataPreviousParent=this.dataParentItem;
        this.addToDataNodeAndChangeNode();
        //END
        do {
            if (acceptSymbol(Source.T_SYMBOL,Source.C_ANYOTHER_SYMBOL,Source.F_UNDEFINED))
               {
                display(" ["+previousTextSymbol+"] ");
                //add the found symbol to the variables
                 variables.add(previousTextSymbol);
                 this.addToDataNode();
                }
         else if (this.acceptSymbol(Source.T_COMMENT,
                                          Source.C_ANYOTHER_SYMBOL,Source.F_UNDEFINED))
           {
            //we do nothing because we have a interleaved comment
           }
        else if (acceptSymbol(Source.T_KEY_SYMBOL,
                            Source.C_DOLLAR_POINT,Source.F_UNDEFINED))
                {
                 display("\nVar]<--\n");
                 this.changeDataNode(dataPreviousParent);
                         return;
                 }
            else { 
                   expectSymbol(Source.T_KEY_SYMBOL,Source.C_DOLLAR_POINT,
                                                             Source.F_UNDEFINED);
                   return;
                 }
        } while (true);
    }
};

public void declareDistinctVariables()
{
     ParsingItem  dataPreviousParent=new ParsingItem();

     if (this.acceptSymbol(Source.T_KEY_SYMBOL,Source.C_D_SYMBOL,Source.F_UNDEFINED))
   { 
      display("\n-->[Dist\n");
        //local distinct variables
       List<String> localDistinct=new ArrayList<String>();
      //data
        dataPreviousParent=this.dataParentItem;
        this.addToDataNodeAndChangeNode();
      //END
        do {
            //expect a symbol defined above as a variable
            if (acceptSymbol(Source.T_SYMBOL,Source.C_ANYOTHER_SYMBOL,Source.F_VARIABLE))
                     {
                      display(" ["+previousTextSymbol+"] ");
                      //add variables to a list
                      localDistinct.add(previousTextSymbol);
                      this.addToDataNode();
                     }
        else if (this.acceptSymbol(Source.T_COMMENT,
                                          Source.C_ANYOTHER_SYMBOL,Source.F_UNDEFINED))
          {
            //we do nothing because we have a interleaved comment
           }
        else if (acceptSymbol(Source.T_KEY_SYMBOL,
                        Source.C_DOLLAR_POINT,Source.F_UNDEFINED))
                 {
                   display("\nDist]<--\n");
                   //we add the local variables list to the list of lists
                   this.distincts.add(localDistinct);
                  this.changeDataNode(dataPreviousParent);
                  return;
                 }
            else 
                {
                  expectSymbol(Source.T_KEY_SYMBOL,
                          Source.C_DOLLAR_POINT,Source.F_UNDEFINED);
                  return;
                }
        } while (true);
    }
};

public void declareTypeOfVariable()
{  
    ParsingItem  dataPreviousParent=new ParsingItem();

     if (this.acceptSymbol(Source.T_KEY_SYMBOL,Source.C_F_SYMBOL,Source.F_UNDEFINED))
   {
         display(" -->[Func\n");
        //data
        dataPreviousParent=this.dataParentItem;
        this.addToDataNodeAndChangeNode();
        //END
         if (this.acceptSymbol(Source.T_COMMENT,
                                          Source.C_ANYOTHER_SYMBOL,Source.F_UNDEFINED))
          {
            //we do nothing because we have a interleaved comment
           }
        expectSymbol(Source.T_SYMBOL,Source.C_ANYOTHER_SYMBOL,Source.F_CONSTANT);
         display(" ["+previousTextSymbol+"] ");
         //we register the type of variables
         String variableType=previousTextSymbol;
         this.addToDataNode();
         if (this.acceptSymbol(Source.T_COMMENT,
                                          Source.C_ANYOTHER_SYMBOL,Source.F_UNDEFINED))
          {
            //we do nothing because we have a interleaved comment
           }
         expectSymbol(Source.T_SYMBOL,Source.C_ANYOTHER_SYMBOL,Source.F_UNDEFINED);
          display(" ["+previousTextSymbol+"] ");
          //we capture the found variable
         String foundVariable=previousTextSymbol;
          if (this.acceptSymbol(Source.T_COMMENT,
                                          Source.C_ANYOTHER_SYMBOL,Source.F_UNDEFINED))
          {
            //we do nothing because we have a interleaved comment
           }
          //we associate the found variable with type
          variableAndType.put(foundVariable, variableType);
          //we associate the label with number of order
          String label="";
          if (capturedItem!=null)
          {
              label=capturedItem.textSymbol;
              //we mark as f label
              capturedItem.functionSymbol=Source.F_LABEL_F;
          }
          if (this.setOfLabels.contains(label))
              
              errorNewLine("Label already exists!");
          else setOfLabels.add(label);
          
          this.variableAndLabel.put(foundVariable, label);
          this.labelAndVariable.put(label, foundVariable);
          this.labelAndOrder.put(label,String.valueOf(this.numberOfOrderOfLabel));
          this.numberOfOrderOfLabel++;//we increment the number of order
          //END association

          this.addToDataNode();

        
     if (acceptSymbol(Source.T_KEY_SYMBOL,Source.C_DOLLAR_POINT,Source.F_UNDEFINED))
     {
         display("\nFunc]<--\n");
         this.changeDataNode(dataPreviousParent);
         return;
     }
else {
      expectSymbol(Source.T_KEY_SYMBOL,Source.C_DOLLAR_POINT,
                                                    Source.F_UNDEFINED);
      return;
     }
  }
    
};

public void assumption()
{
    ParsingItem  dataPreviousParent=new ParsingItem();

     if (this.acceptSymbol(Source.T_KEY_SYMBOL,Source.C_E_SYMBOL,Source.F_UNDEFINED))
   {
         display(" -->[Assum\n");
          if (capturedItem!=null)
          {
              //we link label to the assumption
              capturedItem.functionSymbol=Source.F_LABEL_E;
          }
         //data
         dataPreviousParent=this.dataParentItem;
         this.addToDataNodeAndChangeNode();
         //END
         expectSymbol(Source.T_SYMBOL,Source.C_ANYOTHER_SYMBOL,Source.F_CONSTANT);
         display(" ["+previousTextSymbol+"] "); this.addToDataNode();
         expectSymbol(Source.T_SYMBOL,Source.C_ANYOTHER_SYMBOL,Source.F_UNDEFINED);
         display(" ["+previousTextSymbol+"] "); this.addToDataNode();

        do {
            lastLeaf=dataCurrentItem;
            if (acceptSymbol(Source.T_SYMBOL,Source.C_ANYOTHER_SYMBOL,Source.F_UNDEFINED))
                    {
                       display(" ["+previousTextSymbol+"] ");
                       this.addToDataNode();
                     }
   else if(acceptSymbol(Source.T_COMMENT, Source.C_ANYOTHER_SYMBOL, Source.F_UNDEFINED))
                    {
                        //we do nothing if we have comment here
                       display(" ("+previousTextSymbol+") ");
                    }
    else if (acceptSymbol(Source.T_KEY_SYMBOL,Source.C_DOLLAR_POINT,Source.F_UNDEFINED))
             {
                 display("\nAssum]<--\n") ;
                 //we make the generated assumption and we attach to the label e
                 this.assumptionItemGeneration(capturedItem);
                 this.changeDataNode(dataPreviousParent);
                 return;
             }
          
     else {
            expectSymbol(Source.T_KEY_SYMBOL,Source.C_DOLLAR_POINT,Source.F_UNDEFINED);
            return;
          }
        } while (true);
    }
};
public void axiom()
{
    ParsingItem  datePreviousParent=new ParsingItem();

    if (this.acceptSymbol(Source.T_KEY_SYMBOL,Source.C_A_SYMBOL,Source.F_UNDEFINED))
   {
        display(" -->[Ax\n");
         if (capturedItem!=null)
          {
              //we tie label a to the axiom
              capturedItem.functionSymbol=Source.F_LABEL_A;
          }
        //data
        datePreviousParent=this.dataParentItem;
        this.addToDataNodeAndChangeNode();
        //END
         expectSymbol(Source.T_SYMBOL,Source.C_ANYOTHER_SYMBOL,Source.F_CONSTANT);
         display(" ["+previousTextSymbol+"] "); this.addToDataNode();
         expectSymbol(Source.T_SYMBOL,Source.C_ANYOTHER_SYMBOL,Source.F_UNDEFINED);
         display(" ["+previousTextSymbol+"] "); this.addToDataNode();
         
        do {
            if (acceptSymbol(Source.T_SYMBOL,Source.C_ANYOTHER_SYMBOL,Source.F_UNDEFINED))
                     {
                       display(" ["+previousTextSymbol+"] ");
                       this.addToDataNode();
                     }
            else if(acceptSymbol(Source.T_COMMENT,
                    Source.C_ANYOTHER_SYMBOL, Source.F_UNDEFINED))
                    {
                        //we do nothing if we have a comment here
                       display(" ("+previousTextSymbol+") ");
                     }
       else if (acceptSymbol(Source.T_KEY_SYMBOL,Source.C_DOLLAR_POINT,Source.F_UNDEFINED))
             {
                 display("\nAx]<--\n");
                 this.axiomItemGeneration(capturedItem);
                 this.changeDataNode(datePreviousParent);
                 return;
             }
            else 
            {
              expectSymbol(Source.T_KEY_SYMBOL,
                      Source.C_DOLLAR_POINT,Source.F_UNDEFINED);
              return;
            }
        } while (1==1);
    }

};

public void theoremAndDemonstration()
{
   ParsingItem  dataPreviousParent=new ParsingItem();
   

    if (this.acceptSymbol(Source.T_KEY_SYMBOL,Source.C_P_SYMBOL,Source.F_UNDEFINED))
   {
         display(" -->[Theo\n");
          if (capturedItem!=null)
          {
              //we tie label p to the theorem
              capturedItem.functionSymbol=Source.F_LABEL_P;
          }
         //data
        dataPreviousParent=this.dataParentItem;
        this.addToDataNodeAndChangeNode();
        //END
         expectSymbol(Source.T_SYMBOL,Source.C_ANYOTHER_SYMBOL,Source.F_CONSTANT);
         display(" ["+previousTextSymbol+"] "); this.addToDataNode();
         expectSymbol(Source.T_KEY_SYMBOL,Source.C_ANYOTHER_SYMBOL,Source.F_UNDEFINED);
         display(" ["+previousTextSymbol+"] "); this.addToDataNode();

        do {
            if (acceptSymbol(Source.T_SYMBOL,Source.C_ANYOTHER_SYMBOL,Source.F_UNDEFINED))
                    {
                         display(" ["+previousTextSymbol+"] ");
                         this.addToDataNode();
                    }
  else if(acceptSymbol(Source.T_COMMENT,
                    Source.C_ANYOTHER_SYMBOL, Source.F_UNDEFINED))
                    {
                        //we do nothing if we have a comment here
                       display(" ("+previousTextSymbol+") ");

                     }

    else if (acceptSymbol(Source.T_KEY_SYMBOL,Source.C_EQUAL_SYMBOL,Source.F_UNDEFINED))
             {
                 display("\n>==>==>=>\n");
                 //we generate theorem based on captured item
                 this.theoremItemGeneration(capturedItem);
                 //
                 

                 this.changeDataNode(dataPreviousParent);
                 this.addToDataNodeAndChangeNode();
                 /*exit from the loop*/ break;
              }
            else 
            {
               expectSymbol(Source.T_KEY_SYMBOL,Source.C_EQUAL_SYMBOL,Source.F_UNDEFINED);
                /*exit from the loop*/ break;
            }
        } while (true);
                           
         do
          {
           if (acceptSymbol(Source.T_SYMBOL,Source.C_ANYOTHER_SYMBOL,Source.F_UNDEFINED))
           {
               display(" ["+previousTextSymbol+"] ");
               this.addToDataNode();

              
           }
    else if (acceptSymbol(Source.T_KEY_SYMBOL,Source.C_DOLLAR_POINT,Source.F_UNDEFINED))
             {
                 display("\nTheo]<--\n");
                 //here will generate the demonstration
                 this.generateDemonstration(capturedItem);
                 this.changeDataNode(dataPreviousParent);
                 
                /*exit from the loop*/ break;
             }
    else {
           expectSymbol(Source.T_KEY_SYMBOL,Source.C_DOLLAR_POINT,Source.F_UNDEFINED);
           return;
         }
        } while (true);
       
   }
};
public void block()
{
ParsingItem  blockDataPreviousParent=new ParsingItem();
ParsingItem  dataPreviousParent=new ParsingItem();
//saved variables before entering in a block
  Context context0=null;
 //nothing for now
 if (this.acceptSymbol(Source.T_KEY_SYMBOL,Source.C_OPEN_BRACE,Source.F_UNDEFINED))
{
display("\n-->[Blo\n");
//we save c,v,f,d before entering in a block(this must be a hard copy)
 context0=this.contextSalvage();
 //END saving c,v,f,d before entering in a block

 //data
blockDataPreviousParent=this.dataParentItem;
this.addToDataNodeAndChangeNode();
//we verify if the parent is also a block

if (blockDataPreviousParent!=null)
 {
    if (blockDataPreviousParent.codeSymbol==Source.C_OPEN_BRACE)
    {
        this.display("  ![{in{]   ");
    }
 }
//END
do
{
//we try to see if it is a scope block
this.comment();
this.block();
//if not we verify one at a time
this.declareConstants();
this.declareVariables();
this.declareDistinctVariables();

if (this.acceptSymbol(Source.T_LABEL,Source.C_ANYOTHER_SYMBOL,Source.F_UNDEFINED))
{
display("\n <"+previousTextSymbol+">: ");
//
dataPreviousParent=this.dataParentItem;
//we capture the label
capturedItem=dataCurrentItem;
this.addToDataNodeAndChangeNode();
//
this.axiom();
this.assumption();
this.theoremAndDemonstration();
this.declareTypeOfVariable();
//
this.changeDataNode(dataPreviousParent);
//
}
if (acceptSymbol(Source.T_KEY_SYMBOL,Source.C_CLOSED_BRACE,Source.F_UNDEFINED))
{
display("\nBlo]<--\n");
//we restore c,v,f,d after exit from the block(must be a hard copy)
if (context0!=null)
{
   this.contextRestoration(context0.savedConstants,
           context0.savedVariables, context0.variableAndTypeSaved,
           context0.distinctSaved,context0.blockHypothesesSaved);
}
//END restorre c,v,f,d after exit from the block
this.changeDataNode(blockDataPreviousParent);
break;
}
 else {
         if (typeSymbol==T_END) {errorNewLine("Expect: }");break;}
       }
} while (true);
}
};

public void comment()
{
    //comment is treated at lower level
    if (this.acceptSymbol(Source.T_COMMENT,Source.C_ANYOTHER_SYMBOL,Source.F_UNDEFINED))
   {
        commentsNumber++; if (commentsNumber>5) this.graveApostropheActivated=true;
        display("\n-->[Comm\n");
        ParsingItem el1=this.dataCurrentItem;
         //we process the comment
         SourceComment s=new SourceComment(this);
         s.parse(el1);
         SourceComment.setCommentCategory(el1);
         this.addToDataNode();
         
         {display(" ["+previousTextSymbol+"] ");}
        display("\nComm]<--\n");
   }
};

public void inclusion()
{
ParsingItem  dataPreviousParent=new ParsingItem();

if (this.acceptSymbol(Source.T_KEY_SYMBOL,Source.C_OPEN_SQUARE_BRACKET,Source.F_UNDEFINED))
{
display("\n-->[Incl\n");
//data
dataPreviousParent=this.dataParentItem;
this.addToDataNodeAndChangeNode();
//END
   if (expectSymbol(Source.T_LABEL,Source.C_ANYOTHER_SYMBOL,Source.F_UNDEFINED))
    {
        display(" ["+previousTextSymbol+"] ");
         this.addToDataNode();

         Application0 a= (Application0) Application.getInstance();
         if(a!=null)
           {
         if(a.frame0!=null)
           {
             a.frame0.haveInclusion=true;//we mark that we have included a file
           }
           }
         
            //replacement
            String path=this.folderPath+"\\"+previousTextSymbol;
            Source s2=new Source(path);//the file where the theory is
            s2.folderPath=this.folderPath;
            s2.numberOfOrderOfLabel=this.numberOfOrderOfLabel;
            JFrame baseFrame = Application0.getApplication().getMainFrame();
            ReadingSourceDialog dialog2 =
                              new ReadingSourceDialog(baseFrame,false,s2);
            dialog2.setLocationRelativeTo(baseFrame);



             ReadingInclusionThread thread=new ReadingInclusionThread(this,s2);
             UpdatePercentReadingThread thread2=
                          new UpdatePercentReadingThread(s2,dialog2);
             thread.start();
             thread2.start();
             Application0.getApplication().show(dialog2);
            //END replacement

    }
   if (acceptSymbol(Source.T_KEY_SYMBOL,Source.C_CLOSED_SQUARE_BRACKET,Source.F_UNDEFINED))
      {
          display("\nIncl]<--\n");
          this.changeDataNode(dataPreviousParent);
          return;
      }
    else
    {
        expectSymbol(Source.T_KEY_SYMBOL,Source.C_CLOSED_SQUARE_BRACKET,Source.F_UNDEFINED);
        return;
    }
}
    
};
public void includeTheSource(Source s2)
{
 //we copy the content of s2 in this source
 //constants
 Iterator<String> iteratorConstants=  s2.constants.iterator();
 while(iteratorConstants.hasNext())
 {String name0=iteratorConstants.next();
  this.constants.add(name0);
 }
 //variables
 Iterator<String> iteratorVariables=  s2.variables.iterator();
 while(iteratorVariables.hasNext())
 {String name0=iteratorVariables.next();
  this.variables.add(name0);
 }
 //variable and type
 Iterator<String> iteratorVariableAndType=  s2.variableAndType.keySet().iterator();
 while(iteratorVariableAndType.hasNext())
 {String name0=iteratorVariableAndType.next();
  String xType=(String)s2.variableAndType.get(name0);
  this.variableAndType.put(name0, xType);
 }
  //variable and label
 Iterator<String> iteratorVariableAndLabel=  s2.variableAndLabel.keySet().iterator();
 while(iteratorVariableAndLabel.hasNext())
 {String name0=iteratorVariableAndLabel.next();
  String xType=(String)s2.variableAndLabel.get(name0);
  this.variableAndLabel.put(name0, xType);
 }
  //label and variable
 Iterator<String> iteratorLabelAndVariable=  s2.labelAndVariable.keySet().iterator();
 while(iteratorLabelAndVariable.hasNext())
 {String name0=iteratorLabelAndVariable.next();
  String xType=(String)s2.labelAndVariable.get(name0);
  this.labelAndVariable.put(name0, xType);
 }
 //hypotheses
 Iterator<String> iteratorHypotheses=  s2.hypotheses.keySet().iterator();
 while(iteratorHypotheses.hasNext())
 {String name0=iteratorHypotheses.next();
  Hypothesis xAssumption=(Hypothesis)s2.hypotheses.get(name0);
  this.hypotheses.put(name0, xAssumption);
 }
//axioms
 Iterator<String> iteratorAxioms=  s2.axioms.keySet().iterator();
 while(iteratorAxioms.hasNext())
 {String name0=iteratorAxioms.next();
  Axiom xAxiom=(Axiom)s2.axioms.get(name0);
  this.axioms.put(name0, xAxiom);
 }
 //available axioms
 Iterator<String> iteratorAvailableAxioms=  s2.availableAxioms.iterator();
 while(iteratorAvailableAxioms.hasNext())
 {String name0=iteratorAvailableAxioms.next();
  this.availableAxioms.add(name0);
 }
  //theorems
 Iterator<String> iteratorTheorems=  s2.theorems.keySet().iterator();
 while(iteratorTheorems.hasNext())
 {String name0=iteratorTheorems.next();
  Theorem xTheorem=(Theorem)s2.theorems.get(name0);
  this.theorems.put(name0, xTheorem);
 }
 //available theorems
 Iterator<String> iteratorAvailableTheorems=  s2.availableTheorems.iterator();
 while(iteratorAvailableTheorems.hasNext())
 {String name0=iteratorAvailableTheorems.next();
  this.availableTheorems.add(name0);
 }

 //htlmldef string1 as string2
 Iterator<String> iteratorHtmldefString1AsString2=
                                   s2.htlmldefString1AsString2.keySet().iterator();
 while(iteratorHtmldefString1AsString2.hasNext())
 {String string1=iteratorHtmldefString1AsString2.next();
  String string2=(String)s2.htlmldefString1AsString2.get(string1);
  this.htlmldefString1AsString2.put(string1, string2);
 }

 //class list of definitions
   Iterator<String> iteratorClassListOfDefinitions=
                                   s2.classListOfDefinitions.keySet().iterator();
 while(iteratorClassListOfDefinitions.hasNext())
 {String type=iteratorClassListOfDefinitions.next();
  List<Object> list2=s2.classListOfDefinitions.get(type);
  List<Object> list=this.classListOfDefinitions.get(type);
  if(list2!=null)
  {
  int max=list2.size();
  if(list==null)
  {
   list=new ArrayList<Object>();
  }
  //we insert the lists
  for(int i=0;i<max;i++)
  {
   list.add(list2.get(i));
  }
  this.classListOfDefinitions.remove(type);
  this.classListOfDefinitions.put(type,list);
  }

 }
 
 //label and order
 Iterator<String> iteratorLabelAndOrder=  s2.labelAndOrder.keySet().iterator();
 while(iteratorLabelAndOrder.hasNext())
 {String name0=iteratorLabelAndOrder.next();
  String xNumber=(String)s2.labelAndOrder.get(name0);
  this.labelAndOrder.put(name0, xNumber);
 }
  //set of labels
 Iterator<String> iteratorSetOfLabels=  s2.setOfLabels.iterator();
 while(iteratorSetOfLabels.hasNext())
 {String name0=iteratorSetOfLabels.next();
  this.setOfLabels.add(name0);
 }
 
}

public void assumptionItemGeneration(ParsingItem assumptionLabelItem)
{
Hypothesis assumption=new Hypothesis();
String s1;
ParsingItem item1;
//we attach the generated item and we copy his name
assumptionLabelItem.generatedItem=assumption;
assumption.name=assumptionLabelItem.textSymbol;

if (assumptionLabelItem.containedItems!=null)
{
item1=assumptionLabelItem.containedItems.get(0);
if (item1.containedItems!=null)
{
//we add the ConstantAndVariable item to the generated assumption (e)
assumption.items= new java.util.ArrayList<ConstantAndVariable>();
for(int cursor=0;cursor<item1.containedItems.size();cursor++)
{
s1 =item1.containedItems.get(cursor).textSymbol;
if (variables.contains(s1))
{
ConstantAndVariable cv=new ConstantAndVariable();
cv.constantOrVariable=2;
cv.constantOrVariableText=s1;
if (variableAndType.containsKey(s1))
{cv.variableClass=variableAndType.get(s1);}
else
{
    
    errorNewLine("Variable has no class:"+s1);
}
assumption.items.add(cv);
}
else if (constants.contains(s1))
      {
        ConstantAndVariable cv=new ConstantAndVariable();
        cv.constantOrVariable=1;
        cv.constantOrVariableText=s1;
        cv.variableClass="const";
        assumption.items.add(cv);
      }
else {

ConstantAndVariable cv=new ConstantAndVariable();
cv.constantOrVariable=3;
cv.constantOrVariableText=s1;
cv.variableClass="undefined";
assumption.items.add(cv);

errorNewLine("Unknown symbol: "+s1);
}
}
//we visualize the found ConstantAndVariable items
displayNewLine("");
displayNewLine("GEN ITEM e: { "+assumption.name);
for(int cursor=0;cursor<assumption.items.size();cursor++)
{
display(assumption.items.get(cursor).toString());
}
//we go through the found items of ConstantAndVariable and we extract variables
assumption.variables= new java.util.ArrayList<Variable>();
displayNewLine("");
displayNewLine("var ");
for(int cursor2=0;cursor2<assumption.items.size();cursor2++)
{
if (assumption.items.get(cursor2).constantOrVariable==2)
{
Variable v=new Variable(),v2=null;
v.variableText=assumption.items.get(cursor2).constantOrVariableText;
v.variableClass=assumption.items.get(cursor2).variableClass;
boolean found=false;
int p=0;
if(!assumption.variables.isEmpty())
{
do {
v2=assumption.variables.get(p);
if (v2!=null)
{
    if (v.variableText.equals(v2.variableText))
    {
         found = true;
    }
}
p++;

}while (p<assumption.variables.size());
}
if (!found) {assumption.variables.add(v); display(v.toString());}

}
}

displayNewLine("}  ");
}
//we add the assumption to a the block list of which it is part (or even the file)
blockHypotheses.add(assumption);
//we add the assumption to the big map
hypotheses.put(assumption.name, assumption);
}


}

public void axiomItemGeneration(ParsingItem axiomLabelItem)
{
Axiom axiom=new Axiom();
String s1;
ParsingItem item1;
//we attach the generated item and we copy his name
axiomLabelItem.generatedItem=axiom;
axiom.name=axiomLabelItem.textSymbol;
axiom.sourceLabel=axiomLabelItem;
axiom.type=1;// simple
if (axiomLabelItem.containedItems!=null)
{
item1=axiomLabelItem.containedItems.get(0);
if (item1.containedItems!=null)
{
//we add the ConstantAndVariable to the generated axiom (a)
axiom.items= new java.util.ArrayList<ConstantAndVariable>();
for(int cursor=0;cursor<item1.containedItems.size();cursor++)
{
s1 =item1.containedItems.get(cursor).textSymbol;
if (variables.contains(s1))
   {
    ConstantAndVariable cv=new ConstantAndVariable();
    cv.constantOrVariable=2;
    cv.constantOrVariableText=s1;
    if (variableAndType.containsKey(s1))
    {cv.variableClass=variableAndType.get(s1);}
    else
{
  
   errorNewLine("Variable has no class: "+s1);
}
    axiom.items.add(cv);
   }
else if (constants.contains(s1))
              {ConstantAndVariable cv=new ConstantAndVariable();
                cv.constantOrVariable=1;
                cv.constantOrVariableText=s1;
                cv.variableClass="const";

                axiom.items.add(cv);

              }
else {
    ConstantAndVariable cv=new ConstantAndVariable();
    cv.constantOrVariable=3;
    cv.constantOrVariableText=s1;
    cv.variableClass="undefined";
    axiom.items.add(cv);
    
    errorNewLine("Unknown symbol: "+s1);
}
}
//we visualize the found ConstantAndVariable items
displayNewLine("");
displayNewLine("AXIO ITEM a: { "+axiom.name);
for(int cursor=0;cursor<axiom.items.size();cursor++)
{
 display(axiom.items.get(cursor).toString());
}
//we go through the found ConstantAndVariable items and we extract variables
axiom.variables= new java.util.ArrayList<Variable>();
displayNewLine("");
displayNewLine("var ");
for(int cursor2=0;cursor2<axiom.items.size();cursor2++)
{
if (axiom.items.get(cursor2).constantOrVariable==2)
{
Variable v=new Variable(),v2=null;
v.variableText=axiom.items.get(cursor2).constantOrVariableText;
v.variableClass=axiom.items.get(cursor2).variableClass;
    boolean found=false;
    int p=0;

    if(!axiom.variables.isEmpty())
        {
        do {
             v2=axiom.variables.get(p);
             if (v2!=null)
                {
                    if (v.variableText.equals(v2.variableText))
                    {
                         found = true;
                    }
                }
              p++;

            }while (p<axiom.variables.size());
}
if (!found) {axiom.variables.add(v); display(v.toString());}
}
}
}
//total variables
List<Variable> listOfVariablesOfAssumption=null;
axiom.totalVariables=new ArrayList<Variable>();
//we attach to the axiom the list of found hypotheses(the copy of their list)
axiom.hypotheses=this.copyListOfHypotheses(blockHypotheses);
if (axiom.hypotheses!=null)
{
if (!axiom.hypotheses.isEmpty())
{
axiom.type=2;// composed
displayNewLine("  ");
display("--hypotheses--: ");
for(int cursor4=0;cursor4<axiom.hypotheses.size();cursor4++)
{
display(axiom.hypotheses.get(cursor4).name+"  ");
//total variables
listOfVariablesOfAssumption=null;
listOfVariablesOfAssumption=axiom.hypotheses.get(cursor4).variables;
if (axiom.totalVariables.isEmpty())
{
if (listOfVariablesOfAssumption!=null)
      {
if (!listOfVariablesOfAssumption.isEmpty())
{
for (int i=0;i<listOfVariablesOfAssumption.size();i++)
{
Variable assumptionVariable=new Variable();
assumptionVariable.variableText=listOfVariablesOfAssumption.get(i).variableText;
assumptionVariable.variableClass=listOfVariablesOfAssumption.get(i).variableClass;
axiom.totalVariables.add(assumptionVariable);

}
}
      }
}
else if(axiom.totalVariables.size()>=1)
{
if (listOfVariablesOfAssumption!=null)
{
if (!listOfVariablesOfAssumption.isEmpty())
{
for (int i=0;i<listOfVariablesOfAssumption.size();i++)
{
String nameOfVariableOfAssumption=listOfVariablesOfAssumption.get(i).variableText,nameOfVariableOfTheorem="";
Variable variableOfAssumption=null;
variableOfAssumption=new Variable();
variableOfAssumption.variableClass=listOfVariablesOfAssumption.get(i).variableClass;
variableOfAssumption.variableText=listOfVariablesOfAssumption.get(i).variableText;
boolean found=false;
for(int j=0;j<axiom.totalVariables.size();j++)
{
nameOfVariableOfTheorem=axiom.totalVariables.get(j).variableText;
if (nameOfVariableOfAssumption.equals(nameOfVariableOfTheorem)) {found=true;}
}
if (!found) {axiom.totalVariables.add(variableOfAssumption);}

}
}
}
}
//END total variables

}
displayNewLine("  ");
}
}
//copy variables->total variables
if (axiom.variables!=null)
{
if (!axiom.variables.isEmpty())
{
for (int i=0;i<axiom.variables.size();i++)
{
String nameVariableAssumption=axiom.variables.get(i).variableText,nameVariableTheorem="";
 Variable variableAssumption=null;
 variableAssumption=new Variable();
 variableAssumption.variableClass=axiom.variables.get(i).variableClass;
 variableAssumption.variableText=axiom.variables.get(i).variableText;
boolean found=false;
for(int j=0;j<axiom.totalVariables.size();j++)
{
    nameVariableTheorem=axiom.totalVariables.get(j).variableText;
    if (nameVariableAssumption.equals(nameVariableTheorem)) {found=true;}
}
if (!found) {axiom.totalVariables.add(variableAssumption);}

}
}
}
//END copy
//we add the labels of variables and order them according to declaration order
if (axiom!=null)
{
if (axiom.totalVariables!=null)
{
 //we add the labels and number of order of variables
for (int i=0;i<axiom.totalVariables.size();i++)
{
    Variable v=null;
    String label="",numberOfOrder="";
    v=axiom.totalVariables.get(i);
    if(this.variableAndLabel.get(v.variableText)!=null)
    {
        label=this.variableAndLabel.get(v.variableText);
        if (this.labelAndOrder.get(label)!=null)
        {
            numberOfOrder=this.labelAndOrder.get(label);
        }
            else
            {
            
            errorNewLine("Not found number of order for label:"+label);

            }
    }
    else errorNewLine("Not found label for the variable: "+v.variableText);

  v.label=label;
  v.numberOfLabelOrder=numberOfOrder;
}
//we order ascending according cu number of order of label
   Collections.sort(axiom.totalVariables,new spv.gen.CompareVariablesOrder());
}
}
///END adding labels and ordering

//print total variables
if (axiom.totalVariables!=null)
{
if (!axiom.totalVariables.isEmpty())
{

    displayNewLine("total variables :  ");
    for (int i=0;i<axiom.totalVariables.size();i++)
    {
        display(" "+axiom.totalVariables.get(i).toString()+" ");
    }
}
}
//END print

//we add distinct variables
axiom.distinct=this.copyDistinct(distincts);
List<String> lista=null;
if (axiom.distinct!=null)
 {
 if (!axiom.distinct.isEmpty())
   {
  displayNewLine("");
  displayNewLine("  <D>:  ");
             for (int i=0;i<distincts.size();i++)
             {
                lista=axiom.distinct.get(i);
                display("["+lista.toString()+"],");
             }
    displayNewLine(" ");
    }
    }
displayNewLine("}  ");
}

//we add the axiom at the total table of axioms
this.axioms.put( axiom.name, axiom);
//we verify if it is a sintactic axiom and we actualize the list of definitions
this.generateListOfSyntacticDefinitionsFromA(axiom);
this.availableAxioms.add(axiom.name);//list of available axioms...
}

public void theoremItemGeneration(ParsingItem theoremLabelItem)
{
Theorem theorem=new Theorem();
String s1;
ParsingItem item1;
//we attach the generated item and we copy his name
theoremLabelItem.generatedItem=theorem;
theorem.name=theoremLabelItem.textSymbol;
theorem.sourceLabel=theoremLabelItem;
theorem.type=1;// simple
if (theoremLabelItem.containedItems!=null)
{
item1=theoremLabelItem.containedItems.get(0);
if (item1.containedItems!=null)
{
//we add the ConstantAndVariable item at (p) generated theorem
theorem.items= new java.util.ArrayList<ConstantAndVariable>();
for(int cursor=0;cursor<item1.containedItems.size();cursor++)
{
s1 =item1.containedItems.get(cursor).textSymbol;
if (variables.contains(s1))
   {
    ConstantAndVariable cv=new ConstantAndVariable();
    cv.constantOrVariable=2;
    cv.constantOrVariableText=s1;
    if (variableAndType.containsKey(s1)) {cv.variableClass=variableAndType.get(s1);}
               else {errorNewLine("Variable has no class:"+s1);}
    theorem.items.add(cv);
   }
else if (constants.contains(s1))
              {ConstantAndVariable cv=new ConstantAndVariable();
                cv.constantOrVariable=1;
                cv.constantOrVariableText=s1;
                cv.variableClass="const";
                theorem.items.add(cv);
              }
else {
    ConstantAndVariable cv=new ConstantAndVariable();
    cv.constantOrVariable=3;
    cv.constantOrVariableText=s1;
    cv.variableClass="undefined";
    theorem.items.add(cv);
    errorNewLine("Unknown symbol:"+s1);
}
}
//we visualize the found ConstantAndVariable items
displayNewLine("");
displayNewLine("EL TEO p: { "+theorem.name);
for(int cursor=0;cursor<theorem.items.size();cursor++)
{
 display(theorem.items.get(cursor).toString());
}
//we go through the ConstantAndVariable found items and we extract variables
theorem.variables= new java.util.ArrayList<Variable>();
displayNewLine("");
displayNewLine("var ");
for(int cursor2=0;cursor2<theorem.items.size();cursor2++)
{
if (theorem.items.get(cursor2).constantOrVariable==2)
{
    Variable v=new Variable(),v2=null;
    v.variableText=theorem.items.get(cursor2).constantOrVariableText;
    v.variableClass=theorem.items.get(cursor2).variableClass;
    boolean found=false;
    int p=0;
    if(!theorem.variables.isEmpty())
        {
        do {
             v2=theorem.variables.get(p);
             if (v2!=null)
                {
                    if (v.variableText.equals(v2.variableText))
                    {
                         found = true;
                    }
                }
              p++;

            }while (p<theorem.variables.size());
}


if (!found) {theorem.variables.add(v); display(v.toString());}
}
}
}

//total variables
theorem.totalVariables=new ArrayList<Variable>();
//we attach at the theorem the list of found hypotheses(the copy of their list)
theorem.hypotheses=this.copyListOfHypotheses(blockHypotheses);
List<Variable> listofVariableOfAssumption=null;
if (theorem.hypotheses!=null)
{

if (!theorem.hypotheses.isEmpty())
{
theorem.type=2;//composed

displayNewLine("  ");
display("--hypotheses--: ");
for(int cursor4=0;cursor4<theorem.hypotheses.size();cursor4++)
{
display(theorem.hypotheses.get(cursor4).name+"  ");
listofVariableOfAssumption=null;
listofVariableOfAssumption=theorem.hypotheses.get(cursor4).variables;
//total variables
if (theorem.totalVariables.isEmpty())
{
if (listofVariableOfAssumption!=null)
{
if (!listofVariableOfAssumption.isEmpty())
  {
    for (int i=0;i<listofVariableOfAssumption.size();i++)
    {
         Variable variableOfAssumption=new Variable();
         variableOfAssumption.variableText=listofVariableOfAssumption.get(i).variableText;
         variableOfAssumption.variableClass=listofVariableOfAssumption.get(i).variableClass;
        theorem.totalVariables.add(variableOfAssumption);
    }
  }
}
}

else if(theorem.totalVariables.size()>=1)
{
if (listofVariableOfAssumption!=null)
{
if (!listofVariableOfAssumption.isEmpty())
  {
    for (int i=0;i<listofVariableOfAssumption.size();i++)
    {
        String nameOfVariablesOfAssumption=listofVariableOfAssumption.get(i).variableText,nameOfVariableOfTheorem="";
         Variable variableOfAssumption=null;
         variableOfAssumption=new Variable();
         variableOfAssumption.variableClass=listofVariableOfAssumption.get(i).variableClass;
         variableOfAssumption.variableText=listofVariableOfAssumption.get(i).variableText;

        boolean found=false;
        for(int j=0;j<theorem.totalVariables.size();j++)
        {
            nameOfVariableOfTheorem=theorem.totalVariables.get(j).variableText;
            if (nameOfVariablesOfAssumption.equals(nameOfVariableOfTheorem)) {found=true;}
        }
        if (!found) {theorem.totalVariables.add(variableOfAssumption);}
    }
  }
}
}
//END total variables
}
displayNewLine("  ");
}
}
//copy of variables->total variables
if (theorem.variables!=null)
{
if (!theorem.variables.isEmpty())
{
for (int i=0;i<theorem.variables.size();i++)
{
    String nameOfVariableOfAssumption=theorem.variables.get(i).variableText,nameOfVariableOfTheorem="";
     Variable variableOfAssumption=null;
     variableOfAssumption=new Variable();
     variableOfAssumption.variableClass=theorem.variables.get(i).variableClass;
     variableOfAssumption.variableText=theorem.variables.get(i).variableText;

    boolean found=false;

    for(int j=0;j<theorem.totalVariables.size();j++)

    {
        nameOfVariableOfTheorem=theorem.totalVariables.get(j).variableText;
        if (nameOfVariableOfAssumption.equals(nameOfVariableOfTheorem)) {found=true;}
    }
    if (!found) {theorem.totalVariables.add(variableOfAssumption);}
}
}
}
//END copy
//we add the labels of variables and order them according to the order of declarations
if (theorem!=null)
{
if (theorem.totalVariables!=null)
{
  //we add labels and number of order of variables
for (int i=0;i<theorem.totalVariables.size();i++)
{
    Variable v=null;
    String label="",numberOfOrder="";
    v=theorem.totalVariables.get(i);
    if(this.variableAndLabel.get(v.variableText)!=null)
    {
        label=this.variableAndLabel.get(v.variableText);
        if (this.labelAndOrder.get(label)!=null)
        {
            numberOfOrder=this.labelAndOrder.get(label);
        }
            else
            {
            errorNewLine("Not found order number for the label:"+label);
            }
    }
    else errorNewLine("Not found label for the variable:"+v.variableText);

  v.label=label;
  v.numberOfLabelOrder=numberOfOrder;
}
//we order ascending according to the number of order of the label
   Collections.sort(theorem.totalVariables,new spv.gen.CompareVariablesOrder());
}
}
//END adding labels and ordering

//print total variables
if (theorem.totalVariables!=null)
{
if (!theorem.totalVariables.isEmpty())
{
    displayNewLine(" total var:  ");
    for (int i=0;i<theorem.totalVariables.size();i++)
    {
        display(" "+theorem.totalVariables.get(i).toString()+" ");
    }
}
}

//add distinct variables
theorem.distinct=this.copyDistinct(distincts);
List<String> lista=null;
if (theorem.distinct!=null)
 {
 if (!theorem.distinct.isEmpty())
   {
  displayNewLine("");
  displayNewLine("  <D>:  ");
             for (int i=0;i<distincts.size();i++)
             {
                lista=theorem.distinct.get(i);
                display("["+lista.toString()+"],");
             }
    displayNewLine(" ");
    }
    }
displayNewLine("}    ");
}
//adding the theorem at the total table of theorems
this.theorems.put(theorem.name, theorem);
//we verify if it is a sintactic theorem and actualize the list of definitions
this.generateListOfSyntacticDefinitionsFromP(theorem);
//available theorems at a moment...
this.availableTheorems.add(theorem.name);
}

public int numberFromDigits(byte digits[],int numberOfDigits)
{
int j=0;
int number=0,power5=1;

if ( numberOfDigits>=1 )
{
j=numberOfDigits-1;
 do
    {
    number=number+digits[j]*power5;
    j--;power5=power5*5;
    }while (j>=0);
number=number*20;
}

return number;
};

public static  List<ConstantAndVariable> variablesContentInsertion(List<ConstantAndVariable>
        CVList,Map<String,List<ConstantAndVariable>>   variableContent)
{
List<ConstantAndVariable> result=new java.util.ArrayList<ConstantAndVariable>();
int i=0,j=0,max1=0,max2=0;
ConstantAndVariable cv1=null,cv2=null;
if (CVList!=null)
{
max1=CVList.size();
if (max1!=0) {
do
{
if (CVList.get(i).constantOrVariable==1) //if it is constant
{
cv1=new ConstantAndVariable();
cv1.constantOrVariable=CVList.get(i).constantOrVariable;
cv1.constantOrVariableText=CVList.get(i).constantOrVariableText;
cv1.variableClass=CVList.get(i).variableClass;
result.add(cv1);i++; //we add at the result and increment the position in CVList
}
else if (CVList.get(i).constantOrVariable==2) //if it is variable
{
List<ConstantAndVariable> variableContentItems=new java.util.ArrayList<ConstantAndVariable>();
String nameOfVariable=CVList.get(i).constantOrVariableText;
j=0;max2=0;
if (variableContent.get(nameOfVariable)!=null)
{
variableContentItems=variableContent.get(nameOfVariable);

max2=variableContentItems.size();
if (max2!=0)
{
do
{
cv2=new ConstantAndVariable();
cv2.constantOrVariable=variableContentItems.get(j).constantOrVariable;
cv2.constantOrVariableText=variableContentItems.get(j).constantOrVariableText;
cv2.variableClass=variableContentItems.get(j).variableClass;
result.add(cv2);
j++;//we increment the cursor from the variable
}while (j<max2);
}
i++;//we increment position in the CVList
}else
{
//deleteding
cv1=new ConstantAndVariable();
cv1.constantOrVariable=CVList.get(i).constantOrVariable;
cv1.constantOrVariableText=CVList.get(i).constantOrVariableText;
cv1.variableClass=CVList.get(i).variableClass;
result.add(cv1);i++;//we add to the result and increment the position in CVList
//END deleting
Source.errorNewLine("Variable without match:"+cv1.constantOrVariableText);
}
}
}while(i<max1);
}
}
return result;
}

/*we generate a new string even if not all of the variables have a content*/
public static  List<ConstantAndVariable> noCommentVariablesContentInsertion(List<ConstantAndVariable>
        CVList,Map<String,List<ConstantAndVariable>>   variableContent)
{
List<ConstantAndVariable> result=new java.util.ArrayList<ConstantAndVariable>();
int i=0,j=0,max1=0,max2=0;
ConstantAndVariable cv1=null,cv2=null;
if (CVList!=null)
{
max1=CVList.size();
if (max1!=0) {
do
{
if (CVList.get(i).constantOrVariable==1) //if it is constant
{
cv1=new ConstantAndVariable();
cv1.constantOrVariable=CVList.get(i).constantOrVariable;
cv1.constantOrVariableText=CVList.get(i).constantOrVariableText;
cv1.variableClass=CVList.get(i).variableClass;
result.add(cv1);i++; //add to the result and increment the position in the CVList
}
else if (CVList.get(i).constantOrVariable==2) //if it is variable
{
List<ConstantAndVariable> contentVariableItems=new java.util.ArrayList<ConstantAndVariable>();
String variableName=CVList.get(i).constantOrVariableText;
j=0;max2=0;
if (variableContent.get(variableName)!=null)
{
contentVariableItems=variableContent.get(variableName);

max2=contentVariableItems.size();
if (max2!=0)
{
do
{
cv2=new ConstantAndVariable();
cv2.constantOrVariable=contentVariableItems.get(j).constantOrVariable;
cv2.constantOrVariableText=contentVariableItems.get(j).constantOrVariableText;
cv2.variableClass=contentVariableItems.get(j).variableClass;
result.add(cv2);
j++;//increment the cursor within the variable
}while (j<max2);
}
i++;//we increment the position in CVList
}else
{

cv1=new ConstantAndVariable();
cv1.constantOrVariable=CVList.get(i).constantOrVariable;
cv1.constantOrVariableText=CVList.get(i).constantOrVariableText;
cv1.variableClass=CVList.get(i).variableClass;
result.add(cv1);i++;//add to the result and increment the position in CVList

}
}
}while(i<max1);
}
}
return result;
}
//resolve the variable content system
public static  Map<String,List<ConstantAndVariable>>
        resolveVariableContent
        ( 
          Map<String,List<ConstantAndVariable>>   variableContent,
          DemonstrationEditor editor
        )
{
Map<String,List<ConstantAndVariable>> result=
                       new java.util.HashMap<String,java.util.List<ConstantAndVariable>>();

Map<String,List<ConstantAndVariable>> intermediateResult1=
                       new java.util.HashMap<String,java.util.List<ConstantAndVariable>>();

Map<String,String> variable1AndVariable2=
                       new java.util.HashMap<String,String>();
//the list with variables that have content
//and must be updated in the new statements
 List<String> variablesToUpdate=new ArrayList<String>();
if (variableContent!=null)
{
Iterator<String> iteratorVariable=variableContent.keySet().iterator();
if (iteratorVariable.hasNext())
{
do
{
 String  variable=iteratorVariable.next();
 List<ConstantAndVariable> content=variableContent.get(variable),previousContent=content;
if (content!=null)
{
    do
    {
        previousContent=content;
        content=Source.noCommentVariablesContentInsertion
                (previousContent, variableContent);
        
    } while(!Source.isEqualXWithY(content, previousContent));

    //we update the map of variables and content
    intermediateResult1.put(variable, content);
}

    
} while(iteratorVariable.hasNext());
}

}



if (intermediateResult1!=null)
{
  List<String> newVariablesList=new ArrayList<String>();
Iterator<String> variableIterator=intermediateResult1.keySet().iterator();
if (variableIterator.hasNext())
{
do
{
 String  variable=variableIterator.next();
 List<ConstantAndVariable> content=intermediateResult1.get(variable),previousContent=content;
if (content!=null)
{
   
        previousContent=content;
        content=Source.copyTheListOfConstantAndVariable(previousContent);
       int max_l=content.size();
      for(int i=0;i<max_l;i++)
      {
          if(content.get(i).constantOrVariableText.length()>=2)
          {
          if (
                  (content.get(i).constantOrVariable==2)
                  &(content.get(i).constantOrVariableText.charAt(0)=='$')
                  &(content.get(i).constantOrVariableText.charAt(1)=='l')
             )
          {
              String variableName1=content.get(i).constantOrVariableText,variableName2;
              String classOfVariable1=content.get(i).variableClass;
              if(variable1AndVariable2.get(variableName1)!=null)
              {
                 variableName2=variable1AndVariable2.get(variableName1);
                 content.get(i).constantOrVariableText=variableName2;
              }
              else
              {
                 String globalVariable="$v"+editor.numberOfOderVariables;
                 //we create a global variable
                 editor.newVariableAndClass.put(globalVariable,classOfVariable1);
                 newVariablesList.add(globalVariable);//we add the list
                 editor.numberOfOderVariables++;//we increment the index of global variable
                 //we make the link between variables
                 variable1AndVariable2.put(variableName1,globalVariable);
                  //we update the result
                     variableName2=variableName1.substring(2);//scrape $l
                     
                     List<ConstantAndVariable> globalList=new ArrayList<ConstantAndVariable>();
                     ConstantAndVariable cv=new ConstantAndVariable();
                     cv.variableClass=classOfVariable1;
                     cv.constantOrVariableText=globalVariable;
                     cv.constantOrVariable=2;
                     globalList.add(cv);
                     result.put(variableName2, globalList);
                 //END update the result

                 //we change the name of variable with name of global variable
                 content.get(i).constantOrVariableText=globalVariable;
              }
          }
          }

      }


    
   String newVariable=variable.substring(0);//we make a copy of variable name
   if(variable.length()>=2)
   {
   if ((variable.charAt(0)=='$')
       &(variable.charAt(1)=='l'))
   {
    newVariable=variable.substring(2);//scrape $l
    //we update the map of variable and content
    result.put(newVariable, content);
   }
    else if ((variable.charAt(0)=='$')
       &(variable.charAt(1)=='v'))
   {
    // we update the map of global variable and content
    editor.newVariableAndContent.put(newVariable, content);
    //we write down the name in a list of variables that must be updated
    variablesToUpdate.add(newVariable);
   }
   }
    
}


} while(variableIterator.hasNext());
}

if (!newVariablesList.isEmpty())
{
//the statement and list of new variables
editor.newStatementAndGeneratedVariable.put(editor.selectedDemonstrationItem0.name, newVariablesList);
}

}


if(editor.selectedDemonstrationItem0!=null)
{
//we update the link between statement and the new variables that have content
String statementName=editor.selectedDemonstrationItem0.name;
if (variablesToUpdate!=null)
 {
 if (!variablesToUpdate.isEmpty())
 {
 
  // we attach to the concerned statement the list
  //of global variables that have acquired content
     if (!variablesToUpdate.isEmpty())
  {
  editor.newStatementAndGeneratedVariableContent.put
          (statementName,variablesToUpdate);
  editor.updateNewStatements(editor.demonstrationSource);
  }
  
 }
 }

//END update the link between statement and new variables that have content
}

return result;
}
public static void generateNewVariables
        (
        List<Variable> totalVariables,
        DemonstrationEditor editor,
        Map<String,List<ConstantAndVariable>> variableContent
        )
{

if (totalVariables!=null)
{
String statementName=editor.selectedDemonstrationItem0.name;
int totalVariablesDimension=totalVariables.size();
/*we go through total variables and we see if exists in variableContent
 and if not exists we will create new variables and will load them in
 variableContent and subsequent we will replace them in the new generated statements*/
 List<String> listOfNewVariables=null;
 listOfNewVariables=editor.newStatementAndGeneratedVariable.get(statementName);

 if (listOfNewVariables==null)
 {
     listOfNewVariables=new ArrayList<String>();
 }
 
 for(int i=0;i<totalVariablesDimension;i++)
 {
   Variable v_i=totalVariables.get(i);
  if(!variableContent.containsKey(v_i.variableText))
  {
      //we generate a new variable
      String newVariableName="$v"+editor.numberOfOderVariables;
      //we mention the class of new variable
      editor.newVariableAndClass.put(newVariableName,v_i.variableClass);
      listOfNewVariables.add(newVariableName);
      editor.numberOfOderVariables++;//we increment counter
      ConstantAndVariable newVariable=new ConstantAndVariable();
      newVariable.constantOrVariable=2;
      newVariable.variableClass=v_i.variableClass;
      newVariable.constantOrVariableText=newVariableName;

      List<ConstantAndVariable> newVariableList= new ArrayList<ConstantAndVariable>();
      newVariableList.add(newVariable);
      //we make the link between older name and new variable
      variableContent.put(v_i.variableText,newVariableList);
  }
 }
  if (!listOfNewVariables.isEmpty())
  {
    //we deleted previous values
    editor.newStatementAndGeneratedVariable.remove(statementName);
    //we add new values
    editor.newStatementAndGeneratedVariable.put(statementName, listOfNewVariables);
  }
}
}

public static void generateNewVariablesInGeneral
        (
        List<Variable> totalVariables,
        DemonstrationEditor editor,
        Map<String,List<ConstantAndVariable>> variableContent
        )
{

if (totalVariables!=null)
{

int totalVariablesDimension=totalVariables.size();
 /*we go through total variables and we see if exists in variableContent
 and if not exists we will create new variables and will load them in
 variableContent and subsequent we will replace them in the new generated statements*/
 for(int i=0;i<totalVariablesDimension;i++)
 {
   Variable v_i=totalVariables.get(i);
  if(!variableContent.containsKey(v_i.variableText))
  {
      //we generate a new variable
      String newVariableName="$v"+editor.numberOfOderVariables;
      //we mention the class of new variable
      editor.newVariableAndClass.put(newVariableName,v_i.variableClass);
      
      editor.numberOfOderVariables++;//we increment the counter
      ConstantAndVariable newVariable=new ConstantAndVariable();
      newVariable.constantOrVariable=2;
      newVariable.variableClass=v_i.variableClass;
      newVariable.constantOrVariableText=newVariableName;

      List<ConstantAndVariable> newVariableContent= new ArrayList<ConstantAndVariable>();
      newVariableContent.add(newVariable);
      //we make the link between old name and new variable
      variableContent.put(v_i.variableText,newVariableContent);
  }
 }
  
}
}

public static List<ConstantAndVariable> copyTheListOfConstantAndVariable(List<ConstantAndVariable> list)
{
    //we make the copy of ConstantAndVariable list
    List<ConstantAndVariable> result=new ArrayList<ConstantAndVariable>();
    ConstantAndVariable item=null;
    if (list!=null)
    {int max=list.size();
     for(int i=0;i<max;i++)
     {
      item=new ConstantAndVariable();
      item.constantOrVariable=list.get(i).constantOrVariable;
      item.constantOrVariableText=list.get(i).constantOrVariableText;
      item.variableClass=list.get(i).variableClass;
      result.add(item);
     }

    } else errorNewLine("Null ConstantAndVariable list!");

    return result;
};
public static List<ConstantAndVariable> copyTheSublistOfConstantAndVariable
                               (List<ConstantAndVariable> list,int position,int displacement)
{
    //we make the copy of ConstantAndVariable list
    List<ConstantAndVariable> result=new ArrayList<ConstantAndVariable>();
    ConstantAndVariable item=null;
    if (list!=null)
    {int max=list.size();
     if ((position+displacement-1)<max)
     {
     for(int i=position;i<position+displacement;i++)
     {
      item=new ConstantAndVariable();
      item.constantOrVariable=list.get(i).constantOrVariable;
      item.constantOrVariableText=list.get(i).constantOrVariableText;
      item.variableClass=list.get(i).variableClass;
      result.add(item);
     }
     } else Source.errorNewLine("Out of ConstantAndVariable list boundary!");

    } else Source.errorNewLine("Null ConstantAndVariable list!");

    return result;
};

public static boolean isEqualXWithY(List<ConstantAndVariable> xList,List<ConstantAndVariable> yList)
{    int i=0,max=0;

boolean equal=false;
if (xList!=null)
{
if(yList!=null)
{
max=yList.size();
if ((xList.size()==yList.size())
    &(max>0)
   )
{equal=true;
do
{
//we go through xList and yList
//if it is constant
if ((xList.get(i).constantOrVariable==1)&(yList.get(i).constantOrVariable==1))
{
if (xList.get(i).constantOrVariableText.equals(yList.get(i).constantOrVariableText))
{i++;}
else {equal=false;}
}
//if it is variable
else if
((xList.get(i).constantOrVariable == 2) & (yList.get(i).constantOrVariable == 2))
{
if
((xList.get(i).constantOrVariableText.equals(yList.get(i).constantOrVariableText))&
(xList.get(i).variableClass.equals(yList.get(i).variableClass)))
{i++;}
else {equal=false;}
}
else equal=false;//error
} while ((i<max)&(equal==true));
}
}
}

return equal;
}


public void generateListOfSyntacticDefinitionsFromA(Axiom ax)
{
 String type="wff";
 List<Object> typeList=new ArrayList<Object>();
 if (ax!=null)
 {
 if (ax.items!=null)
  {
     //we search for wff-s
     type="wff";
     typeList=this.classListOfDefinitions.get(type);

     if (typeList==null)
     {
     typeList=new ArrayList<Object>();
     }
     if (ax.items.get(0).constantOrVariableText.equals(type))
      {
         this.classListOfDefinitions.remove(type);//we eliminate from map
         typeList.add(ax);//we enlarge the list of wff axioms
         this.classListOfDefinitions.put(type, typeList);//we update the map
      }

     //we search for set-s
     type="set";
     typeList=this.classListOfDefinitions.get(type);
     if (typeList==null)
     {
     typeList=new ArrayList<Object>();
     }
     if (ax.items.get(0).constantOrVariableText.equals(type))
      {
         this.classListOfDefinitions.remove(type);//we eliminate from map
         typeList.add(ax);//we enlarge the list of set axioms
         this.classListOfDefinitions.put(type, typeList);//we update the map
      }

     //we search for class-s
     type="class";
     typeList=this.classListOfDefinitions.get(type);
     if (typeList==null)
     {
     typeList=new ArrayList<Object>();
     }
     if (ax.items.get(0).constantOrVariableText.equals(type))
      {
         this.classListOfDefinitions.remove(type);//we eliminate from map
         typeList.add(ax);//we enlarge the list of class axioms
         this.classListOfDefinitions.put(type, typeList);//we update the map
      }
      
     

  }
 }
}

public void generateListOfSyntacticDefinitionsFromP(Theorem tx)
{
 String type="wff";
 List<Object> typeList=new ArrayList<Object>();
 if (tx!=null)
 {
 if (tx.items!=null)
  {
     //we search for wff-s
     type="wff";
     typeList=this.classListOfDefinitions.get(type);

     if (typeList==null)
     {
     typeList=new ArrayList<Object>();
     }
     if (tx.items.get(0).constantOrVariableText.equals(type))
      {
         this.classListOfDefinitions.remove(type);//we eliminate from map
         typeList.add(tx);//we enlarge the list of wff theorems
         this.classListOfDefinitions.put(type, typeList);//we update the map
      }

     //we search for set-s
     type="set";
     typeList=this.classListOfDefinitions.get(type);
     if (typeList==null)
     {
     typeList=new ArrayList<Object>();
     }
     if (tx.items.get(0).constantOrVariableText.equals(type))
      {
         this.classListOfDefinitions.remove(type);//we eliminate from map
         typeList.add(tx);//we enlarge the list of set theorems
         this.classListOfDefinitions.put(type, typeList);//we update the map
      }

     //we search for class-s
     type="class";
     typeList=this.classListOfDefinitions.get(type);
     if (typeList==null)
     {
     typeList=new ArrayList<Object>();
     }
     if (tx.items.get(0).constantOrVariableText.equals(type))
      {
         this.classListOfDefinitions.remove(type);//we eliminate from map
         typeList.add(tx);//we enlarge the list of class theorems
         this.classListOfDefinitions.put(type, typeList);//we update the map
      }



  }
 }
}
public SyntacticItem generateSyntacticTree(List<ConstantAndVariable> statement)
{
boolean ok=true;
SyntacticItem syntacticItem=new SyntacticItem();
syntacticItem.initialPosition=1;//initial position is 1
//we begin with position 1 because on 0 is |-
ok=this.verifySyntacticType(statement,"wff",1,true,syntacticItem);
if (!ok) syntacticItem=null;
return syntacticItem;
}

public SyntacticItem generateSyntacticTree(List<ConstantAndVariable> statement,String class1)
{
boolean ok=true;
SyntacticItem syntacticItem=new SyntacticItem();
syntacticItem.initialPosition=1;//initial position is 1
//we begin with position 1 because on 0 is |-
ok=this.verifySyntacticType(statement,class1,0,true,syntacticItem);
if (!ok) syntacticItem=null;
return syntacticItem;
}
public boolean verifySyntacticType
(
  List<ConstantAndVariable> statement,
  String class1,int initialPosition,
  boolean isBase,
  SyntacticItem syntacticItem
)
{
 //update and initialization of syntactic item
 syntacticItem.containedItems=new ArrayList<SyntacticItem>();
 syntacticItem.definitionClass=class1;
 //END update

 boolean ok=false,checkItOut=false;
 //variable used for memorize the last position from statement
 //that respect the conditions of "class1" definition
 int lastPosition=0;

 List<Object> vector=null;
 vector=this.classListOfDefinitions.get(class1);//find the vector cu class1 definitions
 int position=initialPosition;

 if (vector!=null)
 {
  //the counter of syntactic definition (class1) visited at the moment t
  int vectorIndex=0;
  int vectorDimension=vector.size();
  //here we store if a rule at vectorIndex is checked (visited)
  BitSet whatIsVisited=new BitSet(vectorDimension);
  //we verify if in  "statement" exists on "initialPositon"
  //a variable that has the same class with "class1"

   //we liberate the contained items
   syntacticItem.containedItems.clear();
   position=initialPosition;



 do
 {

    List<ConstantAndVariable> definition=null;

     do
     {
      if(whatIsVisited.get(vectorIndex)){vectorIndex++;/*we go to next index*/}
      else {break;}
      if(vectorIndex>=vectorDimension){ break;}
     }
    while(true);
   if(vectorIndex<vectorDimension)
   {
    if (vector.get(vectorIndex).getClass().equals(Axiom.class))
    {
        Axiom ax=(Axiom) vector.get(vectorIndex);
        definition=ax.items;
        syntacticItem.a_p_v=1;//syntactic definition of type axiom
        syntacticItem.definitionName=ax.name;
    }
     else
         if (vector.get(vectorIndex).getClass().equals(Theorem.class))
            {
                Theorem tx=(Theorem) vector.get(vectorIndex);
                definition=tx.items;
                syntacticItem.a_p_v=2;//syntactic definition of type theorem
                syntacticItem.definitionName=tx.name;
            }
     }

    if (definition!=null)
    {
     //we mark the vectorIntex
      whatIsVisited.set(vectorIndex);
     //System.out.println("Rule: "+vectorIndex+":"+ this.displayCV(definition));
    checkItOut=true;

    int j=1;//we start form 1,on 0 is the type(wff,set,class,..)
    
    do
    {
     
     ConstantAndVariable cv_j=definition.get(j);
     boolean localCheck=true;

     if (position<statement.size())
     {
      //if  on j position we have a constant in the definition
      //we must have an identical constant on position "position" in statement
     if ((cv_j.constantOrVariable==1))
     {
       if (statement.get(position).constantOrVariable==1)
       {
       if(!(cv_j.constantOrVariableText.
               equals(statement.get(position).constantOrVariableText)))
       {
           localCheck=false;
       }
       }
       else localCheck=false;

       if (localCheck){lastPosition=position;}
     }
     //if on j position we have a variable in the definition
     //we verify if on position"position" in the statement follows
     //sequence from class "class1"
     else if (cv_j.constantOrVariable==2)
     {
         String variableClass="";
         variableClass=cv_j.variableClass;
         SyntacticItem newSyntacticItem=new SyntacticItem();
         newSyntacticItem.initialPosition=position;//we set  already the initial position
         if
         (!this.verifySyntacticType
                 (statement, variableClass, position,false,newSyntacticItem)
         )

         {
             localCheck=false;
         }

         if(localCheck)
         {
             //we update the list with contained items
             syntacticItem.containedItems.add(newSyntacticItem);
             newSyntacticItem.fatherItem=syntacticItem;//we make the reverse link
             lastPosition=newSyntacticItem.finalPosition;
             position=lastPosition;//we update "position"
         }
     }

     }
      else checkItOut=false;

     if(localCheck)
     {
     j++;
     position++;//we increment position in statement
     } else
     {
      //we search for a new definition that starts identical
      if(j>1)
      {
         List<ConstantAndVariable> definition2=null;
         byte a_p_v2=0;
         String name2="";
         boolean weFound=false;
        for(int i1=vectorIndex+1;i1<vectorDimension;i1++)
        {

          if(!whatIsVisited.get(i1))
          {
            if (vector.get(i1).getClass().equals(Axiom.class))
            {
                Axiom ax=(Axiom) vector.get(i1);
                definition2=ax.items;
                a_p_v2=1;//syntactic definition of type axiom
                name2=ax.name;
            }
             else
             if (vector.get(i1).getClass().equals(Theorem.class))
            {
                Theorem tx=(Theorem) vector.get(i1);
                definition2=tx.items;
                a_p_v2=2;//syntactic definition of type theorem
                name2=tx.name;
            }

            if(definition2!=null)
            {
              if(this.startWithCV(j, definition, definition2))
              {
               /* System.out.println("Continuation: "+
                                                   this.displayCV(definition2));
                * 
                */
                weFound=true;
               definition=definition2;
               syntacticItem.a_p_v=a_p_v2;
               syntacticItem.definitionName=name2;
               //we mark that i1 is visited
               whatIsVisited.set(i1);
               break;

              }
            }
           
          }

        }
        if(!weFound){checkItOut=false;}


      }
      else{checkItOut=false;}

     }

     if(!checkItOut)
     {
         //we delete the items contained in "syntacticItem"
        syntacticItem.containedItems.clear();
        position=initialPosition;//we return to initial position
         break;//if do not check the syntactic type we exit from do 1
     }


     
     if(j>=definition.size()){break;}
    }while (true);

    }
  if(checkItOut) break;//if do not check the syntactic type we exit from do 2
  vectorIndex++;
  if(vectorIndex>=vectorDimension){ break;}
 }
 while(vectorIndex<vectorDimension);
 }


 if (!checkItOut)
 {
    //we verify if on position "position" we have a variable of class "class1"
  if (statement.get(position).constantOrVariable==2)//erorr index 0 size0
          {
             if
               (
               (class1.equals(statement.get(position).variableClass))
               )
             {
                 checkItOut = true;
                 //we memorize the last position from statement that respect
                 //the condition to be variable of class "class1"
                 lastPosition=position;
                 syntacticItem.a_p_v=3;//it is a variable
                 syntacticItem.definitionName=statement.get(position).constantOrVariableText;

               if (checkItOut&isBase&((lastPosition+1)<statement.size()))
                    {
                     checkItOut=false;
                    }
             }
              else checkItOut=false;

          } else checkItOut=false;
  }

 ok=checkItOut;//if it verifies is ok otherwise is not ok

  //if is ok and is base and last position+1 is litle than statement createNewBranchBackwardChaining
 //then statement is not from class "class1"
 if (ok&isBase&((lastPosition+1)<statement.size()))
 {
     ok=false;
 }

 syntacticItem.finalPosition=lastPosition;//we update the final position
 return ok;
}
public boolean startWithCV(int j,List<ConstantAndVariable> d1,
                                                   List<ConstantAndVariable> d2)
{
  boolean ok=true;
  int dimD2=d2.size();

  if(j>dimD2) return false;
  
  for(int i=1;i<j;i++)
  {
   ConstantAndVariable cv1_i=d1.get(i),cv2_i=d2.get(i);

   if(cv1_i.constantOrVariable==1)
   {
       if(cv2_i.constantOrVariable==1)
       {
         if(!cv1_i.constantOrVariableText.equals(cv2_i.constantOrVariableText))
         {
         ok=false;break;
         }
       }
      else{ok=false;break; }
   }
   else if (cv1_i.constantOrVariable==2)
   {
       if(cv2_i.constantOrVariable==2)
       {
         if(!cv1_i.variableClass.equals(cv2_i.variableClass))
         {
         ok=false;break;
         }
       }
       else{ok=false;break; }

   }

  }


  return ok;

}


public SyntacticItem generateSyntacticTree2(List<ConstantAndVariable> statement,String class1)
{
boolean ok=true;
SyntacticItem syntacticItem=new SyntacticItem();
syntacticItem.definitionName="#$1";
syntacticItem.initialPosition=1;//initial position is 1
//we start with position1,on 0 is |-
ok=this.verifySyntacticType2(statement,class1,0,true,syntacticItem);
if (!ok) syntacticItem=null;
return syntacticItem;
}

public boolean verifySyntacticType2
(
  List<ConstantAndVariable> statement,
  String class1,int initialPosition,
  boolean isBase,
  SyntacticItem syntacticItem
)
{
 //update and initialization of syntactic item
 syntacticItem.containedItems=new ArrayList<SyntacticItem>();
 syntacticItem.definitionClass=class1;
 //END update

 boolean ok=false,checkItOut=false;
 //variable used for memorize the last position from statement
 //that respect the conditions of "class1" definition
 int lastPosition=0;

 List<Object> vector=null;
 vector=this.classListOfDefinitions.get(class1);//find the vector cu class1 definitions
 int position=initialPosition;

 if (vector!=null)
 {
  //the counter of syntactic definition (class1) visited at the moment t
  int vectorIndex=0;
  int vectorDimension=vector.size();
  //here we store if a rule at vectorIndex is checked (visited)
  BitSet whatIsVisited=new BitSet(vectorDimension);
  //we verify if in  "statement" exists on "initialPositon"
  //a variable that has the same class with "class1"

   //we liberate the contained items
   syntacticItem.containedItems.clear();
   position=initialPosition;



 do
 {

    List<ConstantAndVariable> definition=null;

     do
     {
      if(whatIsVisited.get(vectorIndex)){vectorIndex++;/*we go to next index*/}
      else {break;}
      if(vectorIndex>=vectorDimension){ break;}
     }
    while(true);
   if(vectorIndex<vectorDimension)
   {
    if (vector.get(vectorIndex).getClass().equals(Axiom.class))
    {
        Axiom ax=(Axiom) vector.get(vectorIndex);
        definition=ax.items;
        syntacticItem.a_p_v=1;//syntactic definition of type axiom
        syntacticItem.definitionName=ax.name;
    }
     else
         if (vector.get(vectorIndex).getClass().equals(Theorem.class))
            {
                Theorem tx=(Theorem) vector.get(vectorIndex);
                definition=tx.items;
                syntacticItem.a_p_v=2;//syntactic definition of type theorem
                syntacticItem.definitionName=tx.name;
            }
     }

    if (definition!=null)
    {
     //we mark the vectorIntex
      whatIsVisited.set(vectorIndex);
     //System.out.println("Rule: "+vectorIndex+":"+ this.displayCV(definition));
    checkItOut=true;

    int j=1;//we start form 1,on 0 is the type(wff,set,class,..)

    do
    {

     ConstantAndVariable cv_j=definition.get(j);
     boolean localCheck=true;

     if (position<statement.size())
     {
      //if  on j position we have a constant in the definition
      //we must have an identical constant on position "position" in statement
     if ((cv_j.constantOrVariable==1))
     {
       if (statement.get(position).constantOrVariable==1)
       {
       if(!(cv_j.constantOrVariableText.
               equals(statement.get(position).constantOrVariableText)))
       {
           localCheck=false;
       }
       }
       else localCheck=false;

       if (localCheck){lastPosition=position;}
     }
     //if on j position we have a variable in the definition
     //we verify if on position"position" in the statement follows
     //sequence from class "class1"
     else if (cv_j.constantOrVariable==2)
     {
         String variableClass="";
         variableClass=cv_j.variableClass;
         SyntacticItem newSyntacticItem=new SyntacticItem();
         newSyntacticItem.initialPosition=position;//we set  already the initial position
         if
         (!this.verifySyntacticType2
                 (statement, variableClass, position,false,newSyntacticItem)
         )

         {
             localCheck=false;
         }

         if(localCheck)
         {
             //we update the list with contained items
             syntacticItem.containedItems.add(newSyntacticItem);
             newSyntacticItem.fatherItem=syntacticItem;//we make the reverse link
             lastPosition=newSyntacticItem.finalPosition;
             position=lastPosition;//we update "position"
             newSyntacticItem.motherVariable=cv_j.constantOrVariableText;
         }
     }

     }
      else checkItOut=false;
     

     //if is ok and j is the last item of the rule
     if(localCheck&checkItOut&(j==definition.size()-1))
     {
       int dim1=definition.size(),j2=0;

       for(int i2=1;i2<dim1;i2++)
       {
          ConstantAndVariable cv=definition.get(i2);
          if(cv.constantOrVariable==2)
          {
           if(syntacticItem.containedItems!=null)
           {
            if(j2<syntacticItem.containedItems.size())
            {
            SyntacticItem syn_j2=syntacticItem.containedItems.get(j2);
            j2++;
             if(syn_j2!=null)
             {
               syn_j2.motherVariable=cv.constantOrVariableText;
             }
            }else {System.out.println("Syntactic items j2 outside range!");}


           } else {System.out.println("Syntactic items null!");}

          }
         }
        }

      if(localCheck)
     {
     j++;
     position++;//we increment position in statement
     } else
     {
      //we search for a new definition that starts identical
      if(j>1)
      {
         List<ConstantAndVariable> definition2=null;
         byte a_p_v2=0;
         String name2="";
         boolean weFound=false;
        for(int i1=vectorIndex+1;i1<vectorDimension;i1++)
        {

          if(!whatIsVisited.get(i1))
          {
            if (vector.get(i1).getClass().equals(Axiom.class))
            {
                Axiom ax=(Axiom) vector.get(i1);
                definition2=ax.items;
                a_p_v2=1;//syntactic definition of type axiom
                name2=ax.name;
            }
             else
             if (vector.get(i1).getClass().equals(Theorem.class))
            {
                Theorem tx=(Theorem) vector.get(i1);
                definition2=tx.items;
                a_p_v2=2;//syntactic definition of type theorem
                name2=tx.name;
            }

            if(definition2!=null)
            {
              if(this.startWithCV(j, definition, definition2))
              {
               /* System.out.println("Continuation: "+
                                                   this.displayCV(definition2));
                *
                */
                weFound=true;
               definition=definition2;
               syntacticItem.a_p_v=a_p_v2;
               syntacticItem.definitionName=name2;
               //we mark that i1 is visited
               whatIsVisited.set(i1);
               break;

              }
            }

          }

        }
        if(!weFound){checkItOut=false;}


      }
      else{checkItOut=false;}

     }

     if(!checkItOut)
     {
         //we delete the items contained in "syntacticItem"
        syntacticItem.containedItems.clear();
        position=initialPosition;//we return to initial position
         break;//if do not check the syntactic type we exit from do 1
     }



     if(j>=definition.size()){break;}
    }while (true);

    }
  if(checkItOut) break;//if do not check the syntactic type we exit from do 2
  vectorIndex++;
  if(vectorIndex>=vectorDimension){ break;}
 }
 while(vectorIndex<vectorDimension);
 }


 if (!checkItOut)
 {
    //we verify if on position "position" we have a variable of class "class1"
  if (statement.get(position).constantOrVariable==2)//erorr index 0 size0
          {
             if
               (
               (class1.equals(statement.get(position).variableClass))
               )
             {
                 checkItOut = true;
                 //we memorize the last position from statement that respect
                 //the condition to be variable of class "class1"
                 lastPosition=position;
                 syntacticItem.a_p_v=3;//it is a variable
                 syntacticItem.definitionName=statement.get(position).constantOrVariableText;

               if (checkItOut&isBase&((lastPosition+1)<statement.size()))
                    {
                     checkItOut=false;
                    }
             }
              else checkItOut=false;

          } else checkItOut=false;
  }

 ok=checkItOut;//if it verifies is ok otherwise is not ok

  //if is ok and is base and last position+1 is litle than statement createNewBranchBackwardChaining
 //then statement is not from class "class1"
 if (ok&isBase&((lastPosition+1)<statement.size()))
 {
     ok=false;
 }

 syntacticItem.finalPosition=lastPosition;//we update the final position
 return ok;
}



public  boolean unifyTemplateWithBase
   (List<ConstantAndVariable> templateList0,List<ConstantAndVariable> baseList0,
    Map<String,List<ConstantAndVariable>> variableContent)
{    int max_t=0,max_b=0;

boolean yes=true;

if (templateList0!=null)
{
if(baseList0!=null)
{
max_t=templateList0.size();
max_b=baseList0.size();
if( (max_t>0)&(max_b>0) )
{
 List<ConstantAndVariable> templateList=null;
 List<ConstantAndVariable> baseList=null;
 templateList=Source.copyTheListOfConstantAndVariable(templateList0);
 baseList=Source.copyTheListOfConstantAndVariable(baseList0);
 //we rename all variables from template ex. name --->$lname
 for (int i=0;i<max_t;i++)
 {
     if (templateList.get(i).constantOrVariable==2)
     {
         String nameOfVariable=templateList.get(i).constantOrVariableText;
         nameOfVariable="$l"+nameOfVariable;
         templateList.get(i).constantOrVariableText=nameOfVariable;
     }
 }
 //END rename

SyntacticItem templateSyntacticTree=this.generateSyntacticTree(templateList);
SyntacticItem baseSyntacticTree=this.generateSyntacticTree(baseList);
yes=this.compareTemplateWithBase(templateSyntacticTree, baseSyntacticTree,
                      variableContent, templateList,baseList);
//we verify if the constants on position 0 are identical
//ex.:wff with wff or |- with |-
if (
    !((templateList.get(0).constantOrVariable==1)
    &(baseList.get(0).constantOrVariable==1)
    &(baseList.get(0).constantOrVariableText.
    equals(templateList.get(0).constantOrVariableText)
    ))
   )
{
 yes=false;
}

//END verification constants
}else yes=false;


}else yes=false;
}else yes=false;


return yes;//we return true if template and base unify
}


public  boolean unifyTemplateWithTreeBase
   (List<ConstantAndVariable> templateList0,
    List<ConstantAndVariable> baseList,
    SyntacticItem baseSyntacticTree,
    Map<String,List<ConstantAndVariable>> variableContent)
{    int max_t=0,max_b=0;

boolean yes=true;

if (templateList0!=null)
{
if(baseSyntacticTree!=null)
{
max_t=templateList0.size();
max_b=baseList.size();

if ((max_t>0)&(max_b>0))
{
 List<ConstantAndVariable> templateList=null;
 
 templateList=Source.copyTheListOfConstantAndVariable(templateList0);
 
 //we rename all variables from template ex. name --->$lname
 for (int i=0;i<max_t;i++)
 {
     if (templateList.get(i).constantOrVariable==2)
     {
         String nameOfVariable=templateList.get(i).constantOrVariableText;
         nameOfVariable="$l"+nameOfVariable;
         templateList.get(i).constantOrVariableText=nameOfVariable;
     }
 }
 //END rename

SyntacticItem templateSyntacticTree=this.generateSyntacticTree(templateList);
/*
System.out.println("Template-> "+this.displayCV(templateList));
System.out.println();
this.displaySyntax(templateSyntacticTree);

System.out.println("Base-> "+this.displayCV(baseList));
System.out.println();
this.displaySyntax(baseSyntacticTree);

 * 
 */
yes=this.compareTemplateWithBase(templateSyntacticTree, baseSyntacticTree,
                      variableContent, templateList,baseList);


//END verification constants
}else yes=false;


}else yes=false;
}else yes=false;


return yes;//we return true if template and base unify
}

public  boolean unifyTemplateAssumptionWithBase
   (List<ConstantAndVariable> templateList,List<ConstantAndVariable> baseList,
    Map<String,List<ConstantAndVariable>> variableContent)
{    int max_t=0,max_b=0;

boolean yes=true;

if (templateList!=null)
{
if(baseList!=null)
{
max_t=templateList.size();
max_b=baseList.size();
if( (max_t>0)&(max_b>0) )
{
SyntacticItem templateSyntacticTree=this.generateSyntacticTree(templateList);
SyntacticItem baseSyntacticTree=this.generateSyntacticTree(baseList);
yes=this.compareTemplateWithBase(templateSyntacticTree, baseSyntacticTree,
                      variableContent, templateList,baseList);
//we verify if the constants on position 0 are identical
//ex.: wff with wff or |- with |-
if (
    !((templateList.get(0).constantOrVariable==1)
    &(baseList.get(0).constantOrVariable==1)
    &(baseList.get(0).constantOrVariableText.
    equals(templateList.get(0).constantOrVariableText)
    ))
   )
{
 yes=false;
}

//END verification constants
}else yes=false;


}else yes=false;
}else yes=false;


return yes;//we return true if template and base unify
}

public  boolean unifyTemplateVariableWithBase
   (List<ConstantAndVariable> templateList,List<ConstantAndVariable> baseList,
    Map<String,List<ConstantAndVariable>> variableContent)
{    int max_t=0,max_b=0;

boolean yes=true;

if (templateList!=null)
{
if(baseList!=null)
{
max_t=templateList.size();
max_b=baseList.size();
if( (max_t==1)&(max_b==2) )
{
    
  if (
         /* on position 0 in template we must have a variable that has
          the same name of class with the name of constant on position 0 from base
          and also on position 1 in base  must be a variable
          with the same class with single variable from template
          */
       (templateList.get(0).variableClass.
                           equals(baseList.get(0).constantOrVariableText))
       &(templateList.get(0).constantOrVariable==2)
       &(baseList.get(0).constantOrVariable==1)
       &(templateList.get(0).variableClass.
                           equals(baseList.get(1).variableClass))
       &(baseList.get(1).constantOrVariable==2)
     )
  {
      /*if on position 1 in the base we have a variable of type $v...*/
      if (
              (baseList.get(1).constantOrVariableText.charAt(0)=='$')
             &(baseList.get(1).constantOrVariableText.charAt(1)=='v')
         )
      {
       List<ConstantAndVariable> lista_initiala=null;
       lista_initiala=variableContent.get(baseList.get(1).constantOrVariableText);
      if (lista_initiala!=null)
      {
        if (!Source.isEqualXWithY(lista_initiala, templateList))
            {
            yes=false;
            }
      }else
      {
      //we do the link between variable on position 1 from base with variable from template
      variableContent.put(baseList.get(1).constantOrVariableText, templateList);
      }
      }
      else
          /* if is not identical the variable from base on position 1
             with variable from template on position 0 then
             template does not unify with base
           */
          if (
              !(baseList.get(1).constantOrVariableText.equals
              (templateList.get(0).constantOrVariableText))
              )
              {
                yes=false;
              }
      
  }
    

}else yes=false;


}else yes=false;
}else yes=false;


return yes;//return true if unify template with base
}


public  boolean compareTemplateWithBase
   (SyntacticItem templateTree,
    SyntacticItem baseTree,
    Map<String,List<ConstantAndVariable>> variableContent,
    List<ConstantAndVariable> templateList,
    List<ConstantAndVariable> baseList
    )
{
    boolean ok=true;
  if (templateTree!=null)
  {
   if (baseTree!=null)
  {

    //we have a or p both in template and also in base
    if ( ((templateTree.a_p_v==1)|
         (templateTree.a_p_v==2))&
          ((baseTree.a_p_v==1)|
          (baseTree.a_p_v==2))
       )
   {
     if(templateTree.containedItems!=null)
     {
      if(baseTree.containedItems!=null)
      {
       int maximumTemplate=templateTree.containedItems.size();
       int maximumBase=templateTree.containedItems.size();
       if (
           (Source.stringEqualWithString(templateTree.definitionClass,
                                  baseTree.definitionClass))&
           (Source.stringEqualWithString(templateTree.definitionName,
                                  baseTree.definitionName))
          )
       {
         if((maximumTemplate>=1)|(maximumBase>=1))
          {
           if((maximumTemplate==maximumBase))
           {
           int i=0;
           do 
           {
             if (!this.compareTemplateWithBase(templateTree.containedItems.get(i),
                baseTree.containedItems.get(i), variableContent,
                templateList,baseList
                )
                ) ok=false;
            if (!ok) break;
            i++;
           }while(i<maximumTemplate);
           }
            else ok=false;
          }

       } else ok=false;

      }
     }


   }
   //if we have in template a or p and in base we have v
   else
    if ( ((templateTree.a_p_v==1)|
        (templateTree.a_p_v==2))&
        (baseTree.a_p_v==3)
        )
   {
      //in base we must have a variable of shape $v....
      //otherwise the lists<constantAnVariable> does not unify ('the literals')
      if (
              (Source.stringEqualWithString(baseTree.definitionClass,
                                     templateTree.definitionClass))
              & (Source.stringStartsWith(baseTree.definitionName,"$v"))
             
         )
      {
       //we make the link between the variable from base with a substring from template
       int displacement=0;
       displacement=templateTree.finalPosition-templateTree.initialPosition+1;
       List<ConstantAndVariable> list=null,initialList=null;
       initialList=variableContent.get(baseTree.definitionName);
       list=Source.copyTheSublistOfConstantAndVariable
               (
               templateList,templateTree.initialPosition, displacement
               );
       if(initialList!=null)
       {
           if(!Source.isEqualXWithY(initialList, list))
           {
               ok=false;
           }
       } else
       {
          variableContent.put(baseTree.definitionName, list);
       }
       
      }
       else ok=false;

   }

   //if we have in base a or p and in template v
   else
    if ( ((baseTree.a_p_v==1)|
        (baseTree.a_p_v==2))&
        (templateTree.a_p_v==3)
        )
   {
       //in base we must have a variable of shape $l....
       //otherwise the lists <constantAndVariable> do not unify ('the literals')
        if (
                (Source.stringEqualWithString(baseTree.definitionClass,
                                       templateTree.definitionClass))
              & (Source.stringStartsWith(templateTree.definitionName,"$l"))
           )
      {
       //we make the link between the variable from template with a substring from base
       int displacement=0;
       displacement=baseTree.finalPosition-baseTree.initialPosition+1;
       List<ConstantAndVariable> list=null,initialList=null;
       initialList=variableContent.get(templateTree.definitionName);
       list=Source.copyTheSublistOfConstantAndVariable
               (
               baseList,baseTree.initialPosition, displacement
               );
       if(initialList!=null)
       {
           if(!Source.isEqualXWithY(initialList, list))
           {
               ok=false;
           }
       } else
       {
       variableContent.put(templateTree.definitionName, list);
       }
     } else ok=false;


   }
  
    //if we have in template v and in base also v
   else
    if ( (templateTree.a_p_v==3)&
         (baseTree.a_p_v==3)
        )
   {
   if(Source.stringEqualWithString(baseTree.definitionClass,templateTree.definitionClass))
     {
 
     if  (
          Source.stringStartsWith(baseTree.definitionName,"$v")
         )
         {
          //we make the link between variable from base and the one from template
          ConstantAndVariable cv=new ConstantAndVariable();
          cv.constantOrVariable=2;//we mark the item as a variable
          cv.variableClass=templateTree.definitionClass;
          cv.constantOrVariableText=templateTree.definitionName;
          List<ConstantAndVariable> list= new ArrayList<ConstantAndVariable>(),initialList=null;
          list.add(cv);
          initialList=variableContent.get(baseTree.definitionName);
          if(initialList!=null)
           {
               if(!Source.isEqualXWithY(initialList, list))
               {
                   ok=false;
               }
           } else
           {
            variableContent.put(baseTree.definitionName,list);
           }
         }
        
       else if
             (
             (!Source.stringStartsWith(baseTree.definitionName,"$v"))
            &(Source.stringStartsWith(templateTree.definitionName,"$l"))
             )
         {
          //we make the link between variable from template of type $l... and the one from base
          ConstantAndVariable cv=new ConstantAndVariable();
          cv.constantOrVariable=2;//we mark the item as a variable
          cv.variableClass=baseTree.definitionClass;
          cv.constantOrVariableText=baseTree.definitionName;
          List<ConstantAndVariable> list= new ArrayList<ConstantAndVariable>(),initialList=null;
          list.add(cv);
          initialList=variableContent.get(templateTree.definitionName);
          if(initialList!=null)
           {
               if(!Source.isEqualXWithY(initialList, list))
               {
                   ok=false;
               }
           } else
             {
              variableContent.put(templateTree.definitionName,list);
             }
         }
       else if (
               (!Source.stringStartsWith(baseTree.definitionName,"$v"))
              &(!Source.stringStartsWith(templateTree.definitionName,"$l"))
               )
     {
         //if we have two normal variables both in the template and also in base
         //and their name is different then the lists <constantAndVariable> do not unify
         // 'so called literals'
        if( !(templateTree.definitionName.equals(baseTree.definitionName)) )
                ok = false;
      }
   }else ok=false;//END variable in template and variable in base
     
 } else ok=false;//END general classification


 }
 }

    return ok;
}


public DemonstrationItem generateDemonstration(ParsingItem theoremLabelItem)
{

 this.listDR.clear();//we delete all the distinct requirements
 
 provedTheorem1=theoremLabelItem.textSymbol;//we memorize the name of the theorem
 //System.out.println(provedTheorem1+":");
ParsingItem equalItem=null;
//with this cursor we go through compressed demonstration
int demonstrationCursor=0;
//the list of theorems and axioms used in demonstration
List<String> nameList=new java.util.ArrayList<String>();
//the index from which begins the compressed demonstration
int compressedDemonstrationIndex=0;


if (theoremLabelItem!=null)
{
if (theoremLabelItem.containedItems!=null)
{
if (theoremLabelItem.containedItems.size()==2)
{

if ((theoremLabelItem.containedItems.get(0).codeSymbol==Source.C_P_SYMBOL)
&(theoremLabelItem.containedItems.get(1).codeSymbol==Source.C_EQUAL_SYMBOL))
{
equalItem=theoremLabelItem.containedItems.get(1);
}
}
}
}
else this.errorNewLine("Non-existing theorem label!");

//we build the name list
//the maximum index of items contained by equal label
int maximumIndexContainedByEqualItem=0;
if (equalItem!=null)
{
if (equalItem.containedItems!=null)
{
//the maximum index of items contained by equal label
maximumIndexContainedByEqualItem=equalItem.containedItems.size()-1;
//we verify if it is a compressed demonstration (if exists open parenthesis)
if (equalItem.containedItems.get(0).textSymbol.equals("("))
{ demonstrationCursor=0; demonstrationCursor++;
do {
if  ( (!equalItem.containedItems.get(demonstrationCursor).textSymbol.equals(")"))&
(demonstrationCursor!=maximumIndexContainedByEqualItem)
)
{
nameList.add(equalItem.containedItems.get(demonstrationCursor).textSymbol);
demonstrationCursor++;
}
else break;
} while (true);

if (equalItem.containedItems.get(demonstrationCursor).textSymbol.equals(")"))
{
compressedDemonstrationIndex=demonstrationCursor+1;
}
else if (demonstrationCursor==maximumIndexContainedByEqualItem)
{ this.errorNewLine("Expect )!");
}
}
else  {  this.errorNewLine("Here is not a compressed proof!");}
}
else {}
}
else this.errorNewLine("Not found equal sign in the proof!");
//the list with the applied axioms and applied theorems
//v0 the number of total variables of the theorem,m the number of hypotheses of the theorem,
//n the number of theorems from the next name list
 
int v0=0,m=0,n=0;
//construction stack of the demonstration tree
Stack<DemonstrationItem> stack= new java.util.Stack<DemonstrationItem>();
DemonstrationItem  currentDemonstrationItem=null,previousDemonstrationItem=null;
//the base item of the demonstration tree
DemonstrationItem mainDemonstrationItem=null;
//the list with repetitions in demonstration
List<DemonstrationItem> repetitionsList=new java.util.ArrayList<DemonstrationItem>();
//the link between variables and their content
Map<String,List<ConstantAndVariable>>   variableContent=null;
//the generated theorem of the label
Theorem t= (Theorem) theoremLabelItem.generatedItem;

if (t!=null) { if (t.totalVariables!=null) {v0=t.totalVariables.size();}
if (t.hypotheses!=null) {m=t.hypotheses.size();}
if (nameList!=null) {n=nameList.size();}
}
else  this.errorNewLine("The label has no generated theorem!");


//we go through compressed demonstration, extract the numbers of order
//and  build the demonstration tree
byte digits1[]=new byte[100];
byte i=0;
char letter='a';
byte digit1=0;
byte digit2=0;
int number=0,numberOfOrder=0;
int positionInString=0;
int stringLength=0;
//string of letters
String stringOfLetters=null;
boolean markTheHub=false;
//the index of new statements
int numberOfNewStatement=0;

//if we have a compressed demonstration, we go through the strings of letters
//and we extract the numbers
if (compressedDemonstrationIndex>0)
{
while (compressedDemonstrationIndex<=maximumIndexContainedByEqualItem)
{
positionInString=0;
stringLength=equalItem.containedItems.get(compressedDemonstrationIndex).
                                                         textSymbol.length();
stringOfLetters=equalItem.containedItems.get(compressedDemonstrationIndex).
                                                         textSymbol;
while (positionInString<stringLength)
{
//we pick the letter from that position
letter=stringOfLetters.charAt(positionInString);
//we extract the numbers
markTheHub=false;
switch (letter )
{
default:

case 'U':digit1=1;digits1[i] = digit1;i++;
if (i > 100) {this.errorNewLine("more then 100 digits!");} break;
case 'V':digit1=2;digits1[i] = digit1;i++;
if (i > 100) {this.errorNewLine("more then 100 digits!");} break;
case 'W':digit1=3;digits1[i] = digit1;i++;
if (i > 100) {this.errorNewLine("more then 100 digits!");}break;
case 'X':digit1=4;digits1[i] = digit1;i++;
if (i > 100) {this.errorNewLine("more then 100 digits!");} break;
case 'Y':digit1=5;digits1[i] = digit1;i++;
if (i > 100) {this.errorNewLine("more then 100 digits!");} break;

case 'A':digit2 =1;number=this.numberFromDigits(digits1, i)+digit2;i=0; break;
case 'B':digit2=2;number=this.numberFromDigits(digits1, i)+digit2;i=0; break;
case 'C':digit2=3;number=this.numberFromDigits(digits1, i)+digit2;i=0; break;
case 'D':digit2=4;number=this.numberFromDigits(digits1, i)+digit2;i=0; break;
case 'E':digit2=5;number=this.numberFromDigits(digits1, i)+digit2;i=0; break;
case 'F':digit2=6;number=this.numberFromDigits(digits1, i)+digit2;i=0; break;
case 'G':digit2=7;number=this.numberFromDigits(digits1, i)+digit2;i=0; break;
case 'H':digit2=8;number=this.numberFromDigits(digits1, i)+digit2;i=0; break;
case 'I':digit2=9;number=this.numberFromDigits(digits1, i)+digit2;i=0; break;
case 'J':digit2=10;number=this.numberFromDigits(digits1, i)+digit2;i=0; break;
case 'K':digit2=11;number=this.numberFromDigits(digits1, i)+digit2;i=0; break;
case 'L':digit2=12;number=this.numberFromDigits(digits1, i)+digit2;i=0; break;
case 'M':digit2=13;number=this.numberFromDigits(digits1, i)+digit2;i=0; break;
case 'N':digit2=14;number=this.numberFromDigits(digits1, i)+digit2;i=0; break;
case 'O':digit2=15;number=this.numberFromDigits(digits1, i)+digit2;i=0; break;
case 'P':digit2=16;number=this.numberFromDigits(digits1, i)+digit2;i=0; break;
case 'Q':digit2=17;number=this.numberFromDigits(digits1, i)+digit2;i=0; break;
case 'R':digit2=18;number=this.numberFromDigits(digits1, i)+digit2;i=0; break;
case 'S':digit2=19;number=this.numberFromDigits(digits1, i)+digit2;i=0; break;
case 'T':digit2=20;number=this.numberFromDigits(digits1, i)+digit2;i=0; break;

case 'Z':markTheHub=true; break;
}

if (number!=0)
{    
//rebooting demonstration items
currentDemonstrationItem=null;
//we sort the numbers of order by category
if ((number<=v0)&(number>0))
{
//we add in the stack a variable of the proper demonstration
//and not a variable of axiom or theorem
//used in proper demonstration
currentDemonstrationItem=new DemonstrationItem();
numberOfOrder=number-1;
if (numberOfOrder>=0)
{
if (t.totalVariables!=null)
{
if (!t.totalVariables.isEmpty())
{
currentDemonstrationItem.type=DemonstrationConstants.PROPER_VARIABLE; //proper variable
currentDemonstrationItem.name=t.totalVariables.get(numberOfOrder).variableText;
currentDemonstrationItem.variableClass=t.totalVariables.get(numberOfOrder).variableClass;
//we create an ConstantAndVariable item
//and we attach it to the proper variable(addressing considerations)
ConstantAndVariable cv=new ConstantAndVariable();
cv.constantOrVariableText=currentDemonstrationItem.name;
cv.variableClass=currentDemonstrationItem.variableClass;
cv.constantOrVariable=2;//variable
currentDemonstrationItem.items=new java.util.ArrayList<ConstantAndVariable>();
currentDemonstrationItem.items.add(cv);//adding to the list

currentDemonstrationItem.numberOfOrderInCompressedProof=number;
stack.push(currentDemonstrationItem);
}else errorNewLine("Concerned theorem has 0 variables!1");

} else errorNewLine("Concerned theorem has 0 variables!2");
}

}
else if ((number>v0)&(number<=(m+v0)))
{
//we add to the stack a proper assumption
currentDemonstrationItem=new DemonstrationItem();
numberOfOrder=number-v0-1;
if (numberOfOrder>=0)
{
if (t.hypotheses!=null)
{
if (!t.hypotheses.isEmpty()) {
currentDemonstrationItem.type=DemonstrationConstants.PROPER_HYPOTHESIS;//proper assumption
currentDemonstrationItem.name=t.hypotheses.get(numberOfOrder).name;

currentDemonstrationItem.variableClass="";
currentDemonstrationItem.referenceObject=t.hypotheses.get(numberOfOrder);//address of the assumption
currentDemonstrationItem.numberOfOrderInCompressedProof=number;

stack.push(currentDemonstrationItem);
} else errorNewLine("Concerned theorem has 0 hypotheses!1");
} else errorNewLine("Concerned theorem has 0 hypotheses!2");
}

}
else if ((number>(m+v0))&(number<=(n+m+v0)))
{
//we search for the axioms and theorems for the base
//or for subsequent levels of demonstration

//we initialize the link from variable and content
variableContent= new java.util.HashMap<String,java.util.List<ConstantAndVariable>>();
String foundName="";
currentDemonstrationItem=new DemonstrationItem();
numberOfOrder=number-m-v0-1;
if (numberOfOrder>=0)
{
if (nameList.get(numberOfOrder)!=null)
{
foundName=nameList.get(numberOfOrder);
if (axioms.containsKey(foundName))
{
if (availableAxioms.contains(foundName))
{
//beginning of 'axiom'
Axiom xAxiom=null;
xAxiom=(Axiom)axioms.get(foundName);

//we create a new statement to which
//we will attach all the tree of obtaining this statement

//this is the statement
currentDemonstrationItem.type=DemonstrationConstants.NEW_STATEMENT; //new statement
String statementName="";
numberOfNewStatement++;
statementName="$a"+String.valueOf(numberOfNewStatement);
currentDemonstrationItem.name=statementName;
currentDemonstrationItem.numberOfOrderInCompressedProof=0;

//we attach to the demonstration item(new statement) the simple axiom or composed axiom
DemonstrationItem axiomOrTheoremSimpleOrComposed=new DemonstrationItem();
currentDemonstrationItem.downLink=new java.util.ArrayList<DemonstrationItem>();
currentDemonstrationItem.downLink.add(axiomOrTheoremSimpleOrComposed);
axiomOrTheoremSimpleOrComposed.aboveLink=currentDemonstrationItem;//we create double pointer

if (xAxiom.type==1)
{axiomOrTheoremSimpleOrComposed.type=DemonstrationConstants.SIMPLE_AXIOM;}//simple axiom
else if (xAxiom.type==2)
{axiomOrTheoremSimpleOrComposed.type=DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM;

}//axiom from composed axiom
axiomOrTheoremSimpleOrComposed.name=xAxiom.name;
axiomOrTheoremSimpleOrComposed.undername=xAxiom.name;
axiomOrTheoremSimpleOrComposed.referenceObject=xAxiom;
axiomOrTheoremSimpleOrComposed.numberOfOrderInCompressedProof=number;

//we attach to the composed axiom the associated hypotheses and variables
axiomOrTheoremSimpleOrComposed.downLink=new java.util.ArrayList<DemonstrationItem>();

int hypothesesDimension=0,totalVariablesDimension=0,dimension=0,ii=0,jj=0,kk=0;

//we verify if the axiom has variables
if (xAxiom.totalVariables!=null)
{
totalVariablesDimension=xAxiom.totalVariables.size();
}

//we verify if the axiom has hypotheses
if (xAxiom.hypotheses!=null)
{
hypothesesDimension=xAxiom.hypotheses.size();
}
//we add the number of variables with number of hypotheses
dimension=hypothesesDimension+totalVariablesDimension;

if ((dimension)>0)
{
//here we create a empty list with a given createNewBranchBackwardChaining
ii=0;DemonstrationItem xDemonstration=null;
do {
xDemonstration=new DemonstrationItem();
axiomOrTheoremSimpleOrComposed.downLink.add( xDemonstration );
xDemonstration.aboveLink=axiomOrTheoremSimpleOrComposed;
ii++;
}while(ii<dimension);
//we go back through xAxiom and total variables
ii=dimension-1;jj=hypothesesDimension-1;kk=totalVariablesDimension-1;
Hypothesis xAssumption=null;Variable xVariable=null;DemonstrationItem baseDemonstration=null;
xDemonstration=null;
do
{
if (ii>=0) { xDemonstration=axiomOrTheoremSimpleOrComposed.downLink.get(ii);ii--; }
else {xDemonstration=null;}
if (jj>=0) {xAssumption=xAxiom.hypotheses.get(jj);jj--;}
else {  xAssumption=null;
if (kk>=0) {xVariable=xAxiom.totalVariables.get(kk);kk--;}
else {xVariable=null;}
}


if (xDemonstration!=null)
{
if (!stack.empty())
{
baseDemonstration=stack.pop();
//we establish double pointer
xDemonstration.downLink=new java.util.ArrayList<DemonstrationItem>();
xDemonstration.downLink.add(baseDemonstration);
baseDemonstration.aboveLink=xDemonstration;
//we modify the values of xDemonstration
if (xAssumption!=null)
{
xDemonstration.type=DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_AXIOM;//assumption from composed axiom
xDemonstration.name=xAssumption.name;
xDemonstration.referenceObject=xAssumption;
xDemonstration.numberOfOrderInCompressedProof=0;

}
else if (xVariable!=null)
{
xDemonstration.type=DemonstrationConstants.VARIABLE;//variable
xDemonstration.name=xVariable.variableText;
xDemonstration.variableClass=xVariable.variableClass;
xDemonstration.numberOfOrderInCompressedProof=0;
}

}
else { if (ii>=0) {errorNewLine("The stack is empty too early!");} }
}

xDemonstration=null; xAssumption=null; xVariable=null;

}while (ii>=0);
//we go through  the tree, we associate the variables with the content
//we make the verifications of matches and we generate new statement
xDemonstration=null;baseDemonstration=null;ii=00;
do {
xDemonstration=axiomOrTheoremSimpleOrComposed.downLink.get(ii);ii++;

if (xDemonstration!=null)
{
if (xDemonstration.downLink!=null) baseDemonstration=xDemonstration.downLink.get(0);//here is an error
if (baseDemonstration!=null)
{

if (xDemonstration.type==DemonstrationConstants.VARIABLE)
{
if (baseDemonstration.type==DemonstrationConstants.PROPER_VARIABLE)
{
//
if (xDemonstration.variableClass.equals(baseDemonstration.variableClass))
//we add the link between variable name and ConstantAndVariable list
variableContent.put(xDemonstration.name, baseDemonstration.items);
else errorNewLine("Misfit class:"+xDemonstration.name);

xDemonstration.provisionallyItems=copyTheListOfConstantAndVariable(baseDemonstration.items);//for help
}
else if (baseDemonstration.type==DemonstrationConstants.NEW_STATEMENT)
{
//ok

// we verify if the class of variable is the same
// with the name of the first item of the new statement
String name1="";
if (baseDemonstration.items!=null)
{ if (baseDemonstration.items.get(0)!=null)
  name1=baseDemonstration.items.get(0).constantOrVariableText;}

if (xDemonstration.variableClass.equals(name1))
{
//we make a copy of the new statement but without the first item
//that states the class of the syntax
List<ConstantAndVariable> newList=new ArrayList<ConstantAndVariable>();
newList=baseDemonstration.items.subList(1, baseDemonstration.items.size());
//we add the link between variable name and new list of ConstantAndVariable
variableContent.put(xDemonstration.name, newList);
xDemonstration.provisionallyItems=copyTheListOfConstantAndVariable(newList);//for help
}
}
else
errorNewLine("Proof tree: variable-> expect proper variable or new statement");

}
else if  (xDemonstration.type==DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_AXIOM)
{

if (baseDemonstration.type==DemonstrationConstants.PROPER_HYPOTHESIS)
{
//ok
List<ConstantAndVariable> newString=null;
//assumption from composed axiom
Hypothesis assumptionFromAxiom=(Hypothesis) xDemonstration.referenceObject;
//proper base assumption
Hypothesis properAssumption=(Hypothesis)baseDemonstration.referenceObject;
newString=variablesContentInsertion(assumptionFromAxiom.items,variableContent);

if (!isEqualXWithY(newString,properAssumption.items))
{
errorNewLine("Misfit hypothesis_from_axiom and proper_hypothesis:"
                                             +assumptionFromAxiom.name+","
                                             +properAssumption.name+" from "+t.name);
errorNewLine("-> "+displayCV(newString));
errorNewLine("-> "+displayCV(properAssumption.items));

}
}
else if (baseDemonstration.type==DemonstrationConstants.NEW_STATEMENT)
{
//ok
List<ConstantAndVariable> newString=null;
Hypothesis assumptionFromAxiom=(Hypothesis) xDemonstration.referenceObject;
newString=variablesContentInsertion(assumptionFromAxiom.items,variableContent);

if (!isEqualXWithY(newString,baseDemonstration.items))
{
errorNewLine("Misfit hypothesis_from_axiom and new_statement:  "
                                                +assumptionFromAxiom.name+","
                                                +baseDemonstration.name+" from "+t.name);
errorNewLine("-> "+displayCV(newString));
errorNewLine("-> "+displayCV(baseDemonstration.items));

}

}

else errorNewLine("Tree proof: hypothesis_from_complex_axiom or simple ->"+
               "expect proper_hypothesis,new statement");



}



}else errorNewLine("Null base pointer!");
} else errorNewLine("Null pointer at complex axiom!");



} while (ii<dimension);


}



//the code to generate new main statement

List<ConstantAndVariable> newString0=null;
Axiom axiomFromComposedAxiom=(Axiom) axiomOrTheoremSimpleOrComposed.referenceObject;
newString0=variablesContentInsertion(axiomFromComposedAxiom.items,variableContent);

currentDemonstrationItem.items=newString0;
//we add to the stack
stack.push(currentDemonstrationItem);
//we make the distinct variable verification on groups of $d $.
if(xAxiom.distinct!=null)
{ 
 boolean ok=true;
 int n1=xAxiom.distinct.size();//number of groups of $d $.
 appliedRule1=xAxiom.name;//we memorize the applied axiom
 for(int i1=0;i1<n1;i1++)
 {
   ok=this.checkVariables(xAxiom.distinct.get(i1), variableContent);
   if(!ok){break;}
 }
 if(!ok){errorNewLine("Non-distinct variables at: "+xAxiom.name+
                                       " at statement: "+currentDemonstrationItem.name);}

}

//END generate new main statement


//END 'axioms'
}
else   {errorNewLine("The axiom is not visible in this part of the file!");}
}
else if (theorems.containsKey(foundName))
{
if (availableTheorems.contains(foundName))
{
//beginning 'theorems'


Theorem xTheorem=null;
xTheorem=(Theorem)theorems.get(foundName);

//we create a new statement at which
//we will attach all the tree of obtaining this statement

//this is the statement
currentDemonstrationItem.type=DemonstrationConstants.NEW_STATEMENT;//new statement
String statementName="";
numberOfNewStatement++;
statementName="$a"+String.valueOf(numberOfNewStatement);
currentDemonstrationItem.name=statementName;
currentDemonstrationItem.numberOfOrderInCompressedProof=0;

//we attach at the demonstration item(new statement)..
//...simple axiom,simple theorem, composed axiom or composed theorem
DemonstrationItem simpleOrComposedAxiomOrTheorem=new DemonstrationItem();
currentDemonstrationItem.downLink=new java.util.ArrayList<DemonstrationItem>();
currentDemonstrationItem.downLink.add(simpleOrComposedAxiomOrTheorem);
simpleOrComposedAxiomOrTheorem.aboveLink=currentDemonstrationItem;//we create double pointer

if (xTheorem.type==1)
{simpleOrComposedAxiomOrTheorem.type=DemonstrationConstants.SIMPLE_THEOREM;}//simple theorem
else if (xTheorem.type==2)
{simpleOrComposedAxiomOrTheorem.type=DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM;

}//theorem from composed theorem
simpleOrComposedAxiomOrTheorem.name=xTheorem.name;
simpleOrComposedAxiomOrTheorem.undername=xTheorem.name;
simpleOrComposedAxiomOrTheorem.referenceObject=xTheorem;
simpleOrComposedAxiomOrTheorem.numberOfOrderInCompressedProof=number;

//we attach at the simple or composed theorem the associated hypotheses and variables
simpleOrComposedAxiomOrTheorem.downLink=new java.util.ArrayList<DemonstrationItem>();

int hypothesesDimension=0,totalVariablesDimension=0,dimension=0,ii=0,jj=0,kk=0;

//we verify if the theorem has variables
if (xTheorem.totalVariables!=null)
{
totalVariablesDimension=xTheorem.totalVariables.size();
}

//we verify if the theorem has hypotheses
if (xTheorem.hypotheses!=null)
{
hypothesesDimension=xTheorem.hypotheses.size();
}
//we add the number of variables with the number of hypotheses
dimension=hypothesesDimension+totalVariablesDimension;

if ((dimension)>0)
{

//here we create an empty list of given createNewBranchBackwardChaining
ii=0;DemonstrationItem xDemonstration=null;
do {
xDemonstration=new DemonstrationItem();
simpleOrComposedAxiomOrTheorem.downLink.add( xDemonstration );
xDemonstration.aboveLink=simpleOrComposedAxiomOrTheorem;
ii++;
}while(ii<dimension);
//we go back through the hypotheses of x theorem and total variables
ii=dimension-1;jj=hypothesesDimension-1;kk=totalVariablesDimension-1;
Hypothesis xAssumption=null;Variable xVariable=null;DemonstrationItem baseDemonstration=null;
xDemonstration=null;
do
{
if (ii>=0) { xDemonstration=simpleOrComposedAxiomOrTheorem.downLink.get(ii);ii--; }
else {xDemonstration=null;}
if (jj>=0) {xAssumption=xTheorem.hypotheses.get(jj);jj--;}
else {  xAssumption=null;

if (kk>=0) {xVariable=xTheorem.totalVariables.get(kk);kk--;}
else {xVariable=null;}


}


if (xDemonstration!=null)
{
if (!stack.empty())
{
baseDemonstration=stack.pop();
//we establish double pointer
xDemonstration.downLink=new java.util.ArrayList<DemonstrationItem>();
xDemonstration.downLink.add(baseDemonstration);
baseDemonstration.aboveLink=xDemonstration;
//we modify values of xDemonstration
if (xAssumption!=null)
{
xDemonstration.type=DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_THEOREM;//asumption from composed theorem
xDemonstration.name=xAssumption.name;
xDemonstration.referenceObject=xAssumption;
xDemonstration.numberOfOrderInCompressedProof=0;

}
else if (xVariable!=null)
{
xDemonstration.type=DemonstrationConstants.VARIABLE;//variable
xDemonstration.name=xVariable.variableText;
xDemonstration.variableClass=xVariable.variableClass;
xDemonstration.numberOfOrderInCompressedProof=0;
}

}
else { if (ii>=0) {errorNewLine("The stack is empty too early!");} }
}

xDemonstration=null; xAssumption=null; xVariable=null;

}while (ii>=0);
//we go through the tree,we associate variables with content,
//we do the verifications of matches and we generate new statement
xDemonstration=null;baseDemonstration=null;ii=00;
do {
xDemonstration=simpleOrComposedAxiomOrTheorem.downLink.get(ii);ii++;

if (xDemonstration!=null)
{
if (xDemonstration.downLink!=null) baseDemonstration=xDemonstration.downLink.get(0);//here we have an error
if (baseDemonstration!=null)
{

if (xDemonstration.type==DemonstrationConstants.VARIABLE)
{
if (baseDemonstration.type==DemonstrationConstants.PROPER_VARIABLE)
{
//
if (xDemonstration.variableClass.equals(baseDemonstration.variableClass))
//we add the link between variable name and list of ConstantAndVariable
variableContent.put(xDemonstration.name, baseDemonstration.items);
else errorNewLine("Misfit class:"+xDemonstration.name);
xDemonstration.provisionallyItems=copyTheListOfConstantAndVariable(baseDemonstration.items);//for help
}
else if (baseDemonstration.type==DemonstrationConstants.NEW_STATEMENT)
{
//ok

//we verify if the class of variable is the same
//with the name of first item of the new statement
String name1="";
if (baseDemonstration.items!=null)
{
  if (baseDemonstration.items.get(0)!=null)
      name1=baseDemonstration.items.get(0).constantOrVariableText;}

if (xDemonstration.variableClass.equals(name1))
{
//we make a copy of the new statement but without the first item
//that states the class of the syntax
List<ConstantAndVariable> newList=new ArrayList<ConstantAndVariable>();
newList=baseDemonstration.items.subList(1, baseDemonstration.items.size());
//we add the link between variable name and new list of ConstantAndVariable
variableContent.put(xDemonstration.name, newList);
xDemonstration.provisionallyItems=copyTheListOfConstantAndVariable(newList);//for help

}
}
else  errorNewLine("Tree proof:variable-> expect "+
                                       "proper_variable or new statement");

}
else if  (xDemonstration.type==DemonstrationConstants.HYPOTHESIS_FROM_COMPOSED_THEOREM)
{

if (baseDemonstration.type==DemonstrationConstants.PROPER_HYPOTHESIS)
{
//ok
List<ConstantAndVariable> newString=null;
//assumption from composed theorem
Hypothesis assumptionFromTheorem=(Hypothesis) xDemonstration.referenceObject;
//base proper assumption
Hypothesis properAssumptiom=(Hypothesis) baseDemonstration.referenceObject;
newString=variablesContentInsertion(assumptionFromTheorem.items,variableContent);

if (!isEqualXWithY(newString,properAssumptiom.items))
{
errorNewLine("Misfit hypothesis_from_theorem and proper_hypothesis: "
             +assumptionFromTheorem.name+ ","+properAssumptiom.name+" from "+t.name);
errorNewLine("-> "+displayCV(newString));
errorNewLine("-> "+displayCV(properAssumptiom.items));

}
}
else if (baseDemonstration.type==DemonstrationConstants.NEW_STATEMENT)
{
//ok
List<ConstantAndVariable> newString=null;
Hypothesis assumptionFromTheorem=(Hypothesis) xDemonstration.referenceObject;//asumption from theorem
newString=variablesContentInsertion(assumptionFromTheorem.items,variableContent);

if (!isEqualXWithY(newString,baseDemonstration.items))
{
errorNewLine("Misfit hypothesis_from_theorem and new_statement: "
        +assumptionFromTheorem.name+","+baseDemonstration.name+" from "+t.name);

errorNewLine("-> "+displayCV(newString));
errorNewLine("-> "+displayCV(baseDemonstration.items));

}
}

else errorNewLine("Tree proof: hypothesis_from_complex_theorem->"+
                                " expect proper_hypothesis,new statement!");



}



}else errorNewLine("Null base pointer!");
} else errorNewLine("Null pointer at complex or simple theorem!");



} while (ii<dimension);




}


//the code to generate new main statement

List<ConstantAndVariable> newString0=null;
Theorem teorema_din_teo_comp=(Theorem) simpleOrComposedAxiomOrTheorem.referenceObject;
newString0=variablesContentInsertion(teorema_din_teo_comp.items,variableContent);

currentDemonstrationItem.items=newString0;
//we add to the stack
stack.push(currentDemonstrationItem);
//END generate new main statement
//we do the verification of the distinct variables on groups of $d $.
if(xTheorem.distinct!=null)
{
 boolean ok=true;
 int n1=xTheorem.distinct.size();//number of groups of $d $.
 appliedRule1=xTheorem.name;//we memorize the applied theorem
 for(int i1=0;i1<n1;i1++)
 {
   ok=this.checkVariables(xTheorem.distinct.get(i1), variableContent);
   if(!ok){break;}
 }
 if(!ok){errorNewLine("Non-distinct variables at:"+xTheorem.name+
                                     " at statement: "+currentDemonstrationItem.name);}

}

//END 'theorems'

}
else
{errorNewLine("The theorem is not visible in this part of the file: "+foundName);}

}
else if(this.labelAndVariable.get(foundName)!=null)
{
//here are the proper variables other than
//those belonging to the theorem in demonstration

//proper extern variable
currentDemonstrationItem.type=DemonstrationConstants.PROPER_VARIABLE;

String variableName="",class0="";//name and class of the variable

if(this.labelAndVariable.get(foundName)!=null)
{
variableName=this.labelAndVariable.get(foundName);

if (this.variableAndType.get(variableName)!=null)
{
class0=this.variableAndType.get(variableName);
}
else errorNewLine("Proof error: the variable has no class!"+variableName);



}
else errorNewLine("Proof error: not found variable for the label!"+foundName);


currentDemonstrationItem.name=variableName;
currentDemonstrationItem.variableClass=class0;
//we create an item of type ConstantAndVariable and we attach it
//to the proper variable(addressing considerations)
ConstantAndVariable cv=new ConstantAndVariable();
cv.constantOrVariableText=variableName;
cv.variableClass=class0;
cv.constantOrVariable=2;//variable
currentDemonstrationItem.items=new java.util.ArrayList<ConstantAndVariable>();
currentDemonstrationItem.items.add(cv);//add to the list

currentDemonstrationItem.numberOfOrderInCompressedProof=number;
stack.push(currentDemonstrationItem);


}
else
{errorNewLine("The found name represent neither a theorem neither a axiom"
                                    +" or a variable declaration:"+foundName);}

}

}

variableContent.clear();//liberate the memory
}
else if (number>(n+m+v0))
{
//we add to the stack an item that is identical with an item marked with Z
int number0;
number0=number-m-n-v0-1;
if (repetitionsList!=null)
{
int maximumNumber=repetitionsList.size();
if((number0>=0)&(number0<maximumNumber))
{
DemonstrationItem el_dem_aux=repetitionsList.get(number0);
currentDemonstrationItem=new DemonstrationItem();
currentDemonstrationItem.name=el_dem_aux.name;
currentDemonstrationItem.type=DemonstrationConstants.NEW_STATEMENT;
currentDemonstrationItem.repetition=true;//we mark that is a repetition
currentDemonstrationItem.numberOfOrderInCompressedProof=number;//number in the compressed list
currentDemonstrationItem.items=this.copyTheListOfConstantAndVariable(el_dem_aux.items);

stack.push(currentDemonstrationItem);
}
else
errorNewLine("Index is outside of repetitions list:"+number0+" from "+maximumNumber+" "+t.name);
} else errorNewLine("Repetitions list is empty!");

}
}//END number different to zero
if (markTheHub)
{
if (previousDemonstrationItem!=null)
{
previousDemonstrationItem.markedAsAHub=true;
repetitionsList.add(previousDemonstrationItem);
int max1=repetitionsList.size();
int number2=max1+m+n+v0;
previousDemonstrationItem.numberOfOrderInCompressedProof=number2;//number of the hub

}
else {this.errorNewLine("Repetition of a incorrect number format!");}

markTheHub=false;//we reset the hub mark
}


number=0;//rebooting



positionInString++;
//we memorize previous item
if (currentDemonstrationItem!=null) {previousDemonstrationItem=currentDemonstrationItem;}
}
compressedDemonstrationIndex++;

}
}

//we create the base of demonstration
if (mainDemonstrationItem==null)
{ if (currentDemonstrationItem!=null) {mainDemonstrationItem=currentDemonstrationItem;}}
//we verify if  the last statement matches with the proper theorem
if (mainDemonstrationItem!=null)
{
List<ConstantAndVariable> lista_elem=null;

if (mainDemonstrationItem.referenceObject!=null)
{
if (mainDemonstrationItem.type==DemonstrationConstants.PROPER_HYPOTHESIS)
{
Hypothesis properAssumption=(Hypothesis)mainDemonstrationItem.referenceObject;
lista_elem = properAssumption.items;
} else errorNewLine("The element from the stack is not a proper_hypothesis!");

}
else {lista_elem=mainDemonstrationItem.items;}

if (!this.isEqualXWithY(t.items,lista_elem))
{
errorNewLine("Misfit final statement with so_called_theorem: "+t.name);
errorNewLine("-> "+displayCV(t.items));
this.numberOfInactiveTheorems++;//we grow the number of inactive theorems
}
 
}
//here we will verify witch of distinct requirements are not
//already defined
if(t.distinct!=null)
{
 
 int n1=t.distinct.size();//number of groups of $d $.

 for(int i1=0;i1<n1;i1++)
 {
   this.checkDistinctReq(t.distinct.get(i1),this.listDR);

 }

}
//if we have distinct var. requirements that are not already defined we show them
int dimDR=this.listDR.size();
if (dimDR>0)
{
 for(int i0=0;i0<dimDR;i0++)
 {
  String provedTheorem1=this.listDR.get(i0).provedTheorem;
  String appliedRule1=this.listDR.get(i0).appliedRule;
  String variablesAndContent1=this.listDR.get(i0).variablesAndContent;
  String v1=this.listDR.get(i0).distinctV1;
  String v2=this.listDR.get(i0).distinctV2;
  errorNewLine("Proving: "+provedTheorem1+" by applying rule:"+appliedRule1+
          " at distinct var.: "+variablesAndContent1+
          " require: $d "+v1+" "+v2+"  $.");
 }
}
//we return the base of demonstration
return mainDemonstrationItem;
}
public DemonstrationItem createDemonstration(ParsingItem theoremLabelItem)
{
DemonstrationItem mainDemonstrationItem=new DemonstrationItem();
DemonstrationItem currentDemonstrationItem=mainDemonstrationItem;

Theorem xTheorem=null;

if (theoremLabelItem!=null)
{
 if (theoremLabelItem.containedItems!=null)
{
if (theoremLabelItem.containedItems.size()>0)
{
if (theoremLabelItem.containedItems.get(0).codeSymbol==Source.C_P_SYMBOL)
{
    
    xTheorem=(Theorem)theoremLabelItem.generatedItem;
}
}
}
}

if (xTheorem!=null)
{
currentDemonstrationItem.name=xTheorem.name;
currentDemonstrationItem.type=DemonstrationConstants.TARGET;
currentDemonstrationItem.items=Source.copyTheListOfConstantAndVariable(xTheorem.items);

}

return mainDemonstrationItem;
}


public Context contextSalvage()
{

Context context0=new Context();
context0.savedConstants= new java.util.HashSet<String>();
context0.savedVariables= new java.util.HashSet<String>();
context0.variableAndTypeSaved= new java.util.HashMap<String, String>();
context0.distinctSaved=new java.util.ArrayList<java.util.List<String>>();
context0.blockHypothesesSaved=new ArrayList<Hypothesis>();

if (constants!=null)
{ 
if (!constants.isEmpty())
{
Iterator<String> it_c=constants.iterator();
if (it_c.hasNext())
{
do {
 context0.savedConstants.add(it_c.next());
} while (it_c.hasNext());
}
}
}


if (variables!=null)
{ 
if (!variables.isEmpty())
{
Iterator<String> it_v=variables.iterator();
if (it_v.hasNext())
{
do {
 context0.savedVariables.add(it_v.next());
} while (it_v.hasNext());
}
}
}

String key="",value="";
Entry<String,String> es=null;

if (variableAndType!=null)
{ 
    if (!variableAndType.isEmpty())
{
Iterator<Entry<String,String>> it_vt=variableAndType.entrySet().iterator();
if (it_vt.hasNext())
{
do {

 es=it_vt.next();
 key=es.getKey(); value=es.getValue();
 context0.variableAndTypeSaved.put(key,value);
} while (it_vt.hasNext());
}

}
}
if(distincts!=null)
{
 if(!distincts.isEmpty())
 {
   context0.distinctSaved=this.copyDistinct(distincts);
 }
}

if (blockHypotheses!=null)
{
if (!blockHypotheses.isEmpty())
{
for (int i=0;i<blockHypotheses.size();i++)
{
 context0.blockHypothesesSaved.add(blockHypotheses.get(i));
}
}
}

return context0;
}


public void contextRestoration( Set<String> savedConstants,
        Set<String> savedVariables, Map<String,String> savedVariableAndType,
        List<List<String>> savedDistincts,List<Hypothesis> savedBlockHypotheses)
{
//we empty values from context

if (constants!=null) constants.clear();
if (variables!=null)variables.clear();
if (variableAndType!=null) variableAndType.clear();
if (distincts!=null) distincts.clear();
if(blockHypotheses!=null) blockHypotheses.clear();


if (savedConstants!=null)
{
if (!savedConstants.isEmpty())
{
Iterator<String> it_c=savedConstants.iterator();
if (it_c.hasNext())
{
do {
 constants.add(it_c.next());
} while (it_c.hasNext());
}
}
}

if (savedVariables!=null)
{
if (!savedVariables.isEmpty())
{
Iterator<String> it_v=savedVariables.iterator();
if (it_v.hasNext())
{
do {
 variables.add(it_v.next());
} while (it_v.hasNext());
}
}
}

String key="",value="";
Entry<String,String> es=null;

if (savedVariableAndType!=null)
{         if (!savedVariableAndType.isEmpty())
{
Iterator<Entry<String,String>> it_vt=savedVariableAndType.entrySet().iterator();
if (it_vt.hasNext())
{
do {

 es=it_vt.next();
 key=es.getKey(); value=es.getValue();
 variableAndType.put(key,value);
} while (it_vt.hasNext());
}

}
}
if (savedDistincts!=null)
{
if (!savedDistincts.isEmpty())
{
    distincts=this.copyDistinct(savedDistincts);
}
}

if (savedBlockHypotheses!=null)
{
if (!savedBlockHypotheses.isEmpty())
{
for (int i=0;i<savedBlockHypotheses.size();i++)
{
 blockHypotheses.add(savedBlockHypotheses.get(i));

}
}
}

}



public List<Set<String>> copyStringSet(List<Set<String>> original)
{
List<Set<String>> copy= new ArrayList<Set<String>>();
Set<String>   originalSetItem=new HashSet<String>(),setItem2=new HashSet<String>();

Iterator<String>  iteratorOriginalSetItem=null;
if (original!=null)
{
if (!original.isEmpty())
{
Iterator <Set<String>> iteratorOriginal=original.iterator();
if (iteratorOriginal.hasNext())
{
 do {
      originalSetItem=iteratorOriginal.next();
      iteratorOriginalSetItem=originalSetItem.iterator();
       setItem2=new HashSet<String>();
       //now we copy each item of originalSetItem at setItem2
           if (originalSetItem!=null)
                   {
                         if (!originalSetItem.isEmpty())
                             {
                                   if (iteratorOriginalSetItem.hasNext())
                                      {
                                     do {
                                          setItem2.add(iteratorOriginalSetItem.next());
                                        } while (iteratorOriginalSetItem.hasNext());
                                      }
                             }
                    }
      //END copy
      //add setItem2 at list of sets
      copy.add(setItem2);
        setItem2=null;
    } while (iteratorOriginal.hasNext());
}
}
}

return copy;
}

public List<Hypothesis> copyListOfHypotheses(List<Hypothesis> original)
{
    //we copy a list of hypotheses
    List<Hypothesis> copy=new  ArrayList<Hypothesis>();
    int i=0;
    if (original!=null)
    {
    if(!original.isEmpty())
    {
     do
     {
         copy.add(original.get(i));
         i++;
     } while (i<original.size());
    }
    }

    return copy;
}
public List<List<String>> copyDistinct(List<List<String>> original)
{
    
    List<List<String>> copy=new java.util.ArrayList<java.util.List<String>>();
    int i=0;
    if (original!=null)
    {
    if(!original.isEmpty())
    {
     do
     {
         copy.add(original.get(i));
         i++;
     } while (i<original.size());
    }
    }

    return copy;
}

public ParsingItem parse()
{
ParsingItem  dataPreviousParent=new ParsingItem();

this.getSymbol();
//we memorize previous symbol
previousTypeSymbol=typeSymbol ;
previousFunctionSymbol=functionSymbol;
previousCodeSymbol=codeSymbol;
previousTextSymbol=textSymbol;
previousPositionSymbol=positionSymbol;
previousLengthSymbol=lengthSymbol;
previousErrorCodeSymbol=errorCodeSymbol;
previousNewLineSymbol=newLineSymbol;
//data items

this.dataRoot= new ParsingItem();
this.dataRoot.typeSymbol=Source.T_FATHER_FILE;
this.dataRoot.functionSymbol=Source.F_UNDEFINED;
this.dataRoot.codeSymbol=0;
this.dataRoot.textSymbol="Root:";
this.dataRoot.positionSymbol=0;
this.dataRoot.lengthSymbol=0;
this.dataRoot.errorCodeSymbol=0;
this.dataRoot.chapterItems=new ArrayList<ParsingItem>();

this.dataCurrentItem= new ParsingItem();
this.dataCurrentItem.typeSymbol=previousTypeSymbol;
this.dataCurrentItem.functionSymbol=previousFunctionSymbol;
this.dataCurrentItem.codeSymbol=previousCodeSymbol;
this.dataCurrentItem.textSymbol=previousTextSymbol;
this.dataCurrentItem.positionSymbol=previousPositionSymbol;
this.dataCurrentItem.lengthSymbol=previousLengthSymbol;
this.dataCurrentItem.errorCodeSymbol=previousErrorCodeSymbol;
this.dataCurrentItem.onNewLine=previousNewLineSymbol;


this.dataParentItem= this.dataRoot;
dataPreviousParent=this.dataRoot;

do
{
  if (stopParsing) return null;
//we try to see if is a scope block
this.comment();
if (stopParsing) return null;
this.inclusion();
if (stopParsing) return null;
this.block();
if (stopParsing) return null;
//if not we check one at a time
this.declareConstants();
if (stopParsing) return null;
this.declareVariables();
if (stopParsing) return null;
this.declareDistinctVariables();
if (stopParsing) return null;


if (this.acceptSymbol(Source.T_LABEL,Source.C_ANYOTHER_SYMBOL,Source.F_UNDEFINED))
{
    if (stopParsing) return null;
display("\n <"+previousTextSymbol+">: ");
//we capture the label
capturedItem=dataCurrentItem;

//
dataPreviousParent=this.dataParentItem;
this.addToDataNodeAndChangeNode();

//
this.axiom();
if (stopParsing) return null;
this.assumption();
if (stopParsing) return null;
this.theoremAndDemonstration();
if (stopParsing) return null;
this.declareTypeOfVariable();

//
this.changeDataNode(dataPreviousParent);

//
}
if (typeSymbol==Source.T_END)
            {
              break;
            }

} while (true);
try 
{
fin.close();
}
catch(IOException e)
{

JOptionPane.showMessageDialog
                    (null,
                     "Cannot close the file! "+e.getMessage(),
                     "Error!",
                     JOptionPane.INFORMATION_MESSAGE
                     );

//System.exit(-1);
}

//we check how many axioms and theorems we have
this.displayNewLine2("Axioms: "+axioms.size());
this.displayNewLine2("Theorems: "+theorems.size());
//this.displayNewLine2("No. of inactive theorems:"+this.numberOfInactiveTheorems);
this.displayNewLine2("Source name :"+this.name);

Application0 a= (Application0) Application.getInstance();
if(a!=null)
{
if(a.frame0!=null)
{
 if(!a.frame0.errorsString.equals(""))
 {
    JFrame mainFrame = Application0.getApplication().getMainFrame();

   Application0.getApplication().show(new ErrorsWindow(mainFrame,true));
 }
}
}


return this.dataRoot;

}

public boolean labelXIsBeforeY(String x,String y)
 {

//we verify the existence of a label before other,
//this is useful for demonstration tree construction

  boolean exist=false;

  if (labelAndOrder!=null)
  {
  if (labelAndOrder.size()>0)
  {
      int o1=0,o2=0;
      String stringOrder1="",stringOrder2="";
      if (x!=null)
      {
         if (this.labelAndOrder.get(x)!=null) stringOrder1=this.labelAndOrder.get(x);
      }
       if (y!=null)
      {
         if (this.labelAndOrder.get(y)!=null) stringOrder2=this.labelAndOrder.get(y);
      }

      if (stringOrder1!=null) o1=java.lang.Integer.parseInt(stringOrder1);
      if (stringOrder2!=null) o2=java.lang.Integer.parseInt(stringOrder2);

      if (o1-o2<0) exist=true;
      
  }
  }
  return exist;


 }
public void tieUpItemToTheChapter()
{
 boolean simpleComment=false,somethingElseSimple=false;
 
if (this.dataCurrentItem!=null)
{
//if is a comment
if(this.dataCurrentItem.typeSymbol==Source.T_COMMENT)
{
//we have a superchapter
if(this.dataCurrentItem.functionSymbol==Source.F_SUPERCHAPTER_COMMENT)
{
if (this.dataRoot.chapterItems!=null)
{
//we add to the root
this.dataRoot.chapterItems.add(this.dataCurrentItem);
//tie up current item to the root
this.dataCurrentItem.fatherChapter=this.dataRoot;
//we make a new list of subordinated chapters
this.dataCurrentItem.chapterItems=new ArrayList<ParsingItem>();
//we mark the new chapter
this.commentSuperchapter=this.dataCurrentItem;
}
}

//we have a chapter
else if(this.dataCurrentItem.functionSymbol == Source.F_CHAPTER_COMMENT)
{
if (this.commentSuperchapter!=null)
{
if (this.commentSuperchapter.chapterItems!=null)
{
//we add to the superchapter
this.commentSuperchapter.chapterItems.add(this.dataCurrentItem);
//we tie up the current item to the superchapter
this.dataCurrentItem.fatherChapter=this.commentSuperchapter;
//we make a new list of subordinated chapters
this.dataCurrentItem.chapterItems=new ArrayList<ParsingItem>();
//we mark the new chapter
this.commentChapter=this.dataCurrentItem;
}
}
 else if (this.commentSuperchapter==null)
{
 if (this.dataRoot.chapterItems!=null)
{
//we add to the root
this.dataRoot.chapterItems.add(this.dataCurrentItem);
//we tie up the current item to the root
this.dataCurrentItem.fatherChapter=this.dataRoot;
//we make a new list of subordinated chapters
this.dataCurrentItem.chapterItems=new ArrayList<ParsingItem>();
//we mark the new chapter
this.commentChapter=this.dataCurrentItem;
}
}

}

//we have a subchapter
else if(this.dataCurrentItem.functionSymbol==Source.F_SUBCHAPTER_COMMENT)
{
if ((this.commentSuperchapter!=null)&(this.commentChapter!=null))
{
if (this.commentChapter.chapterItems!=null)
{
//we add to the chapter
this.commentChapter.chapterItems.add(this.dataCurrentItem);
//we tie up the current item to the chapter
this.dataCurrentItem.fatherChapter=this.commentChapter;
//we make a new list of subordinated chapters
this.dataCurrentItem.chapterItems=new ArrayList<ParsingItem>();
//we mark the new subchapter
this.commentSubchapter=this.dataCurrentItem;
}
}
else if ((this.commentSuperchapter!=null)&(this.commentChapter==null))
{
if (this.commentSuperchapter.chapterItems!=null)
{
//we add to the superchapter
this.commentSuperchapter.chapterItems.add(this.dataCurrentItem);
//we tie up the current item to the superchapter
this.dataCurrentItem.fatherChapter=this.commentSuperchapter;
//we make a new list of subordinated chapters
this.dataCurrentItem.chapterItems=new ArrayList<ParsingItem>();
//we mark the new subchapter
this.commentSubchapter=this.dataCurrentItem;
}
}
else if ((this.commentSuperchapter==null)&(this.commentChapter!=null))
{
 if (this.commentChapter.chapterItems!=null)
{
//we add to the chapter
this.commentChapter.chapterItems.add(this.dataCurrentItem);
//we tie up the current item to the chapter
this.dataCurrentItem.fatherChapter=this.commentChapter;
//we make a new list of subordinated chapters
this.dataCurrentItem.chapterItems=new ArrayList<ParsingItem>();
//we mark the new subchapter
this.commentSubchapter=this.dataCurrentItem;
}
}
else if ((this.commentSuperchapter==null)&(this.commentChapter==null))
{
if (this.dataRoot.chapterItems!=null)
{
//add to the root
this.dataRoot.chapterItems.add(this.dataCurrentItem);
//we tie up the current item to the root
this.dataCurrentItem.fatherChapter=this.dataRoot;
//we make a new list of subordinated chapters
this.dataCurrentItem.chapterItems=new ArrayList<ParsingItem>();
//we mark the new chapter
this.commentSubchapter=this.dataCurrentItem;
}
}

}
//we have a normal or date comment
else if ((this.dataCurrentItem.functionSymbol==Source.F_NORMAL_COMMENT)||
    (this.dataCurrentItem.functionSymbol==Source.F_DATE_COMMENT))
{
 simpleComment=true;

}
else
{
    simpleComment=true;
}

//END comment
}
//something else than comment
else
{
    somethingElseSimple=true;
}

//we process the normal comments or of date type or other types of items
if (simpleComment||somethingElseSimple)
{

if(commentSuperchapter!=null)
{
if ((this.commentChapter!=null)&(this.commentSubchapter!=null))
{
if (this.commentSubchapter.chapterItems!=null)
{
 //we add to the current subchapter
this.commentSubchapter.chapterItems.add(this.dataCurrentItem);
//we tie up the current item to the current chapter
this.dataCurrentItem.fatherChapter=this.commentSubchapter;
}
}
else if ((this.commentChapter!=null)&(this.commentSubchapter==null))
{
if (this.commentChapter.chapterItems!=null)
{
 //we add to the current chapter
this.commentChapter.chapterItems.add(this.dataCurrentItem);
//we tie up the current item to the current chapter
this.dataCurrentItem.fatherChapter=this.commentChapter;
}
}
else if ((this.commentChapter==null)&(this.commentSubchapter!=null))
{
if (this.commentSubchapter.chapterItems!=null)
{
 //we add to the current subchapter
this.commentSubchapter.chapterItems.add(this.dataCurrentItem);
//we tie up the current item to the current subchapter
this.dataCurrentItem.fatherChapter=this.commentSubchapter;
}
}
else if ((this.commentChapter==null)&(this.commentSubchapter==null))
{
if (this.commentSuperchapter.chapterItems!=null)
{
//we add to the superchapter
this.commentSuperchapter.chapterItems.add(this.dataCurrentItem);
//we tie up the current item to the superchapter
this.dataCurrentItem.fatherChapter=this.commentSuperchapter;
}
}
}


 else if(commentSuperchapter==null)
 {
  if ((this.commentChapter!=null)&(this.commentSubchapter!=null))
{
if (this.commentSubchapter.chapterItems!=null)
{
 //we add to the current subchapter
this.commentSubchapter.chapterItems.add(this.dataCurrentItem);
//we tie up the current item to the current chapter
this.dataCurrentItem.fatherChapter=this.commentSubchapter;
}
}
else if ((this.commentChapter!=null)&(this.commentSubchapter==null))
{
if (this.commentChapter.chapterItems!=null)
{
 //we add to the current chapter
this.commentChapter.chapterItems.add(this.dataCurrentItem);
//we tie up the current item to the current chapter
this.dataCurrentItem.fatherChapter=this.commentChapter;
}
}
else if ((this.commentChapter==null)&(this.commentSubchapter!=null))
{
if (this.commentSubchapter.chapterItems!=null)
{
 //we add to the current subchapter
this.commentSubchapter.chapterItems.add(this.dataCurrentItem);
//we tie up the current item to the current subchapter
this.dataCurrentItem.fatherChapter=this.commentSubchapter;
}
}
else if ((this.commentChapter==null)&(this.commentSubchapter==null))
{
if (this.dataRoot.chapterItems!=null)
{
//we add to the root
this.dataRoot.chapterItems.add(this.dataCurrentItem);
//we tie up the current item to the root
this.dataCurrentItem.fatherChapter=this.dataRoot;
}
}
 }

}


//END different from null
}
    
}
 public void generateDistinctRequirements(List<ConstantAndVariable> v1,
                                                  List<ConstantAndVariable> v2)
{
int numberOfVar1=0,numberOfVar2=0;
List<String> listVar1=new ArrayList<String>(), listVar2=new ArrayList<String>();
int d1=0,d2=0;
d1=v1.size();
d2=v2.size();

//we we gather the variable in a list
for (int i=0;i<d1;i++)
{
if (v1.get(i).constantOrVariable==2)
{
   listVar1.add(v1.get(i).constantOrVariableText);
}
}


for (int j=0;j<d2;j++)
{
if (v2.get(j).constantOrVariable==2)
{
   listVar2.add(v2.get(j).constantOrVariableText);
}
}
numberOfVar1=listVar1.size();
numberOfVar2=listVar2.size();

if ((numberOfVar1>0)&(numberOfVar2>0))
{
 if(numberOfVar1==1)
 {
  for(int i=0;i<numberOfVar2;i++)
  {
   //System.out.println("$d "+listVar1.get(0)+" "+listVar2.get(i)+" $.");
   DistinctRequirement dr=new DistinctRequirement(
           provedTheorem1.substring(0),
           appliedRule1.substring(0),
           variablesAndContent1.substring(0),
           listVar1.get(0).substring(0),
           listVar2.get(i).substring(0)
           );
   this.listDR.add(dr);
  
  }

 }
 else if(numberOfVar2==1)
 {
    for(int j=0;j<numberOfVar1;j++)
  {
   //System.out.println("$d "+listVar2.get(0)+" "+listVar1.get(j)+" $.");
    DistinctRequirement dr=new DistinctRequirement(
           provedTheorem1.substring(0),
           appliedRule1.substring(0),
           variablesAndContent1.substring(0),
           listVar2.get(0).substring(0),
           listVar1.get(j).substring(0)
           );
    this.listDR.add(dr);
  }
 }
 
}

}

static public boolean distinctVariables(List<ConstantAndVariable> v1,List<ConstantAndVariable> v2)
{
    boolean ok=true;
    int n1=v1.size(),n2=v2.size();
    if ((n1>0)&(n2>0))
    {
     if(n1==n2)
     {
         if(Source.isEqualXWithY(v1, v2)){ok=false;}
     }
      else if(n1<n2)
      {
          boolean ok2=true;
         int dif=n2-n1;
         for(int i=0;i<dif+1;i++)
         {
          ok2=true;
          for(int j=0;j<n1;j++)
          {
              if(!(v1.get(j).constantOrVariableText.
                                        equals(v2.get(i+j).constantOrVariableText)))
              {
                  ok2=false;
                  break;
              }
          }
          if(ok2){ break; }

         }
       if(ok2){ok=false;}
      }
     
     else if(n2<n1)
      {
         boolean ok2=true;
         int dif=n1-n2;
         for(int i=0;i<dif+1;i++)
         {
          ok2=true;
          for(int j=0;j<n2;j++)
          {
              if(!(v2.get(j).constantOrVariableText.
                                       equals(v1.get(i+j).constantOrVariableText)))
              {
                  ok2=false;
                  break;
              }
          }
          if(ok2){ break; }

         }
       if(ok2){ok=false;}
      }


    }
    
    return ok;
}
static public String showVariableContent(List<ConstantAndVariable> variableContent)
{
    String s="";
    int d=variableContent.size();
    for(int i=0;i<d;i++)
    {
     s=s+" "+variableContent.get(i).constantOrVariableText;
    }

    return s;
}
 public boolean checkVariables(List<String> variablesList,
           Map<String,List<ConstantAndVariable>> variableContent)
{
    //here we check the variables from a $d $. if they are distinct
    boolean ok=true, ok2;
    //variablesList = list of distinct variables
    int n=variablesList.size();
    if(n>1)
    {
    for(int i=0;i<n-1;i++)
    {
        for(int j=1;j<n-i;j++)
        {
          String varI=variablesList.get(i);
          String varIplusJ=variablesList.get(i+j);
         List<ConstantAndVariable> variableContent1=variableContent.get(variablesList.get(i)),
                      variableContent2=variableContent.get(variablesList.get(i+j));
         if(variableContent1!=null)
         {
         if(variableContent2!=null)
         {
          //here we memorize the distinct variables and their content
           variablesAndContent1= varI+" is:"+
                                 Source.showVariableContent(variableContent1)
                   +" [and] "+varIplusJ+" is:"+
                                 Source.showVariableContent(variableContent2);
          this.generateDistinctRequirements(variableContent1,
                                                              variableContent2);
          ok2=Source.distinctVariables(variableContent1,variableContent2);
          if (!ok2){ Source.errorNewLine("Distinct variable violation at: "
                  +varI+" and "+varIplusJ);
                   Source.errorNewLine(varI+" is:"+
                                 Source.showVariableContent(variableContent1));
                   Source.errorNewLine(varIplusJ+" is:"+
                                 Source.showVariableContent(variableContent2));
                   ok=false;
                  }
         }
         }
        
        }
    
    }
    }
    return ok;
}
public void checkDistinctReq(List<String> variablesList,
                                             List<DistinctRequirement> listDR0)
{
    //here we check if distinct requirements are already defined
    
    //variablesList = list of distinct variables
    int n=variablesList.size();
    List<DistinctRequirement> toRemove=new ArrayList<DistinctRequirement>();
    if(n>1)
    {
    for(int i=0;i<n-1;i++)
    {
        for(int j=1;j<n-i;j++)
        {
          String varI=variablesList.get(i);
          String varIplusJ=variablesList.get(i+j);
          int d=listDR0.size();
          toRemove.clear();
          for(int i2=0;i2<d;i2++)
          {
            String v1=listDR0.get(i2).distinctV1;
            String v2=listDR0.get(i2).distinctV2;

            if(
               ((varI.equals(v1))&(varIplusJ.equals(v2)))|
               ((varI.equals(v2))&(varIplusJ.equals(v1)))
              )
            {
                //System.out.println("Remove: "+v1+" / "+v2);
                DistinctRequirement dri2=listDR0.get(i2);
                toRemove.add(dri2);
                
            }
            
          }
          listDR0.removeAll(toRemove);



        }

    }
    }
  
}
static public boolean stringStartsWith(String s,String beginning)
{
   boolean ok=true;
   if (s!=null)
   {
    ok=s.startsWith(beginning);
   }
   else ok=false;
   
   return ok;
}

static public boolean stringEqualWithString(String s1,String s2)
{
   boolean ok=true;
   if (s1!=null)
   {
    if(s2!=null)
    {
     ok=s1.equals(s2);
    }
    else
    ok=false;
   }
   else ok=false;

   return ok;
}
public String displayCV(List<ConstantAndVariable> list)
 {
    String res="";
    if(list!=null)
    {
    int max=list.size();
    for(int i=0;i<max;i++)
    {
    res=res+" "+list.get(i).constantOrVariableText;    
    }
    
    }
   return res; 
 }
public void displaySyntax(SyntacticItem syn)
{

 if (syn!=null)
 {
   System.out.print(" { ");
     if (syn.containedItems!=null)
     {
       if(!syn.containedItems.isEmpty())
       {
           int max=syn.containedItems.size();
           for(int i=0;i<max;i++)
           {
             displaySyntax(syn.containedItems.get(i));
           }

       }
     }

  System.out.print(" ["+syn.hub
          +"|"+syn.repetition
          +"|"+syn.repetitionNumber
          +":"+syn.definitionName+"] } ");


 }


}


};