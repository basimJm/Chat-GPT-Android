package com.blackhand.chatgpt.presentation.chat

import com.blackhand.chatgpt.domin.model.Session

interface ActionCallBack {
    fun onSessionClicked(session: Session?)
}