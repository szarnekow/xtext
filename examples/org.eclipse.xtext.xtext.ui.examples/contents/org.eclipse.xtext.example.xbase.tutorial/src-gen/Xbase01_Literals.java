import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Pair;

@SuppressWarnings("all")
public class Xbase01_Literals {
	public static void main(String[] args) {
		
		{/*null*/;/*"Hello World"*/;/*42*/;/*true*/;/*false*/;/*java.lang.String.class*/;
		  CollectionLiterals.newArrayList(3, 5, 7);
		  CollectionLiterals.newHashSet("foo", "bar", "baz");
		  Pair<Integer,String> _operator_mappedTo = ObjectExtensions.operator_mappedTo(1, "one");
		  Pair<Integer,String> _operator_mappedTo_1 = ObjectExtensions.operator_mappedTo(2, "two");
		  Pair<Integer,String> _operator_mappedTo_2 = ObjectExtensions.operator_mappedTo(3, "three");
		  CollectionLiterals.newHashMap(_operator_mappedTo, _operator_mappedTo_1, _operator_mappedTo_2);
		}
	}
}