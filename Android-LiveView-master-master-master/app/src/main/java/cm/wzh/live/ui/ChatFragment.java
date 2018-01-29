package cm.wzh.live.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import cm.wzh.live.R;
import cm.wzh.live.adapter.MemberAdapter;
import cm.wzh.live.adapter.MessageAdapter;
import cm.wzh.live.entity.Gift;
import cm.wzh.live.entity.Member;
import cm.wzh.live.entity.Message;
import cm.wzh.live.utils.CharUtils;
import cm.wzh.live.view.FragmentDialog;
import cm.wzh.live.view.FragmentGiftDialog;
import cm.wzh.live.view.GiftItemView;
import cm.wzh.live.view.HorizontialListView;
import cm.wzh.live.view.MyVideoView;
import tyrantgit.widget.HeartLayout;

/**
 * author：Administrator on 2016/12/26 09:35
 * description:文件说明
 * version:版本
 */
public class ChatFragment extends Fragment implements View.OnClickListener, View.OnLayoutChangeListener {
    private HorizontialListView listview;
    private ListView messageList;
    private GiftItemView giftView;
    private MemberAdapter mAdapter;
    private MessageAdapter messageAdapter;
    private ArrayList<Member> members;
    private ArrayList<Message> messages;
    private ArrayList<Gift> gifts;
    private HeartLayout heartLayout;
    private Random mRandom;
    private Timer mTimer = new Timer();
    private View sendView, menuView, topView;
    private EditText sendEditText;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;
    private View rootView;
    private View text;
    private RelativeLayout mRoot;
    private View mGiftView;
    private Handler mHandler;
    private boolean giftAnimationPlayEnd = true;
    private View inflate;
    private int mScreenWidth;
    private View lunchuang;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_chat, null, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        WindowManager wm = getActivity().getWindowManager();
        mScreenWidth = wm.getDefaultDisplay().getWidth();
        mHandler = new Handler();
        mRandom = new Random();
        mRoot = view.findViewById(R.id.relay);
        listview = (HorizontialListView) view.findViewById(R.id.list);
        mAdapter = new MemberAdapter(getActivity());
        listview.setAdapter(mAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialog(mAdapter.datas.get(i));
            }
        });
        messageList = (ListView) view.findViewById(R.id.list_message);
        messageAdapter = new MessageAdapter(getActivity());
        messageList.setAdapter(messageAdapter);
        giftView = (GiftItemView) view.findViewById(R.id.gift_item_first);
        heartLayout = (HeartLayout) view.findViewById(R.id.heart_layout);
        this.handler.postDelayed(runnable, 2000);//每5秒执行一次runnable.
        view.findViewById(R.id.send_message).setOnClickListener(this);
        view.findViewById(R.id.gift).setOnClickListener(this);
        sendView = view.findViewById(R.id.layout_send_message);
        text = view.findViewById(R.id.text_sendtext);
        menuView = view.findViewById(R.id.layout_bottom_menu);
        topView = view.findViewById(R.id.layout_top);
        sendEditText = (EditText) view.findViewById(R.id.send_edit);
        //获取屏幕高度
        screenHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;
        rootView = view.findViewById(R.id.activity_main);
        rootView.addOnLayoutChangeListener(this);
        text.setOnClickListener(this);
    }

    private void showDialog(Member m) {
        FragmentDialog.newInstance(m.name, m.sig, "确定", "取消", -1, false, new FragmentDialog.OnClickBottomListener() {
            @Override
            public void onPositiveClick() {

            }

            @Override
            public void onNegtiveClick() {

            }
        }).show(getChildFragmentManager(), "dialog");

    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (messages != null) {
                Message m = new Message();
                m.img = "http://v1.qzone.cc/avatar/201503/06/18/27/54f981200879b698.jpg%21200x200.jpg";
                m.name = CharUtils.getRandomString(8);
                m.level = (int) (Math.random() * 100 + 1);
                m.message = CharUtils.getRandomString(20);
                messages.add(m);
                messageAdapter.notifyDataSetChanged();
                messageList.setSelection(messageAdapter.getCount() - 1);
            }
            handler.postDelayed(this, 1000);
        }
    };
    Handler heartHandler = new Handler();
    Runnable heartRunnable = new Runnable() {
        @Override
        public void run() {
            heartLayout.post(new Runnable() {
                @Override
                public void run() {
                    heartLayout.addHeart(randomColor());
                }
            });
            heartHandler.postDelayed(this, 1000);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        heartHandler.removeCallbacks(heartRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        heartHandler.postDelayed(heartRunnable, 2000);
    }

    private int randomColor() {
        return Color.rgb(mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255));
    }

    /**
     * 添加一些数据
     */
    private void initData() {
        members = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            Member m = new Member();
            m.img = "http://v1.qzone.cc/avatar/201503/06/18/27/54f981200879b698.jpg%21200x200.jpg";
            m.name = "Baby";
            m.sig = "这个家伙很懒，什么都没留下！";
            members.add(m);
        }
        mAdapter.setDatas(members);


        messages = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            Message m = new Message();
            m.img = "http://www.ld12.com/upimg358/allimg/c150808/143Y5Q9254240-11513_lit.png";
            m.name = "Baby";
            m.level = i;
            m.message = "掘金是中国质量最高的技术分享社区,邀请稀土用户作为 Co-Editor 来分享优质的技术干货";
            messages.add(m);
        }
        messageAdapter.setDatas(messages);

        gifts = new ArrayList<>();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.send_message) {
            sendView.setVisibility(View.VISIBLE);
            menuView.setVisibility(View.GONE);
            topView.setVisibility(View.GONE);
            sendEditText.requestFocus();
            InputMethodManager inputManager =
                    (InputMethodManager) sendEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(sendEditText, 0);
        } else if (id == R.id.gift) {
            FragmentGiftDialog.newInstance().setOnGridViewClickListener(new FragmentGiftDialog.OnGridViewClickListener() {
                @Override
                public void click(Gift gift) {
                    if (gift.name == "wxl") {
                        if (giftAnimationPlayEnd) {
                            if (gift.num == 0) {
                                getfly();
                            } else if (gift.num == 1) {
                                getlunchuang();
                            }
                        }
                    } else {
                        gift.name = "文人骚客";
                        gift.giftName = "送你一个小礼物";
                        if (!gifts.contains(gift)) {
                            gifts.add(gift);
                            giftView.setGift(gift);
                        } else {
                            giftView.addNum(1, gift, gift.giftType);
                        }
                    }

                }
            }).show(getChildFragmentManager(), "dialog");
        } else if (id == R.id.text_sendtext) {
            Message message = new Message();
            message.setLevel(20);
            message.setName("wxl");
            message.setMessage(sendEditText.getText().toString());
            messageAdapter.datas.add(message);
        }
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
            sendView.setVisibility(View.VISIBLE);
            menuView.setVisibility(View.GONE);
            topView.setVisibility(View.GONE);
