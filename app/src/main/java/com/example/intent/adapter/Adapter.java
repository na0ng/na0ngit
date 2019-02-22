package com.example.intent.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intent.R;
import com.example.intent.activity.RecycleViewActivity;
import com.example.intent.item.RecyclerItemForm;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Activity activity; // private Context context; 대처 가능. Context가 Activity 품고있음
    private ArrayList<RecyclerItemForm> adata = new ArrayList<>(); // null 방지

    public Adapter(RecycleViewActivity recycleViewActivity, ArrayList<RecyclerItemForm> data) {
        this.activity = recycleViewActivity;
        this.adata = data;
    }

    /*
    * 순서 : getItemCount > onCreateViewHolder > ViewHolder > onBindViewHolder
    *       뷰홀더에서 초기 셋팅 > 바인드뷰홀더에서 셋 텍스트/이미지의 형태로 최종 출력
    */

    @NonNull
    @Override
    /*
    * Q. ViewGroup viewGroup ? ViewGroup parent ?
    * */
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup a, int i) {
        // rv_item_layout 기반으로 뷰 생성
        View view = LayoutInflater.from(a.getContext()).inflate(R.layout.rv_item_layout,a,false);
        return new ViewHolder(view);

        /*
        *  뷰홀더 생성부분 생략안한 ver.
        *  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_layout,parent,false);
        *  ViewHolder viewHolder = new ViewHolder(view);
        *  return viewHolder;
        * */
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, int i) {

        viewHolder.textView.setText(adata.get(i).getMsg());
        viewHolder.imageView.setImageResource(adata.get(i).getImg());
    }

    @Override
    public int getItemCount() { return adata.size(); }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView7);

            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //각 item들 누르면 토스트 메시지 뜸
                    Toast.makeText(activity, "안농:)", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}