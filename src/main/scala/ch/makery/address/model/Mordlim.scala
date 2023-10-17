package ch.makery.address.model

import scala.collection.mutable.Stack

class Mordlim extends Enemy{
  val name = Mordlim.name
  val attackPattern = Mordlim.attackPattern
}

object Mordlim {
  val name = "Mordlim"
  val attackPattern = Stack[String]("GUARD","ATTACK","ATTACK","BREAK","ATTACK","BREAK")
}
