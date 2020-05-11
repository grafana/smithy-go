/*
 * Copyright 2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package software.amazon.smithy.go.codegen;

import software.amazon.smithy.codegen.core.Symbol;
import software.amazon.smithy.model.shapes.Shape;

/**
 * Common symbol utility building functions.
 */
public final class SymbolUtils {

    public static final String POINTABLE = "pointable";
    public static final String NAMESPACE_ALIAS = "namespaceAlias";

    private SymbolUtils() {
    }

    /**
     * Create a value symbol builder.
     *
     * @param typeName the name of the type.
     * @return the symbol builder type.
     */
    public static Symbol.Builder createValueSymbolBuilder(String typeName) {
        return Symbol.builder()
                .putProperty(POINTABLE, false)
                .name(typeName);
    }

    /**
     * Create a pointable symbol builder.
     *
     * @param typeName the name of the type.
     * @return the symbol builder.
     */
    public static Symbol.Builder createPointableSymbolBuilder(String typeName) {
        return Symbol.builder()
                .putProperty(POINTABLE, true)
                .name(typeName);
    }

    /**
     * Create a value symbol builder.
     *
     * @param shape the shape that the type is for.
     * @param typeName the name of the type.
     * @return the symbol builder.
     */
    public static Symbol.Builder createValueSymbolBuilder(Shape shape, String typeName) {
        return createValueSymbolBuilder(typeName).putProperty("shape", shape);
    }

    /**
     * Create a pointable symbol builder.
     *
     * @param shape the shape that the type is for.
     * @param typeName the name of the type.
     * @return the symbol builder.
     */
    public static Symbol.Builder createPointableSymbolBuilder(Shape shape, String typeName) {
        return createPointableSymbolBuilder(typeName).putProperty("shape", shape);
    }

    /**
     * Create a pointable symbol builder.
     *
     * @param typeName the name of the type.
     * @param namespace the namespace of the type.
     * @return the symbol builder.
     */
    public static Symbol.Builder createPointableSymbolBuilder(String typeName, String namespace) {
        return createPointableSymbolBuilder(typeName).namespace(namespace, ".");
    }

    /**
     * Create a value symbol builder.
     *
     * @param typeName the name of the type.
     * @param namespace the namespace of the type.
     * @return the symbol builder.
     */
    public static Symbol.Builder createValueSymbolBuilder(String typeName, String namespace) {
        return createValueSymbolBuilder(typeName).namespace(namespace, ".");
    }

    /**
     * Create a pointable symbol builder.
     *
     * @param shape the shape that the type is for.
     * @param typeName the name of the type.
     * @param namespace the namespace of the type.
     * @return the symbol builder.
     */
    public static Symbol.Builder createPointableSymbolBuilder(Shape shape, String typeName, String namespace) {
        return createPointableSymbolBuilder(shape, typeName).namespace(namespace, ".");
    }

    /**
     * Create a value symbol builder.
     *
     * @param shape the shape that the type is for.
     * @param typeName the name of the type.
     * @param namespace the namespace of the type.
     * @return the symbol builder.
     */
    public static Symbol.Builder createValueSymbolBuilder(Shape shape, String typeName, String namespace) {
        return createValueSymbolBuilder(shape, typeName).namespace(namespace, ".");
    }

    /**
     * Create a pointable symbol builder.
     *
     * @param shape the shape that the type is for.
     * @param typeName the name of the type.
     * @param namespace the namespace of the type.
     * @return the symbol builder.
     */
    public static Symbol.Builder createPointableSymbolBuilder(Shape shape, String typeName, GoDependency namespace) {
        return setImportedNamespace(createPointableSymbolBuilder(shape, typeName), namespace);
    }

    /**
     * Create a value symbol builder.
     *
     * @param shape the shape that the type is for.
     * @param typeName the name of the type.
     * @param namespace the namespace of the type.
     * @return the symbol builder.
     */
    public static Symbol.Builder createValueSymbolBuilder(Shape shape, String typeName, GoDependency namespace) {
        return setImportedNamespace(createValueSymbolBuilder(shape, typeName), namespace);
    }

    /**
     * Create a pointable symbol builder.
     *
     * @param typeName the name of the type.
     * @param namespace the namespace of the type.
     * @return the symbol builder.
     */
    public static Symbol.Builder createPointableSymbolBuilder(String typeName, GoDependency namespace) {
        return setImportedNamespace(createPointableSymbolBuilder(typeName), namespace);
    }

    /**
     * Create a value symbol builder.
     *
     * @param typeName the name of the type.
     * @param namespace the namespace of the type.
     * @return the symbol builder.
     */
    public static Symbol.Builder createValueSymbolBuilder(String typeName, GoDependency namespace) {
        return setImportedNamespace(createValueSymbolBuilder(typeName), namespace);
    }

    private static Symbol.Builder setImportedNamespace(Symbol.Builder builder, GoDependency dependency) {
        return builder.namespace(dependency.importPath, ".")
                .addDependency(dependency)
                .putProperty(NAMESPACE_ALIAS, dependency.alias);
    }
}