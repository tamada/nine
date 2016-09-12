package com.github.ninerules.rules.indentlevel;

import java.util.Optional;

import org.eclipse.jdt.core.dom.MethodDeclaration;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.entities.Message;
import com.github.ninerules.parameters.IndentLevel;
import com.github.ninerules.rules.JdtValidator;

public class IndentLevelValidator extends JdtValidator{
    public static final Message INDENT_LEVEL = new Message("Indentation level is too much (more than %s indent level)");

    public IndentLevelValidator(StrictLevel level) {
        super(level);
    }

    @Override
    public boolean visit(MethodDeclaration node){
        checkViolation(node);
        return super.visit(node);
    }

    private void checkViolation(MethodDeclaration node){
        if(isViolated(node)){
            addViolation(buildViolation(INDENT_LEVEL, new LineCounts(startLine(node))));
        }
    }

    private boolean isViolated(MethodDeclaration node){
        return computesMaxIndentLevel(node)
                .orElse(new IndentLevel(0))
                .isGreaterThan(parameter());
    }

    private Optional<IndentLevel> computesMaxIndentLevel(MethodDeclaration node){
        IndentManipulator manipulator = new IndentManipulator(node);
        return new IndentLevelStream().stream(manipulator)
                .max((indent1, indent2) -> indent1.compareTo(indent2));
    }

    @Override
    public Class<IndentLevel> parameterClass() {
        return IndentLevel.class;
    }
}