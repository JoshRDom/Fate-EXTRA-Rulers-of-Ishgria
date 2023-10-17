package ch.makery.address.model

import scala.collection.mutable.Stack

class AmuYunos extends Enemy{
  val name = AmuYunos.name
  val attackPattern = AmuYunos.attackPattern
}

object AmuYunos {
  val name = "Amu Yunos"
  val attackPattern = Stack[String]("ATTACK","GUARD","BREAK","ATTACK","GUARD","BREAK")
}
