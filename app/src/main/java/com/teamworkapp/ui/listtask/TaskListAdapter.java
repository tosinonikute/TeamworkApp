package com.teamworkapp.ui.listtask;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teamworkapp.R;
import com.teamworkapp.data.model.task.Tag;
import com.teamworkapp.data.model.task.TodoItem;
import com.teamworkapp.ui.edittask.EditTaskActivity;
import com.teamworkapp.util.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Tosin Onikute.
 */

public class TaskListAdapter
        extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    private final Logger logger = Logger.getLogger(getClass());
    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private ArrayList<TodoItem> mTodoItem;
    private Context mContext;

    private String cProjectName;
    private String ctitle;
    private String cDesc;
    private List<Tag> ctag;
    private String ctodoName;
    private String creatorName;
    private String progress;

    private String POSITION = "position";
    private String TODOITEM = "mTodoItem";


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView projectName;
        public final TextView title;
        public final TextView moreinfo;
        public final TextView projectTitle;
        public final ViewGroup layout;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            projectName = (TextView) view.findViewById(R.id.project_name);
            title = (TextView) view.findViewById(R.id.title);
            moreinfo = (TextView) view.findViewById(R.id.moreinfo);
            projectTitle = (TextView) view.findViewById(R.id.project_title);
            layout = (ViewGroup) view.findViewById(R.id.tag_list);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public String getValueAt(int position) {
        return String.valueOf("");
    }

    public TaskListAdapter(Context context, ArrayList<TodoItem> todoItems) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mContext = context;
        mBackground = mTypedValue.resourceId;
        this.mTodoItem = todoItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_task, parent, false);
        view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        /* Set your values */
        final TodoItem model = (TodoItem) mTodoItem.get(position);

        cProjectName = "";
        ctitle = "";
        cDesc = "";
        ctag = new ArrayList<Tag>();
        ctodoName = "";
        creatorName = "";
        progress = "";


        if (model.getProjectName() != null) {
            cProjectName = model.getProjectName();
        }

        if (model.getContent() != null) {
            ctitle = model.getContent();
        }

        if (model.getDescription() != null) {
            cDesc = model.getDescription();
        }

        if (model.getTags() != null) {
            ctag = model.getTags();
        }

        if (model.getTodoListName() != null) {
            ctodoName = model.getTodoListName();
        }

        if (model.getCreatorFirstname() != null && model.getCreatorLastname() != null) {
            creatorName = mContext.getResources().getString(R.string.created_by) + " " +
                    model.getCreatorFirstname() + " " + model.getCreatorLastname() + " ";
        }

        if(model.getProgress() != null){
            progress = mContext.getResources().getString(R.string.progress) + String.valueOf(model.getProgress());
        }


        // check project names
        if(position == 0){
            holder.projectName.setVisibility(View.VISIBLE);
            holder.projectName.setText(cProjectName);
        }

        if(position > 0){
            if(mTodoItem.get(position-1).getProjectName().toString().equals(cProjectName.toString())){
                holder.projectName.setVisibility(View.GONE);
            } else {
                holder.projectName.setVisibility(View.VISIBLE);
                holder.projectName.setText(cProjectName);
            }

        } else {
            holder.projectName.setText(cProjectName);
        }


        holder.title.setText(ctitle);
        holder.moreinfo.setText(ctodoName + " | " + creatorName + progress);
        holder.projectTitle.setText(mContext.getResources().getString(R.string.project) + cProjectName);

        holder.layout.removeAllViews();
        for(int i = 0; i<ctag.size(); i++) {

            TextView tagTextView = new TextView(mContext);
            tagTextView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            llp.setMargins(15, 0, 5, 5);

            tagTextView.setText(ctag.get(i).getName());
            tagTextView.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            tagTextView.setTextSize(10);
            tagTextView.setPadding(5, 5, 5, 5);
            tagTextView.setGravity(Gravity.LEFT);
            tagTextView.setBackgroundColor(mContext.getResources().getColor(R.color.colorAsh));
            tagTextView.setLayoutParams(llp);
            holder.layout.addView(tagTextView);
        }


        // launch the edit task activity to show Task information
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, EditTaskActivity.class);
                intent.putExtra(POSITION, holder.getAdapterPosition());
                intent.putExtra(TODOITEM, mTodoItem);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != mTodoItem ? mTodoItem.size() : 0);
    }

    public void addAll(List<TodoItem> data){
        notifyDataSetChanged();
    }

    public void add(TodoItem data){
        notifyDataSetChanged();
        mTodoItem.add(data);
    }


    public TodoItem getItemPos(int pos){
        return mTodoItem.get(pos);
    }

    public void clear(){
        mTodoItem.clear();
    }

}

