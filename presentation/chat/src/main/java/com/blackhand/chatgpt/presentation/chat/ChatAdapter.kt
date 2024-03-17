package com.blackhand.chatgpt.presentation.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.blackhand.chatgpt.domin.model.ChatHistory
import com.blackhand.chatgpt.presentation.chat.databinding.ItemChatHistoryBinding

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var chatHistories: List<ChatHistory>? = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ChatHistoryViewHolder(
            DataBindingUtil.inflate(
                inflater,
                R.layout.item_chat_history,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ChatHistoryViewHolder)?.bind(chatHistories?.get(position))
    }

    override fun getItemCount(): Int = chatHistories?.size ?: 0

    class ChatHistoryViewHolder(private val binding: ItemChatHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chatHistory: ChatHistory?) {
            binding.message = chatHistory
            binding.executePendingBindings()
        }
    }
}
