/*
 * Copyright 2016 The BigDL Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
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

package com.intel.analytics.bigdl.dllib.keras.objectives

import com.intel.analytics.bigdl.dllib.nn.MarginCriterion
import com.intel.analytics.bigdl.dllib.nn.abstractnn.AbstractCriterion
import com.intel.analytics.bigdl.dllib.tensor.Tensor
import com.intel.analytics.bigdl.dllib.tensor.TensorNumericMath.TensorNumeric

import scala.reflect.ClassTag

/**
 * Creates a criterion that optimizes a two-class classification
 * hinge loss (margin-based loss) between input x (a Tensor of dimension 1) and output y.
 * @param margin if unspecified, is by default 1.
 * @param sizeAverage Boolean. Whether losses are averaged over observations for each
                  mini-batch. Default is true. If false, the losses are instead
                  summed for each mini-batch.
 */
class Hinge[@specialized(Float, Double) T: ClassTag]
(val margin: Double = 1.0, val sizeAverage: Boolean = true)
(implicit ev: TensorNumeric[T]) extends TensorLossFunction[T] {

  override val loss: AbstractCriterion[Tensor[T], Tensor[T], T] =
    MarginCriterion(margin, sizeAverage)
}

object Hinge {
  def apply[@specialized(Float, Double) T: ClassTag](
    margin: Double = 1.0,
    sizeAverage: Boolean = true)
    (implicit ev: TensorNumeric[T]) : Hinge[T] = {
    new Hinge[T](margin, sizeAverage)
  }
}
