package com.github.ninerules.rules;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.Target;
import com.github.ninerules.rules.results.Results;

public class Rules {
    private Validators validators;
    private StrictLevel level = StrictLevel.STRICT;

    public Rules(){
        this(StrictLevel.STRICT);
    }

    public Rules(StrictLevel level){
        validators = new Validators(level);        
    }

    public Results validate(final Target unit){
        return validators.validateEach(level, unit);
    }
}
