/*
 * Copyright (2023) The Delta Lake Project Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.delta.kernel.defaults.internal.data.vector;

import java.util.Optional;
import static java.util.Objects.requireNonNull;

import io.delta.kernel.types.FloatType;

import static io.delta.kernel.defaults.internal.DefaultKernelUtils.checkArgument;

/**
 * {@link io.delta.kernel.data.ColumnVector} implementation for float type data.
 */
public class DefaultFloatVector
    extends AbstractColumnVector {
    private final float[] values;

    /**
     * Create an instance of {@link io.delta.kernel.data.ColumnVector} for float type.
     *
     * @param size        number of elements in the vector.
     * @param nullability Optional array of nullability value for each element in the vector.
     *                    All values in the vector are considered non-null when parameter is empty.
     * @param values      column vector values.
     */
    public DefaultFloatVector(int size, Optional<boolean[]> nullability, float[] values) {
        super(size, FloatType.INSTANCE, nullability);
        this.values = requireNonNull(values, "values is null");
        checkArgument(values.length >= size,
            "invalid number of values (%s) for given size (%s)", values.length, size);
    }

    /**
     * Get the value at given {@code rowId}. The return value is undefined and can be
     * anything, if the slot for {@code rowId} is null.
     *
     * @param rowId
     * @return
     */
    @Override
    public float getFloat(int rowId) {
        checkValidRowId(rowId);
        return values[rowId];
    }
}
