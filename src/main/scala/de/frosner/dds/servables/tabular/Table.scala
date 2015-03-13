package de.frosner.dds.servables.tabular

import de.frosner.dds.core.Servable
import de.frosner.dds.util.StringResource
import org.apache.spark.util.StatCounter
import spray.json._

case class Table(head: Seq[String], rows: Seq[Seq[Any]]) extends Servable {

  val servableType = "table"

  def contentAsJson: JsArray = {
    val jsRows = rows.map(row =>
      JsObject(OrderedMap[String, JsValue](
        head.zip(row).map{ case (columnName, value) => {
          val jsValue = value match {
            case None => JsNull
            case Some(int: Int) => JsNumber(int)
            case int: Int => JsNumber(int)
            case Some(double: Double) => JsNumber(double)
            case double: Double => JsNumber(double)
            case Some(long: Long) => JsNumber(long)
            case long: Long => JsNumber(long)
            case Some(default) => JsString(default.toString)
            case default => JsString(default.toString)
          }
          (columnName, jsValue)
        }}
      ))
    )
    JsArray(jsRows.toVector)
  }

}

object Table {

  def fromStatCounter(stat: StatCounter): Table = fromStatCounters(List("data"), List(stat))

  def fromStatCounters(labels: Seq[String], stats: Seq[StatCounter]) = {
    val head = List(
      "label", "count", "sum", "min", "max", "mean", "stdev", "variance"
    )
    val rows = labels.zip(stats).map{ case (label, stats) =>
      List(label, stats.count, stats.sum, stats.min, stats.max, stats.mean, stats.stdev, stats.variance)
    }
    Table(head, rows)
  }

  lazy val css = StringResource.read("/css/table.css")

}