//            Toast.makeText(MainActivity.getActivity(), "监听到软键盘弹起...", Toast.LENGTH_SHORT).show();
        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
            sendView.setVisibility(View.GONE);
            menuView.setVisibility(View.VISIBLE);
            topView.setVisibility(View.VISIBLE);
//            Toast.makeText(MainActivity.getActivity(), "监听到软件盘关闭...", Toast.LENGTH_SHORT).show();
        }
    }

    //飞机动画
    public void getfly() {
        //飞机动画初始化
        giftAnimationPlayEnd = false;
        inflate = getActivity().getLayoutInflater().inflate(R.layout.fly, null);
        mRoot.addView(inflate);
        if (mHandler == null) return;
        //飞机移动到中间后延迟一秒钟,开始继续前行-x
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                ObjectAnimator plainAnimator = ObjectAnimator.ofFloat(inflate, "translationX", -inflate.getWidth());
                plainAnimator.setDuration(2000);
                plainAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (null != mRoot) {
                            mRoot.removeView(mGiftView);
                        }
                        giftAnimationPlayEnd = true;

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                plainAnimator.start();
            }
        }, 2000);
    }

    //轮船动画
    public void getlunchuang() {
        giftAnimationPlayEnd = false;
        lunchuang = getActivity().getLayoutInflater().inflate(R.layout.lunchuang, null);
        mRoot.addView(lunchuang);
        final RelativeLayout cruises = (RelativeLayout) lunchuang.findViewById(R.id.rl_live_cruises);
        //游轮开始平移动画
        cruises.animate().translationX(mScreenWidth / 2 + mScreenWidth / 3).translationY(120f).withEndAction(new Runnable() {
            @Override
            public void run() {
                if (mHandler == null) return;
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //游轮移动到中间后重新开始移动
                        cruises.animate().translationX(mScreenWidth * 2).translationY(200f).setDuration(3000)
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        //结束后从队列移除开始下一个邮轮动画
                                        if (null != mRoot) {
                                            mRoot.removeView(lunchuang);
                                        }
                                        giftAnimationPlayEnd = true;

                                    }
                                }).start();
                    }
                }, 2000);

            }
        }).setDuration(3000).start();

        //邮轮海水动画
        ImageView seaOne = (ImageView) lunchuang.findViewById(R.id.iv_live_water_one);
        ImageView seaTwo = (ImageView) lunchuang.findViewById(R.id.iv_live_water_one2);
        ObjectAnimator obj = ObjectAnimator.ofFloat(seaOne, "translationX", -50, 50);
        obj.setDuration(1000);
        obj.setRepeatCount(-1);
        obj.setRepeatMode(ObjectAnimator.REVERSE);
        obj.start();
        ObjectAnimator obj2 = ObjectAnimator.ofFloat(seaTwo, "translationX", 50, -50);
        obj2.setDuration(1000);
        obj2.setRepeatCount(-1);
        obj2.setRepeatMode(ObjectAnimator.REVERSE);
        obj2.start();
    }
}
