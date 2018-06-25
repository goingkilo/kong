import java.util.HashSet;
import java.util.Set;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import java.io.File;
import java.util.Scanner; 

public class ASTEx {

	public static void main(String args[]){

		if( args.length != 1 ) { 
			System.out.println("USAGE : java ASTEx filename " ); 
			System.exit(01); 
		}

		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource( read(args[0]).toCharArray() );
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		//ASTNode node = parser.createAST(null);
 
 
		final CompilationUnit compilationUnit = (CompilationUnit) parser.createAST(null);
 
		compilationUnit .accept(new ASTVisitor() {
 
			Set names = new HashSet();
 
			public boolean visit(VariableDeclarationFragment node) {
				SimpleName name = node.getName();
				this.names.add(name.getIdentifier());
				System.out.println("Declaration of '"+name+"' at line"+compilationUnit.getLineNumber(name.getStartPosition()));
				return false; // do not continue to avoid usage info
			}
 
			public boolean visit(SimpleName node) {
				if (this.names.contains(node.getIdentifier())) {
				System.out.println("Usage of '" + node + "' at line " +	compilationUnit.getLineNumber(node.getStartPosition()));
				}
				return true;
			}
 
		});
	}
	
	static String read(String filename) {
		try {
			return new Scanner(new File(filename)).useDelimiter("\\Z").next();
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
