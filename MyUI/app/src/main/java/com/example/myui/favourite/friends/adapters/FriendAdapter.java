package com.example.myui.favourite.friends.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myui.R;
import com.example.myui.models.Friend;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendHolder> {

    private List<Friend> friendList;
    private OnEditClickListener listener;

    public FriendAdapter(List<Friend> friendList) {
        this.friendList = friendList;
    }

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View itemView = layoutInflater.inflate(R.layout.item_friend, parent, false);
        return new FriendHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, int position) {
        holder.bind(friendList.get(position));
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    public class FriendHolder extends RecyclerView.ViewHolder {
        private Context context;
        private TextView name, nickname;
        private ImageView facebook, instagram, mail, message, call, deleteBtn, edit, editBtn, yes, no;

        public FriendHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;

            name = itemView.findViewById(R.id.ggname);
            nickname = itemView.findViewById(R.id.nickname);
            facebook = itemView.findViewById(R.id.facebook);
            instagram = itemView.findViewById(R.id.instagram);
            mail = itemView.findViewById(R.id.ggemail);
            message = itemView.findViewById(R.id.message);
            call = itemView.findViewById(R.id.call);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            editBtn = itemView.findViewById(R.id.editBtn);
            edit = itemView.findViewById(R.id.edit);
            yes = itemView.findViewById(R.id.yes);
            no = itemView.findViewById(R.id.no);
        }

        public void bind(Friend friend) {
            name.setText(friend.getName());
            nickname.setText(friend.getNickname());

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel: " + friend.getPhoneNo()));
                    context.startActivity(intent);
                }
            });

            message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("smsto: " + friend.getPhoneNo()));
                    context.startActivity(intent);
                }
            });

            mail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent((Intent.ACTION_SENDTO));
                    intent.setData(Uri.parse("mailto: " + Uri.encode(friend.getEmail())));
                    context.startActivity(intent);
                }
            });

            instagram.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent((Intent.ACTION_VIEW));
                    intent.setData(Uri.parse("http://instagram.com/" + friend.getInsta()));
                    context.startActivity(intent);
                }
            });

            facebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent((Intent.ACTION_VIEW));
                    intent.setData(Uri.parse("https://www.facebook.com/" + friend.getFb()));
                    context.startActivity(intent);
                }
            });

            itemView.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteBtn.animate().alpha(1f).setDuration(500);
                    yes.animate().alpha(1f).setDuration(500);
                    no.animate().alpha(1f).setDuration(500);
                    facebook.animate().alpha(0f).setDuration(500);
                    instagram.animate().alpha(0f).setDuration(500);
                    mail.animate().alpha(0f).setDuration(500);
                    message.animate().alpha(0f).setDuration(500);
                    call.animate().alpha(0f).setDuration(500);

                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new AlertDialog.Builder(context)
                                    .setIcon(R.drawable.info)
                                    .setTitle("Are you sure to remove this friend>.<?")
                                    .setNegativeButton("No", null)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            int position = friendList.indexOf(friend);
                                            friendList.remove(friend);
                                            notifyItemRemoved(position);
                                        }
                                    }).show();
                        }
                    });

                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            deleteBtn.animate().alpha(0f).setDuration(500);
                            yes.animate().alpha(0f).setDuration(500);
                            no.animate().alpha(0f).setDuration(500);
                            facebook.animate().alpha(1f).setDuration(500);
                            instagram.animate().alpha(1f).setDuration(500);
                            mail.animate().alpha(1f).setDuration(500);
                            message.animate().alpha(1f).setDuration(500);
                            call.animate().alpha(1f).setDuration(500);
                        }
                    });

                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editBtn.animate().alpha(1f).setDuration(500);
                    yes.animate().alpha(1f).setDuration(500);
                    no.animate().alpha(1f).setDuration(500);
                    facebook.animate().alpha(0f).setDuration(500);
                    instagram.animate().alpha(0f).setDuration(500);
                    mail.animate().alpha(0f).setDuration(500);
                    message.animate().alpha(0f).setDuration(500);
                    call.animate().alpha(0f).setDuration(500);

                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editBtn.animate().alpha(0f).setDuration(500);
                            yes.animate().alpha(0f).setDuration(500);
                            no.animate().alpha(0f).setDuration(500);
                            facebook.animate().alpha(1f).setDuration(500);
                            instagram.animate().alpha(1f).setDuration(500);
                            mail.animate().alpha(1f).setDuration(500);
                            message.animate().alpha(1f).setDuration(500);
                            call.animate().alpha(1f).setDuration(500);

                            int position = getAdapterPosition();
                            if (listener != null && position != RecyclerView.NO_POSITION)
                                listener.onEditClick(friendList.get(position));
                        }
                    });

                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editBtn.animate().alpha(0f).setDuration(500);
                            yes.animate().alpha(0f).setDuration(500);
                            no.animate().alpha(0f).setDuration(500);
                            facebook.animate().alpha(1f).setDuration(500);
                            instagram.animate().alpha(1f).setDuration(500);
                            mail.animate().alpha(1f).setDuration(500);
                            message.animate().alpha(1f).setDuration(500);
                            call.animate().alpha(1f).setDuration(500);
                        }
                    });
                }
            });
        }
    }

    public interface OnEditClickListener {
        void onEditClick(Friend friend);
    }

    public void setOnEditClickListener(OnEditClickListener listener){
        this.listener = listener;
    }


}