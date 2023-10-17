package ch.makery.address.model

import scala.collection.mutable.Stack

abstract class Enemy {
  val name : String
  val attackPattern : Stack[String]
}
