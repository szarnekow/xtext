package org.eclipse.xtext.serializer.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalSequencerTestLanguageLexer extends Lexer {
    public static final int RULE_ML_COMMENT=10;
    public static final int T__35=35;
    public static final int RULE_ID=4;
    public static final int T__28=28;
    public static final int T__23=23;
    public static final int T__36=36;
    public static final int T__20=20;
    public static final int RULE_STRING=9;
    public static final int T__21=21;
    public static final int T__19=19;
    public static final int RULE_ID3=7;
    public static final int T__33=33;
    public static final int T__14=14;
    public static final int T__22=22;
    public static final int T__29=29;
    public static final int RULE_ID1=5;
    public static final int T__30=30;
    public static final int RULE_WS=12;
    public static final int T__17=17;
    public static final int T__31=31;
    public static final int RULE_INT=8;
    public static final int EOF=-1;
    public static final int T__27=27;
    public static final int T__16=16;
    public static final int T__32=32;
    public static final int T__24=24;
    public static final int T__38=38;
    public static final int T__37=37;
    public static final int T__26=26;
    public static final int RULE_ANY_OTHER=13;
    public static final int T__25=25;
    public static final int RULE_SL_COMMENT=11;
    public static final int T__34=34;
    public static final int RULE_ID2=6;
    public static final int T__18=18;
    public static final int T__15=15;

    // delegates
    // delegators

    public InternalSequencerTestLanguageLexer() {;} 
    public InternalSequencerTestLanguageLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalSequencerTestLanguageLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g"; }

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:11:7: ( '#1' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:11:9: '#1'
            {
            match("#1"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:12:7: ( '#2' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:12:9: '#2'
            {
            match("#2"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:13:7: ( 'kw1' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:13:9: 'kw1'
            {
            match("kw1"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:14:7: ( 'kw2' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:14:9: 'kw2'
            {
            match("kw2"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:15:7: ( '#3' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:15:9: '#3'
            {
            match("#3"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:16:7: ( 'kw3' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:16:9: 'kw3'
            {
            match("kw3"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:17:7: ( '#4' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:17:9: '#4'
            {
            match("#4"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:18:7: ( '#5' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:18:9: '#5'
            {
            match("#5"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:19:7: ( 'kw4' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:19:9: 'kw4'
            {
            match("kw4"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:20:7: ( 'kw5' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:20:9: 'kw5'
            {
            match("kw5"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:21:7: ( '#6' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:21:9: '#6'
            {
            match("#6"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:22:7: ( ',' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:22:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:23:7: ( '#7' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:23:9: '#7'
            {
            match("#7"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:24:7: ( '#8' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:24:9: '#8'
            {
            match("#8"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:25:7: ( '#9' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:25:9: '#9'
            {
            match("#9"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:26:7: ( 'kw' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:26:9: 'kw'
            {
            match("kw"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:27:7: ( '#10' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:27:9: '#10'
            {
            match("#10"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:28:7: ( '#11' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:28:9: '#11'
            {
            match("#11"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:29:7: ( '#12' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:29:9: '#12'
            {
            match("#12"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:30:7: ( '#13' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:30:9: '#13'
            {
            match("#13"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:31:7: ( '#14' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:31:9: '#14'
            {
            match("#14"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:32:7: ( '#15' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:32:9: '#15'
            {
            match("#15"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:33:7: ( '#16' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:33:9: '#16'
            {
            match("#16"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:34:7: ( '#17' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:34:9: '#17'
            {
            match("#17"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:35:7: ( '#18' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:35:9: '#18'
            {
            match("#18"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "RULE_ID1"
    public final void mRULE_ID1() throws RecognitionException {
        try {
            int _type = RULE_ID1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2227:10: ( '$1' RULE_ID )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2227:12: '$1' RULE_ID
            {
            match("$1"); 

            mRULE_ID(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID1"

    // $ANTLR start "RULE_ID2"
    public final void mRULE_ID2() throws RecognitionException {
        try {
            int _type = RULE_ID2;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2229:10: ( '$2' RULE_ID )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2229:12: '$2' RULE_ID
            {
            match("$2"); 

            mRULE_ID(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID2"

    // $ANTLR start "RULE_ID3"
    public final void mRULE_ID3() throws RecognitionException {
        try {
            int _type = RULE_ID3;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2231:10: ( '$3' RULE_ID )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2231:12: '$3' RULE_ID
            {
            match("$3"); 

            mRULE_ID(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID3"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2233:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2233:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2233:11: ( '^' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='^') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2233:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2233:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2235:10: ( ( '0' .. '9' )+ )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2235:12: ( '0' .. '9' )+
            {
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2235:12: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2235:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2237:13: ( ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2237:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2237:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='\"') ) {
                alt6=1;
            }
            else if ( (LA6_0=='\'') ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2237:16: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2237:20: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop4:
                    do {
                        int alt4=3;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0=='\\') ) {
                            alt4=1;
                        }
                        else if ( ((LA4_0>='\u0000' && LA4_0<='!')||(LA4_0>='#' && LA4_0<='[')||(LA4_0>=']' && LA4_0<='\uFFFF')) ) {
                            alt4=2;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2237:21: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||(input.LA(1)>='t' && input.LA(1)<='u') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2237:66: ~ ( ( '\\\\' | '\"' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2237:86: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2237:91: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop5:
                    do {
                        int alt5=3;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0=='\\') ) {
                            alt5=1;
                        }
                        else if ( ((LA5_0>='\u0000' && LA5_0<='&')||(LA5_0>='(' && LA5_0<='[')||(LA5_0>=']' && LA5_0<='\uFFFF')) ) {
                            alt5=2;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2237:92: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||(input.LA(1)>='t' && input.LA(1)<='u') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2237:137: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2239:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2239:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2239:24: ( options {greedy=false; } : . )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='*') ) {
                    int LA7_1 = input.LA(2);

                    if ( (LA7_1=='/') ) {
                        alt7=2;
                    }
                    else if ( ((LA7_1>='\u0000' && LA7_1<='.')||(LA7_1>='0' && LA7_1<='\uFFFF')) ) {
                        alt7=1;
                    }


                }
                else if ( ((LA7_0>='\u0000' && LA7_0<=')')||(LA7_0>='+' && LA7_0<='\uFFFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2239:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2241:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2241:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2241:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='\u0000' && LA8_0<='\t')||(LA8_0>='\u000B' && LA8_0<='\f')||(LA8_0>='\u000E' && LA8_0<='\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2241:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2241:40: ( ( '\\r' )? '\\n' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='\n'||LA10_0=='\r') ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2241:41: ( '\\r' )? '\\n'
                    {
                    // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2241:41: ( '\\r' )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0=='\r') ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2241:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2243:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2243:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2243:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>='\t' && LA11_0<='\n')||LA11_0=='\r'||LA11_0==' ') ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2245:16: ( . )
            // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:2245:18: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
        // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:8: ( T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | RULE_ID1 | RULE_ID2 | RULE_ID3 | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt12=35;
        alt12 = dfa12.predict(input);
        switch (alt12) {
            case 1 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:10: T__14
                {
                mT__14(); 

                }
                break;
            case 2 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:16: T__15
                {
                mT__15(); 

                }
                break;
            case 3 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:22: T__16
                {
                mT__16(); 

                }
                break;
            case 4 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:28: T__17
                {
                mT__17(); 

                }
                break;
            case 5 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:34: T__18
                {
                mT__18(); 

                }
                break;
            case 6 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:40: T__19
                {
                mT__19(); 

                }
                break;
            case 7 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:46: T__20
                {
                mT__20(); 

                }
                break;
            case 8 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:52: T__21
                {
                mT__21(); 

                }
                break;
            case 9 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:58: T__22
                {
                mT__22(); 

                }
                break;
            case 10 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:64: T__23
                {
                mT__23(); 

                }
                break;
            case 11 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:70: T__24
                {
                mT__24(); 

                }
                break;
            case 12 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:76: T__25
                {
                mT__25(); 

                }
                break;
            case 13 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:82: T__26
                {
                mT__26(); 

                }
                break;
            case 14 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:88: T__27
                {
                mT__27(); 

                }
                break;
            case 15 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:94: T__28
                {
                mT__28(); 

                }
                break;
            case 16 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:100: T__29
                {
                mT__29(); 

                }
                break;
            case 17 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:106: T__30
                {
                mT__30(); 

                }
                break;
            case 18 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:112: T__31
                {
                mT__31(); 

                }
                break;
            case 19 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:118: T__32
                {
                mT__32(); 

                }
                break;
            case 20 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:124: T__33
                {
                mT__33(); 

                }
                break;
            case 21 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:130: T__34
                {
                mT__34(); 

                }
                break;
            case 22 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:136: T__35
                {
                mT__35(); 

                }
                break;
            case 23 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:142: T__36
                {
                mT__36(); 

                }
                break;
            case 24 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:148: T__37
                {
                mT__37(); 

                }
                break;
            case 25 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:154: T__38
                {
                mT__38(); 

                }
                break;
            case 26 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:160: RULE_ID1
                {
                mRULE_ID1(); 

                }
                break;
            case 27 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:169: RULE_ID2
                {
                mRULE_ID2(); 

                }
                break;
            case 28 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:178: RULE_ID3
                {
                mRULE_ID3(); 

                }
                break;
            case 29 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:187: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 30 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:195: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 31 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:204: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 32 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:216: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 33 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:232: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 34 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:248: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 35 :
                // ../org.eclipse.xtext.tests/src-gen/org/eclipse/xtext/serializer/parser/antlr/internal/InternalSequencerTestLanguage.g:1:256: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA12 dfa12 = new DFA12(this);
    static final String DFA12_eotS =
        "\1\uffff\1\14\1\27\1\uffff\2\14\2\uffff\3\14\2\uffff\1\52\10\uffff"+
        "\1\60\24\uffff\1\61\1\62\1\63\1\64\1\65\6\uffff";
    static final String DFA12_eofS =
        "\66\uffff";
    static final String DFA12_minS =
        "\1\0\1\61\1\167\1\uffff\1\61\1\101\2\uffff\2\0\1\52\2\uffff\1\60"+
        "\10\uffff\1\60\24\uffff\5\60\6\uffff";
    static final String DFA12_maxS =
        "\1\uffff\1\71\1\167\1\uffff\1\63\1\172\2\uffff\2\uffff\1\57\2\uffff"+
        "\1\70\10\uffff\1\172\24\uffff\5\172\6\uffff";
    static final String DFA12_acceptS =
        "\3\uffff\1\14\2\uffff\1\35\1\36\3\uffff\1\42\1\43\1\uffff\1\2\1"+
        "\5\1\7\1\10\1\13\1\15\1\16\1\17\1\uffff\1\35\1\14\1\32\1\33\1\34"+
        "\1\36\1\37\1\40\1\41\1\42\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30"+
        "\1\31\1\1\5\uffff\1\20\1\3\1\4\1\6\1\11\1\12";
    static final String DFA12_specialS =
        "\1\1\7\uffff\1\0\1\2\54\uffff}>";
    static final String[] DFA12_transitionS = {
            "\11\14\2\13\2\14\1\13\22\14\1\13\1\14\1\10\1\1\1\4\2\14\1\11"+
            "\4\14\1\3\2\14\1\12\12\7\7\14\32\6\3\14\1\5\1\6\1\14\12\6\1"+
            "\2\17\6\uff85\14",
            "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25",
            "\1\26",
            "",
            "\1\31\1\32\1\33",
            "\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "",
            "\0\35",
            "\0\35",
            "\1\36\4\uffff\1\37",
            "",
            "",
            "\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\27\1\53\1\54\1\55\1\56\1\57\4\27\7\uffff\32\27\4\uffff\1"+
            "\27\1\uffff\32\27",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | RULE_ID1 | RULE_ID2 | RULE_ID3 | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA12_8 = input.LA(1);

                        s = -1;
                        if ( ((LA12_8>='\u0000' && LA12_8<='\uFFFF')) ) {s = 29;}

                        else s = 12;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA12_0 = input.LA(1);

                        s = -1;
                        if ( (LA12_0=='#') ) {s = 1;}

                        else if ( (LA12_0=='k') ) {s = 2;}

                        else if ( (LA12_0==',') ) {s = 3;}

                        else if ( (LA12_0=='$') ) {s = 4;}

                        else if ( (LA12_0=='^') ) {s = 5;}

                        else if ( ((LA12_0>='A' && LA12_0<='Z')||LA12_0=='_'||(LA12_0>='a' && LA12_0<='j')||(LA12_0>='l' && LA12_0<='z')) ) {s = 6;}

                        else if ( ((LA12_0>='0' && LA12_0<='9')) ) {s = 7;}

                        else if ( (LA12_0=='\"') ) {s = 8;}

                        else if ( (LA12_0=='\'') ) {s = 9;}

                        else if ( (LA12_0=='/') ) {s = 10;}

                        else if ( ((LA12_0>='\t' && LA12_0<='\n')||LA12_0=='\r'||LA12_0==' ') ) {s = 11;}

                        else if ( ((LA12_0>='\u0000' && LA12_0<='\b')||(LA12_0>='\u000B' && LA12_0<='\f')||(LA12_0>='\u000E' && LA12_0<='\u001F')||LA12_0=='!'||(LA12_0>='%' && LA12_0<='&')||(LA12_0>='(' && LA12_0<='+')||(LA12_0>='-' && LA12_0<='.')||(LA12_0>=':' && LA12_0<='@')||(LA12_0>='[' && LA12_0<=']')||LA12_0=='`'||(LA12_0>='{' && LA12_0<='\uFFFF')) ) {s = 12;}

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA12_9 = input.LA(1);

                        s = -1;
                        if ( ((LA12_9>='\u0000' && LA12_9<='\uFFFF')) ) {s = 29;}

                        else s = 12;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 12, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}