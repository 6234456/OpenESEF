package eu.qiou.openesef

import java.util.PriorityQueue

class Calculation {
    val list = PriorityQueue<CalculationArc> { a, b -> if (a.order - b.order < 0) 1 else -1 }

}