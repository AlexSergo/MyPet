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
            holder.init()
        }
    }

    override fun getItemCount(): Int {
        return graphs.count()
    }

    class GraphViewHolder(private val binding: GraphItemBinding): RecyclerView.ViewHolder(binding.root){
        fun init(){
            val series = LineGraphSeries<DataPoint>()
            series.appendData(DataPoint(0.0,0.0), true, 10)
            series.appendData(DataPoint(1.0,1.0), true, 10)
            series.appendData(DataPoint(3.0,2.0), true, 10)
            series.appendData(DataPoint(4.0,5.0), true, 10)
            binding.chart.addSeries(series)
            series.color = Color.GREEN
        }
    }
}
