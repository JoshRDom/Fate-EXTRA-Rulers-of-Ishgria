package ch.makery.address.model

import scala.collection.mutable.Stack

class Beiorg extends Enemy{
  val name = Beiorg.name
  val attackPattern = Beiorg.attackPattern
}

object Beiorg {
  val name = "Beiorg"
  val attackPattern = Stack[String]("GUARD","ATTACK","ATTACK","BREAK","ATTACK","ATTACK")
}
