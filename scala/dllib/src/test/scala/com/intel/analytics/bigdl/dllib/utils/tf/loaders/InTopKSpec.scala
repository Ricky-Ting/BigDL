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
package com.intel.analytics.bigdl.dllib.utils.tf.loaders

import com.intel.analytics.bigdl.dllib.tensor.Tensor
import com.intel.analytics.bigdl.dllib.utils.{T, TestUtils}
import com.intel.analytics.bigdl.dllib.utils.tf.Tensorflow.{intAttr, typeAttr}
import com.intel.analytics.bigdl.dllib.utils.tf.TensorflowSpecHelper
import org.tensorflow.framework.{DataType, NodeDef}

class InTopKSpec extends TensorflowSpecHelper {
  "InTopK" should "be correct" in {
    val (a, b) = getResult[Float, Boolean](
      NodeDef.newBuilder()
        .setName("inv_grad_test")
        .putAttr("T", typeAttr(DataType.DT_INT32))
        .putAttr("k", intAttr(2))
        .setOp("InTopK"),
      Seq(Tensor[Float](T(T(1, 2, 3, 4), T(3, 2, 5, 6))), Tensor[Int](T(1, 2))),
      0
    )
    a.map(b, (e1, e2) => {
      TestUtils.conditionFailTest(e1 == e2, "output not match")
      e2
    })
  }
}
