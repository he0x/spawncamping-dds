package de.frosner.dds.core

import org.scalatest.{FlatSpec, Matchers}
import spray.json.{JsObject, JsString, JsValue}

class ServableTest extends FlatSpec with Matchers {

  "A servable" should "have the correct JSON format" in {
    val servable = new Servable {
      override protected def contentAsJson: JsValue = JsString("test-content")
      override val servableType: String = "test-type"
    }
    servable.toJson shouldBe JsObject(
      ("type", JsString("test-type")),
      ("content", JsString("test-content"))
    )
  }

}
