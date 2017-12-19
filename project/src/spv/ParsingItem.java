//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv;
import java.util.List;

public class ParsingItem
     {
        void ParsingItem()
        {

            this.containedItems=null;
            
        };
 
      public byte typeSymbol;
      public byte functionSymbol;
      public byte codeSymbol;
      public String textSymbol;
      
      public boolean onNewLine=false;
      public int positionSymbol;
      public int lengthSymbol;
      public byte errorCodeSymbol;
      public List<ParsingItem> containedItems;
      public ParsingItem fatherItem;

      //chapters
      public List<ParsingItem> chapterItems;
      public ParsingItem fatherChapter;
      //END chapters
            
      public Object generatedItem;
       
      
      @Override
      public String toString()
        {
        
        String displayedText="";
        
        //we are splitting into categories

        if (this.functionSymbol==Source.F_SUPERCHAPTER_COMMENT)
       {
        //we have superchapter-comment

        if (this.containedItems!=null)
        {
         if (this.containedItems.size()>0)
         {

        ParsingItem superChapterItem=this.containedItems.get(0);
        displayedText="<FONT COLOR='#0033ff'> <FONT SIZE=4> "
                 +superChapterItem.textSymbol
                 +"</FONT>";

        }
        }
        //END superchapter
        }
       else if(this.functionSymbol == Source.F_CHAPTER_COMMENT)
       {
        //we have chapter-comment
        
        if (this.containedItems!=null)
        {
         if (this.containedItems.size()>0)
         {

        ParsingItem chapterItem=this.containedItems.get(0);
        displayedText="<FONT COLOR='#0033ff'> <FONT SIZE=4> "
                 +chapterItem.textSymbol
                 +"</FONT>";
              
        }
        }
        //END chapter
        }
        else if (this.functionSymbol==Source.F_SUBCHAPTER_COMMENT)
       {
        //we have subchapter-comment

        if (this.containedItems!=null)
        {
         if (this.containedItems.size()>0)
         {

        ParsingItem subChapterItem=this.containedItems.get(0);
         displayedText="<FONT COLOR='#0033ff'> <FONT SIZE=3> "
                 +subChapterItem.textSymbol
                 +"</FONT>";
        }
        }
        //END subchapter
       }
        else if (this.typeSymbol==Source.T_FATHER_FILE)
        {
            displayedText=
             "<FONT COLOR='#0033ff'> <FONT SIZE=3><B> Base </B></FONT>";
        }

       
         displayedText="<HTML> "+displayedText+" </HTML>";
          

        return displayedText;
        };
      
     }