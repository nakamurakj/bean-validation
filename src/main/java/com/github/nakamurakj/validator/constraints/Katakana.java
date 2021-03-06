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
package com.github.nakamurakj.validator.constraints;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import com.github.nakamurakj.validator.constraintsvalidaor.KatakanaValidator;

/**
 * 半角数値のConstraint
 */
@Documented
@ReportAsSingleViolation
@Constraint(validatedBy = {KatakanaValidator.class})
@Retention(RUNTIME)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
public @interface Katakana {

    String message() default "{com.github.nakamurakj.validator.constraints.Katakana.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /** 全角スペースを許容するか。 */
    boolean space() default false;

    /** 半角スペースを許容するか。 */
    boolean halfSpace() default false;

    /** 長音符(ー)を許容するか。 */
    boolean longMarks() default false;
}