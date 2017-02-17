package com.github.ninerules.parameters;

import java.util.Comparator;

public class ParameterComparator implements Comparator<Parameter>{
    @Override
    public int compare(Parameter o1, Parameter o2) {
        PredicateHelper<Parameter, Integer> helper = buildHelper();
        return helper.apply(o1, o2);
    }

    private PredicateHelper<Parameter, Integer> buildHelper(){
        PredicateHelper<Parameter, Integer> helper = new PredicateHelper<>();
        registerPredicates(helper);
        return helper;
    }

    private void registerPredicates(PredicateHelper<Parameter, Integer> helper){
        helper.register(Parameter::isLessThan, -1);
        helper.register(Parameter::isGreaterThan, 1);
        helper.register(Parameter::isEqualsTo, 0);
    }
}
