//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv;

import java.util.List;


public class SourceComment {
   
 //data declaration

 //the input stream of the comment
 String comment;
 
 //the processing module of the symbol
 public static byte M_COMMENT_NAME_MM=1,M_COMMENT_DATE=2,M_COMMENT_EXACT_TEXT=3,
         M_COMMENT_DIFFERENT_FROM=4,M_COMMENT_DIFFERENT_FROM_NAME_MM=5,M_COMMENT_DIFFERENT_FROM_DATE=6;
 //constants
public static String SUPERCHAPTER_TEXT="##########################################"
                                    + "#####################################";
public static String CHAPTER_TEXT="#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*"
                              + "#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#";
public static String SUBCHAPTER_TEXT="=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
                                 + "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";
public static String APOSTROPHE_TEXT="'";
public static String GRAVE_APOSTROPHE_TEXT="`";
public static String QUOTATION_MARK_TEXT=""+'"';
 
int positionInComment=0;//position in the comment...

 boolean endComment=false;//end of the comment
 
 //symbol variable
 byte typeSymbol;
 byte functionSymbol;
 byte codeSymbol;
 String textSymbol;
 int positionSymbol;
 int lengthSymbol;
 byte errorCodeSymbol;

 //variables for tree construction

 ParsingItem dataRoot=new ParsingItem();
 ParsingItem dataParentItem=new ParsingItem();
 ParsingItem dataCurrentItem=new ParsingItem();

 Source S=null;

//new variables
int initialPosition=0,finalPosition=0,position1=0;
int initialPosition2=0,finalPosition2=0;//we will memorize initial and final position for a possible verification
boolean haveApostrophe=false,contentNodeCreated=false;
String s="";
//s1 as s2
String s1="",s2="";
//END new variables

int [] positions= new int[3];//positions in the comment
int positionCursor=0;
public SourceComment(Source S)
{   

  this.S=S;
  comment="This is a comment!";
        

};

protected void addToDataNodeAndChangeNode()
{
if (this.dataParentItem.containedItems==null)
        {
        List<ParsingItem> ls=new java.util.ArrayList<ParsingItem>();
        this.dataParentItem.containedItems=ls;
        }
   this.dataParentItem.containedItems.add(this.dataCurrentItem);
         //links
            this.dataCurrentItem.fatherItem=this.dataParentItem;
         //END link
        this.dataParentItem=this.dataCurrentItem;

};

    protected void addToDataNode()
    {
     //data,tree
        if (this.dataParentItem.containedItems==null)
        {
        List<ParsingItem> ls=new java.util.ArrayList<ParsingItem>();
        this.dataParentItem.containedItems=ls;
        }
     this.dataParentItem.containedItems.add(this.dataCurrentItem);
      //links
     this.dataCurrentItem.fatherItem=this.dataParentItem;
     //END links
     
     //END
    };

