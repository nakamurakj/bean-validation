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
package com.github.nakamurakj.validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.StringUtils;

/**
 * BeanValidator(@Validを利用しない場合のHandler)
 */
public final class BeanValidator {

    /** {@code javax.validation.ValidatorFactory} */
    private static final ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();

    /**
     * コンストラクタ
     */
    private BeanValidator() {
        // ignore
    }

    /**
     * {@code javax.validation.Validator}を取得する。
     *
     * @return {@code javax.validation.Validator}
     */
    public static Validator getValidator() {
        return FACTORY.getValidator();
    }

    /**
     * javax.validationが設定されているBeanのValidationを行う。
     *
     * @param bean 対象のBean
     * @return {@code ValidateMessage}のリスト
     * @throws IllegalArgumentException 引数エラー
     */
    public static <T> List<ValidateMessage<T>> validateBean(final T bean)
            throws IllegalArgumentException {
        if (bean == null) {
            throw new IllegalArgumentException("bean is null");
        }

        final Set<ConstraintViolation<T>> constraintViolations = getValidator().validate(bean);
        final int size = constraintViolations.size();
        final List<ValidateMessage<T>> messages = new ArrayList<ValidateMessage<T>>(size);
        if (size > 0) {
            final Iterator<ConstraintViolation<T>> ite = constraintViolations.iterator();
            while (ite.hasNext()) {
                messages.add(new ValidateMessage<T>(ite.next()));
            }
        }
        return messages;
    }

    /**
     * バリデーションエラーのエラーメッセージを保持するクラス。
     *
     * @param <T> 検証を行うクラス
     */
    public static final class ValidateMessage<T> {

        /**
         * {@link ConstraintViolation}
         */
        private final ConstraintViolation<T> constraintViolation;

        /** バリデーションエラーのBean */
        private final T rootBean;

        /** バリデーションエラーのBeanのクラス名 */
        private final String rootBeanName;

        /** エラーのプロパティ名 */
        private final String targetProperty;

        /** エラーメッセージ */
        private final String message;

        /**
         * コンストラクタ
         *
         * @param vio {@code ConstraintViolation}
         */
        private ValidateMessage(final ConstraintViolation<T> vio) {
            constraintViolation = vio;
            rootBean = vio.getRootBean();
            rootBeanName = vio.getRootBeanClass().getSimpleName();
            targetProperty = vio.getPropertyPath().toString();
            message = vio.getMessage();
        }

        /**
         * {@link ConstraintViolation}を取得する。
         *
         * @return constraintViolation {@link ConstraintViolation}
         */
        public ConstraintViolation<T> getConstraintViolation() {
            return constraintViolation;
        }

        /**
         * バリデーションエラーのBeanを取得する。
         *
         * @return rootBean バリデーションエラーのBean
         */
        public T getRootBean() {
            return rootBean;
        }

        /**
         * バリデーションエラーのBeanのクラス名を取得する。
         *
         * @return rootBeanName バリデーションエラーのBeanのクラス名
         */
        public String getRootBeanName() {
            return rootBeanName;
        }

        /**
         * エラーのプロパティ名を取得する。
         *
         * @return targetProperty エラーのプロパティ名
         */
        public String getTargetProperty() {
            return targetProperty;
        }

        /**
         * エラーメッセージを取得する。
         *
         * @return message エラーメッセージ
         */
        public String getMessage() {
            return message;
        }

        /**
         * エラーを取得する。
         *
         * @return エラー
         */
        public String getError() {
            return "Invalid" + StringUtils.capitalize(targetProperty);
        }

        /**
         * エラーメッセージを作成する。
         *
         * @return エラーメッセージ
         */
        public String createErrorMessage() {
            return rootBeanName + "#" + targetProperty + "[" + message + "]";
        }
    }

}
