package eu.qiou.openesef

import java.util.PriorityQueue

class Calculation(val lFrom: String) {
    val list = PriorityQueue<CalculationArc> { a, b -> if (a.order - b.order < 0) 1 else -1 }

    override fun toString(): String {
        val res = StringBuilder()

        while (!list.isEmpty()){
            res.append(list.poll().toString())
            res.append("\n")
        }

        res.append("${"_".repeat(lFrom.length)}\n")
        res.append(lFrom)

        return res.toString()
    }

}