    protected void changeDataNode(ParsingItem previousData )
    {
       this.dataParentItem=previousData;
    };

public void getSymbol()
{
//data items
this.dataCurrentItem= new ParsingItem();
this.dataCurrentItem.typeSymbol=typeSymbol;
this.dataCurrentItem.functionSymbol=functionSymbol;
this.dataCurrentItem.codeSymbol=codeSymbol;
this.dataCurrentItem.textSymbol=textSymbol;
this.dataCurrentItem.positionSymbol=positionSymbol;
this.dataCurrentItem.lengthSymbol=lengthSymbol;
this.dataCurrentItem.errorCodeSymbol=errorCodeSymbol;

byte buffer=0;
int number=-1;
boolean buildLabelOrSymbol=false;
int initialPositionLabelOrSymbol=-1;
String labelOrSymbolBuilt="";

boolean exitFromDo=false;
do {


if (this.positionInComment>=comment.length()) number=-1;
else
 {
 buffer=(byte)comment.charAt(this.positionInComment);
 number=0;
 }

if (number!=-1)
{

if(buffer=='`' )
        {
           if(!buildLabelOrSymbol)
         {
         //character `
         typeSymbol=Source.T_KEY_SYMBOL;
         codeSymbol=Source.C_ANYOTHER_SYMBOL;
         textSymbol="`";
         positionSymbol=positionInComment;
         lengthSymbol=1;
         positionInComment=positionInComment+1;
         exitFromDo=true;
         break;
         }
     else if (buildLabelOrSymbol)
      {
         /*I do not increment positionInComment
          it will pass next time on the same position */
        typeSymbol=Source.T_LABEL_OR_SYMBOL;
        codeSymbol=Source.C_ANYOTHER_SYMBOL;
        textSymbol=labelOrSymbolBuilt;
        positionSymbol=initialPositionLabelOrSymbol;
        lengthSymbol=1+positionInComment-initialPositionLabelOrSymbol;

        exitFromDo=true;
        break;
        }

        }
    else if(buffer == 39)
        {
           if(!buildLabelOrSymbol)
         {
            //character '
         typeSymbol=Source.T_KEY_SYMBOL;
         codeSymbol=Source.C_ANYOTHER_SYMBOL;
         textSymbol="'";
         positionSymbol=positionInComment;
         lengthSymbol=1;
         positionInComment=positionInComment+1;
         exitFromDo=true;
         break;
         }
     else if (buildLabelOrSymbol)
      {
        /*I do not increment positionInComment
          it will pass next time on the same position */
        typeSymbol=Source.T_LABEL_OR_SYMBOL;
        codeSymbol=Source.C_ANYOTHER_SYMBOL;
        textSymbol=labelOrSymbolBuilt;
        positionSymbol=initialPositionLabelOrSymbol;
        lengthSymbol=1+positionInComment-initialPositionLabelOrSymbol;

        exitFromDo=true;
        break;
        }

        }
    else if(buffer == 34)
        {
        
        if (!buildLabelOrSymbol)
         {
          //character "
         typeSymbol=Source.T_KEY_SYMBOL;
         codeSymbol=Source.C_ANYOTHER_SYMBOL;
         textSymbol=""+'"';
         positionSymbol=positionInComment;
         lengthSymbol=1;
         positionInComment=positionInComment+1;
         exitFromDo=true;
         break;
         }
        else if (buildLabelOrSymbol)
        {
        /*I do not increment positionInComment
          it will pass next time on the same position */
        typeSymbol=Source.T_LABEL_OR_SYMBOL;
        codeSymbol=Source.C_ANYOTHER_SYMBOL;
        textSymbol=labelOrSymbolBuilt;
        positionSymbol=initialPositionLabelOrSymbol;
        lengthSymbol=1+positionInComment-initialPositionLabelOrSymbol;

        exitFromDo=true;
        break;
        }
        }
else if(buffer == ';')
        {
          if(!buildLabelOrSymbol)
          {
          //character ;
         typeSymbol=Source.T_KEY_SYMBOL;
         codeSymbol=Source.C_ANYOTHER_SYMBOL;
         textSymbol=";";
         positionSymbol=positionInComment;
         lengthSymbol=1;
         positionInComment=positionInComment+1;
         exitFromDo=true;
         break;
         }
         else if (buildLabelOrSymbol)
        {
        /*I do not increment positionInComment
          it will pass next time on the same position */
        typeSymbol=Source.T_LABEL_OR_SYMBOL;
        codeSymbol=Source.C_ANYOTHER_SYMBOL;
        textSymbol=labelOrSymbolBuilt;
        positionSymbol=initialPositionLabelOrSymbol;
        lengthSymbol=1+positionInComment-initialPositionLabelOrSymbol;

        exitFromDo=true;
        break;
        }

        }

else if(buffer == '+')
        {
         if(!buildLabelOrSymbol)
         {
          //character +
         typeSymbol=Source.T_KEY_SYMBOL;
         codeSymbol=Source.C_ANYOTHER_SYMBOL;
         textSymbol="+";
         positionSymbol=positionInComment;
         lengthSymbol=1;
         positionInComment=positionInComment+1;
         exitFromDo=true;
         break;
         }
         else if (buildLabelOrSymbol)
        {
        /*I do not increment positionInComment
          it will pass next time on the same position */
        typeSymbol=Source.T_LABEL_OR_SYMBOL;
        codeSymbol=Source.C_ANYOTHER_SYMBOL;
        textSymbol=labelOrSymbolBuilt;
        positionSymbol=initialPositionLabelOrSymbol;
        lengthSymbol=1+positionInComment-initialPositionLabelOrSymbol;

        exitFromDo=true;
        break;
        }
        }
else if(buffer == '$')
        {
         positionInComment++;//we increment position
         if (this.positionInComment>=comment.length()) number=-1;
         else
            {
               buffer=(byte)comment.charAt(this.positionInComment);
               number=0;
             }

          if (number!=-1)
          {
          if (buffer=='t')
          {
         typeSymbol=Source.T_KEY_SYMBOL;
         codeSymbol=Source.C_ANYOTHER_SYMBOL;
         textSymbol="$t";
         positionSymbol=positionInComment-1;//the position before incrementing
         lengthSymbol=2;
         positionInComment++;
         exitFromDo=true;
         break;
          }
            else
         {
          typeSymbol=Source.T_KEY_SYMBOL;
         codeSymbol=Source.C_ANYOTHER_SYMBOL;
         textSymbol="$"+buffer;
         positionSymbol=positionInComment-1;
         lengthSymbol=2;
         positionInComment++;
         exitFromDo=true;
         break;
         }

          }
          else
          {
             //end of comment
             typeSymbol=Source.T_END; this.endComment=true;
             codeSymbol=Source.C_ANYOTHER_SYMBOL;
             functionSymbol=Source.F_UNDEFINED;
             break;
          }
        //END $t
        }
else if(buffer == '/')
        {
          if (positionInComment+7<this.comment.length())
         {
          boolean onlyStars=true;
             for(int i1=1;i1<7;i1++)
             {
                if (this.comment.charAt(positionInComment+i1)!='*') onlyStars=false;
             }
          if (!onlyStars)
           {
              if(this.comment.charAt(positionInComment+1)=='*')
              {
               if (!buildLabelOrSymbol)
               {
                typeSymbol=Source.T_KEY_SYMBOL;
                codeSymbol=Source.C_ANYOTHER_SYMBOL;
                textSymbol="/*";
                positionSymbol=positionInComment;
                lengthSymbol=2;
                positionInComment=positionInComment+2;
                exitFromDo=true;
                break;
               }
                else
                if (buildLabelOrSymbol)
                {
                /*I do not increment positionInComment
                 it will pass next time on the same position */
                typeSymbol=Source.T_LABEL_OR_SYMBOL;
                codeSymbol=Source.C_ANYOTHER_SYMBOL;
                textSymbol=labelOrSymbolBuilt;
                positionSymbol=initialPositionLabelOrSymbol;
                lengthSymbol=1+positionInComment-initialPositionLabelOrSymbol;

                exitFromDo=true;
                break;
                }

              }
               else 
               {
                if (buildLabelOrSymbol==false)
                  {
                   buildLabelOrSymbol=true;
                   initialPositionLabelOrSymbol=positionInComment+1;
                  }

                char character=(char)buffer;
                labelOrSymbolBuilt=labelOrSymbolBuilt+character ;
                positionInComment=positionInComment+1;
               }
           }

          if (onlyStars)
         {
               if (!buildLabelOrSymbol)
               {
                typeSymbol=Source.T_KEY_SYMBOL;
                codeSymbol=Source.C_ANYOTHER_SYMBOL;
                textSymbol="/*******";
                positionSymbol=positionInComment;
                lengthSymbol=8;
                positionInComment=positionInComment+8;
                exitFromDo=true;
                break;
               }
           else 
                if (buildLabelOrSymbol)
                {
                /*I do not increment positionInComment
                 it will pass next time on the same position */
                typeSymbol=Source.T_LABEL_OR_SYMBOL;
                codeSymbol=Source.C_ANYOTHER_SYMBOL;
                textSymbol=labelOrSymbolBuilt;
                positionSymbol=initialPositionLabelOrSymbol;
                lengthSymbol=1+positionInComment-initialPositionLabelOrSymbol;

                exitFromDo=true;
                break;
                }
         }

         }
        else if (positionInComment+1<this.comment.length())
         {
            if(this.comment.charAt(positionInComment+1)=='*')
              {
                typeSymbol=Source.T_KEY_SYMBOL;
                codeSymbol=Source.C_ANYOTHER_SYMBOL;
                textSymbol="/*";
                positionSymbol=positionInComment;
                lengthSymbol=2;
                positionInComment=positionInComment+2;
                exitFromDo=true;
                break;
              }
               else 
               {
                if (buildLabelOrSymbol==false)
                  {
                   buildLabelOrSymbol=true;
                   initialPositionLabelOrSymbol=positionInComment+1;
                  }

                char caracter=(char)buffer;
                labelOrSymbolBuilt=labelOrSymbolBuilt+caracter ;
                positionInComment=positionInComment+1;

               }
         }
         
             
    
         //END /*
        }
else if(buffer == '*')
        {
          if (positionInComment+7<this.comment.length())
         {
          boolean onlyStars=true;
             for(int i1=1;i1<6;i1++)
             {
                if (this.comment.charAt(positionInComment+i1)!='*') onlyStars=false;
             }
          if (this.comment.charAt(positionInComment+7)!='/') onlyStars=false;
          
          if (!onlyStars)
           {
              if(this.comment.charAt(positionInComment+1)=='/')
              {
               if (!buildLabelOrSymbol)
               {
                typeSymbol=Source.T_KEY_SYMBOL;
                codeSymbol=Source.C_ANYOTHER_SYMBOL;
                textSymbol="*/";
                positionSymbol=positionInComment;
                lengthSymbol=2;
                positionInComment=positionInComment+2;
                exitFromDo=true;
                break;
               }
                else
                if (buildLabelOrSymbol)
                {
                /*I do not increment positionInComment
                  it will pass next time on the same position */
                typeSymbol=Source.T_LABEL_OR_SYMBOL;
                codeSymbol=Source.C_ANYOTHER_SYMBOL;
                textSymbol=labelOrSymbolBuilt;
                positionSymbol=initialPositionLabelOrSymbol;
                lengthSymbol=1+positionInComment-initialPositionLabelOrSymbol;

                exitFromDo=true;
                break;
                }

              }
               else
               {
                if (buildLabelOrSymbol==false)
                  {
                   buildLabelOrSymbol=true;
                   initialPositionLabelOrSymbol=positionInComment+1;
                  }

                char caracter=(char)buffer;
                labelOrSymbolBuilt=labelOrSymbolBuilt+caracter ;
                positionInComment=positionInComment+1;
               }
           }

          if (onlyStars)
         {
               if (!buildLabelOrSymbol)
               {
                typeSymbol=Source.T_KEY_SYMBOL;
                codeSymbol=Source.C_ANYOTHER_SYMBOL;
                textSymbol="*******/";
                positionSymbol=positionInComment;
                lengthSymbol=8;
                positionInComment=positionInComment+8;
                exitFromDo=true;
                break;
               }
           else
                if (buildLabelOrSymbol)
                {
                /*I do not increment positionInComment
                 it will pass next time on the same position */
                typeSymbol=Source.T_LABEL_OR_SYMBOL;
                codeSymbol=Source.C_ANYOTHER_SYMBOL;
                textSymbol=labelOrSymbolBuilt;
                positionSymbol=initialPositionLabelOrSymbol;
                lengthSymbol=1+positionInComment-initialPositionLabelOrSymbol;

                exitFromDo=true;
                break;
                }
         }

         }
        else if (positionInComment+1<this.comment.length())
         {
            if(this.comment.charAt(positionInComment+1)=='/')
              {
                typeSymbol=Source.T_KEY_SYMBOL;
                codeSymbol=Source.C_ANYOTHER_SYMBOL;
                textSymbol="*/";
                positionSymbol=positionInComment;
                lengthSymbol=2;
                positionInComment=positionInComment+2;
                exitFromDo=true;
                break;
              }
               else
               {
                if (buildLabelOrSymbol==false)
                  {
                   buildLabelOrSymbol=true;
                   initialPositionLabelOrSymbol=positionInComment+1;
                  }

                char caracter=(char)buffer;
                labelOrSymbolBuilt=labelOrSymbolBuilt+caracter ;
                positionInComment=positionInComment+1;

               }
         }



         //END /*
        }

//if we have space,new line,tab and so on other than character $
 else if ((buffer==32 || buffer==13 ||
  buffer==10 )&& buildLabelOrSymbol==false)
                    {positionInComment=positionInComment+1; }
//if we have a character different from space,new line,tab and so on
else if (buffer!=32 && buffer!=13
         &&  buffer!=10
         )
  //we start construction of a label or a symbol
{
  if (buildLabelOrSymbol==false)
  {
   buildLabelOrSymbol=true;
   initialPositionLabelOrSymbol=positionInComment+1;
  }

char caracter=(char)buffer;
labelOrSymbolBuilt=labelOrSymbolBuilt+caracter ;
positionInComment=positionInComment+1;

}

//the end of construction of label or symbol
else if ((buffer==32 || buffer==13 || buffer==10)
                     && buildLabelOrSymbol==true)
      {
        positionInComment=positionInComment+1;
        typeSymbol=Source.T_LABEL_OR_SYMBOL;
        codeSymbol=Source.C_ANYOTHER_SYMBOL;
        textSymbol=labelOrSymbolBuilt;
        positionSymbol=initialPositionLabelOrSymbol;
        lengthSymbol=positionInComment-initialPositionLabelOrSymbol;

        exitFromDo=true;
        break;
       }

}
else  {
//could not read some character from outside of comment
typeSymbol=Source.T_END; this.endComment=true;
codeSymbol=Source.C_ANYOTHER_SYMBOL;
functionSymbol=Source.F_UNDEFINED;
break;
}
if (exitFromDo==true){ break;}
} while(true);


}

public void errorNewLine( String msg)
{
   System.out.println(msg);
  
};

//displayed information used to build the software

public void display2( String msg)
{
  //System.out.println(msg);
};

//functions II

public static boolean dateDay2DigitsMonthYear2Or4Digits(String string)
{
    boolean accept=true;
//if we have less than 9 character we exit
if(string.length()<9) return false;
if (string.length()>=9)
{
//we check if on position 0 we have digit
 if(
   (string.charAt(0)!='0')&
   (string.charAt(0)!='1')&
   (string.charAt(0)!='2')&
   (string.charAt(0)!='3')&
   (string.charAt(0)!='4')&
   (string.charAt(0)!='5')&
   (string.charAt(0)!='6')&
   (string.charAt(0)!='7')&
   (string.charAt(0)!='8')&
   (string.charAt(0)!='9')
   )
 {accept=false;
 }
 //we check if on position 1 we have digit
 if(
   (string.charAt(1)!='0')&
   (string.charAt(1)!='1')&
   (string.charAt(1)!='2')&
   (string.charAt(1)!='3')&
   (string.charAt(1)!='4')&
   (string.charAt(1)!='5')&
   (string.charAt(1)!='6')&
   (string.charAt(1)!='7')&
   (string.charAt(1)!='8')&
   (string.charAt(1)!='9')
   )
 {accept=false;
 }
  //we check if on position 2 we have digit
 if(string.charAt(2)!='-')
 {
     accept = false;
 }
//we check if on position 3,4,5 we have next sequences
 if(
   (!string.startsWith("Ian",3))&
   (!string.startsWith("Feb",3))&
   (!string.startsWith("Mar",3))&
   (!string.startsWith("Apr",3))&
   (!string.startsWith("May",3))&
   (!string.startsWith("Jun",3))&
   (!string.startsWith("Jul",3))&
   (!string.startsWith("Aug",3))&
   (!string.startsWith("Sep",3))&
   (!string.startsWith("Oct",3))&
   (!string.startsWith("Nov",3))&
   (!string.startsWith("Dec",3))
   )
 {accept=false;
 }
  //we check if on position 6 we have line
 if(string.charAt(6)!='-')
 {
     accept = false;
 }

 //we check if on position 7,8,9,10 we have digits
 if(
   (string.charAt(7)!='0')&
   (string.charAt(7)!='1')&
   (string.charAt(7)!='2')&
   (string.charAt(7)!='3')&
   (string.charAt(7)!='4')&
   (string.charAt(7)!='5')&
   (string.charAt(7)!='6')&
   (string.charAt(7)!='7')&
   (string.charAt(7)!='8')&
   (string.charAt(7)!='9')
   )
 {accept=false;
 }

 if(
   (string.charAt(8)!='0')&
   (string.charAt(8)!='1')&
   (string.charAt(8)!='2')&
   (string.charAt(8)!='3')&
   (string.charAt(8)!='4')&
   (string.charAt(8)!='5')&
   (string.charAt(8)!='6')&
   (string.charAt(8)!='7')&
   (string.charAt(8)!='8')&
   (string.charAt(8)!='9')
   )
 {accept=false;
 }
}
 //until here we have 9 characters
if (string.length()==11)
{
 if(
   (string.charAt(9)!='0')&
   (string.charAt(9)!='1')&
   (string.charAt(9)!='2')&
   (string.charAt(9)!='3')&
   (string.charAt(9)!='4')&
   (string.charAt(9)!='5')&
   (string.charAt(9)!='6')&
   (string.charAt(9)!='7')&
   (string.charAt(9)!='8')&
   (string.charAt(9)!='9')
   )
 {accept=false;
 }

 if(
   (string.charAt(10)!='0')&
   (string.charAt(10)!='1')&
   (string.charAt(10)!='2')&
   (string.charAt(10)!='3')&
   (string.charAt(10)!='4')&
   (string.charAt(10)!='5')&
   (string.charAt(10)!='6')&
   (string.charAt(10)!='7')&
   (string.charAt(10)!='8')&
   (string.charAt(10)!='9')
   )
 {accept=false;
 }
 }
    return accept;
}

public static boolean dateDay1DigitMonthYear2Or4Digits(String string)
{
    boolean accepta=true;
//if we have less than 8 characters we exit
if(string.length()<8) return false;
if (string.length()>=8)
{
    //we check if on position 0 we have a digit different from zero
 if(
   
   (string.charAt(0)!='1')&
   (string.charAt(0)!='2')&
   (string.charAt(0)!='3')&
   (string.charAt(0)!='4')&
   (string.charAt(0)!='5')&
   (string.charAt(0)!='6')&
   (string.charAt(0)!='7')&
   (string.charAt(0)!='8')&
   (string.charAt(0)!='9')
   )
 {accepta=false;
 }
  //we check if on position 1 we have line
 if(string.charAt(1)!='-')
 {
     accepta = false;
 }
//we check if on positions 2,3,4 we have next sequences
 if(
   (!string.startsWith("Ian",2))&
   (!string.startsWith("Feb",2))&
   (!string.startsWith("Mar",2))&
   (!string.startsWith("Apr",2))&
   (!string.startsWith("May",2))&
   (!string.startsWith("Jun",2))&
   (!string.startsWith("Jul",2))&
   (!string.startsWith("Aug",2))&
   (!string.startsWith("Sep",2))&
   (!string.startsWith("Oct",2))&
   (!string.startsWith("Nov",2))&
   (!string.startsWith("Dec",2))
   )
 {accepta=false;
 }
 //we check if on position 5 we have line
 if(string.charAt(5)!='-')
 {
     accepta = false;
 }

 //we check if on positions 6,7,8,9 we have digits
 if(
   (string.charAt(6)!='0')&
   (string.charAt(6)!='1')&
   (string.charAt(6)!='2')&
   (string.charAt(6)!='3')&
   (string.charAt(6)!='4')&
   (string.charAt(6)!='5')&
   (string.charAt(6)!='6')&
   (string.charAt(6)!='7')&
   (string.charAt(6)!='8')&
   (string.charAt(6)!='9')
   )
 {accepta=false;
 }

 if(
   (string.charAt(7)!='0')&
   (string.charAt(7)!='1')&
   (string.charAt(7)!='2')&
   (string.charAt(7)!='3')&
   (string.charAt(7)!='4')&
   (string.charAt(7)!='5')&
   (string.charAt(7)!='6')&
   (string.charAt(7)!='7')&
   (string.charAt(7)!='8')&
   (string.charAt(7)!='9')
   )
 {accepta=false;
 }
}
 //until here we have 8 characters
 if (string.length()==10)
 {
 if(
   (string.charAt(8)!='0')&
   (string.charAt(8)!='1')&
   (string.charAt(8)!='2')&
   (string.charAt(8)!='3')&
   (string.charAt(8)!='4')&
   (string.charAt(8)!='5')&
   (string.charAt(8)!='6')&
   (string.charAt(8)!='7')&
   (string.charAt(8)!='8')&
   (string.charAt(8)!='9')
   )
 {accepta=false;
 }

 if(
   (string.charAt(9)!='0')&
   (string.charAt(9)!='1')&
   (string.charAt(9)!='2')&
   (string.charAt(9)!='3')&
   (string.charAt(9)!='4')&
   (string.charAt(9)!='5')&
   (string.charAt(9)!='6')&
   (string.charAt(9)!='7')&
   (string.charAt(9)!='8')&
   (string.charAt(9)!='9')
   )
 {accepta=false;
 }
 }
    return accepta;
}

public boolean acceptSymbol(String text,byte mode)
{
       // if (symbol == s) {
       //getsym();
       // return true;
      // }
     // return false;
if (mode==SourceComment.M_COMMENT_EXACT_TEXT)
{
 if (text.equals(this.textSymbol))
 {
  this.getSymbol();
  return true;
 }
}
else if (mode==SourceComment.M_COMMENT_DIFFERENT_FROM)
{
 if (!text.equals(this.textSymbol)&this.typeSymbol!=Source.T_END)
 {
  this.getSymbol();
  return true;
 }

}
else if(mode == SourceComment.M_COMMENT_NAME_MM)
{
 if (this.textSymbol.endsWith(".mm"))
 {
 this.getSymbol();
 return true;
 }

}
else if(mode == SourceComment.M_COMMENT_DIFFERENT_FROM_NAME_MM)
{
 if (!this.textSymbol.endsWith(".mm"))
 {
 this.getSymbol();
 return true;
 }

}

else if (mode==SourceComment.M_COMMENT_DATE)
{
  
 return   (SourceComment.dateDay2DigitsMonthYear2Or4Digits(this.textSymbol)
         ||SourceComment.dateDay1DigitMonthYear2Or4Digits(this.textSymbol));
}

return false;

};
public boolean acceptSymbol(Byte type)
{
       // if (symbol == s) {
       //getsym();
       // return true;
      // }
     // return false;
 if (type==this.typeSymbol)
 {
this.getSymbol();
return true;

 }

 return false;

};


public  boolean expectSymbol(String text,byte mode)
{
    //if (accept(s))
     //   return true;
    //error("expect:"+s+" unexpected symbol");
    //return false;

    if (acceptSymbol(text,mode)) {return true;}
    this.errorNewLine("Expect: "+text+" and not other symbol! "
                                        +this.dataParentItem.textSymbol);
    return false;
};
public  boolean expectSymbol(Byte Tip)
{
    //if (accept(s))
     //   return true;
    //error("expect:"+s+" unexpected symbol");
    //return false;

    if (acceptSymbol(Tip)) {return true;}
    this.errorNewLine("Expect the code: "+Tip+" and not other symbol!");
    return false;
};

//in general about printing

public void stringPlusString()
{
  do
  {
  this.inactiveCommentInTypeSettings();
  
  if(!(textSymbol.equals("/*"))){break;}
  }while(1==1);
  
    position1=0;initialPosition=0;finalPosition=0;
String bigString="",littleString="";
do{
//we have quotation mark
position1=positionInComment;
if (this.acceptSymbol(SourceComment.QUOTATION_MARK_TEXT,SourceComment.M_COMMENT_EXACT_TEXT))
{
initialPosition=position1;
do
{
if (this.acceptSymbol(SourceComment.QUOTATION_MARK_TEXT,SourceComment.M_COMMENT_DIFFERENT_FROM))
{
 /*we're doing something*/
 finalPosition=positionInComment;
}

if (this.typeSymbol==Source.T_END) break;
if (this.textSymbol==SourceComment.QUOTATION_MARK_TEXT)
{
this.getSymbol();
littleString=
this.comment.substring(initialPosition, finalPosition-1);
break;
}
}while(1==1);
}
//or if we have text between apostrophes
else if(this.acceptSymbol(SourceComment.APOSTROPHE_TEXT, SourceComment.M_COMMENT_EXACT_TEXT))
{
initialPosition=position1;
do
{
  if (this.acceptSymbol(SourceComment.APOSTROPHE_TEXT,
          SourceComment.M_COMMENT_DIFFERENT_FROM))
  {
      /*we're doing something*/
     finalPosition=positionInComment;
  }

  if (this.typeSymbol==Source.T_END) break;
  if (this.textSymbol==SourceComment.APOSTROPHE_TEXT)
  {
    this.getSymbol();
    littleString=
    this.comment.substring(initialPosition, finalPosition-1);
    break;
  }
}while(1==1);

}
else if (this.typeSymbol==Source.T_END) break;

bigString=bigString+littleString;//we glue strings between +
littleString="";

  do
  {
  this.inactiveCommentInTypeSettings();

  if(!(textSymbol.equals("/*"))){break;}
  }while(1==1);

//if we find + we go further
}while(this.acceptSymbol("+",SourceComment.M_COMMENT_EXACT_TEXT));

if (this.expectSymbol(";", SourceComment.M_COMMENT_EXACT_TEXT))
{
    this.dataCurrentItem=new ParsingItem();
    this.dataCurrentItem.typeSymbol=Source.TN_STRING;
    this.dataCurrentItem.textSymbol=bigString;
    this.addToDataNode();
}
}



public void stringAsStringPlusString()
{
  do
  {
  this.inactiveCommentInTypeSettings();

  if(!(textSymbol.equals("/*"))){break;}
  }while(1==1);
  
position1=0;initialPosition=0;finalPosition=0;
String bigString="",littleString="";
s1=null;s2=null;//initiation
//it follows string as
position1=positionInComment;
if (this.acceptSymbol(SourceComment.QUOTATION_MARK_TEXT,SourceComment.M_COMMENT_EXACT_TEXT))
{
initialPosition=position1;
do
{
if (this.acceptSymbol(SourceComment.QUOTATION_MARK_TEXT,SourceComment.M_COMMENT_DIFFERENT_FROM))
{
 /*we're doing something*/
 finalPosition=positionInComment;
}

if (this.typeSymbol==Source.T_END) break;
if (this.textSymbol==SourceComment.QUOTATION_MARK_TEXT)
{
this.getSymbol();
littleString=
this.comment.substring(initialPosition, finalPosition-1);
break;
}
}while(1==1);
}

//or if we have text between apostrophes
else if(this.acceptSymbol(SourceComment.APOSTROPHE_TEXT, SourceComment.M_COMMENT_EXACT_TEXT))
{
initialPosition=position1;
do
{
  if (this.acceptSymbol(SourceComment.APOSTROPHE_TEXT,
          SourceComment.M_COMMENT_DIFFERENT_FROM))
  {
     /*we're doing something*/
     finalPosition=positionInComment;
  }

  if (this.typeSymbol==Source.T_END) break;
  if (this.textSymbol==SourceComment.APOSTROPHE_TEXT)
  {
    this.getSymbol();
    littleString=
    this.comment.substring(initialPosition, finalPosition-1);
    break;
  }
}while(1==1);

}
else if (this.typeSymbol==Source.T_END) return;//iesim din functie

  do
  {
  this.inactiveCommentInTypeSettings();

  if(!(textSymbol.equals("/*"))){break;}
  }while(1==1);

//we add the obtained string to the data structure
this.dataCurrentItem=new ParsingItem();
this.dataCurrentItem.typeSymbol=Source.TN_STRING;
this.dataCurrentItem.textSymbol=littleString;
this.addToDataNode();
s1=littleString;
//we empty the little string
littleString="";
//we expect as token
this.acceptSymbol("as", SourceComment.M_COMMENT_EXACT_TEXT);

 do
  {
  this.inactiveCommentInTypeSettings();

  if(!(textSymbol.equals("/*"))){break;}
  }while(1==1);

//it follows string+string+string;
do{
//we have text between quotation marks
position1=positionInComment;
if (this.acceptSymbol(SourceComment.QUOTATION_MARK_TEXT,SourceComment.M_COMMENT_EXACT_TEXT))
{
initialPosition=position1;
do
{
if (this.acceptSymbol(SourceComment.QUOTATION_MARK_TEXT,SourceComment.M_COMMENT_DIFFERENT_FROM))
{
 /*we're doing something*/
 finalPosition=positionInComment;
}

if (this.typeSymbol==Source.T_END) break;
if (this.textSymbol==SourceComment.QUOTATION_MARK_TEXT)
{
this.getSymbol();
littleString=
this.comment.substring(initialPosition, finalPosition-1);
break;
}
}while(1==1);
}
//or we have text between apostrophes
else if(this.acceptSymbol(SourceComment.APOSTROPHE_TEXT, SourceComment.M_COMMENT_EXACT_TEXT))
{
initialPosition=position1;
do
{
  if (this.acceptSymbol(SourceComment.APOSTROPHE_TEXT,
          SourceComment.M_COMMENT_DIFFERENT_FROM))
  {
     /*we're doing something*/
     finalPosition=positionInComment;
  }

  if (this.typeSymbol==Source.T_END) break;
  if (this.textSymbol==SourceComment.APOSTROPHE_TEXT)
  {
    this.getSymbol();
    littleString=
    this.comment.substring(initialPosition, finalPosition-1);
    break;
  }

}while(1==1);

}
else if (this.typeSymbol==Source.T_END) break;

bigString=bigString+littleString;//we glue string between +
littleString="";

 do
  {
  this.inactiveCommentInTypeSettings();

  if(!(textSymbol.equals("/*"))){break;}
  }while(1==1);

//if we find + we go further in the loop
}while(this.acceptSymbol("+",SourceComment.M_COMMENT_EXACT_TEXT));

if (this.expectSymbol(";", SourceComment.M_COMMENT_EXACT_TEXT))
{
    this.dataCurrentItem=new ParsingItem();
    this.dataCurrentItem.typeSymbol=Source.TN_STRING;
    this.dataCurrentItem.textSymbol=bigString;
    this.addToDataNode();
    s2=bigString;
}

}
public void htmlTitle()
{
ParsingItem dataPreviousParent=new ParsingItem();
if (this.acceptSymbol("htmltitle",SourceComment.M_COMMENT_EXACT_TEXT))
   {
    this.dataCurrentItem.typeSymbol=Source.TN_DISPLAY_DEFINITION;//mark
    dataPreviousParent=this.dataParentItem;
    this.addToDataNodeAndChangeNode();
    stringPlusString();//here is the function that parses string+string..;
    this.changeDataNode(dataPreviousParent);
   }
};
public void htmlHome()
{
ParsingItem dataPreviousParent=new ParsingItem();
if (this.acceptSymbol("htmlhome",SourceComment.M_COMMENT_EXACT_TEXT))
{
this.dataCurrentItem.typeSymbol=Source.TN_DISPLAY_DEFINITION;//mark
dataPreviousParent=this.dataParentItem;
this.addToDataNodeAndChangeNode();
stringPlusString();//here is the function that parses string+string..;
this.changeDataNode(dataPreviousParent);

}
};

public void htmlCss()
{
ParsingItem dataPreviousParent=new ParsingItem();
if (this.acceptSymbol("htmlcss",SourceComment.M_COMMENT_EXACT_TEXT))
{
this.dataCurrentItem.typeSymbol=Source.TN_DISPLAY_DEFINITION;//mark
dataPreviousParent=this.dataParentItem;
this.addToDataNodeAndChangeNode();
stringPlusString();//here is the function that parses string+string..;
this.changeDataNode(dataPreviousParent);

}
};

public void htmlBibliography()
{
 ParsingItem dataPreviousParent=new ParsingItem();
 if (this.acceptSymbol("htmlbibliography",SourceComment.M_COMMENT_EXACT_TEXT))
   {
   this.dataCurrentItem.typeSymbol=Source.TN_DISPLAY_DEFINITION;//mark
   dataPreviousParent=this.dataParentItem;
   this.addToDataNodeAndChangeNode();
   stringPlusString();//here is the function that parses string+string..;
   this.changeDataNode(dataPreviousParent);
   }
};

public void htmlFont()
{
 ParsingItem dataPreviousParent=new ParsingItem();
 if (this.acceptSymbol("htmlfont",SourceComment.M_COMMENT_EXACT_TEXT))
   {
   this.dataCurrentItem.typeSymbol=Source.TN_DISPLAY_DEFINITION;//mark
   dataPreviousParent=this.dataParentItem;
   this.addToDataNodeAndChangeNode();
   stringPlusString();//here is the function that parses string+string..;
   this.changeDataNode(dataPreviousParent);
   }
};
//END in general about printing

//extension
public void htmlExtensionTitle()
{
ParsingItem  dataPreviousParent=new ParsingItem();
if (this.acceptSymbol("exthtmltitle",SourceComment.M_COMMENT_EXACT_TEXT))
   {
    this.dataCurrentItem.typeSymbol=Source.TN_DISPLAY_DEFINITION;//mark
    dataPreviousParent=this.dataParentItem;
    this.addToDataNodeAndChangeNode();
    stringPlusString();//here is the function that parses string+string..;
    this.changeDataNode(dataPreviousParent);
   }
};
public void htmlExtensionHome()
{
ParsingItem  dataPreviousParent=new ParsingItem();
if (this.acceptSymbol("exthtmlhome",SourceComment.M_COMMENT_EXACT_TEXT))
   {
    this.dataCurrentItem.typeSymbol=Source.TN_DISPLAY_DEFINITION;//mark
    dataPreviousParent=this.dataParentItem;
    this.addToDataNodeAndChangeNode();
    stringPlusString();//here is the function that parses string+string..;
    this.changeDataNode(dataPreviousParent);
   }
};
public void htmlExtensionLabel()
{
 ParsingItem  dataPreviousParent=new ParsingItem();
 if (this.acceptSymbol("exthtmllabel",SourceComment.M_COMMENT_EXACT_TEXT))
   {
     this.dataCurrentItem.typeSymbol=Source.TN_DISPLAY_DEFINITION;//mark
     dataPreviousParent=this.dataParentItem;
    this.addToDataNodeAndChangeNode();
    stringPlusString();//here is the function that parses string+string..;
    this.changeDataNode(dataPreviousParent);
   }
};
public void htmlExtensionBibliography()
{
 ParsingItem  dataPreviousParent=new ParsingItem();
if (this.acceptSymbol("exthtmlbibliography",SourceComment.M_COMMENT_EXACT_TEXT))
   {
    this.dataCurrentItem.typeSymbol=Source.TN_DISPLAY_DEFINITION;//mark
    dataPreviousParent=this.dataParentItem;
    this.addToDataNodeAndChangeNode();
    stringPlusString();//here is the function that parses string+string..;
    this.changeDataNode(dataPreviousParent);
   }
 };
//END extension

//general variables
public void htmlColorVariable()
{
    ParsingItem  dataPreviousParent=new ParsingItem();
 if (this.acceptSymbol("htmlvarcolor",SourceComment.M_COMMENT_EXACT_TEXT))
   {
    this.dataCurrentItem.typeSymbol=Source.TN_DISPLAY_DEFINITION;//mark
    dataPreviousParent=this.dataParentItem;
    this.addToDataNodeAndChangeNode();
    stringPlusString();//here is the function that parses string+string..;
    this.changeDataNode(dataPreviousParent);
   }
};
public void htmlDirector()
{
 ParsingItem  dataPreviousParent=new ParsingItem();
if (this.acceptSymbol("htmldir",SourceComment.M_COMMENT_EXACT_TEXT))
   {
   this.dataCurrentItem.typeSymbol=Source.TN_DISPLAY_DEFINITION;//mark
   dataPreviousParent=this.dataParentItem;
   this.addToDataNodeAndChangeNode();
   stringPlusString();//here is the function that parses string+string..;
   this.changeDataNode(dataPreviousParent);
   }
};
public void htmlAlternativeDirector()
{
 ParsingItem  dataPreviousParent=new ParsingItem();
 if (this.acceptSymbol("althtmldir",SourceComment.M_COMMENT_EXACT_TEXT))
   {
    this.dataCurrentItem.typeSymbol=Source.TN_DISPLAY_DEFINITION;//mark
    dataPreviousParent=this.dataParentItem;
    this.addToDataNodeAndChangeNode();
    stringPlusString();//here is the function that parses string+string..;
    this.changeDataNode(dataPreviousParent);
   }
};
//END general variables

//symbols definitions
public void htmlDefinition()
{
 ParsingItem  dataPreviousParent=new ParsingItem(),el;
 if (this.acceptSymbol("htmldef",SourceComment.M_COMMENT_EXACT_TEXT))
   {
     this.dataCurrentItem.typeSymbol=Source.TN_DISPLAY_DEFINITION;//mark
     dataPreviousParent=this.dataParentItem;
     el=this.dataCurrentItem;
     
     this.addToDataNodeAndChangeNode();
     stringAsStringPlusString();//here is the function that parses string as string+string..;
     this.changeDataNode(dataPreviousParent);

    this.S.htlmldefString1AsString2.put(s1, s2);
    display2(s1+" as "+s2);
   }
};
public void htmlAlternativeDefinition()
{
 ParsingItem  dataPreviousParent=new ParsingItem();
if (this.acceptSymbol("althtmldef",SourceComment.M_COMMENT_EXACT_TEXT))
   {
    this.dataCurrentItem.typeSymbol=Source.TN_DISPLAY_DEFINITION;//mark
    dataPreviousParent=this.dataParentItem;
    this.addToDataNodeAndChangeNode();
    stringAsStringPlusString();//here is the function that parses string as string+string..;
    this.changeDataNode(dataPreviousParent);
   }
};
public void latexDefinition()
{
 ParsingItem  dataPreviousParent=new ParsingItem();
if (this.acceptSymbol("latexdef",SourceComment.M_COMMENT_EXACT_TEXT))
   {
    this.dataCurrentItem.typeSymbol=Source.TN_DISPLAY_DEFINITION;//mark
    dataPreviousParent=this.dataParentItem;
    this.addToDataNodeAndChangeNode();
    stringAsStringPlusString();//here is the function that parses string as string+string..;
    this.changeDataNode(dataPreviousParent);
   }
};
public void commentInTypeSettings()
{
initialPosition=0;finalPosition=0;
initialPosition=this.positionInComment;//we memorize position from where starts /*
if (this.acceptSymbol("/*",SourceComment.M_COMMENT_EXACT_TEXT))
   {

     do {
            if (acceptSymbol("*/",SourceComment.M_COMMENT_DIFFERENT_FROM))
            {
              //we go further

             finalPosition=positionInComment;//we memorize position in comment

            }
   else if (acceptSymbol("*/",SourceComment.M_COMMENT_EXACT_TEXT))
         {
          this.dataCurrentItem=new ParsingItem();
          this.dataCurrentItem.typeSymbol=Source.TN_COMMENT_FROM_TYPESETTINGS;
          this.dataCurrentItem.codeSymbol=Source.C_ANYOTHER_SYMBOL;
          //we scrape from comment the text between /* and */
          this.dataCurrentItem.textSymbol=
               this.comment.substring(initialPosition,finalPosition-3);
          this.addToDataNode();
          break;
         }
    else if(this.typeSymbol==Source.T_END) break;
         
        } while (true);
   }
};
//is inactive it does not influence data structure
public void inactiveCommentInTypeSettings()
{
initialPosition=0;finalPosition=0;
initialPosition=this.positionInComment;//we memorize position from where starts /*
if (this.acceptSymbol("/*",SourceComment.M_COMMENT_EXACT_TEXT))
   {

     do {
            if (acceptSymbol("*/",SourceComment.M_COMMENT_DIFFERENT_FROM))
            {
              //we go further

             finalPosition=positionInComment;//we memorize the position in comment

            }
   else if (acceptSymbol("*/",SourceComment.M_COMMENT_EXACT_TEXT))
         {
           break;
         }
    else if(this.typeSymbol==Source.T_END) break;

        } while (true);
   }
};
public void chapterInTypeSettings()
{
 String textChapterInTypeSettings1="/*******",textChapterInTypeSettings2="*******/";
 initialPosition=0;finalPosition=0;
initialPosition=this.positionInComment;//we memorize position from where starts the /*
if (this.acceptSymbol(textChapterInTypeSettings1,SourceComment.M_COMMENT_EXACT_TEXT))
   {

     do {
            if (acceptSymbol(textChapterInTypeSettings2,SourceComment.M_COMMENT_DIFFERENT_FROM))
            {
              //we go further

             finalPosition=positionInComment;//we memorize position in the comment

            }
   else if (acceptSymbol(textChapterInTypeSettings2,SourceComment.M_COMMENT_EXACT_TEXT))
         {
          this.dataCurrentItem=new ParsingItem();
          this.dataCurrentItem.typeSymbol=Source.TN_CHAPTER_FROM_TYPESETTINGS;
          this.dataCurrentItem.codeSymbol=Source.C_ANYOTHER_SYMBOL;
          //we scrape from comment the text between /* and */
          this.dataCurrentItem.textSymbol=
            this.comment.substring(initialPosition,
            finalPosition-textChapterInTypeSettings2.length()-1);
          this.addToDataNode();
          break;
         }
    else if(this.typeSymbol==Source.T_END) break;

        } while (true);
    }
};

//END symbols definitions

public void addContent(int mark)
{
    if(finalPosition-mark-initialPosition>0)
    {
    s=this.comment.substring(initialPosition,finalPosition-mark);
    }
     else s="";
if((initialPosition2!=initialPosition|initialPosition2==0)&(finalPosition2!=finalPosition-mark)&!s.equals("\r\n")
&!s.equals("")&!s.equals(" ")&!s.equals("\n")&!s.equals("\r"))
            {
             if(!contentNodeCreated)
             {
               //creem un nod de continut
                this.dataCurrentItem=new ParsingItem();
                this.dataCurrentItem.typeSymbol=
                        Source.TN_CONTENT_FROM_COMMENT;
                this.dataCurrentItem.textSymbol="CNT";
                this.addToDataNodeAndChangeNode();
                //we mark the content node creation
                contentNodeCreated=true;
             }
            //we have an quote it will be displayed untained
            this.dataCurrentItem=new ParsingItem();
            this.dataCurrentItem.typeSymbol=Source.TN_UNTAINED;
            this.dataCurrentItem.textSymbol=
            this.comment.substring(initialPosition,finalPosition-mark);
            this.addToDataNode();//add to the content node
            initialPosition2=initialPosition; finalPosition2=finalPosition-mark;//we memorize the positions
            }
}
public void addToBeReplaced()
{
  ParsingItem copy=this.dataCurrentItem;
             if(!contentNodeCreated)
             {
                //we create a content node
                this.dataCurrentItem=new ParsingItem();
                this.dataCurrentItem.typeSymbol=
                        Source.TN_CONTENT_FROM_COMMENT;
                this.dataCurrentItem.textSymbol="CNT";
                this.addToDataNodeAndChangeNode();
                //we mark the creation of content node
                contentNodeCreated=true;
             }
            //we have a quote which will be displayed by replacement with a HTML string
            this.dataCurrentItem=copy;
            this.dataCurrentItem.typeSymbol=Source.TN_TO_BE_REPLACED;
            this.addToDataNode();//add to the content node
                        
}
public void simpleOrStructuredComment()
{
 if (endComment|(typeSymbol==Source.T_END)) return;
 
ParsingItem item=null;
initialPosition=0;finalPosition=0;position1=0;
//we will memorize the initial and final positions for a possible verification
initialPosition2=0;finalPosition2=0;
haveApostrophe=false;contentNodeCreated=false;
s="";
initialPosition=0;//we set to zero the initial position
if (this.acceptSymbol(".mm",SourceComment.M_COMMENT_NAME_MM))
   {
   
   }
 else 
     if (!this.textSymbol.equals("$t")& (this.typeSymbol!=Source.T_END))
         { /*we move on*/}
 else return ;//we get out from the function
  
 

 do
 {

     if (endComment|(typeSymbol==Source.T_END))
     {
         finalPosition=positionInComment;//the position before the end of comment
         this.addContent(1);
         return;//we get out from the function
     }
   //we check if is another type of symbol
       position1=positionInComment;//we memorize the beforehand position
   if ((acceptSymbol(SourceComment.GRAVE_APOSTROPHE_TEXT,SourceComment.M_COMMENT_EXACT_TEXT))
        & this.S.graveApostropheActivated)
            {
               //the apostrophes close
               if (haveApostrophe)
               {
                   haveApostrophe=false;
                   initialPosition=position1;

               }
               //the apostrophes open
               else
               {
               haveApostrophe=true;
               finalPosition=position1;
               this.addContent(1);

               }
               
               
            }
            else
            if
            (
            !this.textSymbol.equals(SourceComment.GRAVE_APOSTROPHE_TEXT)
            &!this.textSymbol.equals(SourceComment.CHAPTER_TEXT)
            &!this.textSymbol.equals(SourceComment.SUBCHAPTER_TEXT)
            &!this.textSymbol.equals(SourceComment.SUPERCHAPTER_TEXT)
            )
            {
             //we move on
             this.getSymbol();
             //we mark the item which must be replaced cu html format
             if (haveApostrophe)
                 { 
                   this.addToBeReplaced();//we add the item to the content node
                 }
             
            }
      else if (endComment|(typeSymbol==Source.T_END)) return;//we get out from function
     //END other symbol

   //we check if we have a superchapter
   position1=this.positionInComment;//we memorize the position where the title begins

if (this.acceptSymbol(SourceComment.SUPERCHAPTER_TEXT,SourceComment.M_COMMENT_EXACT_TEXT))
   {
    finalPosition=position1;

    this.addContent(SourceComment.SUPERCHAPTER_TEXT.length()+1);

    if (dataRoot!=null)
    {
    this.changeDataNode(dataRoot);//we return to the root
    contentNodeCreated=false;//we mark the closing of the content node
    }
   //we create a superchapter from the comment
   this.dataCurrentItem=new ParsingItem();
   this.dataCurrentItem.typeSymbol=Source.TN_SUPERCHAPTER_FROM_COMMENT;
   this.dataCurrentItem.textSymbol="";
   item=dataCurrentItem;//we memorized the current item
   this.addToDataNode();
   initialPosition=position1;//we memorize the position before the superchapter

     do {
          if (acceptSymbol(SourceComment.SUPERCHAPTER_TEXT,SourceComment.M_COMMENT_DIFFERENT_FROM))
            {
              finalPosition=positionInComment;//we always memorize the final position
            }
   else if (acceptSymbol(SourceComment.SUPERCHAPTER_TEXT,SourceComment.M_COMMENT_EXACT_TEXT))
         {
            //we substract the size of SUPERCHAPTER_TEXT from the length of superchapter title
            item.textSymbol=
            this.comment.substring(initialPosition,finalPosition-
                    SourceComment.SUPERCHAPTER_TEXT.length()-1);
             initialPosition2=initialPosition;
             finalPosition2=finalPosition-
                   SourceComment.SUPERCHAPTER_TEXT.length()-1;//we memorize the positions
            //we mark that we not have an apostrophe after we were in superchapter
            haveApostrophe=false;
            //we reset the initial position
            initialPosition=finalPosition;
            break;
         }
    else { expectSymbol(SourceComment.SUPERCHAPTER_TEXT,SourceComment.M_COMMENT_EXACT_TEXT);
           break;
         }
        } while (true);

    }
   
   //we verify if we have a chapter
   position1=this.positionInComment;//we memorize the position from where title begins

if (this.acceptSymbol(SourceComment.CHAPTER_TEXT,SourceComment.M_COMMENT_EXACT_TEXT))
   {
    finalPosition=position1;

    this.addContent(SourceComment.CHAPTER_TEXT.length()+1);

    if (dataRoot!=null)
    {
    this.changeDataNode(dataRoot);//we return to the root
    contentNodeCreated=false;//we mark the closing of content node
    }
   //we create a chapter from the comment
   this.dataCurrentItem=new ParsingItem();
   this.dataCurrentItem.typeSymbol=Source.TN_CHAPTER_FROM_COMMENT;
   this.dataCurrentItem.textSymbol="";
   item=dataCurrentItem;//we memorize the current item
   this.addToDataNode();
   initialPosition=position1;//we memorize the position before the chapter

     do {
            if (acceptSymbol(SourceComment.CHAPTER_TEXT,SourceComment.M_COMMENT_DIFFERENT_FROM))
            {
              finalPosition=positionInComment;//we always memorize the final position
            }
   else if (acceptSymbol(SourceComment.CHAPTER_TEXT,SourceComment.M_COMMENT_EXACT_TEXT))
         {
            //we substract the size of CHAPTER_TEXT from the length of chapter title
            item.textSymbol=
            this.comment.substring(initialPosition,finalPosition-
                    SourceComment.CHAPTER_TEXT.length()-1);
             initialPosition2=initialPosition;
             finalPosition2=finalPosition-
                   SourceComment.CHAPTER_TEXT.length()-1;//we memorize the positions
            //we mark that we not have an apostrophe after we were in chapter
            haveApostrophe=false;
            //we reset initial position
            initialPosition=finalPosition;
            break;
         }
    else { expectSymbol(SourceComment.CHAPTER_TEXT,SourceComment.M_COMMENT_EXACT_TEXT);
           break;
         }
        } while (true);
     
    }

    //we verify if we have a subchapter
    position1=this.positionInComment;//we memorize the position from where the title begins

if (this.acceptSymbol(SourceComment.SUBCHAPTER_TEXT,SourceComment.M_COMMENT_EXACT_TEXT))
   {
    finalPosition=position1;
    this.addContent(SourceComment.SUBCHAPTER_TEXT.length()+1);

    if (dataRoot!=null)
    {
    this.changeDataNode(dataRoot);//we return to the root
    contentNodeCreated=false;//we mark the closing of the content node
    }
    //we create a subchapter from the comment
    this.dataCurrentItem=new ParsingItem();
    this.dataCurrentItem.typeSymbol=Source.TN_SUBCHAPTER_FROM_COMMENT;
    this.dataCurrentItem.textSymbol="";
    item=dataCurrentItem;//we memorized the current item
    this.addToDataNode();
    initialPosition=position1;//we memorize the position before the subchapter

     do {
            if (acceptSymbol(SourceComment.SUBCHAPTER_TEXT,SourceComment.M_COMMENT_DIFFERENT_FROM))
            {
              finalPosition=positionInComment;//we memorize always the final position
            }
   else if (acceptSymbol(SourceComment.SUBCHAPTER_TEXT,SourceComment.M_COMMENT_EXACT_TEXT))
         {
            //we substract the size of SUBCHAPTER_TEXT from the length of the subchapter
            item.textSymbol=
            this.comment.substring(initialPosition,finalPosition-
                    SourceComment.SUBCHAPTER_TEXT.length()-1);
             initialPosition2=initialPosition;
             finalPosition2=finalPosition-
                    SourceComment.SUBCHAPTER_TEXT.length()-1;//we memorize the positions
            //we mark that we not have an apostrophe afte we were in subchapter
            haveApostrophe=false;
            //we reset the initial position
            initialPosition=finalPosition;
            break;
         }
    else { expectSymbol(SourceComment.SUBCHAPTER_TEXT,SourceComment.M_COMMENT_EXACT_TEXT);
           break;
         }
        } while (true);
    
    }
      
    } while (true);


};

public void displaySettings()
{
 if (endComment|(typeSymbol==Source.T_END)) return;


if (this.acceptSymbol("$t",SourceComment.M_COMMENT_EXACT_TEXT))
   {
   this.dataCurrentItem.typeSymbol=Source.TN_DISPLAY;//we mark the item
   this.addToDataNodeAndChangeNode();
 
   
     do {
            this.commentInTypeSettings();
            this.chapterInTypeSettings();
            this.htmlCss();
            this.htmlFont();
            this.htmlTitle();
            this.htmlHome();
            this.htmlBibliography();

            this.htmlExtensionTitle();
            this.htmlExtensionHome();
            this.htmlExtensionBibliography();
            this.htmlExtensionLabel();

            this.htmlColorVariable();
            this.htmlDirector();
            this.htmlAlternativeDirector();
           
            

            this.htmlDefinition();
            this.htmlAlternativeDefinition();
            this.latexDefinition();

            verifyPositionInComment();

          if (endComment|(typeSymbol==Source.T_END)) return;
          
        } while (true);
   
    }

};
public void verifyPositionInComment()
{
    if (positionCursor<3) { positions[positionCursor]=positionInComment;
                                    positionCursor++;
                          }
              else if (positionCursor>=3){ positionCursor=0;
                                          if ((positions[0]==positions[1])&
                                              positions[1]==positions[2])
                                          {endComment=true;}
                                          //if positionInComment does not modify
                                          //we have syntactic jam
                                         }
}
public static void setCommentCategory (ParsingItem xComment)
{

if (xComment.containedItems!=null)
{
if (xComment.containedItems.size()>0)
{
   ParsingItem subordinatedItem=xComment.containedItems.get(0);
   if (subordinatedItem.typeSymbol==Source.TN_CHAPTER_FROM_COMMENT)
   {
       xComment.functionSymbol =Source.F_CHAPTER_COMMENT;
   }
    else if (subordinatedItem.typeSymbol==Source.TN_SUPERCHAPTER_FROM_COMMENT)
   {
       xComment.functionSymbol =Source.F_SUPERCHAPTER_COMMENT;
   }
   else if (subordinatedItem.typeSymbol==Source.TN_SUBCHAPTER_FROM_COMMENT)
   {
       xComment.functionSymbol =Source.F_SUBCHAPTER_COMMENT;
   }
   else if (subordinatedItem.typeSymbol==Source.TN_CONTENT_FROM_COMMENT)
   {
       xComment.functionSymbol =Source.F_NORMAL_COMMENT;
       String text="";
       ParsingItem contentSubordinatedItem=null;
       if (subordinatedItem.containedItems!=null)
       {
       if (subordinatedItem.containedItems.size()==1)
       {
           contentSubordinatedItem=subordinatedItem.containedItems.get(0);
           if (contentSubordinatedItem.typeSymbol!=Source.TN_UNTAINED) contentSubordinatedItem=null;
       }
       }
       if (contentSubordinatedItem!=null)
       {
       //we make a copy of string to the subordinated item of the content
       int dim1=contentSubordinatedItem.textSymbol.length();
       if (dim1>=1)dim1=dim1-1;
       //we eliminate  [ ]  if exists
       text=contentSubordinatedItem.textSymbol.substring(1, dim1);
              
       //we verify if is a date comment
       if (SourceComment.dateDay1DigitMonthYear2Or4Digits(text)|
           SourceComment.dateDay2DigitsMonthYear2Or4Digits(text)
           )
        {
           xComment.functionSymbol=Source.F_DATE_COMMENT;
        }
       
       }
   }



}
}

}


public void parse(ParsingItem unprocessedComment)
{
//data items

//we relate to the comment that will be processed
this.dataRoot=unprocessedComment;
this.comment=unprocessedComment.textSymbol;

this.getSymbol();
this.dataCurrentItem= new ParsingItem();
this.dataCurrentItem.typeSymbol=typeSymbol;
this.dataCurrentItem.functionSymbol=functionSymbol;
this.dataCurrentItem.codeSymbol=codeSymbol;
this.dataCurrentItem.textSymbol=textSymbol;
this.dataCurrentItem.positionSymbol=positionSymbol;
this.dataCurrentItem.lengthSymbol=lengthSymbol;
this.dataCurrentItem.errorCodeSymbol=errorCodeSymbol;


this.dataParentItem= this.dataRoot;


if (endComment|(typeSymbol==Source.T_END)) return;
do
{   
if (endComment|(typeSymbol==Source.T_END)) return;
//we verify one at time
simpleOrStructuredComment();
if (endComment|(typeSymbol==Source.T_END)) return;
displaySettings();
if (endComment|(typeSymbol==Source.T_END)) return;

} while (true);


}

};