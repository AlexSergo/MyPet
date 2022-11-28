package com.example.mypet.presentation.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mypet.databinding.GraphItemBinding
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class GraphAdapter: RecyclerView.Adapter<GraphAdapter.GraphViewHolder>() {

    private var graphs = mutableListOf<Graph>()

    fun set(graphs: List<Graph>){
        this.graphs = graphs.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GraphViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GraphItemBinding.inflate(inflater)
        return GraphViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GraphViewHolder, position: Int) {
        graphs.getOrNull(position)?.let { graph->
            holder.init(graph)
        }
    }

    override fun getItemCount(): Int {
        return graphs.count()
    }

    class GraphViewHolder(private val binding: GraphItemBinding): RecyclerView.ViewHolder(binding.root){
        fun init(graph: Graph){
            binding.name.text = graph.name
            val dataPointArray = arrayOfNulls<DataPoint>(graph.x.size)
            for (i in 0 until graph.x.count())
                dataPointArray[i] = DataPoint(graph.x[i].toDouble(),graph.y[i].toDouble())
            val series = LineGraphSeries(dataPointArray)
            binding.chart.addSeries(series)
            series.color = Color.GREEN
        }
    }
}
