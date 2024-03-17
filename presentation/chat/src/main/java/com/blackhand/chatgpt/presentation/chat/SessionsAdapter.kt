package com.blackhand.chatgpt.presentation.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blackhand.chatgpt.domin.model.Session
import com.blackhand.chatgpt.presentation.chat.databinding.SessionsItemListBinding

class SessionsAdapter(private val session: List<Session>? , private val actionCallBack: ActionCallBack) :
    RecyclerView.Adapter<SessionsAdapter.SessionsViewHolder>() {

    inner class SessionsViewHolder(private val binding: SessionsItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(session: Session?) {
            binding.session = session
            binding.cvSession.setOnClickListener {
                actionCallBack.onSessionClicked(session)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionsViewHolder {
        val binding =
            SessionsItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SessionsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return session?.size ?: 0
    }

    override fun onBindViewHolder(holder: SessionsViewHolder, position: Int) {
        val session = session?.get(position)
        holder.bind(session)
    }
}