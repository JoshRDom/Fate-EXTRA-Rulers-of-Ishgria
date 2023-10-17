package ch.makery.address.model

import scala.collection.mutable.Stack

class Barion extends Enemy{
  val name = Barion.name
  val attackPattern = Barion.attackPattern
}

object Barion {
  val name = "Barion"
  val attackPattern = Stack[String]("BREAK","GUARD","BREAK","ATTACK","BREAK","GUARD")
}
