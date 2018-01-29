package com.example.administrator.huanxing.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.huanxing.R;
import com.example.administrator.huanxing.activity.PrivateMessageActivity;
import com.example.administrator.huanxing.view.ContactItemView;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.widget.EaseContactList;
import com.hyphenate.easeui.widget.EaseTitleBar;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.action;
import static com.example.administrator.huanxing.R.id.action0;
import static com.example.administrator.huanxing.R.id.friends;
import static com.hyphenate.easeui.model.EaseDefaultEmojiconDatas.getData;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment implements EMValueCallBack<List<String>> {


    private EaseTitleBar title_bar;
    private EaseContactList friends_listview;
    private ListView listView;
    List<EaseUser> listdata=new ArrayList<>();
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    friends_listview.refresh();
                    break;
            }
        }
    };
    private FragmentActivity activity;
    private ContactItemView applicationItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_frame, container, false);
        initView(inflate);
         activity = getActivity();
        getFriends();
        setListener();

        int unreadMsgsCount = EMClient.getInstance().chatManager().getUnreadMsgsCount();
        Toast.makeText(activity, "全部的未读消息有"+unreadMsgsCount, Toast.LENGTH_SHORT).show();
        return inflate;
    }

    private void setListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(activity, PrivateMessageActivity.class);
                EaseUser easeUser = listdata.get(i);
                intent.putExtra("username",easeUser.getUsername());
                startActivity(intent);
            }
        });
    }

    private void initView(View inflate) {
        title_bar = (EaseTitleBar) inflate.findViewById(R.id.title_bar);
        title_bar.setTitle("联系人");
        friends_listview = (EaseContactList) inflate.findViewById(R.id.friends_listview);
        friends_listview.init(listdata);
        listView = friends_listview.getListView();
    }

    //获取好友
    public void getFriends() {
        EMClient.getInstance().contactManager().aysncGetAllContactsFromServer(this);
    }

    @Override
    public void onSuccess(List<String> strings) {
        for (String name:strings){
            listdata.add(new EaseUser(name));

        }
        Message message=new Message();
        message.what=100;
        handler.sendMessage(message);
    }

    @Override
    public void onError(int i, String s) {

    }


}
