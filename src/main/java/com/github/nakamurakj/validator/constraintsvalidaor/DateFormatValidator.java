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

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.github.nakamurakj.validator.Validators;
import com.github.nakamurakj.validator.constraints.DateFormat;

/**
 * 日付文字列のConstraintValidator
 */
public class DateFormatValidator implements ConstraintValidator<DateFormat, String> {

    /** プロファイル種別 */
    private String pattern;

    /**
     * {@inheritDoc}
     */
    public void initialize(DateFormat constraint) {
        this.pattern = constraint.value();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isValid(String value, ConstraintValidatorContext constraintContext) {
        if (value == null) {
            return true;
        }
        return Validators.isDateFormat(value, pattern);
    }

}