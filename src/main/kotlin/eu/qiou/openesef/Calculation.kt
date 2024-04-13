package eu.qiou.openesef

import java.util.PriorityQueue
import kotlin.math.abs

class Calculation(l: List<IFRSCalculationComponent>) {
    private val list = PriorityQueue<IFRSCalculationComponent>{ a, b -> if (a.order - b.order < 0) 1 else -1 }.apply {
        addAll(l)
    }

    fun resultOf(input:Map<IFRSElement, Double>):Double{
        return list.fold(0.0){ acc, c -> acc + input.getOrDefault(c.element, 0.0)*c.weight }
    }

    override fun toString(): String {
       return list.joinToString(separator = "\n") { "${if ( abs(it.weight - 1.0) < 0.0001) "" else it.weight} ${it.element.name} " }
    }

}