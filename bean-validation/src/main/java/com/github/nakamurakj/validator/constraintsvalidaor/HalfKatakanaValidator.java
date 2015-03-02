
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

import com.github.nakamurakj.validator.constraints.HalfKatakana;

/**
 * 半角数値のConstraintValidator
 */
public class HalfKatakanaValidator implements ConstraintValidator<HalfKatakana, String> {

    /**
     * {@inheritDoc}
     */
    public void initialize(HalfKatakana constraint) {
    }

    /**
     * {@inheritDoc}
     */
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if (object == null) {
            return true;
        }
        return isHalfKatakana(object);
    }

}