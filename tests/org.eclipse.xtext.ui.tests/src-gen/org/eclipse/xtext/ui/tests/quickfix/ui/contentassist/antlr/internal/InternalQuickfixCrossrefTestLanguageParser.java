package org.eclipse.xtext.ui.tests.quickfix.ui.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import org.eclipse.xtext.ui.tests.quickfix.services.QuickfixCrossrefTestLanguageGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalQuickfixCrossrefTestLanguageParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'{'", "'}'", "'ref'"
    };
    public static final int RULE_ID=5;
    public static final int RULE_STRING=4;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__13=13;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_INT=6;
    public static final int RULE_WS=9;
    public static final int RULE_SL_COMMENT=8;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=7;

    // delegates
    // delegators


        public InternalQuickfixCrossrefTestLanguageParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalQuickfixCrossrefTestLanguageParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalQuickfixCrossrefTestLanguageParser.tokenNames; }
    public String getGrammarFileName() { return "../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g"; }


     
     	private QuickfixCrossrefTestLanguageGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(QuickfixCrossrefTestLanguageGrammarAccess grammarAccess) {
        	this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected Grammar getGrammar() {
        	return grammarAccess.getGrammar();
        }
        
        @Override
        protected String getValueForTokenName(String tokenName) {
        	return tokenName;
        }




    // $ANTLR start "entryRuleMain"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:60:1: entryRuleMain : ruleMain EOF ;
    public final void entryRuleMain() throws RecognitionException {
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:61:1: ( ruleMain EOF )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:62:1: ruleMain EOF
            {
             before(grammarAccess.getMainRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleMain_in_entryRuleMain61);
            ruleMain();

            state._fsp--;

             after(grammarAccess.getMainRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleMain68); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMain"


    // $ANTLR start "ruleMain"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:69:1: ruleMain : ( ( rule__Main__ElementsAssignment )* ) ;
    public final void ruleMain() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:73:2: ( ( ( rule__Main__ElementsAssignment )* ) )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:74:1: ( ( rule__Main__ElementsAssignment )* )
            {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:74:1: ( ( rule__Main__ElementsAssignment )* )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:75:1: ( rule__Main__ElementsAssignment )*
            {
             before(grammarAccess.getMainAccess().getElementsAssignment()); 
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:76:1: ( rule__Main__ElementsAssignment )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=RULE_STRING && LA1_0<=RULE_ID)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:76:2: rule__Main__ElementsAssignment
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__Main__ElementsAssignment_in_ruleMain94);
            	    rule__Main__ElementsAssignment();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

             after(grammarAccess.getMainAccess().getElementsAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMain"


    // $ANTLR start "entryRuleElement"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:88:1: entryRuleElement : ruleElement EOF ;
    public final void entryRuleElement() throws RecognitionException {
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:89:1: ( ruleElement EOF )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:90:1: ruleElement EOF
            {
             before(grammarAccess.getElementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleElement_in_entryRuleElement122);
            ruleElement();

            state._fsp--;

             after(grammarAccess.getElementRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleElement129); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleElement"


    // $ANTLR start "ruleElement"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:97:1: ruleElement : ( ( rule__Element__Group__0 ) ) ;
    public final void ruleElement() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:101:2: ( ( ( rule__Element__Group__0 ) ) )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:102:1: ( ( rule__Element__Group__0 ) )
            {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:102:1: ( ( rule__Element__Group__0 ) )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:103:1: ( rule__Element__Group__0 )
            {
             before(grammarAccess.getElementAccess().getGroup()); 
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:104:1: ( rule__Element__Group__0 )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:104:2: rule__Element__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__Element__Group__0_in_ruleElement155);
            rule__Element__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getElementAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleElement"


    // $ANTLR start "rule__Element__Group__0"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:118:1: rule__Element__Group__0 : rule__Element__Group__0__Impl rule__Element__Group__1 ;
    public final void rule__Element__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:122:1: ( rule__Element__Group__0__Impl rule__Element__Group__1 )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:123:2: rule__Element__Group__0__Impl rule__Element__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__Element__Group__0__Impl_in_rule__Element__Group__0189);
            rule__Element__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Element__Group__1_in_rule__Element__Group__0192);
            rule__Element__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group__0"


    // $ANTLR start "rule__Element__Group__0__Impl"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:130:1: rule__Element__Group__0__Impl : ( ( rule__Element__DocAssignment_0 )? ) ;
    public final void rule__Element__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:134:1: ( ( ( rule__Element__DocAssignment_0 )? ) )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:135:1: ( ( rule__Element__DocAssignment_0 )? )
            {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:135:1: ( ( rule__Element__DocAssignment_0 )? )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:136:1: ( rule__Element__DocAssignment_0 )?
            {
             before(grammarAccess.getElementAccess().getDocAssignment_0()); 
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:137:1: ( rule__Element__DocAssignment_0 )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_STRING) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:137:2: rule__Element__DocAssignment_0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__Element__DocAssignment_0_in_rule__Element__Group__0__Impl219);
                    rule__Element__DocAssignment_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElementAccess().getDocAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group__0__Impl"


    // $ANTLR start "rule__Element__Group__1"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:147:1: rule__Element__Group__1 : rule__Element__Group__1__Impl rule__Element__Group__2 ;
    public final void rule__Element__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:151:1: ( rule__Element__Group__1__Impl rule__Element__Group__2 )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:152:2: rule__Element__Group__1__Impl rule__Element__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__Element__Group__1__Impl_in_rule__Element__Group__1250);
            rule__Element__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Element__Group__2_in_rule__Element__Group__1253);
            rule__Element__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group__1"


    // $ANTLR start "rule__Element__Group__1__Impl"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:159:1: rule__Element__Group__1__Impl : ( ( rule__Element__NameAssignment_1 ) ) ;
    public final void rule__Element__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:163:1: ( ( ( rule__Element__NameAssignment_1 ) ) )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:164:1: ( ( rule__Element__NameAssignment_1 ) )
            {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:164:1: ( ( rule__Element__NameAssignment_1 ) )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:165:1: ( rule__Element__NameAssignment_1 )
            {
             before(grammarAccess.getElementAccess().getNameAssignment_1()); 
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:166:1: ( rule__Element__NameAssignment_1 )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:166:2: rule__Element__NameAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__Element__NameAssignment_1_in_rule__Element__Group__1__Impl280);
            rule__Element__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getElementAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group__1__Impl"


    // $ANTLR start "rule__Element__Group__2"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:176:1: rule__Element__Group__2 : rule__Element__Group__2__Impl rule__Element__Group__3 ;
    public final void rule__Element__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:180:1: ( rule__Element__Group__2__Impl rule__Element__Group__3 )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:181:2: rule__Element__Group__2__Impl rule__Element__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__Element__Group__2__Impl_in_rule__Element__Group__2310);
            rule__Element__Group__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Element__Group__3_in_rule__Element__Group__2313);
            rule__Element__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group__2"


    // $ANTLR start "rule__Element__Group__2__Impl"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:188:1: rule__Element__Group__2__Impl : ( '{' ) ;
    public final void rule__Element__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:192:1: ( ( '{' ) )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:193:1: ( '{' )
            {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:193:1: ( '{' )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:194:1: '{'
            {
             before(grammarAccess.getElementAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,11,FollowSets000.FOLLOW_11_in_rule__Element__Group__2__Impl341); 
             after(grammarAccess.getElementAccess().getLeftCurlyBracketKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group__2__Impl"


    // $ANTLR start "rule__Element__Group__3"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:207:1: rule__Element__Group__3 : rule__Element__Group__3__Impl rule__Element__Group__4 ;
    public final void rule__Element__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:211:1: ( rule__Element__Group__3__Impl rule__Element__Group__4 )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:212:2: rule__Element__Group__3__Impl rule__Element__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__Element__Group__3__Impl_in_rule__Element__Group__3372);
            rule__Element__Group__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Element__Group__4_in_rule__Element__Group__3375);
            rule__Element__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group__3"


    // $ANTLR start "rule__Element__Group__3__Impl"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:219:1: rule__Element__Group__3__Impl : ( ( rule__Element__ContainedAssignment_3 )* ) ;
    public final void rule__Element__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:223:1: ( ( ( rule__Element__ContainedAssignment_3 )* ) )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:224:1: ( ( rule__Element__ContainedAssignment_3 )* )
            {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:224:1: ( ( rule__Element__ContainedAssignment_3 )* )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:225:1: ( rule__Element__ContainedAssignment_3 )*
            {
             before(grammarAccess.getElementAccess().getContainedAssignment_3()); 
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:226:1: ( rule__Element__ContainedAssignment_3 )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>=RULE_STRING && LA3_0<=RULE_ID)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:226:2: rule__Element__ContainedAssignment_3
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__Element__ContainedAssignment_3_in_rule__Element__Group__3__Impl402);
            	    rule__Element__ContainedAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

             after(grammarAccess.getElementAccess().getContainedAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group__3__Impl"


    // $ANTLR start "rule__Element__Group__4"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:236:1: rule__Element__Group__4 : rule__Element__Group__4__Impl rule__Element__Group__5 ;
    public final void rule__Element__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:240:1: ( rule__Element__Group__4__Impl rule__Element__Group__5 )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:241:2: rule__Element__Group__4__Impl rule__Element__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_rule__Element__Group__4__Impl_in_rule__Element__Group__4433);
            rule__Element__Group__4__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Element__Group__5_in_rule__Element__Group__4436);
            rule__Element__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group__4"


    // $ANTLR start "rule__Element__Group__4__Impl"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:248:1: rule__Element__Group__4__Impl : ( ( rule__Element__Group_4__0 )* ) ;
    public final void rule__Element__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:252:1: ( ( ( rule__Element__Group_4__0 )* ) )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:253:1: ( ( rule__Element__Group_4__0 )* )
            {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:253:1: ( ( rule__Element__Group_4__0 )* )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:254:1: ( rule__Element__Group_4__0 )*
            {
             before(grammarAccess.getElementAccess().getGroup_4()); 
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:255:1: ( rule__Element__Group_4__0 )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==13) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:255:2: rule__Element__Group_4__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__Element__Group_4__0_in_rule__Element__Group__4__Impl463);
            	    rule__Element__Group_4__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

             after(grammarAccess.getElementAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group__4__Impl"


    // $ANTLR start "rule__Element__Group__5"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:265:1: rule__Element__Group__5 : rule__Element__Group__5__Impl ;
    public final void rule__Element__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:269:1: ( rule__Element__Group__5__Impl )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:270:2: rule__Element__Group__5__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__Element__Group__5__Impl_in_rule__Element__Group__5494);
            rule__Element__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group__5"


    // $ANTLR start "rule__Element__Group__5__Impl"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:276:1: rule__Element__Group__5__Impl : ( '}' ) ;
    public final void rule__Element__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:280:1: ( ( '}' ) )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:281:1: ( '}' )
            {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:281:1: ( '}' )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:282:1: '}'
            {
             before(grammarAccess.getElementAccess().getRightCurlyBracketKeyword_5()); 
            match(input,12,FollowSets000.FOLLOW_12_in_rule__Element__Group__5__Impl522); 
             after(grammarAccess.getElementAccess().getRightCurlyBracketKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group__5__Impl"


    // $ANTLR start "rule__Element__Group_4__0"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:307:1: rule__Element__Group_4__0 : rule__Element__Group_4__0__Impl rule__Element__Group_4__1 ;
    public final void rule__Element__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:311:1: ( rule__Element__Group_4__0__Impl rule__Element__Group_4__1 )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:312:2: rule__Element__Group_4__0__Impl rule__Element__Group_4__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__Element__Group_4__0__Impl_in_rule__Element__Group_4__0565);
            rule__Element__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Element__Group_4__1_in_rule__Element__Group_4__0568);
            rule__Element__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_4__0"


    // $ANTLR start "rule__Element__Group_4__0__Impl"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:319:1: rule__Element__Group_4__0__Impl : ( 'ref' ) ;
    public final void rule__Element__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:323:1: ( ( 'ref' ) )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:324:1: ( 'ref' )
            {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:324:1: ( 'ref' )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:325:1: 'ref'
            {
             before(grammarAccess.getElementAccess().getRefKeyword_4_0()); 
            match(input,13,FollowSets000.FOLLOW_13_in_rule__Element__Group_4__0__Impl596); 
             after(grammarAccess.getElementAccess().getRefKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_4__0__Impl"


    // $ANTLR start "rule__Element__Group_4__1"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:338:1: rule__Element__Group_4__1 : rule__Element__Group_4__1__Impl ;
    public final void rule__Element__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:342:1: ( rule__Element__Group_4__1__Impl )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:343:2: rule__Element__Group_4__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__Element__Group_4__1__Impl_in_rule__Element__Group_4__1627);
            rule__Element__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_4__1"


    // $ANTLR start "rule__Element__Group_4__1__Impl"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:349:1: rule__Element__Group_4__1__Impl : ( ( rule__Element__ReferencedAssignment_4_1 ) ) ;
    public final void rule__Element__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:353:1: ( ( ( rule__Element__ReferencedAssignment_4_1 ) ) )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:354:1: ( ( rule__Element__ReferencedAssignment_4_1 ) )
            {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:354:1: ( ( rule__Element__ReferencedAssignment_4_1 ) )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:355:1: ( rule__Element__ReferencedAssignment_4_1 )
            {
             before(grammarAccess.getElementAccess().getReferencedAssignment_4_1()); 
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:356:1: ( rule__Element__ReferencedAssignment_4_1 )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:356:2: rule__Element__ReferencedAssignment_4_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__Element__ReferencedAssignment_4_1_in_rule__Element__Group_4__1__Impl654);
            rule__Element__ReferencedAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getElementAccess().getReferencedAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_4__1__Impl"


    // $ANTLR start "rule__Main__ElementsAssignment"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:371:1: rule__Main__ElementsAssignment : ( ruleElement ) ;
    public final void rule__Main__ElementsAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:375:1: ( ( ruleElement ) )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:376:1: ( ruleElement )
            {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:376:1: ( ruleElement )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:377:1: ruleElement
            {
             before(grammarAccess.getMainAccess().getElementsElementParserRuleCall_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleElement_in_rule__Main__ElementsAssignment693);
            ruleElement();

            state._fsp--;

             after(grammarAccess.getMainAccess().getElementsElementParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Main__ElementsAssignment"


    // $ANTLR start "rule__Element__DocAssignment_0"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:386:1: rule__Element__DocAssignment_0 : ( RULE_STRING ) ;
    public final void rule__Element__DocAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:390:1: ( ( RULE_STRING ) )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:391:1: ( RULE_STRING )
            {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:391:1: ( RULE_STRING )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:392:1: RULE_STRING
            {
             before(grammarAccess.getElementAccess().getDocSTRINGTerminalRuleCall_0_0()); 
            match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_rule__Element__DocAssignment_0724); 
             after(grammarAccess.getElementAccess().getDocSTRINGTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__DocAssignment_0"


    // $ANTLR start "rule__Element__NameAssignment_1"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:401:1: rule__Element__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Element__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:405:1: ( ( RULE_ID ) )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:406:1: ( RULE_ID )
            {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:406:1: ( RULE_ID )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:407:1: RULE_ID
            {
             before(grammarAccess.getElementAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__Element__NameAssignment_1755); 
             after(grammarAccess.getElementAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__NameAssignment_1"


    // $ANTLR start "rule__Element__ContainedAssignment_3"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:416:1: rule__Element__ContainedAssignment_3 : ( ruleElement ) ;
    public final void rule__Element__ContainedAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:420:1: ( ( ruleElement ) )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:421:1: ( ruleElement )
            {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:421:1: ( ruleElement )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:422:1: ruleElement
            {
             before(grammarAccess.getElementAccess().getContainedElementParserRuleCall_3_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleElement_in_rule__Element__ContainedAssignment_3786);
            ruleElement();

            state._fsp--;

             after(grammarAccess.getElementAccess().getContainedElementParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__ContainedAssignment_3"


    // $ANTLR start "rule__Element__ReferencedAssignment_4_1"
    // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:431:1: rule__Element__ReferencedAssignment_4_1 : ( ( RULE_ID ) ) ;
    public final void rule__Element__ReferencedAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:435:1: ( ( ( RULE_ID ) ) )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:436:1: ( ( RULE_ID ) )
            {
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:436:1: ( ( RULE_ID ) )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:437:1: ( RULE_ID )
            {
             before(grammarAccess.getElementAccess().getReferencedElementCrossReference_4_1_0()); 
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:438:1: ( RULE_ID )
            // ../org.eclipse.xtext.ui.tests/src-gen/org/eclipse/xtext/ui/tests/quickfix/ui/contentassist/antlr/internal/InternalQuickfixCrossrefTestLanguage.g:439:1: RULE_ID
            {
             before(grammarAccess.getElementAccess().getReferencedElementIDTerminalRuleCall_4_1_0_1()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__Element__ReferencedAssignment_4_1821); 
             after(grammarAccess.getElementAccess().getReferencedElementIDTerminalRuleCall_4_1_0_1()); 

            }

             after(grammarAccess.getElementAccess().getReferencedElementCrossReference_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__ReferencedAssignment_4_1"

    // Delegated rules


 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_ruleMain_in_entryRuleMain61 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleMain68 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Main__ElementsAssignment_in_ruleMain94 = new BitSet(new long[]{0x0000000000000032L});
        public static final BitSet FOLLOW_ruleElement_in_entryRuleElement122 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleElement129 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Element__Group__0_in_ruleElement155 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Element__Group__0__Impl_in_rule__Element__Group__0189 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_rule__Element__Group__1_in_rule__Element__Group__0192 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Element__DocAssignment_0_in_rule__Element__Group__0__Impl219 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Element__Group__1__Impl_in_rule__Element__Group__1250 = new BitSet(new long[]{0x0000000000000800L});
        public static final BitSet FOLLOW_rule__Element__Group__2_in_rule__Element__Group__1253 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Element__NameAssignment_1_in_rule__Element__Group__1__Impl280 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Element__Group__2__Impl_in_rule__Element__Group__2310 = new BitSet(new long[]{0x0000000000003030L});
        public static final BitSet FOLLOW_rule__Element__Group__3_in_rule__Element__Group__2313 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_11_in_rule__Element__Group__2__Impl341 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Element__Group__3__Impl_in_rule__Element__Group__3372 = new BitSet(new long[]{0x0000000000003030L});
        public static final BitSet FOLLOW_rule__Element__Group__4_in_rule__Element__Group__3375 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Element__ContainedAssignment_3_in_rule__Element__Group__3__Impl402 = new BitSet(new long[]{0x0000000000000032L});
        public static final BitSet FOLLOW_rule__Element__Group__4__Impl_in_rule__Element__Group__4433 = new BitSet(new long[]{0x0000000000003030L});
        public static final BitSet FOLLOW_rule__Element__Group__5_in_rule__Element__Group__4436 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Element__Group_4__0_in_rule__Element__Group__4__Impl463 = new BitSet(new long[]{0x0000000000002002L});
        public static final BitSet FOLLOW_rule__Element__Group__5__Impl_in_rule__Element__Group__5494 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_12_in_rule__Element__Group__5__Impl522 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Element__Group_4__0__Impl_in_rule__Element__Group_4__0565 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__Element__Group_4__1_in_rule__Element__Group_4__0568 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_13_in_rule__Element__Group_4__0__Impl596 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Element__Group_4__1__Impl_in_rule__Element__Group_4__1627 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Element__ReferencedAssignment_4_1_in_rule__Element__Group_4__1__Impl654 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleElement_in_rule__Main__ElementsAssignment693 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_rule__Element__DocAssignment_0724 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__Element__NameAssignment_1755 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleElement_in_rule__Element__ContainedAssignment_3786 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__Element__ReferencedAssignment_4_1821 = new BitSet(new long[]{0x0000000000000002L});
    }


}