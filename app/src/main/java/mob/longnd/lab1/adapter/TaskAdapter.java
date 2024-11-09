package mob.longnd.lab1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mob.longnd.lab1.R;
import mob.longnd.lab1.model.Task;

public class TaskAdapter extends BaseAdapter {

    ArrayList<Task> list;
    Context context;

    public TaskAdapter(ArrayList<Task> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public  class ViewHolder{
        TextView tvTitle,tvContent,tvDate;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from((Activity)context).inflate(R.layout.item_task,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle=convertView.findViewById(R.id.tvTitle);
            viewHolder.tvContent=convertView.findViewById(R.id.tvContent);
            viewHolder.tvDate=convertView.findViewById(R.id.tvDate);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Task task = list.get(position);
        viewHolder.tvTitle.setText(task.getTitle());
        viewHolder.tvContent.setText(task.getContent());
        viewHolder.tvDate.setText(task.getDate());
        return convertView;
    }
}
