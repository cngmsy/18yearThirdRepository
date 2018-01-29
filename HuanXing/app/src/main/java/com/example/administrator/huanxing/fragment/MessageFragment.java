package com.example.administrator.huanxing.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.administrator.huanxing.MainActivity;
import com.example.administrator.huanxing.R;
import com.example.administrator.huanxing.activity.PrivateMessageActivity;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseConversationListFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {


    private FrameLayout message_frame;
    private EaseConversationListFragment conversationListFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_message, container, false);


        EMConversation conversation = EMClient.getInstance().chatManager().getConversation("two");
        int unreadMsgCount = conversation.getUnreadMsgCount();
        Toast.makeText(getActivity(), "two的未读消息有"+unreadMsgCount, Toast.LENGTH_SHORT).show();


        initView(inflate);

        conversationListFragment = new EaseConversationListFragment();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.message_frame, conversationListFragment).commit();
        conversationListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {

            @Override
            public void onListItemClicked(EMConversation conversation) {
                Intent intent = new Intent(getActivity(), PrivateMessageActivity.class);
                intent.putExtra("username",conversation.conversationId());
                startActivity(intent);
            }
        });


        EMClient.getInstance().chatManager().addMessageListener(new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> list) {
                conversationListFragment.refresh();
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> list) {
                conversationListFragment.refresh();
            }

            @Override
            public void onMessageRead(List<EMMessage> list) {
                conversationListFragment.refresh();
            }

            @Override
            public void onMessageDelivered(List<EMMessage> list) {
                conversationListFragment.refresh();
            }

            @Override
            public void onMessageChanged(EMMessage emMessage, Object o) {
                conversationListFragment.refresh();
            }
        });


        return inflate;
    }

    private void initView(View inflate) {
        message_frame = (FrameLayout) inflate.findViewById(R.id.message_frame);
    }
}
