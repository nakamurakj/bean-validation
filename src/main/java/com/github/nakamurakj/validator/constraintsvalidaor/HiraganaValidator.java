/*
 * Copyright 2015 https://github.com/nakamurakj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.nakamurakj.validator.constraintsvalidaor;

import static com.github.nakamurakj.validator.Validators.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.github.nakamurakj.validator.constraints.Hiragana;

/**
 * ひらがなのConstraintValidator
 */
public class HiraganaValidator implements ConstraintValidator<Hiragana, String> {

    /** 全角スペースを許容するか。 */
    boolean space;
    /** 半角スペースを許容するか。 */
    boolean halfSpace;
    /** 長音符(ー)を許容するか。 */
    boolean longMarks;

    /**
     * {@inheritDoc}
     */
    public void initialize(Hiragana constraint) {
        space = constraint.space();
        halfSpace = constraint.halfSpace();
        longMarks = constraint.longMarks();
    }


    /**
     * {@inheritDoc}
     */
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if (object == null) {
            return true;
        }
        return isHiragana(object, space, halfSpace, longMarks);
    }

}
