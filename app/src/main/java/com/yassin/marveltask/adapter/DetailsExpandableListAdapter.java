package com.yassin.marveltask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yassin.marveltask.R;
import com.yassin.marveltask.model.ComicsItems;
import com.yassin.marveltask.model.ExpandableList;

import java.util.ArrayList;
import java.util.List;

public class DetailsExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<ExpandableList> expandableLists = new ArrayList<ExpandableList>();

    public DetailsExpandableListAdapter(Context context, List<ExpandableList> expandableLists) {
        this._context = context;
        this.expandableLists = expandableLists;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return expandableLists.get(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final ComicsItems movie = (ComicsItems) getChild(groupPosition, childPosition);
        ChildHolder childHolder = null;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_group_child, parent, false);
            childHolder = new ChildHolder();
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        childHolder.horizontalListView = convertView.findViewById(R.id.details);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this._context, LinearLayoutManager.HORIZONTAL, false);
        childHolder.horizontalListView.setLayoutManager(layoutManager);

        DetailsAdapter horizontalListAdapter = new DetailsAdapter(this._context,
                expandableLists.get(groupPosition).getComicsItem());

        childHolder.horizontalListView.setAdapter(horizontalListAdapter);


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return expandableLists.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return expandableLists.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        ParentHolder parentHolder = null;

        ExpandableList expandableList = (ExpandableList) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater userInflater = (LayoutInflater) this._context.getSystemService(this._context.LAYOUT_INFLATER_SERVICE);
            convertView = userInflater.inflate(R.layout.parent_group, null);
            convertView.setHorizontalScrollBarEnabled(true);
            parentHolder = new ParentHolder();
            convertView.setTag(parentHolder);

        } else {
            parentHolder = (ParentHolder) convertView.getTag();
        }

        parentHolder.moviesType = convertView.findViewById(R.id.lblListHeader);
        parentHolder.moviesType.setText(expandableList.getHeader());
        parentHolder.indicator = convertView.findViewById(R.id.image_indicator);

        if (isExpanded) {
            parentHolder.indicator.setImageResource(R.drawable.ic_keyboard_arrow_up_black_18dp);
        } else {
            parentHolder.indicator.setImageResource(R.drawable.ic_keyboard_arrow_down_black_18dp);
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private static class ChildHolder {
        static RecyclerView horizontalListView;
    }

    private static class ParentHolder {
        TextView moviesType;
        ImageView indicator;
    }
}
