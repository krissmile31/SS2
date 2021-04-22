package com.example.myui.favourite.friends;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myui.MainActivity;
import com.example.myui.R;
import com.example.myui.favourite.Favourite;
import com.example.myui.favourite.friends.adapters.FriendAdapter;
import com.example.myui.models.Friend;

import java.util.ArrayList;
import java.util.List;

public class FriendActivity extends AppCompatActivity {
    public static final int  FRIEND_ADD = 1;
    public static final int  FRIEND_EDIT = 2;
    private RecyclerView recyclerView;
    private List<Friend> friends;
    private FriendAdapter friendAdapter;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        recyclerView = findViewById(R.id.recycleView);

        friends = new ArrayList<>();
        friends.add(new Friend("Ngọc Ánh", "⛈", "krissmile31","profile.php?id=100010494165637","1801040015@s.hanu.edu.vn", "0987683572"));
        friends.add(new Friend("Minh Phượng", "aya", "chigaixinh","lethiminhphuongggg","1801040171@s.hanu.edu.vn", "0657687299"));
        friends.add(new Friend("Thảo Nguyễn", "thaocon", "thaor_ddaay","profile.php?id=100011387482093","1801040209@s.hanu.edu.vn", "0213455467"));
        friends.add(new Friend("Chibii Thùy", "chibii", "lobe_20", "profile.php?id=100011272230055","1801040216@s.hanu.edu.vn", "0354676878"));

        friendAdapter = new FriendAdapter(friends);
        recyclerView.setAdapter(friendAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendActivity.this, AddFriendActivity.class);
                startActivityForResult(intent, FRIEND_ADD);
            }
        });
        friendAdapter.setOnEditClickListener(new FriendAdapter.OnEditClickListener() {
            @Override
            public void onEditClick(Friend friend) {
                position = friends.indexOf(friend);
                Intent intent = new Intent(FriendActivity.this, EditFriendActivity.class);
                intent.putExtra(EditFriendActivity.EXTRA_NAME, friend.getName());
                intent.putExtra(EditFriendActivity.EXTRA_NICKAME, friend.getNickname());
                intent.putExtra(EditFriendActivity.EXTRA_INSTA, friend.getInsta());
                intent.putExtra(EditFriendActivity.EXTRA_FB, friend.getFb());
                intent.putExtra(EditFriendActivity.EXTRA_EMAIL, friend.getEmail());
                intent.putExtra(EditFriendActivity.EXTRA_PHONENO, friend.getPhoneNo());
                startActivityForResult(intent, FRIEND_EDIT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == FRIEND_ADD) {
            Friend friend = (Friend) data.getSerializableExtra("ADD_FRIEND");
            friends.add(0,friend);
            friendAdapter.notifyItemInserted(0);
            friendAdapter.notifyDataSetChanged();
        }

        else if (resultCode == RESULT_OK && requestCode == FRIEND_EDIT) {
            Friend friend = (Friend) data.getSerializableExtra("EDIT_FRIEND");
            friends.set(position, friend);

            friendAdapter.notifyDataSetChanged();
        }
    }
}
